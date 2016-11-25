package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.CallbacksAdapter;
import net.sgonzalez.example.data.datasource.impl.CharacterCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterLocalDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class CharacterRepository
extends AbsRepository {
  private final CharacterLocalDataSource characterLocalDataSource;
  private final CharacterCloudDataSource characterCloudDataSource;

  @Inject
  public CharacterRepository(CharacterLocalDataSource characterLocalDataSource,
                             CharacterCloudDataSource characterCloudDataSource) {
    super(new TimeCachePolicy());
    this.characterLocalDataSource = characterLocalDataSource;
    this.characterCloudDataSource = characterCloudDataSource;
  }

  public void retrieveById(long characterId, @NonNull Callbacks<CharacterEntity> callbacks) {
    characterLocalDataSource.get(characterId, callbacks);
  }

  public void retrieveStored(final @NonNull Callbacks<List<CharacterEntity>> callbacks) {
    characterLocalDataSource.getAll(callbacks);
  }

  public void retrieveNextPage(final @NonNull Callbacks<List<CharacterEntity>> callbacks) {
    characterLocalDataSource.count(new CallbacksAdapter<Integer>(callbacks) {

      @Override public void onResult(Integer offset) {
        characterCloudDataSource.retrieve(offset, new CallbacksAdapter<List<CharacterEntity>>(callbacks) {

          @Override public void onResult(final List<CharacterEntity> entities) {
            characterLocalDataSource.store(entities, new CallbacksAdapter<List<CharacterEntity>>(callbacks) {

            });
          }
        });
      }
    });
  }
}
