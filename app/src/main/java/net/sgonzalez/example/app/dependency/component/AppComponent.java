package net.sgonzalez.example.app.dependency.component;

import android.app.Application;
import dagger.Component;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.DeepLinkResolver;
import net.sgonzalez.example.app.dependency.component.provider.DataSourceProvider;
import net.sgonzalez.example.app.dependency.component.provider.MapperProvider;
import net.sgonzalez.example.app.dependency.component.provider.RepositoryProvider;
import net.sgonzalez.example.app.dependency.module.AppModule;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.presentation.ui.manager.TypefaceManager;

@ApplicationScope @Component(modules = { AppModule.class }) public interface AppComponent
extends RepositoryProvider, DataSourceProvider, MapperProvider {
  void inject(App app);

  App getApp();

  Application getApplication();

  DeepLinkResolver getDeepLinkResolver();

  MainThreadExecutor getMainThreadExecutor();

  NewThreadExecutor getNewThreadExecutor();

  SameThreadExecutor getSameThreadExecutor();

  TypefaceManager getTypefaceManager();
}
