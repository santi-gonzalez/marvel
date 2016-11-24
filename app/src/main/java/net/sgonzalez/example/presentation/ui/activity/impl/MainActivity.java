package net.sgonzalez.example.presentation.ui.activity.impl;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.R;
import net.sgonzalez.example.app.dependency.component.AndroidComponent;
import net.sgonzalez.example.app.navigation.Navigator;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.presentation.presenter.impl.MainPresenter;
import net.sgonzalez.example.presentation.ui.activity.AbsActivity;
import net.sgonzalez.example.presentation.ui.adapter.BottomLoaderAdapter;
import net.sgonzalez.example.presentation.ui.adapter.CharactersAdapter;
import net.sgonzalez.example.presentation.ui.adapter.ComicsAdapter;
import net.sgonzalez.example.presentation.ui.adapter.EndlessScrollListener;
import net.sgonzalez.example.presentation.ui.adapter.OnItemClickListener;
import net.sgonzalez.example.presentation.ui.adapter.PaddingItemDecoration;

public class MainActivity
extends AbsActivity
implements MainPresenter.Presentable {
  public static final int COMICS_SPAN_COUNT = 2;
  @Inject MainPresenter mainPresenter;
  @Inject Navigator navigator;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.content_frame) CoordinatorLayout contentFrame;
  @BindView(R.id.left_drawer_recycler_view) RecyclerView charactersRecyclerView;
  @BindView(R.id.comics_recycler_view) RecyclerView comicsRecyclerView;
  @BindView(R.id.toolbar) Toolbar toolbar;
  private ActionBarDrawerToggle drawerToggle;
  private CharactersAdapter charactersAdapter;
  private ComicsAdapter comicsAdapter;
  private EndlessScrollListener comicsEndlessScrollListener;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDrawer();
    initComicsList();
    mainPresenter.onViewReady();
  }

  @Override protected void onInject(AndroidComponent androidComponent) {
    androidComponent.inject(this);
  }

  @Override protected void onBindPresentable() {
    mainPresenter.bindPresentable(this);
  }

  @Override protected int getContentView() {
    return R.layout.activity_main;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mainPresenter.onSaveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    mainPresenter.onRestoreInstanceState(savedInstanceState);
  }

  private void initDrawer() {
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
    drawerLayout.addDrawerListener(drawerToggle);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    initCharactersList();
  }

  private void initCharactersList() {
    // init adapter
    charactersAdapter = new CharactersAdapter();
    charactersAdapter.setOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClicked(View view, CharacterModel character) {
        drawerLayout.closeDrawers();
        comicsEndlessScrollListener.disable();
        mainPresenter.onCharacterClicked(character);
      }
    });
    // init layout manager
    RecyclerView.LayoutManager linearLayoutManager = createCharactersLayoutManager();
    // init recycler view
    charactersRecyclerView.setLayoutManager(linearLayoutManager);
    charactersRecyclerView.setAdapter(charactersAdapter);
    charactersRecyclerView.addOnScrollListener(new EndlessScrollListener() {
      @Override public void onLoadMore(int currentPage) {
        mainPresenter.onCharactersBottomReached(currentPage);
      }
    });
  }

  private LinearLayoutManager createCharactersLayoutManager() {
    return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
  }

  private void initComicsList() {
    // init adapter
    comicsAdapter = new ComicsAdapter();
    // init layout manager
    RecyclerView.LayoutManager linearLayoutManager = createComicsLayoutManager(comicsAdapter);
    // init recycler view;
    comicsRecyclerView.setLayoutManager(linearLayoutManager);
    comicsRecyclerView.addItemDecoration(new PaddingItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_gap),
                                                                   true));
    comicsRecyclerView.setAdapter(comicsAdapter);
    comicsEndlessScrollListener = new EndlessScrollListener() {
      @Override public void onLoadMore(int currentPage) {
        mainPresenter.onComicsBottomReached(currentPage);
      }
    };
    comicsRecyclerView.addOnScrollListener(comicsEndlessScrollListener);
  }

  private LinearLayoutManager createComicsLayoutManager(BottomLoaderAdapter adapter) {
    if (isPortrait()) {
      return new LinearLayoutManager(this);
    } else {
      GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COMICS_SPAN_COUNT);
      gridLayoutManager.setSpanSizeLookup(new BottomLoaderAdapter.SpanSizeLookupBLA(adapter, COMICS_SPAN_COUNT));
      return gridLayoutManager;
    }
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    drawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    switch (item.getItemId()) {
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    issueTestNotification();
  }

  private void issueTestNotification() {
    // URL syntax: scheme://authority/path1/path2/path3...
    final String URL = "framework://open/nav/fil/not/hello/world/toa/hello";
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(URL));
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(0,
                               new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification)
                                                                   .setContentTitle("Deep link notification")
                                                                   .setContentText(URL)
                                                                   .setContentIntent(PendingIntent.getActivity(this,
                                                                                                               0,
                                                                                                               intent,
                                                                                                               PendingIntent
                                                                                                               .FLAG_UPDATE_CURRENT))
                                                                   .build());
  }

  @Override public void appendCharacters(List<CharacterModel> characters) {
    charactersAdapter.appendItems(characters);
  }

  @Override public void appendComics(List<ComicModel> comics) {
    comicsAdapter.appendItems(comics);
    comicsEndlessScrollListener.enable();
  }

  @Override public void clearComicsWall() {
    comicsAdapter.clear();
    comicsEndlessScrollListener.reset();
  }

  @Override public void showWallLoading() {
    comicsAdapter.showLoading();
  }

  @Override public void hideWallLoading() {
    comicsAdapter.hideLoading();
  }

  @Override public void showError(String message) {
    Snackbar.make(contentFrame, message, Snackbar.LENGTH_LONG).show();
  }
}
