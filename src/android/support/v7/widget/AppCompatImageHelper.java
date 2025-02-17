package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.v4.widget.AppCompatBackgroundHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import org.org.v4.content.R.styleable;
import org.org.v4.gui.helpers.Resources;

public class AppCompatImageHelper
{
  private TintInfo mBackgroundTint;
  private TintInfo mInternalBackgroundTint;
  private TintInfo mTmpInfo;
  private final ImageView mView;
  
  public AppCompatImageHelper(ImageView paramImageView)
  {
    mView = paramImageView;
  }
  
  private boolean compatTintDrawableUsingFrameworkTint(Drawable paramDrawable)
  {
    if (mTmpInfo == null) {
      mTmpInfo = new TintInfo();
    }
    TintInfo localTintInfo = mTmpInfo;
    localTintInfo.clear();
    Object localObject = AppCompatBackgroundHelper.createView(mView);
    if (localObject != null)
    {
      mHasTintList = true;
      mTintList = ((ColorStateList)localObject);
    }
    localObject = AppCompatBackgroundHelper.a(mView);
    if (localObject != null)
    {
      mHasTintMode = true;
      mTintMode = ((PorterDuff.Mode)localObject);
    }
    if ((!mHasTintList) && (!mHasTintMode)) {
      return false;
    }
    AppCompatDrawableManager.tintDrawable(paramDrawable, localTintInfo, mView.getDrawableState());
    return true;
  }
  
  private boolean update()
  {
    int i = Build.VERSION.SDK_INT;
    if (i > 21) {
      return mInternalBackgroundTint != null;
    }
    return i == 21;
  }
  
  ColorStateList getSupportBackgroundTintList()
  {
    TintInfo localTintInfo = mBackgroundTint;
    if (localTintInfo != null) {
      return mTintList;
    }
    return null;
  }
  
  PorterDuff.Mode getSupportBackgroundTintMode()
  {
    TintInfo localTintInfo = mBackgroundTint;
    if (localTintInfo != null) {
      return mTintMode;
    }
    return null;
  }
  
  void loadFromAttributes(ColorStateList paramColorStateList)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    TintInfo localTintInfo = mBackgroundTint;
    mTintList = paramColorStateList;
    mHasTintList = true;
    setImageResource();
  }
  
  public void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.AppCompatImageView, paramInt, 0);
    try
    {
      Drawable localDrawable2 = mView.getDrawable();
      Drawable localDrawable1 = localDrawable2;
      paramAttributeSet = localDrawable1;
      if (localDrawable2 == null)
      {
        paramInt = localTintTypedArray.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
        paramAttributeSet = localDrawable1;
        if (paramInt != -1)
        {
          localDrawable2 = Resources.getDrawable(mView.getContext(), paramInt);
          localDrawable1 = localDrawable2;
          paramAttributeSet = localDrawable1;
          if (localDrawable2 != null)
          {
            mView.setImageDrawable(localDrawable2);
            paramAttributeSet = localDrawable1;
          }
        }
      }
      if (paramAttributeSet != null) {
        DrawableUtils.fixDrawable(paramAttributeSet);
      }
      boolean bool = localTintTypedArray.hasValue(R.styleable.AppCompatImageView_tint);
      if (bool) {
        AppCompatBackgroundHelper.loadFromAttributes(mView, localTintTypedArray.getColorStateList(R.styleable.AppCompatImageView_tint));
      }
      bool = localTintTypedArray.hasValue(R.styleable.AppCompatImageView_tintMode);
      if (bool) {
        AppCompatBackgroundHelper.loadFromAttributes(mView, DrawableUtils.parseTintMode(localTintTypedArray.getInt(R.styleable.AppCompatImageView_tintMode, -1), null));
      }
      localTintTypedArray.recycle();
      return;
    }
    catch (Throwable paramAttributeSet)
    {
      localTintTypedArray.recycle();
      throw paramAttributeSet;
    }
  }
  
  boolean loadFromAttributes()
  {
    Drawable localDrawable = mView.getBackground();
    return (Build.VERSION.SDK_INT < 21) || (!(localDrawable instanceof RippleDrawable));
  }
  
  void setImageResource()
  {
    Drawable localDrawable = mView.getDrawable();
    if (localDrawable != null) {
      DrawableUtils.fixDrawable(localDrawable);
    }
    if (localDrawable != null)
    {
      if ((update()) && (compatTintDrawableUsingFrameworkTint(localDrawable))) {
        return;
      }
      TintInfo localTintInfo = mBackgroundTint;
      if (localTintInfo != null)
      {
        AppCompatDrawableManager.tintDrawable(localDrawable, localTintInfo, mView.getDrawableState());
        return;
      }
      localTintInfo = mInternalBackgroundTint;
      if (localTintInfo != null) {
        AppCompatDrawableManager.tintDrawable(localDrawable, localTintInfo, mView.getDrawableState());
      }
    }
  }
  
  public void setImageResource(int paramInt)
  {
    if (paramInt != 0)
    {
      Drawable localDrawable = Resources.getDrawable(mView.getContext(), paramInt);
      if (localDrawable != null) {
        DrawableUtils.fixDrawable(localDrawable);
      }
      mView.setImageDrawable(localDrawable);
    }
    else
    {
      mView.setImageDrawable(null);
    }
    setImageResource();
  }
  
  void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    TintInfo localTintInfo = mBackgroundTint;
    mTintMode = paramMode;
    mHasTintMode = true;
    setImageResource();
  }
}
