package org.org.jaxen.ui;

import android.net.Uri;
import org.org.jaxen.util.ClassReader;

public class Label
{
  private final Uri a;
  private final int b;
  private final boolean e;
  private final int f;
  private final int g;
  
  public Label(Uri paramUri, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    ClassReader.a(paramUri);
    a = ((Uri)paramUri);
    f = paramInt1;
    g = paramInt2;
    e = paramBoolean;
    b = paramInt3;
  }
  
  public boolean a()
  {
    return e;
  }
  
  public int b()
  {
    return g;
  }
  
  public int c()
  {
    return b;
  }
  
  public int d()
  {
    return f;
  }
  
  public Uri getValue()
  {
    return a;
  }
}
