package android.support.v4.app;

import android.arch.lifecycle.p;

public class List
{
  private final java.util.List<f> list;
  private final java.util.List<p> start;
  private final java.util.List<m> style;
  
  List(java.util.List paramList1, java.util.List paramList2, java.util.List paramList3)
  {
    list = paramList1;
    style = paramList2;
    start = paramList3;
  }
  
  java.util.List append()
  {
    return style;
  }
  
  java.util.List get()
  {
    return list;
  }
  
  java.util.List getStart()
  {
    return start;
  }
}
