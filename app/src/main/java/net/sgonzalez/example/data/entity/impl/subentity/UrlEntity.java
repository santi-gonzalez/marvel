package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.UrlModel;

public class UrlEntity {
  private String type;
  private String url;

  // Realm requirement
  public UrlEntity() {
    this(null, null);
  }

  public UrlEntity(@NonNull UrlModel source) {
    this(source.getUrl(), source.getType());
  }

  public UrlEntity(String type, String url) {
    this.type = type;
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "UrlEntity{" +
           "type='" + type + '\'' +
           ", url='" + url + '\'' +
           '}';
  }
}
