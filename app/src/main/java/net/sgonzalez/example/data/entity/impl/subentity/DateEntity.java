package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.DateModel;

public class DateEntity {
  private String type;
  private String date;

  public DateEntity(@NonNull DateModel source) {
    this(source.getType(), source.getDate());
  }

  public DateEntity(String type, String date) {
    this.type = type;
    this.date = date;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override public String toString() {
    return "DateEntity{" +
           "type='" + type + '\'' +
           ", date='" + date + '\'' +
           '}';
  }
}
