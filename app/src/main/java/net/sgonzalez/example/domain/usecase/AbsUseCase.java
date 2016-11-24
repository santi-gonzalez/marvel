package net.sgonzalez.example.domain.usecase;

import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.domain.callbacks.Callbacks;
import net.sgonzalez.example.domain.usecase.log.Logger;

public abstract class AbsUseCase<Params, Result>
implements Runnable {
  private final MainThreadExecutor mainThreadExecutor;
  private final NewThreadExecutor newThreadExecutor;
  private final SameThreadExecutor sameThreadExecutor;
  private boolean calledFromExecute;
  private Params params;
  @Nullable private Callbacks<Result> callbacks;

  public AbsUseCase(MainThreadExecutor mainThreadExecutor,
                    NewThreadExecutor newThreadExecutor,
                    SameThreadExecutor sameThreadExecutor) {
    this.mainThreadExecutor = mainThreadExecutor;
    this.newThreadExecutor = newThreadExecutor;
    this.sameThreadExecutor = sameThreadExecutor;
    this.calledFromExecute = false;
  }

  /**
   * Happens on a worker thread.
   */
  public final void execute(Params params, @Nullable Callbacks<Result> callbacks) {
    executeInternal(params, newThreadExecutor, callbacks);
  }

  /**
   * Happens on caller thread. Meant to be used by local operations or by other use cases.
   */
  public final void executeSync(Params params, @Nullable Callbacks<Result> callbacks) {
    executeInternal(params, sameThreadExecutor, callbacks);
  }

  private void executeInternal(Params params, Executor executor, Callbacks<Result> callbacks) {
    calledFromExecute = true;
    setArguments(params, callbacks);
    executor.execute(this);
  }

  private void setArguments(Params params, Callbacks<Result> callbacks) {
    this.params = params;
    this.callbacks = callbacks;
  }

  @Override public final void run() {
    try {
      if (!calledFromExecute) {
        throw new IllegalStateException("run this use case by invoking #executeSync(...) or #execute(...)");
      }
      dispatchExecute();
      onExecute(params);
    } catch(Exception exception) {
      dispatchError(exception);
    }
  }

  /**
   * Happens on the main thread.
   */
  private void dispatchExecute() {
    logVerbose("onExecute");
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override public void run() {
          callbacks.onExecute();
        }
      });
    }
  }

  /**
   * Happens on the main thread.
   */
  protected void dispatchResult(final Result result) {
    logVerbose("onResult: " + result);
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override public void run() {
          callbacks.onResult(result);
        }
      });
    }
  }

  /**
   * Happens on the main thread.
   */
  protected void dispatchError(final Exception exception) {
    logException(exception);
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override public void run() {
          callbacks.onError(exception);
        }
      });
    }
  }

  private void executeMainThread(Runnable runnable) {
    mainThreadExecutor.execute(runnable);
  }

  protected abstract void onExecute(Params params);

  private void logVerbose(String message) {
    Logger.logVerbose(getClass().getSimpleName(), message);
  }

  private void logException(Exception exception) {
    Logger.logException(getClass().getSimpleName(), "unexpected error", exception);
  }
}
