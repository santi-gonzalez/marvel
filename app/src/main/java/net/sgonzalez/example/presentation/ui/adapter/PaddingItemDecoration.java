package net.sgonzalez.example.presentation.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {
  private final int paddingPx;

  public PaddingItemDecoration(int paddingPx) {
    this.paddingPx = paddingPx;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.top += paddingPx;
    outRect.bottom += paddingPx;
    outRect.left += paddingPx;
    outRect.right += paddingPx;
  }
}
