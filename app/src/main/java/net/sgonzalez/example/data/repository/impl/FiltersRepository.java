package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.datasource.impl.FiltersFakeCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FiltersRealmLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;
import net.sgonzalez.example.data.repository.operation.Operation;
import net.sgonzalez.example.data.repository.operation.impl.LocalOrCloudOperation;

@ApplicationScope public class FiltersRepository extends AbsRepository {
  private final FiltersRealmLocalDataSource filtersRealmLocalDataSource;
  private final FiltersFakeCloudDataSource filtersFakeCloudDataSource;

  @Inject
  public FiltersRepository(FiltersRealmLocalDataSource filtersRealmLocalDataSource,
                           FiltersFakeCloudDataSource filtersFakeCloudDataSource) {
    super(new TimeCachePolicy());
    this.filtersRealmLocalDataSource = filtersRealmLocalDataSource;
    this.filtersFakeCloudDataSource = filtersFakeCloudDataSource;
  }

  public final void retrieveById(@NonNull final String id, @NonNull final RetrieveCallbacks<FilterEntity> callbacks) {
    Operation.OperationCallbacks<String, FilterEntity> operationCallbacks =
    new LocalOrCloudOperation.OperationCallbacks<String, FilterEntity>() {
      @Override
      public void retrieveFromLocal(String id, @NonNull RetrieveCallbacks<FilterEntity> callbacks) {
        retrieveFromLocalById(id, callbacks);
      }

      @Override
      public void retrieveFromCloud(String id, @NonNull RetrieveCallbacks<FilterEntity> callbacks) {
        retrieveFromCloudById(id, callbacks);
      }

      @Override
      public void storeInLocal(FilterEntity entity, @NonNull StoreCallbacks<FilterEntity> callbacks) {
        store(entity, callbacks);
      }
    };
    new LocalOrCloudOperation<>(id, getCachePolicy(), callbacks, operationCallbacks).run();
  }

  private void retrieveFromLocalById(@NonNull String id, @NonNull final RetrieveCallbacks<FilterEntity> callbacks) {
    filtersRealmLocalDataSource.retrieveById(id, callbacks);
  }

  private void retrieveFromCloudById(@NonNull String id, @NonNull final RetrieveCallbacks<FilterEntity> callbacks) {
    filtersFakeCloudDataSource.retrieveById(id, callbacks);
  }

  private void store(@NonNull FilterEntity entity, @NonNull StoreCallbacks<FilterEntity> callbacks) {
    filtersRealmLocalDataSource.store(entity, callbacks);
  }
}
