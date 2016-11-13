package net.sgonzalez.example.presentation.presenter;

import android.support.annotation.NonNull;

public interface Presenter<Presentable> {
  void bindPresentable(@NonNull Presentable presentable);
  @NonNull
  Presentable presentable();
}
