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
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.presentation.ui.utils.PicassoUtils;

public class CharactersAdapter
extends BottomLoaderAdapter<CharactersAdapter.ViewHolder> {
  private final List<CharacterModel> dataSet = new ArrayList<>();
  private OnItemClickListener onItemClickListener;

  @Override public ViewHolder onCreateViewHolderBLA(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character, parent, false));
  }

  @Override public void onBindViewHolderBLA(ViewHolder holder, int position) {
    CharacterModel item = getItem(position);
    holder.populate(holder.itemView, item, onItemClickListener);
  }

  @Override public int getItemCountBLA() {
    return dataSet.size();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public CharacterModel getItem(int position) {
    return dataSet.get(position);
  }

  public void appendItems(List<CharacterModel> characters) {
    int previousCount = getItemCount();
    dataSet.addAll(characters);
    int finalCount = getItemCount();
    notifyItemRangeInserted(previousCount, finalCount);
  }

  public static class ViewHolder
  extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view_character) TextView textView;
    @BindView(R.id.image_view_character) ImageView imageView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void populate(View view, final CharacterModel item, final OnItemClickListener onItemClickListener) {
      textView.setText(String.valueOf(item.getName()));
      PicassoUtils.placeImage(this.itemView.getContext(), item.getThumbnail().getFullPath(), imageView);
      view.setOnClickListener(new View.OnClickListener() {

        @Override public void onClick(View view) {
          if (onItemClickListener != null) {
            onItemClickListener.onItemClicked(view, item);
          }
        }
      });
    }
  }
}
