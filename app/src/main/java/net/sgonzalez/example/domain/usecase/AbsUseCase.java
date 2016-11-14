package net.sgonzalez.example.domain.usecase;

import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.domain.callbacks.Callbacks;
import net.sgonzalez.example.domain.usecase.log.Logger;

/**
 * All use cases must return {@link net.sgonzalez.example.domain.model.Model} type instances. Method {@link #dispatchExecute()} is triggered
 * automatically right before {@link #onExecute(Object)}, in the main thread. <h3>Usage</h3> Specify generic <i>Development</i> and <i>Result</i>,
 * which can be of any kind. Given more than a single field is required, create an {@code inner static final class Params} and / or {@code Result},
 * with final fields and public constructor.
 * <p>
 * Inject repositories and mappers (models from data source come as Entities, and should exit as Models).
 * <p>
 * Dispatch result with {@link #dispatchResult(Object)} or error with {@link #dispatchError(Exception)} for convenience from result callbacks.
 * <h3>Logging</h3> Use convenient {@code log*} methods for easy integration with master switches.
 *
 * @see net.sgonzalez.example.data.entity.Entity
 * @see net.sgonzalez.example.domain.model.Model
 */
public abstract class AbsUseCase<Params, Result> implements Runnable {
  private final MainThreadExecutor mainThreadExecutor;
  private final NewThreadExecutor newThreadExecutor;
  private final SameThreadExecutor sameThreadExecutor;
  private boolean calledFromExecute;
  private Params params;
  @Nullable private Callbacks<Result> callbacks;

  public AbsUseCase(MainThreadExecutor mainThreadExecutor, NewThreadExecutor newThreadExecutor, SameThreadExecutor sameThreadExecutor) {
    this.mainThreadExecutor = mainThreadExecutor;
    this.newThreadExecutor = newThreadExecutor;
    this.sameThreadExecutor = sameThreadExecutor;
    this.calledFromExecute = false;
  }

  @SuppressWarnings("unused")
  public final void execute(Params params, @Nullable Callbacks<Result> callbacks) {
    executeInternal(params, newThreadExecutor, callbacks);
  }

  @SuppressWarnings("unused")
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

  @Override
  public final void run() {
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

  private void dispatchExecute() {
    logVerbose("onExecute");
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override
        public void run() {
          callbacks.onExecute();
        }
      });
    }
  }

  protected void dispatchResult(final Result result) {
    logVerbose("onResult: " + result);
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override
        public void run() {
          callbacks.onResult(result);
        }
      });
    }
  }

  protected void dispatchError(final Exception exception) {
    logException(exception);
    if (callbacks != null) {
      executeMainThread(new Runnable() {
        @Override
        public void run() {
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
