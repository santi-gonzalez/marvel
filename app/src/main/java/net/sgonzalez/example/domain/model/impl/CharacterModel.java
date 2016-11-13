package net.sgonzalez.example.domain.model.impl;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.List;
import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;
import net.sgonzalez.example.domain.model.impl.submodel.UrlModel;

public class CharacterModel implements Model<Long> {
  private final long id;
  private final String name;
  private final String description;
  private final String modified;
  private final ImageModel thumbnail;
  private final String resourceURI;
  private final ItemCollectionModel comics;
  private final ItemCollectionModel series;
  private final ItemCollectionModel stories;
  private final ItemCollectionModel events;
  private final List<UrlModel> urls;

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

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(@NonNull CharacterModel copy) {
    return newBuilder().withId(copy.getId())
                       .withName(copy.getName())
                       .withDescription(copy.getDescription())
                       .withModified(copy.getModified())
                       .withThumbnail(copy.getThumbnail())
                       .withResourceURI(copy.getResourceURI())
                       .withComics(copy.getComics())
                       .withSeries(copy.getSeries())
                       .withStories(copy.getStories())
                       .withEvents(copy.getEvents())
                       .withUrls(copy.getUrls());
  }

  @Override
  public Long getId() {
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

  public ImageModel getThumbnail() {
    return thumbnail;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public ItemCollectionModel getComics() {
    return comics;
  }

  public ItemCollectionModel getSeries() {
    return series;
  }

  public ItemCollectionModel getStories() {
    return stories;
  }

  public ItemCollectionModel getEvents() {
    return events;
  }

  public List<UrlModel> getUrls() {
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
    private long id;
    private String name;
    private String description;
    private String modified;
    private ImageModel thumbnail;
    private String resourceURI;
    private ItemCollectionModel comics;
    private ItemCollectionModel series;
    private ItemCollectionModel stories;
    private ItemCollectionModel events;
    private List<UrlModel> urls;

    private Builder() {
    }

    public Builder withId(long id) {
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

    public Builder withThumbnail(ImageModel thumbnail) {
      this.thumbnail = thumbnail;
      return this;
    }

    public Builder withResourceURI(String resourceURI) {
      this.resourceURI = resourceURI;
      return this;
    }

    public Builder withComics(ItemCollectionModel comics) {
      this.comics = comics;
      return this;
    }

    public Builder withSeries(ItemCollectionModel series) {
      this.series = series;
      return this;
    }

    public Builder withStories(ItemCollectionModel stories) {
      this.stories = stories;
      return this;
    }

    public Builder withEvents(ItemCollectionModel events) {
      this.events = events;
      return this;
    }

    public Builder withUrls(List<UrlModel> urls) {
      this.urls = urls;
      return this;
    }

    public CharacterModel build() {
      return new CharacterModel(this);
    }
  }
}
