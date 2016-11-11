package net.sgonzalez.example.presentation.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import net.sgonzalez.example.R;
import net.sgonzalez.example.app.di.component.AndroidComponent;
import net.sgonzalez.example.presentation.ui.activity.AbsActivity;

public class FiltersActivity extends AbsActivity {
  public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
  @BindView(R.id.message) TextView message;
  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(toolbar);
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
    return R.layout.activity_filters;
  }

  @Override
  protected void onExtras(@NonNull Bundle extras) {
    message.setText(extras.getString(EXTRA_MESSAGE, "default message"));
  }
}
