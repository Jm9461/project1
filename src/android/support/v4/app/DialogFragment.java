package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public class DialogFragment
  extends Fragment
  implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener
{
  int mBackStackId = -1;
  boolean mCancelable = true;
  Dialog mDialog;
  boolean mDismissed;
  boolean mShownByMe;
  boolean mShowsDialog = true;
  int mStyle = 0;
  int mTheme = 0;
  boolean mViewDestroyed;
  
  public DialogFragment() {}
  
  public void dismiss()
  {
    dismissInternal(false);
  }
  
  void dismissInternal(boolean paramBoolean)
  {
    if (mDismissed) {
      return;
    }
    mDismissed = true;
    mShownByMe = false;
    Object localObject = mDialog;
    if (localObject != null) {
      ((Dialog)localObject).dismiss();
    }
    mViewDestroyed = true;
    if (mBackStackId >= 0)
    {
      get().popBackStack(mBackStackId, 1);
      mBackStackId = -1;
      return;
    }
    localObject = get().beginTransaction();
    ((FragmentTransaction)localObject).remove(this);
    if (paramBoolean)
    {
      ((FragmentTransaction)localObject).commitAllowingStateLoss();
      return;
    }
    ((FragmentTransaction)localObject).commit();
  }
  
  public Dialog getDialog()
  {
    return mDialog;
  }
  
  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    if (!mShowsDialog) {
      return super.getLayoutInflater(paramBundle);
    }
    mDialog = onCreateDialog(paramBundle);
    paramBundle = mDialog;
    if (paramBundle != null)
    {
      setupDialog(paramBundle, mStyle);
      return (LayoutInflater)mDialog.getContext().getSystemService("layout_inflater");
    }
    return (LayoutInflater)mHost.getContext().getSystemService("layout_inflater");
  }
  
  public int getTheme()
  {
    return mTheme;
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (!mShowsDialog) {
      return;
    }
    Object localObject = getValue();
    if (localObject != null) {
      if (((View)localObject).getParent() == null) {
        mDialog.setContentView((View)localObject);
      } else {
        throw new IllegalStateException("DialogFragment can not be attached to a container view");
      }
    }
    localObject = getActivity();
    if (localObject != null) {
      mDialog.setOwnerActivity((Activity)localObject);
    }
    mDialog.setCancelable(mCancelable);
    mDialog.setOnCancelListener(this);
    mDialog.setOnDismissListener(this);
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getBundle("android:savedDialogState");
      if (paramBundle != null) {
        mDialog.onRestoreInstanceState(paramBundle);
      }
    }
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    if (!mShownByMe) {
      mDismissed = false;
    }
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    boolean bool;
    if (i == 0) {
      bool = true;
    } else {
      bool = false;
    }
    mShowsDialog = bool;
    if (paramBundle != null)
    {
      mStyle = paramBundle.getInt("android:style", 0);
      mTheme = paramBundle.getInt("android:theme", 0);
      mCancelable = paramBundle.getBoolean("android:cancelable", true);
      mShowsDialog = paramBundle.getBoolean("android:showsDialog", mShowsDialog);
      mBackStackId = paramBundle.getInt("android:backStackId", -1);
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return new Dialog(getActivity(), getTheme());
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    Dialog localDialog = mDialog;
    if (localDialog != null)
    {
      mViewDestroyed = true;
      localDialog.dismiss();
      mDialog = null;
    }
  }
  
  public void onDetach()
  {
    super.onDetach();
    if ((!mShownByMe) && (!mDismissed)) {
      mDismissed = true;
    }
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (!mViewDestroyed) {
      dismissInternal(true);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    Object localObject = mDialog;
    if (localObject != null)
    {
      localObject = ((Dialog)localObject).onSaveInstanceState();
      if (localObject != null) {
        paramBundle.putBundle("android:savedDialogState", (Bundle)localObject);
      }
    }
    int i = mStyle;
    if (i != 0) {
      paramBundle.putInt("android:style", i);
    }
    i = mTheme;
    if (i != 0) {
      paramBundle.putInt("android:theme", i);
    }
    boolean bool = mCancelable;
    if (!bool) {
      paramBundle.putBoolean("android:cancelable", bool);
    }
    bool = mShowsDialog;
    if (!bool) {
      paramBundle.putBoolean("android:showsDialog", bool);
    }
    i = mBackStackId;
    if (i != -1) {
      paramBundle.putInt("android:backStackId", i);
    }
  }
  
  public void onStart()
  {
    super.onStart();
    Dialog localDialog = mDialog;
    if (localDialog != null)
    {
      mViewDestroyed = false;
      localDialog.show();
    }
  }
  
  public void onStop()
  {
    super.onStop();
    Dialog localDialog = mDialog;
    if (localDialog != null) {
      localDialog.hide();
    }
  }
  
  public void setupDialog(Dialog paramDialog, int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 2))
    {
      if (paramInt != 3) {
        return;
      }
      paramDialog.getWindow().addFlags(24);
    }
    paramDialog.requestWindowFeature(1);
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    mDismissed = false;
    mShownByMe = true;
    paramFragmentManager = paramFragmentManager.beginTransaction();
    paramFragmentManager.add(this, paramString);
    paramFragmentManager.commit();
  }
}
