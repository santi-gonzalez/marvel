package net.sgonzalez.example.presentation.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import net.sgonzalez.example.R;

/**
 * Works as a regular RecyclerView adapter, but manages a loader ProgressBar at the bottom of it.
 */
public abstract class BottomLoaderAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<BottomLoaderAdapter.InnerViewHolder<VH>> {
  private static final int LOADER_LAYOUT_RES_ID = R.layout.list_item_loader;
  private static final boolean VISIBLE_DEFAULT = true;
  private boolean visible;

  /**
   * Loader visible by default.
   */
  public BottomLoaderAdapter() {
    this(VISIBLE_DEFAULT);
  }

  public BottomLoaderAdapter(boolean visible) {
    this.visible = visible;
  }

  @Override
  public final int getItemViewType(int position) {
    return position == getItemCount() - 1 ? LOADER_LAYOUT_RES_ID : getItemViewTypeBLA(position);
  }

  @Override
  public final InnerViewHolder<VH> onCreateViewHolder(ViewGroup parent, int viewType) {
    InnerViewHolder<VH> result;
    if (viewType == LOADER_LAYOUT_RES_ID) {
      result = onCreateViewHolderInternal(parent, viewType);
    } else {
      VH viewHolder = onCreateViewHolderBLA(parent, viewType);
      result = new InnerViewHolder<>(viewHolder.itemView, viewHolder);
    }
    return result;
  }

  @Override
  public final void onBindViewHolder(InnerViewHolder<VH> holder, int position) {
    if (position == getItemCount() - 1) {
      onBindViewHolderInternal(holder, position);
    } else {
      onBindViewHolderBLA(holder.childViewHolder, position);
    }
  }

  @Override
  public final int getItemCount() {
    return getItemCountBLA() + 1;
  }

  /**
   * Replace regular {@link #getItemViewType(int)} for this version.
   */
  public int getItemViewTypeBLA(int position) {
    return super.getItemViewType(position);
  }

  /**
   * Replace regular {@link #onCreateViewHolder(ViewGroup, int)} for this version.
   */
  public abstract VH onCreateViewHolderBLA(ViewGroup parent, int viewType);
  /**
   * Replace regular {@link #onBindViewHolder(RecyclerView.ViewHolder, int)} for this version.
   */
  public abstract void onBindViewHolderBLA(VH holder, int position);
  /**
   * Replace regular {@link #getItemCount()} for this version.
   */
  public abstract int getItemCountBLA();

  /**
   * Show the loader ProgressBar.
   */
  public void showLoading() {
    visible = true;
    notifyItemChanged(getItemCount() - 1);
  }

  /**
   * Hide the loader ProgressBar.
   */
  public void hideLoading() {
    visible = false;
    notifyItemChanged(getItemCount() - 1);
  }

  private InnerViewHolder<VH> onCreateViewHolderInternal(ViewGroup parent, int viewType) {
    return new InnerViewHolder<>(LayoutInflater.from(parent.getContext())
                                               .inflate(viewType, parent, false));
  }

  private void onBindViewHolderInternal(InnerViewHolder<VH> holder, int position) {
    holder.progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

  static final class InnerViewHolder<VH> extends RecyclerView.ViewHolder {
    @Nullable public VH childViewHolder;
    private ProgressBar progressBar;

    public InnerViewHolder(View itemView) {
      this(itemView, null);
    }

    public InnerViewHolder(View itemView, @Nullable VH childViewHolder) {
      super(itemView);
      if (childViewHolder != null) {
        this.childViewHolder = childViewHolder;
      } else {
        this.progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
      }
    }
  }

  /**
   * Use this class as a {@link android.support.v7.widget.GridLayoutManager.SpanSizeLookup}, providing the adapter and specifying the total span
   * count, if you wish the loader to be centered.
   */
  public static class SpanSizeLookupBLA extends GridLayoutManager.SpanSizeLookup {
    private final BottomLoaderAdapter adapter;
    private final int spanCount;

    public SpanSizeLookupBLA(BottomLoaderAdapter adapter, int spanCount) {
      this.adapter = adapter;
      this.spanCount = spanCount;
    }

    @Override
    public final int getSpanSize(int position) {
      if (position == adapter.getItemCount() - 1) {
        return spanCount;
      } else {
        return getSpanSizeBLA(position);
      }
    }

    public int getSpanSizeBLA(int position) {
      return 1;
    }
  }
}
