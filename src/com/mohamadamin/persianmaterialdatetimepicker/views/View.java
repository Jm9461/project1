package com.mohamadamin.persianmaterialdatetimepicker.views;

import java.util.ArrayList;

public class View
{
  public static String format(String paramString)
  {
    return paramString.replace("0", "?").replace("1", "?").replace("2", "?").replace("3", "?").replace("4", "?").replace("5", "?").replace("6", "?").replace("7", "?").replace("8", "?").replace("9", "?");
  }
  
  public static String update(String paramString)
  {
    return paramString.replace("?", "0").replace("?", "1").replace("?", "2").replace("?", "3").replace("?", "4").replace("?", "5").replace("?", "6").replace("?", "7").replace("?", "8").replace("?", "9");
  }
  
  public static ArrayList update(ArrayList paramArrayList)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      paramArrayList.set(i, format((String)paramArrayList.get(i)));
      i += 1;
    }
    return paramArrayList;
  }
  
  public static String[] update(String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      paramArrayOfString[i] = format(paramArrayOfString[i]);
      i += 1;
    }
    return paramArrayOfString;
  }
}
