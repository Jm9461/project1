package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.Space;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;
import org.org.android.R.dimen;
import org.org.android.R.id;
import org.org.android.asm.Item;
import org.org.android.asm.Plot.a;

final class f
{
  private int A;
  private android.widget.TextView a;
  private boolean b;
  private android.widget.TextView c;
  private FrameLayout d;
  private final Context e;
  private LinearLayout f;
  private final TextInputLayout g;
  private int h;
  private boolean j;
  private CharSequence l;
  private Animator n;
  private int p;
  private int q;
  private CharSequence r;
  private int s;
  private final float t;
  private int x;
  private Typeface z;
  
  public f(TextInputLayout paramTextInputLayout)
  {
    e = paramTextInputLayout.getContext();
    g = paramTextInputLayout;
    t = e.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return;
    }
    android.widget.TextView localTextView;
    if (paramInt2 != 0)
    {
      localTextView = getTitle(paramInt2);
      if (localTextView != null)
      {
        localTextView.setVisibility(0);
        localTextView.setAlpha(1.0F);
      }
    }
    if (paramInt1 != 0)
    {
      localTextView = getTitle(paramInt1);
      if (localTextView != null)
      {
        localTextView.setVisibility(4);
        if (paramInt1 == 1) {
          localTextView.setText(null);
        }
      }
    }
    q = paramInt2;
  }
  
  private void a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      AnimatorSet localAnimatorSet = new AnimatorSet();
      n = localAnimatorSet;
      ArrayList localArrayList = new ArrayList();
      add(localArrayList, b, c, 2, paramInt1, paramInt2);
      add(localArrayList, j, a, 1, paramInt1, paramInt2);
      Plot.a.run(localAnimatorSet, localArrayList);
      localAnimatorSet.addListener(new MainActivity.3(this, paramInt2, getTitle(paramInt1), paramInt1, getTitle(paramInt2)));
      localAnimatorSet.start();
    }
    else
    {
      a(paramInt1, paramInt2);
    }
    g.updateEditTextBackground();
    g.addView(paramBoolean);
    g.clear();
  }
  
  private void a(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramInt == 0) {
      paramViewGroup.setVisibility(8);
    }
  }
  
  private boolean a(android.widget.TextView paramTextView, CharSequence paramCharSequence)
  {
    return (ViewCompat.get(g)) && (g.isEnabled()) && ((s != q) || (paramTextView == null) || (!TextUtils.equals(paramTextView.getText(), paramCharSequence)));
  }
  
  private void add(List paramList, boolean paramBoolean, android.widget.TextView paramTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramTextView != null)
    {
      if (!paramBoolean) {
        return;
      }
      if ((paramInt1 == paramInt3) || (paramInt1 == paramInt2))
      {
        if (paramInt3 == paramInt1) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        }
        paramList.add(start(paramTextView, paramBoolean));
        if (paramInt3 == paramInt1) {
          paramList.add(start(paramTextView));
        }
      }
    }
  }
  
  private boolean c(int paramInt)
  {
    return (paramInt == 1) && (a != null) && (!TextUtils.isEmpty(l));
  }
  
  private boolean f()
  {
    return (f != null) && (g.getEditText() != null);
  }
  
  private android.widget.TextView getTitle(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2) {
        return null;
      }
      return c;
    }
    return a;
  }
  
  private void setText(android.widget.TextView paramTextView, Typeface paramTypeface)
  {
    if (paramTextView != null) {
      paramTextView.setTypeface(paramTypeface);
    }
  }
  
  private ObjectAnimator start(android.widget.TextView paramTextView)
  {
    paramTextView = ObjectAnimator.ofFloat(paramTextView, View.TRANSLATION_Y, new float[] { -t, 0.0F });
    paramTextView.setDuration(217L);
    paramTextView.setInterpolator(Item.a);
    return paramTextView;
  }
  
  private ObjectAnimator start(android.widget.TextView paramTextView, boolean paramBoolean)
  {
    float f1;
    if (paramBoolean) {
      f1 = 1.0F;
    } else {
      f1 = 0.0F;
    }
    paramTextView = ObjectAnimator.ofFloat(paramTextView, View.ALPHA, new float[] { f1 });
    paramTextView.setDuration(167L);
    paramTextView.setInterpolator(Item.FAST_OUT_SLOW_IN_INTERPOLATOR);
    return paramTextView;
  }
  
  void a()
  {
    if (f()) {
      ViewCompat.setPaddingRelative(f, ViewCompat.getPaddingStart(g.getEditText()), 0, ViewCompat.getPaddingEnd(g.getEditText()), 0);
    }
  }
  
  void a(int paramInt)
  {
    A = paramInt;
    android.widget.TextView localTextView = a;
    if (localTextView != null) {
      g.init(localTextView, paramInt);
    }
  }
  
  void a(ColorStateList paramColorStateList)
  {
    android.widget.TextView localTextView = a;
    if (localTextView != null) {
      localTextView.setTextColor(paramColorStateList);
    }
  }
  
  void a(android.widget.TextView paramTextView, int paramInt)
  {
    if ((f == null) && (d == null))
    {
      f = new LinearLayout(e);
      f.setOrientation(0);
      g.addView(f, -1, -2);
      d = new FrameLayout(e);
      f.addView(d, -1, new FrameLayout.LayoutParams(-2, -2));
      Space localSpace = new Space(e);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, 0, 1.0F);
      f.addView(localSpace, localLayoutParams);
      if (g.getEditText() != null) {
        a();
      }
    }
    if (f(paramInt))
    {
      d.setVisibility(0);
      d.addView(paramTextView);
      p += 1;
    }
    else
    {
      f.addView(paramTextView, paramInt);
    }
    f.setVisibility(0);
    h += 1;
  }
  
  void a(CharSequence paramCharSequence)
  {
    clear();
    r = paramCharSequence;
    c.setText(paramCharSequence);
    if (q != 2) {
      s = 2;
    }
    a(q, s, a(c, paramCharSequence));
  }
  
  void a(boolean paramBoolean)
  {
    if (j == paramBoolean) {
      return;
    }
    clear();
    if (paramBoolean)
    {
      a = new AppCompatTextView(e);
      a.setId(R.id.textinput_error);
      Typeface localTypeface = z;
      if (localTypeface != null) {
        a.setTypeface(localTypeface);
      }
      a(A);
      a.setVisibility(4);
      ViewCompat.setText(a, 1);
      a(a, 0);
    }
    else
    {
      b();
      b(a, 0);
      a = null;
      g.updateEditTextBackground();
      g.clear();
    }
    j = paramBoolean;
  }
  
  void b()
  {
    l = null;
    clear();
    if (q == 1) {
      if ((b) && (!TextUtils.isEmpty(r))) {
        s = 2;
      } else {
        s = 0;
      }
    }
    a(q, s, a(a, null));
  }
  
  void b(int paramInt)
  {
    x = paramInt;
    android.widget.TextView localTextView = c;
    if (localTextView != null) {
      android.support.v4.widget.TextView.setTextAppearance(localTextView, paramInt);
    }
  }
  
  void b(ColorStateList paramColorStateList)
  {
    android.widget.TextView localTextView = c;
    if (localTextView != null) {
      localTextView.setTextColor(paramColorStateList);
    }
  }
  
  void b(android.widget.TextView paramTextView, int paramInt)
  {
    if (f == null) {
      return;
    }
    if (f(paramInt))
    {
      FrameLayout localFrameLayout = d;
      if (localFrameLayout != null)
      {
        p -= 1;
        a(localFrameLayout, p);
        d.removeView(paramTextView);
        break label63;
      }
    }
    f.removeView(paramTextView);
    label63:
    h -= 1;
    a(f, h);
  }
  
  void b(CharSequence paramCharSequence)
  {
    clear();
    l = paramCharSequence;
    a.setText(paramCharSequence);
    if (q != 1) {
      s = 1;
    }
    a(q, s, a(a, paramCharSequence));
  }
  
  void b(boolean paramBoolean)
  {
    if (b == paramBoolean) {
      return;
    }
    clear();
    if (paramBoolean)
    {
      c = new AppCompatTextView(e);
      c.setId(R.id.textinput_helper_text);
      Typeface localTypeface = z;
      if (localTypeface != null) {
        c.setTypeface(localTypeface);
      }
      c.setVisibility(4);
      ViewCompat.setText(c, 1);
      b(x);
      a(c, 1);
    }
    else
    {
      k();
      b(c, 1);
      c = null;
      g.updateEditTextBackground();
      g.clear();
    }
    b = paramBoolean;
  }
  
  boolean c()
  {
    return c(s);
  }
  
  void clear()
  {
    Animator localAnimator = n;
    if (localAnimator != null) {
      localAnimator.cancel();
    }
  }
  
  int d()
  {
    android.widget.TextView localTextView = a;
    if (localTextView != null) {
      return localTextView.getCurrentTextColor();
    }
    return -1;
  }
  
  void d(Typeface paramTypeface)
  {
    if (paramTypeface != z)
    {
      z = paramTypeface;
      setText(a, paramTypeface);
      setText(c, paramTypeface);
    }
  }
  
  boolean e()
  {
    return j;
  }
  
  boolean f(int paramInt)
  {
    if (paramInt != 0) {
      return paramInt == 1;
    }
    return true;
  }
  
  boolean g()
  {
    return b;
  }
  
  ColorStateList getItem()
  {
    android.widget.TextView localTextView = a;
    if (localTextView != null) {
      return localTextView.getTextColors();
    }
    return null;
  }
  
  int i()
  {
    android.widget.TextView localTextView = c;
    if (localTextView != null) {
      return localTextView.getCurrentTextColor();
    }
    return -1;
  }
  
  void k()
  {
    clear();
    if (q == 2) {
      s = 0;
    }
    a(q, s, a(c, null));
  }
  
  CharSequence m()
  {
    return r;
  }
  
  CharSequence n()
  {
    return l;
  }
}
