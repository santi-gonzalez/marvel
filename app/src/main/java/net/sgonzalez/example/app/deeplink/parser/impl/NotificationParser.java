package net.sgonzalez.example.app.deeplink.parser.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.parser.AbsParser;
import net.sgonzalez.example.app.di.scope.ApplicationScope;

@ApplicationScope public class NotificationParser extends AbsParser {
  public static final int INDEX_TITLE = 0;
  public static final int INDEX_MESSAGE = 1;

  @Inject
  public NotificationParser(ActionFactory actionFactory) {
    super(actionFactory);
  }

  @Override
  protected int getExpectedParamCount() {
    return 2;
  }

  @Override
  public void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    String title = params[INDEX_TITLE];
    String message = params[INDEX_MESSAGE];
    deepLinkActionRegistry.registerAction(actionFactory.newNotificationAction(title, message));
  }
}
