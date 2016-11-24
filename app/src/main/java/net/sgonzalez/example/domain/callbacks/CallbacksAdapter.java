package net.sgonzalez.example.domain.callbacks;

public abstract class CallbacksAdapter<Result>
implements Callbacks<Result> {
  private final Callbacks forward;

  public CallbacksAdapter() {
    this(null);
  }

  public CallbacksAdapter(Callbacks forward) {
    this.forward = forward;
  }

  @Override public void onExecute() {
    if (forward != null) {
      forward.onExecute();
    }
  }

  @Override public void onResult(Result result) {
    try {
      if (forward != null) {
        //noinspection unchecked
        forward.onResult(result);
      }
    } catch(Exception exception) {
      onError(exception);
    }
  }

  @Override public void onError(Exception exception) {
    if (forward != null) {
      forward.onError(exception);
    }
  }
}
