package net.sgonzalez.example.app.deeplink.action.impl;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import net.sgonzalez.example.R;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.action.AbsAction;

import static android.content.Context.NOTIFICATION_SERVICE;

class NotificationAction
extends AbsAction {
  private final String title;
  private final String message;

  NotificationAction(String title, String message) {
    this.title = title;
    this.message = message;
  }

  @Override protected void onExecute(@NonNull App app, @Nullable Context context) {
    NotificationManager notificationManager = (NotificationManager) app.getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(1,
                               new NotificationCompat.Builder(app).setSmallIcon(R.drawable.ic_notification)
                                                                  .setContentTitle(title)
                                                                  .setContentText(message)
                                                                  .setAutoCancel(true)
                                                                  .build());
  }
}
