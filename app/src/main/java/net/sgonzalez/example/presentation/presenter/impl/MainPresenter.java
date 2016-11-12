package net.sgonzalez.example.presentation.presenter.impl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.AndroidScope;
import net.sgonzalez.example.domain.callbacks.Callbacks;
import net.sgonzalez.example.domain.model.id.impl.LongId;
import net.sgonzalez.example.domain.model.impl.CharacterModel;
import net.sgonzalez.example.domain.model.impl.ComicModel;
import net.sgonzalez.example.domain.usecase.impl.GetFiltersUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveCharactersUseCase;
import net.sgonzalez.example.domain.usecase.impl.RetrieveComicsByCharacterId;
import net.sgonzalez.example.presentation.presenter.AbsPresenter;

@AndroidScope public class MainPresenter extends AbsPresenter<MainPresenter.Presentable> {
  public static final long CHARACTER_ID = 1011334;
  private final GetFiltersUseCase getFiltersUseCase;
  private final RetrieveComicsByCharacterId retrieveComicsByCharacterId;
  private final RetrieveCharactersUseCase retrieveCharactersUseCase;

  @Inject
  public MainPresenter(GetFiltersUseCase getFiltersUseCase, RetrieveComicsByCharacterId retrieveComicsByCharacterId,
                       RetrieveCharactersUseCase retrieveCharactersUseCase) {
    this.getFiltersUseCase = getFiltersUseCase;
    this.retrieveComicsByCharacterId = retrieveComicsByCharacterId;
    this.retrieveCharactersUseCase = retrieveCharactersUseCase;
  }

  public void init() {
    renderCharacters();
    appendFakeComics();
  }

  private void renderCharacters() {
    retrieveCharactersUseCase.execute(null, new Callbacks<List<CharacterModel>>() {
      @Override
      public void onExecute() {
        // TODO: 12/11/2016 show loading in menu drawer
      }

      @Override
      public void onResult(List<CharacterModel> models) {
        getPresentable().appendCharacters(models);
        // TODO: 12/11/2016 hide loading in menu drawer
      }

      @Override
      public void onError(Exception exception) {
        // TODO: 12/11/2016 hide loading in menu drawer
        // TODO: 12/11/2016 show error
      }
    });
  }

  private void appendFakeCharacters() {
    List<CharacterModel> characterList = new ArrayList<>();
    for (int index = 0; index < 50; index++) {
      characterList.add(CharacterModel.newBuilder(new LongId(index))
                                      .build());
    }
    getPresentable().appendCharacters(characterList);
  }

  private void appendFakeComics() {
    List<ComicModel> comicList = new ArrayList<>();
    for (int index = 0; index < 50; index++) {
      comicList.add(ComicModel.newBuilder(new LongId(index))
                              .build());
    }
    getPresentable().appendComics(comicList);
  }

  public boolean onToolbarActionRequestComics() {
    retrieveComicsByCharacterId();
    return true;
  }

  public boolean onToolbarActionRequestCharacters() {
    retrieveCharacters();
    return true;
  }

  private void retrieveComicsByCharacterId() {
    retrieveComicsByCharacterId.execute(CHARACTER_ID, new Callbacks<List<ComicModel>>() {
      @Override
      public void onExecute() {
      }

      @Override
      public void onResult(List<ComicModel> models) {
      }

      @Override
      public void onError(Exception exception) {
      }
    });
  }

  private void retrieveCharacters() {
    retrieveCharactersUseCase.execute(null, new Callbacks<List<CharacterModel>>() {
      @Override
      public void onExecute() {
      }

      @Override
      public void onResult(List<CharacterModel> models) {
      }

      @Override
      public void onError(Exception exception) {
      }
    });
  }

  public interface Presentable {
    void appendCharacters(List<CharacterModel> characters);
    void appendComics(List<ComicModel> comics);
  }
}
