package net.sgonzalez.example.data.datasource.impl;

import android.util.Log;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.response.ComicResponse;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.app.retrofit.service.ComicService;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import retrofit2.Call;
import retrofit2.Retrofit;

@ApplicationScope public class ComicCloudDataSource
extends AbsRetrofitDataSource {
  private final ComicService comicService;

  @Inject public ComicCloudDataSource(Retrofit retrofit, ComicService comicService) {
    super(retrofit);
    this.comicService = comicService;
  }

  public void retrieveByCharacterId(long characterId, int offset, Callbacks<PageResult<List<ComicEntity>>> callbacks) {
    try {
      Call<ComicResponse> request = comicService.getComicsByCharacterId(characterId, offset);
      ComicResponse response = executeRequest(request);
      Log.w("", "offset" + offset);
      Log.w("", "count" + response.data.count);
      Log.w("", "total" + response.data.total);
      boolean bottomReached = offset + response.data.count >= response.data.total;
      PageResult<List<ComicEntity>> pageResult = new PageResult<>(response.data.results, bottomReached);
      callbacks.onResult(pageResult);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
