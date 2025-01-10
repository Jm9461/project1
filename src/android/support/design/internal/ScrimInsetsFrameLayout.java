package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import org.org.android.R.style;
import org.org.android.R.styleable;

public class ScrimInsetsFrameLayout
  extends FrameLayout
{
  Drawable mInsetForeground;
  private Rect mTempRect = new Rect();
  Rect top;
  
  public ScrimInsetsFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = g.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.ScrimInsetsFrameLayout, paramInt, R.style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
    mInsetForeground = paramContext.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
    paramContext.recycle();
    setWillNotDraw(true);
    ViewCompat.setOnApplyWindowInsetsListener(this, new AppBarLayout.1(this));
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int i = getWidth();
    int j = getHeight();
    if ((top != null) && (mInsetForeground != null))
    {
      int k = paramCanvas.save();
      paramCanvas.translate(getScrollX(), getScrollY());
      mTempRect.set(0, 0, i, top.top);
      mInsetForeground.setBounds(mTempRect);
      mInsetForeground.draw(paramCanvas);
      mTempRect.set(0, j - top.bottom, i, j);
      mInsetForeground.setBounds(mTempRect);
      mInsetForeground.draw(paramCanvas);
      Rect localRect1 = mTempRect;
      Rect localRect2 = top;
      localRect1.set(0, top, left, j - bottom);
      mInsetForeground.setBounds(mTempRect);
      mInsetForeground.draw(paramCanvas);
      localRect1 = mTempRect;
      localRect2 = top;
      localRect1.set(i - right, top, i, j - bottom);
      mInsetForeground.setBounds(mTempRect);
      mInsetForeground.draw(paramCanvas);
      paramCanvas.restoreToCount(k);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Drawable localDrawable = mInsetForeground;
    if (localDrawable != null) {
      localDrawable.setCallback(this);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Drawable localDrawable = mInsetForeground;
    if (localDrawable != null) {
      localDrawable.setCallback(null);
    }
  }
  
  protected void onInsetsChanged(WindowInsetsCompat paramWindowInsetsCompat)
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
}
