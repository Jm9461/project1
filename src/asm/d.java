package asm;

import android.media.AudioRecord;

public class d
  implements f
{
  private volatile boolean b;
  private final int d;
  private final AudioRecord k;
  private final int n;
  private final int r;
  
  public d(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    d = paramInt2;
    r = paramInt3;
    n = paramInt4;
    k = new AudioRecord(paramInt1, paramInt4, paramInt3, paramInt2, e());
  }
  
  public int a()
  {
    return r;
  }
  
  public byte c()
  {
    int i = d;
    if (i == 2) {
      return 16;
    }
    if (i == 3) {
      return 8;
    }
    return 16;
  }
  
  public int e()
  {
    return AudioRecord.getMinBufferSize(n, r, d);
  }
  
  public void e(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public boolean g()
  {
    return b;
  }
  
  public AudioRecord i()
  {
    return k;
  }
  
  public int l()
  {
    return n;
  }
}
