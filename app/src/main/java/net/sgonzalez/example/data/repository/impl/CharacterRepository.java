package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.datasource.impl.CharacterMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;
import net.sgonzalez.example.data.repository.operation.Operation;
import net.sgonzalez.example.data.repository.operation.impl.CloudOperation;
import net.sgonzalez.example.data.repository.operation.impl.LocalOperation;

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

  public void retrieveFromCloud(final @NonNull RetrieveCallbacks<List<CharacterEntity>> callbacks) {
    new LocalOperation<>(null, new RetrieveCallbacks<Integer>() {
      @Override
      public void onResult(Integer offset) {
        new CloudOperation<>(offset, callbacks, createRetrieveFromCloudOperation()).run();
      }

      @Override
      public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    }, createCountFromLocalOperationCallbacks()).run();
  }

  @NonNull
  private Operation.OperationCallbacks<Integer, List<CharacterEntity>> createRetrieveFromCloudOperation() {
    return new CloudOperation.OperationCallbacks<Integer, List<CharacterEntity>>() {
      @Override
      public void retrieveFromCloud(Integer offset, @NonNull final RetrieveCallbacks<List<CharacterEntity>> callbacks) {
        characterRetrofitCloudDataSource.retrieve(offset, new RetrieveCallbacks<List<CharacterEntity>>() {
          @Override
          public void onResult(final List<CharacterEntity> entities) {
            characterMemoryLocalDataSource.store(entities, new StoreCallbacks<List<CharacterEntity>>() {
              @Override
              public void onStore(List<CharacterEntity> entities) {
                callbacks.onResult(entities);
              }

              @Override
              public void onError(@NonNull Exception exception) {
                callbacks.onError(exception);
                callbacks.onResult(entities);
              }
            });
          }

          @Override
          public void onError(@NonNull Exception exception) {
            callbacks.onError(exception);
          }
        });
      }
    };
  }

  @NonNull
  private Operation.OperationCallbacks<Void, Integer> createCountFromLocalOperationCallbacks() {
    return new LocalOperation.OperationCallbacks<Void, Integer>() {
      @Override
      public void retrieveFromLocal(Void aVoid, @NonNull RetrieveCallbacks<Integer> callbacks) {
        characterMemoryLocalDataSource.count(callbacks);
      }
    };
  }
}
