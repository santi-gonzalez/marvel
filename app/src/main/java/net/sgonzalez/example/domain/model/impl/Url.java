package net.sgonzalez.example.domain.model.impl;

public class Url {
  private final String type;
  private final String url;

  public Url(String type, String url) {
    this.type = type;
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "Url{" +
           "type='" + type + '\'' +
           ", url='" + url + '\'' +
           '}';
  }
}
