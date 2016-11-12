package net.sgonzalez.example.app.executor;

import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;

@ApplicationScope public class RealmThreadExecutor extends MainThreadExecutor {
  @Inject
  public RealmThreadExecutor() {
  }
}
