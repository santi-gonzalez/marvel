package net.sgonzalez.example.presentation.presenter.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.AndroidScope;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.domain.callbacks.Callbacks;
import net.sgonzalez.example.domain.callbacks.CallbacksAdapter;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.domain.usecase.impl.ClearComicsByCharacterIdUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveCharacterUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveNextCharactersUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveNextComicsByCharacterIdUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveStoredCharactersUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveStoredComicsByCharacterNameUseCase;
import net.sgonzalez.example.presentation.presenter.AbsPresenter;

@AndroidScope public class MainPresenter
extends AbsPresenter<MainPresenter.Presentable> {
  private static final String CURRENT_CHARACTER_ID = "CURRENT_CHARACTER_ID";
  private final RetrieveCharacterUseCase retrieveCharacterUseCase;
  private final RetrieveStoredCharactersUseCase retrieveStoredCharactersUseCase;
  private final RetrieveNextCharactersUseCase retrieveNextCharactersUseCase;
  private final RetrieveStoredComicsByCharacterNameUseCase retrieveStoredComicsByCharacterNameUseCase;
  private final RetrieveNextComicsByCharacterIdUseCase retrieveNextComicsByCharacterIdUseCase;
  private final ClearComicsByCharacterIdUseCase clearComicsByCharacterIdUseCase;
  @Nullable private CharacterModel currentCharacter;

  @Inject
  public MainPresenter(RetrieveCharacterUseCase retrieveCharacterUseCase,
                       RetrieveStoredCharactersUseCase retrieveStoredCharactersUseCase,
                       RetrieveNextCharactersUseCase retrieveNextCharactersUseCase,
                       RetrieveStoredComicsByCharacterNameUseCase retrieveStoredComicsByCharacterNameUseCase,
                       RetrieveNextComicsByCharacterIdUseCase retrieveNextComicsByCharacterIdUseCase,
                       ClearComicsByCharacterIdUseCase clearComicsByCharacterIdUseCase) {
    this.retrieveCharacterUseCase = retrieveCharacterUseCase;
    this.retrieveStoredCharactersUseCase = retrieveStoredCharactersUseCase;
    this.retrieveNextCharactersUseCase = retrieveNextCharactersUseCase;
    this.retrieveStoredComicsByCharacterNameUseCase = retrieveStoredComicsByCharacterNameUseCase;
    this.retrieveNextComicsByCharacterIdUseCase = retrieveNextComicsByCharacterIdUseCase;
    this.clearComicsByCharacterIdUseCase = clearComicsByCharacterIdUseCase;
  }

  @Override public void onSaveInstanceState(@NonNull Bundle state) {
    state.putLong(CURRENT_CHARACTER_ID, currentCharacter != null ? currentCharacter.getId() : 0L);
  }

  @Override public void onRestoreInstanceState(@Nullable Bundle state) {
    if (state != null) {
      final long currentCharacterId = state.getLong(CURRENT_CHARACTER_ID, 0L);
      loadCurrentCharacter(currentCharacterId, new CallbacksAdapter<CharacterModel>() {

        @Override public void onResult(CharacterModel result) {
          setCurrentCharacter(result);
          showStoredComics();
        }
      });
    }
  }

  public void onViewReady() {
    retrieveStoredCharacters(new CallbacksAdapter<List<CharacterModel>>() {

      @Override public void onResult(List<CharacterModel> result) {
        if (!result.isEmpty()) {
          presentable().appendCharacters(result);
        } else {
          showMoreCharacters();
        }
      }
    });
  }

  private void retrieveStoredCharacters(Callbacks<List<CharacterModel>> callbacks) {
    retrieveStoredCharactersUseCase.executeSync(null, callbacks);
  }

  public void onCharactersBottomReached(@SuppressWarnings("UnusedParameters") int currentPage) {
    showMoreCharacters();
  }

  public void onCharacterClicked(CharacterModel character) {
    loadCharacterComicsWall(character);
  }

  public void onComicsBottomReached(@SuppressWarnings("UnusedParameters") int currentPage) {
    showMoreComics();
  }

  private void loadCurrentCharacter(long characterId, Callbacks<CharacterModel> callbacks) {
    retrieveCharacterUseCase.executeSync(characterId, callbacks);
  }

  private void setCurrentCharacter(@Nullable CharacterModel character) {
    currentCharacter = character;
  }

  private void loadCharacterComicsWall(final CharacterModel character) {
    if (currentCharacter != character) {
      clearComics(new CallbacksAdapter<Void>() {

        @Override public void onResult(Void result) {
          setCurrentCharacter(character);
          showMoreComics();
        }
      });
    }
  }

  private void clearComics(Callbacks<Void> callbacks) {
    presentable().clearComicsWall();
    if (currentCharacter != null) {
      clearComicsByCharacterIdUseCase.execute(currentCharacter.getId(), callbacks);
    } else {
      callbacks.onResult(null);
    }
  }

  private void showMoreCharacters() {
    retrieveNextCharactersUseCase.execute(new CallbacksAdapter<List<CharacterModel>>() {

      @Override public void onResult(List<CharacterModel> result) {
        presentable().appendCharacters(result);
      }

      @Override public void onError(@NonNull Exception exception) {
        presentable().hideDrawerLoading(); // TODO: 14/11/2016 show retry button
        presentable().showError(exception.getMessage());
      }
    });
  }

  private void showMoreComics() {
    if (currentCharacter != null) {
      retrieveNextComicsByCharacterIdUseCase.execute(currentCharacter.getId(), new Callbacks<PageResult<List<ComicModel>>>() {

        @Override public void onExecute() {
          presentable().showWallLoading();
        }

        @Override public void onResult(PageResult<List<ComicModel>> pageResult) {
          presentable().appendComics(pageResult.result);
          if (pageResult.bottomReached) {
            presentable().hideWallLoading();
          }
        }

        @Override public void onError(@NonNull Exception exception) {
          presentable().hideWallLoading(); // TODO: 14/11/2016 show retry button
          presentable().showError(exception.getMessage());
        }
      });
    }
  }

  private void showStoredComics() {
    if (currentCharacter != null) {
      retrieveStoredComicsByCharacterNameUseCase.executeSync(currentCharacter.getName(),
      new CallbacksAdapter<List<ComicModel>>() {

        @Override public void onResult(List<ComicModel> result) {
          presentable().appendComics(result);
        }
      });
    }
  }

  public interface Presentable {
    void appendCharacters(List<CharacterModel> characters);

    void appendComics(List<ComicModel> comics);

    void clearComicsWall();

    void showWallLoading();

    void hideWallLoading();

    void hideDrawerLoading();

    void showError(String message);
  }
}
