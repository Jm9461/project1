package android.support.v7.widget;

class Label
{
  int a;
  Object b;
  int c;
  int d;
  
  Label(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    d = paramInt1;
    a = paramInt2;
    c = paramInt3;
    b = paramObject;
  }
  
  String cmdToString()
  {
    int i = d;
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 4)
        {
          if (i != 8) {
            return "??";
          }
          return "mv";
        }
        return "up";
      }
      return "rm";
    }
    return "add";
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Label)paramObject;
      int i = d;
      if (i != d) {
        return false;
      }
      if ((i == 8) && (Math.abs(c - a) == 1) && (c == a) && (a == c)) {
        return true;
      }
      if (c != c) {
        return false;
      }
      if (a != a) {
        return false;
      }
      Object localObject = b;
      if (localObject != null)
      {
        if (!localObject.equals(b)) {
          return false;
        }
      }
      else
      {
        if (b == null) {
          break label151;
        }
        return false;
      }
      return true;
    }
    else
    {
      return false;
    }
    label151:
    return true;
  }
  
  public int hashCode()
  {
    return (d * 31 + a) * 31 + c;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append("[");
    localStringBuilder.append(cmdToString());
    localStringBuilder.append(",s:");
    localStringBuilder.append(a);
    localStringBuilder.append("c:");
    localStringBuilder.append(c);
    localStringBuilder.append(",p:");
    localStringBuilder.append(b);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
