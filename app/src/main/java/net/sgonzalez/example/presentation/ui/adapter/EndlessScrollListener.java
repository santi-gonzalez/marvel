package net.sgonzalez.example.presentation.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
  private static final int DEFAULT_VISIBLE_THRESHOLD = 5;
  private boolean enabled;
  private int previousTotal; // The total number of items in the data set after the last load
  private boolean loading; // True if we are still waiting for the last set of data to load.
  private int visibleThreshold; // The minimum amount of items to have below your current scroll position before loading more.
  private int currentPage;

  public EndlessScrollListener() {
    this(DEFAULT_VISIBLE_THRESHOLD);
  }

  public EndlessScrollListener(int visibleThreshold) {
    this.visibleThreshold = visibleThreshold;
    reset();
    enable();
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) layoutManager;
      int visibleItemCount = recyclerView.getChildCount();
      int totalItemCount = mLinearLayoutManager.getItemCount();
      int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
      if (loading) {
        if (totalItemCount > previousTotal) {
          loading = false;
          previousTotal = totalItemCount;
        }
      }
      if (enabled && !loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
        // End has been reached
        currentPage++;
        onLoadMore(currentPage);
        loading = true;
      }
    }
  }

  public abstract void onLoadMore(int currentPage);

  public void reset() {
    this.previousTotal = 0;
    this.loading = true;
    this.currentPage = 0;
  }

  public void enable() {
    enabled = true;
  }

  public void disable() {
    enabled = false;
  }
}
