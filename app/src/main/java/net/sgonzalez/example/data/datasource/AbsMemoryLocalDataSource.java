package net.sgonzalez.example.data.datasource;

import java.util.HashMap;
import net.sgonzalez.example.data.entity.Entity;

public abstract class AbsMemoryLocalDataSource<IdType, E extends Entity<IdType, ?>> implements DataSource {
  private final HashMap<IdType, E> entityMap;

  public AbsMemoryLocalDataSource() {
    this.entityMap = new HashMap<>();
  }

  protected final HashMap<IdType, E> memory() {
    return entityMap;
  }
}
