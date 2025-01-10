package org.org.asm;

import android.view.View;
import android.view.WindowId;

class GOST3410ParameterSpec
  implements c
{
  private final WindowId digestParamSetOID;
  
  GOST3410ParameterSpec(View paramView)
  {
    digestParamSetOID = paramView.getWindowId();
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof GOST3410ParameterSpec)) && (digestParamSetOID.equals(digestParamSetOID));
  }
  
  public int hashCode()
  {
    return digestParamSetOID.hashCode();
  }
}
