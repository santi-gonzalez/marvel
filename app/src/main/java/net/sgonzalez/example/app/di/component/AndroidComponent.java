package net.sgonzalez.example.app.di.component;

import dagger.Component;
import net.sgonzalez.example.app.di.module.AndroidModule;
import net.sgonzalez.example.app.di.scope.AndroidScope;
import net.sgonzalez.example.presentation.ui.activity.impl.DeepLinkActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.FiltersActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.MainActivity;

@AndroidScope @Component(dependencies = { AppComponent.class }, modules = { AndroidModule.class }) public interface AndroidComponent {
  void inject(MainActivity activity);
  void inject(DeepLinkActivity activity);
  void inject(FiltersActivity activity);
}
