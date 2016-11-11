package net.sgonzalez.example.app.retrofit.retrofit.response;

import net.sgonzalez.example.data.entity.impl.ComicEntity;

public class ComicResponse extends AbsResponseEnvelope<ComicEntity> {
  public ComicResponse() {
    super(ComicEntity.class);
  }
}
