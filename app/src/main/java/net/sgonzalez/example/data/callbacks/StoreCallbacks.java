package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public interface StoreCallbacks<Entity> {
  void onStore(Entity entity);
  void onError(@NonNull Exception exception);
}
