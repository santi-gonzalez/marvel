package net.sgonzalez.example.app.navigation.impl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.navigation.NavigationDestiny;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;

/**
 * You may want to inject {@link Navigator} instead.
 */
@ApplicationScope public class BaseNavigator
implements Navigator {
  @Inject public BaseNavigator() {
  }

  @Override public Context getActivityContext() {
    return null;
  }

  @Override public ContextNavigator withActivityContext(@NonNull Activity activity) {
    return new ContextNavigator(activity);
  }

  @Override public void navigateTo(@NonNull NavigationDestiny navigationDestiny, @Nullable ExtrasProvider extrasProvider) {
    if (!hasActivityContext()) {
      throw new IllegalStateException("context == null; invoke #withActivityContext(Context) before, or from a ContextNavigator "
                                      + "object");
    }
    navigationDestiny.onNavigate(getActivityContext(), extrasProvider);
  }

  @Override public boolean hasActivityContext() {
    return getActivityContext() != null;
  }
}
