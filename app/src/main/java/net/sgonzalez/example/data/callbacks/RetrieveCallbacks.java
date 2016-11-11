package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public interface RetrieveCallbacks<Entity> {
  void onResult(Entity entity);
  void onError(@NonNull Exception exception);
}
