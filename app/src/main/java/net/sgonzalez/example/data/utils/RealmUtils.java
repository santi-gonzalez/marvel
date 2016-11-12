package net.sgonzalez.example.data.utils;

import io.realm.RealmList;
import java.util.ArrayList;
import java.util.Collections;
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

public final class RealmUtils {
  private RealmUtils() {
  }

  public static RealmList<ItemEntity> toItemEntityList(List<ItemModel> source) {
    RealmList<ItemEntity> result = new RealmList<>();
    for (ItemModel item : source) {
      result.add(new ItemEntity(item));
    }
    return result;
  }

  public static List<ItemModel> toItemModelList(RealmList<ItemEntity> source) {
    List<ItemModel> result = new ArrayList<>();
    for (ItemEntity item : source) {
      result.add(new ItemModel(item));
    }
    return Collections.unmodifiableList(result);
  }

  public static RealmList<UrlEntity> toUrlEntityList(List<UrlModel> source) {
    RealmList<UrlEntity> result = new RealmList<>();
    for (UrlModel item : source) {
      result.add(new UrlEntity(item));
    }
    return result;
  }

  public static List<UrlModel> toUrlModelList(RealmList<UrlEntity> source) {
    List<UrlModel> result = new ArrayList<>();
    for (UrlEntity item : source) {
      result.add(new UrlModel(item));
    }
    return Collections.unmodifiableList(result);
  }

  public static RealmList<TextObjectEntity> toTextObjectEntityList(List<TextObjectModel> source) {
    RealmList<TextObjectEntity> result = new RealmList<>();
    for (TextObjectModel item : source) {
      result.add(new TextObjectEntity(item));
    }
    return result;
  }

  public static List<TextObjectModel> toTextObjectModelList(RealmList<TextObjectEntity> source) {
    List<TextObjectModel> result = new ArrayList<>();
    for (TextObjectEntity item : source) {
      result.add(new TextObjectModel(item));
    }
    return Collections.unmodifiableList(result);
  }

  public static RealmList<DateEntity> toDateEntityList(List<DateModel> source) {
    RealmList<DateEntity> result = new RealmList<>();
    for (DateModel item : source) {
      result.add(new DateEntity(item));
    }
    return result;
  }

  public static List<DateModel> toDetaeModelList(RealmList<DateEntity> source) {
    List<DateModel> result = new ArrayList<>();
    for (DateEntity item : source) {
      result.add(new DateModel(item));
    }
    return Collections.unmodifiableList(result);
  }

  public static RealmList<PriceEntity> toPriceEntityList(List<PriceModel> source) {
    RealmList<PriceEntity> result = new RealmList<>();
    for (PriceModel item : source) {
      result.add(new PriceEntity(item));
    }
    return result;
  }

  public static List<PriceModel> toPriceModelList(RealmList<PriceEntity> source) {
    List<PriceModel> result = new ArrayList<>();
    for (PriceEntity item : source) {
      result.add(new PriceModel(item));
    }
    return Collections.unmodifiableList(result);
  }

  public static RealmList<ImageEntity> toImageEntityList(List<ImageModel> source) {
    RealmList<ImageEntity> result = new RealmList<>();
    for (ImageModel item : source) {
      result.add(new ImageEntity(item));
    }
    return result;
  }

  public static List<ImageModel> toImageModelList(RealmList<ImageEntity> source) {
    List<ImageModel> result = new ArrayList<>();
    for (ImageEntity item : source) {
      result.add(new ImageModel(item));
    }
    return Collections.unmodifiableList(result);
  }
}
