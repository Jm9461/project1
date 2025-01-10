package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import com.mohamadamin.persianmaterialdatetimepicker.HapticFeedbackController;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.id;
import com.mohamadamin.persianmaterialdatetimepicker.R.layout;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;
import com.mohamadamin.persianmaterialdatetimepicker.TypefaceHelper;
import com.mohamadamin.persianmaterialdatetimepicker.Utils;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

public class DatePickerDialog
  extends DialogFragment
  implements View.OnClickListener, DatePickerController
{
  private Calendar[] highlightedDays;
  private AccessibleDateAnimator mAnimator;
  private final Calendar mCalendar = new Calendar();
  private CalendarDatePickerDialog.OnDateSetListener mCallBack;
  private int mCurrentView = -1;
  private TextView mDayOfWeekView;
  private String mDayPickerDescription;
  private DayPickerView mDayPickerView;
  private boolean mDelayAnimation = true;
  private HapticFeedbackController mHapticFeedbackController;
  private HashSet<b.c> mListeners = new HashSet();
  private Calendar mMaxDate;
  private int mMaxYear = 1450;
  private Calendar mMinDate;
  private int mMinYear = 1350;
  private LinearLayout mMonthAndDayView;
  private DialogInterface.OnCancelListener mOnCancelListener;
  private DialogInterface.OnDismissListener mOnDismissListener;
  private String mSelectDay;
  private String mSelectYear;
  private TextView mSelectedDayTextView;
  private TextView mSelectedMonthTextView;
  private boolean mThemeDark;
  private int mWeekStart = 7;
  private String mYearPickerDescription;
  private YearPickerView mYearPickerView;
  private TextView mYearView;
  private Calendar[] selectableDays;
  
  public DatePickerDialog() {}
  
  private void adjustDayInMonthIfNeeded(int paramInt1, int paramInt2) {}
  
  public static DatePickerDialog newInstance(CalendarDatePickerDialog.OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3)
  {
    DatePickerDialog localDatePickerDialog = new DatePickerDialog();
    localDatePickerDialog.initialize(paramOnDateSetListener, paramInt1, paramInt2, paramInt3);
    return localDatePickerDialog;
  }
  
  private void setCurrentView(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt != 1) {
        return;
      }
      localObject = Utils.getPulseAnimator(mYearView, 0.85F, 1.1F);
      if (mDelayAnimation)
      {
        ((ValueAnimator)localObject).setStartDelay(500L);
        mDelayAnimation = false;
      }
      mYearPickerView.onDateChanged();
      if (mCurrentView != paramInt)
      {
        mMonthAndDayView.setSelected(false);
        mYearView.setSelected(true);
        mAnimator.setDisplayedChild(1);
        mCurrentView = paramInt;
      }
      ((ObjectAnimator)localObject).start();
      localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.valueOf(mCalendar.get()));
      localAccessibleDateAnimator = mAnimator;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(mYearPickerDescription);
      localStringBuilder.append(": ");
      localStringBuilder.append((String)localObject);
      localAccessibleDateAnimator.setContentDescription(localStringBuilder.toString());
      Utils.tryAccessibilityAnnounce(mAnimator, mSelectYear);
      return;
    }
    Object localObject = Utils.getPulseAnimator(mMonthAndDayView, 0.9F, 1.05F);
    if (mDelayAnimation)
    {
      ((ValueAnimator)localObject).setStartDelay(500L);
      mDelayAnimation = false;
    }
    mDayPickerView.onDateChanged();
    if (mCurrentView != paramInt)
    {
      mMonthAndDayView.setSelected(true);
      mYearView.setSelected(false);
      mAnimator.setDisplayedChild(0);
      mCurrentView = paramInt;
    }
    ((ObjectAnimator)localObject).start();
    localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(mCalendar.format());
    AccessibleDateAnimator localAccessibleDateAnimator = mAnimator;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(mDayPickerDescription);
    localStringBuilder.append(": ");
    localStringBuilder.append((String)localObject);
    localAccessibleDateAnimator.setContentDescription(localStringBuilder.toString());
    Utils.tryAccessibilityAnnounce(mAnimator, mSelectDay);
  }
  
  private void updateDisplay(boolean paramBoolean)
  {
    Object localObject = mDayOfWeekView;
    if (localObject != null) {
      ((TextView)localObject).setText(mCalendar.getValue());
    }
    mSelectedMonthTextView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format(mCalendar.getDisplayName()));
    mSelectedDayTextView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.valueOf(mCalendar.getTime())));
    mYearView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.valueOf(mCalendar.get())));
    long l = mCalendar.getTimeInMillis();
    mAnimator.setDateMillis(l);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(mCalendar.getDisplayName());
    ((StringBuilder)localObject).append(" ");
    ((StringBuilder)localObject).append(mCalendar.getTime());
    localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(((StringBuilder)localObject).toString());
    mMonthAndDayView.setContentDescription((CharSequence)localObject);
    if (paramBoolean)
    {
      localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(mCalendar.format());
      Utils.tryAccessibilityAnnounce(mAnimator, (CharSequence)localObject);
    }
  }
  
  private void updatePickers()
  {
    Iterator localIterator = mListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnDateChangedListener)localIterator.next()).onDateChanged();
    }
  }
  
  public Calendar[] getEndDate()
  {
    return selectableDays;
  }
  
  public int getFirstDayOfWeek()
  {
    return mWeekStart;
  }
  
  public Calendar[] getHighlightedDays()
  {
    return highlightedDays;
  }
  
  public Calendar getMaxDate()
  {
    return mMaxDate;
  }
  
  public int getMaxYear()
  {
    Object localObject = selectableDays;
    if (localObject != null) {
      return localObject[(localObject.length - 1)].get();
    }
    localObject = mMaxDate;
    if ((localObject != null) && (((Calendar)localObject).get() < mMaxYear)) {
      return mMaxDate.get();
    }
    return mMaxYear;
  }
  
  public int getMinYear()
  {
    Object localObject = selectableDays;
    if (localObject != null) {
      return localObject[0].get();
    }
    localObject = mMinDate;
    if ((localObject != null) && (((Calendar)localObject).get() > mMinYear)) {
      return mMinDate.get();
    }
    return mMinYear;
  }
  
  public MonthAdapter.CalendarDay getSelectedDay()
  {
    return new MonthAdapter.CalendarDay(mCalendar);
  }
  
  public Calendar getStartDate()
  {
    return mMinDate;
  }
  
  public void initialize(CalendarDatePickerDialog.OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3)
  {
    mCallBack = paramOnDateSetListener;
    mCalendar.set(paramInt1, paramInt2, paramInt3);
    mThemeDark = false;
  }
  
  public boolean isThemeDark()
  {
    return mThemeDark;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    DialogInterface.OnCancelListener localOnCancelListener = mOnCancelListener;
    if (localOnCancelListener != null) {
      localOnCancelListener.onCancel(paramDialogInterface);
    }
  }
  
  public void onClick(android.view.View paramView)
  {
    tryVibrate();
    if (paramView.getId() == R.id.date_picker_year)
    {
      setCurrentView(1);
      return;
    }
    if (paramView.getId() == R.id.date_picker_month_and_day) {
      setCurrentView(0);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getActivity().getWindow().setSoftInputMode(3);
    if (paramBundle != null) {
      mCalendar.set(paramBundle.getInt("year"), paramBundle.getInt("month"), paramBundle.getInt("day"));
    }
  }
  
  public android.view.View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Log.d("DatePickerDialog", "onCreateView: ");
    getDialog().getWindow().requestFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.mdtp_date_picker_dialog, null);
    mDayOfWeekView = ((TextView)paramLayoutInflater.findViewById(R.id.date_picker_header));
    mMonthAndDayView = ((LinearLayout)paramLayoutInflater.findViewById(R.id.date_picker_month_and_day));
    mMonthAndDayView.setOnClickListener(this);
    mSelectedMonthTextView = ((TextView)paramLayoutInflater.findViewById(R.id.date_picker_month));
    mSelectedDayTextView = ((TextView)paramLayoutInflater.findViewById(R.id.date_picker_day));
    mYearView = ((TextView)paramLayoutInflater.findViewById(R.id.date_picker_year));
    mYearView.setOnClickListener(this);
    int j = -1;
    int k = 0;
    int i = 0;
    if (paramBundle != null)
    {
      mWeekStart = paramBundle.getInt("week_start");
      mMinYear = paramBundle.getInt("year_start");
      mMaxYear = paramBundle.getInt("year_end");
      i = paramBundle.getInt("current_view");
      j = paramBundle.getInt("list_position");
      k = paramBundle.getInt("list_position_offset");
      mMinDate = ((Calendar)paramBundle.getSerializable("min_date"));
      mMaxDate = ((Calendar)paramBundle.getSerializable("max_date"));
      highlightedDays = ((Calendar[])paramBundle.getSerializable("highlighted_days"));
      selectableDays = ((Calendar[])paramBundle.getSerializable("selectable_days"));
      mThemeDark = paramBundle.getBoolean("theme_dark");
    }
    paramViewGroup = getActivity();
    mDayPickerView = new SimpleDayPickerView(paramViewGroup, this);
    mYearPickerView = new YearPickerView(paramViewGroup, this);
    paramBundle = getResources();
    mDayPickerDescription = paramBundle.getString(R.string.mdtp_day_picker_description);
    mSelectDay = paramBundle.getString(R.string.mdtp_select_day);
    mYearPickerDescription = paramBundle.getString(R.string.mdtp_year_picker_description);
    mSelectYear = paramBundle.getString(R.string.mdtp_select_year);
    int m;
    if (mThemeDark) {
      m = R.color.mdtp_date_picker_view_animator_dark_theme;
    } else {
      m = R.color.mdtp_date_picker_view_animator;
    }
    paramLayoutInflater.setBackgroundColor(paramViewGroup.getResources().getColor(m));
    mAnimator = ((AccessibleDateAnimator)paramLayoutInflater.findViewById(R.id.animator));
    mAnimator.addView(mDayPickerView);
    mAnimator.addView(mYearPickerView);
    mAnimator.setDateMillis(mCalendar.getTimeInMillis());
    paramBundle = new AlphaAnimation(0.0F, 1.0F);
    paramBundle.setDuration(300L);
    mAnimator.setInAnimation(paramBundle);
    paramBundle = new AlphaAnimation(1.0F, 0.0F);
    paramBundle.setDuration(300L);
    mAnimator.setOutAnimation(paramBundle);
    paramBundle = (Button)paramLayoutInflater.findViewById(R.id.ok);
    paramBundle.setOnClickListener(new DashboardFragment.1(this));
    paramBundle.setTypeface(TypefaceHelper.get(paramViewGroup, "Roboto-Medium"));
    paramBundle = (Button)paramLayoutInflater.findViewById(R.id.cancel);
    paramBundle.setOnClickListener(new DashboardFragment.2(this));
    paramBundle.setTypeface(TypefaceHelper.get(paramViewGroup, "Roboto-Medium"));
    if (isCancelable()) {
      m = 0;
    } else {
      m = 8;
    }
    paramBundle.setVisibility(m);
    updateDisplay(false);
    setCurrentView(i);
    if (j != -1) {
      if (i == 0) {
        mDayPickerView.postSetSelection(j);
      } else if (i == 1) {
        mYearPickerView.postSetSelectionFromTop(j, k);
      }
    }
    mHapticFeedbackController = new HapticFeedbackController(paramViewGroup);
    return paramLayoutInflater;
  }
  
  public void onDayOfMonthSelected(int paramInt1, int paramInt2, int paramInt3)
  {
    mCalendar.set(paramInt1, paramInt2, paramInt3);
    updatePickers();
    updateDisplay(true);
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    super.onDismiss(paramDialogInterface);
    DialogInterface.OnDismissListener localOnDismissListener = mOnDismissListener;
    if (localOnDismissListener != null) {
      localOnDismissListener.onDismiss(paramDialogInterface);
    }
  }
  
  public void onPause()
  {
    super.onPause();
    mHapticFeedbackController.stop();
  }
  
  public void onResume()
  {
    super.onResume();
    mHapticFeedbackController.start();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("year", mCalendar.get());
    paramBundle.putInt("month", mCalendar.set());
    paramBundle.putInt("day", mCalendar.getTime());
    paramBundle.putInt("week_start", mWeekStart);
    paramBundle.putInt("year_start", mMinYear);
    paramBundle.putInt("year_end", mMaxYear);
    paramBundle.putInt("current_view", mCurrentView);
    int i = -1;
    int j = mCurrentView;
    if (j == 0)
    {
      i = mDayPickerView.getMostVisiblePosition();
    }
    else if (j == 1)
    {
      i = mYearPickerView.getFirstVisiblePosition();
      paramBundle.putInt("list_position_offset", mYearPickerView.getFirstPositionOffset());
    }
    paramBundle.putInt("list_position", i);
    paramBundle.putSerializable("min_date", mMinDate);
    paramBundle.putSerializable("max_date", mMaxDate);
    paramBundle.putSerializable("highlighted_days", highlightedDays);
    paramBundle.putSerializable("selectable_days", selectableDays);
    paramBundle.putBoolean("theme_dark", mThemeDark);
  }
  
  public void onYearSelected(int paramInt)
  {
    adjustDayInMonthIfNeeded(mCalendar.set(), paramInt);
    Calendar localCalendar = mCalendar;
    localCalendar.set(paramInt, localCalendar.set(), mCalendar.getTime());
    updatePickers();
    setCurrentView(0);
    updateDisplay(true);
  }
  
  public void registerOnDateChangedListener(OnDateChangedListener paramOnDateChangedListener)
  {
    mListeners.add(paramOnDateChangedListener);
  }
  
  public void tryVibrate()
  {
    mHapticFeedbackController.tryVibrate();
  }
  
  public abstract interface OnDateChangedListener
  {
    public abstract void onDateChanged();
  }
}
