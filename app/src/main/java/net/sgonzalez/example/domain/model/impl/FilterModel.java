package net.sgonzalez.example.domain.model.impl;

import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.app.identifier.Id;

public class FilterModel implements Model<String> {
  private final Id<String> id;
  private final String key;
  private final String value;

  private FilterModel(Builder builder) {
    id = builder.id;
    key = builder.key;
    value = builder.value;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(FilterModel copy) {
    return newBuilder().withId(copy.id)
                       .withKey(copy.getKey())
                       .withValue(copy.getValue());
  }

  @Override
  public Id<String> getId() {
    return id;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "FilterModel{" +
           "id='" + id + '\'' +
           ", key='" + key + '\'' +
           ", value='" + value + '\'' +
           '}';
  }

  public static final class Builder {
    private Id<String> id;
    private String key;
    private String value;

    private Builder() {
      withId(id);
    }

    public Builder withId(Id<String> id) {
      this.id = id;
      return this;
    }

    public Builder withKey(String key) {
      this.key = key;
      return this;
    }

    public Builder withValue(String value) {
      this.value = value;
      return this;
    }

    public FilterModel build() {
      return new FilterModel(this);
    }
  }
}
