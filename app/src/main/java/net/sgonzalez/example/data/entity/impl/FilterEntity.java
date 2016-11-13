package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import net.sgonzalez.example.app.identifier.Id;
import net.sgonzalez.example.app.identifier.impl.StringId;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.domain.model.impl.FilterModel;

public class FilterEntity implements Entity<String, FilterModel> {
  private String id;
  private String key;
  private String value;

  // Realm requirement
  public FilterEntity() {
    this(null, null, null);
  }

  // Mapper requirement
  public FilterEntity(@NonNull FilterModel source) {
    this(source.getId()
               .get(), source.getKey(), source.getValue());
  }

  public FilterEntity(String id, String key, String value) {
    this.id = id;
    this.key = key;
    this.value = value;
  }

  @Override
  public Id<String> getId() {
    return new StringId(id);
  }

  @Override
  public void setId(Id<String> id) {
    this.id = id.get();
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @NonNull
  @Override
  public FilterModel toModel() {
    return FilterModel.newBuilder()
                      .withId(getId())
                      .withKey(getKey())
                      .withValue(getValue())
                      .build();
  }

  @Override
  public String toString() {
    return "FilterEntity{" +
           "id='" + id + '\'' +
           ", key='" + key + '\'' +
           ", value='" + value + '\'' +
           '}';
  }
}
