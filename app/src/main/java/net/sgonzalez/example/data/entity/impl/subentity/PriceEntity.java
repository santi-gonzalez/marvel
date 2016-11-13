package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.PriceModel;

public class PriceEntity {
  private String type;
  private double price;

  // Realm requirement
  public PriceEntity() {
    this(null, 0D);
  }

  public PriceEntity(@NonNull PriceModel source) {
    this(source.toString(), source.getPrice());
  }

  public PriceEntity(String type, double price) {
    this.type = type;
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "PriceEntity{" +
           "type='" + type + '\'' +
           ", price=" + price +
           '}';
  }
}
