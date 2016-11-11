package net.sgonzalez.example.domain.model.impl;

import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.domain.model.id.Id;

public class ComicModel implements Model<Long> {
  private final Id<Long> id;

  private ComicModel(Builder builder) {
    id = builder.id;
  }

  public static Builder newBuilder(Id<Long> id) {
    return new Builder(id);
  }

  public static Builder newBuilder(ComicModel copy) {
    return new Builder(copy.id);
  }

  public Id<Long> getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ComicModel{" +
           "id='" + id + '\'' +
           '}';
  }

  public static final class Builder {
    private Id<Long> id;

    private Builder(Id<Long> id) {
      withId(id);
    }

    public Builder withId(Id<Long> id) {
      this.id = id;
      return this;
    }

    public ComicModel build() {
      return new ComicModel(this);
    }
  }
}
