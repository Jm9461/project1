package apps.v4.animation;

import b.h.a.l;

public class FloatEvaluator
  implements l<Number>
{
  public FloatEvaluator() {}
  
  public Float evaluate(float paramFloat, Number paramNumber1, Number paramNumber2)
  {
    float f = paramNumber1.floatValue();
    return Float.valueOf((paramNumber2.floatValue() - f) * paramFloat + f);
  }
}
