package okhttp.internal;

import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp.Address;
import okhttp.ConnectionPool;
import okhttp.ConnectionSpec;
import okhttp.Headers.Builder;
import okhttp.Route;
import okhttp.a0.a;
import okhttp.internal.http.RealConnection;
import okhttp.internal.http.RouteDatabase;
import okhttp.internal.http.StreamAllocation;

public abstract class Internal
{
  public static Internal instance;
  
  public Internal() {}
  
  public abstract void add(Headers.Builder paramBuilder, String paramString1, String paramString2);
  
  public abstract void addLenient(Headers.Builder paramBuilder, String paramString);
  
  public abstract void apply(ConnectionSpec paramConnectionSpec, SSLSocket paramSSLSocket, boolean paramBoolean);
  
  public abstract boolean connectionBecameIdle(ConnectionPool paramConnectionPool, RealConnection paramRealConnection);
  
  public abstract int get(a0.a paramA);
  
  public abstract Socket get(ConnectionPool paramConnectionPool, Address paramAddress, StreamAllocation paramStreamAllocation);
  
  public abstract RealConnection get(ConnectionPool paramConnectionPool, Address paramAddress, StreamAllocation paramStreamAllocation, Route paramRoute);
  
  public abstract boolean get(Address paramAddress1, Address paramAddress2);
  
  public abstract void put(ConnectionPool paramConnectionPool, RealConnection paramRealConnection);
  
  public abstract RouteDatabase routeDatabase(ConnectionPool paramConnectionPool);
}
