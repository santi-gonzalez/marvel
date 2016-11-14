package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;

@ApplicationScope public class FilterMemoryLocalDataSource extends AbsMemoryLocalDataSource<String, FilterEntity> {
  @Inject
  public FilterMemoryLocalDataSource() {
  }

  public void retrieveById(@NonNull final String id, @NonNull final Callbacks<FilterEntity> callbacks) {
    callbacks.onDone(memory().get(id));
  }

  public void store(@NonNull final FilterEntity entity, @NonNull final Callbacks<FilterEntity> callbacks) {
    memory().put(entity.getId(), entity);
    callbacks.onDone(entity);
  }
}
