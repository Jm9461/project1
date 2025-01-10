package com.lawyer_smartCalendar.utils;

import android.content.Context;
import android.widget.Toast;
import video.api.a.d;

public class Collection
{
  public Collection() {}
  
  public void a(Context paramContext)
  {
    d localD = d.a();
    localD.a(new f(paramContext).setText());
    localD.a(16);
    localD.c();
  }
  
  public void a(Context paramContext, String paramString1, String paramString2)
  {
    a(paramContext);
    switch (paramString1.hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (paramString1.equals("warning"))
      {
        i = 3;
        break label144;
        if (paramString1.equals("error"))
        {
          i = 0;
          break label144;
          if (paramString1.equals("info"))
          {
            i = 2;
            break label144;
            if (paramString1.equals("normal"))
            {
              i = 4;
              break label144;
              if (paramString1.equals("success"))
              {
                i = 1;
                break label144;
              }
            }
          }
        }
      }
    }
    int i = -1;
    label144:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 4) {
              return;
            }
            video.api.a.f.a(paramContext, paramString2, 0).show();
            return;
          }
          video.api.a.f.d(paramContext, paramString2, 0, true).show();
          return;
        }
        video.api.a.f.b(paramContext, paramString2, 0, true).show();
        return;
      }
      video.api.a.f.a(paramContext, paramString2, 0, true).show();
      return;
    }
    video.api.a.f.add(paramContext, paramString2, 0, true).show();
  }
}
