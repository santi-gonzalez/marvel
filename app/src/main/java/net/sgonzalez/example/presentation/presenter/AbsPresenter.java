package net.sgonzalez.example.presentation.presenter;

import android.support.annotation.NonNull;

public abstract class AbsPresenter<Presentable> implements Presenter<Presentable> {
  private Presentable presentable;

  @Override
  public void attachPresentable(@NonNull Presentable presentable) {
    this.presentable = presentable;
  }

  @NonNull
  @Override
  public Presentable getPresentable() {
    return presentable;
  }
}
