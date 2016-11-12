package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.ItemEntity;

public class ItemModel {
  private final String resourceURI;
  private final String name;

  public ItemModel(@NonNull ItemEntity source) {
    this(source.getResourceURI(), source.getName());
  }

  public ItemModel(String resourceURI, String name) {
    this.resourceURI = resourceURI;
    this.name = name;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "ItemEntity{" +
           "resourceURI='" + resourceURI + '\'' +
           ", name='" + name + '\'' +
           '}';
  }
}
