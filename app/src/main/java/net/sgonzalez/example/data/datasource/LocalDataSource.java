package net.sgonzalez.example.data.datasource;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.Matcher;
import net.sgonzalez.example.data.entity.Entity;

public interface LocalDataSource<E extends Entity> {
  void store(final List<E> entities, @NonNull final Callbacks<List<E>> callbacks);

  void get(@NonNull Matcher<E> matcher, @NonNull final Callbacks<E> callbacks);

  void getAll(@NonNull final Callbacks<List<E>> callbacks);

  void getAll(@Nullable Matcher<E> matcher, @NonNull final Callbacks<List<E>> callbacks);

  void count(@NonNull final Callbacks<Integer> callbacks);

  void count(@Nullable Matcher<E> matcher, @NonNull final Callbacks<Integer> callbacks);
}
