package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.MaterialDialog.ListCallbackSingleChoice;
import apps.api.a.f;
import apps.authenticator.wizard.runtime.Log;
import butterknife.ButterKnife;
import com.joanzapata.material.widget.Button;
import com.lawyer_smartCalendar.utils.Collection;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;
import java.util.Locale;
import lombok.objectweb.asm.c;
import lombok.objectweb.asm.h;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FirstProfileActivity
  extends AppCompatActivity
  implements View.OnClickListener
{
  Button btn_edit_profile;
  EditText editText_address;
  EditText editText_email;
  EditText editText_landline_phone;
  EditText editText_name;
  EditText editText_phone;
  FloatingActionButton img_add_avatar;
  ImageView img_lawyer_pic;
  private Uri origin;
  RadioButton radio_lagalBase_one;
  RadioButton radio_lagalBase_two;
  private GravityEnum state = GravityEnum.END;
  
  public FirstProfileActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    h.a(paramInt1, paramInt2, paramIntent, this, new a());
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i != 2131296352)
    {
      if (i != 2131296474) {
        return;
      }
      paramView = new MaterialDialog.Builder(this);
      paramView.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
      paramView.append(state);
      paramView.setText(state);
      paramView.putString(state);
      paramView.setText(state);
      paramView.title("?????? ?????");
      paramView.items(2130903053);
      paramView.itemsCallbackSingleChoice(-1, new e());
      paramView.show();
      return;
    }
    if (editText_name.getText().toString().equals(""))
    {
      new Collection().a(this, "error", "??? ? ??? ???????? ??? ????? ???? ????.");
      return;
    }
    if ((!editText_phone.getText().toString().equals("")) && (editText_phone.getText().toString().length() < 11))
    {
      new Collection().a(this, "error", "????? ???? ???? 11 ???? ????.");
      return;
    }
    paramView = origin;
    if (paramView != null) {
      Log.send("Profile_image", paramView.toString());
    }
    Log.send("Profile_name", editText_name.getText().toString());
    if (radio_lagalBase_one.isChecked()) {
      Log.initialize("Profile_legal_base", 1);
    } else {
      Log.initialize("Profile_legal_base", 2);
    }
    Log.send("Profile_phone", editText_phone.getText().toString());
    Log.send("Profile_landline_phone", editText_landline_phone.getEditableText().toString());
    Log.send("Profile_address", editText_address.getEditableText().toString());
    Log.send("Profile_email", editText_email.getEditableText().toString());
    Log.write("Profile_has_info", true);
    new Collection().a(this, "success", "?? ?????? ??? ??.");
    finish();
    startActivity(new Intent(this, MainActivity.class));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    setContentView(2131492910);
    ButterKnife.getView(this);
    if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0) {
      state = GravityEnum.END;
    } else {
      state = GravityEnum.START;
    }
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new b());
    btn_edit_profile.setOnClickListener(this);
    btn_edit_profile.setBackgroundColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
    img_add_avatar.setOnClickListener(this);
    radio_lagalBase_one.setOnCheckedChangeListener(new c());
    radio_lagalBase_two.setOnCheckedChangeListener(new d());
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    f.a().a(paramArrayOfString, paramArrayOfInt);
  }
  
  class a
    implements lombok.objectweb.asm.d
  {
    a() {}
    
    public void a(Exception paramException, c paramC, int paramInt)
    {
      paramException.printStackTrace();
    }
    
    public void b(File paramFile, c paramC, int paramInt)
    {
      FirstProfileActivity.create(FirstProfileActivity.this, Uri.parse(paramFile.getPath()));
      paramFile = Picasso.with().load(paramFile);
      paramFile.fit();
      paramFile.transform();
      paramFile.into(img_lawyer_pic);
    }
    
    public void b(c paramC, int paramInt)
    {
      if ((paramC == c.b) || (paramC == c.d))
      {
        paramC = h.a(FirstProfileActivity.this);
        if (paramC != null) {
          paramC.delete();
        }
      }
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
    implements CompoundButton.OnCheckedChangeListener
  {
    c() {}
    
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        radio_lagalBase_two.setChecked(false);
        return;
      }
      radio_lagalBase_two.setChecked(true);
    }
  }
  
  class d
    implements CompoundButton.OnCheckedChangeListener
  {
    d() {}
    
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        radio_lagalBase_one.setChecked(false);
        return;
      }
      radio_lagalBase_one.setChecked(true);
    }
  }
  
  class e
    implements MaterialDialog.ListCallbackSingleChoice
  {
    e() {}
    
    public boolean b(MaterialDialog paramMaterialDialog, View paramView, int paramInt, CharSequence paramCharSequence)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return true;
        }
        h.c(FirstProfileActivity.this, 0);
        return true;
      }
      h.setTitle(FirstProfileActivity.this, 0);
      return true;
    }
  }
}
