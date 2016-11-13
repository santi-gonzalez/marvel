package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;

public class ImageEntity {
  private String path;
  private String extension;

  // Realm requirement
  public ImageEntity() {
    this(null, null);
  }

  public ImageEntity(@NonNull ImageModel source) {
    this(source.getPath(), source.getExtension());
  }

  public ImageEntity(String path, String extension) {
    this.path = path;
    this.extension = extension;
  }

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
    return "ImageEntity{" +
           "path='" + path + '\'' +
           ", extension='" + extension + '\'' +
           '}';
  }
}
