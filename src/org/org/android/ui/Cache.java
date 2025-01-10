package org.org.android.ui;

import a.b.c.n.d;
import a.b.c.n.d.e;
import android.util.Property;

public class Cache
  extends Property<d, d.e>
{
  public static final Property<d, d.e> l = new Cache("circularReveal");
  
  private Cache(String paramString)
  {
    super(d.e.class, paramString);
  }
  
  public Label getElement(List paramList)
  {
    return paramList.getRevealInfo();
  }
  
  public void putAll(List paramList, Label paramLabel)
  {
    paramList.setRevealInfo(paramLabel);
  }
}
