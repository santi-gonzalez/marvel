package net.sgonzalez.example.domain.model;

import net.sgonzalez.example.app.identifier.Id;

public interface Model<IdType> {
  Id<IdType> getId();
}
