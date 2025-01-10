package video.api.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({"InflateParams"})
public class f
{
  private static int b;
  private static int c = Color.parseColor("#FFFFFF");
  private static int k;
  private static int o;
  private static boolean p = true;
  private static final Typeface s;
  private static int t;
  private static int u;
  private static int y;
  private static Typeface z;
  
  static
  {
    b = Color.parseColor("#D50000");
    k = Color.parseColor("#3F51B5");
    o = Color.parseColor("#388E3C");
    u = Color.parseColor("#FFA900");
    t = Color.parseColor("#353A3E");
    s = Typeface.create("sans-serif-condensed", 0);
    z = s;
    y = 16;
  }
  
  public static Toast a(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    return a(paramContext, paramCharSequence, paramInt, null, false);
  }
  
  public static Toast a(Context paramContext, CharSequence paramCharSequence, int paramInt, Drawable paramDrawable, boolean paramBoolean)
  {
    return a(paramContext, paramCharSequence, paramDrawable, t, paramInt, paramBoolean, true);
  }
  
  public static Toast a(Context paramContext, CharSequence paramCharSequence, int paramInt, boolean paramBoolean)
  {
    return a(paramContext, paramCharSequence, i.a(paramContext, R.drawable.ic_check_white_48dp), o, paramInt, paramBoolean, true);
  }
  
  public static Toast a(Context paramContext, CharSequence paramCharSequence, Drawable paramDrawable, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Toast localToast = Toast.makeText(paramContext, null, paramInt2);
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.toast_layout, null);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.toast_icon);
    TextView localTextView = (TextView)localView.findViewById(R.id.toast_text);
    if (paramBoolean2) {
      paramContext = i.p(paramContext, paramInt1);
    } else {
      paramContext = i.a(paramContext, R.drawable.toast_frame);
    }
    i.a(localView, paramContext);
    if (paramBoolean1)
    {
      if (paramDrawable != null)
      {
        paramContext = paramDrawable;
        if (p) {
          paramContext = i.a(paramDrawable, c);
        }
        i.a(localImageView, paramContext);
      }
      else
      {
        throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
      }
    }
    else {
      localImageView.setVisibility(8);
    }
    localTextView.setText(paramCharSequence);
    localTextView.setTextColor(c);
    localTextView.setTypeface(z);
    localTextView.setTextSize(2, y);
    localToast.setView(localView);
    return localToast;
  }
  
  public static Toast add(Context paramContext, CharSequence paramCharSequence, int paramInt, boolean paramBoolean)
  {
    return a(paramContext, paramCharSequence, i.a(paramContext, R.drawable.ic_clear_white_48dp), b, paramInt, paramBoolean, true);
  }
  
  public static Toast b(Context paramContext, CharSequence paramCharSequence, int paramInt, boolean paramBoolean)
  {
    return a(paramContext, paramCharSequence, i.a(paramContext, R.drawable.ic_info_outline_white_48dp), k, paramInt, paramBoolean, true);
  }
  
  public static Toast d(Context paramContext, CharSequence paramCharSequence, int paramInt, boolean paramBoolean)
  {
    return a(paramContext, paramCharSequence, i.a(paramContext, R.drawable.ic_error_outline_white_48dp), u, paramInt, paramBoolean, true);
  }
}
