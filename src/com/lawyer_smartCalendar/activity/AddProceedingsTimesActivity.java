package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import apps.afollestad.materialdialogs.c;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.Log;
import com.lawyer_smartCalendar.data.f;
import com.lawyer_smartCalendar.package_2.NavigationMenuPresenter;
import com.lawyer_smartCalendar.package_2.i;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.OnAlarmReceiver;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.e;
import com.mohamadamin.persianmaterialdatetimepicker.date.CalendarDatePickerDialog.OnDateSetListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddProceedingsTimesActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
  private com.lawyer_smartCalendar.utils.h a;
  private String b = "";
  public Button btn_add_time;
  public int c = -1;
  private android.support.design.widget.Dialog d;
  private android.support.design.widget.Dialog dialog;
  private com.mohamadamin.persianmaterialdatetimepicker.views.Calendar dueDate;
  private String f = "day";
  private String g = "add";
  private String h = "";
  private int hour;
  private String i = "";
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_date;
  public EditText input_date_hour;
  public EditText input_reminder;
  public EditText input_reminder_count;
  public EditText input_result;
  public EditText input_subject;
  public int j = -1;
  private int minute;
  private int o = 1;
  private int p = 1;
  private String ratio = "?????? ???? ???????";
  private GravityEnum state = GravityEnum.END;
  private DatePickerDialog this$0;
  
  public AddProceedingsTimesActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void closeDialog()
  {
    dialog.dismiss();
  }
  
  public void handleLogin()
  {
    d.dismiss();
  }
  
  public void init(DatePickerDialog paramDatePickerDialog, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePickerDialog = java.util.Calendar.getInstance();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).toString();
    paramDatePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    localObject = this$0.getTag();
    if (((String)localObject).hashCode() == 1386192643)
    {
      while (!((String)localObject).equals("input_date")) {}
      paramInt1 = 0;
    }
    else
    {
      paramInt1 = -1;
    }
    if (paramInt1 != 0) {
      return;
    }
    input_date.setText(paramDatePickerDialog.format());
    input_date.setTextColor(Color.parseColor("#E65100"));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
    ((StringBuilder)localObject).append("");
    i = ((StringBuilder)localObject).toString();
  }
  
  public void onClick(View paramView)
  {
    Object localObject1;
    Object localObject2;
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296545: 
      input_reminder_count.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.negativeColorRes(2131099708);
      paramView.setText(state);
      paramView.title("???? ???????");
      paramView.items(2130903040);
      paramView.itemsCallbackSingleChoice(-1, new b());
      paramView.show();
      return;
    case 2131296544: 
      input_reminder_count.callOnClick();
      return;
    case 2131296514: 
      paramView = java.util.Calendar.getInstance();
      TimePickerDialog.newInstance(this, paramView.get(11), paramView.get(12), true).show(getFragmentManager(), "input_time");
      return;
    case 2131296512: 
      input_date.setError(null);
      dueDate = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      this$0 = DatePickerDialog.newInstance(this, dueDate.get(), dueDate.set(), dueDate.getTime());
      this$0.show(getFragmentManager(), "input_date");
      return;
    case 2131296504: 
      input_client_name.setError(null);
      paramView = getLayoutInflater().inflate(2131492934, null);
      localObject1 = (RecyclerView)paramView.findViewById(2131296682);
      ((RecyclerView)localObject1).setLayoutManager(new LinearLayoutManager(this));
      a.setEnabled();
      localObject2 = a.search();
      a.close();
      if (((List)localObject2).size() > 0)
      {
        ((RecyclerView)localObject1).setAdapter(new i(this, "AddProceedingsTimesActivity", (List)localObject2));
        dialog = new android.support.design.widget.Dialog(this);
        dialog.setContentView(paramView);
        dialog.show();
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.setText(GravityEnum.a);
      paramView.valueOf(GravityEnum.END);
      paramView.negativeColorRes(2131099708);
      paramView.content("??? ????? ??? ???? ???.???? ????? ??? ?????? ?? ???? ??? ????.");
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.positiveText("???? ??");
      paramView.negativeText("??? ????");
      paramView.callback(new d());
      paramView.onPositive(new c());
      paramView.show();
      return;
    case 2131296493: 
      if (j == -1)
      {
        new Collection().a(this, "error", "????? ?? ???? ?????? ????.");
        return;
      }
      input_case.setError(null);
      paramView = getLayoutInflater().inflate(2131492933, null);
      localObject1 = (RecyclerView)paramView.findViewById(2131296680);
      ((RecyclerView)localObject1).setLayoutManager(new LinearLayoutManager(this));
      a.setEnabled();
      localObject2 = a.initialize(j);
      a.close();
      if (((List)localObject2).size() > 0)
      {
        ((RecyclerView)localObject1).setAdapter(new NavigationMenuPresenter(this, "AddProceedingsTimesActivity", (List)localObject2));
        d = new android.support.design.widget.Dialog(this);
        d.setContentView(paramView);
        d.show();
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.setText(GravityEnum.a);
      paramView.valueOf(GravityEnum.END);
      paramView.negativeColorRes(2131099708);
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.content("??? ?????? ?? ???? ??? ???? ??? ???? ???.???? ????? ??? ?????? ?? ?????? ??? ????.");
      paramView.positiveText("???? ??");
      paramView.negativeText("??? ??????");
      paramView.callback(new f());
      paramView.onPositive(new e());
      paramView.show();
      return;
    }
    int k = 0;
    if (input_subject.getText().toString().equals(""))
    {
      input_subject.setError("????? ?? ???? ????.");
      k = 1;
    }
    if (j == -1)
    {
      input_client_name.setError("???? ?? ?????? ????.");
      k = 1;
    }
    if (c == -1)
    {
      input_case.setError("?????? ?? ?????? ????.");
      k = 1;
    }
    if (i.equals(""))
    {
      input_date.setError("????? ?????? ?? ?????? ????.");
      k = 1;
    }
    if (input_date_hour.getText().toString().equals(""))
    {
      input_date_hour.setError("???? ?????? ?? ?????? ????.");
      k = 1;
    }
    if (h.equals(""))
    {
      input_reminder_count.setError("???? ??????? ?? ?????? ????.");
      k = 1;
    }
    if (k == 0)
    {
      paramView = new Log();
      paramView.append(j);
      paramView.set(c);
      paramView.close("ProceedingsTimes");
      paramView.start(input_subject.getText().toString());
      localObject2 = java.util.Calendar.getInstance();
      localObject1 = java.util.Calendar.getInstance();
      ((java.util.Calendar)localObject2).setTimeInMillis(Long.parseLong(i));
      ((java.util.Calendar)localObject2).set(11, minute);
      ((java.util.Calendar)localObject2).set(12, hour);
      if (((java.util.Calendar)localObject2).getTimeInMillis() < ((java.util.Calendar)localObject1).getTimeInMillis())
      {
        new Collection().a(this, "error", "????? ?????? ?? ?? ????? ?????? ????.");
        return;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(((java.util.Calendar)localObject2).getTimeInMillis());
      ((StringBuilder)localObject1).append("");
      paramView.init(((StringBuilder)localObject1).toString());
      paramView.add(input_result.getText().toString());
      localObject1 = java.util.Calendar.getInstance();
      ((java.util.Calendar)localObject1).setTimeInMillis(((java.util.Calendar)localObject2).getTimeInMillis());
      if (f.equals("day")) {
        ((java.util.Calendar)localObject1).set(5, ((java.util.Calendar)localObject1).get(5) - o);
      } else {
        ((java.util.Calendar)localObject1).set(11, ((java.util.Calendar)localObject1).get(11) - p);
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((java.util.Calendar)localObject1).getTimeInMillis());
      ((StringBuilder)localObject2).append("");
      h = ((StringBuilder)localObject2).toString();
      paramView.set(h);
      localObject2 = java.util.Calendar.getInstance();
      if (((java.util.Calendar)localObject1).getTimeInMillis() <= ((java.util.Calendar)localObject2).getTimeInMillis())
      {
        new Collection().a(this, "error", "?? ????? ??? ????? ??? ?????? ???? ?????? ??? ????.");
        return;
      }
      a.setEnabled();
      long l;
      if (g.equals("edit"))
      {
        l = Long.parseLong(b);
        a.write(paramView, b);
        new Collection().a(this, "success", "???? ?????? ?? ?????? ?????? ??.");
      }
      else
      {
        l = a.save(paramView);
        new Collection().a(this, "success", "???? ?????? ?? ?????? ??? ??.");
      }
      a.close();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(hour);
      ((StringBuilder)localObject2).append(" : ");
      ((StringBuilder)localObject2).append(minute);
      localObject2 = ((StringBuilder)localObject2).toString();
      Object localObject3 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject3).setTimeInMillis(Long.parseLong(paramView.getString()));
      localObject3 = ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject3).convert();
      Intent localIntent = new Intent(this, OnAlarmReceiver.class);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(l);
      localStringBuilder.append("");
      localIntent.putExtra("id", localStringBuilder.toString());
      localIntent.putExtra("title", paramView.getID());
      paramView = new StringBuilder();
      paramView.append("????? : ");
      paramView.append((String)localObject3);
      paramView.append("\n???? : ");
      paramView.append((String)localObject2);
      localIntent.putExtra("message", paramView.toString());
      new com.lawyer_smartCalendar.utils.Item(this, localIntent, ((java.util.Calendar)localObject1).getTimeInMillis(), l).set();
      setResult(-1);
      e.i.close();
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492903);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    a = new com.lawyer_smartCalendar.utils.h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230856);
    paramBundle.setNavigationOnClickListener(new a());
    btn_add_time.setVisibility(8);
    btn_add_time.setOnClickListener(this);
    input_client_name.setOnClickListener(this);
    input_client_name.setOnFocusChangeListener(this);
    input_case.setOnClickListener(this);
    input_case.setOnFocusChangeListener(this);
    input_result.setOnClickListener(this);
    input_result.setOnFocusChangeListener(this);
    input_date.setOnClickListener(this);
    input_date.setOnFocusChangeListener(this);
    input_reminder.setOnClickListener(this);
    input_date_hour.setOnClickListener(this);
    input_date_hour.setOnFocusChangeListener(this);
    input_reminder_count.setOnClickListener(this);
    input_reminder_count.setText(ratio);
    b = getIntent().getStringExtra("id");
    g = getIntent().getStringExtra("frmMode");
    Object localObject1;
    try
    {
      localObject1 = getIntent().getStringExtra("date");
      paramBundle = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      paramBundle.setTimeInMillis(Long.parseLong((String)localObject1));
      localObject1 = input_date;
      ((TextView)localObject1).setText(paramBundle.format());
      localObject1 = input_date;
      ((TextView)localObject1).setTextColor(Color.parseColor("#E65100"));
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramBundle.getTimeInMillis());
      ((StringBuilder)localObject1).append("");
      paramBundle = ((StringBuilder)localObject1).toString();
      i = paramBundle;
      paramBundle = java.util.Calendar.getInstance();
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramBundle.getTimeInMillis());
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).toString();
    }
    catch (Exception paramBundle) {}
    Object localObject2;
    Object localObject3;
    int k;
    label712:
    StringBuilder localStringBuilder;
    if (g.equals("edit"))
    {
      a.setEnabled();
      localObject1 = a.getInstance(b);
      paramBundle = a;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Log)localObject1).getText());
      ((StringBuilder)localObject2).append("");
      paramBundle = paramBundle.set(((StringBuilder)localObject2).toString());
      localObject2 = a;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(((Log)localObject1).read());
      ((StringBuilder)localObject3).append("");
      localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a(((StringBuilder)localObject3).toString());
      a.close();
      getSupportActionBar().setTitle("?????? ??????");
      input_subject.setText(((Log)localObject1).getID());
      input_client_name.setText(paramBundle.getValue());
      j = ((Log)localObject1).getText();
      paramBundle = "";
      localObject3 = ((com.lawyer_smartCalendar.data.h)localObject2).getString();
      switch (((String)localObject3).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject3).equals("Executive"))
        {
          k = 1;
          break label712;
          if (((String)localObject3).equals("Bank"))
          {
            k = 3;
            break label712;
            if (((String)localObject3).equals("Challenge"))
            {
              k = 2;
              break label712;
              if (((String)localObject3).equals("Normal"))
              {
                k = 0;
                break label712;
              }
            }
          }
        }
      }
      k = -1;
      if (k != 0)
      {
        if (k != 1)
        {
          if (k != 2)
          {
            if (k == 3) {
              paramBundle = "?????";
            }
          }
          else {
            paramBundle = "???????/??????";
          }
        }
        else {
          paramBundle = "??????";
        }
      }
      else {
        paramBundle = "????";
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("?????: ");
      ((StringBuilder)localObject3).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()))));
      localObject2 = ((StringBuilder)localObject3).toString();
      localObject3 = input_case;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle);
      localStringBuilder.append(" - ");
      localStringBuilder.append((String)localObject2);
      ((TextView)localObject3).setText(localStringBuilder.toString());
      input_case.setTextColor(Color.parseColor("#E65100"));
      c = ((Log)localObject1).read();
      localObject2 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject2).setTimeInMillis(Long.parseLong(((Log)localObject1).getString()));
      input_date.setText(((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject2).format());
      input_date.setTextColor(Color.parseColor("#E65100"));
      paramBundle = new StringBuilder();
      paramBundle.append(((GregorianCalendar)localObject2).getTimeInMillis());
      paramBundle.append("");
      i = paramBundle.toString();
      input_result.setText(((Log)localObject1).getValue());
      paramBundle = java.util.Calendar.getInstance();
      paramBundle.setTimeInMillis(Long.parseLong(((Log)localObject1).getString()));
      minute = ((GregorianCalendar)localObject2).get(11);
      hour = ((GregorianCalendar)localObject2).get(12);
      localObject1 = input_date_hour;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramBundle.get(11));
      ((StringBuilder)localObject2).append(":");
      ((StringBuilder)localObject2).append(paramBundle.get(12));
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    try
    {
      localObject1 = getIntent().getStringExtra("frmStarter");
      paramBundle = getIntent().getStringExtra("id");
      k = ((String)localObject1).hashCode();
      boolean bool;
      if (k == 3046192)
      {
        do
        {
          bool = ((String)localObject1).equals("case");
        } while (!bool);
        k = 0;
      }
      else
      {
        k = -1;
      }
      if (k != 0) {
        return;
      }
      localObject1 = a;
      ((com.lawyer_smartCalendar.utils.h)localObject1).setEnabled();
      localObject1 = a;
      localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).a(paramBundle);
      paramBundle = a;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.h)localObject1).c());
      ((StringBuilder)localObject2).append("");
      paramBundle = paramBundle.set(((StringBuilder)localObject2).toString());
      localObject2 = a;
      ((com.lawyer_smartCalendar.utils.h)localObject2).close();
      localObject2 = input_client_name;
      ((TextView)localObject2).setText(paramBundle.getValue());
      localObject2 = input_client_name;
      ((TextView)localObject2).setEnabled(false);
      k = paramBundle.add();
      j = k;
      paramBundle = "";
      localObject2 = ((com.lawyer_smartCalendar.data.h)localObject1).getString();
      k = ((String)localObject2).hashCode();
      switch (k)
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        bool = ((String)localObject2).equals("Executive");
        if (bool)
        {
          k = 1;
          break label1384;
          bool = ((String)localObject2).equals("Bank");
          if (bool)
          {
            k = 3;
            break label1384;
            bool = ((String)localObject2).equals("Challenge");
            if (bool)
            {
              k = 2;
              break label1384;
              bool = ((String)localObject2).equals("Normal");
              if (bool)
              {
                k = 0;
                break label1384;
              }
            }
          }
        }
      }
      k = -1;
      label1384:
      if (k != 0)
      {
        if (k != 1)
        {
          if (k != 2)
          {
            if (k == 3) {
              paramBundle = "?????";
            }
          }
          else {
            paramBundle = "???????/??????";
          }
        }
        else {
          paramBundle = "??????";
        }
      }
      else {
        paramBundle = "????";
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("?????: ");
      ((StringBuilder)localObject2).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject1).getIcon()))));
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject3 = input_case;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle);
      localStringBuilder.append(" - ");
      localStringBuilder.append((String)localObject2);
      ((TextView)localObject3).setText(localStringBuilder.toString());
      paramBundle = input_case;
      paramBundle.setTextColor(Color.parseColor("#E65100"));
      paramBundle = input_case;
      paramBundle.setEnabled(false);
      k = ((com.lawyer_smartCalendar.data.h)localObject1).a();
      c = k;
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558409, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    switch (paramView.getId())
    {
    default: 
      
    case 2131296546: 
      if (paramBoolean)
      {
        input_result.setError(null);
        input_result.callOnClick();
        return;
      }
      break;
    case 2131296544: 
      if (paramBoolean)
      {
        input_reminder_count.setError(null);
        input_reminder_count.callOnClick();
        return;
      }
      break;
    case 2131296514: 
      if (paramBoolean)
      {
        input_date_hour.setError(null);
        input_date_hour.callOnClick();
        return;
      }
      break;
    case 2131296512: 
      if (paramBoolean)
      {
        input_date.setError(null);
        input_date.callOnClick();
        return;
      }
      break;
    case 2131296504: 
      if (paramBoolean)
      {
        input_client_name.setError(null);
        input_client_name.callOnClick();
        return;
      }
      break;
    case 2131296493: 
      if (paramBoolean)
      {
        input_case.setError(null);
        input_case.callOnClick();
      }
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_time.callOnClick();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (isFinishing()) {
      overridePendingTransition(17432576, 17432577);
    }
  }
  
  public void onTimeSet(RadialPickerLayout paramRadialPickerLayout, int paramInt1, int paramInt2)
  {
    minute = paramInt1;
    hour = paramInt2;
    paramRadialPickerLayout = input_date_hour;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(":");
    localStringBuilder.append(paramInt2);
    paramRadialPickerLayout.setText(localStringBuilder.toString());
  }
  
  class a
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      finish();
    }
  }
  
  class b
    implements MaterialDialog.ListCallbackSingleChoice
  {
    b() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_reminder_count.setText(paramCharSequence);
      paramMaterialDialog = AddProceedingsTimesActivity.this;
      AddProceedingsTimesActivity.a(paramMaterialDialog, AddProceedingsTimesActivity.c(paramMaterialDialog));
      switch (paramInt)
      {
      default: 
        return true;
      case 7: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "day");
        AddProceedingsTimesActivity.a(AddProceedingsTimesActivity.this, 5);
        return true;
      case 6: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "day");
        AddProceedingsTimesActivity.a(AddProceedingsTimesActivity.this, 4);
        return true;
      case 5: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "day");
        AddProceedingsTimesActivity.a(AddProceedingsTimesActivity.this, 3);
        return true;
      case 4: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "day");
        AddProceedingsTimesActivity.a(AddProceedingsTimesActivity.this, 2);
        return true;
      case 3: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "day");
        AddProceedingsTimesActivity.a(AddProceedingsTimesActivity.this, 1);
        return true;
      case 2: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "hour");
        AddProceedingsTimesActivity.c(AddProceedingsTimesActivity.this, 5);
        return true;
      case 1: 
        AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "hour");
        AddProceedingsTimesActivity.c(AddProceedingsTimesActivity.this, 2);
        return true;
      }
      AddProceedingsTimesActivity.toString(AddProceedingsTimesActivity.this, "hour");
      AddProceedingsTimesActivity.c(AddProceedingsTimesActivity.this, 1);
      return true;
    }
  }
  
  class c
    implements c
  {
    c() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddProceedingsTimesActivity.this, AddClientActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class d
    implements c
  {
    d() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class e
    implements c
  {
    e() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddProceedingsTimesActivity.this, AddCaseActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class f
    implements c
  {
    f() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
}
