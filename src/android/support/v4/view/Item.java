package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import org.org.mult.R.id;

public final class Item
{
  public static boolean a(ViewGroup paramViewGroup)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramViewGroup.isTransitionGroup();
    }
    Boolean localBoolean = (Boolean)paramViewGroup.getTag(R.id.tag_transition_group);
    return ((localBoolean != null) && (localBoolean.booleanValue())) || (paramViewGroup.getBackground() != null) || (ViewCompat.a(paramViewGroup) != null);
  }
}
