package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.CallbacksAdapter;
import net.sgonzalez.example.data.callbacks.Matcher;
import net.sgonzalez.example.data.datasource.AbsIdMapDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;

@ApplicationScope public class CharacterLocalDataSource
extends AbsIdMapDataSource<Long, CharacterEntity> {
  @Inject public CharacterLocalDataSource() {
  }

  @Override public void getAll(@NonNull final Callbacks<List<CharacterEntity>> callbacks) {
    super.getAll(new CallbacksAdapter<List<CharacterEntity>>(callbacks) {

      @Override public void onResult(List<CharacterEntity> result) {
        callbacks.onResult(sortByName(result));
      }
    });
  }

  @Override public void getAll(@Nullable Matcher<CharacterEntity> matcher, @NonNull Callbacks<List<CharacterEntity>> callbacks) {
    super.getAll(matcher, new CallbacksAdapter<List<CharacterEntity>>() {

      @Override public void onResult(List<CharacterEntity> result) {
        super.onResult(sortByName(result));
      }
    });
  }

  private List<CharacterEntity> sortByName(List<CharacterEntity> set) {
    Collections.sort(set, new Comparator<CharacterEntity>() {

      @Override public int compare(CharacterEntity left, CharacterEntity right) {
        return left.getName().compareTo(right.getName());
      }
    });
    return set;
  }
}
