package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public abstract class CallbacksAdapter<Result>
implements Callbacks<Result> {
  private final Callbacks forward;

  public CallbacksAdapter() {
    this(null);
  }

  /**
   * If a <i>forward</i> {@link net.sgonzalez.example.data.callbacks.Callbacks} is given, non-implemented methods will
   * automatically dispatch callbacks when they happen. In the event a class cast would happen dispatching an
   * {@link #onResult(Object)} , an {@link #onError(Exception)} event will be dispatched instead.
   */
  public CallbacksAdapter(Callbacks forward) {
    this.forward = forward;
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

  @Override public void onError(@NonNull Exception exception) {
    if (forward != null) {
      forward.onError(exception);
    }
  }
}
