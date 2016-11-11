package net.sgonzalez.example.app.deeplink.parser;

import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;

public interface Parser {
  void parse(DeepLinkActionRegistry deepLinkActionRegistry, String... params);
}
