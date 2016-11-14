package net.sgonzalez.example.data.datasource.impl;

import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.retrofit.response.CharacterResponse;
import net.sgonzalez.example.app.retrofit.service.CharacterService;
import net.sgonzalez.example.data.callbacks.Callbacks;
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

  public void retrieve(int offset, Callbacks<List<CharacterEntity>> callbacks) {
    try {
      Call<CharacterResponse> request = characterService.getCharacters(offset);
      CharacterResponse response = executeRequest(request);
      callbacks.onDone(response.data.results);
    } catch(Exception exception) {
      callbacks.onError(exception);
    }
  }
}
