package apps.v4.widget;

public abstract class Property<T, V>
{
  private final String mName;
  
  public Property(Class paramClass, String paramString)
  {
    mName = paramString;
  }
  
  public abstract Object get(Object paramObject);
  
  public String getName()
  {
    return mName;
  }
  
  public void set(Object paramObject1, Object paramObject2)
  {
    paramObject1 = new StringBuilder();
    paramObject1.append("Property ");
    paramObject1.append(getName());
    paramObject1.append(" is read-only");
    throw new UnsupportedOperationException(paramObject1.toString());
  }
}
