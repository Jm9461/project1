package android.support.v7.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ChildHelper
{
  final d0.a mBucket;
  final d0.b mCallback;
  final List<View> mHiddenViews;
  
  ChildHelper(d0.b paramB)
  {
    mCallback = paramB;
    mBucket = new d0.a();
    mHiddenViews = new ArrayList();
  }
  
  private int getOffset(int paramInt)
  {
    if (paramInt < 0) {
      return -1;
    }
    int j = mCallback.getChildCount();
    int i = paramInt;
    while (i < j)
    {
      int k = paramInt - (i - mBucket.countOnesBefore(i));
      if (k == 0)
      {
        while (mBucket.get(i)) {
          i += 1;
        }
        return i;
      }
      i += k;
    }
    return -1;
  }
  
  private void hideViewInternal(View paramView)
  {
    mHiddenViews.add(paramView);
    mCallback.attachViewToParent(paramView);
  }
  
  private boolean unhideViewInternal(View paramView)
  {
    if (mHiddenViews.remove(paramView))
    {
      mCallback.onLeftHiddenState(paramView);
      return true;
    }
    return false;
  }
  
  void addView(View paramView, int paramInt, boolean paramBoolean)
  {
    if (paramInt < 0) {
      paramInt = mCallback.getChildCount();
    } else {
      paramInt = getOffset(paramInt);
    }
    mBucket.insert(paramInt, paramBoolean);
    if (paramBoolean) {
      hideViewInternal(paramView);
    }
    mCallback.addView(paramView, paramInt);
  }
  
  void addView(View paramView, boolean paramBoolean)
  {
    addView(paramView, -1, paramBoolean);
  }
  
  void attachViewToParent(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams, boolean paramBoolean)
  {
    if (paramInt < 0) {
      paramInt = mCallback.getChildCount();
    } else {
      paramInt = getOffset(paramInt);
    }
    mBucket.insert(paramInt, paramBoolean);
    if (paramBoolean) {
      hideViewInternal(paramView);
    }
    mCallback.attachViewToParent(paramView, paramInt, paramLayoutParams);
  }
  
  void detachViewFromParent(int paramInt)
  {
    paramInt = getOffset(paramInt);
    mBucket.remove(paramInt);
    mCallback.detachViewFromParent(paramInt);
  }
  
  View findHiddenNonRemovedView(int paramInt)
  {
    int j = mHiddenViews.size();
    int i = 0;
    while (i < j)
    {
      View localView = (View)mHiddenViews.get(i);
      RecyclerView.d0 localD0 = mCallback.getChildViewHolder(localView);
      if ((localD0.getLayoutPosition() == paramInt) && (!localD0.isInvalid()) && (!localD0.isRemoved())) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  View getChildAt(int paramInt)
  {
    paramInt = getOffset(paramInt);
    return mCallback.getChildAt(paramInt);
  }
  
  int getChildCount()
  {
    return mCallback.getChildCount() - mHiddenViews.size();
  }
  
  View getUnfilteredChildAt(int paramInt)
  {
    return mCallback.getChildAt(paramInt);
  }
  
  int getUnfilteredChildCount()
  {
    return mCallback.getChildCount();
  }
  
  void hide(View paramView)
  {
    int i = mCallback.indexOfChild(paramView);
    if (i >= 0)
    {
      mBucket.set(i);
      hideViewInternal(paramView);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("view is not a child, cannot hide ");
    localStringBuilder.append(paramView);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  int indexOfChild(View paramView)
  {
    int i = mCallback.indexOfChild(paramView);
    if (i == -1) {
      return -1;
    }
    if (mBucket.get(i)) {
      return -1;
    }
    return i - mBucket.countOnesBefore(i);
  }
  
  boolean isHidden(View paramView)
  {
    return mHiddenViews.contains(paramView);
  }
  
  void removeAllViewsUnfiltered()
  {
    mBucket.remove();
    int i = mHiddenViews.size() - 1;
    while (i >= 0)
    {
      mCallback.onLeftHiddenState((View)mHiddenViews.get(i));
      mHiddenViews.remove(i);
      i -= 1;
    }
    mCallback.removeAllViews();
  }
  
  void removeView(View paramView)
  {
    int i = mCallback.indexOfChild(paramView);
    if (i < 0) {
      return;
    }
    if (mBucket.remove(i)) {
      unhideViewInternal(paramView);
    }
    mCallback.removeViewAt(i);
  }
  
  void removeViewAt(int paramInt)
  {
    paramInt = getOffset(paramInt);
    View localView = mCallback.getChildAt(paramInt);
    if (localView == null) {
      return;
    }
    if (mBucket.remove(paramInt)) {
      unhideViewInternal(localView);
    }
    mCallback.removeViewAt(paramInt);
  }
  
  boolean removeViewIfHidden(View paramView)
  {
    int i = mCallback.indexOfChild(paramView);
    if (i == -1)
    {
      unhideViewInternal(paramView);
      return true;
    }
    if (mBucket.get(i))
    {
      mBucket.remove(i);
      unhideViewInternal(paramView);
      mCallback.removeViewAt(i);
      return true;
    }
    return false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(mBucket.toString());
    localStringBuilder.append(", hidden list:");
    localStringBuilder.append(mHiddenViews.size());
    return localStringBuilder.toString();
  }
  
  void unhide(View paramView)
  {
    int i = mCallback.indexOfChild(paramView);
    if (i >= 0)
    {
      if (mBucket.get(i))
      {
        mBucket.clear(i);
        unhideViewInternal(paramView);
        return;
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("trying to unhide a view that was not hidden");
      localStringBuilder.append(paramView);
      throw new RuntimeException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("view is not a child, cannot hide ");
    localStringBuilder.append(paramView);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
}
