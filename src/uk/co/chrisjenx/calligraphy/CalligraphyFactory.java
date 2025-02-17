package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.Map;

class CalligraphyFactory
{
  private static final String ACTION_BAR_SUBTITLE = "action_bar_subtitle";
  private static final String ACTION_BAR_TITLE = "action_bar_title";
  private final int[] mAttributeId;
  
  public CalligraphyFactory(int paramInt)
  {
    mAttributeId = new int[] { paramInt };
  }
  
  private void applyFontToToolbar(Toolbar paramToolbar)
  {
    CharSequence localCharSequence1 = paramToolbar.getTitle();
    CharSequence localCharSequence2 = paramToolbar.getSubtitle();
    paramToolbar.setTitle(" ");
    paramToolbar.setSubtitle(" ");
    int j = paramToolbar.getChildCount();
    int i = 0;
    while (i < j)
    {
      onViewCreated(paramToolbar.getChildAt(i), paramToolbar.getContext(), null);
      i += 1;
    }
    paramToolbar.setTitle(localCharSequence1);
    paramToolbar.setSubtitle(localCharSequence2);
  }
  
  private Typeface getDefaultTypeface(Context paramContext, String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = CalligraphyConfig.get().getFontPath();
    }
    if (!TextUtils.isEmpty(str)) {
      return TypefaceUtils.load(paramContext.getAssets(), str);
    }
    return null;
  }
  
  protected static int[] getStyleForTextView(TextView paramTextView)
  {
    int[] arrayOfInt = new int[2];
    int[] tmp5_4 = arrayOfInt;
    tmp5_4[0] = -1;
    int[] tmp9_5 = tmp5_4;
    tmp9_5[1] = -1;
    tmp9_5;
    if (isActionBarTitle(paramTextView))
    {
      arrayOfInt[0] = 16843470;
      arrayOfInt[1] = 16843512;
    }
    else if (isActionBarSubTitle(paramTextView))
    {
      arrayOfInt[0] = 16843470;
      arrayOfInt[1] = 16843513;
    }
    if (arrayOfInt[0] == -1)
    {
      int i;
      if (CalligraphyConfig.get().getClassStyles().containsKey(paramTextView.getClass())) {
        i = ((Integer)CalligraphyConfig.get().getClassStyles().get(paramTextView.getClass())).intValue();
      } else {
        i = 16842804;
      }
      arrayOfInt[0] = i;
    }
    return arrayOfInt;
  }
  
  protected static boolean isActionBarSubTitle(TextView paramTextView)
  {
    if (matchesResourceIdName(paramTextView, "action_bar_subtitle")) {
      return true;
    }
    if (parentIsToolbarV7(paramTextView)) {
      return TextUtils.equals(((Toolbar)paramTextView.getParent()).getSubtitle(), paramTextView.getText());
    }
    return false;
  }
  
  protected static boolean isActionBarTitle(TextView paramTextView)
  {
    if (matchesResourceIdName(paramTextView, "action_bar_title")) {
      return true;
    }
    if (parentIsToolbarV7(paramTextView)) {
      return TextUtils.equals(((Toolbar)paramTextView.getParent()).getTitle(), paramTextView.getText());
    }
    return false;
  }
  
  protected static boolean matchesResourceIdName(View paramView, String paramString)
  {
    if (paramView.getId() == -1) {
      return false;
    }
    return paramView.getResources().getResourceEntryName(paramView.getId()).equalsIgnoreCase(paramString);
  }
  
  protected static boolean parentIsToolbarV7(View paramView)
  {
    return (CalligraphyUtils.canCheckForV7Toolbar()) && (paramView.getParent() != null) && ((paramView.getParent() instanceof Toolbar));
  }
  
  private String resolveFontPath(Context paramContext, AttributeSet paramAttributeSet)
  {
    String str2 = CalligraphyUtils.pullFontPathFromView(paramContext, paramAttributeSet, mAttributeId);
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = CalligraphyUtils.pullFontPathFromStyle(paramContext, paramAttributeSet, mAttributeId);
    }
    if (TextUtils.isEmpty(str1)) {
      return CalligraphyUtils.pullFontPathFromTextAppearance(paramContext, paramAttributeSet, mAttributeId);
    }
    return str1;
  }
  
  public View onViewCreated(View paramView, Context paramContext, AttributeSet paramAttributeSet)
  {
    if ((paramView != null) && (paramView.getTag(R.id.calligraphy_tag_id) != Boolean.TRUE))
    {
      onViewCreatedInternal(paramView, paramContext, paramAttributeSet);
      paramView.setTag(R.id.calligraphy_tag_id, Boolean.TRUE);
    }
    return paramView;
  }
  
  void onViewCreatedInternal(View paramView, Context paramContext, AttributeSet paramAttributeSet)
  {
    Object localObject;
    if ((paramView instanceof TextView))
    {
      if (TypefaceUtils.isLoaded(((TextView)paramView).getTypeface())) {
        return;
      }
      String str = resolveFontPath(paramContext, paramAttributeSet);
      localObject = str;
      if (TextUtils.isEmpty(str))
      {
        localObject = getStyleForTextView((TextView)paramView);
        if (localObject[1] != -1) {
          localObject = CalligraphyUtils.pullFontPathFromTheme(paramContext, localObject[0], localObject[1], mAttributeId);
        } else {
          localObject = CalligraphyUtils.pullFontPathFromTheme(paramContext, localObject[0], mAttributeId);
        }
      }
      boolean bool;
      if ((!matchesResourceIdName(paramView, "action_bar_title")) && (!matchesResourceIdName(paramView, "action_bar_subtitle"))) {
        bool = false;
      } else {
        bool = true;
      }
      CalligraphyUtils.applyFontToTextView(paramContext, (TextView)paramView, CalligraphyConfig.get(), (String)localObject, bool);
    }
    if ((CalligraphyUtils.canCheckForV7Toolbar()) && ((paramView instanceof Toolbar))) {
      applyFontToToolbar((Toolbar)paramView);
    }
    if ((paramView instanceof HasTypeface))
    {
      paramContext = getDefaultTypeface(paramContext, resolveFontPath(paramContext, paramAttributeSet));
      if (paramContext != null) {
        ((HasTypeface)paramView).setTypeface(paramContext);
      }
    }
    else if ((CalligraphyConfig.get().isCustomViewTypefaceSupport()) && (CalligraphyConfig.get().isCustomViewHasTypeface(paramView)))
    {
      localObject = ReflectionUtils.getMethod(paramView.getClass(), "setTypeface");
      paramContext = getDefaultTypeface(paramContext, resolveFontPath(paramContext, paramAttributeSet));
      if ((localObject != null) && (paramContext != null)) {
        ReflectionUtils.invokeMethod(paramView, (Method)localObject, new Object[] { paramContext });
      }
    }
  }
}
