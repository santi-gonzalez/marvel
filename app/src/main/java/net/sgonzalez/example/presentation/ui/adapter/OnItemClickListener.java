package net.sgonzalez.example.presentation.ui.adapter;

import android.view.View;
import net.sgonzalez.example.domain.model.impl.CharacterModel;

public interface OnItemClickListener {
  void onItemClicked(View view, CharacterModel item);
}
