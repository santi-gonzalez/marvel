package net.sgonzalez.example.presentation.presenter;

import android.support.annotation.NonNull;

public interface Presenter<Presentable> {
  void attachPresentable(@NonNull Presentable presentable);
  @NonNull
  Presentable getPresentable();
}
