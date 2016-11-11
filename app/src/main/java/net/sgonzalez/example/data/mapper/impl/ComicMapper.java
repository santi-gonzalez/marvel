package net.sgonzalez.example.data.mapper.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.mapper.AbsMapper;
import net.sgonzalez.example.domain.model.impl.ComicModel;

@ApplicationScope public class ComicMapper extends AbsMapper<ComicModel, ComicEntity> {
  @Inject
  public ComicMapper() {
    super(ComicEntity.class);
  }
}
