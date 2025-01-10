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

public class AddMeetingActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
  public int a = -1;
  public Button btn_add_time;
  public int c = -1;
  private android.support.design.widget.Dialog d;
  private android.support.design.widget.Dialog dialog;
  private com.mohamadamin.persianmaterialdatetimepicker.views.Calendar dueDate;
  private String e = "?????? ???? ???????";
  private String f = "";
  private int hour;
  private String i = "";
  private String id = "";
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_date;
  public EditText input_date_hour;
  public EditText input_reminder;
  public EditText input_reminder_count;
  public EditText input_subject;
  private DatePickerDialog mDatePickerDialog;
  private int minute;
  private int o = 1;
  private int p = 1;
  private String s = "day";
  private GravityEnum state = GravityEnum.END;
  private com.lawyer_smartCalendar.utils.h this$0;
  private String type = "add";
  
  public AddMeetingActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void cancelDialog()
  {
    d.dismiss();
  }
  
  public void handleLogin()
  {
    dialog.dismiss();
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
    localObject = mDatePickerDialog.getTag();
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
      mDatePickerDialog = DatePickerDialog.newInstance(this, dueDate.get(), dueDate.set(), dueDate.getTime());
      mDatePickerDialog.show(getFragmentManager(), "input_date");
      return;
    case 2131296504: 
      input_client_name.setError(null);
      paramView = getLayoutInflater().inflate(2131492934, null);
      localObject1 = (RecyclerView)paramView.findViewById(2131296682);
      ((RecyclerView)localObject1).setLayoutManager(new LinearLayoutManager(this));
      this$0.setEnabled();
      localObject2 = this$0.search();
      this$0.close();
      if (((List)localObject2).size() > 0)
      {
        ((RecyclerView)localObject1).setAdapter(new i(this, "AddMeetingActivity", (List)localObject2));
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
      if (a == -1)
      {
        new Collection().a(this, "error", "????? ?? ???? ?????? ????.");
        return;
      }
      input_case.setError(null);
      paramView = getLayoutInflater().inflate(2131492933, null);
      localObject1 = (RecyclerView)paramView.findViewById(2131296680);
      ((RecyclerView)localObject1).setLayoutManager(new LinearLayoutManager(this));
      this$0.setEnabled();
      localObject2 = this$0.initialize(a);
      this$0.close();
      if (((List)localObject2).size() > 0)
      {
        ((RecyclerView)localObject1).setAdapter(new NavigationMenuPresenter(this, "AddMeetingActivity", (List)localObject2));
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
    int j = 0;
    if (input_subject.getText().toString().equals(""))
    {
      input_subject.setError("????? ?? ???? ????.");
      j = 1;
    }
    if (a == -1)
    {
      input_client_name.setError("???? ?? ?????? ????.");
      j = 1;
    }
    if (c == -1)
    {
      input_case.setError("?????? ?? ?????? ????.");
      j = 1;
    }
    if (i.equals(""))
    {
      input_date.setError("????? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (input_date_hour.getText().toString().equals(""))
    {
      input_date_hour.setError("???? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (f.equals(""))
    {
      input_reminder_count.setError("???? ??????? ?? ?????? ????.");
      j = 1;
    }
    if (j == 0)
    {
      paramView = new Log();
      paramView.append(a);
      paramView.set(c);
      paramView.close("Meeting");
      paramView.start(input_subject.getText().toString());
      localObject2 = java.util.Calendar.getInstance();
      localObject1 = java.util.Calendar.getInstance();
      ((java.util.Calendar)localObject2).setTimeInMillis(Long.parseLong(i));
      ((java.util.Calendar)localObject2).set(11, hour);
      ((java.util.Calendar)localObject2).set(12, minute);
      if (((java.util.Calendar)localObject2).getTimeInMillis() < ((java.util.Calendar)localObject1).getTimeInMillis())
      {
        new Collection().a(this, "error", "????? ?????? ?? ?? ????? ?????? ????.");
        return;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(((java.util.Calendar)localObject2).getTimeInMillis());
      ((StringBuilder)localObject1).append("");
      paramView.init(((StringBuilder)localObject1).toString());
      paramView.add("");
      localObject1 = java.util.Calendar.getInstance();
      ((java.util.Calendar)localObject1).setTimeInMillis(((java.util.Calendar)localObject2).getTimeInMillis());
      if (s.equals("day")) {
        ((java.util.Calendar)localObject1).set(5, ((java.util.Calendar)localObject1).get(5) - o);
      } else {
        ((java.util.Calendar)localObject1).set(11, ((java.util.Calendar)localObject1).get(11) - p);
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((java.util.Calendar)localObject1).getTimeInMillis());
      ((StringBuilder)localObject2).append("");
      f = ((StringBuilder)localObject2).toString();
      paramView.set(f);
      localObject2 = java.util.Calendar.getInstance();
      if (((java.util.Calendar)localObject1).getTimeInMillis() <= ((java.util.Calendar)localObject2).getTimeInMillis())
      {
        new Collection().a(this, "error", "?? ????? ??? ????? ??? ?????? ???? ?????? ??? ????.");
        return;
      }
      this$0.setEnabled();
      long l;
      if (type.equals("edit"))
      {
        l = Long.parseLong(id);
        this$0.write(paramView, id);
        new Collection().a(this, "success", "???? ?????? ?? ?????? ?????? ??.");
      }
      else
      {
        l = this$0.save(paramView);
        new Collection().a(this, "success", "???? ?????? ?? ?????? ??? ??.");
      }
      this$0.close();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(minute);
      ((StringBuilder)localObject2).append(" : ");
      ((StringBuilder)localObject2).append(hour);
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
    setContentView(2131492900);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    this$0 = new com.lawyer_smartCalendar.utils.h(this);
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
    input_date.setOnClickListener(this);
    input_date.setOnFocusChangeListener(this);
    input_reminder.setOnClickListener(this);
    input_date_hour.setOnClickListener(this);
    input_date_hour.setOnFocusChangeListener(this);
    input_reminder_count.setOnClickListener(this);
    input_reminder_count.setText(e);
    id = getIntent().getStringExtra("id");
    type = getIntent().getStringExtra("frmMode");
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
    int j;
    if (type.equals("edit"))
    {
      this$0.setEnabled();
      localObject1 = this$0.getInstance(id);
      paramBundle = this$0;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Log)localObject1).getText());
      ((StringBuilder)localObject2).append("");
      paramBundle = paramBundle.set(((StringBuilder)localObject2).toString());
      localObject2 = this$0;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(((Log)localObject1).read());
      ((StringBuilder)localObject3).append("");
      localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a(((StringBuilder)localObject3).toString());
      this$0.close();
      getSupportActionBar().setTitle("?????? ???? ??????");
      input_subject.setText(((Log)localObject1).getID());
      input_client_name.setText(paramBundle.getValue());
      a = ((Log)localObject1).getText();
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
          j = 1;
          break label696;
          if (((String)localObject3).equals("Bank"))
          {
            j = 3;
            break label696;
            if (((String)localObject3).equals("Challenge"))
            {
              j = 2;
              break label696;
              if (((String)localObject3).equals("Normal"))
              {
                j = 0;
                break label696;
              }
            }
          }
        }
      }
      j = -1;
      label696:
      if (j != 0)
      {
        if (j != 1)
        {
          if (j != 2)
          {
            if (j == 3) {
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
      StringBuilder localStringBuilder = new StringBuilder();
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
      paramBundle = java.util.Calendar.getInstance();
      paramBundle.setTimeInMillis(Long.parseLong(((Log)localObject1).getString()));
      hour = ((GregorianCalendar)localObject2).get(11);
      minute = ((GregorianCalendar)localObject2).get(12);
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
      j = ((String)localObject1).hashCode();
      if (j == -1357712437)
      {
        boolean bool;
        do
        {
          bool = ((String)localObject1).equals("client");
        } while (!bool);
        j = 0;
      }
      else
      {
        j = -1;
      }
      if (j != 0) {
        return;
      }
      localObject1 = this$0;
      ((com.lawyer_smartCalendar.utils.h)localObject1).setEnabled();
      localObject1 = this$0;
      paramBundle = ((com.lawyer_smartCalendar.utils.h)localObject1).set(paramBundle);
      localObject1 = this$0;
      ((com.lawyer_smartCalendar.utils.h)localObject1).close();
      localObject1 = input_client_name;
      ((TextView)localObject1).setText(paramBundle.getValue());
      localObject1 = input_client_name;
      ((TextView)localObject1).setEnabled(false);
      j = paramBundle.add();
      a = j;
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
      
    case 2131296544: 
      if (paramBoolean)
      {
        input_reminder.setError(null);
        input_reminder.callOnClick();
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
    hour = paramInt1;
    minute = paramInt2;
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
      paramMaterialDialog = AddMeetingActivity.this;
      AddMeetingActivity.a(paramMaterialDialog, AddMeetingActivity.c(paramMaterialDialog));
      switch (paramInt)
      {
      default: 
        return true;
      case 7: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "day");
        AddMeetingActivity.a(AddMeetingActivity.this, 5);
        return true;
      case 6: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "day");
        AddMeetingActivity.a(AddMeetingActivity.this, 4);
        return true;
      case 5: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "day");
        AddMeetingActivity.a(AddMeetingActivity.this, 3);
        return true;
      case 4: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "day");
        AddMeetingActivity.a(AddMeetingActivity.this, 2);
        return true;
      case 3: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "day");
        AddMeetingActivity.a(AddMeetingActivity.this, 1);
        return true;
      case 2: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "hour");
        AddMeetingActivity.c(AddMeetingActivity.this, 5);
        return true;
      case 1: 
        AddMeetingActivity.toString(AddMeetingActivity.this, "hour");
        AddMeetingActivity.c(AddMeetingActivity.this, 2);
        return true;
      }
      AddMeetingActivity.toString(AddMeetingActivity.this, "hour");
      AddMeetingActivity.c(AddMeetingActivity.this, 1);
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
      paramMaterialDialog = new Intent(AddMeetingActivity.this, AddClientActivity.class);
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
      paramMaterialDialog = new Intent(AddMeetingActivity.this, AddCaseActivity.class);
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
