package lombok.objectweb.asm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class h
  implements SupportMenuItem
{
  private static Intent a(Context paramContext, int paramInt)
  {
    putString(paramContext, paramInt);
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    try
    {
      Uri localUri = doInBackground(paramContext);
      onPostExecute(paramContext, localIntent, localUri);
      localIntent.putExtra("output", localUri);
      return localIntent;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localIntent;
  }
  
  public static java.io.File a(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pl.aprilapps.easyphotopicker.last_photo", null);
    if (paramContext == null) {
      return null;
    }
    paramContext = new java.io.File(paramContext);
    if (paramContext.exists()) {
      return paramContext;
    }
    return null;
  }
  
  public static void a(int paramInt1, int paramInt2, Intent paramIntent, Activity paramActivity, d paramD)
  {
    if ((paramInt1 == 7460) || (paramInt1 == 7458) || (paramInt1 == 7459) || (paramInt1 == 7457))
    {
      if (paramInt2 == -1)
      {
        if (paramInt1 == 7457)
        {
          c(paramIntent, paramActivity, paramD);
          return;
        }
        if (paramInt1 == 7458)
        {
          a(paramIntent, paramActivity, paramD);
          return;
        }
        if (paramInt1 == 7459)
        {
          a(paramActivity, paramD);
          return;
        }
        if ((paramIntent != null) && (paramIntent.getData() != null))
        {
          c(paramIntent, paramActivity, paramD);
          return;
        }
        a(paramActivity, paramD);
        return;
      }
      if (paramInt1 == 7457)
      {
        paramD.b(c.a, getValue(paramActivity));
        return;
      }
      if (paramInt1 == 7458)
      {
        paramD.b(c.d, getValue(paramActivity));
        return;
      }
      if (paramInt1 == 7459)
      {
        paramD.b(c.b, getValue(paramActivity));
        return;
      }
      if ((paramIntent != null) && (paramIntent.getData() != null))
      {
        paramD.b(c.a, getValue(paramActivity));
        return;
      }
      paramD.b(c.b, getValue(paramActivity));
    }
  }
  
  private static void a(Activity paramActivity, d paramD)
  {
    try
    {
      Object localObject = PreferenceManager.getDefaultSharedPreferences(paramActivity).getString("pl.aprilapps.easyphotopicker.photo_uri", null);
      boolean bool = TextUtils.isEmpty((CharSequence)localObject);
      if (!bool) {
        setIcon(paramActivity, Uri.parse((String)localObject));
      }
      localObject = getTitle(paramActivity);
      c localC;
      if (localObject == null)
      {
        localObject = new IllegalStateException("Unable to get the picture returned from camera");
        localC = c.b;
        paramD.a((Exception)localObject, localC, getValue(paramActivity));
      }
      else
      {
        localC = c.b;
        paramD.b((java.io.File)localObject, localC, getValue(paramActivity));
      }
      PreferenceManager.getDefaultSharedPreferences(paramActivity).edit().remove("pl.aprilapps.easyphotopicker.last_photo").remove("pl.aprilapps.easyphotopicker.photo_uri").apply();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      paramD.a(localException, c.b, getValue(paramActivity));
    }
  }
  
  private static void a(Intent paramIntent, Activity paramActivity, d paramD)
  {
    try
    {
      paramIntent = File.save(paramActivity, paramIntent.getData());
      c localC = c.d;
      paramD.b(paramIntent, localC, getValue(paramActivity));
      return;
    }
    catch (Exception paramIntent)
    {
      paramIntent.printStackTrace();
      paramD.a(paramIntent, c.d, getValue(paramActivity));
    }
  }
  
  public static void c(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(a(paramActivity, paramInt), 7459);
  }
  
  private static void c(Intent paramIntent, Activity paramActivity, d paramD)
  {
    try
    {
      paramIntent = File.save(paramActivity, paramIntent.getData());
      c localC = c.a;
      paramD.b(paramIntent, localC, getValue(paramActivity));
      return;
    }
    catch (Exception paramIntent)
    {
      paramIntent.printStackTrace();
      paramD.a(paramIntent, c.a, getValue(paramActivity));
    }
  }
  
  private static Uri doInBackground(Context paramContext)
  {
    java.io.File localFile = File.getFile(paramContext);
    Object localObject = paramContext.getApplicationContext().getPackageName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(".easyphotopicker.fileprovider");
    localObject = FileProvider.getUriForFile(paramContext, localStringBuilder.toString(), localFile);
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString("pl.aprilapps.easyphotopicker.photo_uri", ((Uri)localObject).toString());
    paramContext.putString("pl.aprilapps.easyphotopicker.last_photo", localFile.toString());
    paramContext.apply();
    return localObject;
  }
  
  private static Intent getIntent(Context paramContext, int paramInt)
  {
    putString(paramContext, paramInt);
    return new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  }
  
  private static java.io.File getTitle(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pl.aprilapps.easyphotopicker.last_photo", null);
    if (paramContext != null) {
      return new java.io.File(paramContext);
    }
    return null;
  }
  
  private static int getValue(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pl.aprilapps.easyphotopicker.type", 0);
  }
  
  public static a i(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  private static void onPostExecute(Context paramContext, Intent paramIntent, Uri paramUri)
  {
    paramIntent = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    while (paramIntent.hasNext()) {
      paramContext.grantUriPermission(nextactivityInfo.packageName, paramUri, 3);
    }
  }
  
  private static void putString(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("pl.aprilapps.easyphotopicker.type", paramInt).commit();
  }
  
  public static void setEnabled(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().remove("pl.aprilapps.folder_name").remove("pl.aprilapps.folder_location").remove("pl.aprilapps.public_temp").apply();
  }
  
  private static void setIcon(Context paramContext, Uri paramUri)
  {
    paramContext.revokeUriPermission(paramUri, 3);
  }
  
  public static void setTitle(Activity paramActivity, int paramInt)
  {
    paramActivity.startActivityForResult(getIntent(paramActivity, paramInt), 7458);
  }
}
