package net.sgonzalez.example.presentation.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import javax.inject.Inject;
import net.sgonzalez.example.app.deeplink.DeepLinkResolver;
import net.sgonzalez.example.app.dependency.component.AndroidComponent;
import net.sgonzalez.example.presentation.ui.activity.AbsActivity;

public class DeepLinkActivity extends AbsActivity {
  @Inject DeepLinkResolver deepLinkResolver;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    deepLinkResolver.parse(getIntent().getData(), this);
    finish();
  }

  @Override
  protected void onInject(AndroidComponent androidComponent) {
    androidComponent.inject(this);
  }

  @Override
  protected void onBindPresentable() {
    // TODO: 06/11/2016 implement
  }

  @Override
  protected int getContentView() {
    return 0;
  }
}
