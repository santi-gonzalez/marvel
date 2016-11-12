package net.sgonzalez.example.app.deeplink;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import javax.inject.Inject;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.deeplink.action.Action;
import net.sgonzalez.example.app.deeplink.action.impl.ActionFactory;
import net.sgonzalez.example.app.deeplink.log.Logger;
import net.sgonzalez.example.app.deeplink.parser.MainParser;
import net.sgonzalez.example.app.deeplink.parser.Parser;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.app.navigation.NavigationDestiny;

/**
 * Invoke any {@code parse(...)} method to start. Provide a valid Uri when needed (i.e.: {@code "framework://open/nav"}). See each
 * parser constant definition or <i>.readme.txt</i> for quick reference.
 * <p>
 * Manually set {@link #__SHOULD_OPEN_APP} in order to force open app on the event of a fatal parsing error (due to malformed URI,
 * or any other kind).
 */
@ApplicationScope public class DeepLinkResolver implements DeepLinkActionRegistry {
  public static final String TAG = DeepLinkResolver.class.getSimpleName();
  public static boolean __SHOULD_OPEN_APP = true;
  private final Parser mainParser;
  private final Stack<Action> actions;
  @Inject ActionFactory actionFactory;
  @Inject App app;
  private Iterator<String> segmentsIterator;
  private Context context;

  @Inject
  public DeepLinkResolver(MainParser mainParser) {
    this.mainParser = mainParser;
    this.actions = new Stack<>();
    this.segmentsIterator = null;
  }

  public void parse(@NonNull Uri uri, @NonNull Context context) {
    parse(uri.getPathSegments(), context);
  }

  public void parse(@NonNull List<String> pathSegments, @NonNull Context context) {
    parseInternal(pathSegments, context);
  }

  private void parseInternal(List<String> pathSegments, Context context) {
    if (segmentsIterator != null) {
      throw new IllegalStateException("parsing in progress");
    }
    this.context = context;
    segmentsIterator = pathSegments.iterator();
    while (segmentsIterator.hasNext()) {
      try {
        mainParser.parse(this, segmentsIterator.next());
      } catch(Exception exception) {
        Logger.logException(TAG, "unexpected error parsing " + pathSegments, exception);
      }
    }
    if (__SHOULD_OPEN_APP) {
      actions.add(actionFactory.newNavigationAction(NavigationDestiny.MAIN));
    }
    resolvePendingActions();
    clearRemainingActions();
    segmentsIterator = null;
  }

  private void resolvePendingActions() {
    while (!actions.isEmpty()) {
      Action action = actions.pop();
      action.execute(app, context);
      if (action.shouldEndExecution()) {
        break;
      }
    }
  }

  @Override
  public String requestParameter() {
    if (segmentsIterator.hasNext()) {
      return segmentsIterator.next();
    }
    throw new IllegalArgumentException("parameter requested but no url segment was provided");
  }

  @Override
  public void registerAction(Action action) {
    actions.push(action);
    Logger.logDebug(TAG, action + " registered successfully!");
  }

  @Override
  public void clearRemainingActions() {
    actions.clear();
    Logger.logDebug(TAG, "action stack is empty");
  }
}
