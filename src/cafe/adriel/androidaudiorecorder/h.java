package cafe.adriel.androidaudiorecorder;

public class h
  extends b.d.a.b<Float>
{
  public h() {}
  
  public void c()
  {
    try
    {
      onCreate();
      return;
    }
    catch (Exception localException) {}
  }
  
  protected void draw(Float paramFloat, int paramInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    Float localFloat = Float.valueOf(paramFloat.floatValue() / 100.0F);
    paramFloat = localFloat;
    if (localFloat.floatValue() <= 0.5D) {
      paramFloat = Float.valueOf(0.0F);
    } else if ((localFloat.floatValue() > 0.5D) && (localFloat.floatValue() <= 0.6D)) {
      paramFloat = Float.valueOf(0.2F);
    } else if ((localFloat.floatValue() > 0.6D) && (localFloat.floatValue() <= 0.7D)) {
      paramFloat = Float.valueOf(0.6F);
    } else if (localFloat.floatValue() > 0.7D) {
      paramFloat = Float.valueOf(1.0F);
    }
    try
    {
      float f = paramFloat.floatValue();
      paramArrayOfFloat1[0] = f;
      f = paramFloat.floatValue();
      paramArrayOfFloat2[0] = f;
      return;
    }
    catch (Exception paramFloat) {}
  }
}
