package android.support.v4.widget;

import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.View;
import java.lang.reflect.Field;
import org.org.jaxen.text.Place;
import org.org.jaxen.text.Segment;
import org.org.jaxen.text.b.a.a;
import org.org.jaxen.util.ClassReader;

public final class TextView
{
  private static Field sMaxModeField;
  private static boolean sMaxModeFieldFetched;
  private static Field sMaximumField;
  private static boolean sMaximumFieldFetched;
  
  public static Place a(android.widget.TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return new Place(paramTextView.getTextMetricsParams());
    }
    b.a.a localA = new b.a.a(new TextPaint(paramTextView.getPaint()));
    if (Build.VERSION.SDK_INT >= 23)
    {
      localA.b(paramTextView.getBreakStrategy());
      localA.a(paramTextView.getHyphenationFrequency());
    }
    if (Build.VERSION.SDK_INT >= 18) {
      localA.b(init(paramTextView));
    }
    return localA.f();
  }
  
  public static void a(android.widget.TextView paramTextView, int paramInt)
  {
    ClassReader.readInt(paramInt);
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramTextView.setFirstBaselineToTopHeight(paramInt);
      return;
    }
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding())) {
      i = ascent;
    } else {
      i = top;
    }
    if (paramInt > Math.abs(i))
    {
      i = -i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramInt - i, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
    }
  }
  
  public static void a(android.widget.TextView paramTextView, Segment paramSegment)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramTextView.setText((CharSequence)paramSegment.append());
      return;
    }
    if (a(paramTextView).equals(paramSegment.getRegion()))
    {
      paramTextView.setText(paramSegment);
      return;
    }
    throw new IllegalArgumentException("Given text can not be applied to TextView.");
  }
  
  public static Drawable[] applyStyle(android.widget.TextView paramTextView)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 18) {
      return paramTextView.getCompoundDrawablesRelative();
    }
    if (i >= 17)
    {
      int j = paramTextView.getLayoutDirection();
      i = 1;
      if (j != 1) {
        i = 0;
      }
      Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
      paramTextView = arrayOfDrawable;
      if (i != 0)
      {
        paramTextView = arrayOfDrawable[2];
        Drawable localDrawable = arrayOfDrawable[0];
        arrayOfDrawable[0] = paramTextView;
        arrayOfDrawable[2] = localDrawable;
        return arrayOfDrawable;
      }
    }
    else
    {
      paramTextView = paramTextView.getCompoundDrawables();
    }
    return paramTextView;
  }
  
  public static int getMaxLines(android.widget.TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramTextView.getMaxLines();
    }
    if (!sMaxModeFieldFetched)
    {
      sMaxModeField = retrieveField("mMaxMode");
      sMaxModeFieldFetched = true;
    }
    Field localField = sMaxModeField;
    if ((localField != null) && (retrieveIntFromField(localField, paramTextView) == 1))
    {
      if (!sMaximumFieldFetched)
      {
        sMaximumField = retrieveField("mMaximum");
        sMaximumFieldFetched = true;
      }
      localField = sMaximumField;
      if (localField != null) {
        return retrieveIntFromField(localField, paramTextView);
      }
    }
    return -1;
  }
  
  private static TextDirectionHeuristic init(android.widget.TextView paramTextView)
  {
    if ((paramTextView.getTransformationMethod() instanceof PasswordTransformationMethod)) {
      return TextDirectionHeuristics.LTR;
    }
    int j = Build.VERSION.SDK_INT;
    int i = 0;
    if ((j >= 28) && ((paramTextView.getInputType() & 0xF) == 3))
    {
      i = Character.getDirectionality(android.icu.text.DecimalFormatSymbols.getInstance(paramTextView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
      if ((i != 1) && (i != 2)) {
        return TextDirectionHeuristics.LTR;
      }
      return TextDirectionHeuristics.RTL;
    }
    if (paramTextView.getLayoutDirection() == 1) {
      i = 1;
    }
    switch (paramTextView.getTextDirection())
    {
    default: 
      if (i != 0) {
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
      }
      break;
    case 7: 
      return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    case 6: 
      return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    case 5: 
      return TextDirectionHeuristics.LOCALE;
    case 4: 
      return TextDirectionHeuristics.RTL;
    case 3: 
      return TextDirectionHeuristics.LTR;
    case 2: 
      return TextDirectionHeuristics.ANYRTL_LTR;
    }
    return TextDirectionHeuristics.FIRSTSTRONG_LTR;
  }
  
  public static ActionMode.Callback init(android.widget.TextView paramTextView, ActionMode.Callback paramCallback)
  {
    int i = Build.VERSION.SDK_INT;
    if ((i >= 26) && (i <= 27))
    {
      if ((paramCallback instanceof ClassWriter)) {
        return paramCallback;
      }
      return new ClassWriter(paramCallback, paramTextView);
    }
    return paramCallback;
  }
  
  public static void init(android.widget.TextView paramTextView, int paramInt)
  {
    ClassReader.readInt(paramInt);
    int i = paramTextView.getPaint().getFontMetricsInt(null);
    if (paramInt != i) {
      paramTextView.setLineSpacing(paramInt - i, 1.0F);
    }
  }
  
  public static void init(android.widget.TextView paramTextView, Place paramPlace)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      paramTextView.setTextDirection(testConnection(paramPlace.getLongitude()));
    }
    if (Build.VERSION.SDK_INT < 23)
    {
      float f = paramPlace.getValue().getTextScaleX();
      paramTextView.getPaint().set(paramPlace.getValue());
      if (f == paramTextView.getTextScaleX()) {
        paramTextView.setTextScaleX(f / 2.0F + 1.0F);
      }
      paramTextView.setTextScaleX(f);
      return;
    }
    paramTextView.getPaint().set(paramPlace.getValue());
    paramTextView.setBreakStrategy(paramPlace.getName());
    paramTextView.setHyphenationFrequency(paramPlace.getLatitude());
  }
  
  private static Field retrieveField(String paramString)
  {
    Object localObject = null;
    try
    {
      Field localField = android.widget.TextView.class.getDeclaredField(paramString);
      localObject = localField;
      localField.setAccessible(true);
      return localField;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not retrieve ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" field.");
      Log.e("TextViewCompat", localStringBuilder.toString());
    }
    return localObject;
  }
  
  private static int retrieveIntFromField(Field paramField, android.widget.TextView paramTextView)
  {
    try
    {
      int i = paramField.getInt(paramTextView);
      return i;
    }
    catch (IllegalAccessException paramTextView)
    {
      paramTextView = new StringBuilder();
      paramTextView.append("Could not retrieve value of ");
      paramTextView.append(paramField.getName());
      paramTextView.append(" field.");
      Log.d("TextViewCompat", paramTextView.toString());
    }
    return -1;
  }
  
  public static void setCompoundDrawablesRelative(android.widget.TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 18)
    {
      paramTextView.setCompoundDrawablesRelative(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
      return;
    }
    if (i >= 17)
    {
      int j = paramTextView.getLayoutDirection();
      i = 1;
      if (j != 1) {
        i = 0;
      }
      Drawable localDrawable;
      if (i != 0) {
        localDrawable = paramDrawable3;
      } else {
        localDrawable = paramDrawable1;
      }
      if (i == 0) {
        paramDrawable1 = paramDrawable3;
      }
      paramTextView.setCompoundDrawables(localDrawable, paramDrawable2, paramDrawable1, paramDrawable4);
      return;
    }
    paramTextView.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static int setText(android.widget.TextView paramTextView)
  {
    return paramTextView.getPaddingBottom() + getPaintgetFontMetricsIntbottom;
  }
  
  public static void setText(android.widget.TextView paramTextView, int paramInt)
  {
    ClassReader.readInt(paramInt);
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding())) {
      i = descent;
    } else {
      i = bottom;
    }
    if (paramInt > Math.abs(i)) {
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramInt - i);
    }
  }
  
  public static void setTextAppearance(android.widget.TextView paramTextView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      paramTextView.setTextAppearance(paramInt);
      return;
    }
    paramTextView.setTextAppearance(paramTextView.getContext(), paramInt);
  }
  
  private static int testConnection(TextDirectionHeuristic paramTextDirectionHeuristic)
  {
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
      return 1;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
      return 1;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
      return 2;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LTR) {
      return 3;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.RTL) {
      return 4;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
      return 5;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
      return 6;
    }
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
      return 7;
    }
    return 1;
  }
  
  public static int updatePadding(android.widget.TextView paramTextView)
  {
    return paramTextView.getPaddingTop() - getPaintgetFontMetricsInttop;
  }
}
