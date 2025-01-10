package org.org.asm;

import android.graphics.Rect;
import android.support.v4.app.ClassWriter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class i
  extends ClassWriter
{
  public i() {}
  
  private static boolean a(h paramH)
  {
    return (!ClassWriter.a(paramH.l())) || (!ClassWriter.a(paramH.d())) || (!ClassWriter.a(paramH.getTitle()));
  }
  
  public Object a(Object paramObject)
  {
    if (paramObject != null) {
      return ((h)paramObject).clone();
    }
    return null;
  }
  
  public Object a(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    f localF = null;
    paramObject1 = (h)paramObject1;
    paramObject2 = (h)paramObject2;
    paramObject3 = (h)paramObject3;
    if ((paramObject1 != null) && (paramObject2 != null))
    {
      localF = new f();
      localF.a(paramObject1);
      localF.a(paramObject2);
      paramObject1 = localF.b(1);
    }
    else if (paramObject1 == null)
    {
      paramObject1 = localF;
      if (paramObject2 != null) {
        paramObject1 = paramObject2;
      }
    }
    if (paramObject3 != null)
    {
      paramObject2 = new f();
      if (paramObject1 != null) {
        paramObject2.a(paramObject1);
      }
      paramObject2.a(paramObject3);
      return paramObject2;
    }
    return paramObject1;
  }
  
  public void a(ViewGroup paramViewGroup, Object paramObject)
  {
    Handler.initialize(paramViewGroup, (h)paramObject);
  }
  
  public void a(Object paramObject, Rect paramRect)
  {
    if (paramObject != null) {
      ((h)paramObject).a(new MethodVisitor(this, paramRect));
    }
  }
  
  public void a(Object paramObject, View paramView)
  {
    if (paramObject != null) {
      ((h)paramObject).c(paramView);
    }
  }
  
  public void a(Object paramObject, View paramView, ArrayList paramArrayList)
  {
    paramObject = (f)paramObject;
    List localList = paramObject.getSubMenu();
    localList.clear();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      ClassWriter.a(localList, (View)paramArrayList.get(i));
      i += 1;
    }
    localList.add(paramView);
    paramArrayList.add(paramView);
    a(paramObject, paramArrayList);
  }
  
  public void a(Object paramObject1, Object paramObject2, ArrayList paramArrayList1, Object paramObject3, ArrayList paramArrayList2, Object paramObject4, ArrayList paramArrayList3)
  {
    ((h)paramObject1).a(new d(this, paramObject2, paramArrayList1, paramObject3, paramArrayList2, paramObject4, paramArrayList3));
  }
  
  public void a(Object paramObject, ArrayList paramArrayList)
  {
    paramObject = (h)paramObject;
    if (paramObject == null) {
      return;
    }
    int j;
    int i;
    if ((paramObject instanceof f))
    {
      paramObject = (f)paramObject;
      j = paramObject.size();
      i = 0;
      while (i < j)
      {
        a(paramObject.a(i), paramArrayList);
        i += 1;
      }
      return;
    }
    if ((!a(paramObject)) && (ClassWriter.a(paramObject.getSubMenu())))
    {
      j = paramArrayList.size();
      i = 0;
      while (i < j)
      {
        paramObject.e((View)paramArrayList.get(i));
        i += 1;
      }
    }
  }
  
  public void a(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    paramObject = (h)paramObject;
    int j;
    int i;
    if ((paramObject instanceof f))
    {
      paramObject = (f)paramObject;
      j = paramObject.size();
      i = 0;
      while (i < j)
      {
        a(paramObject.a(i), paramArrayList1, paramArrayList2);
        i += 1;
      }
      return;
    }
    if (!a(paramObject))
    {
      List localList = paramObject.getSubMenu();
      if ((localList.size() == paramArrayList1.size()) && (localList.containsAll(paramArrayList1)))
      {
        if (paramArrayList2 == null) {
          i = 0;
        } else {
          i = paramArrayList2.size();
        }
        j = 0;
        while (j < i)
        {
          paramObject.e((View)paramArrayList2.get(j));
          j += 1;
        }
        i = paramArrayList1.size() - 1;
        while (i >= 0)
        {
          paramObject.c((View)paramArrayList1.get(i));
          i -= 1;
        }
      }
    }
  }
  
  public Object b(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    f localF = new f();
    localF.a((h)paramObject);
    return localF;
  }
  
  public Object b(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    f localF = new f();
    if (paramObject1 != null) {
      localF.a((h)paramObject1);
    }
    if (paramObject2 != null) {
      localF.a((h)paramObject2);
    }
    if (paramObject3 != null) {
      localF.a((h)paramObject3);
    }
    return localF;
  }
  
  public void b(Object paramObject, View paramView)
  {
    if (paramObject != null) {
      ((h)paramObject).e(paramView);
    }
  }
  
  public void b(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    paramObject = (f)paramObject;
    if (paramObject != null)
    {
      paramObject.getSubMenu().clear();
      paramObject.getSubMenu().addAll(paramArrayList2);
      a(paramObject, paramArrayList1, paramArrayList2);
    }
  }
  
  public void c(Object paramObject, View paramView)
  {
    if (paramView != null)
    {
      paramObject = (h)paramObject;
      Rect localRect = new Rect();
      setText(paramView, localRect);
      paramObject.a(new ShareActionProvider(this, localRect));
    }
  }
  
  public void c(Object paramObject, View paramView, ArrayList paramArrayList)
  {
    ((h)paramObject).a(new FixedTableModel.a(this, paramView, paramArrayList));
  }
  
  public boolean get(Object paramObject)
  {
    return paramObject instanceof h;
  }
}
