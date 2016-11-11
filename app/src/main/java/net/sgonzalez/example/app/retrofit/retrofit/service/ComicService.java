package net.sgonzalez.example.app.retrofit.retrofit.service;

import net.sgonzalez.example.app.retrofit.retrofit.response.ComicResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComicService {
  @GET("/v1/public/characters/{characterId}/comics")
  Call<ComicResponse> getComicsByCharacterId(@Path("characterId") long characterId);
}
