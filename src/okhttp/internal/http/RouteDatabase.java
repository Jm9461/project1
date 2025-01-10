package okhttp.internal.http;

import e.c0;
import java.util.LinkedHashSet;
import java.util.Set;
import okhttp.Route;

public final class RouteDatabase
{
  private final Set<c0> failedRoutes = new LinkedHashSet();
  
  public RouteDatabase() {}
  
  public void connected(Route paramRoute)
  {
    try
    {
      failedRoutes.remove(paramRoute);
      return;
    }
    catch (Throwable paramRoute)
    {
      throw paramRoute;
    }
  }
  
  public void failed(Route paramRoute)
  {
    try
    {
      failedRoutes.add(paramRoute);
      return;
    }
    catch (Throwable paramRoute)
    {
      throw paramRoute;
    }
  }
  
  public boolean shouldPostpone(Route paramRoute)
  {
    try
    {
      boolean bool = failedRoutes.contains(paramRoute);
      return bool;
    }
    catch (Throwable paramRoute)
    {
      throw paramRoute;
    }
  }
}
