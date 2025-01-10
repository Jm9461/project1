package okhttp.internal.http;

import e.c0;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp.Route;

public final class Iterator
{
  private int i = 0;
  private final List<c0> state;
  
  Iterator(List paramList)
  {
    state = paramList;
  }
  
  public List getResult()
  {
    return new ArrayList(state);
  }
  
  public boolean hasNext()
  {
    return i < state.size();
  }
  
  public Route next()
  {
    if (hasNext())
    {
      List localList = state;
      int j = i;
      i = (j + 1);
      return (Route)localList.get(j);
    }
    throw new NoSuchElementException();
  }
}
