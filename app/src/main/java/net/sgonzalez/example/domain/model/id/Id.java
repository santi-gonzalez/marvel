package net.sgonzalez.example.domain.model.id;

/**
 * <h3>Development</h3> All siblings should implement a nice {@link #toString()} method.
 */
public interface Id<Type> {
  Type get();
}
