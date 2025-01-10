package android.support.v4.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCompat
{
  private static boolean accessibilityDelegateCheckFailed = false;
  private static WeakHashMap<View, String> h;
  private static Field mAccessibilityDelegateField;
  private static WeakHashMap<View, y> mViewPropertyAnimatorCompatMap;
  private static Field sMinHeightField;
  private static boolean sMinHeightFieldFetched;
  private static Field sMinWidthField;
  private static boolean sMinWidthFieldFetched;
  private static ThreadLocal<Rect> sThreadLocalRect;
  
  static
  {
    new AtomicInteger(1);
    mViewPropertyAnimatorCompatMap = null;
  }
  
  public static String a(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getTransitionName();
    }
    WeakHashMap localWeakHashMap = h;
    if (localWeakHashMap == null) {
      return null;
    }
    return (String)localWeakHashMap.get(paramView);
  }
  
  private static void a(View paramView, int paramInt)
  {
    paramView.offsetLeftAndRight(paramInt);
    if (paramView.getVisibility() == 0)
    {
      show(paramView);
      paramView = paramView.getParent();
      if ((paramView instanceof View)) {
        show((View)paramView);
      }
    }
  }
  
  public static void a(View paramView, Rect paramRect)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      paramView.setClipBounds(paramRect);
    }
  }
  
  public static void a(View paramView, ByteVector paramByteVector)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      if (paramByteVector != null) {
        paramByteVector = paramByteVector.b();
      } else {
        paramByteVector = null;
      }
      paramView.setPointerIcon((PointerIcon)paramByteVector);
    }
  }
  
  public static void a(View paramView, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramView.setTransitionName(paramString);
      return;
    }
    if (h == null) {
      h = new WeakHashMap();
    }
    h.put(paramView, paramString);
  }
  
  static boolean a(View paramView, KeyEvent paramKeyEvent)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return false;
    }
    return f.a(paramView).a(paramKeyEvent);
  }
  
  public static void add(View paramView, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      paramView.setImportantForAccessibility(paramInt);
      return;
    }
    if (i >= 16)
    {
      i = paramInt;
      if (paramInt == 4) {
        i = 2;
      }
      paramView.setImportantForAccessibility(i);
    }
  }
  
  public static void add(View paramView, Runnable paramRunnable, long paramLong)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.postOnAnimationDelayed(paramRunnable, paramLong);
      return;
    }
    paramView.postDelayed(paramRunnable, ValueAnimator.getFrameDelay() + paramLong);
  }
  
  public static ViewPropertyAnimatorCompat animate(View paramView)
  {
    if (mViewPropertyAnimatorCompatMap == null) {
      mViewPropertyAnimatorCompatMap = new WeakHashMap();
    }
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat2 = (ViewPropertyAnimatorCompat)mViewPropertyAnimatorCompatMap.get(paramView);
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat1 = localViewPropertyAnimatorCompat2;
    if (localViewPropertyAnimatorCompat2 == null)
    {
      localViewPropertyAnimatorCompat1 = new ViewPropertyAnimatorCompat(paramView);
      mViewPropertyAnimatorCompatMap.put(paramView, localViewPropertyAnimatorCompat1);
    }
    return localViewPropertyAnimatorCompat1;
  }
  
  public static void b(View paramView, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      paramView.setHasTransientState(paramBoolean);
    }
  }
  
  static boolean b(View paramView, KeyEvent paramKeyEvent)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return false;
    }
    return f.a(paramView).a(paramView, paramKeyEvent);
  }
  
  public static int create(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramView.getImportantForAutofill();
    }
    return 0;
  }
  
  public static WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
  {
    Object localObject = paramWindowInsetsCompat;
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramWindowInsetsCompat = (WindowInsets)WindowInsetsCompat.unwrap(paramWindowInsetsCompat);
      localObject = paramView.dispatchApplyWindowInsets(paramWindowInsetsCompat);
      paramView = paramWindowInsetsCompat;
      if (localObject != paramWindowInsetsCompat) {
        paramView = new WindowInsets((WindowInsets)localObject);
      }
      localObject = WindowInsetsCompat.unwrap(paramView);
    }
    return localObject;
  }
  
  public static boolean get(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramView.isLaidOut();
    }
    return (paramView.getWidth() > 0) && (paramView.getHeight() > 0);
  }
  
  public static ColorStateList getBackgroundTintList(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getBackgroundTintList();
    }
    if ((paramView instanceof TintableBackgroundView)) {
      return ((TintableBackgroundView)paramView).getSupportBackgroundTintList();
    }
    return null;
  }
  
  public static PorterDuff.Mode getBackgroundTintMode(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getBackgroundTintMode();
    }
    if ((paramView instanceof TintableBackgroundView)) {
      return ((TintableBackgroundView)paramView).getSupportBackgroundTintMode();
    }
    return null;
  }
  
  public static float getElevation(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getElevation();
    }
    return 0.0F;
  }
  
  private static Rect getEmptyTempRect()
  {
    if (sThreadLocalRect == null) {
      sThreadLocalRect = new ThreadLocal();
    }
    Rect localRect2 = (Rect)sThreadLocalRect.get();
    Rect localRect1 = localRect2;
    if (localRect2 == null)
    {
      localRect2 = new Rect();
      localRect1 = localRect2;
      sThreadLocalRect.set(localRect2);
    }
    localRect1.setEmpty();
    return localRect1;
  }
  
  public static boolean getFitsSystemWindows(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getFitsSystemWindows();
    }
    return false;
  }
  
  public static int getImportantForAccessibility(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getImportantForAccessibility();
    }
    return 0;
  }
  
  public static int getLayoutDirection(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return paramView.getLayoutDirection();
    }
    return 0;
  }
  
  public static int getMinimumHeight(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getMinimumHeight();
    }
    if (!sMinHeightFieldFetched)
    {
      try
      {
        Field localField1 = View.class.getDeclaredField("mMinHeight");
        sMinHeightField = localField1;
        localField1 = sMinHeightField;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException) {}
      sMinHeightFieldFetched = true;
    }
    Field localField2 = sMinHeightField;
    if (localField2 != null) {
      try
      {
        paramView = localField2.get(paramView);
        paramView = (Integer)paramView;
        int i = paramView.intValue();
        return i;
      }
      catch (Exception paramView) {}
    }
    return 0;
  }
  
  public static int getMinimumWidth(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getMinimumWidth();
    }
    if (!sMinWidthFieldFetched)
    {
      try
      {
        Field localField1 = View.class.getDeclaredField("mMinWidth");
        sMinWidthField = localField1;
        localField1 = sMinWidthField;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException) {}
      sMinWidthFieldFetched = true;
    }
    Field localField2 = sMinWidthField;
    if (localField2 != null) {
      try
      {
        paramView = localField2.get(paramView);
        paramView = (Integer)paramView;
        int i = paramView.intValue();
        return i;
      }
      catch (Exception paramView) {}
    }
    return 0;
  }
  
  public static int getPaddingEnd(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return paramView.getPaddingEnd();
    }
    return paramView.getPaddingRight();
  }
  
  public static int getPaddingStart(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return paramView.getPaddingStart();
    }
    return paramView.getPaddingLeft();
  }
  
  public static ViewParent getParentForAccessibility(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getParentForAccessibility();
    }
    return paramView.getParent();
  }
  
  public static int getWindowSystemUiVisibility(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.getWindowSystemUiVisibility();
    }
    return 0;
  }
  
  public static boolean hasAccessibilityDelegate(View paramView)
  {
    if (accessibilityDelegateCheckFailed) {
      return false;
    }
    if (mAccessibilityDelegateField == null) {
      try
      {
        mAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
        mAccessibilityDelegateField.setAccessible(true);
      }
      catch (Throwable paramView)
      {
        accessibilityDelegateCheckFailed = true;
        return false;
      }
    }
    try
    {
      paramView = mAccessibilityDelegateField.get(paramView);
      if (paramView != null) {
        return true;
      }
    }
    catch (Throwable paramView)
    {
      accessibilityDelegateCheckFailed = true;
    }
    return false;
  }
  
  public static boolean hasOnClickListeners(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      return paramView.hasOnClickListeners();
    }
    return false;
  }
  
  public static boolean hasTransientState(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.hasTransientState();
    }
    return false;
  }
  
  public static float init(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.getZ();
    }
    return 0.0F;
  }
  
  public static boolean isNestedScrollingEnabled(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramView.isNestedScrollingEnabled();
    }
    if ((paramView instanceof NestedScrollingChild)) {
      return ((NestedScrollingChild)paramView).isNestedScrollingEnabled();
    }
    return false;
  }
  
  public static boolean isPaddingRelative(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return paramView.isPaddingRelative();
    }
    return false;
  }
  
  public static Rect obtain(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return paramView.getClipBounds();
    }
    return null;
  }
  
  public static void offsetLeftAndRight(View paramView, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
    {
      paramView.offsetLeftAndRight(paramInt);
      return;
    }
    if (i >= 21)
    {
      Rect localRect = getEmptyTempRect();
      i = 0;
      ViewParent localViewParent = paramView.getParent();
      boolean bool;
      if ((localViewParent instanceof View))
      {
        View localView = (View)localViewParent;
        localRect.set(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom());
        bool = localRect.intersects(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()) ^ true;
      }
      a(paramView, paramInt);
      if ((bool) && (localRect.intersect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()))) {
        ((View)localViewParent).invalidate(localRect);
      }
      return;
    }
    a(paramView, paramInt);
  }
  
  public static void offsetTopAndBottom(View paramView, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
    {
      paramView.offsetTopAndBottom(paramInt);
      return;
    }
    if (i >= 21)
    {
      Rect localRect = getEmptyTempRect();
      i = 0;
      ViewParent localViewParent = paramView.getParent();
      boolean bool;
      if ((localViewParent instanceof View))
      {
        View localView = (View)localViewParent;
        localRect.set(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom());
        bool = localRect.intersects(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()) ^ true;
      }
      onLayoutChild(paramView, paramInt);
      if ((bool) && (localRect.intersect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom()))) {
        ((View)localViewParent).invalidate(localRect);
      }
      return;
    }
    onLayoutChild(paramView, paramInt);
  }
  
  public static WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
  {
    Object localObject = paramWindowInsetsCompat;
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramWindowInsetsCompat = (WindowInsets)WindowInsetsCompat.unwrap(paramWindowInsetsCompat);
      localObject = paramView.onApplyWindowInsets(paramWindowInsetsCompat);
      paramView = paramWindowInsetsCompat;
      if (localObject != paramWindowInsetsCompat) {
        paramView = new WindowInsets((WindowInsets)localObject);
      }
      localObject = WindowInsetsCompat.unwrap(paramView);
    }
    return localObject;
  }
  
  public static Display onCreateView(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return paramView.getDisplay();
    }
    if (setText(paramView)) {
      return ((WindowManager)paramView.getContext().getSystemService("window")).getDefaultDisplay();
    }
    return null;
  }
  
  public static void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    paramView.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfoCompat.getInfo());
  }
  
  private static void onLayoutChild(View paramView, int paramInt)
  {
    paramView.offsetTopAndBottom(paramInt);
    if (paramView.getVisibility() == 0)
    {
      show(paramView);
      paramView = paramView.getParent();
      if ((paramView instanceof View)) {
        show((View)paramView);
      }
    }
  }
  
  public static boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.performAccessibilityAction(paramInt, paramBundle);
    }
    return false;
  }
  
  public static void postInvalidateOnAnimation(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.postInvalidateOnAnimation();
      return;
    }
    paramView.postInvalidate();
  }
  
  public static void postInvalidateOnAnimation(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      paramView.setImportantForAutofill(paramInt);
    }
  }
  
  public static void postOnAnimation(View paramView, Runnable paramRunnable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.postOnAnimation(paramRunnable);
      return;
    }
    paramView.postDelayed(paramRunnable, ValueAnimator.getFrameDelay());
  }
  
  public static void requestApplyInsets(View paramView)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 20)
    {
      paramView.requestApplyInsets();
      return;
    }
    if (i >= 16) {
      paramView.requestFitSystemWindows();
    }
  }
  
  public static boolean set(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return paramView.hasOverlappingRendering();
    }
    return true;
  }
  
  public static void setAccessibilityDelegate(View paramView, AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
  {
    if (paramAccessibilityDelegateCompat == null) {
      paramAccessibilityDelegateCompat = null;
    } else {
      paramAccessibilityDelegateCompat = paramAccessibilityDelegateCompat.getBridge();
    }
    paramView.setAccessibilityDelegate(paramAccessibilityDelegateCompat);
  }
  
  public static void setBackgroundDrawable(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
  
  public static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramView.setBackgroundTintList(paramColorStateList);
      if (Build.VERSION.SDK_INT == 21)
      {
        paramColorStateList = paramView.getBackground();
        int i;
        if ((paramView.getBackgroundTintList() == null) && (paramView.getBackgroundTintMode() == null)) {
          i = 0;
        } else {
          i = 1;
        }
        if ((paramColorStateList != null) && (i != 0))
        {
          if (paramColorStateList.isStateful()) {
            paramColorStateList.setState(paramView.getDrawableState());
          }
          paramView.setBackground(paramColorStateList);
        }
      }
    }
    else if ((paramView instanceof TintableBackgroundView))
    {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramView.setBackgroundTintMode(paramMode);
      if (Build.VERSION.SDK_INT == 21)
      {
        paramMode = paramView.getBackground();
        int i;
        if ((paramView.getBackgroundTintList() == null) && (paramView.getBackgroundTintMode() == null)) {
          i = 0;
        } else {
          i = 1;
        }
        if ((paramMode != null) && (i != 0))
        {
          if (paramMode.isStateful()) {
            paramMode.setState(paramView.getDrawableState());
          }
          paramView.setBackground(paramMode);
        }
      }
    }
    else if ((paramView instanceof TintableBackgroundView))
    {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintMode(paramMode);
    }
  }
  
  public static void setElevation(View paramView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.setElevation(paramFloat);
    }
  }
  
  public static void setFitsSystemWindows(View paramView, boolean paramBoolean)
  {
    paramView.setFitsSystemWindows(paramBoolean);
  }
  
  public static void setNestedScrollingEnabled(View paramView, int paramInt)
  {
    if ((paramView instanceof ViewCompat.ViewCompatImpl))
    {
      ((ViewCompat.ViewCompatImpl)paramView).stopNestedScroll(paramInt);
      return;
    }
    if (paramInt == 0) {
      stopNestedScroll(paramView);
    }
  }
  
  public static void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (paramOnApplyWindowInsetsListener == null)
      {
        paramView.setOnApplyWindowInsetsListener(null);
        return;
      }
      paramView.setOnApplyWindowInsetsListener(new ViewCompatLollipop.1(paramOnApplyWindowInsetsListener));
    }
  }
  
  public static void setPaddingRelative(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 17)
    {
      paramView.setPaddingRelative(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    paramView.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setScrollIndicators(View paramView, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      paramView.setScrollIndicators(paramInt1, paramInt2);
    }
  }
  
  public static void setText(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramView.setAccessibilityLiveRegion(paramInt);
    }
  }
  
  public static boolean setText(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramView.isAttachedToWindow();
    }
    return paramView.getWindowToken() != null;
  }
  
  private static void show(View paramView)
  {
    float f = paramView.getTranslationY();
    paramView.setTranslationY(1.0F + f);
    paramView.setTranslationY(f);
  }
  
  public static void stopNestedScroll(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramView.stopNestedScroll();
      return;
    }
    if ((paramView instanceof NestedScrollingChild)) {
      ((NestedScrollingChild)paramView).stopNestedScroll();
    }
  }
  
  public static int update(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramView.getAccessibilityLiveRegion();
    }
    return 0;
  }
}
