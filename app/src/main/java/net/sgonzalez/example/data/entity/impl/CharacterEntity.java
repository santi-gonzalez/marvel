package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.ArrayList;
import java.util.List;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.domain.model.id.Id;
import net.sgonzalez.example.domain.model.id.impl.LongId;
import net.sgonzalez.example.domain.model.impl.CharacterModel;

public class CharacterEntity extends RealmObject implements Entity<Long, CharacterModel> {
  @PrimaryKey private Long id;
  private String name;
  private String description;
  private String modified;
  private Thumbnail thumbnail;
  private String resourceURI;
  private Club comics;
  private Club series;
  private Club stories;
  private Club events;
  private RealmList<Url> urls;

  // Realm requirement
  public CharacterEntity() {
    this(0L);
  }

  // Mapper requirement
  public CharacterEntity(@NonNull CharacterModel source) {
    this(source.getId()
               .get());
  }

  public CharacterEntity(Long id) {
    this.id = id;
  }

  @Override
  public Id<Long> getId() {
    return new LongId(id);
  }

  @Override
  public void setId(Id<Long> id) {
    this.id = id.get();
  }

  public void setId(Long id) {
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

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Thumbnail thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public Club getComics() {
    return comics;
  }

  public void setComics(Club comics) {
    this.comics = comics;
  }

  public Club getSeries() {
    return series;
  }

  public void setSeries(Club series) {
    this.series = series;
  }

  public Club getStories() {
    return stories;
  }

  public void setStories(Club stories) {
    this.stories = stories;
  }

  public Club getEvents() {
    return events;
  }

  public void setEvents(Club events) {
    this.events = events;
  }

  public RealmList<Url> getUrls() {
    return urls;
  }

  public void setUrls(RealmList<Url> urls) {
    this.urls = urls;
  }

  @NonNull
  @Override
  public CharacterModel toModel() {
    return CharacterModel.newBuilder(getId())
                         .withName(getName())
                         .withDescription(getDescription())
                         .withModified(getModified())
                         .withThumbnail(getThumbnail().getPath(), getThumbnail().getExtension())
                         .withResourceURI(getResourceURI())
                         .withComics(getComics().getAvailable(), getComics().getCollectionURI(),
                         toItemList(getComics().getItems()), getComics().getReturned())
                         .withSeries(getSeries().getAvailable(), getSeries().getCollectionURI(),
                         toItemList(getSeries().getItems()), getSeries().getReturned())
                         .withStories(getStories().getAvailable(), getStories().getCollectionURI(),
                         toItemList(getStories().getItems()), getStories().getReturned())
                         .withEvents(getEvents().getAvailable(), getEvents().getCollectionURI(),
                         toItemList(getEvents().getItems()), getEvents().getReturned())
                         .withUrls(toUrlList(getUrls()))
                         .build();
  }

  private List<net.sgonzalez.example.domain.model.impl.Item> toItemList(RealmList<Item> items) {
    if (items == null) {
      return null;
    }
    List<net.sgonzalez.example.domain.model.impl.Item> result = new ArrayList<>(items.size());
    for (Item item : items) {
      result.add(new net.sgonzalez.example.domain.model.impl.Item(item.getResourceURI(), item.getName()));
    }
    return result;
  }

  private List<net.sgonzalez.example.domain.model.impl.Url> toUrlList(RealmList<Url> urls) {
    if (urls == null) {
      return null;
    }
    List<net.sgonzalez.example.domain.model.impl.Url> result = new ArrayList<>(urls.size());
    for (Url url : urls) {
      result.add(new net.sgonzalez.example.domain.model.impl.Url(url.getType(), url.getUrl()));
    }
    return result;
  }

  @Override
  public String toString() {
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
