package net.sgonzalez.example.app.retrofit.retrofit.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
  public AuthenticationInterceptor() {
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = new Request.Builder().url(chain.request()
                                                     .url()
                                                     .newBuilder()
                                                     .addQueryParameter("apikey", "7bb03cdfd44d30f2b20936db67a92366")
                                                     .addQueryParameter("ts", "1")
                                                     .addQueryParameter("hash", "e23c842cb7d9f94e7b984d71208d2536")
                                                     .build())
                                           .build();
    return chain.proceed(request);
  }
}
