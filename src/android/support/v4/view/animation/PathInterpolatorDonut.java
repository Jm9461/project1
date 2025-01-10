package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorDonut
  implements Interpolator
{
  private final float[] mX;
  private final float[] mY;
  
  PathInterpolatorDonut(Path paramPath)
  {
    paramPath = new PathMeasure(paramPath, false);
    float f = paramPath.getLength();
    int j = (int)(f / 0.002F) + 1;
    mX = new float[j];
    mY = new float[j];
    float[] arrayOfFloat = new float[2];
    int i = 0;
    while (i < j)
    {
      paramPath.getPosTan(i * f / (j - 1), arrayOfFloat, null);
      mX[i] = arrayOfFloat[0];
      mY[i] = arrayOfFloat[1];
      i += 1;
    }
  }
  
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      return 0.0F;
    }
    if (paramFloat >= 1.0F) {
      return 1.0F;
    }
    int j = 0;
    int i = mX.length - 1;
    while (i - j > 1)
    {
      int k = (j + i) / 2;
      if (paramFloat < mX[k]) {
        i = k;
      } else {
        j = k;
      }
    }
    float[] arrayOfFloat = mX;
    float f = arrayOfFloat[i] - arrayOfFloat[j];
    if (f == 0.0F) {
      return mY[j];
    }
    paramFloat = (paramFloat - arrayOfFloat[j]) / f;
    arrayOfFloat = mY;
    f = arrayOfFloat[j];
    return (arrayOfFloat[i] - f) * paramFloat + f;
  }
}
