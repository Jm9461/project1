package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper
{
  private static final int[] TINT_ATTRS = { 16843067, 16843068 };
  private Bitmap mSampleTile;
  private final ProgressBar mView;
  
  AppCompatProgressBarHelper(ProgressBar paramProgressBar)
  {
    mView = paramProgressBar;
  }
  
  private Shape getDrawableShape()
  {
    return new RoundRectShape(new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F }, null, null);
  }
  
  private android.graphics.drawable.Drawable tileify(android.graphics.drawable.Drawable paramDrawable, boolean paramBoolean)
  {
    Object localObject1;
    if ((paramDrawable instanceof android.support.v4.graphics.drawable.Drawable))
    {
      localObject1 = ((android.support.v4.graphics.drawable.Drawable)paramDrawable).getWrappedDrawable();
      if (localObject1 != null)
      {
        localObject1 = tileify((android.graphics.drawable.Drawable)localObject1, paramBoolean);
        ((android.support.v4.graphics.drawable.Drawable)paramDrawable).setWrappedDrawable((android.graphics.drawable.Drawable)localObject1);
      }
      return paramDrawable;
    }
    Object localObject2;
    if ((paramDrawable instanceof LayerDrawable))
    {
      paramDrawable = (LayerDrawable)paramDrawable;
      int j = paramDrawable.getNumberOfLayers();
      localObject1 = new android.graphics.drawable.Drawable[j];
      int i = 0;
      while (i < j)
      {
        int k = paramDrawable.getId(i);
        localObject2 = paramDrawable.getDrawable(i);
        if ((k != 16908301) && (k != 16908303)) {
          paramBoolean = false;
        } else {
          paramBoolean = true;
        }
        localObject1[i] = tileify((android.graphics.drawable.Drawable)localObject2, paramBoolean);
        i += 1;
      }
      localObject1 = new LayerDrawable((android.graphics.drawable.Drawable[])localObject1);
      i = 0;
      while (i < j)
      {
        ((LayerDrawable)localObject1).setId(i, paramDrawable.getId(i));
        i += 1;
      }
      return localObject1;
    }
    if ((paramDrawable instanceof BitmapDrawable))
    {
      paramDrawable = (BitmapDrawable)paramDrawable;
      localObject2 = paramDrawable.getBitmap();
      if (mSampleTile == null) {
        mSampleTile = ((Bitmap)localObject2);
      }
      localObject1 = new ShapeDrawable(getDrawableShape());
      localObject2 = new BitmapShader((Bitmap)localObject2, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
      ((ShapeDrawable)localObject1).getPaint().setShader((Shader)localObject2);
      ((ShapeDrawable)localObject1).getPaint().setColorFilter(paramDrawable.getPaint().getColorFilter());
      if (paramBoolean) {
        return new ClipDrawable((android.graphics.drawable.Drawable)localObject1, 3, 1);
      }
      return localObject1;
    }
    return paramDrawable;
  }
  
  private android.graphics.drawable.Drawable tileifyIndeterminate(android.graphics.drawable.Drawable paramDrawable)
  {
    if ((paramDrawable instanceof AnimationDrawable))
    {
      paramDrawable = (AnimationDrawable)paramDrawable;
      int j = paramDrawable.getNumberOfFrames();
      AnimationDrawable localAnimationDrawable = new AnimationDrawable();
      localAnimationDrawable.setOneShot(paramDrawable.isOneShot());
      int i = 0;
      while (i < j)
      {
        android.graphics.drawable.Drawable localDrawable = tileify(paramDrawable.getFrame(i), true);
        localDrawable.setLevel(10000);
        localAnimationDrawable.addFrame(localDrawable, paramDrawable.getDuration(i));
        i += 1;
      }
      localAnimationDrawable.setLevel(10000);
      return localAnimationDrawable;
    }
    return paramDrawable;
  }
  
  Bitmap getSampleTime()
  {
    return mSampleTile;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    android.graphics.drawable.Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(0);
    if (localDrawable != null) {
      mView.setIndeterminateDrawable(tileifyIndeterminate(localDrawable));
    }
    localDrawable = paramAttributeSet.getDrawableIfKnown(1);
    if (localDrawable != null) {
      mView.setProgressDrawable(tileify(localDrawable, false));
    }
    paramAttributeSet.recycle();
  }
}
