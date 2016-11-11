package net.sgonzalez.example.data.mapper;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.domain.model.Model;

public abstract class AbsMapper<M extends Model<?>, E extends Entity<?, M>> {
  private final Class<E> entityClass;

  protected AbsMapper(Class<E> entityClass) {
    this.entityClass = entityClass;
  }

  @NonNull
  public M toModel(@NonNull E source) {
    //noinspection unchecked
    return source.toModel();
  }

  public List<M> toModel(@NonNull List<E> source) {
    List<M> result = new ArrayList<>(source.size());
    for (E entity : source) {
      result.add(toModel(entity));
    }
    return result;
  }

  @NonNull
  private E toEntity(@NonNull M model) {
    try {
      return entityClass.getConstructor(model.getClass())
                        .newInstance(model);
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public List<E> toEntity(@NonNull List<M> source) {
    List<E> result = new ArrayList<>(source.size());
    for (M model : source) {
      result.add(toEntity(model));
    }
    return result;
  }
}
