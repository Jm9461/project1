package spongycastle.mirrajabi.persiancalendar.crypto;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spongycastle.mirrajabi.persiancalendar.R.drawable;
import spongycastle.mirrajabi.persiancalendar.R.string;
import spongycastle.mirrajabi.persiancalendar.asm.ByteVector;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.ActionMenuItemView;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.Frame;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.Item;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.d;
import spongycastle.mirrajabi.persiancalendar.crypto.params.a;
import spongycastle.mirrajabi.persiancalendar.crypto.params.h;
import spongycastle.mirrajabi.persiancalendar.crypto.util.IOException;

public class f
{
  private static WeakReference<ir.mirrajabi.persiancalendar.f.b> v;
  private final String a = ir.mirrajabi.persiancalendar.f.b.class.getName();
  private int b = -16777216;
  private char[] c = i.c;
  private Context context;
  private int d = -65536;
  private List<ir.mirrajabi.persiancalendar.f.g.b> e;
  private int f = -1;
  private Typeface font;
  private boolean g = true;
  private String[] h = { "???????", "????????", "?????", "???", "?????", "??????", "???", "????", "???", "??", "????", "?????" };
  private boolean hasTimeZone;
  private int i = -16776961;
  private int j = -3355444;
  private int k = -65536;
  private float l = 20.0F;
  private int m = R.drawable.circle_current_day;
  private spongycastle.mirrajabi.persiancalendar.crypto.params.g mId;
  private float n = 25.0F;
  private int p = R.drawable.circle_select;
  private int q = -65536;
  private List<ir.mirrajabi.persiancalendar.f.g.b> r;
  private boolean s = true;
  private spongycastle.mirrajabi.persiancalendar.crypto.params.Object t;
  private Typeface type;
  private String[] w = { "????", "???????", "??????", "???????", "????????", "????????", "????" };
  private h x;
  
  private f(Context paramContext)
  {
    context = paramContext;
    r = new ArrayList();
  }
  
