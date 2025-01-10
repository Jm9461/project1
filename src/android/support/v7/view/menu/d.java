package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.b;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.org.v4.content.R.dimen;
import org.org.v4.content.R.layout;

final class d
  extends NavigationMenuPresenter
  implements l, View.OnKeyListener, PopupWindow.OnDismissListener
{
  private static final int b = R.layout.abc_cascading_menu_item_layout;
  private boolean E;
  private boolean H;
  private PopupWindow.OnDismissListener J;
  private int L;
  final List<e.d> a = new ArrayList();
  private View c;
  private l.a d;
  boolean e;
  final Handler f;
  private int g = 0;
  private boolean h;
  private boolean i;
  private final int j;
  private final int k;
  private int l = 0;
  private final Context m;
  ViewTreeObserver mTreeObserver;
  private final int n;
  private final android.support.v7.widget.Object o = new p(this);
  private final boolean p;
  private final List<h> q = new ArrayList();
  private int t;
  private final View.OnAttachStateChangeListener v = new PopupMenuHelper(this);
  final ViewTreeObserver.OnGlobalLayoutListener w = new v(this);
  private int x;
  View y;
  
  public d(Context paramContext, View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    m = paramContext;
    c = paramView;
    j = paramInt1;
    k = paramInt2;
    p = paramBoolean;
    h = false;
    x = b();
    paramContext = paramContext.getResources();
    n = Math.max(getDisplayMetricswidthPixels / 2, paramContext.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    f = new Handler();
  }
  
  private b a()
  {
    b localB = new b(m, null, j, k);
    localB.a(o);
    localB.setOnItemClickListener(this);
    localB.setOnDismissListener(this);
    localB.a(c);
    localB.a(g);
    localB.show(true);
    localB.show(2);
    return localB;
  }
  
  private MenuItem a(f paramF1, f paramF2)
  {
    int i1 = 0;
    int i2 = paramF1.size();
    while (i1 < i2)
    {
      MenuItem localMenuItem = paramF1.getItem(i1);
      if ((localMenuItem.hasSubMenu()) && (paramF2 == localMenuItem.getSubMenu())) {
        return localMenuItem;
      }
      i1 += 1;
    }
    return null;
  }
  
  private View a(h paramH, f paramF)
  {
    paramF = a(c, paramF);
    if (paramF == null) {
      return null;
    }
    ListView localListView = paramH.a();
    paramH = localListView.getAdapter();
    int i2;
    if ((paramH instanceof HeaderViewListAdapter))
    {
      paramH = (HeaderViewListAdapter)paramH;
      i2 = paramH.getHeadersCount();
      paramH = (e.a)paramH.getWrappedAdapter();
    }
    else
    {
      i2 = 0;
      paramH = (e.a)paramH;
    }
    int i4 = -1;
    int i1 = 0;
    int i5 = paramH.getCount();
    int i3;
    for (;;)
    {
      i3 = i4;
      if (i1 >= i5) {
        break;
      }
      if (paramF == paramH.getItem(i1))
      {
        i3 = i1;
        break;
      }
      i1 += 1;
    }
    if (i3 == -1) {
      return null;
    }
    i1 = i3 + i2 - localListView.getFirstVisiblePosition();
    if (i1 >= 0)
    {
      if (i1 >= localListView.getChildCount()) {
        return null;
      }
      return localListView.getChildAt(i1);
    }
    return null;
  }
  
  private void a(f paramF)
  {
    Object localObject3 = LayoutInflater.from(m);
    Object localObject1 = new e.a(paramF, (LayoutInflater)localObject3, p, b);
    if ((!isShowing()) && (h)) {
      ((e.a)localObject1).a(true);
    } else if (isShowing()) {
      ((e.a)localObject1).a(NavigationMenuPresenter.onSubMenuSelected(paramF));
    }
    int i4 = NavigationMenuPresenter.measureContentWidth((ListAdapter)localObject1, null, m, n);
    b localB = a();
    localB.setAdapter((ListAdapter)localObject1);
    localB.setContentWidth(i4);
    localB.a(g);
    if (a.size() > 0)
    {
      localObject1 = a;
      localObject1 = (h)((List)localObject1).get(((List)localObject1).size() - 1);
      localObject2 = a((h)localObject1, paramF);
    }
    else
    {
      localObject1 = null;
      localObject2 = null;
    }
    if (localObject2 != null)
    {
      localB.a(false);
      localB.init(null);
      int i2 = b(i4);
      int i1;
      if (i2 == 1) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      x = i2;
      int i3;
      if (Build.VERSION.SDK_INT >= 26)
      {
        localB.a((View)localObject2);
        i3 = 0;
        i2 = 0;
      }
      else
      {
        int[] arrayOfInt1 = new int[2];
        c.getLocationOnScreen(arrayOfInt1);
        int[] arrayOfInt2 = new int[2];
        ((View)localObject2).getLocationOnScreen(arrayOfInt2);
        if ((g & 0x7) == 5)
        {
          arrayOfInt1[0] += c.getWidth();
          arrayOfInt2[0] += ((View)localObject2).getWidth();
        }
        i3 = arrayOfInt2[0] - arrayOfInt1[0];
        i2 = arrayOfInt2[1] - arrayOfInt1[1];
      }
      if ((g & 0x5) == 5)
      {
        if (i1 != 0) {
          i1 = i3 + i4;
        } else {
          i1 = i3 - ((View)localObject2).getWidth();
        }
      }
      else if (i1 != 0) {
        i1 = ((View)localObject2).getWidth() + i3;
      } else {
        i1 = i3 - i4;
      }
      localB.setHorizontalOffset(i1);
      localB.b(true);
      localB.setVerticalOffset(i2);
    }
    else
    {
      if (H) {
        localB.setHorizontalOffset(L);
      }
      if (i) {
        localB.setVerticalOffset(t);
      }
      localB.setAdapter(e());
    }
    Object localObject2 = new h(localB, paramF, x);
    a.add(localObject2);
    localB.show();
    localObject2 = localB.c();
    ((View)localObject2).setOnKeyListener(this);
    if ((localObject1 == null) && (E) && (paramF.a() != null))
    {
      localObject1 = (FrameLayout)((LayoutInflater)localObject3).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)localObject2, false);
      localObject3 = (TextView)((View)localObject1).findViewById(16908310);
      ((View)localObject1).setEnabled(false);
      ((TextView)localObject3).setText(paramF.a());
      ((ListView)localObject2).addHeaderView((View)localObject1, null, false);
      localB.show();
    }
  }
  
  private int b()
  {
    if (ViewCompat.getLayoutDirection(c) == 1) {
      return 0;
    }
    return 1;
  }
  
  private int b(int paramInt)
  {
    Object localObject = a;
    localObject = ((h)((List)localObject).get(((List)localObject).size() - 1)).a();
    int[] arrayOfInt = new int[2];
    ((View)localObject).getLocationOnScreen(arrayOfInt);
    Rect localRect = new Rect();
    y.getWindowVisibleDisplayFrame(localRect);
    if (x == 1)
    {
      if (arrayOfInt[0] + ((View)localObject).getWidth() + paramInt > right) {
        return 0;
      }
      return 1;
    }
    if (arrayOfInt[0] - paramInt < 0) {
      return 1;
    }
    return 0;
  }
  
  private int b(f paramF)
  {
    int i1 = 0;
    int i2 = a.size();
    while (i1 < i2)
    {
      if (paramF == a.get(i1)).c) {
        return i1;
      }
      i1 += 1;
    }
    return -1;
  }
  
  public void a(int paramInt)
  {
    if (l != paramInt)
    {
      l = paramInt;
      g = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(c));
    }
  }
  
  public void a(f paramF, boolean paramBoolean)
  {
    int i1 = b(paramF);
    if (i1 < 0) {
      return;
    }
    int i2 = i1 + 1;
    if (i2 < a.size()) {
      a.get(i2)).c.close(false);
    }
    Object localObject = (h)a.remove(i1);
    c.b(this);
    if (e)
    {
      this$0.show(null);
      this$0.dismiss(0);
    }
    this$0.dismiss();
    i1 = a.size();
    if (i1 > 0) {
      x = a.get(i1 - 1)).z;
    } else {
      x = b();
    }
    if (i1 == 0)
    {
      dismiss();
      localObject = d;
      if (localObject != null) {
        ((l.a)localObject).onCloseMenu(paramF, true);
      }
      paramF = mTreeObserver;
      if (paramF != null)
      {
        if (paramF.isAlive()) {
          mTreeObserver.removeGlobalOnLayoutListener(w);
        }
        mTreeObserver = null;
      }
      y.removeOnAttachStateChangeListener(v);
      J.onDismiss();
      return;
    }
    if (paramBoolean) {
      a.get(0)).c.close(false);
    }
  }
  
  public void a(l.a paramA)
  {
    d = paramA;
  }
  
  public void a(View paramView)
  {
    if (c != paramView)
    {
      c = paramView;
      g = GravityCompat.getAbsoluteGravity(l, ViewCompat.getLayoutDirection(c));
    }
  }
  
  public void a(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    J = paramOnDismissListener;
  }
  
  public void a(boolean paramBoolean)
  {
    h = paramBoolean;
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    Object localObject = a.iterator();
    while (((Iterator)localObject).hasNext())
    {
      h localH = (h)((Iterator)localObject).next();
      if (paramSubMenuBuilder == c)
      {
        localH.a().requestFocus();
        return true;
      }
    }
    if (paramSubMenuBuilder.hasVisibleItems())
    {
      c(paramSubMenuBuilder);
      localObject = d;
      if (localObject != null)
      {
        ((l.a)localObject).onOpenSubMenu(paramSubMenuBuilder);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public ListView c()
  {
    if (a.isEmpty()) {
      return null;
    }
    List localList = a;
    return ((h)localList.get(localList.size() - 1)).a();
  }
  
  public void c(int paramInt)
  {
    H = true;
    L = paramInt;
  }
  
  public void c(f paramF)
  {
    paramF.a(this, m);
    if (isShowing())
    {
      a(paramF);
      return;
    }
    q.add(paramF);
  }
  
  public void c(boolean paramBoolean)
  {
    E = paramBoolean;
  }
  
  public void d(int paramInt)
  {
    i = true;
    t = paramInt;
  }
  
  public void dismiss()
  {
    int i1 = a.size();
    if (i1 > 0)
    {
      h[] arrayOfH = (h[])a.toArray(new h[i1]);
      i1 -= 1;
      while (i1 >= 0)
      {
        h localH = arrayOfH[i1];
        if (this$0.isShowing()) {
          this$0.dismiss();
        }
        i1 -= 1;
      }
    }
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  protected boolean g()
  {
    return false;
  }
  
  public boolean isShowing()
  {
    return (a.size() > 0) && (a.get(0)).this$0.isShowing());
  }
  
  public void onDismiss()
  {
    Object localObject2 = null;
    int i1 = 0;
    int i2 = a.size();
    Object localObject1;
    for (;;)
    {
      localObject1 = localObject2;
      if (i1 >= i2) {
        break;
      }
      localObject1 = (h)a.get(i1);
      if (!this$0.isShowing()) {
        break;
      }
      i1 += 1;
    }
    if (localObject1 != null) {
      c.close(false);
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
    if (isShowing()) {
      return;
    }
    Iterator localIterator = q.iterator();
    while (localIterator.hasNext()) {
      a((f)localIterator.next());
    }
    q.clear();
    y = c;
    if (y != null)
    {
      int i1;
      if (mTreeObserver == null) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      mTreeObserver = y.getViewTreeObserver();
      if (i1 != 0) {
        mTreeObserver.addOnGlobalLayoutListener(w);
      }
      y.addOnAttachStateChangeListener(v);
    }
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      NavigationMenuPresenter.a(((h)localIterator.next()).a().getAdapter()).notifyDataSetChanged();
    }
  }
}
