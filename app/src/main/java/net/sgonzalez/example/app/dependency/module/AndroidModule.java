package net.sgonzalez.example.app.dependency.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import net.sgonzalez.example.app.dependency.scope.AndroidScope;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.app.navigation.impl.ContextNavigator;

@Module public class AndroidModule {
  private final Context context;

  public AndroidModule(Context context) {
    this.context = context;
  }

  @AndroidScope @Provides Context getContext() {
    return context;
  }

  @AndroidScope @Provides Navigator getContextNavigator(ContextNavigator contextNavigator) {
    return contextNavigator;
  }
}
