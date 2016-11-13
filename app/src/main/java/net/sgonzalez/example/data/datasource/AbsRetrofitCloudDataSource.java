package net.sgonzalez.example.data.datasource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import net.sgonzalez.example.app.retrofit.retrofit.response.ErrorResponse;
import net.sgonzalez.example.data.datasource.exception.NetworkException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * <h3>Development</h3> All methods must present the final parameter as
 * {@link net.sgonzalez.example.data.callbacks.RetrieveCallbacks}
 * or {@link net.sgonzalez.example.data.callbacks.StoreCallbacks}, depending on the intent, with any number and type of other
 * parameters before.
 * <p>
 * All network request methods must be enclosed in a {@code try-catch(NetworkException)} clause, and should <b>NEVER</b> throw an
 * exception.
 * <p>
 * Execution must end with either invoking {@code callbacks.onResult()} / {@code onStore()} or {@code onError}
 * <p>
 * Use {@link #executeRequest(Call)} method for convenience (and also basic error handling and throwing).
 */
public abstract class AbsRetrofitCloudDataSource implements DataSource {
  private final Retrofit retrofit;

  public AbsRetrofitCloudDataSource(Retrofit retrofit) {
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
