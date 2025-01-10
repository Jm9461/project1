package apps.muzei.api.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import apps.muzei.api.R.styleable;
import apps.muzei.api.internal.ThemeUtil;

public class f
{
  private int a;
  private int b = 400;
  private int c;
  private Drawable d;
  private int f = 200;
  private int g;
  private int h;
  private int i;
  private int l;
  private int mPaddingBottom;
  private int mPaddingLeft;
  private int mPaddingRight;
  private int mPaddingTop;
  private int n;
  private Interpolator q;
  private int s;
  private int t;
  private int u;
  private Interpolator x;
  private int y;
  
  public f(Context paramContext, int paramInt)
  {
    this(paramContext, null, 0, paramInt);
  }
  
  public f(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RippleDrawable, paramInt1, paramInt2);
    add(paramAttributeSet.getColor(R.styleable.RippleDrawable_rd_backgroundColor, 0));
    c(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_backgroundAnimDuration, paramContext.getResources().getInteger(17694721)));
    setDefaultShowAsAction(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_rippleType, 0));
    g(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_delayClick, 0));
    load(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_delayRipple, 0));
    paramInt1 = ThemeUtil.getType(paramAttributeSet, R.styleable.RippleDrawable_rd_maxRippleRadius);
    if ((paramInt1 >= 16) && (paramInt1 <= 31)) {
      height(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_maxRippleRadius, -1));
    } else {
      height(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_maxRippleRadius, ThemeUtil.dpToPx(paramContext, 48)));
    }
    setColor(paramAttributeSet.getColor(R.styleable.RippleDrawable_rd_rippleColor, ThemeUtil.colorControlHighlight(paramContext, 0)));
    e(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_rippleAnimDuration, paramContext.getResources().getInteger(17694721)));
    paramInt1 = paramAttributeSet.getResourceId(R.styleable.RippleDrawable_rd_inInterpolator, 0);
    if (paramInt1 != 0) {
      a(AnimationUtils.loadInterpolator(paramContext, paramInt1));
    }
    paramInt1 = paramAttributeSet.getResourceId(R.styleable.RippleDrawable_rd_outInterpolator, 0);
    if (paramInt1 != 0) {
      f(AnimationUtils.loadInterpolator(paramContext, paramInt1));
    }
    f(paramAttributeSet.getInteger(R.styleable.RippleDrawable_rd_maskType, 0));
    padding(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_cornerRadius, 0));
    paddingTop(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topLeftCornerRadius, mPaddingTop));
    paddingRight(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topRightCornerRadius, mPaddingRight));
    paddingLeft(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomRightCornerRadius, mPaddingLeft));
    paddingBottom(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomLeftCornerRadius, mPaddingBottom));
    a(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_padding, 0));
    close(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_leftPadding, i));
    clear(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_rightPadding, t));
    d(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topPadding, s));
    b(paramAttributeSet.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomPadding, u));
    paramAttributeSet.recycle();
  }
  
  public RippleDrawable a()
  {
    if (q == null) {
      q = new AccelerateInterpolator();
    }
    if (x == null) {
      x = new DecelerateInterpolator();
    }
    return new RippleDrawable(d, f, c, a, l, n, h, b, g, q, x, y, mPaddingTop, mPaddingRight, mPaddingLeft, mPaddingBottom, i, s, t, u, null);
  }
  
  public f a(int paramInt)
  {
    i = paramInt;
    s = paramInt;
    t = paramInt;
    u = paramInt;
    return this;
  }
  
  public f a(Drawable paramDrawable)
  {
    d = paramDrawable;
    return this;
  }
  
  public f a(Interpolator paramInterpolator)
  {
    q = paramInterpolator;
    return this;
  }
  
  public f add(int paramInt)
  {
    c = paramInt;
    return this;
  }
  
  public f b(int paramInt)
  {
    u = paramInt;
    return this;
  }
  
  public f c(int paramInt)
  {
    f = paramInt;
    return this;
  }
  
  public f clear(int paramInt)
  {
    t = paramInt;
    return this;
  }
  
  public f close(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public f d(int paramInt)
  {
    s = paramInt;
    return this;
  }
  
  public f e(int paramInt)
  {
    b = paramInt;
    return this;
  }
  
  public f f(int paramInt)
  {
    y = paramInt;
    return this;
  }
  
  public f f(Interpolator paramInterpolator)
  {
    x = paramInterpolator;
    return this;
  }
  
  public f g(int paramInt)
  {
    l = paramInt;
    return this;
  }
  
  public f height(int paramInt)
  {
    h = paramInt;
    return this;
  }
  
  public f load(int paramInt)
  {
    n = paramInt;
    return this;
  }
  
  public f padding(int paramInt)
  {
    mPaddingTop = paramInt;
    mPaddingRight = paramInt;
    mPaddingBottom = paramInt;
    mPaddingLeft = paramInt;
    return this;
  }
  
  public f paddingBottom(int paramInt)
  {
    mPaddingBottom = paramInt;
    return this;
  }
  
  public f paddingLeft(int paramInt)
  {
    mPaddingLeft = paramInt;
    return this;
  }
  
  public f paddingRight(int paramInt)
  {
    mPaddingRight = paramInt;
    return this;
  }
  
  public f paddingTop(int paramInt)
  {
    mPaddingTop = paramInt;
    return this;
  }
  
  public f setColor(int paramInt)
  {
    g = paramInt;
    return this;
  }
  
  public f setDefaultShowAsAction(int paramInt)
  {
    a = paramInt;
    return this;
  }
}
