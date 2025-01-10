package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.j.a;

public class a
  extends RecyclerView.g<j.a>
{
  private String a;
  private Context c;
  private String[][] d;
  
  public a(Context paramContext, String paramString, String[][] paramArrayOfString)
  {
    setHasStableIds(true);
    c = paramContext;
    d = paramArrayOfString;
    a = paramString;
  }
  
  public int a()
  {
    return d.length;
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public j a(ViewGroup paramViewGroup, int paramInt)
  {
    return new j(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492943, paramViewGroup, false));
  }
  
  public void a(j paramJ, int paramInt)
  {
    a.setText(d[paramInt][1]);
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
