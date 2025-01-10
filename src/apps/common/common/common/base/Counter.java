package apps.common.common.common.base;

import android.view.View;
import android.widget.RelativeLayout;

public class Counter
{
  public static int insert(RelativeLayout paramRelativeLayout, int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 3)
        {
          if (paramInt != 4) {
            return 0;
          }
          return paramRelativeLayout.getRight();
        }
        return paramRelativeLayout.getLeft();
      }
      return paramRelativeLayout.getBottom();
    }
    return paramRelativeLayout.getTop();
  }
}
