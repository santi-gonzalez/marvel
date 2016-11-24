package net.sgonzalez.example.domain.usecase.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.repository.impl.ComicRepository;
import net.sgonzalez.example.domain.usecase.AbsUseCase;

public class ClearComicsByCharacterIdUseCase
extends AbsUseCase<Long, Void> {
  private final ComicRepository comicRepository;

  @Inject
  public ClearComicsByCharacterIdUseCase(MainThreadExecutor mainThreadExecutor,
                                         NewThreadExecutor newThreadExecutor,
                                         SameThreadExecutor sameThreadExecutor,
                                         ComicRepository comicRepository) {
    super(mainThreadExecutor, newThreadExecutor, sameThreadExecutor);
    this.comicRepository = comicRepository;
  }

  @Override protected void onExecute(Long characterId) {
    comicRepository.clearInLocalByCharacterId(characterId, new Callbacks<Void>() {
      @Override public void onDone(Void result) {
        dispatchResult(result);
      }

      @Override public void onError(@NonNull Exception exception) {
        dispatchError(exception);
      }
    });
  }
}
