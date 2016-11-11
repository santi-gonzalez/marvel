package net.sgonzalez.example.app.deeplink;

import net.sgonzalez.example.app.deeplink.action.Action;

public interface DeepLinkActionRegistry {
  String requestParameter();
  void registerAction(Action action);
  void clearRemainingActions();
}