  private List a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject, List paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      spongycastle.mirrajabi.persiancalendar.crypto.asm.b localB = (spongycastle.mirrajabi.persiancalendar.crypto.asm.b)paramList.next();
      if (localB.b().a(paramObject)) {
        localArrayList.add(localB);
      }
    }
    return localArrayList;
  }
  
  public static f a(Context paramContext)
  {
    WeakReference localWeakReference = v;
    if ((localWeakReference == null) || (localWeakReference.get() == null)) {
      v = new WeakReference(new f(paramContext.getApplicationContext()));
    }
    return (f)v.get();
  }
  
  private String convertStreamToString(InputStream paramInputStream)
  {
    paramInputStream = new Scanner(paramInputStream).useDelimiter("\\A");
    if (paramInputStream.hasNext()) {
      return paramInputStream.next();
    }
    return "";
  }
  
  private List doInBackground()
  {
    ArrayList localArrayList = new ArrayList();
    int i1 = R.string.events;
    try
    {
      JSONArray localJSONArray = new JSONObject(read(i1)).getJSONArray("events");
      int i2 = localJSONArray.length();
      i1 = 0;
      while (i1 < i2)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i1);
        int i3 = localJSONObject.getInt("year");
        int i4 = localJSONObject.getInt("month");
        int i5 = localJSONObject.getInt("day");
        String str = localJSONObject.getString("title");
        boolean bool = localJSONObject.getBoolean("holiday");
        localArrayList.add(new spongycastle.mirrajabi.persiancalendar.crypto.asm.b(new spongycastle.mirrajabi.persiancalendar.crypto.asm.Object(i3, i4, i5), str, bool));
        i1 += 1;
      }
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
      Log.e(a, localJSONException.getMessage());
    }
    return localArrayList;
  }
  
  private void onCreate()
  {
    if (font == null) {
      font = Typeface.createFromAsset(context.getAssets(), "fonts/NotoNaskhArabic-Regular.ttf");
    }
    if (type == null) {
      type = Typeface.createFromAsset(context.getAssets(), "fonts/NotoNaskhArabic-Regular.ttf");
    }
  }
  
  private String read(int paramInt)
  {
    return convertStreamToString(context.getResources().openRawResource(paramInt));
  }
  
  public String a(String paramString)
  {
    if (c == i.b) {
      return paramString;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    paramString = paramString.toCharArray();
    int i2 = paramString.length;
    int i1 = 0;
    while (i1 < i2)
    {
      char c1 = paramString[i1];
      if (Character.isDigit(c1))
      {
        char[] arrayOfChar = c;
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(c1);
        localStringBuilder2.append("");
        localStringBuilder1.append(arrayOfChar[Integer.parseInt(localStringBuilder2.toString())]);
      }
      else
      {
        localStringBuilder1.append(c1);
      }
      i1 += 1;
    }
    return localStringBuilder1.toString();
  }
  
  public String a(Frame paramFrame)
  {
    return b(paramFrame)[(paramFrame.a() - 1)];
  }
  
  public String a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject, boolean paramBoolean)
  {
    Object localObject = "";
    int i1 = 1;
    Iterator localIterator = add(paramObject).iterator();
    paramObject = (spongycastle.mirrajabi.persiancalendar.crypto.asm.Object)localObject;
    while (localIterator.hasNext())
    {
      localObject = (spongycastle.mirrajabi.persiancalendar.crypto.asm.b)localIterator.next();
      if (((spongycastle.mirrajabi.persiancalendar.crypto.asm.b)localObject).c() == paramBoolean)
      {
        if (i1 != 0)
        {
          i1 = 0;
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramObject);
          localStringBuilder.append("\n");
          paramObject = localStringBuilder.toString();
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramObject);
        localStringBuilder.append(((spongycastle.mirrajabi.persiancalendar.crypto.asm.b)localObject).a());
        paramObject = localStringBuilder.toString();
      }
    }
    return paramObject;
  }
  
  public List a(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    spongycastle.mirrajabi.persiancalendar.crypto.asm.Object localObject1 = a();
    paramInt = localObject1.a() - paramInt - 1;
    int i2 = localObject1.get() + paramInt / 12;
    int i3 = paramInt % 12;
    int i1 = i2;
    paramInt = i3;
    if (i3 < 0)
    {
      i1 = i2 - 1;
      paramInt = i3 + 12;
    }
    localObject1.a(paramInt + 1);
    localObject1.setId(i1);
    localObject1.add(1);
    paramInt = ByteVector.a(localObject1).set() % 7;
    try
    {
      spongycastle.mirrajabi.persiancalendar.crypto.asm.Object localObject2 = a();
      i1 = 1;
      while (i1 <= 31)
      {
        localObject1.add(i1);
        d localD = new d();
        localD.setId(get(i1));
        localD.d(paramInt);
        if (paramInt != 6)
        {
          bool = TextUtils.isEmpty(a(localObject1, true));
          if (bool) {}
        }
        else
        {
          localD.b(true);
        }
        if (g)
        {
          i2 = d(localObject1).size();
          if (i2 > 0) {
            localD.a(true, true);
          }
        }
        if (s)
        {
          i2 = a(localObject1).size();
          if (i2 > 0) {
            localD.a(true, false);
          }
        }
        localD.d(localObject1.clone());
        boolean bool = localObject1.a(localObject2);
        if (bool) {
          localD.d(true);
        }
        localArrayList.add(localD);
        i2 = paramInt + 1;
        paramInt = i2;
        if (i2 == 7) {
          paramInt = 0;
        }
        i1 += 1;
      }
      return localArrayList;
    }
    catch (IOException localIOException) {}
    return localArrayList;
  }
  
  public List a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    if (e == null) {
      e = doInBackground();
    }
    return a(paramObject, e);
  }
  
  public spongycastle.mirrajabi.persiancalendar.crypto.asm.Object a()
  {
    return ByteVector.b(new Item(getCalendar(new Date())));
  }
  
  public f a(float paramFloat)
  {
    n = paramFloat;
    return this;
  }
  
  public f a(Typeface paramTypeface)
  {
    type = paramTypeface;
    return this;
  }
  
  public f a(spongycastle.mirrajabi.persiancalendar.crypto.params.Object paramObject)
  {
    t = paramObject;
    return this;
  }
  
  public f a(a paramA)
  {
    return this;
  }
  
  public f a(spongycastle.mirrajabi.persiancalendar.crypto.params.g paramG)
  {
    mId = paramG;
    return this;
  }
  
  public f a(h paramH)
  {
    x = paramH;
    return this;
  }
  
  public void a(TextView paramTextView)
  {
    b(paramTextView);
    paramTextView.setText(c(paramTextView.getText().toString()));
  }
  
  public List add(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    List localList = a(paramObject);
    localList.addAll(d(paramObject));
    return localList;
  }
  
  public f add(int paramInt)
  {
    b = paramInt;
    return this;
  }
  
  public spongycastle.mirrajabi.persiancalendar.crypto.params.Object add()
  {
    return t;
  }
  
  public void add(spongycastle.mirrajabi.persiancalendar.crypto.asm.b paramB)
  {
    r.add(paramB);
  }
  
  public f b(float paramFloat)
  {
    l = paramFloat;
    return this;
  }
  
  public f b(int paramInt)
  {
    d = paramInt;
    return this;
  }
  
  public void b(TextView paramTextView)
  {
    onCreate();
    paramTextView.setTypeface(font);
  }
  
  public boolean b()
  {
    return g;
  }
  
  public String[] b(Frame paramFrame)
  {
    return (String[])h.clone();
  }
  
  public int c()
  {
    return q;
  }
  
  public String c(String paramString)
  {
    String str = paramString;
    if (Build.VERSION.SDK_INT <= 16) {
      str = spongycastle.mirrajabi.persiancalendar.asm.g.a(paramString);
    }
    return str;
  }
  
  public f c(int paramInt)
  {
    f = paramInt;
    return this;
  }
  
  public int clear()
  {
    return m;
  }
  
  public f clear(int paramInt)
  {
    m = paramInt;
    return this;
  }
  
  public f close(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public int d()
  {
    return f;
  }
  
  public List d(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    return a(paramObject, r);
  }
  
  public f d(int paramInt)
  {
    p = paramInt;
    return this;
  }
  
  public int e()
  {
    return d;
  }
  
  public f e(int paramInt)
  {
    q = paramInt;
    return this;
  }
  
  public f e(boolean paramBoolean)
  {
    s = paramBoolean;
    return this;
  }
  
  public int f()
  {
    return i;
  }
  
  public f f(int paramInt)
  {
    k = paramInt;
    return this;
  }
  
  public f f(Typeface paramTypeface)
  {
    font = paramTypeface;
    return this;
  }
  
  public int g()
  {
    return b;
  }
  
  public Typeface get()
  {
    return type;
  }
  
  public String get(int paramInt)
  {
    return a(Integer.toString(paramInt));
  }
  
  public Calendar getCalendar(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    if (hasTimeZone) {
      localCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
    }
    localCalendar.setTime(paramDate);
    return localCalendar;
  }
  
  public spongycastle.mirrajabi.persiancalendar.crypto.params.g getItemId()
  {
    return mId;
  }
  
  public int h()
  {
    return j;
  }
  
  public float i()
  {
    return l;
  }
  
  public float j()
  {
    return n;
  }
  
  public int k()
  {
    return k;
  }
  
  public boolean m()
  {
    return s;
  }
  
  public h r()
  {
    return x;
  }
  
  public f setColor(int paramInt)
  {
    j = paramInt;
    return this;
  }
  
  public int size()
  {
    return p;
  }
  
  public String write(Frame paramFrame)
  {
    Object localObject;
    if ((paramFrame instanceof ActionMenuItemView))
    {
      localObject = ByteVector.a((ActionMenuItemView)paramFrame);
    }
    else
    {
      localObject = paramFrame;
      if ((paramFrame instanceof spongycastle.mirrajabi.persiancalendar.crypto.asm.Object)) {
        localObject = ByteVector.a((spongycastle.mirrajabi.persiancalendar.crypto.asm.Object)paramFrame);
      }
    }
    return w[(localObject.set() % 7)];
  }
}
