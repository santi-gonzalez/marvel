package net.sgonzalez.example.domain.callbacks;

public abstract class CallbacksAdapter<Result> implements Callbacks<Result> {
  @Override
  public void onExecute() {
  }

  @Override
  public void onResult(Result result) {
  }

  @Override
  public void onError(Exception exception) {
  }
}
