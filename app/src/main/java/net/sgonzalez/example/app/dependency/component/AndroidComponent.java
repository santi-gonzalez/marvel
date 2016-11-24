package net.sgonzalez.example.app.dependency.component;

import dagger.Component;
import net.sgonzalez.example.app.dependency.module.AndroidModule;
import net.sgonzalez.example.app.dependency.scope.AndroidScope;
import net.sgonzalez.example.presentation.ui.activity.impl.DeepLinkActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.FiltersActivity;
import net.sgonzalez.example.presentation.ui.activity.impl.MainActivity;
import net.sgonzalez.example.presentation.ui.view.MarvelCharacterNameTextView;

@AndroidScope @Component(dependencies = { AppComponent.class }, modules = { AndroidModule.class })
public interface AndroidComponent {
  void inject(MainActivity activity);

  void inject(DeepLinkActivity activity);

  void inject(FiltersActivity activity);

  void inject(MarvelCharacterNameTextView view);
}
