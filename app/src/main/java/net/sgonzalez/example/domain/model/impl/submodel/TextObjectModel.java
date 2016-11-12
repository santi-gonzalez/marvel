package net.sgonzalez.example.domain.model.impl.submodel;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.entity.impl.subentity.TextObjectEntity;

public class TextObjectModel {
  private final String type;
  private final String language;
  private final String text;

  public TextObjectModel(@NonNull TextObjectEntity source) {
    this(source.getType(), source.getLanguage(), source.getText());
  }

  public TextObjectModel(String type, String language, String text) {
    this.type = type;
    this.language = language;
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public String getLanguage() {
    return language;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return "TextObjectModel{" +
           "type='" + type + '\'' +
           ", language='" + language + '\'' +
           ", text='" + text + '\'' +
           '}';
  }
}
