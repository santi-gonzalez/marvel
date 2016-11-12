package net.sgonzalez.example.app.dependency.component.provider;

import net.sgonzalez.example.data.datasource.impl.CharacterRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.ComicsRetrofitCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FiltersFakeCloudDataSource;
import net.sgonzalez.example.data.datasource.impl.FiltersRealmLocalDataSource;

public interface DataSourceProvider {
  FiltersRealmLocalDataSource getFiltersLocalDataSource();
  FiltersFakeCloudDataSource getFiltersCloudDataSource();
  ComicsRetrofitCloudDataSource getComicsRetrofitCloudDataSource();
  CharacterRetrofitCloudDataSource getCharacterRetrofitCloudDataSource();
}
