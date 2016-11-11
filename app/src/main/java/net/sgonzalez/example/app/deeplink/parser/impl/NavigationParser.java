package net.sgonzalez.example.app.deeplink.parser.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.parser.AbsParser;
import net.sgonzalez.example.app.deeplink.parser.Parser;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.app.navigation.NavigationDestiny;

@ApplicationScope public class NavigationParser extends AbsParser {
  public static final int INDEX_SHORT_DESTINY_NAME = 0;
  private final Parser exampleParser;

  @Inject
  public NavigationParser(ActionFactory actionFactory, ExampleParser exampleParser) {
    super(actionFactory);
    this.exampleParser = exampleParser;
  }

  @Override
  protected int getExpectedParamCount() {
    return 1;
  }

  @Override
  public void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    String destinyName = params[INDEX_SHORT_DESTINY_NAME];
    NavigationDestiny destiny = NavigationDestiny.from(destinyName);
    deepLinkActionRegistry.registerAction(actionFactory.newNavigationAction(destiny));
    //String param1 = deepLinkActionRegistry.requestParameter();
    //String param2 = deepLinkActionRegistry.requestParameter();
    exampleParser.parse(deepLinkActionRegistry/*, param1, param2, ...*/);
  }
}
