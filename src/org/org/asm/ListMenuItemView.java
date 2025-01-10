package org.org.asm;

class ListMenuItemView
  extends NavigationMenuPresenter
{
  f b;
  
  ListMenuItemView(f paramF)
  {
    b = paramF;
  }
  
  public void a(h paramH)
  {
    f localF = b;
    l -= 1;
    if (l == 0)
    {
      c = false;
      localF.a();
    }
    paramH.c(this);
  }
  
  public void c(h paramH)
  {
    paramH = b;
    if (!c)
    {
      paramH.i();
      b.c = true;
    }
  }
}
