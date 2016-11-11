package net.sgonzalez.example.app.navigation.extras;

import android.content.Intent;
import net.sgonzalez.example.app.navigation.NavigationDestiny;

public interface ExtrasProvider {
  void putExtras(NavigationDestiny destiny, Intent intent);
}
