package android.support.v4.app;

import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager
{
  public FragmentManager() {}
  
  public abstract FragmentTransaction beginTransaction();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract Fragment findFragmentByTag(String paramString);
  
  public abstract Fragment getFragment(Bundle paramBundle, String paramString);
  
  public abstract boolean getFragment();
  
  public abstract void popBackStack(int paramInt1, int paramInt2);
  
  public abstract boolean popBackStackImmediate();
  
  public abstract void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment);
  
  public abstract List remove();
  
  public abstract Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment);
  
  public abstract interface BackStackEntry {}
}
