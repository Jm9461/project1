package android.arch.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ByteVector
{
  final Map<a.b, c.a> a;
  final Map<c.a, List<a.b>> b;
  
  ByteVector(Map paramMap)
  {
    a = paramMap;
    b = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      c localC = (c)localEntry.getValue();
      Object localObject = (List)b.get(localC);
      paramMap = (Map)localObject;
      if (localObject == null)
      {
        localObject = new ArrayList();
        paramMap = (Map)localObject;
        b.put(localC, localObject);
      }
      localObject = localEntry.getKey();
      ((List)paramMap).add(localObject);
    }
  }
  
  private static void a(List paramList, h paramH, c paramC, Object paramObject)
  {
    if (paramList != null)
    {
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        ((Type)paramList.get(i)).a(paramH, paramC, paramObject);
        i -= 1;
      }
    }
  }
  
  void a(h paramH, c paramC, Object paramObject)
  {
    a((List)b.get(paramC), paramH, paramC, paramObject);
    a((List)b.get(c.ON_ANY), paramH, paramC, paramObject);
  }
}
