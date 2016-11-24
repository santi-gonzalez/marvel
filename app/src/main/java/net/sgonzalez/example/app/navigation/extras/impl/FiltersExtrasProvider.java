package net.sgonzalez.example.app.navigation.extras.impl;

import android.content.Intent;
import net.sgonzalez.example.app.navigation.NavigationDestiny;
import net.sgonzalez.example.app.navigation.extras.ExtrasProvider;
import net.sgonzalez.example.presentation.ui.activity.impl.FiltersActivity;

public abstract class FiltersExtrasProvider
implements ExtrasProvider {
  @Override public final void putExtras(NavigationDestiny destiny, Intent intent) {
    intent.putExtra(FiltersActivity.EXTRA_MESSAGE, getMessage());
  }

  public abstract String getMessage();
}
