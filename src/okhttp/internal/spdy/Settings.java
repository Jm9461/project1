package okhttp.internal.spdy;

import java.util.Arrays;

public final class Settings
{
  private int set;
  private final int[] values = new int[10];
  
  public Settings() {}
  
  void clear()
  {
    set = 0;
    Arrays.fill(values, 0);
  }
  
  int get()
  {
    if ((set & 0x80) != 0) {
      return values[7];
    }
    return 65535;
  }
  
  int get(int paramInt)
  {
    return values[paramInt];
  }
  
  int getHeaderTableSize()
  {
    if ((set & 0x2) != 0) {
      return values[1];
    }
    return -1;
  }
  
  int getMaxConcurrentStreams(int paramInt)
  {
    if ((set & 0x10) != 0) {
      paramInt = values[4];
    }
    return paramInt;
  }
  
  int getMaxFrameSize(int paramInt)
  {
    if ((set & 0x20) != 0) {
      paramInt = values[5];
    }
    return paramInt;
  }
  
  boolean isSet(int paramInt)
  {
    return (set & 1 << paramInt) != 0;
  }
  
  void merge(Settings paramSettings)
  {
    int i = 0;
    while (i < 10)
    {
      if (paramSettings.isSet(i)) {
        set(i, paramSettings.get(i));
      }
      i += 1;
    }
  }
  
  Settings set(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      int[] arrayOfInt = values;
      if (paramInt1 >= arrayOfInt.length) {
        return this;
      }
      set |= 1 << paramInt1;
      arrayOfInt[paramInt1] = paramInt2;
    }
    return this;
  }
  
  int size()
  {
    return Integer.bitCount(set);
  }
}
