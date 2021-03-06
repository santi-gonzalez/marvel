package net.sgonzalez.example.app.navigation.impl;

import android.content.Context;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.AndroidScope;
import net.sgonzalez.example.app.navigation.Navigator;

/**
 * You may want to inject {@link Navigator} instead.
 */
@AndroidScope public class ContextNavigator
extends BaseNavigator {
  private final Context context;

  @Inject public ContextNavigator(Context context) {
    this.context = context;
  }

  @Override public Context getActivityContext() {
    return context;
  }
}
