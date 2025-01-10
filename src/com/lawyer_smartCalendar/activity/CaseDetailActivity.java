package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.d;
import android.support.design.widget.TabLayout.g;
import android.support.design.widget.TabLayout.h;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import com.lawyer_smartCalendar.package_2.MainActivity.1;
import com.lawyer_smartCalendar.ui.Item;
import com.lawyer_smartCalendar.ui.LogsFragment;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.e;
import com.lawyer_smartCalendar.utils.f;
import com.lawyer_smartCalendar.utils.h;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CaseDetailActivity
  extends AppCompatActivity
{
  private h a;
  private boolean b = false;
  public String c = "";
  private int d = 250;
  private TabLayout mTabLayout;
  
  public CaseDetailActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void initUI()
  {
    final ViewPager localViewPager = (ViewPager)findViewById(2131296660);
    MainActivity.1 local1 = new MainActivity.1(getSupportFragmentManager(), mTabLayout.getTabCount());
    localViewPager.setOffscreenPageLimit(4);
    localViewPager.setAdapter(local1);
    localViewPager.addOnPageChangeListener(new TabLayout.h(mTabLayout));
    localViewPager.setCurrentItem(3);
    mTabLayout.setOnTabSelectedListener(new d(localViewPager));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1)
    {
      initUI();
      b = true;
    }
  }
  
  public void onBackPressed()
  {
    if (b) {
      setResult(-1);
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492905);
    a = new h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new c());
    c = getIntent().getStringExtra("id");
    findViewById(2131296422);
    mTabLayout = ((TabLayout)findViewById(2131296766));
    paramBundle = mTabLayout;
    TabLayout.g localG = paramBundle.newTab();
    localG.setText("????? ? ?????");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("??????? ??");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("?????");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("???????");
    paramBundle.addTab(localG);
    new f(this).a(this, mTabLayout);
    initUI();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558403, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 2131296288)
    {
      if (i != 2131296290) {
        return super.onOptionsItemSelected(paramMenuItem);
      }
      paramMenuItem = new Intent(this, AddCaseActivity.class);
      paramMenuItem.putExtra("frmMode", "edit");
      paramMenuItem.putExtra("id", c);
      startActivityForResult(paramMenuItem, d);
      return true;
    }
    paramMenuItem = new MaterialDialog.Builder(this);
    paramMenuItem.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
    paramMenuItem.setText(GravityEnum.a);
    paramMenuItem.valueOf(GravityEnum.END);
    paramMenuItem.negativeColorRes(2131099708);
    paramMenuItem.content("??? ???? ??? ??? ???? ??????? ??????");
    paramMenuItem.positiveColorRes(2131099728);
    paramMenuItem.content(2131099730);
    paramMenuItem.content("?? ???? ??? ????? ??????? ????? ?? ?????? ???? ?????? ?? ? ????? ? ??????? ?? ??????? ?? ? ????? ????? ??? ????? ??.");
    paramMenuItem.positiveText("??????");
    paramMenuItem.negativeText("???");
    paramMenuItem.callback(new b());
    paramMenuItem.onPositive(new a());
    paramMenuItem.show();
    return true;
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
      paramMaterialDialog = CaseDetailActivity.this;
      try
      {
        CaseDetailActivity.c(paramMaterialDialog).setEnabled();
        paramMaterialDialog = CaseDetailActivity.this;
        paramMaterialDialog = CaseDetailActivity.c(paramMaterialDialog);
        paramDialogAction = CaseDetailActivity.this;
        String str = c;
        paramMaterialDialog.a(paramDialogAction, str);
        paramMaterialDialog = CaseDetailActivity.this;
        CaseDetailActivity.c(paramMaterialDialog).close();
        paramMaterialDialog = e.f;
        paramMaterialDialog.addView();
        paramMaterialDialog = e.i;
        paramMaterialDialog.close();
      }
      catch (Exception paramMaterialDialog)
      {
        Toast.makeText(getApplicationContext(), paramMaterialDialog.toString(), 1).show();
      }
      setResult(-1);
      finish();
    }
  }
  
  class b
    implements c
  {
    b() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class c
    implements View.OnClickListener
  {
    c() {}
    
    public void onClick(View paramView)
    {
      finish();
    }
  }
  
  class d
    implements TabLayout.d
  {
    d(ViewPager paramViewPager) {}
    
    public void onPostExecute(TabLayout.g paramG)
    {
      localViewPager.setCurrentItem(paramG.getPosition());
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
}
