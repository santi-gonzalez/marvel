package net.sgonzalez.example.app.log;

import android.util.Log;

@SuppressWarnings("unused") public final class MainLogger {
  public static final int MAX_LOG_LENGTH = 2048;

  private MainLogger() {
  }

  public static void v(String tag, String message) {
    message = ensureNotNull(message);
    for (int index = 0; index < message.length(); index += MAX_LOG_LENGTH) {
      Log.v(tag, nextChunk(message, index));
    }
  }

  public static void d(String tag, String message) {
    message = ensureNotNull(message);
    for (int index = 0; index < message.length(); index += MAX_LOG_LENGTH) {
      Log.d(tag, nextChunk(message, index));
    }
  }

  public static void i(String tag, String message) {
    message = ensureNotNull(message);
    for (int index = 0; index < message.length(); index += MAX_LOG_LENGTH) {
      Log.i(tag, nextChunk(message, index));
    }
  }

  public static void w(String tag, String message) {
    message = ensureNotNull(message);
    for (int index = 0; index < message.length(); index += MAX_LOG_LENGTH) {
      Log.w(tag, nextChunk(message, index));
    }
  }

  public static void e(String tag, String message) {
    message = ensureNotNull(message);
    for (int index = 0; index < message.length(); index += MAX_LOG_LENGTH) {
      Log.e(tag, nextChunk(message, index));
    }
  }

  public static void e(String tag, String message, Exception exception) {
    Log.e(tag, message, exception);
  }

  private static String ensureNotNull(String message) {
    return message != null ? message : "null";
  }

  private static String nextChunk(String message, int index) {
    return message.substring(index, Math.min(message.length(), index + MAX_LOG_LENGTH));
  }
}
