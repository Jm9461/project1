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
import android.widget.ScrollView;
import android.widget.TextView;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import apps.afollestad.materialdialogs.c;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.data.b;
import com.lawyer_smartCalendar.data.f;
import com.lawyer_smartCalendar.package_2.Item;
import com.lawyer_smartCalendar.package_2.NavigationMenuPresenter;
import com.lawyer_smartCalendar.package_2.i;
import com.lawyer_smartCalendar.utils.Collection;
import com.mohamadamin.persianmaterialdatetimepicker.date.CalendarDatePickerDialog.OnDateSetListener;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddPaymentActivity
  extends AppCompatActivity
  implements View.OnClickListener, View.OnFocusChangeListener, CalendarDatePickerDialog.OnDateSetListener
{
  private EditText a;
  private EditText b;
  public Button btn_add_honorarium;
  public Button btn_add_installment;
  public int c = -1;
  private String command = "add";
  private android.support.design.widget.Dialog d;
  private String desc = "";
  private android.support.design.widget.Dialog dialog;
  List<com.lawyer_smartCalendar.d.h> e = new ArrayList();
  private Calendar endDate;
  private Item h;
  private String i = "";
  public EditText input_case;
  public EditText input_client_name;
  public EditText input_date;
  public EditText input_detail;
  public EditText input_honorariumTotal;
  public EditText input_pay_type;
  public ScrollView layout_ScrollView;
  private DatePickerDialog mDatePickerDialog;
  private EditText mEditText;
  public RecyclerView recyclerView;
  private String s = "";
  private GravityEnum state = GravityEnum.END;
  private com.lawyer_smartCalendar.utils.h this$0;
  private String type = "";
  public int x = -1;
  
  public AddPaymentActivity() {}
  
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
    paramDatePickerDialog = new Calendar();
    paramDatePickerDialog.set(paramInt1, paramInt2, paramInt3);
    Object localObject = mDatePickerDialog.getTag();
    paramInt1 = ((String)localObject).hashCode();
    if (paramInt1 != -479361475)
    {
      break label43;
      if (paramInt1 != 715652002) {
        break label76;
      }
    }
    label43:
    while (!((String)localObject).equals("input_date_installment"))
    {
      while (!((String)localObject).equals("input_date_Honorarium")) {}
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
      mEditText.setText(paramDatePickerDialog.format());
      mEditText.setTextColor(Color.parseColor("#E65100"));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
      ((StringBuilder)localObject).append("");
      desc = ((StringBuilder)localObject).toString();
      return;
    }
    input_date.setText(paramDatePickerDialog.format());
    input_date.setTextColor(Color.parseColor("#E65100"));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramDatePickerDialog.getTimeInMillis());
    ((StringBuilder)localObject).append("");
    type = ((StringBuilder)localObject).toString();
  }
  
  public void onClick(View paramView)
  {
    Object localObject1;
    Object localObject2;
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296543: 
      input_pay_type.setError(null);
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.negativeColorRes(2131099707);
      paramView.setText(state);
      paramView.title("??? ??????");
      paramView.items(2130903052);
      paramView.itemsCallbackSingleChoice(-1, new g());
      paramView.show();
      return;
    case 2131296512: 
      input_date.setError(null);
      endDate = new Calendar();
      mDatePickerDialog = DatePickerDialog.newInstance(this, endDate.get(), endDate.set(), endDate.getTime());
      mDatePickerDialog.show(getFragmentManager(), "input_date_Honorarium");
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
        ((RecyclerView)localObject1).setAdapter(new i(this, "AddPaymentActivity", (List)localObject2));
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
      localObject1 = (RecyclerView)paramView.findViewById(2131296680);
      ((RecyclerView)localObject1).setLayoutManager(new LinearLayoutManager(this));
      this$0.setEnabled();
      localObject2 = this$0.initialize(c);
      this$0.close();
      if (((List)localObject2).size() > 0)
      {
        ((RecyclerView)localObject1).setAdapter(new NavigationMenuPresenter(this, "AddPaymentActivity", (List)localObject2));
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
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.content("??? ?????? ?? ???? ??? ???? ??? ???? ???.???? ????? ??? ?????? ?? ?????? ??? ????.");
      paramView.positiveText("???? ??");
      paramView.negativeText("??? ??????");
      paramView.callback(new f());
      paramView.onPositive(new e());
      paramView.show();
      return;
    case 2131296345: 
      localObject2 = getLayoutInflater().inflate(2131492966, null);
      mEditText = ((EditText)((View)localObject2).findViewById(2131296520));
      paramView = (TextView)((View)localObject2).findViewById(2131296786);
      localObject1 = (TextView)((View)localObject2).findViewById(2131296797);
      ((TextView)localObject1).setTextColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
      paramView.setTextColor(Color.parseColor("#757575"));
      a = ((EditText)((View)localObject2).findViewById(2131296522));
      a.setOnClickListener(this);
      a.setOnFocusChangeListener(this);
      b = ((EditText)((View)localObject2).findViewById(2131296521));
      b.setOnClickListener(this);
      b.setOnFocusChangeListener(this);
      mEditText.setOnClickListener(new h());
      mEditText.setOnFocusChangeListener(new i());
      MaterialDialog.Builder localBuilder = new MaterialDialog.Builder(this);
      localBuilder.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      localBuilder.append(state);
      localBuilder.setText(state);
      localBuilder.putString(state);
      localBuilder.negativeColorRes(2131099707);
      localBuilder.setText(state);
      localBuilder.customView((View)localObject2, true);
      localBuilder.title("?????? ???");
      localObject2 = localBuilder.show();
      ((View)localObject1).setOnClickListener(new j((android.app.Dialog)localObject2));
      paramView.setOnClickListener(new a((android.app.Dialog)localObject2));
      return;
    }
    int j = 0;
    if (type.equals(""))
    {
      input_date.setError("????? ?????? ?? ???? ????.");
      j = 1;
    }
    if (c == -1)
    {
      input_client_name.setError("???? ?? ?????? ????.");
      j = 1;
    }
    if (x == -1)
    {
      input_case.setError("?????? ?? ?????? ????.");
      j = 1;
    }
    if (s.equals(""))
    {
      input_pay_type.setError("??? ?????? ?? ???? ????.");
      j = 1;
    }
    int k = j;
    if (s.equals("installment"))
    {
      k = j;
      if (e.size() == 0)
      {
        new Collection().a(this, "error", "???? ????? ?? ???? ????.");
        k = 1;
      }
    }
    if (input_honorariumTotal.getText().toString().equals(""))
    {
      input_honorariumTotal.setError("???? ?? ??????? ?? ???? ????.");
      k = 1;
    }
    j = k;
    if (command.equals("add"))
    {
      this$0.setEnabled();
      paramView = this$0;
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(x);
      ((StringBuilder)localObject1).append("");
      boolean bool = paramView.isEnabled(((StringBuilder)localObject1).toString());
      this$0.close();
      j = k;
      if (bool)
      {
        new Collection().a(this, "error", "???? ??? ?????? ?? ??????? ??? ??? ???.");
        j = 1;
      }
    }
    if (j == 0)
    {
      paramView = new com.lawyer_smartCalendar.data.d();
      paramView.d(x);
      paramView.i(type);
      paramView.d(input_detail.getText().toString());
      paramView.c(input_honorariumTotal.getText().toString());
      paramView.append(s);
      paramView.e("honorarium");
      this$0.setEnabled();
      if (command.equals("edit"))
      {
        this$0.a(paramView, i);
        if ((s.equals("installment")) && (e.size() > 0)) {
          this$0.a(e, i);
        }
        new Collection().a(this, "success", "?? ??????? ?? ?????? ?????? ??.");
      }
      else
      {
        long l = this$0.write(paramView);
        if ((s.equals("installment")) && (e.size() > 0))
        {
          paramView = this$0;
          localObject1 = e;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(l);
          ((StringBuilder)localObject2).append("");
          paramView.c((List)localObject1, ((StringBuilder)localObject2).toString());
        }
        new Collection().a(this, "success", "?? ??????? ?? ?????? ??? ??.");
      }
      this$0.close();
      setResult(-1);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492902);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    this$0 = new com.lawyer_smartCalendar.utils.h(this);
    Object localObject1 = (Toolbar)findViewById(2131296838);
    ((View)localObject1).setPadding(0, 0, 0, 0);
    ((Toolbar)localObject1).setContentInsetsAbsolute(0, 0);
    setSupportActionBar((Toolbar)localObject1);
    ((Toolbar)localObject1).setNavigationIcon(2131230872);
    ((Toolbar)localObject1).setNavigationOnClickListener(new b());
    btn_add_honorarium.setOnClickListener(this);
    btn_add_honorarium.setVisibility(8);
    input_date.setOnClickListener(this);
    input_date.setOnFocusChangeListener(this);
    input_client_name.setOnClickListener(this);
    input_client_name.setOnFocusChangeListener(this);
    input_case.setOnClickListener(this);
    input_case.setOnFocusChangeListener(this);
    input_pay_type.setOnClickListener(this);
    input_pay_type.setOnFocusChangeListener(this);
    btn_add_installment.setOnClickListener(this);
    btn_add_installment.setVisibility(8);
    if (paramBundle != null) {
      e = ((ArrayList)paramBundle.getSerializable("payment_list"));
    }
    h = new Item(this, e);
    paramBundle = new LinearLayoutManager(this);
    paramBundle.setOrientation(1);
    recyclerView.setLayoutManager(paramBundle);
    recyclerView.setAdapter(h);
    localObject1 = new Calendar();
    input_date.setText(((Calendar)localObject1).format());
    input_date.setTextColor(Color.parseColor("#E65100"));
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((GregorianCalendar)localObject1).getTimeInMillis());
    ((StringBuilder)localObject2).append("");
    type = ((StringBuilder)localObject2).toString();
    i = getIntent().getStringExtra("id");
    command = getIntent().getStringExtra("frmMode");
    if (command.equals("edit"))
    {
      this$0.setEnabled();
      localObject1 = this$0.getItem(i);
      localObject2 = this$0;
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(((com.lawyer_smartCalendar.data.d)localObject1).b());
      ((StringBuilder)localObject3).append("");
      localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).a(((StringBuilder)localObject3).toString());
      localObject3 = this$0;
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.h)localObject2).c());
      ((StringBuilder)localObject4).append("");
      localObject3 = ((com.lawyer_smartCalendar.utils.h)localObject3).set(((StringBuilder)localObject4).toString());
      localObject4 = this$0;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(((com.lawyer_smartCalendar.data.d)localObject1).e());
      localStringBuilder.append("");
      e = ((com.lawyer_smartCalendar.utils.h)localObject4).load(localStringBuilder.toString());
      this$0.close();
      getSupportActionBar().setTitle("?????? ?? ???????");
      h = new Item(this, e);
      paramBundle.setOrientation(1);
      recyclerView.setLayoutManager(paramBundle);
      recyclerView.setAdapter(h);
      input_client_name.setText(((f)localObject3).getValue());
      c = ((com.lawyer_smartCalendar.data.h)localObject2).c();
      input_client_name.setEnabled(false);
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
          break label768;
          if (((String)localObject3).equals("Bank"))
          {
            j = 3;
            break label768;
            if (((String)localObject3).equals("Challenge"))
            {
              j = 2;
              break label768;
              if (((String)localObject3).equals("Normal"))
              {
                j = 0;
                break label768;
              }
            }
          }
        }
      }
      int j = -1;
      label768:
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
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append(paramBundle);
      ((StringBuilder)localObject4).append(" - ");
      ((StringBuilder)localObject4).append((String)localObject2);
      ((TextView)localObject3).setText(((StringBuilder)localObject4).toString());
      input_case.setTextColor(Color.parseColor("#E65100"));
      x = ((com.lawyer_smartCalendar.data.d)localObject1).b();
      input_case.setEnabled(false);
      s = ((com.lawyer_smartCalendar.data.d)localObject1).getString();
      paramBundle = "";
      localObject2 = ((com.lawyer_smartCalendar.data.d)localObject1).getString();
      switch (((String)localObject2).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject2).equals("installment"))
        {
          j = 3;
          break label1072;
          if (((String)localObject2).equals("bankReceipt"))
          {
            j = 1;
            break label1072;
            if (((String)localObject2).equals("cardToCart"))
            {
              j = 2;
              break label1072;
              if (((String)localObject2).equals("cash"))
              {
                j = 0;
                break label1072;
              }
            }
          }
        }
      }
      j = -1;
      label1072:
      if (j != 0)
      {
        if (j != 1)
        {
          if (j != 2)
          {
            if (j == 3)
            {
              paramBundle = "??????";
              btn_add_installment.setVisibility(0);
            }
          }
          else
          {
            paramBundle = "???? ?? ????";
            btn_add_installment.setVisibility(8);
          }
        }
        else {
          paramBundle = "??? ?????";
        }
      }
      else
      {
        paramBundle = "????";
        btn_add_installment.setVisibility(8);
      }
      input_pay_type.setText(paramBundle);
      input_honorariumTotal.setText(((com.lawyer_smartCalendar.data.d)localObject1).getValue());
      input_detail.setText(((com.lawyer_smartCalendar.data.d)localObject1).getTitle());
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
      
    case 2131296543: 
      if (paramBoolean)
      {
        input_pay_type.callOnClick();
        return;
      }
      break;
    case 2131296512: 
      if (paramBoolean)
      {
        input_date.callOnClick();
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
    case 2131296493: 
      if (paramBoolean) {
        input_case.callOnClick();
      }
      break;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296278) {
      btn_add_honorarium.callOnClick();
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
    a(android.app.Dialog paramDialog) {}
    
    public void onClick(View paramView)
    {
      val$d.dismiss();
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
      paramMaterialDialog.dismiss();
      paramMaterialDialog = new Intent(AddPaymentActivity.this, AddClientActivity.class);
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
      paramMaterialDialog = new Intent(AddPaymentActivity.this, AddCaseActivity.class);
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
  
  class g
    implements MaterialDialog.ListCallbackSingleChoice
  {
    g() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      input_pay_type.setText(paramCharSequence);
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3) {
              return true;
            }
            AddPaymentActivity.a(AddPaymentActivity.this, "installment");
            btn_add_installment.setVisibility(0);
            return true;
          }
          AddPaymentActivity.a(AddPaymentActivity.this, "cardToCart");
          e.clear();
          AddPaymentActivity.a(AddPaymentActivity.this).clear();
          btn_add_installment.setVisibility(8);
          return true;
        }
        AddPaymentActivity.a(AddPaymentActivity.this, "bankReceipt");
        e.clear();
        AddPaymentActivity.a(AddPaymentActivity.this).clear();
        btn_add_installment.setVisibility(8);
        return true;
      }
      AddPaymentActivity.a(AddPaymentActivity.this, "cash");
      btn_add_installment.setVisibility(8);
      e.clear();
      AddPaymentActivity.a(AddPaymentActivity.this).clear();
      return true;
    }
  }
  
  class h
    implements View.OnClickListener
  {
    h() {}
    
    public void onClick(View paramView)
    {
      AddPaymentActivity.truncate(AddPaymentActivity.this, new Calendar());
      paramView = AddPaymentActivity.this;
      AddPaymentActivity.access$setMDatePickerDialog(paramView, DatePickerDialog.newInstance(paramView, AddPaymentActivity.access$getEndDate(paramView).get(), AddPaymentActivity.access$getEndDate(AddPaymentActivity.this).set(), AddPaymentActivity.access$getEndDate(AddPaymentActivity.this).getTime()));
      AddPaymentActivity.access$getMDatePickerDialog(AddPaymentActivity.this).show(getFragmentManager(), "input_date_installment");
    }
  }
  
  class i
    implements View.OnFocusChangeListener
  {
    i() {}
    
    public void onFocusChange(View paramView, boolean paramBoolean)
    {
      if (paramBoolean) {
        AddPaymentActivity.access$getMEditText(AddPaymentActivity.this).callOnClick();
      }
    }
  }
  
  class j
    implements View.OnClickListener
  {
    j(android.app.Dialog paramDialog) {}
    
    public void onClick(View paramView)
    {
      if (AddPaymentActivity.getDesc(AddPaymentActivity.this).equals(""))
      {
        new Collection().a(AddPaymentActivity.this, "error", "????? ??? ?? ???? ????.");
        return;
      }
      if (AddPaymentActivity.access$getA(AddPaymentActivity.this).getText().toString().equals(""))
      {
        new Collection().a(AddPaymentActivity.this, "error", "???? ??? ?? ???? ????.");
        return;
      }
      paramView = new b();
      paramView.add(AddPaymentActivity.getDesc(AddPaymentActivity.this));
      paramView.e(AddPaymentActivity.access$getA(AddPaymentActivity.this).getText().toString());
      paramView.d(AddPaymentActivity.access$getE(AddPaymentActivity.this).getText().toString());
      e.add(paramView);
      new Collection().a(AddPaymentActivity.this, "success", "??? ?? ?????? ????? ??.");
      AddPaymentActivity.a(AddPaymentActivity.this).clear();
      d.dismiss();
    }
  }
}
