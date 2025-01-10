package com.github.clans.extract;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

public class Label
  extends TextView
{
  private static final Xfermode PORTER_DUFF_CLEAR = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
  private int a;
  private Drawable mBackgroundDrawable;
  private int mColorNormal;
  private int mColorPressed;
  private int mColorRipple;
  private FloatingActionButton mFab;
  GestureDetector mGestureDetector = new GestureDetector(getContext(), new FloatingActionButton.2(this));
  private boolean mHandleVisibilityChanges = true;
  private Animation mHideAnimation;
  private int mRawHeight;
  private int mRawWidth;
  private int mShadowColor;
  private int mShadowRadius;
  private int mShadowXOffset;
  private int mShadowYOffset;
  private Animation mShowAnimation;
  private boolean mShowShadow = true;
  private boolean mUsingStyle;
  
  public Label(Context paramContext)
  {
    super(paramContext);
  }
  
  private int calculateMeasuredHeight()
  {
    if (mRawHeight == 0) {
      mRawHeight = getMeasuredHeight();
    }
    return getMeasuredHeight() + calculateShadowHeight();
  }
  
  private int calculateMeasuredWidth()
  {
    if (mRawWidth == 0) {
      mRawWidth = getMeasuredWidth();
    }
    return getMeasuredWidth() + calculateShadowWidth();
  }
  
  private Drawable createFillDrawable()
  {
    Object localObject1 = new StateListDrawable();
    Object localObject2 = createRectDrawable(mColorPressed);
    ((StateListDrawable)localObject1).addState(new int[] { 16842919 }, (Drawable)localObject2);
    localObject2 = createRectDrawable(mColorNormal);
    ((StateListDrawable)localObject1).addState(new int[0], (Drawable)localObject2);
    if (Util.hasLollipop())
    {
      localObject2 = new int[0];
      int i = mColorRipple;
      localObject1 = new RippleDrawable(new ColorStateList(new int[][] { localObject2 }, new int[] { i }), (Drawable)localObject1, null);
      setOutlineProvider(new FloatingActionButton.1(this));
      setClipToOutline(true);
      mBackgroundDrawable = ((Drawable)localObject1);
      return (Drawable)localObject1;
    }
    mBackgroundDrawable = ((Drawable)localObject1);
    return localObject1;
  }
  
  private Drawable createRectDrawable(int paramInt)
  {
    int i = a;
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(new float[] { i, i, i, i, i, i, i, i }, null, null));
    localShapeDrawable.getPaint().setColor(paramInt);
    return localShapeDrawable;
  }
  
  private void playHideAnimation()
  {
    if (mShowAnimation != null)
    {
      mHideAnimation.cancel();
      startAnimation(mShowAnimation);
    }
  }
  
  private void playShowAnimation()
  {
    if (mHideAnimation != null)
    {
      mShowAnimation.cancel();
      startAnimation(mHideAnimation);
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
  
  private void setShadow(FloatingActionButton paramFloatingActionButton)
  {
    mShadowColor = paramFloatingActionButton.getShadowColor();
    mShadowRadius = paramFloatingActionButton.getShadowRadius();
    mShadowXOffset = paramFloatingActionButton.getShadowXOffset();
    mShadowYOffset = paramFloatingActionButton.getShadowYOffset();
    mShowShadow = paramFloatingActionButton.hasShadow();
  }
  
  int calculateShadowHeight()
  {
    if (mShowShadow) {
      return mShadowRadius + Math.abs(mShadowYOffset);
    }
    return 0;
  }
  
  int calculateShadowWidth()
  {
    if (mShowShadow) {
      return mShadowRadius + Math.abs(mShadowXOffset);
    }
    return 0;
  }
  
  void hide(boolean paramBoolean)
  {
    if (paramBoolean) {
      playHideAnimation();
    }
    setVisibility(0);
  }
  
  boolean isHandleVisibilityChanges()
  {
    return mHandleVisibilityChanges;
  }
  
  void onActionDown()
  {
    if (mUsingStyle) {
      mBackgroundDrawable = getBackground();
    }
    Object localObject = mBackgroundDrawable;
    if ((localObject instanceof StateListDrawable))
    {
      ((StateListDrawable)localObject).setState(new int[] { 16842919 });
      return;
    }
    if (Util.hasLollipop())
    {
      localObject = mBackgroundDrawable;
      if ((localObject instanceof RippleDrawable))
      {
        localObject = (RippleDrawable)localObject;
        ((RippleDrawable)localObject).setState(new int[] { 16842910, 16842919 });
        ((RippleDrawable)localObject).setHotspot(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        ((RippleDrawable)localObject).setVisible(true, true);
      }
    }
  }
  
  void onActionUp()
  {
    if (mUsingStyle) {
      mBackgroundDrawable = getBackground();
    }
    Object localObject = mBackgroundDrawable;
    if ((localObject instanceof StateListDrawable))
    {
      ((StateListDrawable)localObject).setState(new int[0]);
      return;
    }
    if (Util.hasLollipop())
    {
      localObject = mBackgroundDrawable;
      if ((localObject instanceof RippleDrawable))
      {
        localObject = (RippleDrawable)localObject;
        ((RippleDrawable)localObject).setState(new int[0]);
        ((RippleDrawable)localObject).setHotspot(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        ((RippleDrawable)localObject).setVisible(true, true);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(calculateMeasuredWidth(), calculateMeasuredHeight());
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    FloatingActionButton localFloatingActionButton = mFab;
    if ((localFloatingActionButton != null) && (localFloatingActionButton.getOnClickListener() != null) && (mFab.isEnabled()))
    {
      int i = paramMotionEvent.getAction();
      if (i != 1)
      {
        if (i == 3)
        {
          onActionUp();
          mFab.onActionUp();
        }
      }
      else
      {
        onActionUp();
        mFab.onActionUp();
      }
      mGestureDetector.onTouchEvent(paramMotionEvent);
      return super.onTouchEvent(paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  void setColors(int paramInt1, int paramInt2, int paramInt3)
  {
    mColorNormal = paramInt1;
    mColorPressed = paramInt2;
    mColorRipple = paramInt3;
  }
  
  void setCornerRadius(int paramInt)
  {
    a = paramInt;
  }
  
  void setFab(FloatingActionButton paramFloatingActionButton)
  {
    mFab = paramFloatingActionButton;
    setShadow(paramFloatingActionButton);
  }
  
  void setHandleVisibilityChanges(boolean paramBoolean)
  {
    mHandleVisibilityChanges = paramBoolean;
  }
  
  void setHideAnimation(Animation paramAnimation)
  {
    mHideAnimation = paramAnimation;
  }
  
  void setShowAnimation(Animation paramAnimation)
  {
    mShowAnimation = paramAnimation;
  }
  
  void setShowShadow(boolean paramBoolean)
  {
    mShowShadow = paramBoolean;
  }
  
  void setUsingStyle(boolean paramBoolean)
  {
    mUsingStyle = paramBoolean;
  }
  
  void show(boolean paramBoolean)
  {
    if (paramBoolean) {
      playShowAnimation();
    }
    setVisibility(4);
  }
  
  void updateBackground()
  {
    LayerDrawable localLayerDrawable;
    if (mShowShadow)
    {
      localLayerDrawable = new LayerDrawable(new Drawable[] { new Shadow(null), createFillDrawable() });
      localLayerDrawable.setLayerInset(1, mShadowRadius + Math.abs(mShadowXOffset), mShadowRadius + Math.abs(mShadowYOffset), mShadowRadius + Math.abs(mShadowXOffset), mShadowRadius + Math.abs(mShadowYOffset));
    }
    else
    {
      localLayerDrawable = new LayerDrawable(new Drawable[] { createFillDrawable() });
    }
    setBackgroundCompat(localLayerDrawable);
  }
  
  class Shadow
    extends Drawable
  {
    private Paint mErase = new Paint(1);
    private Paint mPaint = new Paint(1);
    
    private Shadow()
    {
      init();
    }
    
    private void init()
    {
      setLayerType(1, null);
      mPaint.setStyle(Paint.Style.FILL);
      mPaint.setColor(Label.access$getMColorNormal(Label.this));
      mErase.setXfermode(Label.access$getPORTER_DUFF_CLEAR());
      if (!isInEditMode()) {
        mPaint.setShadowLayer(Label.access$getMShadowRadius(Label.this), Label.access$getMShadowXOffset(Label.this), Label.access$getMShadowYOffset(Label.this), Label.access$getMShadowColor(Label.this));
      }
    }
    
    public void draw(Canvas paramCanvas)
    {
      RectF localRectF = new RectF(Label.access$getMShadowRadius(Label.this) + Math.abs(Label.access$getMShadowXOffset(Label.this)), Label.access$getMShadowRadius(Label.this) + Math.abs(Label.access$getMShadowYOffset(Label.this)), Label.access$getMRawWidth(Label.this), Label.access$getMRawHeight(Label.this));
      paramCanvas.drawRoundRect(localRectF, Label.a(Label.this), Label.a(Label.this), mPaint);
      paramCanvas.drawRoundRect(localRectF, Label.a(Label.this), Label.a(Label.this), mErase);
    }
    
    public int getOpacity()
    {
      return 0;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
  }
}
