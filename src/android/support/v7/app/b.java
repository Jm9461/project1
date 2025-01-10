package android.support.v7.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.d;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import org.org.v4.graphics.drawable.DrawerArrowDrawable;

public class b
  implements DrawerLayout.d
{
  private DrawerArrowDrawable a;
  private final int b;
  private final i d;
  private boolean e = true;
  boolean g = true;
  private boolean i = false;
  private final int l;
  View.OnClickListener mCallback;
  private final DrawerLayout mDrawerLayout;
  
  public b(Activity paramActivity, DrawerLayout paramDrawerLayout, Toolbar paramToolbar, int paramInt1, int paramInt2)
  {
    this(paramActivity, paramToolbar, paramDrawerLayout, null, paramInt1, paramInt2);
  }
  
  b(Activity paramActivity, Toolbar paramToolbar, DrawerLayout paramDrawerLayout, DrawerArrowDrawable paramDrawerArrowDrawable, int paramInt1, int paramInt2)
  {
    if (paramToolbar != null)
    {
      d = new g(paramToolbar);
      paramToolbar.setNavigationOnClickListener(new FilenameDialog.1(this));
    }
    else if ((paramActivity instanceof ActionBarDrawerToggle.DelegateProvider))
    {
      d = ((ActionBarDrawerToggle.DelegateProvider)paramActivity).getDrawerToggleDelegate();
    }
    else
    {
      d = new ClassWriter(paramActivity);
    }
    mDrawerLayout = paramDrawerLayout;
    l = paramInt1;
    b = paramInt2;
    if (paramDrawerArrowDrawable == null) {
      a = new DrawerArrowDrawable(d.b());
    } else {
      a = paramDrawerArrowDrawable;
    }
    d();
  }
  
  private void a(float paramFloat)
  {
    if (paramFloat == 1.0F) {
      a.setVerticalMirror(true);
    } else if (paramFloat == 0.0F) {
      a.setVerticalMirror(false);
    }
    a.setProgress(paramFloat);
  }
  
  void a(int paramInt)
  {
    d.a(paramInt);
  }
  
  void a(Drawable paramDrawable, int paramInt)
  {
    if ((!i) && (!d.c()))
    {
      Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
      i = true;
    }
    d.a(paramDrawable, paramInt);
  }
  
  public void a(View paramView)
  {
    a(1.0F);
    if (g) {
      a(b);
    }
  }
  
  public void a(View paramView, float paramFloat)
  {
    if (e)
    {
      a(Math.min(1.0F, Math.max(0.0F, paramFloat)));
      return;
    }
    a(0.0F);
  }
  
  public void c(View paramView)
  {
    a(0.0F);
    if (g) {
      a(l);
    }
  }
  
  Drawable d()
  {
    return d.a();
  }
  
  public void onCreate()
  {
    if (mDrawerLayout.isDrawerOpen(8388611)) {
      a(1.0F);
    } else {
      a(0.0F);
    }
    if (g)
    {
      DrawerArrowDrawable localDrawerArrowDrawable = a;
      int j;
      if (mDrawerLayout.isDrawerOpen(8388611)) {
        j = b;
      } else {
        j = l;
      }
      a(localDrawerArrowDrawable, j);
    }
  }
  
  public void onDrawerStateChanged(int paramInt) {}
  
  void toggle()
  {
    int j = mDrawerLayout.getDrawerLockMode(8388611);
    if ((mDrawerLayout.isDrawerVisible(8388611)) && (j != 2))
    {
      mDrawerLayout.closeDrawer(8388611);
      return;
    }
    if (j != 1) {
      mDrawerLayout.openDrawer(8388611);
    }
  }
}
