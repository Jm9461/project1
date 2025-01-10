package apps.afollestad.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.afollestad.materialdialogs.internal.MDRootLayout;

class DialogBase
  extends Dialog
  implements DialogInterface.OnShowListener
{
  private DialogInterface.OnShowListener mShowListener;
  protected MDRootLayout view;
  
  DialogBase(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  public View findViewById(int paramInt)
  {
    return view.findViewById(paramInt);
  }
  
  public void onShow(DialogInterface paramDialogInterface)
  {
    DialogInterface.OnShowListener localOnShowListener = mShowListener;
    if (localOnShowListener != null) {
      localOnShowListener.onShow(paramDialogInterface);
    }
  }
  
  public void setContentView(int paramInt)
  {
    throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
  }
  
  public void setContentView(View paramView)
  {
    throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    throw new IllegalAccessError("setContentView() is not supported in MaterialDialog. Specify a custom view in the Builder instead.");
  }
  
  public final void setOnShowListener(DialogInterface.OnShowListener paramOnShowListener)
  {
    mShowListener = paramOnShowListener;
  }
  
  final void setOnShowListenerInternal()
  {
    super.setOnShowListener(this);
  }
  
  final void setViewInternal(View paramView)
  {
    super.setContentView(paramView);
  }
}
