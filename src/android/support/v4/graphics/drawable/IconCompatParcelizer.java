package android.support.v4.graphics.drawable;

import androidx.versionedparcelable.h;

public final class IconCompatParcelizer
  extends androidx.core.graphics.drawable.IconCompatParcelizer
{
  public IconCompatParcelizer() {}
  
  public static IconCompat read(h paramH)
  {
    return androidx.core.graphics.drawable.IconCompatParcelizer.read(paramH);
  }
  
  public static void write(IconCompat paramIconCompat, h paramH)
  {
    androidx.core.graphics.drawable.IconCompatParcelizer.write(paramIconCompat, paramH);
  }
}
