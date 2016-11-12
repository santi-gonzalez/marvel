package net.sgonzalez.example.data.entity.impl;

import io.realm.RealmObject;

public class Url extends RealmObject {
  private String type;
  private String url;

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
    return "Url{" +
           "type='" + type + '\'' +
           ", url='" + url + '\'' +
           '}';
  }
}
