package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Checkable;
import android.widget.ImageView;
import org.org.v4.content.R.attr;

public class CheckableImageButton
  extends AppCompatImageButton
  implements Checkable
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private boolean isChecked;
  
  public CheckableImageButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.imageButtonStyle);
  }
  
  public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ViewCompat.setAccessibilityDelegate(this, new a());
  }
  
  public boolean isChecked()
  {
    return isChecked;
  }
  
  public int[] onCreateDrawableState(int paramInt)
  {
    if (isChecked) {
      return View.mergeDrawableStates(super.onCreateDrawableState(CHECKED_STATE_SET.length + paramInt), CHECKED_STATE_SET);
    }
    return super.onCreateDrawableState(paramInt);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (isChecked != paramBoolean)
    {
      isChecked = paramBoolean;
      refreshDrawableState();
      sendAccessibilityEvent(2048);
    }
  }
  
  public void toggle()
  {
    setChecked(isChecked ^ true);
  }
  
  class a
    extends AccessibilityDelegateCompat
  {
    a() {}
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setChecked(isChecked());
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setContentInvalid(true);
      paramAccessibilityNodeInfoCompat.setError(isChecked());
    }
  }
}
