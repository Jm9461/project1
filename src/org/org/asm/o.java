package org.org.asm;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

class o
  implements Object
{
  protected a0.a b = new a0.a(paramContext, paramViewGroup, paramView, this);
  
  o(Context paramContext, ViewGroup paramViewGroup, View paramView) {}
  
  static o a(View paramView)
  {
    ViewGroup localViewGroup = findSuitableParent(paramView);
    if (localViewGroup != null)
    {
      int j = localViewGroup.getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = localViewGroup.getChildAt(i);
        if ((localView instanceof a0.a)) {
          return f;
        }
        i += 1;
      }
      return new q(localViewGroup.getContext(), localViewGroup, paramView);
    }
    return null;
  }
  
  static ViewGroup findSuitableParent(View paramView)
  {
    while (paramView != null)
    {
      if ((paramView.getId() == 16908290) && ((paramView instanceof ViewGroup))) {
        return (ViewGroup)paramView;
      }
      if ((paramView.getParent() instanceof ViewGroup)) {
        paramView = (ViewGroup)paramView.getParent();
      }
    }
    return null;
  }
  
  public void a(Drawable paramDrawable)
  {
    b.a(paramDrawable);
  }
  
  public void b(Drawable paramDrawable)
  {
    b.remove(paramDrawable);
  }
}
