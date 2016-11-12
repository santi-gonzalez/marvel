package net.sgonzalez.example.app.identifier;

/**
 * <h3>Development</h3> All siblings should implement a nice {@link #toString()} method.
 */
public interface Id<Type> {
  Type get();
}
