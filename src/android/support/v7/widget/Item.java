package android.support.v7.widget;

import android.view.View;

class Item
{
  int a = 0;
  boolean b = true;
  int c;
  int d = 0;
  int g;
  boolean h;
  boolean i;
  int j;
  int k;
  
  Item() {}
  
  boolean a(RecyclerView.a0 paramA0)
  {
    int m = c;
    return (m >= 0) && (m < paramA0.size());
  }
  
  View next(RecyclerView.v paramV)
  {
    paramV = paramV.get(c);
    c += k;
    return paramV;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LayoutState{mAvailable=");
    localStringBuilder.append(g);
    localStringBuilder.append(", mCurrentPosition=");
    localStringBuilder.append(c);
    localStringBuilder.append(", mItemDirection=");
    localStringBuilder.append(k);
    localStringBuilder.append(", mLayoutDirection=");
    localStringBuilder.append(j);
    localStringBuilder.append(", mStartLine=");
    localStringBuilder.append(a);
    localStringBuilder.append(", mEndLine=");
    localStringBuilder.append(d);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
