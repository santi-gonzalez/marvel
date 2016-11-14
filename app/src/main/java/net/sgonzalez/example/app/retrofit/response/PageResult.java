package net.sgonzalez.example.app.retrofit.response;

public final class PageResult<Type> {
  public final Type result;
  public final boolean bottomReached;

  public PageResult(Type result, boolean bottomReached) {
    this.result = result;
    this.bottomReached = bottomReached;
  }
}
