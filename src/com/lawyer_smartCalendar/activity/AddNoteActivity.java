package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.g;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import apps.axt.views.Label;
import butterknife.ButterKnife;
import cafe.adriel.androidaudiorecorder.Row;
import cafe.adriel.androidaudiorecorder.asynctasks.AllowedSolution;
import cafe.adriel.androidaudiorecorder.asynctasks.MathArrays.OrderDirection;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.Item;
import com.lawyer_smartCalendar.package_2.NavigationMenuPresenter;
import com.lawyer_smartCalendar.package_2.i;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.d;
import com.mohamadamin.persianmaterialdatetimepicker.date.CalendarDatePickerDialog.OnDateSetListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddNoteActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
  private static final String path;
  public LinearLayout TextInputLayout_case;
  public LinearLayout TextInputLayout_client;
  private String a = "";
  private com.lawyer_smartCalendar.package_2.h adapter;
  public int b = -1;
  public Button btn_add_audio;
  public Button btn_add_note;
  public int c = -1;
  private String d = "";
  private String date = "add";
  private android.support.design.widget.Dialog dialog;
  private com.mohamadamin.persianmaterialdatetimepicker.views.Calendar dueDate;
  private String id = "";
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_date_hour;
  public EditText input_note_body;
  public EditText input_note_date;
  public EditText input_note_title;
  public EditText input_note_type;
  private ArrayList<File> items = new ArrayList();
  private Label list;
  private DatePickerDialog mDatePickerDialog;
  private android.support.design.widget.Dialog mDialog;
  public RecyclerView recyclerView;
  private GravityEnum state = GravityEnum.END;
  private com.lawyer_smartCalendar.utils.h this$0;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().getPath());
    localStringBuilder.append("/recorded_audio.wav");
    path = localStringBuilder.toString();
  }
  
  public AddNoteActivity() {}
  
  private void onPostExecute(File paramFile)
  {
    items.add(paramFile);
    adapter.clear();
    recyclerView.scrollToPosition(items.size() - 1);
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void cancelDialog()
  {
    mDialog.dismiss();
  }
  
  public void closeDialog()
  {
    dialog.dismiss();
  }
  
  public void init(DatePickerDialog paramDatePickerDialog, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    Object localObject = mDatePickerDialog.getTag();
    if (((String)localObject).hashCode() == -707218514)
    {
      while (!((String)localObject).equals("input_date_note")) {}
      paramInt1 = 0;
    }
    else
    {
      paramInt1 = -1;
    }
    if (paramInt1 != 0) {
      return;
    }
    input_note_date.setText(paramDatePickerDialog.format());
    input_note_date.setTextColor(Color.parseColor("#E65100"));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
    ((StringBuilder)localObject).append("");
    a = ((StringBuilder)localObject).toString();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 200)
    {
      if (paramInt2 == -1)
      {
        paramIntent = new StringBuilder();
        paramIntent.append(list.getName());
        paramIntent.append("/");
        paramIntent.append(getPackageName());
        paramIntent.append("/audio/");
        paramIntent = paramIntent.toString();
        list.update(paramIntent);
        com.mohamadamin.persianmaterialdatetimepicker.views.Calendar localCalendar = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramIntent);
        localStringBuilder.append("audio(");
        localStringBuilder.append(localCalendar.getTimeInMillis());
        localStringBuilder.append(").wav");
        paramIntent = localStringBuilder.toString();
        list.copy(path, paramIntent);
        onPostExecute(new File(Uri.decode(paramIntent)));
        return;
      }
      if (paramInt2 == 0) {
        Toast.makeText(this, "??? ??? ????? ???.", 0).show();
      }
    }
  }
  
  public void onBackPressed()
  {
    if ((items.size() > 0) && (date.equals("add")))
    {
      int i = 0;
      while (i < items.size())
      {
        list.remove(((File)items.get(i)).getPath());
        i += 1;
      }
    }
    finish();
  }
  
  public void onClick(View paramView)
  {
    Object localObject;
    java.util.List localList;
    switch (paramView.getId())
    {
    default: 
      
    case 2131296537: 
      input_note_type.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.negativeColorRes(2131099708);
      paramView.setText(state);
      paramView.title("??? ???????");
      paramView.items(2130903051);
      paramView.itemsCallbackSingleChoice(-1, new g());
      paramView.show();
      return;
    case 2131296535: 
      input_note_date.setError(null);
      dueDate = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      mDatePickerDialog = DatePickerDialog.newInstance(this, dueDate.get(), dueDate.set(), dueDate.getTime());
      mDatePickerDialog.show(getFragmentManager(), "input_date_note");
      return;
    case 2131296514: 
      paramView = java.util.Calendar.getInstance();
      TimePickerDialog.newInstance(this, paramView.get(11), paramView.get(12), true).show(getFragmentManager(), "input_time");
      return;
    case 2131296504: 
      input_client_name.setError(null);
      paramView = getLayoutInflater().inflate(2131492934, null);
      localObject = (RecyclerView)paramView.findViewById(2131296682);
      ((RecyclerView)localObject).setLayoutManager(new LinearLayoutManager(this));
      this$0.setEnabled();
      localList = this$0.search();
      this$0.close();
      if (localList.size() > 0)
      {
        ((RecyclerView)localObject).setAdapter(new i(this, "AddNoteActivity", localList));
        dialog = new android.support.design.widget.Dialog(this);
        dialog.setContentView(paramView);
        dialog.show();
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.setText(state);
      paramView.valueOf(state);
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
      if (c == -1)
      {
        new Collection().a(this, "error", "????? ?? ???? ?????? ????.");
        return;
      }
      input_case.setError(null);
      paramView = getLayoutInflater().inflate(2131492933, null);
      localObject = (RecyclerView)paramView.findViewById(2131296680);
      ((RecyclerView)localObject).setLayoutManager(new LinearLayoutManager(this));
      this$0.setEnabled();
      localList = this$0.initialize(c);
      this$0.close();
      if (localList.size() > 0)
      {
        ((RecyclerView)localObject).setAdapter(new NavigationMenuPresenter(this, "AddNoteActivity", localList));
        mDialog = new android.support.design.widget.Dialog(this);
        mDialog.setContentView(paramView);
        mDialog.show();
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.setText(state);
      paramView.valueOf(state);
      paramView.negativeColorRes(2131099708);
      paramView.content("??? ?????? ?? ???? ??? ???? ??? ???? ???.???? ????? ??? ?????? ?? ?????? ??? ????.");
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.positiveText("???? ??");
      paramView.negativeText("??? ??????");
      paramView.callback(new f());
      paramView.onPositive(new e());
      paramView.show();
      return;
    case 2131296346: 
      int j = 0;
      if (input_note_title.getText().toString().equals(""))
      {
        input_note_title.setError("????? ??????? ?? ???? ????.");
        j = 1;
      }
      if (d.equals(""))
      {
        input_note_type.setError("??? ??????? ?? ?????? ????.");
        j = 1;
      }
      int i = j;
      if (c == -1)
      {
        i = j;
        if (d.equals("ClientCase"))
        {
          input_client_name.setError("???? ?? ?????? ????.");
          i = 1;
        }
      }
      j = i;
      if (b == -1)
      {
        j = i;
        if (d.equals("ClientCase"))
        {
          input_case.setError("?????? ?? ?????? ????.");
          j = 1;
        }
      }
      if (a.equals(""))
      {
        input_note_date.setError("????? ??????? ?? ?????? ????.");
        j = 1;
      }
      if (input_date_hour.getText().toString().equals(""))
      {
        input_date_hour.setError("???? ??????? ?? ?????? ????.");
        j = 1;
      }
      if (input_note_body.getText().toString().equals(""))
      {
        input_note_body.setError("??? ??????? ?? ???? ????.");
        j = 1;
      }
      if (j == 0)
      {
        paramView = new com.lawyer_smartCalendar.data.List();
        paramView.e(input_note_title.getText().toString());
        paramView.append(d);
        paramView.append(c);
        paramView.add(b);
        paramView.add(a);
        paramView.d(input_note_body.getText().toString());
        this$0.setEnabled();
        if (date.equals("edit"))
        {
          this$0.write(paramView, id);
          if (items.size() > 0)
          {
            this$0.remove(id, com.lawyer_smartCalendar.utils.c.c);
            paramView = new ArrayList();
            i = 0;
            while (i < items.size())
            {
              localObject = new Item();
              ((Item)localObject).set(Integer.parseInt(id));
              ((Item)localObject).a("");
              ((Item)localObject).set(((File)items.get(i)).getPath());
              ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.c);
              paramView.add(localObject);
              i += 1;
            }
            this$0.a(paramView);
          }
          if (items.size() == 0) {
            this$0.remove(id, com.lawyer_smartCalendar.utils.c.c);
          }
          new Collection().a(this, "success", "??????? ?? ?????? ?????? ??.");
        }
        else
        {
          long l = this$0.write(paramView);
          if (items.size() > 0)
          {
            paramView = new ArrayList();
            i = 0;
            while (i < items.size())
            {
              localObject = new Item();
              ((Item)localObject).set((int)l);
              ((Item)localObject).a("");
              ((Item)localObject).set(((File)items.get(i)).getPath());
              ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.c);
              paramView.add(localObject);
              i += 1;
            }
            this$0.a(paramView);
          }
          new Collection().a(this, "success", "??????? ?? ?????? ??? ??.");
        }
        this$0.close();
        setResult(-1);
        finish();
        return;
      }
      break;
    case 2131296339: 
      open();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492901);
    ButterKnife.getView(this);
    list = new Label(getApplicationContext());
    this$0 = new com.lawyer_smartCalendar.utils.h(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    Object localObject1 = (Toolbar)findViewById(2131296838);
    ((View)localObject1).setPadding(0, 0, 0, 0);
    ((Toolbar)localObject1).setContentInsetsAbsolute(0, 0);
    setSupportActionBar((Toolbar)localObject1);
    ((Toolbar)localObject1).setNavigationIcon(2131230856);
    ((Toolbar)localObject1).setNavigationOnClickListener(new a());
    btn_add_note.setOnClickListener(this);
    btn_add_note.setVisibility(8);
    btn_add_audio.setOnClickListener(this);
    input_client_name.setOnClickListener(this);
    input_client_name.setOnFocusChangeListener(this);
    input_case.setOnClickListener(this);
    input_case.setOnFocusChangeListener(this);
    input_note_type.setOnClickListener(this);
    input_note_type.setOnFocusChangeListener(this);
    input_note_date.setOnClickListener(this);
    input_note_date.setOnFocusChangeListener(this);
    input_date_hour.setOnClickListener(this);
    input_date_hour.setOnFocusChangeListener(this);
    input_note_body.setOnClickListener(this);
    input_note_body.setOnFocusChangeListener(this);
    dueDate = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
    input_note_date.setText(dueDate.format());
    input_note_date.setTextColor(Color.parseColor("#E65100"));
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(dueDate.getTimeInMillis());
    ((StringBuilder)localObject1).append("");
    a = ((StringBuilder)localObject1).toString();
    date = getIntent().getStringExtra("frmMode");
    id = getIntent().getStringExtra("id");
    Object localObject2;
    Object localObject3;
    Object localObject4;
    int i;
    Object localObject5;
    if (date.equals("edit"))
    {
      localObject1 = null;
      localObject2 = null;
      this$0.setEnabled();
      localObject3 = this$0.create(id);
      if (((com.lawyer_smartCalendar.data.List)localObject3).getString().equals("ClientCase"))
      {
        localObject1 = this$0;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.List)localObject3).size());
        ((StringBuilder)localObject2).append("");
        localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject2).toString());
        localObject2 = this$0;
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.List)localObject3).a());
        ((StringBuilder)localObject4).append("");
        localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a(((StringBuilder)localObject4).toString());
      }
      localObject4 = this$0.search(id, com.lawyer_smartCalendar.utils.c.c);
      this$0.close();
      i = 0;
      while (i < ((java.util.List)localObject4).size())
      {
        localObject5 = new File(Uri.decode(((Item)((java.util.List)localObject4).get(i)).getId()));
        items.add(localObject5);
        i += 1;
      }
      if (((com.lawyer_smartCalendar.data.List)localObject3).getString().equals("ClientCase"))
      {
        input_client_name.setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
        c = ((com.lawyer_smartCalendar.data.f)localObject1).add();
        localObject1 = "";
        localObject4 = ((com.lawyer_smartCalendar.data.h)localObject2).getString();
        switch (((String)localObject4).hashCode())
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          if (((String)localObject4).equals("Executive"))
          {
            i = 1;
            break label756;
            if (((String)localObject4).equals("Bank"))
            {
              i = 3;
              break label756;
              if (((String)localObject4).equals("Challenge"))
              {
                i = 2;
                break label756;
                if (((String)localObject4).equals("Normal"))
                {
                  i = 0;
                  break label756;
                }
              }
            }
          }
        }
        i = -1;
        label756:
        if (i != 0)
        {
          if (i != 1)
          {
            if (i != 2)
            {
              if (i == 3) {
                localObject1 = "?????";
              }
            }
            else {
              localObject1 = "???????/??????";
            }
          }
          else {
            localObject1 = "??????";
          }
        }
        else {
          localObject1 = "????";
        }
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("?????: ");
        ((StringBuilder)localObject4).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()))));
        localObject2 = ((StringBuilder)localObject4).toString();
        localObject4 = input_case;
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append((String)localObject1);
        ((StringBuilder)localObject5).append(" - ");
        ((StringBuilder)localObject5).append((String)localObject2);
        ((TextView)localObject4).setText(((StringBuilder)localObject5).toString());
        input_case.setTextColor(Color.parseColor("#E65100"));
        b = ((com.lawyer_smartCalendar.data.List)localObject3).a();
      }
      getSupportActionBar().setTitle("?????? ???????");
      btn_add_note.setText("?????? ???????");
      input_note_title.setText(((com.lawyer_smartCalendar.data.List)localObject3).get());
      localObject2 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject2).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)localObject3).getId()));
      input_note_date.setText(((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject2).format());
      input_note_date.setTextColor(Color.parseColor("#E65100"));
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(((GregorianCalendar)localObject2).getTimeInMillis());
      ((StringBuilder)localObject1).append("");
      a = ((StringBuilder)localObject1).toString();
      localObject1 = java.util.Calendar.getInstance();
      ((java.util.Calendar)localObject1).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)localObject3).getId()));
      ((GregorianCalendar)localObject2).get(11);
      ((GregorianCalendar)localObject2).get(12);
      localObject2 = input_date_hour;
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append(((java.util.Calendar)localObject1).get(11));
      ((StringBuilder)localObject4).append(":");
      ((StringBuilder)localObject4).append(((java.util.Calendar)localObject1).get(12));
      ((TextView)localObject2).setText(((StringBuilder)localObject4).toString());
      d = ((com.lawyer_smartCalendar.data.List)localObject3).getString();
      localObject1 = ((com.lawyer_smartCalendar.data.List)localObject3).getString();
      i = ((String)localObject1).hashCode();
      if (i != -1199578693) {
        if (i != 76517104)
        {
          break label1175;
          break label1175;
          if (i != 459619368) {
            break label1226;
          }
        }
      }
      label1175:
      while (!((String)localObject1).equals("ClientCase"))
      {
        do
        {
          while (!((String)localObject1).equals("DailyActivity")) {}
          i = 0;
          break;
        } while (!((String)localObject1).equals("Other"));
        i = 2;
        break;
      }
      i = 1;
      break label1228;
      label1226:
      i = -1;
      label1228:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i == 2)
          {
            input_note_type.setText("????");
            TextInputLayout_client.setVisibility(8);
            TextInputLayout_case.setVisibility(8);
          }
        }
        else
        {
          input_note_type.setText("????/??????");
          input_note_type.setEnabled(false);
          input_client_name.setEnabled(false);
          input_case.setEnabled(false);
          TextInputLayout_client.setVisibility(0);
          TextInputLayout_case.setVisibility(0);
        }
      }
      else
      {
        input_note_type.setText("??? ??????");
        TextInputLayout_client.setVisibility(8);
        TextInputLayout_case.setVisibility(8);
      }
      input_note_body.setText(((com.lawyer_smartCalendar.data.List)localObject3).getValue());
      input_note_type.setEnabled(false);
    }
    try
    {
      localObject1 = getIntent().getStringExtra("frmStarter");
      localObject2 = getIntent().getStringExtra("id");
      i = ((String)localObject1).hashCode();
      boolean bool;
      if (i != -1357712437)
      {
        if (i != 3046192) {}
      }
      else
      {
        do
        {
          do
          {
            bool = ((String)localObject1).equals("case");
          } while (!bool);
          i = 0;
          break;
          bool = ((String)localObject1).equals("client");
        } while (!bool);
        i = 1;
        break label1462;
      }
      i = -1;
      label1462:
      if (i != 0)
      {
        if (i == 1)
        {
          d = "ClientCase";
          localObject1 = this$0;
          ((com.lawyer_smartCalendar.utils.h)localObject1).setEnabled();
          localObject1 = this$0;
          localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set((String)localObject2);
          localObject2 = this$0;
          ((com.lawyer_smartCalendar.utils.h)localObject2).close();
          localObject2 = input_client_name;
          ((TextView)localObject2).setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
          i = ((com.lawyer_smartCalendar.data.f)localObject1).add();
          c = i;
          localObject1 = input_note_type;
          ((TextView)localObject1).setText("????/??????");
          localObject1 = input_note_type;
          ((TextView)localObject1).setEnabled(false);
          localObject1 = input_client_name;
          ((TextView)localObject1).setEnabled(false);
          localObject1 = TextInputLayout_client;
          ((View)localObject1).setVisibility(0);
          localObject1 = TextInputLayout_case;
          ((View)localObject1).setVisibility(0);
        }
      }
      else
      {
        d = "ClientCase";
        localObject1 = this$0;
        ((com.lawyer_smartCalendar.utils.h)localObject1).setEnabled();
        localObject1 = this$0;
        localObject3 = ((com.lawyer_smartCalendar.utils.h)localObject1).a((String)localObject2);
        localObject1 = this$0;
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.h)localObject3).c());
        ((StringBuilder)localObject4).append("");
        localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject4).toString());
        localObject4 = this$0;
        ((com.lawyer_smartCalendar.utils.h)localObject4).close();
        localObject4 = input_client_name;
        ((TextView)localObject4).setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
        i = ((com.lawyer_smartCalendar.data.f)localObject1).add();
        c = i;
        localObject1 = "";
        localObject4 = ((com.lawyer_smartCalendar.data.h)localObject3).getString();
        i = ((String)localObject4).hashCode();
        switch (i)
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          bool = ((String)localObject4).equals("Executive");
          if (bool)
          {
            i = 1;
            break label1868;
            bool = ((String)localObject4).equals("Bank");
            if (bool)
            {
              i = 3;
              break label1868;
              bool = ((String)localObject4).equals("Challenge");
              if (bool)
              {
                i = 2;
                break label1868;
                bool = ((String)localObject4).equals("Normal");
                if (bool)
                {
                  i = 0;
                  break label1868;
                }
              }
            }
          }
        }
        i = -1;
        label1868:
        if (i != 0)
        {
          if (i != 1)
          {
            if (i != 2)
            {
              if (i == 3) {
                localObject1 = "?????";
              }
            }
            else {
              localObject1 = "???????/??????";
            }
          }
          else {
            localObject1 = "??????";
          }
        }
        else {
          localObject1 = "????";
        }
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("?????: ");
        ((StringBuilder)localObject4).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject3).getIcon()))));
        localObject3 = ((StringBuilder)localObject4).toString();
        localObject4 = input_case;
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append((String)localObject1);
        ((StringBuilder)localObject5).append(" - ");
        ((StringBuilder)localObject5).append((String)localObject3);
        ((TextView)localObject4).setText(((StringBuilder)localObject5).toString());
        localObject1 = input_case;
        ((TextView)localObject1).setTextColor(Color.parseColor("#E65100"));
        i = Integer.parseInt((String)localObject2);
        b = i;
        localObject1 = input_note_type;
        ((TextView)localObject1).setText("????/??????");
        localObject1 = input_note_type;
        ((TextView)localObject1).setEnabled(false);
        localObject1 = input_client_name;
        ((TextView)localObject1).setEnabled(false);
        localObject1 = input_case;
        ((TextView)localObject1).setEnabled(false);
        localObject1 = TextInputLayout_client;
        ((View)localObject1).setVisibility(0);
        localObject1 = TextInputLayout_case;
        ((View)localObject1).setVisibility(0);
      }
    }
    catch (Exception localException) {}
    if (paramBundle != null) {
      items = ((ArrayList)paramBundle.getSerializable("audio_list"));
    }
    adapter = new com.lawyer_smartCalendar.package_2.h(this, list, items);
    paramBundle = new LinearLayoutManager(this);
    paramBundle.setOrientation(1);
    recyclerView.setLayoutManager(paramBundle);
    recyclerView.setAdapter(adapter);
    paramBundle = apps.api.a.f.a();
    b localB = new b();
    paramBundle.a(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "android.permission.WAKE_LOCK" }, localB);
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
      
    case 2131296537: 
      if (paramBoolean)
      {
        input_note_type.setError(null);
        input_note_type.callOnClick();
        return;
      }
      break;
    case 2131296535: 
      if (paramBoolean)
      {
        input_note_date.setError(null);
        input_note_date.callOnClick();
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
      btn_add_note.callOnClick();
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
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    apps.api.a.f.a().a(paramArrayOfString, paramArrayOfInt);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putSerializable("audio_list", items);
  }
  
  public void onTimeSet(RadialPickerLayout paramRadialPickerLayout, int paramInt1, int paramInt2)
  {
    paramRadialPickerLayout = java.util.Calendar.getInstance();
    paramRadialPickerLayout.setTimeInMillis(Long.parseLong(a));
    paramRadialPickerLayout.set(11, paramInt1);
    paramRadialPickerLayout.set(12, paramInt2);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramRadialPickerLayout.getTimeInMillis());
    localStringBuilder.append("");
    a = localStringBuilder.toString();
    paramRadialPickerLayout = input_date_hour;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt1);
    localStringBuilder.append(":");
    localStringBuilder.append(paramInt2);
    paramRadialPickerLayout.setText(localStringBuilder.toString());
  }
  
  public void open()
  {
    Row localRow = Row.get(this);
    localRow.delete(path);
    localRow.delete(Color.parseColor(d.getValue()));
    localRow.get(200);
    localRow.delete(cafe.adriel.androidaudiorecorder.asynctasks.c.c);
    localRow.delete(MathArrays.OrderDirection.c);
    localRow.delete(AllowedSolution.c);
    localRow.set(false);
    localRow.delete(true);
    localRow.set();
  }
  
  class a
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
  
  class b
    extends apps.api.a.h
  {
    b() {}
    
    public void b(String paramString)
    {
      Toast.makeText(AddNoteActivity.this, "???? ?????? ??? ???? ?? ?? ?????? ?????.", 0).show();
      finish();
    }
    
    public void l() {}
  }
  
  class c
    implements apps.afollestad.materialdialogs.c
  {
    c() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddNoteActivity.this, AddClientActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class d
    implements apps.afollestad.materialdialogs.c
  {
    d() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class e
    implements apps.afollestad.materialdialogs.c
  {
    e() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddNoteActivity.this, AddCaseActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class f
    implements apps.afollestad.materialdialogs.c
  {
    f() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class g
    implements MaterialDialog.ListCallbackSingleChoice
  {
    g() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_note_type.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2) {
            return true;
          }
          TextInputLayout_client.setVisibility(8);
          TextInputLayout_case.setVisibility(8);
          paramMaterialDialog = AddNoteActivity.this;
          c = -1;
          b = -1;
          input_case.setText("");
          input_client_name.setText("");
          AddNoteActivity.a(AddNoteActivity.this, "Other");
          return true;
        }
        TextInputLayout_client.setVisibility(0);
        TextInputLayout_case.setVisibility(0);
        AddNoteActivity.a(AddNoteActivity.this, "ClientCase");
        return true;
      }
      TextInputLayout_client.setVisibility(8);
      TextInputLayout_case.setVisibility(8);
      paramMaterialDialog = AddNoteActivity.this;
      c = -1;
      b = -1;
      input_case.setText("");
      input_client_name.setText("");
      AddNoteActivity.a(AddNoteActivity.this, "DailyActivity");
      return true;
    }
  }
}
