package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.app.identifier.impl.StringId;
import retrofit2.Retrofit;

@ApplicationScope public class FiltersFakeCloudDataSource extends AbsRetrofitCloudDataSource {
  @Inject
  public FiltersFakeCloudDataSource(Retrofit retrofit) {
    super(retrofit);
  }

  public void retrieveById(@NonNull String id, @NonNull RetrieveCallbacks<FilterEntity> callbacks) {
    try {
      FilterEntity result = new FilterEntity();
      result.setId(new StringId(id));
      result.setKey("key-" + id);
      result.setValue("value-" + id);
      callbacks.onResult(result);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
