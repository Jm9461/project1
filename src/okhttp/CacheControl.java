package okhttp;

import java.util.concurrent.TimeUnit;
import okhttp.internal.httpclient.OkHeaders;

public final class CacheControl
{
  public static final CacheControl FORCE_CACHE;
  String headerValue;
  private final boolean isPrivate;
  private final boolean isPublic;
  private final int maxAgeSeconds;
  private final int maxStaleSeconds;
  private final int minFreshSeconds;
  private final boolean mustRevalidate;
  private final boolean noCache;
  private final boolean noStore;
  private final boolean noTransform;
  private final boolean onlyIfCached;
  private final boolean privateFlag;
  private final int sMaxAgeSeconds;
  
  static
  {
    Builder localBuilder = new Builder();
    localBuilder.noCache();
    localBuilder.build();
    localBuilder = new Builder();
    localBuilder.onlyIfCached();
    localBuilder.maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS);
    FORCE_CACHE = localBuilder.build();
  }
  
  CacheControl(Builder paramBuilder)
  {
    noCache = noCache;
    noStore = noStore;
    maxAgeSeconds = maxAgeSeconds;
    sMaxAgeSeconds = -1;
    isPrivate = false;
    isPublic = false;
    mustRevalidate = false;
    maxStaleSeconds = maxStaleSeconds;
    minFreshSeconds = minFreshSeconds;
    onlyIfCached = onlyIfCached;
    noTransform = noTransform;
    privateFlag = privateFlag;
  }
  
  private CacheControl(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, int paramInt3, int paramInt4, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, String paramString)
  {
    noCache = paramBoolean1;
    noStore = paramBoolean2;
    maxAgeSeconds = paramInt1;
    sMaxAgeSeconds = paramInt2;
    isPrivate = paramBoolean3;
    isPublic = paramBoolean4;
    mustRevalidate = paramBoolean5;
    maxStaleSeconds = paramInt3;
    minFreshSeconds = paramInt4;
    onlyIfCached = paramBoolean6;
    noTransform = paramBoolean7;
    privateFlag = paramBoolean8;
    headerValue = paramString;
  }
  
  private String headerValue()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (noCache) {
      localStringBuilder.append("no-cache, ");
    }
    if (noStore) {
      localStringBuilder.append("no-store, ");
    }
    if (maxAgeSeconds != -1)
    {
      localStringBuilder.append("max-age=");
      localStringBuilder.append(maxAgeSeconds);
      localStringBuilder.append(", ");
    }
    if (sMaxAgeSeconds != -1)
    {
      localStringBuilder.append("s-maxage=");
      localStringBuilder.append(sMaxAgeSeconds);
      localStringBuilder.append(", ");
    }
    if (isPrivate) {
      localStringBuilder.append("private, ");
    }
    if (isPublic) {
      localStringBuilder.append("public, ");
    }
    if (mustRevalidate) {
      localStringBuilder.append("must-revalidate, ");
    }
    if (maxStaleSeconds != -1)
    {
      localStringBuilder.append("max-stale=");
      localStringBuilder.append(maxStaleSeconds);
      localStringBuilder.append(", ");
    }
    if (minFreshSeconds != -1)
    {
      localStringBuilder.append("min-fresh=");
      localStringBuilder.append(minFreshSeconds);
      localStringBuilder.append(", ");
    }
    if (onlyIfCached) {
      localStringBuilder.append("only-if-cached, ");
    }
    if (noTransform) {
      localStringBuilder.append("no-transform, ");
    }
    if (privateFlag) {
      localStringBuilder.append("immutable, ");
    }
    if (localStringBuilder.length() == 0) {
      return "";
    }
    localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length());
    return localStringBuilder.toString();
  }
  
  public static CacheControl parse(Headers paramHeaders)
  {
    int i1 = -1;
    int n = -1;
    boolean bool8 = false;
    boolean bool7 = false;
    boolean bool6 = false;
    int i = 1;
    Object localObject1 = null;
    int i2 = 0;
    int i7 = paramHeaders.value();
    boolean bool5 = false;
    boolean bool4 = false;
    boolean bool3 = false;
    int m = -1;
    int k = -1;
    boolean bool2 = false;
    boolean bool16;
    for (boolean bool1 = false;; bool1 = bool16)
    {
      Object localObject2 = paramHeaders;
      if (i2 >= i7) {
        break;
      }
      String str2 = ((Headers)localObject2).name(i2);
      boolean bool15 = bool6;
      String str1 = ((Headers)localObject2).value(i2);
      if (str2.equalsIgnoreCase("Cache-Control"))
      {
        if (localObject1 != null) {
          i = 0;
        } else {
          localObject1 = str1;
        }
      }
      else
      {
        j = i1;
        i3 = n;
        bool9 = bool8;
        bool10 = bool7;
        localObject2 = localObject1;
        bool11 = bool5;
        bool12 = bool4;
        bool13 = bool3;
        i4 = m;
        i5 = k;
        bool14 = bool2;
        bool16 = bool1;
        if (!str2.equalsIgnoreCase("Pragma")) {
          break label1170;
        }
        i = 0;
      }
      int j = 0;
      bool6 = bool15;
      while (j < str1.length())
      {
        i3 = OkHeaders.skipUntil(str1, j, "=,;");
        str2 = str1.substring(j, i3).trim();
        if ((i3 != str1.length()) && (str1.charAt(i3) != ',') && (str1.charAt(i3) != ';'))
        {
          i4 = OkHeaders.skipWhitespace(str1, i3 + 1);
          if ((i4 < str1.length()) && (str1.charAt(i4) == '"'))
          {
            j = i4 + 1;
            i3 = OkHeaders.skipUntil(str1, j, "\"");
            localObject2 = str1.substring(j, i3);
            j = i3 + 1;
          }
          else
          {
            i3 = OkHeaders.skipUntil(str1, i4, ",;");
            j = i3;
            localObject2 = str1.substring(i4, i3).trim();
          }
        }
        else
        {
          j = i3 + 1;
          localObject2 = null;
        }
        int i6;
        if ("no-cache".equalsIgnoreCase(str2))
        {
          bool15 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
        }
        else if ("no-store".equalsIgnoreCase(str2))
        {
          bool14 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool15 = bool1;
        }
        else if ("max-age".equalsIgnoreCase(str2))
        {
          i6 = OkHeaders.parseSeconds((String)localObject2, -1);
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("s-maxage".equalsIgnoreCase(str2))
        {
          i5 = OkHeaders.parseSeconds((String)localObject2, -1);
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("private".equalsIgnoreCase(str2))
        {
          bool13 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("public".equalsIgnoreCase(str2))
        {
          bool12 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("must-revalidate".equalsIgnoreCase(str2))
        {
          bool11 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("max-stale".equalsIgnoreCase(str2))
        {
          i3 = OkHeaders.parseSeconds((String)localObject2, Integer.MAX_VALUE);
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("min-fresh".equalsIgnoreCase(str2))
        {
          i4 = OkHeaders.parseSeconds((String)localObject2, -1);
          i3 = i1;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("only-if-cached".equalsIgnoreCase(str2))
        {
          bool9 = true;
          i3 = i1;
          i4 = n;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else if ("no-transform".equalsIgnoreCase(str2))
        {
          bool10 = true;
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
        }
        else
        {
          i3 = i1;
          i4 = n;
          bool9 = bool8;
          bool10 = bool7;
          bool11 = bool5;
          bool12 = bool4;
          bool13 = bool3;
          i5 = m;
          i6 = k;
          bool14 = bool2;
          bool15 = bool1;
          if ("immutable".equalsIgnoreCase(str2))
          {
            bool6 = true;
            i3 = i1;
            i4 = n;
            bool9 = bool8;
            bool10 = bool7;
            bool11 = bool5;
            bool12 = bool4;
            bool13 = bool3;
            i5 = m;
            i6 = k;
            bool14 = bool2;
            bool15 = bool1;
          }
        }
        i1 = i3;
        n = i4;
        bool8 = bool9;
        bool7 = bool10;
        bool5 = bool11;
        bool4 = bool12;
        bool3 = bool13;
        m = i5;
        k = i6;
        bool2 = bool14;
        bool1 = bool15;
      }
      j = i1;
      int i3 = n;
      boolean bool9 = bool8;
      boolean bool10 = bool7;
      localObject2 = localObject1;
      boolean bool11 = bool5;
      boolean bool12 = bool4;
      boolean bool13 = bool3;
      int i4 = m;
      int i5 = k;
      boolean bool14 = bool2;
      bool16 = bool1;
      label1170:
      i2 += 1;
      i1 = j;
      n = i3;
      bool8 = bool9;
      bool7 = bool10;
      localObject1 = localObject2;
      bool5 = bool11;
      bool4 = bool12;
      bool3 = bool13;
      m = i4;
      k = i5;
      bool2 = bool14;
    }
    if (i == 0) {
      localObject1 = null;
    }
    return new CacheControl(bool1, bool2, k, m, bool3, bool4, bool5, i1, n, bool8, bool7, bool6, localObject1);
  }
  
  public boolean isPrivate()
  {
    return isPrivate;
  }
  
  public boolean isPrivateFlag()
  {
    return privateFlag;
  }
  
  public boolean isPublic()
  {
    return isPublic;
  }
  
  public int maxAgeSeconds()
  {
    return maxAgeSeconds;
  }
  
  public int maxStaleSeconds()
  {
    return maxStaleSeconds;
  }
  
  public int minFreshSeconds()
  {
    return minFreshSeconds;
  }
  
  public boolean mustRevalidate()
  {
    return mustRevalidate;
  }
  
  public boolean noCache()
  {
    return noCache;
  }
  
  public boolean noStore()
  {
    return noStore;
  }
  
  public boolean onlyIfCached()
  {
    return onlyIfCached;
  }
  
  public String toString()
  {
    String str = headerValue;
    if (str != null) {
      return str;
    }
    str = headerValue();
    headerValue = str;
    return str;
  }
  
  public final class Builder
  {
    int maxAgeSeconds = -1;
    int maxStaleSeconds = -1;
    int minFreshSeconds = -1;
    boolean noCache;
    boolean noStore;
    boolean noTransform;
    boolean onlyIfCached;
    boolean privateFlag;
    
    public Builder() {}
    
    public CacheControl build()
    {
      return new CacheControl(this);
    }
    
    public Builder maxStale(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt >= 0)
      {
        long l = paramTimeUnit.toSeconds(paramInt);
        if (l > 2147483647L) {
          paramInt = Integer.MAX_VALUE;
        } else {
          paramInt = (int)l;
        }
        maxStaleSeconds = paramInt;
        return this;
      }
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append("maxStale < 0: ");
      paramTimeUnit.append(paramInt);
      throw new IllegalArgumentException(paramTimeUnit.toString());
    }
    
    public Builder noCache()
    {
      noCache = true;
      return this;
    }
    
    public Builder noStore()
    {
      noStore = true;
      return this;
    }
    
    public Builder onlyIfCached()
    {
      onlyIfCached = true;
      return this;
    }
  }
}
