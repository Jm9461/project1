package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import apps.authenticator.wizard.runtime.Log;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.f;
import com.lawyer_smartCalendar.ui.LogsFragment;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.e;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddClientActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  private h a;
  public Button btn_add_client;
  private String data = "add";
  private String h = "real";
  private String i = "";
  public EditText input_client_address;
  public EditText input_client_email_address;
  public EditText input_client_father_name;
  public EditText input_client_landline_phone;
  public EditText input_client_name;
  public EditText input_client_national_code;
  public EditText input_client_phone_number;
  public TextInputLayout input_layout_client_name;
  public TextInputLayout input_layout_father_name;
  public TextInputLayout input_layout_national_code;
  public TextInputLayout input_layout_phone_number;
  public RadioButton radioButton_legal;
  public RadioButton radioButton_real;
  private GravityEnum state = GravityEnum.END;
  
  public AddClientActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296341) {
      return;
    }
    a.setEnabled();
    int j = a.search().size();
    a.close();
    if ((j == 200) && (!Log.getFilename("app_version", "limited").equals("full")))
    {
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(GravityEnum.a);
      paramView.setText(GravityEnum.a);
      paramView.valueOf(state);
      paramView.negativeColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
      paramView.title("");
      paramView.positiveColorRes(2131099728);
      paramView.neutralColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
      paramView.content("??????? ?????? ????? ????? ????????? ????");
      paramView.positiveText("???? ??");
      paramView.neutralText("??????? ?????? ????? ????? ????????? ????");
      paramView.callback(new e());
      paramView.onNegative(new d());
      paramView.show();
      return;
    }
    int k = 0;
    if (input_client_name.getText().toString().equals(""))
    {
      input_client_name.setError("???? ??? ? ??? ???????? ?? ???? ????.");
      k = 1;
    }
    j = k;
    if (!input_client_national_code.getText().toString().equals(""))
    {
      j = k;
      if (input_client_national_code.getText().toString().length() < 10)
      {
        input_client_national_code.setError("????? ??? ???? 10 ??? ????.");
        j = 1;
      }
    }
    k = j;
    if (input_client_phone_number.getText().toString().length() > 0)
    {
      k = j;
      if (input_client_phone_number.getText().toString().length() < 11)
      {
        input_client_phone_number.setError("????? ???? ????? ???? 11 ??? ????.");
        k = 1;
      }
    }
    if (k == 0)
    {
      paramView = new f();
      paramView.e(input_client_name.getText().toString());
      if (h.equals("real"))
      {
        paramView.a(input_client_father_name.getText().toString());
        paramView.add(input_client_national_code.getText().toString());
        paramView.set(input_client_phone_number.getText().toString());
      }
      else
      {
        paramView.a("");
        paramView.add("");
        paramView.set("");
      }
      paramView.i(input_client_landline_phone.getText().toString());
      paramView.update(input_client_email_address.getText().toString());
      paramView.setTitle(input_client_address.getText().toString());
      paramView.append(h);
      a.setEnabled();
      if (data.equals("edit"))
      {
        a.a(paramView, i);
        new Collection().a(this, "success", getResources().getString(2131755203));
      }
      else
      {
        a.a(paramView);
        new Collection().a(this, "success", getResources().getString(2131755204));
      }
      a.close();
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
    setContentView(2131492898);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    if (!Log.getFilename("app_version", "limited").equals("full")) {
      new Collection().a(this, "info", "??????? ?????? ????? ????? ????????? ????");
    }
    a = new h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230856);
    paramBundle.setNavigationOnClickListener(new a());
    btn_add_client.setOnClickListener(this);
    btn_add_client.setVisibility(8);
    radioButton_real.setOnCheckedChangeListener(new b());
    radioButton_legal.setOnCheckedChangeListener(new c());
    i = getIntent().getStringExtra("id");
    data = getIntent().getStringExtra("frmMode");
    if (data.equals("edit"))
    {
      a.setEnabled();
      paramBundle = a.set(i);
      a.close();
      getSupportActionBar().setTitle("?????? ????");
      input_client_name.setText(paramBundle.getValue());
      input_client_father_name.setText(paramBundle.d());
      input_client_national_code.setText(paramBundle.get());
      input_client_phone_number.setText(paramBundle.getText());
      input_client_landline_phone.setText(paramBundle.a());
      input_client_email_address.setText(paramBundle.e());
      input_client_address.setText(paramBundle.getTitle());
      h = paramBundle.f();
      if (paramBundle.f().equals("real"))
      {
        radioButton_real.setChecked(true);
        return;
      }
      radioButton_legal.setChecked(true);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558409, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_client.callOnClick();
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
  
  class b
    implements CompoundButton.OnCheckedChangeListener
  {
    b() {}
    
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        radioButton_legal.setChecked(false);
        AddClientActivity.a(AddClientActivity.this, "real");
        input_layout_national_code.setVisibility(0);
        input_layout_father_name.setVisibility(0);
        input_layout_phone_number.setVisibility(0);
      }
    }
  }
  
  class c
    implements CompoundButton.OnCheckedChangeListener
  {
    c() {}
    
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        radioButton_real.setChecked(false);
        AddClientActivity.a(AddClientActivity.this, "legal");
        input_layout_national_code.setVisibility(8);
        input_layout_father_name.setVisibility(8);
        input_layout_phone_number.setVisibility(8);
      }
    }
  }
  
  class d
    implements c
  {
    d() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog = AddClientActivity.this;
      paramMaterialDialog.startActivity(new Intent(paramMaterialDialog, PayActivity.class));
    }
  }
  
  class e
    implements c
  {
    e() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
}
