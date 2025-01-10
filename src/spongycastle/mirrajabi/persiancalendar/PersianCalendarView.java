package spongycastle.mirrajabi.persiancalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import ir.mirrajabi.persiancalendar.f.e.a;
import spongycastle.mirrajabi.persiancalendar.crypto.engines.TitlePageIndicator;
import spongycastle.mirrajabi.persiancalendar.crypto.f;
import spongycastle.mirrajabi.persiancalendar.crypto.params.Object;
import spongycastle.mirrajabi.persiancalendar.crypto.params.g;
import spongycastle.mirrajabi.persiancalendar.crypto.params.h;

public class PersianCalendarView
  extends FrameLayout
{
  TitlePageIndicator j = null;
  private f o;
  
  public PersianCalendarView(Context paramContext)
  {
    super(paramContext);
    a(paramContext, null);
  }
  
  public PersianCalendarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }
  
  public PersianCalendarView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, paramAttributeSet);
  }
  
  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    o = f.a(paramContext);
    LayoutInflater.from(paramContext).inflate(R.layout.view_calendar, this, true);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PersianCalendarView, 0, 0);
    if (paramAttributeSet.hasValue(R.styleable.PersianCalendarView_pcv_typefacePath))
    {
      Typeface localTypeface = Typeface.createFromAsset(paramContext.getAssets(), paramAttributeSet.getString(R.styleable.PersianCalendarView_pcv_typefacePath));
      if (localTypeface != null) {
        o.f(localTypeface);
      }
    }
    if (paramAttributeSet.hasValue(R.styleable.PersianCalendarView_pcv_headersTypefacePath))
    {
      paramContext = Typeface.createFromAsset(paramContext.getAssets(), paramAttributeSet.getString(R.styleable.PersianCalendarView_pcv_headersTypefacePath));
      if (paramContext != null) {
        o.a(paramContext);
      }
    }
    o.a(paramAttributeSet.getDimensionPixelSize(R.styleable.PersianCalendarView_pcv_fontSize, 25));
    o.b(paramAttributeSet.getDimensionPixelSize(R.styleable.PersianCalendarView_pcv_headersFontSize, 20));
    paramContext = o;
    paramContext.clear(paramAttributeSet.getResourceId(R.styleable.PersianCalendarView_pcv_todayBackground, paramContext.clear()));
    paramContext = o;
    paramContext.d(paramAttributeSet.getResourceId(R.styleable.PersianCalendarView_pcv_selectedDayBackground, paramContext.size()));
    paramContext = o;
    paramContext.setColor(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorDayName, paramContext.h()));
    paramContext = o;
    paramContext.add(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorBackground, paramContext.e()));
    paramContext = o;
    paramContext.b(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorHolidaySelected, paramContext.e()));
    paramContext = o;
    paramContext.e(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorHoliday, paramContext.c()));
    paramContext = o;
    paramContext.close(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorNormalDaySelected, paramContext.f()));
    paramContext = o;
    paramContext.c(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_colorNormalDay, paramContext.d()));
    paramContext = o;
    paramContext.f(paramAttributeSet.getColor(R.styleable.PersianCalendarView_pcv_eventUnderlineColor, paramContext.k()));
    try
    {
      paramContext = a.class.newInstance();
      j = ((TitlePageIndicator)paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (InstantiationException paramContext)
    {
      paramContext.printStackTrace();
    }
    setBackgroundColor(o.g());
    paramContext = ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
    paramContext.add(R.id.fragment_holder, j, a.class.getName());
    paramContext.commit();
    paramAttributeSet.recycle();
  }
  
  public void a(int paramInt)
  {
    j.onDraw(-paramInt);
  }
  
  public void b()
  {
    a(-1);
  }
  
  public void f()
  {
    a(1);
  }
  
  public f getCalendar()
  {
    return o;
  }
  
  public void setOnDayClickedListener(Object paramObject)
  {
    o.a(paramObject);
  }
  
  public void setOnDayLongClickedListener(h paramH)
  {
    o.a(paramH);
  }
  
  public void setOnMonthChangedListener(g paramG)
  {
    o.a(paramG);
  }
}
