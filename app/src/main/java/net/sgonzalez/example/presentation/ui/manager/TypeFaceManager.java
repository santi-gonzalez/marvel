package net.sgonzalez.example.presentation.ui.manager;

import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;

@SuppressWarnings("SpellCheckingInspection") @ApplicationScope public class TypefaceManager {
  public static final String AMERICAN_CAPTAIN_PATRIUS_02_OTF = "american_captain_patrius_02.otf";
  private static final String[] TYPEFACES = new String[] { AMERICAN_CAPTAIN_PATRIUS_02_OTF };
  private final App app;
  private final Map<String, Typeface> typefaces;

  @Inject
  public TypefaceManager(App app) {
    this.app = app;
    typefaces = new HashMap<>();
    prepare();
  }

  public Typeface getTypeface(String typeface) {
    if (!typefaces.containsKey(typeface)) {
      throw new IllegalArgumentException("requested typeface not present: " + typeface);
    }
    return typefaces.get(typeface);
  }

  private void prepare() {
    for (String typeface : TYPEFACES) {
      typefaces.put(typeface, createTypeface(typeface));
    }
  }

  private Typeface createTypeface(String name) {
    return Typeface.createFromAsset(app.getAssets(), name);
  }
}
