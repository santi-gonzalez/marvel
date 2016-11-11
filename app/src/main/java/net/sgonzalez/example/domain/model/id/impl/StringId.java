package net.sgonzalez.example.domain.model.id.impl;

import net.sgonzalez.example.domain.model.id.Id;

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
