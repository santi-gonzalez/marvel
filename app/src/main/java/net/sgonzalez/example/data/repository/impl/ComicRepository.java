package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.impl.ComicMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class ComicRepository extends AbsRepository {
  private final ComicMemoryLocalDataSource comicMemoryLocalDataSource;
  private final ComicRetrofitCloudDataSource comicRetrofitCloudDataSource;

  @Inject
  public ComicRepository(ComicMemoryLocalDataSource comicMemoryLocalDataSource, ComicRetrofitCloudDataSource comicRetrofitCloudDataSource) {
    super(new TimeCachePolicy());
    this.comicMemoryLocalDataSource = comicMemoryLocalDataSource;
    this.comicRetrofitCloudDataSource = comicRetrofitCloudDataSource;
  }

  public void retrieveStoredByCharacterId(final long characterId, @NonNull final Callbacks<List<ComicEntity>> callbacks) {
    comicMemoryLocalDataSource.getAll(new Callbacks<List<ComicEntity>>() {
      @Override
      public void onDone(List<ComicEntity> result) {
        List<ComicEntity> matches = new ArrayList<>();
        for (ComicEntity entity : result) {
          if (characterId == entity.getId()) {
            matches.add(entity);
          }
        }
        callbacks.onDone(matches);
      }

      @Override
      public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    });
  }

  public void retrieveNextPageByCharacterId(final long characterId, @NonNull final Callbacks<PageResult<List<ComicEntity>>> callbacks) {
    comicMemoryLocalDataSource.count(characterId, new Callbacks<Integer>() {
      @Override
      public void onDone(Integer offset) {
        final ByCharacterIdRequest byCharacterIdRequest = new ByCharacterIdRequest(characterId, offset);
        comicRetrofitCloudDataSource.retrieveByCharacterId(byCharacterIdRequest, new Callbacks<PageResult<List<ComicEntity>>>() {
          @Override
          public void onDone(final PageResult<List<ComicEntity>> result) {
            comicMemoryLocalDataSource.append(byCharacterIdRequest.characterId, result.result, new Callbacks<List<ComicEntity>>() {
              @Override
              public void onDone(List<ComicEntity> entities) {
                callbacks.onDone(result);
              }

              @Override
              public void onError(@NonNull Exception exception) {
                callbacks.onError(exception);
                callbacks.onDone(result);
              }
            });
          }

          @Override
          public void onError(@NonNull Exception exception) {
            callbacks.onError(exception);
          }
        });
      }

      @Override
      public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    });
  }

  public void clearInLocalByCharacterId(long characterId, @NonNull final Callbacks<Void> callbacks) {
    comicMemoryLocalDataSource.clear(characterId, callbacks);
  }

  public static final class ByCharacterIdRequest {
    public final long characterId;
    public final int offset;

    public ByCharacterIdRequest(long characterId, int offset) {
      this.characterId = characterId;
      this.offset = offset;
    }
  }
}
