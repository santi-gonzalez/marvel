package net.sgonzalez.example.app.executor;

import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;

@ApplicationScope public class RealmThreadExecutor extends MainThreadExecutor {
  @Inject
  public RealmThreadExecutor() {
  }
}
