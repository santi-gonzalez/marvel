package net.sgonzalez.example.data.datasource.impl;

import android.support.annotation.NonNull;
import io.realm.Realm;
import javax.inject.Inject;
import net.sgonzalez.example.app.di.scope.ApplicationScope;
import net.sgonzalez.example.app.executor.RealmThreadExecutor;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.datasource.AbsRealmLocalDataSource;
import net.sgonzalez.example.data.entity.impl.FilterEntity;

@ApplicationScope public class FiltersRealmLocalDataSource extends AbsRealmLocalDataSource {
  @Inject
  public FiltersRealmLocalDataSource(Realm realm, RealmThreadExecutor realmThreadExecutor) {
    super(realm, realmThreadExecutor);
  }

  public void retrieveById(@NonNull final String id, @NonNull final RetrieveCallbacks<FilterEntity> callbacks) {
    executeOnRealmThread(new Runnable() {
      @Override
      public void run() {
        getRealm().executeTransaction(new Realm.Transaction() {
          @Override
          public void execute(Realm realm) {
            try {
              callbacks.onResult(realm.where(FilterEntity.class)
                                      .equalTo("id", id)
                                      .findFirst());
            } catch(Exception exception) {
              callbacks.onError(exception);
            }
          }
        });
      }
    });
  }

  public void store(@NonNull final FilterEntity entity, @NonNull final StoreCallbacks<FilterEntity> callbacks) {
    executeOnRealmThread(new Runnable() {
      @Override
      public void run() {
        getRealm().executeTransaction(new Realm.Transaction() {
          @Override
          public void execute(Realm realm) {
            try {
              callbacks.onStore(realm.copyToRealmOrUpdate(entity));
            } catch(Exception exception) {
              callbacks.onError(exception);
            }
          }
        });
      }
    });
  }
}
