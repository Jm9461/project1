package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.internal.g;
import android.support.v4.view.Artist;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import org.org.android.R.attr;
import org.org.android.R.dimen;
import org.org.android.R.style;
import org.org.android.R.styleable;
import org.org.android.asm.Attribute;
import org.org.android.bearer.AbsSpinnerCompat.SavedState;
import org.org.android.gcm.ResourcesCompat;

@CoordinatorLayout.d(Behavior.class)
public class FloatingActionButton
  extends VisibilityAwareImageButton
  implements TintableBackgroundView, android.support.v4.widget.View, org.org.android.widgets.Animator.AnimatorListener
{
  private final org.org.android.widgets.f d;
  private ColorStateList mBackgroundTint;
  private PorterDuff.Mode mBackgroundTintMode;
  private int mBorderWidth;
  private int mColor;
  private PorterDuff.Mode mComment;
  boolean mCompatPadding;
  private int mContentPadding;
  private final AppCompatImageHelper mImageHelper;
  private FloatingActionButtonImpl mImpl;
  private ColorStateList mRippleColor;
  private int mSize;
  private int mState;
  private ColorStateList mTextColor;
  private final Rect position = new Rect();
  final Rect this$0 = new Rect();
  
  public FloatingActionButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.floatingActionButtonStyle);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = g.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.FloatingActionButton, paramInt, R.style.Widget_Design_FloatingActionButton, new int[0]);
    mBackgroundTint = ResourcesCompat.getColorStateList(paramContext, localTypedArray, R.styleable.FloatingActionButton_backgroundTint);
    mBackgroundTintMode = android.support.design.internal.DrawableCompat.parseTintMode(localTypedArray.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
    mRippleColor = ResourcesCompat.getColorStateList(paramContext, localTypedArray, R.styleable.FloatingActionButton_rippleColor);
    mSize = localTypedArray.getInt(R.styleable.FloatingActionButton_fabSize, -1);
    mColor = localTypedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
    mBorderWidth = localTypedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
    float f1 = localTypedArray.getDimension(R.styleable.FloatingActionButton_elevation, 0.0F);
    float f2 = localTypedArray.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0F);
    float f3 = localTypedArray.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
    mCompatPadding = localTypedArray.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
    mState = localTypedArray.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0);
    Attribute localAttribute = Attribute.a(paramContext, localTypedArray, R.styleable.FloatingActionButton_showMotionSpec);
    paramContext = Attribute.a(paramContext, localTypedArray, R.styleable.FloatingActionButton_hideMotionSpec);
    localTypedArray.recycle();
    mImageHelper = new AppCompatImageHelper(this);
    mImageHelper.loadFromAttributes(paramAttributeSet, paramInt);
    d = new org.org.android.widgets.f(this);
    getImpl().setBackgroundDrawable(mBackgroundTint, mBackgroundTintMode, mRippleColor, mBorderWidth);
    getImpl().setElevation(f1);
    getImpl().setBackgroundDrawable(f2);
    getImpl().setPressedTranslationZ(f3);
    getImpl().show(mState);
    getImpl().setElevation(localAttribute);
    getImpl().setBackgroundDrawable(paramContext);
    setScaleType(ImageView.ScaleType.MATRIX);
  }
  
  private FloatingActionButtonImpl createImpl()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new FloatingActionButtonLollipop(this, new c());
    }
    return new FloatingActionButtonImpl(this, new c());
  }
  
  private FloatingActionButtonImpl getImpl()
  {
    if (mImpl == null) {
      mImpl = createImpl();
    }
    return mImpl;
  }
  
  private static int getLength(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (i != Integer.MIN_VALUE)
    {
      if (i != 0)
      {
        if (i == 1073741824) {
          return paramInt2;
        }
        throw new IllegalArgumentException();
      }
      return paramInt1;
    }
    return Math.min(paramInt1, paramInt2);
  }
  
  private int init(int paramInt)
  {
    int i = mColor;
    if (i != 0) {
      return i;
    }
    Resources localResources = getResources();
    if (paramInt != -1)
    {
      if (paramInt != 1) {
        return localResources.getDimensionPixelSize(R.dimen.design_fab_size_normal);
      }
      return localResources.getDimensionPixelSize(R.dimen.design_fab_size_mini);
    }
    if (Math.max(getConfigurationscreenWidthDp, getConfigurationscreenHeightDp) < 470) {
      return init(1);
    }
    return init(0);
  }
  
  private void init()
  {
    Drawable localDrawable = getDrawable();
    if (localDrawable == null) {
      return;
    }
    Object localObject = mTextColor;
    if (localObject == null)
    {
      android.support.v4.graphics.drawable.DrawableCompat.update(localDrawable);
      return;
    }
    int i = ((ColorStateList)localObject).getColorForState(getDrawableState(), 0);
    PorterDuff.Mode localMode = mComment;
    localObject = localMode;
    if (localMode == null) {
      localObject = PorterDuff.Mode.SRC_IN;
    }
    localDrawable.mutate().setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(i, (PorterDuff.Mode)localObject));
  }
  
  private void setBounds(Rect paramRect)
  {
    int i = left;
    Rect localRect = this$0;
    left = (i + left);
    top += top;
    right -= right;
    bottom -= bottom;
  }
  
  private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(final b paramB)
  {
    if (paramB == null) {
      return null;
    }
    return new a(paramB);
  }
  
  public boolean a()
  {
    return d.a();
  }
  
  public boolean add(Rect paramRect)
  {
    if (ViewCompat.get(this))
    {
      paramRect.set(0, 0, getWidth(), getHeight());
      setBounds(paramRect);
      return true;
    }
    return false;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    getImpl().show(getDrawableState());
  }
  
  public ColorStateList getBackgroundTintList()
  {
    return mBackgroundTint;
  }
  
  public PorterDuff.Mode getBackgroundTintMode()
  {
    return mBackgroundTintMode;
  }
  
  public float getCompatElevation()
  {
    return getImpl().getElevation();
  }
  
  public float getCompatHoveredFocusedTranslationZ()
  {
    return getImpl().setBackgroundDrawable();
  }
  
  public float getCompatPressedTranslationZ()
  {
    return getImpl().getPadding();
  }
  
  public Drawable getContentBackground()
  {
    return getImpl().getContentBackground();
  }
  
  public int getCustomSize()
  {
    return mColor;
  }
  
  public int getExpandedComponentIdHint()
  {
    return d.c();
  }
  
  public Attribute getHideMotionSpec()
  {
    return getImpl().setElevation();
  }
  
  public int getRippleColor()
  {
    ColorStateList localColorStateList = mRippleColor;
    if (localColorStateList != null) {
      return localColorStateList.getDefaultColor();
    }
    return 0;
  }
  
  public ColorStateList getRippleColorStateList()
  {
    return mRippleColor;
  }
  
  public Attribute getShowMotionSpec()
  {
    return getImpl().setRippleColor();
  }
  
  public int getSize()
  {
    return mSize;
  }
  
  int getSizeDimension()
  {
    return init(mSize);
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    return getBackgroundTintList();
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    return getBackgroundTintMode();
  }
  
  public ColorStateList getSupportImageTintList()
  {
    return mTextColor;
  }
  
  public PorterDuff.Mode getSupportImageTintMode()
  {
    return mComment;
  }
  
  public boolean getUseCompatPadding()
  {
    return mCompatPadding;
  }
  
  public void hide(android.animation.Animator.AnimatorListener paramAnimatorListener)
  {
    getImpl().hide(paramAnimatorListener);
  }
  
  void hide(b paramB, boolean paramBoolean)
  {
    getImpl().onAnimationEnd(wrapOnVisibilityChangedListener(paramB), paramBoolean);
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    getImpl().setPressedTranslationZ();
  }
  
  public void layout(Rect paramRect)
  {
    paramRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    setBounds(paramRect);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    getImpl().onAttachedToWindow();
  }
  
  public void onAttachedToWindow(android.animation.Animator.AnimatorListener paramAnimatorListener)
  {
    getImpl().open(paramAnimatorListener);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    getImpl().onDetachedFromWindow();
  }
  
  public void onHidden(android.animation.Animator.AnimatorListener paramAnimatorListener)
  {
    getImpl().show(paramAnimatorListener);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getSizeDimension();
    mContentPadding = ((i - mState) / 2);
    getImpl().updatePadding();
    paramInt1 = Math.min(getLength(i, paramInt1), getLength(i, paramInt2));
    Rect localRect = this$0;
    setMeasuredDimension(left + paramInt1 + right, top + paramInt1 + bottom);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof AbsSpinnerCompat.SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (AbsSpinnerCompat.SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    d.d((Bundle)b.get("expandableWidgetHelper"));
  }
  
  protected Parcelable onSaveInstanceState()
  {
    AbsSpinnerCompat.SavedState localSavedState = new AbsSpinnerCompat.SavedState(super.onSaveInstanceState());
    b.put("expandableWidgetHelper", d.d());
    return localSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (add(position)) && (!position.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()))) {
      return false;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundResource(int paramInt)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (mBackgroundTint != paramColorStateList)
    {
      mBackgroundTint = paramColorStateList;
      getImpl().setBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (mBackgroundTintMode != paramMode)
    {
      mBackgroundTintMode = paramMode;
      getImpl().setBackgroundTintMode(paramMode);
    }
  }
  
  public void setCompatElevation(float paramFloat)
  {
    getImpl().setElevation(paramFloat);
  }
  
  public void setCompatElevationResource(int paramInt)
  {
    setCompatElevation(getResources().getDimension(paramInt));
  }
  
  public void setCompatHoveredFocusedTranslationZ(float paramFloat)
  {
    getImpl().setBackgroundDrawable(paramFloat);
  }
  
  public void setCompatHoveredFocusedTranslationZResource(int paramInt)
  {
    setCompatHoveredFocusedTranslationZ(getResources().getDimension(paramInt));
  }
  
  public void setCompatPressedTranslationZ(float paramFloat)
  {
    getImpl().setPressedTranslationZ(paramFloat);
  }
  
  public void setCompatPressedTranslationZResource(int paramInt)
  {
    setCompatPressedTranslationZ(getResources().getDimension(paramInt));
  }
  
  public void setCustomSize(int paramInt)
  {
    if (paramInt >= 0)
    {
      mColor = paramInt;
      return;
    }
    throw new IllegalArgumentException("Custom size must be non-negative");
  }
  
  public void setExpandedComponentIdHint(int paramInt)
  {
    d.c(paramInt);
  }
  
  public void setHideMotionSpec(Attribute paramAttribute)
  {
    getImpl().setBackgroundDrawable(paramAttribute);
  }
  
  public void setHideMotionSpecResource(int paramInt)
  {
    setHideMotionSpec(Attribute.a(getContext(), paramInt));
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    getImpl().show();
  }
  
  public void setImageResource(int paramInt)
  {
    mImageHelper.setImageResource(paramInt);
  }
  
  public void setRippleColor(int paramInt)
  {
    setRippleColor(ColorStateList.valueOf(paramInt));
  }
  
  public void setRippleColor(ColorStateList paramColorStateList)
  {
    if (mRippleColor != paramColorStateList)
    {
      mRippleColor = paramColorStateList;
      getImpl().setRippleColor(mRippleColor);
    }
  }
  
  public void setShowMotionSpec(Attribute paramAttribute)
  {
    getImpl().setElevation(paramAttribute);
  }
  
  public void setShowMotionSpecResource(int paramInt)
  {
    setShowMotionSpec(Attribute.a(getContext(), paramInt));
  }
  
  public void setSize(int paramInt)
  {
    mColor = 0;
    if (paramInt != mSize)
    {
      mSize = paramInt;
      requestLayout();
    }
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    setBackgroundTintList(paramColorStateList);
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    setBackgroundTintMode(paramMode);
  }
  
  public void setSupportImageTintList(ColorStateList paramColorStateList)
  {
    if (mTextColor != paramColorStateList)
    {
      mTextColor = paramColorStateList;
      init();
    }
  }
  
  public void setSupportImageTintMode(PorterDuff.Mode paramMode)
  {
    if (mComment != paramMode)
    {
      mComment = paramMode;
      init();
    }
  }
  
  public void setUseCompatPadding(boolean paramBoolean)
  {
    if (mCompatPadding != paramBoolean)
    {
      mCompatPadding = paramBoolean;
      getImpl().onCompatShadowChanged();
    }
  }
  
  public void show(android.animation.Animator.AnimatorListener paramAnimatorListener)
  {
    getImpl().onAnimationEnd(paramAnimatorListener);
  }
  
  void show(b paramB, boolean paramBoolean)
  {
    getImpl().show(wrapOnVisibilityChangedListener(paramB), paramBoolean);
  }
  
  public boolean show()
  {
    return getImpl().equals();
  }
  
  protected static class BaseBehavior<T extends FloatingActionButton>
    extends CoordinatorLayout.c<T>
  {
    private FloatingActionButton.b listView;
    private boolean mNotificationsEnabled;
    private Rect mTmpRect;
    
    public BaseBehavior()
    {
      mNotificationsEnabled = true;
    }
    
    public BaseBehavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionButton_Behavior_Layout);
      mNotificationsEnabled = paramContext.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
      paramContext.recycle();
    }
    
    private void offsetIfNeeded(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton)
    {
      Rect localRect = this$0;
      if ((localRect != null) && (localRect.centerX() > 0) && (localRect.centerY() > 0))
      {
        CoordinatorLayout.f localF = (CoordinatorLayout.f)paramFloatingActionButton.getLayoutParams();
        int j = 0;
        int i = 0;
        if (paramFloatingActionButton.getRight() >= paramCoordinatorLayout.getWidth() - rightMargin) {
          i = right;
        } else if (paramFloatingActionButton.getLeft() <= leftMargin) {
          i = -left;
        }
        if (paramFloatingActionButton.getBottom() >= paramCoordinatorLayout.getHeight() - bottomMargin) {
          j = bottom;
        } else if (paramFloatingActionButton.getTop() <= topMargin) {
          j = -top;
        }
        if (j != 0) {
          ViewCompat.offsetTopAndBottom(paramFloatingActionButton, j);
        }
        if (i != 0) {
          ViewCompat.offsetLeftAndRight(paramFloatingActionButton, i);
        }
      }
    }
    
    private boolean onLayoutChild(android.view.View paramView, FloatingActionButton paramFloatingActionButton)
    {
      if (!updateFabVisibility(paramView, paramFloatingActionButton)) {
        return false;
      }
      CoordinatorLayout.f localF = (CoordinatorLayout.f)paramFloatingActionButton.getLayoutParams();
      if (paramView.getTop() < paramFloatingActionButton.getHeight() / 2 + topMargin) {
        paramFloatingActionButton.hide(listView, false);
      } else {
        paramFloatingActionButton.show(listView, false);
      }
      return true;
    }
    
    private boolean updateFabVisibility(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, FloatingActionButton paramFloatingActionButton)
    {
      if (!updateFabVisibility(paramAppBarLayout, paramFloatingActionButton)) {
        return false;
      }
      if (mTmpRect == null) {
        mTmpRect = new Rect();
      }
      Rect localRect = mTmpRect;
      ViewGroupUtilsHoneycomb.getDescendantRect(paramCoordinatorLayout, paramAppBarLayout, localRect);
      if (bottom <= paramAppBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
        paramFloatingActionButton.hide(listView, false);
      } else {
        paramFloatingActionButton.show(listView, false);
      }
      return true;
    }
    
    private boolean updateFabVisibility(android.view.View paramView, FloatingActionButton paramFloatingActionButton)
    {
      CoordinatorLayout.f localF = (CoordinatorLayout.f)paramFloatingActionButton.getLayoutParams();
      if (!mNotificationsEnabled) {
        return false;
      }
      if (localF.getAnchorId() != paramView.getId()) {
        return false;
      }
      return paramFloatingActionButton.getUserSetVisibility() == 0;
    }
    
    private static boolean updateOffset(android.view.View paramView)
    {
      paramView = paramView.getLayoutParams();
      if ((paramView instanceof CoordinatorLayout.f)) {
        return ((CoordinatorLayout.f)paramView).getBehavior() instanceof BottomSheetBehavior;
      }
      return false;
    }
    
    public void a(CoordinatorLayout.f paramF)
    {
      if (x == 0) {
        x = 80;
      }
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, Rect paramRect)
    {
      paramCoordinatorLayout = this$0;
      paramRect.set(paramFloatingActionButton.getLeft() + left, paramFloatingActionButton.getTop() + top, paramFloatingActionButton.getRight() - right, paramFloatingActionButton.getBottom() - bottom);
      return true;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, int paramInt)
    {
      List localList = paramCoordinatorLayout.getDependencies(paramFloatingActionButton);
      int i = 0;
      int j = localList.size();
      while (i < j)
      {
        android.view.View localView = (android.view.View)localList.get(i);
        if ((localView instanceof AppBarLayout) ? !updateFabVisibility(paramCoordinatorLayout, (AppBarLayout)localView, paramFloatingActionButton) : (updateOffset(localView)) && (onLayoutChild(localView, paramFloatingActionButton))) {
          break;
        }
        i += 1;
      }
      paramCoordinatorLayout.onLayoutChild(paramFloatingActionButton, paramInt);
      offsetIfNeeded(paramCoordinatorLayout, paramFloatingActionButton);
      return true;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, android.view.View paramView)
    {
      if ((paramView instanceof AppBarLayout)) {
        updateFabVisibility(paramCoordinatorLayout, (AppBarLayout)paramView, paramFloatingActionButton);
      } else if (updateOffset(paramView)) {
        onLayoutChild(paramView, paramFloatingActionButton);
      }
      return false;
    }
  }
  
  public static class Behavior
    extends FloatingActionButton.BaseBehavior<FloatingActionButton>
  {
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
  }
  
  class a
    implements FloatingActionButtonImpl.InternalVisibilityChangedListener
  {
    a(FloatingActionButton.b paramB) {}
    
    public void e()
    {
      paramB.b(FloatingActionButton.this);
    }
    
    public void onShown()
    {
      paramB.subtract(FloatingActionButton.this);
    }
  }
  
  public static abstract class b
  {
    public abstract void b(FloatingActionButton paramFloatingActionButton);
    
    public abstract void subtract(FloatingActionButton paramFloatingActionButton);
  }
  
  private class c
    implements ShadowViewDelegate
  {
    c() {}
    
    public float getRadius()
    {
      return getSizeDimension() / 2.0F;
    }
    
    public void setBackgroundDrawable(Drawable paramDrawable)
    {
      FloatingActionButton.access$getSetBackgroundDrawable(FloatingActionButton.this, paramDrawable);
    }
    
    public boolean setBackgroundDrawable()
    {
      return mCompatPadding;
    }
    
    public void setShadowPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this$0.set(paramInt1, paramInt2, paramInt3, paramInt4);
      FloatingActionButton localFloatingActionButton = FloatingActionButton.this;
      localFloatingActionButton.setPadding(FloatingActionButton.access$getMContentPadding(localFloatingActionButton) + paramInt1, FloatingActionButton.access$getMContentPadding(FloatingActionButton.this) + paramInt2, FloatingActionButton.access$getMContentPadding(FloatingActionButton.this) + paramInt3, FloatingActionButton.access$getMContentPadding(FloatingActionButton.this) + paramInt4);
    }
  }
}
