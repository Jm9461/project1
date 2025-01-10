package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.org.jaxen.util.ClassReader;

public abstract class FragmentHostCallback<E>
  extends h
{
  private final Activity mActivity;
  private final Context mContext;
  final FragmentManagerImpl mFragmentManager = new FragmentManagerImpl();
  private final Handler mHandler;
  
  FragmentHostCallback(Activity paramActivity, Context paramContext, Handler paramHandler, int paramInt)
  {
    mActivity = paramActivity;
    ClassReader.getItem(paramContext, "context == null");
    mContext = ((Context)paramContext);
    ClassReader.getItem(paramHandler, "handler == null");
    mHandler = ((Handler)paramHandler);
  }
  
  FragmentHostCallback(FragmentActivity paramFragmentActivity)
  {
    this(paramFragmentActivity, paramFragmentActivity, mHandler, 0);
  }
  
  Activity getActivity()
  {
    return mActivity;
  }
  
  Context getContext()
  {
    return mContext;
  }
  
  FragmentManagerImpl getFragmentManagerImpl()
  {
    return mFragmentManager;
  }
  
  Handler getHandler()
  {
    return mHandler;
  }
  
  public abstract void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract LayoutInflater onGetLayoutInflater();
  
  public abstract int onGetWindowAnimations();
  
  public abstract boolean onHasWindowAnimations();
  
  public abstract boolean onShouldSaveFragmentState(Fragment paramFragment);
  
  abstract void onStartActivityFromFragment(Fragment paramFragment);
  
  public abstract void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle);
  
  public abstract void onSupportInvalidateOptionsMenu();
}
