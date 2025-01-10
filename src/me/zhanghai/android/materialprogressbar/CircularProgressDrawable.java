package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class CircularProgressDrawable
  extends BaseProgressLayerDrawable<SingleCircularProgressDrawable, CircularProgressBackgroundDrawable>
{
  public CircularProgressDrawable(int paramInt, Context paramContext)
  {
    super(new Drawable[] { new CircularProgressBackgroundDrawable(), new SingleCircularProgressDrawable(paramInt), new SingleCircularProgressDrawable(paramInt) }, paramContext);
  }
}
