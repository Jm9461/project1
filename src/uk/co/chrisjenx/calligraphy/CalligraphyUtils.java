package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public final class CalligraphyUtils
{
  public static final int[] ANDROID_ATTR_TEXT_APPEARANCE = { 16842804 };
  private static Boolean sAppCompatViewCheck = null;
  private static Boolean sToolbarCheck = null;
  
  private CalligraphyUtils() {}
  
  static void applyFontToTextView(Context paramContext, TextView paramTextView, CalligraphyConfig paramCalligraphyConfig)
  {
    applyFontToTextView(paramContext, paramTextView, paramCalligraphyConfig, false);
  }
  
  public static void applyFontToTextView(Context paramContext, TextView paramTextView, CalligraphyConfig paramCalligraphyConfig, String paramString)
  {
    applyFontToTextView(paramContext, paramTextView, paramCalligraphyConfig, paramString, false);
  }
  
  static void applyFontToTextView(Context paramContext, TextView paramTextView, CalligraphyConfig paramCalligraphyConfig, String paramString, boolean paramBoolean)
  {
    if ((paramContext != null) && (paramTextView != null))
    {
      if (paramCalligraphyConfig == null) {
        return;
      }
      if ((!TextUtils.isEmpty(paramString)) && (applyFontToTextView(paramContext, paramTextView, paramString, paramBoolean))) {
        return;
      }
      applyFontToTextView(paramContext, paramTextView, paramCalligraphyConfig, paramBoolean);
    }
  }
  
  static void applyFontToTextView(Context paramContext, TextView paramTextView, CalligraphyConfig paramCalligraphyConfig, boolean paramBoolean)
  {
    if ((paramContext != null) && (paramTextView != null))
    {
      if (paramCalligraphyConfig == null) {
        return;
      }
      if (!paramCalligraphyConfig.isFontSet()) {
        return;
      }
      applyFontToTextView(paramContext, paramTextView, paramCalligraphyConfig.getFontPath(), paramBoolean);
    }
  }
  
  public static boolean applyFontToTextView(Context paramContext, TextView paramTextView, String paramString)
  {
    return applyFontToTextView(paramContext, paramTextView, paramString, false);
  }
  
  static boolean applyFontToTextView(Context paramContext, TextView paramTextView, String paramString, boolean paramBoolean)
  {
    if ((paramTextView != null) && (paramContext != null)) {
      return applyFontToTextView(paramTextView, TypefaceUtils.load(paramContext.getAssets(), paramString), paramBoolean);
    }
    return false;
  }
  
  public static boolean applyFontToTextView(TextView paramTextView, Typeface paramTypeface)
  {
    return applyFontToTextView(paramTextView, paramTypeface, false);
  }
  
  public static boolean applyFontToTextView(TextView paramTextView, Typeface paramTypeface, boolean paramBoolean)
  {
    if ((paramTextView != null) && (paramTypeface != null))
    {
      paramTextView.setPaintFlags(paramTextView.getPaintFlags() | 0x80 | 0x1);
      paramTextView.setTypeface(paramTypeface);
      if (paramBoolean)
      {
        paramTextView.setText(applyTypefaceSpan(paramTextView.getText(), paramTypeface), TextView.BufferType.SPANNABLE);
        paramTextView.addTextChangedListener(new TextWatcher()
        {
          public void afterTextChanged(Editable paramAnonymousEditable)
          {
            CalligraphyUtils.applyTypefaceSpan(paramAnonymousEditable, val$typeface);
          }
          
          public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
          
          public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        });
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public static CharSequence applyTypefaceSpan(CharSequence paramCharSequence, Typeface paramTypeface)
  {
    if (paramCharSequence != null)
    {
      if (((CharSequence)paramCharSequence).length() > 0)
      {
        Object localObject = paramCharSequence;
        if (!(paramCharSequence instanceof Spannable)) {
          localObject = new SpannableString((CharSequence)paramCharSequence);
        }
        ((Spannable)localObject).setSpan(TypefaceUtils.getSpan(paramTypeface), 0, ((CharSequence)localObject).length(), 33);
        return (CharSequence)localObject;
      }
    }
    else {
      return (CharSequence)paramCharSequence;
    }
    return (CharSequence)paramCharSequence;
  }
  
  static boolean canAddV7AppCompatViews()
  {
    if (sAppCompatViewCheck == null) {
      try
      {
        Class.forName("android.support.v7.widget.AppCompatTextView");
        sAppCompatViewCheck = Boolean.TRUE;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        sAppCompatViewCheck = Boolean.FALSE;
      }
    }
    return sAppCompatViewCheck.booleanValue();
  }
  
  static boolean canCheckForV7Toolbar()
  {
    if (sToolbarCheck == null) {
      try
      {
        Class.forName("android.support.v7.widget.Toolbar");
        sToolbarCheck = Boolean.TRUE;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        sToolbarCheck = Boolean.FALSE;
      }
    }
    return sToolbarCheck.booleanValue();
  }
  
  static String pullFontPathFromStyle(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      if (paramAttributeSet == null) {
        return null;
      }
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt);
      if (paramContext != null)
      {
        try
        {
          paramAttributeSet = paramContext.getString(0);
          boolean bool = TextUtils.isEmpty(paramAttributeSet);
          if (!bool)
          {
            paramContext.recycle();
            return paramAttributeSet;
          }
        }
        catch (Throwable paramAttributeSet)
        {
          paramContext.recycle();
          throw paramAttributeSet;
        }
        catch (Exception paramAttributeSet) {}
        paramContext.recycle();
      }
    }
    return null;
  }
  
  static String pullFontPathFromTextAppearance(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      if (paramAttributeSet == null) {
        return null;
      }
      int i = -1;
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, ANDROID_ATTR_TEXT_APPEARANCE);
      if (paramAttributeSet != null) {
        try
        {
          i = paramAttributeSet.getResourceId(0, -1);
          paramAttributeSet.recycle();
        }
        catch (Throwable paramContext)
        {
          paramAttributeSet.recycle();
          throw paramContext;
        }
        catch (Exception paramContext)
        {
          paramAttributeSet.recycle();
          return null;
        }
      }
      paramContext = paramContext.obtainStyledAttributes(i, paramArrayOfInt);
      if (paramContext != null) {
        try
        {
          paramAttributeSet = paramContext.getString(0);
          paramContext.recycle();
          return paramAttributeSet;
        }
        catch (Throwable paramAttributeSet)
        {
          paramContext.recycle();
          throw paramAttributeSet;
        }
        catch (Exception paramAttributeSet)
        {
          paramContext.recycle();
        }
      }
    }
    return null;
  }
  
  static String pullFontPathFromTheme(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if (paramInt1 != -1)
    {
      if (paramArrayOfInt == null) {
        return null;
      }
      Object localObject = paramContext.getTheme();
      TypedValue localTypedValue = new TypedValue();
      ((Resources.Theme)localObject).resolveAttribute(paramInt1, localTypedValue, true);
      localObject = ((Resources.Theme)localObject).obtainStyledAttributes(resourceId, new int[] { paramInt2 });
      try
      {
        paramInt1 = ((TypedArray)localObject).getResourceId(0, -1);
        ((TypedArray)localObject).recycle();
        if (paramInt1 == -1) {
          return null;
        }
        paramContext = paramContext.obtainStyledAttributes(paramInt1, paramArrayOfInt);
        if (paramContext != null) {
          try
          {
            paramArrayOfInt = paramContext.getString(0);
            paramContext.recycle();
            return paramArrayOfInt;
          }
          catch (Throwable paramArrayOfInt)
          {
            paramContext.recycle();
            throw paramArrayOfInt;
          }
          catch (Exception paramArrayOfInt)
          {
            paramContext.recycle();
          }
        }
        return null;
      }
      catch (Throwable paramContext)
      {
        ((TypedArray)localObject).recycle();
        throw paramContext;
      }
      catch (Exception paramContext)
      {
        ((TypedArray)localObject).recycle();
      }
    }
    return null;
  }
  
  static String pullFontPathFromTheme(Context paramContext, int paramInt, int[] paramArrayOfInt)
  {
    if (paramInt != -1)
    {
      if (paramArrayOfInt == null) {
        return null;
      }
      paramContext = paramContext.getTheme();
      TypedValue localTypedValue = new TypedValue();
      paramContext.resolveAttribute(paramInt, localTypedValue, true);
      paramContext = paramContext.obtainStyledAttributes(resourceId, paramArrayOfInt);
      try
      {
        paramArrayOfInt = paramContext.getString(0);
        paramContext.recycle();
        return paramArrayOfInt;
      }
      catch (Throwable paramArrayOfInt)
      {
        paramContext.recycle();
        throw paramArrayOfInt;
      }
      catch (Exception paramArrayOfInt)
      {
        paramContext.recycle();
      }
    }
    return null;
  }
  
  static String pullFontPathFromView(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      if (paramAttributeSet == null) {
        return null;
      }
      try
      {
        Resources localResources = paramContext.getResources();
        int i = paramArrayOfInt[0];
        paramArrayOfInt = localResources.getResourceEntryName(i);
        i = paramAttributeSet.getAttributeResourceValue(null, paramArrayOfInt, -1);
        if (i > 0) {
          return paramContext.getString(i);
        }
        return paramAttributeSet.getAttributeValue(null, paramArrayOfInt);
      }
      catch (Resources.NotFoundException paramContext) {}
    }
    return null;
  }
}
