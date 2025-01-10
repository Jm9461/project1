package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class BaseFragment
  extends Fragment
{
  private Class mActivity;
  
  public BaseFragment() {}
  
  private void end(Class paramClass)
  {
    if (paramClass != null) {
      paramClass.writeHead();
    }
  }
  
  private void i(c paramC)
  {
    Object localObject = getActivity();
    if ((localObject instanceof a))
    {
      ((a)localObject).a().toString(paramC);
      return;
    }
    if ((localObject instanceof h))
    {
      localObject = ((h)localObject).a();
      if ((localObject instanceof m)) {
        ((m)localObject).toString(paramC);
      }
    }
  }
  
  private void reset(Class paramClass)
  {
    if (paramClass != null) {
      paramClass.LogD();
    }
  }
  
  public static void showDialog(Activity paramActivity)
  {
    paramActivity = paramActivity.getFragmentManager();
    if (paramActivity.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null)
    {
      paramActivity.beginTransaction().add(new BaseFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
      paramActivity.executePendingTransactions();
    }
  }
  
  private void showDialog(Class paramClass)
  {
    if (paramClass != null) {
      paramClass.show();
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    end(mActivity);
    i(c.ON_CREATE);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    i(c.ON_DESTROY);
    mActivity = null;
  }
  
  public void onPause()
  {
    super.onPause();
    i(c.ON_PAUSE);
  }
  
  public void onResume()
  {
    super.onResume();
    showDialog(mActivity);
    i(c.ON_RESUME);
  }
  
  public void onStart()
  {
    super.onStart();
    reset(mActivity);
    i(c.ON_START);
  }
  
  public void onStop()
  {
    super.onStop();
    i(c.ON_STOP);
  }
}
