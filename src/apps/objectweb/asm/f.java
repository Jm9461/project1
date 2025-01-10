package apps.objectweb.asm;

import android.content.Context;
import android.content.res.Resources;
import b.d.a.c.c;
import b.d.a.c.d;

public class f
  extends c.d<c.c>
{
  private int B;
  private float b;
  private boolean c;
  private float d;
  private int e;
  private float f;
  private int i;
  private Context m;
  
  public f(Context paramContext)
  {
    super(paramContext);
    m = paramContext;
  }
  
  protected f a()
  {
    return this;
  }
  
  public f a(float paramFloat)
  {
    d = paramFloat;
    return this;
  }
  
  public f a(boolean paramBoolean)
  {
    c = paramBoolean;
    return this;
  }
  
  public f add(int paramInt)
  {
    a(m.getResources().getDimensionPixelSize(paramInt));
    return this;
  }
  
  public f b(float paramFloat)
  {
    f = paramFloat;
    return this;
  }
  
  public f b(int paramInt)
  {
    b(m.getResources().getDimensionPixelSize(paramInt));
    return this;
  }
  
  public f d(int paramInt)
  {
    B = paramInt;
    return this;
  }
  
  public f e(float paramFloat)
  {
    b = paramFloat;
    return this;
  }
  
  public f e(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public f f(int paramInt)
  {
    e(m.getResources().getDimensionPixelSize(paramInt));
    return this;
  }
  
  public Settings readSettings()
  {
    return new Settings(this, null);
  }
  
  public f setSize(int paramInt)
  {
    e = paramInt;
    return this;
  }
}
