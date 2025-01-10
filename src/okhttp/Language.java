package okhttp;

import e.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Language
{
  public static final Language C;
  public static final Language CHUCK;
  public static final Language CLOJURE;
  public static final Language COFFEESCRIPT = add("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
  public static final Language COLDFUSION;
  public static final Language CPLUSPLUS;
  public static final Language CSHARP;
  public static final Language CYTHON;
  public static final Language C_OBJDUMP;
  public static final Language D;
  public static final Language DARCS_PATCH;
  public static final Language DELPHI;
  public static final Language D_OBJDUMP;
  static final Comparator<String> cipherSuites = new Version.BuildAwareOrder();
  private static final Map<String, h> protocols = new TreeMap(cipherSuites);
  final String javaName;
  
  static
  {
    add("SSL_RSA_WITH_NULL_MD5", 1);
    add("SSL_RSA_WITH_NULL_SHA", 2);
    add("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
    add("SSL_RSA_WITH_RC4_128_MD5", 4);
    add("SSL_RSA_WITH_RC4_128_SHA", 5);
    add("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
    add("SSL_RSA_WITH_DES_CBC_SHA", 9);
    CYTHON = add("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    add("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    add("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    add("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    add("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    add("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    add("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    add("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    add("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    add("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    add("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    add("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    add("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    add("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    add("TLS_KRB5_WITH_RC4_128_SHA", 32);
    add("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    add("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    add("TLS_KRB5_WITH_RC4_128_MD5", 36);
    add("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    add("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    add("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    add("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    D = add("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    add("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    D_OBJDUMP = add("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    add("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    add("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    add("TLS_RSA_WITH_NULL_SHA256", 59);
    add("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    add("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    add("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    add("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    add("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    add("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    add("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    add("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    add("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    add("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    add("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    add("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
    add("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    add("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    add("TLS_PSK_WITH_RC4_128_SHA", 138);
    add("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
    add("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
    add("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
    add("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    DARCS_PATCH = add("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    DELPHI = add("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    add("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    add("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    add("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
    add("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
    add("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
    add("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
    add("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    add("TLS_FALLBACK_SCSV", 22016);
    add("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    add("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    add("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    add("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    add("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    add("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    add("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    add("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    add("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    add("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    add("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    add("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    add("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    add("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    add("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    add("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    C = add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    CSHARP = add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    add("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    add("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    add("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    add("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    add("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    add("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    add("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    add("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    add("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    CPLUSPLUS = add("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    C_OBJDUMP = add("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    add("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    add("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    CHUCK = add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    CLOJURE = add("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    add("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    add("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    add("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    add("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    COLDFUSION = add("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
  }
  
  private Language(String paramString)
  {
    if (paramString != null)
    {
      javaName = paramString;
      return;
    }
    throw new NullPointerException();
  }
  
  private static Language add(String paramString, int paramInt)
  {
    return get(paramString);
  }
  
  public static Language get(String paramString)
  {
    try
    {
      Language localLanguage2 = (Language)protocols.get(paramString);
      Language localLanguage1 = localLanguage2;
      if (localLanguage2 == null)
      {
        localLanguage2 = new Language(paramString);
        localLanguage1 = localLanguage2;
        protocols.put(paramString, localLanguage2);
      }
      return localLanguage1;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  static List values(String... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList(paramVarArgs.length);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(get(paramVarArgs[i]));
      i += 1;
    }
    return Collections.unmodifiableList(localArrayList);
  }
  
  public String javaName()
  {
    return javaName;
  }
  
  public String toString()
  {
    return javaName;
  }
}
