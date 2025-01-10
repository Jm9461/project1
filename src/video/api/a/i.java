package video.api.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.view.View;

final class i
{
  static Drawable a(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramContext.getDrawable(paramInt);
    }
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  static Drawable a(Drawable paramDrawable, int paramInt)
  {
    paramDrawable.setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
    return paramDrawable;
  }
  
  static void a(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
  
  static Drawable p(Context paramContext, int paramInt)
  {
    paramContext = (NinePatchDrawable)a(paramContext, R.drawable.toast_frame);
    a(paramContext, paramInt);
    return paramContext;
  }
}
