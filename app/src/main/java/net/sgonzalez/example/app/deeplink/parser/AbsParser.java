package net.sgonzalez.example.app.deeplink.parser;

import java.util.Arrays;
import net.sgonzalez.example.app.deeplink.DeepLinkActionRegistry;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.log.Logger;

public abstract class AbsParser implements Parser {
  protected final ActionFactory actionFactory;

  public AbsParser(ActionFactory actionFactory) {
    this.actionFactory = actionFactory;
  }

  @Override
  public void parse(DeepLinkActionRegistry deepLinkActionRegistry, String... params) {
    if (!checkParams(params)) {
      throw new IllegalArgumentException("param count does not match; expected "
                                         + getExpectedParamCount()
                                         + " got "
                                         + getProvidedParamsCount(params));
    }
    logVerbose("parsing... (params " + Arrays.asList(params) + ")");
    onParse(deepLinkActionRegistry, params);
  }

  private boolean checkParams(String... params) {
    return getProvidedParamsCount(params) == getExpectedParamCount();
  }

  private int getProvidedParamsCount(String... params) {
    return params == null ? 0 : params.length;
  }

  protected abstract int getExpectedParamCount();
  protected abstract void onParse(DeepLinkActionRegistry deepLinkActionRegistry, String... params);

  protected void logVerbose(String message) {
    Logger.logVerbose(getClass().getSimpleName(), message);
  }
}
