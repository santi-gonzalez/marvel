package net.sgonzalez.example.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import net.sgonzalez.example.R;
import net.sgonzalez.example.domain.model.impl.ComicModel;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {
  private final List<ComicModel> dataSet = new ArrayList<>();

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.list_item_comic, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    ComicModel item = getItem(position);
    holder.populate(item);
  }

  @Override
  public int getItemCount() {
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

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view) TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void populate(ComicModel item) {
      textView.setText(String.valueOf(item.getId()));
    }
  }
}