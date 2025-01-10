package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.d;
import android.support.design.widget.TabLayout.g;
import android.support.design.widget.TabLayout.h;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import apps.afollestad.materialdialogs.c;
import com.lawyer_smartCalendar.package_2.Album;
import com.lawyer_smartCalendar.ui.Fragment;
import com.lawyer_smartCalendar.ui.Item;
import com.lawyer_smartCalendar.ui.LogsFragment;
import com.lawyer_smartCalendar.ui.b;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.e;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ClientDetailActivity
  extends AppCompatActivity
{
  public String a = "";
  private com.lawyer_smartCalendar.data.f b = null;
  private com.lawyer_smartCalendar.utils.h c;
  private int d = 150;
  private boolean e = false;
  private GravityEnum f = GravityEnum.END;
  private TabLayout mTabLayout;
  private int menu_res = 2131558404;
  private ViewPager pager;
  
  public ClientDetailActivity() {}
  
  private void initUI()
  {
    pager = ((ViewPager)findViewById(2131296660));
    Album localAlbum = new Album(getSupportFragmentManager(), mTabLayout.getTabCount());
    pager.setOffscreenPageLimit(4);
    pager.setAdapter(localAlbum);
    pager.addOnPageChangeListener(new TabLayout.h(mTabLayout));
    pager.setCurrentItem(3);
    mTabLayout.setOnTabSelectedListener(new e());
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1)
    {
      initUI();
      e = true;
    }
  }
  
  public void onBackPressed()
  {
    if (e) {
      setResult(-1);
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492906);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      f = GravityEnum.END;
    } else {
      f = GravityEnum.START;
    }
    c = new com.lawyer_smartCalendar.utils.h(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setPopupTheme(2131820553);
    paramBundle.setNavigationOnClickListener(new d());
    a = getIntent().getStringExtra("id");
    c.setEnabled();
    b = c.set(a);
    c.close();
    mTabLayout = ((TabLayout)findViewById(2131296766));
    paramBundle = mTabLayout;
    TabLayout.g localG = paramBundle.newTab();
    localG.setText("??????? ??");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("?????");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("?????? ??");
    paramBundle.addTab(localG);
    paramBundle = mTabLayout;
    localG = paramBundle.newTab();
    localG.setText("??????");
    paramBundle.addTab(localG);
    new com.lawyer_smartCalendar.utils.f(this).a(this, mTabLayout);
    initUI();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(menu_res, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    Object localObject;
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 2131296612: 
      if (!b.getText().equals(""))
      {
        paramMenuItem = new StringBuilder();
        paramMenuItem.append("sms:");
        paramMenuItem.append(b.getText());
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramMenuItem.toString())));
        return true;
      }
      new Collection().a(this, "warning", "????? ???? ????? ??? ???? ???.");
      return true;
    case 2131296431: 
      if (!b.e().equals(""))
      {
        paramMenuItem = new StringBuilder();
        paramMenuItem.append("mailto:");
        paramMenuItem.append(b.e());
        paramMenuItem = new Intent("android.intent.action.SENDTO", Uri.parse(paramMenuItem.toString()));
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("???? ?? ??? ????????? : ");
        ((StringBuilder)localObject).append(b.getValue());
        startActivity(Intent.createChooser(paramMenuItem, ((StringBuilder)localObject).toString()));
        return true;
      }
      new Collection().a(this, "warning", "???? ??? ????????? ??? ???? ???.");
      return true;
    case 2131296367: 
      if (b.f().equals("real"))
      {
        paramMenuItem = new MaterialDialog.Builder(this);
        paramMenuItem.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
        paramMenuItem.append(f);
        paramMenuItem.setText(f);
        paramMenuItem.putString(f);
        paramMenuItem.setText(f);
        paramMenuItem.title("???? ?? ????");
        paramMenuItem.items(2130903046);
        paramMenuItem.itemsCallbackSingleChoice(-1, new c());
        paramMenuItem.show();
        return true;
      }
      if (!b.a().equals(""))
      {
        paramMenuItem = new Intent("android.intent.action.DIAL");
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("tel:");
        ((StringBuilder)localObject).append(b.a());
        paramMenuItem.setData(Uri.parse(((StringBuilder)localObject).toString()));
        startActivity(paramMenuItem);
        return true;
      }
      new Collection().a(this, "warning", "????? ???? ???? ??? ???? ???.");
      return true;
    case 2131296303: 
      paramMenuItem = new StringBuilder();
      paramMenuItem.append("");
      paramMenuItem.append("??? ? ??? ???????? : ");
      paramMenuItem.append(b.getValue());
      paramMenuItem.append("\n");
      paramMenuItem = paramMenuItem.toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("??? ??? : ");
      ((StringBuilder)localObject).append(b.d());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("?? ??? : ");
      ((StringBuilder)localObject).append(b.get());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("???? ????? : ");
      ((StringBuilder)localObject).append(b.getText());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("???? ???? : ");
      ((StringBuilder)localObject).append(b.a());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("??? ????????? : ");
      ((StringBuilder)localObject).append(b.e());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramMenuItem);
      ((StringBuilder)localObject).append("???? : ");
      ((StringBuilder)localObject).append(b.getTitle());
      ((StringBuilder)localObject).append("\n");
      paramMenuItem = ((StringBuilder)localObject).toString();
      localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.SEND");
      ((Intent)localObject).setType("text/plain");
      ((Intent)localObject).putExtra("android.intent.extra.TEXT", paramMenuItem);
      startActivity(Intent.createChooser((Intent)localObject, "?????? ????? ????"));
      return true;
    case 2131296290: 
      paramMenuItem = new Intent(this, AddClientActivity.class);
      paramMenuItem.putExtra("frmMode", "edit");
      paramMenuItem.putExtra("id", a);
      startActivityForResult(paramMenuItem, d);
      return true;
    }
    paramMenuItem = new MaterialDialog.Builder(this);
    paramMenuItem.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
    paramMenuItem.append(f);
    paramMenuItem.title("??? ???? ??? ??? ???? ??????? ??????");
    paramMenuItem.setText(f);
    paramMenuItem.valueOf(f);
    paramMenuItem.negativeColorRes(2131099708);
    paramMenuItem.content("?? ???? ??? ????? ??????? ????? ?? ???? ???? ?????? ?? ? ?????? ?? ? ????? ? ??????? ?? ? ????? ????? ??? ????? ??.");
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
      ClientDetailActivity.c(ClientDetailActivity.this).setEnabled();
      paramMaterialDialog = ClientDetailActivity.c(ClientDetailActivity.this);
      paramDialogAction = ClientDetailActivity.this;
      paramMaterialDialog.c(paramDialogAction, a);
      ClientDetailActivity.c(ClientDetailActivity.this).close();
      paramMaterialDialog = e.i;
      try
      {
        paramMaterialDialog.close();
        paramMaterialDialog = e.e;
        paramMaterialDialog.finish();
        paramMaterialDialog = e.f;
        paramMaterialDialog.addView();
        paramMaterialDialog = e.a;
        paramMaterialDialog.onCreateView();
        paramMaterialDialog = e.d;
        paramMaterialDialog.onCreateView();
        paramMaterialDialog = e.b;
        paramMaterialDialog.c();
      }
      catch (Exception paramMaterialDialog) {}
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
    implements MaterialDialog.ListCallbackSingleChoice
  {
    c() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return true;
        }
        if (!ClientDetailActivity.a(ClientDetailActivity.this).a().equals(""))
        {
          paramMaterialDialog = new Intent("android.intent.action.DIAL");
          paramView = new StringBuilder();
          paramView.append("tel:");
          paramView.append(ClientDetailActivity.a(ClientDetailActivity.this).a());
          paramMaterialDialog.setData(Uri.parse(paramView.toString()));
          startActivity(paramMaterialDialog);
          return true;
        }
        new Collection().a(ClientDetailActivity.this, "warning", "????? ???? ???? ??? ???? ???.");
        return true;
      }
      if (!ClientDetailActivity.a(ClientDetailActivity.this).getText().equals(""))
      {
        paramMaterialDialog = new Intent("android.intent.action.DIAL");
        paramView = new StringBuilder();
        paramView.append("tel:");
        paramView.append(ClientDetailActivity.a(ClientDetailActivity.this).getText());
        paramMaterialDialog.setData(Uri.parse(paramView.toString()));
        startActivity(paramMaterialDialog);
        return true;
      }
      new Collection().a(ClientDetailActivity.this, "warning", "????? ???? ????? ??? ???? ???.");
      return true;
    }
  }
  
  class d
    implements View.OnClickListener
  {
    d() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
  
  class e
    implements TabLayout.d
  {
    e() {}
    
    public void onPostExecute(TabLayout.g paramG)
    {
      ClientDetailActivity.access$getPager(ClientDetailActivity.this).setCurrentItem(paramG.getPosition());
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
}
