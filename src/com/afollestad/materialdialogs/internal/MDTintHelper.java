package com.afollestad.materialdialogs.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import apps.afollestad.materialdialogs.R.attr;
import apps.afollestad.materialdialogs.R.drawable;
import apps.afollestad.materialdialogs.base.DialogUtils;
import java.lang.reflect.Field;

@SuppressLint({"PrivateResource"})
public class MDTintHelper
{
  private static ColorStateList createEditTextColorStateList(Context paramContext, int paramInt)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = { -16842910 };
    arrayOfInt1[0] = DialogUtils.resolveColor(paramContext, R.attr.colorControlNormal);
    int i = 0 + 1;
    arrayOfInt[i] = { -16842919, -16842908 };
    arrayOfInt1[i] = DialogUtils.resolveColor(paramContext, R.attr.colorControlNormal);
    i += 1;
    arrayOfInt[i] = new int[0];
    arrayOfInt1[i] = paramInt;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static void init(EditText paramEditText, int paramInt)
  {
    try
    {
      localObject1 = TextView.class.getDeclaredField("mCursorDrawableRes");
      ((Field)localObject1).setAccessible(true);
      int i = ((Field)localObject1).getInt(paramEditText);
      localObject1 = TextView.class.getDeclaredField("mEditor");
      ((Field)localObject1).setAccessible(true);
      localObject1 = ((Field)localObject1).get(paramEditText);
      Field localField = localObject1.getClass().getDeclaredField("mCursorDrawable");
      localField.setAccessible(true);
      Drawable[] arrayOfDrawable = new Drawable[2];
      Object localObject2 = ContextCompat.getDrawable(paramEditText.getContext(), i);
      arrayOfDrawable[0] = localObject2;
      paramEditText = ContextCompat.getDrawable(paramEditText.getContext(), i);
      arrayOfDrawable[1] = paramEditText;
      paramEditText = arrayOfDrawable[0];
      localObject2 = PorterDuff.Mode.SRC_IN;
      paramEditText.setColorFilter(paramInt, (PorterDuff.Mode)localObject2);
      paramEditText = arrayOfDrawable[1];
      localObject2 = PorterDuff.Mode.SRC_IN;
      paramEditText.setColorFilter(paramInt, (PorterDuff.Mode)localObject2);
      localField.set(localObject1, arrayOfDrawable);
      return;
    }
    catch (Exception paramEditText)
    {
      paramEditText.printStackTrace();
      return;
    }
    catch (NoSuchFieldException paramEditText)
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Device issue with cursor tinting: ");
      ((StringBuilder)localObject1).append(paramEditText.getMessage());
      Log.d("MDTintHelper", ((StringBuilder)localObject1).toString());
      paramEditText.printStackTrace();
    }
  }
  
  public static void setTint(CheckBox paramCheckBox, int paramInt)
  {
    int i = DialogUtils.init(paramCheckBox.getContext());
    int[] arrayOfInt1 = { 16842910, 16842912 };
    int[] arrayOfInt2 = { -16842910, 16842912 };
    int j = DialogUtils.resolveColor(paramCheckBox.getContext(), R.attr.colorControlNormal);
    setTint(paramCheckBox, new ColorStateList(new int[][] { { 16842910, -16842912 }, arrayOfInt1, { -16842910, -16842912 }, arrayOfInt2 }, new int[] { j, paramInt, i, i }));
  }
  
  public static void setTint(CheckBox paramCheckBox, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 22)
    {
      paramCheckBox.setButtonTintList(paramColorStateList);
      return;
    }
    Drawable localDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(paramCheckBox.getContext(), R.drawable.abc_btn_check_material));
    DrawableCompat.setTintList(localDrawable, paramColorStateList);
    paramCheckBox.setButtonDrawable(localDrawable);
  }
  
  public static void setTint(EditText paramEditText, int paramInt)
  {
    ColorStateList localColorStateList = createEditTextColorStateList(paramEditText.getContext(), paramInt);
    if ((paramEditText instanceof AppCompatEditText)) {
      ((AppCompatEditText)paramEditText).setSupportBackgroundTintList(localColorStateList);
    } else if (Build.VERSION.SDK_INT >= 21) {
      paramEditText.setBackgroundTintList(localColorStateList);
    }
    init(paramEditText, paramInt);
  }
  
  public static void setTint(ProgressBar paramProgressBar, int paramInt)
  {
    setTint(paramProgressBar, paramInt, false);
  }
  
  private static void setTint(ProgressBar paramProgressBar, int paramInt, boolean paramBoolean)
  {
    Object localObject = ColorStateList.valueOf(paramInt);
    int i = Build.VERSION.SDK_INT;
    if (i >= 21)
    {
      paramProgressBar.setProgressTintList((ColorStateList)localObject);
      paramProgressBar.setSecondaryProgressTintList((ColorStateList)localObject);
      if (!paramBoolean) {
        paramProgressBar.setIndeterminateTintList((ColorStateList)localObject);
      }
    }
    else
    {
      localObject = PorterDuff.Mode.SRC_IN;
      if (i <= 10) {
        localObject = PorterDuff.Mode.MULTIPLY;
      }
      if ((!paramBoolean) && (paramProgressBar.getIndeterminateDrawable() != null)) {
        paramProgressBar.getIndeterminateDrawable().setColorFilter(paramInt, (PorterDuff.Mode)localObject);
      }
      if (paramProgressBar.getProgressDrawable() != null) {
        paramProgressBar.getProgressDrawable().setColorFilter(paramInt, (PorterDuff.Mode)localObject);
      }
    }
  }
  
  public static void setTint(RadioButton paramRadioButton, int paramInt)
  {
    int i = DialogUtils.init(paramRadioButton.getContext());
    int[] arrayOfInt1 = { -16842910, -16842912 };
    int[] arrayOfInt2 = { -16842910, 16842912 };
    int j = DialogUtils.resolveColor(paramRadioButton.getContext(), R.attr.colorControlNormal);
    setTint(paramRadioButton, new ColorStateList(new int[][] { { 16842910, -16842912 }, { 16842910, 16842912 }, arrayOfInt1, arrayOfInt2 }, new int[] { j, paramInt, i, i }));
  }
  
  public static void setTint(RadioButton paramRadioButton, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 22)
    {
      paramRadioButton.setButtonTintList(paramColorStateList);
      return;
    }
    Drawable localDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(paramRadioButton.getContext(), R.drawable.abc_btn_radio_material));
    DrawableCompat.setTintList(localDrawable, paramColorStateList);
    paramRadioButton.setButtonDrawable(localDrawable);
  }
  
  public static void setTint(SeekBar paramSeekBar, int paramInt)
  {
    Object localObject = ColorStateList.valueOf(paramInt);
    int i = Build.VERSION.SDK_INT;
    if (i >= 21)
    {
      paramSeekBar.setThumbTintList((ColorStateList)localObject);
      paramSeekBar.setProgressTintList((ColorStateList)localObject);
      return;
    }
    if (i > 10)
    {
      Drawable localDrawable = DrawableCompat.wrap(paramSeekBar.getProgressDrawable());
      paramSeekBar.setProgressDrawable(localDrawable);
      DrawableCompat.setTintList(localDrawable, (ColorStateList)localObject);
      if (Build.VERSION.SDK_INT >= 16)
      {
        localDrawable = DrawableCompat.wrap(paramSeekBar.getThumb());
        DrawableCompat.setTintList(localDrawable, (ColorStateList)localObject);
        paramSeekBar.setThumb(localDrawable);
      }
      return;
    }
    localObject = PorterDuff.Mode.SRC_IN;
    if (i <= 10) {
      localObject = PorterDuff.Mode.MULTIPLY;
    }
    if (paramSeekBar.getIndeterminateDrawable() != null) {
      paramSeekBar.getIndeterminateDrawable().setColorFilter(paramInt, (PorterDuff.Mode)localObject);
    }
    if (paramSeekBar.getProgressDrawable() != null) {
      paramSeekBar.getProgressDrawable().setColorFilter(paramInt, (PorterDuff.Mode)localObject);
    }
  }
}
