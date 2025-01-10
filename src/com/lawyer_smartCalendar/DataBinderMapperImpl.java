package com.lawyer_smartCalendar;

import android.databinding.symbol;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

public class DataBinderMapperImpl
  extends symbol
{
  static
  {
    new SparseIntArray(0);
  }
  
  public DataBinderMapperImpl() {}
  
  public List getStatuses()
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return localArrayList;
  }
}
