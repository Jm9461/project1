package org.org.android.ui;

import a.b.c.n.d;
import android.util.Property;

public class RefList
  extends Property<d, Integer>
{
  public static final Property<d, Integer> X = new RefList("circularRevealScrimColor");
  
  private RefList(String paramString)
  {
    super(Integer.class, paramString);
  }
  
  public void put(List paramList, Integer paramInteger)
  {
    paramList.setCircularRevealScrimColor(paramInteger.intValue());
  }
  
  public Integer set(List paramList)
  {
    return Integer.valueOf(paramList.getCircularRevealScrimColor());
  }
}
