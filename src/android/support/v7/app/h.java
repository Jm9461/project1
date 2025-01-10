package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import org.org.v4.content.R.attr;

class h
  implements i
{
  h(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7) {}
  
  public Drawable a()
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(b(), null, new int[] { R.attr.homeAsUpIndicator });
    Drawable localDrawable = localTintTypedArray.getDrawable(0);
    localTintTypedArray.recycle();
    return localDrawable;
  }
  
  public void a(int paramInt)
  {
    ActionBar localActionBar = a.getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
  
  public void a(Drawable paramDrawable, int paramInt)
  {
    ActionBar localActionBar = a.getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setHomeAsUpIndicator(paramDrawable);
      localActionBar.setHomeActionContentDescription(paramInt);
    }
  }
  
  public Context b()
  {
    return a.getActionBarThemedContext();
  }
  
  public boolean c()
  {
    ActionBar localActionBar = a.getSupportActionBar();
    return (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0);
  }
}
