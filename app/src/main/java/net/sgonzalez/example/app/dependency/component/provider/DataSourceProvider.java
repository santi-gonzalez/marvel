package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.datasource.impl.CharacterMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterFakeCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FilterMemoryLocalDataSource;

public interface DataSourceProvider {
  FilterMemoryLocalDataSource getFilterMemoryLocalDataSource();
  FilterFakeCloudDataSource getFilterFakeCloudDataSource();
  ComicRetrofitCloudDataSource getComicRetrofitCloudDataSource();
  ComicMemoryLocalDataSource getComicMemoryLocalDataSource();
  CharacterMemoryLocalDataSource getCharacterMemoryLocalDataSource();
  CharacterRetrofitCloudDataSource getCharacterRetrofitCloudDataSource();
}
