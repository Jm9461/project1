package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.andexert.library.RippleView;
import com.lawyer_smartCalendar.a.v.a;

public class w
  extends RecyclerView.g<v.a>
{
  private RippleView a;
  private String[][] b;
  private Context c;
  
  public w(Context paramContext, String[][] paramArrayOfString)
  {
    setHasStableIds(true);
    c = paramContext;
    b = paramArrayOfString;
  }
  
  public int a()
  {
    return b.length;
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public ac a(ViewGroup paramViewGroup, int paramInt)
  {
    return new ac(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492981, paramViewGroup, false));
  }
  
  public void a(ac paramAc, int paramInt)
  {
    b.setText(b[paramInt][1]);
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
