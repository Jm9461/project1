package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.Item;
import com.lawyer_smartCalendar.data.Label;
import com.lawyer_smartCalendar.package_2.NavigationMenuPresenter;
import com.lawyer_smartCalendar.package_2.aa;
import com.lawyer_smartCalendar.package_2.i;
import com.lawyer_smartCalendar.utils.Collection;
import com.mohamadamin.persianmaterialdatetimepicker.date.CalendarDatePickerDialog.OnDateSetListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import lombok.objectweb.asm.a;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddClientDocumentsActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener
{
  private String a = "add";
  private aa adapter;
  private com.lawyer_smartCalendar.utils.h b;
  public Button btn_add_document;
  public Button btn_add_image;
  public int c = -1;
  private android.support.design.widget.Dialog d;
  private android.support.design.widget.Dialog dialog;
  private String e = "";
  private String id = "";
  public ImageView img_clear_date;
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_date_delivered;
  public EditText input_date_received;
  public EditText input_document_note;
  public EditText input_document_type;
  private ArrayList<File> items = new ArrayList();
  private String mTitle = "";
  public RecyclerView recyclerView;
  private com.mohamadamin.persianmaterialdatetimepicker.views.Calendar start;
  private GravityEnum state = GravityEnum.END;
  private DatePickerDialog this$0;
  private String type = "";
  public int x = -1;
  
  public AddClientDocumentsActivity() {}
  
  private void init(File paramFile)
  {
    items.add(paramFile);
    adapter.clear();
    recyclerView.scrollToPosition(items.size() - 1);
  }
  
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
    paramDatePickerDialog = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    Object localObject = this$0.getTag();
    paramInt1 = ((String)localObject).hashCode();
    if (paramInt1 != 979522120)
    {
      break label43;
      if (paramInt1 != 2140188573) {
        break label76;
      }
    }
    label43:
    while (!((String)localObject).equals("input_date_delivered"))
    {
      while (!((String)localObject).equals("input_date_received")) {}
      paramInt1 = 0;
      break;
    }
    paramInt1 = 1;
    break label78;
    label76:
    paramInt1 = -1;
    label78:
    if (paramInt1 != 0)
    {
      if (paramInt1 != 1) {
        return;
      }
      input_date_delivered.setText(paramDatePickerDialog.format());
      input_date_delivered.setTextColor(Color.parseColor("#E65100"));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
      ((StringBuilder)localObject).append("");
      type = ((StringBuilder)localObject).toString();
      return;
    }
    input_date_received.setText(paramDatePickerDialog.format());
    input_date_received.setTextColor(Color.parseColor("#E65100"));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
    ((StringBuilder)localObject).append("");
    mTitle = ((StringBuilder)localObject).toString();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    lombok.objectweb.asm.h.a(paramInt1, paramInt2, paramIntent, this, new a());
  }
  
  public void onClick(View paramView)
  {
    Object localObject;
    List localList;
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296518: 
      input_document_type.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.negativeColorRes(2131099708);
      paramView.setText(state);
      paramView.title("??? ???");
      paramView.items(2130903050);
      paramView.itemsCallbackSingleChoice(-1, new h());
      paramView.show();
      return;
    case 2131296515: 
      input_date_received.setError(null);
      start = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      this$0 = DatePickerDialog.newInstance(this, start.get(), start.set(), start.getTime());
      this$0.show(getFragmentManager(), "input_date_received");
      return;
    case 2131296513: 
      input_date_delivered.setError(null);
      start = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
      this$0 = DatePickerDialog.newInstance(this, start.get(), start.set(), start.getTime());
      this$0.show(getFragmentManager(), "input_date_delivered");
      return;
    case 2131296504: 
      input_client_name.setError(null);
      paramView = getLayoutInflater().inflate(2131492934, null);
      localObject = (RecyclerView)paramView.findViewById(2131296682);
      ((RecyclerView)localObject).setLayoutManager(new LinearLayoutManager(this));
      b.setEnabled();
      localList = b.search();
      b.close();
      if (localList.size() > 0)
      {
        ((RecyclerView)localObject).setAdapter(new i(this, "AddClientDocumentsActivity", localList));
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
      paramView.callback(new e());
      paramView.onPositive(new d());
      paramView.show();
      return;
    case 2131296493: 
      if (x == -1)
      {
        new Collection().a(this, "error", "????? ?? ???? ?????? ????.");
        return;
      }
      input_case.setError(null);
      paramView = getLayoutInflater().inflate(2131492933, null);
      localObject = (RecyclerView)paramView.findViewById(2131296680);
      ((RecyclerView)localObject).setLayoutManager(new LinearLayoutManager(this));
      b.setEnabled();
      localList = b.initialize(x);
      b.close();
      if (localList.size() > 0)
      {
        ((RecyclerView)localObject).setAdapter(new NavigationMenuPresenter(this, "AddClientDocumentsActivity", localList));
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
      paramView.callback(new g());
      paramView.onPositive(new f());
      paramView.show();
      return;
    case 2131296476: 
      type = "";
      input_date_delivered.setText("");
      return;
    case 2131296344: 
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.setText(state);
      paramView.title("?????? ?????");
      paramView.items(2130903053);
      paramView.itemsCallbackSingleChoice(-1, new i());
      paramView.show();
      return;
    }
    int i = 0;
    if (x == -1)
    {
      input_client_name.setError("???? ?? ?????? ????.");
      i = 1;
    }
    if (c == -1)
    {
      input_case.setError("?????? ?? ?????? ????.");
      i = 1;
    }
    if (e.equals(""))
    {
      input_document_type.setError("??? ??? ?? ?????? ????.");
      i = 1;
    }
    if (mTitle.equals(""))
    {
      input_date_received.setError("????? ?????? ??? ?? ?????? ????.");
      i = 1;
    }
    if (i == 0)
    {
      if ((!type.equals("")) && (!mTitle.equals("")))
      {
        paramView = java.util.Calendar.getInstance();
        localObject = java.util.Calendar.getInstance();
        java.util.Calendar.getInstance();
        paramView.setTimeInMillis(Long.parseLong(mTitle));
        ((java.util.Calendar)localObject).setTimeInMillis(Long.parseLong(type));
        if ((paramView.get(1) > ((java.util.Calendar)localObject).get(1)) || (paramView.get(2) > ((java.util.Calendar)localObject).get(2)) || (paramView.get(5) >= ((java.util.Calendar)localObject).get(5)))
        {
          new Collection().a(this, "error", "????? ????? ???? ????? ?? ????? ?????? ????.");
          return;
        }
      }
      paramView = new Label();
      paramView.b(x);
      paramView.a(c);
      paramView.b(e);
      paramView.put(mTitle);
      paramView.add(type);
      paramView.a(input_document_note.getText().toString());
      b.setEnabled();
      if (a.equals("edit"))
      {
        b.add(paramView, id);
        if (items.size() > 0)
        {
          b.remove(id, com.lawyer_smartCalendar.utils.c.d);
          paramView = new ArrayList();
          i = 0;
          while (i < items.size())
          {
            localObject = new Item();
            ((Item)localObject).set(Integer.parseInt(id));
            ((Item)localObject).a("");
            ((Item)localObject).set(((File)items.get(i)).getPath());
            ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.d);
            paramView.add(localObject);
            i += 1;
          }
          b.a(paramView);
        }
        if (items.size() == 0) {
          b.remove(id, com.lawyer_smartCalendar.utils.c.d);
        }
        new Collection().a(this, "success", "??? ?? ?????? ?????? ??.");
      }
      else
      {
        long l = b.write(paramView);
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
            ((Item)localObject).setTitle(com.lawyer_smartCalendar.utils.c.d);
            paramView.add(localObject);
            i += 1;
          }
          b.a(paramView);
        }
        new Collection().a(this, "success", "??? ?? ?????? ??? ??.");
      }
      b.close();
      setResult(-1);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492899);
    ButterKnife.getView(this);
    b = new com.lawyer_smartCalendar.utils.h(this);
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
    ((Toolbar)localObject1).setNavigationOnClickListener(new b());
    btn_add_document.setOnClickListener(this);
    btn_add_image.setOnClickListener(this);
    img_clear_date.setOnClickListener(this);
    btn_add_document.setVisibility(8);
    input_client_name.setOnClickListener(this);
    input_client_name.setOnFocusChangeListener(this);
    input_case.setOnClickListener(this);
    input_case.setOnFocusChangeListener(this);
    input_document_type.setOnClickListener(this);
    input_document_type.setOnFocusChangeListener(this);
    input_date_received.setOnClickListener(this);
    input_date_received.setOnFocusChangeListener(this);
    input_date_delivered.setOnClickListener(this);
    input_date_delivered.setOnFocusChangeListener(this);
    id = getIntent().getStringExtra("id");
    a = getIntent().getStringExtra("frmMode");
    Object localObject2;
    Object localObject3;
    Object localObject4;
    int i;
    Object localObject5;
    if (a.equals("edit"))
    {
      getSupportActionBar().setTitle("?????? ????? ? ????? ??????");
      btn_add_document.setText("?????? ???");
      b.setEnabled();
      localObject2 = b.f(id);
      localObject1 = b;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(((Label)localObject2).d());
      ((StringBuilder)localObject3).append("");
      localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject3).toString());
      localObject3 = b;
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append(((Label)localObject2).a());
      ((StringBuilder)localObject4).append("");
      localObject3 = ((com.lawyer_smartCalendar.utils.h)localObject3).a(((StringBuilder)localObject4).toString());
      localObject4 = b.search(id, com.lawyer_smartCalendar.utils.c.d);
      b.close();
      i = 0;
      while (i < ((List)localObject4).size())
      {
        localObject5 = new File(Uri.decode(((Item)((List)localObject4).get(i)).getId()));
        items.add(localObject5);
        i += 1;
      }
      input_client_name.setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
      x = ((Label)localObject2).d();
      input_client_name.setEnabled(false);
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject3).getString();
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject1).equals("Executive"))
        {
          i = 1;
          break label648;
          if (((String)localObject1).equals("Bank"))
          {
            i = 3;
            break label648;
            if (((String)localObject1).equals("Challenge"))
            {
              i = 2;
              break label648;
              if (((String)localObject1).equals("Normal"))
              {
                i = 0;
                break label648;
              }
            }
          }
        }
      }
      i = -1;
      label648:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3) {
              localObject1 = "";
            } else {
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
      input_case.setTextColor(Color.parseColor("#E65100"));
      c = ((Label)localObject2).a();
      input_case.setEnabled(false);
      e = ((Label)localObject2).getColor();
      localObject1 = ((Label)localObject2).getColor();
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject1).equals("SejliDocument"))
        {
          i = 2;
          break label964;
          if (((String)localObject1).equals("Other"))
          {
            i = 3;
            break label964;
            if (((String)localObject1).equals("Check"))
            {
              i = 0;
              break label964;
              if (((String)localObject1).equals("MarriageCertificate"))
              {
                i = 1;
                break label964;
              }
            }
          }
        }
      }
      i = -1;
      label964:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i == 3) {
              input_document_type.setText("????");
            }
          }
          else {
            input_document_type.setText("????? ????");
          }
        }
        else {
          input_document_type.setText("??? ??????");
        }
      }
      else {
        input_document_type.setText("??");
      }
      if (!((Label)localObject2).getName().equals(""))
      {
        mTitle = ((Label)localObject2).getName();
        localObject1 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
        ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).setTimeInMillis(Long.parseLong(((Label)localObject2).getName()));
        input_date_received.setText(((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).format());
        input_date_received.setTextColor(Color.parseColor("#E65100"));
      }
      else
      {
        input_date_delivered.setText("");
        input_date_received.setText("");
        type = "";
        mTitle = "";
      }
      if (!((Label)localObject2).getText().equals(""))
      {
        type = ((Label)localObject2).getText();
        localObject1 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
        ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).setTimeInMillis(Long.parseLong(((Label)localObject2).getText()));
        input_date_delivered.setText(((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).format());
        input_date_delivered.setTextColor(Color.parseColor("#E65100"));
      }
      else
      {
        type = "";
        input_date_delivered.setText("");
      }
      input_document_note.setText(((Label)localObject2).getValue());
    }
    try
    {
      localObject2 = getIntent().getStringExtra("frmStarter");
      localObject1 = getIntent().getStringExtra("id");
      i = ((String)localObject2).hashCode();
      boolean bool;
      if (i == 3046192)
      {
        do
        {
          bool = ((String)localObject2).equals("case");
        } while (!bool);
        i = 0;
      }
      else
      {
        i = -1;
      }
      if (i == 0)
      {
        localObject2 = b;
        ((com.lawyer_smartCalendar.utils.h)localObject2).setEnabled();
        localObject2 = b;
        localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a((String)localObject1);
        localObject1 = b;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(((com.lawyer_smartCalendar.data.h)localObject2).c());
        ((StringBuilder)localObject3).append("");
        localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject3).toString());
        localObject3 = b;
        ((com.lawyer_smartCalendar.utils.h)localObject3).close();
        localObject3 = input_client_name;
        ((TextView)localObject3).setText(((com.lawyer_smartCalendar.data.f)localObject1).getValue());
        i = ((com.lawyer_smartCalendar.data.f)localObject1).add();
        x = i;
        localObject1 = input_client_name;
        ((TextView)localObject1).setEnabled(false);
        localObject1 = "";
        localObject3 = ((com.lawyer_smartCalendar.data.h)localObject2).getString();
        i = ((String)localObject3).hashCode();
        switch (i)
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          bool = ((String)localObject3).equals("Executive");
          if (bool)
          {
            i = 1;
            break label1568;
            bool = ((String)localObject3).equals("Bank");
            if (bool)
            {
              i = 3;
              break label1568;
              bool = ((String)localObject3).equals("Challenge");
              if (bool)
              {
                i = 2;
                break label1568;
                bool = ((String)localObject3).equals("Normal");
                if (bool)
                {
                  i = 0;
                  break label1568;
                }
              }
            }
          }
        }
        i = -1;
        label1568:
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
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("?????: ");
        ((StringBuilder)localObject3).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()))));
        localObject3 = ((StringBuilder)localObject3).toString();
        localObject4 = input_case;
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append((String)localObject1);
        ((StringBuilder)localObject5).append(" - ");
        ((StringBuilder)localObject5).append((String)localObject3);
        ((TextView)localObject4).setText(((StringBuilder)localObject5).toString());
        localObject1 = input_case;
        ((TextView)localObject1).setTextColor(Color.parseColor("#E65100"));
        i = ((com.lawyer_smartCalendar.data.h)localObject2).a();
        c = i;
        localObject1 = input_case;
        ((TextView)localObject1).setEnabled(false);
      }
    }
    catch (Exception localException) {}
    if (paramBundle != null) {
      items = ((ArrayList)paramBundle.getSerializable("easy_image_photos_list"));
    }
    adapter = new aa(this, recyclerView, items);
    paramBundle = new GridLayoutManager(this, 3, 1, false);
    recyclerView.setLayoutManager(paramBundle);
    recyclerView.setAdapter(adapter);
    paramBundle = apps.api.a.f.a();
    c localC = new c();
    paramBundle.a(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE" }, localC);
    paramBundle = lombok.objectweb.asm.h.i(this);
    paramBundle.setValue("DocumentImage");
    paramBundle.setEnabled(false);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558409, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    lombok.objectweb.asm.h.setEnabled(this);
    super.onDestroy();
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    switch (paramView.getId())
    {
    default: 
      
    case 2131296518: 
      if (paramBoolean)
      {
        input_document_type.setError(null);
        input_document_type.callOnClick();
        return;
      }
      break;
    case 2131296515: 
      if (paramBoolean)
      {
        input_date_received.setError(null);
        input_date_received.callOnClick();
        return;
      }
      break;
    case 2131296513: 
      if (paramBoolean)
      {
        input_date_delivered.setError(null);
        input_date_delivered.callOnClick();
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
      btn_add_document.callOnClick();
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
    paramBundle.putSerializable("easy_image_photos_list", items);
  }
  
  class a
    implements lombok.objectweb.asm.d
  {
    a() {}
    
    public void a(Exception paramException, lombok.objectweb.asm.c paramC, int paramInt)
    {
      paramException.printStackTrace();
    }
    
    public void b(File paramFile, lombok.objectweb.asm.c paramC, int paramInt)
    {
      AddClientDocumentsActivity.c(AddClientDocumentsActivity.this, paramFile);
    }
    
    public void b(lombok.objectweb.asm.c paramC, int paramInt)
    {
      if ((paramC == lombok.objectweb.asm.c.b) || (paramC == lombok.objectweb.asm.c.d))
      {
        paramC = lombok.objectweb.asm.h.a(AddClientDocumentsActivity.this);
        if (paramC != null) {
          paramC.delete();
        }
      }
    }
  }
  
  class b
    implements View.OnClickListener
  {
    b() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
  
  class c
    extends apps.api.a.h
  {
    c() {}
    
    public void b(String paramString)
    {
      Toast.makeText(AddClientDocumentsActivity.this, "???? ?????? ??? ???? ?? ?? ?????? ?????.", 0).show();
      finish();
    }
    
    public void l() {}
  }
  
  class d
    implements apps.afollestad.materialdialogs.c
  {
    d() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddClientDocumentsActivity.this, AddClientActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class e
    implements apps.afollestad.materialdialogs.c
  {
    e() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class f
    implements apps.afollestad.materialdialogs.c
  {
    f() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddClientDocumentsActivity.this, AddCaseActivity.class);
      paramMaterialDialog.putExtra("frmMode", "add");
      startActivity(paramMaterialDialog);
    }
  }
  
  class g
    implements apps.afollestad.materialdialogs.c
  {
    g() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class h
    implements MaterialDialog.ListCallbackSingleChoice
  {
    h() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_document_type.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3) {
              return true;
            }
            AddClientDocumentsActivity.a(AddClientDocumentsActivity.this, "Other");
            return true;
          }
          AddClientDocumentsActivity.a(AddClientDocumentsActivity.this, "SejliDocument");
          return true;
        }
        AddClientDocumentsActivity.a(AddClientDocumentsActivity.this, "MarriageCertificate");
        return true;
      }
      AddClientDocumentsActivity.a(AddClientDocumentsActivity.this, "Check");
      return true;
    }
  }
  
  class i
    implements MaterialDialog.ListCallbackSingleChoice
  {
    i() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return true;
        }
        lombok.objectweb.asm.h.c(AddClientDocumentsActivity.this, 0);
        return true;
      }
      lombok.objectweb.asm.h.setTitle(AddClientDocumentsActivity.this, 0);
      return true;
    }
  }
}
