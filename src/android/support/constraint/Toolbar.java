package android.support.constraint;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class Toolbar
  extends ViewGroup
{
  Label N;
  
  protected ClassWriter generateDefaultLayoutParams()
  {
    return new ClassWriter(-2, -2);
  }
  
  public ClassWriter generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ClassWriter(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new ConstraintLayout.a(paramLayoutParams);
  }
  
  public Label getConstraintSet()
  {
    if (N == null) {
      N = new Label();
    }
    N.a(this);
    return N;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
}
