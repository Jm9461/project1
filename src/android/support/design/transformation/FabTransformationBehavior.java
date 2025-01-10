package android.support.design.transformation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.design.widget.ClassReader;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.f;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import org.org.android.R.id;
import org.org.android.asm.Attribute;
import org.org.android.asm.ClassWriter;
import org.org.android.asm.Frame;
import org.org.android.asm.Item;
import org.org.android.asm.Plot.a;
import org.org.android.asm.Type;
import org.org.android.asm.d;
import org.org.android.ui.RefList;
import org.org.android.ui.c;
import org.org.android.ui.g;

public abstract class FabTransformationBehavior
  extends ExpandableTransformationBehavior
{
  private final RectF a = new RectF();
  private final RectF b = new RectF();
  private final int[] o = new int[2];
  private final Rect z = new Rect();
  
  public FabTransformationBehavior() {}
  
  public FabTransformationBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private float a(e paramE, org.org.android.asm.Label paramLabel, float paramFloat1, float paramFloat2)
  {
    long l1 = paramLabel.a();
    long l2 = paramLabel.getValue();
    paramE = b.a("expansion");
    float f = (float)(paramE.a() + paramE.getValue() + 17L - l1) / (float)l2;
    return Item.lerp(paramFloat1, paramFloat2, paramLabel.getName().getInterpolation(f));
  }
  
  private float a(View paramView1, View paramView2, d paramD)
  {
    RectF localRectF1 = b;
    RectF localRectF2 = a;
    a(paramView1, localRectF1);
    a(paramView2, localRectF2);
    localRectF2.offset(0.0F, -c(paramView1, paramView2, paramD));
    return localRectF1.centerY() - top;
  }
  
  private void a(View paramView, long paramLong, int paramInt1, int paramInt2, float paramFloat, java.util.List paramList)
  {
    if ((Build.VERSION.SDK_INT >= 21) && (paramLong > 0L))
    {
      paramView = ViewAnimationUtils.createCircularReveal(paramView, paramInt1, paramInt2, paramFloat, paramFloat);
      paramView.setStartDelay(0L);
      paramView.setDuration(paramLong);
      paramList.add(paramView);
    }
  }
  
  private void a(View paramView, long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, float paramFloat, java.util.List paramList)
  {
    if ((Build.VERSION.SDK_INT >= 21) && (paramLong1 + paramLong2 < paramLong3))
    {
      paramView = ViewAnimationUtils.createCircularReveal(paramView, paramInt1, paramInt2, paramFloat, paramFloat);
      paramView.setStartDelay(paramLong1 + paramLong2);
      paramView.setDuration(paramLong3 - (paramLong1 + paramLong2));
      paramList.add(paramView);
    }
  }
  
  private void a(View paramView, RectF paramRectF)
  {
    paramRectF.set(0.0F, 0.0F, paramView.getWidth(), paramView.getHeight());
    int[] arrayOfInt = o;
    paramView.getLocationInWindow(arrayOfInt);
    paramRectF.offsetTo(arrayOfInt[0], arrayOfInt[1]);
    paramRectF.offset((int)-paramView.getTranslationX(), (int)-paramView.getTranslationY());
  }
  
  private void a(View paramView, e paramE, org.org.android.asm.Label paramLabel1, org.org.android.asm.Label paramLabel2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, RectF paramRectF)
  {
    paramFloat1 = a(paramE, paramLabel1, paramFloat1, paramFloat3);
    paramFloat2 = a(paramE, paramLabel2, paramFloat2, paramFloat4);
    paramLabel1 = z;
    paramView.getWindowVisibleDisplayFrame(paramLabel1);
    paramE = b;
    paramE.set(paramLabel1);
    paramLabel1 = a;
    a(paramView, paramLabel1);
    paramLabel1.offset(paramFloat1, paramFloat2);
    paramLabel1.intersect(paramE);
    paramRectF.set(paramLabel1);
  }
  
  private void a(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, float paramFloat1, float paramFloat2, java.util.List paramList1, java.util.List paramList2)
  {
    if (!(paramView2 instanceof org.org.android.ui.List)) {
      return;
    }
    final org.org.android.ui.List localList = (org.org.android.ui.List)paramView2;
    float f2 = d(paramView1, paramView2, a);
    float f3 = a(paramView1, paramView2, a);
    ((FloatingActionButton)paramView1).add(z);
    float f1 = z.width() / 2.0F;
    org.org.android.asm.Label localLabel = b.a("expansion");
    if (paramBoolean1)
    {
      if (!paramBoolean2) {
        localList.setRevealInfo(new org.org.android.ui.Label(f2, f3, f1));
      }
      if (paramBoolean2) {
        f1 = getRevealInfoh;
      }
      paramView1 = g.a(localList, f2, f3, ClassReader.a(f2, f3, 0.0F, 0.0F, paramFloat1, paramFloat2));
      paramView1.addListener(new d(localList));
      a(paramView2, localLabel.a(), (int)f2, (int)f3, f1, paramList1);
    }
    else
    {
      paramFloat1 = getRevealInfoh;
      paramView1 = g.a(localList, f2, f3, f1);
      a(paramView2, localLabel.a(), (int)f2, (int)f3, paramFloat1, paramList1);
      a(paramView2, localLabel.a(), localLabel.getValue(), b.a(), (int)f2, (int)f3, f1, paramList1);
    }
    localLabel.add(paramView1);
    paramList1.add(paramView1);
    paramList2.add(g.a(localList));
  }
  
  private void a(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, java.util.List paramList1, java.util.List paramList2)
  {
    if (!(paramView2 instanceof ViewGroup)) {
      return;
    }
    if (((paramView2 instanceof org.org.android.ui.List)) && (c.c == 0)) {
      return;
    }
    paramView1 = b(paramView2);
    if (paramView1 == null) {
      return;
    }
    if (paramBoolean1)
    {
      if (!paramBoolean2) {
        Frame.d.set(paramView1, Float.valueOf(0.0F));
      }
      paramView1 = ObjectAnimator.ofFloat(paramView1, Frame.d, new float[] { 1.0F });
    }
    else
    {
      paramView1 = ObjectAnimator.ofFloat(paramView1, Frame.d, new float[] { 0.0F });
    }
    b.a("contentFade").add(paramView1);
    paramList1.add(paramView1);
  }
  
  private void a(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, java.util.List paramList1, java.util.List paramList2, RectF paramRectF)
  {
    float f1 = b(paramView1, paramView2, a);
    float f2 = c(paramView1, paramView2, a);
    if ((f1 != 0.0F) && (f2 != 0.0F))
    {
      if (((paramBoolean1) && (f2 < 0.0F)) || ((!paramBoolean1) && (f2 > 0.0F)))
      {
        paramView1 = b.a("translationXCurveUpwards");
        paramList2 = b.a("translationYCurveUpwards");
      }
      else
      {
        paramView1 = b.a("translationXCurveDownwards");
        paramList2 = b.a("translationYCurveDownwards");
      }
    }
    else
    {
      paramView1 = b.a("translationXLinear");
      paramList2 = b.a("translationYLinear");
    }
    if (paramBoolean1)
    {
      if (!paramBoolean2)
      {
        paramView2.setTranslationX(-f1);
        paramView2.setTranslationY(-f2);
      }
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_X, new float[] { 0.0F });
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Y, new float[] { 0.0F });
      a(paramView2, paramE, paramView1, paramList2, -f1, -f2, 0.0F, 0.0F, paramRectF);
      paramView2 = localObjectAnimator2;
      paramE = localObjectAnimator1;
    }
    else
    {
      paramRectF = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_X, new float[] { -f1 });
      paramE = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Y, new float[] { -f2 });
      paramView2 = paramRectF;
    }
    paramView1.add(paramView2);
    paramList2.add(paramE);
    paramList1.add(paramView2);
    paramList1.add(paramE);
  }
  
  private void add(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, java.util.List paramList1, java.util.List paramList2)
  {
    float f = ViewCompat.getElevation(paramView2) - ViewCompat.getElevation(paramView1);
    if (paramBoolean1)
    {
      if (!paramBoolean2) {
        paramView2.setTranslationZ(-f);
      }
      paramView1 = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Z, new float[] { 0.0F });
    }
    else
    {
      paramView1 = ObjectAnimator.ofFloat(paramView2, View.TRANSLATION_Z, new float[] { -f });
    }
    b.a("elevation").add(paramView1);
    paramList1.add(paramView1);
  }
  
  private float b(View paramView1, View paramView2, d paramD)
  {
    RectF localRectF1 = b;
    RectF localRectF2 = a;
    a(paramView1, localRectF1);
    a(paramView2, localRectF2);
    float f = 0.0F;
    int i = g & 0x7;
    if (i != 1)
    {
      if (i != 3)
      {
        if (i == 5) {
          f = right - right;
        }
      }
      else {
        f = left - left;
      }
    }
    else {
      f = localRectF2.centerX() - localRectF1.centerX();
    }
    return f + l;
  }
  
  private ViewGroup b(View paramView)
  {
    View localView = paramView.findViewById(R.id.mtrl_child_content_container);
    if (localView != null) {
      return createView(localView);
    }
    if ((!(paramView instanceof onSuccess)) && (!(paramView instanceof CACHE))) {
      return createView(paramView);
    }
    return createView(((ViewGroup)paramView).getChildAt(0));
  }
  
  private float c(View paramView1, View paramView2, d paramD)
  {
    RectF localRectF1 = b;
    RectF localRectF2 = a;
    a(paramView1, localRectF1);
    a(paramView2, localRectF2);
    float f = 0.0F;
    int i = g & 0x70;
    if (i != 16)
    {
      if (i != 48)
      {
        if (i == 80) {
          f = bottom - bottom;
        }
      }
      else {
        f = top - top;
      }
    }
    else {
      f = localRectF2.centerY() - localRectF1.centerY();
    }
    return f + b;
  }
  
  private ViewGroup createView(View paramView)
  {
    if ((paramView instanceof ViewGroup)) {
      return (ViewGroup)paramView;
    }
    return null;
  }
  
  private float d(View paramView1, View paramView2, d paramD)
  {
    RectF localRectF1 = b;
    RectF localRectF2 = a;
    a(paramView1, localRectF1);
    a(paramView2, localRectF2);
    localRectF2.offset(-b(paramView1, paramView2, paramD), 0.0F);
    return localRectF1.centerX() - left;
  }
  
  private int init(View paramView)
  {
    ColorStateList localColorStateList = ViewCompat.getBackgroundTintList(paramView);
    if (localColorStateList != null) {
      return localColorStateList.getColorForState(paramView.getDrawableState(), localColorStateList.getDefaultColor());
    }
    return 0;
  }
  
  private void init(View paramView1, final View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, java.util.List paramList1, java.util.List paramList2)
  {
    if ((paramView2 instanceof org.org.android.ui.List))
    {
      if (!(paramView1 instanceof ImageView)) {
        return;
      }
      final org.org.android.ui.List localList = (org.org.android.ui.List)paramView2;
      final Drawable localDrawable = ((ImageView)paramView1).getDrawable();
      if (localDrawable == null) {
        return;
      }
      localDrawable.mutate();
      if (paramBoolean1)
      {
        if (!paramBoolean2) {
          localDrawable.setAlpha(255);
        }
        paramView1 = ObjectAnimator.ofInt(localDrawable, ClassWriter.K, new int[] { 0 });
      }
      else
      {
        paramView1 = ObjectAnimator.ofInt(localDrawable, ClassWriter.K, new int[] { 255 });
      }
      paramView1.addUpdateListener(new b(paramView2));
      b.a("iconFade").add(paramView1);
      paramList1.add(paramView1);
      paramList2.add(new c(localList, localDrawable));
    }
  }
  
  private void write(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2, e paramE, java.util.List paramList1, java.util.List paramList2)
  {
    if (!(paramView2 instanceof org.org.android.ui.List)) {
      return;
    }
    paramView2 = (org.org.android.ui.List)paramView2;
    int i = init(paramView1);
    if (paramBoolean1)
    {
      if (!paramBoolean2) {
        paramView2.setCircularRevealScrimColor(i);
      }
      paramView1 = ObjectAnimator.ofInt(paramView2, RefList.X, new int[] { 0xFFFFFF & i });
    }
    else
    {
      paramView1 = ObjectAnimator.ofInt(paramView2, RefList.X, new int[] { i });
    }
    paramView1.setEvaluator(Type.getType());
    b.a("color").add(paramView1);
    paramList1.add(paramView1);
  }
  
  protected AnimatorSet a(final View paramView1, final View paramView2, final boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = a(paramView2.getContext(), paramBoolean1);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (Build.VERSION.SDK_INT >= 21) {
      add(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, localArrayList1, localArrayList2);
    }
    RectF localRectF = b;
    a(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, localArrayList1, localArrayList2, localRectF);
    float f1 = localRectF.width();
    float f2 = localRectF.height();
    init(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, localArrayList1, localArrayList2);
    a(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, f1, f2, localArrayList1, localArrayList2);
    write(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, localArrayList1, localArrayList2);
    a(paramView1, paramView2, paramBoolean1, paramBoolean2, (e)localObject, localArrayList1, localArrayList2);
    localObject = new AnimatorSet();
    Plot.a.run((AnimatorSet)localObject, localArrayList1);
    ((Animator)localObject).addListener(new a(paramBoolean1, paramView2, paramView1));
    int i = 0;
    int j = localArrayList2.size();
    while (i < j)
    {
      ((Animator)localObject).addListener((Animator.AnimatorListener)localArrayList2.get(i));
      i += 1;
    }
    return localObject;
  }
  
  protected abstract e a(Context paramContext, boolean paramBoolean);
  
  public void a(CoordinatorLayout.f paramF)
  {
    if (x == 0) {
      x = 80;
    }
  }
  
  public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    if (paramView1.getVisibility() != 8)
    {
      if ((paramView2 instanceof FloatingActionButton))
      {
        int i = ((FloatingActionButton)paramView2).getExpandedComponentIdHint();
        if ((i == 0) || (i == paramView1.getId())) {
          return true;
        }
      }
      else
      {
        return false;
      }
    }
    else {
      throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }
    return false;
  }
  
  class a
    extends AnimatorListenerAdapter
  {
    a(boolean paramBoolean, View paramView1, View paramView2) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (!paramBoolean1)
      {
        paramView2.setVisibility(4);
        paramView1.setAlpha(1.0F);
        paramView1.setVisibility(0);
      }
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      if (paramBoolean1)
      {
        paramView2.setVisibility(0);
        paramView1.setAlpha(0.0F);
        paramView1.setVisibility(4);
      }
    }
  }
  
  class b
    implements ValueAnimator.AnimatorUpdateListener
  {
    b(View paramView) {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      paramView2.invalidate();
    }
  }
  
  class c
    extends AnimatorListenerAdapter
  {
    c(org.org.android.ui.List paramList, Drawable paramDrawable) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      localList.setCircularRevealOverlayDrawable(null);
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      localList.setCircularRevealOverlayDrawable(localDrawable);
    }
  }
  
  class d
    extends AnimatorListenerAdapter
  {
    d(org.org.android.ui.List paramList) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      paramAnimator = localList.getRevealInfo();
      h = Float.MAX_VALUE;
      localList.setRevealInfo(paramAnimator);
    }
  }
  
  protected static class e
  {
    public d a;
    public Attribute b;
    
    protected e() {}
  }
}
