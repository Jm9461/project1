package com.aurelhubert.ahbottomnavigation.parsers;

public final class Board
{
  public static int get(Tag paramTag, int paramInt)
  {
    int i = paramTag.count();
    if (i == 0) {
      return paramInt;
    }
    return i;
  }
  
  public static int next(Tag paramTag, int paramInt)
  {
    int i = paramTag.getValue();
    if (i == 0) {
      return paramInt;
    }
    return i;
  }
}
