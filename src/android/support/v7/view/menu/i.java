package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.b;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import org.org.v4.content.R.dimen;
import org.org.v4.content.R.layout;

final class i
  extends NavigationMenuPresenter
  implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, l, View.OnKeyListener
{
  private static final int p = R.layout.abc_popup_menu_item_layout;
  final b a;
  private PopupWindow.OnDismissListener b;
  private final Context c;
  private l.a d;
  private boolean e;
  private boolean f;
  private final e.a g;
  private final int h;
  private final f i;
  private final int j;
  private int k = 0;
  private boolean l;
  private int m;
  private final int n;
  private final View.OnAttachStateChangeListener q = new MenuPopupHelper(this);
  private final boolean r;
  final ViewTreeObserver.OnGlobalLayoutListener this$0 = new a(this);
  View u;
  ViewTreeObserver x;
  private View y;
  
  public i(Context paramContext, f paramF, View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    c = paramContext;
    i = paramF;
    r = paramBoolean;
    g = new e.a(paramF, LayoutInflater.from(paramContext), r, p);
    j = paramInt1;
    n = paramInt2;
    Resources localResources = paramContext.getResources();
    h = Math.max(getDisplayMetricswidthPixels / 2, localResources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    y = paramView;
    a = new b(c, null, j, n);
    paramF.a(this, paramContext);
  }
  
  private boolean a()
  {
    if (isShowing()) {
      return true;
    }
    if (!e)
    {
      Object localObject = y;
      if (localObject == null) {
        return false;
      }
      u = ((View)localObject);
      a.setOnDismissListener(this);
      a.setOnItemClickListener(this);
      a.show(true);
      localObject = u;
      int i1;
      if (x == null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      x = ((View)localObject).getViewTreeObserver();
      if (i1 != 0) {
        x.addOnGlobalLayoutListener(this$0);
      }
      ((View)localObject).addOnAttachStateChangeListener(q);
      a.a((View)localObject);
      a.a(k);
      if (!l)
      {
        m = NavigationMenuPresenter.measureContentWidth(g, null, c, h);
        l = true;
      }
      a.setContentWidth(m);
      a.show(2);
      a.setAdapter(e());
      a.show();
      localObject = a.c();
      ((View)localObject).setOnKeyListener(this);
      if ((f) && (i.a() != null))
      {
        FrameLayout localFrameLayout = (FrameLayout)LayoutInflater.from(c).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)localObject, false);
        TextView localTextView = (TextView)localFrameLayout.findViewById(16908310);
        if (localTextView != null) {
          localTextView.setText(i.a());
        }
        localFrameLayout.setEnabled(false);
        ((ListView)localObject).addHeaderView(localFrameLayout, null, false);
      }
      a.setAdapter(g);
      a.show();
      return true;
    }
    return false;
  }
  
  public void a(int paramInt)
  {
    k = paramInt;
  }
  
  public void a(f paramF, boolean paramBoolean)
  {
    if (paramF != i) {
      return;
    }
    dismiss();
    l.a localA = d;
    if (localA != null) {
      localA.onCloseMenu(paramF, paramBoolean);
    }
  }
  
  public void a(l.a paramA)
  {
    d = paramA;
  }
  
  public void a(View paramView)
  {
    y = paramView;
  }
  
  public void a(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    b = paramOnDismissListener;
  }
  
  public void a(boolean paramBoolean)
  {
    g.a(paramBoolean);
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    if (paramSubMenuBuilder.hasVisibleItems())
    {
      Object localObject = new w(c, paramSubMenuBuilder, u, r, j, n);
      ((w)localObject).a(d);
      ((w)localObject).a(NavigationMenuPresenter.onSubMenuSelected(paramSubMenuBuilder));
      ((w)localObject).a(b);
      b = null;
      i.close(false);
      int i2 = a.getHorizontalOffset();
      int i1 = i2;
      int i3 = a.getVerticalOffset();
      if ((Gravity.getAbsoluteGravity(k, ViewCompat.getLayoutDirection(y)) & 0x7) == 5) {
        i1 = i2 + y.getWidth();
      }
      if (((w)localObject).a(i1, i3))
      {
        localObject = d;
        if (localObject != null) {
          ((l.a)localObject).onOpenSubMenu(paramSubMenuBuilder);
        }
        return true;
      }
    }
    return false;
  }
  
  public ListView c()
  {
    return a.c();
  }
  
  public void c(int paramInt)
  {
    a.setHorizontalOffset(paramInt);
  }
  
  public void c(f paramF) {}
  
  public void c(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  public void d(int paramInt)
  {
    a.setVerticalOffset(paramInt);
  }
  
  public void dismiss()
  {
    if (isShowing()) {
      a.dismiss();
    }
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public boolean isShowing()
  {
    return (!e) && (a.isShowing());
  }
  
  public void onDismiss()
  {
    e = true;
    i.close();
    Object localObject = x;
    if (localObject != null)
    {
      if (!((ViewTreeObserver)localObject).isAlive()) {
        x = u.getViewTreeObserver();
      }
      x.removeGlobalOnLayoutListener(this$0);
      x = null;
    }
    u.removeOnAttachStateChangeListener(q);
    localObject = b;
    if (localObject != null) {
      ((PopupWindow.OnDismissListener)localObject).onDismiss();
    }
  }
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 1) && (paramInt == 82))
    {
      dismiss();
      return true;
    }
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {}
  
  public Parcelable onSaveInstanceState()
  {
    return null;
  }
  
  public void show()
  {
    if (a()) {
      return;
    }
    throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    l = false;
    e.a localA = g;
    if (localA != null) {
      localA.notifyDataSetChanged();
    }
  }
}
