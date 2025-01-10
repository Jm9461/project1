package android.support.v4.app;

import android.arch.lifecycle.Update;
import android.arch.lifecycle.h;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class Node
{
  public Node() {}
  
  public static Node append(h paramH)
  {
    return new LoaderManagerImpl(paramH, ((Update)paramH).add());
  }
  
  public abstract void dump();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
}
