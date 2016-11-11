package net.sgonzalez.example.app.deeplink.action;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.log.Logger;

public abstract class AbsAction implements Action {
  @Override
  public void execute(@NonNull App app, @Nullable Context context) {
    try {
      logVerbose("executing sync...");
      onExecute(app, context);
    } catch(Exception exception) {
      logException(exception);
    }
  }

  @Override
  public boolean shouldEndExecution() {
    return false;
  }

  /**
   * Parameter <i>context</i> (which can be always safely casted into Activity) is always {@code null} for async operations.
   */
  protected abstract void onExecute(@NonNull App app, @Nullable Context context) throws Exception;

  protected void logVerbose(String message) {
    Logger.logVerbose(getClass().getSimpleName(), message);
  }

  protected void logException(Exception exception) {
    Logger.logException(getClass().getSimpleName(), "error executing action in " + this, exception);
  }
}
