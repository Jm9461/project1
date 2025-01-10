package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.view.Item;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ClassWriter
{
  public ClassWriter() {}
  
  protected static void a(List paramList, View paramView)
  {
    int k = paramList.size();
    if (a(paramList, paramView, k)) {
      return;
    }
    paramList.add(paramView);
    int i = k;
    while (i < paramList.size())
    {
      paramView = (View)paramList.get(i);
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int m = paramView.getChildCount();
        int j = 0;
        while (j < m)
        {
          View localView = paramView.getChildAt(j);
          if (!a(paramList, localView, k)) {
            paramList.add(localView);
          }
          j += 1;
        }
      }
      i += 1;
    }
  }
  
  protected static boolean a(List paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  private static boolean a(List paramList, View paramView, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      if (paramList.get(i) == paramView) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  static String b(Map paramMap, String paramString)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (paramString.equals(localEntry.getValue())) {
        return (String)localEntry.getKey();
      }
    }
    return null;
  }
  
  public abstract Object a(Object paramObject);
  
  public abstract Object a(Object paramObject1, Object paramObject2, Object paramObject3);
  
  ArrayList a(ArrayList paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      View localView = (View)paramArrayList.get(i);
      localArrayList.add(ViewCompat.a(localView));
      ViewCompat.a(localView, null);
      i += 1;
    }
    return localArrayList;
  }
  
  void a(View paramView, ArrayList paramArrayList1, ArrayList paramArrayList2, ArrayList paramArrayList3, Map paramMap)
  {
    int k = paramArrayList2.size();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < k)
    {
      Object localObject = (View)paramArrayList1.get(i);
      String str = ViewCompat.a((View)localObject);
      localArrayList.add(str);
      if (str != null)
      {
        ViewCompat.a((View)localObject, null);
        localObject = (String)paramMap.get(str);
        int j = 0;
        while (j < k)
        {
          if (((String)localObject).equals(paramArrayList3.get(j)))
          {
            ViewCompat.a((View)paramArrayList2.get(j), str);
            break;
          }
          j += 1;
        }
      }
      i += 1;
    }
    RecyclerView.Adapter.a(paramView, new Widget(this, k, paramArrayList2, paramArrayList3, paramArrayList1, localArrayList));
  }
  
  void a(View paramView, ArrayList paramArrayList, Map paramMap)
  {
    RecyclerView.Adapter.a(paramView, new Plot.a(this, paramArrayList, paramMap));
  }
  
  public abstract void a(ViewGroup paramViewGroup, Object paramObject);
  
  void a(ViewGroup paramViewGroup, ArrayList paramArrayList, Map paramMap)
  {
    RecyclerView.Adapter.a(paramViewGroup, new DayFragment.1(this, paramArrayList, paramMap));
  }
  
  public abstract void a(Object paramObject, Rect paramRect);
  
  public abstract void a(Object paramObject, View paramView);
  
  public abstract void a(Object paramObject, View paramView, ArrayList paramArrayList);
  
  public abstract void a(Object paramObject1, Object paramObject2, ArrayList paramArrayList1, Object paramObject3, ArrayList paramArrayList2, Object paramObject4, ArrayList paramArrayList3);
  
  public abstract void a(Object paramObject, ArrayList paramArrayList);
  
  public abstract void a(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2);
  
  void a(ArrayList paramArrayList, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        if (Item.a(paramView))
        {
          paramArrayList.add(paramView);
          return;
        }
        int j = paramView.getChildCount();
        int i = 0;
        while (i < j)
        {
          a(paramArrayList, paramView.getChildAt(i));
          i += 1;
        }
        return;
      }
      paramArrayList.add(paramView);
    }
  }
  
  void a(Map paramMap, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      String str = ViewCompat.a(paramView);
      if (str != null) {
        paramMap.put(str, paramView);
      }
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int j = paramView.getChildCount();
        int i = 0;
        while (i < j)
        {
          a(paramMap, paramView.getChildAt(i));
          i += 1;
        }
      }
    }
  }
  
  public abstract Object b(Object paramObject);
  
  public abstract Object b(Object paramObject1, Object paramObject2, Object paramObject3);
  
  public abstract void b(Object paramObject, View paramView);
  
  public abstract void b(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2);
  
  public abstract void c(Object paramObject, View paramView);
  
  public abstract void c(Object paramObject, View paramView, ArrayList paramArrayList);
  
  public abstract boolean get(Object paramObject);
  
  protected void setText(View paramView, Rect paramRect)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    paramRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
  }
}
