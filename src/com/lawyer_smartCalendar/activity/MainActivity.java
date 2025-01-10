package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.b;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.d;
import android.support.design.widget.TabLayout.g;
import android.support.design.widget.TabLayout.h;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.b;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import apps.authenticator.wizard.runtime.Log;
import com.lawyer_smartCalendar.package_2.TabPagerAdapter;
import com.lawyer_smartCalendar.utils.ACRA;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.e;
import com.lawyer_smartCalendar.utils.h;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity
  extends AppCompatActivity
  implements NavigationView.b
{
  private NavigationView drawer;
  private DrawerLayout drawerLayout;
  public String e = "meeting";
  private View toolbar;
  
  public MainActivity() {}
  
  private void send(String paramString)
  {
    String str = getPackageName();
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.setType("text/plain");
    Object localObject = ACRA.c;
    int i = ((String)localObject).hashCode();
    if (i != -1395998121) {
      if (i != -710688120)
      {
        break label59;
        break label59;
        if (i != 104374574) {
          break label107;
        }
      }
    }
    label59:
    while (!((String)localObject).equals("bazaar"))
    {
      do
      {
        while (!((String)localObject).equals("myket")) {}
        i = 1;
        break;
      } while (!((String)localObject).equals("iranapps"));
      i = 2;
      break;
    }
    i = 0;
    break label109;
    label107:
    i = -1;
    label109:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i == 2)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("http://iranapps.ir/app/");
          ((StringBuilder)localObject).append(str);
          localIntent.putExtra("android.intent.extra.TEXT", ((StringBuilder)localObject).toString());
        }
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("http://myket.ir/app/");
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append("/?lang=fa");
        localIntent.putExtra("android.intent.extra.TEXT", ((StringBuilder)localObject).toString());
      }
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("https://cafebazaar.ir/app/");
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append("/?l=fa");
      localIntent.putExtra("android.intent.extra.TEXT", ((StringBuilder)localObject).toString());
    }
    startActivity(Intent.createChooser(localIntent, paramString));
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void execute()
  {
    new Collection().a(this, "success", "????? ???? ??? ???? ?????? ????.");
    Object localObject1 = ACRA.c;
    int i = -1;
    try
    {
      int j = ((String)localObject1).hashCode();
      if (j != -1395998121) {
        if (j != -710688120) {
          if (j != 104374574) {
            break label97;
          }
        }
      }
      boolean bool;
      do
      {
        do
        {
          do
          {
            bool = ((String)localObject1).equals("myket");
          } while (!bool);
          i = 1;
          break;
          bool = ((String)localObject1).equals("iranapps");
        } while (!bool);
        i = 2;
        break;
        bool = ((String)localObject1).equals("bazaar");
      } while (!bool);
      i = 0;
      label97:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2) {
            return;
          }
          localObject1 = new Intent("android.intent.action.VIEW");
          ((Intent)localObject1).setPackage("ir.tgbs.android.iranapps");
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("iranapps://app/");
          ((StringBuilder)localObject2).append(getPackageName());
          ((StringBuilder)localObject2).append("?a=comment&r=5");
          ((Intent)localObject1).setData(Uri.parse(((StringBuilder)localObject2).toString()));
          startActivity((Intent)localObject1);
          return;
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("myket://comment?id=");
        ((StringBuilder)localObject1).append(getPackageName());
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = new Intent();
        ((Intent)localObject2).setAction("android.intent.action.VIEW");
        ((Intent)localObject2).setData(Uri.parse((String)localObject1));
        startActivity((Intent)localObject2);
        return;
      }
      localObject1 = new Intent("android.intent.action.EDIT");
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("bazaar://details?id=");
      ((StringBuilder)localObject2).append(getPackageName());
      ((Intent)localObject1).setData(Uri.parse(((StringBuilder)localObject2).toString()));
      ((Intent)localObject1).setPackage("com.farsitel.bazaar");
      startActivity((Intent)localObject1);
      return;
    }
    catch (Exception localException) {}
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (drawerLayout.isDrawerOpen(8388611))
    {
      drawerLayout.closeDrawer(8388611);
      return;
    }
    super.onBackPressed();
  }
  
  public void onCreate()
  {
    TabLayout localTabLayout = (TabLayout)findViewById(2131296766);
    Object localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("???? ????");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("???????");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("??????? ?????");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("?????? ???? ?????");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("?????? ??");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("??????");
    localTabLayout.addTab((TabLayout.g)localObject);
    localObject = localTabLayout.newTab();
    ((TabLayout.g)localObject).setText("?????");
    localTabLayout.addTab((TabLayout.g)localObject);
    new com.lawyer_smartCalendar.utils.f(this).a(this, localTabLayout);
    localObject = (ViewPager)findViewById(2131296660);
    TabPagerAdapter localTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), localTabLayout.getTabCount());
    ((ViewPager)localObject).setOffscreenPageLimit(7);
    ((ViewPager)localObject).setAdapter(localTabPagerAdapter);
    ((ViewPager)localObject).addOnPageChangeListener(new TabLayout.h(localTabLayout));
    ((ViewPager)localObject).setCurrentItem(6);
    localTabLayout.setOnTabSelectedListener(new a((ViewPager)localObject));
    localTabLayout.setOnTabSelectedListener(new b((ViewPager)localObject));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    setContentView(2131492914);
    com.lawyer_smartCalendar.utils.HistoryLoader.INSTANCE = this;
    paramBundle = new h(this);
    if (!Log.get("installed", false)) {
      paramBundle.getItem();
    }
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    e.c = this;
    drawerLayout = ((DrawerLayout)findViewById(2131296422));
    paramBundle = new b(this, drawerLayout, paramBundle, 2131755179, 2131755178);
    drawerLayout.addDrawerListener(paramBundle);
    paramBundle.onCreate();
    drawer = ((NavigationView)findViewById(2131296642));
    toolbar = drawer.inflateHeaderView(2131493024);
    toolbar.setBackgroundColor(d.a());
    drawer.setNavigationItemSelectedListener(this);
    set();
    onCreate();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (!Log.getFilename("app_version", "limited").equals("full")) {
      getMenuInflater().inflate(2131558408, paramMenu);
    } else {
      getMenuInflater().inflate(2131558407, paramMenu);
    }
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onMenuItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131296640)
    {
      finish();
      startActivity(new Intent(this, SettingActivity.class));
    }
    else if (i == 2131296638)
    {
      startActivity(new Intent(this, ProfileActivity.class));
    }
    else if (i == 2131296637)
    {
      startActivity(new Intent(this, LinkActivity.class));
    }
    else if (i == 2131296641)
    {
      send("?????? ????? ?????? ?? ??????");
    }
    else if (i == 2131296639)
    {
      execute();
    }
    else if (i == 2131296635)
    {
      startActivity(new Intent(this, AboutActivity.class));
    }
    else if (i == 2131296636)
    {
      startActivity(new Intent(this, ContactActivity.class));
    }
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 2131296299)
    {
      if (i != 2131296301) {
        return super.onOptionsItemSelected(paramMenuItem);
      }
      if (!e.equals("none"))
      {
        paramMenuItem = new Intent(this, SearchActivity.class);
        paramMenuItem.putExtra("searchMode", e);
        startActivity(paramMenuItem);
        return true;
      }
    }
    else
    {
      startActivity(new Intent(this, PayActivity.class));
    }
    return true;
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    apps.api.a.f.a().a(paramArrayOfString, paramArrayOfInt);
  }
  
  public void set()
  {
    Object localObject = (TextView)toolbar.findViewById(2131296861);
    CircularImageView localCircularImageView = (CircularImageView)toolbar.findViewById(2131296477);
    ((TextView)localObject).setText(Log.getFilename("Profile_name", ""));
    if (!Log.getFilename("Profile_image", "").equals("")) {
      try
      {
        localObject = new File(Log.getFilename("Profile_image", ""));
        localObject = Picasso.with().load((File)localObject);
        ((RequestCreator)localObject).fit();
        ((RequestCreator)localObject).transform();
        ((RequestCreator)localObject).into(localCircularImageView);
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  class a
    implements TabLayout.d
  {
    a(ViewPager paramViewPager) {}
    
    public void onPostExecute(TabLayout.g paramG)
    {
      mViewPager.setCurrentItem(paramG.getPosition());
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
  
  class b
    implements TabLayout.d
  {
    b(ViewPager paramViewPager) {}
    
    public void onPostExecute(TabLayout.g paramG)
    {
      switch (paramG.getPosition())
      {
      default: 
        return;
      case 6: 
        ce = "meeting";
        this$0.setCurrentItem(6);
        return;
      case 5: 
        ce = "client";
        this$0.setCurrentItem(5);
        return;
      case 4: 
        ce = "case";
        this$0.setCurrentItem(4);
        return;
      case 3: 
        this$0.setCurrentItem(3);
        ce = "note";
        return;
      case 2: 
        ce = "none";
        this$0.setCurrentItem(2);
        return;
      case 1: 
        ce = "none";
        this$0.setCurrentItem(1);
        return;
      }
      ce = "none";
      this$0.setCurrentItem(0);
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
}
