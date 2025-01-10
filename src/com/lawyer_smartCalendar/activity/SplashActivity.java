package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import apps.android.google.api.Priority;
import apps.authenticator.wizard.runtime.Log;
import apps.common.common.common.data.h;
import com.lawyer_smartCalendar.utils.Utils;
import com.lawyer_smartCalendar.utils.d;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity
  extends apps.common.common.common.ui.MainActivity
{
  public SplashActivity() {}
  
  public void a(h paramH)
  {
    paramH.b(d.c());
    paramH.visitJumpInsn(1500);
    paramH.a(3);
    paramH.c(2);
    paramH.setTitle(2131230894);
    paramH.d(Priority.ERROR);
    paramH.f(1500);
    paramH.setEnabled(400);
    paramH.create(400);
    paramH.d(3000);
    paramH.setIcon(3);
    paramH.setShortcut(2131099879);
    paramH.setShowAsAction(3000);
    paramH.getIcon(2131099879);
    paramH.e(getResources().getString(2131755084));
    paramH.l(2131099898);
    paramH.a(15.0F);
    paramH.e(2000);
    paramH.b(Priority.b);
    paramH.d("fonts/IRANSansMobile_Medium.ttf");
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onActivityResult()
  {
    finish();
    if (!Log.get("Profile_has_info", false))
    {
      startActivity(new Intent(this, FirstProfileActivity.class));
      return;
    }
    startActivity(new Intent(this, MainActivity.class));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    new Utils().initialize(this, Color.parseColor(d.getValue()));
  }
}
