package android.support.v7.widget;

import a.b.g.f.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.org.jaxen.text.Place;
import org.org.jaxen.text.Segment;

public class AppCompatTextView
  extends android.widget.TextView
  implements TintableBackgroundView, android.support.v4.widget.TimePicker
{
  private Future<b> f;
  private final AppCompatBackgroundHelper mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
  private final TimePicker mTimePicker;
  
  public AppCompatTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842884);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mBackgroundTintHelper.loadFromAttributes(paramAttributeSet, paramInt);
    mTimePicker = new TimePicker(this);
    mTimePicker.applyStyle(paramAttributeSet, paramInt);
    mTimePicker.applyCompoundDrawablesTints();
  }
  
  private void a()
  {
    Object localObject = f;
    if (localObject != null)
    {
      f = null;
      try
      {
        localObject = ((Future)localObject).get();
        localObject = (Segment)localObject;
        android.support.v4.widget.TextView.a(this, (Segment)localObject);
        return;
      }
      catch (ExecutionException localExecutionException) {}catch (InterruptedException localInterruptedException) {}
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Object localObject = mBackgroundTintHelper;
    if (localObject != null) {
      ((AppCompatBackgroundHelper)localObject).applySupportBackgroundTint();
    }
    localObject = mTimePicker;
    if (localObject != null) {
      ((TimePicker)localObject).applyCompoundDrawablesTints();
    }
  }
  
  public int getAutoSizeMaxTextSize()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeMaxTextSize();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.getCurrentHour();
    }
    return -1;
  }
  
  public int getAutoSizeMinTextSize()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeMinTextSize();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.onSaveInstanceState();
    }
    return -1;
  }
  
  public int getAutoSizeStepGranularity()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeStepGranularity();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.setTime();
    }
    return -1;
  }
  
  public int[] getAutoSizeTextAvailableSizes()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      return super.getAutoSizeTextAvailableSizes();
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      return localTimePicker.getHour();
    }
    return new int[0];
  }
  
  public int getAutoSizeTextType()
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      if (super.getAutoSizeTextType() == 1) {
        return 1;
      }
    }
    else
    {
      TimePicker localTimePicker = mTimePicker;
      if (localTimePicker != null) {
        return localTimePicker.getTextSize();
      }
    }
    return 0;
  }
  
  public int getFirstBaselineToTopHeight()
  {
    return android.support.v4.widget.TextView.updatePadding(this);
  }
  
  public int getLastBaselineToBottomHeight()
  {
    return android.support.v4.widget.TextView.setText(this);
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
  
  public CharSequence getText()
  {
    a();
    return super.getText();
  }
  
  public Place getTextMetricsParamsCompat()
  {
    return android.support.v4.widget.TextView.a(this);
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    Resources.validate(localInputConnection, paramEditorInfo, this);
    return localInputConnection;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setTime(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    a();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
    paramCharSequence = mTimePicker;
    if ((paramCharSequence != null) && (!android.support.v4.widget.TimePicker.mIs24HourMode) && (paramCharSequence.setHour())) {
      mTimePicker.setEnabled();
    }
  }
  
  public void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.onSaveInstanceState(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void setAutoSizeTextTypeUniformWithPresetSizes(int[] paramArrayOfInt, int paramInt)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setEnabled(paramArrayOfInt, paramInt);
    }
  }
  
  public void setAutoSizeTextTypeWithDefaults(int paramInt)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setAutoSizeTextTypeWithDefaults(paramInt);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setEnabled(paramInt);
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
  
  public void setCustomSelectionActionModeCallback(ActionMode.Callback paramCallback)
  {
    super.setCustomSelectionActionModeCallback(android.support.v4.widget.TextView.init(this, paramCallback));
  }
  
  public void setFirstBaselineToTopHeight(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      super.setFirstBaselineToTopHeight(paramInt);
      return;
    }
    android.support.v4.widget.TextView.a(this, paramInt);
  }
  
  public void setLastBaselineToBottomHeight(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      super.setLastBaselineToBottomHeight(paramInt);
      return;
    }
    android.support.v4.widget.TextView.setText(this, paramInt);
  }
  
  public void setLineHeight(int paramInt)
  {
    android.support.v4.widget.TextView.init(this, paramInt);
  }
  
  public void setPrecomputedText(Segment paramSegment)
  {
    android.support.v4.widget.TextView.a(this, paramSegment);
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
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    super.setTextAppearance(paramContext, paramInt);
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.applyStyle(paramContext, paramInt);
    }
  }
  
  public void setTextFuture(Future paramFuture)
  {
    f = paramFuture;
    requestLayout();
  }
  
  public void setTextMetricsParamsCompat(Place paramPlace)
  {
    android.support.v4.widget.TextView.init(this, paramPlace);
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    if (android.support.v4.widget.TimePicker.mIs24HourMode)
    {
      super.setTextSize(paramInt, paramFloat);
      return;
    }
    TimePicker localTimePicker = mTimePicker;
    if (localTimePicker != null) {
      localTimePicker.setHour(paramInt, paramFloat);
    }
  }
}
