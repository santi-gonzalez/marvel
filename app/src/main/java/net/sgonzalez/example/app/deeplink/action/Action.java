package net.sgonzalez.example.app.deeplink.action;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.app.App;

public interface Action {
  void execute(@NonNull App app, @Nullable Context context);
  boolean shouldEndExecution();
}
