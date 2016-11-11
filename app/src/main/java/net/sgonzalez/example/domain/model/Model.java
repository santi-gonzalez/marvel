package net.sgonzalez.example.domain.model;

import net.sgonzalez.example.domain.model.id.Id;

public interface Model<IdType> {
  Id<IdType> getId();
}
