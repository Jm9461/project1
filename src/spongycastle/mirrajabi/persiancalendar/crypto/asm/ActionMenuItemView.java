package spongycastle.mirrajabi.persiancalendar.crypto.asm;

import spongycastle.mirrajabi.persiancalendar.crypto.util.AtomicBoolean;
import spongycastle.mirrajabi.persiancalendar.crypto.util.Feed;
import spongycastle.mirrajabi.persiancalendar.crypto.util.IOException;

public class ActionMenuItemView
  extends Frame
{
  private int b;
  private int c;
  private int e;
  
  public ActionMenuItemView(int paramInt1, int paramInt2, int paramInt3)
  {
    setTitle(paramInt1);
    c = 1;
    initialize(paramInt2);
    a(paramInt3);
  }
  
  public int a()
  {
    return b;
  }
  
  public void a(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 30))
    {
      c = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("day ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append("is out of range!");
    throw new IOException(localStringBuilder.toString());
  }
  
  public ActionMenuItemView clone()
  {
    return new ActionMenuItemView(e(), a(), get());
  }
  
  public int e()
  {
    return e;
  }
  
  public int get()
  {
    return c;
  }
  
  public void initialize(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 12))
    {
      a(c);
      b = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("month ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append("is out of range!");
    throw new AtomicBoolean(localStringBuilder.toString());
  }
  
  public int set()
  {
    throw new RuntimeException("not implemented yet!");
  }
  
  public void setTitle(int paramInt)
  {
    if (paramInt != 0)
    {
      e = paramInt;
      return;
    }
    throw new Feed("Year 0 is invalid!");
  }
}
