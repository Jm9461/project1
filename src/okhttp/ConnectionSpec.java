package okhttp;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp.internal.Util;

public final class ConnectionSpec
{
  private static final Language[] APPROVED_CIPHER_SUITES = { Language.CPLUSPLUS, Language.CHUCK, Language.C_OBJDUMP, Language.CLOJURE, Language.COFFEESCRIPT, Language.COLDFUSION, Language.C, Language.CSHARP, Language.DARCS_PATCH, Language.DELPHI, Language.D, Language.D_OBJDUMP, Language.CYTHON };
  public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
  public static final ConnectionSpec MODERN_TLS;
  final String[] cipherSuites;
  final boolean supportsTlsExtensions;
  final boolean tls;
  final String[] tlsVersions;
  
  static
  {
    Builder localBuilder = new Builder(true);
    localBuilder.cipherSuites(APPROVED_CIPHER_SUITES);
    localBuilder.tlsVersions(new TlsVersion[] { TlsVersion.APPROVED_CIPHER_SUITES, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0 });
    localBuilder.supportsTlsExtensions(true);
    MODERN_TLS = localBuilder.build();
    localBuilder = new Builder(MODERN_TLS);
    localBuilder.tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_0 });
    localBuilder.supportsTlsExtensions(true);
    localBuilder.build();
  }
  
  ConnectionSpec(Builder paramBuilder)
  {
    tls = tls;
    cipherSuites = cipherSuites;
    tlsVersions = tlsVersions;
    supportsTlsExtensions = supportsTlsExtensions;
  }
  
  private ConnectionSpec supportedSpec(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    if (cipherSuites != null) {
      localObject = Util.intersect(Language.cipherSuites, paramSSLSocket.getEnabledCipherSuites(), cipherSuites);
    } else {
      localObject = paramSSLSocket.getEnabledCipherSuites();
    }
    String[] arrayOfString1;
    if (tlsVersions != null) {
      arrayOfString1 = Util.intersect(Util.cipherSuites, paramSSLSocket.getEnabledProtocols(), tlsVersions);
    } else {
      arrayOfString1 = paramSSLSocket.getEnabledProtocols();
    }
    String[] arrayOfString2 = paramSSLSocket.getSupportedCipherSuites();
    int i = Util.match(Language.cipherSuites, arrayOfString2, "TLS_FALLBACK_SCSV");
    paramSSLSocket = (SSLSocket)localObject;
    if (paramBoolean)
    {
      paramSSLSocket = (SSLSocket)localObject;
      if (i != -1) {
        paramSSLSocket = Util.concat((String[])localObject, arrayOfString2[i]);
      }
    }
    Object localObject = new Builder();
    ((Builder)localObject).cipherSuites(paramSSLSocket);
    ((Builder)localObject).tlsVersions(arrayOfString1);
    return ((Builder)localObject).build();
  }
  
  void apply(SSLSocket paramSSLSocket, boolean paramBoolean)
  {
    Object localObject = supportedSpec(paramSSLSocket, paramBoolean);
    String[] arrayOfString = tlsVersions;
    if (arrayOfString != null) {
      paramSSLSocket.setEnabledProtocols(arrayOfString);
    }
    localObject = cipherSuites;
    if (localObject != null) {
      paramSSLSocket.setEnabledCipherSuites((String[])localObject);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ConnectionSpec)) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    paramObject = (ConnectionSpec)paramObject;
    boolean bool = tls;
    if (bool != tls) {
      return false;
    }
    if (bool)
    {
      if (!Arrays.equals(cipherSuites, cipherSuites)) {
        return false;
      }
      if (!Arrays.equals(tlsVersions, tlsVersions)) {
        return false;
      }
      if (supportsTlsExtensions != supportsTlsExtensions) {
        return false;
      }
    }
    return true;
  }
  
  public List getTypes()
  {
    String[] arrayOfString = cipherSuites;
    if (arrayOfString != null) {
      return Language.values(arrayOfString);
    }
    return null;
  }
  
  public int hashCode()
  {
    if (tls) {
      return ((17 * 31 + Arrays.hashCode(cipherSuites)) * 31 + Arrays.hashCode(tlsVersions)) * 31 + (supportsTlsExtensions ^ true);
    }
    return 17;
  }
  
  public boolean isCompatible(SSLSocket paramSSLSocket)
  {
    if (!tls) {
      return false;
    }
    String[] arrayOfString = tlsVersions;
    if ((arrayOfString != null) && (!Util.equals(Util.cipherSuites, arrayOfString, paramSSLSocket.getEnabledProtocols()))) {
      return false;
    }
    arrayOfString = cipherSuites;
    return (arrayOfString == null) || (Util.equals(Language.cipherSuites, arrayOfString, paramSSLSocket.getEnabledCipherSuites()));
  }
  
  public boolean isTls()
  {
    return tls;
  }
  
  public boolean supportsTlsExtensions()
  {
    return supportsTlsExtensions;
  }
  
  public List tlsVersions()
  {
    String[] arrayOfString = tlsVersions;
    if (arrayOfString != null) {
      return TlsVersion.get(arrayOfString);
    }
    return null;
  }
  
  public String toString()
  {
    if (!tls) {
      return "ConnectionSpec()";
    }
    Object localObject = cipherSuites;
    String str = "[all enabled]";
    if (localObject != null) {
      localObject = getTypes().toString();
    } else {
      localObject = "[all enabled]";
    }
    if (tlsVersions != null) {
      str = tlsVersions().toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ConnectionSpec(cipherSuites=");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(", tlsVersions=");
    localStringBuilder.append(str);
    localStringBuilder.append(", supportsTlsExtensions=");
    localStringBuilder.append(supportsTlsExtensions);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public final class Builder
  {
    String[] cipherSuites;
    boolean supportsTlsExtensions;
    boolean tls;
    String[] tlsVersions;
    
    public Builder()
    {
      tls = tls;
      cipherSuites = cipherSuites;
      tlsVersions = tlsVersions;
      supportsTlsExtensions = supportsTlsExtensions;
    }
    
    Builder()
    {
      tls = this$1;
    }
    
    public ConnectionSpec build()
    {
      return new ConnectionSpec(this);
    }
    
    public Builder cipherSuites(String... paramVarArgs)
    {
      if (tls)
      {
        if (paramVarArgs.length != 0)
        {
          cipherSuites = ((String[])paramVarArgs.clone());
          return this;
        }
        throw new IllegalArgumentException("At least one cipher suite is required");
      }
      throw new IllegalStateException("no cipher suites for cleartext connections");
    }
    
    public Builder cipherSuites(Language... paramVarArgs)
    {
      if (tls)
      {
        String[] arrayOfString = new String[paramVarArgs.length];
        int i = 0;
        while (i < paramVarArgs.length)
        {
          arrayOfString[i] = javaName;
          i += 1;
        }
        cipherSuites(arrayOfString);
        return this;
      }
      paramVarArgs = new IllegalStateException("no cipher suites for cleartext connections");
      throw paramVarArgs;
    }
    
    public Builder supportsTlsExtensions(boolean paramBoolean)
    {
      if (tls)
      {
        supportsTlsExtensions = paramBoolean;
        return this;
      }
      throw new IllegalStateException("no TLS extensions for cleartext connections");
    }
    
    public Builder tlsVersions(String... paramVarArgs)
    {
      if (tls)
      {
        if (paramVarArgs.length != 0)
        {
          tlsVersions = ((String[])paramVarArgs.clone());
          return this;
        }
        throw new IllegalArgumentException("At least one TLS version is required");
      }
      throw new IllegalStateException("no TLS versions for cleartext connections");
    }
    
    public Builder tlsVersions(TlsVersion... paramVarArgs)
    {
      if (tls)
      {
        String[] arrayOfString = new String[paramVarArgs.length];
        int i = 0;
        while (i < paramVarArgs.length)
        {
          arrayOfString[i] = javaName;
          i += 1;
        }
        tlsVersions(arrayOfString);
        return this;
      }
      paramVarArgs = new IllegalStateException("no TLS versions for cleartext connections");
      throw paramVarArgs;
    }
  }
}
