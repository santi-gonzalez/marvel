package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public abstract class CallbacksAdapter<Entity>
implements Callbacks<Entity> {
  private final Callbacks forward;

  public CallbacksAdapter() {
    this(null);
  }

  public CallbacksAdapter(Callbacks forward) {
    this.forward = forward;
  }

  @Override public void onDone(Entity result) {
    try {
      if (forward != null) {
        //noinspection unchecked
        forward.onDone(result);
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
