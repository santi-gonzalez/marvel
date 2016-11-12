package net.sgonzalez.example.domain.model.impl;

public class Thumbnail {
  private final String path;
  private final String extension;

  public Thumbnail(String path, String extension) {
    this.path = path;
    this.extension = extension;
  }

  public String getPath() {
    return path;
  }

  public String getExtension() {
    return extension;
  }

  public String getFullPath() {
    return getPath() + "." + getExtension();
  }

  @Override
  public String toString() {
    return "Thumbnail{" +
           "path='" + path + '\'' +
           ", extension='" + extension + '\'' +
           '}';
  }
}
