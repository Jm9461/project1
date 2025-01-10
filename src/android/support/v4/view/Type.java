package android.support.v4.view;

import android.view.MotionEvent;

public final class Type
{
  public static boolean a(MotionEvent paramMotionEvent, int paramInt)
  {
    return (paramMotionEvent.getSource() & paramInt) == paramInt;
  }
}
