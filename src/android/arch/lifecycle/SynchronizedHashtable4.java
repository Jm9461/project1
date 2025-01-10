package android.arch.lifecycle;

public class SynchronizedHashtable4
{
  private final Attribute a;
  private final ResultSet type;
  
  public SynchronizedHashtable4(Attribute paramAttribute, ResultSet paramResultSet)
  {
    type = paramResultSet;
    a = paramAttribute;
  }
  
  public List get(Class paramClass)
  {
    String str = paramClass.getCanonicalName();
    if (str != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
      localStringBuilder.append(str);
      return get(localStringBuilder.toString(), paramClass);
    }
    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
  }
  
  public List get(String paramString, Class paramClass)
  {
    List localList = a.read(paramString);
    if (paramClass.isInstance(localList)) {
      return localList;
    }
    paramClass = type.getValue(paramClass);
    a.a(paramString, paramClass);
    return paramClass;
  }
}
