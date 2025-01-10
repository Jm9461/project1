package org.org.jaxen.ui;

import a.b.g.e.b.g;
import a.b.g.e.c.d;
import a.b.g.g.g;
import a.b.g.g.n;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.org.jaxen.asm.a;
import org.org.jaxen.util.LruCache;
import org.org.jaxen.util.f;

public class b
{
  private static final Comparator<byte[]> a = new Version.BuildAwareOrder();
  private static final ClassWriter b;
  static final n<String, ArrayList<c.d<b.g>>> c;
  static final Object d;
  static final g<String, Typeface> g = new LruCache(16);
  
  static
  {
    b = new ClassWriter("fonts", 10, 10000);
    d = new Object();
    c = new f();
  }
  
  public static Typeface a(android.content.Context paramContext, h paramH, android.support.v4.content.view.Label paramLabel, Handler paramHandler, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramH.c());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(paramInt2);
    localObject = ((StringBuilder)localObject).toString();
    Typeface localTypeface = (Typeface)g.get(localObject);
    if (localTypeface != null)
    {
      if (paramLabel != null)
      {
        paramLabel.setText(localTypeface);
        return localTypeface;
      }
    }
    else
    {
      if ((paramBoolean) && (paramInt1 == -1))
      {
        paramContext = a(paramContext, paramH, paramInt2);
        if (paramLabel != null)
        {
          paramInt1 = d;
          if (paramInt1 == 0) {
            paramLabel.a(b, paramHandler);
          } else {
            paramLabel.a(paramInt1, paramHandler);
          }
        }
        return b;
      }
      paramH = new Context(paramContext, paramH, paramInt2, (String)localObject);
      if (paramBoolean)
      {
        paramContext = b;
        try
        {
          paramContext = paramContext.get(paramH, paramInt1);
          return b;
        }
        catch (InterruptedException paramContext)
        {
          return null;
        }
      }
      if (paramLabel == null) {
        paramContext = null;
      } else {
        paramContext = new d(paramLabel, paramHandler);
      }
      paramLabel = d;
      try
      {
        if (c.containsKey(localObject))
        {
          if (paramContext != null) {
            ((ArrayList)c.get(localObject)).add(paramContext);
          }
          return null;
        }
        if (paramContext != null)
        {
          paramHandler = new ArrayList();
          paramHandler.add(paramContext);
          c.put(localObject, paramHandler);
        }
        b.a(paramH, new Type((String)localObject));
        return null;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    }
    return localTypeface;
  }
  
  private static List a(h paramH, Resources paramResources)
  {
    if (paramH.getTitle() != null) {
      return paramH.getTitle();
    }
    return android.support.v4.content.view.Type.getValue(paramResources, paramH.e());
  }
  
