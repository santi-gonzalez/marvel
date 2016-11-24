package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.ImageEntity;

public class ImageModel {
  private final String path;
  private final String extension;

  public ImageModel(@NonNull ImageEntity source) {
    this(source.getPath(), source.getExtension());
  }

  public ImageModel(String path, String extension) {
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

  @Override public String toString() {
    return "ImageModel{" +
           "path='" + path + '\'' +
           ", extension='" + extension + '\'' +
           '}';
  }
}
