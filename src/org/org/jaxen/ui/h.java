package org.org.jaxen.ui;

import android.util.Base64;
import java.util.List;
import org.org.jaxen.util.ClassReader;

public final class h
{
  private final String a;
  private final String b;
  private final int d;
  private final List<List<byte[]>> e;
  private final String f;
  private final String g;
  
  public h(String paramString1, String paramString2, String paramString3, List paramList)
  {
    ClassReader.a(paramString1);
    f = ((String)paramString1);
    ClassReader.a(paramString2);
    b = ((String)paramString2);
    ClassReader.a(paramString3);
    g = ((String)paramString3);
    ClassReader.a(paramList);
    e = ((List)paramList);
    d = 0;
    paramString1 = new StringBuilder(f);
    paramString1.append("-");
    paramString1.append(b);
    paramString1.append("-");
    paramString1.append(g);
    a = paramString1.toString();
  }
  
  public String c()
  {
    return a;
  }
  
  public int e()
  {
    return d;
  }
  
  public String f()
  {
    return f;
  }
  
  public String getGroupId()
  {
    return b;
  }
  
  public String getIntent()
  {
    return g;
  }
  
  public List getTitle()
  {
    return e;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("FontRequest {mProviderAuthority: ");
    ((StringBuilder)localObject).append(f);
    ((StringBuilder)localObject).append(", mProviderPackage: ");
    ((StringBuilder)localObject).append(b);
    ((StringBuilder)localObject).append(", mQuery: ");
    ((StringBuilder)localObject).append(g);
    ((StringBuilder)localObject).append(", mCertificates:");
    localStringBuilder.append(((StringBuilder)localObject).toString());
    int i = 0;
    while (i < e.size())
    {
      localStringBuilder.append(" [");
      localObject = (List)e.get(i);
      int j = 0;
      while (j < ((List)localObject).size())
      {
        localStringBuilder.append(" \"");
        localStringBuilder.append(Base64.encodeToString((byte[])((List)localObject).get(j), 0));
        localStringBuilder.append("\"");
        j += 1;
      }
      localStringBuilder.append(" ]");
      i += 1;
    }
    localStringBuilder.append("}");
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("mCertificatesArray: ");
    ((StringBuilder)localObject).append(d);
    localStringBuilder.append(((StringBuilder)localObject).toString());
    return localStringBuilder.toString();
  }
}
