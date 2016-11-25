package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import java.util.List;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.data.entity.impl.subentity.ImageEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ItemCollectionEntity;
import net.sgonzalez.example.data.entity.impl.subentity.UrlEntity;
import net.sgonzalez.example.data.mapper.SubMapper;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;

public class CharacterEntity
implements Entity<Long, CharacterModel> {
  private Long id;
  private String name;
  private String description;
  private String modified;
  private ImageEntity thumbnail;
  private String resourceURI;
  private ItemCollectionEntity comics;
  private ItemCollectionEntity series;
  private ItemCollectionEntity stories;
  private ItemCollectionEntity events;
  private List<UrlEntity> urls;

  // Mapper requirement
  public CharacterEntity(@NonNull CharacterModel source) {
    this(source.getId(), source.getName(), source.getDescription(), source.getModified(), new ImageEntity(source.getThumbnail()),
    source.getResourceURI(), new ItemCollectionEntity(source.getComics()), new ItemCollectionEntity(source.getSeries()),
    new ItemCollectionEntity(source.getStories()), new ItemCollectionEntity(source.getEvents()),
    SubMapper.toUrlEntity(source.getUrls()));
  }

  public CharacterEntity(Long id,
                         String name,
                         String description,
                         String modified,
                         ImageEntity thumbnail,
                         String resourceURI,
                         ItemCollectionEntity comics,
                         ItemCollectionEntity series,
                         ItemCollectionEntity stories,
                         ItemCollectionEntity events,
                         List<UrlEntity> urls) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.modified = modified;
    this.thumbnail = thumbnail;
    this.resourceURI = resourceURI;
    this.comics = comics;
    this.series = series;
    this.stories = stories;
    this.events = events;
    this.urls = urls;
  }

  @Override public Long getId() {
    return id;
  }

  @Override public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public ImageEntity getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(ImageEntity thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public ItemCollectionEntity getComics() {
    return comics;
  }

  public void setComics(ItemCollectionEntity comics) {
    this.comics = comics;
  }

  public ItemCollectionEntity getSeries() {
    return series;
  }

  public void setSeries(ItemCollectionEntity series) {
    this.series = series;
  }

  public ItemCollectionEntity getStories() {
    return stories;
  }

  public void setStories(ItemCollectionEntity stories) {
    this.stories = stories;
  }

  public ItemCollectionEntity getEvents() {
    return events;
  }

  public void setEvents(ItemCollectionEntity events) {
    this.events = events;
  }

  public List<UrlEntity> getUrls() {
    return urls;
  }

  public void setUrls(List<UrlEntity> urls) {
    this.urls = urls;
  }

  @NonNull @Override public CharacterModel toModel() {
    return CharacterModel.newBuilder()
                         .withId(getId())
                         .withName(getName())
                         .withDescription(getDescription())
                         .withModified(getModified())
                         .withThumbnail(new ImageModel(getThumbnail()))
                         .withResourceURI(getResourceURI())
                         .withComics(new ItemCollectionModel(getComics()))
                         .withSeries(new ItemCollectionModel(getSeries()))
                         .withStories(new ItemCollectionModel(getStories()))
                         .withEvents(new ItemCollectionModel(getEvents()))
                         .withUrls(SubMapper.toUrlModel(getUrls()))
                         .build();
  }

  @Override public String toString() {
    return "CharacterEntity{" +
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
}
