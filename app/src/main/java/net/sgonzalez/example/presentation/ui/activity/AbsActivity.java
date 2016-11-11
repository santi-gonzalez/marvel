package net.sgonzalez.example.presentation.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.di.component.AndroidComponent;
import net.sgonzalez.example.app.di.component.AppComponent;
import net.sgonzalez.example.app.di.component.DaggerAndroidComponent;
import net.sgonzalez.example.app.di.module.AndroidModule;

public abstract class AbsActivity extends AppCompatActivity {
  public static final int INVALID_ID = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onCreateInternal();
  }

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    onCreateInternal();
  }

  private void onCreateInternal() {
    onInject(DaggerAndroidComponent.builder()
                                   .appComponent(getAppComponent())
                                   .androidModule(new AndroidModule(this))
                                   .build());
    onBindPresentable();
    int layoutResId = getContentView();
    if (layoutResId > INVALID_ID) {
      setContentView(layoutResId);
      ButterKnife.bind(this);
    }
    onExtras(getIntent().getExtras() != null ? getIntent().getExtras() : new Bundle());
  }

  protected abstract void onInject(AndroidComponent androidComponent);
  protected abstract void onBindPresentable();
  @LayoutRes
  protected abstract int getContentView();

  protected void onExtras(@NonNull Bundle extras) {
  }

  protected App getApp() {
    return (App) getApplication();
  }

  protected AppComponent getAppComponent() {
    return getApp().getAppComponent();
  }
}
