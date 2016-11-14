package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.datasource.AbsMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.ComicEntity;

@ApplicationScope public class ComicMemoryLocalDataSource extends AbsMemoryLocalDataSource<Long, ComicEntity> {
  private Map<Long, List<Long>> characterComicsMap;

  @Inject
  public ComicMemoryLocalDataSource() {
    characterComicsMap = new HashMap<>();
  }

  public void append(final long characterId, final List<ComicEntity> entities, @NonNull final Callbacks<List<ComicEntity>> callbacks) {
    store(entities, new Callbacks<List<ComicEntity>>() {
      @Override
      public void onDone(List<ComicEntity> entities) {
        if (!characterComicsMap.containsKey(characterId)) {
          characterComicsMap.put(characterId, new ArrayList<Long>(entities.size()));
        }
        for (ComicEntity entity : entities) {
          characterComicsMap.get(characterId)
                            .add(entity.getId());
        }
        callbacks.onDone(entities);
      }

      @Override
      public void onError(@NonNull Exception exception) {
        callbacks.onError(exception);
      }
    });
  }

  public void count(long characterId, @NonNull final Callbacks<Integer> callbacks) {
    callbacks.onDone(characterComicsMap.containsKey(characterId) ? characterComicsMap.get(characterId)
                                                                                     .size() : 0);
  }

  public void clear(long characterId, Callbacks<Void> callbacks) {
    characterComicsMap.remove(characterId);
    callbacks.onDone(null);
  }
}
