package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.UrlEntity;

public class UrlModel {
  private final String type;
  private final String url;

  public UrlModel(@NonNull UrlEntity source) {
    this(source.getType(), source.getUrl());
  }

  public UrlModel(String type, String url) {
    this.type = type;
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "UrlModel{" +
           "type='" + type + '\'' +
           ", url='" + url + '\'' +
           '}';
  }
}
