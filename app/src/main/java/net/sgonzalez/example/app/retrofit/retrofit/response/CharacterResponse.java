package net.sgonzalez.example.app.retrofit.retrofit.response;

import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.entity.impl.ComicEntity;

public class CharacterResponse extends AbsResponseEnvelope<CharacterEntity> {
  public CharacterResponse() {
    super(ComicEntity.class);
  }
}
