package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import org.org.android.asm.Item;
import org.org.jaxen.expr.MathUtils;
import org.org.jaxen.text.TextDirectionHeuristicCompat;
import org.org.jaxen.text.TextDirectionHeuristicsCompat;
import org.org.v4.content.R.styleable;

public final class CollapsingTextHelper
{
  private static final Paint DEBUG_DRAW_PAINT;
  private static final boolean USE_SCALING_TEXTURE;
  private int[] DISABLED_STATE_SET;
  private boolean mBoundsChanged;
  private final Rect mCollapsedBounds;
  private float mCollapsedDrawX;
  private float mCollapsedDrawY;
  private int mCollapsedShadowColor;
  private float mCollapsedShadowDx;
  private float mCollapsedShadowDy;
  private float mCollapsedShadowRadius;
  private ColorStateList mCollapsedTextColor;
  private int mCollapsedTextGravity = 16;
  private float mCollapsedTextSize = 15.0F;
  private Typeface mCollapsedTypeface;
  private final RectF mCurrentBounds;
  private float mCurrentDrawX;
  private float mCurrentDrawY;
  private float mCurrentTextSize;
  private Typeface mCurrentTypeface;
  private boolean mDrawTitle;
  private final Rect mExpandedBounds;
  private float mExpandedDrawX;
  private float mExpandedDrawY;
  private float mExpandedFraction;
  private int mExpandedShadowColor;
  private float mExpandedShadowDx;
  private float mExpandedShadowDy;
  private float mExpandedShadowRadius;
  private ColorStateList mExpandedTextColor;
  private int mExpandedTextGravity = 16;
  private float mExpandedTextSize = 15.0F;
  private Bitmap mExpandedTitleTexture;
  private Typeface mExpandedTypeface;
  private boolean mIsRtl;
  private final TextPaint mPaint;
  private TimeInterpolator mPositionInterpolator;
  private float mScale;
  private CharSequence mText;
  private final TextPaint mTextPaint;
  private TimeInterpolator mTextSizeInterpolator;
  private CharSequence mTextToDraw;
  private float mTextureAscent;
  private float mTextureDescent;
  private Paint mTexturePaint;
  private boolean mUseTexture;
  private final View mView;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 18) {
      bool = true;
    } else {
      bool = false;
    }
    USE_SCALING_TEXTURE = bool;
    DEBUG_DRAW_PAINT = null;
    Paint localPaint = DEBUG_DRAW_PAINT;
    if (localPaint != null)
    {
      localPaint.setAntiAlias(true);
      DEBUG_DRAW_PAINT.setColor(-65281);
    }
  }
  
  public CollapsingTextHelper(View paramView)
  {
    mView = paramView;
    mTextPaint = new TextPaint(129);
    mPaint = new TextPaint(mTextPaint);
    mCollapsedBounds = new Rect();
    mExpandedBounds = new Rect();
    mCurrentBounds = new RectF();
  }
  
  private static int blendColors(int paramInt1, int paramInt2, float paramFloat)
  {
    float f1 = 1.0F - paramFloat;
    float f2 = Color.alpha(paramInt1);
    float f3 = Color.alpha(paramInt2);
    float f4 = Color.red(paramInt1);
    float f5 = Color.red(paramInt2);
    float f6 = Color.green(paramInt1);
    float f7 = Color.green(paramInt2);
    float f8 = Color.blue(paramInt1);
    float f9 = Color.blue(paramInt2);
    return Color.argb((int)(f2 * f1 + f3 * paramFloat), (int)(f4 * f1 + f5 * paramFloat), (int)(f6 * f1 + f7 * paramFloat), (int)(f8 * f1 + f9 * paramFloat));
  }
  
  private void calculateBaseOffsets()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  private void calculateCurrentOffsets()
  {
    calculateOffsets(mExpandedFraction);
  }
  
  private boolean calculateIsRtl(CharSequence paramCharSequence)
  {
    int j = ViewCompat.getLayoutDirection(mView);
    int i = 1;
    if (j != 1) {
      i = 0;
    }
    TextDirectionHeuristicCompat localTextDirectionHeuristicCompat;
    if (i != 0) {
      localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL;
    } else {
      localTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    }
    return localTextDirectionHeuristicCompat.a(paramCharSequence, 0, paramCharSequence.length());
  }
  
  private void calculateOffsets(float paramFloat)
  {
    interpolateBounds(paramFloat);
    mCurrentDrawX = lerp(mExpandedDrawX, mCollapsedDrawX, paramFloat, mPositionInterpolator);
    mCurrentDrawY = lerp(mExpandedDrawY, mCollapsedDrawY, paramFloat, mPositionInterpolator);
    setInterpolatedTextSize(lerp(mExpandedTextSize, mCollapsedTextSize, paramFloat, mTextSizeInterpolator));
    if (mCollapsedTextColor != mExpandedTextColor) {
      mTextPaint.setColor(blendColors(createSwitchThumbColorStateList(), getDisabledThemeAttrColor(), paramFloat));
    } else {
      mTextPaint.setColor(getDisabledThemeAttrColor());
    }
    mTextPaint.setShadowLayer(lerp(mExpandedShadowRadius, mCollapsedShadowRadius, paramFloat, null), lerp(mExpandedShadowDx, mCollapsedShadowDx, paramFloat, null), lerp(mExpandedShadowDy, mCollapsedShadowDy, paramFloat, null), blendColors(mExpandedShadowColor, mCollapsedShadowColor, paramFloat));
    ViewCompat.postInvalidateOnAnimation(mView);
  }
  
  private void calculateUsingTextSize(float paramFloat)
  {
    if (mText == null) {
      return;
    }
    float f2 = mCollapsedBounds.width();
    float f3 = mExpandedBounds.width();
    int j = 0;
    int i = 0;
    float f1;
    Object localObject;
    Typeface localTypeface;
    if (isClose(paramFloat, mCollapsedTextSize))
    {
      f1 = mCollapsedTextSize;
      mScale = 1.0F;
      localObject = mCurrentTypeface;
      localTypeface = mCollapsedTypeface;
      if (localObject != localTypeface)
      {
        mCurrentTypeface = localTypeface;
        i = 1;
      }
      paramFloat = f2;
    }
    else
    {
      f1 = mExpandedTextSize;
      localObject = mCurrentTypeface;
      localTypeface = mExpandedTypeface;
      i = j;
      if (localObject != localTypeface)
      {
        mCurrentTypeface = localTypeface;
        i = 1;
      }
      if (isClose(paramFloat, mExpandedTextSize)) {
        mScale = 1.0F;
      } else {
        mScale = (paramFloat / mExpandedTextSize);
      }
      paramFloat = mCollapsedTextSize / mExpandedTextSize;
      if (f3 * paramFloat > f2) {
        paramFloat = Math.min(f2 / paramFloat, f3);
      } else {
        paramFloat = f3;
      }
    }
    boolean bool = true;
    j = i;
    if (paramFloat > 0.0F)
    {
      if ((mCurrentTextSize == f1) && (!mBoundsChanged) && (i == 0)) {
        i = 0;
      } else {
        i = 1;
      }
      mCurrentTextSize = f1;
      mBoundsChanged = false;
      j = i;
    }
    if ((mTextToDraw == null) || (j != 0))
    {
      mTextPaint.setTextSize(mCurrentTextSize);
      mTextPaint.setTypeface(mCurrentTypeface);
      localObject = mTextPaint;
      if (mScale == 1.0F) {
        bool = false;
      }
      ((Paint)localObject).setLinearText(bool);
      localObject = TextUtils.ellipsize(mText, mTextPaint, paramFloat, TextUtils.TruncateAt.END);
      if (!TextUtils.equals((CharSequence)localObject, mTextToDraw))
      {
        mTextToDraw = ((CharSequence)localObject);
        mIsRtl = calculateIsRtl(mTextToDraw);
      }
    }
  }
  
  private void calculateUsingTextSize(TextPaint paramTextPaint)
  {
    paramTextPaint.setTextSize(mCollapsedTextSize);
    paramTextPaint.setTypeface(mCollapsedTypeface);
  }
  
  private void clearTexture()
  {
    Bitmap localBitmap = mExpandedTitleTexture;
    if (localBitmap != null)
    {
      localBitmap.recycle();
      mExpandedTitleTexture = null;
    }
  }
  
  private int createSwitchThumbColorStateList()
  {
    int[] arrayOfInt = DISABLED_STATE_SET;
    if (arrayOfInt != null) {
      return mExpandedTextColor.getColorForState(arrayOfInt, 0);
    }
    return mExpandedTextColor.getDefaultColor();
  }
  
  private void ensureExpandedTexture()
  {
    if ((mExpandedTitleTexture == null) && (!mExpandedBounds.isEmpty()))
    {
      if (TextUtils.isEmpty(mTextToDraw)) {
        return;
      }
      calculateOffsets(0.0F);
      mTextureAscent = mTextPaint.ascent();
      mTextureDescent = mTextPaint.descent();
      Object localObject = mTextPaint;
      CharSequence localCharSequence = mTextToDraw;
      int i = Math.round(((Paint)localObject).measureText(localCharSequence, 0, localCharSequence.length()));
      int j = Math.round(mTextureDescent - mTextureAscent);
      if (i > 0)
      {
        if (j <= 0) {
          return;
        }
        mExpandedTitleTexture = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        localObject = new Canvas(mExpandedTitleTexture);
        localCharSequence = mTextToDraw;
        ((Canvas)localObject).drawText(localCharSequence, 0, localCharSequence.length(), 0.0F, j - mTextPaint.descent(), mTextPaint);
        if (mTexturePaint == null) {
          mTexturePaint = new Paint(3);
        }
      }
    }
  }
  
  private void interpolateBounds(float paramFloat)
  {
    mCurrentBounds.left = lerp(mExpandedBounds.left, mCollapsedBounds.left, paramFloat, mPositionInterpolator);
    mCurrentBounds.top = lerp(mExpandedDrawY, mCollapsedDrawY, paramFloat, mPositionInterpolator);
    mCurrentBounds.right = lerp(mExpandedBounds.right, mCollapsedBounds.right, paramFloat, mPositionInterpolator);
    mCurrentBounds.bottom = lerp(mExpandedBounds.bottom, mCollapsedBounds.bottom, paramFloat, mPositionInterpolator);
  }
  
  private static boolean isClose(float paramFloat1, float paramFloat2)
  {
    return Math.abs(paramFloat1 - paramFloat2) < 0.001F;
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3, TimeInterpolator paramTimeInterpolator)
  {
    float f = paramFloat3;
    if (paramTimeInterpolator != null) {
      f = paramTimeInterpolator.getInterpolation(paramFloat3);
    }
    return Item.lerp(paramFloat1, paramFloat2, f);
  }
  
  private Typeface readFontFamilyTypeface(int paramInt)
  {
    TypedArray localTypedArray = mView.getContext().obtainStyledAttributes(paramInt, new int[] { 16843692 });
    try
    {
      Object localObject = localTypedArray.getString(0);
      if (localObject != null)
      {
        localObject = Typeface.create((String)localObject, 0);
        localTypedArray.recycle();
        return localObject;
      }
      localTypedArray.recycle();
      return null;
    }
    catch (Throwable localThrowable)
    {
      localTypedArray.recycle();
      throw localThrowable;
    }
  }
  
  private static boolean rectEquals(Rect paramRect, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return (left == paramInt1) && (top == paramInt2) && (right == paramInt3) && (bottom == paramInt4);
  }
  
  private void setInterpolatedTextSize(float paramFloat)
  {
    calculateUsingTextSize(paramFloat);
    boolean bool;
    if ((USE_SCALING_TEXTURE) && (mScale != 1.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    mUseTexture = bool;
    if (mUseTexture) {
      ensureExpandedTexture();
    }
    ViewCompat.postInvalidateOnAnimation(mView);
  }
  
  public float draw()
  {
    calculateUsingTextSize(mPaint);
    return -mPaint.ascent();
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = paramCanvas.save();
    if ((mTextToDraw != null) && (mDrawTitle))
    {
      float f4 = mCurrentDrawX;
      float f3 = mCurrentDrawY;
      int i;
      if ((mUseTexture) && (mExpandedTitleTexture != null)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0)
      {
        f2 = mTextureAscent * mScale;
      }
      else
      {
        f1 = mTextPaint.ascent();
        f2 = mScale;
        mTextPaint.descent();
        f2 = f1 * f2;
      }
      float f1 = f3;
      if (i != 0) {
        f1 = f3 + f2;
      }
      float f2 = mScale;
      if (f2 != 1.0F) {
        paramCanvas.scale(f2, f2, f4, f1);
      }
      if (i != 0)
      {
        paramCanvas.drawBitmap(mExpandedTitleTexture, f4, f1, mTexturePaint);
      }
      else
      {
        CharSequence localCharSequence = mTextToDraw;
        paramCanvas.drawText(localCharSequence, 0, localCharSequence.length(), f4, f1, mTextPaint);
      }
    }
    paramCanvas.restoreToCount(j);
  }
  
  public void draw(RectF paramRectF)
  {
    boolean bool = calculateIsRtl(mText);
    Rect localRect = mCollapsedBounds;
    float f;
    if (!bool) {
      f = left;
    } else {
      f = right - setInterpolatedTextSize();
    }
    left = f;
    localRect = mCollapsedBounds;
    top = top;
    if (!bool) {
      f = left + setInterpolatedTextSize();
    } else {
      f = right;
    }
    right = f;
    bottom = (mCollapsedBounds.top + draw());
  }
  
  public ColorStateList getCollapsedTextColor()
  {
    return mCollapsedTextColor;
  }
  
  public int getDisabledThemeAttrColor()
  {
    int[] arrayOfInt = DISABLED_STATE_SET;
    if (arrayOfInt != null) {
      return mCollapsedTextColor.getColorForState(arrayOfInt, 0);
    }
    return mCollapsedTextColor.getDefaultColor();
  }
  
  public float getExpansionFraction()
  {
    return mExpandedFraction;
  }
  
  void onBoundsChanged()
  {
    boolean bool;
    if ((mCollapsedBounds.width() > 0) && (mCollapsedBounds.height() > 0) && (mExpandedBounds.width() > 0) && (mExpandedBounds.height() > 0)) {
      bool = true;
    } else {
      bool = false;
    }
    mDrawTitle = bool;
  }
  
  public void recalculate()
  {
    if ((mView.getHeight() > 0) && (mView.getWidth() > 0))
    {
      calculateBaseOffsets();
      calculateCurrentOffsets();
    }
  }
  
  public void setCollapsedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(mCollapsedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      mCollapsedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  public void setCollapsedTextAppearance(int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      mCollapsedTextColor = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
    }
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize)) {
      mCollapsedTextSize = localTintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int)mCollapsedTextSize);
    }
    mCollapsedShadowColor = localTintTypedArray.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
    mCollapsedShadowDx = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0F);
    mCollapsedShadowDy = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0F);
    mCollapsedShadowRadius = localTintTypedArray.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0F);
    localTintTypedArray.recycle();
    if (Build.VERSION.SDK_INT >= 16) {
      mCollapsedTypeface = readFontFamilyTypeface(paramInt);
    }
    recalculate();
  }
  
  public void setCollapsedTextColor(ColorStateList paramColorStateList)
  {
    if (mCollapsedTextColor != paramColorStateList)
    {
      mCollapsedTextColor = paramColorStateList;
      recalculate();
    }
  }
  
  public void setCollapsedTextGravity(int paramInt)
  {
    if (mCollapsedTextGravity != paramInt)
    {
      mCollapsedTextGravity = paramInt;
      recalculate();
    }
  }
  
  public void setExpandedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!rectEquals(mExpandedBounds, paramInt1, paramInt2, paramInt3, paramInt4))
    {
      mExpandedBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
      mBoundsChanged = true;
      onBoundsChanged();
    }
  }
  
  public void setExpandedTextColor(ColorStateList paramColorStateList)
  {
    if (mExpandedTextColor != paramColorStateList)
    {
      mExpandedTextColor = paramColorStateList;
      recalculate();
    }
  }
  
  public void setExpandedTextGravity(int paramInt)
  {
    if (mExpandedTextGravity != paramInt)
    {
      mExpandedTextGravity = paramInt;
      recalculate();
    }
  }
  
  public void setExpandedTextSize(float paramFloat)
  {
    if (mExpandedTextSize != paramFloat)
    {
      mExpandedTextSize = paramFloat;
      recalculate();
    }
  }
  
  public void setExpansionFraction(float paramFloat)
  {
    paramFloat = MathUtils.constrain(paramFloat, 0.0F, 1.0F);
    if (paramFloat != mExpandedFraction)
    {
      mExpandedFraction = paramFloat;
      calculateCurrentOffsets();
    }
  }
  
  public float setInterpolatedTextSize()
  {
    if (mText == null) {
      return 0.0F;
    }
    calculateUsingTextSize(mPaint);
    TextPaint localTextPaint = mPaint;
    CharSequence localCharSequence = mText;
    return localTextPaint.measureText(localCharSequence, 0, localCharSequence.length());
  }
  
  public void setPositionInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    mPositionInterpolator = paramTimeInterpolator;
    recalculate();
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (!paramCharSequence.equals(mText)))
    {
      mText = paramCharSequence;
      mTextToDraw = null;
      clearTexture();
      recalculate();
    }
  }
  
  public final boolean setText()
  {
    ColorStateList localColorStateList = mCollapsedTextColor;
    if ((localColorStateList == null) || (!localColorStateList.isStateful())) {
      localColorStateList = mExpandedTextColor;
    }
    return (localColorStateList != null) && (localColorStateList.isStateful());
  }
  
  public final boolean setText(int[] paramArrayOfInt)
  {
    DISABLED_STATE_SET = paramArrayOfInt;
    if (setText())
    {
      recalculate();
      return true;
    }
    return false;
  }
  
  public void setTextSizeInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    mTextSizeInterpolator = paramTimeInterpolator;
    recalculate();
  }
  
  public void setTypefaces(Typeface paramTypeface)
  {
    mExpandedTypeface = paramTypeface;
    mCollapsedTypeface = paramTypeface;
    recalculate();
  }
}
