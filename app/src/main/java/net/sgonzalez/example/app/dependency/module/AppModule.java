package net.sgonzalez.example.app.dependency.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.app.navigation.impl.BaseNavigator;
import net.sgonzalez.example.app.retrofit.interceptor.AuthenticationInterceptor;
import net.sgonzalez.example.app.retrofit.service.CharacterService;
import net.sgonzalez.example.app.retrofit.service.ComicService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class AppModule {
  public static final String MARVEL_BASE_URL = "https://gateway.marvel.com/";
  private final App app;

  public AppModule(App app) {
    this.app = app;
  }

  @Provides
  @ApplicationScope
  App getApp() {
    return this.app;
  }

  @Provides
  @ApplicationScope
  Application getApplication() {
    return getApp();
  }

  @Provides
  @ApplicationScope
  Navigator getBaseNavigator(BaseNavigator baseNavigator) {
    return baseNavigator;
  }

  @Provides
  @ApplicationScope
  OkHttpClient getOkHttpClient() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
    return new OkHttpClient.Builder().addInterceptor(authenticationInterceptor)
                                     .addInterceptor(httpLoggingInterceptor)
                                     .build();
  }

  @Provides
  @ApplicationScope
  Retrofit getRetrofit(OkHttpClient client) {
    return new Retrofit.Builder().baseUrl(MARVEL_BASE_URL)
                                 .client(client)
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .build();
  }

  @Provides
  @ApplicationScope
  ComicService getComicsService(Retrofit retrofit) {
    return retrofit.create(ComicService.class);
  }

  @Provides
  @ApplicationScope
  CharacterService getCharacterService(Retrofit retrofit) {
    return retrofit.create(CharacterService.class);
  }
}
