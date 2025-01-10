package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.dimen;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;

public class TextViewWithCircularIndicator
  extends TextView
{
  private final int mCircleColor;
  Paint mCirclePaint = new Paint();
  private boolean mDrawCircle;
  private final String mItemIsSelectedText;
  
  public TextViewWithCircularIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.getResources();
    mCircleColor = paramAttributeSet.getColor(R.color.mdtp_accent_color);
    paramAttributeSet.getDimensionPixelOffset(R.dimen.mdtp_month_select_circle_radius);
    mItemIsSelectedText = paramContext.getResources().getString(R.string.mdtp_item_is_selected);
    init();
  }
  
  private void init()
  {
    mCirclePaint.setFakeBoldText(true);
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setColor(mCircleColor);
    mCirclePaint.setTextAlign(Paint.Align.CENTER);
    mCirclePaint.setStyle(Paint.Style.FILL);
    mCirclePaint.setAlpha(255);
  }
  
  public void drawIndicator(boolean paramBoolean)
  {
    mDrawCircle = paramBoolean;
  }
  
  public CharSequence getContentDescription()
  {
    String str2 = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(getText().toString());
    String str1 = str2;
    if (mDrawCircle) {
      str1 = String.format(mItemIsSelectedText, new Object[] { str2 });
    }
    return str1;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (mDrawCircle)
    {
      int i = getWidth();
      int j = getHeight();
      int k = Math.min(i, j) / 2;
      paramCanvas.drawCircle(i / 2, j / 2, k, mCirclePaint);
    }
    setSelected(mDrawCircle);
    super.onDraw(paramCanvas);
  }
}
