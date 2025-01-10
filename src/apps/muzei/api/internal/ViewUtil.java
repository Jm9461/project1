package apps.muzei.api.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import apps.muzei.api.R.attr;
import apps.muzei.api.R.styleable;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewUtil
{
  static
  {
    new AtomicInteger(1);
  }
  
  public static void applyFont(TextView paramTextView, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramAttributeSet = paramTextView.getContext().obtainStyledAttributes(paramAttributeSet, new int[] { R.attr.tv_fontFamily }, paramInt1, paramInt2);
    String str = paramAttributeSet.getString(0);
    if (str != null) {
      paramTextView.setTypeface(TypefaceUtil.load(paramTextView.getContext(), str, 0));
    }
    paramAttributeSet.recycle();
  }
  
  public static void applyStyle(View paramView, int paramInt)
  {
    applyStyle(paramView, null, 0, paramInt);
  }
  
  public static void applyStyle(View paramView, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    TypedArray localTypedArray = paramView.getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.View, paramInt1, paramInt2);
    int i = -1;
    int n = Integer.MIN_VALUE;
    int m = Integer.MIN_VALUE;
    int i7 = -1;
    int i5 = 0;
    int i4 = 0;
    int i2 = 0;
    int i3 = 0;
    int i1 = -1;
    int i10 = localTypedArray.getIndexCount();
    int k = -1;
    int i6 = 0;
    int j = -1;
    while (i6 < i10)
    {
      int i8 = localTypedArray.getIndex(i6);
      if (i8 == R.styleable.View_android_background)
      {
        setBackground(paramView, localTypedArray.getDrawable(i8));
      }
      else if (i8 == R.styleable.View_android_backgroundTint)
      {
        if (Build.VERSION.SDK_INT >= 21) {
          paramView.setBackgroundTintList(localTypedArray.getColorStateList(i8));
        }
      }
      else if (i8 == R.styleable.View_android_backgroundTintMode)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          i8 = localTypedArray.getInt(i8, 3);
          if (i8 != 3)
          {
            if (i8 != 5)
            {
              if (i8 != 9) {
                switch (i8)
                {
                default: 
                  break;
                case 16: 
                  paramView.setBackgroundTintMode(PorterDuff.Mode.ADD);
                  break;
                case 15: 
                  paramView.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
                  break;
                case 14: 
                  paramView.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
                  break;
                }
              } else {
                paramView.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
              }
            }
            else {
              paramView.setBackgroundTintMode(PorterDuff.Mode.SRC_IN);
            }
          }
          else {
            paramView.setBackgroundTintMode(PorterDuff.Mode.SRC_OVER);
          }
        }
      }
      else if (i8 == R.styleable.View_android_elevation)
      {
        if (Build.VERSION.SDK_INT >= 21) {
          paramView.setElevation(localTypedArray.getDimensionPixelOffset(i8, 0));
        }
      }
      else
      {
        if (i8 == R.styleable.View_android_padding)
        {
          i2 = 1;
          i3 = 1;
          i7 = localTypedArray.getDimensionPixelSize(i8, -1);
          i8 = n;
          i9 = m;
          break label1340;
        }
        if (i8 == R.styleable.View_android_paddingLeft)
        {
          i1 = localTypedArray.getDimensionPixelSize(i8, -1);
          i2 = 1;
          i8 = n;
          i9 = m;
          break label1340;
        }
        if (i8 == R.styleable.View_android_paddingTop)
        {
          k = localTypedArray.getDimensionPixelSize(i8, -1);
          i8 = n;
          i9 = m;
          break label1340;
        }
        if (i8 == R.styleable.View_android_paddingRight)
        {
          i = localTypedArray.getDimensionPixelSize(i8, -1);
          i3 = 1;
          i8 = n;
          i9 = m;
          break label1340;
        }
        if (i8 == R.styleable.View_android_paddingBottom)
        {
          j = localTypedArray.getDimensionPixelSize(i8, -1);
          i8 = n;
          i9 = m;
          break label1340;
        }
        if (i8 == R.styleable.View_android_paddingStart)
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            i8 = localTypedArray.getDimensionPixelSize(i8, Integer.MIN_VALUE);
            if (i8 != Integer.MIN_VALUE) {
              n = 1;
            } else {
              n = 0;
            }
            i9 = m;
            i5 = n;
            break label1340;
          }
        }
        else if (i8 == R.styleable.View_android_paddingEnd)
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            i9 = localTypedArray.getDimensionPixelSize(i8, Integer.MIN_VALUE);
            if (i9 != Integer.MIN_VALUE) {
              m = 1;
            } else {
              m = 0;
            }
            i8 = n;
            i4 = m;
            break label1340;
          }
        }
        else if (i8 == R.styleable.View_android_fadeScrollbars)
        {
          paramView.setScrollbarFadingEnabled(localTypedArray.getBoolean(i8, true));
        }
        else if (i8 == R.styleable.View_android_fadingEdgeLength)
        {
          paramView.setFadingEdgeLength(localTypedArray.getDimensionPixelOffset(i8, 0));
        }
        else if (i8 == R.styleable.View_android_minHeight)
        {
          paramView.setMinimumHeight(localTypedArray.getDimensionPixelSize(i8, 0));
        }
        else if (i8 == R.styleable.View_android_minWidth)
        {
          paramView.setMinimumWidth(localTypedArray.getDimensionPixelSize(i8, 0));
        }
        else if (i8 == R.styleable.View_android_requiresFadingEdge)
        {
          paramView.setVerticalFadingEdgeEnabled(localTypedArray.getBoolean(i8, true));
        }
        else if (i8 == R.styleable.View_android_scrollbarDefaultDelayBeforeFade)
        {
          if (Build.VERSION.SDK_INT >= 16) {
            paramView.setScrollBarDefaultDelayBeforeFade(localTypedArray.getInteger(i8, 0));
          }
        }
        else if (i8 == R.styleable.View_android_scrollbarFadeDuration)
        {
          if (Build.VERSION.SDK_INT >= 16) {
            paramView.setScrollBarFadeDuration(localTypedArray.getInteger(i8, 0));
          }
        }
        else if (i8 == R.styleable.View_android_scrollbarSize)
        {
          if (Build.VERSION.SDK_INT >= 16) {
            paramView.setScrollBarSize(localTypedArray.getDimensionPixelSize(i8, 0));
          }
        }
        else if (i8 == R.styleable.View_android_scrollbarStyle)
        {
          i8 = localTypedArray.getInteger(i8, 0);
          if (i8 != 0)
          {
            if (i8 != 16777216)
            {
              if (i8 != 33554432)
              {
                if (i8 == 50331648) {
                  paramView.setScrollBarStyle(50331648);
                }
              }
              else {
                paramView.setScrollBarStyle(33554432);
              }
            }
            else {
              paramView.setScrollBarStyle(16777216);
            }
          }
          else {
            paramView.setScrollBarStyle(0);
          }
        }
        else if (i8 == R.styleable.View_android_soundEffectsEnabled)
        {
          paramView.setSoundEffectsEnabled(localTypedArray.getBoolean(i8, true));
        }
        else if (i8 == R.styleable.View_android_textAlignment)
        {
          if (Build.VERSION.SDK_INT >= 17) {
            switch (localTypedArray.getInteger(i8, 0))
            {
            default: 
              break;
            case 6: 
              paramView.setTextAlignment(6);
              break;
            case 5: 
              paramView.setTextAlignment(5);
              break;
            case 4: 
              paramView.setTextAlignment(4);
              break;
            case 3: 
              paramView.setTextAlignment(3);
              break;
            case 2: 
              paramView.setTextAlignment(2);
              break;
            case 1: 
              paramView.setTextAlignment(1);
              break;
            case 0: 
              paramView.setTextAlignment(0);
            }
          }
        }
        else if (i8 == R.styleable.View_android_textDirection)
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            i8 = localTypedArray.getInteger(i8, 0);
            if (i8 != 0)
            {
              if (i8 != 1)
              {
                if (i8 != 2)
                {
                  if (i8 != 3)
                  {
                    if (i8 != 4)
                    {
                      if (i8 == 5) {
                        paramView.setTextDirection(5);
                      }
                    }
                    else {
                      paramView.setTextDirection(4);
                    }
                  }
                  else {
                    paramView.setTextDirection(3);
                  }
                }
                else {
                  paramView.setTextDirection(2);
                }
              }
              else {
                paramView.setTextDirection(1);
              }
            }
            else {
              paramView.setTextDirection(0);
            }
          }
        }
        else if (i8 == R.styleable.View_android_visibility)
        {
          i8 = localTypedArray.getInteger(i8, 0);
          if (i8 != 0)
          {
            if (i8 != 1)
            {
              if (i8 == 2) {
                paramView.setVisibility(8);
              }
            }
            else {
              paramView.setVisibility(4);
            }
          }
          else {
            paramView.setVisibility(0);
          }
        }
        else if (i8 == R.styleable.View_android_layoutDirection)
        {
          if (Build.VERSION.SDK_INT >= 17)
          {
            i8 = localTypedArray.getInteger(i8, 0);
            if (i8 != 0)
            {
              if (i8 != 1)
              {
                if (i8 != 2)
                {
                  if (i8 == 3) {
                    paramView.setLayoutDirection(3);
                  }
                }
                else {
                  paramView.setLayoutDirection(2);
                }
              }
              else {
                paramView.setLayoutDirection(1);
              }
            }
            else {
              paramView.setLayoutDirection(0);
            }
          }
        }
        else if ((i8 == R.styleable.View_android_src) && ((paramView instanceof ImageView)))
        {
          i8 = localTypedArray.getResourceId(i8, 0);
          ((ImageView)paramView).setImageResource(i8);
        }
      }
      int i9 = m;
      i8 = n;
      label1340:
      i6 += 1;
      n = i8;
      m = i9;
    }
    if (i7 >= 0)
    {
      paramView.setPadding(i7, i7, i7, i7);
    }
    else if (Build.VERSION.SDK_INT < 17)
    {
      if (i5 != 0) {
        i1 = n;
      }
      if (i4 != 0) {
        i = m;
      }
      if (i1 >= 0) {
        m = i1;
      } else {
        m = paramView.getPaddingLeft();
      }
      if (k < 0) {
        k = paramView.getPaddingTop();
      }
      if (i < 0) {
        i = paramView.getPaddingRight();
      }
      if (j < 0) {
        j = paramView.getPaddingBottom();
      }
      paramView.setPadding(m, k, i, j);
    }
    else
    {
      if ((i2 != 0) || (i3 != 0))
      {
        if (i2 == 0) {
          i1 = paramView.getPaddingLeft();
        }
        if (k >= 0) {
          i2 = k;
        } else {
          i2 = paramView.getPaddingTop();
        }
        if (i3 == 0) {
          i = paramView.getPaddingRight();
        }
        if (j >= 0) {
          i3 = j;
        } else {
          i3 = paramView.getPaddingBottom();
        }
        paramView.setPadding(i1, i2, i, i3);
      }
      if ((i5 != 0) || (i4 != 0))
      {
        if (i5 == 0) {
          n = paramView.getPaddingStart();
        }
        if (k < 0) {
          k = paramView.getPaddingTop();
        }
        if (i4 == 0) {
          m = paramView.getPaddingEnd();
        }
        if (j < 0) {
          j = paramView.getPaddingBottom();
        }
        paramView.setPaddingRelative(n, k, m, j);
      }
    }
    localTypedArray.recycle();
    if ((paramView instanceof TextView)) {
      applyStyle((TextView)paramView, paramAttributeSet, paramInt1, paramInt2);
    }
  }
  
  private static void applyStyle(AutoCompleteTextView paramAutoCompleteTextView, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramAttributeSet = paramAutoCompleteTextView.getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.AutoCompleteTextView, paramInt1, paramInt2);
    paramInt2 = paramAttributeSet.getIndexCount();
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      int i = paramAttributeSet.getIndex(paramInt1);
      if (i == R.styleable.AutoCompleteTextView_android_completionHint) {
        paramAutoCompleteTextView.setCompletionHint(paramAttributeSet.getString(i));
      } else if (i == R.styleable.AutoCompleteTextView_android_completionThreshold) {
        paramAutoCompleteTextView.setThreshold(paramAttributeSet.getInteger(i, 0));
      } else if (i == R.styleable.AutoCompleteTextView_android_dropDownAnchor) {
        paramAutoCompleteTextView.setDropDownAnchor(paramAttributeSet.getResourceId(i, 0));
      } else if (i == R.styleable.AutoCompleteTextView_android_dropDownHeight) {
        paramAutoCompleteTextView.setDropDownHeight(paramAttributeSet.getLayoutDimension(i, -2));
      } else if (i == R.styleable.AutoCompleteTextView_android_dropDownWidth) {
        paramAutoCompleteTextView.setDropDownWidth(paramAttributeSet.getLayoutDimension(i, -2));
      } else if (i == R.styleable.AutoCompleteTextView_android_dropDownHorizontalOffset) {
        paramAutoCompleteTextView.setDropDownHorizontalOffset(paramAttributeSet.getDimensionPixelSize(i, 0));
      } else if (i == R.styleable.AutoCompleteTextView_android_dropDownVerticalOffset) {
        paramAutoCompleteTextView.setDropDownVerticalOffset(paramAttributeSet.getDimensionPixelSize(i, 0));
      } else if (i == R.styleable.AutoCompleteTextView_android_popupBackground) {
        paramAutoCompleteTextView.setDropDownBackgroundDrawable(paramAttributeSet.getDrawable(i));
      }
      paramInt1 += 1;
    }
    paramAttributeSet.recycle();
  }
  
  public static void applyStyle(TextView paramTextView, int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    Object localObject2 = null;
    Object localObject1 = null;
    int m = -1;
    int i = -1;
    int k = 0;
    int n = 0;
    float f1 = 0.0F;
    float f4 = 0.0F;
    float f2 = 0.0F;
    float f5 = 0.0F;
    float f3 = 0.0F;
    float f6 = 0.0F;
    Object localObject3 = paramTextView.getContext().obtainStyledAttributes(paramInt, R.styleable.TextAppearance);
    paramInt = m;
    int j = i;
    if (localObject3 != null)
    {
      int i2 = ((TypedArray)localObject3).getIndexCount();
      k = 0;
      f3 = f6;
      f2 = f5;
      f1 = f4;
      j = n;
      paramInt = m;
      while (k < i2)
      {
        int i3 = ((TypedArray)localObject3).getIndex(k);
        int i1;
        if (i3 == R.styleable.TextAppearance_android_textColorHighlight)
        {
          paramTextView.setHighlightColor(((TypedArray)localObject3).getColor(i3, 0));
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textColor)
        {
          paramTextView.setTextColor(((TypedArray)localObject3).getColorStateList(i3));
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textColorHint)
        {
          paramTextView.setHintTextColor(((TypedArray)localObject3).getColorStateList(i3));
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textColorLink)
        {
          paramTextView.setLinkTextColor(((TypedArray)localObject3).getColorStateList(i3));
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textSize)
        {
          paramTextView.setTextSize(0, ((TypedArray)localObject3).getDimensionPixelSize(i3, 0));
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_typeface)
        {
          m = ((TypedArray)localObject3).getInt(i3, -1);
          localObject2 = localObject1;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_fontFamily)
        {
          localObject2 = ((TypedArray)localObject3).getString(i3);
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_tv_fontFamily)
        {
          localObject2 = ((TypedArray)localObject3).getString(i3);
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textStyle)
        {
          n = ((TypedArray)localObject3).getInt(i3, -1);
          localObject2 = localObject1;
          m = paramInt;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_textAllCaps)
        {
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
          if (Build.VERSION.SDK_INT >= 14)
          {
            paramTextView.setAllCaps(((TypedArray)localObject3).getBoolean(i3, false));
            localObject2 = localObject1;
            m = paramInt;
            n = i;
            i1 = j;
            f4 = f1;
            f5 = f2;
            f6 = f3;
          }
        }
        else if (i3 == R.styleable.TextAppearance_android_shadowColor)
        {
          i1 = ((TypedArray)localObject3).getInt(i3, 0);
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          f4 = f1;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_shadowDx)
        {
          f4 = ((TypedArray)localObject3).getFloat(i3, 0.0F);
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f5 = f2;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_shadowDy)
        {
          f5 = ((TypedArray)localObject3).getFloat(i3, 0.0F);
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f6 = f3;
        }
        else if (i3 == R.styleable.TextAppearance_android_shadowRadius)
        {
          f6 = ((TypedArray)localObject3).getFloat(i3, 0.0F);
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
        }
        else if (i3 == R.styleable.TextAppearance_android_elegantTextHeight)
        {
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
          if (Build.VERSION.SDK_INT >= 21)
          {
            paramTextView.setElegantTextHeight(((TypedArray)localObject3).getBoolean(i3, false));
            localObject2 = localObject1;
            m = paramInt;
            n = i;
            i1 = j;
            f4 = f1;
            f5 = f2;
            f6 = f3;
          }
        }
        else if (i3 == R.styleable.TextAppearance_android_letterSpacing)
        {
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
          if (Build.VERSION.SDK_INT >= 21)
          {
            paramTextView.setLetterSpacing(((TypedArray)localObject3).getFloat(i3, 0.0F));
            localObject2 = localObject1;
            m = paramInt;
            n = i;
            i1 = j;
            f4 = f1;
            f5 = f2;
            f6 = f3;
          }
        }
        else
        {
          localObject2 = localObject1;
          m = paramInt;
          n = i;
          i1 = j;
          f4 = f1;
          f5 = f2;
          f6 = f3;
          if (i3 == R.styleable.TextAppearance_android_fontFeatureSettings)
          {
            localObject2 = localObject1;
            m = paramInt;
            n = i;
            i1 = j;
            f4 = f1;
            f5 = f2;
            f6 = f3;
            if (Build.VERSION.SDK_INT >= 21)
            {
              paramTextView.setFontFeatureSettings(((TypedArray)localObject3).getString(i3));
              f6 = f3;
              f5 = f2;
              f4 = f1;
              i1 = j;
              n = i;
              m = paramInt;
              localObject2 = localObject1;
            }
          }
        }
        k += 1;
        localObject1 = localObject2;
        paramInt = m;
        i = n;
        j = i1;
        f1 = f4;
        f2 = f5;
        f3 = f6;
      }
      ((TypedArray)localObject3).recycle();
      k = j;
      j = i;
      localObject2 = localObject1;
    }
    if (k != 0) {
      paramTextView.setShadowLayer(f3, f1, f2, k);
    }
    localObject1 = null;
    if (localObject2 != null)
    {
      localObject3 = TypefaceUtil.load(paramTextView.getContext(), (String)localObject2, j);
      localObject2 = localObject3;
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        paramTextView.setTypeface((Typeface)localObject3);
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          if (paramInt == 3) {
            localObject1 = Typeface.MONOSPACE;
          }
        }
        else {
          localObject1 = Typeface.SERIF;
        }
      }
      else {
        localObject1 = Typeface.SANS_SERIF;
      }
      paramTextView.setTypeface((Typeface)localObject1, j);
    }
  }
  
  private static void applyStyle(TextView paramTextView, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    float f3 = 0.0F;
    float f2 = 0.0F;
    Object localObject8 = null;
    int i3 = 0;
    Object localObject1 = paramTextView.getContext();
    int k = -1;
    localObject1 = ((Context)localObject1).obtainStyledAttributes(paramAttributeSet, R.styleable.TextViewAppearance, paramInt1, paramInt2);
    int i = ((TypedArray)localObject1).getResourceId(R.styleable.TextViewAppearance_android_textAppearance, 0);
    ((TypedArray)localObject1).recycle();
    if (i != 0) {
      localObject2 = paramTextView.getContext().obtainStyledAttributes(i, R.styleable.TextAppearance);
    } else {
      localObject2 = null;
    }
    int j;
    float f1;
    if (localObject2 != null)
    {
      n = ((TypedArray)localObject2).getIndexCount();
      i = 0;
      m = 0;
      j = -1;
      localObject1 = null;
      f1 = 0.0F;
      f2 = 0.0F;
      f3 = 0.0F;
      while (m < n)
      {
        i1 = ((TypedArray)localObject2).getIndex(m);
        if (i1 == R.styleable.TextAppearance_android_textColorHighlight) {
          paramTextView.setHighlightColor(((TypedArray)localObject2).getColor(i1, 0));
        } else if (i1 == R.styleable.TextAppearance_android_textColor) {
          paramTextView.setTextColor(((TypedArray)localObject2).getColorStateList(i1));
        } else if (i1 == R.styleable.TextAppearance_android_textColorHint) {
          paramTextView.setHintTextColor(((TypedArray)localObject2).getColorStateList(i1));
        } else if (i1 == R.styleable.TextAppearance_android_textColorLink) {
          paramTextView.setLinkTextColor(((TypedArray)localObject2).getColorStateList(i1));
        } else if (i1 == R.styleable.TextAppearance_android_textSize) {
          paramTextView.setTextSize(0, ((TypedArray)localObject2).getDimensionPixelSize(i1, 0));
        } else if (i1 == R.styleable.TextAppearance_android_typeface) {
          k = ((TypedArray)localObject2).getInt(i1, -1);
        } else if (i1 == R.styleable.TextAppearance_android_fontFamily) {
          localObject1 = ((TypedArray)localObject2).getString(i1);
        } else if (i1 == R.styleable.TextAppearance_tv_fontFamily) {
          localObject1 = ((TypedArray)localObject2).getString(i1);
        } else if (i1 == R.styleable.TextAppearance_android_textStyle) {
          j = ((TypedArray)localObject2).getInt(i1, -1);
        } else if (i1 == R.styleable.TextAppearance_android_textAllCaps)
        {
          if (Build.VERSION.SDK_INT >= 14) {
            paramTextView.setAllCaps(((TypedArray)localObject2).getBoolean(i1, false));
          }
        }
        else if (i1 == R.styleable.TextAppearance_android_shadowColor) {
          i = ((TypedArray)localObject2).getInt(i1, 0);
        } else if (i1 == R.styleable.TextAppearance_android_shadowDx) {
          f3 = ((TypedArray)localObject2).getFloat(i1, 0.0F);
        } else if (i1 == R.styleable.TextAppearance_android_shadowDy) {
          f2 = ((TypedArray)localObject2).getFloat(i1, 0.0F);
        } else if (i1 == R.styleable.TextAppearance_android_shadowRadius) {
          f1 = ((TypedArray)localObject2).getFloat(i1, 0.0F);
        } else if (i1 == R.styleable.TextAppearance_android_elegantTextHeight)
        {
          if (Build.VERSION.SDK_INT >= 21) {
            paramTextView.setElegantTextHeight(((TypedArray)localObject2).getBoolean(i1, false));
          }
        }
        else if (i1 == R.styleable.TextAppearance_android_letterSpacing)
        {
          if (Build.VERSION.SDK_INT >= 21) {
            paramTextView.setLetterSpacing(((TypedArray)localObject2).getFloat(i1, 0.0F));
          }
        }
        else if ((i1 == R.styleable.TextAppearance_android_fontFeatureSettings) && (Build.VERSION.SDK_INT >= 21)) {
          paramTextView.setFontFeatureSettings(((TypedArray)localObject2).getString(i1));
        }
        m += 1;
      }
      ((TypedArray)localObject2).recycle();
    }
    else
    {
      i = 0;
      j = -1;
      localObject1 = null;
      f1 = 0.0F;
    }
    TypedArray localTypedArray = paramTextView.getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.TextView, paramInt1, paramInt2);
    int i7 = localTypedArray.getIndexCount();
    int n = 0;
    float f4 = f3;
    f3 = f2;
    int m = 0;
    Object localObject6 = null;
    Object localObject7 = null;
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = null;
    f2 = f4;
    Object localObject5 = localObject1;
    int i1 = i;
    int i2 = j;
    while (n < i7)
    {
      int i8 = localTypedArray.getIndex(n);
      Object localObject14;
      Object localObject9;
      int i4;
      int i5;
      float f5;
      float f6;
      int i6;
      Object localObject10;
      Object localObject11;
      Object localObject12;
      Object localObject13;
      if (i8 == R.styleable.TextView_android_drawableLeft)
      {
        i = 1;
        localObject14 = localTypedArray.getDrawable(i8);
        f4 = f3;
        localObject9 = localObject8;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
      }
      else if (i8 == R.styleable.TextView_android_drawableTop)
      {
        i = 1;
        localObject13 = localTypedArray.getDrawable(i8);
        f4 = f3;
        localObject9 = localObject8;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_drawableRight)
      {
        i = 1;
        localObject12 = localTypedArray.getDrawable(i8);
        f4 = f3;
        localObject9 = localObject8;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_drawableBottom)
      {
        i = 1;
        localObject11 = localTypedArray.getDrawable(i8);
        f4 = f3;
        localObject9 = localObject8;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_drawableStart)
      {
        j = 1;
        localObject9 = localTypedArray.getDrawable(i8);
        f4 = f3;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_drawableEnd)
      {
        j = 1;
        localObject10 = localTypedArray.getDrawable(i8);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_drawablePadding)
      {
        paramTextView.setCompoundDrawablePadding(localTypedArray.getDimensionPixelSize(i8, 0));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_maxLines)
      {
        paramTextView.setMaxLines(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_maxHeight)
      {
        paramTextView.setMaxHeight(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_lines)
      {
        paramTextView.setLines(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_height)
      {
        paramTextView.setHeight(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_minLines)
      {
        paramTextView.setMinLines(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_minHeight)
      {
        paramTextView.setMinHeight(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_maxEms)
      {
        paramTextView.setMaxEms(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_maxWidth)
      {
        paramTextView.setMaxWidth(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_ems)
      {
        paramTextView.setEms(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_width)
      {
        paramTextView.setWidth(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_minEms)
      {
        paramTextView.setMinEms(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_minWidth)
      {
        paramTextView.setMinWidth(localTypedArray.getDimensionPixelSize(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_gravity)
      {
        paramTextView.setGravity(localTypedArray.getInt(i8, -1));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_scrollHorizontally)
      {
        paramTextView.setHorizontallyScrolling(localTypedArray.getBoolean(i8, false));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_includeFontPadding)
      {
        paramTextView.setIncludeFontPadding(localTypedArray.getBoolean(i8, true));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_cursorVisible)
      {
        paramTextView.setCursorVisible(localTypedArray.getBoolean(i8, true));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textScaleX)
      {
        paramTextView.setTextScaleX(localTypedArray.getFloat(i8, 1.0F));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_shadowColor)
      {
        i5 = localTypedArray.getInt(i8, 0);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_shadowDx)
      {
        f6 = localTypedArray.getFloat(i8, 0.0F);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_shadowDy)
      {
        f4 = localTypedArray.getFloat(i8, 0.0F);
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_shadowRadius)
      {
        f5 = localTypedArray.getFloat(i8, 0.0F);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textColorHighlight)
      {
        paramTextView.setHighlightColor(localTypedArray.getColor(i8, 0));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textColor)
      {
        paramTextView.setTextColor(localTypedArray.getColorStateList(i8));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textColorHint)
      {
        paramTextView.setHintTextColor(localTypedArray.getColorStateList(i8));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textColorLink)
      {
        paramTextView.setLinkTextColor(localTypedArray.getColorStateList(i8));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textSize)
      {
        paramTextView.setTextSize(0, localTypedArray.getDimensionPixelSize(i8, 0));
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_typeface)
      {
        i6 = localTypedArray.getInt(i8, -1);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textStyle)
      {
        i4 = localTypedArray.getInt(i8, -1);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_fontFamily)
      {
        localObject1 = localTypedArray.getString(i8);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_tv_fontFamily)
      {
        localObject1 = localTypedArray.getString(i8);
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
      }
      else if (i8 == R.styleable.TextView_android_textAllCaps)
      {
        if (Build.VERSION.SDK_INT >= 14)
        {
          paramTextView.setAllCaps(localTypedArray.getBoolean(i8, false));
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
        else
        {
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
      }
      else if (i8 == R.styleable.TextView_android_elegantTextHeight)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramTextView.setElegantTextHeight(localTypedArray.getBoolean(i8, false));
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
        else
        {
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
      }
      else if (i8 == R.styleable.TextView_android_letterSpacing)
      {
        if (Build.VERSION.SDK_INT >= 21)
        {
          paramTextView.setLetterSpacing(localTypedArray.getFloat(i8, 0.0F));
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
        else
        {
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
        }
      }
      else
      {
        f4 = f3;
        localObject9 = localObject8;
        i = i3;
        i4 = i2;
        i5 = i1;
        localObject1 = localObject5;
        f5 = f1;
        f6 = f2;
        i6 = k;
        j = m;
        localObject10 = localObject6;
        localObject11 = localObject7;
        localObject12 = localObject4;
        localObject13 = localObject3;
        localObject14 = localObject2;
        if (i8 == R.styleable.TextView_android_fontFeatureSettings)
        {
          f4 = f3;
          localObject9 = localObject8;
          i = i3;
          i4 = i2;
          i5 = i1;
          localObject1 = localObject5;
          f5 = f1;
          f6 = f2;
          i6 = k;
          j = m;
          localObject10 = localObject6;
          localObject11 = localObject7;
          localObject12 = localObject4;
          localObject13 = localObject3;
          localObject14 = localObject2;
          if (Build.VERSION.SDK_INT >= 21)
          {
            paramTextView.setFontFeatureSettings(localTypedArray.getString(i8));
            f4 = f3;
            localObject9 = localObject8;
            i = i3;
            i4 = i2;
            i5 = i1;
            localObject1 = localObject5;
            f5 = f1;
            f6 = f2;
            i6 = k;
            j = m;
            localObject10 = localObject6;
            localObject11 = localObject7;
            localObject12 = localObject4;
            localObject13 = localObject3;
            localObject14 = localObject2;
          }
        }
      }
      n += 1;
      f3 = f4;
      localObject8 = localObject9;
      i3 = i;
      i2 = i4;
      i1 = i5;
      localObject5 = localObject1;
      f1 = f5;
      f2 = f6;
      k = i6;
      m = j;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject4 = localObject12;
      localObject3 = localObject13;
      localObject2 = localObject14;
    }
    localTypedArray.recycle();
    if (i1 != 0) {
      paramTextView.setShadowLayer(f1, f2, f3, i1);
    }
    if (i3 != 0)
    {
      localObject1 = paramTextView.getCompoundDrawables();
      if (localObject8 != null) {
        localObject1[0] = localObject8;
      } else if (localObject2 != null) {
        localObject1[0] = localObject2;
      }
      if (localObject3 != null) {
        localObject1[1] = localObject3;
      }
      if (localObject6 != null) {
        localObject1[2] = localObject6;
      } else if (localObject4 != null) {
        localObject1[2] = localObject4;
      }
      if (localObject7 != null) {
        localObject1[3] = localObject7;
      }
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(localObject1[0], localObject1[1], localObject1[2], localObject1[3]);
    }
    if ((m != 0) && (Build.VERSION.SDK_INT >= 17))
    {
      localObject1 = paramTextView.getCompoundDrawablesRelative();
      if (localObject8 != null) {
        localObject1[0] = localObject8;
      }
      if (localObject6 != null) {
        localObject1[2] = localObject6;
      }
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(localObject1[0], localObject1[1], localObject1[2], localObject1[3]);
    }
    localObject1 = null;
    if (localObject5 != null)
    {
      localObject3 = TypefaceUtil.load(paramTextView.getContext(), localObject5, i2);
      localObject2 = localObject3;
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        paramTextView.setTypeface((Typeface)localObject3);
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      if (k != 1)
      {
        if (k != 2)
        {
          if (k == 3) {
            localObject1 = Typeface.MONOSPACE;
          }
        }
        else {
          localObject1 = Typeface.SERIF;
        }
      }
      else {
        localObject1 = Typeface.SANS_SERIF;
      }
      paramTextView.setTypeface((Typeface)localObject1, i2);
    }
    if ((paramTextView instanceof AutoCompleteTextView)) {
      applyStyle((AutoCompleteTextView)paramTextView, paramAttributeSet, paramInt1, paramInt2);
    }
  }
  
  public static boolean hasState(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null) {
      return false;
    }
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
}
