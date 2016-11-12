package net.sgonzalez.example.app.identifier.impl;

import net.sgonzalez.example.app.identifier.Id;

public class LongId implements Id<Long> {
  private final Long id;

  public LongId(Integer id) {
    this((long) id);
  }

  public LongId(Long id) {
    this.id = id;
  }

  @Override
  public Long get() {
    return id;
  }

  @Override
  public String toString() {
    return "LongId{" +
           "id=" + id +
           '}';
  }
}
