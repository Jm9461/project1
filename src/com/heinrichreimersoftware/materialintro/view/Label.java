package com.heinrichreimersoftware.materialintro.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Label
  extends ViewPager
{
  private boolean e = true;
  private boolean f = true;
  private boolean i = false;
  private float j;
  
  public Label(Context paramContext)
  {
    super(paramContext);
  }
  
  public Label(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void a()
  {
    int k = getCurrentItem();
    scrollTo(getWidth() * k, getScrollY());
    setCurrentItem(k);
  }
  
  private boolean b(MotionEvent paramMotionEvent)
  {
    if ((a(paramMotionEvent) == -1) && (!f))
    {
      if (!i)
      {
        i = true;
        a();
        return false;
      }
    }
    else if ((a(paramMotionEvent) == 1) && (!e))
    {
      if (!i)
      {
        i = true;
        a();
        return false;
      }
    }
    else
    {
      i = false;
      return true;
    }
    return false;
  }
  
  public int a(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      j = paramMotionEvent.getX();
      return 0;
    }
    if ((paramMotionEvent.getAction() == 2) || (paramMotionEvent.getAction() == 1))
    {
      float f1 = paramMotionEvent.getX() - j;
      if (0.0F > Math.abs(f1)) {
        return 0;
      }
      if (f1 > 0.0F) {
        return -1;
      }
      if (f1 < 0.0F) {
        return 1;
      }
    }
    return 0;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (b(paramMotionEvent)) && (super.onInterceptTouchEvent(paramMotionEvent));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return (b(paramMotionEvent)) && (super.onTouchEvent(paramMotionEvent));
  }
  
  public void setSwipeLeftEnabled(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  public void setSwipeRightEnabled(boolean paramBoolean)
  {
    f = paramBoolean;
  }
}
