package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import io.realm.RealmList;
import io.realm.RealmObject;
import net.sgonzalez.example.data.utils.RealmUtils;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;

public class ItemCollectionEntity extends RealmObject {
  private int available;
  private String collectionURI;
  private RealmList<ItemEntity> items;
  private int returned;

  // Realm requirement
  public ItemCollectionEntity() {
    this(0, null, new RealmList<ItemEntity>(), 0);
  }

  public ItemCollectionEntity(@NonNull ItemCollectionModel source) {
    this(source.getAvailable(), source.getCollectionURI(), RealmUtils.toItemEntityList(source.getItems()), source.getReturned());
  }

  public ItemCollectionEntity(int available, String collectionURI, RealmList<ItemEntity> items, int returned) {
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

  public RealmList<ItemEntity> getItems() {
    return items;
  }

  public void setItems(RealmList<ItemEntity> items) {
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
