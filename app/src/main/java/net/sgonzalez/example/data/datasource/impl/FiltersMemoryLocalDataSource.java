package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.datasource.AbsMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;

@ApplicationScope public class FiltersMemoryLocalDataSource extends AbsMemoryLocalDataSource<String, FilterEntity> {
  @Inject
  public FiltersMemoryLocalDataSource() {
    super();
  }

  public void retrieveById(@NonNull final String id, @NonNull final RetrieveCallbacks<FilterEntity> callbacks) {
    callbacks.onResult(memory().get(id));
  }

  public void store(@NonNull final FilterEntity entity, @NonNull final StoreCallbacks<FilterEntity> callbacks) {
    memory().put(entity.getId()
                       .get(), entity);
  }
}
