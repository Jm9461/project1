package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.lawyer_smartCalendar.utils.d;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContactActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  public RelativeLayout contact_gmail;
  public RelativeLayout contact_instagram;
  public RelativeLayout contact_telegram;
  
  public ContactActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296391: 
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://telegram.me/")));
      return;
    case 2131296390: 
      paramView = new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/"));
      paramView.setPackage("com.instagram.android");
      try
      {
        startActivity(paramView);
        return;
      }
      catch (ActivityNotFoundException paramView)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://instagram.com/")));
        return;
      }
    }
    startActivity(Intent.createChooser(new Intent("android.intent.action.SENDTO", Uri.parse("mailto:info@eazarbar.org")), "???? ?? ??? ?????????"));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492907);
    ButterKnife.getView(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new a());
    contact_gmail.setOnClickListener(this);
    contact_instagram.setOnClickListener(this);
    contact_telegram.setOnClickListener(this);
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
