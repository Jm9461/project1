package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.ViewAnimator;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.List;

public class AccessibleDateAnimator
  extends ViewAnimator
{
  private long mDateMillis;
  
  public AccessibleDateAnimator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 32)
    {
      paramAccessibilityEvent.getText().clear();
      Object localObject = new Calendar();
      ((Calendar)localObject).setTimeInMillis(mDateMillis);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(((Calendar)localObject).getDisplayName());
      localStringBuilder.append(" ");
      localStringBuilder.append(((Calendar)localObject).get());
      localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(localStringBuilder.toString());
      paramAccessibilityEvent.getText().add(localObject);
      return true;
    }
    return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public void setDateMillis(long paramLong)
  {
    mDateMillis = paramLong;
  }
}
