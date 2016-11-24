package net.sgonzalez.example.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import net.sgonzalez.example.R;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.presentation.ui.utils.PicassoUtils;

public class ComicsAdapter
extends BottomLoaderAdapter<ComicsAdapter.ViewHolder> {
  private final List<ComicModel> dataSet = new ArrayList<>();

  public ComicsAdapter() {
    super(false);
  }

  @Override public ViewHolder onCreateViewHolderBLA(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comic, parent, false));
  }

  @Override public void onBindViewHolderBLA(ViewHolder holder, int position) {
    ComicModel item = getItem(position);
    holder.populate(item);
  }

  @Override public int getItemCountBLA() {
    return dataSet.size();
  }

  public ComicModel getItem(int position) {
    return dataSet.get(position);
  }

  public void appendItems(List<ComicModel> comics) {
    int previousCount = getItemCount();
    dataSet.addAll(comics);
    int finalCount = getItemCount();
    notifyItemRangeInserted(previousCount, finalCount);
  }

  public void clear() {
    int previousCount = getItemCount();
    dataSet.clear();
    notifyItemRangeRemoved(0, previousCount);
  }

  public static class ViewHolder
  extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view) TextView textView;
    @BindView(R.id.image_view) ImageView imageView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void populate(ComicModel item) {
      textView.setText(item.getTitle());
      PicassoUtils.placeImage(itemView.getContext(), item.getThumbnail().getFullPath(), imageView);
    }
  }
}