package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.PopupWindow.OnDismissListener;

public class w
  implements y
{
  private boolean B;
  private View a;
  private final int b;
  private final Context c;
  private final f d;
  private l.a e;
  private final boolean f;
  private final PopupWindow.OnDismissListener l = new ActivityChooserView.Callbacks(this);
  private int m = 8388611;
  private PopupWindow.OnDismissListener mOnDismissListener;
  private NavigationMenuPresenter mPopup;
  private final int p;
  
  public w(Context paramContext, f paramF, View paramView, boolean paramBoolean, int paramInt)
  {
    this(paramContext, paramF, paramView, paramBoolean, paramInt, 0);
  }
  
  public w(Context paramContext, f paramF, View paramView, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    c = paramContext;
    d = paramF;
    a = paramView;
    f = paramBoolean;
    b = paramInt1;
    p = paramInt2;
  }
  
  private void a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    NavigationMenuPresenter localNavigationMenuPresenter = getPopup();
    localNavigationMenuPresenter.c(paramBoolean2);
    if (paramBoolean1)
    {
      int i = paramInt1;
      if ((GravityCompat.getAbsoluteGravity(m, ViewCompat.getLayoutDirection(a)) & 0x7) == 5) {
        i = paramInt1 - a.getWidth();
      }
      localNavigationMenuPresenter.c(i);
      localNavigationMenuPresenter.d(paramInt2);
      paramInt1 = (int)(48.0F * c.getResources().getDisplayMetrics().density / 2.0F);
      localNavigationMenuPresenter.d(new Rect(i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1));
    }
    localNavigationMenuPresenter.show();
  }
  
  private NavigationMenuPresenter getView()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a12 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  public void a(int paramInt)
  {
    m = paramInt;
  }
  
  public void a(l.a paramA)
  {
    e = paramA;
    NavigationMenuPresenter localNavigationMenuPresenter = mPopup;
    if (localNavigationMenuPresenter != null) {
      localNavigationMenuPresenter.a(paramA);
    }
  }
  
  public void a(View paramView)
  {
    a = paramView;
  }
  
  public void a(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    mOnDismissListener = paramOnDismissListener;
  }
  
  public void a(boolean paramBoolean)
  {
    B = paramBoolean;
    NavigationMenuPresenter localNavigationMenuPresenter = mPopup;
    if (localNavigationMenuPresenter != null) {
      localNavigationMenuPresenter.a(paramBoolean);
    }
  }
  
  public boolean a()
  {
    if (isShowing()) {
      return true;
    }
    if (a == null) {
      return false;
    }
    a(0, 0, false, false);
    return true;
  }
  
  public boolean a(int paramInt1, int paramInt2)
  {
    if (isShowing()) {
      return true;
    }
    if (a == null) {
      return false;
    }
    a(paramInt1, paramInt2, true, true);
    return true;
  }
  
  public void dismiss()
  {
    if (isShowing()) {
      mPopup.dismiss();
    }
  }
  
  public NavigationMenuPresenter getPopup()
  {
    if (mPopup == null) {
      mPopup = getView();
    }
    return mPopup;
  }
  
  public boolean isShowing()
  {
    NavigationMenuPresenter localNavigationMenuPresenter = mPopup;
    return (localNavigationMenuPresenter != null) && (localNavigationMenuPresenter.isShowing());
  }
  
  protected void onDismiss()
  {
    mPopup = null;
    PopupWindow.OnDismissListener localOnDismissListener = mOnDismissListener;
    if (localOnDismissListener != null) {
      localOnDismissListener.onDismiss();
    }
  }
  
  public void show()
  {
    if (a()) {
      return;
    }
    throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
  }
}
