package com.joanzapata.material.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v7.widget.ListViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import apps.muzei.api.R.attr;
import apps.muzei.api.R.styleable;
import java.lang.reflect.Method;
import org.org.jaxen.text.TextUtilsCompat;

public class ListPopupWindow
{
  private static Method sClipToWindowEnabledMethod;
  private ListAdapter mAdapter;
  private Context mContext;
  private boolean mDropDownAlwaysVisible = false;
  private View mDropDownAnchorView;
  private int mDropDownGravity = 0;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  private DropDownListView mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private int mDropDownWindowLayoutType;
  private boolean mForceIgnoreOutsideTouch = false;
  private Handler mHandler = new Handler();
  private final ListSelectorHider mHideSelector = new ListSelectorHider(null);
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  private int mMinute;
  private boolean mModal;
  private DataSetObserver mObserver;
  private PopupWindowCompat mPopup;
  private int mPromptPosition = 0;
  private View mPromptView;
  private final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable(null);
  private final PopupScrollListener mScrollListener = new PopupScrollListener(null);
  private Rect mTempRect = new Rect();
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor(null);
  
  static
  {
    Object localObject = Boolean.TYPE;
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { localObject });
      sClipToWindowEnabledMethod = (Method)localObject;
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
    }
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    mContext = paramContext;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    mDropDownHorizontalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    mDropDownVerticalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (mDropDownVerticalOffset != 0) {
      mDropDownVerticalOffsetSet = true;
    }
    localTypedArray.recycle();
    mPopup = new PopupWindowCompat(paramContext, paramAttributeSet, paramInt1);
    mPopup.setInputMethodMode(1);
    TextUtilsCompat.getLayoutDirectionFromLocale(mContext.getResources().getConfiguration().locale);
  }
  
  private int buildDropDown()
  {
    int j = 0;
    int i = 0;
    Object localObject2;
    if (mDropDownList == null)
    {
      Object localObject3 = mContext;
      new ListPopupWindow.2(this);
      mDropDownList = new DropDownListView((Context)localObject3, mModal ^ true);
      localObject1 = mDropDownListHighlight;
      if (localObject1 != null) {
        mDropDownList.setSelector((Drawable)localObject1);
      }
      mDropDownList.setAdapter(mAdapter);
      mDropDownList.setOnItemClickListener(mItemClickListener);
      mDropDownList.setFocusable(true);
      mDropDownList.setFocusableInTouchMode(true);
      mDropDownList.setOnItemSelectedListener(new IcsListPopupWindow.1(this));
      mDropDownList.setOnScrollListener(mScrollListener);
      localObject1 = mItemSelectedListener;
      if (localObject1 != null) {
        mDropDownList.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)localObject1);
      }
      localObject2 = mDropDownList;
      View localView = mPromptView;
      localObject1 = localObject2;
      if (localView != null)
      {
        localObject1 = new LinearLayout((Context)localObject3);
        ((LinearLayout)localObject1).setOrientation(1);
        localObject3 = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        i = mPromptPosition;
        if (i != 0)
        {
          if (i != 1)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Invalid hint position ");
            ((StringBuilder)localObject2).append(mPromptPosition);
            Log.e("ListPopupWindow", ((StringBuilder)localObject2).toString());
          }
          else
          {
            ((ViewGroup)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
            ((ViewGroup)localObject1).addView(localView);
          }
        }
        else
        {
          ((ViewGroup)localObject1).addView(localView);
          ((ViewGroup)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
        }
        localView.measure(View.MeasureSpec.makeMeasureSpec(mDropDownWidth, Integer.MIN_VALUE), 0);
        localObject2 = (LinearLayout.LayoutParams)localView.getLayoutParams();
        i = localView.getMeasuredHeight() + topMargin + bottomMargin;
      }
      mPopup.setContentView((View)localObject1);
    }
    else
    {
      localObject1 = mPromptView;
      i = j;
      if (localObject1 != null)
      {
        localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
        i = ((View)localObject1).getMeasuredHeight() + topMargin + bottomMargin;
      }
    }
    int k = 0;
    Object localObject1 = mPopup.getBackground();
    if (localObject1 != null)
    {
      ((Drawable)localObject1).getPadding(mTempRect);
      localObject1 = mTempRect;
      m = top;
      j = m + bottom;
      k = j;
      if (!mDropDownVerticalOffsetSet)
      {
        mDropDownVerticalOffset = (-m);
        k = j;
      }
    }
    else
    {
      mTempRect.setEmpty();
    }
    j = 0;
    if (Build.VERSION.SDK_INT >= 21) {
      j = Math.max(show("status_bar_height"), show("navigation_bar_height"));
    }
    if (mPopup.getInputMethodMode() == 2) {}
    int m = mPopup.getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset) - j;
    if ((!mDropDownAlwaysVisible) && (mDropDownHeight != -1))
    {
      j = mDropDownWidth;
      if (j != -2)
      {
        if (j != -1)
        {
          j = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
        }
        else
        {
          j = mContext.getResources().getDisplayMetrics().widthPixels;
          localObject1 = mTempRect;
          j = View.MeasureSpec.makeMeasureSpec(j - (left + right), 1073741824);
        }
      }
      else
      {
        j = mContext.getResources().getDisplayMetrics().widthPixels;
        localObject1 = mTempRect;
        j = View.MeasureSpec.makeMeasureSpec(j - (left + right), Integer.MIN_VALUE);
      }
      m = mDropDownList.measureHeightOfChildrenCompat(j, 0, -1, m - i, -1);
      j = i;
      if (m > 0) {
        j = i + k;
      }
      return m + j;
    }
    return m + k;
  }
  
  private void removePromptView()
  {
    Object localObject = mPromptView;
    if (localObject != null)
    {
      localObject = ((View)localObject).getParent();
      if ((localObject instanceof ViewGroup)) {
        ((ViewGroup)localObject).removeView(mPromptView);
      }
    }
  }
  
  private void setPopupClipToScreenEnabled(boolean paramBoolean)
  {
    Method localMethod = sClipToWindowEnabledMethod;
    if (localMethod != null)
    {
      PopupWindowCompat localPopupWindowCompat = mPopup;
      try
      {
        localMethod.invoke(localPopupWindowCompat, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (Exception localException)
      {
        Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        return;
      }
    }
    if ((paramBoolean) && (Build.VERSION.SDK_INT >= 3)) {
      mPopup.setClippingEnabled(false);
    }
  }
  
  private int show(String paramString)
  {
    int i = mContext.getResources().getIdentifier(paramString, "dimen", "android");
    if (i > 0) {
      return mContext.getResources().getDimensionPixelSize(i);
    }
    return 0;
  }
  
  public void clearListSelection()
  {
    DropDownListView localDropDownListView = mDropDownList;
    if (localDropDownListView != null)
    {
      DropDownListView.access$setMListSelectionHidden(localDropDownListView, true);
      localDropDownListView.requestLayout();
    }
  }
  
  public void dismiss()
  {
    mPopup.dismiss();
    removePromptView();
    mPopup.setContentView(null);
    mDropDownList = null;
    mHandler.removeCallbacks(mResizePopupRunnable);
  }
  
  public View getAnchorView()
  {
    return mDropDownAnchorView;
  }
  
  public Drawable getBackground()
  {
    return mPopup.getBackground();
  }
  
  public int getHorizontalOffset()
  {
    return mDropDownHorizontalOffset;
  }
  
  public ListView getListView()
  {
    return mDropDownList;
  }
  
  public int getVerticalOffset()
  {
    if (!mDropDownVerticalOffsetSet) {
      return 0;
    }
    return mDropDownVerticalOffset;
  }
  
  public int getWidth()
  {
    return mDropDownWidth;
  }
  
  public boolean isInputMethodNotNeeded()
  {
    return mPopup.getInputMethodMode() == 2;
  }
  
  public boolean isShowing()
  {
    return mPopup.isShowing();
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    DataSetObserver localDataSetObserver = mObserver;
    if (localDataSetObserver == null)
    {
      mObserver = new PopupDataSetObserver(null);
    }
    else
    {
      ListAdapter localListAdapter = mAdapter;
      if (localListAdapter != null) {
        localListAdapter.unregisterDataSetObserver(localDataSetObserver);
      }
    }
    mAdapter = paramListAdapter;
    if (mAdapter != null) {
      paramListAdapter.registerDataSetObserver(mObserver);
    }
    paramListAdapter = mDropDownList;
    if (paramListAdapter != null) {
      paramListAdapter.setAdapter(mAdapter);
    }
  }
  
  public void setAnchorView(View paramView)
  {
    mDropDownAnchorView = paramView;
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContentWidth(int paramInt)
  {
    Object localObject = mPopup.getBackground();
    if (localObject != null)
    {
      ((Drawable)localObject).getPadding(mTempRect);
      localObject = mTempRect;
      mDropDownWidth = (left + right + paramInt);
      return;
    }
    setWidth(paramInt);
  }
  
  public void setHeight(int paramInt)
  {
    mDropDownWindowLayoutType = paramInt;
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    mDropDownHorizontalOffset = paramInt;
  }
  
  public void setInputMethodMode(int paramInt)
  {
    mPopup.setInputMethodMode(paramInt);
  }
  
  public void setMinute(int paramInt)
  {
    mMinute = paramInt;
  }
  
  public void setModal(boolean paramBoolean)
  {
    mModal = paramBoolean;
    mPopup.setFocusable(paramBoolean);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setPromptPosition(int paramInt)
  {
    mPromptPosition = paramInt;
  }
  
  public void setVerticalOffset(int paramInt)
  {
    mDropDownVerticalOffset = paramInt;
    mDropDownVerticalOffsetSet = true;
  }
  
  public void setWidth(int paramInt)
  {
    mDropDownWidth = paramInt;
  }
  
  public void show()
  {
    int j = buildDropDown();
    int i = 0;
    int m = 0;
    boolean bool3 = isInputMethodNotNeeded();
    boolean bool4 = mPopup.isShowing();
    boolean bool2 = true;
    boolean bool1 = true;
    int k = -1;
    if (bool4)
    {
      i = mDropDownWidth;
      if (i == -1) {
        i = -1;
      } else if (i == -2) {
        i = getAnchorView().getWidth();
      } else {
        i = mDropDownWidth;
      }
      m = mDropDownHeight;
      if (m == -1)
      {
        if (!bool3) {
          j = -1;
        }
        if (bool3)
        {
          localPopupWindowCompat = mPopup;
          if (mDropDownWidth != -1) {
            k = 0;
          }
          localPopupWindowCompat.setWindowLayoutMode(k, 0);
        }
        else
        {
          localPopupWindowCompat = mPopup;
          if (mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          }
          localPopupWindowCompat.setWindowLayoutMode(k, -1);
        }
      }
      else if (m != -2)
      {
        j = mDropDownHeight;
      }
      localPopupWindowCompat = mPopup;
      if ((mForceIgnoreOutsideTouch) || (mDropDownAlwaysVisible)) {
        bool1 = false;
      }
      localPopupWindowCompat.setOutsideTouchable(bool1);
      mPopup.update(getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, i, j);
      return;
    }
    k = mDropDownWidth;
    if (k == -1) {
      i = -1;
    } else if (k == -2) {
      mPopup.setWidth(getAnchorView().getWidth());
    } else {
      mPopup.setWidth(k);
    }
    k = mDropDownHeight;
    if (k == -1)
    {
      j = -1;
    }
    else if (k == -2)
    {
      mPopup.setHeight(j);
      j = m;
    }
    else
    {
      mPopup.setHeight(k);
      j = m;
    }
    mPopup.setWindowLayoutMode(i, j);
    setPopupClipToScreenEnabled(true);
    PopupWindowCompat localPopupWindowCompat = mPopup;
    if ((!mForceIgnoreOutsideTouch) && (!mDropDownAlwaysVisible)) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    localPopupWindowCompat.setOutsideTouchable(bool1);
    mPopup.setTouchInterceptor(mTouchInterceptor);
    android.support.v4.widget.PopupWindowCompat.update(mPopup, getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
    mDropDownList.setSelection(-1);
    if ((!mModal) || (mDropDownList.touchModeDrawsInPressedStateCompat())) {
      clearListSelection();
    }
    if (!mModal) {
      mHandler.post(mHideSelector);
    }
    if (mDropDownWindowLayoutType != 0) {
      mPopup.getContentView().getViewTreeObserver().addOnPreDrawListener(new MainActivity.1(this));
    }
  }
  
  class DropDownListView
    extends ListView
  {
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    
    public DropDownListView(boolean paramBoolean)
    {
      super(null, R.attr.dropDownListViewStyle);
      mHijackFocus = paramBoolean;
      setCacheColorHint(0);
    }
    
    public boolean touchModeDrawsInPressedStateCompat()
    {
      return ((mHijackFocus) && (mListSelectionHidden)) || (super.isInTouchMode());
    }
  }
  
  class ListSelectorHider
    implements Runnable
  {
    private ListSelectorHider() {}
    
    public void run()
    {
      clearListSelection();
    }
  }
  
  class PopupDataSetObserver
    extends DataSetObserver
  {
    private PopupDataSetObserver() {}
    
    public void onChanged()
    {
      if (isShowing()) {
        show();
      }
    }
    
    public void onInvalidated()
    {
      dismiss();
    }
  }
  
  class PopupScrollListener
    implements AbsListView.OnScrollListener
  {
    private PopupScrollListener() {}
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if ((paramInt == 1) && (!isInputMethodNotNeeded()) && (ListPopupWindow.access$getMPopup(ListPopupWindow.this).getContentView() != null))
      {
        ListPopupWindow.access$getMHandler(ListPopupWindow.this).removeCallbacks(ListPopupWindow.access$getMResizePopupRunnable(ListPopupWindow.this));
        ListPopupWindow.access$getMResizePopupRunnable(ListPopupWindow.this).run();
      }
    }
  }
  
  class PopupTouchInterceptor
    implements View.OnTouchListener
  {
    private PopupTouchInterceptor() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      if ((i == 0) && (ListPopupWindow.access$getMPopup(ListPopupWindow.this) != null) && (ListPopupWindow.access$getMPopup(ListPopupWindow.this).isShowing()) && (j >= 0) && (j < ListPopupWindow.access$getMPopup(ListPopupWindow.this).getWidth()) && (k >= 0) && (k < ListPopupWindow.access$getMPopup(ListPopupWindow.this).getHeight())) {
        ListPopupWindow.access$getMHandler(ListPopupWindow.this).postDelayed(ListPopupWindow.access$getMResizePopupRunnable(ListPopupWindow.this), 250L);
      } else if (i == 1) {
        ListPopupWindow.access$getMHandler(ListPopupWindow.this).removeCallbacks(ListPopupWindow.access$getMResizePopupRunnable(ListPopupWindow.this));
      }
      return false;
    }
  }
  
  class ResizePopupRunnable
    implements Runnable
  {
    private ResizePopupRunnable() {}
    
    public void run()
    {
      if ((ListPopupWindow.access$getMDropDownList(ListPopupWindow.this) != null) && (ListPopupWindow.access$getMDropDownList(ListPopupWindow.this).getCount() > ListPopupWindow.access$getMDropDownList(ListPopupWindow.this).getChildCount()))
      {
        int i = ListPopupWindow.access$getMDropDownList(ListPopupWindow.this).getChildCount();
        ListPopupWindow localListPopupWindow = ListPopupWindow.this;
        if (i <= mListItemExpandMaximum)
        {
          ListPopupWindow.access$getMPopup(localListPopupWindow).setInputMethodMode(2);
          show();
        }
      }
    }
  }
}
