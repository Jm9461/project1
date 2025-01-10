package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FragmentStatePagerAdapter
  extends PagerAdapter
{
  private FragmentTransaction mCurTransaction = null;
  private Fragment mCurrentPrimaryItem = null;
  private final FragmentManager mFragmentManager;
  private ArrayList<f> mFragments = new ArrayList();
  private ArrayList<f.g> mSavedState = new ArrayList();
  
  public FragmentStatePagerAdapter(FragmentManager paramFragmentManager)
  {
    mFragmentManager = paramFragmentManager;
  }
  
  public void destroyItem(ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getId() != -1) {
      return;
    }
    paramViewGroup = new StringBuilder();
    paramViewGroup.append("ViewPager with adapter ");
    paramViewGroup.append(this);
    paramViewGroup.append(" requires a view id");
    throw new IllegalStateException(paramViewGroup.toString());
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramObject = (Fragment)paramObject;
    if (mCurTransaction == null) {
      mCurTransaction = mFragmentManager.beginTransaction();
    }
    while (mSavedState.size() <= paramInt) {
      mSavedState.add(null);
    }
    ArrayList localArrayList = mSavedState;
    if (paramObject.isAdded()) {
      paramViewGroup = mFragmentManager.saveFragmentInstanceState(paramObject);
    } else {
      paramViewGroup = null;
    }
    localArrayList.set(paramInt, paramViewGroup);
    mFragments.set(paramInt, null);
    mCurTransaction.remove(paramObject);
  }
  
  public void finishUpdate(ViewGroup paramViewGroup)
  {
    paramViewGroup = mCurTransaction;
    if (paramViewGroup != null)
    {
      paramViewGroup.add();
      mCurTransaction = null;
    }
  }
  
  public abstract Fragment getItem(int paramInt);
  
  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    if (mFragments.size() > paramInt)
    {
      localFragment = (Fragment)mFragments.get(paramInt);
      if (localFragment != null) {
        return localFragment;
      }
    }
    if (mCurTransaction == null) {
      mCurTransaction = mFragmentManager.beginTransaction();
    }
    Fragment localFragment = getItem(paramInt);
    if (mSavedState.size() > paramInt)
    {
      Fragment.SavedState localSavedState = (Fragment.SavedState)mSavedState.get(paramInt);
      if (localSavedState != null) {
        localFragment.setInitialSavedState(localSavedState);
      }
    }
    while (mFragments.size() <= paramInt) {
      mFragments.add(null);
    }
    localFragment.setMenuVisibility(false);
    localFragment.setUserVisibleHint(false);
    mFragments.set(paramInt, localFragment);
    mCurTransaction.add(paramViewGroup.getId(), localFragment);
    return localFragment;
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return ((Fragment)paramObject).getValue() == paramView;
  }
  
  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
    if (paramParcelable != null)
    {
      paramParcelable = (Bundle)paramParcelable;
      paramParcelable.setClassLoader(paramClassLoader);
      paramClassLoader = paramParcelable.getParcelableArray("states");
      mSavedState.clear();
      mFragments.clear();
      int i;
      if (paramClassLoader != null)
      {
        i = 0;
        while (i < paramClassLoader.length)
        {
          mSavedState.add((Fragment.SavedState)paramClassLoader[i]);
          i += 1;
        }
      }
      paramClassLoader = paramParcelable.keySet().iterator();
      while (paramClassLoader.hasNext())
      {
        String str = (String)paramClassLoader.next();
        if (str.startsWith("f"))
        {
          i = Integer.parseInt(str.substring(1));
          Object localObject = mFragmentManager.getFragment(paramParcelable, str);
          if (localObject != null)
          {
            while (mFragments.size() <= i) {
              mFragments.add(null);
            }
            ((Fragment)localObject).setMenuVisibility(false);
            mFragments.set(i, localObject);
          }
          else
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Bad fragment at key ");
            ((StringBuilder)localObject).append(str);
            Log.w("FragmentStatePagerAdapt", ((StringBuilder)localObject).toString());
          }
        }
      }
    }
  }
  
  public Parcelable saveState()
  {
    Object localObject2 = null;
    Object localObject1;
    Object localObject3;
    if (mSavedState.size() > 0)
    {
      localObject1 = new Bundle();
      localObject2 = localObject1;
      localObject3 = new Fragment.SavedState[mSavedState.size()];
      mSavedState.toArray((Object[])localObject3);
      ((Bundle)localObject1).putParcelableArray("states", (Parcelable[])localObject3);
    }
    int i = 0;
    while (i < mFragments.size())
    {
      localObject3 = (Fragment)mFragments.get(i);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (((Fragment)localObject3).isAdded())
        {
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = new Bundle();
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("f");
          ((StringBuilder)localObject2).append(i);
          localObject2 = ((StringBuilder)localObject2).toString();
          mFragmentManager.putFragment((Bundle)localObject1, (String)localObject2, (Fragment)localObject3);
        }
      }
      i += 1;
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup = (Fragment)paramObject;
    paramObject = mCurrentPrimaryItem;
    if (paramViewGroup != paramObject)
    {
      if (paramObject != null)
      {
        paramObject.setMenuVisibility(false);
        mCurrentPrimaryItem.setUserVisibleHint(false);
      }
      paramViewGroup.setMenuVisibility(true);
      paramViewGroup.setUserVisibleHint(true);
      mCurrentPrimaryItem = paramViewGroup;
    }
  }
}
