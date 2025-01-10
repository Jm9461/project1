package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class HorizontalProgressDrawable
  extends BaseProgressLayerDrawable<SingleHorizontalProgressDrawable, HorizontalProgressBackgroundDrawable>
{
  public HorizontalProgressDrawable(Context paramContext)
  {
    super(new Drawable[] { new HorizontalProgressBackgroundDrawable(paramContext), new SingleHorizontalProgressDrawable(paramContext), new SingleHorizontalProgressDrawable(paramContext) }, paramContext);
  }
}
