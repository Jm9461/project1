package apps.v4.animation;

import android.view.animation.Interpolator;
import b.h.a.a.a;
import java.util.ArrayList;

public abstract class Animator
  implements Cloneable
{
  ArrayList<a.a> mListeners = null;
  
  public Animator() {}
  
  public void addListener(AnimatorListener paramAnimatorListener)
  {
    if (mListeners == null) {
      mListeners = new ArrayList();
    }
    mListeners.add(paramAnimatorListener);
  }
  
  public Animator clone()
  {
    AssertionError localAssertionError;
    try
    {
      Object localObject = super.clone();
      localObject = (Animator)localObject;
      if (mListeners != null)
      {
        ArrayList localArrayList1 = mListeners;
        ArrayList localArrayList2 = new ArrayList();
        mListeners = localArrayList2;
        int j = localArrayList1.size();
        int i = 0;
        while (i < j)
        {
          localArrayList2 = mListeners;
          localArrayList2.add(localArrayList1.get(i));
          i += 1;
        }
      }
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localAssertionError = new AssertionError();
      throw localAssertionError;
    }
    return localAssertionError;
  }
  
  public ArrayList getListeners()
  {
    return mListeners;
  }
  
  public void removeListener(AnimatorListener paramAnimatorListener)
  {
    ArrayList localArrayList = mListeners;
    if (localArrayList == null) {
      return;
    }
    localArrayList.remove(paramAnimatorListener);
    if (mListeners.size() == 0) {
      mListeners = null;
    }
  }
  
  public abstract Animator setDuration(long paramLong);
  
  public abstract void setInterpolator(Interpolator paramInterpolator);
  
  public void start() {}
  
  public abstract interface AnimatorListener
  {
    public abstract void onAnimationEnd(Animator paramAnimator);
    
    public abstract void onAnimationRepeat(Animator paramAnimator);
    
    public abstract void onAnimationStart(Animator paramAnimator);
  }
}
