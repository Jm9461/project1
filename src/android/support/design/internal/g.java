package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import org.org.android.R.attr;
import org.org.android.R.styleable;

public final class g
{
  private static final int[] c = { R.attr.colorSecondary };
  private static final int[] d = { R.attr.colorPrimary };
  
  public static void a(Context paramContext)
  {
    a(paramContext, d, "Theme.AppCompat");
  }
  
  private static void a(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ThemeEnforcement, paramInt1, paramInt2);
    boolean bool = paramAttributeSet.getBoolean(R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
    paramAttributeSet.recycle();
    if (bool) {
      b(paramContext);
    }
    a(paramContext);
  }
  
  private static void a(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int... paramVarArgs)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ThemeEnforcement, paramInt1, paramInt2);
    int i = R.styleable.ThemeEnforcement_enforceTextAppearance;
    boolean bool = false;
    if (!localTypedArray.getBoolean(i, false))
    {
      localTypedArray.recycle();
      return;
    }
    if ((paramVarArgs != null) && (paramVarArgs.length != 0)) {
      bool = applyStyle(paramContext, paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2, paramVarArgs);
    } else if (localTypedArray.getResourceId(R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1) {
      bool = true;
    }
    localTypedArray.recycle();
    if (bool) {
      return;
    }
    throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
  }
  
  private static void a(Context paramContext, int[] paramArrayOfInt, String paramString)
  {
    if (a(paramContext, paramArrayOfInt)) {
      return;
    }
    paramContext = new StringBuilder();
    paramContext.append("The style on this component requires your app theme to be ");
    paramContext.append(paramString);
    paramContext.append(" (or a descendant).");
    throw new IllegalArgumentException(paramContext.toString());
  }
  
  private static boolean a(Context paramContext, int[] paramArrayOfInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramArrayOfInt);
    boolean bool = paramContext.hasValue(0);
    paramContext.recycle();
    return bool;
  }
  
  private static boolean applyStyle(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int... paramVarArgs)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2);
    paramInt2 = paramVarArgs.length;
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      if (paramContext.getResourceId(paramVarArgs[paramInt1], -1) == -1)
      {
        paramContext.recycle();
        return false;
      }
      paramInt1 += 1;
    }
    paramContext.recycle();
    return true;
  }
  
  public static void b(Context paramContext)
  {
    a(paramContext, c, "Theme.MaterialComponents");
  }
  
  public static TypedArray obtainStyledAttributes(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int... paramVarArgs)
  {
    a(paramContext, paramAttributeSet, paramInt1, paramInt2);
    a(paramContext, paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2, paramVarArgs);
    return paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2);
  }
  
  public static TintTypedArray obtainStyledAttrsFromThemeAttr(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt1, int paramInt1, int paramInt2, int... paramVarArgs)
  {
    a(paramContext, paramAttributeSet, paramInt1, paramInt2);
    a(paramContext, paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2, paramVarArgs);
    return TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, paramArrayOfInt1, paramInt1, paramInt2);
  }
}
