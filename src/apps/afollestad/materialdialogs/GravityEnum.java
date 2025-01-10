package apps.afollestad.materialdialogs;

import android.os.Build.VERSION;
import b.a.a.e;

public enum GravityEnum
{
  private static final boolean HAS_RTL;
  
  static
  {
    boolean bool = false;
    START = new GravityEnum("START", 0);
    a = new GravityEnum("CENTER", 1);
    END = new GravityEnum("END", 2);
    $VALUES = new GravityEnum[] { START, a, END };
    if (Build.VERSION.SDK_INT >= 17) {
      bool = true;
    }
    HAS_RTL = bool;
  }
  
  public int getGravityInt()
  {
    int i = 1.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i == 3)
        {
          if (HAS_RTL) {
            return 8388613;
          }
          return 5;
        }
        throw new IllegalStateException("Invalid gravity constant");
      }
    }
    else
    {
      if (!HAS_RTL) {
        break label56;
      }
      return 8388611;
    }
    return 1;
    label56:
    return 3;
  }
  
  public int getTextAlignment()
  {
    int i = 1.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[ordinal()];
    if (i != 2)
    {
      if (i != 3) {
        return 5;
      }
      return 6;
    }
    return 4;
  }
}
