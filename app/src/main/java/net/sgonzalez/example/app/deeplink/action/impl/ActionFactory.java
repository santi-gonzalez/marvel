package net.sgonzalez.example.app.deeplink.action.impl;

import android.support.annotation.Nullable;
import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.action.Action;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.navigation.NavigationDestiny;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;

@ApplicationScope public class ActionFactory {
  @Inject Navigator navigator;

  @Inject
  public ActionFactory() {
  }

  public Action newNavigationAction(NavigationDestiny navigationDestiny) {
    return new NavigationAction(navigator, navigationDestiny, null);
  }

  public Action newNavigationAction(@Nullable ExtrasProvider extrasProvider, NavigationDestiny navigationDestiny) {
    return new NavigationAction(navigator, navigationDestiny, extrasProvider);
  }

  public Action newNotificationAction(String title, String message) {
    return new NotificationAction(title, message);
  }

  public Action newToastAction(String message) {
    return new ToastAction(message);
  }
}
