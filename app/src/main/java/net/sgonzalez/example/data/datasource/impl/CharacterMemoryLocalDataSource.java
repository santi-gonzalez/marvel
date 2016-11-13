package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import net.sgonzalez.example.app.dependency.scope.ApplicationScope;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.datasource.AbsMemoryLocalDataSource;
import net.sgonzalez.example.data.entity.impl.CharacterEntity;

@ApplicationScope public class CharacterMemoryLocalDataSource extends AbsMemoryLocalDataSource<Long, CharacterEntity> {
  @Inject
  public CharacterMemoryLocalDataSource() {
    super();
  }

  public void store(final List<CharacterEntity> entities, @NonNull final StoreCallbacks<List<CharacterEntity>> callbacks) {
    for (CharacterEntity entity : entities) {
      memory().put(entity.getId(), entity);
    }
    callbacks.onStore(entities);
  }

  public void count(@NonNull final RetrieveCallbacks<Integer> callbacks) {
    callbacks.onResult(memory().size());
  }
}
