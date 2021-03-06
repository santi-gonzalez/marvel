package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.ItemModel;

public class ItemEntity {
  private String resourceURI;
  private String name;

  public ItemEntity(@NonNull ItemModel source) {
    this(source.getResourceURI(), source.getName());
  }

  public ItemEntity(String resourceURI, String name) {
    this.resourceURI = resourceURI;
    this.name = name;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return "ItemEntity{" +
           "resourceURI='" + resourceURI + '\'' +
           ", name='" + name + '\'' +
           '}';
  }
}
