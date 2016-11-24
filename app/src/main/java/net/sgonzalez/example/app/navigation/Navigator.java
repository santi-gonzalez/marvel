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
   * Mutate this navigator into a {@link ContextNavigator} by using of the provided Activity.
   */
  ContextNavigator withActivityContext(@NonNull Activity activity);

  void navigateTo(@NonNull NavigationDestiny navigationDestiny, @Nullable ExtrasProvider extrasProvider);
}
