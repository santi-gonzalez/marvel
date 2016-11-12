package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.datasource.impl.ComicsRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.repository.AbsRepository;
import net.sgonzalez.example.data.repository.operation.Operation;
import net.sgonzalez.example.data.repository.operation.impl.CloudOperation;

@ApplicationScope public class ComicRepository extends AbsRepository {
  private final ComicsRetrofitCloudDataSource comicsRetrofitCloudDataSource;

  @Inject
  public ComicRepository(ComicsRetrofitCloudDataSource comicsRetrofitCloudDataSource) {
    super(new TimeCachePolicy());
    this.comicsRetrofitCloudDataSource = comicsRetrofitCloudDataSource;
  }

  public void retrieveByCharacterId(long characterId, @NonNull RetrieveCallbacks<List<ComicEntity>> callbacks) {
    final Operation.OperationCallbacks<Long, List<ComicEntity>> operationCallbacks =
    new CloudOperation.OperationCallbacks<Long, List<ComicEntity>>() {
      @Override
      public void retrieveFromCloud(Long characterId, @NonNull RetrieveCallbacks<List<ComicEntity>> callbacks) {
        comicsRetrofitCloudDataSource.retrieveByCharacterId(characterId, callbacks);
      }
    };
    new CloudOperation<>(characterId, callbacks, operationCallbacks).run();
  }
}
