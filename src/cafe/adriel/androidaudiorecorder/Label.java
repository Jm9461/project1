package cafe.adriel.androidaudiorecorder;

import android.graphics.Color;
import android.os.Handler;
import asm.d;
import asm.f;
import cafe.adriel.androidaudiorecorder.asynctasks.AllowedSolution;
import cafe.adriel.androidaudiorecorder.asynctasks.MathArrays.OrderDirection;
import cafe.adriel.androidaudiorecorder.asynctasks.c;

public class Label
{
  private static final Handler h = new Handler();
  
  public static f a(c paramC, MathArrays.OrderDirection paramOrderDirection, AllowedSolution paramAllowedSolution)
  {
    return new d(paramC.a(), 2, paramOrderDirection.getAction(), paramAllowedSolution.a());
  }
  
  public static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(toString(paramInt / 3600));
    localStringBuilder.append(":");
    localStringBuilder.append(toString(paramInt / 60));
    localStringBuilder.append(":");
    localStringBuilder.append(toString(paramInt % 60));
    return localStringBuilder.toString();
  }
  
  public static void a(int paramInt, Runnable paramRunnable)
  {
    h.postDelayed(paramRunnable, paramInt);
  }
  
  public static int getColor(int paramInt)
  {
    int i = Color.alpha(paramInt);
    int j = Color.red(paramInt);
    int k = Color.green(paramInt);
    paramInt = Color.blue(paramInt);
    return Color.argb(i, Math.max((int)(j * 0.8F), 0), Math.max((int)(k * 0.8F), 0), Math.max((int)(paramInt * 0.8F), 0));
  }
  
  private static String toString(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= 9))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("0");
      localStringBuilder.append(paramInt);
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt);
    localStringBuilder.append("");
    return localStringBuilder.toString();
  }
  
  public static boolean update(int paramInt)
  {
    if (17170445 == paramInt) {
      return true;
    }
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = Color.red(paramInt);
    arrayOfInt[1] = Color.green(paramInt);
    arrayOfInt[2] = Color.blue(paramInt);
    double d1 = arrayOfInt[0] * arrayOfInt[0];
    Double.isNaN(d1);
    double d2 = arrayOfInt[1] * arrayOfInt[1];
    Double.isNaN(d2);
    double d3 = arrayOfInt[2] * arrayOfInt[2];
    Double.isNaN(d3);
    return (int)Math.sqrt(d1 * 0.241D + d2 * 0.691D + d3 * 0.068D) >= 200;
  }
}
