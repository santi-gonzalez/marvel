package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.Matcher;
import net.sgonzalez.example.data.datasource.impl.ComicCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicLocalDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ItemEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class ComicRepository
extends AbsRepository {
  private final ComicLocalDataSource comicLocalDataSource;
  private final ComicCloudDataSource comicCloudDataSource;

  @Inject public ComicRepository(ComicLocalDataSource comicLocalDataSource, ComicCloudDataSource comicCloudDataSource) {
    super(new TimeCachePolicy());
    this.comicLocalDataSource = comicLocalDataSource;
    this.comicCloudDataSource = comicCloudDataSource;
  }

  public void retrieveStoredByCharacterName(final String characterName, @NonNull Callbacks<List<ComicEntity>> callbacks) {
    comicLocalDataSource.getAll(new Matcher<ComicEntity>() {
      @Override public boolean isValid(ComicEntity entity) {
        for (ItemEntity itemEntity : entity.getCharacters().getItems()) {
          if (itemEntity.getName().equals(characterName)) {
            return true;
          }
        }
        return false;
      }
    }, callbacks);
  }

  public void retrieveNextPageByCharacterId(final long characterId,
                                            @NonNull final Callbacks<PageResult<List<ComicEntity>>> callbacks) {
    comicLocalDataSource.count(characterId, new Callbacks<Integer>() {
      @Override public void onDone(Integer offset) {
        final ByCharacterIdRequest byCharacterIdRequest = new ByCharacterIdRequest(characterId, offset);
        comicCloudDataSource.retrieveByCharacterId(byCharacterIdRequest, new Callbacks<PageResult<List<ComicEntity>>>() {
          @Override public void onDone(final PageResult<List<ComicEntity>> result) {
            comicLocalDataSource.store(byCharacterIdRequest.characterId, result.result, new Callbacks<List<ComicEntity>>() {
              @Override public void onDone(List<ComicEntity> entities) {
                callbacks.onDone(result);
              }

              @Override public void onError(@NonNull Exception exception) {
                callbacks.onError(exception);
                callbacks.onDone(result);
              }
            });
          }

          @Override public void onError(@NonNull Exception exception) {
            callbacks.onError(exception);
          }
        });
      }

      @Override public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    });
  }

  public void clearInLocalByCharacterId(long characterId, @NonNull final Callbacks<Void> callbacks) {
    comicLocalDataSource.clear(characterId, callbacks);
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
