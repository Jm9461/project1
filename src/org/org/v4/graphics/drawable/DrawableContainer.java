package org.org.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;

class DrawableContainer
  extends Drawable
  implements Drawable.Callback
{
  private boolean a;
  private int mAlpha = 255;
  private Runnable mAnimationRunnable;
  private Drawable mCurrDrawable;
  private Drawable mDrawable;
  private boolean mMutated;
  private int mPosition = -1;
  private Rect mRect;
  private DrawableContainerState mState;
  private MaterialProgressDrawable.3 this$0;
  private long x;
  private long y;
  
  DrawableContainer() {}
  
  private void draw(Drawable paramDrawable)
  {
    if (this$0 == null) {
      this$0 = new MaterialProgressDrawable.3();
    }
    Object localObject = this$0;
    ((MaterialProgressDrawable.3)localObject).put(paramDrawable.getCallback());
    paramDrawable.setCallback((Drawable.Callback)localObject);
    try
    {
      int i = mState.k;
      if (i <= 0)
      {
        bool = a;
        if (bool) {
          paramDrawable.setAlpha(mAlpha);
        }
      }
      boolean bool = mState.mMode;
      if (bool)
      {
        paramDrawable.setColorFilter(mState.mState);
      }
      else
      {
        bool = mState.type;
        if (bool) {
          DrawableCompat.setTintList(paramDrawable, mState.mTint);
        }
        bool = mState.token;
        if (bool) {
          DrawableCompat.setTintMode(paramDrawable, mState.mTintMode);
        }
      }
      paramDrawable.setVisible(isVisible(), true);
      paramDrawable.setDither(mState.l);
      paramDrawable.setState(getState());
      paramDrawable.setLevel(getLevel());
      paramDrawable.setBounds(getBounds());
      i = Build.VERSION.SDK_INT;
      if (i >= 23) {
        paramDrawable.setLayoutDirection(getLayoutDirection());
      }
      i = Build.VERSION.SDK_INT;
      if (i >= 19) {
        paramDrawable.setAutoMirrored(mState.mDrawable);
      }
      localObject = mRect;
      i = Build.VERSION.SDK_INT;
      if ((i >= 21) && (localObject != null)) {
        paramDrawable.setHotspotBounds(left, top, right, bottom);
      }
      paramDrawable.setCallback(this$0.apply());
      return;
    }
    catch (Throwable localThrowable)
    {
      paramDrawable.setCallback(this$0.apply());
      throw localThrowable;
    }
  }
  
  static int init(Resources paramResources, int paramInt)
  {
    if (paramResources != null) {
      paramInt = getDisplayMetricsdensityDpi;
    }
    if (paramInt == 0) {
      return 160;
    }
    return paramInt;
  }
  
  private boolean selectDrawable()
  {
    return (isAutoMirrored()) && (getLayoutDirection() == 1);
  }
  
  void animate(boolean paramBoolean)
  {
    a = true;
    long l1 = SystemClock.uptimeMillis();
    int j = 0;
    Drawable localDrawable = mDrawable;
    long l2;
    int i;
    if (localDrawable != null)
    {
      l2 = y;
      i = j;
      if (l2 != 0L) {
        if (l2 <= l1)
        {
          localDrawable.setAlpha(mAlpha);
          y = 0L;
          i = j;
        }
        else
        {
          localDrawable.setAlpha((255 - (int)((l2 - l1) * 255L) / mState.k) * mAlpha / 255);
          i = 1;
        }
      }
    }
    else
    {
      y = 0L;
      i = j;
    }
    localDrawable = mCurrDrawable;
    if (localDrawable != null)
    {
      l2 = x;
      j = i;
      if (l2 != 0L) {
        if (l2 <= l1)
        {
          localDrawable.setVisible(false, false);
          mCurrDrawable = null;
          x = 0L;
          j = i;
        }
        else
        {
          i = (int)((l2 - l1) * 255L) / mState.mAlpha;
          localDrawable.setAlpha(mAlpha * i / 255);
          j = 1;
        }
      }
    }
    else
    {
      x = 0L;
      j = i;
    }
    if ((paramBoolean) && (j != 0)) {
      scheduleSelf(mAnimationRunnable, 16L + l1);
    }
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    mState.addChild(paramTheme);
  }
  
  public boolean canApplyTheme()
  {
    return mState.canApplyTheme();
  }
  
  DrawableContainerState draw()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.draw(paramCanvas);
    }
    localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.draw(paramCanvas);
    }
  }
  
  boolean draw(int paramInt)
  {
    if (paramInt == mPosition) {
      return false;
    }
    long l = SystemClock.uptimeMillis();
    Object localObject;
    if (mState.mAlpha > 0)
    {
      localObject = mCurrDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setVisible(false, false);
      }
      localObject = mDrawable;
      if (localObject != null)
      {
        mCurrDrawable = ((Drawable)localObject);
        x = (mState.mAlpha + l);
      }
      else
      {
        mCurrDrawable = null;
        x = 0L;
      }
    }
    else
    {
      localObject = mDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setVisible(false, false);
      }
    }
    if (paramInt >= 0)
    {
      localObject = mState;
      if (paramInt < mChildren)
      {
        localObject = ((DrawableContainerState)localObject).get(paramInt);
        mDrawable = ((Drawable)localObject);
        mPosition = paramInt;
        if (localObject != null)
        {
          paramInt = mState.k;
          if (paramInt > 0) {
            y = (paramInt + l);
          }
          draw((Drawable)localObject);
        }
        break label191;
      }
    }
    mDrawable = null;
    mPosition = -1;
    label191:
    if ((y != 0L) || (x != 0L))
    {
      localObject = mAnimationRunnable;
      if (localObject == null) {
        mAnimationRunnable = new MonthByWeekFragment.2(this);
      } else {
        unscheduleSelf((Runnable)localObject);
      }
      animate(true);
    }
    invalidateSelf();
    return true;
  }
  
  public int getAlpha()
  {
    return mAlpha;
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | mState.getChangingConfigurations();
  }
  
  public final Drawable.ConstantState getConstantState()
  {
    if (mState.canConstantState())
    {
      mState.mChangingConfigurations = getChangingConfigurations();
      return mState;
    }
    return null;
  }
  
  public Drawable getCurrent()
  {
    return mDrawable;
  }
  
  public void getHotspotBounds(Rect paramRect)
  {
    Rect localRect = mRect;
    if (localRect != null)
    {
      paramRect.set(localRect);
      return;
    }
    super.getHotspotBounds(paramRect);
  }
  
  public int getIntrinsicHeight()
  {
    if (mState.getWrapSelectorWheel()) {
      return mState.getHeight();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicHeight();
    }
    return -1;
  }
  
  public int getIntrinsicWidth()
  {
    if (mState.getWrapSelectorWheel()) {
      return mState.getWidth();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicWidth();
    }
    return -1;
  }
  
  public int getMinimumHeight()
  {
    if (mState.getWrapSelectorWheel()) {
      return mState.get();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getMinimumHeight();
    }
    return 0;
  }
  
  public int getMinimumWidth()
  {
    if (mState.getWrapSelectorWheel()) {
      return mState.getMinimumWidth();
    }
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.getMinimumWidth();
    }
    return 0;
  }
  
  public int getOpacity()
  {
    Drawable localDrawable = mDrawable;
    if ((localDrawable != null) && (localDrawable.isVisible())) {
      return mState.getOpacity();
    }
    return -2;
  }
  
  public void getOutline(Outline paramOutline)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.getOutline(paramOutline);
    }
  }
  
  public boolean getPadding(Rect paramRect)
  {
    Object localObject = mState.add();
    boolean bool;
    if (localObject != null)
    {
      paramRect.set((Rect)localObject);
      if ((left | top | bottom | right) != 0) {
        bool = true;
      } else {
        bool = false;
      }
    }
    else
    {
      localObject = mDrawable;
      if (localObject != null) {
        bool = ((Drawable)localObject).getPadding(paramRect);
      } else {
        bool = super.getPadding(paramRect);
      }
    }
    if (selectDrawable())
    {
      int i = left;
      left = right;
      right = i;
    }
    return bool;
  }
  
  final void inflate(Resources paramResources)
  {
    mState.add(paramResources);
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (localDrawableContainerState != null) {
      localDrawableContainerState.addChild();
    }
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().invalidateDrawable(this);
    }
  }
  
  public boolean isAutoMirrored()
  {
    return mState.mDrawable;
  }
  
  public void jumpToCurrentState()
  {
    int i = 0;
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null)
    {
      localDrawable.jumpToCurrentState();
      mCurrDrawable = null;
      i = 1;
    }
    localDrawable = mDrawable;
    if (localDrawable != null)
    {
      localDrawable.jumpToCurrentState();
      if (a) {
        mDrawable.setAlpha(mAlpha);
      }
    }
    if (x != 0L)
    {
      x = 0L;
      i = 1;
    }
    if (y != 0L)
    {
      y = 0L;
      i = 1;
    }
    if (i != 0) {
      invalidateSelf();
    }
  }
  
  public Drawable mutate()
  {
    if ((!mMutated) && (super.mutate() == this))
    {
      DrawableContainerState localDrawableContainerState = draw();
      localDrawableContainerState.init();
      mutate(localDrawableContainerState);
      mMutated = true;
    }
    return this;
  }
  
  protected void mutate(DrawableContainerState paramDrawableContainerState)
  {
    mState = paramDrawableContainerState;
    int i = mPosition;
    if (i >= 0)
    {
      mDrawable = paramDrawableContainerState.get(i);
      paramDrawableContainerState = mDrawable;
      if (paramDrawableContainerState != null) {
        draw(paramDrawableContainerState);
      }
    }
    mCurrDrawable = null;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
  }
  
  public boolean onLayoutDirectionChanged(int paramInt)
  {
    return mState.init(paramInt, readInt());
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      return localDrawable.setLevel(paramInt);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.setLevel(paramInt);
    }
    return false;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      return localDrawable.setState(paramArrayOfInt);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      return localDrawable.setState(paramArrayOfInt);
    }
    return false;
  }
  
  int readInt()
  {
    return mPosition;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().scheduleDrawable(this, paramRunnable, paramLong);
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if ((!a) || (mAlpha != paramInt))
    {
      a = true;
      mAlpha = paramInt;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null)
      {
        if (y == 0L)
        {
          localDrawable.setAlpha(paramInt);
          return;
        }
        animate(false);
      }
    }
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (mDrawable != paramBoolean)
    {
      mDrawable = paramBoolean;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null) {
        DrawableCompat.setTint(localDrawable, mDrawable);
      }
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Object localObject = mState;
    mMode = true;
    if (mState != paramColorFilter)
    {
      mState = paramColorFilter;
      localObject = mDrawable;
      if (localObject != null) {
        ((Drawable)localObject).setColorFilter(paramColorFilter);
      }
    }
  }
  
  public void setDither(boolean paramBoolean)
  {
    DrawableContainerState localDrawableContainerState = mState;
    if (l != paramBoolean)
    {
      l = paramBoolean;
      Drawable localDrawable = mDrawable;
      if (localDrawable != null) {
        localDrawable.setDither(l);
      }
    }
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      DrawableCompat.setHotspot(localDrawable, paramFloat1, paramFloat2);
    }
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object localObject = mRect;
    if (localObject == null) {
      mRect = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      ((Rect)localObject).set(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    localObject = mDrawable;
    if (localObject != null) {
      DrawableCompat.setHotspotBounds((Drawable)localObject, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    DrawableContainerState localDrawableContainerState = mState;
    type = true;
    if (mTint != paramColorStateList)
    {
      mTint = paramColorStateList;
      DrawableCompat.setTintList(mDrawable, paramColorStateList);
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    DrawableContainerState localDrawableContainerState = mState;
    token = true;
    if (mTintMode != paramMode)
    {
      mTintMode = paramMode;
      DrawableCompat.setTintMode(mDrawable, paramMode);
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    Drawable localDrawable = mCurrDrawable;
    if (localDrawable != null) {
      localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    return bool;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if ((paramDrawable == mDrawable) && (getCallback() != null)) {
      getCallback().unscheduleDrawable(this, paramRunnable);
    }
  }
  
  abstract class DrawableContainerState
    extends Drawable.ConstantState
  {
    int a;
    boolean b = false;
    boolean c = false;
    int data = 160;
    boolean g;
    int h;
    int k = 0;
    boolean l = true;
    int mAlpha = 0;
    boolean mCanConstantState;
    int mChangingConfigurations;
    boolean mCheckedConstantState;
    int mChildren;
    int mChildrenChangingConfigurations;
    SparseArray<Drawable.ConstantState> mData;
    boolean mDrawable;
    Drawable[] mDrawables;
    boolean mHaveOpacity;
    boolean mHaveStateful;
    boolean mMode;
    int mOpacity;
    ColorFilter mState;
    boolean mStateful;
    ColorStateList mTint;
    PorterDuff.Mode mTintMode;
    Resources next;
    boolean p;
    Rect r;
    boolean size;
    final DrawableContainer this$0;
    boolean token;
    boolean type;
    int width;
    int x;
    int y;
    
    DrawableContainerState(DrawableContainer paramDrawableContainer, Resources paramResources)
    {
      this$0 = paramDrawableContainer;
      if (paramResources != null) {
        paramDrawableContainer = paramResources;
      } else if (this$1 != null) {
        paramDrawableContainer = next;
      } else {
        paramDrawableContainer = null;
      }
      next = paramDrawableContainer;
      int i;
      if (this$1 != null) {
        i = data;
      } else {
        i = 0;
      }
      data = DrawableContainer.init(paramResources, i);
      if (this$1 != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        mChildrenChangingConfigurations = mChildrenChangingConfigurations;
        mCanConstantState = true;
        mCheckedConstantState = true;
        b = b;
        c = c;
        l = l;
        g = g;
        h = h;
        k = k;
        mAlpha = mAlpha;
        mDrawable = mDrawable;
        mState = mState;
        mMode = mMode;
        mTint = mTint;
        mTintMode = mTintMode;
        type = type;
        token = token;
        if (data == data)
        {
          if (p)
          {
            r = new Rect(r);
            p = true;
          }
          if (size)
          {
            x = x;
            y = y;
            width = width;
            a = a;
            size = true;
          }
        }
        if (mHaveOpacity)
        {
          mOpacity = mOpacity;
          mHaveOpacity = true;
        }
        if (mHaveStateful)
        {
          mStateful = mStateful;
          mHaveStateful = true;
        }
        paramDrawableContainer = mDrawables;
        mDrawables = new Drawable[paramDrawableContainer.length];
        mChildren = mChildren;
        this$1 = mData;
        if (this$1 != null) {
          mData = this$1.clone();
        } else {
          mData = new SparseArray(mChildren);
        }
        int j = mChildren;
        i = 0;
        while (i < j)
        {
          if (paramDrawableContainer[i] != null)
          {
            this$1 = paramDrawableContainer[i].getConstantState();
            if (this$1 != null) {
              mData.put(i, this$1);
            } else {
              mDrawables[i] = paramDrawableContainer[i];
            }
          }
          i += 1;
        }
        return;
      }
      mDrawables = new Drawable[10];
      mChildren = 0;
    }
    
    private Drawable apply(Drawable paramDrawable)
    {
      if (Build.VERSION.SDK_INT >= 23) {
        paramDrawable.setLayoutDirection(h);
      }
      paramDrawable = paramDrawable.mutate();
      paramDrawable.setCallback(this$0);
      return paramDrawable;
    }
    
    private void remove()
    {
      Object localObject = mData;
      if (localObject != null)
      {
        int j = ((SparseArray)localObject).size();
        int i = 0;
        while (i < j)
        {
          int m = mData.keyAt(i);
          localObject = (Drawable.ConstantState)mData.valueAt(i);
          mDrawables[m] = apply(((Drawable.ConstantState)localObject).newDrawable(next));
          i += 1;
        }
        mData = null;
      }
    }
    
    public final Rect add()
    {
      if (b) {
        return null;
      }
      if ((r == null) && (!p))
      {
        remove();
        Object localObject1 = null;
        Rect localRect = new Rect();
        int j = mChildren;
        Drawable[] arrayOfDrawable = mDrawables;
        int i = 0;
        while (i < j)
        {
          Object localObject3 = localObject1;
          if (arrayOfDrawable[i].getPadding(localRect))
          {
            Object localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new Rect(0, 0, 0, 0);
            }
            int m = left;
            if (m > left) {
              left = m;
            }
            m = top;
            if (m > top) {
              top = m;
            }
            m = right;
            if (m > right) {
              right = m;
            }
            m = bottom;
            localObject3 = localObject2;
            if (m > bottom)
            {
              bottom = m;
              localObject3 = localObject2;
            }
          }
          i += 1;
          localObject1 = localObject3;
        }
        p = true;
        r = localObject1;
        return localObject1;
      }
      return r;
    }
    
    final void add(Resources paramResources)
    {
      if (paramResources != null)
      {
        next = paramResources;
        int i = DrawableContainer.init(paramResources, data);
        int j = data;
        data = i;
        if (j != i)
        {
          size = false;
          p = false;
        }
      }
    }
    
    public final int addChild(Drawable paramDrawable)
    {
      int i = mChildren;
      if (i >= mDrawables.length) {
        addChild(i, i + 10);
      }
      paramDrawable.mutate();
      paramDrawable.setVisible(false, true);
      paramDrawable.setCallback(this$0);
      mDrawables[i] = paramDrawable;
      mChildren += 1;
      mChildrenChangingConfigurations |= paramDrawable.getChangingConfigurations();
      addChild();
      r = null;
      p = false;
      size = false;
      mCanConstantState = false;
      return i;
    }
    
    void addChild()
    {
      mHaveOpacity = false;
      mHaveStateful = false;
    }
    
    public void addChild(int paramInt1, int paramInt2)
    {
      Drawable[] arrayOfDrawable = new Drawable[paramInt2];
      System.arraycopy(mDrawables, 0, arrayOfDrawable, 0, paramInt1);
      mDrawables = arrayOfDrawable;
    }
    
    final void addChild(Resources.Theme paramTheme)
    {
      if (paramTheme != null)
      {
        remove();
        int j = mChildren;
        Drawable[] arrayOfDrawable = mDrawables;
        int i = 0;
        while (i < j)
        {
          if ((arrayOfDrawable[i] != null) && (arrayOfDrawable[i].canApplyTheme()))
          {
            arrayOfDrawable[i].applyTheme(paramTheme);
            mChildrenChangingConfigurations |= arrayOfDrawable[i].getChangingConfigurations();
          }
          i += 1;
        }
        add(paramTheme.getResources());
      }
    }
    
    public final void append(boolean paramBoolean)
    {
      b = paramBoolean;
    }
    
    public boolean canApplyTheme()
    {
      int j = mChildren;
      Drawable[] arrayOfDrawable = mDrawables;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfDrawable[i];
        if (localObject != null)
        {
          if (((Drawable)localObject).canApplyTheme()) {
            return true;
          }
        }
        else
        {
          localObject = (Drawable.ConstantState)mData.get(i);
          if ((localObject != null) && (((Drawable.ConstantState)localObject).canApplyTheme())) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    
    public boolean canConstantState()
    {
      try
      {
        if (mCanConstantState)
        {
          boolean bool = mCheckedConstantState;
          return bool;
        }
        remove();
        mCanConstantState = true;
        int j = mChildren;
        Drawable[] arrayOfDrawable = mDrawables;
        int i = 0;
        while (i < j)
        {
          if (arrayOfDrawable[i].getConstantState() == null)
          {
            mCheckedConstantState = false;
            return false;
          }
          i += 1;
        }
        mCheckedConstantState = true;
        return true;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    protected void draw()
    {
      size = true;
      remove();
      int j = mChildren;
      Drawable[] arrayOfDrawable = mDrawables;
      y = -1;
      x = -1;
      a = 0;
      width = 0;
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = arrayOfDrawable[i];
        int m = localDrawable.getIntrinsicWidth();
        if (m > x) {
          x = m;
        }
        m = localDrawable.getIntrinsicHeight();
        if (m > y) {
          y = m;
        }
        m = localDrawable.getMinimumWidth();
        if (m > width) {
          width = m;
        }
        m = localDrawable.getMinimumHeight();
        if (m > a) {
          a = m;
        }
        i += 1;
      }
    }
    
    public final int get()
    {
      if (!size) {
        draw();
      }
      return a;
    }
    
    public final Drawable get(int paramInt)
    {
      Object localObject = mDrawables[paramInt];
      if (localObject != null) {
        return localObject;
      }
      localObject = mData;
      if (localObject != null)
      {
        int i = ((SparseArray)localObject).indexOfKey(paramInt);
        if (i >= 0)
        {
          localObject = apply(((Drawable.ConstantState)mData.valueAt(i)).newDrawable(next));
          mDrawables[paramInt] = localObject;
          mData.removeAt(i);
          if (mData.size() != 0) {
            return localObject;
          }
          mData = null;
          return localObject;
        }
      }
      return null;
      return localObject;
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations | mChildrenChangingConfigurations;
    }
    
    final int getChildCount()
    {
      return mDrawables.length;
    }
    
    public final int getChildren()
    {
      return mChildren;
    }
    
    public final int getHeight()
    {
      if (!size) {
        draw();
      }
      return y;
    }
    
    public final int getMinimumWidth()
    {
      if (!size) {
        draw();
      }
      return width;
    }
    
    public final int getOpacity()
    {
      if (mHaveOpacity) {
        return mOpacity;
      }
      remove();
      int m = mChildren;
      Drawable[] arrayOfDrawable = mDrawables;
      int i;
      if (m > 0) {
        i = arrayOfDrawable[0].getOpacity();
      } else {
        i = -2;
      }
      int j = 1;
      while (j < m)
      {
        i = Drawable.resolveOpacity(i, arrayOfDrawable[j].getOpacity());
        j += 1;
      }
      mOpacity = i;
      mHaveOpacity = true;
      return i;
    }
    
    public final int getWidth()
    {
      if (!size) {
        draw();
      }
      return x;
    }
    
    public final boolean getWrapSelectorWheel()
    {
      return c;
    }
    
    abstract void init();
    
    final boolean init(int paramInt1, int paramInt2)
    {
      boolean bool2 = false;
      int j = mChildren;
      Drawable[] arrayOfDrawable = mDrawables;
      int i = 0;
      while (i < j)
      {
        boolean bool3 = bool2;
        if (arrayOfDrawable[i] != null)
        {
          boolean bool1 = false;
          if (Build.VERSION.SDK_INT >= 23) {
            bool1 = arrayOfDrawable[i].setLayoutDirection(paramInt1);
          }
          bool3 = bool2;
          if (i == paramInt2) {
            bool3 = bool1;
          }
        }
        i += 1;
        bool2 = bool3;
      }
      h = paramInt1;
      return bool2;
    }
    
    public final void setColor(int paramInt)
    {
      mAlpha = paramInt;
    }
    
    public final void setEnabled(boolean paramBoolean)
    {
      c = paramBoolean;
    }
    
    public final void setMaxValue(int paramInt)
    {
      k = paramInt;
    }
  }
}
