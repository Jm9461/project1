package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import org.org.v4.content.R.attr;

public class e
  extends AppCompatDialog
  implements DialogInterface
{
  final AlertController mAlert = new AlertController(getContext(), this, getWindow());
  
  protected e(Context paramContext, int paramInt)
  {
    super(paramContext, a(paramContext, paramInt));
  }
  
  static int a(Context paramContext, int paramInt)
  {
    if ((paramInt >>> 24 & 0xFF) >= 1) {
      return paramInt;
    }
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(R.attr.alertDialogTheme, localTypedValue, true);
    return resourceId;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mAlert.installContent();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mAlert.onKeyDown(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mAlert.onKeyUp(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    super.setTitle(paramCharSequence);
    mAlert.setTitle(paramCharSequence);
  }
  
  public class a
  {
    private final int a;
    private final AlertController.f b;
    
    public a()
    {
      this(e.a(this$1, 0));
    }
    
    public a(int paramInt)
    {
      b = new AlertController.f(new ContextThemeWrapper(this$1, e.a(this$1, paramInt)));
      a = paramInt;
    }
    
    public a a(Drawable paramDrawable)
    {
      b.mIcon = paramDrawable;
      return this;
    }
    
    public a a(ListAdapter paramListAdapter, DialogInterface.OnClickListener paramOnClickListener)
    {
      AlertController.f localF = b;
      mAdapter = paramListAdapter;
      mOnClickListener = paramOnClickListener;
      return this;
    }
    
    public e a()
    {
      e localE = new e(b.this$0, a);
      b.apply(mAlert);
      localE.setCancelable(b.c);
      if (b.c) {
        localE.setCanceledOnTouchOutside(true);
      }
      localE.setOnCancelListener(b.p);
      localE.setOnDismissListener(b.q);
      DialogInterface.OnKeyListener localOnKeyListener = b.r;
      if (localOnKeyListener != null) {
        localE.setOnKeyListener(localOnKeyListener);
      }
      return localE;
    }
    
    public a b(DialogInterface.OnKeyListener paramOnKeyListener)
    {
      b.r = paramOnKeyListener;
      return this;
    }
    
    public a b(View paramView)
    {
      b.mCustomTitleView = paramView;
      return this;
    }
    
    public a b(CharSequence paramCharSequence)
    {
      b.mTitle = paramCharSequence;
      return this;
    }
    
    public Context getCount()
    {
      return b.this$0;
    }
  }
}
