package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ContentFrameLayout
  extends FrameLayout
{
  private a mAttachListener;
  private final Rect mDecorPadding = new Rect();
  private TypedValue mFixedHeightMajor;
  private TypedValue mFixedHeightMinor;
  private TypedValue mFixedWidthMajor;
  private TypedValue mFixedWidthMinor;
  private TypedValue mMinWidthMajor;
  private TypedValue mMinWidthMinor;
  
  public ContentFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void dispatchFitSystemWindows(Rect paramRect)
  {
    fitSystemWindows(paramRect);
  }
  
  public TypedValue getFixedHeightMajor()
  {
    if (mFixedHeightMajor == null) {
      mFixedHeightMajor = new TypedValue();
    }
    return mFixedHeightMajor;
  }
  
  public TypedValue getFixedHeightMinor()
  {
    if (mFixedHeightMinor == null) {
      mFixedHeightMinor = new TypedValue();
    }
    return mFixedHeightMinor;
  }
  
  public TypedValue getFixedWidthMajor()
  {
    if (mFixedWidthMajor == null) {
      mFixedWidthMajor = new TypedValue();
    }
    return mFixedWidthMajor;
  }
  
  public TypedValue getFixedWidthMinor()
  {
    if (mFixedWidthMinor == null) {
      mFixedWidthMinor = new TypedValue();
    }
    return mFixedWidthMinor;
  }
  
  public TypedValue getMinWidthMajor()
  {
    if (mMinWidthMajor == null) {
      mMinWidthMajor = new TypedValue();
    }
    return mMinWidthMajor;
  }
  
  public TypedValue getMinWidthMinor()
  {
    if (mMinWidthMinor == null) {
      mMinWidthMinor = new TypedValue();
    }
    return mMinWidthMinor;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    a localA = mAttachListener;
    if (localA != null) {
      localA.onAttachedFromWindow();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    a localA = mAttachListener;
    if (localA != null) {
      localA.onDetachedFromWindow();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    int j;
    if (widthPixels < heightPixels) {
      j = 1;
    } else {
      j = 0;
    }
    int i1 = View.MeasureSpec.getMode(paramInt1);
    int i2 = View.MeasureSpec.getMode(paramInt2);
    int n = 0;
    int k = n;
    int m = paramInt1;
    Object localObject;
    if (i1 == Integer.MIN_VALUE)
    {
      if (j != 0) {
        localObject = mFixedWidthMinor;
      } else {
        localObject = mFixedWidthMajor;
      }
      k = n;
      m = paramInt1;
      if (localObject != null)
      {
        int i3 = type;
        k = n;
        m = paramInt1;
        if (i3 != 0)
        {
          i = 0;
          if (i3 == 5)
          {
            i = (int)((TypedValue)localObject).getDimension(localDisplayMetrics);
          }
          else if (i3 == 6)
          {
            i = widthPixels;
            i = (int)((TypedValue)localObject).getFraction(i, i);
          }
          k = n;
          m = paramInt1;
          if (i > 0)
          {
            localObject = mDecorPadding;
            m = View.MeasureSpec.makeMeasureSpec(Math.min(i - (left + right), View.MeasureSpec.getSize(paramInt1)), 1073741824);
            k = 1;
          }
        }
      }
    }
    int i = paramInt2;
    if (i2 == Integer.MIN_VALUE)
    {
      if (j != 0) {
        localObject = mFixedHeightMajor;
      } else {
        localObject = mFixedHeightMinor;
      }
      i = paramInt2;
      if (localObject != null)
      {
        n = type;
        i = paramInt2;
        if (n != 0)
        {
          paramInt1 = 0;
          if (n == 5)
          {
            paramInt1 = (int)((TypedValue)localObject).getDimension(localDisplayMetrics);
          }
          else if (n == 6)
          {
            paramInt1 = heightPixels;
            paramInt1 = (int)((TypedValue)localObject).getFraction(paramInt1, paramInt1);
          }
          i = paramInt2;
          if (paramInt1 > 0)
          {
            localObject = mDecorPadding;
            i = View.MeasureSpec.makeMeasureSpec(Math.min(paramInt1 - (top + bottom), View.MeasureSpec.getSize(paramInt2)), 1073741824);
          }
        }
      }
    }
    super.onMeasure(m, i);
    i2 = getMeasuredWidth();
    m = 0;
    n = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    paramInt2 = n;
    paramInt1 = m;
    if (k == 0)
    {
      paramInt2 = n;
      paramInt1 = m;
      if (i1 == Integer.MIN_VALUE)
      {
        if (j != 0) {
          localObject = mMinWidthMinor;
        } else {
          localObject = mMinWidthMajor;
        }
        paramInt2 = n;
        paramInt1 = m;
        if (localObject != null)
        {
          j = type;
          paramInt2 = n;
          paramInt1 = m;
          if (j != 0)
          {
            paramInt1 = 0;
            if (j == 5)
            {
              paramInt1 = (int)((TypedValue)localObject).getDimension(localDisplayMetrics);
            }
            else if (j == 6)
            {
              paramInt1 = widthPixels;
              paramInt1 = (int)((TypedValue)localObject).getFraction(paramInt1, paramInt1);
            }
            j = paramInt1;
            if (paramInt1 > 0)
            {
              localObject = mDecorPadding;
              j = paramInt1 - (left + right);
            }
            paramInt2 = n;
            paramInt1 = m;
            if (i2 < j)
            {
              paramInt2 = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
              paramInt1 = 1;
            }
          }
        }
      }
    }
    if (paramInt1 != 0) {
      super.onMeasure(paramInt2, i);
    }
  }
  
  public void setAttachListener(a paramA)
  {
    mAttachListener = paramA;
  }
  
  public void setDecorPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDecorPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    if (ViewCompat.get(this)) {
      requestLayout();
    }
  }
  
  public static abstract interface a
  {
    public abstract void onAttachedFromWindow();
    
    public abstract void onDetachedFromWindow();
  }
}
