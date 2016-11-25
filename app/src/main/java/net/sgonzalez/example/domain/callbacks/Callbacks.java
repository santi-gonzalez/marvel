package net.sgonzalez.example.domain.callbacks;

import android.support.annotation.NonNull;

public interface Callbacks<Result> {
  void onExecute();

  void onResult(Result result);

  void onError(@NonNull Exception exception);
}
