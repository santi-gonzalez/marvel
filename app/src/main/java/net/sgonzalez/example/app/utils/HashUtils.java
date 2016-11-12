package net.sgonzalez.example.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtils {
  public static final String MD5_DIGEST = "MD5";

  private HashUtils() {
  }

  public static String toMd5(String s) {
    try {
      MessageDigest m = MessageDigest.getInstance(MD5_DIGEST);
      m.update(s.getBytes(), 0, s.length());
      return new BigInteger(1, m.digest()).toString(16);
    } catch(NoSuchAlgorithmException exception) {
      throw new RuntimeException("device can't create proper auth info to perform API requests", exception);
    }
  }
}
