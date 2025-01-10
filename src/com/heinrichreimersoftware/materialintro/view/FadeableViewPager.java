package com.heinrichreimersoftware.materialintro.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.j;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FadeableViewPager
  extends Label
{
  public FadeableViewPager(Context paramContext)
  {
    super(paramContext);
  }
  
  public FadeableViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void a(ViewPager.j paramJ)
  {
    super.a(new b(paramJ, null));
  }
  
  public void addOnPageChangeListener(ViewPager.j paramJ)
  {
    super.addOnPageChangeListener(new b(paramJ, null));
  }
  
  public PagerAdapter getAdapter()
  {
    c localC = (c)super.getAdapter();
    if (localC == null) {
      return null;
    }
    return localC.getAdapter();
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter)
  {
    super.setAdapter(new c(paramPagerAdapter, null));
  }
  
  public void setOnPageChangeListener(ViewPager.j paramJ)
  {
    super.setOnPageChangeListener(new b(paramJ, null));
  }
  
  private class b
    implements ViewPager.j
  {
    private final ViewPager.j mListener;
    
    private b(ViewPager.j paramJ)
    {
      mListener = paramJ;
    }
    
    public void onPageScrollStateChanged(int paramInt)
    {
      mListener.onPageScrollStateChanged(paramInt);
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      int j = getAdapter().getCount();
      ViewPager.j localJ = mListener;
      int i = Math.min(paramInt1, j - 1);
      if (paramInt1 >= j) {
        paramFloat = 0.0F;
      }
      if (paramInt1 >= j) {
        paramInt2 = 0;
      }
      localJ.onPageScrolled(i, paramFloat, paramInt2);
    }
    
    public void onPageSelected(int paramInt)
    {
      int i = getAdapter().getCount();
      mListener.onPageSelected(Math.min(paramInt, i - 1));
    }
  }
  
  private class c
    extends PagerAdapter
  {
    private final PagerAdapter mAdapter;
    
    private c(PagerAdapter paramPagerAdapter)
    {
      mAdapter = paramPagerAdapter;
      paramPagerAdapter.registerDataSetObserver(new a(this$1));
    }
    
    public void destroyItem(ViewGroup paramViewGroup)
    {
      mAdapter.destroyItem(paramViewGroup);
    }
    
    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      if (paramInt < mAdapter.getCount()) {
        mAdapter.destroyItem(paramViewGroup, paramInt, paramObject);
      }
    }
    
    public void finishUpdate(ViewGroup paramViewGroup)
    {
      mAdapter.finishUpdate(paramViewGroup);
    }
    
    public PagerAdapter getAdapter()
    {
      return mAdapter;
    }
    
    public int getCount()
    {
      return mAdapter.getCount() + 1;
    }
    
    public int getItemPosition(Object paramObject)
    {
      int i = mAdapter.getItemPosition(paramObject);
      if (i < mAdapter.getCount()) {
        return i;
      }
      return -2;
    }
    
    public CharSequence getPageTitle(int paramInt)
    {
      if (paramInt < mAdapter.getCount()) {
        return mAdapter.getPageTitle(paramInt);
      }
      return null;
    }
    
    public float getPageWidth(int paramInt)
    {
      if (paramInt < mAdapter.getCount()) {
        return mAdapter.getPageWidth(paramInt);
      }
      return 1.0F;
    }
    
    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      if (paramInt < mAdapter.getCount()) {
        return mAdapter.instantiateItem(paramViewGroup, paramInt);
      }
      return null;
    }
    
    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return mAdapter.isViewFromObject(paramView, paramObject);
    }
    
    public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      mAdapter.registerDataSetObserver(paramDataSetObserver);
    }
    
    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
    {
      mAdapter.restoreState(paramParcelable, paramClassLoader);
    }
    
    public Parcelable saveState()
    {
      return mAdapter.saveState();
    }
    
    public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      if (paramInt < mAdapter.getCount()) {
        mAdapter.setPrimaryItem(paramViewGroup, paramInt, paramObject);
      }
    }
    
    public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      mAdapter.unregisterDataSetObserver(paramDataSetObserver);
    }
    
    class a
      extends DataSetObserver
    {
      a(FadeableViewPager paramFadeableViewPager) {}
      
      public void onChanged()
      {
        notifyDataSetChanged();
      }
      
      public void onInvalidated()
      {
        notifyDataSetChanged();
      }
    }
  }
}
