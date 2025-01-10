package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import java.lang.ref.WeakReference;
import org.org.v4.content.R.styleable;

class TimePicker
{
  private final ClassWriter a;
  private int mContext = 0;
  private TintInfo mDrawableBottomTint;
  private TintInfo mDrawableEndTint;
  private TintInfo mDrawableLeftTint;
  private TintInfo mDrawableRightTint;
  private TintInfo mDrawableStartTint;
  private TintInfo mDrawableTopTint;
  private boolean mMode;
  private Typeface mTypeface;
  private final android.widget.TextView mView;
  
  TimePicker(android.widget.TextView paramTextView)
  {
    mView = paramTextView;
    a = new ClassWriter(mView);
  }
  
  private void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo)
  {
    if ((paramDrawable != null) && (paramTintInfo != null)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, mView.getDrawableState());
    }
  }
  
  private void applyStyle(Context paramContext, TintTypedArray paramTintTypedArray)
  {
    mContext = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, mContext);
    boolean bool2 = paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily);
    boolean bool1 = false;
    int i;
    if ((!bool2) && (!paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)))
    {
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface))
      {
        mMode = false;
        i = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1);
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3) {
              return;
            }
            mTypeface = Typeface.MONOSPACE;
            return;
          }
          mTypeface = Typeface.SERIF;
          return;
        }
        mTypeface = Typeface.SANS_SERIF;
      }
    }
    else
    {
      mTypeface = null;
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
        i = R.styleable.TextAppearance_fontFamily;
      } else {
        i = R.styleable.TextAppearance_android_fontFamily;
      }
      if (!paramContext.isRestricted())
      {
        paramContext = new TextLabelWidget(this, new WeakReference(mView));
        int j = mContext;
        try
        {
          paramContext = paramTintTypedArray.getDrawable(i, j, paramContext);
          mTypeface = paramContext;
          if (mTypeface == null) {
            bool1 = true;
          }
          mMode = bool1;
        }
        catch (Resources.NotFoundException paramContext) {}catch (UnsupportedOperationException paramContext) {}
      }
      if (mTypeface == null)
      {
        paramContext = paramTintTypedArray.getString(i);
        if (paramContext != null) {
          mTypeface = Typeface.create(paramContext, mContext);
        }
      }
    }
  }
  
  private static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt)
  {
    paramContext = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (paramContext != null)
    {
      paramAppCompatDrawableManager = new TintInfo();
      mHasTintList = true;
      mTintList = paramContext;
      return paramAppCompatDrawableManager;
    }
    return null;
  }
  
  private void update(int paramInt, float paramFloat)
  {
    a.b(paramInt, paramFloat);
  }
  
  void applyCompoundDrawablesTints()
  {
    Drawable[] arrayOfDrawable;
    if ((mDrawableLeftTint != null) || (mDrawableBottomTint != null) || (mDrawableTopTint != null) || (mDrawableRightTint != null))
    {
      arrayOfDrawable = mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], mDrawableBottomTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], mDrawableRightTint);
    }
    if ((Build.VERSION.SDK_INT >= 17) && ((mDrawableStartTint != null) || (mDrawableEndTint != null)))
    {
      arrayOfDrawable = mView.getCompoundDrawablesRelative();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableStartTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableEndTint);
    }
  }
  
  void applyStyle(Context paramContext, int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    if ((Build.VERSION.SDK_INT < 23) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)))
    {
      ColorStateList localColorStateList = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (localColorStateList != null) {
        mView.setTextColor(localColorStateList);
      }
    }
    if ((localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) && (localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)) {
      mView.setTextSize(0, 0.0F);
    }
    applyStyle(paramContext, localTintTypedArray);
    localTintTypedArray.recycle();
    paramContext = mTypeface;
    if (paramContext != null) {
      mView.setTypeface(paramContext, mContext);
    }
  }
  
  void applyStyle(AttributeSet paramAttributeSet, int paramInt)
  {
    Context localContext = mView.getContext();
    Object localObject1 = AppCompatDrawableManager.get();
    Object localObject2 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int k = ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
      mDrawableLeftTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
      mDrawableBottomTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
      mDrawableTopTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
      mDrawableRightTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
    }
    if (Build.VERSION.SDK_INT >= 17)
    {
      if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableStart)) {
        mDrawableStartTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
      }
      if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
        mDrawableEndTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
      }
    }
    ((TintTypedArray)localObject2).recycle();
    boolean bool3 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 0;
    int j = 0;
    Object localObject7 = null;
    localObject2 = null;
    TintTypedArray localTintTypedArray1 = null;
    Object localObject4 = null;
    localObject1 = null;
    Object localObject5 = null;
    Object localObject3 = null;
    Object localObject6 = null;
    if (k != -1)
    {
      TintTypedArray localTintTypedArray2 = TintTypedArray.obtainStyledAttributes(localContext, k, R.styleable.TextAppearance);
      bool1 = bool2;
      i = j;
      if (!bool3)
      {
        bool1 = bool2;
        i = j;
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_textAllCaps))
        {
          bool1 = localTintTypedArray2.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
          i = 1;
        }
      }
      applyStyle(localContext, localTintTypedArray2);
      localObject2 = localObject7;
      localObject3 = localObject6;
      if (Build.VERSION.SDK_INT < 23)
      {
        localObject1 = localTintTypedArray1;
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_android_textColor)) {
          localObject1 = localTintTypedArray2.getColorStateList(R.styleable.TextAppearance_android_textColor);
        }
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
          localObject5 = localTintTypedArray2.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
        }
        localObject2 = localObject1;
        localObject4 = localObject5;
        localObject3 = localObject6;
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_android_textColorLink))
        {
          localObject3 = localTintTypedArray2.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
          localObject4 = localObject5;
          localObject2 = localObject1;
        }
      }
      localTintTypedArray2.recycle();
      localObject1 = localObject4;
    }
    localTintTypedArray1 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    bool2 = bool1;
    j = i;
    if (!bool3)
    {
      bool2 = bool1;
      j = i;
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_textAllCaps))
      {
        j = 1;
        bool2 = localTintTypedArray1.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      }
    }
    localObject4 = localObject2;
    localObject5 = localObject1;
    localObject6 = localObject3;
    if (Build.VERSION.SDK_INT < 23)
    {
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_android_textColor)) {
        localObject2 = localTintTypedArray1.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
        localObject1 = localTintTypedArray1.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
      }
      localObject4 = localObject2;
      localObject5 = localObject1;
      localObject6 = localObject3;
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_android_textColorLink))
      {
        localObject6 = localTintTypedArray1.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        localObject5 = localObject1;
        localObject4 = localObject2;
      }
    }
    if ((Build.VERSION.SDK_INT >= 28) && (localTintTypedArray1.hasValue(R.styleable.TextAppearance_android_textSize)) && (localTintTypedArray1.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)) {
      mView.setTextSize(0, 0.0F);
    }
    applyStyle(localContext, localTintTypedArray1);
    localTintTypedArray1.recycle();
    if (localObject4 != null) {
      mView.setTextColor(localObject4);
    }
    if (localObject5 != null) {
      mView.setHintTextColor((ColorStateList)localObject5);
    }
    if (localObject6 != null) {
      mView.setLinkTextColor((ColorStateList)localObject6);
    }
    if ((!bool3) && (j != 0)) {
      setAllCaps(bool2);
    }
    localObject1 = mTypeface;
    if (localObject1 != null) {
      mView.setTypeface((Typeface)localObject1, mContext);
    }
    a.a(paramAttributeSet, paramInt);
    if (android.support.v4.widget.TimePicker.mIs24HourMode) {
      if (a.getTextSize() != 0)
      {
        localObject1 = a.get();
        if (localObject1.length > 0) {
          if (mView.getAutoSizeStepGranularity() != -1.0F) {
            mView.setAutoSizeTextTypeUniformWithConfiguration(a.getHeight(), a.getValue(), a.getWidth(), 0);
          } else {
            mView.setAutoSizeTextTypeUniformWithPresetSizes((int[])localObject1, 0);
          }
        }
      }
      else {}
    }
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.AppCompatTextView);
    paramInt = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
    i = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
    j = paramAttributeSet.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, -1);
    paramAttributeSet.recycle();
    if (paramInt != -1) {
      android.support.v4.widget.TextView.a(mView, paramInt);
    }
    if (i != -1) {
      android.support.v4.widget.TextView.setText(mView, i);
    }
    if (j != -1) {
      android.support.v4.widget.TextView.init(mView, j);
    }
  }
  
  int getCurrentHour()
  {
    return a.getValue();
  }
  
  int[] getHour()
  {
    return a.get();
  }
  
  int getTextSize()
  {
    return a.getTextSize();
  }
  
  int onSaveInstanceState()
  {
    return a.getHeight();
  }
  
  void onSaveInstanceState(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a.a(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAllCaps(boolean paramBoolean)
  {
    mView.setAllCaps(paramBoolean);
  }
  
  void setEnabled()
  {
    a.a();
  }
  
  void setEnabled(int paramInt)
  {
    a.a(paramInt);
  }
  
  void setEnabled(int[] paramArrayOfInt, int paramInt)
  {
    a.a(paramArrayOfInt, paramInt);
  }
  
  void setHour(int paramInt, float paramFloat)
  {
    if ((!android.support.v4.widget.TimePicker.mIs24HourMode) && (!setHour())) {
      update(paramInt, paramFloat);
    }
  }
  
  boolean setHour()
  {
    return a.put();
  }
  
  int setTime()
  {
    return a.getWidth();
  }
  
  void setTime(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!android.support.v4.widget.TimePicker.mIs24HourMode) {
      setEnabled();
    }
  }
  
  void update(WeakReference paramWeakReference, Typeface paramTypeface)
  {
    if (mMode)
    {
      mTypeface = paramTypeface;
      paramWeakReference = (android.widget.TextView)paramWeakReference.get();
      if (paramWeakReference != null) {
        paramWeakReference.setTypeface(paramTypeface, mContext);
      }
    }
  }
}
