package apps.afollestad.materialdialogs.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;

public class DialogUtils
{
  public static boolean a(Object paramObject, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      if (paramArrayOfObject.length == 0) {
        return false;
      }
      int j = paramArrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfObject[i].equals(paramObject)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static int adjustAlpha(int paramInt, float paramFloat)
  {
    return Color.argb(Math.round(Color.alpha(paramInt) * paramFloat), Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  public static ColorStateList getActionTextColorStateList(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getResources().getValue(paramInt, localTypedValue, true);
    int i = type;
    if ((i >= 28) && (i <= 31)) {
      return getActionTextStateList(paramContext, data);
    }
    if (Build.VERSION.SDK_INT <= 22) {
      return paramContext.getResources().getColorStateList(paramInt);
    }
    return paramContext.getColorStateList(paramInt);
  }
  
  public static ColorStateList getActionTextStateList(Context paramContext, int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0) {
      i = resolveColor(paramContext, 16842806);
    }
    paramInt = adjustAlpha(i, 0.4F);
    return new ColorStateList(new int[][] { { -16842910 }, new int[0] }, new int[] { paramInt, i });
  }
  
  public static int getColor(Context paramContext, int paramInt)
  {
    return ContextCompat.getColor(paramContext, paramInt);
  }
  
  private static int gravityEnumToAttrInt(GravityEnum paramGravityEnum)
  {
    int i = 2.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[paramGravityEnum.ordinal()];
    if (i != 1)
    {
      if (i != 2) {
        return 0;
      }
      return 2;
    }
    return 1;
  }
  
  public static void hideKeyboard(DialogInterface paramDialogInterface, MaterialDialog.Builder paramBuilder)
  {
    MaterialDialog localMaterialDialog = (MaterialDialog)paramDialogInterface;
    if (localMaterialDialog.getInputEditText() == null) {
      return;
    }
    paramBuilder = (InputMethodManager)paramBuilder.getContext().getSystemService("input_method");
    if (paramBuilder != null)
    {
      View localView = localMaterialDialog.getCurrentFocus();
      paramDialogInterface = null;
      if (localView != null) {
        paramDialogInterface = localView.getWindowToken();
      } else if (localMaterialDialog.getView() != null) {
        paramDialogInterface = localMaterialDialog.getView().getWindowToken();
      }
      if (paramDialogInterface != null) {
        paramBuilder.hideSoftInputFromWindow(paramDialogInterface, 0);
      }
    }
  }
  
  public static int init(Context paramContext)
  {
    int i;
    if (isColorDark(resolveColor(paramContext, 16842806))) {
      i = -16777216;
    } else {
      i = -1;
    }
    return adjustAlpha(i, 0.3F);
  }
  
  public static boolean isColorDark(int paramInt)
  {
    double d1 = Color.red(paramInt);
    Double.isNaN(d1);
    double d2 = Color.green(paramInt);
    Double.isNaN(d2);
    double d3 = Color.blue(paramInt);
    Double.isNaN(d3);
    return 1.0D - (d1 * 0.299D + d2 * 0.587D + d3 * 0.114D) / 255.0D >= 0.5D;
  }
  
  public static ColorStateList resolveActionTextColorStateList(Context paramContext, int paramInt, ColorStateList paramColorStateList)
  {
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt });
    try
    {
      TypedValue localTypedValue = localTypedArray.peekValue(0);
      if (localTypedValue == null)
      {
        localTypedArray.recycle();
        return paramColorStateList;
      }
      paramInt = type;
      if (paramInt >= 28)
      {
        paramInt = type;
        if (paramInt <= 31)
        {
          paramContext = getActionTextStateList(paramContext, data);
          localTypedArray.recycle();
          return paramContext;
        }
      }
      paramContext = localTypedArray.getColorStateList(0);
      if (paramContext != null)
      {
        localTypedArray.recycle();
        return paramContext;
      }
      localTypedArray.recycle();
      return paramColorStateList;
    }
    catch (Throwable paramContext)
    {
      localTypedArray.recycle();
      throw paramContext;
    }
  }
  
  public static boolean resolveBoolean(Context paramContext, int paramInt)
  {
    return resolveBoolean(paramContext, paramInt, false);
  }
  
  public static boolean resolveBoolean(Context paramContext, int paramInt, boolean paramBoolean)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt });
    try
    {
      paramBoolean = paramContext.getBoolean(0, paramBoolean);
      paramContext.recycle();
      return paramBoolean;
    }
    catch (Throwable localThrowable)
    {
      paramContext.recycle();
      throw localThrowable;
    }
  }
  
  public static int resolveColor(Context paramContext, int paramInt)
  {
    return resolveColor(paramContext, paramInt, 0);
  }
  
  public static int resolveColor(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt1 });
    try
    {
      paramInt1 = paramContext.getColor(0, paramInt2);
      paramContext.recycle();
      return paramInt1;
    }
    catch (Throwable localThrowable)
    {
      paramContext.recycle();
      throw localThrowable;
    }
  }
  
  public static int resolveDimension(Context paramContext, int paramInt)
  {
    return resolveDimension(paramContext, paramInt, -1);
  }
  
  private static int resolveDimension(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt1 });
    try
    {
      paramInt1 = paramContext.getDimensionPixelSize(0, paramInt2);
      paramContext.recycle();
      return paramInt1;
    }
    catch (Throwable localThrowable)
    {
      paramContext.recycle();
      throw localThrowable;
    }
  }
  
  public static Drawable resolveDrawable(Context paramContext, int paramInt)
  {
    return resolveDrawable(paramContext, paramInt, null);
  }
  
  private static Drawable resolveDrawable(Context paramContext, int paramInt, Drawable paramDrawable)
  {
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt });
    try
    {
      Drawable localDrawable = localTypedArray.getDrawable(0);
      paramContext = localDrawable;
      Object localObject = paramContext;
      if (localDrawable == null)
      {
        localObject = paramContext;
        if (paramDrawable != null) {
          localObject = paramDrawable;
        }
      }
      localTypedArray.recycle();
      return localObject;
    }
    catch (Throwable paramContext)
    {
      localTypedArray.recycle();
      throw paramContext;
    }
  }
  
  public static GravityEnum resolveGravityEnum(Context paramContext, int paramInt, GravityEnum paramGravityEnum)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { paramInt });
    try
    {
      paramInt = paramContext.getInt(0, gravityEnumToAttrInt(paramGravityEnum));
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          paramGravityEnum = GravityEnum.START;
          paramContext.recycle();
          return paramGravityEnum;
        }
        paramGravityEnum = GravityEnum.END;
        paramContext.recycle();
        return paramGravityEnum;
      }
      paramGravityEnum = GravityEnum.a;
      paramContext.recycle();
      return paramGravityEnum;
    }
    catch (Throwable paramGravityEnum)
    {
      paramContext.recycle();
      throw paramGravityEnum;
    }
  }
  
  public static String resolveString(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    return (String)string;
  }
  
  public static void setBackgroundCompat(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  public static void showKeyboard(DialogInterface paramDialogInterface, MaterialDialog.Builder paramBuilder)
  {
    paramDialogInterface = (MaterialDialog)paramDialogInterface;
    if (paramDialogInterface.getInputEditText() == null) {
      return;
    }
    paramDialogInterface.getInputEditText().post(new DialogUtils.1(paramDialogInterface, paramBuilder));
  }
}