  public static Map a(android.content.Context paramContext, Label[] paramArrayOfLabel, CancellationSignal paramCancellationSignal)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfLabel.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayOfLabel[i];
      if (((Label)localObject).c() == 0)
      {
        localObject = ((Label)localObject).getValue();
        if (!localHashMap.containsKey(localObject)) {
          localHashMap.put(localObject, a.read(paramContext, paramCancellationSignal, (Uri)localObject));
        }
      }
      i += 1;
    }
    return Collections.unmodifiableMap(localHashMap);
  }
  
  static e a(android.content.Context paramContext, h paramH, int paramInt)
  {
    try
    {
      paramH = initialize(paramContext, null, paramH);
      int j = paramH.getPaddingLeft();
      int i = -3;
      if (j == 0)
      {
        paramContext = org.org.jaxen.asm.Type.a(paramContext, null, paramH.getResultObject(), paramInt);
        if (paramContext != null) {
          i = 0;
        }
        return new e(paramContext, i);
      }
      if (paramH.getPaddingLeft() == 1) {
        i = -2;
      }
      return new e(null, i);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return new e(null, -1);
  }
  
  private static List decode(Signature[] paramArrayOfSignature)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramArrayOfSignature.length)
    {
      localArrayList.add(paramArrayOfSignature[i].toByteArray());
      i += 1;
    }
    return localArrayList;
  }
  
  private static boolean get(List paramList1, List paramList2)
  {
    if (paramList1.size() != paramList2.size()) {
      return false;
    }
    int i = 0;
    while (i < paramList1.size())
    {
      if (!Arrays.equals((byte[])paramList1.get(i), (byte[])paramList2.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static ProviderInfo initialize(PackageManager paramPackageManager, h paramH, Resources paramResources)
  {
    String str = paramH.f();
    ProviderInfo localProviderInfo = paramPackageManager.resolveContentProvider(str, 0);
    if (localProviderInfo != null)
    {
      if (packageName.equals(paramH.getGroupId()))
      {
        paramPackageManager = decode(getPackageInfopackageName, 64).signatures);
        Collections.sort(paramPackageManager, a);
        paramH = a(paramH, paramResources);
        int i = 0;
        while (i < paramH.size())
        {
          paramResources = new ArrayList((Collection)paramH.get(i));
          Collections.sort(paramResources, a);
          if (get(paramPackageManager, paramResources)) {
            return localProviderInfo;
          }
          i += 1;
        }
        return null;
      }
      paramPackageManager = new StringBuilder();
      paramPackageManager.append("Found content provider ");
      paramPackageManager.append(str);
      paramPackageManager.append(", but package was not ");
      paramPackageManager.append(paramH.getGroupId());
      throw new PackageManager.NameNotFoundException(paramPackageManager.toString());
    }
    paramPackageManager = new StringBuilder();
    paramPackageManager.append("No package found for authority: ");
    paramPackageManager.append(str);
    paramPackageManager = new PackageManager.NameNotFoundException(paramPackageManager.toString());
    throw paramPackageManager;
  }
  
  public static MultiBackgroundInitializer.MultiBackgroundInitializerResults initialize(android.content.Context paramContext, CancellationSignal paramCancellationSignal, h paramH)
  {
    ProviderInfo localProviderInfo = initialize(paramContext.getPackageManager(), paramH, paramContext.getResources());
    if (localProviderInfo == null) {
      return new MultiBackgroundInitializer.MultiBackgroundInitializerResults(1, null);
    }
    return new MultiBackgroundInitializer.MultiBackgroundInitializerResults(0, load(paramContext, paramH, authority, paramCancellationSignal));
  }
  
  static Label[] load(android.content.Context paramContext, h paramH, String paramString, CancellationSignal paramCancellationSignal)
  {
    ArrayList localArrayList = new ArrayList();
    Uri localUri1 = new Uri.Builder().scheme("content").authority(paramString).build();
    Uri localUri2 = new Uri.Builder().scheme("content").authority(paramString).appendPath("file").build();
    paramString = null;
    Object localObject = null;
    try
    {
      int i = Build.VERSION.SDK_INT;
      if (i > 16)
      {
        paramContext = paramContext.getContentResolver();
        paramH = paramH.getIntent();
        paramString = localObject;
      }
      try
      {
        paramContext = paramContext.query(localUri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { paramH }, null, paramCancellationSignal);
        break label246;
        paramString = localObject;
        paramContext = paramContext.getContentResolver();
        paramString = localObject;
        paramH = paramH.getIntent();
        paramString = localObject;
        paramContext = paramContext.query(localUri1, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { paramH }, null);
        label246:
        paramH = localArrayList;
        if (paramContext != null)
        {
          paramString = paramContext;
          i = paramContext.getCount();
          paramH = localArrayList;
          if (i > 0)
          {
            paramString = paramContext;
            int m = paramContext.getColumnIndex("result_code");
            paramString = paramContext;
            paramCancellationSignal = new ArrayList();
            try
            {
              int n = paramContext.getColumnIndex("_id");
              int i1 = paramContext.getColumnIndex("file_id");
              int i2 = paramContext.getColumnIndex("font_ttc_index");
              int i3 = paramContext.getColumnIndex("font_weight");
              int i4 = paramContext.getColumnIndex("font_italic");
              for (;;)
              {
                boolean bool = paramContext.moveToNext();
                paramH = paramCancellationSignal;
                if (!bool) {
                  break;
                }
                if (m != -1) {
                  i = paramContext.getInt(m);
                } else {
                  i = 0;
                }
                int j;
                if (i2 != -1) {
                  j = paramContext.getInt(i2);
                } else {
                  j = 0;
                }
                if (i1 == -1) {
                  paramH = ContentUris.withAppendedId(localUri1, paramContext.getLong(n));
                } else {
                  paramH = ContentUris.withAppendedId(localUri2, paramContext.getLong(i1));
                }
                int k;
                if (i3 != -1) {
                  k = paramContext.getInt(i3);
                } else {
                  k = 400;
                }
                if (i4 != -1)
                {
                  int i5 = paramContext.getInt(i4);
                  if (i5 == 1)
                  {
                    bool = true;
                    break label500;
                  }
                }
                bool = false;
                label500:
                paramCancellationSignal.add(new Label(paramH, j, k, bool, i));
              }
              if (paramContext == null) {
                break label538;
              }
            }
            catch (Throwable paramH) {}
          }
        }
        paramContext.close();
        label538:
        return (Label[])paramH.toArray(new Label[0]);
      }
      catch (Throwable paramH)
      {
        paramContext = paramString;
      }
      if (paramContext == null) {
        break label569;
      }
    }
    catch (Throwable paramH)
    {
      paramContext = paramString;
    }
    paramContext.close();
    label569:
    throw paramH;
  }
}
