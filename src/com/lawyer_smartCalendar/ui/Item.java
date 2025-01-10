package com.lawyer_smartCalendar.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.clans.extract.FloatingActionButton;
import com.github.clans.extract.FloatingActionMenu;
import com.joanzapata.material.widget.ImageView;
import com.lawyer_smartCalendar.activity.AddMeetingActivity;
import com.lawyer_smartCalendar.activity.AddProceedingsTimesActivity;
import com.lawyer_smartCalendar.generators.UntypedHashtable;
import com.lawyer_smartCalendar.package_2.Frame;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.h;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import spongycastle.mirrajabi.persiancalendar.PersianCalendarView;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.b;

public class Item
  extends Fragment
  implements View.OnClickListener
{
  private spongycastle.mirrajabi.persiancalendar.crypto.asm.Object a;
  private PersianCalendarView b;
  private RecyclerView c;
  private spongycastle.mirrajabi.persiancalendar.crypto.asm.Object d;
  private TextView entries;
  FloatingActionButton fab;
  private ImageView mPauseButton;
  private ImageView mPlayButton;
  FloatingActionButton refresh;
  FloatingActionMenu this$0;
  private String type = "";
  
  public Item() {}
  
  public void a(Intent paramIntent)
  {
    write(paramIntent);
  }
  
  public void close()
  {
    get(a.get(), a.a());
  }
  
  public void get(int paramInt1, int paramInt2)
  {
    Object localObject = new Calendar();
    Calendar localCalendar = new Calendar();
    ((Calendar)localObject).set(paramInt1, paramInt2 - 1, 1);
    localCalendar.set(paramInt1, paramInt2 - 1, 1);
    localCalendar.init(5, UntypedHashtable.getKeyCode(paramInt2));
    h localH = new h(getActivity());
    localH.setEnabled();
    localObject = localH.initialize(((GregorianCalendar)localObject).getTimeInMillis(), localCalendar.getTimeInMillis());
    localH.close();
    c.setAdapter(new Frame(getActivity(), this, (List)localObject));
    if (((List)localObject).isEmpty())
    {
      c.setVisibility(8);
      entries.setVisibility(0);
      return;
    }
    c.setVisibility(0);
    entries.setVisibility(8);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296758: 
      this$0.close(false);
      paramView = new Intent(getActivity(), AddMeetingActivity.class);
      paramView.putExtra("frmMode", "add");
      if (!type.equals("")) {
        paramView.putExtra("date", type);
      }
      type = "";
      a(paramView);
      return;
    case 2131296757: 
      this$0.close(false);
      paramView = new Intent(getActivity(), AddProceedingsTimesActivity.class);
      paramView.putExtra("frmMode", "add");
      if (!type.equals("")) {
        paramView.putExtra("date", type);
      }
      type = "";
      a(paramView);
      return;
    case 2131296482: 
      b.b();
      return;
    }
    b.f();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492982, paramViewGroup, false);
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    new h(getActivity());
    entries = ((TextView)paramView.findViewById(2131296870));
    mPauseButton = ((ImageView)paramView.findViewById(2131296482));
    mPlayButton = ((ImageView)paramView.findViewById(2131296480));
    this$0 = ((FloatingActionMenu)paramView.findViewById(2131296259));
    fab = ((FloatingActionButton)paramView.findViewById(2131296757));
    refresh = ((FloatingActionButton)paramView.findViewById(2131296758));
    mPlayButton.setOnClickListener(this);
    mPauseButton.setOnClickListener(this);
    fab.setOnClickListener(this);
    refresh.setOnClickListener(this);
    c = ((RecyclerView)paramView.findViewById(2131296684));
    c.setLayoutManager(new LinearLayoutManager(getActivity()));
    b = ((PersianCalendarView)paramView.findViewById(2131296666));
    paramBundle = b.getCalendar();
    a = paramBundle.a();
    paramView = (TextView)paramView.findViewById(2131296860);
    paramBundle.add(new b(a, "Custom event", false));
    paramBundle.a(new f(this, paramView, paramBundle));
    b.setOnDayClickedListener(new g(this, paramBundle, paramView));
    paramBundle.e(false);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramBundle.a(a));
    ((StringBuilder)localObject).append(" ??? ");
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" ");
    localStringBuilder.append(paramBundle.get(a.get()));
    paramView.setText(localStringBuilder.toString());
    paramBundle.add(getResources().getColor(2131099707));
    paramView.setTextColor(d.a());
    get(a.get(), a.a());
  }
}
