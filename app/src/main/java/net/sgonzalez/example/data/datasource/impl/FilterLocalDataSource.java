package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsIdMapDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;

@ApplicationScope public class FilterLocalDataSource
extends AbsIdMapDataSource<String, FilterEntity> {
  @Inject public FilterLocalDataSource() {
  }

  public void retrieveById(@NonNull final String id, @NonNull final Callbacks<FilterEntity> callbacks) {
    callbacks.onResult(memory().get(id));
  }

  public void store(@NonNull final FilterEntity entity, @NonNull final Callbacks<FilterEntity> callbacks) {
    memory().put(entity.getId(), entity);
    callbacks.onResult(entity);
  }
}
