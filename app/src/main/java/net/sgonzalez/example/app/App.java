package net.sgonzalez.example.app;

import android.app.Application;
import net.sgonzalez.example.app.dependency.component.AppComponent;
import net.sgonzalez.example.app.dependency.component.DaggerAppComponent;
import net.sgonzalez.example.app.dependency.module.AppModule;

public class App
extends Application {
  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    createAppComponent();
  }

  private void createAppComponent() {
    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
