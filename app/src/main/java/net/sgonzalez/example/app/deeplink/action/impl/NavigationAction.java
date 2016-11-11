package net.sgonzalez.example.app.deeplink.action.impl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.action.AbsAction;
import net.sgonzalez.example.app.navigation.NavigationDestiny;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;

class NavigationAction extends AbsAction {
  private final NavigationDestiny navigationDestiny;
  private final ExtrasProvider extrasProvider;
  private Navigator navigator;

  NavigationAction(Navigator navigator, @NonNull NavigationDestiny navigationDestiny, @Nullable ExtrasProvider extrasProvider) {
    this.navigator = navigator;
    this.navigationDestiny = navigationDestiny;
    this.extrasProvider = extrasProvider;
  }

  @Override
  protected void onExecute(@NonNull App app, @Nullable Context context) {
    if (!navigator.hasActivityContext() && context != null) {
      navigator = this.navigator.withActivityContext((Activity) context);
    }
    navigator.navigateTo(navigationDestiny, extrasProvider);
  }
}
