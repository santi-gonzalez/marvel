package net.sgonzalez.example.data.mapper;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import net.sgonzalez.example.data.entity.impl.subentity.DateEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ImageEntity;
import net.sgonzalez.example.data.entity.impl.subentity.ItemEntity;
import net.sgonzalez.example.data.entity.impl.subentity.PriceEntity;
import net.sgonzalez.example.data.entity.impl.subentity.TextObjectEntity;
import net.sgonzalez.example.data.entity.impl.subentity.UrlEntity;
import net.sgonzalez.example.domain.model.impl.submodel.DateModel;
import net.sgonzalez.example.domain.model.impl.submodel.ImageModel;
import net.sgonzalez.example.domain.model.impl.submodel.ItemModel;
import net.sgonzalez.example.domain.model.impl.submodel.PriceModel;
import net.sgonzalez.example.domain.model.impl.submodel.TextObjectModel;
import net.sgonzalez.example.domain.model.impl.submodel.UrlModel;

// Note: I don't really like having this kind of class. Creating a new sub-type would mean this class should be extended. I'd
// like to make the process a bit more automatic.
public final class SubMapper {
  private SubMapper() {
  }

  public static List<TextObjectEntity> toTextObjectEntity(@NonNull List<TextObjectModel> source) {
    List<TextObjectEntity> result = new ArrayList<>(source.size());
    for (TextObjectModel item : source) {
      result.add(new TextObjectEntity(item));
    }
    return result;
  }

  public static List<TextObjectModel> toTextObjectModel(@NonNull List<TextObjectEntity> source) {
    List<TextObjectModel> result = new ArrayList<>(source.size());
    for (TextObjectEntity item : source) {
      result.add(new TextObjectModel(item));
    }
    return result;
  }

  public static List<UrlEntity> toUrlEntity(@NonNull List<UrlModel> source) {
    List<UrlEntity> result = new ArrayList<>(source.size());
    for (UrlModel item : source) {
      result.add(new UrlEntity(item));
    }
    return result;
  }

  public static List<UrlModel> toUrlModel(@NonNull List<UrlEntity> source) {
    List<UrlModel> result = new ArrayList<>(source.size());
    for (UrlEntity item : source) {
      result.add(new UrlModel(item));
    }
    return result;
  }

  public static List<ItemEntity> toItemEntity(@NonNull List<ItemModel> source) {
    List<ItemEntity> result = new ArrayList<>(source.size());
    for (ItemModel item : source) {
      result.add(new ItemEntity(item));
    }
    return result;
  }

  public static List<ItemModel> toItemModel(@NonNull List<ItemEntity> source) {
    List<ItemModel> result = new ArrayList<>(source.size());
    for (ItemEntity item : source) {
      result.add(new ItemModel(item));
    }
    return result;
  }

  public static List<DateEntity> toDateEntity(@NonNull List<DateModel> source) {
    List<DateEntity> result = new ArrayList<>(source.size());
    for (DateModel item : source) {
      result.add(new DateEntity(item));
    }
    return result;
  }

  public static List<DateModel> toDateModel(@NonNull List<DateEntity> source) {
    List<DateModel> result = new ArrayList<>(source.size());
    for (DateEntity item : source) {
      result.add(new DateModel(item));
    }
    return result;
  }

  public static List<PriceEntity> toPriceEntity(@NonNull List<PriceModel> source) {
    List<PriceEntity> result = new ArrayList<>(source.size());
    for (PriceModel item : source) {
      result.add(new PriceEntity(item));
    }
    return result;
  }

  public static List<PriceModel> toPriceModel(@NonNull List<PriceEntity> source) {
    List<PriceModel> result = new ArrayList<>(source.size());
    for (PriceEntity item : source) {
      result.add(new PriceModel(item));
    }
    return result;
  }

  public static List<ImageEntity> toImageEntity(@NonNull List<ImageModel> source) {
    List<ImageEntity> result = new ArrayList<>(source.size());
    for (ImageModel item : source) {
      result.add(new ImageEntity(item));
    }
    return result;
  }

  public static List<ImageModel> toImageModel(@NonNull List<ImageEntity> source) {
    List<ImageModel> result = new ArrayList<>(source.size());
    for (ImageEntity item : source) {
      result.add(new ImageModel(item));
    }
    return result;
  }
}
