package net.sgonzalez.example.data.entity.impl;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Club extends RealmObject {
  private int available;
  private String collectionURI;
  private RealmList<Item> items;
  private int returned;

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

  public RealmList<Item> getItems() {
    return items;
  }

  public void setItems(RealmList<Item> items) {
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
    return "Club{" +
           "available=" + available +
           ", collectionURI='" + collectionURI + '\'' +
           ", items=" + items +
           ", returned=" + returned +
           '}';
  }
}
