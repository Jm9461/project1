package com.lawyer_smartCalendar.ui;

import android.widget.TextView;
import com.github.clans.extract.FloatingActionMenu;
import spongycastle.mirrajabi.persiancalendar.crypto.params.g;

class f
  implements g
{
  f(Item paramItem, TextView paramTextView, spongycastle.mirrajabi.persiancalendar.crypto.f paramF) {}
  
  public void a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    Item.a(j, paramObject);
    Item.set(j, paramObject);
    TextView localTextView = c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(b.a(paramObject));
    localStringBuilder.append(" ??? ");
    localStringBuilder.append(b.get(paramObject.get()));
    localTextView.setText(localStringBuilder.toString());
    j.get(paramObject.get(), paramObject.a());
    j.this$0.close(false);
  }
}
