package okhttp;

public abstract interface Authenticator
{
  public static final Authenticator NONE = new AuthenticatorAdapter();
  
  public abstract Request authenticate(Route paramRoute, Response paramResponse);
}
