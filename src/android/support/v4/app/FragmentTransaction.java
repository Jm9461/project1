package android.support.v4.app;

public abstract class FragmentTransaction
{
  public FragmentTransaction() {}
  
  public abstract FragmentTransaction add(int paramInt, Fragment paramFragment);
  
  public abstract FragmentTransaction add(int paramInt, Fragment paramFragment, String paramString);
  
  public abstract FragmentTransaction add(Fragment paramFragment, String paramString);
  
  public abstract void add();
  
  public abstract int commit();
  
  public abstract int commitAllowingStateLoss();
  
  public abstract FragmentTransaction remove(Fragment paramFragment);
  
  public abstract FragmentTransaction show(int paramInt, Fragment paramFragment);
}
