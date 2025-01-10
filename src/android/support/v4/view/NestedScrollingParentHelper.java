package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper
{
  private int mNestedScrollAxes;
  
  public NestedScrollingParentHelper(ViewGroup paramViewGroup) {}
  
  public int getNestedScrollAxes()
  {
    return mNestedScrollAxes;
  }
  
  public void onNestedScrollAccepted(View paramView, int paramInt)
  {
    mNestedScrollAxes = 0;
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    mNestedScrollAxes = paramInt1;
  }
}
