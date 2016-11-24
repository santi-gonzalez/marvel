package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public interface Callbacks<Entity> {
  void onDone(Entity result);

  void onError(@NonNull Exception exception);
}
