package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.PriceEntity;

public class PriceModel {
  private String type;
  private double price;

  public PriceModel(@NonNull PriceEntity source) {
    this(source.getType(), source.getPrice());
  }

  public PriceModel(String type, double price) {
    this.type = type;
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "PriceModel{" +
           "type='" + type + '\'' +
           ", price=" + price +
           '}';
  }
}
