package org.org.jaxen.text;

import java.util.Locale;

class ToStringContext
  extends d
{
  static final ToStringContext INSTANCE = new ToStringContext();
  
  ToStringContext()
  {
    super(null);
  }
  
  protected boolean c()
  {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
  }
}
