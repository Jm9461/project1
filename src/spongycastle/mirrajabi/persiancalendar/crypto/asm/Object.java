package spongycastle.mirrajabi.persiancalendar.crypto.asm;

import java.util.Calendar;
import spongycastle.mirrajabi.persiancalendar.crypto.util.AtomicBoolean;
import spongycastle.mirrajabi.persiancalendar.crypto.util.Feed;
import spongycastle.mirrajabi.persiancalendar.crypto.util.IOException;

public class Object
  extends Frame
{
  private int c;
  private int g;
  private int id;
  
  public Object(int paramInt1, int paramInt2, int paramInt3)
  {
    setId(paramInt1);
    g = 1;
    a(paramInt2);
    add(paramInt3);
  }
  
  public int a()
  {
    return c;
  }
  
  public void a(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 12))
    {
      add(g);
      c = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("month ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append("is out of range!");
    throw new AtomicBoolean(localStringBuilder.toString());
  }
  
  public boolean a(Object paramObject)
  {
    return (getId() == paramObject.getId()) && (a() == paramObject.a()) && ((get() == paramObject.get()) || (get() == -1));
  }
  
  public void add(int paramInt)
  {
    if (paramInt >= 1)
    {
      if ((c <= 6) && (paramInt > 31))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      int i = c;
      if ((i > 6) && (i <= 12) && (paramInt > 30))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      if ((animate()) && (c == 12) && (paramInt > 30))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      if ((!animate()) && (c == 12) && (paramInt > 29))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("day ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" ");
        localStringBuilder.append("is out of range!");
        throw new IOException(localStringBuilder.toString());
      }
      g = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("day ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append("is out of range!");
    throw new IOException(localStringBuilder.toString());
  }
  
  public boolean animate()
  {
    int i = id;
    if (i > 0) {
      i -= 474;
    } else {
      i = 473;
    }
    return (i % 2820 + 474 + 38) * 682 % 2816 < 682;
  }
  
  public Object clone()
  {
    return new Object(get(), a(), getId());
  }
  
  public int get()
  {
    return id;
  }
  
  public int getId()
  {
    return g;
  }
  
  public int set()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(1, id);
    localCalendar.set(2, c - 1);
    localCalendar.set(5, g);
    return localCalendar.get(7);
  }
  
  public void setId(int paramInt)
  {
    if (paramInt != 0)
    {
      id = paramInt;
      return;
    }
    throw new Feed("Year 0 is invalid!");
  }
}
