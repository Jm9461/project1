package android.support.design.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.org.android.R.color;
import org.org.android.ReverseListIterator;
import org.org.android.asm.Attribute;
import org.org.android.asm.Item;
import org.org.android.asm.MaterialRippleLayout.4;
import org.org.android.asm.Plot.a;
import org.org.android.asm.QCandidates.3;
import org.org.android.utils.ThemeUtils;

class FloatingActionButtonImpl
{
  static final TimeInterpolator a = Item.b;
  static final int[] i = new int[0];
  static final int[] p = { 16842919, 16842910 };
  static final int[] q;
  static final int[] r = { 16843623, 16842908, 16842910 };
  static final int[] s;
  static final int[] t = { 16842908, 16842910 };
  private final Label b;
  float config = 1.0F;
  private Attribute entry;
  private ArrayList<Animator.AnimatorListener> flags;
  Attribute h;
  int height = 0;
  private ArrayList<Animator.AnimatorListener> list;
  CircularBorderDrawable mBorderDrawable;
  Drawable mContentBackground;
  float mElevation;
  private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
  float mPressedTranslationZ;
  Drawable mRippleDrawable;
  private float mRotation;
  ShadowDrawableWrapper mShadowDrawable;
  final ShadowViewDelegate mShadowViewDelegate;
  Drawable mShapeDrawable;
  Attribute mSplitView;
  private final RectF mTempDst = new RectF();
  private final RectF mTempSrc = new RectF();
  private final Rect mTmpRect = new Rect();
  float mView;
  Animator mVisibilityAnim;
  private final Matrix matrix = new Matrix();
  final VisibilityAwareImageButton this$0;
  private Attribute w;
  int x;
  
  static
  {
    q = new int[] { 16843623, 16842910 };
    s = new int[] { 16842910 };
  }
  
  FloatingActionButtonImpl(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate)
  {
    this$0 = paramVisibilityAwareImageButton;
    mShadowViewDelegate = paramShadowViewDelegate;
    b = new Label();
    b.a(p, hide(new FloatingActionButtonEclairMr1(this)));
    b.a(r, hide(new CardViewEclairMr1(this)));
    b.a(t, hide(new CardViewEclairMr1(this)));
    b.a(q, hide(new CardViewEclairMr1(this)));
    b.a(s, hide(new WindowDecorActionBar(this)));
    b.a(i, hide(new ActionBar(this)));
    mRotation = this$0.getRotation();
  }
  
  private void draw()
  {
    if (Build.VERSION.SDK_INT == 19) {
      if (mRotation % 90.0F != 0.0F)
      {
        if (this$0.getLayerType() != 1) {
          this$0.setLayerType(1, null);
        }
      }
      else if (this$0.getLayerType() != 0) {
        this$0.setLayerType(0, null);
      }
    }
    Object localObject = mShadowDrawable;
    if (localObject != null) {
      ((ShadowDrawableWrapper)localObject).setRotation(-mRotation);
    }
    localObject = mBorderDrawable;
    if (localObject != null) {
      ((CircularBorderDrawable)localObject).setRotation(-mRotation);
    }
  }
  
  private void ensurePreDrawListener()
  {
    if (mPreDrawListener == null) {
      mPreDrawListener = new CoordinatorLayout.OnPreDrawListener(this);
    }
  }
  
  private ValueAnimator hide(Slider paramSlider)
  {
    ValueAnimator localValueAnimator = new ValueAnimator();
    localValueAnimator.setInterpolator(a);
    localValueAnimator.setDuration(100L);
    localValueAnimator.addListener(paramSlider);
    localValueAnimator.addUpdateListener(paramSlider);
    localValueAnimator.setFloatValues(new float[] { 0.0F, 1.0F });
    return localValueAnimator;
  }
  
  private boolean hide()
  {
    return (ViewCompat.get(this$0)) && (!this$0.isInEditMode());
  }
  
  private Attribute next()
  {
    if (entry == null) {
      entry = Attribute.a(this$0.getContext(), ReverseListIterator.design_fab_show_motion_spec);
    }
    return entry;
  }
  
