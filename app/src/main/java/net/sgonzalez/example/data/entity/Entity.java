package net.sgonzalez.example.data.entity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.app.identifier.Id;
import net.sgonzalez.example.domain.model.Model;

public interface Entity<IdType, M extends Model<IdType>> {
  Id<IdType> getId();
  void setId(Id<IdType> id);
  @NonNull
  M toModel();
}
