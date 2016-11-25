package net.sgonzalez.example.data.entity.impl;

import android.support.annotation.NonNull;
import java.util.List;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.data.entity.impl.subentity.DateEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ImageEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ItemCollectionEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ItemEntity;
import net.sgonzalez.example.data.entity.impl.subentity.PriceEntity;
import net.sgonzalez.example.data.entity.impl.subentity.TextObjectEntity;
import net.sgonzalez.example.data.entity.impl.subentity.UrlEntity;
import net.sgonzalez.example.data.mapper.SubMapper;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemModel;

public class ComicEntity
implements Entity<Long, ComicModel> {
  private long id;
  private long digitalId;
  private String title;
  private int issueNumber;
  private String variantDescription;
  private String description;
  private String modified;
  private String isbn;
  private String upc;
  private String diamondCode;
  private String ean;
  private String issn;
  private String format;
  private int pageCount;
  private List<TextObjectEntity> textObjects;
  private String resourceURI;
  private List<UrlEntity> urls;
  private ItemEntity series;
  private List<ItemEntity> variants;
  //private List<Collection> collections;
  //private List<Issue> collectedIssues;
  private List<DateEntity> dates;
  private List<PriceEntity> prices;
  private ImageEntity thumbnail;
  private List<ImageEntity> images;
  private ItemCollectionEntity creators;
  private ItemCollectionEntity characters;
  private ItemCollectionEntity stories;
  private ItemCollectionEntity events;

  // Mapper requirement
  public ComicEntity(@NonNull ComicModel source) {
    this(source.getId(), source.getDigitalId(), source.getTitle(), source.getIssueNumber(), source.getVariantDescription(),
    source.getDescription(), source.getModified(), source.getIsbn(), source.getUpc(), source.getDiamondCode(), source.getEan(),
    source.getIssn(), source.getFormat(), source.getPageCount(), SubMapper.toTextObjectEntity(source.getTextObjects()),
    source.getResourceURI(), SubMapper.toUrlEntity(source.getUrls()), new ItemEntity(source.getSeries()),
    SubMapper.toItemEntity(source.getVariants()), SubMapper.toDateEntity(source.getDates()),
    SubMapper.toPriceEntity(source.getPrices()), new ImageEntity(source.getThumbnail()),
    SubMapper.toImageEntity(source.getImages()), new ItemCollectionEntity(source.getCreators()),
    new ItemCollectionEntity(source.getCharacters()), new ItemCollectionEntity(source.getStories()),
    new ItemCollectionEntity(source.getEvents()));
  }

  public ComicEntity(long id,
                     long digitalId,
                     String title,
                     int issueNumber,
                     String variantDescription,
                     String description,
                     String modified,
                     String isbn,
                     String upc,
                     String diamondCode,
                     String ean,
                     String issn,
                     String format,
                     int pageCount,
                     List<TextObjectEntity> textObjects,
                     String resourceURI,
                     List<UrlEntity> urls,
                     ItemEntity series,
                     List<ItemEntity> variants,
                     List<DateEntity> dates,
                     List<PriceEntity> prices,
                     ImageEntity thumbnail,
                     List<ImageEntity> images,
                     ItemCollectionEntity creators,
                     ItemCollectionEntity characters,
                     ItemCollectionEntity stories,
                     ItemCollectionEntity events) {
    this.id = id;
    this.digitalId = digitalId;
    this.title = title;
    this.issueNumber = issueNumber;
    this.variantDescription = variantDescription;
    this.description = description;
    this.modified = modified;
    this.isbn = isbn;
    this.upc = upc;
    this.diamondCode = diamondCode;
    this.ean = ean;
    this.issn = issn;
    this.format = format;
    this.pageCount = pageCount;
    this.textObjects = textObjects;
    this.resourceURI = resourceURI;
    this.urls = urls;
    this.series = series;
    this.variants = variants;
    this.dates = dates;
    this.prices = prices;
    this.thumbnail = thumbnail;
    this.images = images;
    this.creators = creators;
    this.characters = characters;
    this.stories = stories;
    this.events = events;
  }

  @Override public Long getId() {
    return id;
  }

  @Override public void setId(Long id) {
    this.id = id;
  }

  public long getDigitalId() {
    return digitalId;
  }

  public void setDigitalId(long digitalId) {
    this.digitalId = digitalId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getIssueNumber() {
    return issueNumber;
  }

  public void setIssueNumber(int issueNumber) {
    this.issueNumber = issueNumber;
  }

  public String getVariantDescription() {
    return variantDescription;
  }

  public void setVariantDescription(String variantDescription) {
    this.variantDescription = variantDescription;
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

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getUpc() {
    return upc;
  }

  public void setUpc(String upc) {
    this.upc = upc;
  }

  public String getDiamondCode() {
    return diamondCode;
  }

  public void setDiamondCode(String diamondCode) {
    this.diamondCode = diamondCode;
  }

  public String getEan() {
    return ean;
  }

  public void setEan(String ean) {
    this.ean = ean;
  }

  public String getIssn() {
    return issn;
  }

  public void setIssn(String issn) {
    this.issn = issn;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public List<TextObjectEntity> getTextObjects() {
    return textObjects;
  }

  public void setTextObjects(List<TextObjectEntity> textObjects) {
    this.textObjects = textObjects;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public List<UrlEntity> getUrls() {
    return urls;
  }

  public void setUrls(List<UrlEntity> urls) {
    this.urls = urls;
  }

  public ItemEntity getSeries() {
    return series;
  }

  public void setSeries(ItemEntity series) {
    this.series = series;
  }

  public List<ItemEntity> getVariants() {
    return variants;
  }

  public void setVariants(List<ItemEntity> variants) {
    this.variants = variants;
  }

  public List<DateEntity> getDates() {
    return dates;
  }

  public void setDates(List<DateEntity> dates) {
    this.dates = dates;
  }

  public List<PriceEntity> getPrices() {
    return prices;
  }

  public void setPrices(List<PriceEntity> prices) {
    this.prices = prices;
  }

  public ImageEntity getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(ImageEntity thumbnail) {
    this.thumbnail = thumbnail;
  }

  public List<ImageEntity> getImages() {
    return images;
  }

  public void setImages(List<ImageEntity> images) {
    this.images = images;
  }

  public ItemCollectionEntity getCreators() {
    return creators;
  }

  public void setCreators(ItemCollectionEntity creators) {
    this.creators = creators;
  }

  public ItemCollectionEntity getCharacters() {
    return characters;
  }

  public void setCharacters(ItemCollectionEntity characters) {
    this.characters = characters;
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

  @NonNull @Override public ComicModel toModel() {
    return ComicModel.newBuilder()
                     .withId(getId())
                     .withDigitalId(getDigitalId())
                     .withTitle(getTitle())
                     .withIssueNumber(getIssueNumber())
                     .withVariantDescription(getVariantDescription())
                     .withDescription(getDescription())
                     .withModified(getModified())
                     .withIsbn(getIsbn())
                     .withUpc(getUpc())
                     .withDiamondCode(getDiamondCode())
                     .withEan(getEan())
                     .withIssn(getIssn())
                     .withFormat(getFormat())
                     .withPageCount(getPageCount())
                     .withTextObjects(SubMapper.toTextObjectModel(getTextObjects()))
                     .withResourceURI(getResourceURI())
                     .withUrls(SubMapper.toUrlModel(getUrls()))
                     .withSeries(new ItemModel(getSeries()))
                     .withVariants(SubMapper.toItemModel(getVariants()))
                     .withDates(SubMapper.toDateModel(getDates()))
                     .withPrices(SubMapper.toPriceModel(getPrices()))
                     .withThumbnail(new ImageModel(getThumbnail()))
                     .withImages(SubMapper.toImageModel(getImages()))
                     .withCreators(new ItemCollectionModel(getCreators()))
                     .withCharacters(new ItemCollectionModel(getCharacters()))
                     .withStories(new ItemCollectionModel(getStories()))
                     .withEvents(new ItemCollectionModel(getEvents()))
                     .build();
  }

  @Override public String toString() {
    return "ComicEntity{" +
           "id=" + id +
           ", digitalId=" + digitalId +
           ", title='" + title + '\'' +
           ", issueNumber=" + issueNumber +
           ", variantDescription='" + variantDescription + '\'' +
           ", description='" + description + '\'' +
           ", modified='" + modified + '\'' +
           ", isbn='" + isbn + '\'' +
           ", upc='" + upc + '\'' +
           ", diamondCode='" + diamondCode + '\'' +
           ", ean='" + ean + '\'' +
           ", issn='" + issn + '\'' +
           ", format='" + format + '\'' +
           ", pageCount=" + pageCount +
           ", textObjects=" + textObjects +
           ", resourceURI='" + resourceURI + '\'' +
           ", urls=" + urls +
           ", series=" + series +
           ", variants=" + variants +
           ", dates=" + dates +
           ", prices=" + prices +
           ", thumbnail=" + thumbnail +
           ", images=" + images +
           ", creators=" + creators +
           ", characters=" + characters +
           ", stories=" + stories +
           ", events=" + events +
           '}';
  }
}
