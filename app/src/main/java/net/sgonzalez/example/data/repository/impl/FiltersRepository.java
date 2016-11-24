package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.impl.FilterCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class FiltersRepository
extends AbsRepository {
  private final FilterLocalDataSource filterLocalDataSource;
  private final FilterCloudDataSource filterCloudDataSource;

  @Inject public FiltersRepository(FilterLocalDataSource filterLocalDataSource, FilterCloudDataSource filterCloudDataSource) {
    super(new TimeCachePolicy());
    this.filterLocalDataSource = filterLocalDataSource;
    this.filterCloudDataSource = filterCloudDataSource;
  }

  public final void retrieveById(@NonNull final String id, @NonNull final Callbacks<FilterEntity> callbacks) {
  }
}