  private AnimatorSet set(Attribute paramAttribute, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    ArrayList localArrayList = new ArrayList();
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this$0, View.ALPHA, new float[] { paramFloat1 });
    paramAttribute.a("opacity").add(localObjectAnimator);
    localArrayList.add(localObjectAnimator);
    localObjectAnimator = ObjectAnimator.ofFloat(this$0, View.SCALE_X, new float[] { paramFloat2 });
    paramAttribute.a("scale").add(localObjectAnimator);
    localArrayList.add(localObjectAnimator);
    localObjectAnimator = ObjectAnimator.ofFloat(this$0, View.SCALE_Y, new float[] { paramFloat2 });
    paramAttribute.a("scale").add(localObjectAnimator);
    localArrayList.add(localObjectAnimator);
    transform(paramFloat3, matrix);
    localObjectAnimator = ObjectAnimator.ofObject(this$0, new MaterialRippleLayout.4(), new QCandidates.3(), new Matrix[] { new Matrix(matrix) });
    paramAttribute.a("iconScale").add(localObjectAnimator);
    localArrayList.add(localObjectAnimator);
    paramAttribute = new AnimatorSet();
    Plot.a.run(paramAttribute, localArrayList);
    return paramAttribute;
  }
  
  private Attribute set()
  {
    if (w == null) {
      w = Attribute.a(this$0.getContext(), ReverseListIterator.design_fab_hide_motion_spec);
    }
    return w;
  }
  
  private void transform(float paramFloat, Matrix paramMatrix)
  {
    paramMatrix.reset();
    Drawable localDrawable = this$0.getDrawable();
    if ((localDrawable != null) && (x != 0))
    {
      RectF localRectF1 = mTempSrc;
      RectF localRectF2 = mTempDst;
      localRectF1.set(0.0F, 0.0F, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
      int j = x;
      localRectF2.set(0.0F, 0.0F, j, j);
      paramMatrix.setRectToRect(localRectF1, localRectF2, Matrix.ScaleToFit.CENTER);
      j = x;
      paramMatrix.postScale(paramFloat, paramFloat, j / 2.0F, j / 2.0F);
    }
  }
  
  CircularBorderDrawable createBorderDrawable(int paramInt, ColorStateList paramColorStateList)
  {
    Context localContext = this$0.getContext();
    CircularBorderDrawable localCircularBorderDrawable = newCircularDrawable();
    localCircularBorderDrawable.setGradientColors(ContextCompat.getColor(localContext, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(localContext, R.color.design_fab_stroke_end_outer_color));
    localCircularBorderDrawable.setBorderWidth(paramInt);
    localCircularBorderDrawable.setBorderTint(paramColorStateList);
    return localCircularBorderDrawable;
  }
  
  GradientDrawable createShapeDrawable()
  {
    GradientDrawable localGradientDrawable = init();
    localGradientDrawable.setShape(1);
    localGradientDrawable.setColor(-1);
    return localGradientDrawable;
  }
  
  boolean equals()
  {
    if (this$0.getVisibility() != 0)
    {
      if (height == 2) {
        return true;
      }
    }
    else if (height != 1) {
      return true;
    }
    return false;
  }
  
  final Drawable getContentBackground()
  {
    return mContentBackground;
  }
  
  float getElevation()
  {
    return mElevation;
  }
  
  float getPadding()
  {
    return mPressedTranslationZ;
  }
  
  void getPadding(Rect paramRect)
  {
    mShadowDrawable.getPadding(paramRect);
  }
  
  void hide(Animator.AnimatorListener paramAnimatorListener)
  {
    if (flags == null) {
      flags = new ArrayList();
    }
    flags.add(paramAnimatorListener);
  }
  
  GradientDrawable init()
  {
    return new GradientDrawable();
  }
  
  final void init(float paramFloat)
  {
    config = paramFloat;
    Matrix localMatrix = matrix;
    transform(paramFloat, localMatrix);
    this$0.setImageMatrix(localMatrix);
  }
  
  void init(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    ShadowDrawableWrapper localShadowDrawableWrapper = mShadowDrawable;
    if (localShadowDrawableWrapper != null)
    {
      localShadowDrawableWrapper.setShadowSize(paramFloat1, mPressedTranslationZ + paramFloat1);
      updatePadding();
    }
  }
  
  boolean isInside()
  {
    if (this$0.getVisibility() == 0)
    {
      if (height == 1) {
        return true;
      }
    }
    else if (height != 2) {
      return true;
    }
    return false;
  }
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawable();
  }
  
  public void onAnimationEnd(Animator.AnimatorListener paramAnimatorListener)
  {
    ArrayList localArrayList = list;
    if (localArrayList == null) {
      return;
    }
    localArrayList.remove(paramAnimatorListener);
  }
  
  void onAnimationEnd(InternalVisibilityChangedListener paramInternalVisibilityChangedListener, boolean paramBoolean)
  {
    if (isInside()) {
      return;
    }
    Object localObject = mVisibilityAnim;
    if (localObject != null) {
      ((Animator)localObject).cancel();
    }
    if (hide())
    {
      localObject = mSplitView;
      if (localObject == null) {
        localObject = set();
      }
      localObject = set((Attribute)localObject, 0.0F, 0.0F, 0.0F);
      ((Animator)localObject).addListener(new ScrollingTabContainerView.VisibilityAnimListener(this, paramBoolean, paramInternalVisibilityChangedListener));
      paramInternalVisibilityChangedListener = list;
      if (paramInternalVisibilityChangedListener != null)
      {
        paramInternalVisibilityChangedListener = paramInternalVisibilityChangedListener.iterator();
        while (paramInternalVisibilityChangedListener.hasNext()) {
          ((Animator)localObject).addListener((Animator.AnimatorListener)paramInternalVisibilityChangedListener.next());
        }
      }
      ((AnimatorSet)localObject).start();
      return;
    }
    localObject = this$0;
    int j;
    if (paramBoolean) {
      j = 8;
    } else {
      j = 4;
    }
    ((VisibilityAwareImageButton)localObject).internalSetVisibility(j, paramBoolean);
    if (paramInternalVisibilityChangedListener != null) {
      paramInternalVisibilityChangedListener.e();
    }
  }
  
  void onAttachedToWindow()
  {
    if (requirePreDrawListener())
    {
      ensurePreDrawListener();
      this$0.getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);
    }
  }
  
  void onCompatShadowChanged() {}
  
  void onDetachedFromWindow()
  {
    if (mPreDrawListener != null)
    {
      this$0.getViewTreeObserver().removeOnPreDrawListener(mPreDrawListener);
      mPreDrawListener = null;
    }
  }
  
  void onPaddingUpdated(Rect paramRect) {}
  
  void onPreDraw()
  {
    float f = this$0.getRotation();
    if (mRotation != f)
    {
      mRotation = f;
      draw();
    }
  }
  
  void open(Animator.AnimatorListener paramAnimatorListener)
  {
    ArrayList localArrayList = flags;
    if (localArrayList == null) {
      return;
    }
    localArrayList.remove(paramAnimatorListener);
  }
  
  boolean requirePreDrawListener()
  {
    return true;
  }
  
  float setBackgroundDrawable()
  {
    return mView;
  }
  
  final void setBackgroundDrawable(float paramFloat)
  {
    if (mView != paramFloat)
    {
      mView = paramFloat;
      init(mElevation, mView, mPressedTranslationZ);
    }
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList1, PorterDuff.Mode paramMode, ColorStateList paramColorStateList2, int paramInt)
  {
    mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(mShapeDrawable, paramColorStateList1);
    if (paramMode != null) {
      DrawableCompat.setTintMode(mShapeDrawable, paramMode);
    }
    mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(mRippleDrawable, ThemeUtils.createDefaultColorStateList(paramColorStateList2));
    if (paramInt > 0)
    {
      mBorderDrawable = createBorderDrawable(paramInt, paramColorStateList1);
      paramColorStateList1 = new Drawable[3];
      paramColorStateList1[0] = mBorderDrawable;
      paramColorStateList1[1] = mShapeDrawable;
      paramColorStateList1[2] = mRippleDrawable;
    }
    else
    {
      mBorderDrawable = null;
      paramColorStateList1 = new Drawable[2];
      paramColorStateList1[0] = mShapeDrawable;
      paramColorStateList1[1] = mRippleDrawable;
    }
    mContentBackground = new LayerDrawable(paramColorStateList1);
    paramColorStateList1 = this$0.getContext();
    paramMode = mContentBackground;
    float f1 = mShadowViewDelegate.getRadius();
    float f2 = mElevation;
    mShadowDrawable = new ShadowDrawableWrapper(paramColorStateList1, paramMode, f1, f2, f2 + mPressedTranslationZ);
    mShadowDrawable.setAddPaddingForCorners(false);
    mShadowViewDelegate.setBackgroundDrawable(mShadowDrawable);
  }
  
  final void setBackgroundDrawable(Attribute paramAttribute)
  {
    mSplitView = paramAttribute;
  }
  
  void setBackgroundTintList(ColorStateList paramColorStateList)
  {
    Object localObject = mShapeDrawable;
    if (localObject != null) {
      DrawableCompat.setTintList((Drawable)localObject, paramColorStateList);
    }
    localObject = mBorderDrawable;
    if (localObject != null) {
      ((CircularBorderDrawable)localObject).setBorderTint(paramColorStateList);
    }
  }
  
  void setBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    Drawable localDrawable = mShapeDrawable;
    if (localDrawable != null) {
      DrawableCompat.setTintMode(localDrawable, paramMode);
    }
  }
  
  final Attribute setElevation()
  {
    return mSplitView;
  }
  
  final void setElevation(float paramFloat)
  {
    if (mElevation != paramFloat)
    {
      mElevation = paramFloat;
      init(mElevation, mView, mPressedTranslationZ);
    }
  }
  
  final void setElevation(Attribute paramAttribute)
  {
    h = paramAttribute;
  }
  
  void setPressedTranslationZ()
  {
    b.b();
  }
  
  final void setPressedTranslationZ(float paramFloat)
  {
    if (mPressedTranslationZ != paramFloat)
    {
      mPressedTranslationZ = paramFloat;
      init(mElevation, mView, mPressedTranslationZ);
    }
  }
  
  final Attribute setRippleColor()
  {
    return h;
  }
  
  void setRippleColor(ColorStateList paramColorStateList)
  {
    Drawable localDrawable = mRippleDrawable;
    if (localDrawable != null) {
      DrawableCompat.setTintList(localDrawable, ThemeUtils.createDefaultColorStateList(paramColorStateList));
    }
  }
  
  final void show()
  {
    init(config);
  }
  
  final void show(int paramInt)
  {
    if (x != paramInt)
    {
      x = paramInt;
      show();
    }
  }
  
  public void show(Animator.AnimatorListener paramAnimatorListener)
  {
    if (list == null) {
      list = new ArrayList();
    }
    list.add(paramAnimatorListener);
  }
  
  void show(InternalVisibilityChangedListener paramInternalVisibilityChangedListener, boolean paramBoolean)
  {
    if (equals()) {
      return;
    }
    Object localObject = mVisibilityAnim;
    if (localObject != null) {
      ((Animator)localObject).cancel();
    }
    if (hide())
    {
      if (this$0.getVisibility() != 0)
      {
        this$0.setAlpha(0.0F);
        this$0.setScaleY(0.0F);
        this$0.setScaleX(0.0F);
        init(0.0F);
      }
      localObject = h;
      if (localObject == null) {
        localObject = next();
      }
      localObject = set((Attribute)localObject, 1.0F, 1.0F, 1.0F);
      ((Animator)localObject).addListener(new FloatingActionButtonHoneycombMr1.2(this, paramBoolean, paramInternalVisibilityChangedListener));
      paramInternalVisibilityChangedListener = flags;
      if (paramInternalVisibilityChangedListener != null)
      {
        paramInternalVisibilityChangedListener = paramInternalVisibilityChangedListener.iterator();
        while (paramInternalVisibilityChangedListener.hasNext()) {
          ((Animator)localObject).addListener((Animator.AnimatorListener)paramInternalVisibilityChangedListener.next());
        }
      }
      ((AnimatorSet)localObject).start();
      return;
    }
    this$0.internalSetVisibility(0, paramBoolean);
    this$0.setAlpha(1.0F);
    this$0.setScaleY(1.0F);
    this$0.setScaleX(1.0F);
    init(1.0F);
    if (paramInternalVisibilityChangedListener != null) {
      paramInternalVisibilityChangedListener.onShown();
    }
  }
  
  void show(int[] paramArrayOfInt)
  {
    b.setState(paramArrayOfInt);
  }
  
  final void updatePadding()
  {
    Rect localRect = mTmpRect;
    getPadding(localRect);
    onPaddingUpdated(localRect);
    mShadowViewDelegate.setShadowPadding(left, top, right, bottom);
  }
  
  abstract interface InternalVisibilityChangedListener
  {
    public abstract void e();
    
    public abstract void onShown();
  }
}
