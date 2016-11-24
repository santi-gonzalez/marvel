package net.sgonzalez.example.data.datasource;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.CallbacksAdapter;
import net.sgonzalez.example.data.callbacks.Matcher;
import net.sgonzalez.example.data.entity.Entity;

public abstract class AbsIdMapDataSource<IdType, E extends Entity<IdType, ?>>
implements LocalDataSource<E> {
  private final HashMap<IdType, E> entityMap;

  public AbsIdMapDataSource() {
    this.entityMap = new HashMap<>();
  }

  protected final HashMap<IdType, E> memory() {
    return entityMap;
  }

  @Override public void store(final List<E> entities, @NonNull final Callbacks<List<E>> callbacks) {
    for (E entity : entities) {
      memory().put(entity.getId(), entity);
    }
    callbacks.onDone(entities);
  }

  public void get(@NonNull IdType id, @NonNull final Callbacks<E> callbacks) {
    callbacks.onDone(memory().get(id));
  }

  @Override public void get(@NonNull Matcher<E> matcher, @NonNull final Callbacks<E> callbacks) {
    getAll(matcher, new CallbacksAdapter<List<E>>(callbacks) {
      @Override public void onDone(List<E> result) {
        callbacks.onDone(result.size() > 0 ? result.get(0) : null);
      }
    });
  }

  @Override public void getAll(@NonNull final Callbacks<List<E>> callbacks) {
    callbacks.onDone(new ArrayList<>(memory().values()));
  }

  @Override public void getAll(@Nullable Matcher<E> matcher, @NonNull final Callbacks<List<E>> callbacks) {
    if (matcher == null) {
      getAll(callbacks);
    } else {
      Collection<E> entities = memory().values();
      ArrayList<E> result = new ArrayList<>();
      for (E entity : entities) {
        if (matcher.isValid(entity)) {
          result.add(entity);
        }
      }
      callbacks.onDone(result);
    }
  }

  @Override public void count(@NonNull final Callbacks<Integer> callbacks) {
    callbacks.onDone(memory().size());
  }

  @Override public void count(@Nullable Matcher<E> matcher, @NonNull Callbacks<Integer> callbacks) {
    if (matcher == null) {
      count(callbacks);
    } else {
      Collection<E> entities = memory().values();
      int size = 0;
      for (E entity : entities) {
        if (matcher.isValid(entity)) {
          size++;
        }
      }
      callbacks.onDone(size);
    }
  }
}
