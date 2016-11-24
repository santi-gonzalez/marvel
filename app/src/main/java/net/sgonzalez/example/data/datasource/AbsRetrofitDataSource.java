package net.sgonzalez.example.data.datasource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import net.sgonzalez.example.app.retrofit.response.ErrorResponse;
import net.sgonzalez.example.data.datasource.exception.NetworkException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public abstract class AbsRetrofitDataSource
implements CloudDataSource {
  private final Retrofit retrofit;

  public AbsRetrofitDataSource(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  protected final <Type> Type executeRequest(Call<Type> call) {
    try {
      Response<Type> response = call.execute();
      checkSuccessfulOrThrow(response);
      return response.body();
    } catch(IOException exception) {
      throw new NetworkException(exception);
    }
  }

  protected void checkSuccessfulOrThrow(Response response) {
    try {
      if (response == null) {
        throw new NetworkException();
      }
      if (!response.isSuccessful()) {
        Converter<ResponseBody, ErrorResponse> converter = getErrorResponseConverter();
        ErrorResponse error = converter.convert(response.errorBody());
        throw new NetworkException(response.code(), response.message(), error);
      }
    } catch(IOException exception) {
      throw new NetworkException(exception);
    }
  }

  private Converter<ResponseBody, ErrorResponse> getErrorResponseConverter() {
    return retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
  }
}
