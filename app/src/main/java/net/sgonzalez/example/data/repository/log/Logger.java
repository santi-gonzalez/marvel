package net.sgonzalez.example.data.repository.log;

import android.util.Log;

public final class Logger {
  public static boolean DEBUG_LOGS_ENABLED = true;

  private Logger() {
  }

  public static void logVerbose(String tag, String message) {
    if (DEBUG_LOGS_ENABLED) {
      Log.v(tag, message);
    }
  }

  public static void logDebug(String tag, String message) {
    if (DEBUG_LOGS_ENABLED) {
      Log.d(tag, message);
    }
  }

  public static void logException(String tag, String message, Exception exception) {
    Log.e(tag, message, exception);
  }
}
