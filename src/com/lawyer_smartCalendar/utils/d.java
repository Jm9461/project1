package com.lawyer_smartCalendar.utils;

import android.graphics.Color;
import apps.authenticator.wizard.runtime.Log;

public class d
{
  static int[] q = { Color.parseColor("#303F9F"), Color.parseColor("#1976D2"), Color.parseColor("#00796B"), Color.parseColor("#F57C00"), Color.parseColor("#455A64"), Color.parseColor("#C2185B"), Color.parseColor("#c62828"), Color.parseColor("#00838F"), Color.parseColor("#6A1B9A"), Color.parseColor("#5D4037") };
  static int[] r = { Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#009688"), Color.parseColor("#FF9800"), Color.parseColor("#607D8B"), Color.parseColor("#E91E63"), Color.parseColor("#d32f2f"), Color.parseColor("#00ACC1"), Color.parseColor("#8E24AA"), Color.parseColor("#795548") };
  static String[] s = { "#3F51B5", "#2196F3", "#009688", "#FF9800", "#607D8B", "#E91E63", "#d32f2f", "#00ACC1", "#8E24AA", "#795548" };
  
  public d() {}
  
  public static int a()
  {
    int i = r[0];
    switch (Log.get("themeColor", -13615201))
    {
    default: 
      return i;
    case -689152: 
      return r[3];
    case -3790808: 
      return r[6];
    case -4056997: 
      return r[5];
    case -9823334: 
      return r[8];
    case -10665929: 
      return r[9];
    case -12232092: 
      return r[4];
    case -13615201: 
      return r[0];
    case -15108398: 
      return r[1];
    case -16743537: 
      return r[7];
    }
    return r[2];
  }
  
  public static boolean b(int paramInt)
  {
    int i = 0;
    for (;;)
    {
      int[] arrayOfInt = q;
      if (i >= arrayOfInt.length) {
        break;
      }
      if (paramInt == arrayOfInt[i]) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static int c()
  {
    int i = q[0];
    switch (Log.get("themeColor", -13615201))
    {
    default: 
      return i;
    case -689152: 
      return 2131099890;
    case -3790808: 
      return 2131099893;
    case -4056997: 
      return 2131099892;
    case -9823334: 
      return 2131099895;
    case -10665929: 
      return 2131099886;
    case -12232092: 
      return 2131099891;
    case -13615201: 
      return 2131099887;
    case -15108398: 
      return 2131099888;
    case -16743537: 
      return 2131099894;
    }
    return 2131099889;
  }
  
  public static int getId()
  {
    int i = q[0];
    switch (Log.get("themeColor", -13615201))
    {
    default: 
      return 2131820551;
    case -689152: 
      return 2131820751;
    case -3790808: 
      return 2131820754;
    case -4056997: 
      return 2131820753;
    case -9823334: 
      return 2131820756;
    case -10665929: 
      return 2131820748;
    case -12232092: 
      return 2131820752;
    case -13615201: 
      return 2131820746;
    case -15108398: 
      return 2131820749;
    case -16743537: 
      return 2131820755;
    }
    return 2131820750;
  }
  
  public static String getValue()
  {
    String str = s[0];
    switch (Log.get("themeColor", -13615201))
    {
    default: 
      return str;
    case -689152: 
      return s[3];
    case -3790808: 
      return s[6];
    case -4056997: 
      return s[5];
    case -9823334: 
      return s[8];
    case -10665929: 
      return s[9];
    case -12232092: 
      return s[4];
    case -13615201: 
      return s[0];
    case -15108398: 
      return s[1];
    case -16743537: 
      return s[7];
    }
    return s[2];
  }
  
  public int[] d()
  {
    return q;
  }
}
