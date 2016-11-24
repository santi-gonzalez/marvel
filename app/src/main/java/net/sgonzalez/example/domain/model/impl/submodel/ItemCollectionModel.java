package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.List;
import net.sgonzalez.example.data.entity.impl.subentity.ItemCollectionEntity;
import net.sgonzalez.example.data.mapper.SubMapper;

public class ItemCollectionModel {
  private final int available;
  private final String collectionURI;
  private final List<ItemModel> items;
  private final int returned;

  public ItemCollectionModel(@NonNull ItemCollectionEntity source) {
    this(source.getAvailable(), source.getCollectionURI(), SubMapper.toItemModel(source.getItems()), source.getReturned());
  }

  public ItemCollectionModel(int available, String collectionURI, List<ItemModel> items, int returned) {
    this.available = available;
    this.collectionURI = collectionURI;
    this.items = items != null ? Collections.unmodifiableList(items) : null;
    this.returned = returned;
  }

  public int getAvailable() {
    return available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public List<ItemModel> getItems() {
    return items;
  }

  public int getReturned() {
    return returned;
  }

  @Override public String toString() {
    return "ItemCollectionModel{" +
           "available=" + available +
           ", collectionURI='" + collectionURI + '\'' +
           ", items=" + items +
           ", returned=" + returned +
           '}';
  }
}
