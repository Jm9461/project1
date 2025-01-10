package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import org.org.v4.util.R.attr;
import org.org.v4.util.R.color;
import org.org.v4.util.R.style;
import org.org.v4.util.R.styleable;

public class CardView
  extends FrameLayout
{
  private static final int[] COLOR_BACKGROUND_ATTR = { 16842801 };
  private static final CardViewImpl IMPL;
  private boolean mCompatPadding;
  final Rect mContentPadding = new Rect();
  private final CardViewDelegate mContext = new a();
  private boolean mPreventCornerOverlap;
  final Rect mShadowBounds = new Rect();
  int mUserSetMinHeight;
  int mUserSetMinWidth;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      IMPL = new CardViewApi21();
    } else if (i >= 17) {
      IMPL = new CardViewJellybeanMr1();
    } else {
      IMPL = new CardViewEclairMr1();
    }
    IMPL.initStatic();
  }
  
  public CardView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CardView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.cardViewStyle);
  }
  
  public CardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CardView, paramInt, R.style.CardView);
    if (localTypedArray.hasValue(R.styleable.CardView_cardBackgroundColor))
    {
      paramAttributeSet = localTypedArray.getColorStateList(R.styleable.CardView_cardBackgroundColor);
    }
    else
    {
      paramAttributeSet = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
      paramInt = paramAttributeSet.getColor(0, 0);
      paramAttributeSet.recycle();
      paramAttributeSet = new float[3];
      Color.colorToHSV(paramInt, paramAttributeSet);
      if (paramAttributeSet[2] > 0.5F) {
        paramInt = getResources().getColor(R.color.cardview_light_background);
      } else {
        paramInt = getResources().getColor(R.color.cardview_dark_background);
      }
      paramAttributeSet = ColorStateList.valueOf(paramInt);
    }
    float f4 = localTypedArray.getDimension(R.styleable.CardView_cardCornerRadius, 0.0F);
    float f2 = localTypedArray.getDimension(R.styleable.CardView_cardElevation, 0.0F);
    float f3 = localTypedArray.getDimension(R.styleable.CardView_cardMaxElevation, 0.0F);
    mCompatPadding = localTypedArray.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
    mPreventCornerOverlap = localTypedArray.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
    paramInt = localTypedArray.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
    mContentPadding.left = localTypedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, paramInt);
    mContentPadding.top = localTypedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, paramInt);
    mContentPadding.right = localTypedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, paramInt);
    mContentPadding.bottom = localTypedArray.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, paramInt);
    float f1 = f3;
    if (f2 > f3) {
      f1 = f2;
    }
    mUserSetMinWidth = localTypedArray.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
    mUserSetMinHeight = localTypedArray.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
    localTypedArray.recycle();
    IMPL.initialize(mContext, paramContext, paramAttributeSet, f4, f2, f1);
  }
  
  public ColorStateList getCardBackgroundColor()
  {
    return IMPL.setMaxElevation(mContext);
  }
  
  public float getCardElevation()
  {
    return IMPL.getElevation(mContext);
  }
  
  public int getContentPaddingBottom()
  {
    return mContentPadding.bottom;
  }
  
  public int getContentPaddingLeft()
  {
    return mContentPadding.left;
  }
  
  public int getContentPaddingRight()
  {
    return mContentPadding.right;
  }
  
  public int getContentPaddingTop()
  {
    return mContentPadding.top;
  }
  
  public float getMaxCardElevation()
  {
    return IMPL.getMaxElevation(mContext);
  }
  
  public boolean getPreventCornerOverlap()
  {
    return mPreventCornerOverlap;
  }
  
  public float getRadius()
  {
    return IMPL.getRadius(mContext);
  }
  
  public boolean getUseCompatPadding()
  {
    return mCompatPadding;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!(IMPL instanceof CardViewApi21))
    {
      int i = View.MeasureSpec.getMode(paramInt1);
      if ((i == Integer.MIN_VALUE) || (i == 1073741824)) {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinHeight(mContext)), View.MeasureSpec.getSize(paramInt1)), i);
      }
      i = View.MeasureSpec.getMode(paramInt2);
      if ((i == Integer.MIN_VALUE) || (i == 1073741824)) {
        paramInt2 = View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinWidth(mContext)), View.MeasureSpec.getSize(paramInt2)), i);
      }
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setCardBackgroundColor(int paramInt)
  {
    IMPL.setBackgroundColor(mContext, ColorStateList.valueOf(paramInt));
  }
  
  public void setCardBackgroundColor(ColorStateList paramColorStateList)
  {
    IMPL.setBackgroundColor(mContext, paramColorStateList);
  }
  
  public void setCardElevation(float paramFloat)
  {
    IMPL.setElevation(mContext, paramFloat);
  }
  
  public void setMaxCardElevation(float paramFloat)
  {
    IMPL.setMaxElevation(mContext, paramFloat);
  }
  
  public void setMinimumHeight(int paramInt)
  {
    mUserSetMinHeight = paramInt;
    super.setMinimumHeight(paramInt);
  }
  
  public void setMinimumWidth(int paramInt)
  {
    mUserSetMinWidth = paramInt;
    super.setMinimumWidth(paramInt);
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void setPaddingRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void setPreventCornerOverlap(boolean paramBoolean)
  {
    if (paramBoolean != mPreventCornerOverlap)
    {
      mPreventCornerOverlap = paramBoolean;
      IMPL.onPreventCornerOverlapChanged(mContext);
    }
  }
  
  public void setRadius(float paramFloat)
  {
    IMPL.setRadius(mContext, paramFloat);
  }
  
  public void setUseCompatPadding(boolean paramBoolean)
  {
    if (mCompatPadding != paramBoolean)
    {
      mCompatPadding = paramBoolean;
      IMPL.onCompatPaddingChanged(mContext);
    }
  }
  
  class a
    implements CardViewDelegate
  {
    private Drawable mBackground;
    
    a() {}
    
    public View get()
    {
      return CardView.this;
    }
    
    public Drawable getBackground()
    {
      return mBackground;
    }
    
    public boolean getPreventCornerOverlap()
    {
      return CardView.this.getPreventCornerOverlap();
    }
    
    public boolean getUseCompatPadding()
    {
      return CardView.this.getUseCompatPadding();
    }
    
    public void setBackgroundDrawable(Drawable paramDrawable)
    {
      mBackground = paramDrawable;
      CardView.this.setBackgroundDrawable(paramDrawable);
    }
    
    public void setMinWidthHeightInternal(int paramInt1, int paramInt2)
    {
      CardView localCardView = CardView.this;
      if (paramInt1 > mUserSetMinWidth) {
        CardView.setMinimumWidth(localCardView, paramInt1);
      }
      localCardView = CardView.this;
      if (paramInt2 > mUserSetMinHeight) {
        CardView.initialize(localCardView, paramInt2);
      }
    }
    
    public void setShadowPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      mShadowBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      CardView localCardView = CardView.this;
      Rect localRect = mContentPadding;
      CardView.setShadowPadding(localCardView, left + paramInt1, top + paramInt2, right + paramInt3, bottom + paramInt4);
    }
  }
}
