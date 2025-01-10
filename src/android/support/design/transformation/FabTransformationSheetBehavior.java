package android.support.design.transformation;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.f;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;
import org.org.android.ReverseListIterator;
import org.org.android.asm.Attribute;
import org.org.android.asm.d;

public class FabTransformationSheetBehavior
  extends FabTransformationBehavior
{
  private Map<View, Integer> c;
  
  public FabTransformationSheetBehavior() {}
  
  public FabTransformationSheetBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void a(View paramView, boolean paramBoolean)
  {
    Object localObject = paramView.getParent();
    if (!(localObject instanceof CoordinatorLayout)) {
      return;
    }
    localObject = (CoordinatorLayout)localObject;
    int k = ((ViewGroup)localObject).getChildCount();
    if ((Build.VERSION.SDK_INT >= 16) && (paramBoolean)) {
      c = new HashMap(k);
    }
    int i = 0;
    while (i < k)
    {
      View localView = ((ViewGroup)localObject).getChildAt(i);
      int j;
      if (((localView.getLayoutParams() instanceof CoordinatorLayout.f)) && ((((CoordinatorLayout.f)localView.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior))) {
        j = 1;
      } else {
        j = 0;
      }
      if ((localView != paramView) && (j == 0)) {
        if (!paramBoolean)
        {
          Map localMap = c;
          if ((localMap != null) && (localMap.containsKey(localView))) {
            ViewCompat.add(localView, ((Integer)c.get(localView)).intValue());
          }
        }
        else
        {
          if (Build.VERSION.SDK_INT >= 16) {
            c.put(localView, Integer.valueOf(localView.getImportantForAccessibility()));
          }
          ViewCompat.add(localView, 4);
        }
      }
      i += 1;
    }
    if (!paramBoolean) {
      c = null;
    }
  }
  
  protected FabTransformationBehavior.e a(Context paramContext, boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = ReverseListIterator.mtrl_fab_transformation_sheet_expand_spec;
    } else {
      i = ReverseListIterator.mtrl_fab_transformation_sheet_collapse_spec;
    }
    FabTransformationBehavior.e localE = new FabTransformationBehavior.e();
    b = Attribute.a(paramContext, i);
    a = new d(17, 0.0F, 0.0F);
    return localE;
  }
  
  protected boolean setChecked(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramView2, paramBoolean1);
    return super.setChecked(paramView1, paramView2, paramBoolean1, paramBoolean2);
  }
}
