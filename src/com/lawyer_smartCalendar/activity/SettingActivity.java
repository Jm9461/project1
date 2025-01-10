package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import apps.api.a.f;
import apps.api.a.h;
import apps.authenticator.wizard.runtime.Log;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog.Builder;
import com.afollestad.materialdialogs.color.g;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.i;
import java.util.Locale;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingActivity
  extends AppCompatActivity
  implements View.OnClickListener, g
{
  public Button btn_backup;
  public Button btn_change_color;
  public Button btn_restore;
  
  public SettingActivity()
  {
    GravityEnum localGravityEnum = GravityEnum.END;
  }
  
  public void a(ColorChooserDialog paramColorChooserDialog, int paramInt)
  {
    if (d.b(paramInt))
    {
      Log.initialize("themeColor", paramInt);
      finish();
      startActivity(new Intent(this, SplashActivity.class));
      return;
    }
    new Collection().a(this, "error", "???? ??? ???? ??? ?? ?? ???? ?????? ????.");
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    finish();
    startActivity(new Intent(this, MainActivity.class));
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296355: 
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(GravityEnum.END);
      paramView.title("??? ???? ????????? ??????? ??????? ??????");
      paramView.setText(GravityEnum.END);
      paramView.valueOf(GravityEnum.END);
      paramView.negativeColorRes(2131099708);
      paramView.content("?? ???? ????????? ??????? ???? ??? ? ??????? ???? ??????? ??????? ?? ???.");
      paramView.positiveColorRes(2131099728);
      paramView.content(2131099708);
      paramView.positiveText("??????");
      paramView.negativeText("?????????");
      paramView.callback(new d());
      paramView.onPositive(new c());
      paramView.show();
      return;
    case 2131296350: 
      paramView = new d().d();
      ColorChooserDialog.Builder localBuilder = new ColorChooserDialog.Builder(this, 2131755098);
      localBuilder.customColors(paramView, null);
      localBuilder.preselect(3);
      localBuilder.allowUserColorInput(false);
      localBuilder.allowUserColorInputAlpha(false);
      localBuilder.set("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      localBuilder.cancelButton(2131755096);
      localBuilder.doneButton(2131755097).show(this);
      return;
    }
    new i(this).doInBackground();
    new Collection().a(this, "success", "??????? ???? ?? ?????? ????? ??.");
  }
  
  public void onCloseMenu(ColorChooserDialog paramColorChooserDialog) {}
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492925);
    ButterKnife.getView(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new a());
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      paramBundle = GravityEnum.END;
    } else {
      paramBundle = GravityEnum.START;
    }
    btn_change_color.setOnClickListener(this);
    btn_backup.setOnClickListener(this);
    btn_restore.setOnClickListener(this);
    paramBundle = f.a();
    b localB = new b();
    paramBundle.a(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE" }, localB);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (isFinishing()) {
      overridePendingTransition(17432576, 17432577);
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    f.a().a(paramArrayOfString, paramArrayOfInt);
  }
  
  class a
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
  
  class b
    extends h
  {
    b() {}
    
    public void b(String paramString)
    {
      Toast.makeText(SettingActivity.this, "???? ?????? ??? ???? ?? ?? ?????? ?????.", 0).show();
      finish();
    }
    
    public void l() {}
  }
  
  class c
    implements c
  {
    c() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      if (new i(SettingActivity.this).a())
      {
        new Collection().a(SettingActivity.this, "success", "????????? ?? ?????? ????? ??.");
        return;
      }
      new Collection().a(SettingActivity.this, "error", "????????? ????? ???.???? ??? ???? ??????? ???? ?????? ????.");
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
}
