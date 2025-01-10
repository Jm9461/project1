package android.databinding;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class MergedDataBinderMapper
  extends symbol
{
  private List<c> entries = new CopyOnWriteArrayList();
  private Set<Class<? extends c>> ids = new HashSet();
  
  public MergedDataBinderMapper()
  {
    new CopyOnWriteArrayList();
  }
  
  public void add(symbol paramSymbol)
  {
    Class localClass = paramSymbol.getClass();
    if (ids.add(localClass))
    {
      entries.add(paramSymbol);
      paramSymbol = paramSymbol.getStatuses().iterator();
      while (paramSymbol.hasNext()) {
        add((symbol)paramSymbol.next());
      }
    }
  }
}
