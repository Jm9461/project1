package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class ViewOffsetHelper
{
  private int mLayoutLeft;
  private int mLayoutTop;
  private int mOffsetLeft;
  private int mOffsetTop;
  private final View mView;
  
  public ViewOffsetHelper(View paramView)
  {
    mView = paramView;
  }
  
  private void updateOffsets()
  {
    View localView = mView;
    ViewCompat.offsetTopAndBottom(localView, mOffsetTop - (localView.getTop() - mLayoutTop));
    localView = mView;
    ViewCompat.offsetLeftAndRight(localView, mOffsetLeft - (localView.getLeft() - mLayoutLeft));
  }
  
  public int getTopAndBottomOffset()
  {
    return mOffsetTop;
  }
  
  public void onViewLayout()
  {
    mLayoutTop = mView.getTop();
    mLayoutLeft = mView.getLeft();
    updateOffsets();
  }
  
  public boolean setLeftAndRightOffset(int paramInt)
  {
    if (mOffsetLeft != paramInt)
    {
      mOffsetLeft = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }
  
  public boolean setTopAndBottomOffset(int paramInt)
  {
    if (mOffsetTop != paramInt)
    {
      mOffsetTop = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }
}
