package com.devs.curve;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class ErrorReporterActivity
  extends Activity
  implements View.OnClickListener
{
  public ErrorReporterActivity() {}
  
  private void initButtons()
  {
    ((TextView)findViewById(R.id.tv_cancel)).setOnClickListener(this);
    ((TextView)findViewById(R.id.tv_report)).setOnClickListener(this);
  }
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == R.id.tv_cancel)
    {
      finish();
      return;
    }
    if (i == R.id.tv_report)
    {
      d.a(getApplication()).a(this);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(c.activity_erroe_reporter);
    if (Build.VERSION.SDK_INT >= 11) {
      setFinishOnTouchOutside(false);
    }
    paramBundle = getWindow();
    paramBundle.setBackgroundDrawable(new ColorDrawable(0));
    paramBundle.setLayout(-1, -2);
    initButtons();
  }
}
