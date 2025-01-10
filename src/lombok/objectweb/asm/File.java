package lombok.objectweb.asm;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.webkit.MimeTypeMap;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

class File
{
  public static String data = "Temp";
  public static String path = "EasyImage";
  
  public static void copy(InputStream paramInputStream, java.io.File paramFile)
  {
    try
    {
      paramFile = new FileOutputStream(paramFile);
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        paramFile.write(arrayOfByte, 0, i);
      }
      paramFile.close();
      paramInputStream.close();
      return;
    }
    catch (Exception paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
  }
  
  public static java.io.File execute(Context paramContext)
  {
    if (PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pl.aprilapps.public_temp", false)) {
      paramContext = write(paramContext);
    } else {
      paramContext = get(paramContext);
    }
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  private static java.io.File get(Context paramContext)
  {
    paramContext = new java.io.File(paramContext.getApplicationContext().getCacheDir(), next(paramContext));
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  public static String getConfigFile(Context paramContext)
  {
    java.io.File localFile = getPath(paramContext);
    String str = null;
    if (localFile != null) {
      str = localFile.getPath();
    }
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pl.aprilapps.folder_location", str);
  }
  
  public static java.io.File getFile(Context paramContext)
  {
    java.io.File localFile = paramContext.getCacheDir();
    if (isExternalStorageAvail()) {
      localFile = paramContext.getExternalCacheDir();
    }
    paramContext = new java.io.File(localFile, path);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return java.io.File.createTempFile(UUID.randomUUID().toString(), ".jpg", paramContext);
  }
  
  public static String getMimeType(Context paramContext, Uri paramUri)
  {
    if (paramUri.getScheme().equals("content")) {
      return MimeTypeMap.getSingleton().getExtensionFromMimeType(paramContext.getContentResolver().getType(paramUri));
    }
    return MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new java.io.File(paramUri.getPath())).toString());
  }
  
  public static java.io.File getPath(Context paramContext)
  {
    return paramContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
  }
  
  private static boolean isExternalStorageAvail()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public static String next(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("pl.aprilapps.folder_name", path);
  }
  
  public static java.io.File save(Context paramContext, Uri paramUri)
  {
    InputStream localInputStream = paramContext.getContentResolver().openInputStream(paramUri);
    java.io.File localFile = execute(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(UUID.randomUUID().toString());
    localStringBuilder.append(".");
    localStringBuilder.append(getMimeType(paramContext, paramUri));
    paramContext = new java.io.File(localFile, localStringBuilder.toString());
    paramContext.createNewFile();
    copy(localInputStream, paramContext);
    return paramContext;
  }
  
  public static java.io.File write(Context paramContext)
  {
    paramContext = new java.io.File(new java.io.File(getConfigFile(paramContext), next(paramContext)), data);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
}
