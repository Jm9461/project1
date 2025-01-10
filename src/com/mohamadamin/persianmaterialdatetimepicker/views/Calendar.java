package com.mohamadamin.persianmaterialdatetimepicker.views;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Calendar
  extends GregorianCalendar
{
  private int count;
  private int data;
  private String path = "/";
  private int t;
  
  public Calendar()
  {
    setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  private String decode(int paramInt)
  {
    if (paramInt < 9)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("0");
      localStringBuilder.append(paramInt);
      return localStringBuilder.toString();
    }
    return String.valueOf(paramInt);
  }
  
  private long write(long paramLong)
  {
    return 86400000L * paramLong - 210866803200000L + Label.a(getTimeInMillis() + 210866803200000L, 8.64E7D);
  }
  
  public String convert()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(decode(data));
    localStringBuilder.append(path);
    localStringBuilder.append(decode(set() + 1));
    localStringBuilder.append(path);
    localStringBuilder.append(decode(t));
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
  
  public String format()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getValue());
    localStringBuilder.append("  ");
    localStringBuilder.append(t);
    localStringBuilder.append("  ");
    localStringBuilder.append(getDisplayName());
    localStringBuilder.append("  ");
    localStringBuilder.append(data);
    return localStringBuilder.toString();
  }
  
  public int get()
  {
    return data;
  }
  
  public String getDisplayName()
  {
    return Contact.name[count];
  }
  
  public int getTime()
  {
    return t;
  }
  
  public String getValue()
  {
    int i = get(7);
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i != 4)
          {
            if (i != 5)
            {
              if (i != 7) {
                return Contact.value[6];
              }
              return Contact.value[0];
            }
            return Contact.value[5];
          }
          return Contact.value[4];
        }
        return Contact.value[3];
      }
      return Contact.value[2];
    }
    return Contact.value[1];
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
  
  public void init(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return;
    }
    if ((paramInt1 >= 0) && (paramInt1 < 15))
    {
      if (paramInt1 == 1)
      {
        set(data + paramInt2, set() + 1, t);
        return;
      }
      if (paramInt1 == 2)
      {
        set(data + (set() + 1 + paramInt2) / 12, (set() + 1 + paramInt2) % 12, t);
        return;
      }
      add(paramInt1, paramInt2);
      write();
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public int set()
  {
    return count;
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    super.set(paramInt1, paramInt2);
    write();
  }
  
  public void set(int paramInt1, int paramInt2, int paramInt3)
  {
    data = paramInt1;
    count = (paramInt2 + 1);
    t = paramInt3;
    paramInt1 = data;
    if (paramInt1 <= 0) {
      paramInt1 += 1;
    }
    setTimeInMillis(write(Label.add(paramInt1, count - 1, t)));
  }
  
  public void setTimeInMillis(long paramLong)
  {
    super.setTimeInMillis(paramLong);
    write();
  }
  
  public void setTimeZone(TimeZone paramTimeZone)
  {
    super.setTimeZone(paramTimeZone);
    write();
  }
  
  public String toString()
  {
    String str = super.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str.substring(0, str.length() - 1));
    localStringBuilder.append(",PersianDate=");
    localStringBuilder.append(convert());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  protected void write()
  {
    long l2 = Label.update(Math.floor(getTimeInMillis() + 210866803200000L) / 86400000L);
    long l1 = l2 >> 16;
    int i = (int)(0xFF00 & l2);
    int j = (int)(0xFF & l2);
    if (l1 <= 0L) {
      l1 -= 1L;
    }
    data = ((int)l1);
    count = (i >> 8);
    t = j;
  }
}
