package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.lawyer_smartCalendar.utils.d;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ImageActivity
  extends AppCompatActivity
{
  private String content;
  private ImageView preview;
  
  public ImageActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492911);
    paramBundle = (Toolbar)findViewById(2131296838);
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new a());
    content = getIntent().getStringExtra("image");
    preview = ((ImageView)findViewById(2131296481));
    paramBundle = BitmapFactory.decodeFile(content);
    preview.setImageBitmap(paramBundle);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558415, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 2131296303) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    if (Build.VERSION.SDK_INT >= 24) {
      try
      {
        paramMenuItem = StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]);
        paramMenuItem.invoke(null, new Object[0]);
      }
      catch (Exception paramMenuItem) {}
    }
    try
    {
      paramMenuItem.printStackTrace();
      paramMenuItem = content;
      paramMenuItem = BitmapFactory.decodeFile(paramMenuItem);
      Object localObject = new ByteArrayOutputStream();
      Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.JPEG;
      paramMenuItem.compress(localCompressFormat, 100, (OutputStream)localObject);
      paramMenuItem = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), paramMenuItem, "Title", null));
      localObject = new Intent("android.intent.action.SEND");
      ((Intent)localObject).setType("image/*");
      ((Intent)localObject).putExtra("android.intent.extra.STREAM", paramMenuItem);
      startActivity(Intent.createChooser((Intent)localObject, "Share"));
      return true;
    }
    catch (Exception paramMenuItem)
    {
      Toast.makeText(this, ((Exception)paramMenuItem).toString(), 1).show();
    }
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
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      finish();
    }
  }
}
