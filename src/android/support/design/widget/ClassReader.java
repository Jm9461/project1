package android.support.design.widget;

public final class ClassReader
{
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (1.0F - paramFloat3) * paramFloat1 + paramFloat3 * paramFloat2;
  }
  
  private static float a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if ((paramFloat1 > paramFloat2) && (paramFloat1 > paramFloat3) && (paramFloat1 > paramFloat4)) {
      return paramFloat1;
    }
    if ((paramFloat2 > paramFloat3) && (paramFloat2 > paramFloat4)) {
      return paramFloat2;
    }
    if (paramFloat3 > paramFloat4) {
      return paramFloat3;
    }
    return paramFloat4;
  }
  
  public static float a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    return a(b(paramFloat1, paramFloat2, paramFloat3, paramFloat4), b(paramFloat1, paramFloat2, paramFloat5, paramFloat4), b(paramFloat1, paramFloat2, paramFloat5, paramFloat6), b(paramFloat1, paramFloat2, paramFloat3, paramFloat6));
  }
  
  public static float b(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return (float)Math.hypot(paramFloat3 - paramFloat1, paramFloat4 - paramFloat2);
  }
}
