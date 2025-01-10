package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.layout;
import org.org.v4.content.R.styleable;
import org.org.v4.view.ContextThemeWrapper;

public class AppCompatSpinner
  extends Spinner
  implements TintableBackgroundView
{
  private static final int[] ATTRS_ANDROID_SPINNERMODE = { 16843505 };
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  int mDropDownWidth;
  private ListPopupWindow.ForwardingListener mForwardingListener;
  DropdownPopup mPopup;
  private final Context mPopupContext;
  private final boolean mPopupSet;
  private SpinnerAdapter mTempAdapter;
  final Rect mTempRect = new Rect();
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, Resources.Theme paramTheme)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    TintTypedArray localTintTypedArray2 = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.Spinner, paramInt1, 0);
    mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
    int i;
    if (paramTheme != null)
    {
      mPopupContext = new ContextThemeWrapper(paramContext, paramTheme);
    }
    else
    {
      i = localTintTypedArray2.getResourceId(R.styleable.Spinner_popupTheme, 0);
      if (i != 0)
      {
        mPopupContext = new ContextThemeWrapper(paramContext, i);
      }
      else
      {
        if (Build.VERSION.SDK_INT < 23) {
          paramTheme = paramContext;
        } else {
          paramTheme = null;
        }
        mPopupContext = paramTheme;
      }
    }
    if (mPopupContext != null)
    {
      int j = paramInt2;
      if (paramInt2 == -1)
      {
        paramTheme = null;
        Object localObject2 = null;
        Object localObject1 = ATTRS_ANDROID_SPINNERMODE;
        for (;;)
        {
          try
          {
            TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, (int[])localObject1, paramInt1, 0);
            localObject1 = localTypedArray;
            localObject2 = localObject1;
            paramTheme = (Resources.Theme)localObject1;
            boolean bool = localTypedArray.hasValue(0);
            i = paramInt2;
            if (bool)
            {
              localObject2 = localObject1;
              paramTheme = (Resources.Theme)localObject1;
              i = localTypedArray.getInt(0, 0);
            }
            j = i;
            if (localTypedArray == null) {
              break label278;
            }
            paramInt2 = i;
            paramTheme = (Resources.Theme)localObject1;
            paramTheme.recycle();
            j = paramInt2;
          }
          catch (Throwable paramContext)
          {
            break;
          }
          catch (Exception localException)
          {
            localObject2 = paramTheme;
            Log.i("AppCompatSpinner", "Could not read android:spinnerMode", localException);
            j = paramInt2;
            if (paramTheme == null) {
              break label278;
            }
          }
        }
        if (localObject2 != null) {
          ((TypedArray)localObject2).recycle();
        }
        throw paramContext;
      }
      label278:
      if (j == 1)
      {
        paramTheme = new DropdownPopup(mPopupContext, paramAttributeSet, paramInt1);
        TintTypedArray localTintTypedArray1 = TintTypedArray.obtainStyledAttributes(mPopupContext, paramAttributeSet, R.styleable.Spinner, paramInt1, 0);
        mDropDownWidth = localTintTypedArray1.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
        paramTheme.setBackgroundDrawable(localTintTypedArray1.getDrawable(R.styleable.Spinner_android_popupBackground));
        paramTheme.setPromptText(localTintTypedArray2.getString(R.styleable.Spinner_android_prompt));
        localTintTypedArray1.recycle();
        mPopup = paramTheme;
        mForwardingListener = new ActivityChooserView.3(this, this, paramTheme);
      }
    }
    paramTheme = localTintTypedArray2.getTextArray(R.styleable.Spinner_android_entries);
    if (paramTheme != null)
    {
      paramContext = new ArrayAdapter(paramContext, 17367048, paramTheme);
      paramContext.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
      setAdapter(paramContext);
    }
    localTintTypedArray2.recycle();
    mPopupSet = true;
    paramContext = mTempAdapter;
    if (paramContext != null)
    {
      setAdapter(paramContext);
      mTempAdapter = null;
    }
    mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt1);
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable)
  {
    if (paramSpinnerAdapter == null) {
      return 0;
    }
    int i = 0;
    Object localObject = null;
    int k = 0;
    int i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int i2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int j = Math.max(0, getSelectedItemPosition());
    int i3 = Math.min(paramSpinnerAdapter.getCount(), j + 15);
    j = Math.max(0, j - (15 - (i3 - j)));
    while (j < i3)
    {
      int n = paramSpinnerAdapter.getItemViewType(j);
      int m = k;
      if (n != k)
      {
        m = n;
        localObject = null;
      }
      View localView = paramSpinnerAdapter.getView(j, (View)localObject, this);
      localObject = localView;
      if (localView.getLayoutParams() == null) {
        localView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
      }
      localView.measure(i1, i2);
      i = Math.max(i, localView.getMeasuredWidth());
      j += 1;
      k = m;
    }
    j = i;
    if (paramDrawable != null)
    {
      paramDrawable.getPadding(mTempRect);
      paramSpinnerAdapter = mTempRect;
      j = i + (left + right);
    }
    return j;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.applySupportBackgroundTint();
    }
  }
  
  public int getDropDownHorizontalOffset()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getHorizontalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownHorizontalOffset();
    }
    return 0;
  }
  
  public int getDropDownVerticalOffset()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getVerticalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownVerticalOffset();
    }
    return 0;
  }
  
  public int getDropDownWidth()
  {
    if (mPopup != null) {
      return mDropDownWidth;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownWidth();
    }
    return 0;
  }
  
  public Drawable getPopupBackground()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getBackground();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getPopupBackground();
    }
    return null;
  }
  
  public Context getPopupContext()
  {
    if (mPopup != null) {
      return mPopupContext;
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return super.getPopupContext();
    }
    return null;
  }
  
  public CharSequence getPrompt()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null) {
      return localDropdownPopup.getHintText();
    }
    return super.getPrompt();
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      return localAppCompatBackgroundHelper.getSupportBackgroundTintMode();
    }
    return null;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    DropdownPopup localDropdownPopup = mPopup;
    if ((localDropdownPopup != null) && (localDropdownPopup.isShowing())) {
      mPopup.dismiss();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if ((mPopup != null) && (View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)) {
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight());
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    ListPopupWindow.ForwardingListener localForwardingListener = mForwardingListener;
    if ((localForwardingListener != null) && (localForwardingListener.onTouch(this, paramMotionEvent))) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick()
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      if (!localDropdownPopup.isShowing()) {
        mPopup.show();
      }
      return true;
    }
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    if (!mPopupSet)
    {
      mTempAdapter = paramSpinnerAdapter;
      return;
    }
    super.setAdapter(paramSpinnerAdapter);
    if (mPopup != null)
    {
      Context localContext2 = mPopupContext;
      Context localContext1 = localContext2;
      if (localContext2 == null) {
        localContext1 = getContext();
      }
      mPopup.setAdapter(new SpinnerCompat.DropDownAdapter(paramSpinnerAdapter, localContext1.getTheme()));
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.onSetBackgroundResource(paramInt);
    }
  }
  
  public void setDropDownHorizontalOffset(int paramInt)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setHorizontalOffset(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownHorizontalOffset(paramInt);
    }
  }
  
  public void setDropDownVerticalOffset(int paramInt)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setVerticalOffset(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownVerticalOffset(paramInt);
    }
  }
  
  public void setDropDownWidth(int paramInt)
  {
    if (mPopup != null)
    {
      mDropDownWidth = paramInt;
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownWidth(paramInt);
    }
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setBackgroundDrawable(paramDrawable);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setPopupBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setPopupBackgroundResource(int paramInt)
  {
    setPopupBackgroundDrawable(org.org.v4.gui.helpers.Resources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence)
  {
    DropdownPopup localDropdownPopup = mPopup;
    if (localDropdownPopup != null)
    {
      localDropdownPopup.setPromptText(paramCharSequence);
      return;
    }
    super.setPrompt(paramCharSequence);
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    AppCompatBackgroundHelper localAppCompatBackgroundHelper = mBackgroundTintHelper;
    if (localAppCompatBackgroundHelper != null) {
      localAppCompatBackgroundHelper.setSupportBackgroundTintMode(paramMode);
    }
  }
  
  class DropdownPopup
    extends ListPopupWindow
  {
    ListAdapter mAdapter;
    private CharSequence mHintText;
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      a(AppCompatSpinner.this);
      show(true);
      setPromptPosition(0);
      setOnItemClickListener(new v.c.a(this, AppCompatSpinner.this));
    }
    
    void computeContentWidth()
    {
      Object localObject = getBackground();
      int i = 0;
      if (localObject != null)
      {
        ((Drawable)localObject).getPadding(mTempRect);
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
          i = mTempRect.right;
        } else {
          i = -mTempRect.left;
        }
      }
      else
      {
        localObject = mTempRect;
        right = 0;
        left = 0;
      }
      int n = getPaddingLeft();
      int i1 = getPaddingRight();
      int i2 = getWidth();
      localObject = AppCompatSpinner.this;
      int j = mDropDownWidth;
      if (j == -2)
      {
        int k = ((AppCompatSpinner)localObject).compatMeasureContentWidth((SpinnerAdapter)mAdapter, getBackground());
        j = k;
        int m = getContext().getResources().getDisplayMetrics().widthPixels;
        localObject = mTempRect;
        m = m - left - right;
        if (k > m) {
          j = m;
        }
        setContentWidth(Math.max(j, i2 - n - i1));
      }
      else if (j == -1)
      {
        setContentWidth(i2 - n - i1);
      }
      else
      {
        setContentWidth(j);
      }
      if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
        i += i2 - i1 - getWidth();
      } else {
        i += n;
      }
      setHorizontalOffset(i);
    }
    
    public CharSequence getHintText()
    {
      return mHintText;
    }
    
    boolean isVisibleToUser(View paramView)
    {
      return (ViewCompat.setText(paramView)) && (paramView.getGlobalVisibleRect(mVisibleRect));
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      mAdapter = paramListAdapter;
    }
    
    public void setPromptText(CharSequence paramCharSequence)
    {
      mHintText = paramCharSequence;
    }
    
    public void show()
    {
      boolean bool = isShowing();
      computeContentWidth();
      show(2);
      super.show();
      c().setChoiceMode(1);
      setSelection(getSelectedItemPosition());
      if (bool) {
        return;
      }
      ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
      if (localViewTreeObserver != null)
      {
        v.c.b localB = new v.c.b(this);
        localViewTreeObserver.addOnGlobalLayoutListener(localB);
        setOnDismissListener(new v.c.c(this, localB));
      }
    }
  }
}
