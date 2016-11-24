package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.DateEntity;

public class DateModel {
  private final String type;
  private final String date;

  public DateModel(@NonNull DateEntity source) {
    this(source.getType(), source.getDate());
  }

  public DateModel(String type, String date) {
    this.type = type;
    this.date = date;
  }

  public String getType() {
    return type;
  }

  public String getDate() {
    return date;
  }

  @Override public String toString() {
    return "DateModel{" +
           "type='" + type + '\'' +
           ", date='" + date + '\'' +
           '}';
  }
}
