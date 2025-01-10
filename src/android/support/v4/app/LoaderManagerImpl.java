package android.support.v4.app;

import a.b.g.g.o;
import android.arch.lifecycle.Attribute;
import android.arch.lifecycle.Context;
import android.arch.lifecycle.Item;
import android.arch.lifecycle.List;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ResultSet;
import android.arch.lifecycle.SynchronizedHashtable4;
import android.arch.lifecycle.h;
import android.arch.lifecycle.j;
import android.arch.lifecycle.k;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.content.b;
import android.support.v4.content.b.a;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.org.jaxen.util.ClassWriter;
import org.org.jaxen.util.DebugUtils;

class LoaderManagerImpl
  extends Node
{
  static boolean DEBUG = false;
  private final LoaderViewModel mActivity;
  private final h mHost;
  
  LoaderManagerImpl(h paramH, Attribute paramAttribute)
  {
    mHost = paramH;
    mActivity = LoaderViewModel.getResults(paramAttribute);
  }
  
  public void dump()
  {
    mActivity.b();
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    mActivity.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("LoaderManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(mHost, localStringBuilder);
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }
  
  static class LoaderViewModel
    extends List
  {
    private static final ResultSet results = new a();
    private o<LoaderManagerImpl.a> a = new ClassWriter();
    
    LoaderViewModel() {}
    
    static LoaderViewModel getResults(Attribute paramAttribute)
    {
      return (LoaderViewModel)new SynchronizedHashtable4(paramAttribute, results).get(LoaderViewModel.class);
    }
    
    protected void a()
    {
      super.a();
      if (a.get() >= 0)
      {
        a.a();
        return;
      }
      ((LoaderManagerImpl.a)a.get(0)).cancel(true);
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    void b()
    {
      int j = a.get();
      int i = 0;
      while (i < j)
      {
        ((LoaderManagerImpl.a)a.get(i)).b();
        i += 1;
      }
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      if (a.get() > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Loaders:");
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("    ");
        localObject = ((StringBuilder)localObject).toString();
        if (a.get() >= 0) {
          return;
        }
        LoaderManagerImpl.a localA = (LoaderManagerImpl.a)a.get(0);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(a.toString(0));
        paramPrintWriter.print(": ");
        paramPrintWriter.println(localA.toString());
        localA.dump((String)localObject, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        throw new NullPointerException("Null throw statement replaced by Soot");
      }
    }
    
    static final class a
      implements ResultSet
    {
      a() {}
      
      public List getValue(Class paramClass)
      {
        return new LoaderManagerImpl.LoaderViewModel();
      }
    }
  }
  
  public static class a<D>
    extends j<D>
    implements b.a<D>
  {
    private h a;
    private final Bundle mArgs;
    private final int mId;
    private final b<D> mLoader;
    private b<D> o;
    private LoaderManagerImpl.b<D> x;
    
    public void a(Object paramObject)
    {
      super.a(paramObject);
      paramObject = o;
      if (paramObject == null) {
        return;
      }
      paramObject.add();
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    void b()
    {
      h localH = a;
      LoaderManagerImpl.b localB = x;
      if ((localH != null) && (localB != null))
      {
        super.b(localB);
        b(localH, localB);
      }
    }
    
    public void b(Context paramContext)
    {
      super.b(paramContext);
      a = null;
      x = null;
    }
    
    Loader cancel(boolean paramBoolean)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Destroying: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mLoader.cancel();
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mId=");
      paramPrintWriter.print(mId);
      paramPrintWriter.print(" mArgs=");
      paramPrintWriter.println(mArgs);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mLoader=");
      paramPrintWriter.println(mLoader);
      Loader localLoader = mLoader;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("  ");
      localLoader.dump(localStringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    protected void start()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Starting: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mLoader.registerOnLoadCanceledListener();
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    protected void stop()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Stopping: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mLoader.reset();
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(64);
      localStringBuilder.append("LoaderInfo{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" #");
      localStringBuilder.append(mId);
      localStringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(mLoader, localStringBuilder);
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
    }
  }
  
  static class b<D>
    implements k<D>
  {}
}
