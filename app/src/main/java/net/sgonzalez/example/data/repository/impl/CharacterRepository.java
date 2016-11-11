package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;
import net.sgonzalez.example.data.repository.operation.Operation;
import net.sgonzalez.example.data.repository.operation.impl.CloudOperation;

@ApplicationScope public class CharacterRepository extends AbsRepository {
  private final CharacterRetrofitCloudDataSource characterRetrofitCloudDataSource;

  @Inject
  public CharacterRepository(CharacterRetrofitCloudDataSource characterRetrofitCloudDataSource) {
    super(new TimeCachePolicy());
    this.characterRetrofitCloudDataSource = characterRetrofitCloudDataSource;
  }

  public void retrieve(RetrieveCallbacks<List<CharacterEntity>> callbacks) {
    Operation.OperationCallbacks<Void, List<CharacterEntity>> operationCallbacks =
    new CloudOperation.OperationCallbacks<Void, List<CharacterEntity>>() {
      @Override
      public void retrieveFromCloud(Void aVoid, @NonNull RetrieveCallbacks<List<CharacterEntity>> callbacks) {
        characterRetrofitCloudDataSource.retrieve(callbacks);
      }
    };
    new CloudOperation<>(null, callbacks, operationCallbacks).run();
  }
}
