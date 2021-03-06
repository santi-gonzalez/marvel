package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import retrofit2.Retrofit;

@ApplicationScope public class FilterCloudDataSource
extends AbsRetrofitDataSource {
  @Inject public FilterCloudDataSource(Retrofit retrofit) {
    super(retrofit);
  }

  public void retrieveById(@NonNull String id, @NonNull Callbacks<FilterEntity> callbacks) {
    try {
      FilterEntity result = new FilterEntity(id, "key-" + id, "value-" + id);
      callbacks.onResult(result);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
