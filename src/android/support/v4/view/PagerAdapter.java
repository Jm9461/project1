package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.database.Observable;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter
{
  private final DataSetObservable mObservable = new DataSetObservable();
  private DataSetObserver mViewPagerObserver;
  
  public PagerAdapter() {}
  
  public abstract void destroyItem(ViewGroup paramViewGroup);
  
  public abstract void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject);
  
  public abstract void finishUpdate(ViewGroup paramViewGroup);
  
  public abstract int getCount();
  
  public int getItemPosition(Object paramObject)
  {
    return -1;
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    return null;
  }
  
  public float getPageWidth(int paramInt)
  {
    return 1.0F;
  }
  
  public abstract Object instantiateItem(ViewGroup paramViewGroup, int paramInt);
  
  public abstract boolean isViewFromObject(View paramView, Object paramObject);
  
  public void notifyDataSetChanged()
  {
    try
    {
      if (mViewPagerObserver != null) {
        mViewPagerObserver.onChanged();
      }
      mObservable.notifyChanged();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    mObservable.registerObserver(paramDataSetObserver);
  }
  
  public abstract void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader);
  
  public abstract Parcelable saveState();
  
  public abstract void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject);
  
  void setViewPagerObserver(DataSetObserver paramDataSetObserver)
  {
    try
    {
      mViewPagerObserver = paramDataSetObserver;
      return;
    }
    catch (Throwable paramDataSetObserver)
    {
      throw paramDataSetObserver;
    }
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    mObservable.unregisterObserver(paramDataSetObserver);
  }
}
