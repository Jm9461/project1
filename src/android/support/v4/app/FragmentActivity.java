package android.support.v4.app;

import a.b.g.g.o;
import android.app.Activity;
import android.arch.lifecycle.Attribute;
import android.arch.lifecycle.Update;
import android.arch.lifecycle.d;
import android.arch.lifecycle.f;
import android.arch.lifecycle.m;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import org.org.jaxen.util.ClassWriter;

public class FragmentActivity
  extends g
  implements Update, ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompatApi23.RequestPermissionsRequestCodeValidator
{
  private Attribute a;
  boolean mCreated;
  final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
  final Handler mHandler = new SlidingDrawer.SlidingHandler(this);
  int mNextCandidateRequestIndex;
  o<String> mPendingFragmentActivityResults;
  boolean mRequestedPermissionsFromFragment;
  boolean mResumed;
  boolean mStartedActivityFromFragment;
  boolean mStopped = true;
  boolean this$0;
  
  public FragmentActivity() {}
  
  private static boolean a(FragmentManager paramFragmentManager, f paramF)
  {
    boolean bool1 = false;
    paramFragmentManager = paramFragmentManager.remove().iterator();
    while (paramFragmentManager.hasNext())
    {
      Object localObject = (Fragment)paramFragmentManager.next();
      if (localObject != null)
      {
        boolean bool2 = bool1;
        if (((Fragment)localObject).a().b().a(f.c))
        {
          o.d(paramF);
          bool2 = true;
        }
        localObject = ((Fragment)localObject).getChildAt();
        bool1 = bool2;
        if (localObject != null) {
          bool1 = bool2 | a((FragmentManager)localObject, paramF);
        }
      }
    }
    return bool1;
  }
  
  private int allocateRequestIndex(Fragment paramFragment)
  {
    if (mPendingFragmentActivityResults.get() < 65534)
    {
      while (mPendingFragmentActivityResults.put(mNextCandidateRequestIndex) >= 0) {
        mNextCandidateRequestIndex = ((mNextCandidateRequestIndex + 1) % 65534);
      }
      int i = mNextCandidateRequestIndex;
      mPendingFragmentActivityResults.put(i, mWho);
      mNextCandidateRequestIndex = ((mNextCandidateRequestIndex + 1) % 65534);
      return i;
    }
    paramFragment = new IllegalStateException("Too many pending Fragment activity results.");
    throw paramFragment;
  }
  
  private void d()
  {
    while (a(getSupportFragmentManager(), f.a)) {}
  }
  
  static void startActivityFromFragment(int paramInt)
  {
    if ((0xFFFF0000 & paramInt) == 0) {
      return;
    }
    throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
  }
  
  public d a()
  {
    return super.a();
  }
  
  public Attribute add()
  {
    if (getApplication() != null)
    {
      if (a == null)
      {
        NonConfigurationInstances localNonConfigurationInstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
        if (localNonConfigurationInstances != null) {
          a = a;
        }
        if (a == null) {
          a = new Attribute();
        }
      }
      return a;
    }
    throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
  }
  
  final View dispatchFragmentsOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return mFragments.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("Local FragmentActivity ");
    paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
    paramPrintWriter.println(" State:");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("  ");
    localObject = ((StringBuilder)localObject).toString();
    paramPrintWriter.print((String)localObject);
    paramPrintWriter.print("mCreated=");
    paramPrintWriter.print(mCreated);
    paramPrintWriter.print(" mResumed=");
    paramPrintWriter.print(mResumed);
    paramPrintWriter.print(" mStopped=");
    paramPrintWriter.print(mStopped);
    if (getApplication() != null) {
      Node.append(this).dump((String)localObject, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    mFragments.getSupportFragmentManager().dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public FragmentManager getSupportFragmentManager()
  {
    return mFragments.getSupportFragmentManager();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    mFragments.noteStateNotSaved();
    int i = paramInt1 >> 16;
    if (i != 0)
    {
      i -= 1;
      localObject = (String)mPendingFragmentActivityResults.remove(i);
      mPendingFragmentActivityResults.d(i);
      if (localObject == null)
      {
        Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
        return;
      }
      Fragment localFragment = mFragments.findFragmentByWho((String)localObject);
      if (localFragment == null)
      {
        paramIntent = new StringBuilder();
        paramIntent.append("Activity result no fragment exists for who: ");
        paramIntent.append((String)localObject);
        Log.w("FragmentActivity", paramIntent.toString());
        return;
      }
      localFragment.onCreateView(0xFFFF & paramInt1, paramInt2, paramIntent);
      return;
    }
    Object localObject = ActivityCompat.setFullScreen();
    if ((localObject != null) && (((CordovaInterfaceImpl)localObject).onActivityResult(this, paramInt1, paramInt2, paramIntent))) {
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    FragmentManager localFragmentManager = mFragments.getSupportFragmentManager();
    boolean bool = localFragmentManager.getFragment();
    if ((bool) && (Build.VERSION.SDK_INT <= 25)) {
      return;
    }
    if ((bool) || (!localFragmentManager.popBackStackImmediate())) {
      super.onBackPressed();
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    mFragments.noteStateNotSaved();
    mFragments.dispatchConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    Object localObject2 = mFragments;
    Object localObject1 = null;
    ((FragmentController)localObject2).attachHost(null);
    super.onCreate(paramBundle);
    localObject2 = (NonConfigurationInstances)getLastNonConfigurationInstance();
    Object localObject3;
    if (localObject2 != null)
    {
      localObject3 = a;
      if ((localObject3 != null) && (a == null)) {
        a = ((Attribute)localObject3);
      }
    }
    if (paramBundle != null)
    {
      localObject3 = paramBundle.getParcelable("android:support:fragments");
      FragmentController localFragmentController = mFragments;
      if (localObject2 != null) {
        localObject1 = fragments;
      }
      localFragmentController.restoreAllState((Parcelable)localObject3, (List)localObject1);
      if (paramBundle.containsKey("android:support:next_request_index"))
      {
        mNextCandidateRequestIndex = paramBundle.getInt("android:support:next_request_index");
        localObject1 = paramBundle.getIntArray("android:support:request_indicies");
        paramBundle = paramBundle.getStringArray("android:support:request_fragment_who");
        int i;
        if ((localObject1 != null) && (paramBundle != null) && (localObject1.length == paramBundle.length))
        {
          mPendingFragmentActivityResults = new ClassWriter(localObject1.length);
          i = 0;
        }
        while (i < localObject1.length)
        {
          mPendingFragmentActivityResults.put(localObject1[i], paramBundle[i]);
          i += 1;
          continue;
          Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
        }
      }
    }
    if (mPendingFragmentActivityResults == null)
    {
      mPendingFragmentActivityResults = new ClassWriter();
      mNextCandidateRequestIndex = 0;
    }
    mFragments.dispatchCreate();
  }
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    if (paramInt == 0) {
      return super.onCreatePanelMenu(paramInt, paramMenu) | mFragments.dispatchCreateOptionsMenu(paramMenu, getMenuInflater());
    }
    return super.onCreatePanelMenu(paramInt, paramMenu);
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView = dispatchFragmentsOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    if (localView == null) {
      return super.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
    }
    return localView;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView2 = dispatchFragmentsOnCreateView(null, paramString, paramContext, paramAttributeSet);
    View localView1 = localView2;
    if (localView2 == null) {
      localView1 = super.onCreateView(paramString, paramContext, paramAttributeSet);
    }
    return localView1;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if ((a != null) && (!isChangingConfigurations())) {
      a.a();
    }
    mFragments.dispatchDestroy();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    mFragments.restoreAllState();
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (super.onMenuItemSelected(paramInt, paramMenuItem)) {
      return true;
    }
    if (paramInt != 0)
    {
      if (paramInt != 6) {
        return false;
      }
      return mFragments.dispatchContextItemSelected(paramMenuItem);
    }
    return mFragments.dispatchOptionsItemSelected(paramMenuItem);
  }
  
  public void onMultiWindowModeChanged(boolean paramBoolean)
  {
    mFragments.restoreAllState(paramBoolean);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    mFragments.noteStateNotSaved();
  }
  
  public void onPanelClosed(int paramInt, Menu paramMenu)
  {
    if (paramInt == 0) {
      mFragments.dispatchOptionsMenuClosed(paramMenu);
    }
    super.onPanelClosed(paramInt, paramMenu);
  }
  
  protected void onPause()
  {
    super.onPause();
    mResumed = false;
    if (mHandler.hasMessages(2))
    {
      mHandler.removeMessages(2);
      onResumeFragments();
    }
    mFragments.dispatchPause();
  }
  
  public void onPictureInPictureModeChanged(boolean paramBoolean)
  {
    mFragments.dispatchReallyStop(paramBoolean);
  }
  
  protected void onPostResume()
  {
    super.onPostResume();
    mHandler.removeMessages(2);
    onResumeFragments();
    mFragments.execPendingActions();
  }
  
  protected boolean onPrepareOptionsPanel(View paramView, Menu paramMenu)
  {
    return super.onPreparePanel(0, paramView, paramMenu);
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    if ((paramInt == 0) && (paramMenu != null)) {
      return onPrepareOptionsPanel(paramView, paramMenu) | mFragments.dispatchPrepareOptionsMenu(paramMenu);
    }
    return super.onPreparePanel(paramInt, paramView, paramMenu);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    mFragments.noteStateNotSaved();
    int i = paramInt >> 16 & 0xFFFF;
    if (i != 0)
    {
      i -= 1;
      String str = (String)mPendingFragmentActivityResults.remove(i);
      mPendingFragmentActivityResults.d(i);
      if (str == null)
      {
        Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
        return;
      }
      Fragment localFragment = mFragments.findFragmentByWho(str);
      if (localFragment == null)
      {
        paramArrayOfString = new StringBuilder();
        paramArrayOfString.append("Activity result no fragment exists for who: ");
        paramArrayOfString.append(str);
        Log.w("FragmentActivity", paramArrayOfString.toString());
        return;
      }
      localFragment.onRequestPermissionsResult(0xFFFF & paramInt, paramArrayOfString, paramArrayOfInt);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    mHandler.sendEmptyMessage(2);
    mResumed = true;
    mFragments.execPendingActions();
  }
  
  protected void onResumeFragments()
  {
    mFragments.dispatchResume();
  }
  
  public Object onRetainCustomNonConfigurationInstance()
  {
    return null;
  }
  
  public final Object onRetainNonConfigurationInstance()
  {
    Object localObject = onRetainCustomNonConfigurationInstance();
    List localList = mFragments.retainNonConfig();
    if ((localList == null) && (a == null) && (localObject == null)) {
      return null;
    }
    localObject = new NonConfigurationInstances();
    a = a;
    fragments = localList;
    return localObject;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    d();
    Object localObject = mFragments.saveAllState();
    if (localObject != null) {
      paramBundle.putParcelable("android:support:fragments", (Parcelable)localObject);
    }
    if (mPendingFragmentActivityResults.get() > 0)
    {
      paramBundle.putInt("android:support:next_request_index", mNextCandidateRequestIndex);
      localObject = new int[mPendingFragmentActivityResults.get()];
      String[] arrayOfString = new String[mPendingFragmentActivityResults.get()];
      int i = 0;
      while (i < mPendingFragmentActivityResults.get())
      {
        localObject[i] = mPendingFragmentActivityResults.toString(i);
        arrayOfString[i] = ((String)mPendingFragmentActivityResults.get(i));
        i += 1;
      }
      paramBundle.putIntArray("android:support:request_indicies", (int[])localObject);
      paramBundle.putStringArray("android:support:request_fragment_who", arrayOfString);
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    mStopped = false;
    if (!mCreated)
    {
      mCreated = true;
      mFragments.dispatchActivityCreated();
    }
    mFragments.noteStateNotSaved();
    mFragments.execPendingActions();
    mFragments.dispatchStart();
  }
  
  public void onStateNotSaved()
  {
    mFragments.noteStateNotSaved();
  }
  
  protected void onStop()
  {
    super.onStop();
    mStopped = true;
    d();
    mFragments.dispatchStop();
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    if ((!mStartedActivityFromFragment) && (paramInt != -1)) {
      startActivityFromFragment(paramInt);
    }
    super.startActivityForResult(paramIntent, paramInt);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    if ((!mStartedActivityFromFragment) && (paramInt != -1)) {
      startActivityFromFragment(paramInt);
    }
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
  
  public void startActivityFromFragment(Fragment paramFragment) {}
  
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    mStartedActivityFromFragment = true;
    if (paramInt == -1) {}
    try
    {
      ActivityCompat.startActivityForResult(this, paramIntent, -1, paramBundle);
      mStartedActivityFromFragment = false;
      return;
    }
    catch (Throwable paramFragment)
    {
      int i;
      mStartedActivityFromFragment = false;
      throw paramFragment;
    }
    startActivityFromFragment(paramInt);
    i = allocateRequestIndex(paramFragment);
    ActivityCompat.startActivityForResult(this, paramIntent, (i + 1 << 16) + (0xFFFF & paramInt), paramBundle);
    mStartedActivityFromFragment = false;
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((!this$0) && (paramInt1 != -1)) {
      startActivityFromFragment(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle)
  {
    if ((!this$0) && (paramInt1 != -1)) {
      startActivityFromFragment(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
  
  public void supportInvalidateOptionsMenu()
  {
    invalidateOptionsMenu();
  }
  
  public final void validateRequestPermissionsRequestCode(int paramInt)
  {
    if ((!mRequestedPermissionsFromFragment) && (paramInt != -1)) {
      startActivityFromFragment(paramInt);
    }
  }
  
  class HostCallbacks
    extends j<g>
  {
    public HostCallbacks()
    {
      super();
    }
    
    public View findViewById(int paramInt)
    {
      return FragmentActivity.this.findViewById(paramInt);
    }
    
    public void onDump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    
    public LayoutInflater onGetLayoutInflater()
    {
      return getLayoutInflater().cloneInContext(FragmentActivity.this);
    }
    
    public int onGetWindowAnimations()
    {
      Window localWindow = getWindow();
      if (localWindow == null) {
        return 0;
      }
      return getAttributeswindowAnimations;
    }
    
    public boolean onHasView()
    {
      Window localWindow = getWindow();
      return (localWindow != null) && (localWindow.peekDecorView() != null);
    }
    
    public boolean onHasWindowAnimations()
    {
      return getWindow() != null;
    }
    
    public boolean onShouldSaveFragmentState(Fragment paramFragment)
    {
      return isFinishing() ^ true;
    }
    
    public void onStartActivityFromFragment(Fragment paramFragment)
    {
      startActivityFromFragment(paramFragment);
    }
    
    public void onStartActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle)
    {
      startActivityFromFragment(paramFragment, paramIntent, paramInt, paramBundle);
    }
    
    public void onSupportInvalidateOptionsMenu()
    {
      supportInvalidateOptionsMenu();
    }
  }
  
  final class NonConfigurationInstances
  {
    Attribute a;
    List fragments;
    
    NonConfigurationInstances() {}
  }
}
