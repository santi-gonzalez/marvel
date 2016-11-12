package net.sgonzalez.example.domain.model.impl;

import java.util.Collections;
import java.util.List;

public class Club {
  private final int available;
  private final String collectionURI;
  private final List<Item> items;
  private final int returned;

  public Club(int available, String collectionURI, List<Item> items, int returned) {
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

  public List<Item> getItems() {
    return items;
  }

  public int getReturned() {
    return returned;
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
