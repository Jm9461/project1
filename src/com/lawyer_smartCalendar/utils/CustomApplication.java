package com.lawyer_smartCalendar.utils;

import android.app.Application;
import android.content.ContextWrapper;
import apps.authenticator.wizard.runtime.a;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;

public class CustomApplication
  extends Application
{
  public CustomApplication() {}
  
  public void onCreate()
  {
    super.onCreate();
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/IRANSansMobile_Light.ttf").setFontAttrId(2130968987).build());
    a localA = new a();
    localA.a(this);
    localA.a(0);
    localA.set(getPackageName());
    localA.a(true);
    localA.a();
  }
}