package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.repository.impl.CharacterRepository;
import net.sgonzalez.example.data.repository.impl.ComicRepository;
import net.sgonzalez.example.data.repository.impl.FiltersRepository;

public interface RepositoryProvider {
  FiltersRepository getFiltersRepository();

  ComicRepository getComicRepository();

  CharacterRepository getCharacterRepository();
}
