package net.sgonzalez.example.data.repository.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.cache.impl.TimeCachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.impl.FilterFakeCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.data.repository.AbsRepository;

@ApplicationScope public class FiltersRepository extends AbsRepository {
  private final FilterMemoryLocalDataSource filterMemoryLocalDataSource;
  private final FilterFakeCloudDataSource filterFakeCloudDataSource;

  @Inject
  public FiltersRepository(FilterMemoryLocalDataSource filterMemoryLocalDataSource, FilterFakeCloudDataSource filterFakeCloudDataSource) {
    super(new TimeCachePolicy());
    this.filterMemoryLocalDataSource = filterMemoryLocalDataSource;
    this.filterFakeCloudDataSource = filterFakeCloudDataSource;
  }

  public final void retrieveById(@NonNull final String id, @NonNull final Callbacks<FilterEntity> callbacks) {
  }
}
