package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import apps.authenticator.wizard.runtime.Log;
import apps.core.event.util.IInAppBillingService;
import apps.core.event.util.IInAppBillingService.Stub;
import com.lawyer_smartCalendar.utils.ACRA;
import com.lawyer_smartCalendar.utils.Collection;
import com.lawyer_smartCalendar.utils.e;
import com.lawyer_smartCalendar.utils.f;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PayActivity
  extends AppCompatActivity
{
  String artist;
  ServiceConnection conn = new a();
  TextView content;
  String id;
  private Button mButtonCancel;
  private Button mButtonOk;
  IInAppBillingService mService;
  TextView name;
  TextView time;
  String title;
  
  public PayActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Object localObject1;
    Object localObject2;
    if (paramInt1 == 1002) {
      try
      {
        int i = paramIntent.getIntExtra("RESPONSE_CODE", 0);
        localObject1 = getApplicationContext();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("response code : ");
        ((StringBuilder)localObject2).append(i);
        Toast.makeText((Context)localObject1, ((StringBuilder)localObject2).toString(), 1).show();
      }
      catch (Exception paramIntent)
      {
        return;
      }
    }
    if (paramInt1 == 1001)
    {
      paramInt1 = paramIntent.getIntExtra("RESPONSE_CODE", 0);
      localObject1 = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
      paramIntent = "";
      if (paramInt2 == -1) {
        try
        {
          paramIntent = new JSONObject((String)localObject1);
          localObject1 = paramIntent.getString("productId");
          paramIntent = paramIntent.getString("purchaseToken");
          Log.send("app_version", "full");
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("????? ????? : ");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append("\n");
          localObject1 = ((StringBuilder)localObject2).toString();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append("???? ???? : ");
          ((StringBuilder)localObject2).append(paramIntent);
          ((StringBuilder)localObject2).append("\n");
          paramIntent = ((StringBuilder)localObject2).toString();
          localObject1 = new MaterialDialog.Builder(this);
          ((MaterialDialog.Builder)localObject1).get("IRANSansMobile_Medium.ttf", "IRANSansMobile_Medium.ttf");
          localObject2 = GravityEnum.a;
          ((MaterialDialog.Builder)localObject1).append((GravityEnum)localObject2);
          localObject2 = GravityEnum.a;
          ((MaterialDialog.Builder)localObject1).setText((GravityEnum)localObject2);
          ((MaterialDialog.Builder)localObject1).title("????? : ???? ?? ?????? ????? ??");
          localObject2 = GravityEnum.END;
          ((MaterialDialog.Builder)localObject1).valueOf((GravityEnum)localObject2);
          ((MaterialDialog.Builder)localObject1).content(paramIntent);
          ((MaterialDialog.Builder)localObject1).negativeColorRes(2131099708);
          ((MaterialDialog.Builder)localObject1).positiveColorRes(2131099672);
          ((MaterialDialog.Builder)localObject1).positiveText("????");
          ((MaterialDialog.Builder)localObject1).callback(new b());
          ((MaterialDialog.Builder)localObject1).show();
          paramIntent = e.c;
          try
          {
            paramIntent.invalidateOptionsMenu();
          }
          catch (Exception paramIntent) {}
          return;
        }
        catch (Exception paramIntent)
        {
          return;
        }
        catch (JSONException paramIntent)
        {
          Toast.makeText(this, paramIntent.getMessage(), 1).show();
        }
      }
      switch (paramInt1)
      {
      default: 
        break;
      case 2: 
        break;
      case 8: 
        paramIntent = "??? ?? ????? ???? ??? ????? ??? ???????? ????? ????";
        break;
      case 7: 
        paramIntent = "??? ????? ?? ??? ???? ?? ?????? ??? ???";
        break;
      case 6: 
        paramIntent = "??? ?? ????? ????? ?????? ??????";
        break;
      case 5: 
        paramIntent = "?????????? ?????? ?? API ????? ??????. ??? ??? ?????? ???????? ?????? ??? ????? ????:?????? ?? ????? ???? ???? ???? ??????? ???? ???? ?? ??????? ????? ???? ???? ?????? ?? ??? ?????? ????? ??? ???? ?? ??????? ???? ???? ??????? ?? ?????? ?? ??? ????? ???.";
        break;
      case 4: 
        paramIntent = "??? ????? ???? ???? ????? ????";
        break;
      case 3: 
        paramIntent = "API? ???? ??????? ????? ??? ???????? ???????";
        break;
      }
      paramIntent = "???? ???? ??? ??? ??";
      localObject1 = new MaterialDialog.Builder(this);
      ((MaterialDialog.Builder)localObject1).get("IRANSansMobile_Medium.ttf", "IRANSansMobile_Medium.ttf");
      localObject2 = GravityEnum.a;
      ((MaterialDialog.Builder)localObject1).append((GravityEnum)localObject2);
      localObject2 = GravityEnum.a;
      ((MaterialDialog.Builder)localObject1).setText((GravityEnum)localObject2);
      ((MaterialDialog.Builder)localObject1).title("??? :");
      localObject2 = GravityEnum.END;
      ((MaterialDialog.Builder)localObject1).valueOf((GravityEnum)localObject2);
      ((MaterialDialog.Builder)localObject1).content(paramIntent);
      ((MaterialDialog.Builder)localObject1).negativeColorRes(2131099708);
      ((MaterialDialog.Builder)localObject1).positiveColorRes(2131099672);
      ((MaterialDialog.Builder)localObject1).positiveText("????");
      ((MaterialDialog.Builder)localObject1).callback(new c());
      ((MaterialDialog.Builder)localObject1).show();
      return;
    }
  }
  
  public void onBackPressed()
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131492917);
    getSharedPreferences("app_version", 0);
    content = ((TextView)findViewById(2131296869));
    time = ((TextView)findViewById(2131296868));
    name = ((TextView)findViewById(2131296804));
    mButtonOk = ((Button)findViewById(2131296353));
    mButtonCancel = ((Button)findViewById(2131296354));
    content.setTypeface(new f(this).get());
    time.setTypeface(new f(this).get());
    name.setTypeface(new f(this).get());
    mButtonOk.setTypeface(new f(this).get());
    mButtonCancel.setTypeface(new f(this).get());
    Object localObject = Calendar.getInstance();
    if (paramBundle != null)
    {
      id = paramBundle.getString("CURRENT_TIME");
      artist = paramBundle.getString("IMEI_CODE");
      title = paramBundle.getString("ORDER_ID");
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append(((Calendar)localObject).getTimeInMillis());
      paramBundle.append("");
      id = paramBundle.toString();
      artist = Settings.Secure.getString(getApplicationContext().getContentResolver(), "android_id");
      paramBundle = new StringBuilder();
      paramBundle.append(artist);
      paramBundle.append(id);
      title = paramBundle.toString();
    }
    paramBundle = null;
    localObject = ACRA.c;
    int i = ((String)localObject).hashCode();
    int j = -1;
    if (i != -1395998121) {
      if (i != -710688120)
      {
        break label341;
        break label341;
        if (i != 104374574) {
          break label392;
        }
      }
    }
    label341:
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
    break label394;
    label392:
    i = -1;
    label394:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i == 2)
        {
          localObject = new Intent("ir.tgbs.iranapps.billing.InAppBillingService.BIND");
          paramBundle = (Bundle)localObject;
          ((Intent)localObject).setPackage("ir.tgbs.android.iranapp");
        }
      }
      else
      {
        localObject = new Intent("ir.mservices.market.InAppBillingService.BIND");
        paramBundle = (Bundle)localObject;
        ((Intent)localObject).setPackage("ir.mservices.market");
      }
    }
    else
    {
      localObject = new Intent("ir.cafebazaar.pardakht.InAppBillingService.BIND");
      paramBundle = (Bundle)localObject;
      ((Intent)localObject).setPackage("com.farsitel.bazaar");
    }
    bindService(paramBundle, conn, 1);
    mButtonOk.setOnClickListener(new d());
    mButtonCancel.setOnClickListener(new e());
    paramBundle = ACRA.c;
    i = paramBundle.hashCode();
    if (i != -1395998121) {
      if (i != -710688120) {
        if (i == 104374574) {}
      }
    }
    while (!paramBundle.equals("bazaar"))
    {
      do
      {
        do
        {
          i = j;
          break;
        } while (!paramBundle.equals("myket"));
        i = 1;
        break;
      } while (!paramBundle.equals("iranapps"));
      i = 2;
      break;
    }
    i = 0;
    if (i != 0)
    {
      if ((i == 1) || (i == 2)) {
        name.setVisibility(8);
      }
    }
    else {
      name.setVisibility(0);
    }
    name.setOnClickListener(new f());
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    ServiceConnection localServiceConnection = conn;
    if (localServiceConnection != null) {
      unbindService(localServiceConnection);
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("CURRENT_TIME", id);
    paramBundle.putString("IMEI_CODE", artist);
    paramBundle.putString("ORDER_ID", title);
  }
  
  class a
    implements ServiceConnection
  {
    a() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      mService = IInAppBillingService.Stub.asInterface(paramIBinder);
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      mService = null;
    }
  }
  
  class b
    implements c
  {
    b() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
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
    }
  }
  
  class d
    implements View.OnClickListener
  {
    d() {}
    
    public void onClick(View paramView)
    {
      paramView = mService;
      Object localObject = PayActivity.this;
      try
      {
        localObject = ((ContextWrapper)localObject).getPackageName();
        StringBuilder localStringBuilder = new StringBuilder();
        String str = title;
        localStringBuilder.append(str.hashCode());
        localStringBuilder.append("");
        paramView = paramView.getBuyIntent(3, (String)localObject, "lawyer.smartCalendar_full_version", "inapp", localStringBuilder.toString()).getParcelable("BUY_INTENT");
        localObject = (PendingIntent)paramView;
        paramView = PayActivity.this;
        localObject = ((PendingIntent)localObject).getIntentSender();
        paramView.startIntentSenderForResult((IntentSender)localObject, 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        return;
      }
      catch (Exception paramView) {}
    }
  }
  
  class e
    implements View.OnClickListener
  {
    e() {}
    
    public void onClick(View paramView)
    {
      paramView = mService;
      Object localObject1 = PayActivity.this;
      try
      {
        localObject1 = paramView.getPurchases(3, ((ContextWrapper)localObject1).getPackageName(), "inapp", null);
        int i = ((Bundle)localObject1).getInt("RESPONSE_CODE");
        if (i == 0)
        {
          ((Bundle)localObject1).getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
          paramView = ((Bundle)localObject1).getStringArrayList("INAPP_PURCHASE_DATA_LIST");
          ((Bundle)localObject1).getStringArrayList("INAPP_DATA_SIGNATURE");
          ((Bundle)localObject1).getString("INAPP_CONTINUATION_TOKEN");
          i = 0;
          for (;;)
          {
            int j = paramView.size();
            if (i >= j) {
              break;
            }
            localObject1 = new JSONObject(paramView.get(i).toString());
            boolean bool = ((JSONObject)localObject1).getString("productId").equals("lawyer.smartCalendar_full_version");
            if (bool)
            {
              Object localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("????? ????? : ");
              ((StringBuilder)localObject2).append(((JSONObject)localObject1).getString("productId"));
              ((StringBuilder)localObject2).append("\n");
              localObject1 = ((StringBuilder)localObject2).toString();
              Log.send("app_version", "full");
              localObject2 = PayActivity.this;
              localObject2 = new MaterialDialog.Builder((Context)localObject2);
              ((MaterialDialog.Builder)localObject2).get("IRANSansMobile_Medium.ttf", "IRANSansMobile_Medium.ttf");
              GravityEnum localGravityEnum = GravityEnum.a;
              ((MaterialDialog.Builder)localObject2).append(localGravityEnum);
              localGravityEnum = GravityEnum.a;
              ((MaterialDialog.Builder)localObject2).setText(localGravityEnum);
              ((MaterialDialog.Builder)localObject2).title("????? : ???? ??? ??????? ??");
              localGravityEnum = GravityEnum.END;
              ((MaterialDialog.Builder)localObject2).valueOf(localGravityEnum);
              ((MaterialDialog.Builder)localObject2).content((CharSequence)localObject1);
              ((MaterialDialog.Builder)localObject2).negativeColorRes(2131099708);
              ((MaterialDialog.Builder)localObject2).positiveColorRes(2131099672);
              ((MaterialDialog.Builder)localObject2).positiveText("????");
              ((MaterialDialog.Builder)localObject2).callback(new a());
              ((MaterialDialog.Builder)localObject2).show();
              localObject1 = e.c;
              try
              {
                ((AppCompatActivity)localObject1).invalidateOptionsMenu();
              }
              catch (Exception localException) {}
            }
            i += 1;
          }
          return;
        }
        paramView = new Collection();
        PayActivity localPayActivity = PayActivity.this;
        paramView.a(localPayActivity, "error", "??????? ???? ???? ???.");
        return;
      }
      catch (Exception paramView)
      {
        Toast.makeText(getApplicationContext(), ((Exception)paramView).getMessage(), 1).show();
      }
    }
    
    class a
      implements c
    {
      a() {}
      
      public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
      {
        paramMaterialDialog.dismiss();
        finish();
      }
    }
  }
  
  class f
    implements View.OnClickListener
  {
    f() {}
    
    public void onClick(View paramView)
    {
      paramView = new Intent("android.intent.action.VIEW", Uri.parse("https://cafebazaar.ir/help/?l=fa"));
      startActivity(paramView);
    }
  }
}
