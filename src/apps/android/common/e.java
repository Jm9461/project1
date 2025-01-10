package apps.android.common;

import b.e.b.a.a;
import b.h.a.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class e
  implements l<Number>
{
  protected float i;
  private ArrayList<a.a> k;
  
  public final Float a(float paramFloat, Number paramNumber1, Number paramNumber2)
  {
    paramFloat = i * paramFloat;
    float f1 = paramNumber1.floatValue();
    float f2 = paramNumber2.floatValue() - paramNumber1.floatValue();
    float f3 = i;
    float f4 = calculate(paramFloat, f1, f2, f3).floatValue();
    paramNumber1 = k.iterator();
    while (paramNumber1.hasNext()) {
      ((x)paramNumber1.next()).a(paramFloat, f4, f1, f2, f3);
    }
    return Float.valueOf(f4);
  }
  
  public void a(x... paramVarArgs)
  {
    Collections.addAll(k, paramVarArgs);
  }
  
  public abstract Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
}
