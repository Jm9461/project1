package android.support.constraint;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.constraint.asm.asm.a;
import android.util.AttributeSet;
import android.view.View;

public class FloatingActionButton
  extends Item
{
  private a a;
  private int data = 0;
  private int type = 0;
  
  public FloatingActionButton(Context paramContext)
  {
    super(paramContext);
    super.setVisibility(8);
  }
  
  public int getType()
  {
    return data;
  }
  
  protected void init(AttributeSet paramAttributeSet)
  {
    super.init(paramAttributeSet);
    a = new a();
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintLayout_Layout);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == IpAddress.ConstraintLayout_Layout_barrierDirection) {
          setType(paramAttributeSet.getInt(k, 0));
        } else if (k == IpAddress.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
          a.setEnabled(paramAttributeSet.getBoolean(k, true));
        }
        i += 1;
      }
    }
    b = a;
    a();
  }
  
  public void setType(int paramInt)
  {
    data = paramInt;
    type = paramInt;
    if (Build.VERSION.SDK_INT < 17)
    {
      paramInt = data;
      if (paramInt == 5) {
        type = 0;
      } else if (paramInt == 6) {
        type = 1;
      }
    }
    else
    {
      if (1 == getResources().getConfiguration().getLayoutDirection()) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0)
      {
        paramInt = data;
        if (paramInt == 5) {
          type = 1;
        } else if (paramInt == 6) {
          type = 0;
        }
      }
      else
      {
        paramInt = data;
        if (paramInt == 5) {
          type = 0;
        } else if (paramInt == 6) {
          type = 1;
        }
      }
    }
    a.set(type);
  }
}
