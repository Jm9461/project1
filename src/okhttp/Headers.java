package okhttp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp.internal.Util;

public final class Headers
{
  private final String[] namesAndValues;
  
  Headers(Builder paramBuilder)
  {
    paramBuilder = namesAndValues;
    namesAndValues = ((String[])paramBuilder.toArray(new String[paramBuilder.size()]));
  }
  
  private static String get(String[] paramArrayOfString, String paramString)
  {
    int i = paramArrayOfString.length - 2;
    while (i >= 0)
    {
      if (paramString.equalsIgnoreCase(paramArrayOfString[i])) {
        return paramArrayOfString[(i + 1)];
      }
      i -= 2;
    }
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Headers)) && (Arrays.equals(namesAndValues, namesAndValues));
  }
  
  public String get(String paramString)
  {
    return get(namesAndValues, paramString);
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(namesAndValues);
  }
  
  public String name(int paramInt)
  {
    return namesAndValues[(paramInt * 2)];
  }
  
  public Builder newBuilder()
  {
    Builder localBuilder = new Builder();
    Collections.addAll(namesAndValues, namesAndValues);
    return localBuilder;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = value();
    while (i < j)
    {
      localStringBuilder.append(name(i));
      localStringBuilder.append(": ");
      localStringBuilder.append(value(i));
      localStringBuilder.append("\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public int value()
  {
    return namesAndValues.length / 2;
  }
  
  public String value(int paramInt)
  {
    return namesAndValues[(paramInt * 2 + 1)];
  }
  
  public List values(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    int j = value();
    while (i < j)
    {
      Object localObject2 = localObject1;
      if (paramString.equalsIgnoreCase(name(i)))
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList(2);
        }
        ((List)localObject2).add(value(i));
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 != null) {
      return Collections.unmodifiableList(localObject1);
    }
    return Collections.emptyList();
  }
  
  public final class Builder
  {
    final List<String> namesAndValues = new ArrayList(20);
    
    public Builder() {}
    
    private void validate(String paramString1, String paramString2)
    {
      if (paramString1 != null)
      {
        if (!paramString1.isEmpty())
        {
          int i = 0;
          int j = paramString1.length();
          int k;
          while (i < j)
          {
            k = paramString1.charAt(i);
            if ((k > 32) && (k < 127)) {
              i += 1;
            } else {
              throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString1 }));
            }
          }
          if (paramString2 != null)
          {
            i = 0;
            j = paramString2.length();
            for (;;)
            {
              if (i >= j) {
                return;
              }
              k = paramString2.charAt(i);
              if (((k <= 31) && (k != 9)) || (k >= 127)) {
                break;
              }
              i += 1;
            }
            throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString1, paramString2 }));
          }
          paramString2 = new StringBuilder();
          paramString2.append("value for name ");
          paramString2.append(paramString1);
          paramString2.append(" == null");
          throw new NullPointerException(paramString2.toString());
        }
        throw new IllegalArgumentException("name is empty");
      }
      paramString1 = new NullPointerException("name == null");
      throw paramString1;
    }
    
    public Builder add(String paramString1, String paramString2)
    {
      validate(paramString1, paramString2);
      addLenient(paramString1, paramString2);
      return this;
    }
    
    Builder addLenient(String paramString)
    {
      int i = paramString.indexOf(":", 1);
      if (i != -1)
      {
        addLenient(paramString.substring(0, i), paramString.substring(i + 1));
        return this;
      }
      if (paramString.startsWith(":"))
      {
        addLenient("", paramString.substring(1));
        return this;
      }
      addLenient("", paramString);
      return this;
    }
    
    Builder addLenient(String paramString1, String paramString2)
    {
      namesAndValues.add(paramString1);
      namesAndValues.add(paramString2.trim());
      return this;
    }
    
    public Headers build()
    {
      return new Headers(this);
    }
    
    public String get(String paramString)
    {
      int i = namesAndValues.size() - 2;
      while (i >= 0)
      {
        if (paramString.equalsIgnoreCase((String)namesAndValues.get(i))) {
          return (String)namesAndValues.get(i + 1);
        }
        i -= 2;
      }
      return null;
    }
    
    public Builder removeAll(String paramString)
    {
      int j;
      for (int i = 0; i < namesAndValues.size(); i = j + 2)
      {
        j = i;
        if (paramString.equalsIgnoreCase((String)namesAndValues.get(i)))
        {
          namesAndValues.remove(i);
          namesAndValues.remove(i);
          j = i - 2;
        }
      }
      return this;
    }
    
    public Builder set(String paramString1, String paramString2)
    {
      validate(paramString1, paramString2);
      removeAll(paramString1);
      addLenient(paramString1, paramString2);
      return this;
    }
  }
}
