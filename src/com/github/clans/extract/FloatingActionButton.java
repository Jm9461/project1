package com.github.clans.extract;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FloatingActionButton
  extends ImageButton
{
  private static final Xfermode PORTER_DUFF_CLEAR = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
  private boolean mAnimateProgress;
  private Drawable mBackgroundDrawable;
  private Paint mBackgroundPaint = new Paint(1);
  private float mBarExtraLength;
  private boolean mBarGrowingFromFront = true;
  private int mBarLength = 16;
  private boolean mButtonPositionSaved;
  private View.OnClickListener mClickListener;
  private int mColorDisabled;
  private int mColorNormal;
  private int mColorPressed;
  private int mColorRipple;
  private float mCurrentProgress;
  int mFabSize;
  GestureDetector mGestureDetector = new GestureDetector(getContext(), new b());
  private Animation mHideAnimation;
  private Drawable mIcon;
  private int mIconSize = Util.dpToPx(getContext(), 24.0F);
  private String mLabelText;
  private long mLastTimeAnimated;
  private float mOriginalX = -1.0F;
  private float mOriginalY = -1.0F;
  private long mPausedTimeWithoutGrowing = 0L;
  private int mProgress;
  private int mProgressBackgroundColor;
  private boolean mProgressBarEnabled;
  private RectF mProgressCircleBounds = new RectF();
  private int mProgressColor;
  private boolean mProgressIndeterminate;
  private int mProgressMax = 100;
  private Paint mProgressPaint = new Paint(1);
  private int mProgressWidth = Util.dpToPx(getContext(), 6.0F);
  int mShadowColor;
  int mShadowRadius = Util.dpToPx(getContext(), 4.0F);
  int mShadowXOffset = Util.dpToPx(getContext(), 1.0F);
  int mShadowYOffset = Util.dpToPx(getContext(), 3.0F);
  private boolean mShouldProgressIndeterminate;
  private boolean mShouldSetProgress;
  private boolean mShouldUpdateButtonPosition;
  private Animation mShowAnimation;
  private boolean mShowProgressBackground;
  boolean mShowShadow;
  private float mSpinSpeed = 195.0F;
  private float mTargetProgress;
  private double mTimeStartGrowing;
  private boolean mUsingElevation;
  private boolean mUsingElevationCompat;
  
  public FloatingActionButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramContext, paramAttributeSet, paramInt1);
  }
  
  private float calculateCenterX()
  {
    return getMeasuredWidth() / 2;
  }
  
  private float calculateCenterY()
  {
    return getMeasuredHeight() / 2;
  }
  
  private int calculateMeasuredHeight()
  {
    int j = getCircleSize() + calculateShadowWidth();
    int i = j;
    if (mProgressBarEnabled) {
      i = j + mProgressWidth * 2;
    }
    return i;
  }
  
  private int calculateMeasuredWidth()
  {
    int j = getCircleSize() + calculateShadowHeight();
    int i = j;
    if (mProgressBarEnabled) {
      i = j + mProgressWidth * 2;
    }
    return i;
  }
  
  private Drawable createCircleDrawable(int paramInt)
  {
    d localD = new d(new OvalShape(), null);
    localD.getPaint().setColor(paramInt);
    return localD;
  }
  
  private Drawable createFillDrawable()
  {
    Object localObject1 = new StateListDrawable();
    Object localObject2 = createCircleDrawable(mColorDisabled);
    ((StateListDrawable)localObject1).addState(new int[] { -16842910 }, (Drawable)localObject2);
    localObject2 = createCircleDrawable(mColorPressed);
    ((StateListDrawable)localObject1).addState(new int[] { 16842919 }, (Drawable)localObject2);
    localObject2 = createCircleDrawable(mColorNormal);
    ((StateListDrawable)localObject1).addState(new int[0], (Drawable)localObject2);
    if (Util.hasLollipop())
    {
      localObject2 = new int[0];
      int i = mColorRipple;
      localObject1 = new RippleDrawable(new ColorStateList(new int[][] { localObject2 }, new int[] { i }), (Drawable)localObject1, null);
      setOutlineProvider(new FloatingActionButton.a(this));
      setClipToOutline(true);
      mBackgroundDrawable = ((Drawable)localObject1);
      return (Drawable)localObject1;
    }
    mBackgroundDrawable = ((Drawable)localObject1);
    return localObject1;
  }
  
  private int getCircleSize()
  {
    Resources localResources = getResources();
    int i;
    if (mFabSize == 0) {
      i = R.dimen.fab_size_normal;
    } else {
      i = R.dimen.fab_size_mini;
    }
    return localResources.getDimensionPixelSize(i);
  }
  
  private int getShadowX()
  {
    return mShadowRadius + Math.abs(mShadowXOffset);
  }
  
  private int getShadowY()
  {
    return mShadowRadius + Math.abs(mShadowYOffset);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionButton, paramInt, 0);
    mColorNormal = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorNormal, -2473162);
    mColorPressed = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorPressed, -1617853);
    mColorDisabled = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorDisabled, -5592406);
    mColorRipple = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorRipple, -1711276033);
    mShowShadow = paramContext.getBoolean(R.styleable.FloatingActionButton_fab_showShadow, true);
    mShadowColor = paramContext.getColor(R.styleable.FloatingActionButton_fab_shadowColor, 1711276032);
    mShadowRadius = paramContext.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowRadius, mShadowRadius);
    mShadowXOffset = paramContext.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowXOffset, mShadowXOffset);
    mShadowYOffset = paramContext.getDimensionPixelSize(R.styleable.FloatingActionButton_fab_shadowYOffset, mShadowYOffset);
    mFabSize = paramContext.getInt(R.styleable.FloatingActionButton_fab_size, 0);
    mLabelText = paramContext.getString(R.styleable.FloatingActionButton_fab_label);
    mShouldProgressIndeterminate = paramContext.getBoolean(R.styleable.FloatingActionButton_fab_progress_indeterminate, false);
    mProgressColor = paramContext.getColor(R.styleable.FloatingActionButton_fab_progress_color, -16738680);
    mProgressBackgroundColor = paramContext.getColor(R.styleable.FloatingActionButton_fab_progress_backgroundColor, 1291845632);
    mProgressMax = paramContext.getInt(R.styleable.FloatingActionButton_fab_progress_max, mProgressMax);
    mShowProgressBackground = paramContext.getBoolean(R.styleable.FloatingActionButton_fab_progress_showBackground, true);
    if (paramContext.hasValue(R.styleable.FloatingActionButton_fab_progress))
    {
      mProgress = paramContext.getInt(R.styleable.FloatingActionButton_fab_progress, 0);
      mShouldSetProgress = true;
    }
    if (paramContext.hasValue(R.styleable.FloatingActionButton_fab_elevationCompat))
    {
      float f = paramContext.getDimensionPixelOffset(R.styleable.FloatingActionButton_fab_elevationCompat, 0);
      if (isInEditMode()) {
        setElevation(f);
      } else {
        setElevationCompat(f);
      }
    }
    initShowAnimation(paramContext);
    initHideAnimation(paramContext);
    paramContext.recycle();
    if (isInEditMode()) {
      if (mShouldProgressIndeterminate)
      {
        setIndeterminate(true);
      }
      else if (mShouldSetProgress)
      {
        saveButtonOriginalPosition();
        setProgress(mProgress, false);
      }
    }
    setClickable(true);
  }
  
  private void initHideAnimation(TypedArray paramTypedArray)
  {
    int i = paramTypedArray.getResourceId(R.styleable.FloatingActionButton_fab_hideAnimation, R.anim.fab_scale_down);
    mHideAnimation = AnimationUtils.loadAnimation(getContext(), i);
  }
  
  private void initShowAnimation(TypedArray paramTypedArray)
  {
    int i = paramTypedArray.getResourceId(R.styleable.FloatingActionButton_fab_showAnimation, R.anim.fab_scale_up);
    mShowAnimation = AnimationUtils.loadAnimation(getContext(), i);
  }
  
  private void saveButtonOriginalPosition()
  {
    if (!mButtonPositionSaved)
    {
      if (mOriginalX == -1.0F) {
        mOriginalX = getX();
      }
      if (mOriginalY == -1.0F) {
        mOriginalY = getY();
      }
      mButtonPositionSaved = true;
    }
  }
  
  private void setBackgroundCompat(Drawable paramDrawable)
  {
    if (Util.hasJellyBean())
    {
      setBackground(paramDrawable);
      return;
    }
    setBackgroundDrawable(paramDrawable);
  }
  
  private void setupProgressBarPaints()
  {
    mBackgroundPaint.setColor(mProgressBackgroundColor);
    mBackgroundPaint.setStyle(Paint.Style.STROKE);
    mBackgroundPaint.setStrokeWidth(mProgressWidth);
    mProgressPaint.setColor(mProgressColor);
    mProgressPaint.setStyle(Paint.Style.STROKE);
    mProgressPaint.setStrokeWidth(mProgressWidth);
  }
  
  private void setupProgressBounds()
  {
    boolean bool = hasShadow();
    int j = 0;
    int i;
    if (bool) {
      i = getShadowX();
    } else {
      i = 0;
    }
    if (hasShadow()) {
      j = getShadowY();
    }
    int k = mProgressWidth;
    mProgressCircleBounds = new RectF(k / 2 + i, k / 2 + j, calculateMeasuredHeight() - i - mProgressWidth / 2, calculateMeasuredWidth() - j - mProgressWidth / 2);
  }
  
  private void updateButtonPosition()
  {
    float f1;
    float f2;
    if (mProgressBarEnabled)
    {
      if (mOriginalX > getX()) {
        f1 = getX() + mProgressWidth;
      } else {
        f1 = getX() - mProgressWidth;
      }
      if (mOriginalY > getY()) {
        f2 = getY() + mProgressWidth;
      } else {
        f2 = getY() - mProgressWidth;
      }
    }
    else
    {
      f1 = mOriginalX;
      f2 = mOriginalY;
    }
    setX(f1);
    setY(f2);
  }
  
  private void updateProgressLength(long paramLong)
  {
    long l = mPausedTimeWithoutGrowing;
    if (l >= 200L)
    {
      double d1 = mTimeStartGrowing;
      double d2 = paramLong;
      Double.isNaN(d2);
      mTimeStartGrowing = (d1 + d2);
      d1 = mTimeStartGrowing;
      if (d1 > 500.0D)
      {
        mTimeStartGrowing = (d1 - 500.0D);
        mPausedTimeWithoutGrowing = 0L;
        mBarGrowingFromFront ^= true;
      }
      float f1 = (float)Math.cos((mTimeStartGrowing / 500.0D + 1.0D) * 3.141592653589793D) / 2.0F + 0.5F;
      float f2 = 270 - mBarLength;
      if (mBarGrowingFromFront)
      {
        mBarExtraLength = (f1 * f2);
        return;
      }
      f1 = (1.0F - f1) * f2;
      mCurrentProgress += mBarExtraLength - f1;
      mBarExtraLength = f1;
      return;
    }
    mPausedTimeWithoutGrowing = (l + paramLong);
  }
  
  int calculateShadowHeight()
  {
    if (hasShadow()) {
      return getShadowY() * 2;
    }
    return 0;
  }
  
  int calculateShadowWidth()
  {
    if (hasShadow()) {
      return getShadowX() * 2;
    }
    return 0;
  }
  
  public int getButtonSize()
  {
    return mFabSize;
  }
  
  public int getColorDisabled()
  {
    return mColorDisabled;
  }
  
  public int getColorNormal()
  {
    return mColorNormal;
  }
  
  public int getColorPressed()
  {
    return mColorPressed;
  }
  
  public int getColorRipple()
  {
    return mColorRipple;
  }
  
  Animation getHideAnimation()
  {
    return mHideAnimation;
  }
  
  protected Drawable getIconDrawable()
  {
    Drawable localDrawable = mIcon;
    if (localDrawable != null) {
      return localDrawable;
    }
    return new ColorDrawable(0);
  }
  
  public String getLabelText()
  {
    return mLabelText;
  }
  
  Label getLabelView()
  {
    return (Label)getTag(R.id.fab_label);
  }
  
  public int getLabelVisibility()
  {
    Label localLabel = getLabelView();
    if (localLabel != null) {
      return localLabel.getVisibility();
    }
    return -1;
  }
  
  public int getMax()
  {
    try
    {
      int i = mProgressMax;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  View.OnClickListener getOnClickListener()
  {
    return mClickListener;
  }
  
  public int getProgress()
  {
    try
    {
      int i;
      if (mProgressIndeterminate) {
        i = 0;
      } else {
        i = mProgress;
      }
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getShadowColor()
  {
    return mShadowColor;
  }
  
  public int getShadowRadius()
  {
    return mShadowRadius;
  }
  
  public int getShadowXOffset()
  {
    return mShadowXOffset;
  }
  
  public int getShadowYOffset()
  {
    return mShadowYOffset;
  }
  
  Animation getShowAnimation()
  {
    return mShowAnimation;
  }
  
  public boolean hasShadow()
  {
    return (!mUsingElevation) && (mShowShadow);
  }
  
  public void hide(boolean paramBoolean)
  {
    if (!isHidden())
    {
      if (paramBoolean) {
        playHideAnimation();
      }
      super.setVisibility(4);
    }
  }
  
  public boolean isHidden()
  {
    return getVisibility() == 4;
  }
  
  void onActionDown()
  {
    Object localObject = mBackgroundDrawable;
    if ((localObject instanceof StateListDrawable))
    {
      ((StateListDrawable)localObject).setState(new int[] { 16842910, 16842919 });
      return;
    }
    if (Util.hasLollipop())
    {
      localObject = (RippleDrawable)mBackgroundDrawable;
      ((RippleDrawable)localObject).setState(new int[] { 16842910, 16842919 });
      ((RippleDrawable)localObject).setHotspot(calculateCenterX(), calculateCenterY());
      ((RippleDrawable)localObject).setVisible(true, true);
    }
  }
  
  void onActionUp()
  {
    Object localObject = mBackgroundDrawable;
    if ((localObject instanceof StateListDrawable))
    {
      ((StateListDrawable)localObject).setState(new int[] { 16842910 });
      return;
    }
    if (Util.hasLollipop())
    {
      localObject = (RippleDrawable)mBackgroundDrawable;
      ((RippleDrawable)localObject).setState(new int[] { 16842910 });
      ((RippleDrawable)localObject).setHotspot(calculateCenterX(), calculateCenterY());
      ((RippleDrawable)localObject).setVisible(true, true);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mProgressBarEnabled)
    {
      if (mShowProgressBackground) {
        paramCanvas.drawArc(mProgressCircleBounds, 360.0F, 360.0F, false, mBackgroundPaint);
      }
      int i = 0;
      float f1;
      float f2;
      if (mProgressIndeterminate)
      {
        i = 1;
        long l = SystemClock.uptimeMillis() - mLastTimeAnimated;
        f1 = (float)l * mSpinSpeed / 1000.0F;
        updateProgressLength(l);
        mCurrentProgress += f1;
        f1 = mCurrentProgress;
        if (f1 > 360.0F) {
          mCurrentProgress = (f1 - 360.0F);
        }
        mLastTimeAnimated = SystemClock.uptimeMillis();
        f1 = mCurrentProgress - 90.0F;
        f2 = mBarLength + mBarExtraLength;
        if (isInEditMode())
        {
          f1 = 0.0F;
          f2 = 135.0F;
        }
        paramCanvas.drawArc(mProgressCircleBounds, f1, f2, false, mProgressPaint);
      }
      else
      {
        if (mCurrentProgress != mTargetProgress)
        {
          i = 1;
          f1 = (float)(SystemClock.uptimeMillis() - mLastTimeAnimated) / 1000.0F;
          f1 = mSpinSpeed * f1;
          f2 = mCurrentProgress;
          float f3 = mTargetProgress;
          if (f2 > f3) {
            mCurrentProgress = Math.max(f2 - f1, f3);
          } else {
            mCurrentProgress = Math.min(f2 + f1, f3);
          }
          mLastTimeAnimated = SystemClock.uptimeMillis();
        }
        paramCanvas.drawArc(mProgressCircleBounds, -90.0F, mCurrentProgress, false, mProgressPaint);
      }
      if (i != 0) {
        invalidate();
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(calculateMeasuredHeight(), calculateMeasuredWidth());
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof e))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (e)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    mCurrentProgress = mCurrentProgress;
    mTargetProgress = mTargetProgress;
    mSpinSpeed = mSpinSpeed;
    mProgressWidth = mProgressWidth;
    mProgressColor = mProgressColor;
    mProgressBackgroundColor = mProgressBackgroundColor;
    mShouldProgressIndeterminate = mShouldProgressIndeterminate;
    mShouldSetProgress = mShouldSetProgress;
    mProgress = mProgress;
    mAnimateProgress = mAnimateProgress;
    mShowProgressBackground = mShowProgressBackground;
    mLastTimeAnimated = SystemClock.uptimeMillis();
  }
  
  public Parcelable onSaveInstanceState()
  {
    e localE = new e(super.onSaveInstanceState());
    mCurrentProgress = mCurrentProgress;
    mTargetProgress = mTargetProgress;
    mSpinSpeed = mSpinSpeed;
    mProgressWidth = mProgressWidth;
    mProgressColor = mProgressColor;
    mProgressBackgroundColor = mProgressBackgroundColor;
    boolean bool = mProgressIndeterminate;
    mShouldProgressIndeterminate = bool;
    if ((mProgressBarEnabled) && (mProgress > 0) && (!bool)) {
      bool = true;
    } else {
      bool = false;
    }
    mShouldSetProgress = bool;
    mProgress = mProgress;
    mAnimateProgress = mAnimateProgress;
    mShowProgressBackground = mShowProgressBackground;
    return localE;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    saveButtonOriginalPosition();
    if (mShouldProgressIndeterminate)
    {
      setIndeterminate(true);
      mShouldProgressIndeterminate = false;
    }
    else if (mShouldSetProgress)
    {
      setProgress(mProgress, mAnimateProgress);
      mShouldSetProgress = false;
    }
    else if (mShouldUpdateButtonPosition)
    {
      updateButtonPosition();
      mShouldUpdateButtonPosition = false;
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    setupProgressBounds();
    setupProgressBarPaints();
    updateBackground();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mClickListener != null) && (isEnabled()))
    {
      Label localLabel = (Label)getTag(R.id.fab_label);
      if (localLabel == null) {
        return super.onTouchEvent(paramMotionEvent);
      }
      int i = paramMotionEvent.getAction();
      if (i != 1)
      {
        if (i == 3)
        {
          if (localLabel != null) {
            localLabel.onActionUp();
          }
          onActionUp();
        }
      }
      else
      {
        if (localLabel != null) {
          localLabel.onActionUp();
        }
        onActionUp();
      }
      mGestureDetector.onTouchEvent(paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void playHideAnimation()
  {
    mShowAnimation.cancel();
    startAnimation(mHideAnimation);
  }
  
  void playShowAnimation()
  {
    mHideAnimation.cancel();
    startAnimation(mShowAnimation);
  }
  
  public void setButtonSize(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      throw new IllegalArgumentException("Use @FabSize constants only!");
    }
    if (mFabSize != paramInt)
    {
      mFabSize = paramInt;
      updateBackground();
    }
  }
  
  public void setColorDisabled(int paramInt)
  {
    if (paramInt != mColorDisabled)
    {
      mColorDisabled = paramInt;
      updateBackground();
    }
  }
  
  public void setColorDisabledResId(int paramInt)
  {
    setColorDisabled(getResources().getColor(paramInt));
  }
  
  public void setColorNormal(int paramInt)
  {
    if (mColorNormal != paramInt)
    {
      mColorNormal = paramInt;
      updateBackground();
    }
  }
  
  public void setColorNormalResId(int paramInt)
  {
    setColorNormal(getResources().getColor(paramInt));
  }
  
  public void setColorPressed(int paramInt)
  {
    if (paramInt != mColorPressed)
    {
      mColorPressed = paramInt;
      updateBackground();
    }
  }
  
  public void setColorPressedResId(int paramInt)
  {
    setColorPressed(getResources().getColor(paramInt));
  }
  
  public void setColorRipple(int paramInt)
  {
    if (paramInt != mColorRipple)
    {
      mColorRipple = paramInt;
      updateBackground();
    }
  }
  
  public void setColorRippleResId(int paramInt)
  {
    setColorRipple(getResources().getColor(paramInt));
  }
  
  void setColors(int paramInt1, int paramInt2, int paramInt3)
  {
    mColorNormal = paramInt1;
    mColorPressed = paramInt2;
    mColorRipple = paramInt3;
  }
  
  public void setElevation(float paramFloat)
  {
    if ((Util.hasLollipop()) && (paramFloat > 0.0F))
    {
      super.setElevation(paramFloat);
      if (!isInEditMode())
      {
        mUsingElevation = true;
        mShowShadow = false;
      }
      updateBackground();
    }
  }
  
  public void setElevationCompat(float paramFloat)
  {
    mShadowColor = 637534208;
    mShadowRadius = Math.round(paramFloat / 2.0F);
    mShadowXOffset = 0;
    float f;
    if (mFabSize == 0) {
      f = paramFloat;
    } else {
      f = paramFloat / 2.0F;
    }
    mShadowYOffset = Math.round(f);
    if (Util.hasLollipop())
    {
      super.setElevation(paramFloat);
      mUsingElevationCompat = true;
      mShowShadow = false;
      updateBackground();
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      if (localLayoutParams != null) {
        setLayoutParams(localLayoutParams);
      }
      return;
    }
    mShowShadow = true;
    updateBackground();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    Label localLabel = (Label)getTag(R.id.fab_label);
    if (localLabel != null) {
      localLabel.setEnabled(paramBoolean);
    }
  }
  
  public void setHideAnimation(Animation paramAnimation)
  {
    mHideAnimation = paramAnimation;
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    if (mIcon != paramDrawable)
    {
      mIcon = paramDrawable;
      updateBackground();
    }
  }
  
  public void setImageResource(int paramInt)
  {
    Drawable localDrawable = getResources().getDrawable(paramInt);
    if (mIcon != localDrawable)
    {
      mIcon = localDrawable;
      updateBackground();
    }
  }
  
  public void setIndeterminate(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    try
    {
      mCurrentProgress = 0.0F;
      mProgressBarEnabled = paramBoolean;
      mShouldUpdateButtonPosition = true;
      mProgressIndeterminate = paramBoolean;
      mLastTimeAnimated = SystemClock.uptimeMillis();
      setupProgressBounds();
      updateBackground();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setLabelText(String paramString)
  {
    mLabelText = paramString;
    Label localLabel = getLabelView();
    if (localLabel != null) {
      localLabel.setText(paramString);
    }
  }
  
  public void setLabelTextColor(int paramInt)
  {
    getLabelView().setTextColor(paramInt);
  }
  
  public void setLabelTextColor(ColorStateList paramColorStateList)
  {
    getLabelView().setTextColor(paramColorStateList);
  }
  
  public void setLabelVisibility(int paramInt)
  {
    Label localLabel = getLabelView();
    if (localLabel != null)
    {
      localLabel.setVisibility(paramInt);
      boolean bool;
      if (paramInt == 0) {
        bool = true;
      } else {
        bool = false;
      }
      localLabel.setHandleVisibilityChanges(bool);
    }
  }
  
  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) && (mUsingElevationCompat))
    {
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      leftMargin += getShadowX();
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      topMargin += getShadowY();
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      rightMargin += getShadowX();
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramLayoutParams;
      bottomMargin += getShadowY();
    }
    super.setLayoutParams(paramLayoutParams);
  }
  
  public void setMax(int paramInt)
  {
    try
    {
      mProgressMax = paramInt;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    super.setOnClickListener(paramOnClickListener);
    mClickListener = paramOnClickListener;
    paramOnClickListener = (View)getTag(R.id.fab_label);
    if (paramOnClickListener != null) {
      paramOnClickListener.setOnClickListener(new FloatingActionButton.c(this));
    }
  }
  
  public void setProgress(int paramInt, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        boolean bool = mProgressIndeterminate;
        if (bool) {
          return;
        }
        mProgress = paramInt;
        mAnimateProgress = paramBoolean;
        if (!mButtonPositionSaved)
        {
          mShouldSetProgress = true;
          return;
        }
        mProgressBarEnabled = true;
        mShouldUpdateButtonPosition = true;
        setupProgressBounds();
        saveButtonOriginalPosition();
        updateBackground();
        int i;
        if (paramInt < 0)
        {
          i = 0;
        }
        else
        {
          i = paramInt;
          if (paramInt > mProgressMax) {
            i = mProgressMax;
          }
        }
        f1 = i;
        float f2 = mTargetProgress;
        if (f1 == f2) {
          return;
        }
        if (mProgressMax > 0)
        {
          f1 = i / mProgressMax * 360.0F;
          mTargetProgress = f1;
          mLastTimeAnimated = SystemClock.uptimeMillis();
          if (!paramBoolean) {
            mCurrentProgress = mTargetProgress;
          }
          invalidate();
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      float f1 = 0.0F;
    }
  }
  
  public void setShadowColor(int paramInt)
  {
    if (mShadowColor != paramInt)
    {
      mShadowColor = paramInt;
      updateBackground();
    }
  }
  
  public void setShadowColorResource(int paramInt)
  {
    paramInt = getResources().getColor(paramInt);
    if (mShadowColor != paramInt)
    {
      mShadowColor = paramInt;
      updateBackground();
    }
  }
  
  public void setShadowRadius(float paramFloat)
  {
    mShadowRadius = Util.dpToPx(getContext(), paramFloat);
    requestLayout();
    updateBackground();
  }
  
  public void setShadowRadius(int paramInt)
  {
    paramInt = getResources().getDimensionPixelSize(paramInt);
    if (mShadowRadius != paramInt)
    {
      mShadowRadius = paramInt;
      requestLayout();
      updateBackground();
    }
  }
  
  public void setShadowXOffset(float paramFloat)
  {
    mShadowXOffset = Util.dpToPx(getContext(), paramFloat);
    requestLayout();
    updateBackground();
  }
  
  public void setShadowXOffset(int paramInt)
  {
    paramInt = getResources().getDimensionPixelSize(paramInt);
    if (mShadowXOffset != paramInt)
    {
      mShadowXOffset = paramInt;
      requestLayout();
      updateBackground();
    }
  }
  
  public void setShadowYOffset(float paramFloat)
  {
    mShadowYOffset = Util.dpToPx(getContext(), paramFloat);
    requestLayout();
    updateBackground();
  }
  
  public void setShadowYOffset(int paramInt)
  {
    paramInt = getResources().getDimensionPixelSize(paramInt);
    if (mShadowYOffset != paramInt)
    {
      mShadowYOffset = paramInt;
      requestLayout();
      updateBackground();
    }
  }
  
  public void setShowAnimation(Animation paramAnimation)
  {
    mShowAnimation = paramAnimation;
  }
  
  public void setShowProgressBackground(boolean paramBoolean)
  {
    try
    {
      mShowProgressBackground = paramBoolean;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setShowShadow(boolean paramBoolean)
  {
    if (mShowShadow != paramBoolean)
    {
      mShowShadow = paramBoolean;
      updateBackground();
    }
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    Label localLabel = (Label)getTag(R.id.fab_label);
    if (localLabel != null) {
      localLabel.setVisibility(paramInt);
    }
  }
  
  public void show(boolean paramBoolean)
  {
    if (isHidden())
    {
      if (paramBoolean) {
        playShowAnimation();
      }
      super.setVisibility(0);
    }
  }
  
  void updateBackground()
  {
    boolean bool = hasShadow();
    int j = 0;
    LayerDrawable localLayerDrawable;
    if (bool) {
      localLayerDrawable = new LayerDrawable(new Drawable[] { new f(null), createFillDrawable(), getIconDrawable() });
    } else {
      localLayerDrawable = new LayerDrawable(new Drawable[] { createFillDrawable(), getIconDrawable() });
    }
    int i;
    if (getIconDrawable() != null) {
      i = Math.max(getIconDrawable().getIntrinsicWidth(), getIconDrawable().getIntrinsicHeight());
    } else {
      i = -1;
    }
    int k = getCircleSize();
    if (i <= 0) {
      i = mIconSize;
    }
    int m = (k - i) / 2;
    if (hasShadow()) {
      i = mShadowRadius + Math.abs(mShadowXOffset);
    } else {
      i = 0;
    }
    if (hasShadow()) {
      j = mShadowRadius + Math.abs(mShadowYOffset);
    }
    if (mProgressBarEnabled)
    {
      k = mProgressWidth;
      j += k;
      i += k;
    }
    if (hasShadow()) {
      k = 2;
    } else {
      k = 1;
    }
    localLayerDrawable.setLayerInset(k, i + m, j + m, i + m, j + m);
    setBackgroundCompat(localLayerDrawable);
  }
  
  class b
    extends GestureDetector.SimpleOnGestureListener
  {
    b() {}
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      Label localLabel = (Label)getTag(R.id.fab_label);
      if (localLabel != null) {
        localLabel.onActionDown();
      }
      onActionDown();
      return super.onDown(paramMotionEvent);
    }
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      Label localLabel = (Label)getTag(R.id.fab_label);
      if (localLabel != null) {
        localLabel.onActionUp();
      }
      onActionUp();
      return super.onSingleTapUp(paramMotionEvent);
    }
  }
  
  class d
    extends ShapeDrawable
  {
    private int circleInsetHorizontal;
    private int circleInsetVertical;
    
    private d(Shape paramShape)
    {
      super();
      boolean bool = hasShadow();
      int j = 0;
      if (bool) {
        i = mShadowRadius + Math.abs(mShadowXOffset);
      } else {
        i = 0;
      }
      circleInsetHorizontal = i;
      int i = j;
      if (hasShadow())
      {
        i = mShadowRadius;
        i = Math.abs(mShadowYOffset) + i;
      }
      circleInsetVertical = i;
      if (FloatingActionButton.access$getMProgressBarEnabled(FloatingActionButton.this))
      {
        circleInsetHorizontal += FloatingActionButton.access$getMProgressWidth(FloatingActionButton.this);
        circleInsetVertical += FloatingActionButton.access$getMProgressWidth(FloatingActionButton.this);
      }
    }
    
    public void draw(Canvas paramCanvas)
    {
      setBounds(circleInsetHorizontal, circleInsetVertical, FloatingActionButton.access$getCalculateMeasuredHeight(FloatingActionButton.this) - circleInsetHorizontal, FloatingActionButton.access$getCalculateMeasuredWidth(FloatingActionButton.this) - circleInsetVertical);
      super.draw(paramCanvas);
    }
  }
  
  class e
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<com.github.clans.fab.FloatingActionButton.e> CREATOR = new a();
    boolean mAnimateProgress;
    float mCurrentProgress;
    int mProgress;
    int mProgressBackgroundColor;
    boolean mProgressBarEnabled;
    boolean mProgressBarVisibilityChanged;
    int mProgressColor;
    boolean mProgressIndeterminate;
    int mProgressWidth;
    boolean mShouldProgressIndeterminate;
    boolean mShouldSetProgress;
    boolean mShowProgressBackground;
    float mSpinSpeed;
    float mTargetProgress;
    
    private e()
    {
      super();
      mCurrentProgress = this$1.readFloat();
      mTargetProgress = this$1.readFloat();
      int i = this$1.readInt();
      boolean bool2 = true;
      boolean bool1;
      if (i != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mProgressBarEnabled = bool1;
      mSpinSpeed = this$1.readFloat();
      mProgress = this$1.readInt();
      mProgressWidth = this$1.readInt();
      mProgressColor = this$1.readInt();
      mProgressBackgroundColor = this$1.readInt();
      if (this$1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mProgressBarVisibilityChanged = bool1;
      if (this$1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mProgressIndeterminate = bool1;
      if (this$1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mShouldProgressIndeterminate = bool1;
      if (this$1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mShouldSetProgress = bool1;
      if (this$1.readInt() != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mAnimateProgress = bool1;
      if (this$1.readInt() != 0) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      mShowProgressBackground = bool1;
    }
    
    e()
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    final class a
      implements Parcelable.Creator<com.github.clans.fab.FloatingActionButton.e>
    {
      a() {}
      
      public FloatingActionButton.e createFromParcel(Parcel paramParcel)
      {
        return new FloatingActionButton.e(paramParcel, null);
      }
      
      public FloatingActionButton.e[] newArray(int paramInt)
      {
        return new FloatingActionButton.e[paramInt];
      }
    }
  }
  
  class f
    extends Drawable
  {
    private Paint mErase = new Paint(1);
    private Paint mPaint = new Paint(1);
    private float mRadius;
    
    private f()
    {
      init();
    }
    
    private void init()
    {
      setLayerType(1, null);
      mPaint.setStyle(Paint.Style.FILL);
      mPaint.setColor(FloatingActionButton.access$getMColorNormal(FloatingActionButton.this));
      mErase.setXfermode(FloatingActionButton.access$getPORTER_DUFF_CLEAR());
      if (!isInEditMode())
      {
        Paint localPaint = mPaint;
        FloatingActionButton localFloatingActionButton = FloatingActionButton.this;
        localPaint.setShadowLayer(mShadowRadius, mShadowXOffset, mShadowYOffset, mShadowColor);
      }
      mRadius = (FloatingActionButton.access$getGetCircleSize(FloatingActionButton.this) / 2);
      if ((FloatingActionButton.access$getMProgressBarEnabled(FloatingActionButton.this)) && (FloatingActionButton.access$getMShowProgressBackground(FloatingActionButton.this))) {
        mRadius += FloatingActionButton.access$getMProgressWidth(FloatingActionButton.this);
      }
    }
    
    public void draw(Canvas paramCanvas)
    {
      paramCanvas.drawCircle(FloatingActionButton.access$getCalculateCenterX(FloatingActionButton.this), FloatingActionButton.access$getCalculateCenterY(FloatingActionButton.this), mRadius, mPaint);
      paramCanvas.drawCircle(FloatingActionButton.access$getCalculateCenterX(FloatingActionButton.this), FloatingActionButton.access$getCalculateCenterY(FloatingActionButton.this), mRadius, mErase);
    }
    
    public int getOpacity()
    {
      return 0;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
  }
}
