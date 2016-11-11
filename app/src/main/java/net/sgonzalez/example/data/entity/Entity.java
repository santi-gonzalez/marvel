package net.sgonzalez.example.data.entity;

import android.support.annotation.NonNull;
import io.realm.RealmModel;
import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.domain.model.id.Id;

public interface Entity<IdType, M extends Model<IdType>> extends RealmModel {
  Id<IdType> getId();
  void setId(Id<IdType> id);
  @NonNull
  M toModel();
}
