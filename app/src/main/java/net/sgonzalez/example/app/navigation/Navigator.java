package net.sgonzalez.example.app.navigation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;
import net.sgonzalez.example.app.navigation.impl.ContextNavigator;

public interface Navigator {
  Context getActivityContext();
  boolean hasActivityContext();
  /**
   * Mutate this navigator to a {@link ContextNavigator} by the use of the provided Activity.
   */
  ContextNavigator withActivityContext(Activity activity);
  void navigateTo(@NonNull NavigationDestiny navigationDestiny, @Nullable ExtrasProvider extrasProvider);
}
