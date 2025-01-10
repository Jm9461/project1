package com.devs.curve;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import com.devs.acr.ErrorReporterActivity;
import com.devs.acr.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class d
  implements Thread.UncaughtExceptionHandler
{
  private static d e;
  private static String y = "ACR: New Crash Report Generated";
  private String A;
  private String B;
  private String H;
  private String I;
  private String M;
  private String[] TAG;
  private String a;
  private String b;
  private HashMap<String, String> c = new HashMap();
  private Application context;
  private String d;
  private String f;
  private String g;
  private String h;
  private String i;
  private String m;
  private String n;
  private String p;
  private String q;
  private String s;
  private String t;
  private long x;
  
  static
  {
    a.class.getSimpleName();
  }
  
  private d(Application paramApplication)
  {
    context = paramApplication;
  }
  
  public static d a(Application paramApplication)
  {
    if (e == null) {
      e = new d(paramApplication);
    }
    return e;
  }
  
  private String[] a()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(h);
    ((StringBuilder)localObject).append("/");
    localObject = new File(((StringBuilder)localObject).toString());
    ((File)localObject).mkdir();
    return ((File)localObject).list(new KeyProfilesActivity.1(this));
  }
  
  private boolean b()
  {
    return a().length > 0;
  }
  
  private String d()
  {
    init(context);
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("\nVERSION\t\t: ");
    localStringBuilder1.append(s);
    localStringBuilder1.append("\nPACKAGE      : ");
    localStringBuilder1.append(i);
    localStringBuilder1.append("\nFILE-PATH    : ");
    localStringBuilder1.append(h);
    localStringBuilder1.append("\nPHONE-MODEL  : ");
    localStringBuilder1.append(f);
    localStringBuilder1.append("\nANDROID_VERS : ");
    localStringBuilder1.append(b);
    localStringBuilder1.append("\nBOARD        : ");
    localStringBuilder1.append(d);
    localStringBuilder1.append("\nBRAND        : ");
    localStringBuilder1.append(g);
    localStringBuilder1.append("\nDEVICE       : ");
    localStringBuilder1.append(q);
    localStringBuilder1.append("\nDISPLAY      : ");
    localStringBuilder1.append(a);
    localStringBuilder1.append("\nFINGER-PRINT : ");
    localStringBuilder1.append(M);
    localStringBuilder1.append("\nHOST         : ");
    localStringBuilder1.append(H);
    localStringBuilder1.append("\nID           : ");
    localStringBuilder1.append(m);
    localStringBuilder1.append("\nMODEL        : ");
    localStringBuilder1.append(n);
    localStringBuilder1.append("\nPRODUCT      : ");
    localStringBuilder1.append(A);
    localStringBuilder1.append("\nMANUFACTURER : ");
    localStringBuilder1.append(B);
    localStringBuilder1.append("\nTAGS         : ");
    localStringBuilder1.append(I);
    localStringBuilder1.append("\nTIME         : ");
    localStringBuilder1.append(x);
    localStringBuilder1.append("\nTYPE         : ");
    localStringBuilder1.append(p);
    localStringBuilder1.append("\nUSER         : ");
    localStringBuilder1.append(t);
    localStringBuilder1.append("\nTOTAL-INTERNAL-MEMORY     : ");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(getTotalInternalMemorySize());
    localStringBuilder2.append(" mb");
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append("\nAVAILABLE-INTERNAL-MEMORY : ");
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(getAvailableInternalMemorySize());
    localStringBuilder2.append(" mb");
    localStringBuilder1.append(localStringBuilder2.toString());
    return localStringBuilder1.toString();
  }
  
  private String doInBackground()
  {
    String str1 = "";
    Iterator localIterator = c.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      String str2 = (String)c.get(localObject);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str1);
      localStringBuilder.append(localObject);
      localStringBuilder.append(" = ");
      localStringBuilder.append(str2);
      localStringBuilder.append("\n");
      str1 = localStringBuilder.toString();
    }
    return str1;
  }
  
  private long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l / 1048576L;
  }
  
  private long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l / 1048576L;
  }
  
  private void i(String paramString) {}
  
  private void init(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      s = versionName;
      i = packageName;
      f = Build.MODEL;
      b = Build.VERSION.RELEASE;
      d = Build.BOARD;
      g = Build.BRAND;
      q = Build.DEVICE;
      a = Build.DISPLAY;
      M = Build.FINGERPRINT;
      H = Build.HOST;
      m = Build.ID;
      n = Build.MODEL;
      A = Build.PRODUCT;
      B = Build.MANUFACTURER;
      I = Build.TAGS;
      x = Build.TIME;
      p = Build.TYPE;
      t = Build.USER;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void sendMail(Context paramContext, String paramString)
  {
    i("====sendErrorMail");
    Intent localIntent = new Intent("android.intent.action.SEND");
    String str = y;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n\n");
    localStringBuilder.append(paramString);
    localStringBuilder.append("\n\n");
    paramString = localStringBuilder.toString();
    localIntent.putExtra("android.intent.extra.EMAIL", TAG);
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.putExtra("android.intent.extra.SUBJECT", str);
    localIntent.setType("message/rfc822");
    localIntent.setFlags(268435456);
    paramContext.startActivity(Intent.createChooser(localIntent, "Title:"));
  }
  
  private void writeToFile(String paramString)
  {
    i("====SaveAsFile");
    try
    {
      int j = new Random().nextInt(99999);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("stack-");
      ((StringBuilder)localObject).append(j);
      ((StringBuilder)localObject).append(".stacktrace");
      localObject = ((StringBuilder)localObject).toString();
      Application localApplication = context;
      localObject = localApplication.openFileOutput((String)localObject, 0);
      ((FileOutputStream)localObject).write(paramString.getBytes());
      ((FileOutputStream)localObject).close();
      return;
    }
    catch (Exception paramString) {}
  }
  
  void a(Context paramContext)
  {
    try
    {
      Object localObject1 = paramContext.getFilesDir().getAbsolutePath();
      h = ((String)localObject1);
      boolean bool = b();
      if (bool)
      {
        localObject1 = new StringBuilder();
        String[] arrayOfString = a();
        int j = 0;
        int i1 = arrayOfString.length;
        int k = 0;
        while (k < i1)
        {
          String str1 = arrayOfString[k];
          if (j <= 5)
          {
            ((StringBuilder)localObject1).append("New Trace collected :\n=====================\n");
            localObject2 = new StringBuilder();
            str2 = h;
            ((StringBuilder)localObject2).append(str2);
            ((StringBuilder)localObject2).append("/");
            ((StringBuilder)localObject2).append(str1);
            localObject2 = ((StringBuilder)localObject2).toString();
            localObject2 = new BufferedReader(new FileReader((String)localObject2));
            for (;;)
            {
              str2 = ((BufferedReader)localObject2).readLine();
              if (str2 == null) {
                break;
              }
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(str2);
              localStringBuilder.append("\n");
              ((StringBuilder)localObject1).append(localStringBuilder.toString());
            }
            ((BufferedReader)localObject2).close();
          }
          Object localObject2 = new StringBuilder();
          String str2 = h;
          ((StringBuilder)localObject2).append(str2);
          ((StringBuilder)localObject2).append("/");
          ((StringBuilder)localObject2).append(str1);
          new File(((StringBuilder)localObject2).toString()).delete();
          k += 1;
          j += 1;
        }
        sendMail(paramContext, ((StringBuilder)localObject1).toString());
      }
      return;
    }
    catch (Exception paramContext)
    {
      ((Exception)paramContext).printStackTrace();
    }
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    i("====uncaughtException");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Error Report collected on : ");
    localStringBuilder.append(new Date().toString());
    localStringBuilder.append("\n\nInformations :\n==============");
    localStringBuilder.append(d());
    paramThread = doInBackground();
    if (!paramThread.equals(""))
    {
      localStringBuilder.append("\n\nCustom Informations :\n==============\n");
      localStringBuilder.append(paramThread);
    }
    localStringBuilder.append("\n\nStack :\n==============\n");
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    localStringBuilder.append(localStringWriter.toString());
    localStringBuilder.append("\nCause :\n==============");
    for (paramThread = paramThrowable.getCause(); paramThread != null; paramThread = paramThread.getCause())
    {
      paramThread.printStackTrace(localPrintWriter);
      localStringBuilder.append(localStringWriter.toString());
    }
    localPrintWriter.close();
    localStringBuilder.append("\n\n**** End of current Report ***");
    paramThread = new StringBuilder();
    paramThread.append("====uncaughtException \n Report: ");
    paramThread.append(localStringBuilder.toString());
    i(paramThread.toString());
    writeToFile(localStringBuilder.toString());
    paramThread = new Intent(context, ErrorReporterActivity.class);
    paramThread.setFlags(268435456);
    context.startActivity(paramThread);
    Process.killProcess(Process.myPid());
    System.exit(10);
  }
}
