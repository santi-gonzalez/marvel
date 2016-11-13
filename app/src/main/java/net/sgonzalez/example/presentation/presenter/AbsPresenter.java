package net.sgonzalez.example.presentation.presenter;

import android.support.annotation.NonNull;

public abstract class AbsPresenter<Presentable> implements Presenter<Presentable> {
  private Presentable presentable;

  @Override
  public void bindPresentable(@NonNull Presentable presentable) {
    this.presentable = presentable;
  }

  @NonNull
  @Override
  public Presentable presentable() {
    return presentable;
  }
}
