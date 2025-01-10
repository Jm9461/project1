package org.org.android.asm;

import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.org.android.R.id;

public class Frame
  extends Property<ViewGroup, Float>
{
  public static final Property<ViewGroup, Float> d = new Frame("childrenAlpha");
  
  private Frame(String paramString)
  {
    super(Float.class, paramString);
  }
  
  public void onDraw(ViewGroup paramViewGroup, Float paramFloat)
  {
    float f = paramFloat.floatValue();
    paramViewGroup.setTag(R.id.mtrl_internal_children_alpha_tag, Float.valueOf(f));
    int i = 0;
    int j = paramViewGroup.getChildCount();
    while (i < j)
    {
      paramViewGroup.getChildAt(i).setAlpha(f);
      i += 1;
    }
  }
  
  public Float set(ViewGroup paramViewGroup)
  {
    paramViewGroup = (Float)paramViewGroup.getTag(R.id.mtrl_internal_children_alpha_tag);
    if (paramViewGroup != null) {
      return paramViewGroup;
    }
    return Float.valueOf(1.0F);
  }
}
