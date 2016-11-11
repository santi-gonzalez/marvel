package net.sgonzalez.example.app.navigation.impl;

import android.content.Context;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.AndroidScope;
import net.sgonzalez.example.app.navigation.Navigator;

/**
 * Do not inject this class directly. Inject {@link Navigator} instead.
 */
@AndroidScope public class ContextNavigator extends BaseNavigator {
  private final Context context;

  @Inject
  public ContextNavigator(Context context) {
    this.context = context;
  }

  @Override
  public Context getActivityContext() {
    return context;
  }
}
