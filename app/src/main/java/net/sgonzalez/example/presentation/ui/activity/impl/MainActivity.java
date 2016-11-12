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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
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
import net.sgonzalez.example.presentation.ui.adapter.CharactersAdapter;
import net.sgonzalez.example.presentation.ui.adapter.ComicsAdapter;
import net.sgonzalez.example.presentation.ui.adapter.EndlessScrollListener;

public class MainActivity extends AbsActivity implements MainPresenter.Presentable {
  @Inject MainPresenter mainPresenter;
  @Inject Navigator navigator;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.left_drawer_recycler_view) RecyclerView charactersRecyclerView;
  @BindView(R.id.comics_recycler_view) RecyclerView comicsRecyclerView;
  @BindView(R.id.toolbar) Toolbar toolbar;
  private ActionBarDrawerToggle drawerToggle;
  private CharactersAdapter charactersAdapter;
  private ComicsAdapter comicsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initToolbarDrawer();
    initContentAdapter();
    initContent();
  }

  @Override
  protected void onInject(AndroidComponent androidComponent) {
    androidComponent.inject(this);
  }

  @Override
  protected void onBindPresentable() {
    mainPresenter.attachPresentable(this);
  }

  @Override
  protected int getContentView() {
    return R.layout.activity_main;
  }

  private void initToolbarDrawer() {
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0) {
      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        getSupportActionBar().setTitle("Drawer closed");
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        getSupportActionBar().setTitle("Drawer open");
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }
    };
    drawerLayout.addDrawerListener(drawerToggle);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    initDrawerAdapter();
  }

  private void initDrawerAdapter() {
    charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    charactersAdapter = new CharactersAdapter();
    charactersRecyclerView.setAdapter(charactersAdapter);
    charactersRecyclerView.addOnScrollListener(new EndlessScrollListener() {
      @Override
      public void onLoadMore(int currentPage) {
        mainPresenter.onLoadMoreDrawer(currentPage);
      }
    });
  }

  private void initContentAdapter() {
    comicsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    //comicsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    comicsAdapter = new ComicsAdapter();
    comicsRecyclerView.setAdapter(comicsAdapter);
  }

  private void initContent() {
    mainPresenter.init();
  }

  @NonNull
  @Override
  public ActionBar getSupportActionBar() {
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
      case R.id.request_comics:
        return mainPresenter.onToolbarActionRequestComics();
      case R.id.request_character:
        return mainPresenter.onToolbarActionRequestCharacters();
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
  public void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
         .show();
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
                                                                      .setContentIntent(PendingIntent.getActivity(this, 0, intent,
                                                                      PendingIntent.FLAG_UPDATE_CURRENT))
                                                                      .build());
  }
}
