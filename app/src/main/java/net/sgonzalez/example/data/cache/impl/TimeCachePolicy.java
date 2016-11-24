package net.sgonzalez.example.data.cache.impl;

import android.support.annotation.Nullable;
import net.sgonzalez.example.data.cache.CachePolicy;

public class TimeCachePolicy
implements CachePolicy {
  public static final int FIVE_MINUTES_SECONDS = 60 * 5;
  private final int timeToLiveSeconds;

  public TimeCachePolicy() {
    this(FIVE_MINUTES_SECONDS);
  }

  public TimeCachePolicy(int seconds) {
    this.timeToLiveSeconds = seconds;
  }

  @Override public boolean isValid(@Nullable Object object) {
    if (object == null) {
      return false;
    }
    // TODO: 06/11/2016 implement
    long modifyTime = System.currentTimeMillis();
    long deltaLiveTimeSeconds = (System.currentTimeMillis() - modifyTime) / 1000;
    return deltaLiveTimeSeconds <= timeToLiveSeconds;
  }
}
