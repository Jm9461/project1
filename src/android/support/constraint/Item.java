package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.asm.asm.i;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class Item
  extends View
{
  protected Context activity;
  protected i b = null;
  protected int c = 0;
  protected int[] d = new int[32];
  protected boolean i = false;
  private String j;
  
  public Item(Context paramContext)
  {
    super(paramContext);
    activity = paramContext;
    init(null);
  }
  
  private void init(String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (activity == null) {
      return;
    }
    paramString = paramString.trim();
    int m = 0;
    try
    {
      k = g.class.getField(paramString).getInt(null);
      m = k;
    }
    catch (Exception localException) {}
    int k = m;
    if (m == 0) {
      k = activity.getResources().getIdentifier(paramString, "id", activity.getPackageName());
    }
    m = k;
    if (k == 0)
    {
      m = k;
      if (isInEditMode())
      {
        m = k;
        if ((getParent() instanceof ConstraintLayout))
        {
          localObject = ((ConstraintLayout)getParent()).a(0, paramString);
          m = k;
          if (localObject != null)
          {
            m = k;
            if ((localObject instanceof Integer)) {
              m = ((Integer)localObject).intValue();
            }
          }
        }
      }
    }
    if (m != 0)
    {
      setTag(m, null);
      return;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Could not find id of \"");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("\"");
    Log.w("ConstraintHelper", ((StringBuilder)localObject).toString());
  }
  
  private void setIds(String paramString)
  {
    if (paramString == null) {
      return;
    }
    int m;
    for (int k = 0;; k = m + 1)
    {
      m = paramString.indexOf(',', k);
      if (m == -1)
      {
        init(paramString.substring(k));
        return;
      }
      init(paramString.substring(k, m));
    }
  }
  
  public void a()
  {
    if (b == null) {
      return;
    }
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    if ((localLayoutParams instanceof ConstraintLayout.a)) {
      a = b;
    }
  }
  
  public void a(ConstraintLayout paramConstraintLayout) {}
  
  public void addButton(ConstraintLayout paramConstraintLayout) {}
  
  public void b(ConstraintLayout paramConstraintLayout)
  {
    if (isInEditMode()) {
      setIds(j);
    }
    Object localObject = b;
    if (localObject == null) {
      return;
    }
    ((i)localObject).h();
    int k = 0;
    while (k < c)
    {
      localObject = paramConstraintLayout.findViewById(d[k]);
      if (localObject != null) {
        b.a(paramConstraintLayout.a((View)localObject));
      }
      k += 1;
    }
  }
  
  public int[] getReferencedIds()
  {
    return Arrays.copyOf(d, c);
  }
  
  protected void init(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintLayout_Layout);
      int m = paramAttributeSet.getIndexCount();
      int k = 0;
      while (k < m)
      {
        int n = paramAttributeSet.getIndex(k);
        if (n == IpAddress.ConstraintLayout_Layout_constraint_referenced_ids)
        {
          j = paramAttributeSet.getString(n);
          setIds(j);
        }
        k += 1;
      }
    }
  }
  
  public void onDraw(Canvas paramCanvas) {}
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (i)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    setMeasuredDimension(0, 0);
  }
  
  public void setReferencedIds(int[] paramArrayOfInt)
  {
    c = 0;
    int k = 0;
    while (k < paramArrayOfInt.length)
    {
      setTag(paramArrayOfInt[k], null);
      k += 1;
    }
  }
  
  public void setTag(int paramInt, Object paramObject)
  {
    int k = c;
    paramObject = d;
    if (k + 1 > paramObject.length) {
      d = Arrays.copyOf(paramObject, paramObject.length * 2);
    }
    paramObject = d;
    k = c;
    paramObject[k] = paramInt;
    c = (k + 1);
  }
}
