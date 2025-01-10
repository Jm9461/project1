package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.joanzapata.material.widget.ImageView;
import com.lawyer_smartCalendar.data.Frame;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.h;
import com.shawnlin.numberpicker.NumberPicker;
import com.shawnlin.numberpicker.NumberPicker.e;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddTaxActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener
{
  private String a = "";
  private h b;
  public Button btn_add_tax;
  public EditText input_Income;
  public EditText input_comment;
  public EditText input_net_income;
  public EditText input_tax;
  public EditText input_year;
  private String op = "add";
  
  public AddTaxActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onClick(final View paramView)
  {
    int i = paramView.getId();
    if (i != 2131296347)
    {
      if (i != 2131296552) {
        return;
      }
      paramView = new android.support.design.widget.Dialog(this);
      View localView = getLayoutInflater().inflate(2131492935, null);
      final NumberPicker localNumberPicker = (NumberPicker)localView.findViewById(2131296653);
      ImageView localImageView = (ImageView)localView.findViewById(2131296475);
      if (input_year.getText().toString().equals("")) {
        localNumberPicker.setValue(1397);
      } else {
        localNumberPicker.setValue(Integer.parseInt(input_year.getText().toString()));
      }
      localNumberPicker.setOnValueChangedListener(new d());
      localImageView.setOnClickListener(new e(localNumberPicker, paramView));
      paramView.setContentView(localView);
      paramView.show();
      return;
    }
    i = 0;
    if (input_year.getText().toString().equals(""))
    {
      input_year.setError("??? ?? ?????? ????.");
      i = 1;
    }
    if (input_Income.getText().toString().equals("0"))
    {
      input_Income.setError("???? ????? ?? ???? ????.");
      i = 1;
    }
    if (input_tax.getText().toString().equals("0"))
    {
      input_tax.setError("?????? ?? ???? ????.");
      i = 1;
    }
    if (input_net_income.getText().toString().equals("0"))
    {
      input_net_income.setError("????? ???? ?? ???? ????.");
      i = 1;
    }
    if (i == 0)
    {
      paramView = new Frame();
      paramView.set(Integer.parseInt(input_year.getText().toString()));
      paramView.d(Long.parseLong(input_net_income.getText().toString()));
      paramView.get(Long.parseLong(input_tax.getText().toString()));
      paramView.set(Long.parseLong(input_Income.getText().toString()));
      paramView.d(input_comment.getText().toString());
      b.setEnabled();
      if (op.equals("edit"))
      {
        b.a(paramView, a);
        new Collection().a(this, "success", "?? ?????? ?????? ??.");
      }
      else
      {
        b.getItem(paramView);
        new Collection().a(this, "success", "?? ?????? ??? ??.");
      }
      b.close();
      setResult(-1);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492904);
    ButterKnife.getView(this);
    b = new h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230856);
    paramBundle.setNavigationOnClickListener(new a());
    btn_add_tax.setOnClickListener(this);
    btn_add_tax.setVisibility(8);
    input_year.setOnClickListener(this);
    input_year.setOnFocusChangeListener(this);
    input_Income.addTextChangedListener(new b());
    input_tax.addTextChangedListener(new c());
    a = getIntent().getStringExtra("id");
    op = getIntent().getStringExtra("frmMode");
    if (op.equals("edit"))
    {
      b.setEnabled();
      paramBundle = b.copy(a);
      b.close();
      getSupportActionBar().setTitle("?????? ??????");
      EditText localEditText = input_year;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.d());
      localStringBuilder.append("");
      localEditText.setText(localStringBuilder.toString());
      input_year.setTextColor(Color.parseColor("#E65100"));
      localEditText = input_Income;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.getValue());
      localStringBuilder.append("");
      localEditText.setText(localStringBuilder.toString());
      localEditText = input_tax;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.get());
      localStringBuilder.append("");
      localEditText.setText(localStringBuilder.toString());
      localEditText = input_net_income;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramBundle.b());
      localStringBuilder.append("");
      localEditText.setText(localStringBuilder.toString());
      input_comment.setText(paramBundle.f());
      input_year.setEnabled(false);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558409, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramView.getId() != 2131296552) {
      return;
    }
    if (paramBoolean)
    {
      input_year.setError(null);
      input_year.callOnClick();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_tax.callOnClick();
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
    implements TextWatcher
  {
    b() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if (!input_tax.getText().toString().equals(""))
      {
        paramEditable = input_Income;
        try
        {
          long l1 = Long.parseLong(paramEditable.getText().toString());
          paramEditable = input_tax;
          long l2 = Long.parseLong(paramEditable.getText().toString());
          paramEditable = input_net_income;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(l1 - l2);
          localStringBuilder.append("");
          paramEditable.setText(localStringBuilder.toString());
          paramEditable = input_net_income;
          paramEditable.setError(null);
          return;
        }
        catch (Exception paramEditable) {}
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  class c
    implements TextWatcher
  {
    c() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if (!input_Income.getText().toString().equals(""))
      {
        paramEditable = input_Income;
        try
        {
          long l1 = Long.parseLong(paramEditable.getText().toString());
          paramEditable = input_tax;
          long l2 = Long.parseLong(paramEditable.getText().toString());
          paramEditable = input_net_income;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(l1 - l2);
          localStringBuilder.append("");
          paramEditable.setText(localStringBuilder.toString());
          paramEditable = input_net_income;
          paramEditable.setError(null);
          return;
        }
        catch (Exception paramEditable) {}
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  class d
    implements NumberPicker.e
  {
    d() {}
    
    public void onValueChange(NumberPicker paramNumberPicker, int paramInt1, int paramInt2) {}
  }
  
  class e
    implements View.OnClickListener
  {
    e(NumberPicker paramNumberPicker, android.support.design.widget.Dialog paramDialog) {}
    
    public void onClick(View paramView)
    {
      paramView = input_year;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localNumberPicker.getValue());
      localStringBuilder.append("");
      paramView.setText(localStringBuilder.toString());
      input_year.setTextColor(Color.parseColor("#E65100"));
      paramView.dismiss();
    }
  }
}
