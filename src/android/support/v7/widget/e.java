package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import org.org.v4.content.R.dimen;
import org.org.v4.content.R.id;
import org.org.v4.content.R.layout;
import org.org.v4.content.R.style;

class e
{
  private final View a;
  private final int[] b = new int[2];
  private final Context c;
  private final WindowManager.LayoutParams d = new WindowManager.LayoutParams();
  private final TextView e;
  private final Rect f = new Rect();
  private final int[] h = new int[2];
  
  e(Context paramContext)
  {
    c = paramContext;
    a = LayoutInflater.from(c).inflate(R.layout.abc_tooltip, null);
    e = ((TextView)a.findViewById(R.id.message));
    d.setTitle(getClass().getSimpleName());
    d.packageName = c.getPackageName();
    paramContext = d;
    type = 1002;
    width = -2;
    height = -2;
    format = -3;
    windowAnimations = R.style.Animation_AppCompat_Tooltip;
    flags = 24;
  }
  
  private static View a(View paramView)
  {
    View localView = paramView.getRootView();
    ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
    if (((localLayoutParams instanceof WindowManager.LayoutParams)) && (type == 2)) {
      return localView;
    }
    for (paramView = paramView.getContext(); (paramView instanceof ContextWrapper); paramView = ((ContextWrapper)paramView).getBaseContext()) {
      if ((paramView instanceof Activity)) {
        return ((Activity)paramView).getWindow().getDecorView();
      }
    }
    return localView;
  }
  
  private void show(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, WindowManager.LayoutParams paramLayoutParams)
  {
    token = paramView.getApplicationWindowToken();
    int i = c.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
    if (paramView.getWidth() < i) {
      paramInt1 = paramView.getWidth() / 2;
    }
    if (paramView.getHeight() >= i)
    {
      i = c.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
      j = paramInt2 + i;
      i = paramInt2 - i;
      paramInt2 = j;
    }
    else
    {
      paramInt2 = paramView.getHeight();
      i = 0;
    }
    gravity = 49;
    Object localObject1 = c.getResources();
    if (paramBoolean) {
      j = R.dimen.tooltip_y_offset_touch;
    } else {
      j = R.dimen.tooltip_y_offset_non_touch;
    }
    int k = ((Resources)localObject1).getDimensionPixelOffset(j);
    localObject1 = a(paramView);
    if (localObject1 == null)
    {
      Log.e("TooltipPopup", "Cannot find app view");
      return;
    }
    ((View)localObject1).getWindowVisibleDisplayFrame(f);
    Object localObject2 = f;
    if ((left < 0) && (top < 0))
    {
      localObject2 = c.getResources();
      j = ((Resources)localObject2).getIdentifier("status_bar_height", "dimen", "android");
      if (j != 0) {
        j = ((Resources)localObject2).getDimensionPixelSize(j);
      } else {
        j = 0;
      }
      localObject2 = ((Resources)localObject2).getDisplayMetrics();
      f.set(0, j, widthPixels, heightPixels);
    }
    ((View)localObject1).getLocationOnScreen(b);
    paramView.getLocationOnScreen(h);
    paramView = h;
    int j = paramView[0];
    localObject2 = b;
    paramView[0] = (j - localObject2[0]);
    paramView[1] -= localObject2[1];
    x = (paramView[0] + paramInt1 - ((View)localObject1).getWidth() / 2);
    paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    a.measure(paramInt1, paramInt1);
    paramInt1 = a.getMeasuredHeight();
    paramView = h;
    i = paramView[1] + i - k - paramInt1;
    paramInt2 = paramView[1] + paramInt2 + k;
    if (paramBoolean)
    {
      if (i >= 0)
      {
        y = i;
        return;
      }
      y = paramInt2;
      return;
    }
    if (paramInt2 + paramInt1 <= f.height())
    {
      y = paramInt2;
      return;
    }
    y = i;
  }
  
  void a()
  {
    if (!b()) {
      return;
    }
    ((WindowManager)c.getSystemService("window")).removeView(a);
  }
  
  void a(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, CharSequence paramCharSequence)
  {
    if (b()) {
      a();
    }
    e.setText(paramCharSequence);
    show(paramView, paramInt1, paramInt2, paramBoolean, d);
    ((WindowManager)c.getSystemService("window")).addView(a, d);
  }
  
  boolean b()
  {
    return a.getParent() != null;
  }
}
