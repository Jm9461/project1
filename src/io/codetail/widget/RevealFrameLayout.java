package io.codetail.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import ch.acra.widget.RevealAnimator;
import ch.acra.widget.Target;

public class RevealFrameLayout
  extends FrameLayout
  implements RevealAnimator
{
  private float mRadius;
  private Path mRevealPath = new Path();
  private boolean mRunning;
  private Target mTarget;
  private final Rect mTempRect = new Rect();
  
  public RevealFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RevealFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RevealFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    if ((mRunning) && (paramView == mTarget.getTarget()))
    {
      int i = paramCanvas.save();
      mRevealPath.reset();
      Path localPath = mRevealPath;
      Target localTarget = mTarget;
      localPath.addCircle(name, port, mRadius, Path.Direction.CW);
      paramCanvas.clipPath(mRevealPath);
      boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
      paramCanvas.restoreToCount(i);
      return bool;
    }
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  public float getRevealRadius()
  {
    return mRadius;
  }
  
  public void onRevealAnimationEnd()
  {
    mRunning = false;
    invalidate(mTempRect);
  }
  
  public void onRevealAnimationStart()
  {
    mRunning = true;
  }
  
  public void setRevealRadius(float paramFloat)
  {
    mRadius = paramFloat;
    invalidate(mTempRect);
  }
  
  public void setTarget(Target paramTarget)
  {
    paramTarget.getTarget().getHitRect(mTempRect);
    mTarget = paramTarget;
  }
}
