package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.axt.views.Label;
import com.lawyer_smartCalendar.a.c.a;
import java.io.File;
import java.util.List;

public class h
  extends RecyclerView.g<c.a>
{
  private com.lawyer_smartCalendar.utils.h a;
  private Label c;
  private List<File> l;
  private Context m;
  
  public h(Context paramContext, Label paramLabel, List paramList)
  {
    m = paramContext;
    l = paramList;
    c = paramLabel;
    a = new com.lawyer_smartCalendar.utils.h(paramContext);
  }
  
  public int a()
  {
    return l.size();
  }
  
  public c a(ViewGroup paramViewGroup, int paramInt)
  {
    return new c(this, LayoutInflater.from(m).inflate(2131493050, paramViewGroup, false));
  }
  
  public void a(c paramC, int paramInt)
  {
    f.setText(((File)l.get(paramInt)).getName());
  }
}
