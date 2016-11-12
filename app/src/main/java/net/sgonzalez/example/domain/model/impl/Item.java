package net.sgonzalez.example.domain.model.impl;

public class Item {
  private final String resourceURI;
  private final String name;

  public Item(String resourceURI, String name) {
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
    return "Item{" +
           "resourceURI='" + resourceURI + '\'' +
           ", name='" + name + '\'' +
           '}';
  }
}
