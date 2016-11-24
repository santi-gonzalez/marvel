package net.sgonzalez.example.data.mapper.impl;

import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.data.mapper.AbsMapper;
import net.sgonzalez.example.domain.model.impl.FilterModel;

@ApplicationScope public class FilterMapper
extends AbsMapper<FilterModel, FilterEntity> {
  public static final FilterMapper INSTANCE = new FilterMapper();

  @Inject public FilterMapper() {
    super(FilterEntity.class);
  }
}
