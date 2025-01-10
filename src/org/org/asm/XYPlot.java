package org.org.asm;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public abstract class XYPlot
  extends h
{
  private static final String[] s = { "android:visibility:visibility", "android:visibility:parent" };
  private int c = 3;
  
  public XYPlot() {}
  
  private i0.c a(Label paramLabel1, Label paramLabel2)
  {
    i0.c localC = new i0.c();
    c = false;
    l = false;
    if ((paramLabel1 != null) && (c.containsKey("android:visibility:visibility")))
    {
      b = ((Integer)c.get("android:visibility:visibility")).intValue();
      a = ((ViewGroup)c.get("android:visibility:parent"));
    }
    else
    {
      b = -1;
      a = null;
    }
    if ((paramLabel2 != null) && (c.containsKey("android:visibility:visibility")))
    {
      i = ((Integer)c.get("android:visibility:visibility")).intValue();
      g = ((ViewGroup)c.get("android:visibility:parent"));
    }
    else
    {
      i = -1;
      g = null;
    }
    if ((paramLabel1 != null) && (paramLabel2 != null))
    {
      if ((b == i) && (a == g)) {
        return localC;
      }
      int i = b;
      int j = i;
      if (i != j)
      {
        if (i == 0)
        {
          l = false;
          c = true;
          return localC;
        }
        if (j == 0)
        {
          l = true;
          c = true;
          return localC;
        }
      }
      else
      {
        if (g == null)
        {
          l = false;
          c = true;
          return localC;
        }
        if (a == null)
        {
          l = true;
          c = true;
          return localC;
        }
      }
    }
    else
    {
      if ((paramLabel1 == null) && (i == 0))
      {
        l = true;
        c = true;
        return localC;
      }
      if ((paramLabel2 == null) && (b == 0))
      {
        l = false;
        c = true;
      }
    }
    return localC;
  }
  
  private void save(Label paramLabel)
  {
    int i = a.getVisibility();
    c.put("android:visibility:visibility", Integer.valueOf(i));
    c.put("android:visibility:parent", a.getParent());
    int[] arrayOfInt = new int[2];
    a.getLocationOnScreen(arrayOfInt);
    c.put("android:visibility:screenLocation", arrayOfInt);
  }
  
  public abstract Animator a(ViewGroup paramViewGroup, View paramView, Label paramLabel1, Label paramLabel2);
  
  public Animator a(ViewGroup paramViewGroup, Label paramLabel1, int paramInt1, Label paramLabel2, int paramInt2)
  {
    if ((c & 0x2) != 2) {
      return null;
    }
    Object localObject2;
    if (paramLabel1 != null) {
      localObject2 = a;
    } else {
      localObject2 = null;
    }
    Object localObject1;
    if (paramLabel2 != null) {
      localObject1 = a;
    } else {
      localObject1 = null;
    }
    Object localObject5 = null;
    Object localObject4 = null;
    Object localObject3;
    if ((localObject1 != null) && (((View)localObject1).getParent() != null))
    {
      if (paramInt2 == 4)
      {
        localObject3 = localObject1;
        localObject1 = localObject5;
      }
      else if (localObject2 == localObject1)
      {
        localObject3 = localObject1;
        localObject1 = localObject5;
      }
      else if (v)
      {
        localObject1 = localObject2;
        localObject3 = localObject4;
      }
      else
      {
        localObject1 = l.a(paramViewGroup, (View)localObject2, (View)((View)localObject2).getParent());
        localObject3 = localObject4;
      }
    }
    else if (localObject1 != null)
    {
      localObject3 = localObject4;
    }
    else
    {
      localObject1 = localObject5;
      localObject3 = localObject4;
      if (localObject2 != null) {
        if (((View)localObject2).getParent() == null)
        {
          localObject1 = localObject2;
          localObject3 = localObject4;
        }
        else
        {
          localObject1 = localObject5;
          localObject3 = localObject4;
          if ((((View)localObject2).getParent() instanceof View))
          {
            View localView = (View)((View)localObject2).getParent();
            if (!acbc)
            {
              localObject1 = l.a(paramViewGroup, (View)localObject2, localView);
              localObject3 = localObject4;
            }
            else
            {
              localObject1 = localObject5;
              localObject3 = localObject4;
              if (localView.getParent() == null)
              {
                paramInt1 = localView.getId();
                localObject1 = localObject5;
                localObject3 = localObject4;
                if (paramInt1 != -1)
                {
                  localObject1 = localObject5;
                  localObject3 = localObject4;
                  if (paramViewGroup.findViewById(paramInt1) != null)
                  {
                    localObject1 = localObject5;
                    localObject3 = localObject4;
                    if (v)
                    {
                      localObject1 = localObject2;
                      localObject3 = localObject4;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    if ((localObject1 != null) && (paramLabel1 != null))
    {
      localObject2 = (int[])c.get("android:visibility:screenLocation");
      paramInt1 = localObject2[0];
      paramInt2 = localObject2[1];
      localObject2 = new int[2];
      paramViewGroup.getLocationOnScreen((int[])localObject2);
      ((View)localObject1).offsetLeftAndRight(paramInt1 - localObject2[0] - ((View)localObject1).getLeft());
      ((View)localObject1).offsetTopAndBottom(paramInt2 - localObject2[1] - ((View)localObject1).getTop());
      localObject2 = n.a(paramViewGroup);
      ((g)localObject2).b((View)localObject1);
      paramViewGroup = a(paramViewGroup, (View)localObject1, paramLabel1, paramLabel2);
      if (paramViewGroup == null)
      {
        ((g)localObject2).c((View)localObject1);
        return paramViewGroup;
      }
      paramViewGroup.addListener(new i0.a(this, (g)localObject2, (View)localObject1));
      return paramViewGroup;
    }
    if (localObject3 != null)
    {
      paramInt1 = localObject3.getVisibility();
      a.set(localObject3, 0);
      paramViewGroup = a(paramViewGroup, localObject3, paramLabel1, paramLabel2);
      if (paramViewGroup != null)
      {
        paramLabel1 = new i0.b(localObject3, paramInt2, true);
        paramViewGroup.addListener(paramLabel1);
        Edge.a(paramViewGroup, paramLabel1);
        a(paramLabel1);
        return paramViewGroup;
      }
      a.set(localObject3, paramInt1);
      return paramViewGroup;
    }
    return null;
  }
  
  public Animator a(ViewGroup paramViewGroup, Label paramLabel1, Label paramLabel2)
  {
    i0.c localC = a(paramLabel1, paramLabel2);
    if ((c) && ((a != null) || (g != null)))
    {
      if (l) {
        return b(paramViewGroup, paramLabel1, b, paramLabel2, i);
      }
      return a(paramViewGroup, paramLabel1, b, paramLabel2, i);
    }
    return null;
  }
  
  public void a(Label paramLabel)
  {
    save(paramLabel);
  }
  
  public abstract Animator b(ViewGroup paramViewGroup, View paramView, Label paramLabel1, Label paramLabel2);
  
  public Animator b(ViewGroup paramViewGroup, Label paramLabel1, int paramInt1, Label paramLabel2, int paramInt2)
  {
    if ((c & 0x1) == 1)
    {
      if (paramLabel2 == null) {
        return null;
      }
      if (paramLabel1 == null)
      {
        View localView = (View)a.getParent();
        if (abcc) {
          return null;
        }
      }
      return b(paramViewGroup, a, paramLabel1, paramLabel2);
    }
    return null;
  }
  
  public boolean b(Label paramLabel1, Label paramLabel2)
  {
    if ((paramLabel1 == null) && (paramLabel2 == null)) {
      return false;
    }
    if ((paramLabel1 != null) && (paramLabel2 != null) && (c.containsKey("android:visibility:visibility") != c.containsKey("android:visibility:visibility"))) {
      return false;
    }
    paramLabel1 = a(paramLabel1, paramLabel2);
    return (c) && ((b == 0) || (i == 0));
  }
  
  public void c(Label paramLabel)
  {
    save(paramLabel);
  }
  
  public void d(int paramInt)
  {
    if ((paramInt & 0xFFFFFFFC) == 0)
    {
      c = paramInt;
      return;
    }
    throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
  }
  
  public String[] getValue()
  {
    return s;
  }
}
