package org.org.jaxen.text;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.PrecomputedText.Params;
import android.text.PrecomputedText.Params.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import java.util.Locale;
import org.org.jaxen.util.X509LDAPCertStoreParameters;

public final class Place
{
  private final TextPaint a;
  final PrecomputedText.Params dir;
  private final int latitude;
  private final int name;
  private final TextDirectionHeuristic size;
  
  public Place(PrecomputedText.Params paramParams)
  {
    a = paramParams.getTextPaint();
    size = paramParams.getTextDirection();
    name = paramParams.getBreakStrategy();
    latitude = paramParams.getHyphenationFrequency();
    dir = paramParams;
  }
  
  Place(TextPaint paramTextPaint, TextDirectionHeuristic paramTextDirectionHeuristic, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      dir = new PrecomputedText.Params.Builder(paramTextPaint).setBreakStrategy(paramInt1).setHyphenationFrequency(paramInt2).setTextDirection(paramTextDirectionHeuristic).build();
    } else {
      dir = null;
    }
    a = paramTextPaint;
    size = paramTextDirectionHeuristic;
    name = paramInt1;
    latitude = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (paramObject != null)
    {
      if (!(paramObject instanceof Place)) {
        return false;
      }
      paramObject = (Place)paramObject;
      PrecomputedText.Params localParams = dir;
      if (localParams != null) {
        return localParams.equals(dir);
      }
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (name != paramObject.getName()) {
          return false;
        }
        if (latitude != paramObject.getLatitude()) {
          return false;
        }
      }
      if ((Build.VERSION.SDK_INT >= 18) && (size != paramObject.getLongitude())) {
        return false;
      }
      if (a.getTextSize() != paramObject.getValue().getTextSize()) {
        return false;
      }
      if (a.getTextScaleX() != paramObject.getValue().getTextScaleX()) {
        return false;
      }
      if (a.getTextSkewX() != paramObject.getValue().getTextSkewX()) {
        return false;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        if (a.getLetterSpacing() != paramObject.getValue().getLetterSpacing()) {
          return false;
        }
        if (!TextUtils.equals(a.getFontFeatureSettings(), paramObject.getValue().getFontFeatureSettings())) {
          return false;
        }
      }
      if (a.getFlags() != paramObject.getValue().getFlags()) {
        return false;
      }
      int i = Build.VERSION.SDK_INT;
      if (i >= 24)
      {
        if (!a.getTextLocales().equals(paramObject.getValue().getTextLocales())) {
          return false;
        }
      }
      else if ((i >= 17) && (!a.getTextLocale().equals(paramObject.getValue().getTextLocale()))) {
        return false;
      }
      if (a.getTypeface() == null)
      {
        if (paramObject.getValue().getTypeface() != null) {
          return false;
        }
      }
      else
      {
        if (a.getTypeface().equals(paramObject.getValue().getTypeface())) {
          break label335;
        }
        return false;
      }
      return true;
    }
    else
    {
      return false;
    }
    label335:
    return true;
  }
  
  public int getLatitude()
  {
    return latitude;
  }
  
  public TextDirectionHeuristic getLongitude()
  {
    return size;
  }
  
  public int getName()
  {
    return name;
  }
  
  public TextPaint getValue()
  {
    return a;
  }
  
  public int hashCode()
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 24) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Float.valueOf(a.getLetterSpacing()), Integer.valueOf(a.getFlags()), a.getTextLocales(), a.getTypeface(), Boolean.valueOf(a.isElegantTextHeight()), size, Integer.valueOf(name), Integer.valueOf(latitude) });
    }
    if (i >= 21) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Float.valueOf(a.getLetterSpacing()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), Boolean.valueOf(a.isElegantTextHeight()), size, Integer.valueOf(name), Integer.valueOf(latitude) });
    }
    if (i >= 18) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), size, Integer.valueOf(name), Integer.valueOf(latitude) });
    }
    if (i >= 17) {
      return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTextLocale(), a.getTypeface(), size, Integer.valueOf(name), Integer.valueOf(latitude) });
    }
    return X509LDAPCertStoreParameters.hashCode(new Object[] { Float.valueOf(a.getTextSize()), Float.valueOf(a.getTextScaleX()), Float.valueOf(a.getTextSkewX()), Integer.valueOf(a.getFlags()), a.getTypeface(), size, Integer.valueOf(name), Integer.valueOf(latitude) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder("{");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("textSize=");
    localStringBuilder2.append(a.getTextSize());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", textScaleX=");
    localStringBuilder2.append(a.getTextScaleX());
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", textSkewX=");
    localStringBuilder2.append(a.getTextSkewX());
    localStringBuilder1.append(localStringBuilder2.toString());
    if (Build.VERSION.SDK_INT >= 21)
    {
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(", letterSpacing=");
      localStringBuilder2.append(a.getLetterSpacing());
      localStringBuilder1.append(localStringBuilder2.toString());
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(", elegantTextHeight=");
      localStringBuilder2.append(a.isElegantTextHeight());
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    int i = Build.VERSION.SDK_INT;
    if (i >= 24)
    {
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(", textLocale=");
      localStringBuilder2.append(a.getTextLocales());
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    else if (i >= 17)
    {
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(", textLocale=");
      localStringBuilder2.append(a.getTextLocale());
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", typeface=");
    localStringBuilder2.append(a.getTypeface());
    localStringBuilder1.append(localStringBuilder2.toString());
    if (Build.VERSION.SDK_INT >= 26)
    {
      localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(", variationSettings=");
      localStringBuilder2.append(a.getFontVariationSettings());
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", textDir=");
    localStringBuilder2.append(size);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", breakStrategy=");
    localStringBuilder2.append(name);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(", hyphenationFrequency=");
    localStringBuilder2.append(latitude);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append("}");
    return localStringBuilder1.toString();
  }
}
