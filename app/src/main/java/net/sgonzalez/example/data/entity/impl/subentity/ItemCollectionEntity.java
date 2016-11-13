package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import java.util.List;
import net.sgonzalez.example.data.mapper.SubMapper;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;

public class ItemCollectionEntity {
  private int available;
  private String collectionURI;
  private List<ItemEntity> items;
  private int returned;

  public ItemCollectionEntity(@NonNull ItemCollectionModel source) {
    this(source.getAvailable(), source.getCollectionURI(), SubMapper.toItemEntity(source.getItems()), source.getReturned());
  }

  public ItemCollectionEntity(int available, String collectionURI, List<ItemEntity> items, int returned) {
    this.available = available;
    this.collectionURI = collectionURI;
    this.items = items;
    this.returned = returned;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public String getCollectionURI() {
    return collectionURI;
  }

  public void setCollectionURI(String collectionURI) {
    this.collectionURI = collectionURI;
  }

  public List<ItemEntity> getItems() {
    return items;
  }

  public void setItems(List<ItemEntity> items) {
    this.items = items;
  }

  public int getReturned() {
    return returned;
  }

  public void setReturned(int returned) {
    this.returned = returned;
  }

  @Override
  public String toString() {
    return "ItemCollectionEntity{" +
           "available=" + available +
           ", collectionURI='" + collectionURI + '\'' +
           ", items=" + items +
           ", returned=" + returned +
           '}';
  }
}
