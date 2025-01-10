package android.support.v7.widget;

import a.b.g.g.a;
import a.b.g.g.o;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.org.jaxen.asm.ColorUtils;
import org.org.jaxen.util.ClassWriter;
import org.org.jaxen.util.Label;
import org.org.jaxen.util.LongSparseArray;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.color;
import org.org.v4.content.R.drawable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AppCompatDrawableManager
{
  private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
  private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
  private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
  private static final TintManager.ColorFilterLruCache COLOR_FILTER_CACHE;
  private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
  private static AppCompatDrawableManager INSTANCE;
  private static final int[] TINT_CHECKABLE_BUTTON_LIST = { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
  private static final int[] TINT_COLOR_CONTROL_NORMAL;
  private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
  private a<String, j.d> mDelegates;
  private final WeakHashMap<Context, a.b.g.g.f<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap(0);
  private o<String> mKnownDrawableIdTags;
  private WeakHashMap<Context, o<ColorStateList>> mTintLists;
  private TypedValue mTypedValue;
  private boolean multiline;
  
  static
  {
    COLOR_FILTER_CACHE = new TintManager.ColorFilterLruCache(6);
    COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
    TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
    COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light };
    COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
    TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material };
  }
  
  public AppCompatDrawableManager() {}
  
  private void addDelegate(String paramString, InflateDelegate paramInflateDelegate)
  {
    if (mDelegates == null) {
      mDelegates = new Label();
    }
    mDelegates.put(paramString, paramInflateDelegate);
  }
  
  private boolean addDrawableToCache(Context paramContext, long paramLong, Drawable paramDrawable)
  {
    try
    {
      Drawable.ConstantState localConstantState = paramDrawable.getConstantState();
      if (localConstantState != null)
      {
        LongSparseArray localLongSparseArray = (LongSparseArray)mDrawableCaches.get(paramContext);
        paramDrawable = localLongSparseArray;
        if (localLongSparseArray == null)
        {
          localLongSparseArray = new LongSparseArray();
          paramDrawable = localLongSparseArray;
          mDrawableCaches.put(paramContext, localLongSparseArray);
        }
        paramDrawable.put(paramLong, new WeakReference(localConstantState));
        return true;
      }
      return false;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private void addTintListToCache(Context paramContext, int paramInt, ColorStateList paramColorStateList)
  {
    if (mTintLists == null) {
      mTintLists = new WeakHashMap();
    }
    ClassWriter localClassWriter2 = (ClassWriter)mTintLists.get(paramContext);
    ClassWriter localClassWriter1 = localClassWriter2;
    if (localClassWriter2 == null)
    {
      localClassWriter2 = new ClassWriter();
      localClassWriter1 = localClassWriter2;
      mTintLists.put(paramContext, localClassWriter2);
    }
    localClassWriter1.a(paramInt, paramColorStateList);
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private ColorStateList createBorderlessButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, 0);
  }
  
  private ColorStateList createButtonColorStateList(Context paramContext, int paramInt)
  {
    int[][] arrayOfInt = new int[4][];
    int[] arrayOfInt1 = new int[4];
    int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    int j = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = j;
    j = 0 + 1;
    arrayOfInt[j] = ThemeUtils.PRESSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    j += 1;
    arrayOfInt[j] = ThemeUtils.FOCUSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    i = j + 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = paramInt;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static long createCacheKey(TypedValue paramTypedValue)
  {
    return assetCookie << 32 | data;
  }
  
  private ColorStateList createColoredButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorAccent));
  }
  
  private ColorStateList createDefaultButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorButtonNormal));
  }
  
  private Drawable createDrawableIfNeeded(Context paramContext, int paramInt)
  {
    if (mTypedValue == null) {
      mTypedValue = new TypedValue();
    }
    TypedValue localTypedValue = mTypedValue;
    paramContext.getResources().getValue(paramInt, localTypedValue, true);
    long l = createCacheKey(localTypedValue);
    Drawable localDrawable = getCachedDrawable(paramContext, l);
    Object localObject = localDrawable;
    if (localDrawable != null) {
      return localDrawable;
    }
    if (paramInt == R.drawable.abc_cab_background_top_material) {
      localObject = new LayerDrawable(new Drawable[] { getDrawable(paramContext, R.drawable.abc_cab_background_internal_bg), getDrawable(paramContext, R.drawable.abc_cab_background_top_mtrl_alpha) });
    }
    if (localObject != null)
    {
      ((Drawable)localObject).setChangingConfigurations(changingConfigurations);
      addDrawableToCache(paramContext, l, (Drawable)localObject);
    }
    return localObject;
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    ColorStateList localColorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    int i;
    if ((localColorStateList != null) && (localColorStateList.isStateful()))
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = localColorStateList.getColorForState(arrayOfInt[0], 0);
      i = 0 + 1;
      arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      i += 1;
      arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[i] = localColorStateList.getDefaultColor();
    }
    else
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
      i = 0 + 1;
      arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      i += 1;
      arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    }
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int[] paramArrayOfInt)
  {
    if ((paramColorStateList != null) && (paramMode != null)) {
      return getPorterDuffColorFilter(paramColorStateList.getColorForState(paramArrayOfInt, 0), paramMode);
    }
    return null;
  }
  
  public static AppCompatDrawableManager get()
  {
    try
    {
      if (INSTANCE == null)
      {
        INSTANCE = new AppCompatDrawableManager();
        installDefaultInflateDelegates(INSTANCE);
      }
      AppCompatDrawableManager localAppCompatDrawableManager = INSTANCE;
      return localAppCompatDrawableManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private void get(Context paramContext)
  {
    if (multiline) {
      return;
    }
    multiline = true;
    paramContext = getDrawable(paramContext, R.drawable.abc_vector_test);
    if ((paramContext != null) && (isVectorDrawable(paramContext))) {
      return;
    }
    multiline = false;
    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
  }
  
  private Drawable getCachedDrawable(Context paramContext, long paramLong)
  {
    try
    {
      LongSparseArray localLongSparseArray = (LongSparseArray)mDrawableCaches.get(paramContext);
      if (localLongSparseArray == null) {
        return null;
      }
      Object localObject = (WeakReference)localLongSparseArray.get(paramLong);
      if (localObject != null)
      {
        localObject = (Drawable.ConstantState)((WeakReference)localObject).get();
        if (localObject != null)
        {
          paramContext = ((Drawable.ConstantState)localObject).newDrawable(paramContext.getResources());
          return paramContext;
        }
        localLongSparseArray.delete(paramLong);
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static PorterDuffColorFilter getPorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    try
    {
      PorterDuffColorFilter localPorterDuffColorFilter2 = COLOR_FILTER_CACHE.get(paramInt, paramMode);
      PorterDuffColorFilter localPorterDuffColorFilter1 = localPorterDuffColorFilter2;
      if (localPorterDuffColorFilter2 == null)
      {
        localPorterDuffColorFilter2 = new PorterDuffColorFilter(paramInt, paramMode);
        localPorterDuffColorFilter1 = localPorterDuffColorFilter2;
        COLOR_FILTER_CACHE.put(paramInt, paramMode, localPorterDuffColorFilter2);
      }
      return localPorterDuffColorFilter1;
    }
    catch (Throwable paramMode)
    {
      throw paramMode;
    }
  }
  
  private ColorStateList getTintListFromCache(Context paramContext, int paramInt)
  {
    WeakHashMap localWeakHashMap = mTintLists;
    if (localWeakHashMap != null)
    {
      paramContext = (ClassWriter)localWeakHashMap.get(paramContext);
      if (paramContext != null) {
        return (ColorStateList)paramContext.remove(paramInt);
      }
    }
    return null;
  }
  
  static PorterDuff.Mode getTintMode(int paramInt)
  {
    if (paramInt == R.drawable.abc_switch_thumb_material) {
      return PorterDuff.Mode.MULTIPLY;
    }
    return null;
  }
  
  private static void installDefaultInflateDelegates(AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    if (Build.VERSION.SDK_INT < 24)
    {
      paramAppCompatDrawableManager.addDelegate("vector", new VdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-vector", new ServiceController());
      paramAppCompatDrawableManager.addDelegate("animated-selector", new AvdcInflateDelegate());
    }
  }
  
  private static boolean isVectorDrawable(Drawable paramDrawable)
  {
    return ((paramDrawable instanceof org.org.jraf.drawable.VectorDrawableCompat)) || ("android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()));
  }
  
  private Drawable loadDrawableFromDelegates(Context paramContext, int paramInt)
  {
    Object localObject1 = mDelegates;
    Object localObject2;
    if ((localObject1 != null) && (!((org.org.jaxen.util.f)localObject1).isEmpty()))
    {
      localObject1 = mKnownDrawableIdTags;
      if (localObject1 != null)
      {
        localObject1 = (String)((ClassWriter)localObject1).remove(paramInt);
        if (!"appcompat_skip_skip".equals(localObject1))
        {
          if ((localObject1 != null) && (mDelegates.get(localObject1) == null)) {
            return null;
          }
        }
        else {
          return null;
        }
      }
      else
      {
        mKnownDrawableIdTags = new ClassWriter();
      }
      if (mTypedValue == null) {
        mTypedValue = new TypedValue();
      }
      TypedValue localTypedValue = mTypedValue;
      Object localObject4 = paramContext.getResources();
      ((android.content.res.Resources)localObject4).getValue(paramInt, localTypedValue, true);
      long l = createCacheKey(localTypedValue);
      localObject2 = getCachedDrawable(paramContext, l);
      localObject1 = localObject2;
      if (localObject2 != null) {
        return localObject2;
      }
      Object localObject3 = string;
      localObject2 = localObject1;
      if (localObject3 != null)
      {
        localObject2 = localObject1;
        if (((CharSequence)localObject3).toString().endsWith(".xml"))
        {
          localObject3 = localObject1;
          try
          {
            localObject4 = ((android.content.res.Resources)localObject4).getXml(paramInt);
            localObject3 = localObject1;
            AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject4);
            int i;
            do
            {
              localObject3 = localObject1;
              i = ((XmlPullParser)localObject4).next();
            } while ((i != 2) && (i != 1));
            if (i == 2)
            {
              localObject3 = localObject1;
              localObject2 = ((XmlPullParser)localObject4).getName();
              Object localObject5 = mKnownDrawableIdTags;
              localObject3 = localObject1;
              ((ClassWriter)localObject5).a(paramInt, localObject2);
              localObject5 = mDelegates;
              localObject3 = localObject1;
              localObject2 = ((org.org.jaxen.util.f)localObject5).get(localObject2);
              localObject5 = (InflateDelegate)localObject2;
              localObject2 = localObject1;
              if (localObject5 != null)
              {
                localObject3 = localObject1;
                localObject2 = ((InflateDelegate)localObject5).createFromXmlInner(paramContext, (XmlPullParser)localObject4, localAttributeSet, paramContext.getTheme());
              }
              if (localObject2 != null)
              {
                i = changingConfigurations;
                localObject3 = localObject2;
                ((Drawable)localObject2).setChangingConfigurations(i);
                localObject3 = localObject2;
                addDrawableToCache(paramContext, l, (Drawable)localObject2);
              }
            }
            else
            {
              localObject3 = localObject1;
              paramContext = new XmlPullParserException("No start tag found");
              throw paramContext;
            }
          }
          catch (Exception paramContext)
          {
            Log.e("AppCompatDrawableManag", "Exception while inflating drawable", paramContext);
            localObject2 = localObject3;
          }
        }
      }
      if (localObject2 == null)
      {
        mKnownDrawableIdTags.a(paramInt, "appcompat_skip_skip");
        return localObject2;
      }
    }
    else
    {
      return null;
    }
    return localObject2;
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode)
  {
    Drawable localDrawable = paramDrawable;
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
      localDrawable = paramDrawable.mutate();
    }
    paramDrawable = paramMode;
    if (paramMode == null) {
      paramDrawable = DEFAULT_MODE;
    }
    localDrawable.setColorFilter(getPorterDuffColorFilter(paramInt, paramDrawable));
  }
  
  private Drawable tintDrawable(Context paramContext, int paramInt, boolean paramBoolean, Drawable paramDrawable)
  {
    Object localObject = getTintList(paramContext, paramInt);
    if (localObject != null)
    {
      paramContext = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
        paramContext = paramDrawable.mutate();
      }
      paramContext = DrawableCompat.wrap(paramContext);
      DrawableCompat.setTintList(paramContext, (ColorStateList)localObject);
      paramDrawable = getTintMode(paramInt);
      if (paramDrawable != null) {
        DrawableCompat.setTintMode(paramContext, paramDrawable);
      }
      return paramContext;
    }
    if (paramInt == R.drawable.abc_seekbar_track_material)
    {
      localObject = (LayerDrawable)paramDrawable;
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated), DEFAULT_MODE);
      return paramDrawable;
    }
    if ((paramInt != R.drawable.abc_ratingbar_material) && (paramInt != R.drawable.abc_ratingbar_indicator_material) && (paramInt != R.drawable.abc_ratingbar_small_material))
    {
      if ((!tintDrawableUsingColorFilter(paramContext, paramInt, paramDrawable)) && (paramBoolean)) {
        return null;
      }
    }
    else
    {
      localObject = (LayerDrawable)paramDrawable;
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated), DEFAULT_MODE);
      setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated), DEFAULT_MODE);
    }
    return paramDrawable;
  }
  
  static void tintDrawable(Drawable paramDrawable, TintInfo paramTintInfo, int[] paramArrayOfInt)
  {
    if ((DrawableUtils.canSafelyMutateDrawable(paramDrawable)) && (paramDrawable.mutate() != paramDrawable))
    {
      Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
      return;
    }
    if ((!mHasTintList) && (!mHasTintMode))
    {
      paramDrawable.clearColorFilter();
    }
    else
    {
      ColorStateList localColorStateList;
      if (mHasTintList) {
        localColorStateList = mTintList;
      } else {
        localColorStateList = null;
      }
      if (mHasTintMode) {
        paramTintInfo = mTintMode;
      } else {
        paramTintInfo = DEFAULT_MODE;
      }
      paramDrawable.setColorFilter(createTintFilter(localColorStateList, paramTintInfo, paramArrayOfInt));
    }
    if (Build.VERSION.SDK_INT <= 23) {
      paramDrawable.invalidateSelf();
    }
  }
  
  static boolean tintDrawableUsingColorFilter(Context paramContext, int paramInt, Drawable paramDrawable)
  {
    Object localObject2 = DEFAULT_MODE;
    int i = 0;
    int j = 0;
    int m = -1;
    Object localObject1;
    int k;
    if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt))
    {
      j = R.attr.colorControlNormal;
      i = 1;
      localObject1 = localObject2;
      k = m;
    }
    else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt))
    {
      j = R.attr.colorControlActivated;
      i = 1;
      localObject1 = localObject2;
      k = m;
    }
    else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt))
    {
      j = 16842801;
      i = 1;
      localObject1 = PorterDuff.Mode.MULTIPLY;
      k = m;
    }
    else if (paramInt == R.drawable.abc_list_divider_mtrl_alpha)
    {
      j = 16842800;
      i = 1;
      k = Math.round(40.8F);
      localObject1 = localObject2;
    }
    else
    {
      localObject1 = localObject2;
      k = m;
      if (paramInt == R.drawable.abc_dialog_material_background)
      {
        j = 16842801;
        i = 1;
        k = m;
        localObject1 = localObject2;
      }
    }
    if (i != 0)
    {
      localObject2 = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
        localObject2 = paramDrawable.mutate();
      }
      ((Drawable)localObject2).setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(paramContext, j), (PorterDuff.Mode)localObject1));
      if (k != -1) {
        ((Drawable)localObject2).setAlpha(k);
      }
      return true;
    }
    return false;
  }
  
  public void addDrawableToCache(Context paramContext)
  {
    try
    {
      paramContext = (LongSparseArray)mDrawableCaches.get(paramContext);
      if (paramContext != null) {
        paramContext.clear();
      }
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public Drawable getDrawable(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = getDrawable(paramContext, paramInt, false);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  Drawable getDrawable(Context paramContext, int paramInt, boolean paramBoolean)
  {
    try
    {
      get(paramContext);
      Object localObject2 = loadDrawableFromDelegates(paramContext, paramInt);
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = createDrawableIfNeeded(paramContext, paramInt);
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = ContextCompat.getDrawable(paramContext, paramInt);
      }
      localObject1 = localObject2;
      if (localObject2 != null) {
        localObject1 = tintDrawable(paramContext, paramInt, paramBoolean, (Drawable)localObject2);
      }
      if (localObject1 != null) {
        DrawableUtils.fixDrawable((Drawable)localObject1);
      }
      return localObject1;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  Drawable getDrawable(Context paramContext, TintManager paramTintManager, int paramInt)
  {
    try
    {
      Drawable localDrawable2 = loadDrawableFromDelegates(paramContext, paramInt);
      Drawable localDrawable1 = localDrawable2;
      if (localDrawable2 == null) {
        localDrawable1 = paramTintManager.superGetDrawable(paramInt);
      }
      if (localDrawable1 != null)
      {
        paramContext = tintDrawable(paramContext, paramInt, false, localDrawable1);
        return paramContext;
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  ColorStateList getTintList(Context paramContext, int paramInt)
  {
    try
    {
      ColorStateList localColorStateList3 = getTintListFromCache(paramContext, paramInt);
      ColorStateList localColorStateList1 = localColorStateList3;
      ColorStateList localColorStateList2 = localColorStateList1;
      if (localColorStateList3 == null)
      {
        if (paramInt == R.drawable.abc_edit_text_material) {
          localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_edittext);
        } else if (paramInt == R.drawable.abc_switch_track_mtrl_alpha) {
          localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_switch_track);
        } else if (paramInt == R.drawable.abc_switch_thumb_material) {
          localColorStateList1 = createSwitchThumbColorStateList(paramContext);
        } else if (paramInt == R.drawable.abc_btn_default_mtrl_shape) {
          localColorStateList1 = createDefaultButtonColorStateList(paramContext);
        } else if (paramInt == R.drawable.abc_btn_borderless_material) {
          localColorStateList1 = createBorderlessButtonColorStateList(paramContext);
        } else if (paramInt == R.drawable.abc_btn_colored_material) {
          localColorStateList1 = createColoredButtonColorStateList(paramContext);
        } else if ((paramInt != R.drawable.abc_spinner_mtrl_am_alpha) && (paramInt != R.drawable.abc_spinner_textfield_background_material))
        {
          if (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt)) {
            localColorStateList1 = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorControlNormal);
          } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt)) {
            localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_default);
          } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt)) {
            localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_btn_checkable);
          } else if (paramInt == R.drawable.abc_seekbar_thumb_material) {
            localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_seek_thumb);
          }
        }
        else {
          localColorStateList1 = org.org.v4.gui.helpers.Resources.getColorStateList(paramContext, R.color.abc_tint_spinner);
        }
        localColorStateList2 = localColorStateList1;
        if (localColorStateList1 != null)
        {
          addTintListToCache(paramContext, paramInt, localColorStateList1);
          localColorStateList2 = localColorStateList1;
        }
      }
      return localColorStateList2;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  class AvdcInflateDelegate
    implements AppCompatDrawableManager.InflateDelegate
  {
    AvdcInflateDelegate() {}
    
    public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    {
      try
      {
        paramContext = org.org.v4.graphics.drawable.VectorDrawableCompat.create(paramContext, paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", paramContext);
      }
      return null;
    }
  }
  
  abstract interface InflateDelegate
  {
    public abstract Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme);
  }
  
  class VdcInflateDelegate
    implements AppCompatDrawableManager.InflateDelegate
  {
    VdcInflateDelegate() {}
    
    public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    {
      try
      {
        paramContext = org.org.jraf.drawable.VectorDrawableCompat.createFromXmlInner(paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Log.e("VdcInflateDelegate", "Exception while inflating <vector>", paramContext);
      }
      return null;
    }
  }
}
