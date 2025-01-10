package org.org.asm;

import a.b.f.c.k;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class Type
  extends h
{
  private static final Property<View, PointF> DOUBLE = new RefList(PointF.class, "topLeft");
  private static final Property<c.k, PointF> FLOAT_TYPE;
  private static final Property<View, PointF> INTEGER = new CircularProgressDrawable.2(PointF.class, "position");
  private static final Property<c.k, PointF> INT_TYPE;
  private static IntEvaluator LONG = new IntEvaluator();
  private static final Property<View, PointF> LONG_TYPE;
  private static final String[] b = { "android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY" };
  private static final Property<Drawable, PointF> d = new Attribute(PointF.class, "boundsOrigin");
  private int[] a = new int[2];
  private boolean c = false;
  private boolean e = false;
  
  static
  {
    INT_TYPE = new MaterialRippleLayout.1(PointF.class, "topLeft");
    FLOAT_TYPE = new MaterialRippleLayout.4(PointF.class, "bottomRight");
    LONG_TYPE = new CircularProgressDrawable.1(PointF.class, "bottomRight");
  }
  
  public Type() {}
  
  private boolean a(View paramView1, View paramView2)
  {
    if (e)
    {
      Label localLabel = b(paramView1, true);
      if (localLabel == null) {
        return paramView1 == paramView2;
      }
      return paramView2 == a;
    }
    return true;
  }
  
  private void draw(Label paramLabel)
  {
    View localView = a;
    if ((ViewCompat.get(localView)) || (localView.getWidth() != 0) || (localView.getHeight() != 0))
    {
      c.put("android:changeBounds:bounds", new Rect(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom()));
      c.put("android:changeBounds:parent", a.getParent());
      if (e)
      {
        a.getLocationInWindow(a);
        c.put("android:changeBounds:windowX", Integer.valueOf(a[0]));
        c.put("android:changeBounds:windowY", Integer.valueOf(a[1]));
      }
      if (c) {
        c.put("android:changeBounds:clip", ViewCompat.obtain(localView));
      }
    }
  }
  
  public Animator a(ViewGroup paramViewGroup, Label paramLabel1, Label paramLabel2)
  {
    if (paramLabel1 != null)
    {
      if (paramLabel2 == null) {
        return null;
      }
      java.lang.Object localObject2 = c;
      java.lang.Object localObject1 = c;
      localObject2 = (ViewGroup)((Map)localObject2).get("android:changeBounds:parent");
      java.lang.Object localObject3 = (ViewGroup)((Map)localObject1).get("android:changeBounds:parent");
      if ((localObject2 != null) && (localObject3 != null))
      {
        localObject1 = a;
        int m;
        int k;
        int j;
        int i;
        if (a((View)localObject2, (View)localObject3))
        {
          paramViewGroup = (Rect)c.get("android:changeBounds:bounds");
          localObject2 = (Rect)c.get("android:changeBounds:bounds");
          m = left;
          int n = left;
          int i1 = top;
          int i2 = top;
          int i3 = right;
          int i4 = right;
          int i5 = bottom;
          int i6 = bottom;
          int i7 = i3 - m;
          int i8 = i5 - i1;
          int i9 = i4 - n;
          int i10 = i6 - i2;
          localObject3 = (Rect)c.get("android:changeBounds:clip");
          localObject2 = (Rect)c.get("android:changeBounds:clip");
          k = 0;
          j = 0;
          if ((i7 == 0) || (i8 == 0))
          {
            i = k;
            if (i9 != 0)
            {
              i = k;
              if (i10 == 0) {}
            }
          }
          else
          {
            if ((m != n) || (i1 != i2)) {
              j = 0 + 1;
            }
            if (i3 == i4)
            {
              i = j;
              if (i5 == i6) {}
            }
            else
            {
              i = j + 1;
            }
          }
          if ((localObject3 == null) || (((Rect)localObject3).equals(localObject2)))
          {
            j = i;
            if (localObject3 == null)
            {
              j = i;
              if (localObject2 == null) {}
            }
          }
          else
          {
            j = i + 1;
          }
          if (j > 0)
          {
            if (!c)
            {
              a.set((View)localObject1, m, i1, i3, i5);
              if (j == 2)
              {
                if ((i7 == i9) && (i8 == i10))
                {
                  paramViewGroup = getIcon().draw(m, i1, n, i2);
                  paramViewGroup = ClassVisitor.add(localObject1, INTEGER, paramViewGroup);
                }
                else
                {
                  paramLabel1 = new ClassWriter((View)localObject1);
                  paramViewGroup = getIcon().draw(m, i1, n, i2);
                  paramLabel2 = ClassVisitor.add(paramLabel1, INT_TYPE, paramViewGroup);
                  paramViewGroup = getIcon().draw(i3, i5, i4, i6);
                  localObject2 = ClassVisitor.add(paramLabel1, FLOAT_TYPE, paramViewGroup);
                  paramViewGroup = new AnimatorSet();
                  paramViewGroup.playTogether(new Animator[] { paramLabel2, localObject2 });
                  paramViewGroup.addListener(new k(this, paramLabel1));
                }
              }
              else if ((m == n) && (i1 == i2))
              {
                paramViewGroup = getIcon().draw(i3, i5, i4, i6);
                paramViewGroup = ClassVisitor.add(localObject1, LONG_TYPE, paramViewGroup);
              }
              else
              {
                paramViewGroup = getIcon().draw(m, i1, n, i2);
                paramViewGroup = ClassVisitor.add(localObject1, DOUBLE, paramViewGroup);
              }
            }
            else
            {
              a.set((View)localObject1, m, i1, m + Math.max(i7, i9), i1 + Math.max(i8, i10));
              if ((m == n) && (i1 == i2))
              {
                paramViewGroup = null;
              }
              else
              {
                paramViewGroup = getIcon().draw(m, i1, n, i2);
                paramViewGroup = ClassVisitor.add(localObject1, INTEGER, paramViewGroup);
              }
              paramLabel1 = (Label)localObject3;
              if (localObject3 == null) {
                paramLabel1 = new Rect(0, 0, i7, i8);
              }
              if (localObject2 == null) {
                paramLabel2 = new Rect(0, 0, i9, i10);
              } else {
                paramLabel2 = (Label)localObject2;
              }
              if (!paramLabel1.equals(paramLabel2))
              {
                ViewCompat.a((View)localObject1, paramLabel1);
                paramLabel2 = ObjectAnimator.ofObject(localObject1, "clipBounds", LONG, new java.lang.Object[] { paramLabel1, paramLabel2 });
                paramLabel1 = paramLabel2;
                paramLabel2.addListener(new AnimatorSet.1(this, (View)localObject1, (Rect)localObject2, n, i2, i4, i6));
              }
              else
              {
                paramLabel1 = null;
              }
              paramViewGroup = l.a(paramViewGroup, paramLabel1);
            }
            if (!(((View)localObject1).getParent() instanceof ViewGroup)) {
              return paramViewGroup;
            }
            paramLabel1 = (ViewGroup)((View)localObject1).getParent();
            n.a(paramLabel1, true);
            a(new p(this, paramLabel1));
            return paramViewGroup;
          }
        }
        else
        {
          i = ((Integer)c.get("android:changeBounds:windowX")).intValue();
          j = ((Integer)c.get("android:changeBounds:windowY")).intValue();
          k = ((Integer)c.get("android:changeBounds:windowX")).intValue();
          m = ((Integer)c.get("android:changeBounds:windowY")).intValue();
          if ((i != k) || (j != m)) {
            break label954;
          }
        }
        return null;
        label954:
        paramViewGroup.getLocationInWindow(a);
        paramLabel1 = Bitmap.createBitmap(((View)localObject1).getWidth(), ((View)localObject1).getHeight(), Bitmap.Config.ARGB_8888);
        ((View)localObject1).draw(new Canvas(paramLabel1));
        paramLabel1 = new BitmapDrawable(paramLabel1);
        float f = a.b((View)localObject1);
        a.a((View)localObject1, 0.0F);
        a.a(paramViewGroup).a(paramLabel1);
        paramLabel2 = getIcon();
        localObject2 = a;
        paramLabel2 = paramLabel2.draw(i - localObject2[0], j - localObject2[1], k - localObject2[0], m - localObject2[1]);
        paramLabel2 = ObjectAnimator.ofPropertyValuesHolder(paramLabel1, new PropertyValuesHolder[] { FieldVisitor.ofFloat(d, paramLabel2) });
        paramLabel2.addListener(new Handle(this, paramViewGroup, paramLabel1, (View)localObject1, f));
        return paramLabel2;
      }
    }
    return null;
    return paramViewGroup;
  }
  
  public void a(Label paramLabel)
  {
    draw(paramLabel);
  }
  
  public void c(Label paramLabel)
  {
    draw(paramLabel);
  }
  
  public String[] getValue()
  {
    return b;
  }
}
