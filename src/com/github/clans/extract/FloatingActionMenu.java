package com.github.clans.extract;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class FloatingActionMenu
  extends ViewGroup
{
  private boolean fab_label;
  private int mAnimationDelayPerItem;
  private int mBackgroundColor;
  private int mButtonSpacing = Util.dpToPx(getContext(), 0.0F);
  private int mButtonsCount;
  private AnimatorSet mCloseAnimatorSet = new AnimatorSet();
  private Interpolator mCloseInterpolator;
  private ValueAnimator mHideBackgroundAnimator;
  private Drawable mIcon;
  private boolean mIconAnimated = true;
  private AnimatorSet mIconToggleSet;
  private ImageView mImageToggle;
  private boolean mIsAnimated = true;
  private boolean mIsMenuOpening;
  private boolean mIsSetClosedOnTouchOutside;
  private String mLabelText;
  private int mLabelsColorNormal;
  private int mLabelsColorPressed;
  private int mLabelsColorRipple;
  private Context mLabelsContext;
  private int mLabelsCornerRadius = Util.dpToPx(getContext(), 3.0F);
  private int mLabelsEllipsize;
  private int mLabelsHideAnimation;
  private int mLabelsMargin = Util.dpToPx(getContext(), 0.0F);
  private int mLabelsMaxLines;
  private int mLabelsPaddingBottom = Util.dpToPx(getContext(), 4.0F);
  private int mLabelsPaddingLeft = Util.dpToPx(getContext(), 8.0F);
  private int mLabelsPaddingRight = Util.dpToPx(getContext(), 8.0F);
  private int mLabelsPaddingTop = Util.dpToPx(getContext(), 4.0F);
  private int mLabelsPosition;
  private int mLabelsShowAnimation;
  private boolean mLabelsShowShadow;
  private boolean mLabelsSingleLine;
  private int mLabelsStyle;
  private ColorStateList mLabelsTextColor;
  private float mLabelsTextSize;
  private int mLabelsVerticalOffset = Util.dpToPx(getContext(), 0.0F);
  private int mMaxButtonWidth;
  private FloatingActionButton mMenuButton;
  private int mMenuColorNormal;
  private int mMenuColorPressed;
  private int mMenuColorRipple;
  private int mMenuFabSize;
  private boolean mMenuOpened;
  private int mMenuShadowColor;
  private float mMenuShadowRadius = 4.0F;
  private float mMenuShadowXOffset = 1.0F;
  private float mMenuShadowYOffset = 3.0F;
  private boolean mMenuShowShadow;
  private AnimatorSet mOpenAnimatorSet = new AnimatorSet();
  private int mOpenDirection;
  private Interpolator mOpenInterpolator;
  private ValueAnimator mShowBackgroundAnimator;
  private h mToggleListener;
  private Handler mUiHandler = new Handler();
  private Typeface t;
  
  public FloatingActionMenu(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FloatingActionMenu(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public FloatingActionMenu(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void addLabel(FloatingActionButton paramFloatingActionButton)
  {
    String str = paramFloatingActionButton.getLabelText();
    if (TextUtils.isEmpty(str)) {
      return;
    }
    Label localLabel = new Label(mLabelsContext);
    localLabel.setClickable(true);
    localLabel.setFab(paramFloatingActionButton);
    localLabel.setShowAnimation(AnimationUtils.loadAnimation(getContext(), mLabelsShowAnimation));
    localLabel.setHideAnimation(AnimationUtils.loadAnimation(getContext(), mLabelsHideAnimation));
    if (mLabelsStyle > 0)
    {
      localLabel.setTextAppearance(getContext(), mLabelsStyle);
      localLabel.setShowShadow(false);
      localLabel.setUsingStyle(true);
    }
    else
    {
      localLabel.setColors(mLabelsColorNormal, mLabelsColorPressed, mLabelsColorRipple);
      localLabel.setShowShadow(mLabelsShowShadow);
      localLabel.setCornerRadius(mLabelsCornerRadius);
      if (mLabelsEllipsize > 0) {
        setLabelEllipsize(localLabel);
      }
      localLabel.setMaxLines(mLabelsMaxLines);
      localLabel.updateBackground();
      localLabel.setTextSize(0, mLabelsTextSize);
      localLabel.setTextColor(mLabelsTextColor);
      int m = mLabelsPaddingLeft;
      int k = mLabelsPaddingTop;
      int j = m;
      int i = k;
      if (mLabelsShowShadow)
      {
        j = m + (paramFloatingActionButton.getShadowRadius() + Math.abs(paramFloatingActionButton.getShadowXOffset()));
        i = k + (paramFloatingActionButton.getShadowRadius() + Math.abs(paramFloatingActionButton.getShadowYOffset()));
      }
      localLabel.setPadding(j, i, mLabelsPaddingLeft, mLabelsPaddingTop);
      if ((mLabelsMaxLines < 0) || (mLabelsSingleLine)) {
        localLabel.setSingleLine(mLabelsSingleLine);
      }
    }
    Typeface localTypeface = t;
    if (localTypeface != null) {
      localLabel.setTypeface(localTypeface);
    }
    localLabel.setText(str);
    localLabel.setOnClickListener(paramFloatingActionButton.getOnClickListener());
    addView(localLabel);
    paramFloatingActionButton.setTag(R.id.fab_label, localLabel);
  }
  
  private int adjustForOvershoot(int paramInt)
  {
    double d1 = paramInt;
    Double.isNaN(d1);
    double d2 = paramInt;
    Double.isNaN(d2);
    return (int)(d1 * 0.03D + d2);
  }
  
  private void createDefaultIconAnimation()
  {
    int i = mOpenDirection;
    float f2 = 135.0F;
    float f1;
    if (i == 0)
    {
      if (mLabelsPosition == 0) {
        f1 = -135.0F;
      } else {
        f1 = 135.0F;
      }
      if (mLabelsPosition == 0) {
        f2 = -135.0F;
      }
    }
    else
    {
      if (mLabelsPosition == 0) {
        f1 = 135.0F;
      } else {
        f1 = -135.0F;
      }
      if (mLabelsPosition != 0) {
        f2 = -135.0F;
      }
    }
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(mImageToggle, "rotation", new float[] { f1, 0.0F });
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(mImageToggle, "rotation", new float[] { 0.0F, f2 });
    mOpenAnimatorSet.play(localObjectAnimator2);
    mCloseAnimatorSet.play(localObjectAnimator1);
    mOpenAnimatorSet.setInterpolator(mOpenInterpolator);
    mCloseAnimatorSet.setInterpolator(mCloseInterpolator);
    mOpenAnimatorSet.setDuration(300L);
    mCloseAnimatorSet.setDuration(300L);
  }
  
  private void createLabels()
  {
    int i = 0;
    while (i < mButtonsCount)
    {
      if (getChildAt(i) != mImageToggle)
      {
        FloatingActionButton localFloatingActionButton1 = (FloatingActionButton)getChildAt(i);
        if (localFloatingActionButton1.getTag(R.id.fab_label) == null)
        {
          addLabel(localFloatingActionButton1);
          FloatingActionButton localFloatingActionButton2 = mMenuButton;
          if (localFloatingActionButton1 == localFloatingActionButton2) {
            localFloatingActionButton2.setOnClickListener(new FloatingActionMenu.c(this));
          }
        }
      }
      i += 1;
    }
  }
  
  private void createMenuButton()
  {
    mMenuButton = new FloatingActionButton(getContext());
    FloatingActionButton localFloatingActionButton = mMenuButton;
    boolean bool = mMenuShowShadow;
    mShowShadow = bool;
    if (bool)
    {
      mShadowRadius = Util.dpToPx(getContext(), mMenuShadowRadius);
      mMenuButton.mShadowXOffset = Util.dpToPx(getContext(), mMenuShadowXOffset);
      mMenuButton.mShadowYOffset = Util.dpToPx(getContext(), mMenuShadowYOffset);
    }
    mMenuButton.setColors(mMenuColorNormal, mMenuColorPressed, mMenuColorRipple);
    localFloatingActionButton = mMenuButton;
    mShadowColor = mMenuShadowColor;
    mFabSize = mMenuFabSize;
    localFloatingActionButton.updateBackground();
    mMenuButton.setLabelText(mLabelText);
    mImageToggle = new ImageView(getContext());
    mImageToggle.setImageDrawable(mIcon);
    addView(mMenuButton, super.generateDefaultLayoutParams());
    addView(mImageToggle);
    createDefaultIconAnimation();
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionMenu, 0, 0);
    mButtonSpacing = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_buttonSpacing, mButtonSpacing);
    mLabelsMargin = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_margin, mLabelsMargin);
    mLabelsPosition = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_labels_position, 0);
    int j = R.styleable.FloatingActionMenu_menu_labels_showAnimation;
    int i;
    if (mLabelsPosition == 0) {
      i = R.anim.fab_slide_in_from_right;
    } else {
      i = R.anim.fab_slide_in_from_left;
    }
    mLabelsShowAnimation = paramAttributeSet.getResourceId(j, i);
    j = R.styleable.FloatingActionMenu_menu_labels_hideAnimation;
    if (mLabelsPosition == 0) {
      i = R.anim.fab_slide_out_to_right;
    } else {
      i = R.anim.fab_slide_out_to_left;
    }
    mLabelsHideAnimation = paramAttributeSet.getResourceId(j, i);
    mLabelsPaddingTop = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingTop, mLabelsPaddingTop);
    mLabelsPaddingRight = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingRight, mLabelsPaddingRight);
    mLabelsPaddingBottom = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingBottom, mLabelsPaddingBottom);
    mLabelsPaddingLeft = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_paddingLeft, mLabelsPaddingLeft);
    mLabelsTextColor = paramAttributeSet.getColorStateList(R.styleable.FloatingActionMenu_menu_labels_textColor);
    if (mLabelsTextColor == null) {
      mLabelsTextColor = ColorStateList.valueOf(-1);
    }
    mLabelsTextSize = paramAttributeSet.getDimension(R.styleable.FloatingActionMenu_menu_labels_textSize, getResources().getDimension(R.dimen.labels_text_size));
    mLabelsCornerRadius = paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_cornerRadius, mLabelsCornerRadius);
    mLabelsShowShadow = paramAttributeSet.getBoolean(R.styleable.FloatingActionMenu_menu_labels_showShadow, true);
    mLabelsColorNormal = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_labels_colorNormal, -13421773);
    mLabelsColorPressed = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_labels_colorPressed, -12303292);
    mLabelsColorRipple = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_labels_colorRipple, 1728053247);
    mMenuShowShadow = paramAttributeSet.getBoolean(R.styleable.FloatingActionMenu_menu_showShadow, true);
    mMenuShadowColor = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_shadowColor, 1711276032);
    mMenuShadowRadius = paramAttributeSet.getDimension(R.styleable.FloatingActionMenu_menu_shadowRadius, mMenuShadowRadius);
    mMenuShadowXOffset = paramAttributeSet.getDimension(R.styleable.FloatingActionMenu_menu_shadowXOffset, mMenuShadowXOffset);
    mMenuShadowYOffset = paramAttributeSet.getDimension(R.styleable.FloatingActionMenu_menu_shadowYOffset, mMenuShadowYOffset);
    mMenuColorNormal = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_colorNormal, -2473162);
    mMenuColorPressed = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_colorPressed, -1617853);
    mMenuColorRipple = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_colorRipple, -1711276033);
    mAnimationDelayPerItem = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_animationDelayPerItem, 50);
    mIcon = paramAttributeSet.getDrawable(R.styleable.FloatingActionMenu_menu_icon);
    if (mIcon == null) {
      mIcon = getResources().getDrawable(R.drawable.fab_add);
    }
    mLabelsSingleLine = paramAttributeSet.getBoolean(R.styleable.FloatingActionMenu_menu_labels_singleLine, false);
    mLabelsEllipsize = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_labels_ellipsize, 0);
    mLabelsMaxLines = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_labels_maxLines, -1);
    mMenuFabSize = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_fab_size, 0);
    mLabelsStyle = paramAttributeSet.getResourceId(R.styleable.FloatingActionMenu_menu_labels_style, 0);
    paramContext = paramAttributeSet.getString(R.styleable.FloatingActionMenu_menu_labels_customFont);
    try
    {
      boolean bool = TextUtils.isEmpty(paramContext);
      if (!bool) {
        t = Typeface.createFromAsset(getContext().getAssets(), paramContext);
      }
      mOpenDirection = paramAttributeSet.getInt(R.styleable.FloatingActionMenu_menu_openDirection, 0);
      mBackgroundColor = paramAttributeSet.getColor(R.styleable.FloatingActionMenu_menu_backgroundColor, 0);
      if (paramAttributeSet.hasValue(R.styleable.FloatingActionMenu_menu_fab_label))
      {
        fab_label = true;
        mLabelText = paramAttributeSet.getString(R.styleable.FloatingActionMenu_menu_fab_label);
      }
      if (paramAttributeSet.hasValue(R.styleable.FloatingActionMenu_menu_labels_padding)) {
        initPadding(paramAttributeSet.getDimensionPixelSize(R.styleable.FloatingActionMenu_menu_labels_padding, 0));
      }
      mOpenInterpolator = new OvershootInterpolator();
      mCloseInterpolator = new AnticipateInterpolator();
      mLabelsContext = new ContextThemeWrapper(getContext(), mLabelsStyle);
      initBackgroundDimAnimation();
      createMenuButton();
      initMenuButtonAnimations(paramAttributeSet);
      paramAttributeSet.recycle();
      return;
    }
    catch (RuntimeException paramAttributeSet)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to load specified custom font: ");
      localStringBuilder.append(paramContext);
      throw new IllegalArgumentException(localStringBuilder.toString(), paramAttributeSet);
    }
  }
  
  private void initBackgroundDimAnimation()
  {
    int i = Color.alpha(mBackgroundColor);
    int j = Color.red(mBackgroundColor);
    int k = Color.green(mBackgroundColor);
    int m = Color.blue(mBackgroundColor);
    mShowBackgroundAnimator = ValueAnimator.ofInt(new int[] { 0, i });
    mShowBackgroundAnimator.setDuration(300L);
    mShowBackgroundAnimator.addUpdateListener(new FloatingActionMenu.a(this, j, k, m));
    mHideBackgroundAnimator = ValueAnimator.ofInt(new int[] { i, 0 });
    mHideBackgroundAnimator.setDuration(300L);
    mHideBackgroundAnimator.addUpdateListener(new FloatingActionMenu.b(this, j, k, m));
  }
  
  private void initMenuButtonAnimations(TypedArray paramTypedArray)
  {
    int i = paramTypedArray.getResourceId(R.styleable.FloatingActionMenu_menu_fab_show_animation, R.anim.fab_scale_up);
    setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getContext(), i));
    AnimationUtils.loadAnimation(getContext(), i);
    i = paramTypedArray.getResourceId(R.styleable.FloatingActionMenu_menu_fab_hide_animation, R.anim.fab_scale_down);
    setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getContext(), i));
    AnimationUtils.loadAnimation(getContext(), i);
  }
  
  private void initPadding(int paramInt)
  {
    mLabelsPaddingTop = paramInt;
    mLabelsPaddingRight = paramInt;
    mLabelsPaddingBottom = paramInt;
    mLabelsPaddingLeft = paramInt;
  }
  
  private boolean isBackgroundEnabled()
  {
    return mBackgroundColor != 0;
  }
  
  private void setLabelEllipsize(Label paramLabel)
  {
    int i = mLabelsEllipsize;
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i != 4) {
            return;
          }
          paramLabel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
          return;
        }
        paramLabel.setEllipsize(TextUtils.TruncateAt.END);
        return;
      }
      paramLabel.setEllipsize(TextUtils.TruncateAt.MIDDLE);
      return;
    }
    paramLabel.setEllipsize(TextUtils.TruncateAt.START);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof ViewGroup.MarginLayoutParams;
  }
  
  public void close(boolean paramBoolean)
  {
    if (isOpened())
    {
      if (isBackgroundEnabled()) {
        mHideBackgroundAnimator.start();
      }
      Object localObject;
      if (mIconAnimated)
      {
        localObject = mIconToggleSet;
        if (localObject != null)
        {
          ((AnimatorSet)localObject).start();
        }
        else
        {
          mCloseAnimatorSet.start();
          mOpenAnimatorSet.cancel();
        }
      }
      int j = 0;
      int k = 0;
      mIsMenuOpening = false;
      int i = 0;
      while (i < getChildCount())
      {
        localObject = getChildAt(i);
        int n = j;
        int m = k;
        if ((localObject instanceof FloatingActionButton))
        {
          n = j;
          m = k;
          if (((View)localObject).getVisibility() != 8)
          {
            m = k + 1;
            localObject = (FloatingActionButton)localObject;
            mUiHandler.postDelayed(new FloatingActionMenu.f(this, (FloatingActionButton)localObject, paramBoolean), j);
            n = j + mAnimationDelayPerItem;
          }
        }
        i += 1;
        j = n;
        k = m;
      }
      mUiHandler.postDelayed(new FloatingActionMenu.g(this), mAnimationDelayPerItem * (k + 1));
    }
  }
  
  protected ViewGroup.MarginLayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.MarginLayoutParams(-2, -2);
  }
  
  public ViewGroup.MarginLayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ViewGroup.MarginLayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.MarginLayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new ViewGroup.MarginLayoutParams(paramLayoutParams);
  }
  
  public int getAnimationDelayPerItem()
  {
    return mAnimationDelayPerItem;
  }
  
  public AnimatorSet getIconToggleAnimatorSet()
  {
    return mIconToggleSet;
  }
  
  public int getMenuButtonColorNormal()
  {
    return mMenuColorNormal;
  }
  
  public int getMenuButtonColorPressed()
  {
    return mMenuColorPressed;
  }
  
  public int getMenuButtonColorRipple()
  {
    return mMenuColorRipple;
  }
  
  public String getMenuButtonLabelText()
  {
    return mLabelText;
  }
  
  public ImageView getMenuIconView()
  {
    return mImageToggle;
  }
  
  public boolean isOpened()
  {
    return mMenuOpened;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    bringChildToFront(mMenuButton);
    bringChildToFront(mImageToggle);
    mButtonsCount = getChildCount();
    createLabels();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i;
    if (mLabelsPosition == 0) {
      i = paramInt3 - paramInt1 - mMaxButtonWidth / 2 - getPaddingRight();
    } else {
      i = mMaxButtonWidth / 2 + getPaddingLeft();
    }
    int j;
    if (mOpenDirection == 0) {
      j = 1;
    } else {
      j = 0;
    }
    if (j != 0) {
      paramInt1 = paramInt4 - paramInt2 - mMenuButton.getMeasuredHeight() - getPaddingBottom();
    } else {
      paramInt1 = getPaddingTop();
    }
    paramInt2 = i - mMenuButton.getMeasuredWidth() / 2;
    Object localObject = mMenuButton;
    ((View)localObject).layout(paramInt2, paramInt1, ((View)localObject).getMeasuredWidth() + paramInt2, mMenuButton.getMeasuredHeight() + paramInt1);
    paramInt2 = i - mImageToggle.getMeasuredWidth() / 2;
    paramInt3 = mMenuButton.getMeasuredHeight() / 2 + paramInt1 - mImageToggle.getMeasuredHeight() / 2;
    localObject = mImageToggle;
    ((View)localObject).layout(paramInt2, paramInt3, ((View)localObject).getMeasuredWidth() + paramInt2, mImageToggle.getMeasuredHeight() + paramInt3);
    if (j != 0) {
      paramInt1 = mMenuButton.getMeasuredHeight() + paramInt1 + mButtonSpacing;
    }
    paramInt4 = mButtonsCount - 1;
    while (paramInt4 >= 0)
    {
      localObject = getChildAt(paramInt4);
      FloatingActionButton localFloatingActionButton;
      if (localObject != mImageToggle)
      {
        localFloatingActionButton = (FloatingActionButton)localObject;
        if (localFloatingActionButton.getVisibility() == 8) {}
      }
      else
      {
        paramInt2 = i - localFloatingActionButton.getMeasuredWidth() / 2;
        int k = paramInt1;
        if (j != 0) {
          k = paramInt1 - localFloatingActionButton.getMeasuredHeight() - mButtonSpacing;
        }
        if (localFloatingActionButton != mMenuButton)
        {
          localFloatingActionButton.layout(paramInt2, k, localFloatingActionButton.getMeasuredWidth() + paramInt2, localFloatingActionButton.getMeasuredHeight() + k);
          if (!mIsMenuOpening) {
            localFloatingActionButton.hide(false);
          }
        }
        View localView = (View)localFloatingActionButton.getTag(R.id.fab_label);
        if (localView != null)
        {
          if (fab_label) {
            paramInt1 = mMaxButtonWidth;
          } else {
            paramInt1 = localFloatingActionButton.getMeasuredWidth();
          }
          paramInt1 = paramInt1 / 2 + mLabelsMargin;
          if (mLabelsPosition == 0) {
            paramInt1 = i - paramInt1;
          } else {
            paramInt1 = i + paramInt1;
          }
          if (mLabelsPosition == 0) {
            paramInt2 = paramInt1 - localView.getMeasuredWidth();
          } else {
            paramInt2 = localView.getMeasuredWidth() + paramInt1;
          }
          if (mLabelsPosition == 0) {
            paramInt3 = paramInt2;
          } else {
            paramInt3 = paramInt1;
          }
          if (mLabelsPosition != 0) {
            paramInt1 = paramInt2;
          }
          paramInt2 = k - mLabelsVerticalOffset + (localFloatingActionButton.getMeasuredHeight() - localView.getMeasuredHeight()) / 2;
          localView.layout(paramInt3, paramInt2, paramInt1, paramInt2 + localView.getMeasuredHeight());
          if (!mIsMenuOpening) {
            localView.setVisibility(4);
          }
        }
        if (j != 0) {
          paramInt1 = k - mButtonSpacing;
        } else {
          paramInt1 = ((View)localObject).getMeasuredHeight() + k + mButtonSpacing;
        }
      }
      paramInt4 -= 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    mMaxButtonWidth = 0;
    int j = 0;
    measureChildWithMargins(mImageToggle, paramInt1, 0, paramInt2, 0);
    int i = 0;
    View localView;
    while (i < mButtonsCount)
    {
      localView = getChildAt(i);
      if ((localView.getVisibility() != 8) && (localView != mImageToggle))
      {
        measureChildWithMargins(localView, paramInt1, 0, paramInt2, 0);
        mMaxButtonWidth = Math.max(mMaxButtonWidth, localView.getMeasuredWidth());
      }
      i += 1;
    }
    i = 0;
    int n;
    for (int k = 0;; k = n)
    {
      int m = mButtonsCount;
      int i2 = 1;
      if (i >= m) {
        break;
      }
      localView = getChildAt(i);
      m = j;
      n = k;
      if (localView.getVisibility() != 8) {
        if (localView == mImageToggle)
        {
          m = j;
          n = k;
        }
        else
        {
          int i3 = localView.getMeasuredWidth();
          int i1 = k + localView.getMeasuredHeight();
          Label localLabel = (Label)localView.getTag(R.id.fab_label);
          m = j;
          n = i1;
          if (localLabel != null)
          {
            m = mMaxButtonWidth;
            n = localView.getMeasuredWidth();
            if (fab_label) {
              k = i2;
            } else {
              k = 2;
            }
            k = (m - n) / k;
            measureChildWithMargins(localLabel, paramInt1, localView.getMeasuredWidth() + localLabel.calculateShadowWidth() + mLabelsMargin + k, paramInt2, 0);
            m = Math.max(j, 0 + i3 + localLabel.getMeasuredWidth() + k);
            n = i1;
          }
        }
      }
      i += 1;
      j = m;
    }
    i = Math.max(mMaxButtonWidth, mLabelsMargin + j) + getPaddingLeft() + getPaddingRight();
    j = adjustForOvershoot(k + (mButtonSpacing * (mButtonsCount - 1) + getPaddingTop() + getPaddingBottom()));
    if (getLayoutParamswidth == -1) {
      i = View.getDefaultSize(getSuggestedMinimumWidth(), paramInt1);
    }
    paramInt1 = j;
    if (getLayoutParamsheight == -1) {
      paramInt1 = View.getDefaultSize(getSuggestedMinimumHeight(), paramInt2);
    }
    setMeasuredDimension(i, paramInt1);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (mIsSetClosedOnTouchOutside)
    {
      int i = paramMotionEvent.getAction();
      if (i != 0)
      {
        if (i != 1) {
          return false;
        }
        close(mIsAnimated);
        return true;
      }
      return isOpened();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void open(boolean paramBoolean)
  {
    if (!isOpened())
    {
      if (isBackgroundEnabled()) {
        mShowBackgroundAnimator.start();
      }
      Object localObject;
      if (mIconAnimated)
      {
        localObject = mIconToggleSet;
        if (localObject != null)
        {
          ((AnimatorSet)localObject).start();
        }
        else
        {
          mCloseAnimatorSet.cancel();
          mOpenAnimatorSet.start();
        }
      }
      int j = 0;
      int k = 0;
      mIsMenuOpening = true;
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        localObject = getChildAt(i);
        int n = j;
        int m = k;
        if ((localObject instanceof FloatingActionButton))
        {
          n = j;
          m = k;
          if (((View)localObject).getVisibility() != 8)
          {
            m = k + 1;
            localObject = (FloatingActionButton)localObject;
            mUiHandler.postDelayed(new FloatingActionMenu.d(this, (FloatingActionButton)localObject, paramBoolean), j);
            n = j + mAnimationDelayPerItem;
          }
        }
        i -= 1;
        j = n;
        k = m;
      }
      mUiHandler.postDelayed(new FloatingActionMenu.e(this), mAnimationDelayPerItem * (k + 1));
    }
  }
  
  public void setAnimated(boolean paramBoolean)
  {
    mIsAnimated = paramBoolean;
    AnimatorSet localAnimatorSet = mOpenAnimatorSet;
    long l2 = 300L;
    long l1;
    if (paramBoolean) {
      l1 = 300L;
    } else {
      l1 = 0L;
    }
    localAnimatorSet.setDuration(l1);
    localAnimatorSet = mCloseAnimatorSet;
    if (paramBoolean) {
      l1 = l2;
    } else {
      l1 = 0L;
    }
    localAnimatorSet.setDuration(l1);
  }
  
  public void setAnimationDelayPerItem(int paramInt)
  {
    mAnimationDelayPerItem = paramInt;
  }
  
  public void setClosedOnTouchOutside(boolean paramBoolean)
  {
    mIsSetClosedOnTouchOutside = paramBoolean;
  }
  
  public void setIconAnimated(boolean paramBoolean)
  {
    mIconAnimated = paramBoolean;
  }
  
  public void setIconAnimationCloseInterpolator(Interpolator paramInterpolator)
  {
    mCloseAnimatorSet.setInterpolator(paramInterpolator);
  }
  
  public void setIconAnimationInterpolator(Interpolator paramInterpolator)
  {
    mOpenAnimatorSet.setInterpolator(paramInterpolator);
    mCloseAnimatorSet.setInterpolator(paramInterpolator);
  }
  
  public void setIconAnimationOpenInterpolator(Interpolator paramInterpolator)
  {
    mOpenAnimatorSet.setInterpolator(paramInterpolator);
  }
  
  public void setIconToggleAnimatorSet(AnimatorSet paramAnimatorSet)
  {
    mIconToggleSet = paramAnimatorSet;
  }
  
  public void setMenuButtonColorNormal(int paramInt)
  {
    mMenuColorNormal = paramInt;
    mMenuButton.setColorNormal(paramInt);
  }
  
  public void setMenuButtonColorNormalResId(int paramInt)
  {
    mMenuColorNormal = getResources().getColor(paramInt);
    mMenuButton.setColorNormalResId(paramInt);
  }
  
  public void setMenuButtonColorPressed(int paramInt)
  {
    mMenuColorPressed = paramInt;
    mMenuButton.setColorPressed(paramInt);
  }
  
  public void setMenuButtonColorPressedResId(int paramInt)
  {
    mMenuColorPressed = getResources().getColor(paramInt);
    mMenuButton.setColorPressedResId(paramInt);
  }
  
  public void setMenuButtonColorRipple(int paramInt)
  {
    mMenuColorRipple = paramInt;
    mMenuButton.setColorRipple(paramInt);
  }
  
  public void setMenuButtonColorRippleResId(int paramInt)
  {
    mMenuColorRipple = getResources().getColor(paramInt);
    mMenuButton.setColorRippleResId(paramInt);
  }
  
  public void setMenuButtonHideAnimation(Animation paramAnimation)
  {
    mMenuButton.setHideAnimation(paramAnimation);
  }
  
  public void setMenuButtonLabelText(String paramString)
  {
    mMenuButton.setLabelText(paramString);
  }
  
  public void setMenuButtonShowAnimation(Animation paramAnimation)
  {
    mMenuButton.setShowAnimation(paramAnimation);
  }
  
  public void setOnMenuButtonClickListener(View.OnClickListener paramOnClickListener)
  {
    mMenuButton.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnMenuButtonLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    mMenuButton.setOnLongClickListener(paramOnLongClickListener);
  }
  
  public void setOnMenuToggleListener(h paramH)
  {
    mToggleListener = paramH;
  }
  
  public void toggle(boolean paramBoolean)
  {
    if (isOpened())
    {
      close(paramBoolean);
      return;
    }
    open(paramBoolean);
  }
  
  public abstract interface h
  {
    public abstract void getMediaCount(boolean paramBoolean);
  }
}
