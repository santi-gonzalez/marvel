package net.sgonzalez.example.data.datasource.impl;

import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.response.ComicResponse;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.app.retrofit.service.ComicService;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.repository.impl.ComicRepository;
import retrofit2.Call;
import retrofit2.Retrofit;

@ApplicationScope public class ComicRetrofitCloudDataSource extends AbsRetrofitCloudDataSource {
  private final ComicService comicService;

  @Inject
  public ComicRetrofitCloudDataSource(Retrofit retrofit, ComicService comicService) {
    super(retrofit);
    this.comicService = comicService;
  }

  public void retrieveByCharacterId(ComicRepository.ByCharacterIdRequest byCharacterIdRequest,
                                    Callbacks<PageResult<List<ComicEntity>>> callbacks) {
    try {
      Call<ComicResponse> request = comicService.getComicsByCharacterId(byCharacterIdRequest.characterId, byCharacterIdRequest.offset);
      ComicResponse response = executeRequest(request);
      boolean bottomReached = byCharacterIdRequest.offset + response.data.count >= response.data.total;
      PageResult<List<ComicEntity>> pageResult = new PageResult<>(response.data.results, bottomReached);
      callbacks.onDone(pageResult);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
