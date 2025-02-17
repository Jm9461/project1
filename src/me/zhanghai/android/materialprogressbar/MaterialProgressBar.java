package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import me.zhanghai.android.materialprogressbar.internal.DrawableCompat;

public class MaterialProgressBar
  extends ProgressBar
{
  public static final int DETERMINATE_CIRCULAR_PROGRESS_STYLE_DYNAMIC = 1;
  public static final int DETERMINATE_CIRCULAR_PROGRESS_STYLE_NORMAL = 0;
  public static final int PROGRESS_STYLE_CIRCULAR = 0;
  public static final int PROGRESS_STYLE_HORIZONTAL = 1;
  private static final String TAG = MaterialProgressBar.class.getSimpleName();
  private int mProgressStyle;
  private TintInfo mProgressTintInfo = new TintInfo(null);
  private boolean mSuperInitialized = true;
  
  public MaterialProgressBar(Context paramContext)
  {
    super(paramContext);
    init(null, 0, 0);
  }
  
  public MaterialProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0, 0);
  }
  
  public MaterialProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt, 0);
  }
  
  public MaterialProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramAttributeSet, paramInt1, paramInt2);
  }
  
  private void applyIndeterminateTint()
  {
    Drawable localDrawable = getIndeterminateDrawable();
    if (localDrawable == null) {
      return;
    }
    TintInfo localTintInfo = mProgressTintInfo;
    if ((mHasIndeterminateTint) || (mHasIndeterminateTintMode))
    {
      localDrawable.mutate();
      localTintInfo = mProgressTintInfo;
      applyTintForDrawable(localDrawable, mIndeterminateTint, mHasIndeterminateTint, mIndeterminateTintMode, mHasIndeterminateTintMode);
    }
  }
  
  private void applyPrimaryProgressTint()
  {
    if (getProgressDrawable() == null) {
      return;
    }
    Object localObject = mProgressTintInfo;
    if ((mHasProgressTint) || (mHasProgressTintMode))
    {
      localObject = getTintTargetFromProgressDrawable(16908301, true);
      if (localObject != null)
      {
        TintInfo localTintInfo = mProgressTintInfo;
        applyTintForDrawable((Drawable)localObject, mProgressTint, mHasProgressTint, mProgressTintMode, mHasProgressTintMode);
      }
    }
  }
  
  private void applyProgressBackgroundTint()
  {
    if (getProgressDrawable() == null) {
      return;
    }
    Object localObject = mProgressTintInfo;
    if ((mHasProgressBackgroundTint) || (mHasProgressBackgroundTintMode))
    {
      localObject = getTintTargetFromProgressDrawable(16908288, false);
      if (localObject != null)
      {
        TintInfo localTintInfo = mProgressTintInfo;
        applyTintForDrawable((Drawable)localObject, mProgressBackgroundTint, mHasProgressBackgroundTint, mProgressBackgroundTintMode, mHasProgressBackgroundTintMode);
      }
    }
  }
  
  private void applyProgressTints()
  {
    if (getProgressDrawable() == null) {
      return;
    }
    applyPrimaryProgressTint();
    applyProgressBackgroundTint();
    applySecondaryProgressTint();
  }
  
  private void applySecondaryProgressTint()
  {
    if (getProgressDrawable() == null) {
      return;
    }
    Object localObject = mProgressTintInfo;
    if ((mHasSecondaryProgressTint) || (mHasSecondaryProgressTintMode))
    {
      localObject = getTintTargetFromProgressDrawable(16908303, false);
      if (localObject != null)
      {
        TintInfo localTintInfo = mProgressTintInfo;
        applyTintForDrawable((Drawable)localObject, mSecondaryProgressTint, mHasSecondaryProgressTint, mSecondaryProgressTintMode, mHasSecondaryProgressTintMode);
      }
    }
  }
  
  private void applyTintForDrawable(Drawable paramDrawable, ColorStateList paramColorStateList, boolean paramBoolean1, PorterDuff.Mode paramMode, boolean paramBoolean2)
  {
    if ((paramBoolean1) || (paramBoolean2))
    {
      if (paramBoolean1) {
        if ((paramDrawable instanceof TintableDrawable))
        {
          ((TintableDrawable)paramDrawable).setTintList(paramColorStateList);
        }
        else
        {
          Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
          if (Build.VERSION.SDK_INT >= 21) {
            paramDrawable.setTintList(paramColorStateList);
          }
        }
      }
      if (paramBoolean2) {
        if ((paramDrawable instanceof TintableDrawable))
        {
          ((TintableDrawable)paramDrawable).setTintMode(paramMode);
        }
        else
        {
          Log.w(TAG, "Drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
          if (Build.VERSION.SDK_INT >= 21) {
            paramDrawable.setTintMode(paramMode);
          }
        }
      }
      if (paramDrawable.isStateful()) {
        paramDrawable.setState(getDrawableState());
      }
    }
  }
  
  private void fixCanvasScalingAndColorFilterWhenHardwareAccelerated()
  {
    if ((Build.VERSION.SDK_INT < 21) && (isHardwareAccelerated()) && (getLayerType() != 1)) {
      setLayerType(1, null);
    }
  }
  
  private Drawable getTintTargetFromProgressDrawable(int paramInt, boolean paramBoolean)
  {
    Drawable localDrawable2 = getProgressDrawable();
    if (localDrawable2 == null) {
      return null;
    }
    localDrawable2.mutate();
    Drawable localDrawable1 = null;
    if ((localDrawable2 instanceof LayerDrawable)) {
      localDrawable1 = ((LayerDrawable)localDrawable2).findDrawableByLayerId(paramInt);
    }
    if ((localDrawable1 == null) && (paramBoolean)) {
      return localDrawable2;
    }
    return localDrawable1;
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    Context localContext = getContext();
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.MaterialProgressBar, paramInt1, paramInt2);
    mProgressStyle = paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_progressStyle, 0);
    boolean bool2 = paramAttributeSet.getBoolean(R.styleable.MaterialProgressBar_mpb_setBothDrawables, false);
    boolean bool3 = paramAttributeSet.getBoolean(R.styleable.MaterialProgressBar_mpb_useIntrinsicPadding, true);
    paramInt1 = R.styleable.MaterialProgressBar_mpb_showProgressBackground;
    if (mProgressStyle == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool1 = paramAttributeSet.getBoolean(paramInt1, bool1);
    paramInt1 = paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_determinateCircularProgressStyle, 0);
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_progressTint))
    {
      mProgressTintInfo.mProgressTint = paramAttributeSet.getColorStateList(R.styleable.MaterialProgressBar_mpb_progressTint);
      mProgressTintInfo.mHasProgressTint = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_progressTintMode))
    {
      mProgressTintInfo.mProgressTintMode = DrawableCompat.parseTintMode(paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_progressTintMode, -1), null);
      mProgressTintInfo.mHasProgressTintMode = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_secondaryProgressTint))
    {
      mProgressTintInfo.mSecondaryProgressTint = paramAttributeSet.getColorStateList(R.styleable.MaterialProgressBar_mpb_secondaryProgressTint);
      mProgressTintInfo.mHasSecondaryProgressTint = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_secondaryProgressTintMode))
    {
      mProgressTintInfo.mSecondaryProgressTintMode = DrawableCompat.parseTintMode(paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_secondaryProgressTintMode, -1), null);
      mProgressTintInfo.mHasSecondaryProgressTintMode = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_progressBackgroundTint))
    {
      mProgressTintInfo.mProgressBackgroundTint = paramAttributeSet.getColorStateList(R.styleable.MaterialProgressBar_mpb_progressBackgroundTint);
      mProgressTintInfo.mHasProgressBackgroundTint = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_progressBackgroundTintMode))
    {
      mProgressTintInfo.mProgressBackgroundTintMode = DrawableCompat.parseTintMode(paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_progressBackgroundTintMode, -1), null);
      mProgressTintInfo.mHasProgressBackgroundTintMode = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_indeterminateTint))
    {
      mProgressTintInfo.mIndeterminateTint = paramAttributeSet.getColorStateList(R.styleable.MaterialProgressBar_mpb_indeterminateTint);
      mProgressTintInfo.mHasIndeterminateTint = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.MaterialProgressBar_mpb_indeterminateTintMode))
    {
      mProgressTintInfo.mIndeterminateTintMode = DrawableCompat.parseTintMode(paramAttributeSet.getInt(R.styleable.MaterialProgressBar_mpb_indeterminateTintMode, -1), null);
      mProgressTintInfo.mHasIndeterminateTintMode = true;
    }
    paramAttributeSet.recycle();
    paramInt2 = mProgressStyle;
    if (paramInt2 != 0)
    {
      if (paramInt2 == 1)
      {
        if (((isIndeterminate()) || (bool2)) && (!isInEditMode())) {
          setIndeterminateDrawable(new IndeterminateHorizontalProgressDrawable(localContext));
        }
        if ((!isIndeterminate()) || (bool2)) {
          setProgressDrawable(new HorizontalProgressDrawable(localContext));
        }
      }
      else
      {
        paramAttributeSet = new StringBuilder();
        paramAttributeSet.append("Unknown progress style: ");
        paramAttributeSet.append(mProgressStyle);
        throw new IllegalArgumentException(paramAttributeSet.toString());
      }
    }
    else
    {
      if (((isIndeterminate()) || (bool2)) && (!isInEditMode())) {
        setIndeterminateDrawable(new IndeterminateCircularProgressDrawable(localContext));
      }
      if ((!isIndeterminate()) || (bool2)) {
        setProgressDrawable(new CircularProgressDrawable(paramInt1, localContext));
      }
    }
    setUseIntrinsicPadding(bool3);
    setShowProgressBackground(bool1);
  }
  
  public Drawable getCurrentDrawable()
  {
    if (isIndeterminate()) {
      return getIndeterminateDrawable();
    }
    return getProgressDrawable();
  }
  
  public ColorStateList getIndeterminateTintList()
  {
    return mProgressTintInfo.mIndeterminateTint;
  }
  
  public PorterDuff.Mode getIndeterminateTintMode()
  {
    return mProgressTintInfo.mIndeterminateTintMode;
  }
  
  public ColorStateList getProgressBackgroundTintList()
  {
    return mProgressTintInfo.mProgressBackgroundTint;
  }
  
  public PorterDuff.Mode getProgressBackgroundTintMode()
  {
    return mProgressTintInfo.mProgressBackgroundTintMode;
  }
  
  public int getProgressStyle()
  {
    return mProgressStyle;
  }
  
  public ColorStateList getProgressTintList()
  {
    return mProgressTintInfo.mProgressTint;
  }
  
  public PorterDuff.Mode getProgressTintMode()
  {
    return mProgressTintInfo.mProgressTintMode;
  }
  
  public ColorStateList getSecondaryProgressTintList()
  {
    return mProgressTintInfo.mSecondaryProgressTint;
  }
  
  public PorterDuff.Mode getSecondaryProgressTintMode()
  {
    return mProgressTintInfo.mSecondaryProgressTintMode;
  }
  
  public boolean getShowProgressBackground()
  {
    Drawable localDrawable = getCurrentDrawable();
    if ((localDrawable instanceof ShowBackgroundDrawable)) {
      return ((ShowBackgroundDrawable)localDrawable).getShowBackground();
    }
    return false;
  }
  
  public boolean getUseIntrinsicPadding()
  {
    Drawable localDrawable = getCurrentDrawable();
    if ((localDrawable instanceof IntrinsicPaddingDrawable)) {
      return ((IntrinsicPaddingDrawable)localDrawable).getUseIntrinsicPadding();
    }
    throw new IllegalStateException("Drawable does not implement IntrinsicPaddingDrawable");
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    fixCanvasScalingAndColorFilterWhenHardwareAccelerated();
  }
  
  public void setIndeterminate(boolean paramBoolean)
  {
    try
    {
      super.setIndeterminate(paramBoolean);
      if ((mSuperInitialized) && (!(getCurrentDrawable() instanceof MaterialProgressDrawable))) {
        Log.w(TAG, "Current drawable is not a MaterialProgressDrawable, you may want to set app:mpb_setBothDrawables");
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setIndeterminateDrawable(Drawable paramDrawable)
  {
    super.setIndeterminateDrawable(paramDrawable);
    if (mProgressTintInfo != null) {
      applyIndeterminateTint();
    }
  }
  
  public void setIndeterminateTintList(ColorStateList paramColorStateList)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mIndeterminateTint = paramColorStateList;
    mHasIndeterminateTint = true;
    applyIndeterminateTint();
  }
  
  public void setIndeterminateTintMode(PorterDuff.Mode paramMode)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mIndeterminateTintMode = paramMode;
    mHasIndeterminateTintMode = true;
    applyIndeterminateTint();
  }
  
  public void setProgressBackgroundTintList(ColorStateList paramColorStateList)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mProgressBackgroundTint = paramColorStateList;
    mHasProgressBackgroundTint = true;
    applyProgressBackgroundTint();
  }
  
  public void setProgressBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mProgressBackgroundTintMode = paramMode;
    mHasProgressBackgroundTintMode = true;
    applyProgressBackgroundTint();
  }
  
  public void setProgressDrawable(Drawable paramDrawable)
  {
    super.setProgressDrawable(paramDrawable);
    if (mProgressTintInfo != null) {
      applyProgressTints();
    }
  }
  
  public void setProgressTintList(ColorStateList paramColorStateList)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mProgressTint = paramColorStateList;
    mHasProgressTint = true;
    applyPrimaryProgressTint();
  }
  
  public void setProgressTintMode(PorterDuff.Mode paramMode)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mProgressTintMode = paramMode;
    mHasProgressTintMode = true;
    applyPrimaryProgressTint();
  }
  
  public void setSecondaryProgressTintList(ColorStateList paramColorStateList)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mSecondaryProgressTint = paramColorStateList;
    mHasSecondaryProgressTint = true;
    applySecondaryProgressTint();
  }
  
  public void setSecondaryProgressTintMode(PorterDuff.Mode paramMode)
  {
    TintInfo localTintInfo = mProgressTintInfo;
    mSecondaryProgressTintMode = paramMode;
    mHasSecondaryProgressTintMode = true;
    applySecondaryProgressTint();
  }
  
  public void setShowProgressBackground(boolean paramBoolean)
  {
    Drawable localDrawable = getCurrentDrawable();
    if ((localDrawable instanceof ShowBackgroundDrawable)) {
      ((ShowBackgroundDrawable)localDrawable).setShowBackground(paramBoolean);
    }
    localDrawable = getIndeterminateDrawable();
    if ((localDrawable instanceof ShowBackgroundDrawable)) {
      ((ShowBackgroundDrawable)localDrawable).setShowBackground(paramBoolean);
    }
  }
  
  public void setUseIntrinsicPadding(boolean paramBoolean)
  {
    Drawable localDrawable = getCurrentDrawable();
    if ((localDrawable instanceof IntrinsicPaddingDrawable)) {
      ((IntrinsicPaddingDrawable)localDrawable).setUseIntrinsicPadding(paramBoolean);
    }
    localDrawable = getIndeterminateDrawable();
    if ((localDrawable instanceof IntrinsicPaddingDrawable)) {
      ((IntrinsicPaddingDrawable)localDrawable).setUseIntrinsicPadding(paramBoolean);
    }
  }
  
  private static class TintInfo
  {
    public boolean mHasIndeterminateTint;
    public boolean mHasIndeterminateTintMode;
    public boolean mHasProgressBackgroundTint;
    public boolean mHasProgressBackgroundTintMode;
    public boolean mHasProgressTint;
    public boolean mHasProgressTintMode;
    public boolean mHasSecondaryProgressTint;
    public boolean mHasSecondaryProgressTintMode;
    public ColorStateList mIndeterminateTint;
    public PorterDuff.Mode mIndeterminateTintMode;
    public ColorStateList mProgressBackgroundTint;
    public PorterDuff.Mode mProgressBackgroundTintMode;
    public ColorStateList mProgressTint;
    public PorterDuff.Mode mProgressTintMode;
    public ColorStateList mSecondaryProgressTint;
    public PorterDuff.Mode mSecondaryProgressTintMode;
    
    private TintInfo() {}
  }
}
