package com.lawyer_smartCalendar.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import com.joanzapata.material.widget.Spinner;
import com.lawyer_smartCalendar.utils.d;
import java.util.ArrayList;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SuportActivity
  extends AppCompatActivity
{
  public SuportActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    setContentView(2131492926);
    Object localObject = new ArrayList();
    ((List)localObject).add("????? ???");
    ((List)localObject).add("??????");
    ((List)localObject).add("???????");
    ((List)localObject).add("???");
    ((List)localObject).add("????");
    paramBundle = (Spinner)findViewById(2131296744);
    localObject = new ArrayAdapter(this, 17367048, (List)localObject);
    ((ArrayAdapter)localObject).setDropDownViewResource(17367049);
    paramBundle.setAdapter((SpinnerAdapter)localObject);
  }
}
