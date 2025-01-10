package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import com.lawyer_smartCalendar.a.k.a;
import com.lawyer_smartCalendar.d.c;
import java.util.List;
import java.util.Locale;

public class d
  extends RecyclerView.g<k.a>
{
  private Context a;
  private com.lawyer_smartCalendar.utils.f b;
  com.lawyer_smartCalendar.ui.d e;
  private GravityEnum f = GravityEnum.END;
  private List<c> x;
  
  public d(Context paramContext, com.lawyer_smartCalendar.ui.d paramD, List paramList)
  {
    setHasStableIds(true);
    a = paramContext;
    x = paramList;
    e = paramD;
    b = new com.lawyer_smartCalendar.utils.f(a);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0)
    {
      f = GravityEnum.END;
      return;
    }
    f = GravityEnum.START;
  }
  
  public int a()
  {
    return x.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Attribute a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Attribute(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492949, paramViewGroup, false));
  }
  
  public void a(Attribute paramAttribute, int paramInt)
  {
    type.setText(((com.lawyer_smartCalendar.data.f)x.get(paramInt)).getValue());
    char[] arrayOfChar = ((com.lawyer_smartCalendar.data.f)x.get(paramInt)).getValue().toCharArray();
    TextView localTextView = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(arrayOfChar[0]);
    localStringBuilder.append("");
    localTextView.setText(localStringBuilder.toString());
    type.setTypeface(b.setText());
    a.setTypeface(b.get());
    if (((com.lawyer_smartCalendar.data.f)x.get(paramInt)).f().equals("real"))
    {
      if (!((com.lawyer_smartCalendar.data.f)x.get(paramInt)).getText().equals("")) {
        b.setText(((com.lawyer_smartCalendar.data.f)x.get(paramInt)).getText());
      } else {
        b.setText("-");
      }
    }
    else if (!((com.lawyer_smartCalendar.data.f)x.get(paramInt)).a().equals("")) {
      b.setText(((com.lawyer_smartCalendar.data.f)x.get(paramInt)).a());
    } else {
      b.setText("-");
    }
    b.setTypeface(b.setText());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
