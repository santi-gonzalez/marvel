package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.mapper.impl.CharacterMapper;
import net.sgonzalez.example.data.mapper.impl.ComicMapper;
import net.sgonzalez.example.data.mapper.impl.FilterMapper;

public interface MapperProvider {
  FilterMapper getFilterMapper();
  ComicMapper getComicMapper();
  CharacterMapper getCharacterMapper();
}
