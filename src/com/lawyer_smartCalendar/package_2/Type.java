package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import com.lawyer_smartCalendar.a.l.a;
import com.lawyer_smartCalendar.d.c;
import java.util.List;
import java.util.Locale;

public class Type
  extends RecyclerView.g<l.a>
{
  private Context a;
  private List<c> c;
  private com.lawyer_smartCalendar.utils.f d;
  private GravityEnum header = GravityEnum.END;
  
  public Type(Context paramContext, List paramList)
  {
    setHasStableIds(true);
    a = paramContext;
    c = paramList;
    d = new com.lawyer_smartCalendar.utils.f(a);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0)
    {
      header = GravityEnum.END;
      return;
    }
    header = GravityEnum.START;
  }
  
  public int a()
  {
    return c.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Label a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Label(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492949, paramViewGroup, false));
  }
  
  public void a(Label paramLabel, int paramInt)
  {
    d.setText(((com.lawyer_smartCalendar.data.f)c.get(paramInt)).getValue());
    char[] arrayOfChar = ((com.lawyer_smartCalendar.data.f)c.get(paramInt)).getValue().toCharArray();
    TextView localTextView = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(arrayOfChar[0]);
    localStringBuilder.append("");
    localTextView.setText(localStringBuilder.toString());
    d.setTypeface(d.setText());
    a.setTypeface(d.get());
    if (((com.lawyer_smartCalendar.data.f)c.get(paramInt)).f().equals("real"))
    {
      if (!((com.lawyer_smartCalendar.data.f)c.get(paramInt)).getText().equals("")) {
        e.setText(((com.lawyer_smartCalendar.data.f)c.get(paramInt)).getText());
      } else {
        e.setText("-");
      }
    }
    else if (!((com.lawyer_smartCalendar.data.f)c.get(paramInt)).a().equals("")) {
      e.setText(((com.lawyer_smartCalendar.data.f)c.get(paramInt)).a());
    } else {
      e.setText("-");
    }
    e.setTypeface(d.setText());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
