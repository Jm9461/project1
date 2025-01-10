package android.arch.lifecycle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Attribute
{
  private final HashMap<String, n> b = new HashMap();
  
  public Attribute() {}
  
  public final void a()
  {
    Iterator localIterator = b.values().iterator();
    while (localIterator.hasNext()) {
      ((List)localIterator.next()).a();
    }
    b.clear();
  }
  
  final void a(String paramString, List paramList)
  {
    paramString = (List)b.put(paramString, paramList);
    if (paramString != null) {
      paramString.a();
    }
  }
  
  final List read(String paramString)
  {
    return (List)b.get(paramString);
  }
}
