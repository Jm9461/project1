package android.support.v4.widget;

import a.b.g.g.j;
import a.b.g.g.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.org.jaxen.util.Pools.Pool;
import org.org.jaxen.util.Pools.SimplePool;

public final class f<T>
{
  private final j<ArrayList<T>> a = new Pools.SimplePool(10);
  private final n<T, ArrayList<T>> b = new org.org.jaxen.util.f();
  private final ArrayList<T> n = new ArrayList();
  private final HashSet<T> r = new HashSet();
  
  public f() {}
  
  private void a(ArrayList paramArrayList)
  {
    paramArrayList.clear();
    a.release(paramArrayList);
  }
  
  private void add(Object paramObject, ArrayList paramArrayList, HashSet paramHashSet)
  {
    if (paramArrayList.contains(paramObject)) {
      return;
    }
    if (!paramHashSet.contains(paramObject))
    {
      paramHashSet.add(paramObject);
      ArrayList localArrayList = (ArrayList)b.get(paramObject);
      if (localArrayList != null)
      {
        int i = 0;
        int j = localArrayList.size();
        while (i < j)
        {
          add(localArrayList.get(i), paramArrayList, paramHashSet);
          i += 1;
        }
      }
      paramHashSet.remove(paramObject);
      paramArrayList.add(paramObject);
      return;
    }
    paramObject = new RuntimeException("This graph contains cyclic dependencies");
    throw paramObject;
  }
  
  private ArrayList size()
  {
    ArrayList localArrayList2 = (ArrayList)a.acquire();
    ArrayList localArrayList1 = localArrayList2;
    if (localArrayList2 == null) {
      localArrayList1 = new ArrayList();
    }
    return localArrayList1;
  }
  
  public ArrayList a()
  {
    n.clear();
    r.clear();
    int i = 0;
    int j = b.size();
    while (i < j)
    {
      add(b.getValue(i), n, r);
      i += 1;
    }
    return n;
  }
  
  public List a(Object paramObject)
  {
    Object localObject1 = null;
    int i = 0;
    int j = b.size();
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)b.get(i);
      Object localObject2 = localObject1;
      if (localArrayList != null)
      {
        localObject2 = localObject1;
        if (localArrayList.contains(paramObject))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArrayList();
          }
          ((ArrayList)localObject2).add(b.getValue(i));
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public void add(Object paramObject1, Object paramObject2)
  {
    if ((b.containsKey(paramObject1)) && (b.containsKey(paramObject2)))
    {
      ArrayList localArrayList2 = (ArrayList)b.get(paramObject1);
      ArrayList localArrayList1 = localArrayList2;
      if (localArrayList2 == null)
      {
        localArrayList2 = size();
        localArrayList1 = localArrayList2;
        b.put(paramObject1, localArrayList2);
      }
      localArrayList1.add(paramObject2);
      return;
    }
    throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
  }
  
  public boolean add(Object paramObject)
  {
    return b.containsKey(paramObject);
  }
  
  public void b()
  {
    int i = 0;
    int j = b.size();
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)b.get(i);
      if (localArrayList != null) {
        a(localArrayList);
      }
      i += 1;
    }
    b.clear();
  }
  
  public boolean b(Object paramObject)
  {
    int i = 0;
    int j = b.size();
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)b.get(i);
      if ((localArrayList != null) && (localArrayList.contains(paramObject))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public List get(Object paramObject)
  {
    return (List)b.get(paramObject);
  }
  
  public void setEnabled(Object paramObject)
  {
    if (!b.containsKey(paramObject)) {
      b.put(paramObject, null);
    }
  }
}
