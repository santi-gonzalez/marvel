package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.callbacks.CallbacksAdapter;
import net.sgonzalez.example.data.callbacks.Matcher;
import net.sgonzalez.example.data.datasource.AbsIdMapDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;

@ApplicationScope public class ComicLocalDataSource
extends AbsIdMapDataSource<Long, ComicEntity> {
  private Map<Long, List<Long>> characterComicsMap;

  @Inject public ComicLocalDataSource() {
    characterComicsMap = new HashMap<>();
  }

  public void store(final long characterId,
                    final List<ComicEntity> entities,
                    @NonNull final Callbacks<List<ComicEntity>> callbacks) {
    store(entities, new Callbacks<List<ComicEntity>>() {
      @Override public void onDone(List<ComicEntity> entities) {
        assignComics(characterId, entities);
        callbacks.onDone(entities);
      }

      @Override public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    });
  }

  public void assignComics(long characterId, List<ComicEntity> entities) {
    createComicListIfNecessary(characterId, entities);
    for (ComicEntity entity : entities) {
      assignComic(characterId, entity);
    }
  }

  private void assignComic(long characterId, ComicEntity entity) {
    characterComicsMap.get(characterId).add(entity.getId());
  }

  @Override public void getAll(@NonNull final Callbacks<List<ComicEntity>> callbacks) {
    super.getAll(new CallbacksAdapter<List<ComicEntity>>(callbacks) {
      @Override public void onDone(List<ComicEntity> result) {
        super.onDone(sortByDigitalIdReversed(result));
      }
    });
  }

  @Override public void getAll(@Nullable Matcher<ComicEntity> matcher, @NonNull Callbacks<List<ComicEntity>> callbacks) {
    super.getAll(matcher, new CallbacksAdapter<List<ComicEntity>>(callbacks) {
      @Override public void onDone(List<ComicEntity> result) {
        super.onDone(sortByDigitalIdReversed(result));
      }
    });
  }

  public void count(long characterId, @NonNull final Callbacks<Integer> callbacks) {
    callbacks.onDone(characterComicsMap.containsKey(characterId) ? characterComicsMap.get(characterId).size() : 0);
  }

  public void clear(long characterId, Callbacks<Void> callbacks) {
    characterComicsMap.remove(characterId);
    callbacks.onDone(null);
  }

  private void createComicListIfNecessary(long characterId, List<ComicEntity> entities) {
    if (!characterComicsMap.containsKey(characterId)) {
      characterComicsMap.put(characterId, new ArrayList<Long>(entities.size()));
    }
  }

  private List<ComicEntity> sortByTitle(List<ComicEntity> set) {
    Collections.sort(set, new Comparator<ComicEntity>() {
      @Override public int compare(ComicEntity left, ComicEntity right) {
        return left.getTitle().compareTo(right.getTitle());
      }
    });
    return set;
  }

  private List<ComicEntity> sortByDigitalId(List<ComicEntity> set) {
    Collections.sort(set, new Comparator<ComicEntity>() {
      @Override public int compare(ComicEntity left, ComicEntity right) {
        return Long.valueOf(left.getDigitalId()).compareTo(right.getDigitalId());
      }
    });
    return set;
  }

  private List<ComicEntity> sortByDigitalIdReversed(List<ComicEntity> set) {
    sortByDigitalId(set);
    Collections.reverse(set);
    return set;
  }
}
