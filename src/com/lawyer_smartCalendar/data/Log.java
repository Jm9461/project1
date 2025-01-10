package com.lawyer_smartCalendar.data;

public class Log
{
  private String ID;
  private int bytes;
  private String id;
  private int q;
  private String s;
  private String selector;
  private int text;
  private String value;
  
  public Log() {}
  
  public void add(String paramString)
  {
    selector = paramString;
  }
  
  public void append(int paramInt)
  {
    text = paramInt;
  }
  
  public void close(String paramString)
  {
    value = paramString;
  }
  
  public int d()
  {
    return q;
  }
  
  public String get()
  {
    return value;
  }
  
  public String getID()
  {
    return ID;
  }
  
  public String getId()
  {
    return id;
  }
  
  public String getString()
  {
    return s;
  }
  
  public int getText()
  {
    return text;
  }
  
  public String getValue()
  {
    return selector;
  }
  
  public void init(int paramInt)
  {
    q = paramInt;
  }
  
  public void init(String paramString)
  {
    s = paramString;
  }
  
  public int read()
  {
    return bytes;
  }
  
  public void set(int paramInt)
  {
    bytes = paramInt;
  }
  
  public void set(String paramString)
  {
    id = paramString;
  }
  
  public void start(String paramString)
  {
    ID = paramString;
  }
}
