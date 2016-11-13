package net.sgonzalez.example.presentation.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import net.sgonzalez.example.R;

public abstract class AbsBottomLoaderAdapter<VH extends RecyclerView.ViewHolder>
extends RecyclerView.Adapter<AbsBottomLoaderAdapter.InnerViewHolder<VH>> {
  public static final int LOADER_LAYOUT_RES_ID = R.layout.list_item_loader;

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

  public int getItemViewTypeBLA(int position) {
    return super.getItemViewType(position);
  }

  public abstract VH onCreateViewHolderBLA(ViewGroup parent, int viewType);
  public abstract void onBindViewHolderBLA(VH holder, int position);
  public abstract int getItemCountBLA();

  private InnerViewHolder<VH> onCreateViewHolderInternal(ViewGroup parent, int viewType) {
    return new InnerViewHolder<>(LayoutInflater.from(parent.getContext())
                                               .inflate(viewType, parent, false));
  }

  private void onBindViewHolderInternal(InnerViewHolder<VH> holder, int position) {
    // do nothing
  }

  public static class InnerViewHolder<VH> extends RecyclerView.ViewHolder {
    @Nullable public VH childViewHolder;
    private ProgressBar progressBar; // in case we need to do something with it in the future...

    public InnerViewHolder(View itemView) {
      this(itemView, null);
    }

    public InnerViewHolder(View itemView, @Nullable VH childViewHolder) {
      super(itemView);
      this.childViewHolder = childViewHolder;
      progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
    }
  }
}
