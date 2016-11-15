package net.sgonzalez.example.domain.usecase.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.entity.impl.ComicEntity;
import net.sgonzalez.example.data.mapper.impl.ComicMapper;
import net.sgonzalez.example.data.repository.impl.ComicRepository;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.domain.usecase.AbsUseCase;

public class RetrieveNextComicsByCharacterIdUseCase extends AbsUseCase<Long, PageResult<List<ComicModel>>> {
  private final ComicRepository comicRepository;
  private final ComicMapper comicMapper;

  @Inject
  public RetrieveNextComicsByCharacterIdUseCase(MainThreadExecutor mainThreadExecutor, NewThreadExecutor newThreadExecutor,
                                                SameThreadExecutor sameThreadExecutor, ComicRepository comicRepository, ComicMapper comicMapper) {
    super(mainThreadExecutor, newThreadExecutor, sameThreadExecutor);
    this.comicRepository = comicRepository;
    this.comicMapper = comicMapper;
  }

  @Override
  protected void onExecute(Long characterId) {
    comicRepository.retrieveNextPageByCharacterId(characterId, new Callbacks<PageResult<List<ComicEntity>>>() {
      @Override
      public void onDone(PageResult<List<ComicEntity>> pageResult) {
        dispatchResult(comicMapper.toPRListModel(pageResult));
      }

      @Override
      public void onError(@NonNull Exception exception) {
        dispatchError(exception);
      }
    });
  }
}
