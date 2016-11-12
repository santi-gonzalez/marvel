package net.sgonzalez.example.data.entity.impl;

import io.realm.RealmObject;

public class Thumbnail extends RealmObject {
  private String path;
  private String extension;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  @Override
  public String toString() {
    return "Thumbnail{" +
           "path='" + path + '\'' +
           ", extension='" + extension + '\'' +
           '}';
  }
}
