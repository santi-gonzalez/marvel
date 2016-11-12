package net.sgonzalez.example.presentation.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;
import javax.inject.Inject;
import net.sgonzalez.example.app.App;
import net.sgonzalez.example.app.di.component.AppComponent;
import net.sgonzalez.example.app.di.component.DaggerAndroidComponent;
import net.sgonzalez.example.app.di.module.AndroidModule;
import net.sgonzalez.example.presentation.ui.manager.TypefaceManager;

public class MarvelCharacterNameTextView extends TextView {
  @Inject TypefaceManager typefaceManager;

  public MarvelCharacterNameTextView(Context context) {
    super(context);
    init(context);
  }

  public MarvelCharacterNameTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public MarvelCharacterNameTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public MarvelCharacterNameTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context);
  }

  private void init(Context context) {
    if (isInEditMode()) {
      return;
    }
    inject(context);
  }

  private void inject(Context context) {
    DaggerAndroidComponent.builder()
                          .appComponent(getAppComponent(context))
                          .androidModule(new AndroidModule(context))
                          .build()
                          .inject(this);
    setTypeface(typefaceManager.getTypeface(TypefaceManager.AMERICAN_CAPTAIN_PATRIUS_02_OTF));
  }

  private AppComponent getAppComponent(Context context) {
    return ((App) context.getApplicationContext()).getAppComponent();
  }
}
