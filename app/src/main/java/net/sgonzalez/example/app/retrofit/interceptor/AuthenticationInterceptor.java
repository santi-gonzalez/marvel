package net.sgonzalez.example.app.retrofit.interceptor;

import java.io.IOException;
import net.sgonzalez.example.app.utils.HashUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor
implements Interceptor {
  private static final String PUBLIC_KEY = NoSourceControl.PUBLIC_KEY;
  private static final String PRIVATE_KEY = NoSourceControl.PRIVATE_KEY;
  private static final String API_KEY_QUERY_PARAM = "apikey";
  private static final String TIMESTAMP_QUERY_PARAM = "ts";
  private static final String HASH_QUERY_PARAM = "hash";

  @Override public Response intercept(Chain chain) throws
  IOException {
    AuthInfo authInfo = generateAuthInfo();
    Request request = new Request.Builder().url(chain.request()
                                                     .url()
                                                     .newBuilder()
                                                     .addQueryParameter(API_KEY_QUERY_PARAM, authInfo.apiKey)
                                                     .addQueryParameter(TIMESTAMP_QUERY_PARAM, authInfo.timestamp)
                                                     .addQueryParameter(HASH_QUERY_PARAM, authInfo.hash)
                                                     .build()).build();
    return chain.proceed(request);
  }

  private AuthInfo generateAuthInfo() {
    long arbitraryTimestamp = System.currentTimeMillis();
    AuthInfo authInfo = new AuthInfo();
    authInfo.apiKey = PUBLIC_KEY;
    authInfo.timestamp = String.valueOf(arbitraryTimestamp);
    authInfo.hash = HashUtils.toMd5(String.valueOf(arbitraryTimestamp) + PRIVATE_KEY + PUBLIC_KEY);
    return authInfo;
  }

  private static class AuthInfo {
    private String apiKey;
    private String timestamp;
    private String hash;
  }
}
