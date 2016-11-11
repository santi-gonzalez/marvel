package net.sgonzalez.example.app.di.component.provider;

import net.sgonzalez.example.data.repository.impl.CharacterRepository;
import net.sgonzalez.example.data.repository.impl.ComicRepository;
import net.sgonzalez.example.data.repository.impl.FiltersRepository;

public interface RepositoryProvider {
  FiltersRepository getFiltersRepository();
  ComicRepository getComicRepository();
  CharacterRepository getCharacterRepository();
}
