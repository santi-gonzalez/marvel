package net.sgonzalez.example.app.deeplink.parser.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.parser.AbsParser;
import net.sgonzalez.example.app.di.scope.ApplicationScope;

@ApplicationScope public class ExampleParser extends AbsParser {
  public static final int INDEX_PARAM_1 = 0;
  public static final int INDEX_PARAM_2 = 1;

  @Inject
  public ExampleParser(ActionFactory actionFactory) {
    super(actionFactory);
  }

  @Override
  protected int getExpectedParamCount() {
    return 0;
  }

  @Override
  public void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    // retrieve params and do something
  }
}
