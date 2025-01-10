package android.support.design.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.behavior.HideBottomViewOnScrollBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.b;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.design.widget.CoordinatorLayout.f;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.Artist;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.e;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.org.android.asm.Item;
import org.org.android.core.Detail;

public class BottomAppBar
  extends Toolbar
  implements CoordinatorLayout.b
{
  private final int ABSOLUTE;
  private Animator animation;
  private Animator animator;
  private int circleRadius;
  private final MethodWriter d;
  private final Detail detail;
  private boolean isIgnorable;
  private boolean linearProgress;
  private Animator mAnimator;
  AnimatorListenerAdapter this$0;
  
  private float a(boolean paramBoolean)
  {
    FloatingActionButton localFloatingActionButton = create();
    if (localFloatingActionButton == null) {
      return 0.0F;
    }
    Rect localRect = new Rect();
    localFloatingActionButton.add(localRect);
    float f2 = localRect.height();
    float f1 = f2;
    if (f2 == 0.0F) {
      f1 = localFloatingActionButton.getMeasuredHeight();
    }
    float f3 = localFloatingActionButton.getHeight() - bottom;
    f2 = localFloatingActionButton.getHeight() - localRect.height();
    f1 = -getCradleVerticalOffset() + f1 / 2.0F + f3;
    float f4 = localFloatingActionButton.getPaddingBottom();
    f3 = -getMeasuredHeight();
    if (!paramBoolean) {
      f1 = f2 - f4;
    }
    return f3 + f1;
  }
  
  private int a(int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    int j = 1;
    if (i == 1) {
      i = 1;
    } else {
      i = 0;
    }
    if (paramInt == 1)
    {
      int k = getMeasuredWidth() / 2;
      int m = ABSOLUTE;
      paramInt = j;
      if (i != 0) {
        paramInt = -1;
      }
      return (k - m) * paramInt;
    }
    return 0;
  }
  
  private void a(int paramInt, List paramList)
  {
    if (!linearProgress) {
      return;
    }
    d.invoke();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  private void animateOut(int paramInt, boolean paramBoolean)
  {
    if (!ViewCompat.get(this)) {
      return;
    }
    Object localObject = mAnimator;
    if (localObject != null) {
      ((Animator)localObject).cancel();
    }
    localObject = new ArrayList();
    if (!start())
    {
      paramInt = 0;
      paramBoolean = false;
    }
    show(paramInt, paramBoolean, (List)localObject);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playTogether((Collection)localObject);
    mAnimator = localAnimatorSet;
    mAnimator.addListener(new b());
    mAnimator.start();
  }
  
  private void cancel()
  {
    Animator localAnimator = animator;
    if (localAnimator != null) {
      localAnimator.cancel();
    }
    localAnimator = mAnimator;
    if (localAnimator != null) {
      localAnimator.cancel();
    }
    localAnimator = animation;
    if (localAnimator != null) {
      localAnimator.cancel();
    }
  }
  
  private FloatingActionButton create()
  {
    if (!(getParent() instanceof CoordinatorLayout)) {
      return null;
    }
    Iterator localIterator = ((CoordinatorLayout)getParent()).get(this).iterator();
    while (localIterator.hasNext())
    {
      View localView = (View)localIterator.next();
      if ((localView instanceof FloatingActionButton)) {
        return (FloatingActionButton)localView;
      }
    }
    return null;
  }
  
  private void d()
  {
    d.b(getFabTranslationX());
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  private ActionMenuView getActionMenuView()
  {
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      if ((localView instanceof ActionMenuView)) {
        return (ActionMenuView)localView;
      }
      i += 1;
    }
    return null;
  }
  
  private float getFabTranslationX()
  {
    return a(circleRadius);
  }
  
  private float getFabTranslationY()
  {
    return a(linearProgress);
  }
  
  private boolean isRunning()
  {
    Animator localAnimator = animator;
    if ((localAnimator == null) || (!localAnimator.isRunning()))
    {
      localAnimator = mAnimator;
      if ((localAnimator == null) || (!localAnimator.isRunning())) {
        localAnimator = animation;
      }
    }
    return (localAnimator != null) && (localAnimator.isRunning());
  }
  
  private void onDraw(ActionMenuView paramActionMenuView, int paramInt, boolean paramBoolean)
  {
    int j = 0;
    int i;
    if (ViewCompat.getLayoutDirection(this) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    int k = 0;
    while (k < getChildCount())
    {
      View localView = getChildAt(k);
      int n;
      if (((localView.getLayoutParams() instanceof Toolbar.e)) && ((getLayoutParamsgravity & 0x800007) == 8388611)) {
        n = 1;
      } else {
        n = 0;
      }
      int m = j;
      if (n != 0)
      {
        if (i != 0) {
          m = localView.getLeft();
        } else {
          m = localView.getRight();
        }
        m = Math.max(j, m);
      }
      k += 1;
      j = m;
    }
    if (i != 0) {
      i = paramActionMenuView.getRight();
    } else {
      i = paramActionMenuView.getLeft();
    }
    float f;
    if ((paramInt == 1) && (paramBoolean)) {
      f = j - i;
    } else {
      f = 0.0F;
    }
    paramActionMenuView.setTranslationX(f);
  }
  
  private void onHidden(FloatingActionButton paramFloatingActionButton)
  {
    show(paramFloatingActionButton);
    paramFloatingActionButton.onHidden(this$0);
    paramFloatingActionButton.hide(this$0);
  }
  
  private void show(int paramInt, List paramList)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(create(), "translationX", new float[] { a(paramInt) });
    localObjectAnimator.setDuration(300L);
    paramList.add(localObjectAnimator);
  }
  
  private void show(final int paramInt, final boolean paramBoolean, List paramList)
  {
    Object localObject = getActionMenuView();
    if (localObject == null) {
      return;
    }
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localObject, "alpha", new float[] { 1.06535322E9F });
    if (((!linearProgress) && ((!paramBoolean) || (!start()))) || ((circleRadius != 1) && (paramInt != 1)))
    {
      if (((View)localObject).getAlpha() < 1.0F) {
        paramList.add(localObjectAnimator1);
      }
    }
    else
    {
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localObject, "alpha", new float[] { 0.0F });
      localObjectAnimator2.addListener(new c((ActionMenuView)localObject, paramInt, paramBoolean));
      localObject = new AnimatorSet();
      ((AnimatorSet)localObject).setDuration(150L);
      ((AnimatorSet)localObject).playSequentially(new Animator[] { localObjectAnimator2, localObjectAnimator1 });
      paramList.add(localObject);
    }
  }
  
  private void show(FloatingActionButton paramFloatingActionButton)
  {
    paramFloatingActionButton.show(this$0);
    paramFloatingActionButton.onAttachedToWindow(this$0);
  }
  
  private void start(int paramInt)
  {
    if (circleRadius != paramInt)
    {
      if (!ViewCompat.get(this)) {
        return;
      }
      Object localObject = animation;
      if (localObject != null) {
        ((Animator)localObject).cancel();
      }
      localObject = new ArrayList();
      a(paramInt, (List)localObject);
      show(paramInt, (List)localObject);
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.playTogether((Collection)localObject);
      animation = localAnimatorSet;
      animation.addListener(new a());
      animation.start();
    }
  }
  
  private boolean start()
  {
    FloatingActionButton localFloatingActionButton = create();
    return (localFloatingActionButton != null) && (localFloatingActionButton.show());
  }
  
  public ColorStateList getBackgroundTint()
  {
    detail.getConstructors();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public CoordinatorLayout.c getBehavior()
  {
    return new Behavior();
  }
  
  public float getCradleVerticalOffset()
  {
    d.c();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public int getFabAlignmentMode()
  {
    return circleRadius;
  }
  
  public float getFabCradleMargin()
  {
    d.a();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public float getFabCradleRoundedCornerRadius()
  {
    d.put();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public boolean getHideOnScroll()
  {
    return isIgnorable;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    cancel();
    d();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof d))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (d)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    circleRadius = circleRadius;
    linearProgress = linearProgress;
  }
  
  protected Parcelable onSaveInstanceState()
  {
    d localD = new d(super.onSaveInstanceState());
    circleRadius = circleRadius;
    linearProgress = linearProgress;
    return localD;
  }
  
  public void setBackgroundTint(ColorStateList paramColorStateList)
  {
    DrawableCompat.setTintList(detail, paramColorStateList);
  }
  
  public void setCradleVerticalOffset(float paramFloat)
  {
    if (paramFloat == getCradleVerticalOffset()) {
      return;
    }
    d.a(paramFloat);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setFabAlignmentMode(int paramInt)
  {
    start(paramInt);
    animateOut(paramInt, linearProgress);
    circleRadius = paramInt;
  }
  
  public void setFabCradleMargin(float paramFloat)
  {
    if (paramFloat == getFabCradleMargin()) {
      return;
    }
    d.put(paramFloat);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setFabCradleRoundedCornerRadius(float paramFloat)
  {
    if (paramFloat == getFabCradleRoundedCornerRadius()) {
      return;
    }
    d.c(paramFloat);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  void setFabDiameter(int paramInt)
  {
    d.b();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setHideOnScroll(boolean paramBoolean)
  {
    isIgnorable = paramBoolean;
  }
  
  public void setSubtitle(CharSequence paramCharSequence) {}
  
  public void setTitle(CharSequence paramCharSequence) {}
  
  public static class Behavior
    extends HideBottomViewOnScrollBehavior<BottomAppBar>
  {
    private final Rect left = new Rect();
    
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    private boolean onDependentViewChanged(FloatingActionButton paramFloatingActionButton, BottomAppBar paramBottomAppBar)
    {
      getLayoutParamsanchorGravity = 17;
      BottomAppBar.animateOut(paramBottomAppBar, paramFloatingActionButton);
      return true;
    }
    
    protected void close(BottomAppBar paramBottomAppBar)
    {
      super.a(paramBottomAppBar);
      FloatingActionButton localFloatingActionButton = BottomAppBar.getView(paramBottomAppBar);
      if (localFloatingActionButton != null)
      {
        localFloatingActionButton.clearAnimation();
        localFloatingActionButton.animate().translationY(BottomAppBar.d(paramBottomAppBar)).setInterpolator(Item.a).setDuration(225L);
      }
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, BottomAppBar paramBottomAppBar, int paramInt)
    {
      FloatingActionButton localFloatingActionButton = BottomAppBar.getView(paramBottomAppBar);
      if (localFloatingActionButton != null)
      {
        onDependentViewChanged(localFloatingActionButton, paramBottomAppBar);
        localFloatingActionButton.layout(left);
        paramBottomAppBar.setFabDiameter(left.height());
      }
      if (BottomAppBar.isDownloading(paramBottomAppBar))
      {
        paramCoordinatorLayout.onLayoutChild(paramBottomAppBar, paramInt);
        return super.onLayoutChild(paramCoordinatorLayout, paramBottomAppBar, paramInt);
      }
      BottomAppBar.invoke(paramBottomAppBar);
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, BottomAppBar paramBottomAppBar, View paramView1, View paramView2, int paramInt1, int paramInt2)
    {
      return (paramBottomAppBar.getHideOnScroll()) && (super.onStartNestedScroll(paramCoordinatorLayout, paramBottomAppBar, paramView1, paramView2, paramInt1, paramInt2));
    }
    
    protected void show(BottomAppBar paramBottomAppBar)
    {
      super.b(paramBottomAppBar);
      paramBottomAppBar = BottomAppBar.getView(paramBottomAppBar);
      if (paramBottomAppBar != null)
      {
        paramBottomAppBar.add(left);
        float f = paramBottomAppBar.getMeasuredHeight() - left.height();
        paramBottomAppBar.clearAnimation();
        paramBottomAppBar.animate().translationY(-paramBottomAppBar.getPaddingBottom() + f).setInterpolator(Item.b).setDuration(175L);
      }
    }
  }
  
  class a
    extends AnimatorListenerAdapter
  {
    a() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      BottomAppBar.access$setMCurrentAnimator(BottomAppBar.this, null);
    }
  }
  
  class b
    extends AnimatorListenerAdapter
  {
    b() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      BottomAppBar.access$setMCurrentShowAnim(BottomAppBar.this, null);
    }
  }
  
  class c
    extends AnimatorListenerAdapter
  {
    public boolean mCancelled;
    
    c(ActionMenuView paramActionMenuView, int paramInt, boolean paramBoolean) {}
    
    public void onAnimationCancel(Animator paramAnimator)
    {
      mCancelled = true;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (!mCancelled) {
        BottomAppBar.subtract(BottomAppBar.this, a, paramInt, paramBoolean);
      }
    }
  }
  
  static class d
    extends Artist
  {
    public static final Parcelable.Creator<d> CREATOR = new a();
    int circleRadius;
    boolean linearProgress;
    
    public d(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      circleRadius = paramParcel.readInt();
      boolean bool;
      if (paramParcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      }
      linearProgress = bool;
    }
    
    public d(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<BottomAppBar.d>
    {
      a() {}
      
      public BottomAppBar.d createFromParcel(Parcel paramParcel)
      {
        return new BottomAppBar.d(paramParcel, null);
      }
      
      public BottomAppBar.d createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new BottomAppBar.d(paramParcel, paramClassLoader);
      }
      
      public BottomAppBar.d[] newArray(int paramInt)
      {
        return new BottomAppBar.d[paramInt];
      }
    }
  }
}
