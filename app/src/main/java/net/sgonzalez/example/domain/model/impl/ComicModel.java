package net.sgonzalez.example.domain.model.impl;

import android.support.annotation.NonNull;
import java.util.List;
import net.sgonzalez.example.domain.model.Model;
import net.sgonzalez.example.domain.model.impl.submodel.DateModel;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemCollectionModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemModel;
import net.sgonzalez.example.domain.model.impl.submodel.PriceModel;
import net.sgonzalez.example.domain.model.impl.submodel.TextObjectModel;
import net.sgonzalez.example.domain.model.impl.submodel.UrlModel;

public class ComicModel
implements Model<Long> {
  private final long id;
  private final long digitalId;
  private final String title;
  private final int issueNumber;
  private final String variantDescription;
  private final String description;
  private final String modified;
  private final String isbn;
  private final String upc;
  private final String diamondCode;
  private final String ean;
  private final String issn;
  private final String format;
  private final int pageCount;
  private final List<TextObjectModel> textObjects;
  private final String resourceURI;
  private final List<UrlModel> urls;
  private final ItemModel series;
  private final List<ItemModel> variants;
  //private final List<Collection> collections;
  //private final List<Issue> collectedIssues;
  private final List<DateModel> dates;
  private final List<PriceModel> prices;
  private final ImageModel thumbnail;
  private final List<ImageModel> images;
  private final ItemCollectionModel creators;
  private final ItemCollectionModel characters;
  private final ItemCollectionModel stories;
  private final ItemCollectionModel events;

  private ComicModel(Builder builder) {
    id = builder.id;
    digitalId = builder.digitalId;
    title = builder.title;
    issueNumber = builder.issueNumber;
    variantDescription = builder.variantDescription;
    description = builder.description;
    modified = builder.modified;
    isbn = builder.isbn;
    upc = builder.upc;
    diamondCode = builder.diamondCode;
    ean = builder.ean;
    issn = builder.issn;
    format = builder.format;
    pageCount = builder.pageCount;
    textObjects = builder.textObjects;
    resourceURI = builder.resourceURI;
    urls = builder.urls;
    series = builder.series;
    variants = builder.variants;
    dates = builder.dates;
    prices = builder.prices;
    thumbnail = builder.thumbnail;
    images = builder.images;
    creators = builder.creators;
    characters = builder.characters;
    stories = builder.stories;
    events = builder.events;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(@NonNull ComicModel copy) {
    return newBuilder().withId(copy.getId())
                       .withDigitalId(copy.getDigitalId())
                       .withTitle(copy.getTitle())
                       .withIssueNumber(copy.getIssueNumber())
                       .withVariantDescription(copy.getVariantDescription())
                       .withDescription(copy.getDescription())
                       .withModified(copy.getModified())
                       .withIsbn(copy.getIsbn())
                       .withUpc(copy.getUpc())
                       .withDiamondCode(copy.getDiamondCode())
                       .withEan(copy.getEan())
                       .withIssn(copy.getIssn())
                       .withFormat(copy.getFormat())
                       .withPageCount(copy.getPageCount())
                       .withTextObjects(copy.getTextObjects())
                       .withResourceURI(copy.getResourceURI())
                       .withUrls(copy.getUrls())
                       .withSeries(copy.getSeries())
                       .withVariants(copy.getVariants())
                       .withDates(copy.getDates())
                       .withPrices(copy.getPrices())
                       .withThumbnail(copy.getThumbnail())
                       .withImages(copy.getImages())
                       .withCreators(copy.getCreators())
                       .withCharacters(copy.getCharacters())
                       .withStories(copy.getStories())
                       .withEvents(copy.getEvents());
  }

  @Override public Long getId() {
    return id;
  }

  public long getDigitalId() {
    return digitalId;
  }

  public String getTitle() {
    return title;
  }

  public int getIssueNumber() {
    return issueNumber;
  }

  public String getVariantDescription() {
    return variantDescription;
  }

  public String getDescription() {
    return description;
  }

  public String getModified() {
    return modified;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getUpc() {
    return upc;
  }

  public String getDiamondCode() {
    return diamondCode;
  }

  public String getEan() {
    return ean;
  }

  public String getIssn() {
    return issn;
  }

  public String getFormat() {
    return format;
  }

  public int getPageCount() {
    return pageCount;
  }

  public List<TextObjectModel> getTextObjects() {
    return textObjects;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public List<UrlModel> getUrls() {
    return urls;
  }

  public ItemModel getSeries() {
    return series;
  }

  public List<ItemModel> getVariants() {
    return variants;
  }

  public List<DateModel> getDates() {
    return dates;
  }

  public List<PriceModel> getPrices() {
    return prices;
  }

  public ImageModel getThumbnail() {
    return thumbnail;
  }

  public List<ImageModel> getImages() {
    return images;
  }

  public ItemCollectionModel getCreators() {
    return creators;
  }

  public ItemCollectionModel getCharacters() {
    return characters;
  }

  public ItemCollectionModel getStories() {
    return stories;
  }

  public ItemCollectionModel getEvents() {
    return events;
  }

  @Override public String toString() {
    return "ComicModel{" +
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

  public static final class Builder {
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
    private List<TextObjectModel> textObjects;
    private String resourceURI;
    private List<UrlModel> urls;
    private ItemModel series;
    private List<ItemModel> variants;
    //private RealmList<Collection> collections;
    //private RealmList<Issue> collectedIssues;
    private List<DateModel> dates;
    private List<PriceModel> prices;
    private ImageModel thumbnail;
    private List<ImageModel> images;
    private ItemCollectionModel creators;
    private ItemCollectionModel characters;
    private ItemCollectionModel stories;
    private ItemCollectionModel events;

    private Builder() {
    }

    public Builder withId(long id) {
      this.id = id;
      return this;
    }

    public Builder withDigitalId(long digitalId) {
      this.digitalId = digitalId;
      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withIssueNumber(int issueNumber) {
      this.issueNumber = issueNumber;
      return this;
    }

    public Builder withVariantDescription(String variantDescription) {
      this.variantDescription = variantDescription;
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

    public Builder withIsbn(String isbn) {
      this.isbn = isbn;
      return this;
    }

    public Builder withUpc(String upc) {
      this.upc = upc;
      return this;
    }

    public Builder withDiamondCode(String diamondCode) {
      this.diamondCode = diamondCode;
      return this;
    }

    public Builder withEan(String ean) {
      this.ean = ean;
      return this;
    }

    public Builder withIssn(String issn) {
      this.issn = issn;
      return this;
    }

    public Builder withFormat(String format) {
      this.format = format;
      return this;
    }

    public Builder withPageCount(int pageCount) {
      this.pageCount = pageCount;
      return this;
    }

    public Builder withTextObjects(List<TextObjectModel> textObjects) {
      this.textObjects = textObjects;
      return this;
    }

    public Builder withResourceURI(String resourceURI) {
      this.resourceURI = resourceURI;
      return this;
    }

    public Builder withUrls(List<UrlModel> urls) {
      this.urls = urls;
      return this;
    }

    public Builder withSeries(ItemModel series) {
      this.series = series;
      return this;
    }

    public Builder withVariants(List<ItemModel> variants) {
      this.variants = variants;
      return this;
    }

    public Builder withDates(List<DateModel> dates) {
      this.dates = dates;
      return this;
    }

    public Builder withPrices(List<PriceModel> prices) {
      this.prices = prices;
      return this;
    }

    public Builder withThumbnail(ImageModel thumbnail) {
      this.thumbnail = thumbnail;
      return this;
    }

    public Builder withImages(List<ImageModel> images) {
      this.images = images;
      return this;
    }

    public Builder withCreators(ItemCollectionModel creators) {
      this.creators = creators;
      return this;
    }

    public Builder withCharacters(ItemCollectionModel characters) {
      this.characters = characters;
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

    public ComicModel build() {
      return new ComicModel(this);
    }
  }
}
