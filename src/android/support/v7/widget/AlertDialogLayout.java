package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import org.org.v4.content.R.id;

public class AlertDialogLayout
  extends LinearLayoutCompat
{
  public AlertDialogLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public AlertDialogLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private static int a(View paramView)
  {
    int i = ViewCompat.getMinimumHeight(paramView);
    if (i > 0) {
      return i;
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      if (paramView.getChildCount() == 1) {
        return a(paramView.getChildAt(0));
      }
    }
    return 0;
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() != 8)
      {
        o0.a localA = (o0.a)localView.getLayoutParams();
        if (width == -1)
        {
          int k = height;
          height = localView.getMeasuredHeight();
          measureChildWithMargins(localView, j, 0, paramInt2, 0);
          height = k;
        }
      }
      i += 1;
    }
  }
  
  private boolean onMeasureExactFormat(int paramInt1, int paramInt2)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    int i5 = getChildCount();
    int i = 0;
    View localView;
    while (i < i5)
    {
      localView = getChildAt(i);
      if (localView.getVisibility() != 8)
      {
        j = localView.getId();
        if (j == R.id.topPanel)
        {
          localObject3 = localView;
        }
        else if (j == R.id.buttonPanel)
        {
          localObject2 = localView;
        }
        else
        {
          if ((j != R.id.contentPanel) && (j != R.id.customPanel)) {
            return false;
          }
          if (localObject1 != null) {
            return false;
          }
          localObject1 = localView;
        }
      }
      i += 1;
    }
    int i7 = View.MeasureSpec.getMode(paramInt2);
    int i3 = View.MeasureSpec.getSize(paramInt2);
    int i6 = View.MeasureSpec.getMode(paramInt1);
    int j = 0;
    i = getPaddingTop() + getPaddingBottom();
    int k = i;
    if (localObject3 != null)
    {
      localObject3.measure(paramInt1, 0);
      k = i + localObject3.getMeasuredHeight();
      j = View.combineMeasuredStates(0, localObject3.getMeasuredState());
    }
    i = 0;
    int i1 = 0;
    int m = j;
    int n = k;
    if (localObject2 != null)
    {
      localObject2.measure(paramInt1, 0);
      m = a(localObject2);
      i = m;
      i1 = localObject2.getMeasuredHeight() - m;
      n = k + m;
      m = View.combineMeasuredStates(j, localObject2.getMeasuredState());
    }
    int i2 = 0;
    j = m;
    k = n;
    if (localObject1 != null)
    {
      if (i7 == 0) {
        j = 0;
      } else {
        j = View.MeasureSpec.makeMeasureSpec(Math.max(0, i3 - n), i7);
      }
      localObject1.measure(paramInt1, j);
      j = localObject1.getMeasuredHeight();
      i2 = j;
      k = n + j;
      j = View.combineMeasuredStates(m, localObject1.getMeasuredState());
    }
    int i4 = i3 - k;
    m = j;
    n = k;
    i3 = i4;
    if (localObject2 != null)
    {
      i1 = Math.min(i4, i1);
      m = i4;
      n = i;
      if (i1 > 0)
      {
        m = i4 - i1;
        n = i + i1;
      }
      localObject2.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(n, 1073741824));
      n = k - i + localObject2.getMeasuredHeight();
      i = View.combineMeasuredStates(j, localObject2.getMeasuredState());
      i3 = m;
      m = i;
    }
    j = m;
    i = n;
    if (localObject1 != null)
    {
      j = m;
      i = n;
      if (i3 > 0)
      {
        localObject1.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(i2 + i3, i7));
        i = n - i2 + localObject1.getMeasuredHeight();
        j = View.combineMeasuredStates(m, localObject1.getMeasuredState());
      }
    }
    m = 0;
    k = 0;
    while (k < i5)
    {
      localView = getChildAt(k);
      n = m;
      if (localView.getVisibility() != 8) {
        n = Math.max(m, localView.getMeasuredWidth());
      }
      k += 1;
      m = n;
    }
    setMeasuredDimension(View.resolveSizeAndState(m + (getPaddingLeft() + getPaddingRight()), paramInt1, j), View.resolveSizeAndState(i, paramInt2, 0));
    if (i6 != 1073741824) {
      forceUniformWidth(i5, paramInt2);
    }
    return true;
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.layout(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = getPaddingLeft();
    int k = paramInt3 - paramInt1;
    int m = getPaddingRight();
    int n = getPaddingRight();
    paramInt1 = getMeasuredHeight();
    int i1 = getChildCount();
    int i2 = getGravity();
    paramInt3 = i2 & 0x70;
    if (paramInt3 != 16)
    {
      if (paramInt3 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - paramInt1;
      }
    }
    else {
      paramInt1 = getPaddingTop() + (paramInt4 - paramInt2 - paramInt1) / 2;
    }
    Object localObject = getDividerDrawable();
    if (localObject == null) {
      paramInt3 = 0;
    } else {
      paramInt3 = ((Drawable)localObject).getIntrinsicHeight();
    }
    paramInt4 = 0;
    for (;;)
    {
      localObject = this;
      if (paramInt4 >= i1) {
        break;
      }
      View localView = ((ViewGroup)localObject).getChildAt(paramInt4);
      paramInt2 = paramInt1;
      if (localView != null)
      {
        paramInt2 = paramInt1;
        if (localView.getVisibility() != 8)
        {
          int i3 = localView.getMeasuredWidth();
          int i4 = localView.getMeasuredHeight();
          o0.a localA = (o0.a)localView.getLayoutParams();
          int i = gravity;
          paramInt2 = i;
          if (i < 0) {
            paramInt2 = i2 & 0x800007;
          }
          paramInt2 = GravityCompat.getAbsoluteGravity(paramInt2, ViewCompat.getLayoutDirection(this)) & 0x7;
          if (paramInt2 != 1)
          {
            if (paramInt2 != 5) {
              paramInt2 = leftMargin + j;
            } else {
              paramInt2 = k - m - i3 - rightMargin;
            }
          }
          else {
            paramInt2 = (k - j - n - i3) / 2 + j + leftMargin - rightMargin;
          }
          i = paramInt1;
          if (((LinearLayoutCompat)localObject).hasDividerBeforeChildAt(paramInt4)) {
            i = paramInt1 + paramInt3;
          }
          paramInt1 = i + topMargin;
          setChildFrame(localView, paramInt2, paramInt1, i3, i4);
          paramInt2 = paramInt1 + (i4 + bottomMargin);
        }
      }
      paramInt4 += 1;
      paramInt1 = paramInt2;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!onMeasureExactFormat(paramInt1, paramInt2)) {
      super.onMeasure(paramInt1, paramInt2);
    }
  }
}
