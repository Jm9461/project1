package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class FragmentController
{
  private final j<?> mHost;
  
  private FragmentController(FragmentHostCallback paramFragmentHostCallback)
  {
    mHost = paramFragmentHostCallback;
  }
  
  public static FragmentController createController(FragmentHostCallback paramFragmentHostCallback)
  {
    return new FragmentController(paramFragmentHostCallback);
  }
  
  public void attachHost(Fragment paramFragment)
  {
    FragmentHostCallback localFragmentHostCallback = mHost;
    mFragmentManager.attachController(localFragmentHostCallback, localFragmentHostCallback, paramFragment);
  }
  
  public void dispatchActivityCreated()
  {
    mHost.mFragmentManager.dispatchActivityCreated();
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    mHost.mFragmentManager.dispatchConfigurationChanged(paramConfiguration);
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    return mHost.mFragmentManager.dispatchContextItemSelected(paramMenuItem);
  }
  
  public void dispatchCreate()
  {
    mHost.mFragmentManager.dispatchCreate();
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    return mHost.mFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public void dispatchDestroy()
  {
    mHost.mFragmentManager.dispatchDestroy();
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    return mHost.mFragmentManager.dispatchOptionsItemSelected(paramMenuItem);
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    mHost.mFragmentManager.dump(paramMenu);
  }
  
  public void dispatchPause()
  {
    mHost.mFragmentManager.dispatchPause();
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    return mHost.mFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
  }
  
  public void dispatchReallyStop(boolean paramBoolean)
  {
    mHost.mFragmentManager.moveToState(paramBoolean);
  }
  
  public void dispatchResume()
  {
    mHost.mFragmentManager.dispatchResume();
  }
  
  public void dispatchStart()
  {
    mHost.mFragmentManager.dispatchStart();
  }
  
  public void dispatchStop()
  {
    mHost.mFragmentManager.dispatchStop();
  }
  
  public boolean execPendingActions()
  {
    return mHost.mFragmentManager.execPendingActions();
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    return mHost.mFragmentManager.findFragmentByWho(paramString);
  }
  
  public FragmentManager getSupportFragmentManager()
  {
    return mHost.getFragmentManagerImpl();
  }
  
  public void noteStateNotSaved()
  {
    mHost.mFragmentManager.restoreAllState();
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return mHost.mFragmentManager.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public void restoreAllState()
  {
    mHost.mFragmentManager.dump();
  }
  
  public void restoreAllState(Parcelable paramParcelable, List paramList)
  {
    mHost.mFragmentManager.restoreAllState(paramParcelable, paramList);
  }
  
  public void restoreAllState(boolean paramBoolean)
  {
    mHost.mFragmentManager.dump(paramBoolean);
  }
  
  public List retainNonConfig()
  {
    return mHost.mFragmentManager.onCreateView();
  }
  
  public Parcelable saveAllState()
  {
    return mHost.mFragmentManager.saveAllState();
  }
}
