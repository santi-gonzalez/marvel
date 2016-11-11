package net.sgonzalez.example.data.repository.operation.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import net.sgonzalez.example.data.cache.CachePolicy;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;
import net.sgonzalez.example.data.repository.operation.Operation;

public class LocalOrCloudOperation<Params, Result> implements Operation {
  @Nullable private final Params params;
  @NonNull private final RetrieveCallbacks<Result> callbacks;
  @NonNull private final Operation.OperationCallbacks<Params, Result> operationCallbacks;
  @Nullable private final CachePolicy cachePolicy;

  public LocalOrCloudOperation(@Nullable Params params, @Nullable CachePolicy cachePolicy,
                               @NonNull RetrieveCallbacks<Result> callbacks,
                               @NonNull Operation.OperationCallbacks<Params, Result> operationCallbacks) {
    this.params = params;
    this.cachePolicy = cachePolicy;
    this.callbacks = callbacks;
    this.operationCallbacks = operationCallbacks;
  }

  @Override
  public final void run() {
    operationCallbacks.retrieveFromLocal(params, new RetrieveCallbacks<Result>() {
      @Override
      public void onResult(Result localResult) {
        if (cachePolicy != null && !cachePolicy.isValid(localResult)) {
          retrieveFromCloud();
        } else {
          callbacks.onResult(localResult);
        }
      }

      @Override
      public void onError(@NonNull Exception exception) {
        retrieveFromCloud();
      }

      private void retrieveFromCloud() {
        operationCallbacks.retrieveFromCloud(params, new RetrieveCallbacks<Result>() {
          @Override
          public void onResult(final Result cloudResult) {
            operationCallbacks.storeInLocal(cloudResult, new StoreCallbacks<Result>() {
              @Override
              public void onStore(Result localResult) {
                callbacks.onResult(localResult);
              }

              @Override
              public void onError(@NonNull Exception exception) {
                callbacks.onError(exception);
                callbacks.onResult(cloudResult);
              }
            });
          }

          @Override
          public void onError(@NonNull Exception exception) {
            callbacks.onError(exception);
          }
        });
      }
    });
  }

  public static abstract class OperationCallbacks<Params, Result> implements Operation.OperationCallbacks<Params, Result> { }
}
