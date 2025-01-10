package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager
  extends LinearLayoutManager
{
  final Rect height = new Rect();
  int[] mCachedBorders;
  boolean mPendingSpanCountChange = false;
  final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
  final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
  View[] mSet;
  int mSpanCount = -1;
  c mSpanSizeLookup = new a();
  
  public GridLayoutManager(Context paramContext, int paramInt)
  {
    super(paramContext);
    setSpanCount(paramInt);
  }
  
  public GridLayoutManager(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramContext, paramInt2, paramBoolean);
    setSpanCount(paramInt1);
  }
  
  public GridLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setSpanCount(getPropertiesspanCount);
  }
  
  private void assignSpans(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
    {
      paramInt2 = 0;
      i = 1;
    }
    else
    {
      paramInt2 = paramInt1 - 1;
      paramInt1 = -1;
      i = -1;
    }
    int j = 0;
    while (paramInt2 != paramInt1)
    {
      View localView = mSet[paramInt2];
      b localB = (b)localView.getLayoutParams();
      width = getSpanSize(paramV, paramA0, a(localView));
      height = j;
      j += width;
      paramInt2 += i;
    }
  }
  
  private void b(RecyclerView.v paramV, RecyclerView.a0 paramA0, LinearLayoutManager.a paramA, int paramInt)
  {
    if (paramInt == 1) {
      i = 1;
    } else {
      i = 0;
    }
    paramInt = getSpanIndex(paramV, paramA0, w);
    int j = paramInt;
    if (i != 0) {
      while (j > 0)
      {
        paramInt = w;
        if (paramInt <= 0) {
          break;
        }
        w = (paramInt - 1);
        j = getSpanIndex(paramV, paramA0, w);
      }
    }
    int k = paramA0.size();
    int i = w;
    while (i < k - 1)
    {
      j = getSpanIndex(paramV, paramA0, i + 1);
      if (j <= paramInt) {
        break;
      }
      i += 1;
      paramInt = j;
    }
    w = i;
  }
  
  private void cachePreLayoutSpanMapping()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      b localB = (b)getChildAt(i).getLayoutParams();
      int k = localB.a();
      mPreLayoutSpanSizeCache.put(k, localB.getSpanSize());
      mPreLayoutSpanIndexCache.put(k, localB.getSpanIndex());
      i += 1;
    }
  }
  
  private void calculateItemBorders(int paramInt)
  {
    mCachedBorders = calculateItemBorders(mCachedBorders, mSpanCount, paramInt);
  }
  
  static int[] calculateItemBorders(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt;
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length == paramInt1 + 1))
    {
      arrayOfInt = paramArrayOfInt;
      if (paramArrayOfInt[(paramArrayOfInt.length - 1)] == paramInt2) {}
    }
    else
    {
      arrayOfInt = new int[paramInt1 + 1];
    }
    arrayOfInt[0] = 0;
    int n = paramInt2 / paramInt1;
    int i2 = paramInt2 % paramInt1;
    int j = 0;
    paramInt2 = 0;
    int i = 1;
    while (i <= paramInt1)
    {
      int k = n;
      int i1 = paramInt2 + i2;
      paramInt2 = i1;
      int m = k;
      if (i1 > 0)
      {
        paramInt2 = i1;
        m = k;
        if (paramInt1 - i1 < i2)
        {
          m = n + 1;
          paramInt2 = i1 - paramInt1;
        }
      }
      j += m;
      arrayOfInt[i] = j;
      i += 1;
    }
    return arrayOfInt;
  }
  
  private void clearPreLayoutSpanMappingCache()
  {
    mPreLayoutSpanSizeCache.clear();
    mPreLayoutSpanIndexCache.clear();
  }
  
  private void ensureViewSet()
  {
    View[] arrayOfView = mSet;
    if ((arrayOfView == null) || (arrayOfView.length != mSpanCount)) {
      mSet = new View[mSpanCount];
    }
  }
  
  private int getSpanGroupIndex(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt)
  {
    if (!paramA0.get()) {
      return mSpanSizeLookup.getSpanGroupIndex(paramInt, mSpanCount);
    }
    int i = paramV.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      paramV = new StringBuilder();
      paramV.append("Cannot find span size for pre layout position. ");
      paramV.append(paramInt);
      Log.w("GridLayoutManager", paramV.toString());
      return 0;
    }
    return mSpanSizeLookup.getSpanGroupIndex(i, mSpanCount);
  }
  
  private int getSpanIndex(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt)
  {
    if (!paramA0.get()) {
      return mSpanSizeLookup.getCachedSpanIndex(paramInt, mSpanCount);
    }
    int i = mPreLayoutSpanIndexCache.get(paramInt, -1);
    if (i != -1) {
      return i;
    }
    i = paramV.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      paramV = new StringBuilder();
      paramV.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      paramV.append(paramInt);
      Log.w("GridLayoutManager", paramV.toString());
      return 0;
    }
    return mSpanSizeLookup.getCachedSpanIndex(i, mSpanCount);
  }
  
  private int getSpanSize(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt)
  {
    if (!paramA0.get()) {
      return mSpanSizeLookup.getSpanSize(paramInt);
    }
    int i = mPreLayoutSpanSizeCache.get(paramInt, -1);
    if (i != -1) {
      return i;
    }
    i = paramV.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      paramV = new StringBuilder();
      paramV.append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:");
      paramV.append(paramInt);
      Log.w("GridLayoutManager", paramV.toString());
      return 1;
    }
    return mSpanSizeLookup.getSpanSize(i);
  }
  
  private void guessMeasurement(float paramFloat, int paramInt)
  {
    calculateItemBorders(Math.max(Math.round(mSpanCount * paramFloat), paramInt));
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
    if (paramBoolean) {
      paramBoolean = shouldReMeasureChild(paramView, paramInt1, paramInt2, localP);
    } else {
      paramBoolean = shouldMeasureChild(paramView, paramInt1, paramInt2, localP);
    }
    if (paramBoolean) {
      paramView.measure(paramInt1, paramInt2);
    }
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt, boolean paramBoolean)
  {
    b localB = (b)paramView.getLayoutParams();
    Rect localRect = mDecorInsets;
    int j = top + bottom + topMargin + bottomMargin;
    int i = left + right + leftMargin + rightMargin;
    int k = assignSpans(height, width);
    if (mOrientation == 1)
    {
      i = RecyclerView.o.getChildMeasureSpec(k, paramInt, i, width, false);
      paramInt = RecyclerView.o.getChildMeasureSpec(a.get(), getHeightMode(), j, height, true);
    }
    else
    {
      paramInt = RecyclerView.o.getChildMeasureSpec(k, paramInt, j, height, false);
      i = RecyclerView.o.getChildMeasureSpec(a.get(), getWidthMode(), i, width, true);
    }
    measureChildWithDecorationsAndMargin(paramView, i, paramInt, paramBoolean);
  }
  
  private void updateMeasurements()
  {
    int i;
    if (getOrientation() == 1) {
      i = getWidth() - getPaddingRight() - getPaddingLeft();
    } else {
      i = getHeight() - getPaddingBottom() - getPaddingTop();
    }
    calculateItemBorders(i);
  }
  
  View a(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt1, int paramInt2, int paramInt3)
  {
    d();
    Object localObject1 = null;
    Object localObject2 = null;
    int j = a.f();
    int k = a.a();
    int i;
    if (paramInt2 > paramInt1) {
      i = 1;
    } else {
      i = -1;
    }
    while (paramInt1 != paramInt2)
    {
      View localView = getChildAt(paramInt1);
      int m = a(localView);
      Object localObject3 = localObject1;
      Object localObject4 = localObject2;
      if (m >= 0)
      {
        localObject3 = localObject1;
        localObject4 = localObject2;
        if (m < paramInt3) {
          if (getSpanIndex(paramV, paramA0, m) != 0)
          {
            localObject3 = localObject1;
            localObject4 = localObject2;
          }
          else if (((RecyclerView.p)localView.getLayoutParams()).next())
          {
            localObject3 = localObject1;
            localObject4 = localObject2;
            if (localObject1 == null)
            {
              localObject3 = localView;
              localObject4 = localObject2;
            }
          }
          else
          {
            if ((a.f(localView) < k) && (a.a(localView) >= j)) {
              return localView;
            }
            localObject3 = localObject1;
            localObject4 = localObject2;
            if (localObject2 == null)
            {
              localObject4 = localView;
              localObject3 = localObject1;
            }
          }
        }
      }
      paramInt1 += i;
      localObject1 = localObject3;
      localObject2 = localObject4;
    }
    if (localObject2 != null) {
      return localObject2;
    }
    return localObject1;
  }
  
  public View a(View paramView, int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    View localView2 = findContainingItemView(paramView);
    if (localView2 == null) {
      return null;
    }
    Object localObject = (b)localView2.getLayoutParams();
    int i6 = height;
    int i7 = height + width;
    if (super.a(paramView, paramInt, paramV, paramA0) == null) {
      return null;
    }
    int i12;
    if (fill(paramInt) == 1) {
      i12 = 1;
    } else {
      i12 = 0;
    }
    if (i12 != c) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    int j;
    int k;
    if (paramInt != 0)
    {
      paramInt = getChildCount() - 1;
      j = -1;
      k = -1;
    }
    else
    {
      paramInt = 0;
      j = 1;
      k = getChildCount();
    }
    int m;
    if ((mOrientation == 1) && (isLayoutRTL())) {
      m = 1;
    } else {
      m = 0;
    }
    paramView = null;
    localObject = null;
    int i8 = getSpanGroupIndex(paramV, paramA0, paramInt);
    int i = -1;
    int i1 = 0;
    int i3 = -1;
    int i2 = 0;
    int n = paramInt;
    while (n != k)
    {
      paramInt = getSpanGroupIndex(paramV, paramA0, n);
      View localView1 = getChildAt(n);
      if (localView1 == localView2) {
        break;
      }
      if ((localView1.hasFocusable()) && (paramInt != i8))
      {
        if (paramView != null) {
          break;
        }
      }
      else
      {
        b localB = (b)localView1.getLayoutParams();
        int i9 = height;
        int i10 = height + width;
        if ((localView1.hasFocusable()) && (i9 == i6) && (i10 == i7)) {
          return localView1;
        }
        if (((localView1.hasFocusable()) && (paramView == null)) || ((!localView1.hasFocusable()) && (localObject == null)))
        {
          paramInt = 1;
        }
        else
        {
          paramInt = Math.max(i9, i6);
          int i4 = Math.min(i10, i7);
          int i5 = 0;
          int i11 = i4 - paramInt;
          if (localView1.hasFocusable())
          {
            if (i11 > i1)
            {
              paramInt = 1;
            }
            else
            {
              if (i11 == i1)
              {
                if (i9 > i) {
                  paramInt = 1;
                } else {
                  paramInt = 0;
                }
                if (m == paramInt)
                {
                  paramInt = 1;
                  break label476;
                }
              }
              paramInt = i5;
            }
          }
          else
          {
            paramInt = i5;
            if (paramView == null)
            {
              i4 = 0;
              paramInt = i5;
              if (a(localView1, false, true)) {
                if (i11 > i2)
                {
                  paramInt = 1;
                }
                else
                {
                  paramInt = i5;
                  if (i11 == i2)
                  {
                    if (i9 > i3) {
                      i4 = 1;
                    }
                    paramInt = i5;
                    if (m == i4) {
                      paramInt = 1;
                    }
                  }
                }
              }
            }
          }
        }
        label476:
        if (paramInt != 0) {
          if (localView1.hasFocusable())
          {
            i = height;
            i1 = Math.min(i10, i7) - Math.max(i9, i6);
            paramView = localView1;
          }
          else
          {
            i3 = height;
            paramInt = Math.min(i10, i7);
            i2 = Math.max(i9, i6);
            localObject = localView1;
            i2 = paramInt - i2;
          }
        }
      }
      n += j;
    }
    if (paramView != null) {
      return paramView;
    }
    return localObject;
  }
  
  public void a(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (paramA0.get()) {
      cachePreLayoutSpanMapping();
    }
    super.a(paramV, paramA0);
    clearPreLayoutSpanMappingCache();
  }
  
  void a(RecyclerView.v paramV, RecyclerView.a0 paramA0, LinearLayoutManager.a paramA, int paramInt)
  {
    super.a(paramV, paramA0, paramA, paramInt);
    updateMeasurements();
    if ((paramA0.size() > 0) && (!paramA0.get())) {
      b(paramV, paramA0, paramA, paramInt);
    }
    ensureViewSet();
  }
  
  int assignSpans(int paramInt1, int paramInt2)
  {
    if ((mOrientation == 1) && (isLayoutRTL()))
    {
      arrayOfInt = mCachedBorders;
      int i = mSpanCount;
      return arrayOfInt[(i - paramInt1)] - arrayOfInt[(i - paramInt1 - paramInt2)];
    }
    int[] arrayOfInt = mCachedBorders;
    return arrayOfInt[(paramInt1 + paramInt2)] - arrayOfInt[paramInt1];
  }
  
  void b(RecyclerView.a0 paramA0, LinearLayoutManager.c paramC, RecyclerView.o.c paramC1)
  {
    int j = mSpanCount;
    int i = 0;
    while ((i < mSpanCount) && (paramC.next(paramA0)) && (j > 0))
    {
      int k = f;
      paramC1.a(k, Math.max(0, i));
      j -= mSpanSizeLookup.getSpanSize(k);
      f += b;
      i += 1;
    }
  }
  
  public boolean checkLayoutParams(RecyclerView.p paramP)
  {
    return paramP instanceof b;
  }
  
  public RecyclerView.p generateDefaultLayoutParams()
  {
    if (mOrientation == 0) {
      return new b(-2, -1);
    }
    return new b(-1, -2);
  }
  
  public RecyclerView.p generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new b(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new b((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new b(paramLayoutParams);
  }
  
  public int getColumnCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (mOrientation == 1) {
      return mSpanCount;
    }
    if (paramA0.size() < 1) {
      return 0;
    }
    return getSpanGroupIndex(paramV, paramA0, paramA0.size() - 1) + 1;
  }
  
  public int getRowCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (mOrientation == 0) {
      return mSpanCount;
    }
    if (paramA0.size() < 1) {
      return 0;
    }
    return getSpanGroupIndex(paramV, paramA0, paramA0.size() - 1) + 1;
  }
  
  void layoutChunk(RecyclerView.v paramV, RecyclerView.a0 paramA0, LinearLayoutManager.c paramC, LinearLayoutManager.b paramB)
  {
    int i3 = a.getMode();
    if (i3 != 1073741824) {
      k = 1;
    } else {
      k = 0;
    }
    if (getChildCount() > 0) {
      m = mCachedBorders[mSpanCount];
    } else {
      m = 0;
    }
    if (k != 0) {
      updateMeasurements();
    }
    boolean bool;
    if (b == 1) {
      bool = true;
    } else {
      bool = false;
    }
    int i = mSpanCount;
    int n;
    if (!bool)
    {
      i = getSpanIndex(paramV, paramA0, f) + getSpanSize(paramV, paramA0, f);
      n = 0;
      j = 0;
    }
    else
    {
      n = 0;
      j = 0;
    }
    Object localObject;
    while ((n < mSpanCount) && (paramC.next(paramA0)) && (i > 0))
    {
      i2 = f;
      i1 = getSpanSize(paramV, paramA0, i2);
      if (i1 <= mSpanCount)
      {
        i -= i1;
        if (i >= 0)
        {
          localObject = paramC.next(paramV);
          if (localObject != null)
          {
            j += i1;
            mSet[n] = localObject;
            n += 1;
          }
        }
      }
      else
      {
        paramV = new StringBuilder();
        paramV.append("Item at position ");
        paramV.append(i2);
        paramV.append(" requires ");
        paramV.append(i1);
        paramV.append(" spans but GridLayoutManager has only ");
        paramV.append(mSpanCount);
        paramV.append(" spans.");
        throw new IllegalArgumentException(paramV.toString());
      }
    }
    if (n == 0)
    {
      c = true;
      return;
    }
    assignSpans(paramV, paramA0, n, j, bool);
    int j = 0;
    i = 0;
    float f2;
    for (float f1 = 0.0F; j < n; f1 = f2)
    {
      paramV = mSet[j];
      if (a == null)
      {
        if (bool) {
          addView(paramV);
        } else {
          addView(paramV, 0);
        }
      }
      else if (bool) {
        addDisappearingView(paramV);
      } else {
        addDisappearingView(paramV, 0);
      }
      calculateItemDecorationsForChild(paramV, height);
      measureChildWithDecorationsAndMargin(paramV, i3, false);
      i2 = a.remove(paramV);
      i1 = i;
      if (i2 > i) {
        i1 = i2;
      }
      paramA0 = (b)paramV.getLayoutParams();
      float f3 = a.getDecoratedMeasurementInOther(paramV) * 1.0F / width;
      f2 = f1;
      if (f3 > f1) {
        f2 = f3;
      }
      j += 1;
      i = i1;
    }
    if (k != 0)
    {
      guessMeasurement(f1, m);
      i = 0;
      j = 0;
      while (j < n)
      {
        paramV = mSet[j];
        measureChildWithDecorationsAndMargin(paramV, 1073741824, true);
        m = a.remove(paramV);
        k = i;
        if (m > i) {
          k = m;
        }
        j += 1;
        i = k;
      }
    }
    j = 0;
    while (j < n)
    {
      paramV = mSet[j];
      if (a.remove(paramV) != i)
      {
        paramA0 = (b)paramV.getLayoutParams();
        localObject = mDecorInsets;
        k = top + bottom + topMargin + bottomMargin;
        m = left + right + leftMargin + rightMargin;
        i1 = assignSpans(height, width);
        if (mOrientation == 1)
        {
          m = RecyclerView.o.getChildMeasureSpec(i1, 1073741824, m, width, false);
          k = View.MeasureSpec.makeMeasureSpec(i - k, 1073741824);
        }
        else
        {
          m = View.MeasureSpec.makeMeasureSpec(i - m, 1073741824);
          k = RecyclerView.o.getChildMeasureSpec(i1, 1073741824, k, height, false);
        }
        measureChildWithDecorationsAndMargin(paramV, m, k, true);
      }
      j += 1;
    }
    j = i;
    int k = 0;
    int m = 0;
    int i1 = 0;
    j = 0;
    if (mOrientation == 1)
    {
      if (j == -1)
      {
        j = k;
        i = j - i;
      }
      else
      {
        j = k;
        i1 = j + i;
        i = j;
        j = i1;
      }
    }
    else if (j == -1)
    {
      m = k;
      k = m - i;
      i = i1;
    }
    else
    {
      k = k;
      m = k + i;
      i = i1;
    }
    int i4 = 0;
    i3 = m;
    int i2 = k;
    m = j;
    i1 = i;
    k = i4;
    while (k < n)
    {
      paramV = mSet[k];
      paramA0 = (b)paramV.getLayoutParams();
      if (mOrientation == 1)
      {
        if (isLayoutRTL())
        {
          j = getPaddingLeft() + mCachedBorders[(mSpanCount - height)];
          i = j - a.getDecoratedMeasurementInOther(paramV);
        }
        else
        {
          i = getPaddingLeft() + mCachedBorders[height];
          j = a.getDecoratedMeasurementInOther(paramV) + i;
        }
      }
      else
      {
        i1 = getPaddingTop() + mCachedBorders[height];
        m = a.getDecoratedMeasurementInOther(paramV) + i1;
        j = i3;
        i = i2;
      }
      measureChildWithDecorationsAndMargin(paramV, i, i1, j, m);
      if ((!paramA0.next()) && (!paramA0.isItemChanged())) {
        break label1108;
      }
      i = true;
      label1108:
      d |= paramV.hasFocusable();
      k += 1;
      i2 = i;
      i3 = j;
    }
    Arrays.fill(mSet, null);
  }
  
  public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v paramV, RecyclerView.a0 paramA0, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (!(localLayoutParams instanceof b))
    {
      super.onInitializeAccessibilityNodeInfoForItem(paramView, paramAccessibilityNodeInfoCompat);
      return;
    }
    paramView = (b)localLayoutParams;
    int i = getSpanGroupIndex(paramV, paramA0, paramView.a());
    boolean bool;
    if (mOrientation == 0)
    {
      j = paramView.getSpanIndex();
      k = paramView.getSpanSize();
      if ((mSpanCount > 1) && (paramView.getSpanSize() == mSpanCount)) {
        bool = true;
      } else {
        bool = false;
      }
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(j, k, i, 1, bool, false));
      return;
    }
    int j = paramView.getSpanIndex();
    int k = paramView.getSpanSize();
    if ((mSpanCount > 1) && (paramView.getSpanSize() == mSpanCount)) {
      bool = true;
    } else {
      bool = false;
    }
    paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, j, k, bool, false));
  }
  
  public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsChanged(RecyclerView paramRecyclerView)
  {
    mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3)
  {
    mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject)
  {
    mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onLayoutChildren(RecyclerView.a0 paramA0)
  {
    super.onLayoutChildren(paramA0);
    mPendingSpanCountChange = false;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    updateMeasurements();
    ensureViewSet();
    return super.scrollHorizontallyBy(paramInt, paramV, paramA0);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    updateMeasurements();
    ensureViewSet();
    return super.scrollVerticallyBy(paramInt, paramV, paramA0);
  }
  
  public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2)
  {
    if (mCachedBorders == null) {
      super.setMeasuredDimension(paramRect, paramInt1, paramInt2);
    }
    int i = getPaddingLeft() + getPaddingRight();
    int j = getPaddingTop() + getPaddingBottom();
    if (mOrientation == 1)
    {
      paramInt2 = RecyclerView.o.chooseSize(paramInt2, paramRect.height() + j, getMinimumHeight());
      paramRect = mCachedBorders;
      paramInt1 = RecyclerView.o.chooseSize(paramInt1, paramRect[(paramRect.length - 1)] + i, getMinimumWidth());
    }
    else
    {
      paramInt1 = RecyclerView.o.chooseSize(paramInt1, paramRect.width() + i, getMinimumWidth());
      paramRect = mCachedBorders;
      paramInt2 = RecyclerView.o.chooseSize(paramInt2, paramRect[(paramRect.length - 1)] + j, getMinimumHeight());
    }
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public void setSpanCount(int paramInt)
  {
    if (paramInt == mSpanCount) {
      return;
    }
    mPendingSpanCountChange = true;
    if (paramInt >= 1)
    {
      mSpanCount = paramInt;
      mSpanSizeLookup.invalidateSpanIndexCache();
      requestLayout();
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Span count should be at least 1. Provided ");
    localStringBuilder.append(paramInt);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public void setStackFromEnd(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      super.setStackFromEnd(false);
      return;
    }
    throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return (d == null) && (!mPendingSpanCountChange);
  }
  
  public static final class a
    extends GridLayoutManager.c
  {
    public a() {}
    
    public int getSpanIndex(int paramInt1, int paramInt2)
    {
      return paramInt1 % paramInt2;
    }
    
    public int getSpanSize(int paramInt)
    {
      return 1;
    }
  }
  
  public static class b
    extends RecyclerView.p
  {
    int height = -1;
    int width = 0;
    
    public b(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public b(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public b(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public b(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public int getSpanIndex()
    {
      return height;
    }
    
    public int getSpanSize()
    {
      return width;
    }
  }
  
  public static abstract class c
  {
    private boolean mCacheSpanIndices = false;
    final SparseIntArray mSpanIndexCache = new SparseIntArray();
    
    public c() {}
    
    int getCachedSpanIndex(int paramInt1, int paramInt2)
    {
      if (!mCacheSpanIndices) {
        return getSpanIndex(paramInt1, paramInt2);
      }
      int i = mSpanIndexCache.get(paramInt1, -1);
      if (i != -1) {
        return i;
      }
      paramInt2 = getSpanIndex(paramInt1, paramInt2);
      mSpanIndexCache.put(paramInt1, paramInt2);
      return paramInt2;
    }
    
    public int getSpanGroupIndex(int paramInt1, int paramInt2)
    {
      int i = 0;
      int m = 0;
      int i2 = getSpanSize(paramInt1);
      int k = 0;
      while (k < paramInt1)
      {
        int n = getSpanSize(k);
        int i1 = i + n;
        int j;
        if (i1 == paramInt2)
        {
          i = 0;
          j = m + 1;
        }
        else
        {
          i = i1;
          j = m;
          if (i1 > paramInt2)
          {
            i = n;
            j = m + 1;
          }
        }
        k += 1;
        m = j;
      }
      if (i + i2 > paramInt2) {
        return m + 1;
      }
      return m;
    }
    
    public abstract int getSpanIndex(int paramInt1, int paramInt2);
    
    public abstract int getSpanSize(int paramInt);
    
    public void invalidateSpanIndexCache()
    {
      mSpanIndexCache.clear();
    }
  }
}
