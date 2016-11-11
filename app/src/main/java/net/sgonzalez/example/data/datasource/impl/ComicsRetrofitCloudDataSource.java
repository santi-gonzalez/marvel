package net.sgonzalez.example.data.datasource.impl;

import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.retrofit.response.ComicResponse;
import net.sgonzalez.example.app.retrofit.retrofit.service.ComicService;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import retrofit2.Call;
import retrofit2.Retrofit;

@ApplicationScope public class ComicsRetrofitCloudDataSource extends AbsRetrofitCloudDataSource {
  private final ComicService comicService;

  @Inject
  public ComicsRetrofitCloudDataSource(Retrofit retrofit, ComicService comicService) {
    super(retrofit);
    this.comicService = comicService;
  }

  public void retrieveByCharacterId(long characterId, RetrieveCallbacks<List<ComicEntity>> callbacks) {
    try {
      Call<ComicResponse> request = comicService.getComicsByCharacterId(characterId);
      ComicResponse response = executeRequest(request);
      callbacks.onResult(response.data.results);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
