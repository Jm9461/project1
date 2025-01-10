package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.i;
import com.lawyer_smartCalendar.package_2.ClassReader;
import com.lawyer_smartCalendar.package_2.a;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.h;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddCityBankAccountActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener
{
  public String a = "";
  Button btn_add_account_number;
  private h c;
  private android.support.design.widget.Dialog dialog;
  private String dir = "add";
  public String f = "";
  private String id = "";
  public EditText input_account_number;
  public EditText input_select_city;
  public EditText input_select_type;
  private android.support.design.widget.Dialog this$0;
  Toolbar toolbar;
  
  public AddCityBankAccountActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void cancel(String paramString)
  {
    int i = paramString.hashCode();
    if (i != 3053931)
    {
      break label17;
      if (i != 3575610) {
        break label48;
      }
    }
    label17:
    while (!paramString.equals("city"))
    {
      while (!paramString.equals("type")) {}
      i = 0;
      break;
    }
    i = 1;
    break label50;
    label48:
    i = -1;
    label50:
    if (i != 0)
    {
      if (i != 1) {
        return;
      }
      dialog.dismiss();
      return;
    }
    this$0.dismiss();
  }
  
  public void onClick(View paramView)
  {
    RecyclerView localRecyclerView;
    Object localObject;
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296548: 
      paramView = getLayoutInflater().inflate(2131492930, null);
      localRecyclerView = (RecyclerView)paramView.findViewById(2131296686);
      localRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      c.setEnabled();
      localObject = c.export();
      c.close();
      localRecyclerView.setAdapter(new ClassReader(this, (String[][])localObject));
      this$0 = new android.support.design.widget.Dialog(this);
      this$0.setContentView(paramView);
      this$0.show();
      return;
    case 2131296547: 
      paramView = getLayoutInflater().inflate(2131492930, null);
      localRecyclerView = (RecyclerView)paramView.findViewById(2131296686);
      localRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      localObject = new h(this);
      ((h)localObject).setEnabled();
      String[][] arrayOfString = ((h)localObject).getValues();
      ((h)localObject).close();
      localRecyclerView.setAdapter(new a(this, "AddCityBankAccountActivity", arrayOfString));
      dialog = new android.support.design.widget.Dialog(this);
      dialog.setContentView(paramView);
      dialog.show();
      return;
    }
    int i = 0;
    if (a.equals(""))
    {
      input_select_city.setError(getResources().getString(2131755104));
      i = 1;
    }
    if (f.equals(""))
    {
      input_select_type.setError(getResources().getString(2131755103));
      i = 1;
    }
    if (input_account_number.getText().toString().equals(""))
    {
      input_account_number.setError(getResources().getString(2131755105));
      i = 1;
    }
    if (i == 0)
    {
      paramView = new i();
      paramView.c(Integer.parseInt(a));
      paramView.b(Integer.parseInt(f));
      paramView.a(input_account_number.getText().toString());
      c.setEnabled();
      if (dir.equals("edit"))
      {
        c.setEnabled(paramView, id);
        new Collection().a(this, "success", getResources().getString(2131755201));
      }
      else
      {
        c.setEnabled(paramView);
        new Collection().a(this, "success", getResources().getString(2131755202));
      }
      c.close();
      setResult(-1);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492897);
    ButterKnife.getView(this);
    c = new h(this);
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(2131230856);
    toolbar.setNavigationOnClickListener(new a());
    input_select_city.setOnClickListener(this);
    input_select_type.setOnClickListener(this);
    input_select_city.setOnFocusChangeListener(this);
    input_select_type.setOnFocusChangeListener(this);
    btn_add_account_number.setOnClickListener(this);
    btn_add_account_number.setVisibility(8);
    id = getIntent().getStringExtra("id");
    dir = getIntent().getStringExtra("frmMode");
    if (dir.equals("edit"))
    {
      c.setEnabled();
      paramBundle = c.setTitle(id);
      Object localObject1 = c;
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramBundle.size());
      ((StringBuilder)localObject2).append("");
      localObject1 = ((h)localObject1).remove(((StringBuilder)localObject2).toString());
      localObject2 = c;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.a());
      localStringBuilder.append("");
      localObject2 = ((h)localObject2).getCount(localStringBuilder.toString());
      c.close();
      getSupportActionBar().setTitle("?????? ????? ???? ????????");
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.size());
      localStringBuilder.append("");
      a = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.a());
      localStringBuilder.append("");
      f = localStringBuilder.toString();
      input_select_city.setText((CharSequence)localObject1);
      input_select_type.setText((CharSequence)localObject2);
      input_account_number.setText(paramBundle.get());
    }
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
      
    case 2131296548: 
      if (paramBoolean)
      {
        input_select_type.setError(null);
        input_select_type.callOnClick();
        return;
      }
      break;
    case 2131296547: 
      if (paramBoolean)
      {
        input_select_city.setError(null);
        input_select_city.callOnClick();
      }
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_account_number.callOnClick();
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
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      finish();
    }
  }
}
