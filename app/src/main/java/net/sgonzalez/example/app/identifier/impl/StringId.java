package net.sgonzalez.example.app.identifier.impl;

import net.sgonzalez.example.app.identifier.Id;

public class StringId implements Id<String> {
  private final String id;

  public StringId(String id) {
    this.id = id;
  }

  @Override
  public String get() {
    return id;
  }

  @Override
  public String toString() {
    return "StringId{" +
           "id='" + id + '\'' +
           '}';
  }
}
