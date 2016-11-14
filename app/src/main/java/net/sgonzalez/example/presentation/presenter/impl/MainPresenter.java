package net.sgonzalez.example.presentation.presenter.impl;

import android.support.annotation.Nullable;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.AndroidScope;
import net.sgonzalez.example.app.retrofit.response.PageResult;
import net.sgonzalez.example.domain.callbacks.Callbacks;
import net.sgonzalez.example.domain.callbacks.CallbacksAdapter;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.domain.usecase.impl.ClearCharacterComicsUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveCharactersUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveComicsByCharacterIdUseCase;
import net.sgonzalez.example.presentation.presenter.AbsPresenter;

@AndroidScope public class MainPresenter extends AbsPresenter<MainPresenter.Presentable> {
  private static final int INITIAL_PAGE = 0;
  private final RetrieveCharactersUseCase retrieveCharactersUseCase;
  private final RetrieveComicsByCharacterIdUseCase retrieveComicsByCharacterIdUseCase;
  private final ClearCharacterComicsUseCase clearCharacterComicsUseCase;
  @Nullable private CharacterModel currentCharacter;

  @Inject
  public MainPresenter(RetrieveCharactersUseCase retrieveCharactersUseCase, RetrieveComicsByCharacterIdUseCase retrieveComicsByCharacterIdUseCase,
                       ClearCharacterComicsUseCase clearCharacterComicsUseCase) {
    this.retrieveCharactersUseCase = retrieveCharactersUseCase;
    this.retrieveComicsByCharacterIdUseCase = retrieveComicsByCharacterIdUseCase;
    this.clearCharacterComicsUseCase = clearCharacterComicsUseCase;
  }

  public void onViewReady() {
    showMoreCharacters(INITIAL_PAGE);
  }

  public void onCharactersBottomReached(int currentPage) {
    showMoreCharacters(currentPage);
  }

  public void onCharacterClicked(CharacterModel character) {
    loadCharacterComicsWall(character);
  }

  public void onComicsBottomReached(int currentPage) {
    showMoreComics(currentPage);
  }

  private void loadCharacterComicsWall(final CharacterModel character) {
    if (currentCharacter != character) {
      clearComics(new CallbacksAdapter<Void>() {
        @Override
        public void onResult(Void result) {
          setCurrentCharacter(character);
          showMoreComics(INITIAL_PAGE);
        }
      });
    }
  }

  private void clearComics(Callbacks<Void> callbacks) {
    presentable().clearComicsWall();
    if (currentCharacter != null) {
      clearCharacterComicsUseCase.execute(currentCharacter.getId(), callbacks);
    } else {
      callbacks.onResult(null);
    }
  }

  private void showMoreCharacters(final int currentPage) {
    retrieveCharactersUseCase.execute(null, new Callbacks<List<CharacterModel>>() {
      @Override
      public void onExecute() {
        if (currentPage == INITIAL_PAGE) {
          // TODO: 12/11/2016 show loading in menu drawer
        }
      }

      @Override
      public void onResult(List<CharacterModel> models) {
        presentable().appendCharacters(models);
        // TODO: 12/11/2016 hide loading in menu drawer
      }

      @Override
      public void onError(Exception exception) {
        // TODO: 12/11/2016 hide loading in menu drawer
        // TODO: 12/11/2016 show error
      }
    });
  }

  private void setCurrentCharacter(@Nullable CharacterModel character) {
    currentCharacter = character;
  }

  private void showMoreComics(final int currentPage) {
    if (currentCharacter != null) {
      retrieveComicsByCharacterIdUseCase.execute(currentCharacter.getId(), new Callbacks<PageResult<List<ComicModel>>>() {
        @Override
        public void onExecute() {
          if (currentPage == INITIAL_PAGE) {
            // TODO: 13/11/2016 show loading in wall
          }
        }

        @Override
        public void onResult(PageResult<List<ComicModel>> pageResult) {
          presentable().appendComics(pageResult.result);
          // TODO: 13/11/2016 hide loading in wall
        }

        @Override
        public void onError(Exception exception) {
          // TODO: 13/11/2016 hide loading in wall
          // TODO: 13/11/2016 show error
        }
      });
    }
  }

  public interface Presentable {
    void appendCharacters(List<CharacterModel> characters);
    void appendComics(List<ComicModel> comics);
    void clearComicsWall();
  }
}
