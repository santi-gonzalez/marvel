package net.sgonzalez.example.app.deeplink.parser;

import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.parser.impl.NavigationParser;
import net.sgonzalez.example.app.deeplink.parser.impl.NotificationParser;
import net.sgonzalez.example.app.deeplink.parser.impl.ToastParser;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;

@ApplicationScope public class MainParser extends AbsParser {
  public static final int INDEX_ACTION_SEGMENT = 0;
  public static final String ACTION_NAVIGATION = "nav";
  public static final String ACTION_NOTIFICATION = "not";
  public static final String ACTION_TOAST = "toa";
  private final Parser navigationParser;
  private final Parser notificationParser;
  private final Parser toastParser;

  @Inject
  public MainParser(ActionFactory actionFactory, NavigationParser navigationParser, NotificationParser notificationParser, ToastParser toastParser) {
    super(actionFactory);
    this.navigationParser = navigationParser;
    this.notificationParser = notificationParser;
    this.toastParser = toastParser;
  }

  @Override
  protected int getExpectedParamCount() {
    return 1;
  }

  @Override
  public void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    // extend this switch to enable support for more actions
    String segment = params[INDEX_ACTION_SEGMENT];
    switch (segment) {
      case ACTION_NAVIGATION:
        parseNavigationAction(deepLinkActionRegistry);
        break;
      case ACTION_NOTIFICATION:
        parseNotificationAction(deepLinkActionRegistry);
        break;
      case ACTION_TOAST:
        parseToastAction(deepLinkActionRegistry);
        break;
      default:
        throw new UnsupportedOperationException("provided segment is not supported: " + segment);
    }
  }

  private void parseNavigationAction(DeepLinkActionRegistry deepLinkActionRegistry) {
    String shortNavigationDestiny = deepLinkActionRegistry.requestParameter();
    navigationParser.parse(deepLinkActionRegistry, shortNavigationDestiny);
  }

  private void parseNotificationAction(DeepLinkActionRegistry deepLinkActionRegistry) {
    String title = deepLinkActionRegistry.requestParameter();
    String message = deepLinkActionRegistry.requestParameter();
    notificationParser.parse(deepLinkActionRegistry, title, message);
  }

  private void parseToastAction(DeepLinkActionRegistry deepLinkActionRegistry) {
    String message = deepLinkActionRegistry.requestParameter();
    toastParser.parse(deepLinkActionRegistry, message);
  }
}
