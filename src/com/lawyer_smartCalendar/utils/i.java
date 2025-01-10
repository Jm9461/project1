package com.lawyer_smartCalendar.utils;

import android.app.backup.BackupManager;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class i
{
  public static String t = "lawyerSmartCalendar";
  private String b = "sahvBackUp.bk";
  private Context c;
  private String f = "sahv";
  
  public i(Context paramContext)
  {
    c = paramContext;
  }
  
  public boolean a()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory());
      ((StringBuilder)localObject1).append("/");
      localObject2 = t;
      ((StringBuilder)localObject1).append((String)localObject2);
      localObject1 = new File(((StringBuilder)localObject1).toString());
      localObject2 = Environment.getDataDirectory();
      boolean bool = ((File)localObject1).canWrite();
      if (bool)
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("//data//");
        localObject4 = c;
        ((StringBuilder)localObject3).append(((Context)localObject4).getPackageName());
        ((StringBuilder)localObject3).append("//databases//");
        localObject4 = f;
        ((StringBuilder)localObject3).append((String)localObject4);
        localObject2 = new File((File)localObject2, ((StringBuilder)localObject3).toString());
        localObject3 = b;
        localObject1 = new File((File)localObject1, (String)localObject3);
        bool = ((File)localObject1).exists();
        if (bool)
        {
          localObject1 = new FileInputStream((File)localObject1).getChannel();
          localObject2 = new FileOutputStream((File)localObject2).getChannel();
          ((FileChannel)localObject2).transferFrom((ReadableByteChannel)localObject1, 0L, ((FileChannel)localObject1).size());
          ((FileChannel)localObject1).close();
          ((FileChannel)localObject2).close();
          return true;
        }
      }
      return false;
    }
    catch (Exception localException)
    {
      Object localObject2 = new Collection();
      Object localObject3 = c;
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("???? : ");
      ((StringBuilder)localObject4).append(localException.getMessage());
      ((StringBuilder)localObject4).append(" ?? ???? ???. ");
      ((Collection)localObject2).a((Context)localObject3, "error", ((StringBuilder)localObject4).toString());
    }
    return false;
  }
  
  public void doInBackground()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory());
      ((StringBuilder)localObject1).append("/");
      localObject2 = t;
      ((StringBuilder)localObject1).append((String)localObject2);
      localObject1 = new File(((StringBuilder)localObject1).toString());
      boolean bool = ((File)localObject1).exists();
      if (!bool) {
        ((File)localObject1).mkdirs();
      }
      localObject2 = Environment.getDataDirectory();
      bool = ((File)localObject1).canWrite();
      if (bool)
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("//data//");
        localObject4 = c;
        ((StringBuilder)localObject3).append(((Context)localObject4).getPackageName());
        ((StringBuilder)localObject3).append("//databases//");
        localObject4 = f;
        ((StringBuilder)localObject3).append((String)localObject4);
        localObject2 = new File((File)localObject2, ((StringBuilder)localObject3).toString());
        localObject3 = b;
        localObject1 = new File((File)localObject1, (String)localObject3);
        bool = ((File)localObject2).exists();
        if (bool)
        {
          localObject2 = new FileInputStream((File)localObject2).getChannel();
          localObject1 = new FileOutputStream((File)localObject1).getChannel();
          ((FileChannel)localObject1).transferFrom((ReadableByteChannel)localObject2, 0L, ((FileChannel)localObject2).size());
          ((FileChannel)localObject2).close();
          ((FileChannel)localObject1).close();
          localObject1 = c;
          BackupManager.dataChanged(((Context)localObject1).getPackageName());
        }
      }
      return;
    }
    catch (Exception localException)
    {
      Object localObject2 = new Collection();
      Object localObject3 = c;
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("???? : ");
      ((StringBuilder)localObject4).append(localException.getMessage());
      ((StringBuilder)localObject4).append(" ?? ???? ???. ");
      ((Collection)localObject2).a((Context)localObject3, "error", ((StringBuilder)localObject4).toString());
    }
  }
}
