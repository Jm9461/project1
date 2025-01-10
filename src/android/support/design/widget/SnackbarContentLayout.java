package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.org.android.R.dimen;
import org.org.android.R.id;
import org.org.android.R.styleable;
import org.org.android.util.LocationUtil.UpdateLocationListenner;

public class SnackbarContentLayout
  extends LinearLayout
  implements LocationUtil.UpdateLocationListenner
{
  private Button mActionView;
  private int mMaxInlineActionWidth;
  private int mMaxWidth;
  private TextView mMessageView;
  
  public SnackbarContentLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SnackbarContentLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SnackbarLayout);
    mMaxWidth = paramContext.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
    mMaxInlineActionWidth = paramContext.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
    paramContext.recycle();
  }
  
  private static void updateTopBottomPadding(View paramView, int paramInt1, int paramInt2)
  {
    if (ViewCompat.isPaddingRelative(paramView))
    {
      ViewCompat.setPaddingRelative(paramView, ViewCompat.getPaddingStart(paramView), paramInt1, ViewCompat.getPaddingEnd(paramView), paramInt2);
      return;
    }
    paramView.setPadding(paramView.getPaddingLeft(), paramInt1, paramView.getPaddingRight(), paramInt2);
  }
  
  private boolean updateViewsWithinLayout(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    if (paramInt1 != getOrientation())
    {
      setOrientation(paramInt1);
      bool = true;
    }
    if ((mMessageView.getPaddingTop() != paramInt2) || (mMessageView.getPaddingBottom() != paramInt3))
    {
      updateTopBottomPadding(mMessageView, paramInt2, paramInt3);
      return true;
    }
    return bool;
  }
  
  public Button getActionView()
  {
    return mActionView;
  }
  
  public TextView getMessageView()
  {
    return mMessageView;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mMessageView = ((TextView)findViewById(R.id.snackbar_text));
    mActionView = ((Button)findViewById(R.id.snackbar_action));
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = paramInt1;
    if (mMaxWidth > 0)
    {
      j = getMeasuredWidth();
      k = mMaxWidth;
      i = paramInt1;
      if (j > k)
      {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
        i = paramInt1;
        super.onMeasure(paramInt1, paramInt2);
      }
    }
    int j = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
    int k = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
    if (mMessageView.getLayout().getLineCount() > 1) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    int m = 0;
    if ((paramInt1 != 0) && (mMaxInlineActionWidth > 0) && (mActionView.getMeasuredWidth() > mMaxInlineActionWidth))
    {
      paramInt1 = m;
      if (updateViewsWithinLayout(1, j, j - k)) {
        paramInt1 = 1;
      }
    }
    else
    {
      if (paramInt1 == 0) {
        j = k;
      }
      paramInt1 = m;
      if (updateViewsWithinLayout(0, j, j)) {
        paramInt1 = 1;
      }
    }
    if (paramInt1 != 0) {
      super.onMeasure(i, paramInt2);
    }
  }
}
