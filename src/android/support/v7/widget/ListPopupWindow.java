package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;
import org.org.v4.content.R.styleable;

public class ListPopupWindow
  implements android.support.v7.view.menu.ListPopupWindow
{
  private static Method b;
  private static Method sClipToWindowEnabledMethod;
  private static Method sGetMaxAvailableHeightMethod;
  private boolean h;
  private ListAdapter mAdapter;
  private Context mContext;
  private boolean mDropDownAlwaysVisible = false;
  private View mDropDownAnchorView;
  private int mDropDownGravity = 0;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  ListPopupWindow.DropDownListView mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private int mDropDownWindowLayoutType = 1002;
  private boolean mForceIgnoreOutsideTouch = false;
  final Handler mHandler;
  private final p0.c mHideSelector = new p0.c(this);
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  private boolean mModal;
  private DataSetObserver mObserver;
  PopupWindow mPopup;
  private int mPromptPosition = 0;
  private View mPromptView;
  final p0.g mRunnable = new p0.g(this);
  private final p0.e mScrollListener = new p0.e(this);
  private Rect mTempAdapter;
  private final Rect mTempRect = new Rect();
  private final p0.f mTouchInterceptor = new p0.f(this);
  private boolean y;
  
  static
  {
    Object localObject1 = Boolean.TYPE;
    try
    {
      localObject1 = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { localObject1 });
      sClipToWindowEnabledMethod = (Method)localObject1;
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
    }
    Object localObject2 = Integer.TYPE;
    Class localClass = Boolean.TYPE;
    try
    {
      localObject2 = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[] { View.class, localObject2, localClass });
      sGetMaxAvailableHeightMethod = (Method)localObject2;
    }
    catch (NoSuchMethodException localNoSuchMethodException2)
    {
      Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
    }
    try
    {
      Method localMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[] { Rect.class });
      b = localMethod;
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException3)
    {
      Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
    }
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    mContext = paramContext;
    mHandler = new Handler(paramContext.getMainLooper());
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    mDropDownHorizontalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    mDropDownVerticalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (mDropDownVerticalOffset != 0) {
      mDropDownVerticalOffsetSet = true;
    }
    localTypedArray.recycle();
    mPopup = new AppCompatPopupWindow(paramContext, paramAttributeSet, paramInt1, paramInt2);
    mPopup.setInputMethodMode(1);
  }
  
  private int buildDropDown()
  {
    int j = 0;
    int i = 0;
    Object localObject1 = mDropDownList;
    boolean bool = false;
    Object localObject2;
    int k;
    if (localObject1 == null)
    {
      Object localObject3 = mContext;
      new p0.a(this);
      mDropDownList = e((Context)localObject3, mModal ^ true);
      localObject1 = mDropDownListHighlight;
      if (localObject1 != null) {
        mDropDownList.setSelector((Drawable)localObject1);
      }
      mDropDownList.setAdapter(mAdapter);
      mDropDownList.setOnItemClickListener(mItemClickListener);
      mDropDownList.setFocusable(true);
      mDropDownList.setFocusableInTouchMode(true);
      mDropDownList.setOnItemSelectedListener(new p0.b(this));
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
        if (mDropDownWidth >= 0)
        {
          i = Integer.MIN_VALUE;
          j = mDropDownWidth;
        }
        else
        {
          i = 0;
          j = 0;
        }
        localView.measure(View.MeasureSpec.makeMeasureSpec(j, i), 0);
        localObject2 = (LinearLayout.LayoutParams)localView.getLayoutParams();
        i = localView.getMeasuredHeight();
        j = topMargin;
        k = bottomMargin;
        i = i + j + k;
      }
      mPopup.setContentView((View)localObject1);
    }
    else
    {
      localObject1 = (ViewGroup)mPopup.getContentView();
      localObject1 = mPromptView;
      i = j;
      if (localObject1 != null)
      {
        localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
        i = ((View)localObject1).getMeasuredHeight() + topMargin + bottomMargin;
      }
    }
    localObject1 = mPopup.getBackground();
    if (localObject1 != null)
    {
      ((Drawable)localObject1).getPadding(mTempRect);
      localObject1 = mTempRect;
      m = top;
      j = bottom + m;
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
      k = 0;
    }
    if (mPopup.getInputMethodMode() == 2) {
      bool = true;
    }
    int m = getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset, bool);
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
        j = i + (k + (mDropDownList.getPaddingTop() + mDropDownList.getPaddingBottom()));
      }
      return m + j;
    }
    return m + k;
  }
  
  private int getMaxAvailableHeight(View paramView, int paramInt, boolean paramBoolean)
  {
    Object localObject = sGetMaxAvailableHeightMethod;
    if (localObject != null)
    {
      PopupWindow localPopupWindow = mPopup;
      try
      {
        localObject = ((Method)localObject).invoke(localPopupWindow, new Object[] { paramView, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean) });
        localObject = (Integer)localObject;
        int i = ((Integer)localObject).intValue();
        return i;
      }
      catch (Exception localException)
      {
        Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
      }
    }
    return mPopup.getMaxAvailableHeight(paramView, paramInt);
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
      PopupWindow localPopupWindow = mPopup;
      try
      {
        localMethod.invoke(localPopupWindow, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (Exception localException)
      {
        Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
      }
    }
  }
  
  public void a(int paramInt)
  {
    mDropDownGravity = paramInt;
  }
  
  public void a(View paramView)
  {
    mDropDownAnchorView = paramView;
  }
  
  public void b(boolean paramBoolean)
  {
    h = true;
    y = paramBoolean;
  }
  
  public ListView c()
  {
    return mDropDownList;
  }
  
  public void clearListSelection()
  {
    ListPopupWindow.DropDownListView localDropDownListView = mDropDownList;
    if (localDropDownListView != null)
    {
      localDropDownListView.setListSelectionHidden(true);
      localDropDownListView.requestLayout();
    }
  }
  
  public void dismiss()
  {
    mPopup.dismiss();
    removePromptView();
    mPopup.setContentView(null);
    mDropDownList = null;
    mHandler.removeCallbacks(mRunnable);
  }
  
  public void dismiss(int paramInt)
  {
    mPopup.setAnimationStyle(paramInt);
  }
  
  ListPopupWindow.DropDownListView e(Context paramContext, boolean paramBoolean)
  {
    return new ListPopupWindow.DropDownListView(paramContext, paramBoolean);
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
  
  public boolean isModal()
  {
    return mModal;
  }
  
  public boolean isShowing()
  {
    return mPopup.isShowing();
  }
  
  public void setAdapter(Rect paramRect)
  {
    mTempAdapter = paramRect;
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    DataSetObserver localDataSetObserver = mObserver;
    if (localDataSetObserver == null)
    {
      mObserver = new p0.d(this);
    }
    else
    {
      ListAdapter localListAdapter = mAdapter;
      if (localListAdapter != null) {
        localListAdapter.unregisterDataSetObserver(localDataSetObserver);
      }
    }
    mAdapter = paramListAdapter;
    if (paramListAdapter != null) {
      paramListAdapter.registerDataSetObserver(mObserver);
    }
    paramListAdapter = mDropDownList;
    if (paramListAdapter != null) {
      paramListAdapter.setAdapter(mAdapter);
    }
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
  
  public void setHorizontalOffset(int paramInt)
  {
    mDropDownHorizontalOffset = paramInt;
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    mItemClickListener = paramOnItemClickListener;
  }
  
  public void setPromptPosition(int paramInt)
  {
    mPromptPosition = paramInt;
  }
  
  public void setSelection(int paramInt)
  {
    ListPopupWindow.DropDownListView localDropDownListView = mDropDownList;
    if ((isShowing()) && (localDropDownListView != null))
    {
      localDropDownListView.setListSelectionHidden(false);
      localDropDownListView.setSelection(paramInt);
      if (localDropDownListView.getChoiceMode() != 0) {
        localDropDownListView.setItemChecked(paramInt, true);
      }
    }
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
    boolean bool2 = isInputMethodNotNeeded();
    PopupWindowCompat.setWindowLayoutType(mPopup, mDropDownWindowLayoutType);
    boolean bool3 = mPopup.isShowing();
    boolean bool1 = true;
    Object localObject2;
    if (bool3)
    {
      if (!ViewCompat.setText(getAnchorView())) {
        return;
      }
      i = mDropDownWidth;
      if (i == -1) {
        i = -1;
      } else if (i == -2) {
        i = getAnchorView().getWidth();
      } else {
        i = mDropDownWidth;
      }
      k = mDropDownHeight;
      if (k == -1)
      {
        if (!bool2) {
          j = -1;
        }
        if (bool2)
        {
          localObject1 = mPopup;
          if (mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          }
          ((PopupWindow)localObject1).setWidth(k);
          mPopup.setHeight(0);
        }
        else
        {
          localObject1 = mPopup;
          if (mDropDownWidth == -1) {
            k = -1;
          } else {
            k = 0;
          }
          ((PopupWindow)localObject1).setWidth(k);
          mPopup.setHeight(-1);
        }
      }
      else if (k != -2)
      {
        j = mDropDownHeight;
      }
      localObject1 = mPopup;
      if ((mForceIgnoreOutsideTouch) || (mDropDownAlwaysVisible)) {
        bool1 = false;
      }
      ((PopupWindow)localObject1).setOutsideTouchable(bool1);
      localObject1 = mPopup;
      localObject2 = getAnchorView();
      int m = mDropDownHorizontalOffset;
      int n = mDropDownVerticalOffset;
      k = i;
      if (i < 0) {
        k = -1;
      }
      i = j;
      if (j < 0) {
        i = -1;
      }
      ((PopupWindow)localObject1).update((View)localObject2, m, n, k, i);
      return;
    }
    int i = mDropDownWidth;
    if (i == -1) {
      i = -1;
    } else if (i == -2) {
      i = getAnchorView().getWidth();
    } else {
      i = mDropDownWidth;
    }
    int k = mDropDownHeight;
    if (k == -1) {
      j = -1;
    } else if (k != -2) {
      j = mDropDownHeight;
    }
    mPopup.setWidth(i);
    mPopup.setHeight(j);
    setPopupClipToScreenEnabled(true);
    Object localObject1 = mPopup;
    if ((!mForceIgnoreOutsideTouch) && (!mDropDownAlwaysVisible)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ((PopupWindow)localObject1).setOutsideTouchable(bool1);
    mPopup.setTouchInterceptor(mTouchInterceptor);
    if (h) {
      PopupWindowCompat.init(mPopup, y);
    }
    localObject1 = b;
    if (localObject1 != null)
    {
      localObject2 = mPopup;
      Rect localRect = mTempAdapter;
      try
      {
        ((Method)localObject1).invoke(localObject2, new Object[] { localRect });
      }
      catch (Exception localException)
      {
        Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", localException);
      }
    }
    PopupWindowCompat.update(mPopup, getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
    mDropDownList.setSelection(-1);
    if ((!mModal) || (mDropDownList.isInTouchMode())) {
      clearListSelection();
    }
    if (!mModal) {
      mHandler.post(mHideSelector);
    }
  }
  
  public void show(int paramInt)
  {
    mPopup.setInputMethodMode(paramInt);
  }
  
  public void show(boolean paramBoolean)
  {
    mModal = paramBoolean;
    mPopup.setFocusable(paramBoolean);
  }
}
