package butterknife.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

public final class Collection4
{
  static
  {
    new TypedValue();
  }
  
  public static View get(View paramView, int paramInt, String paramString)
  {
    Object localObject = paramView.findViewById(paramInt);
    if (localObject != null) {
      return localObject;
    }
    paramView = getString(paramView, paramInt);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Required view '");
    ((StringBuilder)localObject).append(paramView);
    ((StringBuilder)localObject).append("' with ID ");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append(" for ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public static Object get(View paramView, int paramInt, String paramString, Class paramClass)
  {
    try
    {
      paramClass = paramClass.cast(paramView);
      return paramClass;
    }
    catch (ClassCastException paramClass)
    {
      paramView = getString(paramView, paramInt);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View '");
      localStringBuilder.append(paramView);
      localStringBuilder.append("' with ID ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" for ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" was of the wrong type. See cause for more info.");
      throw new IllegalStateException(localStringBuilder.toString(), paramClass);
    }
  }
  
  private static String getString(View paramView, int paramInt)
  {
    if (paramView.isInEditMode()) {
      return "<unavailable while editing>";
    }
    return paramView.getContext().getResources().getResourceEntryName(paramInt);
  }
  
  public static Object replace(View paramView, int paramInt, String paramString, Class paramClass)
  {
    return get(get(paramView, paramInt, paramString), paramInt, paramString, paramClass);
  }
}
