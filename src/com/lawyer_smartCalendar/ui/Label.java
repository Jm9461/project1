package com.lawyer_smartCalendar.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.g;
import com.lawyer_smartCalendar.utils.e;

class Label
  implements AHBottomNavigation.g
{
  Label(c paramC) {}
  
  public boolean a(int paramInt, boolean paramBoolean)
  {
    Object localObject;
    switch (paramInt)
    {
    default: 
      break;
    case 7: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      c.a(a).show(2131296450, new WelcomeFragment());
      ce = "none";
      break;
    case 6: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      c.a(a).show(2131296450, new WelcomeFragment());
      ce = "none";
      break;
    case 5: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      c.a(a).show(2131296450, new WelcomeFragment());
      ce = "none";
      break;
    case 4: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      c.a(a).show(2131296450, new WebViewFragment());
      ce = "none";
      break;
    case 3: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      c.a(a).show(2131296450, new DialerFragment());
      ce = "none";
      break;
    case 2: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      localObject = new h();
      c.a(a).show(2131296450, (android.support.v4.app.Fragment)localObject);
      e.b = (h)localObject;
      ce = "none";
      break;
    case 1: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      localObject = new Fragment();
      c.a(a).show(2131296450, (android.support.v4.app.Fragment)localObject);
      e.d = (Fragment)localObject;
      ce = "none";
      break;
    case 0: 
      localObject = a;
      c.a((c)localObject, ((android.support.v4.app.Fragment)localObject).getActivity().getSupportFragmentManager().beginTransaction());
      localObject = new b();
      c.a(a).show(2131296450, (android.support.v4.app.Fragment)localObject);
      e.a = (b)localObject;
      ce = "note";
    }
    c.a(a).commit();
    return true;
  }
}
