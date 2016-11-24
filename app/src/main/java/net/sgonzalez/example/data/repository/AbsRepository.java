package net.sgonzalez.example.data.repository;

import net.sgonzalez.example.data.cache.CachePolicy;
import net.sgonzalez.example.data.repository.log.Logger;

public abstract class AbsRepository
implements Repository {
  private final CachePolicy cachePolicy;

  public AbsRepository(CachePolicy cachePolicy) {
    this.cachePolicy = cachePolicy;
  }

  protected CachePolicy getCachePolicy() {
    return cachePolicy;
  }

  protected final void logVerbose(String message) {
    Logger.logVerbose(getClass().getSimpleName(), message);
  }

  protected final void logDebug(String message) {
    Logger.logDebug(getClass().getSimpleName(), message);
  }

  protected final void logError(Exception exception) {
    Logger.logException(getClass().getSimpleName(), "unexpected error", exception);
  }
}
