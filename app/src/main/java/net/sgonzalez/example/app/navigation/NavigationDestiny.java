package net.sgonzalez.example.app.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;
import net.sgonzalez.example.presentation.ui.activity.AbsActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.FiltersActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.MainActivity;

public enum NavigationDestiny {
  MAIN(MainActivity.class, "mai"),
  FILTERS(FiltersActivity.class, "fil");
  private final Class<? extends AbsActivity> activityClass;
  private final String shortDestinyName;

  NavigationDestiny(@NonNull Class<? extends AbsActivity> activityClass, String shortDestinyName) {
    this.activityClass = activityClass;
    this.shortDestinyName = shortDestinyName;
  }

  public static NavigationDestiny from(@NonNull String shortDestinyName) {
    for (NavigationDestiny destiny : values()) {
      if (destiny.shortDestinyName.equals(shortDestinyName)) {
        return destiny;
      }
    }
    throw new UnsupportedOperationException("not supported: " + shortDestinyName);
  }

  public void onNavigate(Context context, @Nullable ExtrasProvider extrasProvider) {
    Intent intent = getIntent(context, extrasProvider);
    startActivity(context, intent);
  }

  private Intent getIntent(Context context, @Nullable ExtrasProvider extrasProvider) {
    Intent intent = new Intent(context, activityClass);
    if (extrasProvider != null) {
      extrasProvider.putExtras(this, intent);
    }
    return intent;
  }

  private void startActivity(Context context, Intent intent) {
    context.startActivity(intent);
  }
}
