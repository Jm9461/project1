package com.joanzapata.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import apps.muzei.api.R.styleable;

public class PopupWindowCompat
  extends PopupWindow
{
  private final boolean mOverlapAnchor;
  
  public PopupWindowCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PopupWindow, paramInt, 0);
    mOverlapAnchor = paramContext.getBoolean(R.styleable.PopupWindow_overlapAnchor, false);
    paramContext.recycle();
  }
  
  public void showAsDropDown(View paramView, int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (Build.VERSION.SDK_INT < 21)
    {
      i = paramInt2;
      if (mOverlapAnchor) {
        i = paramInt2 - paramView.getHeight();
      }
    }
    super.showAsDropDown(paramView, paramInt1, i);
  }
  
  public void showAsDropDown(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2;
    if (Build.VERSION.SDK_INT < 21)
    {
      i = paramInt2;
      if (mOverlapAnchor) {
        i = paramInt2 - paramView.getHeight();
      }
    }
    super.showAsDropDown(paramView, paramInt1, i, paramInt3);
  }
  
  public void update(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt2;
    if (Build.VERSION.SDK_INT < 21)
    {
      i = paramInt2;
      if (mOverlapAnchor) {
        i = paramInt2 - paramView.getHeight();
      }
    }
    super.update(paramView, paramInt1, i, paramInt3, paramInt4);
  }
}
