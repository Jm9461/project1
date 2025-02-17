package android.support.v7.app;

class TwilightCalculator
{
  private static TwilightCalculator sInstance;
  public int state;
  public long sunrise;
  public long sunset;
  
  TwilightCalculator() {}
  
  static TwilightCalculator getInstance()
  {
    if (sInstance == null) {
      sInstance = new TwilightCalculator();
    }
    return sInstance;
  }
  
  public void calculateTwilight(long paramLong, double paramDouble1, double paramDouble2)
  {
    float f1 = (float)(paramLong - 946728000000L) / 8.64E7F;
    float f2 = 0.01720197F * f1 + 6.24006F;
    double d1 = f2;
    double d2 = Math.sin(f2);
    Double.isNaN(d1);
    d1 = 1.796593063D + (d1 + d2 * 0.03341960161924362D + Math.sin(2.0F * f2) * 3.4906598739326E-4D + Math.sin(3.0F * f2) * 5.236000106378924E-6D) + 3.141592653589793D;
    paramDouble2 = -paramDouble2 / 360.0D;
    d2 = f1 - 9.0E-4F;
    Double.isNaN(d2);
    d2 = 9.0E-4F + (float)Math.round(d2 - paramDouble2);
    Double.isNaN(d2);
    paramDouble2 = d2 + paramDouble2 + Math.sin(f2) * 0.0053D + Math.sin(2.0D * d1) * -0.0069D;
    d1 = Math.asin(Math.sin(d1) * Math.sin(0.4092797040939331D));
    paramDouble1 = 0.01745329238474369D * paramDouble1;
    paramDouble1 = (Math.sin(-0.10471975803375244D) - Math.sin(paramDouble1) * Math.sin(d1)) / (Math.cos(paramDouble1) * Math.cos(d1));
    if (paramDouble1 >= 1.0D)
    {
      state = 1;
      sunset = -1L;
      sunrise = -1L;
      return;
    }
    if (paramDouble1 <= -1.0D)
    {
      state = 0;
      sunset = -1L;
      sunrise = -1L;
      return;
    }
    f1 = (float)(Math.acos(paramDouble1) / 6.283185307179586D);
    paramDouble1 = f1;
    Double.isNaN(paramDouble1);
    sunset = (Math.round((paramDouble1 + paramDouble2) * 8.64E7D) + 946728000000L);
    paramDouble1 = f1;
    Double.isNaN(paramDouble1);
    sunrise = (Math.round((paramDouble2 - paramDouble1) * 8.64E7D) + 946728000000L);
    if ((sunrise < paramLong) && (sunset > paramLong))
    {
      state = 0;
      return;
    }
    state = 1;
  }
}
