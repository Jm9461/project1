package org.org.asm;

import a.b.f.m;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Label
{
  public View a;
  final ArrayList<m> b = new ArrayList();
  public final Map<String, Object> c = new HashMap();
  
  public Label() {}
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Label)) && (a == a) && (c.equals(c));
  }
  
  public int hashCode()
  {
    return a.hashCode() * 31 + c.hashCode();
  }
  
  public String toString()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("TransitionValues@");
    ((StringBuilder)localObject1).append(Integer.toHexString(hashCode()));
    ((StringBuilder)localObject1).append(":\n");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("    view = ");
    ((StringBuilder)localObject2).append(a);
    ((StringBuilder)localObject2).append("\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("    values:");
    localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = c.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      String str = (String)((Iterator)localObject2).next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("    ");
      localStringBuilder.append(str);
      localStringBuilder.append(": ");
      localStringBuilder.append(c.get(str));
      localStringBuilder.append("\n");
      localObject1 = localStringBuilder.toString();
    }
    return localObject1;
  }
}
