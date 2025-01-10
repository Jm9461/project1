package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

class ClassWriter
  implements i
{
  private final Activity a;
  private ActionBarDrawerToggleHoneycomb.SetIndicatorInfo c;
  
  ClassWriter(Activity paramActivity)
  {
    a = paramActivity;
  }
  
  public Drawable a()
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      TypedArray localTypedArray = b().obtainStyledAttributes(null, new int[] { 16843531 }, 16843470, 0);
      Drawable localDrawable = localTypedArray.getDrawable(0);
      localTypedArray.recycle();
      return localDrawable;
    }
    return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(a);
  }
  
  public void a(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      ActionBar localActionBar = a.getActionBar();
      if (localActionBar != null) {
        localActionBar.setHomeActionContentDescription(paramInt);
      }
      return;
    }
    c = ActionBarDrawerToggleHoneycomb.setActionBarDescription(c, a, paramInt);
  }
  
  public void a(Drawable paramDrawable, int paramInt)
  {
    ActionBar localActionBar = a.getActionBar();
    if (localActionBar != null)
    {
      if (Build.VERSION.SDK_INT >= 18)
      {
        localActionBar.setHomeAsUpIndicator(paramDrawable);
        localActionBar.setHomeActionContentDescription(paramInt);
        return;
      }
      localActionBar.setDisplayShowHomeEnabled(true);
      c = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(c, a, paramDrawable, paramInt);
      localActionBar.setDisplayShowHomeEnabled(false);
    }
  }
  
  public Context b()
  {
    ActionBar localActionBar = a.getActionBar();
    if (localActionBar != null) {
      return localActionBar.getThemedContext();
    }
    return a;
  }
  
  public boolean c()
  {
    ActionBar localActionBar = a.getActionBar();
    return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
  }
}
