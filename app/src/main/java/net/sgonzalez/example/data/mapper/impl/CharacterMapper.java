package net.sgonzalez.example.data.mapper.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.mapper.AbsMapper;
import net.sgonzalez.example.domain.model.impl.CharacterModel;

@ApplicationScope public class CharacterMapper extends AbsMapper<CharacterModel, CharacterEntity> {
  @Inject
  protected CharacterMapper() {
    super(CharacterEntity.class);
  }
}
