package net.sgonzalez.example.presentation.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PaddingItemDecoration
extends RecyclerView.ItemDecoration {
  private final int paddingPx;
  private final boolean excludeLast;

  public PaddingItemDecoration(int paddingPx) {
    this(paddingPx, false);
  }

  public PaddingItemDecoration(int paddingPx, boolean excludeLast) {
    this.paddingPx = paddingPx;
    this.excludeLast = excludeLast;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (excludeLast && isLastItem(view, parent)) {
      return;
    }
    outRect.top += paddingPx;
    outRect.bottom += paddingPx;
    outRect.left += paddingPx;
    outRect.right += paddingPx;
  }

  private boolean isLastItem(View view, RecyclerView parent) {
    return parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1;
  }
}
