package android.arch.lifecycle;

public enum f
{
  static
  {
    a = new f("CREATED", 2);
    c = new f("STARTED", 3);
    g = new f("RESUMED", 4);
  }
  
  public boolean a(f paramF)
  {
    return compareTo(paramF) >= 0;
  }
}
