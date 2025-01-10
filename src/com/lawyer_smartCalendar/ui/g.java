package com.lawyer_smartCalendar.ui;

import android.widget.TextView;
import com.github.clans.extract.FloatingActionMenu;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.GregorianCalendar;
import spongycastle.mirrajabi.persiancalendar.crypto.f;

class g
  implements spongycastle.mirrajabi.persiancalendar.crypto.params.Object
{
  g(Item paramItem, f paramF, TextView paramTextView) {}
  
  public void set(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(a.write(paramObject));
    ((StringBuilder)localObject1).append(" ");
    ((StringBuilder)localObject1).append(a.get(paramObject.getId()));
    ((StringBuilder)localObject1).append(" ");
    ((StringBuilder)localObject1).append(a.a(paramObject));
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append(" ??? ");
    localStringBuilder.append(a.get(paramObject.get()));
    ((TextView)localObject2).setText(localStringBuilder.toString());
    g.this$0.open(true);
    localObject1 = new Calendar();
    ((Calendar)localObject1).set(paramObject.get(), paramObject.a() - 1, paramObject.getId());
    paramObject = g;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((GregorianCalendar)localObject1).getTimeInMillis());
    ((StringBuilder)localObject2).append("");
    Item.a(paramObject, ((StringBuilder)localObject2).toString());
  }
}
