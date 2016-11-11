package net.sgonzalez.example.app.retrofit.retrofit.service;

import net.sgonzalez.example.app.retrofit.retrofit.response.CharacterResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterService {
  @GET("/v1/public/characters")
  Call<CharacterResponse> getCharacters();
}
