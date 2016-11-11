package net.sgonzalez.example.data.cache;

import android.support.annotation.Nullable;

public interface CachePolicy {
  boolean isValid(@Nullable Object object);
}
