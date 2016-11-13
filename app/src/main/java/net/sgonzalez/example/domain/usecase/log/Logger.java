package net.sgonzalez.example.domain.usecase.log;

import net.sgonzalez.example.app.log.MainLogger;

public final class Logger {
  public static boolean DEBUG_LOGS_ENABLED = true;

  private Logger() {
  }

  public static void logVerbose(String tag, String message) {
    if (DEBUG_LOGS_ENABLED) {
      MainLogger.v(tag, message);
    }
  }

  public static void logException(String tag, String message, Exception exception) {
    MainLogger.e(tag, message, exception);
  }
}
