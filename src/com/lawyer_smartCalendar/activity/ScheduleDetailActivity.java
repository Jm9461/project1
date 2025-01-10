package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.d;
import android.support.design.widget.TabLayout.g;
import android.support.design.widget.TabLayout.h;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import com.lawyer_smartCalendar.data.Log;
import com.lawyer_smartCalendar.package_2.MainActivity.ScreenSlidePagerAdapter;
import com.lawyer_smartCalendar.ui.Item;
import com.lawyer_smartCalendar.utils.NotificationHelper;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.e;
import com.lawyer_smartCalendar.utils.f;
import com.lawyer_smartCalendar.utils.h;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ScheduleDetailActivity
  extends AppCompatActivity
{
  public String a = "";
  private Log b;
  private h c;
  private boolean d = false;
  private int img = 150;
  private TabLayout mTabLayout;
  private ViewPager pager;
  
  public ScheduleDetailActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == img) && (paramInt2 == -1))
    {
      onCreate();
      d = true;
    }
  }
  
  public void onBackPressed()
  {
    if (d)
    {
      e.i.close();
      setResult(-1);
    }
    finish();
  }
  
  public void onCreate()
  {
    pager = ((ViewPager)findViewById(2131296660));
    MainActivity.ScreenSlidePagerAdapter localScreenSlidePagerAdapter = new MainActivity.ScreenSlidePagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
    pager.setOffscreenPageLimit(2);
    pager.setAdapter(localScreenSlidePagerAdapter);
    pager.addOnPageChangeListener(new TabLayout.h(mTabLayout));
    pager.setCurrentItem(1);
    mTabLayout.setOnTabSelectedListener(new d());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492921);
    c = new h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    int i = 0;
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    a = getIntent().getStringExtra("id");
    c.setEnabled();
    b = c.getInstance(a);
    c.close();
    NotificationHelper.cancelNotification(this, Integer.parseInt(a));
    Object localObject = b.get();
    int j = ((String)localObject).hashCode();
    if (j != -1688280549)
    {
      break label155;
      if (j != 1705285671) {
        break label186;
      }
    }
    label155:
    while (!((String)localObject).equals("Meeting"))
    {
      while (!((String)localObject).equals("ProceedingsTimes")) {}
      break;
    }
    i = 1;
    break label188;
    label186:
    i = -1;
    label188:
    if (i != 0)
    {
      if (i == 1) {
        getSupportActionBar().setTitle("?????? ??????");
      }
    }
    else {
      getSupportActionBar().setTitle("?????? ??????");
    }
    paramBundle.setNavigationOnClickListener(new c());
    mTabLayout = ((TabLayout)findViewById(2131296766));
    paramBundle = mTabLayout;
    localObject = paramBundle.newTab();
    ((TabLayout.g)localObject).setText("?????? ??????");
    paramBundle.addTab((TabLayout.g)localObject);
    paramBundle = mTabLayout;
    localObject = paramBundle.newTab();
    ((TabLayout.g)localObject).setText("???????");
    paramBundle.addTab((TabLayout.g)localObject);
    new f(this).a(this, mTabLayout);
    onCreate();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558413, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 2131296288) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    paramMenuItem = new MaterialDialog.Builder(this);
    paramMenuItem.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
    paramMenuItem.setText(GravityEnum.a);
    paramMenuItem.valueOf(GravityEnum.END);
    paramMenuItem.negativeColorRes(2131099708);
    paramMenuItem.content("??? ???? ??? ??? ???? ??????? ??????");
    paramMenuItem.positiveColorRes(2131099728);
    paramMenuItem.content(2131099730);
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
      ScheduleDetailActivity.c(ScheduleDetailActivity.this).setEnabled();
      paramMaterialDialog = ScheduleDetailActivity.c(ScheduleDetailActivity.this);
      paramDialogAction = ScheduleDetailActivity.this;
      paramMaterialDialog.save(paramDialogAction, a);
      ScheduleDetailActivity.c(ScheduleDetailActivity.this).close();
      paramMaterialDialog = ScheduleDetailActivity.this;
      try
      {
        paramMaterialDialog.setResult(-1);
        paramMaterialDialog = e.i;
        paramMaterialDialog.close();
      }
      catch (Exception paramMaterialDialog) {}
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
      onBackPressed();
    }
  }
  
  class d
    implements TabLayout.d
  {
    d() {}
    
    public void onPostExecute(TabLayout.g paramG)
    {
      ScheduleDetailActivity.access$getPager(ScheduleDetailActivity.this).setCurrentItem(paramG.getPosition());
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
}
