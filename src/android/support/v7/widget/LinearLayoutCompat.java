package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import org.org.v4.content.R.styleable;

public class LinearLayoutCompat
  extends ViewGroup
{
  private boolean mBaselineAligned = true;
  private int mBaselineAlignedChildIndex = -1;
  private int mBaselineChildTop = 0;
  private Drawable mDivider;
  private int mDividerHeight;
  private int mDividerPadding;
  private int mDividerWidth;
  private int mGravity = 8388659;
  private int[] mMaxAscent;
  private int[] mMaxDescent;
  private int mOrientation;
  private int mShowDividers;
  private int mTotalLength;
  private boolean mUseLargestChild;
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0) {
      setOrientation(paramInt);
    }
    paramInt = paramContext.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0) {
      setGravity(paramInt);
    }
    boolean bool = paramContext.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool) {
      setBaselineAligned(bool);
    }
    mWeightSum = paramContext.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    mBaselineAlignedChildIndex = paramContext.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    mUseLargestChild = paramContext.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(paramContext.getDrawable(R.styleable.LinearLayoutCompat_divider));
    mShowDividers = paramContext.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    mDividerPadding = paramContext.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    paramContext.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        o0.a localA = (o0.a)localView.getLayoutParams();
        if (height == -1)
        {
          int k = width;
          width = localView.getMeasuredWidth();
          measureChildWithMargins(localView, paramInt2, 0, j, 0);
          width = k;
        }
      }
      i += 1;
    }
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    int i = 0;
    while (i < paramInt1)
    {
      View localView = getVirtualChildAt(i);
      if (localView.getVisibility() != 8)
      {
        o0.a localA = (o0.a)localView.getLayoutParams();
        if (width == -1)
        {
          int k = height;
          height = localView.getMeasuredHeight();
          measureChildWithMargins(localView, j, 0, paramInt2, 0);
          height = k;
        }
      }
      i += 1;
    }
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.layout(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof o0.a;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas)
  {
    int k = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl(this);
    int i = 0;
    View localView;
    o0.a localA;
    while (i < k)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localA = (o0.a)localView.getLayoutParams();
        int j;
        if (bool) {
          j = localView.getRight() + rightMargin;
        } else {
          j = localView.getLeft() - leftMargin - mDividerWidth;
        }
        drawVerticalDivider(paramCanvas, j);
      }
      i += 1;
    }
    if (hasDividerBeforeChildAt(k))
    {
      localView = getVirtualChildAt(k - 1);
      if (localView == null)
      {
        if (bool) {
          i = getPaddingLeft();
        } else {
          i = getWidth() - getPaddingRight() - mDividerWidth;
        }
      }
      else
      {
        localA = (o0.a)localView.getLayoutParams();
        if (bool) {
          i = localView.getLeft() - leftMargin - mDividerWidth;
        } else {
          i = localView.getRight() + rightMargin;
        }
      }
      drawVerticalDivider(paramCanvas, i);
    }
  }
  
  void drawDividersVertical(Canvas paramCanvas)
  {
    int j = getVirtualChildCount();
    int i = 0;
    View localView;
    o0.a localA;
    while (i < j)
    {
      localView = getVirtualChildAt(i);
      if ((localView != null) && (localView.getVisibility() != 8) && (hasDividerBeforeChildAt(i)))
      {
        localA = (o0.a)localView.getLayoutParams();
        drawHorizontalDivider(paramCanvas, localView.getTop() - topMargin - mDividerHeight);
      }
      i += 1;
    }
    if (hasDividerBeforeChildAt(j))
    {
      localView = getVirtualChildAt(j - 1);
      if (localView == null)
      {
        i = getHeight() - getPaddingBottom() - mDividerHeight;
      }
      else
      {
        localA = (o0.a)localView.getLayoutParams();
        i = localView.getBottom() + bottomMargin;
      }
      drawHorizontalDivider(paramCanvas, i);
    }
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(getPaddingLeft() + mDividerPadding, paramInt, getWidth() - getPaddingRight() - mDividerPadding, mDividerHeight + paramInt);
    mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt)
  {
    mDivider.setBounds(paramInt, getPaddingTop() + mDividerPadding, mDividerWidth + paramInt, getHeight() - getPaddingBottom() - mDividerPadding);
    mDivider.draw(paramCanvas);
  }
  
  protected o0.a generateDefaultLayoutParams()
  {
    int i = mOrientation;
    if (i == 0) {
      return new o0.a(-2, -2);
    }
    if (i == 1) {
      return new o0.a(-1, -2);
    }
    return null;
  }
  
  public o0.a generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new o0.a(getContext(), paramAttributeSet);
  }
  
  protected o0.a generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new o0.a(paramLayoutParams);
  }
  
  public int getBaseline()
  {
    if (mBaselineAlignedChildIndex < 0) {
      return super.getBaseline();
    }
    int i = getChildCount();
    int j = mBaselineAlignedChildIndex;
    if (i > j)
    {
      View localView = getChildAt(j);
      int k = localView.getBaseline();
      if (k == -1)
      {
        if (mBaselineAlignedChildIndex == 0) {
          return -1;
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
      }
      j = mBaselineChildTop;
      i = j;
      if (mOrientation == 1)
      {
        int m = mGravity & 0x70;
        i = j;
        if (m != 48) {
          if (m != 16)
          {
            if (m != 80) {
              i = j;
            } else {
              i = getBottom() - getTop() - getPaddingBottom() - mTotalLength;
            }
          }
          else {
            i = j + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - mTotalLength) / 2;
          }
        }
      }
      return getLayoutParamstopMargin + i + k;
    }
    throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
  }
  
  public int getBaselineAlignedChildIndex()
  {
    return mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt)
  {
    return 0;
  }
  
  public Drawable getDividerDrawable()
  {
    return mDivider;
  }
  
  public int getDividerPadding()
  {
    return mDividerPadding;
  }
  
  public int getDividerWidth()
  {
    return mDividerWidth;
  }
  
  public int getGravity()
  {
    return mGravity;
  }
  
  int getLocationOffset(View paramView)
  {
    return 0;
  }
  
  int getNextLocationOffset(View paramView)
  {
    return 0;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public int getShowDividers()
  {
    return mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt)
  {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount()
  {
    return getChildCount();
  }
  
  public float getWeightSum()
  {
    return mWeightSum;
  }
  
  protected boolean hasDividerBeforeChildAt(int paramInt)
  {
    if (paramInt == 0)
    {
      if ((mShowDividers & 0x1) != 0) {
        return true;
      }
    }
    else if (paramInt == getChildCount())
    {
      if ((mShowDividers & 0x4) != 0) {
        return true;
      }
    }
    else if ((mShowDividers & 0x2) != 0)
    {
      paramInt -= 1;
      while (paramInt >= 0)
      {
        if (getChildAt(paramInt).getVisibility() != 8) {
          return true;
        }
        paramInt -= 1;
      }
      return false;
    }
    return false;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool1 = ViewUtils.isLayoutRtl(this);
    int k = getPaddingTop();
    int n = paramInt4 - paramInt2;
    int i1 = getPaddingBottom();
    int i2 = getPaddingBottom();
    int i3 = getVirtualChildCount();
    int i4 = mGravity;
    boolean bool2 = mBaselineAligned;
    int[] arrayOfInt1 = mMaxAscent;
    int[] arrayOfInt2 = mMaxDescent;
    paramInt2 = GravityCompat.getAbsoluteGravity(i4 & 0x800007, ViewCompat.getLayoutDirection(this));
    if (paramInt2 != 1)
    {
      if (paramInt2 != 5) {
        paramInt1 = getPaddingLeft();
      } else {
        paramInt1 = getPaddingLeft() + paramInt3 - paramInt1 - mTotalLength;
      }
    }
    else {
      paramInt1 = getPaddingLeft() + (paramInt3 - paramInt1 - mTotalLength) / 2;
    }
    int i;
    if (bool1)
    {
      i = i3 - 1;
      paramInt4 = -1;
    }
    else
    {
      i = 0;
      paramInt4 = 1;
    }
    paramInt2 = 0;
    for (paramInt3 = paramInt1; paramInt2 < i3; paramInt3 = paramInt1)
    {
      int i5 = i + paramInt4 * paramInt2;
      View localView = getVirtualChildAt(i5);
      int j;
      if (localView == null)
      {
        paramInt1 = paramInt3 + measureNullChild(i5);
        j = paramInt2;
      }
      else
      {
        paramInt1 = paramInt3;
        j = paramInt2;
        if (localView.getVisibility() != 8)
        {
          int i6 = localView.getMeasuredWidth();
          int i7 = localView.getMeasuredHeight();
          o0.a localA = (o0.a)localView.getLayoutParams();
          if ((bool2) && (height != -1)) {
            paramInt1 = localView.getBaseline();
          } else {
            paramInt1 = -1;
          }
          int m = gravity;
          j = m;
          if (m < 0) {
            j = i4 & 0x70;
          }
          j &= 0x70;
          if (j != 16)
          {
            if (j != 48)
            {
              if (j != 80)
              {
                paramInt1 = k;
              }
              else
              {
                j = n - i1 - i7 - bottomMargin;
                if (paramInt1 != -1)
                {
                  m = localView.getMeasuredHeight();
                  paramInt1 = j - (arrayOfInt2[2] - (m - paramInt1));
                }
                else
                {
                  paramInt1 = j;
                }
              }
            }
            else
            {
              j = topMargin + k;
              if (paramInt1 != -1) {
                paramInt1 = j + (arrayOfInt1[1] - paramInt1);
              } else {
                paramInt1 = j;
              }
            }
          }
          else {
            paramInt1 = (n - k - i2 - i7) / 2 + k + topMargin - bottomMargin;
          }
          j = paramInt3;
          if (hasDividerBeforeChildAt(i5)) {
            j = paramInt3 + mDividerWidth;
          }
          paramInt3 = j + leftMargin;
          setChildFrame(localView, paramInt3 + getLocationOffset(localView), paramInt1, i6, i7);
          paramInt1 = paramInt3 + (i6 + rightMargin + getNextLocationOffset(localView));
          j = paramInt2 + getChildrenSkipCount(localView, i5);
        }
      }
      paramInt2 = j + 1;
    }
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = paramInt3 - paramInt1;
    int k = getPaddingRight();
    int m = getPaddingRight();
    int n = getVirtualChildCount();
    int i1 = mGravity;
    paramInt1 = i1 & 0x70;
    if (paramInt1 != 16)
    {
      if (paramInt1 != 80) {
        paramInt1 = getPaddingTop();
      } else {
        paramInt1 = getPaddingTop() + paramInt4 - paramInt2 - mTotalLength;
      }
    }
    else {
      paramInt1 = getPaddingTop() + (paramInt4 - paramInt2 - mTotalLength) / 2;
    }
    paramInt2 = 0;
    while (paramInt2 < n)
    {
      View localView = getVirtualChildAt(paramInt2);
      if (localView == null)
      {
        paramInt3 = paramInt1 + measureNullChild(paramInt2);
        paramInt4 = paramInt2;
      }
      else
      {
        paramInt3 = paramInt1;
        paramInt4 = paramInt2;
        if (localView.getVisibility() != 8)
        {
          int i3 = localView.getMeasuredWidth();
          int i2 = localView.getMeasuredHeight();
          o0.a localA = (o0.a)localView.getLayoutParams();
          paramInt4 = gravity;
          paramInt3 = paramInt4;
          if (paramInt4 < 0) {
            paramInt3 = i1 & 0x800007;
          }
          paramInt3 = GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(this)) & 0x7;
          if (paramInt3 != 1)
          {
            if (paramInt3 != 5) {
              paramInt3 = leftMargin + i;
            } else {
              paramInt3 = j - k - i3 - rightMargin;
            }
          }
          else {
            paramInt3 = (j - i - m - i3) / 2 + i + leftMargin - rightMargin;
          }
          paramInt4 = paramInt1;
          if (hasDividerBeforeChildAt(paramInt2)) {
            paramInt4 = paramInt1 + mDividerHeight;
          }
          paramInt1 = paramInt4 + topMargin;
          setChildFrame(localView, paramInt3, paramInt1 + getLocationOffset(localView), i3, i2);
          paramInt3 = bottomMargin;
          i3 = getNextLocationOffset(localView);
          paramInt4 = paramInt2 + getChildrenSkipCount(localView, paramInt2);
          paramInt3 = paramInt1 + (i2 + paramInt3 + i3);
        }
      }
      paramInt2 = paramInt4 + 1;
      paramInt1 = paramInt3;
    }
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int i12 = getVirtualChildCount();
    int i9 = View.MeasureSpec.getMode(paramInt1);
    int i13 = View.MeasureSpec.getMode(paramInt2);
    if ((mMaxAscent == null) || (mMaxDescent == null))
    {
      mMaxAscent = new int[4];
      mMaxDescent = new int[4];
    }
    int[] arrayOfInt1 = mMaxAscent;
    int[] arrayOfInt2 = mMaxDescent;
    arrayOfInt1[3] = -1;
    arrayOfInt1[2] = -1;
    arrayOfInt1[1] = -1;
    arrayOfInt1[0] = -1;
    arrayOfInt2[3] = -1;
    arrayOfInt2[2] = -1;
    arrayOfInt2[1] = -1;
    arrayOfInt2[0] = -1;
    boolean bool1 = mBaselineAligned;
    int i3 = 0;
    boolean bool2 = mUseLargestChild;
    int i6;
    if (i9 == 1073741824) {
      i6 = 1;
    } else {
      i6 = 0;
    }
    int i4 = 0;
    int i2 = 0;
    int m = 0;
    int j = 1;
    int i1 = 0;
    float f1 = 0.0F;
    int k = 0;
    int i = 0;
    int n = 0;
    Object localObject1;
    int i8;
    Object localObject2;
    while (k < i12)
    {
      View localView = getVirtualChildAt(k);
      if (localView == null)
      {
        mTotalLength += measureNullChild(k);
        i7 = i2;
        i5 = m;
        m = k;
      }
      else
      {
        i7 = i2;
        if (localView.getVisibility() == 8)
        {
          k += getChildrenSkipCount(localView, k);
          i7 = i2;
          i5 = m;
          m = k;
        }
        else
        {
          if (hasDividerBeforeChildAt(k)) {
            mTotalLength += mDividerWidth;
          }
          localObject1 = (o0.a)localView.getLayoutParams();
          f1 += weight;
          if ((i9 == 1073741824) && (width == 0) && (weight > 0.0F))
          {
            if (i6 != 0)
            {
              mTotalLength += leftMargin + rightMargin;
            }
            else
            {
              i2 = mTotalLength;
              mTotalLength = Math.max(i2, leftMargin + i2 + rightMargin);
            }
            if (bool1)
            {
              i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
              localView.measure(i2, i2);
              i2 = i3;
              i5 = i7;
            }
            else
            {
              i2 = 1;
              i5 = i7;
            }
          }
          else
          {
            if ((width == 0) && (weight > 0.0F))
            {
              width = -2;
              i2 = 0;
            }
            else
            {
              i2 = Integer.MIN_VALUE;
            }
            if (f1 == 0.0F) {
              i5 = mTotalLength;
            } else {
              i5 = 0;
            }
            i8 = i;
            measureChildBeforeLayout(localView, k, paramInt1, i5, paramInt2, 0);
            if (i2 != Integer.MIN_VALUE) {
              width = i2;
            }
            localObject2 = localObject1;
            i10 = localView.getMeasuredWidth();
            if (i6 != 0)
            {
              mTotalLength += leftMargin + i10 + rightMargin + getNextLocationOffset(localView);
            }
            else
            {
              i = mTotalLength;
              mTotalLength = Math.max(i, i + i10 + leftMargin + rightMargin + getNextLocationOffset(localView));
            }
            i2 = i3;
            i = i8;
            i5 = i7;
            if (bool2)
            {
              i5 = Math.max(i10, i7);
              i2 = i3;
              i = i8;
            }
          }
          int i10 = k;
          i7 = 0;
          k = m;
          i3 = i7;
          if (i13 != 1073741824)
          {
            k = m;
            i3 = i7;
            if (height == -1)
            {
              k = 1;
              i3 = 1;
            }
          }
          m = topMargin + bottomMargin;
          i7 = localView.getMeasuredHeight() + m;
          i8 = View.combineMeasuredStates(i4, localView.getMeasuredState());
          if (bool1)
          {
            int i14 = localView.getBaseline();
            if (i14 != -1)
            {
              int i11 = gravity;
              i4 = i11;
              if (i11 < 0) {
                i4 = mGravity;
              }
              i4 = ((i4 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
              arrayOfInt1[i4] = Math.max(arrayOfInt1[i4], i14);
              arrayOfInt2[i4] = Math.max(arrayOfInt2[i4], i7 - i14);
            }
            else {}
          }
          i1 = Math.max(i1, i7);
          if ((j != 0) && (height == -1)) {
            j = 1;
          } else {
            j = 0;
          }
          if (weight > 0.0F)
          {
            if (i3 != 0) {
              i7 = m;
            }
            m = Math.max(n, i7);
          }
          else
          {
            if (i3 != 0) {
              i7 = m;
            }
            i = Math.max(i, i7);
            m = n;
          }
          i3 = i10 + getChildrenSkipCount(localView, i10);
          i7 = i5;
          n = m;
          m = i3;
          i5 = k;
          i3 = i2;
          i4 = i8;
        }
      }
      k = m + 1;
      i2 = i7;
      m = i5;
    }
    k = i1;
    if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i12))) {
      mTotalLength += mDividerWidth;
    }
    if ((arrayOfInt1[1] == -1) && (arrayOfInt1[0] == -1) && (arrayOfInt1[2] == -1) && (arrayOfInt1[3] == -1)) {
      break label1077;
    }
    k = Math.max(k, Math.max(arrayOfInt1[3], Math.max(arrayOfInt1[0], Math.max(arrayOfInt1[1], arrayOfInt1[2]))) + Math.max(arrayOfInt2[3], Math.max(arrayOfInt2[0], Math.max(arrayOfInt2[1], arrayOfInt2[2]))));
    label1077:
    if (bool2)
    {
      if ((i9 != Integer.MIN_VALUE) && (i9 != 0)) {
        break label1272;
      }
      mTotalLength = 0;
      i1 = 0;
      while (i1 < i12)
      {
        localObject1 = getVirtualChildAt(i1);
        if (localObject1 == null)
        {
          mTotalLength += measureNullChild(i1);
        }
        else if (((View)localObject1).getVisibility() == 8)
        {
          i1 += getChildrenSkipCount((View)localObject1, i1);
        }
        else
        {
          localObject2 = (o0.a)((View)localObject1).getLayoutParams();
          if (i6 != 0)
          {
            mTotalLength += leftMargin + i2 + rightMargin + getNextLocationOffset((View)localObject1);
          }
          else
          {
            i5 = mTotalLength;
            mTotalLength = Math.max(i5, i5 + i2 + leftMargin + rightMargin + getNextLocationOffset((View)localObject1));
          }
        }
        i1 += 1;
      }
    }
    label1272:
    mTotalLength += getPaddingLeft() + getPaddingRight();
    i1 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumWidth()), paramInt1, 0);
    int i5 = i1;
    int i7 = (i1 & 0xFFFFFF) - mTotalLength;
    if ((i3 == 0) && ((i7 == 0) || (f1 <= 0.0F)))
    {
      n = Math.max(i, n);
      if ((bool2) && (i9 != 1073741824))
      {
        i = 0;
        while (i < i12)
        {
          localObject1 = getVirtualChildAt(i);
          if ((localObject1 != null) && (((View)localObject1).getVisibility() != 8)) {
            if (getLayoutParamsweight > 0.0F) {
              ((View)localObject1).measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(((View)localObject1).getMeasuredHeight(), 1073741824));
            } else {}
          }
          i += 1;
        }
      }
      i2 = i1;
      i = k;
      i1 = i4;
      i3 = j;
      k = n;
    }
    else
    {
      i1 = i;
      float f2 = mWeightSum;
      if (f2 > 0.0F) {
        f1 = f2;
      }
      arrayOfInt1[3] = -1;
      arrayOfInt1[2] = -1;
      arrayOfInt1[1] = -1;
      arrayOfInt1[0] = -1;
      arrayOfInt2[3] = -1;
      arrayOfInt2[2] = -1;
      arrayOfInt2[1] = -1;
      arrayOfInt2[0] = -1;
      i2 = -1;
      mTotalLength = 0;
      i3 = 0;
      k = i5;
      i = i7;
      n = j;
      j = i4;
      while (i3 < i12)
      {
        localObject1 = getVirtualChildAt(i3);
        if ((localObject1 != null) && (((View)localObject1).getVisibility() != 8))
        {
          localObject2 = (o0.a)((View)localObject1).getLayoutParams();
          f2 = weight;
          if (f2 > 0.0F)
          {
            i5 = (int)(i * f2 / f1);
            f1 -= f2;
            i4 = i - i5;
            i7 = ViewGroup.getChildMeasureSpec(paramInt2, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin, height);
            if ((width == 0) && (i9 == 1073741824))
            {
              if (i5 > 0) {
                i = i5;
              } else {
                i = 0;
              }
              ((View)localObject1).measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), i7);
            }
            else
            {
              i5 = ((View)localObject1).getMeasuredWidth() + i5;
              i = i5;
              if (i5 < 0) {
                i = 0;
              }
              ((View)localObject1).measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), i7);
            }
            j = View.combineMeasuredStates(j, ((View)localObject1).getMeasuredState() & 0xFF000000);
            i = i4;
          }
          if (i6 != 0)
          {
            mTotalLength += ((View)localObject1).getMeasuredWidth() + leftMargin + rightMargin + getNextLocationOffset((View)localObject1);
          }
          else
          {
            i4 = mTotalLength;
            mTotalLength = Math.max(i4, ((View)localObject1).getMeasuredWidth() + i4 + leftMargin + rightMargin + getNextLocationOffset((View)localObject1));
          }
          if ((i13 != 1073741824) && (height == -1)) {
            i4 = 1;
          } else {
            i4 = 0;
          }
          i8 = topMargin + bottomMargin;
          i7 = ((View)localObject1).getMeasuredHeight() + i8;
          i5 = Math.max(i2, i7);
          if (i4 != 0) {
            i2 = i8;
          } else {
            i2 = i7;
          }
          i2 = Math.max(i1, i2);
          if ((n != 0) && (height == -1)) {
            n = 1;
          } else {
            n = 0;
          }
          if (bool1)
          {
            i8 = ((View)localObject1).getBaseline();
            if (i8 != -1)
            {
              i4 = gravity;
              i1 = i4;
              if (i4 < 0) {
                i1 = mGravity;
              }
              i1 = ((i1 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
              arrayOfInt1[i1] = Math.max(arrayOfInt1[i1], i8);
              arrayOfInt2[i1] = Math.max(arrayOfInt2[i1], i7 - i8);
            }
            else {}
          }
          i1 = i2;
          i2 = i5;
        }
        i3 += 1;
      }
      i3 = k;
      mTotalLength += getPaddingLeft() + getPaddingRight();
      if ((arrayOfInt1[1] == -1) && (arrayOfInt1[0] == -1) && (arrayOfInt1[2] == -1))
      {
        i = i2;
        if (arrayOfInt1[3] == -1) {}
      }
      else
      {
        i = Math.max(i2, Math.max(arrayOfInt1[3], Math.max(arrayOfInt1[0], Math.max(arrayOfInt1[1], arrayOfInt1[2]))) + Math.max(arrayOfInt2[3], Math.max(arrayOfInt2[0], Math.max(arrayOfInt2[1], arrayOfInt2[2]))));
      }
      k = i1;
      i2 = i3;
      i3 = n;
      i1 = j;
    }
    j = i;
    if (i3 == 0)
    {
      j = i;
      if (i13 != 1073741824) {
        j = k;
      }
    }
    setMeasuredDimension(i2 | 0xFF000000 & i1, View.resolveSizeAndState(Math.max(j + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), paramInt2, i1 << 16));
    if (m != 0) {
      forceUniformHeight(i12, paramInt1);
    }
  }
  
  int measureNullChild(int paramInt)
  {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2)
  {
    mTotalLength = 0;
    int i5 = 0;
    float f1 = 0.0F;
    int i9 = getVirtualChildCount();
    int i10 = View.MeasureSpec.getMode(paramInt1);
    int i8 = View.MeasureSpec.getMode(paramInt2);
    int i11 = mBaselineAlignedChildIndex;
    boolean bool = mUseLargestChild;
    int m = 0;
    int k = 0;
    int i = 0;
    int n = 0;
    int i2 = 0;
    int i3 = 0;
    int i1 = 0;
    int j = 1;
    Object localObject2;
    int i4;
    Object localObject1;
    int i7;
    int i12;
    while (i1 < i9)
    {
      localObject2 = getVirtualChildAt(i1);
      if (localObject2 == null)
      {
        mTotalLength += measureNullChild(i1);
        i4 = k;
      }
      else if (((View)localObject2).getVisibility() == 8)
      {
        i1 += getChildrenSkipCount((View)localObject2, i1);
        i4 = k;
      }
      else
      {
        if (hasDividerBeforeChildAt(i1)) {
          mTotalLength += mDividerHeight;
        }
        localObject1 = (o0.a)((View)localObject2).getLayoutParams();
        f1 += weight;
        if ((i8 == 1073741824) && (height == 0) && (weight > 0.0F))
        {
          i3 = mTotalLength;
          mTotalLength = Math.max(i3, topMargin + i3 + bottomMargin);
          i4 = 1;
          i6 = i;
          i = i2;
        }
        else
        {
          if ((height == 0) && (weight > 0.0F))
          {
            height = -2;
            i4 = 0;
          }
          else
          {
            i4 = Integer.MIN_VALUE;
          }
          if (f1 == 0.0F) {
            i6 = mTotalLength;
          } else {
            i6 = 0;
          }
          i7 = i;
          measureChildBeforeLayout((View)localObject2, i1, paramInt1, 0, paramInt2, i6);
          if (i4 != Integer.MIN_VALUE) {
            height = i4;
          }
          i12 = ((View)localObject2).getMeasuredHeight();
          i = mTotalLength;
          mTotalLength = Math.max(i, i + i12 + topMargin + bottomMargin + getNextLocationOffset((View)localObject2));
          i6 = i7;
          i = i2;
          i4 = i3;
          if (bool)
          {
            i = Math.max(i12, i2);
            i4 = i3;
            i6 = i7;
          }
        }
        i2 = k;
        if ((i11 >= 0) && (i11 == i1 + 1)) {
          mBaselineChildTop = mTotalLength;
        }
        if ((i1 < i11) && (weight > 0.0F)) {
          throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
        }
        i7 = 0;
        k = m;
        i3 = i7;
        if (i10 != 1073741824)
        {
          k = m;
          i3 = i7;
          if (width == -1)
          {
            k = 1;
            i3 = 1;
          }
        }
        m = leftMargin + rightMargin;
        i7 = ((View)localObject2).getMeasuredWidth() + m;
        i6 = Math.max(i6, i7);
        i5 = View.combineMeasuredStates(i5, ((View)localObject2).getMeasuredState());
        if ((j != 0) && (width == -1)) {
          j = 1;
        } else {
          j = 0;
        }
        if (weight > 0.0F)
        {
          if (i3 == 0) {
            m = i7;
          }
          m = Math.max(n, m);
          n = i2;
        }
        else
        {
          if (i3 == 0) {
            m = i7;
          }
          i2 = Math.max(i2, m);
          m = n;
          n = i2;
        }
        i1 += getChildrenSkipCount((View)localObject2, i1);
        i7 = n;
        i3 = i4;
        i2 = i;
        n = m;
        i = i6;
        i4 = i7;
        m = k;
      }
      i1 += 1;
      k = i4;
    }
    if ((mTotalLength > 0) && (hasDividerBeforeChildAt(i9))) {
      mTotalLength += mDividerHeight;
    }
    if ((bool) && ((i8 == Integer.MIN_VALUE) || (i8 == 0)))
    {
      mTotalLength = 0;
      i1 = 0;
      while (i1 < i9)
      {
        localObject1 = getVirtualChildAt(i1);
        if (localObject1 == null)
        {
          mTotalLength += measureNullChild(i1);
        }
        else if (((View)localObject1).getVisibility() == 8)
        {
          i1 += getChildrenSkipCount((View)localObject1, i1);
        }
        else
        {
          localObject2 = (o0.a)((View)localObject1).getLayoutParams();
          i4 = mTotalLength;
          mTotalLength = Math.max(i4, i4 + i2 + topMargin + bottomMargin + getNextLocationOffset((View)localObject1));
        }
        i1 += 1;
      }
    }
    mTotalLength += getPaddingTop() + getPaddingBottom();
    int i6 = View.resolveSizeAndState(Math.max(mTotalLength, getSuggestedMinimumHeight()), paramInt2, 0);
    i1 = (i6 & 0xFFFFFF) - mTotalLength;
    if ((i3 == 0) && ((i1 == 0) || (f1 <= 0.0F)))
    {
      n = Math.max(k, n);
      if (bool) {
        if (i8 != 1073741824)
        {
          k = 0;
          while (k < i9)
          {
            localObject1 = getVirtualChildAt(k);
            if ((localObject1 != null) && (((View)localObject1).getVisibility() != 8)) {
              if (getLayoutParamsweight > 0.0F) {
                ((View)localObject1).measure(View.MeasureSpec.makeMeasureSpec(((View)localObject1).getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
              } else {}
            }
            k += 1;
          }
        }
        else {}
      }
      k = n;
    }
    else
    {
      float f2 = mWeightSum;
      if (f2 > 0.0F) {
        f1 = f2;
      }
      mTotalLength = 0;
      i4 = 0;
      i2 = i1;
      n = i;
      i = i5;
      while (i4 < i9)
      {
        localObject1 = getVirtualChildAt(i4);
        if (((View)localObject1).getVisibility() != 8)
        {
          localObject2 = (o0.a)((View)localObject1).getLayoutParams();
          float f3 = weight;
          f2 = f1;
          i1 = i;
          i3 = i2;
          if (f3 > 0.0F)
          {
            i1 = (int)(i2 * f3 / f1);
            i5 = getPaddingLeft();
            i7 = getPaddingRight();
            f2 = f1 - f3;
            i11 = leftMargin;
            i12 = rightMargin;
            i3 = i2 - i1;
            i5 = ViewGroup.getChildMeasureSpec(paramInt1, i5 + i7 + i11 + i12, width);
            if ((height == 0) && (i8 == 1073741824))
            {
              if (i1 <= 0) {
                i1 = 0;
              }
              ((View)localObject1).measure(i5, View.MeasureSpec.makeMeasureSpec(i1, 1073741824));
            }
            else
            {
              i2 = ((View)localObject1).getMeasuredHeight() + i1;
              i1 = i2;
              if (i2 < 0) {
                i1 = 0;
              }
              ((View)localObject1).measure(i5, View.MeasureSpec.makeMeasureSpec(i1, 1073741824));
            }
            i1 = View.combineMeasuredStates(i, ((View)localObject1).getMeasuredState() & 0xFF00);
          }
          i2 = leftMargin + rightMargin;
          i5 = ((View)localObject1).getMeasuredWidth() + i2;
          n = Math.max(n, i5);
          if ((i10 != 1073741824) && (width == -1)) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            i = i2;
          } else {
            i = i5;
          }
          k = Math.max(k, i);
          if ((j != 0) && (width == -1)) {
            i = 1;
          } else {
            i = 0;
          }
          j = mTotalLength;
          mTotalLength = Math.max(j, j + ((View)localObject1).getMeasuredHeight() + topMargin + bottomMargin + getNextLocationOffset((View)localObject1));
          i2 = i3;
          j = i;
          i = i1;
          f1 = f2;
        }
        i4 += 1;
      }
      mTotalLength += getPaddingTop() + getPaddingBottom();
      i5 = i;
      i = n;
    }
    n = i;
    if (j == 0)
    {
      n = i;
      if (i10 != 1073741824) {
        n = k;
      }
    }
    setMeasuredDimension(View.resolveSizeAndState(Math.max(n + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), paramInt1, i5), i6);
    if (m != 0) {
      forceUniformWidth(i9, paramInt2);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mDivider == null) {
      return;
    }
    if (mOrientation == 1)
    {
      drawDividersVertical(paramCanvas);
      return;
    }
    drawDividersHorizontal(paramCanvas);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(o0.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(o0.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mOrientation == 1)
    {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (mOrientation == 1)
    {
      measureVertical(paramInt1, paramInt2);
      return;
    }
    measureHorizontal(paramInt1, paramInt2);
  }
  
  public void setBaselineAligned(boolean paramBoolean)
  {
    mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getChildCount()))
    {
      mBaselineAlignedChildIndex = paramInt;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("base aligned child index out of range (0, ");
    localStringBuilder.append(getChildCount());
    localStringBuilder.append(")");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public void setDividerDrawable(Drawable paramDrawable)
  {
    if (paramDrawable == mDivider) {
      return;
    }
    mDivider = paramDrawable;
    boolean bool = false;
    if (paramDrawable != null)
    {
      mDividerWidth = paramDrawable.getIntrinsicWidth();
      mDividerHeight = paramDrawable.getIntrinsicHeight();
    }
    else
    {
      mDividerWidth = 0;
      mDividerHeight = 0;
    }
    if (paramDrawable == null) {
      bool = true;
    }
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt)
  {
    mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0) {
        i = paramInt | 0x800003;
      }
      paramInt = i;
      if ((i & 0x70) == 0) {
        paramInt = i | 0x30;
      }
      mGravity = paramInt;
      requestLayout();
    }
  }
  
  public void setHorizontalGravity(int paramInt)
  {
    paramInt &= 0x800007;
    int i = mGravity;
    if ((0x800007 & i) != paramInt)
    {
      mGravity = (0xFF7FFFF8 & i | paramInt);
      requestLayout();
    }
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean)
  {
    mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt)
  {
    if (mOrientation != paramInt)
    {
      mOrientation = paramInt;
      requestLayout();
    }
  }
  
  public void setShowDividers(int paramInt)
  {
    if (paramInt != mShowDividers) {
      requestLayout();
    }
    mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt)
  {
    paramInt &= 0x70;
    int i = mGravity;
    if ((i & 0x70) != paramInt)
    {
      mGravity = (i & 0xFFFFFF8F | paramInt);
      requestLayout();
    }
  }
  
  public void setWeightSum(float paramFloat)
  {
    mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
}
