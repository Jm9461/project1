package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompatHC;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.c;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.l.a;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ContentFrameLayout.a;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.TintManager;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.support.v7.widget.k0.a;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.reflect.Constructor;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.color;
import org.org.v4.content.R.id;
import org.org.v4.content.R.layout;
import org.org.v4.content.R.style;
import org.org.v4.content.R.styleable;
import org.org.v4.view.ActionMode;
import org.org.v4.view.ActionMode.Callback;
import org.org.v4.view.ContextThemeWrapper;
import org.org.v4.view.SupportMenuInflater;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImplV7
  extends AppCompatDelegate
  implements MenuBuilder.Callback, LayoutInflater.Factory2
{
  private static final int[] CameraPreference;
  private static final boolean EINK_SCREEN;
  private static boolean EINK_SONY;
  private int a = -100;
  ActionBar mActionBar;
  private ActionMenuPresenterCallback mActionMenuPresenterCallback;
  ActionMode mActionMode;
  PopupWindow mActionModePopup;
  ActionBarContextView mActionModeView;
  final AppCompatCallback mAppCompatCallback;
  private AppCompatViewInflater mAppCompatViewInflater;
  final Window.Callback mAppCompatWindowCallback;
  private boolean mClosingActionMenu;
  final Context mContext;
  private DecorContentParent mDecorContentParent;
  private boolean mEnableDefaultActionBarUp;
  ViewPropertyAnimatorCompat mFadeAnim = null;
  private boolean mFeatureIndeterminateProgress;
  private boolean mFeatureProgress;
  private boolean mHandleNativeActionModes = true;
  boolean mHasActionBar;
  int mInvalidatePanelMenuFeatures;
  boolean mInvalidatePanelMenuPosted;
  private final Runnable mInvalidatePanelMenuRunnable = new MonthByWeekFragment.2(this);
  boolean mIsDestroyed;
  boolean mIsFloating;
  private boolean mLongPressBackDown;
  MenuInflater mMenuInflater;
  final Window.Callback mOriginalWindowCallback;
  boolean mOverlayActionBar;
  boolean mOverlayActionMode;
  private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
  private PanelFeatureState[] mPanels;
  private PanelFeatureState mPreparedPanel;
  Runnable mShowActionModePopup;
  private View mStatusGuard;
  private ViewGroup mSubDecor;
  private boolean mSubDecorInstalled;
  private Rect mTempRect1;
  private Rect mTempRect2;
  private CharSequence mTitle;
  private TextView mTitleView;
  final Window mWindow;
  boolean mWindowNoTitle;
  private f r;
  private boolean t;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 21) {
      bool = true;
    } else {
      bool = false;
    }
    EINK_SCREEN = bool;
    CameraPreference = new int[] { 16842836 };
    if ((EINK_SCREEN) && (!EINK_SONY))
    {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
      EINK_SONY = true;
    }
  }
  
  AppCompatDelegateImplV7(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    mContext = paramContext;
    mWindow = paramWindow;
    mAppCompatCallback = paramAppCompatCallback;
    mOriginalWindowCallback = mWindow.getCallback();
    paramWindow = mOriginalWindowCallback;
    if (!(paramWindow instanceof WindowCallbackWrapper))
    {
      mAppCompatWindowCallback = new WindowCallbackWrapper(this, paramWindow);
      mWindow.setCallback(mAppCompatWindowCallback);
      paramContext = TintTypedArray.obtainStyledAttributes(paramContext, null, CameraPreference);
      paramWindow = paramContext.getDrawableIfKnown(0);
      if (paramWindow != null) {
        mWindow.setBackgroundDrawable(paramWindow);
      }
      paramContext.recycle();
      return;
    }
    throw new IllegalStateException("AppCompat has already installed itself into the Window");
  }
  
  private void applyFixedSizeWindow()
  {
    ContentFrameLayout localContentFrameLayout = (ContentFrameLayout)mSubDecor.findViewById(16908290);
    Object localObject = mWindow.getDecorView();
    localContentFrameLayout.setDecorPadding(((View)localObject).getPaddingLeft(), ((View)localObject).getPaddingTop(), ((View)localObject).getPaddingRight(), ((View)localObject).getPaddingBottom());
    localObject = mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, localContentFrameLayout.getMinWidthMajor());
    ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, localContentFrameLayout.getMinWidthMinor());
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, localContentFrameLayout.getFixedWidthMajor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, localContentFrameLayout.getFixedWidthMinor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, localContentFrameLayout.getFixedHeightMajor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, localContentFrameLayout.getFixedHeightMinor());
    }
    ((TypedArray)localObject).recycle();
    localContentFrameLayout.requestLayout();
  }
  
  private int b()
  {
    int i = a;
    if (i != -100) {
      return i;
    }
    return AppCompatDelegate.getDefaultNightMode();
  }
  
  private boolean connect()
  {
    if (t)
    {
      Object localObject = mContext;
      if ((localObject instanceof Activity))
      {
        localObject = ((Context)localObject).getPackageManager();
        Context localContext1 = mContext;
        Context localContext2 = mContext;
        try
        {
          localObject = ((PackageManager)localObject).getActivityInfo(new ComponentName(localContext1, localContext2.getClass()), 0);
          if ((configChanges & 0x200) == 0) {
            return true;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", localNameNotFoundException);
          return true;
        }
      }
    }
    return false;
  }
  
  private ViewGroup createSubDecor()
  {
    Object localObject1 = mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    if (((TypedArray)localObject1).hasValue(R.styleable.AppCompatTheme_windowActionBar))
    {
      if (((TypedArray)localObject1).getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
        requestWindowFeature(1);
      } else if (((TypedArray)localObject1).getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
        requestWindowFeature(108);
      }
      if (((TypedArray)localObject1).getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
        requestWindowFeature(109);
      }
      if (((TypedArray)localObject1).getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
        requestWindowFeature(10);
      }
      mIsFloating = ((TypedArray)localObject1).getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
      ((TypedArray)localObject1).recycle();
      mWindow.getDecorView();
      Object localObject2 = LayoutInflater.from(mContext);
      localObject1 = null;
      if (!mWindowNoTitle)
      {
        if (mIsFloating)
        {
          localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_dialog_title_material, null);
          mOverlayActionBar = false;
          mHasActionBar = false;
        }
        else if (mHasActionBar)
        {
          localObject1 = new TypedValue();
          mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, (TypedValue)localObject1, true);
          int i = resourceId;
          if (i != 0) {
            localObject1 = new ContextThemeWrapper(mContext, i);
          } else {
            localObject1 = mContext;
          }
          localObject1 = (ViewGroup)LayoutInflater.from((Context)localObject1).inflate(R.layout.abc_screen_toolbar, null);
          mDecorContentParent = ((DecorContentParent)((View)localObject1).findViewById(R.id.decor_content_parent));
          mDecorContentParent.setWindowCallback(getWindowCallback());
          if (mOverlayActionBar) {
            mDecorContentParent.initFeature(109);
          }
          if (mFeatureIndeterminateProgress) {
            mDecorContentParent.initFeature(2);
          }
          if (mFeatureProgress) {
            mDecorContentParent.initFeature(5);
          }
        }
      }
      else
      {
        if (mOverlayActionMode) {
          localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
        } else {
          localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_screen_simple, null);
        }
        if (Build.VERSION.SDK_INT >= 21) {
          ViewCompat.setOnApplyWindowInsetsListener((View)localObject1, new OnApplyWindowInsetsListener()
          {
            public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
            {
              int i = paramAnonymousWindowInsetsCompat.getSystemWindowInsetTop();
              int j = access$300(i);
              WindowInsetsCompat localWindowInsetsCompat = paramAnonymousWindowInsetsCompat;
              if (i != j) {
                localWindowInsetsCompat = paramAnonymousWindowInsetsCompat.replaceSystemWindowInsets(paramAnonymousWindowInsetsCompat.getSystemWindowInsetLeft(), j, paramAnonymousWindowInsetsCompat.getSystemWindowInsetRight(), paramAnonymousWindowInsetsCompat.getSystemWindowInsetBottom());
              }
              return ViewCompat.onApplyWindowInsets(paramAnonymousView, localWindowInsetsCompat);
            }
          });
        } else {
          ((FitWindowsViewGroup)localObject1).setOnFitSystemWindowsListener(new k0.a()
          {
            public void onFitSystemWindows(Rect paramAnonymousRect)
            {
              top = access$300(top);
            }
          });
        }
      }
      if (localObject1 != null)
      {
        if (mDecorContentParent == null) {
          mTitleView = ((TextView)((View)localObject1).findViewById(R.id.title));
        }
        ViewUtils.makeOptionalFitsSystemWindows((View)localObject1);
        localObject2 = (ContentFrameLayout)((View)localObject1).findViewById(R.id.action_bar_activity_content);
        ViewGroup localViewGroup = (ViewGroup)mWindow.findViewById(16908290);
        if (localViewGroup != null)
        {
          while (localViewGroup.getChildCount() > 0)
          {
            View localView = localViewGroup.getChildAt(0);
            localViewGroup.removeViewAt(0);
            ((ViewGroup)localObject2).addView(localView);
          }
          localViewGroup.setId(-1);
          ((View)localObject2).setId(16908290);
          if ((localViewGroup instanceof FrameLayout)) {
            ((FrameLayout)localViewGroup).setForeground(null);
          }
        }
        mWindow.setContentView((View)localObject1);
        ((ContentFrameLayout)localObject2).setAttachListener(new ContentFrameLayout.a()
        {
          public void onAttachedFromWindow() {}
          
          public void onDetachedFromWindow()
          {
            dismissPopups();
          }
        });
        return localObject1;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("AppCompat does not support the current theme features: { windowActionBar: ");
      ((StringBuilder)localObject1).append(mHasActionBar);
      ((StringBuilder)localObject1).append(", windowActionBarOverlay: ");
      ((StringBuilder)localObject1).append(mOverlayActionBar);
      ((StringBuilder)localObject1).append(", android:windowIsFloating: ");
      ((StringBuilder)localObject1).append(mIsFloating);
      ((StringBuilder)localObject1).append(", windowActionModeOverlay: ");
      ((StringBuilder)localObject1).append(mOverlayActionMode);
      ((StringBuilder)localObject1).append(", windowNoTitle: ");
      ((StringBuilder)localObject1).append(mWindowNoTitle);
      ((StringBuilder)localObject1).append(" }");
      throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
    }
    ((TypedArray)localObject1).recycle();
    localObject1 = new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    throw ((Throwable)localObject1);
  }
  
  private void d()
  {
    if (r == null) {
      r = new f(this, TwilightManager.getLastKnownLocation(mContext));
    }
  }
  
  private void ensureSubDecor()
  {
    if (!mSubDecorInstalled)
    {
      mSubDecor = createSubDecor();
      Object localObject1 = getTitle();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        Object localObject2 = mDecorContentParent;
        if (localObject2 != null)
        {
          ((DecorContentParent)localObject2).setWindowTitle((CharSequence)localObject1);
        }
        else if (peekSupportActionBar() != null)
        {
          peekSupportActionBar().setWindowTitle((CharSequence)localObject1);
        }
        else
        {
          localObject2 = mTitleView;
          if (localObject2 != null) {
            ((TextView)localObject2).setText((CharSequence)localObject1);
          }
        }
      }
      applyFixedSizeWindow();
      onSubDecorInstalled(mSubDecor);
      mSubDecorInstalled = true;
      localObject1 = getPanelState(0, false);
      if ((!mIsDestroyed) && ((localObject1 == null) || (menu == null))) {
        invalidatePanelMenu(108);
      }
    }
  }
  
  private boolean init(int paramInt)
  {
    android.content.res.Resources localResources = mContext.getResources();
    Configuration localConfiguration = localResources.getConfiguration();
    int i = uiMode;
    if (paramInt == 2) {
      paramInt = 32;
    } else {
      paramInt = 16;
    }
    if ((i & 0x30) != paramInt)
    {
      if (connect())
      {
        ((Activity)mContext).recreate();
      }
      else
      {
        localConfiguration = new Configuration(localConfiguration);
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        uiMode = (uiMode & 0xFFFFFFCF | paramInt);
        localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
        if (Build.VERSION.SDK_INT < 26) {
          Frame.initialize(localResources);
        }
      }
      return true;
    }
    return false;
  }
  
  private void initWindowDecorActionBar()
  {
    ensureSubDecor();
    if (mHasActionBar)
    {
      if (mActionBar != null) {
        return;
      }
      Object localObject = mOriginalWindowCallback;
      if ((localObject instanceof Activity)) {
        mActionBar = new WindowDecorActionBar((Activity)localObject, mOverlayActionBar);
      } else if ((localObject instanceof Dialog)) {
        mActionBar = new WindowDecorActionBar((Dialog)localObject);
      }
      localObject = mActionBar;
      if (localObject != null) {
        ((ActionBar)localObject).setDefaultDisplayHomeAsUpEnabled(mEnableDefaultActionBarUp);
      }
    }
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState)
  {
    View localView = createdPanelView;
    if (localView != null)
    {
      shownPanelView = localView;
      return true;
    }
    if (menu == null) {
      return false;
    }
    if (mPanelMenuPresenterCallback == null) {
      mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
    }
    shownPanelView = ((View)paramPanelFeatureState.getListMenuView(mPanelMenuPresenterCallback));
    return shownPanelView != null;
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState)
  {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    decorView = new ListMenuDecorView(listPresenterContext);
    gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState)
  {
    Object localObject3 = mContext;
    int i = featureId;
    if (i != 0)
    {
      localObject1 = localObject3;
      if (i != 108) {}
    }
    else
    {
      localObject1 = localObject3;
      if (mDecorContentParent != null)
      {
        TypedValue localTypedValue = new TypedValue();
        Resources.Theme localTheme = ((Context)localObject3).getTheme();
        localTheme.resolveAttribute(R.attr.actionBarTheme, localTypedValue, true);
        localObject1 = null;
        if (resourceId != 0)
        {
          localObject2 = ((Context)localObject3).getResources().newTheme();
          localObject1 = localObject2;
          ((Resources.Theme)localObject2).setTo(localTheme);
          ((Resources.Theme)localObject2).applyStyle(resourceId, true);
          ((Resources.Theme)localObject2).resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
        }
        else
        {
          localTheme.resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
        }
        Object localObject2 = localObject1;
        if (resourceId != 0)
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject1 = ((Context)localObject3).getResources().newTheme();
            localObject2 = localObject1;
            ((Resources.Theme)localObject1).setTo(localTheme);
          }
          ((Resources.Theme)localObject2).applyStyle(resourceId, true);
        }
        localObject1 = localObject3;
        if (localObject2 != null)
        {
          localObject3 = new ContextThemeWrapper((Context)localObject3, 0);
          localObject1 = localObject3;
          ((ContextThemeWrapper)localObject3).getTheme().setTo((Resources.Theme)localObject2);
        }
      }
    }
    Object localObject1 = new android.support.v7.view.menu.f((Context)localObject1);
    ((android.support.v7.view.menu.f)localObject1).setCallback(this);
    paramPanelFeatureState.setMenu((android.support.v7.view.menu.f)localObject1);
    return true;
  }
  
  private void invalidatePanelMenu(int paramInt)
  {
    mInvalidatePanelMenuFeatures |= 1 << paramInt;
    if (!mInvalidatePanelMenuPosted)
    {
      ViewCompat.postOnAnimation(mWindow.getDecorView(), mInvalidatePanelMenuRunnable);
      mInvalidatePanelMenuPosted = true;
    }
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getRepeatCount() == 0)
    {
      PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
      if (!isOpen) {
        return preparePanel(localPanelFeatureState, paramKeyEvent);
      }
    }
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mActionMode != null) {
      return false;
    }
    boolean bool3 = false;
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    boolean bool1;
    if (paramInt == 0)
    {
      DecorContentParent localDecorContentParent = mDecorContentParent;
      if ((localDecorContentParent != null) && (localDecorContentParent.canShowOverflowMenu()) && (!ViewConfiguration.get(mContext).hasPermanentMenuKey()))
      {
        if (!mDecorContentParent.isOverflowMenuShowing())
        {
          bool1 = bool3;
          if (mIsDestroyed) {
            break label208;
          }
          bool1 = bool3;
          if (!preparePanel(localPanelFeatureState, paramKeyEvent)) {
            break label208;
          }
          bool1 = mDecorContentParent.showOverflowMenu();
          break label208;
        }
        bool1 = mDecorContentParent.hideOverflowMenu();
        break label208;
      }
    }
    if ((!isOpen) && (!isHandled))
    {
      bool1 = bool3;
      if (isPrepared)
      {
        boolean bool2 = true;
        if (refreshMenuContent)
        {
          isPrepared = false;
          bool2 = preparePanel(localPanelFeatureState, paramKeyEvent);
        }
        bool1 = bool3;
        if (bool2)
        {
          openPanel(localPanelFeatureState, paramKeyEvent);
          bool1 = true;
        }
      }
    }
    else
    {
      bool1 = isOpen;
      closePanel(localPanelFeatureState, true);
    }
    label208:
    if (bool1)
    {
      paramKeyEvent = (AudioManager)mContext.getSystemService("audio");
      if (paramKeyEvent != null)
      {
        paramKeyEvent.playSoundEffect(0);
        return bool1;
      }
      Log.w("AppCompatDelegate", "Couldn't get audio manager");
    }
    return bool1;
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if (!isOpen)
    {
      if (mIsDestroyed) {
        return;
      }
      int i;
      if (featureId == 0)
      {
        if ((mContext.getResources().getConfiguration().screenLayout & 0xF) == 4) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0) {
          return;
        }
      }
      Object localObject = getWindowCallback();
      if ((localObject != null) && (!((Window.Callback)localObject).onMenuOpened(featureId, menu)))
      {
        closePanel(paramPanelFeatureState, true);
        return;
      }
      WindowManager localWindowManager = (WindowManager)mContext.getSystemService("window");
      if (localWindowManager == null) {
        return;
      }
      if (!preparePanel(paramPanelFeatureState, paramKeyEvent)) {
        return;
      }
      int j = -2;
      if ((decorView != null) && (!refreshDecorView))
      {
        paramKeyEvent = createdPanelView;
        if (paramKeyEvent != null)
        {
          paramKeyEvent = paramKeyEvent.getLayoutParams();
          i = j;
          if (paramKeyEvent == null) {
            break label356;
          }
          i = j;
          if (width != -1) {
            break label356;
          }
          i = -1;
          break label356;
        }
      }
      for (;;)
      {
        i = j;
        break;
        paramKeyEvent = decorView;
        if (paramKeyEvent == null)
        {
          if (!initializePanelDecor(paramPanelFeatureState)) {
            return;
          }
          if (decorView != null) {}
        }
        else if ((refreshDecorView) && (paramKeyEvent.getChildCount() > 0))
        {
          decorView.removeAllViews();
        }
        if (!initializePanelContent(paramPanelFeatureState)) {
          return;
        }
        if (!paramPanelFeatureState.hasPanelItems()) {
          return;
        }
        localObject = shownPanelView.getLayoutParams();
        paramKeyEvent = (KeyEvent)localObject;
        if (localObject == null) {
          paramKeyEvent = new ViewGroup.LayoutParams(-2, -2);
        }
        i = background;
        decorView.setBackgroundResource(i);
        localObject = shownPanelView.getParent();
        if ((localObject != null) && ((localObject instanceof ViewGroup))) {
          ((ViewGroup)localObject).removeView(shownPanelView);
        }
        decorView.addView(shownPanelView, paramKeyEvent);
        if (!shownPanelView.hasFocus()) {
          shownPanelView.requestFocus();
        }
      }
      label356:
      isHandled = false;
      paramKeyEvent = new WindowManager.LayoutParams(i, -2, x, y, 1002, 8519680, -3);
      gravity = gravity;
      windowAnimations = windowAnimations;
      localWindowManager.addView(decorView, paramKeyEvent);
      isOpen = true;
    }
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    if (paramKeyEvent.isSystem()) {
      return false;
    }
    boolean bool2 = false;
    boolean bool1;
    if (!isPrepared)
    {
      bool1 = bool2;
      if (!preparePanel(paramPanelFeatureState, paramKeyEvent)) {}
    }
    else
    {
      android.support.v7.view.menu.f localF = menu;
      bool1 = bool2;
      if (localF != null) {
        bool1 = localF.performShortcut(paramInt1, paramKeyEvent, paramInt2);
      }
    }
    if ((bool1) && ((paramInt2 & 0x1) == 0) && (mDecorContentParent == null)) {
      closePanel(paramPanelFeatureState, true);
    }
    return bool1;
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if (mIsDestroyed) {
      return false;
    }
    boolean bool = isPrepared;
    PanelFeatureState localPanelFeatureState = paramPanelFeatureState;
    if (bool) {
      return true;
    }
    Object localObject1 = mPreparedPanel;
    if ((localObject1 != null) && (localObject1 != localPanelFeatureState)) {
      closePanel((PanelFeatureState)localObject1, false);
    }
    localObject1 = getWindowCallback();
    if (localObject1 != null) {
      createdPanelView = ((Window.Callback)localObject1).onCreatePanelView(featureId);
    }
    int i = featureId;
    if ((i != 0) && (i != 108)) {
      i = 0;
    } else {
      i = 1;
    }
    Object localObject2;
    if (i != 0)
    {
      localObject2 = mDecorContentParent;
      if (localObject2 != null) {
        ((DecorContentParent)localObject2).setMenuPrepared();
      }
    }
    if ((createdPanelView == null) && ((i == 0) || (!(peekSupportActionBar() instanceof ToolbarActionBar))))
    {
      if ((menu == null) || (refreshMenuContent))
      {
        if (menu == null)
        {
          if (!initializePanelMenu(localPanelFeatureState)) {
            break label475;
          }
          if (menu == null) {
            return false;
          }
        }
        if ((i != 0) && (mDecorContentParent != null))
        {
          if (mActionMenuPresenterCallback == null) {
            mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
          }
          mDecorContentParent.setMenu(menu, mActionMenuPresenterCallback);
        }
        menu.stopDispatchingItemsChanged();
        if (!((Window.Callback)localObject1).onCreatePanelMenu(featureId, menu))
        {
          localPanelFeatureState.setMenu(null);
          if (i != 0)
          {
            paramPanelFeatureState = mDecorContentParent;
            if (paramPanelFeatureState != null)
            {
              paramPanelFeatureState.setMenu(null, mActionMenuPresenterCallback);
              return false;
            }
          }
        }
        else
        {
          refreshMenuContent = false;
        }
      }
      else
      {
        menu.stopDispatchingItemsChanged();
        localObject2 = frozenActionViewState;
        if (localObject2 != null)
        {
          menu.restoreActionViewStates((Bundle)localObject2);
          frozenActionViewState = null;
        }
        if (!((Window.Callback)localObject1).onPreparePanel(0, createdPanelView, menu))
        {
          if (i != 0)
          {
            paramPanelFeatureState = mDecorContentParent;
            if (paramPanelFeatureState != null) {
              paramPanelFeatureState.setMenu(null, mActionMenuPresenterCallback);
            }
          }
          menu.startDispatchingItemsChanged();
          return false;
        }
        if (paramKeyEvent != null) {
          i = paramKeyEvent.getDeviceId();
        } else {
          i = -1;
        }
        if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
          bool = true;
        } else {
          bool = false;
        }
        qwertyMode = bool;
        menu.setQwertyMode(qwertyMode);
        menu.startDispatchingItemsChanged();
      }
    }
    else
    {
      isPrepared = true;
      isHandled = false;
      mPreparedPanel = paramPanelFeatureState;
      return true;
    }
    label475:
    return false;
  }
  
  private void reopenMenu(android.support.v7.view.menu.f paramF, boolean paramBoolean)
  {
    paramF = mDecorContentParent;
    if ((paramF != null) && (paramF.canShowOverflowMenu()) && ((!ViewConfiguration.get(mContext).hasPermanentMenuKey()) || (mDecorContentParent.isOverflowMenuShowPending())))
    {
      paramF = getWindowCallback();
      if ((mDecorContentParent.isOverflowMenuShowing()) && (paramBoolean))
      {
        mDecorContentParent.hideOverflowMenu();
        if (!mIsDestroyed) {
          paramF.onPanelClosed(108, getPanelState0menu);
        }
      }
      else if ((paramF != null) && (!mIsDestroyed))
      {
        if ((mInvalidatePanelMenuPosted) && ((mInvalidatePanelMenuFeatures & 0x1) != 0))
        {
          mWindow.getDecorView().removeCallbacks(mInvalidatePanelMenuRunnable);
          mInvalidatePanelMenuRunnable.run();
        }
        PanelFeatureState localPanelFeatureState = getPanelState(0, true);
        android.support.v7.view.menu.f localF = menu;
        if ((localF != null) && (!refreshMenuContent) && (paramF.onPreparePanel(0, createdPanelView, localF)))
        {
          paramF.onMenuOpened(108, menu);
          mDecorContentParent.showOverflowMenu();
        }
      }
    }
    else
    {
      paramF = getPanelState(0, true);
      refreshDecorView = true;
      closePanel(paramF, false);
      openPanel(paramF, null);
    }
  }
  
  private int sanitizeWindowFeatureId(int paramInt)
  {
    if (paramInt == 8)
    {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
      return 108;
    }
    if (paramInt == 9)
    {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
      return 109;
    }
    return paramInt;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent)
  {
    if (paramViewParent == null) {
      return false;
    }
    View localView = mWindow.getDecorView();
    for (;;)
    {
      if (paramViewParent == null) {
        return true;
      }
      if ((paramViewParent == localView) || (!(paramViewParent instanceof View))) {
        break;
      }
      if (ViewCompat.setText((View)paramViewParent)) {
        return false;
      }
      paramViewParent = paramViewParent.getParent();
    }
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled()
  {
    if (!mSubDecorInstalled) {
      return;
    }
    throw new AndroidRuntimeException("Window feature must be requested before adding content");
  }
  
  int a(int paramInt)
  {
    if (paramInt != -100)
    {
      if (paramInt != 0) {
        return paramInt;
      }
      if ((Build.VERSION.SDK_INT >= 23) && (((UiModeManager)mContext.getSystemService(UiModeManager.class)).getNightMode() == 0)) {
        return -1;
      }
      d();
      return r.b();
    }
    return -1;
  }
  
  public boolean a()
  {
    boolean bool = false;
    int i = b();
    int j = a(i);
    if (j != -1) {
      bool = init(j);
    }
    if (i == 0)
    {
      d();
      r.add();
    }
    t = true;
    return bool;
  }
  
  int access$300(int paramInt)
  {
    int i = 0;
    int i2 = 0;
    Object localObject1 = mActionModeView;
    int i1 = 0;
    int n = i;
    int m = paramInt;
    if (localObject1 != null)
    {
      n = i;
      m = paramInt;
      if ((((View)localObject1).getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
      {
        localObject1 = (ViewGroup.MarginLayoutParams)mActionModeView.getLayoutParams();
        int k = 0;
        i = 0;
        int j;
        if (mActionModeView.isShown())
        {
          if (mTempRect1 == null)
          {
            mTempRect1 = new Rect();
            mTempRect2 = new Rect();
          }
          Object localObject2 = mTempRect1;
          Rect localRect = mTempRect2;
          ((Rect)localObject2).set(0, paramInt, 0, 0);
          ViewUtils.computeFitSystemWindows(mSubDecor, (Rect)localObject2, localRect);
          if (top == 0) {
            j = paramInt;
          } else {
            j = 0;
          }
          if (topMargin != j)
          {
            j = 1;
            topMargin = paramInt;
            localObject2 = mStatusGuard;
            if (localObject2 == null)
            {
              mStatusGuard = new View(mContext);
              mStatusGuard.setBackgroundColor(mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
              mSubDecor.addView(mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
              i = j;
            }
            else
            {
              localObject2 = ((View)localObject2).getLayoutParams();
              i = j;
              if (height != paramInt)
              {
                height = paramInt;
                mStatusGuard.setLayoutParams((ViewGroup.LayoutParams)localObject2);
                i = j;
              }
            }
          }
          if (mStatusGuard != null) {
            j = 1;
          } else {
            j = 0;
          }
          m = j;
          k = paramInt;
          if (!mOverlayActionMode)
          {
            k = paramInt;
            if (j != 0) {
              k = 0;
            }
          }
          j = m;
        }
        else
        {
          j = i2;
          i = k;
          k = paramInt;
          if (topMargin != 0)
          {
            i = 1;
            topMargin = 0;
            k = paramInt;
            j = i2;
          }
        }
        n = j;
        m = k;
        if (i != 0)
        {
          mActionModeView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
          m = k;
          n = j;
        }
      }
    }
    localObject1 = mStatusGuard;
    if (localObject1 != null)
    {
      if (n != 0) {
        paramInt = i1;
      } else {
        paramInt = 8;
      }
      ((View)localObject1).setVisibility(paramInt);
    }
    return m;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ((ViewGroup)mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    mOriginalWindowCallback.onContentChanged();
  }
  
  void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu)
  {
    Object localObject1 = paramPanelFeatureState;
    Object localObject2 = paramMenu;
    if (paramMenu == null)
    {
      PanelFeatureState localPanelFeatureState = paramPanelFeatureState;
      if (paramPanelFeatureState == null)
      {
        localPanelFeatureState = paramPanelFeatureState;
        if (paramInt >= 0)
        {
          localObject1 = mPanels;
          localPanelFeatureState = paramPanelFeatureState;
          if (paramInt < localObject1.length) {
            localPanelFeatureState = localObject1[paramInt];
          }
        }
      }
      localObject1 = localPanelFeatureState;
      localObject2 = paramMenu;
      if (localPanelFeatureState != null)
      {
        localObject2 = menu;
        localObject1 = localPanelFeatureState;
      }
    }
    if ((localObject1 != null) && (!isOpen)) {
      return;
    }
    if (!mIsDestroyed) {
      mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)localObject2);
    }
  }
  
  void checkCloseActionMenu(android.support.v7.view.menu.f paramF)
  {
    if (mClosingActionMenu) {
      return;
    }
    mClosingActionMenu = true;
    mDecorContentParent.dismissPopups();
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!mIsDestroyed)) {
      localCallback.onPanelClosed(108, paramF);
    }
    mClosingActionMenu = false;
  }
  
  void closePanel(int paramInt)
  {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean)
  {
    if ((paramBoolean) && (featureId == 0))
    {
      localObject = mDecorContentParent;
      if ((localObject != null) && (((DecorContentParent)localObject).isOverflowMenuShowing()))
      {
        checkCloseActionMenu(menu);
        return;
      }
    }
    Object localObject = (WindowManager)mContext.getSystemService("window");
    if ((localObject != null) && (isOpen))
    {
      ViewGroup localViewGroup = decorView;
      if (localViewGroup != null)
      {
        ((ViewManager)localObject).removeView(localViewGroup);
        if (paramBoolean) {
          callOnPanelClosed(featureId, paramPanelFeatureState, null);
        }
      }
    }
    isPrepared = false;
    isHandled = false;
    isOpen = false;
    shownPanelView = null;
    refreshDecorView = true;
    if (mPreparedPanel == paramPanelFeatureState) {
      mPreparedPanel = null;
    }
  }
  
  public View createView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    Object localObject = mAppCompatViewInflater;
    boolean bool2 = false;
    if (localObject == null)
    {
      localObject = mContext.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
      if ((localObject != null) && (!AppCompatViewInflater.class.getName().equals(localObject))) {
        try
        {
          mAppCompatViewInflater = ((AppCompatViewInflater)Class.forName((String)localObject).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        catch (Throwable localThrowable)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to instantiate custom view inflater ");
          localStringBuilder.append((String)localObject);
          localStringBuilder.append(". Falling back to default.");
          Log.i("AppCompatDelegate", localStringBuilder.toString(), localThrowable);
          mAppCompatViewInflater = new AppCompatViewInflater();
        }
      } else {
        mAppCompatViewInflater = new AppCompatViewInflater();
      }
    }
    boolean bool1 = false;
    if (EINK_SCREEN) {
      if ((paramAttributeSet instanceof XmlPullParser))
      {
        bool1 = bool2;
        if (((XmlPullParser)paramAttributeSet).getDepth() > 1) {
          bool1 = true;
        }
      }
      else
      {
        bool1 = shouldInheritContext((ViewParent)paramView);
      }
    }
    return mAppCompatViewInflater.createView(paramView, paramString, paramContext, paramAttributeSet, bool1, EINK_SCREEN, true, TintManager.get());
  }
  
  void dismissPopups()
  {
    Object localObject1 = mDecorContentParent;
    if (localObject1 != null) {
      ((DecorContentParent)localObject1).dismissPopups();
    }
    if (mActionModePopup != null)
    {
      mWindow.getDecorView().removeCallbacks(mShowActionModePopup);
      if (mActionModePopup.isShowing())
      {
        localObject1 = mActionModePopup;
        try
        {
          ((PopupWindow)localObject1).dismiss();
        }
        catch (IllegalArgumentException localIllegalArgumentException) {}
      }
      mActionModePopup = null;
    }
    endOnGoingFadeAnimation();
    Object localObject2 = getPanelState(0, false);
    if (localObject2 != null)
    {
      localObject2 = menu;
      if (localObject2 != null) {
        ((android.support.v7.view.menu.f)localObject2).close();
      }
    }
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    Object localObject = mOriginalWindowCallback;
    boolean bool = localObject instanceof c;
    int i = 1;
    if ((bool) || ((localObject instanceof AppCompatDialog)))
    {
      localObject = mWindow.getDecorView();
      if ((localObject != null) && (android.support.v4.view.i.a((View)localObject, paramKeyEvent))) {
        return true;
      }
    }
    if ((paramKeyEvent.getKeyCode() == 82) && (mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent))) {
      return true;
    }
    int j = paramKeyEvent.getKeyCode();
    if (paramKeyEvent.getAction() != 0) {
      i = 0;
    }
    if (i != 0) {
      return onKeyDown(j, paramKeyEvent);
    }
    return onKeyUp(j, paramKeyEvent);
  }
  
  void doInvalidatePanelMenu(int paramInt)
  {
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    if (menu != null)
    {
      Bundle localBundle = new Bundle();
      menu.saveActionViewStates(localBundle);
      if (localBundle.size() > 0) {
        frozenActionViewState = localBundle;
      }
      menu.stopDispatchingItemsChanged();
      menu.clear();
    }
    refreshMenuContent = true;
    refreshDecorView = true;
    if (((paramInt == 108) || (paramInt == 0)) && (mDecorContentParent != null))
    {
      localPanelFeatureState = getPanelState(0, false);
      if (localPanelFeatureState != null)
      {
        isPrepared = false;
        preparePanel(localPanelFeatureState, null);
      }
    }
  }
  
  void endOnGoingFadeAnimation()
  {
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = mFadeAnim;
    if (localViewPropertyAnimatorCompat != null) {
      localViewPropertyAnimatorCompat.cancel();
    }
  }
  
  final boolean f()
  {
    if (mSubDecorInstalled)
    {
      ViewGroup localViewGroup = mSubDecor;
      if ((localViewGroup != null) && (ViewCompat.get(localViewGroup))) {
        return true;
      }
    }
    return false;
  }
  
  PanelFeatureState findMenuPanel(Menu paramMenu)
  {
    PanelFeatureState[] arrayOfPanelFeatureState = mPanels;
    int i;
    if (arrayOfPanelFeatureState != null) {
      i = arrayOfPanelFeatureState.length;
    } else {
      i = 0;
    }
    int j = 0;
    while (j < i)
    {
      PanelFeatureState localPanelFeatureState = arrayOfPanelFeatureState[j];
      if ((localPanelFeatureState != null) && (menu == paramMenu)) {
        return localPanelFeatureState;
      }
      j += 1;
    }
    return null;
  }
  
  public View findViewById(int paramInt)
  {
    ensureSubDecor();
    return mWindow.findViewById(paramInt);
  }
  
  final Context getActionBarThemedContext()
  {
    Context localContext = null;
    Object localObject = getSupportActionBar();
    if (localObject != null) {
      localContext = ((ActionBar)localObject).getThemedContext();
    }
    localObject = localContext;
    if (localContext == null) {
      localObject = mContext;
    }
    return localObject;
  }
  
  public final i getDrawerToggleDelegate()
  {
    return new h(this);
  }
  
  public MenuInflater getMenuInflater()
  {
    if (mMenuInflater == null)
    {
      initWindowDecorActionBar();
      Object localObject = mActionBar;
      if (localObject != null) {
        localObject = ((ActionBar)localObject).getThemedContext();
      } else {
        localObject = mContext;
      }
      mMenuInflater = new SupportMenuInflater((Context)localObject);
    }
    return mMenuInflater;
  }
  
  protected PanelFeatureState getPanelState(int paramInt, boolean paramBoolean)
  {
    Object localObject2 = mPanels;
    Object localObject1 = localObject2;
    if ((localObject2 == null) || (localObject2.length <= paramInt))
    {
      arrayOfPanelFeatureState = new PanelFeatureState[paramInt + 1];
      if (localObject2 != null) {
        System.arraycopy(localObject2, 0, arrayOfPanelFeatureState, 0, localObject2.length);
      }
      localObject1 = arrayOfPanelFeatureState;
      mPanels = arrayOfPanelFeatureState;
    }
    PanelFeatureState[] arrayOfPanelFeatureState = localObject1[paramInt];
    localObject2 = arrayOfPanelFeatureState;
    if (arrayOfPanelFeatureState == null)
    {
      localObject2 = new PanelFeatureState(paramInt);
      localObject1[paramInt] = localObject2;
    }
    return localObject2;
  }
  
  public ActionBar getSupportActionBar()
  {
    initWindowDecorActionBar();
    return mActionBar;
  }
  
  final CharSequence getTitle()
  {
    Window.Callback localCallback = mOriginalWindowCallback;
    if ((localCallback instanceof Activity)) {
      return ((Activity)localCallback).getTitle();
    }
    return mTitle;
  }
  
  final Window.Callback getWindowCallback()
  {
    return mWindow.getCallback();
  }
  
  public void installViewFactory()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(mContext);
    if (localLayoutInflater.getFactory() == null)
    {
      LayoutInflaterCompatHC.setFactory(localLayoutInflater, this);
      return;
    }
    if (!(localLayoutInflater.getFactory2() instanceof AppCompatDelegateImplV7)) {
      Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
    }
  }
  
  public void invalidateOptionsMenu()
  {
    ActionBar localActionBar = getSupportActionBar();
    if ((localActionBar != null) && (localActionBar.invalidateOptionsMenu())) {
      return;
    }
    invalidatePanelMenu(0);
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return mHandleNativeActionModes;
  }
  
  boolean onBackPressed()
  {
    Object localObject = mActionMode;
    if (localObject != null)
    {
      ((ActionMode)localObject).finish();
      return true;
    }
    localObject = getSupportActionBar();
    return (localObject != null) && (((ActionBar)localObject).collapseActionView());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if ((mHasActionBar) && (mSubDecorInstalled))
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null) {
        localActionBar.onConfigurationChanged(paramConfiguration);
      }
    }
    AppCompatDrawableManager.get().addDrawableToCache(mContext);
    a();
  }
  
  public void onCreate()
  {
    if (mInvalidatePanelMenuPosted) {
      mWindow.getDecorView().removeCallbacks(mInvalidatePanelMenuRunnable);
    }
    mIsDestroyed = true;
    Object localObject = mActionBar;
    if (localObject != null) {
      ((ActionBar)localObject).onDestroy();
    }
    localObject = r;
    if (localObject != null) {
      ((f)localObject).a();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Object localObject2 = mOriginalWindowCallback;
    if ((localObject2 instanceof Activity))
    {
      Object localObject1 = null;
      localObject2 = (Activity)localObject2;
      try
      {
        localObject2 = NavUtils.getParentActivityName((Activity)localObject2);
        localObject1 = localObject2;
      }
      catch (IllegalArgumentException localIllegalArgumentException) {}
      if (localObject1 != null)
      {
        localObject1 = peekSupportActionBar();
        if (localObject1 == null) {
          mEnableDefaultActionBarUp = true;
        } else {
          ((ActionBar)localObject1).setDefaultDisplayHomeAsUpEnabled(true);
        }
      }
    }
    if ((paramBundle != null) && (a == -100)) {
      a = paramBundle.getInt("appcompat:local_night_mode", -100);
    }
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    if (paramInt != 4)
    {
      if (paramInt != 82) {
        return false;
      }
      onKeyDownPanel(0, paramKeyEvent);
      return true;
    }
    if ((paramKeyEvent.getFlags() & 0x80) == 0) {
      bool = false;
    }
    mLongPressBackDown = bool;
    return false;
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject = getSupportActionBar();
    if ((localObject != null) && (((ActionBar)localObject).onKeyShortcut(paramInt, paramKeyEvent))) {
      return true;
    }
    localObject = mPreparedPanel;
    if ((localObject != null) && (performPanelShortcut((PanelFeatureState)localObject, paramKeyEvent.getKeyCode(), paramKeyEvent, 1)))
    {
      paramKeyEvent = mPreparedPanel;
      if (paramKeyEvent != null)
      {
        isHandled = true;
        return true;
      }
    }
    else
    {
      if (mPreparedPanel == null)
      {
        localObject = getPanelState(0, true);
        preparePanel((PanelFeatureState)localObject, paramKeyEvent);
        boolean bool = performPanelShortcut((PanelFeatureState)localObject, paramKeyEvent.getKeyCode(), paramKeyEvent, 1);
        isPrepared = false;
        if (!bool) {
          break label117;
        }
        return true;
      }
      return false;
    }
    return true;
    label117:
    return false;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4)
    {
      if (paramInt != 82) {
        return false;
      }
      onKeyUpPanel(0, paramKeyEvent);
      return true;
    }
    boolean bool = mLongPressBackDown;
    mLongPressBackDown = false;
    paramKeyEvent = getPanelState(0, false);
    if ((paramKeyEvent != null) && (isOpen))
    {
      if (!bool)
      {
        closePanel(paramKeyEvent, true);
        return true;
      }
    }
    else {
      return onBackPressed();
    }
    return true;
  }
  
  public boolean onMenuItemSelected(android.support.v7.view.menu.f paramF, MenuItem paramMenuItem)
  {
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!mIsDestroyed))
    {
      paramF = findMenuPanel(paramF.getRootMenu());
      if (paramF != null) {
        return localCallback.onMenuItemSelected(featureId, paramMenuItem);
      }
    }
    return false;
  }
  
  public void onMenuModeChange(android.support.v7.view.menu.f paramF)
  {
    reopenMenu(paramF, true);
  }
  
  void onMenuOpened(int paramInt)
  {
    if (paramInt == 108)
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null) {
        localActionBar.dispatchMenuVisibilityChanged(true);
      }
    }
  }
  
  void onPanelClosed(int paramInt)
  {
    Object localObject;
    if (paramInt == 108)
    {
      localObject = getSupportActionBar();
      if (localObject != null) {
        ((ActionBar)localObject).dispatchMenuVisibilityChanged(false);
      }
    }
    else if (paramInt == 0)
    {
      localObject = getPanelState(paramInt, true);
      if (isOpen) {
        closePanel((PanelFeatureState)localObject, false);
      }
    }
  }
  
  public void onPostCreate(Bundle paramBundle)
  {
    ensureSubDecor();
  }
  
  public void onPostResume()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setShowHideAnimationEnabled(true);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = a;
    if (i != -100) {
      paramBundle.putInt("appcompat:local_night_mode", i);
    }
  }
  
  public void onStop()
  {
    Object localObject = getSupportActionBar();
    if (localObject != null) {
      ((ActionBar)localObject).setShowHideAnimationEnabled(false);
    }
    localObject = r;
    if (localObject != null) {
      ((f)localObject).a();
    }
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  public final void onTitleChanged(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    Object localObject = mDecorContentParent;
    if (localObject != null)
    {
      ((DecorContentParent)localObject).setWindowTitle(paramCharSequence);
      return;
    }
    if (peekSupportActionBar() != null)
    {
      peekSupportActionBar().setWindowTitle(paramCharSequence);
      return;
    }
    localObject = mTitleView;
    if (localObject != null) {
      ((TextView)localObject).setText(paramCharSequence);
    }
  }
  
  final ActionBar peekSupportActionBar()
  {
    return mActionBar;
  }
  
  public boolean requestWindowFeature(int paramInt)
  {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if ((mWindowNoTitle) && (paramInt == 108)) {
      return false;
    }
    if ((mHasActionBar) && (paramInt == 1)) {
      mHasActionBar = false;
    }
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 5)
        {
          if (paramInt != 10)
          {
            if (paramInt != 108)
            {
              if (paramInt != 109) {
                return mWindow.requestFeature(paramInt);
              }
              throwFeatureRequestIfSubDecorInstalled();
              mOverlayActionBar = true;
              return true;
            }
            throwFeatureRequestIfSubDecorInstalled();
            mHasActionBar = true;
            return true;
          }
          throwFeatureRequestIfSubDecorInstalled();
          mOverlayActionMode = true;
          return true;
        }
        throwFeatureRequestIfSubDecorInstalled();
        mFeatureProgress = true;
        return true;
      }
      throwFeatureRequestIfSubDecorInstalled();
      mFeatureIndeterminateProgress = true;
      return true;
    }
    throwFeatureRequestIfSubDecorInstalled();
    mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    LayoutInflater.from(mContext).inflate(paramInt, localViewGroup);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView, paramLayoutParams);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void setSupportActionBar()
  {
    a();
  }
  
  public void setSupportActionBar(Toolbar paramToolbar)
  {
    if (!(mOriginalWindowCallback instanceof Activity)) {
      return;
    }
    ActionBar localActionBar = getSupportActionBar();
    if (!(localActionBar instanceof WindowDecorActionBar))
    {
      mMenuInflater = null;
      if (localActionBar != null) {
        localActionBar.onDestroy();
      }
      if (paramToolbar != null)
      {
        paramToolbar = new ToolbarActionBar(paramToolbar, ((Activity)mOriginalWindowCallback).getTitle(), mAppCompatWindowCallback);
        mActionBar = paramToolbar;
        mWindow.setCallback(paramToolbar.getWrappedWindowCallback());
      }
      else
      {
        mActionBar = null;
        mWindow.setCallback(mAppCompatWindowCallback);
      }
      invalidateOptionsMenu();
      return;
    }
    throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
  }
  
  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback)
  {
    if (paramCallback != null)
    {
      Object localObject = mActionMode;
      if (localObject != null) {
        ((ActionMode)localObject).finish();
      }
      paramCallback = new ActionModeCallbackWrapperV7(paramCallback);
      localObject = getSupportActionBar();
      if (localObject != null)
      {
        mActionMode = ((ActionBar)localObject).startActionMode(paramCallback);
        localObject = mActionMode;
        if (localObject != null)
        {
          AppCompatCallback localAppCompatCallback = mAppCompatCallback;
          if (localAppCompatCallback != null) {
            localAppCompatCallback.onSupportActionModeStarted((ActionMode)localObject);
          }
        }
      }
      if (mActionMode == null) {
        mActionMode = startSupportActionModeFromWindow(paramCallback);
      }
      return mActionMode;
    }
    throw new IllegalArgumentException("ActionMode callback can not be null.");
  }
  
  ActionMode startSupportActionModeFromWindow(ActionMode.Callback paramCallback)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a16 = a15\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  final class ActionMenuPresenterCallback
    implements l.a
  {
    ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(android.support.v7.view.menu.f paramF, boolean paramBoolean)
    {
      checkCloseActionMenu(paramF);
    }
    
    public boolean onOpenSubMenu(android.support.v7.view.menu.f paramF)
    {
      Window.Callback localCallback = getWindowCallback();
      if (localCallback != null) {
        localCallback.onMenuOpened(108, paramF);
      }
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV7
    implements ActionMode.Callback
  {
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV7(ActionMode.Callback paramCallback)
    {
      mWrapped = paramCallback;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onCreateActionMode(paramActionMode, paramMenu);
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mWrapped.onDestroyActionMode(paramActionMode);
      paramActionMode = AppCompatDelegateImplV7.this;
      if (mActionModePopup != null) {
        mWindow.getDecorView().removeCallbacks(mShowActionModePopup);
      }
      paramActionMode = AppCompatDelegateImplV7.this;
      if (mActionModeView != null)
      {
        paramActionMode.endOnGoingFadeAnimation();
        paramActionMode = AppCompatDelegateImplV7.this;
        localObject = ViewCompat.animate(mActionModeView);
        ((ViewPropertyAnimatorCompat)localObject).alpha(0.0F);
        mFadeAnim = ((ViewPropertyAnimatorCompat)localObject);
        mFadeAnim.setListener(new h.j.a(this));
      }
      paramActionMode = AppCompatDelegateImplV7.this;
      Object localObject = mAppCompatCallback;
      if (localObject != null) {
        ((AppCompatCallback)localObject).onSupportActionModeFinished(mActionMode);
      }
      mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
  
  class ListMenuDecorView
    extends ContentFrameLayout
  {
    public ListMenuDecorView(Context paramContext)
    {
      super();
    }
    
    private boolean isOutOfBounds(int paramInt1, int paramInt2)
    {
      return (paramInt1 < -5) || (paramInt2 < -5) || (paramInt1 > getWidth() + 5) || (paramInt2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      return (AppCompatDelegateImplV7.this.dispatchKeyEvent(paramKeyEvent)) || (super.dispatchKeyEvent(paramKeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) && (isOutOfBounds((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())))
      {
        closePanel(0);
        return true;
      }
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    
    public void setBackgroundResource(int paramInt)
    {
      setBackgroundDrawable(org.org.v4.gui.helpers.Resources.getDrawable(getContext(), paramInt));
    }
  }
  
  public final class PanelFeatureState
  {
    int background;
    View createdPanelView;
    ViewGroup decorView;
    int featureId;
    Bundle frozenActionViewState;
    int gravity;
    boolean isHandled;
    boolean isOpen;
    boolean isPrepared;
    e listMenuPresenter;
    Context listPresenterContext;
    android.support.v7.view.menu.f menu;
    public boolean qwertyMode;
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    int windowAnimations;
    int x;
    int y;
    
    PanelFeatureState()
    {
      featureId = this$1;
      refreshDecorView = false;
    }
    
    MenuView getListMenuView(l.a paramA)
    {
      if (menu == null) {
        return null;
      }
      if (listMenuPresenter == null)
      {
        listMenuPresenter = new e(listPresenterContext, R.layout.abc_list_menu_item_layout);
        listMenuPresenter.a(paramA);
        menu.a(listMenuPresenter);
      }
      return listMenuPresenter.getMenuView(decorView);
    }
    
    public boolean hasPanelItems()
    {
      if (shownPanelView == null) {
        return false;
      }
      if (createdPanelView != null) {
        return true;
      }
      return listMenuPresenter.a().getCount() > 0;
    }
    
    void setMenu(android.support.v7.view.menu.f paramF)
    {
      Object localObject = menu;
      if (paramF == localObject) {
        return;
      }
      if (localObject != null) {
        ((android.support.v7.view.menu.f)localObject).b(listMenuPresenter);
      }
      menu = paramF;
      if (paramF != null)
      {
        localObject = listMenuPresenter;
        if (localObject != null) {
          paramF.a((l)localObject);
        }
      }
    }
    
    void setStyle(Context paramContext)
    {
      TypedValue localTypedValue = new TypedValue();
      Resources.Theme localTheme = paramContext.getResources().newTheme();
      localTheme.setTo(paramContext.getTheme());
      localTheme.resolveAttribute(R.attr.actionBarPopupTheme, localTypedValue, true);
      int i = resourceId;
      if (i != 0) {
        localTheme.applyStyle(i, true);
      }
      localTheme.resolveAttribute(R.attr.panelMenuListTheme, localTypedValue, true);
      i = resourceId;
      if (i != 0) {
        localTheme.applyStyle(i, true);
      } else {
        localTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
      }
      paramContext = new ContextThemeWrapper(paramContext, 0);
      paramContext.getTheme().setTo(localTheme);
      listPresenterContext = paramContext;
      paramContext = paramContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
      background = paramContext.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
      windowAnimations = paramContext.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
      paramContext.recycle();
    }
  }
  
  final class PanelMenuPresenterCallback
    implements l.a
  {
    PanelMenuPresenterCallback() {}
    
    public void onCloseMenu(android.support.v7.view.menu.f paramF, boolean paramBoolean)
    {
      android.support.v7.view.menu.f localF = paramF.getRootMenu();
      int i;
      if (localF != paramF) {
        i = 1;
      } else {
        i = 0;
      }
      AppCompatDelegateImplV7 localAppCompatDelegateImplV7 = AppCompatDelegateImplV7.this;
      if (i != 0) {
        paramF = localF;
      }
      paramF = localAppCompatDelegateImplV7.findMenuPanel(paramF);
      if (paramF != null)
      {
        if (i != 0)
        {
          callOnPanelClosed(featureId, paramF, localF);
          closePanel(paramF, true);
          return;
        }
        closePanel(paramF, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(android.support.v7.view.menu.f paramF)
    {
      if (paramF == null)
      {
        Object localObject = AppCompatDelegateImplV7.this;
        if (mHasActionBar)
        {
          localObject = ((AppCompatDelegateImplV7)localObject).getWindowCallback();
          if ((localObject != null) && (!mIsDestroyed)) {
            ((Window.Callback)localObject).onMenuOpened(108, paramF);
          }
        }
      }
      return true;
    }
  }
}
