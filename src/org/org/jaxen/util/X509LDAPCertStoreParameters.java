package org.org.jaxen.util;

import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.Objects;

public class X509LDAPCertStoreParameters
{
  public static boolean apply(Object paramObject1, Object paramObject2)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return Objects.equals(paramObject1, paramObject2);
    }
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return Objects.hash(paramVarArgs);
    }
    return Arrays.hashCode(paramVarArgs);
  }
}
