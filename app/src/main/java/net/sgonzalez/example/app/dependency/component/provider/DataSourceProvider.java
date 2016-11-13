package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.datasource.impl.CharacterMemoryLocalDataSource;
import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicsRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FiltersFakeCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FiltersMemoryLocalDataSource;

public interface DataSourceProvider {
  FiltersMemoryLocalDataSource getFiltersMemoryLocalDataSource();
  FiltersFakeCloudDataSource getFiltersCloudDataSource();
  ComicsRetrofitCloudDataSource getComicsRetrofitCloudDataSource();
  CharacterMemoryLocalDataSource getCharacterMemoryLocalDataSource();
  CharacterRetrofitCloudDataSource getCharacterRetrofitCloudDataSource();
}
