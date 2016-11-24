package net.sgonzalez.example.app.deeplink.parser.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.parser.AbsParser;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;

@ApplicationScope public class ToastParser
extends AbsParser {
  public static final int INDEX_MESSAGE = 0;

  @Inject public ToastParser(ActionFactory actionFactory) {
    super(actionFactory);
  }

  @Override protected int getExpectedParamCount() {
    return 1;
  }

  @Override public void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    String message = params[INDEX_MESSAGE];
    deepLinkActionRegistry.registerAction(actionFactory.newToastAction(message));
  }
}
