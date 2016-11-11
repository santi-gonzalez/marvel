package net.sgonzalez.example.app.executor;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;

@ApplicationScope public class SameThreadExecutor implements Executor {
  @Inject
  public SameThreadExecutor() {
  }

  @Override
  public void execute(@NonNull Runnable runnable) {
    runnable.run();
  }
}
