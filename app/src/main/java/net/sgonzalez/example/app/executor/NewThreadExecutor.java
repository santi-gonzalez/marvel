package net.sgonzalez.example.app.executor;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;

@ApplicationScope public class NewThreadExecutor implements Executor {
  ExecutorService executorService;

  @Inject
  public NewThreadExecutor() {
    executorService = Executors.newCachedThreadPool();
  }

  @Override
  public void execute(@NonNull Runnable runnable) {
    executorService.execute(runnable);
  }
}
