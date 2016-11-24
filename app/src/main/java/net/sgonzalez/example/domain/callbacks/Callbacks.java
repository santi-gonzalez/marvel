package net.sgonzalez.example.domain.callbacks;

/**
 * All these callbacks always happen on main thread.
 */
public interface Callbacks<Result> {
  void onExecute();

  void onResult(Result result);

  void onError(Exception exception);
}
