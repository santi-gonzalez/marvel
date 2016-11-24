package net.sgonzalez.example.data.mapper.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.mapper.AbsMapper;
import net.sgonzalez.example.domain.model.impl.CharacterModel;

@ApplicationScope public class CharacterMapper
extends AbsMapper<CharacterModel, CharacterEntity> {
  public static final CharacterMapper INSTANCE = new CharacterMapper();

  @Inject protected CharacterMapper() {
    super(CharacterEntity.class);
  }
}
