package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.domain.model.id.Id;
import net.sgonzalez.example.domain.model.id.impl.LongId;
import net.sgonzalez.example.domain.model.impl.ComicModel;

public class ComicEntity extends RealmObject implements Entity<Long, ComicModel> {
  @PrimaryKey private Long id;

  // Realm requirement
  public ComicEntity() {
    this(0L);
  }

  // Mapper requirement
  public ComicEntity(@NonNull ComicModel source) {
    this(source.getId()
               .get());
  }

  public ComicEntity(Long id) {
    this.id = id;
  }

  @Override
  public Id<Long> getId() {
    return new LongId(id);
  }

  @Override
  public void setId(Id<Long> id) {
    this.id = id.get();
  }

  @NonNull
  @Override
  public ComicModel toModel() {
    return ComicModel.newBuilder(getId())
                     .build();
  }

  @Override
  public String toString() {
    return "ComicEntity{" +
           "id='" + id + '\'' +
           '}';
  }
}
