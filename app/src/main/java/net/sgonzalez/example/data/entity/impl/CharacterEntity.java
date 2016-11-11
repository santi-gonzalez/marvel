package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.domain.model.id.Id;
import net.sgonzalez.example.domain.model.id.impl.LongId;
import net.sgonzalez.example.domain.model.impl.CharacterModel;

public class CharacterEntity extends RealmObject implements Entity<Long, CharacterModel> {
  @PrimaryKey private Long id;

  // Realm requirement
  public CharacterEntity() {
    this(0L);
  }

  // Mapper requirement
  public CharacterEntity(@NonNull CharacterModel source) {
    this(source.getId()
               .get());
  }

  public CharacterEntity(Long id) {
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
  public CharacterModel toModel() {
    return CharacterModel.newBuilder(getId())
                         .build();
  }

  @Override
  public String toString() {
    return "CharacterEntity{" +
           "id='" + id + '\'' +
           '}';
  }
}
