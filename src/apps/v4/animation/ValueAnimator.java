package apps.v4.animation;

import android.animation.TimeInterpolator;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import b.h.a.k;
import b.h.a.m;
import b.h.a.m.f;
import b.h.a.m.g;
import java.util.ArrayList;
import java.util.HashMap;

public class ValueAnimator
  extends Animator
{
  private static ThreadLocal<m.f> sAnimationHandler = new ThreadLocal();
  private static final ThreadLocal<ArrayList<m>> sAnimations = new CycleDetectingLockFactory.1();
  private static final Interpolator sDefaultInterpolator;
  private static final ThreadLocal<ArrayList<m>> sDelayedAnims;
  private static final ThreadLocal<ArrayList<m>> sEndingAnims;
  private static long sFrameDelay = 10L;
  private static final ThreadLocal<ArrayList<m>> sPendingAnimations = new BitmapHunter.1();
  private static final ThreadLocal<ArrayList<m>> sReadyAnims;
  private int mCurrentIteration = 0;
  private long mDelayStartTime;
  private long mDuration = 300L;
  boolean mInitialized = false;
  private Interpolator mInterpolator = sDefaultInterpolator;
  private boolean mPlayingBackwards = false;
  int mPlayingState = 0;
  private int mRepeatCount = 0;
  private int mRepeatMode = 1;
  private boolean mRunning = false;
  long mSeekTime = -1L;
  private long mStartDelay = 0L;
  long mStartTime;
  private boolean mStartedDelay = false;
  private ArrayList<m.g> mUpdateListeners = null;
  PropertyValuesHolder[] mValues;
  HashMap<String, k> mValuesMap;
  
  static
  {
    sDelayedAnims = new Platform.1();
    sEndingAnims = new ThreadLocal()
    {
      protected ArrayList initialValue()
      {
        return new ArrayList();
      }
    };
    sReadyAnims = new ThreadLocal()
    {
      protected ArrayList initialValue()
      {
        return new ArrayList();
      }
    };
    sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    new IntEvaluator();
    new FloatEvaluator();
  }
  
  public ValueAnimator() {}
  
  private boolean delayedAnimationFrame(long paramLong)
  {
    if (!mStartedDelay)
    {
      mStartedDelay = true;
      mDelayStartTime = paramLong;
    }
    else
    {
      long l1 = paramLong - mDelayStartTime;
      long l2 = mStartDelay;
      if (l1 > l2)
      {
        mStartTime = (paramLong - (l1 - l2));
        mPlayingState = 1;
        return true;
      }
    }
    return false;
  }
  
  private void endAnimation()
  {
    ((ArrayList)sAnimations.get()).remove(this);
    ((ArrayList)sPendingAnimations.get()).remove(this);
    ((ArrayList)sDelayedAnims.get()).remove(this);
    mPlayingState = 0;
    if (mRunning)
    {
      ArrayList localArrayList = mListeners;
      if (localArrayList != null)
      {
        localArrayList = (ArrayList)localArrayList.clone();
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          ((Animator.AnimatorListener)localArrayList.get(i)).onAnimationEnd(this);
          i += 1;
        }
      }
    }
    mRunning = false;
  }
  
  public static ValueAnimator ofFloat(float... paramVarArgs)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setFloatValues(paramVarArgs);
    return localValueAnimator;
  }
  
  private void start(boolean paramBoolean)
  {
    if (Looper.myLooper() != null)
    {
      mPlayingBackwards = paramBoolean;
      mCurrentIteration = 0;
      mPlayingState = 0;
      mStartedDelay = false;
      ((ArrayList)sPendingAnimations.get()).add(this);
      if (mStartDelay == 0L)
      {
        setCurrentPlayTime(getCurrentPlayTime());
        mPlayingState = 0;
        mRunning = true;
        localObject = mListeners;
        if (localObject != null)
        {
          localObject = (ArrayList)((ArrayList)localObject).clone();
          int j = ((ArrayList)localObject).size();
          int i = 0;
          while (i < j)
          {
            ((Animator.AnimatorListener)((ArrayList)localObject).get(i)).onAnimationStart(this);
            i += 1;
          }
        }
      }
      AnimationHandler localAnimationHandler = (AnimationHandler)sAnimationHandler.get();
      localObject = localAnimationHandler;
      if (localAnimationHandler == null)
      {
        localAnimationHandler = new AnimationHandler(null);
        localObject = localAnimationHandler;
        sAnimationHandler.set(localAnimationHandler);
      }
      ((Handler)localObject).sendEmptyMessage(0);
      return;
    }
    Object localObject = new AndroidRuntimeException("Animators may only be run on Looper threads");
    throw ((Throwable)localObject);
  }
  
  private void startAnimation()
  {
    initAnimation();
    ((ArrayList)sAnimations.get()).add(this);
    if (mStartDelay > 0L)
    {
      ArrayList localArrayList = mListeners;
      if (localArrayList != null)
      {
        localArrayList = (ArrayList)localArrayList.clone();
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          ((Animator.AnimatorListener)localArrayList.get(i)).onAnimationStart(this);
          i += 1;
        }
      }
    }
  }
  
  void animateValue(float paramFloat)
  {
    paramFloat = mInterpolator.getInterpolation(paramFloat);
    int j = mValues.length;
    int i = 0;
    while (i < j)
    {
      mValues[i].calculateValue(paramFloat);
      i += 1;
    }
    ArrayList localArrayList = mUpdateListeners;
    if (localArrayList != null)
    {
      j = localArrayList.size();
      i = 0;
      while (i < j)
      {
        ((AnimatorUpdateListener)mUpdateListeners.get(i)).onAnimationUpdate(this);
        i += 1;
      }
    }
  }
  
  boolean animationFrame(long paramLong)
  {
    boolean bool2 = false;
    if (mPlayingState == 0)
    {
      mPlayingState = 1;
      l = mSeekTime;
      if (l < 0L)
      {
        mStartTime = paramLong;
      }
      else
      {
        mStartTime = (paramLong - l);
        mSeekTime = -1L;
      }
    }
    int i = mPlayingState;
    if ((i != 1) && (i != 2)) {
      return false;
    }
    long l = mDuration;
    if (l > 0L) {
      f2 = (float)(paramLong - mStartTime) / (float)l;
    } else {
      f2 = 1.0F;
    }
    boolean bool1 = bool2;
    float f1 = f2;
    if (f2 >= 1.0F)
    {
      i = mCurrentIteration;
      int j = mRepeatCount;
      if ((i >= j) && (j != -1))
      {
        bool1 = true;
        f1 = Math.min(f2, 1.0F);
      }
      else
      {
        ArrayList localArrayList = mListeners;
        if (localArrayList != null)
        {
          j = localArrayList.size();
          i = 0;
          while (i < j)
          {
            ((Animator.AnimatorListener)mListeners.get(i)).onAnimationRepeat(this);
            i += 1;
          }
        }
        if (mRepeatMode == 2) {
          mPlayingBackwards ^= true;
        }
        mCurrentIteration += (int)f2;
        f1 = f2 % 1.0F;
        mStartTime += mDuration;
        bool1 = bool2;
      }
    }
    float f2 = f1;
    if (mPlayingBackwards) {
      f2 = 1.0F - f1;
    }
    animateValue(f2);
    return bool1;
  }
  
  public ValueAnimator clone()
  {
    ValueAnimator localValueAnimator = (ValueAnimator)super.clone();
    int j;
    int i;
    if (mUpdateListeners != null)
    {
      localObject = mUpdateListeners;
      mUpdateListeners = new ArrayList();
      j = ((ArrayList)localObject).size();
      i = 0;
      while (i < j)
      {
        mUpdateListeners.add(((ArrayList)localObject).get(i));
        i += 1;
      }
    }
    mSeekTime = -1L;
    mPlayingBackwards = false;
    mCurrentIteration = 0;
    mInitialized = false;
    mPlayingState = 0;
    mStartedDelay = false;
    Object localObject = mValues;
    if (localObject != null)
    {
      j = localObject.length;
      mValues = new PropertyValuesHolder[j];
      mValuesMap = new HashMap(j);
      i = 0;
      while (i < j)
      {
        PropertyValuesHolder localPropertyValuesHolder = localObject[i].clone();
        mValues[i] = localPropertyValuesHolder;
        mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
        i += 1;
      }
    }
    return localValueAnimator;
  }
  
  public long getCurrentPlayTime()
  {
    if ((mInitialized) && (mPlayingState != 0)) {
      return AnimationUtils.currentAnimationTimeMillis() - mStartTime;
    }
    return 0L;
  }
  
  void initAnimation()
  {
    if (!mInitialized)
    {
      int j = mValues.length;
      int i = 0;
      while (i < j)
      {
        mValues[i].init();
        i += 1;
      }
      mInitialized = true;
    }
  }
  
  public void setCurrentPlayTime(long paramLong)
  {
    initAnimation();
    long l = AnimationUtils.currentAnimationTimeMillis();
    if (mPlayingState != 1)
    {
      mSeekTime = paramLong;
      mPlayingState = 2;
    }
    mStartTime = (l - paramLong);
    animationFrame(l);
  }
  
  public ValueAnimator setDuration(long paramLong)
  {
    if (paramLong >= 0L)
    {
      mDuration = paramLong;
      return this;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Animators cannot have negative duration: ");
    localStringBuilder.append(paramLong);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    if (paramTypeEvaluator != null)
    {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = mValues;
      if ((arrayOfPropertyValuesHolder != null) && (arrayOfPropertyValuesHolder.length > 0)) {
        arrayOfPropertyValuesHolder[0].setEvaluator(paramTypeEvaluator);
      }
    }
  }
  
  public void setFloatValues(float... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      if (paramVarArgs.length == 0) {
        return;
      }
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = mValues;
      if ((arrayOfPropertyValuesHolder != null) && (arrayOfPropertyValuesHolder.length != 0)) {
        arrayOfPropertyValuesHolder[0].setFloatValues(paramVarArgs);
      } else {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat("", paramVarArgs) });
      }
      mInitialized = false;
    }
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    if (paramInterpolator != null)
    {
      mInterpolator = paramInterpolator;
      return;
    }
    mInterpolator = new LinearInterpolator();
  }
  
  public void setValues(PropertyValuesHolder... paramVarArgs)
  {
    int j = paramVarArgs.length;
    mValues = paramVarArgs;
    mValuesMap = new HashMap(j);
    int i = 0;
    while (i < j)
    {
      PropertyValuesHolder localPropertyValuesHolder = paramVarArgs[i];
      mValuesMap.put(localPropertyValuesHolder.getPropertyName(), localPropertyValuesHolder);
      i += 1;
    }
    mInitialized = false;
  }
  
  public void start()
  {
    start(false);
  }
  
  public String toString()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("ValueAnimator@");
    ((StringBuilder)localObject1).append(Integer.toHexString(hashCode()));
    Object localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = localObject2;
    if (mValues != null)
    {
      int i = 0;
      while (i < mValues.length)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("\n    ");
        ((StringBuilder)localObject2).append(mValues[i].toString());
        localObject1 = ((StringBuilder)localObject2).toString();
        i += 1;
      }
    }
    return localObject2;
    return localObject1;
  }
  
  class AnimationHandler
    extends Handler
  {
    private AnimationHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      int j = 1;
      int i = 1;
      ArrayList localArrayList1 = (ArrayList)ValueAnimator.access$getSAnimations().get();
      ArrayList localArrayList2 = (ArrayList)ValueAnimator.access$getSDelayedAnims().get();
      int k = what;
      ValueAnimator localValueAnimator;
      if (k != 0)
      {
        if (k != 1) {
          return;
        }
      }
      else
      {
        paramMessage = (ArrayList)ValueAnimator.access$getSPendingAnimations().get();
        if ((localArrayList1.size() > 0) || (localArrayList2.size() > 0)) {
          i = 0;
        }
        for (;;)
        {
          j = i;
          if (paramMessage.size() <= 0) {
            break;
          }
          localObject = (ArrayList)paramMessage.clone();
          paramMessage.clear();
          k = ((ArrayList)localObject).size();
          j = 0;
          while (j < k)
          {
            localValueAnimator = (ValueAnimator)((ArrayList)localObject).get(j);
            if (ValueAnimator.access$getMStartDelay(localValueAnimator) == 0L) {
              ValueAnimator.access$getStartAnimation(localValueAnimator);
            } else {
              localArrayList2.add(localValueAnimator);
            }
            j += 1;
          }
        }
      }
      long l = AnimationUtils.currentAnimationTimeMillis();
      Object localObject = (ArrayList)ValueAnimator.access$getSReadyAnims().get();
      paramMessage = (ArrayList)ValueAnimator.access$getSEndingAnims().get();
      k = localArrayList2.size();
      i = 0;
      while (i < k)
      {
        localValueAnimator = (ValueAnimator)localArrayList2.get(i);
        if (ValueAnimator.access$getDelayedAnimationFrame(localValueAnimator, l)) {
          ((ArrayList)localObject).add(localValueAnimator);
        }
        i += 1;
      }
      k = ((ArrayList)localObject).size();
      if (k > 0)
      {
        i = 0;
        while (i < k)
        {
          localValueAnimator = (ValueAnimator)((ArrayList)localObject).get(i);
          ValueAnimator.access$getStartAnimation(localValueAnimator);
          ValueAnimator.access$setMRunning(localValueAnimator, true);
          localArrayList2.remove(localValueAnimator);
          i += 1;
        }
        ((ArrayList)localObject).clear();
      }
      i = localArrayList1.size();
      k = 0;
      while (k < i)
      {
        localObject = (ValueAnimator)localArrayList1.get(k);
        if (((ValueAnimator)localObject).animationFrame(l)) {
          paramMessage.add(localObject);
        }
        if (localArrayList1.size() == i)
        {
          k += 1;
        }
        else
        {
          i -= 1;
          paramMessage.remove(localObject);
        }
      }
      if (paramMessage.size() > 0)
      {
        i = 0;
        while (i < paramMessage.size())
        {
          ValueAnimator.access$getEndAnimation((ValueAnimator)paramMessage.get(i));
          i += 1;
        }
        paramMessage.clear();
      }
      if (j != 0)
      {
        if ((localArrayList1.isEmpty()) && (localArrayList2.isEmpty())) {
          return;
        }
        sendEmptyMessageDelayed(1, Math.max(0L, ValueAnimator.access$getSFrameDelay() - (AnimationUtils.currentAnimationTimeMillis() - l)));
        return;
      }
    }
  }
  
  public abstract interface AnimatorUpdateListener
  {
    public abstract void onAnimationUpdate(ValueAnimator paramValueAnimator);
  }
}
