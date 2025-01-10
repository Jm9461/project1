package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import org.org.android.R.attr;
import org.org.android.R.id;
import org.org.android.R.layout;
import org.org.android.R.style;

public class Dialog
  extends AppCompatDialog
{
  boolean f = true;
  private boolean g = true;
  private BottomSheetBehavior.c h = new Plot(this);
  private boolean i;
  private BottomSheetBehavior<FrameLayout> this$0;
  
  public Dialog(Context paramContext)
  {
    this(paramContext, 0);
  }
  
  public Dialog(Context paramContext, int paramInt)
  {
    super(paramContext, getThemeResId(paramContext, paramInt));
    supportRequestWindowFeature(1);
  }
  
  private static int getThemeResId(Context paramContext, int paramInt)
  {
    int j = paramInt;
    if (paramInt == 0)
    {
      TypedValue localTypedValue = new TypedValue();
      if (paramContext.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, localTypedValue, true)) {
        return resourceId;
      }
      j = R.style.Theme_Design_Light_BottomSheetDialog;
    }
    return j;
  }
  
  private View wrapInBottomSheet(int paramInt, View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    FrameLayout localFrameLayout = (FrameLayout)View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
    CoordinatorLayout localCoordinatorLayout = (CoordinatorLayout)localFrameLayout.findViewById(R.id.coordinator);
    View localView = paramView;
    if (paramInt != 0)
    {
      localView = paramView;
      if (paramView == null) {
        localView = getLayoutInflater().inflate(paramInt, localCoordinatorLayout, false);
      }
    }
    paramView = (FrameLayout)localCoordinatorLayout.findViewById(R.id.design_bottom_sheet);
    this$0 = BottomSheetBehavior.from(paramView);
    this$0.onRestoreInstanceState(h);
    this$0.put(f);
    if (paramLayoutParams == null) {
      paramView.addView(localView);
    } else {
      paramView.addView(localView, paramLayoutParams);
    }
    localCoordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new BottomSheetDialog.1(this));
    ViewCompat.setAccessibilityDelegate(paramView, new RecyclerViewAccessibilityDelegate.1(this));
    paramView.setOnTouchListener(new Main.25(this));
    return localFrameLayout;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getWindow();
    if (paramBundle != null)
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramBundle.clearFlags(67108864);
        paramBundle.addFlags(Integer.MIN_VALUE);
      }
      paramBundle.setLayout(-1, -1);
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    BottomSheetBehavior localBottomSheetBehavior = this$0;
    if ((localBottomSheetBehavior != null) && (localBottomSheetBehavior.getState() == 5)) {
      this$0.reset(4);
    }
  }
  
  public void setCancelable(boolean paramBoolean)
  {
    super.setCancelable(paramBoolean);
    if (f != paramBoolean)
    {
      f = paramBoolean;
      BottomSheetBehavior localBottomSheetBehavior = this$0;
      if (localBottomSheetBehavior != null) {
        localBottomSheetBehavior.put(paramBoolean);
      }
    }
  }
  
  public void setCanceledOnTouchOutside(boolean paramBoolean)
  {
    super.setCanceledOnTouchOutside(paramBoolean);
    if ((paramBoolean) && (!f)) {
      f = true;
    }
    g = paramBoolean;
    i = true;
  }
  
  public void setContentView(int paramInt)
  {
    super.setContentView(wrapInBottomSheet(paramInt, null, null));
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(wrapInBottomSheet(0, paramView, null));
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(wrapInBottomSheet(0, paramView, paramLayoutParams));
  }
  
  boolean update()
  {
    if (!i)
    {
      TypedArray localTypedArray = getContext().obtainStyledAttributes(new int[] { 16843611 });
      g = localTypedArray.getBoolean(0, true);
      localTypedArray.recycle();
      i = true;
    }
    return g;
  }
}
