package net.sgonzalez.example.data.datasource.impl;

import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.retrofit.response.CharacterResponse;
import net.sgonzalez.example.app.retrofit.retrofit.service.CharacterService;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.datasource.AbsRetrofitCloudDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import retrofit2.Call;
import retrofit2.Retrofit;

@ApplicationScope public class CharacterRetrofitCloudDataSource extends AbsRetrofitCloudDataSource {
  private final CharacterService characterService;

  @Inject
  public CharacterRetrofitCloudDataSource(Retrofit retrofit, CharacterService characterService) {
    super(retrofit);
    this.characterService = characterService;
  }

  public void retrieve(RetrieveCallbacks<List<CharacterEntity>> callbacks) {
    try {
      Call<CharacterResponse> request = characterService.getCharacters();
      CharacterResponse response = executeRequest(request);
      callbacks.onResult(response.data.results);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
