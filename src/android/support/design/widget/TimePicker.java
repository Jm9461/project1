package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.ByteVector;
import android.support.v4.view.accessibility.ClassWriter;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import org.org.android.R.styleable;

public class TimePicker
  extends FrameLayout
{
  private final AccessibilityManager a;
  private final ClassWriter b;
  private Snackbar.SnackbarLayout.OnAttachStateChangeListener mOnAttachStateChangeListener;
  private Snackbar.SnackbarLayout.OnLayoutChangeListener mOnLayoutChangeListener;
  
  protected TimePicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  protected TimePicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SnackbarLayout);
    if (paramAttributeSet.hasValue(R.styleable.SnackbarLayout_elevation)) {
      ViewCompat.setElevation(this, paramAttributeSet.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
    }
    paramAttributeSet.recycle();
    a = ((AccessibilityManager)paramContext.getSystemService("accessibility"));
    b = new Alarm(this);
    ByteVector.b(a, b);
    setClickableOrFocusableBasedOnAccessibility(a.isTouchExplorationEnabled());
  }
  
  private void setClickableOrFocusableBasedOnAccessibility(boolean paramBoolean)
  {
    setClickable(paramBoolean ^ true);
    setFocusable(paramBoolean);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Snackbar.SnackbarLayout.OnAttachStateChangeListener localOnAttachStateChangeListener = mOnAttachStateChangeListener;
    if (localOnAttachStateChangeListener != null) {
      localOnAttachStateChangeListener.onViewAttachedToWindow(this);
    }
    ViewCompat.requestApplyInsets(this);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Snackbar.SnackbarLayout.OnAttachStateChangeListener localOnAttachStateChangeListener = mOnAttachStateChangeListener;
    if (localOnAttachStateChangeListener != null) {
      localOnAttachStateChangeListener.onViewDetachedFromWindow(this);
    }
    ByteVector.a(a, b);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Snackbar.SnackbarLayout.OnLayoutChangeListener localOnLayoutChangeListener = mOnLayoutChangeListener;
    if (localOnLayoutChangeListener != null) {
      localOnLayoutChangeListener.onLayoutChange(this, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  void setOnAttachStateChangeListener(Snackbar.SnackbarLayout.OnAttachStateChangeListener paramOnAttachStateChangeListener)
  {
    mOnAttachStateChangeListener = paramOnAttachStateChangeListener;
  }
  
  void setOnLayoutChangeListener(Snackbar.SnackbarLayout.OnLayoutChangeListener paramOnLayoutChangeListener)
  {
    mOnLayoutChangeListener = paramOnLayoutChangeListener;
  }
}
