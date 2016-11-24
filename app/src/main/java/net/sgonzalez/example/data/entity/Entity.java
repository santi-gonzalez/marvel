package net.sgonzalez.example.data.entity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.Model;

public interface Entity<IdType, M extends Model<IdType>> {
  IdType getId();

  void setId(IdType id);

  @NonNull M toModel();
}
