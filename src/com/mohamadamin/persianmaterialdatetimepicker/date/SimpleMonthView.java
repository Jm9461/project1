package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.mohamadamin.persianmaterialdatetimepicker.views.View;

public class SimpleMonthView
  extends MonthView
{
  public SimpleMonthView(Context paramContext, AttributeSet paramAttributeSet, DatePickerController paramDatePickerController)
  {
    super(paramContext, paramAttributeSet, paramDatePickerController);
  }
  
  public void drawMonthDay(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    if (mSelectedDay == paramInt3) {
      paramCanvas.drawCircle(paramInt4, paramInt5 - MonthView.MINI_DAY_NUMBER_TEXT_SIZE / 3, MonthView.DAY_SELECTED_CIRCLE_SIZE, mSelectedCirclePaint);
    }
    if (isHighlighted(paramInt1, paramInt2, paramInt3)) {
      mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
    } else {
      mMonthNumPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
    }
    if (isOutOfRange(paramInt1, paramInt2, paramInt3))
    {
      mMonthNumPaint.setColor(mDisabledDayTextColor);
    }
    else if (mSelectedDay == paramInt3)
    {
      mMonthNumPaint.setColor(mSelectedDayTextColor);
    }
    else if ((mHasToday) && (mToday == paramInt3))
    {
      mMonthNumPaint.setColor(mTodayNumberColor);
    }
    else
    {
      Paint localPaint = mMonthNumPaint;
      if (isHighlighted(paramInt1, paramInt2, paramInt3)) {
        paramInt1 = mHighlightedDayTextColor;
      } else {
        paramInt1 = mDayTextColor;
      }
      localPaint.setColor(paramInt1);
    }
    paramCanvas.drawText(View.format(String.format("%d", new Object[] { Integer.valueOf(paramInt3) })), paramInt4, paramInt5, mMonthNumPaint);
  }
}
