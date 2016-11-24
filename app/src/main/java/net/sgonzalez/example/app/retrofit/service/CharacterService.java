package net.sgonzalez.example.app.retrofit.service;

import net.sgonzalez.example.app.retrofit.response.CharacterResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterService {
  @GET("/v1/public/characters") Call<CharacterResponse> getCharacters(@Query("offset") int offset);
}
