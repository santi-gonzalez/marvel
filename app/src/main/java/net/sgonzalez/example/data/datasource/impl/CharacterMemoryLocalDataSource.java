package net.sgonzalez.example.data.datasource.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.datasource.AbsMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;

@ApplicationScope public class CharacterMemoryLocalDataSource extends AbsMemoryLocalDataSource<Long, CharacterEntity> {
  @Inject
  public CharacterMemoryLocalDataSource() {
  }
}
