package apps.core.event.util;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

public abstract interface IInAppBillingService
  extends IInterface
{
  public abstract Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract Bundle getPurchases(int paramInt, String paramString1, String paramString2, String paramString3);
  
  public abstract class Stub
    extends Binder
    implements IInAppBillingService
  {
    public static IInAppBillingService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
      if ((localIInterface != null) && ((localIInterface instanceof IInAppBillingService))) {
        return (IInAppBillingService)localIInterface;
      }
      return new a.a.a(paramIBinder);
    }
  }
}
