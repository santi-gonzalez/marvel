package net.sgonzalez.example.data.callbacks;

import android.support.annotation.NonNull;

public interface Callbacks<Result> {
  void onResult(Result result);

  void onError(@NonNull Exception exception);
}
