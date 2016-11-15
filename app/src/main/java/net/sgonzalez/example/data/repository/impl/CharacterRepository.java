package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.impl.CharacterMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class CharacterRepository extends AbsRepository {
  private final CharacterMemoryLocalDataSource characterMemoryLocalDataSource;
  private final CharacterRetrofitCloudDataSource characterRetrofitCloudDataSource;

  @Inject
  public CharacterRepository(CharacterMemoryLocalDataSource characterMemoryLocalDataSource,
                             CharacterRetrofitCloudDataSource characterRetrofitCloudDataSource) {
    super(new TimeCachePolicy());
    this.characterMemoryLocalDataSource = characterMemoryLocalDataSource;
    this.characterRetrofitCloudDataSource = characterRetrofitCloudDataSource;
  }

  public void retrieveById(long characterId, @NonNull Callbacks<CharacterEntity> callbacks) {
    characterMemoryLocalDataSource.get(characterId, callbacks);
  }

  public void retrieveStored(final @NonNull Callbacks<List<CharacterEntity>> callbacks) {
    characterMemoryLocalDataSource.getAll(callbacks);
  }

  public void retrieveNextPage(final @NonNull Callbacks<List<CharacterEntity>> callbacks) {
    characterMemoryLocalDataSource.count(new Callbacks<Integer>() {
      @Override
      public void onDone(Integer offset) {
        characterRetrofitCloudDataSource.retrieve(offset, new Callbacks<List<CharacterEntity>>() {
          @Override
          public void onDone(final List<CharacterEntity> entities) {
            characterMemoryLocalDataSource.store(entities, new Callbacks<List<CharacterEntity>>() {
              @Override
              public void onDone(List<CharacterEntity> entities) {
                callbacks.onDone(entities);
              }

              @Override
              public void onError(@NonNull Exception exception) {
                callbacks.onError(exception);
                callbacks.onDone(entities);
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
}
