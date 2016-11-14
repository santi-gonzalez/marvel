package net.sgonzalez.example.app.retrofit.response;

import java.util.List;
import net.sgonzalez.example.data.entity.Entity;
import net.sgonzalez.example.data.entity.impl.ComicEntity;

public abstract class AbsResponseEnvelope<Type extends Entity> {
  private final Class<ComicEntity> typeClass;
  public int code;
  public String status;
  public String copyright;
  public String attributionText;
  public String attributionHTML;
  public String etag;
  public DataEnvelope<Type> data;

  public AbsResponseEnvelope(Class<ComicEntity> typeClass) {
    this.typeClass = typeClass;
  }

  public class DataEnvelope<Type> {
    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Type> results;
  }
}
