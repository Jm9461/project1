package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import org.org.v4.content.R.styleable;
import org.org.v4.gui.helpers.Resources;

class AppCompatCompoundButtonHelper
{
  private ColorStateList mButtonTintList = null;
  private PorterDuff.Mode mButtonTintMode = null;
  private boolean mHasButtonTint = false;
  private boolean mHasButtonTintMode = false;
  private boolean mSkipNextApply;
  private final CompoundButton mView;
  
  AppCompatCompoundButtonHelper(CompoundButton paramCompoundButton)
  {
    mView = paramCompoundButton;
  }
  
  void applyButtonTint()
  {
    Drawable localDrawable = CompoundButtonCompat.getButtonDrawable(mView);
    if ((localDrawable != null) && ((mHasButtonTint) || (mHasButtonTintMode)))
    {
      localDrawable = DrawableCompat.wrap(localDrawable).mutate();
      if (mHasButtonTint) {
        DrawableCompat.setTintList(localDrawable, mButtonTintList);
      }
      if (mHasButtonTintMode) {
        DrawableCompat.setTintMode(localDrawable, mButtonTintMode);
      }
      if (localDrawable.isStateful()) {
        localDrawable.setState(mView.getDrawableState());
      }
      mView.setButtonDrawable(localDrawable);
    }
  }
  
  int getCompoundPaddingLeft(int paramInt)
  {
    int i = paramInt;
    if (Build.VERSION.SDK_INT < 17)
    {
      Drawable localDrawable = CompoundButtonCompat.getButtonDrawable(mView);
      i = paramInt;
      if (localDrawable != null) {
        i = paramInt + localDrawable.getIntrinsicWidth();
      }
    }
    return i;
  }
  
  ColorStateList getSupportButtonTintList()
  {
    return mButtonTintList;
  }
  
  PorterDuff.Mode getSupportButtonTintMode()
  {
    return mButtonTintMode;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = mView.getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.CompoundButton, paramInt, 0);
    try
    {
      boolean bool = paramAttributeSet.hasValue(R.styleable.CompoundButton_android_button);
      if (bool)
      {
        paramInt = paramAttributeSet.getResourceId(R.styleable.CompoundButton_android_button, 0);
        if (paramInt != 0) {
          mView.setButtonDrawable(Resources.getDrawable(mView.getContext(), paramInt));
        }
      }
      bool = paramAttributeSet.hasValue(R.styleable.CompoundButton_buttonTint);
      if (bool) {
        CompoundButtonCompat.setButtonTintList(mView, paramAttributeSet.getColorStateList(R.styleable.CompoundButton_buttonTint));
      }
      bool = paramAttributeSet.hasValue(R.styleable.CompoundButton_buttonTintMode);
      if (bool) {
        CompoundButtonCompat.setButtonTintMode(mView, DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.CompoundButton_buttonTintMode, -1), null));
      }
      paramAttributeSet.recycle();
      return;
    }
    catch (Throwable localThrowable)
    {
      paramAttributeSet.recycle();
      throw localThrowable;
    }
  }
  
  void onSetButtonDrawable()
  {
    if (mSkipNextApply)
    {
      mSkipNextApply = false;
      return;
    }
    mSkipNextApply = true;
    applyButtonTint();
  }
  
  void setSupportButtonTintList(ColorStateList paramColorStateList)
  {
    mButtonTintList = paramColorStateList;
    mHasButtonTint = true;
    applyButtonTint();
  }
  
  void setSupportButtonTintMode(PorterDuff.Mode paramMode)
  {
    mButtonTintMode = paramMode;
    mHasButtonTintMode = true;
    applyButtonTint();
  }
}
