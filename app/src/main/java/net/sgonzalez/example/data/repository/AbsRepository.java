package net.sgonzalez.example.data.repository;

import net.sgonzalez.example.data.cache.CachePolicy;
import net.sgonzalez.example.data.callbacks.Callbacks;
import net.sgonzalez.example.data.repository.log.Logger;

/**
 * All repositories must return {@link net.sgonzalez.example.data.entity.Entity} type instances. <h3>Development</h3> All methods
 * must present the final parameter as {@link Callbacks}, with any number and type of other parameters before.
 * <p>
 * All methods must create and run an {@link net.sgonzalez.example.data.repository.operation.Operation} instance, performing any
 * logic inside. <i>Operations</i> represent different work flows, such as "request from cloud and store", or "load from local".
 * They expose all the required actions in the form of callbacks. <h3>Logging</h3> Use convenient {@code log*} methods for easy
 * integration with master switches.
 */
public abstract class AbsRepository implements Repository {
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
