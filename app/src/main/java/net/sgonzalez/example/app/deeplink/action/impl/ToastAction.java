package net.sgonzalez.example.app.deeplink.action.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.action.AbsAction;

class ToastAction
extends AbsAction {
  private final String message;

  ToastAction(String message) {
    this.message = message;
  }

  @Override protected void onExecute(@NonNull App app, @Nullable Context context) {
    Toast.makeText(app, message, Toast.LENGTH_SHORT).show();
  }
}
