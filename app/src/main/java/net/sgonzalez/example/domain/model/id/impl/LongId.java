package net.sgonzalez.example.domain.model.id.impl;

import net.sgonzalez.example.domain.model.id.Id;

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
