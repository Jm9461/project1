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
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddDailyActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener
{
  private static final String path;
  public LinearLayout TextInputLayout_case;
  public LinearLayout TextInputLayout_client;
  private Calendar a;
  private com.lawyer_smartCalendar.package_2.h adapter;
  private String b = "";
  public Button btn_add_audio;
  public Button btn_add_note;
  public int c = -1;
  private android.support.design.widget.Dialog d;
  private DatePickerDialog dialog;
  public int f = -1;
  private String i = "";
  private String id = "";
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_note_body;
  public EditText input_note_date;
  public EditText input_note_title;
  public EditText input_note_type;
  private ArrayList<File> items = new ArrayList();
  private Label list;
  private android.support.design.widget.Dialog progressDialog;
  public RecyclerView recyclerView;
  private GravityEnum state = GravityEnum.END;
  private com.lawyer_smartCalendar.utils.h this$0;
  private String title = "add";
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().getPath());
    localStringBuilder.append("/recorded_audio.wav");
    path = localStringBuilder.toString();
  }
  
  public AddDailyActivity() {}
  
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
  
  public void init(DatePickerDialog paramDatePickerDialog, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePickerDialog = new Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    Object localObject = dialog.getTag();
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
    b = ((StringBuilder)localObject).toString();
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
        Calendar localCalendar = new Calendar();
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
    if ((items.size() > 0) && (title.equals("add")))
    {
      int j = 0;
      while (j < items.size())
      {
        list.remove(((File)items.get(j)).getPath());
        j += 1;
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
      a = new Calendar();
      dialog = DatePickerDialog.newInstance(this, a.get(), a.set(), a.getTime());
      dialog.show(getFragmentManager(), "input_date_note");
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
        progressDialog = new android.support.design.widget.Dialog(this);
        progressDialog.setContentView(paramView);
        progressDialog.show();
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
        d = new android.support.design.widget.Dialog(this);
        d.setContentView(paramView);
        d.show();
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
      int k = 0;
      if (input_note_title.getText().toString().equals(""))
      {
        input_note_title.setError("????? ??????? ?? ???? ????.");
        k = 1;
      }
      if (i.equals(""))
      {
        input_note_type.setError("??? ??????? ?? ?????? ????.");
        k = 1;
      }
      int j = k;
      if (c == -1)
      {
        j = k;
        if (!i.equals("Other"))
        {
          input_client_name.setError("???? ?? ?????? ????.");
          j = 1;
        }
      }
      k = j;
      if (f == -1)
      {
        k = j;
        if (!i.equals("Other"))
        {
          input_case.setError("?????? ?? ?????? ????.");
          k = 1;
        }
      }
      if (b.equals(""))
      {
        input_note_date.setError("????? ??????? ?? ?????? ????.");
        k = 1;
      }
      if (input_note_body.getText().toString().equals(""))
      {
        input_note_body.setError("??? ??????? ?? ???? ????.");
        k = 1;
      }
      if (k == 0)
      {
        paramView = new com.lawyer_smartCalendar.data.List();
        paramView.e(input_note_title.getText().toString());
        paramView.append(i);
        paramView.append(c);
        paramView.add(f);
        paramView.add(b);
        paramView.d(input_note_body.getText().toString());
        this$0.setEnabled();
        if (title.equals("edit"))
        {
          this$0.write(paramView, id);
          if (items.size() > 0)
          {
            this$0.remove(id, com.lawyer_smartCalendar.utils.c.c);
            paramView = new ArrayList();
            j = 0;
            while (j < items.size())
            {
              localObject = new Item();
              ((Item)localObject).set(Integer.parseInt(id));
              ((Item)localObject).a("");
              ((Item)localObject).set(((File)items.get(j)).getPath());
              ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.c);
              paramView.add(localObject);
              j += 1;
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
            j = 0;
            while (j < items.size())
            {
              localObject = new Item();
              ((Item)localObject).set((int)l);
              ((Item)localObject).a("");
              ((Item)localObject).set(((File)items.get(j)).getPath());
              ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.c);
              paramView.add(localObject);
              j += 1;
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
    input_note_body.setOnClickListener(this);
    input_note_body.setOnFocusChangeListener(this);
    a = new Calendar();
    input_note_date.setText(a.format());
    input_note_date.setTextColor(Color.parseColor("#E65100"));
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(a.getTimeInMillis());
    ((StringBuilder)localObject1).append("");
    b = ((StringBuilder)localObject1).toString();
    title = getIntent().getStringExtra("frmMode");
    id = getIntent().getStringExtra("id");
    if (title.equals("edit"))
    {
      localObject1 = null;
      Object localObject2 = null;
      this$0.setEnabled();
      com.lawyer_smartCalendar.data.List localList = this$0.create(id);
      if (localList.getString().equals("ClientCase"))
      {
        localObject1 = this$0;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(localList.size());
        ((StringBuilder)localObject2).append("");
        localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject2).toString());
        localObject2 = this$0;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(localList.a());
        ((StringBuilder)localObject3).append("");
        localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a(((StringBuilder)localObject3).toString());
      }
      Object localObject3 = this$0.search(id, com.lawyer_smartCalendar.utils.c.c);
      this$0.close();
      int j = 0;
      Object localObject4;
      while (j < ((java.util.List)localObject3).size())
      {
        localObject4 = new File(Uri.decode(((Item)((java.util.List)localObject3).get(j)).getId()));
        items.add(localObject4);
        j += 1;
      }
      if (localList.getString().equals("ClientCase"))
      {
        input_client_name.setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
        c = ((com.lawyer_smartCalendar.data.f)localObject1).add();
        localObject1 = "";
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
            break label724;
            if (((String)localObject3).equals("Bank"))
            {
              j = 3;
              break label724;
              if (((String)localObject3).equals("Challenge"))
              {
                j = 2;
                break label724;
                if (((String)localObject3).equals("Normal"))
                {
                  j = 0;
                  break label724;
                }
              }
            }
          }
        }
        j = -1;
        label724:
        if (j != 0)
        {
          if (j != 1)
          {
            if (j != 2)
            {
              if (j == 3) {
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
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("?????: ");
        ((StringBuilder)localObject3).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()))));
        localObject2 = ((StringBuilder)localObject3).toString();
        localObject3 = input_case;
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append((String)localObject1);
        ((StringBuilder)localObject4).append(" - ");
        ((StringBuilder)localObject4).append((String)localObject2);
        ((TextView)localObject3).setText(((StringBuilder)localObject4).toString());
        input_case.setTextColor(Color.parseColor("#E65100"));
        f = localList.a();
      }
      getSupportActionBar().setTitle("?????? ???????");
      btn_add_note.setText("?????? ???????");
      input_note_title.setText(localList.get());
      localObject1 = new Calendar();
      ((Calendar)localObject1).setTimeInMillis(Long.parseLong(localList.getId()));
      input_note_date.setText(((Calendar)localObject1).format());
      input_note_date.setTextColor(Color.parseColor("#E65100"));
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((GregorianCalendar)localObject1).getTimeInMillis());
      ((StringBuilder)localObject2).append("");
      b = ((StringBuilder)localObject2).toString();
      i = localList.getString();
      localObject1 = localList.getString();
      j = ((String)localObject1).hashCode();
      if (j != -1199578693)
      {
        break label1031;
        if (j != 76517104) {
          break label1064;
        }
      }
      label1031:
      while (!((String)localObject1).equals("ClientCase"))
      {
        while (!((String)localObject1).equals("Other")) {}
        j = 1;
        break;
      }
      j = 0;
      break label1066;
      label1064:
      j = -1;
      label1066:
      if (j != 0)
      {
        if (j == 1)
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
      input_note_body.setText(localList.getValue());
      input_note_type.setEnabled(false);
    }
    if (paramBundle != null) {
      items = ((ArrayList)paramBundle.getSerializable("audio_list"));
    }
    adapter = new com.lawyer_smartCalendar.package_2.h(this, list, items);
    paramBundle = new LinearLayoutManager(this);
    paramBundle.setOrientation(1);
    recyclerView.setLayoutManager(paramBundle);
    recyclerView.setAdapter(adapter);
    paramBundle = apps.api.a.f.a();
    localObject1 = new b();
    paramBundle.a(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "android.permission.WAKE_LOCK" }, (apps.api.a.h)localObject1);
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
      Toast.makeText(AddDailyActivity.this, "???? ?????? ??? ???? ?? ?? ?????? ?????.", 0).show();
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
      paramMaterialDialog = new Intent(AddDailyActivity.this, AddClientActivity.class);
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
      paramMaterialDialog = new Intent(AddDailyActivity.this, AddCaseActivity.class);
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
        if (paramInt != 1) {
          return true;
        }
        TextInputLayout_client.setVisibility(8);
        TextInputLayout_case.setVisibility(8);
        paramMaterialDialog = AddDailyActivity.this;
        c = -1;
        f = -1;
        input_case.setText("");
        input_client_name.setText("");
        AddDailyActivity.a(AddDailyActivity.this, "Other");
        return true;
      }
      TextInputLayout_client.setVisibility(0);
      TextInputLayout_case.setVisibility(0);
      AddDailyActivity.a(AddDailyActivity.this, "ClientCase");
      return true;
    }
  }
}
