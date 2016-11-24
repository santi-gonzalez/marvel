package net.sgonzalez.example.domain.usecase.impl;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.sgonzalez.example.app.executor.MainThreadExecutor;
import net.sgonzalez.example.app.executor.NewThreadExecutor;
import net.sgonzalez.example.app.executor.SameThreadExecutor;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;
import net.sgonzalez.example.data.mapper.impl.CharacterMapper;
import net.sgonzalez.example.data.repository.impl.CharacterRepository;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.usecase.AbsUseCase;

public class RetrieveCharacterUseCase
extends AbsUseCase<Long, CharacterModel> {
  private final CharacterRepository characterRepository;
  private final CharacterMapper characterMapper;

  @Inject
  public RetrieveCharacterUseCase(MainThreadExecutor mainThreadExecutor,
                                  NewThreadExecutor newThreadExecutor,
                                  SameThreadExecutor sameThreadExecutor,
                                  CharacterRepository characterRepository,
                                  CharacterMapper characterMapper) {
    super(mainThreadExecutor, newThreadExecutor, sameThreadExecutor);
    this.characterRepository = characterRepository;
    this.characterMapper = characterMapper;
  }

  @Override protected void onExecute(Long characterId) {
    characterRepository.retrieveById(characterId, new Callbacks<CharacterEntity>() {
      @Override public void onDone(CharacterEntity entity) {
        dispatchResult(characterMapper.toModel(entity));
      }

      @Override public void onError(@NonNull Exception exception) {
        dispatchError(exception);
      }
    });
  }
}
