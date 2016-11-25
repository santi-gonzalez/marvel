package net.sgonzalez.example.domain.usecase.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import javax.inject.Inject;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.entity.impl.FilterEntity;
import net.sgonzalez.example.data.repository.impl.FiltersRepository;
import net.sgonzalez.example.domain.model.impl.FilterModel;
import net.sgonzalez.example.domain.usecase.AbsUseCase;

public class GetFiltersUseCase
extends AbsUseCase<String, FilterModel> {
  private final FiltersRepository filtersRepository;

  @Inject
  public GetFiltersUseCase(MainThreadExecutor mainThreadExecutor,
                           NewThreadExecutor newThreadExecutor,
                           SameThreadExecutor sameThreadExecutor,
                           FiltersRepository filtersRepository) {
    super(mainThreadExecutor, newThreadExecutor, sameThreadExecutor);
    this.filtersRepository = filtersRepository;
  }

  @Override protected void onExecute(String id) {
    if (!TextUtils.isEmpty(id)) {
      filtersRepository.retrieveById(id, new Callbacks<FilterEntity>() {

        @Override public void onResult(FilterEntity entity) {
          dispatchResult(entity.toModel());
        }

        @Override public void onError(@NonNull Exception exception) {
          dispatchError(exception);
        }
      });
    }
  }
}
