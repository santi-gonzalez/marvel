package net.sgonzalez.example.domain.model.impl;

import java.util.Collections;
import java.util.List;
import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.domain.model.id.Id;

public class CharacterModel implements Model<Long> {
  private final Id<Long> id;
  private final String name;
  private final String description;
  private final String modified;
  private final Thumbnail thumbnail;
  private final String resourceURI;
  private final Club comics;
  private final Club series;
  private final Club stories;
  private final Club events;
  private final List<Url> urls;

  private CharacterModel(Builder builder) {
    id = builder.id;
    name = builder.name;
    description = builder.description;
    modified = builder.modified;
    thumbnail = builder.thumbnail;
    resourceURI = builder.resourceURI;
    comics = builder.comics;
    series = builder.series;
    stories = builder.stories;
    events = builder.events;
    urls = builder.urls != null ? Collections.unmodifiableList(builder.urls) : null;
  }

  public static Builder newBuilder(Id<Long> id) {
    return new Builder(id);
  }

  public static Builder newBuilder(CharacterModel copy) {
    return new Builder(copy.id);
  }

  public Id<Long> getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getModified() {
    return modified;
  }

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public Club getComics() {
    return comics;
  }

  public Club getSeries() {
    return series;
  }

  public Club getStories() {
    return stories;
  }

  public Club getEvents() {
    return events;
  }

  public List<Url> getUrls() {
    return urls;
  }

  @Override
  public String toString() {
    return "CharacterModel{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", description='" + description + '\'' +
           ", modified='" + modified + '\'' +
           ", thumbnail=" + thumbnail +
           ", resourceURI='" + resourceURI + '\'' +
           ", comics=" + comics +
           ", series=" + series +
           ", stories=" + stories +
           ", events=" + events +
           ", urls=" + urls +
           '}';
  }

  public static final class Builder {
    private Id<Long> id;
    private String name;
    private String description;
    private String modified;
    private Thumbnail thumbnail;
    private String resourceURI;
    private Club comics;
    private Club series;
    private Club stories;
    private Club events;
    private List<Url> urls;

    private Builder(Id<Long> id) {
      withId(id);
    }

    public Builder withId(Id<Long> id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withModified(String modified) {
      this.modified = modified;
      return this;
    }

    public Builder withThumbnail(String path, String extension) {
      this.thumbnail = new Thumbnail(path, extension);
      return this;
    }

    public Builder withResourceURI(String resourceURI) {
      this.resourceURI = resourceURI;
      return this;
    }

    public Builder withComics(int available, String collectionURI, List<Item> items, int returned) {
      this.comics = new Club(available, collectionURI, items, returned);
      return this;
    }

    public Builder withSeries(int available, String collectionURI, List<Item> items, int returned) {
      this.series = new Club(available, collectionURI, items, returned);
      return this;
    }

    public Builder withStories(int available, String collectionURI, List<Item> items, int returned) {
      this.stories = new Club(available, collectionURI, items, returned);
      return this;
    }

    public Builder withEvents(int available, String collectionURI, List<Item> items, int returned) {
      this.events = new Club(available, collectionURI, items, returned);
      return this;
    }

    public Builder withUrls(List<Url> urls) {
      this.urls = urls;
      return this;
    }

    public CharacterModel build() {
      return new CharacterModel(this);
    }
  }
}
