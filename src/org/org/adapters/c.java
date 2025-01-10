package org.org.adapters;

class c
{
  public final String a;
  public final int b;
  public final int c;
  public final int d;
  
  private c(String paramString, int paramInt1, int paramInt2)
  {
    a = paramString;
    b = paramInt1;
    d = paramInt2;
    c = -1;
  }
  
  private c(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    a = paramString;
    b = paramInt1;
    d = paramInt2;
    c = paramInt3;
  }
  
  private boolean a(int paramInt)
  {
    int i = d;
    if (i != 7)
    {
      if (paramInt == 7) {
        return true;
      }
      if (i != paramInt)
      {
        int j = c;
        if (j == paramInt) {
          return true;
        }
        if (((i == 4) || (j == 4)) && (paramInt == 3)) {
          return true;
        }
        if (((d == 9) || (c == 9)) && (paramInt == 8)) {
          return true;
        }
        return ((d == 12) || (c == 12)) && (paramInt == 11);
      }
    }
    return true;
  }
}
