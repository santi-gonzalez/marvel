package net.sgonzalez.example.presentation.ui.activity.impl;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import net.sgonzalez.example.presentation.ui.adapter.AbsBottomLoaderAdapter;
import net.sgonzalez.example.presentation.ui.adapter.CharactersAdapter;
import net.sgonzalez.example.presentation.ui.adapter.ComicsAdapter;
import net.sgonzalez.example.presentation.ui.adapter.EndlessScrollListener;
import net.sgonzalez.example.presentation.ui.adapter.OnItemClickListener;

public class MainActivity extends AbsActivity implements MainPresenter.Presentable {
  public static final int COMICS_SPAN_COUNT = 2;
  @Inject MainPresenter mainPresenter;
  @Inject Navigator navigator;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.left_drawer_recycler_view) RecyclerView charactersRecyclerView;
  @BindView(R.id.comics_recycler_view) RecyclerView comicsRecyclerView;
  @BindView(R.id.toolbar) Toolbar toolbar;
  private ActionBarDrawerToggle drawerToggle;
  private CharactersAdapter charactersAdapter;
  private ComicsAdapter comicsAdapter;
  private EndlessScrollListener comicsEndlessScrollListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolbarDrawer();
    initComicsList();
    mainPresenter.onViewReady();
  }

  @Override
  protected void onInject(AndroidComponent androidComponent) {
    androidComponent.inject(this);
  }

  @Override
  protected void onBindPresentable() {
    mainPresenter.bindPresentable(this);
  }

  @Override
  protected int getContentView() {
    return R.layout.activity_main;
  }

  private void initToolbarDrawer() {
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
      @Override
      public void onItemClicked(View view, CharacterModel character) {
        drawerLayout.closeDrawers();
        mainPresenter.onCharacterClicked(character);
      }
    });
    // init layout manager
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    // init recycler view
    charactersRecyclerView.setLayoutManager(linearLayoutManager);
    charactersRecyclerView.setAdapter(charactersAdapter);
    charactersRecyclerView.addOnScrollListener(new EndlessScrollListener() {
      @Override
      public void onLoadMore(int currentPage) {
        mainPresenter.onCharactersBottomReached(currentPage);
      }
    });
  }

  private void initComicsList() {
    // init adapter
    comicsAdapter = new ComicsAdapter();
    // init layout manager
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COMICS_SPAN_COUNT);
    gridLayoutManager.setSpanSizeLookup(new AbsBottomLoaderAdapter.SpanSizeLookupBLA(comicsAdapter, COMICS_SPAN_COUNT));
    // init recycler view
    comicsRecyclerView.setLayoutManager(gridLayoutManager);
    comicsRecyclerView.setAdapter(comicsAdapter);
    comicsEndlessScrollListener = new EndlessScrollListener() {
      @Override
      public void onLoadMore(int currentPage) {
        Log.e(">>>", "bottom reached!");
        mainPresenter.onComicsBottomReached(currentPage);
      }
    };
    comicsRecyclerView.addOnScrollListener(comicsEndlessScrollListener);
  }

  @NonNull
  @Override
  public final ActionBar getSupportActionBar() {
    //noinspection ConstantConditions
    return super.getSupportActionBar();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    drawerToggle.syncState();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    switch (item.getItemId()) {
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    issueTestNotification();
  }

  @Override
  public void appendCharacters(List<CharacterModel> characters) {
    charactersAdapter.appendItems(characters);
  }

  @Override
  public void appendComics(List<ComicModel> comics) {
    comicsAdapter.appendItems(comics);
  }

  @Override
  public void clearComicsWall() {
    comicsAdapter.clear();
    comicsEndlessScrollListener.reset();
  }

  @Override
  public void showWallLoading() {
    comicsAdapter.showLoading();
  }

  @Override
  public void hideWallLoading() {
    comicsAdapter.hideLoading();
  }

  private void issueTestNotification() {
    // URL syntax: scheme://authority/path1/path2/path3...
    final String URL = "framework://open/nav/fil/not/hello/world/toa/hello";
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(URL));
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(0, new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification)
                                                                      .setContentTitle("Deep link notification")
                                                                      .setContentText(URL)
                                                                      .setContentIntent(
                                                                      PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                                                                      .build());
  }
}
