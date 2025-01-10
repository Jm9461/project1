package spongycastle.mirrajabi.persiancalendar.crypto.asm;

import java.util.Calendar;
import spongycastle.mirrajabi.persiancalendar.crypto.util.AtomicBoolean;
import spongycastle.mirrajabi.persiancalendar.crypto.util.Feed;
import spongycastle.mirrajabi.persiancalendar.crypto.util.IOException;

public class Item
  extends Frame
{
  private static final int[] i = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  private int a;
  private int c;
  private int d;
  
  public Item()
  {
    this(Calendar.getInstance());
  }
  
  public Item(int paramInt1, int paramInt2, int paramInt3)
  {
    this();
    setTitle(paramInt1);
    c = 1;
    set(paramInt2);
    a(paramInt3);
  }
  
  public Item(Calendar paramCalendar)
  {
    a = paramCalendar.get(1);
    d = (paramCalendar.get(2) + 1);
    c = paramCalendar.get(5);
  }
  
  public int a()
  {
    return d;
  }
  
  public void a(int paramInt)
  {
    if (paramInt >= 1)
    {
      int j = d;
      if ((j != 2) && (paramInt > i[j]))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      if ((d == 2) && (b()) && (paramInt > 29))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      if ((d == 2) && (!b()) && (paramInt > 28))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
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
  
  public boolean b()
  {
    int j = a;
    if (j % 400 == 0) {
      return true;
    }
    if (j % 100 == 0) {
      return false;
    }
    return j % 4 == 0;
  }
  
  public Item clone()
  {
    return new Item(getTitle(), a(), getName());
  }
  
  public int getName()
  {
    return c;
  }
  
  public int getTitle()
  {
    return a;
  }
  
  public int set()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(1, a);
    localCalendar.set(2, d - 1);
    localCalendar.set(5, c);
    return localCalendar.get(7);
  }
  
  public void set(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 12))
    {
      a(getName());
      d = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("month ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append("is out of range!");
    throw new AtomicBoolean(localStringBuilder.toString());
  }
  
  public void setTitle(int paramInt)
  {
    if (paramInt != 0)
    {
      a = paramInt;
      return;
    }
    throw new Feed("Year 0 is invalid!");
  }
}
