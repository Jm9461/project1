package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import apps.authenticator.wizard.runtime.Log;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.f;
import com.lawyer_smartCalendar.package_2.a;
import com.lawyer_smartCalendar.package_2.i;
import com.lawyer_smartCalendar.ui.LogsFragment;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.e;
import com.mohamadamin.persianmaterialdatetimepicker.date.CalendarDatePickerDialog.OnDateSetListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddCaseActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener
{
  public String a = "";
  private com.lawyer_smartCalendar.utils.h b;
  public Button btn_add_case;
  private String c = "add";
  private android.support.design.widget.Dialog d;
  private android.support.design.widget.Dialog dialog;
  private Calendar dueDate;
  private GravityEnum e = GravityEnum.END;
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private String id = "";
  public EditText input_archive_number;
  public EditText input_attorney_letter_number;
  public EditText input_case_date;
  public EditText input_case_issue;
  public EditText input_case_number;
  public EditText input_case_process;
  public EditText input_case_status;
  public EditText input_case_title;
  public EditText input_client_name;
  public EditText input_client_title_type;
  public EditText input_court_branch;
  public EditText input_court_city;
  public EditText input_court_type;
  public EditText input_lawsuit_info;
  private DatePickerDialog mDatePickerDialog;
  public int p = -1;
  private String s = "";
  private String type = "";
  
  public AddCaseActivity() {}
  
  public void a(String paramString)
  {
    int j;
    if (paramString.hashCode() == 3053931)
    {
      while (!paramString.equals("city")) {}
      j = 0;
    }
    else
    {
      j = -1;
    }
    if (j != 0) {
      return;
    }
    d.dismiss();
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void closeDialog()
  {
    dialog.dismiss();
  }
  
  public void init(DatePickerDialog paramDatePickerDialog, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePickerDialog = new Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    input_case_date.setText(paramDatePickerDialog.format());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramDatePickerDialog.getTimeInMillis());
    localStringBuilder.append("");
    type = localStringBuilder.toString();
  }
  
  public void onClick(View paramView)
  {
    RecyclerView localRecyclerView;
    Object localObject;
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296511: 
      input_court_type.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(e);
      paramView.setText(e);
      paramView.putString(e);
      paramView.negativeColorRes(2131099707);
      paramView.setText(e);
      paramView.title("???? ? ??? ??????");
      paramView.items(2130903042);
      paramView.itemsCallbackSingleChoice(-1, new e());
      paramView.show();
      return;
    case 2131296510: 
      paramView = getLayoutInflater().inflate(2131492930, null);
      localRecyclerView = (RecyclerView)paramView.findViewById(2131296686);
      localRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      b.setEnabled();
      localObject = b.getValues();
      b.close();
      localRecyclerView.setAdapter(new a(this, "AddCaseActivity", (String[][])localObject));
      d = new android.support.design.widget.Dialog(this);
      d.setContentView(paramView);
      d.show();
      return;
    case 2131296507: 
      input_client_title_type.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(e);
      paramView.setText(e);
      paramView.putString(e);
      paramView.negativeColorRes(2131099707);
      paramView.setText(e);
      paramView.title("??? ????");
      paramView.items(2130903048);
      paramView.itemsCallbackSingleChoice(-1, new g());
      paramView.show();
      return;
    case 2131296504: 
      input_client_name.setError(null);
      paramView = getLayoutInflater().inflate(2131492934, null);
      localRecyclerView = (RecyclerView)paramView.findViewById(2131296682);
      localRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      b.setEnabled();
      localObject = b.search();
      b.close();
      if (((List)localObject).size() > 0)
      {
        localRecyclerView.setAdapter(new i(this, "AddCaseActivity", (List)localObject));
        dialog = new android.support.design.widget.Dialog(this);
        dialog.setContentView(paramView);
        dialog.show();
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.setText(GravityEnum.a);
      paramView.valueOf(e);
      paramView.negativeColorRes(2131099707);
      paramView.content("??? ????? ??? ???? ???.???? ????? ??? ?????? ?? ???? ??? ????.");
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.positiveText("???? ??");
      paramView.negativeText("??? ????");
      paramView.callback(new a());
      paramView.onPositive(new j());
      paramView.show();
      return;
    case 2131296499: 
      input_case_title.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(e);
      paramView.setText(e);
      paramView.putString(e);
      paramView.negativeColorRes(2131099708);
      paramView.setText(e);
      paramView.title("??????");
      paramView.items(2130903045);
      paramView.itemsCallbackSingleChoice(-1, new f());
      paramView.show();
      return;
    case 2131296498: 
      input_case_issue.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(e);
      paramView.setText(e);
      paramView.putString(e);
      paramView.negativeColorRes(2131099707);
      paramView.setText(e);
      paramView.title("????? ??????");
      paramView.items(2130903043);
      paramView.itemsCallbackSingleChoice(-1, new h());
      paramView.show();
      return;
    case 2131296497: 
      input_case_process.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(e);
      paramView.setText(e);
      paramView.putString(e);
      paramView.negativeColorRes(2131099708);
      paramView.setText(e);
      paramView.title("???? ??????");
      paramView.items(2130903044);
      paramView.itemsCallbackSingleChoice(-1, new i());
      paramView.show();
      return;
    case 2131296494: 
      input_case_date.setError(null);
      dueDate = new Calendar();
      mDatePickerDialog = DatePickerDialog.newInstance(this, dueDate.get(), dueDate.set(), dueDate.getTime());
      mDatePickerDialog.show(getFragmentManager(), "DatePickerDialog");
      return;
    }
    b.setEnabled();
    int j = b.initialize().size();
    b.close();
    if ((j == 200) && (!Log.getFilename("app_version", "limited").equals("full")))
    {
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(GravityEnum.a);
      paramView.setText(GravityEnum.a);
      paramView.valueOf(e);
      paramView.negativeColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
      paramView.title("");
      paramView.positiveColorRes(2131099728);
      paramView.neutralColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
      paramView.content("??????? ?????? ????? ????? ????????? ????");
      paramView.positiveText("???? ??");
      paramView.neutralText("??????? ?????? ????? ????? ????????? ????");
      paramView.callback(new d());
      paramView.onNegative(new c());
      paramView.show();
      return;
    }
    j = 0;
    if (i.equals(""))
    {
      input_case_title.setError("????? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (type.equals(""))
    {
      input_case_date.setError("????? ????? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (p == -1)
    {
      input_client_name.setError("?? ???? ?????? ????.");
      j = 1;
    }
    if (id.equals(""))
    {
      input_client_title_type.setError("??? ???? ?? ?????? ????.");
      j = 1;
    }
    if (input_case_issue.getText().toString().equals(""))
    {
      input_case_issue.setError("????? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (g.equals(""))
    {
      input_case_status.setError("????? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (f.equals(""))
    {
      input_case_process.setError("???? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (h.equals(""))
    {
      input_court_type.setError("???? ? ??? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (input_archive_number.getText().toString().equals(""))
    {
      input_archive_number.setError("????? ??????? ?? ?????? ????.");
      j = 1;
    }
    if (a.equals(""))
    {
      input_court_city.setError("???????? ??????? ?? ?????? ????.");
      j = 1;
    }
    if (input_court_branch.getText().toString().equals(""))
    {
      input_court_branch.setError("???? ?????? ?? ?????? ????.");
      j = 1;
    }
    if (j == 0)
    {
      paramView = new com.lawyer_smartCalendar.data.h();
      paramView.a(p);
      paramView.put(i);
      paramView.add(type);
      paramView.setTitle(id);
      paramView.set(input_case_issue.getText().toString());
      paramView.a(h);
      paramView.e(Integer.parseInt(a));
      paramView.c(input_court_branch.getText().toString());
      paramView.write(input_case_number.getText().toString());
      paramView.setIcon(input_archive_number.getText().toString());
      paramView.d(input_attorney_letter_number.getText().toString());
      paramView.e(input_lawsuit_info.getText().toString());
      paramView.format(f);
      paramView.append(g);
      b.setEnabled();
      if (c.equals("edit"))
      {
        b.a(paramView, s);
        new Collection().a(this, "success", getResources().getString(2131755199));
      }
      else
      {
        b.a(paramView);
        new Collection().a(this, "success", getResources().getString(2131755200));
      }
      b.close();
      paramView = e.e;
      try
      {
        paramView.finish();
        paramView = e.f;
        paramView.addView();
      }
      catch (Exception paramView) {}
      setResult(-1);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492896);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      e = GravityEnum.END;
    } else {
      e = GravityEnum.START;
    }
    if (!Log.getFilename("app_version", "limited").equals("full")) {
      new Collection().a(this, "info", "??????? ?????? ????? ????? ????????? ????");
    }
    b = new com.lawyer_smartCalendar.utils.h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new b());
    btn_add_case.setOnClickListener(this);
    btn_add_case.setVisibility(8);
    input_client_name.setOnClickListener(this);
    input_client_name.setOnFocusChangeListener(this);
    input_case_issue.setOnClickListener(this);
    input_case_issue.setOnFocusChangeListener(this);
    input_case_status.setOnClickListener(this);
    input_case_status.setOnFocusChangeListener(this);
    input_court_type.setOnClickListener(this);
    input_court_type.setOnFocusChangeListener(this);
    input_case_title.setOnClickListener(this);
    input_case_title.setOnFocusChangeListener(this);
    input_case_date.setOnClickListener(this);
    input_case_date.setOnFocusChangeListener(this);
    input_client_title_type.setOnClickListener(this);
    input_client_title_type.setOnFocusChangeListener(this);
    input_court_city.setOnClickListener(this);
    input_court_city.setOnFocusChangeListener(this);
    input_case_process.setOnClickListener(this);
    input_case_process.setOnFocusChangeListener(this);
    c = getIntent().getStringExtra("frmMode");
    Object localObject1;
    int j;
    if (c.equals("edit"))
    {
      s = getIntent().getStringExtra("id");
      b.setEnabled();
      localObject1 = b.a(s);
      paramBundle = b;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.h)localObject1).c());
      ((StringBuilder)localObject2).append("");
      Object localObject3 = paramBundle.set(((StringBuilder)localObject2).toString());
      paramBundle = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.h)localObject1).b());
      ((StringBuilder)localObject2).append("");
      localObject2 = paramBundle.remove(((StringBuilder)localObject2).toString());
      paramBundle = new StringBuilder();
      paramBundle.append(((com.lawyer_smartCalendar.data.h)localObject1).b());
      paramBundle.append("");
      a = paramBundle.toString();
      b.close();
      getSupportActionBar().setTitle("?????? ??????");
      paramBundle = "";
      String str = ((com.lawyer_smartCalendar.data.h)localObject1).getString();
      switch (str.hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (str.equals("Executive"))
        {
          j = 1;
          break label644;
          if (str.equals("Bank"))
          {
            j = 3;
            break label644;
            if (str.equals("Challenge"))
            {
              j = 2;
              break label644;
              if (str.equals("Normal"))
              {
                j = 0;
                break label644;
              }
            }
          }
        }
      }
      j = -1;
      label644:
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
      i = ((com.lawyer_smartCalendar.data.h)localObject1).getString();
      input_case_title.setText(paramBundle);
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getIcon().equals(""))
      {
        type = ((com.lawyer_smartCalendar.data.h)localObject1).getIcon();
        paramBundle = new Calendar();
        paramBundle.setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject1).getIcon()));
        input_case_date.setText(paramBundle.format());
        input_case_date.setTextColor(Color.parseColor("#E65100"));
      }
      p = ((f)localObject3).add();
      input_client_name.setText(((f)localObject3).getValue());
      id = ((com.lawyer_smartCalendar.data.h)localObject1).getTitle();
      paramBundle = "";
      localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).getTitle();
      switch (((String)localObject3).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject3).equals("Accused"))
        {
          j = 3;
          break label980;
          if (((String)localObject3).equals("Reconsidered"))
          {
            j = 5;
            break label980;
            if (((String)localObject3).equals("Read"))
            {
              j = 1;
              break label980;
              if (((String)localObject3).equals("Applicant"))
              {
                j = 0;
                break label980;
                if (((String)localObject3).equals("Plaintiff"))
                {
                  j = 2;
                  break label980;
                  if (((String)localObject3).equals("Revisionist"))
                  {
                    j = 4;
                    break label980;
                  }
                }
              }
            }
          }
        }
      }
      j = -1;
      label980:
      if (j != 0)
      {
        if (j != 1)
        {
          if (j != 2)
          {
            if (j != 3)
            {
              if (j != 4)
              {
                if (j == 5) {
                  paramBundle = "????? ??????";
                }
              }
              else {
                paramBundle = "???????? ????";
              }
            }
            else {
              paramBundle = "????";
            }
          }
          else {
            paramBundle = "????";
          }
        }
        else {
          paramBundle = "??????";
        }
      }
      else {
        paramBundle = "??????";
      }
      input_client_title_type.setText(paramBundle);
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getCount().equals("")) {
        input_case_issue.setText(((com.lawyer_smartCalendar.data.h)localObject1).getCount());
      }
      g = ((com.lawyer_smartCalendar.data.h)localObject1).i();
      paramBundle = "";
      localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).i();
      j = ((String)localObject3).hashCode();
      if (j != -1851041679) {
        if (j != 73298585)
        {
          break label1130;
          break label1130;
          if (j != 2010341507) {
            break label1181;
          }
        }
      }
      label1130:
      while (!((String)localObject3).equals("Record"))
      {
        do
        {
          while (!((String)localObject3).equals("Criminal")) {}
          j = 1;
          break;
        } while (!((String)localObject3).equals("Legal"));
        j = 0;
        break;
      }
      j = 2;
      break label1183;
      label1181:
      j = -1;
      label1183:
      if (j != 0)
      {
        if (j != 1)
        {
          if (j == 2) {
            paramBundle = "????";
          }
        }
        else {
          paramBundle = "?????";
        }
      }
      else {
        paramBundle = "?????";
      }
      input_case_status.setText(paramBundle);
      f = ((com.lawyer_smartCalendar.data.h)localObject1).getText();
      paramBundle = ((com.lawyer_smartCalendar.data.h)localObject1).getText();
      j = paramBundle.hashCode();
      if (j != -1808122976)
      {
        break label1260;
        if (j != -609016686) {
          break label1293;
        }
      }
      label1260:
      while (!paramBundle.equals("Stream"))
      {
        while (!paramBundle.equals("Finished")) {}
        j = 1;
        break;
      }
      j = 0;
      break label1295;
      label1293:
      j = -1;
      label1295:
      if (j != 0)
      {
        if (j == 1)
        {
          input_case_process.setTextColor(Color.parseColor("#757575"));
          input_case_process.setText("??????");
        }
      }
      else
      {
        input_case_process.setTextColor(Color.parseColor("#E65100"));
        input_case_process.setText("??????");
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).d().equals("")) {
        input_case_number.setText(((com.lawyer_smartCalendar.data.h)localObject1).d());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).e().equals("")) {
        input_archive_number.setText(((com.lawyer_smartCalendar.data.h)localObject1).e());
      }
      input_court_city.setText((CharSequence)localObject2);
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getName().equals("")) {
        input_court_branch.setText(((com.lawyer_smartCalendar.data.h)localObject1).getName());
      }
      h = ((com.lawyer_smartCalendar.data.h)localObject1).getId();
      paramBundle = ((com.lawyer_smartCalendar.data.h)localObject1).getId();
      switch (paramBundle.hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (paramBundle.equals("8"))
        {
          j = 7;
          break label1662;
          if (paramBundle.equals("7"))
          {
            j = 6;
            break label1662;
            if (paramBundle.equals("6"))
            {
              j = 5;
              break label1662;
              if (paramBundle.equals("5"))
              {
                j = 4;
                break label1662;
                if (paramBundle.equals("4"))
                {
                  j = 3;
                  break label1662;
                  if (paramBundle.equals("3"))
                  {
                    j = 2;
                    break label1662;
                    if (paramBundle.equals("2"))
                    {
                      j = 1;
                      break label1662;
                      if (paramBundle.equals("1"))
                      {
                        j = 0;
                        break label1662;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      j = -1;
      switch (j)
      {
      default: 
        break;
      case 7: 
        input_court_type.setText("????? ????? ?????");
        break;
      case 6: 
        input_court_type.setText("????? ???? ????");
        break;
      case 5: 
        input_court_type.setText("????? ?????");
        break;
      case 4: 
        input_court_type.setText("??????");
        break;
      case 3: 
        input_court_type.setText("?? ??????");
        break;
      case 2: 
        input_court_type.setText("??????");
        break;
      case 1: 
        input_court_type.setText("????? ???");
        break;
      case 0: 
        label1662:
        input_court_type.setText("????");
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).f().equals("")) {
        input_lawsuit_info.setText(((com.lawyer_smartCalendar.data.h)localObject1).f());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getValue().equals("")) {
        input_attorney_letter_number.setText(((com.lawyer_smartCalendar.data.h)localObject1).getValue());
      }
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
      localObject1 = b;
      ((com.lawyer_smartCalendar.utils.h)localObject1).setEnabled();
      localObject1 = b;
      paramBundle = ((com.lawyer_smartCalendar.utils.h)localObject1).set(paramBundle);
      localObject1 = b;
      ((com.lawyer_smartCalendar.utils.h)localObject1).close();
      localObject1 = input_client_name;
      ((TextView)localObject1).setText(paramBundle.getValue());
      localObject1 = input_client_name;
      ((TextView)localObject1).setEnabled(false);
      j = paramBundle.add();
      p = j;
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
      
    case 2131296511: 
      if (paramBoolean)
      {
        input_court_type.callOnClick();
        return;
      }
      break;
    case 2131296510: 
      if (paramBoolean)
      {
        input_court_city.callOnClick();
        return;
      }
      break;
    case 2131296507: 
      if (paramBoolean)
      {
        input_client_title_type.callOnClick();
        return;
      }
      break;
    case 2131296504: 
      if (paramBoolean)
      {
        input_client_name.callOnClick();
        return;
      }
      break;
    case 2131296499: 
      if (paramBoolean)
      {
        input_case_title.callOnClick();
        return;
      }
      break;
    case 2131296498: 
      if (paramBoolean)
      {
        input_case_status.callOnClick();
        return;
      }
      break;
    case 2131296497: 
      if (paramBoolean)
      {
        input_case_process.callOnClick();
        return;
      }
      break;
    case 2131296495: 
      if (paramBoolean)
      {
        input_case_issue.callOnClick();
        return;
      }
      break;
    case 2131296494: 
      if (paramBoolean) {
        input_case_date.callOnClick();
      }
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_case.callOnClick();
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
  
  class a
    implements c
  {
    a() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class b
    implements View.OnClickListener
  {
    b() {}
    
    public void onClick(View paramView)
    {
      finish();
    }
  }
  
  class c
    implements c
  {
    c() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog = AddCaseActivity.this;
      paramMaterialDialog.startActivity(new Intent(paramMaterialDialog, PayActivity.class));
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
    implements MaterialDialog.ListCallbackSingleChoice
  {
    e() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_court_type.setText(paramCharSequence);
      paramMaterialDialog = AddCaseActivity.this;
      paramView = new StringBuilder();
      paramView.append(paramInt + 1);
      paramView.append("");
      AddCaseActivity.d(paramMaterialDialog, paramView.toString());
      return true;
    }
  }
  
  class f
    implements MaterialDialog.ListCallbackSingleChoice
  {
    f() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_case_title.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3) {
              return true;
            }
            AddCaseActivity.b(AddCaseActivity.this, "Bank");
            return true;
          }
          AddCaseActivity.b(AddCaseActivity.this, "Challenge");
          return true;
        }
        AddCaseActivity.b(AddCaseActivity.this, "Executive");
        return true;
      }
      AddCaseActivity.b(AddCaseActivity.this, "Normal");
      return true;
    }
  }
  
  class g
    implements MaterialDialog.ListCallbackSingleChoice
  {
    g() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_client_title_type.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3)
            {
              if (paramInt != 4)
              {
                if (paramInt != 5) {
                  return true;
                }
                AddCaseActivity.get(AddCaseActivity.this, "Reconsidered");
                return true;
              }
              AddCaseActivity.get(AddCaseActivity.this, "Revisionist");
              return true;
            }
            AddCaseActivity.get(AddCaseActivity.this, "Accused");
            return true;
          }
          AddCaseActivity.get(AddCaseActivity.this, "Plaintiff");
          return true;
        }
        AddCaseActivity.get(AddCaseActivity.this, "Read");
        return true;
      }
      AddCaseActivity.get(AddCaseActivity.this, "Applicant");
      return true;
    }
  }
  
  class h
    implements MaterialDialog.ListCallbackSingleChoice
  {
    h() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_case_status.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2) {
            return true;
          }
          AddCaseActivity.a(AddCaseActivity.this, "Record");
          return true;
        }
        AddCaseActivity.a(AddCaseActivity.this, "Criminal");
        return true;
      }
      AddCaseActivity.a(AddCaseActivity.this, "Legal");
      return true;
    }
  }
  
  class i
    implements MaterialDialog.ListCallbackSingleChoice
  {
    i() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_case_process.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return true;
        }
        AddCaseActivity.c(AddCaseActivity.this, "Finished");
        input_case_process.setTextColor(Color.parseColor("#757575"));
        return true;
      }
      AddCaseActivity.c(AddCaseActivity.this, "Stream");
      input_case_process.setTextColor(Color.parseColor("#E65100"));
      return true;
    }
  }
  
  class j
    implements c
  {
    j() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddCaseActivity.this, AddClientActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
}
