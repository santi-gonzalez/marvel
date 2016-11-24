package net.sgonzalez.example.data.entity.impl.subentity;

import android.support.annotation.NonNull;
import net.sgonzalez.example.domain.model.impl.submodel.TextObjectModel;

public class TextObjectEntity {
  private String type;
  private String language;
  private String text;

  public TextObjectEntity(@NonNull TextObjectModel source) {
    this(source.getType(), source.getLanguage(), source.getText());
  }

  public TextObjectEntity(String type, String language, String text) {
    this.type = type;
    this.language = language;
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override public String toString() {
    return "TextObjectEntity{" +
           "type='" + type + '\'' +
           ", language='" + language + '\'' +
           ", text='" + text + '\'' +
           '}';
  }
}
