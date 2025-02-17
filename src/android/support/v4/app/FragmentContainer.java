package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class FragmentContainer
{
  public FragmentContainer() {}
  
  public abstract View findViewById(int paramInt);
  
  public Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle)
  {
    return Fragment.instantiate(paramContext, paramString, paramBundle);
  }
  
  public abstract boolean onHasView();
}
