package android.support.design.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.internal.g;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.Artist;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.List;
import org.org.android.R.attr;
import org.org.android.R.color;
import org.org.android.R.dimen;
import org.org.android.R.id;
import org.org.android.R.layout;
import org.org.android.R.string;
import org.org.android.R.style;
import org.org.android.R.styleable;
import org.org.android.asm.Item;

public class TextInputLayout
  extends LinearLayout
{
  private GradientDrawable a;
  private boolean c;
  private PorterDuff.Mode e;
  private int flags;
  private Drawable h;
  private final int height;
  private final int i;
  private boolean isLoading;
  private final int j;
  private boolean l;
  private final int left;
  private boolean list;
  private boolean loading;
  private ColorStateList m;
  private ValueAnimator mAnimator;
  private boolean mCancellingAnimations;
  final CollapsingTextHelper mCollapsingTextHelper = new CollapsingTextHelper(this);
  private final int mColor;
  private final FrameLayout mContent;
  private Drawable mContext;
  boolean mCounterEnabled;
  private int mCounterMaxLength;
  private final int mCounterOverflowTextAppearance;
  private boolean mCounterOverflowed;
  private final int mCounterTextAppearance;
  private android.widget.TextView mCounterView;
  private Drawable mData;
  private ColorStateList mDefaultTextColor;
  private Drawable mDrawable;
  android.widget.EditText mEditText;
  private boolean mError;
  private boolean mErrorShown;
  private ColorStateList mFocusedTextColor;
  private boolean mHasReconstructedEditTextBackground;
  private CharSequence mHint;
  private boolean mHintAnimationEnabled;
  private boolean mHintEnabled;
  private final f mMenu = new f(this);
  private float mMiniKeyboard;
  private float mMiniKeyboardSlideAllowance;
  private final RectF mTarget = new RectF();
  private CharSequence mTitle;
  private final Rect mTmpRect = new Rect();
  private float mTopPadding;
  private Typeface mTypeface;
  private float mVerticalGap;
  private int n;
  private CharSequence name;
  private int o;
  private final int offset;
  private int r;
  private final int size;
  private CheckableImageButton this$0;
  private int type;
  private final int value;
  
  public TextInputLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.textInputStyle);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setOrientation(1);
    setWillNotDraw(false);
    setAddStatesFromChildren(true);
    mContent = new FrameLayout(paramContext);
    mContent.setAddStatesFromChildren(true);
    addView(mContent);
    mCollapsingTextHelper.setTextSizeInterpolator(Item.FAST_OUT_SLOW_IN_INTERPOLATOR);
    mCollapsingTextHelper.setPositionInterpolator(Item.FAST_OUT_SLOW_IN_INTERPOLATOR);
    mCollapsingTextHelper.setCollapsedTextGravity(8388659);
    paramAttributeSet = g.obtainStyledAttrsFromThemeAttr(paramContext, paramAttributeSet, R.styleable.TextInputLayout, paramInt, R.style.Widget_Design_TextInputLayout, new int[0]);
    mHintEnabled = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
    setHint(paramAttributeSet.getText(R.styleable.TextInputLayout_android_hint));
    mHintAnimationEnabled = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
    mColor = paramContext.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_bottom_offset);
    left = paramContext.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
    height = paramAttributeSet.getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
    mMiniKeyboardSlideAllowance = paramAttributeSet.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, 0.0F);
    mMiniKeyboard = paramAttributeSet.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, 0.0F);
    mVerticalGap = paramAttributeSet.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, 0.0F);
    mTopPadding = paramAttributeSet.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, 0.0F);
    o = paramAttributeSet.getColor(R.styleable.TextInputLayout_boxBackgroundColor, 0);
    n = paramAttributeSet.getColor(R.styleable.TextInputLayout_boxStrokeColor, 0);
    j = paramContext.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default);
    i = paramContext.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused);
    flags = j;
    setBoxBackgroundMode(paramAttributeSet.getInt(R.styleable.TextInputLayout_boxBackgroundMode, 0));
    if (paramAttributeSet.hasValue(R.styleable.TextInputLayout_android_textColorHint))
    {
      ColorStateList localColorStateList = paramAttributeSet.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
      mFocusedTextColor = localColorStateList;
      mDefaultTextColor = localColorStateList;
    }
    value = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_default_box_stroke_color);
    offset = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_disabled_color);
    size = ContextCompat.getColor(paramContext, R.color.mtrl_textinput_hovered_box_stroke_color);
    if (paramAttributeSet.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
      setHintTextAppearance(paramAttributeSet.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
    }
    paramInt = paramAttributeSet.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
    boolean bool1 = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
    int k = paramAttributeSet.getResourceId(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
    boolean bool2 = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_helperTextEnabled, false);
    paramContext = paramAttributeSet.getText(R.styleable.TextInputLayout_helperText);
    boolean bool3 = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
    setCounterMaxLength(paramAttributeSet.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
    mCounterTextAppearance = paramAttributeSet.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
    mCounterOverflowTextAppearance = paramAttributeSet.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
    list = paramAttributeSet.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false);
    mDrawable = paramAttributeSet.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
    mTitle = paramAttributeSet.getText(R.styleable.TextInputLayout_passwordToggleContentDescription);
    if (paramAttributeSet.hasValue(R.styleable.TextInputLayout_passwordToggleTint))
    {
      l = true;
      m = paramAttributeSet.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
    }
    if (paramAttributeSet.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode))
    {
      c = true;
      e = android.support.design.internal.DrawableCompat.parseTintMode(paramAttributeSet.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
    }
    paramAttributeSet.recycle();
    setHelperTextEnabled(bool2);
    setHelperText(paramContext);
    setHelperTextTextAppearance(k);
    setErrorEnabled(bool1);
    setErrorTextAppearance(paramInt);
    setCounterEnabled(bool3);
    setIcon();
    ViewCompat.add(this, 2);
  }
  
  private void a()
  {
    if (a == null) {
      return;
    }
    updateLabelVisibility();
    android.widget.EditText localEditText = mEditText;
    if ((localEditText != null) && (r == 2))
    {
      if (localEditText.getBackground() != null) {
        h = mEditText.getBackground();
      }
      ViewCompat.setBackgroundDrawable(mEditText, null);
    }
    localEditText = mEditText;
    if ((localEditText != null) && (r == 1))
    {
      Drawable localDrawable = h;
      if (localDrawable != null) {
        ViewCompat.setBackgroundDrawable(localEditText, localDrawable);
      }
    }
    int k = flags;
    if (k > -1)
    {
      int i1 = type;
      if (i1 != 0) {
        a.setStroke(k, i1);
      }
    }
    a.setCornerRadii(getCornerRadiiAsArray());
    a.setColor(o);
    invalidate();
  }
  
  private static void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    int k = 0;
    int i1 = paramViewGroup.getChildCount();
    while (k < i1)
    {
      View localView = paramViewGroup.getChildAt(k);
      localView.setEnabled(paramBoolean);
      if ((localView instanceof ViewGroup)) {
        a((ViewGroup)localView, paramBoolean);
      }
      k += 1;
    }
  }
  
  private void animateToExpansionFraction()
  {
    if (cancel()) {
      ((ClassWriter)a).c();
    }
  }
  
  private boolean cancel()
  {
    return (mHintEnabled) && (!TextUtils.isEmpty(mHint)) && ((a instanceof ClassWriter));
  }
  
  private void collapseHint(boolean paramBoolean)
  {
    ValueAnimator localValueAnimator = mAnimator;
    if ((localValueAnimator != null) && (localValueAnimator.isRunning())) {
      mAnimator.cancel();
    }
    if ((paramBoolean) && (mHintAnimationEnabled)) {
      animateToExpansionFraction(1.0F);
    } else {
      mCollapsingTextHelper.setExpansionFraction(1.0F);
    }
    mError = false;
    if (cancel()) {
      onLayout();
    }
  }
  
  private int draw()
  {
    if (!mHintEnabled) {
      return 0;
    }
    int k = r;
    if ((k != 0) && (k != 1))
    {
      if (k != 2) {
        return 0;
      }
      return (int)(mCollapsingTextHelper.draw() / 2.0F);
    }
    return (int)mCollapsingTextHelper.draw();
  }
  
  private void draw(RectF paramRectF)
  {
    float f = left;
    int k = left;
    left = (f - k);
    top -= k;
    right += k;
    bottom += k;
  }
  
  private int drawText()
  {
    int k = r;
    if (k != 1)
    {
      if (k != 2) {
        return getPaddingTop();
      }
      return getBoxBackgroundgetBoundstop - draw();
    }
    return getBoxBackgroundgetBoundstop + height;
  }
  
  private void ensureBackgroundDrawableStateWorkaround()
  {
    int k = Build.VERSION.SDK_INT;
    if ((k != 21) && (k != 22)) {
      return;
    }
    Drawable localDrawable1 = mEditText.getBackground();
    if (localDrawable1 == null) {
      return;
    }
    if (!mHasReconstructedEditTextBackground)
    {
      Drawable localDrawable2 = localDrawable1.getConstantState().newDrawable();
      if ((localDrawable1 instanceof DrawableContainer)) {
        mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)localDrawable1, localDrawable2.getConstantState());
      }
      if (!mHasReconstructedEditTextBackground)
      {
        ViewCompat.setBackgroundDrawable(mEditText, localDrawable2);
        mHasReconstructedEditTextBackground = true;
        setHint();
      }
    }
  }
  
  private void expandHint(boolean paramBoolean)
  {
    ValueAnimator localValueAnimator = mAnimator;
    if ((localValueAnimator != null) && (localValueAnimator.isRunning())) {
      mAnimator.cancel();
    }
    if ((paramBoolean) && (mHintAnimationEnabled)) {
      animateToExpansionFraction(0.0F);
    } else {
      mCollapsingTextHelper.setExpansionFraction(0.0F);
    }
    if ((cancel()) && (((ClassWriter)a).a())) {
      animateToExpansionFraction();
    }
    mError = true;
  }
  
  private Drawable getBoxBackground()
  {
    int k = r;
    if ((k != 1) && (k != 2)) {
      throw new IllegalStateException();
    }
    return a;
  }
  
  private float[] getCornerRadiiAsArray()
  {
    if (!android.support.design.internal.DrawableCompat.isLayoutRtl(this))
    {
      f1 = mMiniKeyboardSlideAllowance;
      f2 = mMiniKeyboard;
      f3 = mVerticalGap;
      f4 = mTopPadding;
      return new float[] { f1, f1, f2, f2, f3, f3, f4, f4 };
    }
    float f1 = mMiniKeyboard;
    float f2 = mMiniKeyboardSlideAllowance;
    float f3 = mTopPadding;
    float f4 = mVerticalGap;
    return new float[] { f1, f1, f2, f2, f3, f3, f4, f4 };
  }
  
  private void init()
  {
    if (mEditText == null) {
      return;
    }
    if (refresh())
    {
      if (this$0 == null)
      {
        this$0 = ((CheckableImageButton)LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_password_icon, mContent, false));
        this$0.setImageDrawable(mDrawable);
        this$0.setContentDescription(mTitle);
        mContent.addView(this$0);
        this$0.setOnClickListener(new b());
      }
      localObject = mEditText;
      if ((localObject != null) && (ViewCompat.getMinimumHeight((View)localObject) <= 0)) {
        mEditText.setMinimumHeight(ViewCompat.getMinimumHeight(this$0));
      }
      this$0.setVisibility(0);
      this$0.setChecked(loading);
      if (mContext == null) {
        mContext = new ColorDrawable();
      }
      mContext.setBounds(0, 0, this$0.getMeasuredWidth(), 1);
      localObject = android.support.v4.widget.TextView.applyStyle(mEditText);
      if (localObject[2] != mContext) {
        mData = localObject[2];
      }
      android.support.v4.widget.TextView.setCompoundDrawablesRelative(mEditText, localObject[0], localObject[1], mContext, localObject[3]);
      this$0.setPadding(mEditText.getPaddingLeft(), mEditText.getPaddingTop(), mEditText.getPaddingRight(), mEditText.getPaddingBottom());
      return;
    }
    Object localObject = this$0;
    if ((localObject != null) && (((View)localObject).getVisibility() == 0)) {
      this$0.setVisibility(8);
    }
    if (mContext != null)
    {
      localObject = android.support.v4.widget.TextView.applyStyle(mEditText);
      if (localObject[2] == mContext)
      {
        android.support.v4.widget.TextView.setCompoundDrawablesRelative(mEditText, localObject[0], localObject[1], mData, localObject[3]);
        mContext = null;
      }
    }
  }
  
  private void layout()
  {
    Object localObject1 = mEditText;
    if (localObject1 == null) {
      return;
    }
    Object localObject2 = ((View)localObject1).getBackground();
    localObject1 = localObject2;
    if (localObject2 == null) {
      return;
    }
    if (android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable((Drawable)localObject2)) {
      localObject1 = ((Drawable)localObject2).mutate();
    }
    localObject2 = new Rect();
    ViewGroupUtilsHoneycomb.getDescendantRect(this, mEditText, (Rect)localObject2);
    localObject2 = ((Drawable)localObject1).getBounds();
    if (left != right)
    {
      Rect localRect = new Rect();
      ((Drawable)localObject1).getPadding(localRect);
      int k = left;
      int i1 = left;
      int i2 = right;
      int i3 = right;
      ((Drawable)localObject1).setBounds(k - i1, top, i2 + i3 * 2, mEditText.getBottom());
    }
  }
  
  private void onDraw()
  {
    if ((r != 0) && (a != null) && (mEditText != null))
    {
      if (getRight() == 0) {
        return;
      }
      int i7 = mEditText.getLeft();
      int i1 = i7;
      int i6 = save();
      int i2 = i6;
      int i5 = mEditText.getRight();
      int i3 = i5;
      int i4 = mEditText.getBottom() + mColor;
      int k = i4;
      if (r == 2)
      {
        k = i;
        i1 = i7 + k / 2;
        i2 = i6 - k / 2;
        i3 = i5 - k / 2;
        k = i4 + k / 2;
      }
      a.setBounds(i1, i2, i3, k);
      a();
      layout();
    }
  }
  
  private void onLayout()
  {
    if (!cancel()) {
      return;
    }
    RectF localRectF = mTarget;
    mCollapsingTextHelper.draw(localRectF);
    draw(localRectF);
    ((ClassWriter)a).a(localRectF);
  }
  
  private boolean refresh()
  {
    return (list) && ((updateCounter()) || (loading));
  }
  
  private int save()
  {
    android.widget.EditText localEditText = mEditText;
    if (localEditText == null) {
      return 0;
    }
    int k = r;
    if (k != 1)
    {
      if (k != 2) {
        return 0;
      }
      return localEditText.getTop() + draw();
    }
    return localEditText.getTop();
  }
  
  private void set()
  {
    int k = r;
    if (k == 0)
    {
      a = null;
      return;
    }
    if ((k == 2) && (mHintEnabled) && (!(a instanceof ClassWriter)))
    {
      a = new ClassWriter();
      return;
    }
    if (!(a instanceof GradientDrawable)) {
      a = new GradientDrawable();
    }
  }
  
  private void setEditText(android.widget.EditText paramEditText)
  {
    if (mEditText == null)
    {
      if (!(paramEditText instanceof EditText)) {
        Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
      }
      mEditText = paramEditText;
      setHint();
      setTextInputAccessibilityDelegate(new d(this));
      if (!updateCounter()) {
        mCollapsingTextHelper.setTypefaces(mEditText.getTypeface());
      }
      mCollapsingTextHelper.setExpandedTextSize(mEditText.getTextSize());
      int k = mEditText.getGravity();
      mCollapsingTextHelper.setCollapsedTextGravity(k & 0xFFFFFF8F | 0x30);
      mCollapsingTextHelper.setExpandedTextGravity(k);
      mEditText.addTextChangedListener(new a());
      if (mDefaultTextColor == null) {
        mDefaultTextColor = mEditText.getHintTextColors();
      }
      if (mHintEnabled)
      {
        if (TextUtils.isEmpty(mHint))
        {
          name = mEditText.getHint();
          setHint(name);
          mEditText.setHint(null);
        }
        isLoading = true;
      }
      if (mCounterView != null) {
        updateCounter(mEditText.getText().length());
      }
      mMenu.a();
      init();
      updateLabelState(false, true);
      return;
    }
    throw new IllegalArgumentException("We already have an EditText, can only have one");
  }
  
  private void setHint()
  {
    set();
    if (r != 0) {
      setInsets();
    }
    onDraw();
  }
  
  private void setHintInternal(CharSequence paramCharSequence)
  {
    if (!TextUtils.equals(paramCharSequence, mHint))
    {
      mHint = paramCharSequence;
      mCollapsingTextHelper.setText(paramCharSequence);
      if (!mError) {
        onLayout();
      }
    }
  }
  
  private void setIcon()
  {
    if ((mDrawable != null) && ((l) || (c)))
    {
      mDrawable = android.support.v4.graphics.drawable.DrawableCompat.wrap(mDrawable).mutate();
      if (l) {
        android.support.v4.graphics.drawable.DrawableCompat.setTintList(mDrawable, m);
      }
      if (c) {
        android.support.v4.graphics.drawable.DrawableCompat.setTintMode(mDrawable, e);
      }
      Object localObject = this$0;
      if (localObject != null)
      {
        localObject = ((ImageView)localObject).getDrawable();
        Drawable localDrawable = mDrawable;
        if (localObject != localDrawable) {
          this$0.setImageDrawable(localDrawable);
        }
      }
    }
  }
  
  private void setInsets()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mContent.getLayoutParams();
    int k = draw();
    if (k != topMargin)
    {
      topMargin = k;
      mContent.requestLayout();
    }
  }
  
  private boolean updateCounter()
  {
    android.widget.EditText localEditText = mEditText;
    return (localEditText != null) && ((localEditText.getTransformationMethod() instanceof PasswordTransformationMethod));
  }
  
  private void updateLabelState(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool1 = isEnabled();
    Object localObject = mEditText;
    int i1 = 1;
    int k;
    if ((localObject != null) && (!TextUtils.isEmpty(((android.widget.EditText)localObject).getText()))) {
      k = 1;
    } else {
      k = 0;
    }
    localObject = mEditText;
    if ((localObject == null) || (!((View)localObject).hasFocus())) {
      i1 = 0;
    }
    boolean bool2 = mMenu.c();
    localObject = mDefaultTextColor;
    if (localObject != null)
    {
      mCollapsingTextHelper.setCollapsedTextColor((ColorStateList)localObject);
      mCollapsingTextHelper.setExpandedTextColor(mDefaultTextColor);
    }
    if (!bool1)
    {
      mCollapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(offset));
      mCollapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(offset));
    }
    else if (bool2)
    {
      mCollapsingTextHelper.setCollapsedTextColor(mMenu.getItem());
    }
    else
    {
      if (mCounterOverflowed)
      {
        localObject = mCounterView;
        if (localObject != null)
        {
          mCollapsingTextHelper.setCollapsedTextColor(((android.widget.TextView)localObject).getTextColors());
          break label219;
        }
      }
      if (i1 != 0)
      {
        localObject = mFocusedTextColor;
        if (localObject != null) {
          mCollapsingTextHelper.setCollapsedTextColor((ColorStateList)localObject);
        }
      }
    }
    label219:
    if ((k == 0) && ((!isEnabled()) || ((i1 == 0) && (!bool2))))
    {
      if ((paramBoolean2) || (!mError)) {
        expandHint(paramBoolean1);
      }
    }
    else if ((paramBoolean2) || (mError)) {
      collapseHint(paramBoolean1);
    }
  }
  
  private void updateLabelVisibility()
  {
    int k = r;
    if (k != 1)
    {
      if (k != 2) {
        return;
      }
      if (n == 0) {
        n = mFocusedTextColor.getColorForState(getDrawableState(), mFocusedTextColor.getDefaultColor());
      }
    }
    else
    {
      flags = 0;
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramView instanceof android.widget.EditText))
    {
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(paramLayoutParams);
      gravity = (gravity & 0xFFFFFF8F | 0x10);
      mContent.addView(paramView, localLayoutParams);
      mContent.setLayoutParams(paramLayoutParams);
      setInsets();
      setEditText((android.widget.EditText)paramView);
      return;
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  void addView(boolean paramBoolean)
  {
    updateLabelState(paramBoolean, false);
  }
  
  void animateToExpansionFraction(float paramFloat)
  {
    if (mCollapsingTextHelper.getExpansionFraction() == paramFloat) {
      return;
    }
    if (mAnimator == null)
    {
      mAnimator = new ValueAnimator();
      mAnimator.setInterpolator(Item.c);
      mAnimator.setDuration(167L);
      mAnimator.addUpdateListener(new c());
    }
    mAnimator.setFloatValues(new float[] { mCollapsingTextHelper.getExpansionFraction(), paramFloat });
    mAnimator.start();
  }
  
  void clear()
  {
    if (a != null)
    {
      if (r == 0) {
        return;
      }
      Object localObject = mEditText;
      int i1 = 1;
      int k;
      if ((localObject != null) && (((View)localObject).hasFocus())) {
        k = 1;
      } else {
        k = 0;
      }
      localObject = mEditText;
      if ((localObject == null) || (!((View)localObject).isHovered())) {
        i1 = 0;
      }
      if (r == 2)
      {
        if (!isEnabled())
        {
          type = offset;
        }
        else if (mMenu.c())
        {
          type = mMenu.d();
        }
        else
        {
          if (mCounterOverflowed)
          {
            localObject = mCounterView;
            if (localObject != null)
            {
              type = ((android.widget.TextView)localObject).getCurrentTextColor();
              break label176;
            }
          }
          if (k != 0) {
            type = n;
          } else if (i1 != 0) {
            type = size;
          } else {
            type = value;
          }
        }
        label176:
        if (((i1 != 0) || (k != 0)) && (isEnabled())) {
          flags = i;
        } else {
          flags = j;
        }
        a();
      }
    }
  }
  
  public void dispatchProvideAutofillStructure(ViewStructure paramViewStructure, int paramInt)
  {
    if (name != null)
    {
      Object localObject = mEditText;
      if (localObject != null)
      {
        boolean bool = isLoading;
        isLoading = false;
        localObject = ((android.widget.TextView)localObject).getHint();
        mEditText.setHint(name);
        try
        {
          super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
          mEditText.setHint((CharSequence)localObject);
          isLoading = bool;
          return;
        }
        catch (Throwable paramViewStructure)
        {
          mEditText.setHint((CharSequence)localObject);
          isLoading = bool;
          throw paramViewStructure;
        }
      }
    }
    super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
  }
  
  protected void dispatchRestoreInstanceState(SparseArray paramSparseArray)
  {
    mCancellingAnimations = true;
    super.dispatchRestoreInstanceState(paramSparseArray);
    mCancellingAnimations = false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    GradientDrawable localGradientDrawable = a;
    if (localGradientDrawable != null) {
      localGradientDrawable.draw(paramCanvas);
    }
    super.draw(paramCanvas);
    if (mHintEnabled) {
      mCollapsingTextHelper.draw(paramCanvas);
    }
  }
  
  protected void drawableStateChanged()
  {
    if (mErrorShown) {
      return;
    }
    boolean bool2 = true;
    mErrorShown = true;
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    boolean bool1 = false;
    if ((!ViewCompat.get(this)) || (!isEnabled())) {
      bool2 = false;
    }
    addView(bool2);
    updateEditTextBackground();
    onDraw();
    clear();
    CollapsingTextHelper localCollapsingTextHelper = mCollapsingTextHelper;
    if (localCollapsingTextHelper != null) {
      bool1 = false | localCollapsingTextHelper.setText(arrayOfInt);
    }
    if (bool1) {
      invalidate();
    }
    mErrorShown = false;
  }
  
  public int getBoxBackgroundColor()
  {
    return o;
  }
  
  public float getBoxCornerRadiusBottomEnd()
  {
    return mVerticalGap;
  }
  
  public float getBoxCornerRadiusBottomStart()
  {
    return mTopPadding;
  }
  
  public float getBoxCornerRadiusTopEnd()
  {
    return mMiniKeyboard;
  }
  
  public float getBoxCornerRadiusTopStart()
  {
    return mMiniKeyboardSlideAllowance;
  }
  
  public int getBoxStrokeColor()
  {
    return n;
  }
  
  public int getCounterMaxLength()
  {
    return mCounterMaxLength;
  }
  
  CharSequence getCounterOverflowDescription()
  {
    if ((mCounterEnabled) && (mCounterOverflowed))
    {
      android.widget.TextView localTextView = mCounterView;
      if (localTextView != null) {
        return localTextView.getContentDescription();
      }
    }
    return null;
  }
  
  public ColorStateList getDefaultHintTextColor()
  {
    return mDefaultTextColor;
  }
  
  public android.widget.EditText getEditText()
  {
    return mEditText;
  }
  
  public CharSequence getError()
  {
    if (mMenu.e()) {
      return mMenu.n();
    }
    return null;
  }
  
  public int getErrorCurrentTextColors()
  {
    return mMenu.d();
  }
  
  final int getErrorTextCurrentColor()
  {
    return mMenu.d();
  }
  
  public CharSequence getHelperText()
  {
    if (mMenu.g()) {
      return mMenu.m();
    }
    return null;
  }
  
  public int getHelperTextCurrentTextColor()
  {
    return mMenu.i();
  }
  
  public CharSequence getHint()
  {
    if (mHintEnabled) {
      return mHint;
    }
    return null;
  }
  
  final float getHintCollapsedTextHeight()
  {
    return mCollapsingTextHelper.draw();
  }
  
  final int getHintCurrentCollapsedTextColor()
  {
    return mCollapsingTextHelper.getDisabledThemeAttrColor();
  }
  
  public CharSequence getPasswordVisibilityToggleContentDescription()
  {
    return mTitle;
  }
  
  public Drawable getPasswordVisibilityToggleDrawable()
  {
    return mDrawable;
  }
  
  public Typeface getTypeface()
  {
    return mTypeface;
  }
  
  void init(android.widget.TextView paramTextView, int paramInt)
  {
    int k = 0;
    try
    {
      android.support.v4.widget.TextView.setTextAppearance(paramTextView, paramInt);
      paramInt = k;
      if (Build.VERSION.SDK_INT >= 23)
      {
        int i1 = paramTextView.getTextColors().getDefaultColor();
        paramInt = k;
        if (i1 == -65281) {
          paramInt = 1;
        }
      }
    }
    catch (Exception localException)
    {
      paramInt = 1;
    }
    if (paramInt != 0)
    {
      android.support.v4.widget.TextView.setTextAppearance(paramTextView, R.style.TextAppearance_AppCompat_Caption);
      paramTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
    }
  }
  
  public void init(boolean paramBoolean)
  {
    if (list)
    {
      int k = mEditText.getSelectionEnd();
      if (updateCounter())
      {
        mEditText.setTransformationMethod(null);
        loading = true;
      }
      else
      {
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loading = false;
      }
      this$0.setChecked(loading);
      if (paramBoolean) {
        this$0.jumpDrawablesToCurrentState();
      }
      mEditText.setSelection(k);
    }
  }
  
  boolean isLoading()
  {
    return isLoading;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (a != null) {
      onDraw();
    }
    if (mHintEnabled)
    {
      android.widget.EditText localEditText = mEditText;
      if (localEditText != null)
      {
        Rect localRect = mTmpRect;
        ViewGroupUtilsHoneycomb.getDescendantRect(this, localEditText, localRect);
        paramInt1 = left + mEditText.getCompoundPaddingLeft();
        paramInt3 = right - mEditText.getCompoundPaddingRight();
        int k = drawText();
        mCollapsingTextHelper.setExpandedBounds(paramInt1, top + mEditText.getCompoundPaddingTop(), paramInt3, bottom - mEditText.getCompoundPaddingBottom());
        mCollapsingTextHelper.setCollapsedBounds(paramInt1, k, paramInt3, paramInt4 - paramInt2 - getPaddingBottom());
        mCollapsingTextHelper.recalculate();
        if ((cancel()) && (!mError)) {
          onLayout();
        }
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    init();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof e))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (e)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setError(error);
    if (value) {
      init(true);
    }
    requestLayout();
  }
  
  public Parcelable onSaveInstanceState()
  {
    e localE = new e(super.onSaveInstanceState());
    if (mMenu.c()) {
      error = getError();
    }
    value = loading;
    return localE;
  }
  
  public boolean preparePanel()
  {
    return mMenu.g();
  }
  
  public void setBoxBackgroundColor(int paramInt)
  {
    if (o != paramInt)
    {
      o = paramInt;
      a();
    }
  }
  
  public void setBoxBackgroundColorResource(int paramInt)
  {
    setBoxBackgroundColor(ContextCompat.getColor(getContext(), paramInt));
  }
  
  public void setBoxBackgroundMode(int paramInt)
  {
    if (paramInt == r) {
      return;
    }
    r = paramInt;
    setHint();
  }
  
  public void setBoxStrokeColor(int paramInt)
  {
    if (n != paramInt)
    {
      n = paramInt;
      clear();
    }
  }
  
  public void setCounterEnabled(boolean paramBoolean)
  {
    if (mCounterEnabled != paramBoolean)
    {
      if (paramBoolean)
      {
        mCounterView = new AppCompatTextView(getContext());
        mCounterView.setId(R.id.textinput_counter);
        Object localObject = mTypeface;
        if (localObject != null) {
          mCounterView.setTypeface((Typeface)localObject);
        }
        mCounterView.setMaxLines(1);
        init(mCounterView, mCounterTextAppearance);
        mMenu.a(mCounterView, 2);
        localObject = mEditText;
        if (localObject == null) {
          updateCounter(0);
        } else {
          updateCounter(((android.widget.EditText)localObject).getText().length());
        }
      }
      else
      {
        mMenu.b(mCounterView, 2);
        mCounterView = null;
      }
      mCounterEnabled = paramBoolean;
    }
  }
  
  public void setCounterMaxLength(int paramInt)
  {
    if (mCounterMaxLength != paramInt)
    {
      if (paramInt > 0) {
        mCounterMaxLength = paramInt;
      } else {
        mCounterMaxLength = -1;
      }
      if (mCounterEnabled)
      {
        android.widget.EditText localEditText = mEditText;
        if (localEditText == null) {
          paramInt = 0;
        } else {
          paramInt = localEditText.getText().length();
        }
        updateCounter(paramInt);
      }
    }
  }
  
  public void setDefaultHintTextColor(ColorStateList paramColorStateList)
  {
    mDefaultTextColor = paramColorStateList;
    mFocusedTextColor = paramColorStateList;
    if (mEditText != null) {
      addView(false);
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    a(this, paramBoolean);
    super.setEnabled(paramBoolean);
  }
  
  public void setError(CharSequence paramCharSequence)
  {
    if (!mMenu.e())
    {
      if (TextUtils.isEmpty(paramCharSequence)) {
        return;
      }
      setErrorEnabled(true);
    }
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      mMenu.b(paramCharSequence);
      return;
    }
    mMenu.b();
  }
  
  public void setErrorEnabled(boolean paramBoolean)
  {
    mMenu.a(paramBoolean);
  }
  
  public void setErrorTextAppearance(int paramInt)
  {
    mMenu.a(paramInt);
  }
  
  public void setErrorTextColor(ColorStateList paramColorStateList)
  {
    mMenu.a(paramColorStateList);
  }
  
  public void setHelperText(CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence))
    {
      if (preparePanel()) {
        setHelperTextEnabled(false);
      }
    }
    else
    {
      if (!preparePanel()) {
        setHelperTextEnabled(true);
      }
      mMenu.a(paramCharSequence);
    }
  }
  
  public void setHelperTextColor(ColorStateList paramColorStateList)
  {
    mMenu.b(paramColorStateList);
  }
  
  public void setHelperTextEnabled(boolean paramBoolean)
  {
    mMenu.b(paramBoolean);
  }
  
  public void setHelperTextTextAppearance(int paramInt)
  {
    mMenu.b(paramInt);
  }
  
  public void setHint(CharSequence paramCharSequence)
  {
    if (mHintEnabled)
    {
      setHintInternal(paramCharSequence);
      sendAccessibilityEvent(2048);
    }
  }
  
  public void setHintAnimationEnabled(boolean paramBoolean)
  {
    mHintAnimationEnabled = paramBoolean;
  }
  
  public void setHintEnabled(boolean paramBoolean)
  {
    if (paramBoolean != mHintEnabled)
    {
      mHintEnabled = paramBoolean;
      if (!mHintEnabled)
      {
        isLoading = false;
        if ((!TextUtils.isEmpty(mHint)) && (TextUtils.isEmpty(mEditText.getHint()))) {
          mEditText.setHint(mHint);
        }
        setHintInternal(null);
      }
      else
      {
        CharSequence localCharSequence = mEditText.getHint();
        if (!TextUtils.isEmpty(localCharSequence))
        {
          if (TextUtils.isEmpty(mHint)) {
            setHint(localCharSequence);
          }
          mEditText.setHint(null);
        }
        isLoading = true;
      }
      if (mEditText != null) {
        setInsets();
      }
    }
  }
  
  public void setHintTextAppearance(int paramInt)
  {
    mCollapsingTextHelper.setCollapsedTextAppearance(paramInt);
    mFocusedTextColor = mCollapsingTextHelper.getCollapsedTextColor();
    if (mEditText != null)
    {
      addView(false);
      setInsets();
    }
  }
  
  public void setPasswordVisibilityToggleContentDescription(int paramInt)
  {
    CharSequence localCharSequence;
    if (paramInt != 0) {
      localCharSequence = getResources().getText(paramInt);
    } else {
      localCharSequence = null;
    }
    setPasswordVisibilityToggleContentDescription(localCharSequence);
  }
  
  public void setPasswordVisibilityToggleContentDescription(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    CheckableImageButton localCheckableImageButton = this$0;
    if (localCheckableImageButton != null) {
      localCheckableImageButton.setContentDescription(paramCharSequence);
    }
  }
  
  public void setPasswordVisibilityToggleDrawable(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = org.org.v4.gui.helpers.Resources.getDrawable(getContext(), paramInt);
    } else {
      localDrawable = null;
    }
    setPasswordVisibilityToggleDrawable(localDrawable);
  }
  
  public void setPasswordVisibilityToggleDrawable(Drawable paramDrawable)
  {
    mDrawable = paramDrawable;
    CheckableImageButton localCheckableImageButton = this$0;
    if (localCheckableImageButton != null) {
      localCheckableImageButton.setImageDrawable(paramDrawable);
    }
  }
  
  public void setPasswordVisibilityToggleEnabled(boolean paramBoolean)
  {
    if (list != paramBoolean)
    {
      list = paramBoolean;
      if ((!paramBoolean) && (loading))
      {
        android.widget.EditText localEditText = mEditText;
        if (localEditText != null) {
          localEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
      }
      loading = false;
      init();
    }
  }
  
  public void setPasswordVisibilityToggleTintList(ColorStateList paramColorStateList)
  {
    m = paramColorStateList;
    l = true;
    setIcon();
  }
  
  public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode paramMode)
  {
    e = paramMode;
    c = true;
    setIcon();
  }
  
  public void setTextInputAccessibilityDelegate(d paramD)
  {
    android.widget.EditText localEditText = mEditText;
    if (localEditText != null) {
      ViewCompat.setAccessibilityDelegate(localEditText, paramD);
    }
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    if (paramTypeface != mTypeface)
    {
      mTypeface = paramTypeface;
      mCollapsingTextHelper.setTypefaces(paramTypeface);
      mMenu.d(paramTypeface);
      android.widget.TextView localTextView = mCounterView;
      if (localTextView != null) {
        localTextView.setTypeface(paramTypeface);
      }
    }
  }
  
  void updateCounter(int paramInt)
  {
    boolean bool2 = mCounterOverflowed;
    if (mCounterMaxLength == -1)
    {
      mCounterView.setText(String.valueOf(paramInt));
      mCounterView.setContentDescription(null);
      mCounterOverflowed = false;
    }
    else
    {
      if (ViewCompat.update(mCounterView) == 1) {
        ViewCompat.setText(mCounterView, 0);
      }
      if (paramInt > mCounterMaxLength) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mCounterOverflowed = bool1;
      boolean bool1 = mCounterOverflowed;
      if (bool2 != bool1)
      {
        android.widget.TextView localTextView = mCounterView;
        int k;
        if (bool1) {
          k = mCounterOverflowTextAppearance;
        } else {
          k = mCounterTextAppearance;
        }
        init(localTextView, k);
        if (mCounterOverflowed) {
          ViewCompat.setText(mCounterView, 1);
        }
      }
      mCounterView.setText(getContext().getString(R.string.character_counter_pattern, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(mCounterMaxLength) }));
      mCounterView.setContentDescription(getContext().getString(R.string.character_counter_content_description, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(mCounterMaxLength) }));
    }
    if ((mEditText != null) && (bool2 != mCounterOverflowed))
    {
      addView(false);
      clear();
      updateEditTextBackground();
    }
  }
  
  void updateEditTextBackground()
  {
    Object localObject1 = mEditText;
    if (localObject1 == null) {
      return;
    }
    Object localObject2 = ((View)localObject1).getBackground();
    localObject1 = localObject2;
    if (localObject2 == null) {
      return;
    }
    ensureBackgroundDrawableStateWorkaround();
    if (android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable((Drawable)localObject2)) {
      localObject1 = ((Drawable)localObject2).mutate();
    }
    if (mMenu.c())
    {
      ((Drawable)localObject1).setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(mMenu.d(), PorterDuff.Mode.SRC_IN));
      return;
    }
    if (mCounterOverflowed)
    {
      localObject2 = mCounterView;
      if (localObject2 != null)
      {
        ((Drawable)localObject1).setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(((android.widget.TextView)localObject2).getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        return;
      }
    }
    android.support.v4.graphics.drawable.DrawableCompat.update((Drawable)localObject1);
    mEditText.refreshDrawableState();
  }
  
  class a
    implements TextWatcher
  {
    a() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      TextInputLayout localTextInputLayout = TextInputLayout.this;
      localTextInputLayout.addView(TextInputLayout.access$getMCounterEnabled(localTextInputLayout) ^ true);
      localTextInputLayout = TextInputLayout.this;
      if (mCounterEnabled) {
        localTextInputLayout.updateCounter(paramEditable.length());
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  class b
    implements View.OnClickListener
  {
    b() {}
    
    public void onClick(View paramView)
    {
      init(false);
    }
  }
  
  class c
    implements ValueAnimator.AnimatorUpdateListener
  {
    c() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      mCollapsingTextHelper.setExpansionFraction(((Float)paramValueAnimator.getAnimatedValue()).floatValue());
    }
  }
  
  public static class d
    extends AccessibilityDelegateCompat
  {
    private final TextInputLayout this$0;
    
    public d(TextInputLayout paramTextInputLayout)
    {
      this$0 = paramTextInputLayout;
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramView = this$0.getEditText();
      if (paramView != null) {
        paramView = paramView.getText();
      } else {
        paramView = null;
      }
      CharSequence localCharSequence3 = this$0.getHint();
      CharSequence localCharSequence1 = this$0.getError();
      CharSequence localCharSequence2 = this$0.getCounterOverflowDescription();
      boolean bool1 = TextUtils.isEmpty(paramView) ^ true;
      boolean bool2 = TextUtils.isEmpty(localCharSequence3) ^ true;
      boolean bool3 = TextUtils.isEmpty(localCharSequence1) ^ true;
      boolean bool5 = false;
      int i;
      if ((!bool3) && (TextUtils.isEmpty(localCharSequence2))) {
        i = 0;
      } else {
        i = 1;
      }
      if (bool1) {
        paramAccessibilityNodeInfoCompat.setError(paramView);
      } else if (bool2) {
        paramAccessibilityNodeInfoCompat.setError(localCharSequence3);
      }
      if (bool2)
      {
        paramAccessibilityNodeInfoCompat.setText(localCharSequence3);
        boolean bool4 = bool5;
        if (!bool1)
        {
          bool4 = bool5;
          if (bool2) {
            bool4 = true;
          }
        }
        paramAccessibilityNodeInfoCompat.setText(bool4);
      }
      if (i != 0)
      {
        if (bool3) {
          paramView = localCharSequence1;
        } else {
          paramView = localCharSequence2;
        }
        paramAccessibilityNodeInfoCompat.setParent(paramView);
        paramAccessibilityNodeInfoCompat.setParent(true);
      }
    }
    
    public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramView = this$0.getEditText();
      if (paramView != null) {
        paramView = paramView.getText();
      } else {
        paramView = null;
      }
      if (TextUtils.isEmpty(paramView)) {
        paramView = this$0.getHint();
      }
      if (!TextUtils.isEmpty((CharSequence)paramView)) {
        paramAccessibilityEvent.getText().add(paramView);
      }
    }
  }
  
  static class e
    extends Artist
  {
    public static final Parcelable.Creator<e> CREATOR = new a();
    CharSequence error;
    boolean value;
    
    e(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      error = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
      int i = paramParcel.readInt();
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      value = bool;
    }
    
    e(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("TextInputLayout.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" error=");
      localStringBuilder.append(error);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<TextInputLayout.e>
    {
      a() {}
      
      public TextInputLayout.e createFromParcel(Parcel paramParcel)
      {
        return new TextInputLayout.e(paramParcel, null);
      }
      
      public TextInputLayout.e createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new TextInputLayout.e(paramParcel, paramClassLoader);
      }
      
      public TextInputLayout.e[] newArray(int paramInt)
      {
        return new TextInputLayout.e[paramInt];
      }
    }
  }
}
