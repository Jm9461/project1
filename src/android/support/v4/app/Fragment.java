package android.support.v4.app;

import a.b.g.g.n;
import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.Attribute;
import android.arch.lifecycle.Item;
import android.arch.lifecycle.Update;
import android.arch.lifecycle.c;
import android.arch.lifecycle.e;
import android.arch.lifecycle.h;
import android.arch.lifecycle.j;
import android.arch.lifecycle.m;
import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.LayoutInflaterCompatHC;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.org.jaxen.util.DebugUtils;

public class Fragment
  implements ComponentCallbacks, View.OnCreateContextMenuListener, h, Update
{
  private static final n<String, Class<?>> sClassMap = new org.org.jaxen.util.f();
  static final Object y = new Object();
  Attribute DEBUG;
  d a;
  h b;
  m e;
  j<e> g = new Item();
  int i;
  boolean l;
  boolean mActive;
  List mActivity;
  boolean mAdded;
  Bundle mArguments;
  int mBackStackNesting;
  boolean mCalled;
  FragmentManagerImpl mChildFragmentManager;
  ViewGroup mContainer;
  Boolean mContainerId;
  boolean mDeferStart;
  boolean mDetached;
  int mFragmentId;
  FragmentManagerImpl mFragmentManager;
  boolean mFromLayout;
  boolean mHasMenu;
  boolean mHidden;
  FragmentHostCallback mHost;
  boolean mInLayout;
  int mIndex = -1;
  View mInnerView;
  LayoutInflater mInstance;
  boolean mLoadersStarted;
  boolean mMenuVisible = true;
  boolean mNeedMenuInvalidate;
  Fragment mParentFragment;
  boolean mRemoving;
  boolean mRestored;
  boolean mRetainInstance;
  boolean mRetaining;
  Bundle mSavedFragmentState;
  SparseArray<Parcelable> mSavedViewState;
  int mState = 0;
  String mTag;
  Fragment mTarget;
  int mTargetIndex = -1;
  int mTargetRequestCode;
  boolean mUserVisibleHint = true;
  View mView;
  String mWho;
  m o = new m(this);
  float x;
  
  public Fragment() {}
  
  public static Fragment instantiate(android.content.Context paramContext, String paramString, Bundle paramBundle)
  {
    Object localObject1 = sClassMap;
    try
    {
      localObject1 = ((org.org.jaxen.util.f)localObject1).get(paramString);
      Object localObject2 = (Class)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = paramContext.getClassLoader().loadClass(paramString);
        paramContext = (android.content.Context)localObject1;
        localObject2 = sClassMap;
        ((org.org.jaxen.util.f)localObject2).put(paramString, localObject1);
        localObject1 = paramContext;
      }
      paramContext = ((Class)localObject1).getConstructor(new Class[0]);
      paramContext = paramContext.newInstance(new Object[0]);
      paramContext = (Fragment)paramContext;
      if (paramBundle != null)
      {
        paramBundle.setClassLoader(paramContext.getClass().getClassLoader());
        paramContext.setArguments(paramBundle);
        return paramContext;
      }
    }
    catch (InvocationTargetException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": calling Fragment constructor caused an exception");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": could not find Fragment constructor");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (InstantiationException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (ClassNotFoundException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    return paramContext;
  }
  
  static boolean isSupportFragmentClass(android.content.Context paramContext, String paramString)
  {
    Object localObject1 = sClassMap;
    try
    {
      localObject1 = ((org.org.jaxen.util.f)localObject1).get(paramString);
      Object localObject2 = (Class)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = paramContext.getClassLoader().loadClass(paramString);
        paramContext = (android.content.Context)localObject1;
        localObject2 = sClassMap;
        ((org.org.jaxen.util.f)localObject2).put(paramString, localObject1);
        localObject1 = paramContext;
      }
      boolean bool = f.class.isAssignableFrom((Class)localObject1);
      return bool;
    }
    catch (ClassNotFoundException paramContext) {}
    return false;
  }
  
  private d onCreateView()
  {
    if (a == null) {
      a = new d();
    }
    return a;
  }
  
  public android.arch.lifecycle.d a()
  {
    return o;
  }
  
  void a(int paramInt)
  {
    if ((a == null) && (paramInt == 0)) {
      return;
    }
    onCreateViewg = paramInt;
  }
  
  void a(int paramInt1, int paramInt2)
  {
    if ((a == null) && (paramInt1 == 0) && (paramInt2 == 0)) {
      return;
    }
    onCreateView();
    d localD = a;
    m = paramInt1;
    l = paramInt2;
  }
  
  void a(l paramL)
  {
    onCreateView();
    Object localObject = a.A;
    if (paramL == localObject) {
      return;
    }
    if ((paramL != null) && (localObject != null))
    {
      paramL = new StringBuilder();
      paramL.append("Trying to set a replacement startPostponedEnterTransition on ");
      paramL.append(this);
      throw new IllegalStateException(paramL.toString());
    }
    localObject = a;
    if (B) {
      A = paramL;
    }
    if (paramL != null) {
      paramL.b();
    }
  }
  
  void a(boolean paramBoolean)
  {
    onCreateViewp = paramBoolean;
  }
  
  public Attribute add()
  {
    if (getContext() != null)
    {
      if (DEBUG == null) {
        DEBUG = new Attribute();
      }
      return DEBUG;
    }
    throw new IllegalStateException("Can't access ViewModels from detached fragment");
  }
  
  public Object b()
  {
    Object localObject = a;
    if (localObject == null) {
      return null;
    }
    localObject = e;
    if (localObject == y) {
      return getString();
    }
    return localObject;
  }
  
  void d()
  {
    d localD = a;
    l localL;
    if (localD == null)
    {
      localL = null;
    }
    else
    {
      B = false;
      localL = A;
      A = null;
    }
    if (localL != null) {
      localL.a();
    }
  }
  
  void dump()
  {
    onLowMemory();
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.dump();
    }
  }
  
  void dump(Menu paramMenu)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible)) {
        onCreateOptionsMenu(paramMenu);
      }
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      if (localFragmentManagerImpl != null) {
        localFragmentManagerImpl.dump(paramMenu);
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(i));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(mUserVisibleHint);
    if (mFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(mFragmentManager);
    }
    if (mHost != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(mHost);
    }
    if (mParentFragment != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(mParentFragment);
    }
    if (mArguments != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(mArguments);
    }
    if (mSavedFragmentState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(mSavedFragmentState);
    }
    if (mSavedViewState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(mSavedViewState);
    }
    if (mTarget != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(mTargetRequestCode);
    }
    if (getId() != 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getId());
    }
    if (mContainer != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(mContainer);
    }
    if (mView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(mView);
    }
    if (mInnerView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(mView);
    }
    if (getView() != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getView());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(size());
    }
    if (getContext() != null) {
      Node.append(this).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    if (mChildFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Child ");
      ((StringBuilder)localObject).append(mChildFragmentManager);
      ((StringBuilder)localObject).append(":");
      paramPrintWriter.println(((StringBuilder)localObject).toString());
      localObject = mChildFragmentManager;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("  ");
      ((FragmentManagerImpl)localObject).dump(localStringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  void dump(boolean paramBoolean)
  {
    setIndex(paramBoolean);
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.dump(paramBoolean);
    }
  }
  
  Context e()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return j;
  }
  
  public void equals(Intent paramIntent, int paramInt)
  {
    startActivityForResult(paramIntent, paramInt, null);
  }
  
  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
  
  public void execPendingActions()
  {
    FragmentManagerImpl localFragmentManagerImpl = mFragmentManager;
    if ((localFragmentManagerImpl != null) && (mHost != null))
    {
      if (Looper.myLooper() != mFragmentManager.mHost.getHandler().getLooper())
      {
        mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new AgendaListView.2(this));
        return;
      }
      d();
      return;
    }
    onCreateViewB = false;
  }
  
  Context f()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return i;
  }
  
  Fragment findFragmentByWho(String paramString)
  {
    if (paramString.equals(mWho)) {
      return this;
    }
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      return localFragmentManagerImpl.findFragmentByWho(paramString);
    }
    return null;
  }
  
  public Object findItem()
  {
    Object localObject = a;
    if (localObject == null) {
      return null;
    }
    localObject = y;
    if (localObject == y) {
      return getItemId();
    }
    return localObject;
  }
  
  public final FragmentManager get()
  {
    return mFragmentManager;
  }
  
  public final FragmentActivity getActivity()
  {
    FragmentHostCallback localFragmentHostCallback = mHost;
    if (localFragmentHostCallback == null) {
      return null;
    }
    return (FragmentActivity)localFragmentHostCallback.getActivity();
  }
  
  public final Bundle getArguments()
  {
    return mArguments;
  }
  
  FragmentManager getChildAt()
  {
    return mChildFragmentManager;
  }
  
  public final FragmentManager getChildFragmentManager()
  {
    if (mChildFragmentManager == null)
    {
      instantiateChildFragmentManager();
      int j = mState;
      if (j >= 4) {
        mChildFragmentManager.dispatchResume();
      } else if (j >= 3) {
        mChildFragmentManager.dispatchStart();
      } else if (j >= 2) {
        mChildFragmentManager.dispatchActivityCreated();
      } else if (j >= 1) {
        mChildFragmentManager.dispatchCreate();
      }
    }
    return mChildFragmentManager;
  }
  
  public android.content.Context getContext()
  {
    FragmentHostCallback localFragmentHostCallback = mHost;
    if (localFragmentHostCallback == null) {
      return null;
    }
    return localFragmentHostCallback.getContext();
  }
  
  int getIcon()
  {
    d localD = a;
    if (localD == null) {
      return 0;
    }
    return m;
  }
  
  int getId()
  {
    d localD = a;
    if (localD == null) {
      return 0;
    }
    return g;
  }
  
  public final android.content.Context getInstance()
  {
    Object localObject = getContext();
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" not attached to a context.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public Object getItem()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return a;
  }
  
  public Object getItemId()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return d;
  }
  
  Animator getLayoutInflater()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return mInflater;
  }
  
  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    return onCreateView(paramBundle);
  }
  
  public final Fragment getParentFragment()
  {
    return mParentFragment;
  }
  
  public final Resources getResources()
  {
    return getInstance().getResources();
  }
  
  public Object getString()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return s;
  }
  
  public View getValue()
  {
    return mView;
  }
  
  View getView()
  {
    d localD = a;
    if (localD == null) {
      return null;
    }
    return mView;
  }
  
  public final int hashCode()
  {
    return super.hashCode();
  }
  
  int i()
  {
    d localD = a;
    if (localD == null) {
      return 0;
    }
    return l;
  }
  
  void initState()
  {
    mIndex = -1;
    mWho = null;
    mAdded = false;
    mRemoving = false;
    mFromLayout = false;
    mInLayout = false;
    mRestored = false;
    mBackStackNesting = 0;
    mFragmentManager = null;
    mChildFragmentManager = null;
    mHost = null;
    mFragmentId = 0;
    i = 0;
    mTag = null;
    mHidden = false;
    mDetached = false;
    mRetaining = false;
  }
  
  LayoutInflater instantiate(Bundle paramBundle)
  {
    mInstance = getLayoutInflater(paramBundle);
    return mInstance;
  }
  
  void instantiateChildFragmentManager()
  {
    if (mHost != null)
    {
      mChildFragmentManager = new FragmentManagerImpl();
      mChildFragmentManager.attachController(mHost, new Fragment.1(this), this);
      return;
    }
    throw new IllegalStateException("Fragment has not been attached yet.");
  }
  
  public final boolean isAdded()
  {
    return (mHost != null) && (mAdded);
  }
  
  boolean isEnabled()
  {
    d localD = a;
    if (localD == null) {
      return false;
    }
    return p;
  }
  
  public final boolean isHidden()
  {
    return mHidden;
  }
  
  final boolean isInBackStack()
  {
    return mBackStackNesting > 0;
  }
  
  boolean isVisible()
  {
    d localD = a;
    if (localD == null) {
      return false;
    }
    return B;
  }
  
  public Animation loadAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }
  
  void moveToState()
  {
    mCalled = false;
    onDetach();
    mInstance = null;
    Object localObject;
    if (mCalled)
    {
      localObject = mChildFragmentManager;
      if (localObject != null)
      {
        if (mRetaining)
        {
          ((FragmentManagerImpl)localObject).dispatchDestroy();
          mChildFragmentManager = null;
          return;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Child FragmentManager of ");
        ((StringBuilder)localObject).append(this);
        ((StringBuilder)localObject).append(" was not ");
        ((StringBuilder)localObject).append(" destroyed and this fragment is not retaining instance");
        throw new IllegalStateException(((StringBuilder)localObject).toString());
      }
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Fragment ");
      ((StringBuilder)localObject).append(this);
      ((StringBuilder)localObject).append(" did not call through to super.onDetach()");
      throw new SuperNotCalledException(((StringBuilder)localObject).toString());
    }
  }
  
  void moveToState(int paramInt)
  {
    onCreateViewN = paramInt;
  }
  
  void moveToState(View paramView)
  {
    onCreateViewmView = paramView;
  }
  
  void moveToState(boolean paramBoolean)
  {
    onPause(paramBoolean);
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.moveToState(paramBoolean);
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public final boolean onActivityResult()
  {
    FragmentManagerImpl localFragmentManagerImpl = mFragmentManager;
    if (localFragmentManagerImpl == null) {
      return false;
    }
    return localFragmentManagerImpl.getFragment();
  }
  
  void onAttach(Animator paramAnimator)
  {
    onCreateViewmInflater = paramAnimator;
  }
  
  public void onAttach(Activity paramActivity)
  {
    mCalled = true;
  }
  
  public void onAttach(android.content.Context paramContext)
  {
    mCalled = true;
    paramContext = mHost;
    if (paramContext == null) {
      paramContext = null;
    } else {
      paramContext = paramContext.getActivity();
    }
    if (paramContext != null)
    {
      mCalled = false;
      onAttach(paramContext);
    }
  }
  
  void onAttach(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getParcelable("android:support:fragments");
      if (paramBundle != null)
      {
        if (mChildFragmentManager == null) {
          instantiateChildFragmentManager();
        }
        mChildFragmentManager.restoreAllState(paramBundle, mActivity);
        mActivity = null;
        mChildFragmentManager.dispatchCreate();
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    mCalled = true;
    onAttach(paramBundle);
    paramBundle = mChildFragmentManager;
    if ((paramBundle != null) && (!paramBundle.update(1))) {
      mChildFragmentManager.dispatchCreate();
    }
  }
  
  void onCreate(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.restoreAllState();
    }
    l = true;
    b = new Label(this);
    e = null;
    mView = onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (mView != null)
    {
      b.a();
      g.a(b);
      return;
    }
    if (e == null)
    {
      b = null;
      return;
    }
    throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu) {}
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public LayoutInflater onCreateView(Bundle paramBundle)
  {
    paramBundle = mHost;
    if (paramBundle != null)
    {
      paramBundle = paramBundle.onGetLayoutInflater();
      getChildFragmentManager();
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      localFragmentManagerImpl.getLayoutInflaterFactory();
      LayoutInflaterCompatHC.setFactory(paramBundle, localFragmentManagerImpl);
      return paramBundle;
    }
    throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return null;
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onCreateView(View paramView, Bundle paramBundle) {}
  
  public void onDestroy()
  {
    int j = 1;
    mCalled = true;
    Object localObject = getActivity();
    if ((localObject == null) || (!((Activity)localObject).isChangingConfigurations())) {
      j = 0;
    }
    localObject = DEBUG;
    if ((localObject != null) && (j == 0)) {
      ((Attribute)localObject).a();
    }
  }
  
  public void onDestroyOptionsMenu() {}
  
  public void onDestroyView()
  {
    mCalled = true;
  }
  
  public void onDetach()
  {
    mCalled = true;
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public void onInflate(android.content.Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mCalled = true;
    paramContext = mHost;
    if (paramContext == null) {
      paramContext = null;
    } else {
      paramContext = paramContext.getActivity();
    }
    if (paramContext != null)
    {
      mCalled = false;
      onInflate(paramContext, paramAttributeSet, paramBundle);
    }
  }
  
  public void onLowMemory()
  {
    mCalled = true;
  }
  
  public boolean onMenuItemClick()
  {
    Object localObject = a;
    if (localObject != null)
    {
      localObject = b;
      if (localObject != null) {
        return ((Boolean)localObject).booleanValue();
      }
    }
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onPause()
  {
    mCalled = true;
  }
  
  public void onPause(Fragment paramFragment) {}
  
  public void onPause(boolean paramBoolean) {}
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {}
  
  public void onResume()
  {
    mCalled = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart()
  {
    mCalled = true;
  }
  
  public void onStop()
  {
    mCalled = true;
  }
  
  public void onViewStateRestored(Bundle paramBundle)
  {
    mCalled = true;
  }
  
  void performActivityCreated(Bundle paramBundle)
  {
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.restoreAllState();
    }
    mState = 2;
    mCalled = false;
    onActivityCreated(paramBundle);
    if (mCalled)
    {
      paramBundle = mChildFragmentManager;
      if (paramBundle != null) {
        paramBundle.dispatchActivityCreated();
      }
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Fragment ");
      paramBundle.append(this);
      paramBundle.append(" did not call through to super.onActivityCreated()");
      throw new SuperNotCalledException(paramBundle.toString());
    }
  }
  
  void performConfigurationChanged(Configuration paramConfiguration)
  {
    onConfigurationChanged(paramConfiguration);
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.dispatchConfigurationChanged(paramConfiguration);
    }
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if (onContextItemSelected(paramMenuItem)) {
        return true;
      }
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      if ((localFragmentManagerImpl != null) && (localFragmentManagerImpl.dispatchContextItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performCreate()
  {
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.restoreAllState();
    }
  }
  
  void performCreate(Bundle paramBundle)
  {
    FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
    if (localFragmentManagerImpl != null) {
      localFragmentManagerImpl.restoreAllState();
    }
    mState = 1;
    mCalled = false;
    onCreate(paramBundle);
    mLoadersStarted = true;
    if (mCalled)
    {
      o.toString(c.ON_CREATE);
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("Fragment ");
    paramBundle.append(this);
    paramBundle.append(" did not call through to super.onCreate()");
    throw new SuperNotCalledException(paramBundle.toString());
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!mHidden)
    {
      bool1 = bool2;
      if (mHasMenu)
      {
        bool1 = bool2;
        if (mMenuVisible)
        {
          bool1 = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        }
      }
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      if (localFragmentManagerImpl != null) {
        return bool1 | localFragmentManagerImpl.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
      }
    }
    else
    {
      return false;
    }
    return bool1;
  }
  
  void performDestroy()
  {
    o.toString(c.ON_DESTROY);
    Object localObject = mChildFragmentManager;
    if (localObject != null) {
      ((FragmentManagerImpl)localObject).dispatchDestroy();
    }
    mState = 0;
    mCalled = false;
    mLoadersStarted = false;
    onDestroy();
    if (mCalled)
    {
      mChildFragmentManager = null;
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" did not call through to super.onDestroy()");
    throw new SuperNotCalledException(((StringBuilder)localObject).toString());
  }
  
  void performDestroyView()
  {
    if (mView != null) {
      e.toString(c.ON_DESTROY);
    }
    Object localObject = mChildFragmentManager;
    if (localObject != null) {
      ((FragmentManagerImpl)localObject).dispatchDestroyView();
    }
    mState = 1;
    mCalled = false;
    onDestroyView();
    if (mCalled)
    {
      Node.append(this).dump();
      l = false;
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" did not call through to super.onDestroyView()");
    throw new SuperNotCalledException(((StringBuilder)localObject).toString());
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible) && (onOptionsItemSelected(paramMenuItem))) {
        return true;
      }
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      if ((localFragmentManagerImpl != null) && (localFragmentManagerImpl.dispatchOptionsItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performPause()
  {
    if (mView != null) {
      e.toString(c.ON_PAUSE);
    }
    o.toString(c.ON_PAUSE);
    Object localObject = mChildFragmentManager;
    if (localObject != null) {
      ((FragmentManagerImpl)localObject).dispatchPause();
    }
    mState = 3;
    mCalled = false;
    onPause();
    if (mCalled) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" did not call through to super.onPause()");
    throw new SuperNotCalledException(((StringBuilder)localObject).toString());
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!mHidden)
    {
      bool1 = bool2;
      if (mHasMenu)
      {
        bool1 = bool2;
        if (mMenuVisible)
        {
          bool1 = true;
          onPrepareOptionsMenu(paramMenu);
        }
      }
      FragmentManagerImpl localFragmentManagerImpl = mChildFragmentManager;
      if (localFragmentManagerImpl != null) {
        return bool1 | localFragmentManagerImpl.dispatchPrepareOptionsMenu(paramMenu);
      }
    }
    else
    {
      return false;
    }
    return bool1;
  }
  
  void performResume()
  {
    Object localObject = mChildFragmentManager;
    if (localObject != null)
    {
      ((FragmentManagerImpl)localObject).restoreAllState();
      mChildFragmentManager.execPendingActions();
    }
    mState = 4;
    mCalled = false;
    onResume();
    if (mCalled)
    {
      localObject = mChildFragmentManager;
      if (localObject != null)
      {
        ((FragmentManagerImpl)localObject).dispatchResume();
        mChildFragmentManager.execPendingActions();
      }
      o.toString(c.ON_RESUME);
      if (mView != null) {
        e.toString(c.ON_RESUME);
      }
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Fragment ");
      ((StringBuilder)localObject).append(this);
      ((StringBuilder)localObject).append(" did not call through to super.onResume()");
      throw new SuperNotCalledException(((StringBuilder)localObject).toString());
    }
  }
  
  void performSaveInstanceState(Bundle paramBundle)
  {
    onSaveInstanceState(paramBundle);
    Object localObject = mChildFragmentManager;
    if (localObject != null)
    {
      localObject = ((FragmentManagerImpl)localObject).saveAllState();
      if (localObject != null) {
        paramBundle.putParcelable("android:support:fragments", (Parcelable)localObject);
      }
    }
  }
  
  void performStart()
  {
    Object localObject = mChildFragmentManager;
    if (localObject != null)
    {
      ((FragmentManagerImpl)localObject).restoreAllState();
      mChildFragmentManager.execPendingActions();
    }
    mState = 3;
    mCalled = false;
    onStart();
    if (mCalled)
    {
      localObject = mChildFragmentManager;
      if (localObject != null) {
        ((FragmentManagerImpl)localObject).dispatchStart();
      }
      o.toString(c.ON_START);
      if (mView != null) {
        e.toString(c.ON_START);
      }
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Fragment ");
      ((StringBuilder)localObject).append(this);
      ((StringBuilder)localObject).append(" did not call through to super.onStart()");
      throw new SuperNotCalledException(((StringBuilder)localObject).toString());
    }
  }
  
  void performStop()
  {
    if (mView != null) {
      e.toString(c.ON_STOP);
    }
    o.toString(c.ON_STOP);
    Object localObject = mChildFragmentManager;
    if (localObject != null) {
      ((FragmentManagerImpl)localObject).dispatchStop();
    }
    mState = 2;
    mCalled = false;
    onStop();
    if (mCalled) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" did not call through to super.onStop()");
    throw new SuperNotCalledException(((StringBuilder)localObject).toString());
  }
  
  public Object remove()
  {
    Object localObject = a;
    if (localObject == null) {
      return null;
    }
    localObject = v;
    if (localObject == y) {
      return getItem();
    }
    return localObject;
  }
  
  final void restoreViewState(Bundle paramBundle)
  {
    SparseArray localSparseArray = mSavedViewState;
    if (localSparseArray != null)
    {
      mInnerView.restoreHierarchyState(localSparseArray);
      mSavedViewState = null;
    }
    mCalled = false;
    onViewStateRestored(paramBundle);
    if (mCalled)
    {
      if (mView != null) {
        e.toString(c.ON_CREATE);
      }
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Fragment ");
      paramBundle.append(this);
      paramBundle.append(" did not call through to super.onViewStateRestored()");
      throw new SuperNotCalledException(paramBundle.toString());
    }
  }
  
  public void setArguments(Bundle paramBundle)
  {
    if ((mIndex >= 0) && (onActivityResult())) {
      throw new IllegalStateException("Fragment already active and state has been saved");
    }
    mArguments = paramBundle;
  }
  
  public void setHasOptionsMenu(boolean paramBoolean) {}
  
  final void setIndex(int paramInt, Fragment paramFragment)
  {
    mIndex = paramInt;
    if (paramFragment != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(mWho);
      localStringBuilder.append(":");
      localStringBuilder.append(mIndex);
      mWho = localStringBuilder.toString();
      return;
    }
    paramFragment = new StringBuilder();
    paramFragment.append("android:fragment:");
    paramFragment.append(mIndex);
    mWho = paramFragment.toString();
  }
  
  public void setIndex(boolean paramBoolean) {}
  
  public void setInitialSavedState(SavedState paramSavedState)
  {
    if (mIndex < 0)
    {
      if (paramSavedState != null)
      {
        paramSavedState = mState;
        if (paramSavedState != null) {}
      }
      else
      {
        paramSavedState = null;
      }
      mSavedFragmentState = paramSavedState;
      return;
    }
    throw new IllegalStateException("Fragment already active");
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    if (mMenuVisible != paramBoolean)
    {
      mMenuVisible = paramBoolean;
      if ((mHasMenu) && (isAdded()) && (!isHidden())) {
        mHost.onSupportInvalidateOptionsMenu();
      }
    }
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    if ((!mUserVisibleHint) && (paramBoolean) && (mState < 3) && (mFragmentManager != null) && (isAdded()) && (mLoadersStarted)) {
      mFragmentManager.performPendingDeferredStart(this);
    }
    mUserVisibleHint = paramBoolean;
    boolean bool;
    if ((mState < 3) && (!paramBoolean)) {
      bool = true;
    } else {
      bool = false;
    }
    mDeferStart = bool;
    if (mSavedFragmentState != null) {
      mContainerId = Boolean.valueOf(paramBoolean);
    }
  }
  
  int size()
  {
    d localD = a;
    if (localD == null) {
      return 0;
    }
    return N;
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle)
  {
    FragmentHostCallback localFragmentHostCallback = mHost;
    if (localFragmentHostCallback != null)
    {
      localFragmentHostCallback.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
      return;
    }
    paramIntent = new StringBuilder();
    paramIntent.append("Fragment ");
    paramIntent.append(this);
    paramIntent.append(" not attached to Activity");
    throw new IllegalStateException(paramIntent.toString());
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    FragmentHostCallback localFragmentHostCallback = mHost;
    if (localFragmentHostCallback != null)
    {
      localFragmentHostCallback.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
      return;
    }
    paramIntent = new StringBuilder();
    paramIntent.append("Fragment ");
    paramIntent.append(this);
    paramIntent.append(" not attached to Activity");
    throw new IllegalStateException(paramIntent.toString());
  }
  
  public Animator toString(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, localStringBuilder);
    if (mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(mIndex);
    }
    if (mFragmentId != 0)
    {
      localStringBuilder.append(" id=0x");
      localStringBuilder.append(Integer.toHexString(mFragmentId));
    }
    if (mTag != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(mTag);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public boolean visit()
  {
    Object localObject = a;
    if (localObject != null)
    {
      localObject = c;
      if (localObject != null) {
        return ((Boolean)localObject).booleanValue();
      }
    }
    return true;
  }
  
  public void write(Intent paramIntent)
  {
    startActivity(paramIntent, null);
  }
  
  public class InstantiationException
    extends RuntimeException
  {
    public InstantiationException(Exception paramException)
    {
      super(paramException);
    }
  }
  
  public class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<f.g> CREATOR = new f.g.a();
    final Bundle mState;
    
    SavedState()
    {
      mState = this$1;
    }
    
    SavedState(ClassLoader paramClassLoader)
    {
      mState = this$1.readBundle();
      if (paramClassLoader != null)
      {
        this$1 = mState;
        if (this$1 != null) {
          this$1.setClassLoader(paramClassLoader);
        }
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeBundle(mState);
    }
  }
}
