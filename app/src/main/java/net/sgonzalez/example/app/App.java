package net.sgonzalez.example.app;

import android.app.Application;
import net.sgonzalez.example.app.di.component.AppComponent;
import net.sgonzalez.example.app.di.component.DaggerAppComponent;
import net.sgonzalez.example.app.di.module.AppModule;

public class App extends Application {
  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    createAppComponent();
  }

  private void createAppComponent() {
    appComponent = DaggerAppComponent.builder()
                                     .appModule(new AppModule(this))
                                     .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
