package net.sgonzalez.example.app.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;

@ApplicationScope public class MainThreadExecutor
implements Executor {
  private final Handler handler;

  @Inject public MainThreadExecutor() {
    handler = new Handler(Looper.getMainLooper());
  }

  @Override public void execute(@NonNull Runnable runnable) {
    handler.post(runnable);
  }
}
