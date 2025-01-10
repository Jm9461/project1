package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mohamadamin.persianmaterialdatetimepicker.R.dimen;
import com.mohamadamin.persianmaterialdatetimepicker.R.layout;
import java.util.ArrayList;
import java.util.List;

public class YearPickerView
  extends ListView
  implements AdapterView.OnItemClickListener, DatePickerDialog.OnDateChangedListener
{
  private YearAdapter mAdapter;
  private int mChildSize;
  private final DatePickerController mController;
  private TextViewWithCircularIndicator mSelectedView;
  private int mViewSize;
  
  public YearPickerView(Context paramContext, DatePickerController paramDatePickerController)
  {
    super(paramContext);
    mController = paramDatePickerController;
    mController.registerOnDateChangedListener(this);
    setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    paramDatePickerController = paramContext.getResources();
    mViewSize = paramDatePickerController.getDimensionPixelOffset(R.dimen.mdtp_date_picker_view_animator_height);
    mChildSize = paramDatePickerController.getDimensionPixelOffset(R.dimen.mdtp_year_label_height);
    setVerticalFadingEdgeEnabled(true);
    setFadingEdgeLength(mChildSize / 3);
    init(paramContext);
    setOnItemClickListener(this);
    setSelector(new StateListDrawable());
    setDividerHeight(0);
    onDateChanged();
  }
  
  private static int getYearFromTextView(TextView paramTextView)
  {
    return Integer.valueOf(com.mohamadamin.persianmaterialdatetimepicker.views.View.update(paramTextView.getText().toString())).intValue();
  }
  
  private void init(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    int i = mController.getMinYear();
    while (i <= mController.getMaxYear())
    {
      localArrayList.add(String.format("%d", new Object[] { Integer.valueOf(i) }));
      i += 1;
    }
    localArrayList = com.mohamadamin.persianmaterialdatetimepicker.views.View.update(localArrayList);
    mAdapter = new YearAdapter(paramContext, R.layout.mdtp_year_label_text_view, localArrayList);
    setAdapter(mAdapter);
  }
  
  public int getFirstPositionOffset()
  {
    android.view.View localView = getChildAt(0);
    if (localView == null) {
      return 0;
    }
    return localView.getTop();
  }
  
  public void onDateChanged()
  {
    mAdapter.notifyDataSetChanged();
    postSetSelectionCentered(mController.getSelectedDay().year - mController.getMinYear());
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    if (paramAccessibilityEvent.getEventType() == 4096)
    {
      paramAccessibilityEvent.setFromIndex(0);
      paramAccessibilityEvent.setToIndex(0);
    }
  }
  
  public void onItemClick(AdapterView paramAdapterView, android.view.View paramView, int paramInt, long paramLong)
  {
    mController.tryVibrate();
    paramAdapterView = (TextViewWithCircularIndicator)paramView;
    if (paramAdapterView != null)
    {
      paramView = mSelectedView;
      if (paramAdapterView != paramView)
      {
        if (paramView != null)
        {
          paramView.drawIndicator(false);
          mSelectedView.requestLayout();
        }
        paramAdapterView.drawIndicator(true);
        paramAdapterView.requestLayout();
        mSelectedView = paramAdapterView;
      }
      mController.onYearSelected(getYearFromTextView(paramAdapterView));
      mAdapter.notifyDataSetChanged();
    }
  }
  
  public void postSetSelectionCentered(int paramInt)
  {
    postSetSelectionFromTop(paramInt, mViewSize / 2 - mChildSize / 2);
  }
  
  public void postSetSelectionFromTop(int paramInt1, int paramInt2)
  {
    post(new YearPickerView.1(this, paramInt1, paramInt2));
  }
  
  class YearAdapter
    extends ArrayAdapter<String>
  {
    public YearAdapter(Context paramContext, int paramInt, List paramList)
    {
      super(paramInt, paramList);
    }
    
    public android.view.View getView(int paramInt, android.view.View paramView, ViewGroup paramViewGroup)
    {
      paramView = (TextViewWithCircularIndicator)super.getView(paramInt, paramView, paramViewGroup);
      paramView.requestLayout();
      paramInt = YearPickerView.access$getGetYearFromTextView(paramView);
      boolean bool;
      if (access$getMControllergetSelectedDayyear == paramInt) {
        bool = true;
      } else {
        bool = false;
      }
      paramView.drawIndicator(bool);
      if (bool) {
        YearPickerView.access$setMSelectedView(YearPickerView.this, paramView);
      }
      return paramView;
    }
  }
}
