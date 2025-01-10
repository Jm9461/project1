package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class h
{
  public h() {}
  
  private void a(Context paramContext)
  {
    try
    {
      Class localClass = get(paramContext.getClass());
      a(localClass.getName());
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getClass().getSimpleName());
      localStringBuilder.append(" does not have a Parcelizer");
      throw new RuntimeException(localStringBuilder.toString(), localClassNotFoundException);
    }
  }
  
  protected static void a(Context paramContext, h paramH)
  {
    try
    {
      Object localObject = e(paramContext);
      Class localClass = paramContext.getClass();
      localObject = ((Class)localObject).getDeclaredMethod("write", new Class[] { localClass, a.class });
      ((Method)localObject).invoke(null, new Object[] { paramContext, paramH });
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      if ((paramContext.getCause() instanceof RuntimeException)) {
        throw ((RuntimeException)paramContext.getCause());
      }
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramContext);
    }
  }
  
  private static Class e(Context paramContext)
  {
    return get(paramContext.getClass());
  }
  
  protected static Context get(String paramString, h paramH)
  {
    try
    {
      paramString = Class.forName(paramString, true, a.class.getClassLoader());
      paramString = paramString.getDeclaredMethod("read", new Class[] { a.class });
      paramString = paramString.invoke(null, new Object[] { paramH });
      return (Context)paramString;
    }
    catch (ClassNotFoundException paramString) {}catch (NoSuchMethodException paramString)
    {
      break label73;
    }
    catch (InvocationTargetException paramString)
    {
      break label84;
    }
    catch (IllegalAccessException paramString) {}
    throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramString);
    label73:
    throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramString);
    label84:
    if ((paramString.getCause() instanceof RuntimeException)) {
      throw ((RuntimeException)paramString.getCause());
    }
    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramString);
    throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramString);
  }
  
  private static Class get(Class paramClass)
  {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }
  
  protected abstract int a();
  
  public String a(String paramString, int paramInt)
  {
    if (!b(paramInt)) {
      return paramString;
    }
    return getValue();
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    c(paramInt2);
    l(paramInt1);
  }
  
  public void a(Parcelable paramParcelable, int paramInt)
  {
    c(paramInt);
    b(paramParcelable);
  }
  
  protected abstract void a(String paramString);
  
  public void a(boolean paramBoolean1, boolean paramBoolean2) {}
  
  protected abstract void a(byte[] paramArrayOfByte);
  
  public byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if (!b(paramInt)) {
      return paramArrayOfByte;
    }
    return read();
  }
  
  public int add(int paramInt1, int paramInt2)
  {
    if (!b(paramInt2)) {
      return paramInt1;
    }
    return a();
  }
  
  protected abstract void b();
  
  protected abstract void b(Parcelable paramParcelable);
  
  public void b(String paramString, int paramInt)
  {
    c(paramInt);
    a(paramString);
  }
  
  protected abstract boolean b(int paramInt);
  
  protected abstract h c();
  
  protected abstract void c(int paramInt);
  
  public void c(byte[] paramArrayOfByte, int paramInt)
  {
    c(paramInt);
    a(paramArrayOfByte);
  }
  
  protected abstract Parcelable d();
  
  public Parcelable d(Parcelable paramParcelable, int paramInt)
  {
    if (!b(paramInt)) {
      return paramParcelable;
    }
    return d();
  }
  
  protected Context f()
  {
    String str = getValue();
    if (str == null) {
      return null;
    }
    return get(str, c());
  }
  
  protected abstract String getValue();
  
  protected void i(Context paramContext)
  {
    if (paramContext == null)
    {
      a(null);
      return;
    }
    a(paramContext);
    h localH = c();
    a(paramContext, localH);
    localH.b();
  }
  
  protected abstract void l(int paramInt);
  
  public boolean processBytes()
  {
    return false;
  }
  
  protected abstract byte[] read();
}
