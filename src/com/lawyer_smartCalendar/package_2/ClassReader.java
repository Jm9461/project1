package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.b.a;
import com.lawyer_smartCalendar.utils.f;

public class ClassReader
  extends RecyclerView.g<b.a>
{
  private String[][] a;
  private Typeface b;
  private Context c;
  
  public ClassReader(Context paramContext, String[][] paramArrayOfString)
  {
    setHasStableIds(true);
    c = paramContext;
    a = paramArrayOfString;
    b = new f(c).setText();
  }
  
  public int a()
  {
    return a.length;
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public m a(ViewGroup paramViewGroup, int paramInt)
  {
    return new m(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493049, paramViewGroup, false));
  }
  
  public void b(m paramM, int paramInt)
  {
    b.setText(a[paramInt][1]);
    b.setTypeface(b);
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
