package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView.g;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import com.lawyer_smartCalendar.d.j;
import com.lawyer_smartCalendar.data.Frame;
import com.lawyer_smartCalendar.ui.DialerFragment;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.f;
import com.lawyer_smartCalendar.utils.h;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ClassWriter
  extends RecyclerView.g<com.lawyer_smartCalendar.a.f0.a>
{
  public Context a;
  public DialerFragment b;
  public List<j> c;
  private GravityEnum e;
  private h g;
  
  public ClassWriter(Context paramContext, DialerFragment paramDialerFragment, List paramList)
  {
    new HashMap();
    e = GravityEnum.END;
    a = paramContext;
    c = paramList;
    b = paramDialerFragment;
    new f(a);
    Calendar.getInstance();
    g = new h(a);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0)
    {
      e = GravityEnum.END;
      return;
    }
    e = GravityEnum.START;
  }
  
  public int a()
  {
    return c.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public f0.a a(ViewGroup paramViewGroup, int paramInt)
  {
    return new f0.a(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493046, paramViewGroup, false));
  }
  
  public void a(f0.a paramA, int paramInt)
  {
    Object localObject = new DecimalFormat("#,###,###,###,###,###,###");
    d.setTypeface(new f(a).get());
    d.setTextColor(Color.parseColor(d.getValue()));
    header.setTypeface(new f(a).setText());
    TextView localTextView = header;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(((Frame)c.get(paramInt)).d());
    localStringBuilder.append("");
    localTextView.setText(localStringBuilder.toString());
    a.setTextColor(Color.parseColor(d.getValue()));
    a.setTypeface(new f(a).get());
    localTextView = e;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(((DecimalFormat)localObject).format(((Frame)c.get(paramInt)).getValue()));
    localStringBuilder.append(" ?????");
    localTextView.setText(localStringBuilder.toString());
    p.setTypeface(new f(a).get());
    p.setTextColor(Color.parseColor(d.getValue()));
    localTextView = t;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(((DecimalFormat)localObject).format(((Frame)c.get(paramInt)).get()));
    localStringBuilder.append(" ?????");
    localTextView.setText(localStringBuilder.toString());
    f.setTypeface(new f(a).get());
    f.setTextColor(Color.parseColor(d.getValue()));
    localTextView = c;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(((DecimalFormat)localObject).format(((Frame)c.get(paramInt)).b()));
    localStringBuilder.append(" ?????");
    localTextView.setText(localStringBuilder.toString());
    if (!((Frame)c.get(paramInt)).f().equals(""))
    {
      paramA = g;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(((Frame)c.get(paramInt)).f());
      ((StringBuilder)localObject).append("");
      paramA.setText(((StringBuilder)localObject).toString());
      return;
    }
    g.setVisibility(8);
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
