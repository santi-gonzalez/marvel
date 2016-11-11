package net.sgonzalez.example.data.datasource;

import io.realm.Realm;
import net.sgonzalez.example.app.executor.RealmThreadExecutor;

public abstract class AbsRealmLocalDataSource implements DataSource {
  private final Realm realm;
  private final RealmThreadExecutor realmThreadExecutor;

  public AbsRealmLocalDataSource(Realm realm, RealmThreadExecutor realmThreadExecutor) {
    this.realm = realm;
    this.realmThreadExecutor = realmThreadExecutor;
  }

  protected Realm getRealm() {
    return realm;
  }

  protected void executeOnRealmThread(Runnable runnable) {
    realmThreadExecutor.execute(runnable);
  }
}
