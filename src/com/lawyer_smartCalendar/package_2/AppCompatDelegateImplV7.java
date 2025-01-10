package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.d.a;
import com.lawyer_smartCalendar.utils.h;
import java.io.File;
import java.util.List;

public class AppCompatDelegateImplV7
  extends RecyclerView.g<d.a>
{
  private List<File> l;
  private Context m;
  
  public AppCompatDelegateImplV7(Context paramContext, List paramList)
  {
    m = paramContext;
    l = paramList;
    new MediaPlayer();
    new h(paramContext);
  }
  
  public int a()
  {
    return l.size();
  }
  
  public o a(ViewGroup paramViewGroup, int paramInt)
  {
    return new o(this, LayoutInflater.from(m).inflate(2131493051, paramViewGroup, false));
  }
  
  public void a(o paramO, int paramInt)
  {
    c.setText(((File)l.get(paramInt)).getName());
  }
}
