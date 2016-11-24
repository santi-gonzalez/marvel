package net.sgonzalez.example.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class AbsPresenter<Presentable>
implements Presenter<Presentable> {
  private Presentable presentable;

  @Override public void bindPresentable(@NonNull Presentable presentable) {
    this.presentable = presentable;
  }

  @NonNull @Override public Presentable presentable() {
    return presentable;
  }

  public abstract void onSaveInstanceState(@NonNull Bundle state);

  public abstract void onRestoreInstanceState(@Nullable Bundle state);
}
