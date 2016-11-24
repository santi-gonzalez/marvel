package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.datasource.impl.CharacterCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterLocalDataSource;

public interface DataSourceProvider {
  FilterLocalDataSource getFilterMemoryLocalDataSource();

  FilterCloudDataSource getFilterFakeCloudDataSource();

  ComicCloudDataSource getComicRetrofitCloudDataSource();

  ComicLocalDataSource getComicMemoryLocalDataSource();

  CharacterLocalDataSource getCharacterMemoryLocalDataSource();

  CharacterCloudDataSource getCharacterRetrofitCloudDataSource();
}
