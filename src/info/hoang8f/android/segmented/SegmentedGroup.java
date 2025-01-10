package info.hoang8f.android.segmented;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;

public class SegmentedGroup
  extends RadioGroup
{
  private a b;
  private int mCheckedTextColor = -1;
  private Float mCornerRadius = Float.valueOf(getResources().getDimension(R.dimen.radio_button_conner_radius));
  private int mTintColor = resources.getColor(R.color.radio_button_selected_color);
  private int oneDP = (int)getResources().getDimension(R.dimen.radio_button_stroke_border);
  private Resources resources = getResources();
  
  public SegmentedGroup(Context paramContext)
  {
    super(paramContext);
    b = new a(mCornerRadius.floatValue());
  }
  
  public SegmentedGroup(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAttrs(paramAttributeSet);
    b = new a(mCornerRadius.floatValue());
  }
  
  private void initAttrs(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = getContext().getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.SegmentedGroup, 0, 0);
    try
    {
      oneDP = ((int)paramAttributeSet.getDimension(R.styleable.SegmentedGroup_sc_border_width, getResources().getDimension(R.dimen.radio_button_stroke_border)));
      mCornerRadius = Float.valueOf(paramAttributeSet.getDimension(R.styleable.SegmentedGroup_sc_corner_radius, getResources().getDimension(R.dimen.radio_button_conner_radius)));
      mTintColor = paramAttributeSet.getColor(R.styleable.SegmentedGroup_sc_tint_color, getResources().getColor(R.color.radio_button_selected_color));
      mCheckedTextColor = paramAttributeSet.getColor(R.styleable.SegmentedGroup_sc_checked_text_color, getResources().getColor(17170443));
      paramAttributeSet.recycle();
      return;
    }
    catch (Throwable localThrowable)
    {
      paramAttributeSet.recycle();
      throw localThrowable;
    }
  }
  
  private void updateBackground(View paramView)
  {
    int i = b.hashcode();
    int j = b.b();
    Object localObject = { -16842919, 16842912 };
    int k = mTintColor;
    int m = mCheckedTextColor;
    localObject = new ColorStateList(new int[][] { { 16842919 }, { -16842919, -16842912 }, localObject }, new int[] { -7829368, k, m });
    ((Button)paramView).setTextColor((ColorStateList)localObject);
    localObject = resources.getDrawable(i).mutate();
    Drawable localDrawable = resources.getDrawable(j).mutate();
    ((GradientDrawable)localObject).setColor(mTintColor);
    ((GradientDrawable)localObject).setStroke(oneDP, mTintColor);
    ((GradientDrawable)localDrawable).setStroke(oneDP, mTintColor);
    ((GradientDrawable)localObject).setCornerRadii(b.c(paramView));
    ((GradientDrawable)localDrawable).setCornerRadii(b.c(paramView));
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { -16842912 }, localDrawable);
    localStateListDrawable.addState(new int[] { 16842912 }, (Drawable)localObject);
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(localStateListDrawable);
      return;
    }
    paramView.setBackgroundDrawable(localStateListDrawable);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    updateBackground();
  }
  
  public void setTintColor(int paramInt)
  {
    mTintColor = paramInt;
    updateBackground();
  }
  
  public void updateBackground()
  {
    int j = super.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      updateBackground(localView);
      if (i == j - 1) {
        return;
      }
      RadioGroup.LayoutParams localLayoutParams = (RadioGroup.LayoutParams)localView.getLayoutParams();
      localLayoutParams = new RadioGroup.LayoutParams(width, height, weight);
      if (getOrientation() == 0) {
        localLayoutParams.setMargins(0, 0, -oneDP, 0);
      } else {
        localLayoutParams.setMargins(0, 0, 0, -oneDP);
      }
      localView.setLayoutParams(localLayoutParams);
      i += 1;
    }
  }
  
  private class a
  {
    private final float[] a;
    private float[] c;
    private final float[] d;
    private final float[] e;
    private int f = -1;
    private float g;
    private int i = -1;
    private final float[] j;
    private final float[] k;
    private final int p = R.drawable.radio_unchecked;
    private final float s = TypedValue.applyDimension(1, 0.1F, getResources().getDisplayMetrics());
    private final int time = R.drawable.radio_checked;
    private final float[] v;
    
    public a(float paramFloat)
    {
      g = paramFloat;
      paramFloat = g;
      float f1 = s;
      e = new float[] { paramFloat, paramFloat, f1, f1, f1, f1, paramFloat, paramFloat };
      a = new float[] { f1, f1, paramFloat, paramFloat, paramFloat, paramFloat, f1, f1 };
      v = new float[] { f1, f1, f1, f1, f1, f1, f1, f1 };
      d = new float[] { paramFloat, paramFloat, paramFloat, paramFloat, paramFloat, paramFloat, paramFloat, paramFloat };
      j = new float[] { paramFloat, paramFloat, paramFloat, paramFloat, f1, f1, f1, f1 };
      k = new float[] { f1, f1, f1, f1, paramFloat, paramFloat, paramFloat, paramFloat };
    }
    
    private int a()
    {
      return getChildCount();
    }
    
    private void a(int paramInt1, int paramInt2)
    {
      if ((i == paramInt1) && (f == paramInt2)) {
        return;
      }
      i = paramInt1;
      f = paramInt2;
      paramInt1 = i;
      if (paramInt1 == 1)
      {
        c = d;
        return;
      }
      paramInt2 = f;
      float[] arrayOfFloat;
      if (paramInt2 == 0)
      {
        if (getOrientation() == 0) {
          arrayOfFloat = e;
        } else {
          arrayOfFloat = j;
        }
        c = arrayOfFloat;
        return;
      }
      if (paramInt2 == paramInt1 - 1)
      {
        if (getOrientation() == 0) {
          arrayOfFloat = a;
        } else {
          arrayOfFloat = k;
        }
        c = arrayOfFloat;
        return;
      }
      c = v;
    }
    
    private int b(View paramView)
    {
      return indexOfChild(paramView);
    }
    
    public int b()
    {
      return p;
    }
    
    public float[] c(View paramView)
    {
      a(a(), b(paramView));
      return c;
    }
    
    public int hashcode()
    {
      return time;
    }
  }
}
