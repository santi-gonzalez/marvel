package net.sgonzalez.example.data.datasource;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.entity.Entity;

public abstract class AbsMemoryLocalDataSource<IdType, E extends Entity<IdType, ?>> implements DataSource {
  private final HashMap<IdType, E> entityMap;

  public AbsMemoryLocalDataSource() {
    this.entityMap = new HashMap<>();
  }

  protected final HashMap<IdType, E> memory() {
    return entityMap;
  }

  public void store(final List<E> entities, @NonNull final Callbacks<List<E>> callbacks) {
    for (E entity : entities) {
      memory().put(entity.getId(), entity);
    }
    callbacks.onDone(entities);
  }

  public void getAll(@NonNull final Callbacks<List<E>> callbacks) {
    callbacks.onDone(new ArrayList<>(memory().values()));
  }

  public void count(@NonNull final Callbacks<Integer> callbacks) {
    callbacks.onDone(memory().size());
  }

  public void get(IdType id, @NonNull final Callbacks<E> callbacks) {
    callbacks.onDone(memory().get(id));
  }
}
