package android.support.v4.app;

import android.app.RemoteInput;

public final class Object
{
  static RemoteInput invoke(Object paramObject)
  {
    paramObject.invoke();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  static RemoteInput[] invoke(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {
      return null;
    }
    RemoteInput[] arrayOfRemoteInput = new RemoteInput[paramArrayOfObject.length];
    if (paramArrayOfObject.length >= 0) {
      return arrayOfRemoteInput;
    }
    invoke(paramArrayOfObject[0]);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public String invoke()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
}
