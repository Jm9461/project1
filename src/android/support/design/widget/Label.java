package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

public final class Label
{
  ValueAnimator a = null;
  private final Animator.AnimatorListener h = new GlowPadView.2(this);
  private StateListAnimator.Tuple mLastMatch = null;
  private final ArrayList<u.b> mTuples = new ArrayList();
  
  public Label() {}
  
  private void setColor()
  {
    ValueAnimator localValueAnimator = a;
    if (localValueAnimator != null)
    {
      localValueAnimator.cancel();
      a = null;
    }
  }
  
  private void setColor(StateListAnimator.Tuple paramTuple)
  {
    a = b;
    a.start();
  }
  
  public void a(int[] paramArrayOfInt, ValueAnimator paramValueAnimator)
  {
    paramArrayOfInt = new StateListAnimator.Tuple(paramArrayOfInt, paramValueAnimator);
    paramValueAnimator.addListener(h);
    mTuples.add(paramArrayOfInt);
  }
  
  public void b()
  {
    ValueAnimator localValueAnimator = a;
    if (localValueAnimator != null)
    {
      localValueAnimator.end();
      a = null;
    }
  }
  
  public void setState(int[] paramArrayOfInt)
  {
    Object localObject2 = null;
    int j = mTuples.size();
    int i = 0;
    Object localObject1;
    for (;;)
    {
      localObject1 = localObject2;
      if (i >= j) {
        break;
      }
      localObject1 = (StateListAnimator.Tuple)mTuples.get(i);
      if (StateSet.stateSetMatches(mSpecs, paramArrayOfInt)) {
        break;
      }
      i += 1;
    }
    paramArrayOfInt = mLastMatch;
    if (localObject1 == paramArrayOfInt) {
      return;
    }
    if (paramArrayOfInt != null) {
      setColor();
    }
    mLastMatch = ((StateListAnimator.Tuple)localObject1);
    if (localObject1 != null) {
      setColor((StateListAnimator.Tuple)localObject1);
    }
  }
}
