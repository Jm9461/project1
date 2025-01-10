package org.org.android.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.util.StateSet;
import org.org.jaxen.asm.ColorUtils;

public class ThemeUtils
{
  private static final int[] ACTIVATED_STATE_SET;
  private static final int[] CHECKED_STATE_SET;
  private static final int[] DISABLED_STATE_SET = { 16842913, 16843623 };
  private static final int[] EMPTY_STATE_SET;
  private static final int[] FOCUSED_STATE_SET = { 16842913 };
  private static final int[] PRESSED_STATE_SET;
  private static final int[] SELECTED_STATE_SET;
  private static final int[] c;
  private static final int[] d;
  public static final boolean debug;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    }
    debug = bool;
    ACTIVATED_STATE_SET = new int[] { 16842919 };
    PRESSED_STATE_SET = new int[] { 16843623, 16842908 };
    c = new int[] { 16842908 };
    d = new int[] { 16843623 };
    CHECKED_STATE_SET = new int[] { 16842913, 16842919 };
    SELECTED_STATE_SET = new int[] { 16842913, 16843623, 16842908 };
    EMPTY_STATE_SET = new int[] { 16842913, 16842908 };
  }
  
  public static ColorStateList createDefaultColorStateList(ColorStateList paramColorStateList)
  {
    if (debug)
    {
      arrayOfInt = new int[2][];
      arrayOfInt1 = new int[2];
      arrayOfInt[0] = FOCUSED_STATE_SET;
      arrayOfInt1[0] = getDisabledThemeAttrColor(paramColorStateList, CHECKED_STATE_SET);
      i = 0 + 1;
      arrayOfInt[i] = StateSet.NOTHING;
      arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, ACTIVATED_STATE_SET);
      return new ColorStateList(arrayOfInt, arrayOfInt1);
    }
    int[][] arrayOfInt = new int[10][];
    int[] arrayOfInt1 = new int[10];
    int[] arrayOfInt2 = CHECKED_STATE_SET;
    arrayOfInt[0] = arrayOfInt2;
    arrayOfInt1[0] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    int i = 0 + 1;
    arrayOfInt2 = SELECTED_STATE_SET;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt2 = EMPTY_STATE_SET;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt2 = DISABLED_STATE_SET;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt[i] = FOCUSED_STATE_SET;
    arrayOfInt1[i] = 0;
    i += 1;
    arrayOfInt2 = ACTIVATED_STATE_SET;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt2 = PRESSED_STATE_SET;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt2 = c;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt2 = d;
    arrayOfInt[i] = arrayOfInt2;
    arrayOfInt1[i] = getDisabledThemeAttrColor(paramColorStateList, arrayOfInt2);
    i += 1;
    arrayOfInt[i] = StateSet.NOTHING;
    arrayOfInt1[i] = 0;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static int getDisabledThemeAttrColor(ColorStateList paramColorStateList, int[] paramArrayOfInt)
  {
    int i;
    if (paramColorStateList != null) {
      i = paramColorStateList.getColorForState(paramArrayOfInt, paramColorStateList.getDefaultColor());
    } else {
      i = 0;
    }
    int j = i;
    if (debug) {
      j = getThemeAttrColor(i);
    }
    return j;
  }
  
  private static int getThemeAttrColor(int paramInt)
  {
    return ColorUtils.setAlphaComponent(paramInt, Math.min(Color.alpha(paramInt) * 2, 255));
  }
}
