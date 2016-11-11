package net.sgonzalez.example.data.datasource.exception;

import java.io.IOException;
import net.sgonzalez.example.app.retrofit.retrofit.response.ErrorResponse;

public class NetworkException extends RuntimeException {
  public static final int NON_PRESENT = -1;
  public static final String NETWORK_ERROR_MESSAGE = "network error";
  private final int responseCode;
  private final String responseMessage;
  private final ErrorResponse errorResponse;

  public NetworkException() {
    super(NETWORK_ERROR_MESSAGE);
    this.responseCode = NON_PRESENT;
    this.responseMessage = "";
    this.errorResponse = new ErrorResponse();
  }

  public NetworkException(int code, String message, ErrorResponse response) {
    super(response.message != null ? response.message : (message != null ? message : NETWORK_ERROR_MESSAGE));
    this.responseCode = code;
    this.responseMessage = message;
    this.errorResponse = response;
  }

  public NetworkException(IOException exception) {
    super(exception);
    this.responseCode = NON_PRESENT;
    this.responseMessage = "";
    this.errorResponse = new ErrorResponse();
  }

  public int getResponseCode() {
    return responseCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }
}
