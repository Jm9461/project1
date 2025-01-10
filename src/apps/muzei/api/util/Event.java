package apps.muzei.api.util;

public class Event
{
  final int bottom;
  final float[] cornerRadius = new float[8];
  final int left;
  final int right;
  final int top;
  final int type;
  
  public Event(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    type = paramInt1;
    float[] arrayOfFloat = cornerRadius;
    arrayOfFloat[0] = paramInt2;
    arrayOfFloat[1] = paramInt2;
    arrayOfFloat[2] = paramInt3;
    arrayOfFloat[3] = paramInt3;
    arrayOfFloat[4] = paramInt4;
    arrayOfFloat[5] = paramInt4;
    arrayOfFloat[6] = paramInt5;
    arrayOfFloat[7] = paramInt5;
    left = paramInt6;
    top = paramInt7;
    right = paramInt8;
    bottom = paramInt9;
  }
}
