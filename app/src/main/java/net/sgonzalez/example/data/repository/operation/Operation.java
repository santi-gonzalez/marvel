package net.sgonzalez.example.data.repository.operation;

import android.support.annotation.NonNull;
import net.sgonzalez.example.data.callbacks.RetrieveCallbacks;
import net.sgonzalez.example.data.callbacks.StoreCallbacks;

public interface Operation extends Runnable {
  interface OperationCallbacks<Params, Result> {
    void retrieveFromLocal(Params params, @NonNull RetrieveCallbacks<Result> callbacks);
    void retrieveFromCloud(Params params, @NonNull RetrieveCallbacks<Result> callbacks);
    void storeInLocal(Result result, @NonNull StoreCallbacks<Result> callbacks);
  }
}
