package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.Attribute;
import android.arch.lifecycle.Item;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.org.jaxen.util.DebugUtils;
import org.org.jaxen.util.LogWriter;
import org.org.jaxen.util.TFloatLinkedList;

final class FragmentManagerImpl
  extends FragmentManager
  implements LayoutInflater.Factory2
{
  static boolean DEBUG = false;
  static final Interpolator DECELERATE_CUBIC;
  static final Interpolator DECELERATE_QUINT;
  static Field sAnimationListenerField = null;
  ArrayList<Boolean> a;
  ArrayList<f> b;
  private final CopyOnWriteArrayList<l.j> c = new CopyOnWriteArrayList();
  ArrayList<k.c> j;
  ArrayList<c> k;
  ArrayList<l.n> l;
  SparseArray<f> mActive;
  final ArrayList<f> mAdded = new ArrayList();
  ArrayList<Integer> mAvailBackStackIndices;
  ArrayList<c> mBackStack;
  ArrayList<c> mBackStackIndices;
  FragmentContainer mContainer;
  ArrayList<f> mCreatedMenus;
  int mCurState = 0;
  boolean mDestroyed;
  java.lang.Runnable mExecCommit = new MonthByWeekFragment.2(this);
  boolean mExecutingActions;
  boolean mHavePendingDeferredStart;
  FragmentHostCallback mHost;
  int mIndex = 0;
  boolean mNeedMenuInvalidate;
  String mNoTransactionsBecause;
  Fragment mParent;
  ArrayList<l.l> mPendingActions;
  boolean mState;
  SparseArray<Parcelable> mStateArray = null;
  Bundle mStateBundle = null;
  boolean mStateSaved;
  Fragment mTarget;
  List mView;
  
  static
  {
    DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    new AccelerateInterpolator(2.5F);
    new AccelerateInterpolator(1.5F);
  }
  
  FragmentManagerImpl() {}
  
  private int a(ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt1, int paramInt2, TFloatLinkedList paramTFloatLinkedList)
  {
    int m = paramInt2;
    int i = paramInt2 - 1;
    while (i >= paramInt1)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList1.get(i);
      boolean bool = ((Boolean)paramArrayList2.get(i)).booleanValue();
      int i1;
      if ((localBackStackRecord.c()) && (!localBackStackRecord.a(paramArrayList1, i + 1, paramInt2))) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      int n = m;
      if (i1 != 0)
      {
        if (l == null) {
          l = new ArrayList();
        }
        b localB = new b(localBackStackRecord, bool);
        l.add(localB);
        localBackStackRecord.a(localB);
        if (bool) {
          localBackStackRecord.run();
        } else {
          localBackStackRecord.run(false);
        }
        n = m - 1;
        if (i != n)
        {
          paramArrayList1.remove(i);
          paramArrayList1.add(n, localBackStackRecord);
        }
        dump(paramTFloatLinkedList);
      }
      i -= 1;
      m = n;
    }
    return m;
  }
  
  private void a()
  {
    if (l != null) {
      while (!l.isEmpty()) {
        ((b)l.remove(0)).run();
      }
    }
  }
  
  private void a(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    Object localObject = l;
    int m;
    if (localObject == null) {
      m = 0;
    } else {
      m = ((ArrayList)localObject).size();
    }
    int i = 0;
    while (i < m)
    {
      localObject = (b)l.get(i);
      int n;
      int i1;
      if ((paramArrayList1 != null) && (!b))
      {
        n = paramArrayList1.indexOf(c);
        if ((n != -1) && (((Boolean)paramArrayList2.get(n)).booleanValue()))
        {
          ((b)localObject).write();
          n = m;
          i1 = i;
          break label221;
        }
      }
      if (!((b)localObject).c())
      {
        n = m;
        i1 = i;
        if (paramArrayList1 != null)
        {
          n = m;
          i1 = i;
          if (!c.a(paramArrayList1, 0, paramArrayList1.size())) {}
        }
      }
      else
      {
        l.remove(i);
        i1 = i - 1;
        n = m - 1;
        if ((paramArrayList1 != null) && (!b))
        {
          i = paramArrayList1.indexOf(c);
          if ((i != -1) && (((Boolean)paramArrayList2.get(i)).booleanValue()))
          {
            ((b)localObject).write();
            break label221;
          }
        }
        ((b)localObject).run();
      }
      label221:
      i = i1 + 1;
      m = n;
    }
  }
  
  private boolean a(String paramString, int paramInt1, int paramInt2)
  {
    execPendingActions();
    onCreateView(true);
    Object localObject = mTarget;
    if ((localObject != null) && (paramInt1 < 0) && (paramString == null))
    {
      localObject = ((Fragment)localObject).getChildAt();
      if ((localObject != null) && (((FragmentManager)localObject).popBackStackImmediate())) {
        return true;
      }
    }
    boolean bool = a(k, a, paramString, paramInt1, paramInt2);
    if (bool)
    {
      mExecutingActions = true;
      try
      {
        add(k, a);
        run();
      }
      catch (Throwable paramString)
      {
        run();
        throw paramString;
      }
    }
    performPendingDeferredStart();
    clear();
    return bool;
  }
  
  private void add(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    if (paramArrayList1 != null)
    {
      if (paramArrayList1.isEmpty()) {
        return;
      }
      if ((paramArrayList2 != null) && (paramArrayList1.size() == paramArrayList2.size()))
      {
        a(paramArrayList1, paramArrayList2);
        int i2 = paramArrayList1.size();
        int m = 0;
        int i = 0;
        while (i < i2)
        {
          int n = m;
          int i1 = i;
          if (!getc)
          {
            if (m != i) {
              run(paramArrayList1, paramArrayList2, m, i);
            }
            n = i + 1;
            m = n;
            if (((Boolean)paramArrayList2.get(i)).booleanValue()) {
              for (;;)
              {
                m = n;
                if (n >= i2) {
                  break;
                }
                m = n;
                if (!((Boolean)paramArrayList2.get(n)).booleanValue()) {
                  break;
                }
                m = n;
                if (getc) {
                  break;
                }
                n += 1;
              }
            }
            run(paramArrayList1, paramArrayList2, i, m);
            n = m;
            i1 = m - 1;
          }
          i = i1 + 1;
          m = n;
        }
        if (m != i2) {
          run(paramArrayList1, paramArrayList2, m, i2);
        }
      }
      else
      {
        throw new IllegalStateException("Internal error with the back stack records");
      }
    }
  }
  
  private void checkStateLoss()
  {
    if (!getFragment())
    {
      if (mNoTransactionsBecause == null) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can not perform this action inside of ");
      localStringBuilder.append(mNoTransactionsBecause);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
  }
  
  private void clear()
  {
    SparseArray localSparseArray = mActive;
    if (localSparseArray != null)
    {
      int i = localSparseArray.size() - 1;
      while (i >= 0)
      {
        if (mActive.valueAt(i) == null)
        {
          localSparseArray = mActive;
          localSparseArray.delete(localSparseArray.keyAt(i));
        }
        i -= 1;
      }
    }
  }
  
  private Fragment dump(Fragment paramFragment)
  {
    ViewGroup localViewGroup = mContainer;
    View localView = mView;
    if (localViewGroup != null)
    {
      if (localView == null) {
        return null;
      }
      int i = mAdded.indexOf(paramFragment) - 1;
      while (i >= 0)
      {
        paramFragment = (Fragment)mAdded.get(i);
        if ((mContainer == localViewGroup) && (mView != null)) {
          return paramFragment;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  private static void dump(List paramList)
  {
    if (paramList == null) {
      return;
    }
    Object localObject = paramList.get();
    if (localObject != null)
    {
      localObject = ((java.util.List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        nextmRetaining = true;
      }
    }
    paramList = paramList.append();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        dump((List)paramList.next());
      }
    }
  }
  
  private void dump(TFloatLinkedList paramTFloatLinkedList)
  {
    int i = mCurState;
    if (i < 1) {
      return;
    }
    int m = Math.min(i, 3);
    int n = mAdded.size();
    i = 0;
    while (i < n)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (mState < m)
      {
        moveToState(localFragment, m, localFragment.getId(), localFragment.getIcon(), false);
        if ((mView != null) && (!mHidden) && (mActive)) {
          paramTFloatLinkedList.add(localFragment);
        }
      }
      i += 1;
    }
  }
  
  private boolean execPendingActions(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    boolean bool = false;
    try
    {
      if ((mPendingActions != null) && (mPendingActions.size() != 0))
      {
        int m = mPendingActions.size();
        int i = 0;
        while (i < m)
        {
          bool |= ((Runnable)mPendingActions.get(i)).run(paramArrayList1, paramArrayList2);
          i += 1;
        }
        mPendingActions.clear();
        mHost.getHandler().removeCallbacks(mExecCommit);
        return bool;
      }
      return false;
    }
    catch (Throwable paramArrayList1)
    {
      throw paramArrayList1;
    }
  }
  
  static Animation makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2)
  {
    paramContext = new AlphaAnimation(paramFloat1, paramFloat2);
    paramContext.setInterpolator(DECELERATE_CUBIC);
    paramContext.setDuration(220L);
    return new Animation(paramContext);
  }
  
  static Animation makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramContext = new AnimationSet(false);
    Object localObject = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    ((android.view.animation.Animation)localObject).setInterpolator(DECELERATE_QUINT);
    ((android.view.animation.Animation)localObject).setDuration(220L);
    paramContext.addAnimation((android.view.animation.Animation)localObject);
    localObject = new AlphaAnimation(paramFloat3, paramFloat4);
    ((android.view.animation.Animation)localObject).setInterpolator(DECELERATE_CUBIC);
    ((android.view.animation.Animation)localObject).setDuration(220L);
    paramContext.addAnimation((android.view.animation.Animation)localObject);
    return new Animation(paramContext);
  }
  
  static boolean modifiesAlpha(Animation paramAnimation)
  {
    android.view.animation.Animation localAnimation = mView;
    if ((localAnimation instanceof AlphaAnimation)) {
      return true;
    }
    if ((localAnimation instanceof AnimationSet))
    {
      paramAnimation = ((AnimationSet)localAnimation).getAnimations();
      int i = 0;
      while (i < paramAnimation.size())
      {
        if ((paramAnimation.get(i) instanceof AlphaAnimation)) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    return start(mActivity);
  }
  
  private void moveToState()
  {
    Object localObject = mActive;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = ((SparseArray)localObject).size();
    }
    int m = 0;
    while (m < i)
    {
      localObject = (Fragment)mActive.valueAt(m);
      if (localObject != null) {
        if (((Fragment)localObject).getView() != null)
        {
          int n = ((Fragment)localObject).size();
          View localView = ((Fragment)localObject).getView();
          android.view.animation.Animation localAnimation = localView.getAnimation();
          if (localAnimation != null)
          {
            localAnimation.cancel();
            localView.clearAnimation();
          }
          ((Fragment)localObject).moveToState(null);
          moveToState((Fragment)localObject, n, 0, 0, false);
        }
        else if (((Fragment)localObject).getLayoutInflater() != null)
        {
          ((Fragment)localObject).getLayoutInflater().end();
        }
      }
      m += 1;
    }
  }
  
  private void moveToState(int paramInt)
  {
    try
    {
      mExecutingActions = true;
      moveToState(paramInt, false);
      mExecutingActions = false;
      execPendingActions();
      return;
    }
    catch (Throwable localThrowable)
    {
      mExecutingActions = false;
      throw localThrowable;
    }
  }
  
  private void moveToState(Fragment paramFragment, Animation paramAnimation, int paramInt)
  {
    View localView = mView;
    ViewGroup localViewGroup = mContainer;
    localViewGroup.startViewTransition(localView);
    paramFragment.moveToState(paramInt);
    Object localObject = mView;
    if (localObject != null)
    {
      localObject = new Overlay((android.view.animation.Animation)localObject, localViewGroup, localView);
      paramFragment.moveToState(mView);
      ((android.view.animation.Animation)localObject).setAnimationListener(new AppCompatDelegateImplV7.5(this, setHWLayerAnimListenerIfAlpha((android.view.animation.Animation)localObject), localViewGroup, paramFragment));
      moveToState(localView, paramAnimation);
      mView.startAnimation((android.view.animation.Animation)localObject);
      return;
    }
    localObject = mActivity;
    paramFragment.onAttach(mActivity);
    ((Animator)localObject).addListener(new MainActivity.36(this, localViewGroup, localView, paramFragment));
    ((Animator)localObject).setTarget(mView);
    moveToState(mView, paramAnimation);
    ((Animator)localObject).start();
  }
  
  private static void moveToState(View paramView, Animation paramAnimation)
  {
    if (paramView != null)
    {
      if (paramAnimation == null) {
        return;
      }
      if (shouldRunOnHWLayer(paramView, paramAnimation))
      {
        Object localObject = mActivity;
        if (localObject != null)
        {
          ((Animator)localObject).addListener(new MainActivity.29(paramView));
          return;
        }
        localObject = setHWLayerAnimListenerIfAlpha(mView);
        paramView.setLayerType(2, null);
        mView.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView)
        {
          public void onAnimationEnd(android.view.animation.Animation paramAnonymousAnimation)
          {
            if ((!ViewCompat.setText(FragmentManagerImpl.this)) && (Build.VERSION.SDK_INT < 24)) {
              setLayerType(0, null);
            } else {
              post(new l.e.a(this));
            }
            super.onAnimationEnd(paramAnonymousAnimation);
          }
        });
      }
    }
  }
  
  private void onCreateView(boolean paramBoolean)
  {
    if (!mExecutingActions)
    {
      if (mHost != null)
      {
        if (Looper.myLooper() == mHost.getHandler().getLooper())
        {
          if (!paramBoolean) {
            checkStateLoss();
          }
          if (k == null)
          {
            k = new ArrayList();
            a = new ArrayList();
          }
          mExecutingActions = true;
          try
          {
            a(null, null);
            mExecutingActions = false;
            return;
          }
          catch (Throwable localThrowable)
          {
            mExecutingActions = false;
            throw localThrowable;
          }
        }
        throw new IllegalStateException("Must be called from main thread of fragment host");
      }
      throw new IllegalStateException("Fragment host has been destroyed");
    }
    throw new IllegalStateException("FragmentManager is already executing transactions");
  }
  
  private void onDraw(TFloatLinkedList paramTFloatLinkedList)
  {
    int m = paramTFloatLinkedList.size();
    int i = 0;
    while (i < m)
    {
      Fragment localFragment = (Fragment)paramTFloatLinkedList.get(i);
      if (!mAdded)
      {
        View localView = localFragment.getValue();
        x = localView.getAlpha();
        localView.setAlpha(0.0F);
      }
      i += 1;
    }
  }
  
  private static void process(ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList1.get(paramInt1);
      boolean bool2 = ((Boolean)paramArrayList2.get(paramInt1)).booleanValue();
      boolean bool1 = true;
      if (bool2)
      {
        localBackStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1) {
          bool1 = false;
        }
        localBackStackRecord.run(bool1);
      }
      else
      {
        localBackStackRecord.bumpBackStackNesting(1);
        localBackStackRecord.run();
      }
      paramInt1 += 1;
    }
  }
  
  public static int reverseTransit(int paramInt)
  {
    if (paramInt != 4097)
    {
      if (paramInt != 4099)
      {
        if (paramInt != 8194) {
          return 0;
        }
        return 4097;
      }
      return 4099;
    }
    return 8194;
  }
  
  private void run()
  {
    mExecutingActions = false;
    a.clear();
    k.clear();
  }
  
  private void run(ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt1, int paramInt2)
  {
    boolean bool = getc;
    Object localObject = b;
    if (localObject == null) {
      b = new ArrayList();
    } else {
      ((ArrayList)localObject).clear();
    }
    b.addAll(mAdded);
    int n = paramInt1;
    int m = 0;
    localObject = findFragmentById();
    for (;;)
    {
      int i1 = 1;
      if (n >= paramInt2) {
        break;
      }
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList1.get(n);
      if (!((Boolean)paramArrayList2.get(n)).booleanValue()) {
        localObject = localBackStackRecord.a(b, (Fragment)localObject);
      } else {
        localObject = localBackStackRecord.b(b, (Fragment)localObject);
      }
      i = i1;
      if (m == 0) {
        if (mAddToBackStack) {
          i = i1;
        } else {
          i = 0;
        }
      }
      n += 1;
      m = i;
    }
    b.clear();
    if (!bool) {
      f.a(this, paramArrayList1, paramArrayList2, paramInt1, paramInt2, false);
    }
    process(paramArrayList1, paramArrayList2, paramInt1, paramInt2);
    n = paramInt2;
    if (bool)
    {
      localObject = new TFloatLinkedList();
      dump((TFloatLinkedList)localObject);
      n = a(paramArrayList1, paramArrayList2, paramInt1, paramInt2, (TFloatLinkedList)localObject);
      onDraw((TFloatLinkedList)localObject);
    }
    int i = paramInt1;
    if (n != paramInt1)
    {
      i = paramInt1;
      if (bool)
      {
        f.a(this, paramArrayList1, paramArrayList2, paramInt1, n, true);
        moveToState(mCurState, true);
        i = paramInt1;
      }
    }
    while (i < paramInt2)
    {
      localObject = (BackStackRecord)paramArrayList1.get(i);
      if (((Boolean)paramArrayList2.get(i)).booleanValue())
      {
        paramInt1 = mIndex;
        if (paramInt1 >= 0)
        {
          freeBackStackIndex(paramInt1);
          mIndex = -1;
        }
      }
      ((BackStackRecord)localObject).a();
      i += 1;
    }
    if (m != 0) {
      i();
    }
  }
  
  private static Animation.AnimationListener setHWLayerAnimListenerIfAlpha(android.view.animation.Animation paramAnimation)
  {
    if (sAnimationListenerField == null) {}
    try
    {
      Field localField = android.view.animation.Animation.class.getDeclaredField("mListener");
      sAnimationListenerField = localField;
      localField = sAnimationListenerField;
      localField.setAccessible(true);
      localField = sAnimationListenerField;
      paramAnimation = localField.get(paramAnimation);
      return (Animation.AnimationListener)paramAnimation;
    }
    catch (IllegalAccessException paramAnimation)
    {
      Log.e("FragmentManager", "Cannot access Animation's mListener field", paramAnimation);
      return null;
    }
    catch (NoSuchFieldException paramAnimation)
    {
      Log.e("FragmentManager", "No field with the name mListener is found in Animation class", paramAnimation);
    }
    return null;
  }
  
  static boolean shouldRunOnHWLayer(View paramView, Animation paramAnimation)
  {
    if (paramView != null)
    {
      if (paramAnimation == null) {
        return false;
      }
      if ((Build.VERSION.SDK_INT >= 19) && (paramView.getLayerType() == 0) && (ViewCompat.set(paramView)) && (modifiesAlpha(paramAnimation))) {
        return true;
      }
    }
    return false;
  }
  
  static boolean start(Animator paramAnimator)
  {
    if (paramAnimator == null) {
      return false;
    }
    int i;
    if ((paramAnimator instanceof ValueAnimator))
    {
      paramAnimator = ((ValueAnimator)paramAnimator).getValues();
      i = 0;
      while (i < paramAnimator.length)
      {
        if ("alpha".equals(paramAnimator[i].getPropertyName())) {
          return true;
        }
        i += 1;
      }
    }
    else if ((paramAnimator instanceof AnimatorSet))
    {
      paramAnimator = ((AnimatorSet)paramAnimator).getChildAnimations();
      i = 0;
      while (i < paramAnimator.size())
      {
        if (start((Animator)paramAnimator.get(i))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  private void throwException(RuntimeException paramRuntimeException)
  {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
    FragmentHostCallback localFragmentHostCallback = mHost;
    if (localFragmentHostCallback != null) {
      try
      {
        localFragmentHostCallback.onDump("  ", null, localPrintWriter, new String[0]);
      }
      catch (Exception localException1)
      {
        Log.e("FragmentManager", "Failed dumping state", localException1);
      }
    } else {
      try
      {
        dump("  ", null, localException1, new String[0]);
      }
      catch (Exception localException2)
      {
        Log.e("FragmentManager", "Failed dumping state", localException2);
      }
    }
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean)
  {
    if (paramInt != 4097)
    {
      if (paramInt != 4099)
      {
        if (paramInt != 8194) {
          return -1;
        }
        if (paramBoolean) {
          return 3;
        }
        return 4;
      }
      if (paramBoolean) {
        return 5;
      }
      return 6;
    }
    if (paramBoolean) {
      return 1;
    }
    return 2;
  }
  
  void a(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).a(paramFragment, paramContext, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.add(this, paramFragment, paramContext);
      }
    }
  }
  
  void a(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).a(paramFragment, paramBundle, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.add(this, paramFragment, paramBundle);
      }
    }
  }
  
  void a(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).a(paramFragment, paramView, paramBundle, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.add(this, paramFragment, paramView, paramBundle);
      }
    }
  }
  
  void a(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).a(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.f(this, paramFragment);
      }
    }
  }
  
  public void a(Runnable paramRunnable, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (mHost == null) {
        return;
      }
      if (mDestroyed) {
        return;
      }
    }
    onCreateView(paramBoolean);
    if (paramRunnable.run(k, a))
    {
      mExecutingActions = true;
      try
      {
        add(k, a);
        run();
      }
      catch (Throwable paramRunnable)
      {
        run();
        throw paramRunnable;
      }
    }
    performPendingDeferredStart();
    clear();
  }
  
  boolean a(ArrayList paramArrayList1, ArrayList paramArrayList2, String paramString, int paramInt1, int paramInt2)
  {
    Object localObject = mBackStack;
    if (localObject == null) {
      return false;
    }
    if ((paramString == null) && (paramInt1 < 0) && ((paramInt2 & 0x1) == 0))
    {
      paramInt1 = ((ArrayList)localObject).size() - 1;
      if (paramInt1 < 0) {
        return false;
      }
      paramArrayList1.add(mBackStack.remove(paramInt1));
      paramArrayList2.add(Boolean.valueOf(true));
      return true;
    }
    int i = -1;
    if ((paramString != null) || (paramInt1 >= 0))
    {
      int m = mBackStack.size() - 1;
      while (m >= 0)
      {
        localObject = (BackStackRecord)mBackStack.get(m);
        if (((paramString != null) && (paramString.equals(((BackStackRecord)localObject).getName()))) || ((paramInt1 >= 0) && (paramInt1 == mIndex))) {
          break;
        }
        m -= 1;
      }
      if (m < 0) {
        return false;
      }
      i = m;
      if ((paramInt2 & 0x1) != 0)
      {
        paramInt2 = m - 1;
        for (;;)
        {
          i = paramInt2;
          if (paramInt2 < 0) {
            break;
          }
          localObject = (BackStackRecord)mBackStack.get(paramInt2);
          if ((paramString == null) || (!paramString.equals(((BackStackRecord)localObject).getName())))
          {
            i = paramInt2;
            if (paramInt1 < 0) {
              break;
            }
            i = paramInt2;
            if (paramInt1 != mIndex) {
              break;
            }
          }
          paramInt2 -= 1;
        }
      }
    }
    if (i == mBackStack.size() - 1) {
      return false;
    }
    paramInt1 = mBackStack.size() - 1;
    while (paramInt1 > i)
    {
      paramArrayList1.add(mBackStack.remove(paramInt1));
      paramArrayList2.add(Boolean.valueOf(true));
      paramInt1 -= 1;
    }
    return true;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord)
  {
    if (mBackStack == null) {
      mBackStack = new ArrayList();
    }
    mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment)
  {
    if ((paramFragment != null) && ((mActive.get(mIndex) != paramFragment) || ((mHost != null) && (paramFragment.get() != this))))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(paramFragment);
      localStringBuilder.append(" is not an active fragment of FragmentManager ");
      localStringBuilder.append(this);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    mTarget = paramFragment;
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (DEBUG)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("add: ");
      ((StringBuilder)localObject).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)localObject).toString());
    }
    makeActive(paramFragment);
    if (!mDetached)
    {
      if (!mAdded.contains(paramFragment))
      {
        localObject = mAdded;
        try
        {
          mAdded.add(paramFragment);
          mAdded = true;
          mRemoving = false;
          if (mView == null) {
            mNeedMenuInvalidate = false;
          }
          if ((mHasMenu) && (mMenuVisible)) {
            mNeedMenuInvalidate = true;
          }
          if (!paramBoolean) {
            return;
          }
          moveToState(paramFragment);
          return;
        }
        catch (Throwable paramFragment)
        {
          throw paramFragment;
        }
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Fragment already added: ");
      ((StringBuilder)localObject).append(paramFragment);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord)
  {
    try
    {
      StringBuilder localStringBuilder;
      if ((mAvailBackStackIndices != null) && (mAvailBackStackIndices.size() > 0))
      {
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Adding back stack index ");
          localStringBuilder.append(i);
          localStringBuilder.append(" with ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.set(i, paramBackStackRecord);
        return i;
      }
      if (mBackStackIndices == null) {
        mBackStackIndices = new ArrayList();
      }
      int i = mBackStackIndices.size();
      if (DEBUG)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Setting back stack index ");
        localStringBuilder.append(i);
        localStringBuilder.append(" to ");
        localStringBuilder.append(paramBackStackRecord);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
      mBackStackIndices.add(paramBackStackRecord);
      return i;
    }
    catch (Throwable paramBackStackRecord)
    {
      throw paramBackStackRecord;
    }
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment)
  {
    if (mHost == null)
    {
      mHost = paramFragmentHostCallback;
      mContainer = paramFragmentContainer;
      mParent = paramFragment;
      return;
    }
    throw new IllegalStateException("Already attached");
  }
  
  public void attachFragment(Fragment paramFragment)
  {
    Object localObject;
    if (DEBUG)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("attach: ");
      ((StringBuilder)localObject).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)localObject).toString());
    }
    if (mDetached)
    {
      mDetached = false;
      if (!mAdded) {
        if (!mAdded.contains(paramFragment))
        {
          if (DEBUG)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("add from attach: ");
            ((StringBuilder)localObject).append(paramFragment);
            Log.v("FragmentManager", ((StringBuilder)localObject).toString());
          }
          localObject = mAdded;
          try
          {
            mAdded.add(paramFragment);
            mAdded = true;
            if ((!mHasMenu) || (!mMenuVisible)) {
              return;
            }
            mNeedMenuInvalidate = true;
            return;
          }
          catch (Throwable paramFragment)
          {
            throw paramFragment;
          }
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Fragment already added: ");
          ((StringBuilder)localObject).append(paramFragment);
          throw new IllegalStateException(((StringBuilder)localObject).toString());
        }
      }
    }
  }
  
  void b(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).b(paramFragment, paramContext, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.e(this, paramFragment, paramContext);
      }
    }
  }
  
  void b(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).b(paramFragment, paramBundle, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.a(this, paramFragment, paramBundle);
      }
    }
  }
  
  void b(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).b(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.show(this, paramFragment);
      }
    }
  }
  
  public FragmentTransaction beginTransaction()
  {
    return new BackStackRecord(this);
  }
  
  void c(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).c(paramFragment, paramBundle, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.d(this, paramFragment, paramBundle);
      }
    }
  }
  
  void c(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).c(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.e(this, paramFragment);
      }
    }
  }
  
  void d(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).d(paramFragment, paramBundle, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.remove(this, paramFragment, paramBundle);
      }
    }
  }
  
  void d(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).d(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.d(this, paramFragment);
      }
    }
  }
  
  public void detachFragment(Fragment paramFragment)
  {
    Object localObject;
    if (DEBUG)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("detach: ");
      ((StringBuilder)localObject).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)localObject).toString());
    }
    if (!mDetached)
    {
      mDetached = true;
      if (mAdded)
      {
        if (DEBUG)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("remove from detach: ");
          ((StringBuilder)localObject).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)localObject).toString());
        }
        localObject = mAdded;
        try
        {
          mAdded.remove(paramFragment);
          if ((mHasMenu) && (mMenuVisible)) {
            mNeedMenuInvalidate = true;
          }
          mAdded = false;
          return;
        }
        catch (Throwable paramFragment)
        {
          throw paramFragment;
        }
      }
    }
  }
  
  public void dispatchActivityCreated()
  {
    mStateSaved = false;
    mState = false;
    moveToState(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performConfigurationChanged(paramConfiguration);
      }
      i += 1;
    }
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    if (mCurState < 1) {
      return false;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if ((localFragment != null) && (localFragment.performContextItemSelected(paramMenuItem))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void dispatchCreate()
  {
    mStateSaved = false;
    mState = false;
    moveToState(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    if (mCurState < 1) {
      return false;
    }
    boolean bool1 = false;
    Object localObject1 = null;
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      boolean bool2 = bool1;
      Object localObject2 = localObject1;
      if (localFragment != null)
      {
        bool2 = bool1;
        localObject2 = localObject1;
        if (localFragment.performCreateOptionsMenu(paramMenu, paramMenuInflater))
        {
          bool2 = true;
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArrayList();
          }
          ((ArrayList)localObject2).add(localFragment);
        }
      }
      i += 1;
      bool1 = bool2;
      localObject1 = localObject2;
    }
    if (mCreatedMenus != null)
    {
      i = 0;
      while (i < mCreatedMenus.size())
      {
        paramMenu = (Fragment)mCreatedMenus.get(i);
        if ((localObject1 == null) || (!localObject1.contains(paramMenu))) {
          paramMenu.onDestroyOptionsMenu();
        }
        i += 1;
      }
    }
    mCreatedMenus = localObject1;
    return bool1;
  }
  
  public void dispatchDestroy()
  {
    mDestroyed = true;
    execPendingActions();
    moveToState(0);
    mHost = null;
    mContainer = null;
    mParent = null;
  }
  
  public void dispatchDestroyView()
  {
    moveToState(1);
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (mCurState < 1) {
      return false;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if ((localFragment != null) && (localFragment.performOptionsItemSelected(paramMenuItem))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void dispatchPause()
  {
    moveToState(3);
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    if (mCurState < 1) {
      return false;
    }
    boolean bool1 = false;
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      boolean bool2 = bool1;
      if (localFragment != null)
      {
        bool2 = bool1;
        if (localFragment.performPrepareOptionsMenu(paramMenu)) {
          bool2 = true;
        }
      }
      i += 1;
      bool1 = bool2;
    }
    return bool1;
  }
  
  public void dispatchResume()
  {
    mStateSaved = false;
    mState = false;
    moveToState(4);
  }
  
  public void dispatchStart()
  {
    mStateSaved = false;
    mState = false;
    moveToState(3);
  }
  
  public void dispatchStop()
  {
    mState = true;
    moveToState(2);
  }
  
  void draw(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).draw(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.a(this, paramFragment);
      }
    }
  }
  
  public void dump()
  {
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.dump();
      }
      i += 1;
    }
  }
  
  public void dump(Menu paramMenu)
  {
    if (mCurState < 1) {
      return;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.dump(paramMenu);
      }
      i += 1;
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString);
    ((StringBuilder)localObject1).append("    ");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = mActive;
    int i;
    if (localObject2 != null)
    {
      m = ((SparseArray)localObject2).size();
      if (m > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("Active Fragments in ");
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
        paramPrintWriter.println(":");
        i = 0;
        while (i < m)
        {
          localObject2 = (Fragment)mActive.valueAt(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localObject2);
          if (localObject2 != null) {
            ((Fragment)localObject2).dump((String)localObject1, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          }
          i += 1;
        }
      }
    }
    int m = mAdded.size();
    if (m > 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Added Fragments:");
      i = 0;
      while (i < m)
      {
        localObject2 = (Fragment)mAdded.get(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(i);
        paramPrintWriter.print(": ");
        paramPrintWriter.println(((Fragment)localObject2).toString());
        i += 1;
      }
    }
    localObject2 = mCreatedMenus;
    if (localObject2 != null)
    {
      m = ((ArrayList)localObject2).size();
      if (m > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Fragments Created Menus:");
        i = 0;
        while (i < m)
        {
          localObject2 = (Fragment)mCreatedMenus.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject2).toString());
          i += 1;
        }
      }
    }
    localObject2 = mBackStack;
    if (localObject2 != null)
    {
      m = ((ArrayList)localObject2).size();
      if (m > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Back Stack:");
        i = 0;
        while (i < m)
        {
          localObject2 = (BackStackRecord)mBackStack.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((BackStackRecord)localObject2).toString());
          ((BackStackRecord)localObject2).dump((String)localObject1, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          i += 1;
        }
      }
    }
    try
    {
      if (mBackStackIndices != null)
      {
        m = mBackStackIndices.size();
        if (m > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Back Stack Indices:");
          i = 0;
          while (i < m)
          {
            paramFileDescriptor = (BackStackRecord)mBackStackIndices.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      if ((mAvailBackStackIndices != null) && (mAvailBackStackIndices.size() > 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mAvailBackStackIndices: ");
        paramPrintWriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
      }
      paramFileDescriptor = mPendingActions;
      if (paramFileDescriptor != null)
      {
        m = paramFileDescriptor.size();
        if (m > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Pending Actions:");
          i = 0;
          while (i < m)
          {
            paramFileDescriptor = (Runnable)mPendingActions.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("FragmentManager misc state:");
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mHost=");
      paramPrintWriter.println(mHost);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mContainer=");
      paramPrintWriter.println(mContainer);
      if (mParent != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mParent=");
        paramPrintWriter.println(mParent);
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mCurState=");
      paramPrintWriter.print(mCurState);
      paramPrintWriter.print(" mStateSaved=");
      paramPrintWriter.print(mStateSaved);
      paramPrintWriter.print(" mStopped=");
      paramPrintWriter.print(mState);
      paramPrintWriter.print(" mDestroyed=");
      paramPrintWriter.println(mDestroyed);
      if (mNeedMenuInvalidate)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mNeedMenuInvalidate=");
        paramPrintWriter.println(mNeedMenuInvalidate);
      }
      if (mNoTransactionsBecause != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mNoTransactionsBecause=");
        paramPrintWriter.println(mNoTransactionsBecause);
        return;
      }
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void dump(boolean paramBoolean)
  {
    int i = mAdded.size() - 1;
    while (i >= 0)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.dump(paramBoolean);
      }
      i -= 1;
    }
  }
  
  void enqueueAction()
  {
    for (;;)
    {
      int m;
      try
      {
        ArrayList localArrayList = l;
        int n = 0;
        if ((localArrayList == null) || (l.isEmpty())) {
          break label96;
        }
        i = 1;
        m = n;
        if (mPendingActions == null) {
          break label101;
        }
        m = n;
        if (mPendingActions.size() != 1) {
          break label101;
        }
        m = 1;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      mHost.getHandler().removeCallbacks(mExecCommit);
      mHost.getHandler().post(mExecCommit);
      return;
      label96:
      int i = 0;
      continue;
      label101:
      if (i == 0) {
        if (m == 0) {}
      }
    }
  }
  
  public void enqueueAction(Runnable paramRunnable, boolean paramBoolean)
  {
    if (!paramBoolean) {
      checkStateLoss();
    }
    try
    {
      if ((!mDestroyed) && (mHost != null))
      {
        if (mPendingActions == null) {
          mPendingActions = new ArrayList();
        }
        mPendingActions.add(paramRunnable);
        enqueueAction();
        return;
      }
      if (paramBoolean) {
        return;
      }
      throw new IllegalStateException("Activity has been destroyed");
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  public boolean execPendingActions()
  {
    onCreateView(true);
    boolean bool = false;
    while (execPendingActions(k, a))
    {
      mExecutingActions = true;
      try
      {
        add(k, a);
        run();
        bool = true;
      }
      catch (Throwable localThrowable)
      {
        run();
        throw localThrowable;
      }
    }
    performPendingDeferredStart();
    clear();
    return bool;
  }
  
  void f(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).f(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.remove(this, paramFragment);
      }
    }
  }
  
  public Fragment findFragmentById()
  {
    return mTarget;
  }
  
  public Fragment findFragmentById(int paramInt)
  {
    int i = mAdded.size() - 1;
    while (i >= 0)
    {
      localObject = (Fragment)mAdded.get(i);
      if ((localObject != null) && (mFragmentId == paramInt)) {
        return localObject;
      }
      i -= 1;
    }
    Object localObject = mActive;
    if (localObject != null)
    {
      i = ((SparseArray)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mActive.valueAt(i);
        if ((localObject != null) && (mFragmentId == paramInt)) {
          return localObject;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString)
  {
    int i;
    if (paramString != null)
    {
      i = mAdded.size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mAdded.get(i);
        if ((localObject != null) && (paramString.equals(mTag))) {
          return localObject;
        }
        i -= 1;
      }
    }
    Object localObject = mActive;
    if ((localObject != null) && (paramString != null))
    {
      i = ((SparseArray)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mActive.valueAt(i);
        if ((localObject != null) && (paramString.equals(mTag))) {
          return localObject;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    Object localObject = mActive;
    if ((localObject != null) && (paramString != null))
    {
      int i = ((SparseArray)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)mActive.valueAt(i);
        if (localObject != null)
        {
          localObject = ((Fragment)localObject).findFragmentByWho(paramString);
          if (localObject != null) {
            return localObject;
          }
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public void freeBackStackIndex(int paramInt)
  {
    try
    {
      mBackStackIndices.set(paramInt, null);
      if (mAvailBackStackIndices == null) {
        mAvailBackStackIndices = new ArrayList();
      }
      if (DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Freeing back stack index ");
        localStringBuilder.append(paramInt);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
      mAvailBackStackIndices.add(Integer.valueOf(paramInt));
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1) {
      return null;
    }
    paramBundle = (Fragment)mActive.get(i);
    if (paramBundle != null) {
      return paramBundle;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("Fragment no longer exists for key ");
    paramBundle.append(paramString);
    paramBundle.append(": index ");
    paramBundle.append(i);
    throwException(new IllegalStateException(paramBundle.toString()));
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public boolean getFragment()
  {
    return (mStateSaved) || (mState);
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory()
  {
    return this;
  }
  
  public void hideFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("hide: ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    if (!mHidden)
    {
      mHidden = true;
      mNeedMenuInvalidate = (true ^ mNeedMenuInvalidate);
    }
  }
  
  void i()
  {
    if (j != null)
    {
      int i = 0;
      while (i < j.size())
      {
        ((m)j.get(i)).i();
        i += 1;
      }
    }
  }
  
  Animation loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    int n = paramFragment.getId();
    android.view.animation.Animation localAnimation = paramFragment.loadAnimation(paramInt1, paramBoolean, n);
    if (localAnimation != null) {
      return new Animation(localAnimation);
    }
    paramFragment = paramFragment.toString(paramInt1, paramBoolean, n);
    if (paramFragment != null) {
      return new Animation(paramFragment);
    }
    if (n != 0)
    {
      boolean bool = "anim".equals(mHost.getContext().getResources().getResourceTypeName(n));
      int m = 0;
      int i = m;
      if (bool)
      {
        paramFragment = mHost;
        try
        {
          paramFragment = AnimationUtils.loadAnimation(paramFragment.getContext(), n);
          if (paramFragment != null)
          {
            paramFragment = new Animation(paramFragment);
            return paramFragment;
          }
          i = 1;
        }
        catch (RuntimeException paramFragment)
        {
          i = m;
        }
        catch (Resources.NotFoundException paramFragment)
        {
          throw paramFragment;
        }
      }
      if (i == 0) {
        try
        {
          paramFragment = AnimatorInflater.loadAnimator(mHost.getContext(), n);
          if (paramFragment != null)
          {
            paramFragment = new Animation(paramFragment);
            return paramFragment;
          }
        }
        catch (RuntimeException paramFragment)
        {
          if (!bool)
          {
            paramFragment = AnimationUtils.loadAnimation(mHost.getContext(), n);
            if (paramFragment != null) {
              return new Animation(paramFragment);
            }
          }
          else
          {
            throw paramFragment;
          }
        }
      }
    }
    if (paramInt1 == 0) {
      return null;
    }
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0) {
      return null;
    }
    switch (paramInt1)
    {
    default: 
      paramInt1 = paramInt2;
      if (paramInt2 == 0)
      {
        paramInt1 = paramInt2;
        if (mHost.onHasWindowAnimations()) {
          paramInt1 = mHost.onGetWindowAnimations();
        }
      }
      break;
    case 6: 
      return makeFadeAnimation(mHost.getContext(), 1.0F, 0.0F);
    case 5: 
      return makeFadeAnimation(mHost.getContext(), 0.0F, 1.0F);
    case 4: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
    case 3: 
      return makeOpenCloseAnimation(mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
    case 2: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
    case 1: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
    }
    if (paramInt1 == 0) {}
    return null;
  }
  
  void makeActive(Fragment paramFragment)
  {
    if (mIndex >= 0) {
      return;
    }
    int i = mIndex;
    mIndex = (i + 1);
    paramFragment.setIndex(i, mParent);
    if (mActive == null) {
      mActive = new SparseArray();
    }
    mActive.put(mIndex, paramFragment);
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Allocated fragment index ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
  }
  
  void makeInactive(Fragment paramFragment)
  {
    if (mIndex < 0) {
      return;
    }
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Freeing fragment index ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    mActive.put(mIndex, null);
    paramFragment.initState();
  }
  
  void moveToState(int paramInt, boolean paramBoolean)
  {
    if ((mHost == null) && (paramInt != 0)) {
      throw new IllegalStateException("No activity");
    }
    if ((!paramBoolean) && (paramInt == mCurState)) {
      return;
    }
    mCurState = paramInt;
    if (mActive != null)
    {
      int i = mAdded.size();
      paramInt = 0;
      while (paramInt < i)
      {
        run((Fragment)mAdded.get(paramInt));
        paramInt += 1;
      }
      i = mActive.size();
      paramInt = 0;
      Object localObject;
      while (paramInt < i)
      {
        localObject = (Fragment)mActive.valueAt(paramInt);
        if ((localObject != null) && ((mRemoving) || (mDetached)) && (!mActive)) {
          run((Fragment)localObject);
        }
        paramInt += 1;
      }
      startPendingDeferredFragments();
      if (mNeedMenuInvalidate)
      {
        localObject = mHost;
        if ((localObject != null) && (mCurState == 4))
        {
          ((FragmentHostCallback)localObject).onSupportInvalidateOptionsMenu();
          mNeedMenuInvalidate = false;
        }
      }
    }
  }
  
  void moveToState(Fragment paramFragment)
  {
    moveToState(paramFragment, mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    boolean bool2 = mAdded;
    boolean bool1 = true;
    int i;
    if ((bool2) && (!mDetached))
    {
      i = paramInt1;
    }
    else
    {
      i = paramInt1;
      if (paramInt1 > 1) {
        i = 1;
      }
    }
    int m = i;
    if (mRemoving)
    {
      paramInt1 = mState;
      m = i;
      if (i > paramInt1) {
        if ((paramInt1 == 0) && (paramFragment.isInBackStack())) {
          m = 1;
        } else {
          m = mState;
        }
      }
    }
    paramInt1 = m;
    if (mDeferStart)
    {
      paramInt1 = m;
      if (mState < 3)
      {
        paramInt1 = m;
        if (m > 2) {
          paramInt1 = 2;
        }
      }
    }
    m = mState;
    Object localObject3;
    Object localObject2;
    if (m <= paramInt1)
    {
      if ((mFromLayout) && (!mInLayout)) {
        return;
      }
      if ((paramFragment.getView() != null) || (paramFragment.getLayoutInflater() != null))
      {
        paramFragment.moveToState(null);
        paramFragment.onAttach(null);
        moveToState(paramFragment, paramFragment.size(), 0, 0, true);
      }
      i = mState;
      Object localObject1;
      if (i != 0)
      {
        paramInt2 = paramInt1;
        if (i != 1)
        {
          paramInt3 = paramInt1;
          if (i == 2) {
            break label1261;
          }
          paramInt2 = paramInt1;
          if (i == 3) {
            break label1326;
          }
          break label1398;
        }
      }
      else
      {
        paramInt2 = paramInt1;
        if (paramInt1 > 0)
        {
          if (DEBUG)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("moveto CREATED: ");
            ((StringBuilder)localObject1).append(paramFragment);
            Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
          }
          localObject1 = mSavedFragmentState;
          paramInt2 = paramInt1;
          if (localObject1 != null)
          {
            ((Bundle)localObject1).setClassLoader(mHost.getContext().getClassLoader());
            mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
            mTarget = getFragment(mSavedFragmentState, "android:target_state");
            if (mTarget != null) {
              mTargetRequestCode = mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            localObject1 = mContainerId;
            if (localObject1 != null)
            {
              mUserVisibleHint = ((Boolean)localObject1).booleanValue();
              mContainerId = null;
            }
            else
            {
              mUserVisibleHint = mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            }
            paramInt2 = paramInt1;
            if (!mUserVisibleHint)
            {
              mDeferStart = true;
              paramInt2 = paramInt1;
              if (paramInt1 > 2) {
                paramInt2 = 2;
              }
            }
          }
          localObject1 = mHost;
          mHost = ((FragmentHostCallback)localObject1);
          localObject3 = mParent;
          mParentFragment = ((Fragment)localObject3);
          if (localObject3 != null) {
            localObject1 = mChildFragmentManager;
          } else {
            localObject1 = ((FragmentHostCallback)localObject1).getFragmentManagerImpl();
          }
          mFragmentManager = ((FragmentManagerImpl)localObject1);
          localObject1 = mTarget;
          if (localObject1 != null)
          {
            localObject1 = mActive.get(mIndex);
            localObject3 = mTarget;
            if (localObject1 == localObject3)
            {
              if (mState < 1) {
                moveToState((Fragment)localObject3, 1, 0, 0, true);
              }
            }
            else
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("Fragment ");
              ((StringBuilder)localObject1).append(paramFragment);
              ((StringBuilder)localObject1).append(" declared target fragment ");
              ((StringBuilder)localObject1).append(mTarget);
              ((StringBuilder)localObject1).append(" that does not belong to this FragmentManager!");
              throw new IllegalStateException(((StringBuilder)localObject1).toString());
            }
          }
          a(paramFragment, mHost.getContext(), false);
          mCalled = false;
          paramFragment.onAttach(mHost.getContext());
          if (mCalled)
          {
            localObject1 = mParentFragment;
            if (localObject1 == null) {
              mHost.onStartActivityFromFragment(paramFragment);
            } else {
              ((Fragment)localObject1).onPause(paramFragment);
            }
            b(paramFragment, mHost.getContext(), false);
            if (!mLoadersStarted)
            {
              b(paramFragment, mSavedFragmentState, false);
              paramFragment.performCreate(mSavedFragmentState);
              a(paramFragment, mSavedFragmentState, false);
            }
            else
            {
              paramFragment.onAttach(mSavedFragmentState);
              mState = 1;
            }
            mRetaining = false;
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Fragment ");
            ((StringBuilder)localObject1).append(paramFragment);
            ((StringBuilder)localObject1).append(" did not call through to super.onAttach()");
            throw new SuperNotCalledException(((StringBuilder)localObject1).toString());
          }
        }
      }
      onCreateView(paramFragment);
      paramInt3 = paramInt2;
      if (paramInt2 > 1)
      {
        if (DEBUG)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("moveto ACTIVITY_CREATED: ");
          ((StringBuilder)localObject1).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
        }
        if (!mFromLayout)
        {
          localObject1 = null;
          paramInt1 = i;
          if (paramInt1 != 0) {
            if (paramInt1 != -1)
            {
              localObject3 = (ViewGroup)mContainer.findViewById(paramInt1);
              localObject1 = localObject3;
              if (localObject3 == null) {
                if (mRestored)
                {
                  localObject1 = localObject3;
                }
                else
                {
                  try
                  {
                    localObject1 = paramFragment.getResources();
                    paramInt1 = i;
                    localObject1 = ((Resources)localObject1).getResourceName(paramInt1);
                  }
                  catch (Resources.NotFoundException localNotFoundException)
                  {
                    localObject2 = "unknown";
                  }
                  localObject3 = new StringBuilder();
                  ((StringBuilder)localObject3).append("No view found for id 0x");
                  ((StringBuilder)localObject3).append(Integer.toHexString(i));
                  ((StringBuilder)localObject3).append(" (");
                  ((StringBuilder)localObject3).append((String)localObject2);
                  ((StringBuilder)localObject3).append(") for fragment ");
                  ((StringBuilder)localObject3).append(paramFragment);
                  throwException(new IllegalArgumentException(((StringBuilder)localObject3).toString()));
                  throw new NullPointerException("Null throw statement replaced by Soot");
                }
              }
            }
            else
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Cannot create fragment ");
              ((StringBuilder)localObject2).append(paramFragment);
              ((StringBuilder)localObject2).append(" for a container view with no id");
              throwException(new IllegalArgumentException(((StringBuilder)localObject2).toString()));
              throw new NullPointerException("Null throw statement replaced by Soot");
            }
          }
          mContainer = ((ViewGroup)localObject2);
          paramFragment.onCreate(paramFragment.instantiate(mSavedFragmentState), (ViewGroup)localObject2, mSavedFragmentState);
          localObject3 = mView;
          if (localObject3 != null)
          {
            mInnerView = ((View)localObject3);
            ((View)localObject3).setSaveFromParentEnabled(false);
            if (localObject2 != null) {
              ((ViewGroup)localObject2).addView(mView);
            }
            if (mHidden) {
              mView.setVisibility(8);
            }
            paramFragment.onCreateView(mView, mSavedFragmentState);
            a(paramFragment, mView, mSavedFragmentState, false);
            if ((mView.getVisibility() == 0) && (mContainer != null)) {
              paramBoolean = bool1;
            } else {
              paramBoolean = false;
            }
            mActive = paramBoolean;
          }
          else
          {
            mInnerView = null;
          }
        }
        paramFragment.performActivityCreated(mSavedFragmentState);
        d(paramFragment, mSavedFragmentState, false);
        if (mView != null) {
          paramFragment.restoreViewState(mSavedFragmentState);
        }
        mSavedFragmentState = null;
        paramInt3 = paramInt2;
      }
      label1261:
      paramInt2 = paramInt3;
      if (paramInt3 > 2)
      {
        if (DEBUG)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("moveto STARTED: ");
          ((StringBuilder)localObject2).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
        }
        paramFragment.performStart();
        c(paramFragment, false);
        paramInt2 = paramInt3;
      }
      label1326:
      paramInt1 = paramInt2;
      if (paramInt2 > 3)
      {
        if (DEBUG)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("moveto RESUMED: ");
          ((StringBuilder)localObject2).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
        }
        paramFragment.performResume();
        d(paramFragment, false);
        mSavedFragmentState = null;
        mSavedViewState = null;
        paramInt1 = paramInt2;
      }
      label1398:
      i = paramInt1;
    }
    else
    {
      i = paramInt1;
      if (m > paramInt1)
      {
        if (m != 1)
        {
          if (m != 2)
          {
            if (m != 3)
            {
              if (m != 4)
              {
                i = paramInt1;
                break label2036;
              }
              if (paramInt1 < 4)
              {
                if (DEBUG)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append("movefrom RESUMED: ");
                  ((StringBuilder)localObject2).append(paramFragment);
                  Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
                }
                paramFragment.performPause();
                draw(paramFragment, false);
              }
            }
            if (paramInt1 < 3)
            {
              if (DEBUG)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append("movefrom STARTED: ");
                ((StringBuilder)localObject2).append(paramFragment);
                Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
              }
              paramFragment.performStop();
              a(paramFragment, false);
            }
          }
          if (paramInt1 < 2)
          {
            if (DEBUG)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("movefrom ACTIVITY_CREATED: ");
              ((StringBuilder)localObject2).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
            }
            if ((mView != null) && (mHost.onShouldSaveFragmentState(paramFragment)) && (mSavedViewState == null)) {
              saveFragmentViewState(paramFragment);
            }
            paramFragment.performDestroyView();
            b(paramFragment, false);
            localObject2 = mView;
            if (localObject2 != null)
            {
              localObject3 = mContainer;
              if (localObject3 != null)
              {
                ((ViewGroup)localObject3).endViewTransition((View)localObject2);
                mView.clearAnimation();
                localObject3 = null;
                localObject2 = localObject3;
                if (mCurState > 0)
                {
                  localObject2 = localObject3;
                  if (!mDestroyed) {
                    if ((mView.getVisibility() == 0) && (x >= 0.0F)) {
                      localObject2 = loadAnimation(paramFragment, paramInt2, false, paramInt3);
                    } else {
                      localObject2 = localObject3;
                    }
                  }
                }
                x = 0.0F;
                if (localObject2 != null) {
                  moveToState(paramFragment, (Animation)localObject2, paramInt1);
                }
                mContainer.removeView(mView);
              }
            }
            mContainer = null;
            mView = null;
            b = null;
            g.a(null);
            mInnerView = null;
            mInLayout = false;
          }
          else {}
        }
        i = paramInt1;
        if (paramInt1 < 1)
        {
          if (mDestroyed) {
            if (paramFragment.getView() != null)
            {
              localObject2 = paramFragment.getView();
              paramFragment.moveToState(null);
              ((View)localObject2).clearAnimation();
            }
            else if (paramFragment.getLayoutInflater() != null)
            {
              localObject2 = paramFragment.getLayoutInflater();
              paramFragment.onAttach(null);
              ((Animator)localObject2).cancel();
            }
          }
          if ((paramFragment.getView() == null) && (paramFragment.getLayoutInflater() == null))
          {
            if (DEBUG)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("movefrom CREATED: ");
              ((StringBuilder)localObject2).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
            }
            if (!mRetaining)
            {
              paramFragment.performDestroy();
              write(paramFragment, false);
            }
            else
            {
              mState = 0;
            }
            paramFragment.moveToState();
            f(paramFragment, false);
            i = paramInt1;
            if (!paramBoolean) {
              if (!mRetaining)
              {
                makeInactive(paramFragment);
                i = paramInt1;
              }
              else
              {
                mHost = null;
                mParentFragment = null;
                mFragmentManager = null;
                i = paramInt1;
              }
            }
          }
          else
          {
            paramFragment.moveToState(paramInt1);
            i = 1;
          }
        }
      }
    }
    label2036:
    if (mState != i)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("moveToState: Fragment state for ");
      ((StringBuilder)localObject2).append(paramFragment);
      ((StringBuilder)localObject2).append(" not updated inline; ");
      ((StringBuilder)localObject2).append("expected state ");
      ((StringBuilder)localObject2).append(i);
      ((StringBuilder)localObject2).append(" found ");
      ((StringBuilder)localObject2).append(mState);
      Log.w("FragmentManager", ((StringBuilder)localObject2).toString());
      mState = i;
    }
  }
  
  public void moveToState(boolean paramBoolean)
  {
    int i = mAdded.size() - 1;
    while (i >= 0)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.moveToState(paramBoolean);
      }
      i -= 1;
    }
  }
  
  void onCreate(Fragment paramFragment)
  {
    if (mView != null)
    {
      Animation localAnimation = loadAnimation(paramFragment, paramFragment.getIcon(), mHidden ^ true, paramFragment.i());
      if (localAnimation != null)
      {
        Object localObject = mActivity;
        if (localObject != null)
        {
          ((Animator)localObject).setTarget(mView);
          if (mHidden)
          {
            if (paramFragment.isEnabled())
            {
              paramFragment.a(false);
            }
            else
            {
              localObject = mContainer;
              View localView = mView;
              ((ViewGroup)localObject).startViewTransition(localView);
              mActivity.addListener(new TwoCardOverlayAnimation.2(this, (ViewGroup)localObject, localView, paramFragment));
            }
          }
          else {
            mView.setVisibility(0);
          }
          moveToState(mView, localAnimation);
          mActivity.start();
          break label213;
        }
      }
      if (localAnimation != null)
      {
        moveToState(mView, localAnimation);
        mView.startAnimation(mView);
        mView.start();
      }
      int i;
      if ((mHidden) && (!paramFragment.isEnabled())) {
        i = 8;
      } else {
        i = 0;
      }
      mView.setVisibility(i);
      if (paramFragment.isEnabled()) {
        paramFragment.a(false);
      }
    }
    label213:
    if ((mAdded) && (mHasMenu) && (mMenuVisible)) {
      mNeedMenuInvalidate = true;
    }
    mNeedMenuInvalidate = false;
    paramFragment.setHasOptionsMenu(mHidden);
  }
  
  List onCreateView()
  {
    dump(mView);
    return mView;
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if (!"fragment".equals(paramString)) {
      return null;
    }
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    int i = 0;
    String str1 = paramString;
    if (paramString == null) {
      str1 = localTypedArray.getString(0);
    }
    int n = localTypedArray.getResourceId(1, -1);
    String str2 = localTypedArray.getString(2);
    localTypedArray.recycle();
    if (!Fragment.isSupportFragmentClass(mHost.getContext(), str1)) {
      return null;
    }
    if (paramView != null) {
      i = paramView.getId();
    }
    if ((i == -1) && (n == -1) && (str2 == null))
    {
      paramView = new StringBuilder();
      paramView.append(paramAttributeSet.getPositionDescription());
      paramView.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
      paramView.append(str1);
      throw new IllegalArgumentException(paramView.toString());
    }
    if (n != -1) {
      paramView = findFragmentById(n);
    } else {
      paramView = null;
    }
    paramString = paramView;
    if (paramView == null)
    {
      paramString = paramView;
      if (str2 != null) {
        paramString = findFragmentByTag(str2);
      }
    }
    paramView = paramString;
    if (paramString == null)
    {
      paramView = paramString;
      if (i != -1) {
        paramView = findFragmentById(i);
      }
    }
    if (DEBUG)
    {
      paramString = new StringBuilder();
      paramString.append("onCreateView: id=0x");
      paramString.append(Integer.toHexString(n));
      paramString.append(" fname=");
      paramString.append(str1);
      paramString.append(" existing=");
      paramString.append(paramView);
      Log.v("FragmentManager", paramString.toString());
    }
    if (paramView == null)
    {
      paramString = mContainer.instantiate(paramContext, str1, null);
      mFromLayout = true;
      int m;
      if (n != 0) {
        m = n;
      } else {
        m = i;
      }
      mFragmentId = m;
      i = i;
      mTag = str2;
      mInLayout = true;
      mFragmentManager = this;
      paramView = mHost;
      mHost = paramView;
      paramString.onInflate(paramView.getContext(), paramAttributeSet, mSavedFragmentState);
      addFragment(paramString, true);
    }
    else
    {
      if (mInLayout) {
        break label567;
      }
      mInLayout = true;
      paramContext = mHost;
      mHost = paramContext;
      paramString = paramView;
      if (!mRetaining)
      {
        paramView.onInflate(paramContext.getContext(), paramAttributeSet, mSavedFragmentState);
        paramString = paramView;
      }
    }
    if ((mCurState < 1) && (mFromLayout)) {
      moveToState(paramString, 1, 0, 0, false);
    } else {
      moveToState(paramString);
    }
    paramView = mView;
    if (paramView != null)
    {
      if (n != 0) {
        paramView.setId(n);
      }
      if (mView.getTag() == null) {
        mView.setTag(str2);
      }
      return mView;
    }
    paramView = new StringBuilder();
    paramView.append("Fragment ");
    paramView.append(str1);
    paramView.append(" did not create a view.");
    throw new IllegalStateException(paramView.toString());
    label567:
    paramView = new StringBuilder();
    paramView.append(paramAttributeSet.getPositionDescription());
    paramView.append(": Duplicate id 0x");
    paramView.append(Integer.toHexString(n));
    paramView.append(", tag ");
    paramView.append(str2);
    paramView.append(", or parent id 0x");
    paramView.append(Integer.toHexString(i));
    paramView.append(" with another fragment for ");
    paramView.append(str1);
    throw new IllegalArgumentException(paramView.toString());
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  void onCreateView(Fragment paramFragment)
  {
    if ((mFromLayout) && (!l))
    {
      paramFragment.onCreate(paramFragment.instantiate(mSavedFragmentState), null, mSavedFragmentState);
      View localView = mView;
      if (localView != null)
      {
        mInnerView = localView;
        localView.setSaveFromParentEnabled(false);
        if (mHidden) {
          mView.setVisibility(8);
        }
        paramFragment.onCreateView(mView, mSavedFragmentState);
        a(paramFragment, mView, mSavedFragmentState, false);
        return;
      }
      mInnerView = null;
    }
  }
  
  void performPendingDeferredStart()
  {
    if (mHavePendingDeferredStart)
    {
      mHavePendingDeferredStart = false;
      startPendingDeferredFragments();
    }
  }
  
  public void performPendingDeferredStart(Fragment paramFragment)
  {
    if (mDeferStart)
    {
      if (mExecutingActions)
      {
        mHavePendingDeferredStart = true;
        return;
      }
      mDeferStart = false;
      moveToState(paramFragment, mCurState, 0, 0, false);
    }
  }
  
  public void popBackStack(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      enqueueAction(new Runnable()
      {
        final int d;
        final String q;
        final int x;
        
        public boolean run(ArrayList paramAnonymousArrayList1, ArrayList paramAnonymousArrayList2)
        {
          Object localObject = mTarget;
          if ((localObject != null) && (x < 0) && (q == null))
          {
            localObject = ((Fragment)localObject).getChildAt();
            if ((localObject != null) && (((FragmentManager)localObject).popBackStackImmediate())) {
              return false;
            }
          }
          return a(paramAnonymousArrayList1, paramAnonymousArrayList2, q, x, d);
        }
      }, false);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Bad id: ");
    localStringBuilder.append(paramInt1);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public boolean popBackStackImmediate()
  {
    checkStateLoss();
    return a(null, -1, 0);
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    int i = mIndex;
    if (i >= 0)
    {
      paramBundle.putInt(paramString, i);
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("Fragment ");
    paramBundle.append(paramFragment);
    paramBundle.append(" is not currently in the FragmentManager");
    throwException(new IllegalStateException(paramBundle.toString()));
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public java.util.List remove()
  {
    if (mAdded.isEmpty()) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = mAdded;
    try
    {
      java.util.List localList = (java.util.List)mAdded.clone();
      return localList;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void removeFragment(Fragment paramFragment)
  {
    Object localObject;
    if (DEBUG)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("remove: ");
      ((StringBuilder)localObject).append(paramFragment);
      ((StringBuilder)localObject).append(" nesting=");
      ((StringBuilder)localObject).append(mBackStackNesting);
      Log.v("FragmentManager", ((StringBuilder)localObject).toString());
    }
    boolean bool = paramFragment.isInBackStack();
    if ((!mDetached) || ((bool ^ true)))
    {
      localObject = mAdded;
      try
      {
        mAdded.remove(paramFragment);
        if ((mHasMenu) && (mMenuVisible)) {
          mNeedMenuInvalidate = true;
        }
        mAdded = false;
        mRemoving = true;
        return;
      }
      catch (Throwable paramFragment)
      {
        throw paramFragment;
      }
    }
  }
  
  public void restoreAllState()
  {
    mView = null;
    mStateSaved = false;
    mState = false;
    int m = mAdded.size();
    int i = 0;
    while (i < m)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performCreate();
      }
      i += 1;
    }
  }
  
  void restoreAllState(Parcelable paramParcelable, List paramList)
  {
    if (paramParcelable == null) {
      return;
    }
    FragmentManagerState localFragmentManagerState = (FragmentManagerState)paramParcelable;
    if (mActive == null) {
      return;
    }
    Object localObject3;
    Object localObject1;
    int m;
    Object localObject2;
    Object localObject4;
    int n;
    if (paramList != null)
    {
      localObject3 = paramList.get();
      localObject1 = paramList.append();
      paramParcelable = paramList.getStart();
      if (localObject3 != null) {
        i = ((java.util.List)localObject3).size();
      } else {
        i = 0;
      }
      m = 0;
      while (m < i)
      {
        localObject2 = (Fragment)((java.util.List)localObject3).get(m);
        if (DEBUG)
        {
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append("restoreAllState: re-attaching retained ");
          ((StringBuilder)localObject4).append(localObject2);
          Log.v("FragmentManager", ((StringBuilder)localObject4).toString());
        }
        n = 0;
        for (;;)
        {
          localObject4 = mActive;
          if ((n >= localObject4.length) || (mIndex == mIndex)) {
            break;
          }
          n += 1;
        }
        localObject4 = mActive;
        if (n != localObject4.length)
        {
          localObject4 = localObject4[n];
          mInstance = ((Fragment)localObject2);
          mSavedViewState = null;
          mBackStackNesting = 0;
          mInLayout = false;
          mAdded = false;
          mTarget = null;
          Bundle localBundle = mSavedFragmentState;
          if (localBundle != null)
          {
            localBundle.setClassLoader(mHost.getContext().getClassLoader());
            mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
            mSavedFragmentState = mSavedFragmentState;
          }
          m += 1;
        }
        else
        {
          paramParcelable = new StringBuilder();
          paramParcelable.append("Could not find active fragment with index ");
          paramParcelable.append(mIndex);
          throwException(new IllegalStateException(paramParcelable.toString()));
          throw new NullPointerException("Null throw statement replaced by Soot");
        }
      }
    }
    else
    {
      paramParcelable = null;
      localObject1 = null;
    }
    mActive = new SparseArray(mActive.length);
    int i = 0;
    for (;;)
    {
      localObject2 = mActive;
      if (i >= localObject2.length) {
        break;
      }
      localObject4 = localObject2[i];
      if (localObject4 != null)
      {
        localObject3 = null;
        localObject2 = localObject3;
        if (localObject1 != null)
        {
          localObject2 = localObject3;
          if (i < ((java.util.List)localObject1).size()) {
            localObject2 = (List)((java.util.List)localObject1).get(i);
          }
        }
        if ((paramParcelable != null) && (i < paramParcelable.size())) {
          localObject3 = (Attribute)paramParcelable.get(i);
        } else {
          localObject3 = null;
        }
        localObject2 = ((FragmentState)localObject4).instantiate(mHost, mContainer, mParent, (List)localObject2, (Attribute)localObject3);
        if (DEBUG)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("restoreAllState: active #");
          ((StringBuilder)localObject3).append(i);
          ((StringBuilder)localObject3).append(": ");
          ((StringBuilder)localObject3).append(localObject2);
          Log.v("FragmentManager", ((StringBuilder)localObject3).toString());
        }
        mActive.put(mIndex, localObject2);
        mInstance = null;
      }
      i += 1;
    }
    if (paramList != null)
    {
      paramParcelable = paramList.get();
      if (paramParcelable != null) {
        i = paramParcelable.size();
      } else {
        i = 0;
      }
      m = 0;
      while (m < i)
      {
        paramList = (Fragment)paramParcelable.get(m);
        n = mTargetIndex;
        if (n >= 0)
        {
          mTarget = ((Fragment)mActive.get(n));
          if (mTarget == null)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Re-attaching retained fragment ");
            ((StringBuilder)localObject1).append(paramList);
            ((StringBuilder)localObject1).append(" target no longer exists: ");
            ((StringBuilder)localObject1).append(mTargetIndex);
            Log.w("FragmentManager", ((StringBuilder)localObject1).toString());
          }
        }
        m += 1;
      }
    }
    mAdded.clear();
    if (mAdded != null)
    {
      i = 0;
      for (;;)
      {
        paramParcelable = mAdded;
        if (i >= paramParcelable.length) {
          break label932;
        }
        paramParcelable = (Fragment)mActive.get(paramParcelable[i]);
        if (paramParcelable == null) {
          break label878;
        }
        mAdded = true;
        if (DEBUG)
        {
          paramList = new StringBuilder();
          paramList.append("restoreAllState: added #");
          paramList.append(i);
          paramList.append(": ");
          paramList.append(paramParcelable);
          Log.v("FragmentManager", paramList.toString());
        }
        if (!mAdded.contains(paramParcelable))
        {
          paramList = mAdded;
          try
          {
            mAdded.add(paramParcelable);
            i += 1;
          }
          catch (Throwable paramParcelable)
          {
            throw paramParcelable;
          }
        }
      }
      throw new IllegalStateException("Already added!");
      label878:
      paramParcelable = new StringBuilder();
      paramParcelable.append("No instantiated fragment for index #");
      paramParcelable.append(mAdded[i]);
      throwException(new IllegalStateException(paramParcelable.toString()));
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    label932:
    paramParcelable = mBackStack;
    if (paramParcelable != null)
    {
      mBackStack = new ArrayList(paramParcelable.length);
      i = 0;
      for (;;)
      {
        paramParcelable = mBackStack;
        if (i >= paramParcelable.length) {
          break;
        }
        paramParcelable = paramParcelable[i].instantiate(this);
        if (DEBUG)
        {
          paramList = new StringBuilder();
          paramList.append("restoreAllState: back stack #");
          paramList.append(i);
          paramList.append(" (index ");
          paramList.append(mIndex);
          paramList.append("): ");
          paramList.append(paramParcelable);
          Log.v("FragmentManager", paramList.toString());
          paramList = new PrintWriter(new LogWriter("FragmentManager"));
          paramParcelable.dump("  ", paramList, false);
          paramList.close();
        }
        mBackStack.add(paramParcelable);
        m = mIndex;
        if (m >= 0) {
          setBackStackIndex(m, paramParcelable);
        }
        i += 1;
      }
    }
    else
    {
      mBackStack = null;
    }
    i = mIndex;
    if (i >= 0) {
      mTarget = ((Fragment)mActive.get(i));
    }
    mIndex = mFragmentId;
  }
  
  void retainNonConfig()
  {
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject5 = null;
    Object localObject3 = null;
    Object localObject6 = null;
    Object localObject2 = null;
    if (mActive != null)
    {
      int i = 0;
      for (;;)
      {
        localObject4 = localObject1;
        localObject5 = localObject3;
        localObject6 = localObject2;
        if (i >= mActive.size()) {
          break;
        }
        Fragment localFragment = (Fragment)mActive.valueAt(i);
        localObject5 = localObject1;
        localObject6 = localObject3;
        Object localObject7 = localObject2;
        if (localFragment != null)
        {
          localObject4 = localObject1;
          int m;
          if (mRetainInstance)
          {
            localObject5 = localObject1;
            if (localObject1 == null) {
              localObject5 = new ArrayList();
            }
            ((ArrayList)localObject5).add(localFragment);
            localObject1 = mTarget;
            if (localObject1 != null) {
              m = mIndex;
            } else {
              m = -1;
            }
            mTargetIndex = m;
            localObject4 = localObject5;
            if (DEBUG)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("retainNonConfig: keeping retained ");
              ((StringBuilder)localObject1).append(localFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
              localObject4 = localObject5;
            }
          }
          localObject1 = mChildFragmentManager;
          if (localObject1 != null)
          {
            ((FragmentManagerImpl)localObject1).retainNonConfig();
            localObject5 = mChildFragmentManager.mView;
          }
          else
          {
            localObject5 = mActivity;
          }
          localObject1 = localObject3;
          if (localObject3 == null)
          {
            localObject1 = localObject3;
            if (localObject5 != null)
            {
              localObject6 = new ArrayList(mActive.size());
              localObject3 = localObject6;
              m = 0;
              for (;;)
              {
                localObject1 = localObject3;
                if (m >= i) {
                  break;
                }
                ((ArrayList)localObject6).add(null);
                m += 1;
              }
            }
          }
          if (localObject1 != null) {
            ((ArrayList)localObject1).add(localObject5);
          }
          localObject3 = localObject2;
          if (localObject2 == null)
          {
            localObject3 = localObject2;
            if (DEBUG != null)
            {
              localObject5 = new ArrayList(mActive.size());
              localObject2 = localObject5;
              m = 0;
              for (;;)
              {
                localObject3 = localObject2;
                if (m >= i) {
                  break;
                }
                ((ArrayList)localObject5).add(null);
                m += 1;
              }
            }
          }
          localObject5 = localObject4;
          localObject6 = localObject1;
          localObject7 = localObject3;
          if (localObject3 != null)
          {
            localObject3.add(DEBUG);
            localObject7 = localObject3;
            localObject6 = localObject1;
            localObject5 = localObject4;
          }
        }
        i += 1;
        localObject1 = localObject5;
        localObject3 = localObject6;
        localObject2 = localObject7;
      }
    }
    if ((localObject4 == null) && (localObject5 == null) && (localObject6 == null))
    {
      mView = null;
      return;
    }
    mView = new List(localObject4, (java.util.List)localObject5, (java.util.List)localObject6);
  }
  
  void run(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean1) {
      paramBackStackRecord.run(paramBoolean3);
    } else {
      paramBackStackRecord.run();
    }
    Object localObject = new ArrayList(1);
    ArrayList localArrayList = new ArrayList(1);
    ((ArrayList)localObject).add(paramBackStackRecord);
    localArrayList.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2) {
      f.a(this, (ArrayList)localObject, localArrayList, 0, 1, true);
    }
    if (paramBoolean3) {
      moveToState(mCurState, true);
    }
    localObject = mActive;
    if (localObject != null)
    {
      int m = ((SparseArray)localObject).size();
      int i = 0;
      while (i < m)
      {
        localObject = (Fragment)mActive.valueAt(i);
        if ((localObject != null) && (mView != null) && (mActive) && (paramBackStackRecord.a(i)))
        {
          float f = x;
          if (f > 0.0F) {
            mView.setAlpha(f);
          }
          if (paramBoolean3)
          {
            x = 0.0F;
          }
          else
          {
            x = -1.0F;
            mActive = false;
          }
        }
        i += 1;
      }
    }
  }
  
  void run(Fragment paramFragment)
  {
    if (paramFragment == null) {
      return;
    }
    int m = mCurState;
    int i = m;
    if (mRemoving) {
      if (paramFragment.isInBackStack()) {
        i = Math.min(m, 1);
      } else {
        i = Math.min(m, 0);
      }
    }
    moveToState(paramFragment, i, paramFragment.getIcon(), paramFragment.i(), false);
    if (mView != null)
    {
      Object localObject1 = dump(paramFragment);
      Object localObject2;
      if (localObject1 != null)
      {
        localObject1 = mView;
        localObject2 = mContainer;
        i = ((ViewGroup)localObject2).indexOfChild((View)localObject1);
        m = ((ViewGroup)localObject2).indexOfChild(mView);
        if (m < i)
        {
          ((ViewGroup)localObject2).removeViewAt(m);
          ((ViewGroup)localObject2).addView(mView, i);
        }
      }
      if ((mActive) && (mContainer != null))
      {
        float f = x;
        if (f > 0.0F) {
          mView.setAlpha(f);
        }
        x = 0.0F;
        mActive = false;
        localObject1 = loadAnimation(paramFragment, paramFragment.getIcon(), true, paramFragment.i());
        if (localObject1 != null)
        {
          moveToState(mView, (Animation)localObject1);
          localObject2 = mView;
          if (localObject2 != null)
          {
            mView.startAnimation((android.view.animation.Animation)localObject2);
          }
          else
          {
            mActivity.setTarget(mView);
            mActivity.start();
          }
        }
      }
    }
    if (mNeedMenuInvalidate) {
      onCreate(paramFragment);
    }
  }
  
  Parcelable saveAllState()
  {
    a();
    moveToState();
    execPendingActions();
    mStateSaved = true;
    mView = null;
    Object localObject1 = mActive;
    if (localObject1 != null)
    {
      if (((SparseArray)localObject1).size() <= 0) {
        return null;
      }
      int i1 = mActive.size();
      FragmentState[] arrayOfFragmentState = new FragmentState[i1];
      int m = 0;
      int i = 0;
      Object localObject2;
      Object localObject3;
      while (i < i1)
      {
        localObject1 = (Fragment)mActive.valueAt(i);
        if (localObject1 != null) {
          if (mIndex >= 0)
          {
            int n = 1;
            localObject2 = new FragmentState((Fragment)localObject1);
            arrayOfFragmentState[i] = localObject2;
            if ((mState > 0) && (mSavedFragmentState == null))
            {
              mSavedFragmentState = saveFragmentBasicState((Fragment)localObject1);
              localObject3 = mTarget;
              if (localObject3 != null) {
                if (mIndex >= 0)
                {
                  if (mSavedFragmentState == null) {
                    mSavedFragmentState = new Bundle();
                  }
                  putFragment(mSavedFragmentState, "android:target_state", mTarget);
                  m = mTargetRequestCode;
                  if (m != 0) {
                    mSavedFragmentState.putInt("android:target_req_state", m);
                  }
                }
                else
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append("Failure saving state: ");
                  ((StringBuilder)localObject2).append(localObject1);
                  ((StringBuilder)localObject2).append(" has target not in fragment manager: ");
                  ((StringBuilder)localObject2).append(mTarget);
                  throwException(new IllegalStateException(((StringBuilder)localObject2).toString()));
                  throw new NullPointerException("Null throw statement replaced by Soot");
                }
              }
            }
            else
            {
              mSavedFragmentState = mSavedFragmentState;
            }
            m = n;
            if (DEBUG)
            {
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append("Saved state of ");
              ((StringBuilder)localObject3).append(localObject1);
              ((StringBuilder)localObject3).append(": ");
              ((StringBuilder)localObject3).append(mSavedFragmentState);
              Log.v("FragmentManager", ((StringBuilder)localObject3).toString());
              m = n;
            }
          }
          else
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Failure saving state: active ");
            ((StringBuilder)localObject2).append(localObject1);
            ((StringBuilder)localObject2).append(" has cleared index: ");
            ((StringBuilder)localObject2).append(mIndex);
            throwException(new IllegalStateException(((StringBuilder)localObject2).toString()));
            throw new NullPointerException("Null throw statement replaced by Soot");
          }
        }
        i += 1;
      }
      if (m == 0)
      {
        if (DEBUG)
        {
          Log.v("FragmentManager", "saveAllState: no fragments!");
          return null;
        }
      }
      else
      {
        localObject1 = null;
        localObject3 = null;
        m = mAdded.size();
        if (m > 0)
        {
          localObject2 = new int[m];
          i = 0;
          for (;;)
          {
            localObject1 = localObject2;
            if (i >= m) {
              break label690;
            }
            localObject2[i] = mAdded.get(i)).mIndex;
            if (localObject2[i] < 0) {
              break;
            }
            if (DEBUG)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("saveAllState: adding fragment #");
              ((StringBuilder)localObject1).append(i);
              ((StringBuilder)localObject1).append(": ");
              ((StringBuilder)localObject1).append(mAdded.get(i));
              Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
            }
            i += 1;
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Failure saving state: active ");
          ((StringBuilder)localObject1).append(mAdded.get(i));
          ((StringBuilder)localObject1).append(" has cleared index: ");
          ((StringBuilder)localObject1).append(localObject2[i]);
          throwException(new IllegalStateException(((StringBuilder)localObject1).toString()));
          throw new NullPointerException("Null throw statement replaced by Soot");
        }
        label690:
        ArrayList localArrayList = mBackStack;
        localObject2 = localObject3;
        if (localArrayList != null)
        {
          m = localArrayList.size();
          localObject2 = localObject3;
          if (m > 0)
          {
            localObject3 = new BackStackState[m];
            i = 0;
            for (;;)
            {
              localObject2 = localObject3;
              if (i >= m) {
                break;
              }
              localObject3[i] = new BackStackState((BackStackRecord)mBackStack.get(i));
              if (DEBUG)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append("saveAllState: adding back stack #");
                ((StringBuilder)localObject2).append(i);
                ((StringBuilder)localObject2).append(": ");
                ((StringBuilder)localObject2).append(mBackStack.get(i));
                Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
              }
              i += 1;
            }
          }
        }
        localObject3 = new FragmentManagerState();
        mActive = arrayOfFragmentState;
        mAdded = ((int[])localObject1);
        mBackStack = ((BackStackState[])localObject2);
        localObject1 = mTarget;
        if (localObject1 != null) {
          mIndex = mIndex;
        }
        mFragmentId = mIndex;
        retainNonConfig();
        return localObject3;
      }
    }
    return null;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (mStateBundle == null) {
      mStateBundle = new Bundle();
    }
    paramFragment.performSaveInstanceState(mStateBundle);
    c(paramFragment, mStateBundle, false);
    if (!mStateBundle.isEmpty())
    {
      localObject2 = mStateBundle;
      mStateBundle = null;
    }
    if (mView != null) {
      saveFragmentViewState(paramFragment);
    }
    Object localObject1 = localObject2;
    if (mSavedViewState != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Bundle();
      }
      ((Bundle)localObject1).putSparseParcelableArray("android:view_state", mSavedViewState);
    }
    localObject2 = localObject1;
    if (!mUserVisibleHint)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new Bundle();
      }
      ((Bundle)localObject2).putBoolean("android:user_visible_hint", mUserVisibleHint);
    }
    return localObject2;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment)
  {
    if (mIndex >= 0)
    {
      if (mState > 0)
      {
        paramFragment = saveFragmentBasicState(paramFragment);
        if (paramFragment != null) {
          return new Fragment.SavedState(paramFragment);
        }
      }
      else
      {
        return null;
      }
    }
    else
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(paramFragment);
      localStringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(localStringBuilder.toString()));
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    return null;
  }
  
  void saveFragmentViewState(Fragment paramFragment)
  {
    if (mInnerView == null) {
      return;
    }
    SparseArray localSparseArray = mStateArray;
    if (localSparseArray == null) {
      mStateArray = new SparseArray();
    } else {
      localSparseArray.clear();
    }
    mInnerView.saveHierarchyState(mStateArray);
    if (mStateArray.size() > 0)
    {
      mSavedViewState = mStateArray;
      mStateArray = null;
    }
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord)
  {
    try
    {
      if (mBackStackIndices == null) {
        mBackStackIndices = new ArrayList();
      }
      int m = mBackStackIndices.size();
      int i = m;
      StringBuilder localStringBuilder;
      if (paramInt < m)
      {
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Setting back stack index ");
          localStringBuilder.append(paramInt);
          localStringBuilder.append(" to ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.set(paramInt, paramBackStackRecord);
      }
      else
      {
        while (i < paramInt)
        {
          mBackStackIndices.add(null);
          if (mAvailBackStackIndices == null) {
            mAvailBackStackIndices = new ArrayList();
          }
          if (DEBUG)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Adding available back stack index ");
            localStringBuilder.append(i);
            Log.v("FragmentManager", localStringBuilder.toString());
          }
          mAvailBackStackIndices.add(Integer.valueOf(i));
          i += 1;
        }
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Adding back stack index ");
          localStringBuilder.append(paramInt);
          localStringBuilder.append(" with ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.add(paramBackStackRecord);
      }
      return;
    }
    catch (Throwable paramBackStackRecord)
    {
      throw paramBackStackRecord;
    }
  }
  
  public void showFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("show: ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    if (mHidden)
    {
      mHidden = false;
      mNeedMenuInvalidate ^= true;
    }
  }
  
  void startPendingDeferredFragments()
  {
    if (mActive == null) {
      return;
    }
    int i = 0;
    while (i < mActive.size())
    {
      Fragment localFragment = (Fragment)mActive.valueAt(i);
      if (localFragment != null) {
        performPendingDeferredStart(localFragment);
      }
      i += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("FragmentManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    Fragment localFragment = mParent;
    if (localFragment != null) {
      DebugUtils.buildShortClassTag(localFragment, localStringBuilder);
    } else {
      DebugUtils.buildShortClassTag(mHost, localStringBuilder);
    }
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }
  
  boolean update(int paramInt)
  {
    return mCurState >= paramInt;
  }
  
  void write(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject = mParent;
    if (localObject != null)
    {
      localObject = ((Fragment)localObject).get();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).write(paramFragment, true);
      }
    }
    localObject = c.iterator();
    while (((Iterator)localObject).hasNext())
    {
      p localP = (p)((Iterator)localObject).next();
      if ((!paramBoolean) || (b)) {
        a.add(this, paramFragment);
      }
    }
  }
  
  class AnimateOnHWLayerIfNeededListener
    implements Animation.AnimationListener
  {
    AnimateOnHWLayerIfNeededListener() {}
    
    public void onAnimationEnd(android.view.animation.Animation paramAnimation)
    {
      Animation.AnimationListener localAnimationListener = FragmentManagerImpl.this;
      if (localAnimationListener != null) {
        localAnimationListener.onAnimationEnd(paramAnimation);
      }
    }
    
    public void onAnimationRepeat(android.view.animation.Animation paramAnimation)
    {
      Animation.AnimationListener localAnimationListener = FragmentManagerImpl.this;
      if (localAnimationListener != null) {
        localAnimationListener.onAnimationRepeat(paramAnimation);
      }
    }
    
    public void onAnimationStart(android.view.animation.Animation paramAnimation)
    {
      Animation.AnimationListener localAnimationListener = FragmentManagerImpl.this;
      if (localAnimationListener != null) {
        localAnimationListener.onAnimationStart(paramAnimation);
      }
    }
  }
  
  class FragmentTag
  {
    public static final int[] Fragment = { 16842755, 16842960, 16842961 };
  }
}
