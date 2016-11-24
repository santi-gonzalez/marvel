package net.sgonzalez.example.data.callbacks;

public interface Matcher<E> {
  boolean isValid(E entity);
}
