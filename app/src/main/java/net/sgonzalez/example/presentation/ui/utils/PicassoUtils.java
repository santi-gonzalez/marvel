package net.sgonzalez.example.presentation.ui.utils;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public final class PicassoUtils {
  private PicassoUtils() {
  }

  public static void placeImage(Context context, String url, ImageView target) {
    Picasso.with(context).load(url).into(target);
  }
}
