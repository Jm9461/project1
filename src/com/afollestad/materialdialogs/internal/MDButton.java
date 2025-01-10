package com.afollestad.materialdialogs.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.R.dimen;
import apps.afollestad.materialdialogs.base.DialogUtils;

@SuppressLint({"AppCompatCustomView"})
public class MDButton
  extends TextView
{
  private Drawable mDefaultBackground;
  private boolean mStacked = false;
  private Drawable mStackedBackground;
  private int mStackedEndPadding;
  private GravityEnum mStackedGravity;
  
  public MDButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  public MDButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }
  
  private void init(Context paramContext)
  {
    mStackedEndPadding = paramContext.getResources().getDimensionPixelSize(R.dimen.md_dialog_frame_margin);
    mStackedGravity = GravityEnum.END;
  }
  
  public void setAllCapsCompat(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      setAllCaps(paramBoolean);
      return;
    }
    if (paramBoolean)
    {
      setTransformationMethod(new AllCapsTransformationMethod(getContext()));
      return;
    }
    setTransformationMethod(null);
  }
  
  public void setDefaultSelector(Drawable paramDrawable)
  {
    mDefaultBackground = paramDrawable;
    if (!mStacked) {
      setStacked(false, true);
    }
  }
  
  void setStacked(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((mStacked != paramBoolean1) || (paramBoolean2))
    {
      int i;
      if (paramBoolean1) {
        i = mStackedGravity.getGravityInt() | 0x10;
      } else {
        i = 17;
      }
      setGravity(i);
      if (Build.VERSION.SDK_INT >= 17)
      {
        if (paramBoolean1) {
          i = mStackedGravity.getTextAlignment();
        } else {
          i = 4;
        }
        setTextAlignment(i);
      }
      Drawable localDrawable;
      if (paramBoolean1) {
        localDrawable = mStackedBackground;
      } else {
        localDrawable = mDefaultBackground;
      }
      DialogUtils.setBackgroundCompat(this, localDrawable);
      if (paramBoolean1) {
        setPadding(mStackedEndPadding, getPaddingTop(), mStackedEndPadding, getPaddingBottom());
      }
      mStacked = paramBoolean1;
    }
  }
  
  public void setStackedGravity(GravityEnum paramGravityEnum)
  {
    mStackedGravity = paramGravityEnum;
  }
  
  public void setStackedSelector(Drawable paramDrawable)
  {
    mStackedBackground = paramDrawable;
    if (mStacked) {
      setStacked(true, true);
    }
  }
}
