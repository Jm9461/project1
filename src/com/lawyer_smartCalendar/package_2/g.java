package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import com.lawyer_smartCalendar.a.a.a;
import com.lawyer_smartCalendar.d.a;
import com.lawyer_smartCalendar.data.i;
import com.lawyer_smartCalendar.ui.WebViewFragment;
import com.lawyer_smartCalendar.utils.f;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;
import java.util.Locale;

public class g
  extends RecyclerView.g<a.a>
{
  public Context a;
  public WebViewFragment b;
  private h c;
  private GravityEnum d = GravityEnum.END;
  public List<a> f;
  
  public g(Context paramContext, WebViewFragment paramWebViewFragment, List paramList)
  {
    a = paramContext;
    f = paramList;
    b = paramWebViewFragment;
    new f(a);
    c = new h(a);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0)
    {
      d = GravityEnum.END;
      return;
    }
    d = GravityEnum.START;
  }
  
  public int a()
  {
    return f.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public MethodWriter a(ViewGroup paramViewGroup, int paramInt)
  {
    return new MethodWriter(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492893, paramViewGroup, false));
  }
  
  public void a(MethodWriter paramMethodWriter, int paramInt)
  {
    c.setEnabled();
    Object localObject1 = c;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((i)f.get(paramInt)).size());
    ((StringBuilder)localObject2).append("");
    localObject1 = ((h)localObject1).remove(((StringBuilder)localObject2).toString());
    localObject2 = c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(((i)f.get(paramInt)).a());
    localStringBuilder.append("");
    localObject2 = ((h)localObject2).getCount(localStringBuilder.toString());
    c.close();
    c.setTypeface(new f(a).get());
    d.setTypeface(new f(a).setText());
    d.setText((CharSequence)localObject1);
    f.setTypeface(new f(a).get());
    e.setTypeface(new f(a).setText());
    e.setText((CharSequence)localObject2);
    B.setTypeface(new f(a).get());
    g.setTypeface(new f(a).setText());
    g.setText(((i)f.get(paramInt)).get());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
