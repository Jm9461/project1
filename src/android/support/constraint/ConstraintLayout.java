package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.constraint.asm.asm.ByteVector;
import android.support.constraint.asm.asm.SizeLayoutType;
import android.support.constraint.asm.asm.XLayoutStyle;
import android.support.constraint.asm.asm.c;
import android.support.constraint.asm.asm.d;
import android.support.constraint.asm.asm.f;
import android.support.constraint.asm.asm.h;
import android.support.constraint.i.j.e;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout
  extends ViewGroup
{
  private ArrayList<b> a = new ArrayList(4);
  f b = new f();
  private HashMap<String, Integer> c = new HashMap();
  private int d = 0;
  private int e = -1;
  private int f = 0;
  private int g = Integer.MAX_VALUE;
  private boolean h = true;
  private Label i = null;
  private int j = Integer.MAX_VALUE;
  private int k = 3;
  SparseArray<View> l = new SparseArray();
  int mHeight = -1;
  int mMeasuredHeight = 0;
  int mMeasuredWidth = 0;
  int mWidth = -1;
  private int p = -1;
  private android.support.constraint.asm.m width;
  private final ArrayList<e> x = new ArrayList(100);
  private int y = -1;
  
  public ConstraintLayout(Context paramContext)
  {
    super(paramContext);
    a(null);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramAttributeSet);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramAttributeSet);
  }
  
  private final d a(int paramInt)
  {
    if (paramInt == 0) {
      return b;
    }
    View localView = (View)l.get(paramInt);
    if (localView == this) {
      return b;
    }
    if (localView == null) {
      return null;
    }
    return getLayoutParamsa;
  }
  
  private void a()
  {
    boolean bool = isInEditMode();
    int i7 = getChildCount();
    Object localObject4;
    Object localObject3;
    if (bool)
    {
      m = 0;
      while (m < i7)
      {
        localObject4 = getChildAt(m);
        try
        {
          localObject3 = getResources().getResourceName(((View)localObject4).getId());
          Object localObject1 = localObject3;
          add(0, localObject3, Integer.valueOf(((View)localObject4).getId()));
          n = ((String)localObject3).indexOf('/');
          if (n != -1) {
            localObject1 = ((String)localObject3).substring(n + 1);
          }
          a(((View)localObject4).getId()).c((String)localObject1);
        }
        catch (Resources.NotFoundException localNotFoundException1) {}
        m += 1;
      }
    }
    int m = 0;
    while (m < i7)
    {
      localObject2 = a(getChildAt(m));
      if (localObject2 != null) {
        ((d)localObject2).a();
      }
      m += 1;
    }
    if (e != -1)
    {
      m = 0;
      while (m < i7)
      {
        localObject2 = getChildAt(m);
        if ((((View)localObject2).getId() == e) && ((localObject2 instanceof Toolbar))) {
          i = ((Toolbar)localObject2).getConstraintSet();
        }
        m += 1;
      }
    }
    Object localObject2 = i;
    if (localObject2 != null) {
      ((Label)localObject2).update(this);
    }
    b.write();
    int n = a.size();
    if (n > 0)
    {
      m = 0;
      while (m < n)
      {
        ((Item)a.get(m)).b(this);
        m += 1;
      }
    }
    m = 0;
    while (m < i7)
    {
      localObject2 = getChildAt(m);
      if ((localObject2 instanceof MethodWriter)) {
        ((MethodWriter)localObject2).a(this);
      }
      m += 1;
    }
    int i2 = 0;
    while (i2 < i7)
    {
      localObject3 = getChildAt(i2);
      localObject4 = a((View)localObject3);
      if (localObject4 != null)
      {
        localObject2 = (a)((View)localObject3).getLayoutParams();
        ((a)localObject2).add();
        if (Q) {
          Q = false;
        } else if (bool) {
          try
          {
            String str = getResources().getResourceName(((View)localObject3).getId());
            add(0, str, Integer.valueOf(((View)localObject3).getId()));
            m = str.indexOf("id/");
            str = str.substring(m + 3);
            a(((View)localObject3).getId()).c(str);
          }
          catch (Resources.NotFoundException localNotFoundException2) {}
        }
        ((d)localObject4).g(((View)localObject3).getVisibility());
        if (e) {
          ((d)localObject4).g(8);
        }
        ((d)localObject4).a(localObject3);
        b.a((d)localObject4);
        if ((!width) || (!weight)) {
          x.add(localObject4);
        }
        float f1;
        if (gravity)
        {
          localObject3 = (android.support.constraint.asm.asm.MethodWriter)localObject4;
          m = E;
          n = F;
          f1 = S;
          if (Build.VERSION.SDK_INT < 17)
          {
            m = o;
            n = j;
            f1 = c;
          }
          if (f1 != -1.0F) {
            ((android.support.constraint.asm.asm.MethodWriter)localObject3).e(f1);
          } else if (m != -1) {
            ((android.support.constraint.asm.asm.MethodWriter)localObject3).next(m);
          } else if (n != -1) {
            ((android.support.constraint.asm.asm.MethodWriter)localObject3).read(n);
          }
        }
        for (;;)
        {
          break;
          if ((k != -1) || (m != -1) || (l != -1) || (b != -1) || (t != -1) || (g != -1) || (q != -1) || (s != -1) || (C != -1) || (B != -1) || (i != -1) || (h != -1) || (p != -1) || (target != -1) || (text != -1) || (x != -1) || (width == -1) || (height == -1))
          {
            int i1 = id;
            int i5;
            int i3;
            int i4;
            if (Build.VERSION.SDK_INT < 17)
            {
              n = k;
              m = n;
              i5 = m;
              i6 = l;
              i1 = b;
              i3 = r;
              i4 = v;
              f1 = y;
              if ((m == -1) && (i5 == -1))
              {
                if (t != -1)
                {
                  m = t;
                  n = i5;
                  break label908;
                }
                if (g != -1)
                {
                  n = g;
                  break label908;
                }
              }
              m = n;
              n = i5;
              label908:
              if ((i6 == -1) && (i1 == -1))
              {
                if (q != -1)
                {
                  i6 = q;
                  i5 = n;
                  n = i6;
                  break label1018;
                }
                if (s != -1)
                {
                  i1 = s;
                  i5 = n;
                  n = i6;
                  break label1018;
                }
              }
              i5 = n;
              n = i6;
            }
            else
            {
              m = state;
              i4 = level;
              i5 = next;
              f1 = bottom;
              i3 = right;
              n = left;
            }
            label1018:
            int i6 = x;
            if (i6 != -1)
            {
              localObject3 = a(i6);
              if (localObject3 != null) {
                ((d)localObject4).a((d)localObject3, d, n);
              }
            }
            else
            {
              Object localObject5;
              if (m != -1)
              {
                localObject3 = a(m);
                if (localObject3 != null)
                {
                  localObject5 = c.a;
                  ((d)localObject4).a((c)localObject5, (d)localObject3, (c)localObject5, leftMargin, i3);
                }
              }
              else if (i5 != -1)
              {
                localObject3 = a(i5);
                if (localObject3 != null) {
                  ((d)localObject4).a(c.a, (d)localObject3, c.d, leftMargin, i3);
                }
              }
              localObject3 = localObject2;
              Object localObject6;
              if (n != -1)
              {
                localObject5 = a(n);
                if (localObject5 != null) {
                  ((d)localObject4).a(c.d, (d)localObject5, c.a, rightMargin, i4);
                }
              }
              else if (i1 != -1)
              {
                localObject5 = a(i1);
                if (localObject5 != null)
                {
                  localObject6 = c.d;
                  ((d)localObject4).a((c)localObject6, (d)localObject5, (c)localObject6, rightMargin, i4);
                }
              }
              m = C;
              if (m != -1)
              {
                localObject5 = a(m);
                if (localObject5 != null)
                {
                  localObject6 = c.i;
                  ((d)localObject4).a((c)localObject6, (d)localObject5, (c)localObject6, topMargin, u);
                }
              }
              else
              {
                m = B;
                if (m != -1)
                {
                  localObject5 = a(m);
                  if (localObject5 != null) {
                    ((d)localObject4).a(c.i, (d)localObject5, c.c, topMargin, u);
                  }
                }
              }
              m = i;
              if (m != -1)
              {
                localObject5 = a(m);
                if (localObject5 != null) {
                  ((d)localObject4).a(c.c, (d)localObject5, c.i, bottomMargin, f);
                }
              }
              else
              {
                m = h;
                if (m != -1)
                {
                  localObject5 = a(m);
                  if (localObject5 != null)
                  {
                    localObject6 = c.c;
                    ((d)localObject4).a((c)localObject6, (d)localObject5, (c)localObject6, bottomMargin, f);
                  }
                }
              }
              m = p;
              if (m != -1)
              {
                localObject6 = (View)l.get(m);
                localObject5 = a(p);
                if ((localObject5 != null) && (localObject6 != null) && ((((View)localObject6).getLayoutParams() instanceof a)))
                {
                  localObject6 = (a)((View)localObject6).getLayoutParams();
                  z = true;
                  z = true;
                  ((d)localObject4).a(c.g).a(((d)localObject5).a(c.g), 0, -1, SizeLayoutType.s, 0, true);
                  ((d)localObject4).a(c.i).d();
                  ((d)localObject4).a(c.c).d();
                }
              }
              if ((f1 >= 0.0F) && (f1 != 0.5F)) {
                ((d)localObject4).a(f1);
              }
              f1 = w;
              if ((f1 >= 0.0F) && (f1 != 0.5F)) {
                ((d)localObject4).c(f1);
              }
            }
            if ((bool) && ((target != -1) || (text != -1))) {
              ((d)localObject4).append(target, text);
            }
            if (!weight)
            {
              if (width == -1)
              {
                ((d)localObject4).b(XLayoutStyle.e);
                aag = leftMargin;
                adg = rightMargin;
              }
              else
              {
                ((d)localObject4).b(XLayoutStyle.c);
                ((d)localObject4).d(0);
              }
            }
            else
            {
              ((d)localObject4).b(XLayoutStyle.a);
              ((d)localObject4).d(width);
            }
            if (!width)
            {
              if (height == -1)
              {
                ((d)localObject4).a(XLayoutStyle.e);
                aig = topMargin;
                acg = bottomMargin;
              }
              else
              {
                ((d)localObject4).a(XLayoutStyle.c);
                ((d)localObject4).c(0);
              }
            }
            else
            {
              ((d)localObject4).a(XLayoutStyle.a);
              ((d)localObject4).c(height);
            }
            localObject3 = data;
            if (localObject3 != null) {
              ((d)localObject4).a((String)localObject3);
            }
            ((d)localObject4).b(N);
            ((d)localObject4).add(A);
            ((d)localObject4).putShort(H);
            ((d)localObject4).add(M);
            ((d)localObject4).a(height, index, count, position);
            ((d)localObject4).b(top, status, type, length);
          }
        }
      }
      i2 += 1;
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    Object localObject1 = this;
    int i11 = getPaddingTop() + getPaddingBottom();
    int i12 = getPaddingLeft() + getPaddingRight();
    int i13 = getChildCount();
    int m = 0;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    int i2;
    int i3;
    int n;
    int i1;
    Object localObject5;
    boolean bool;
    while (m < i13)
    {
      localObject2 = getChildAt(m);
      if (((View)localObject2).getVisibility() != 8)
      {
        localObject3 = (a)((View)localObject2).getLayoutParams();
        localObject4 = a;
        if ((!gravity) && (!this$0))
        {
          ((d)localObject4).g(((View)localObject2).getVisibility());
          i2 = width;
          i3 = height;
          if ((i2 != 0) && (i3 != 0))
          {
            n = 0;
            i1 = 0;
            if (i2 == -2) {
              n = 1;
            }
            i4 = ViewGroup.getChildMeasureSpec(paramInt1, i12, i2);
            if (i3 == -2) {
              i1 = 1;
            }
            ((View)localObject2).measure(i4, ViewGroup.getChildMeasureSpec(paramInt2, i11, i3));
            localObject5 = width;
            if (localObject5 != null) {
              width += 1L;
            }
            if (i2 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            ((d)localObject4).e(bool);
            if (i3 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            ((d)localObject4).l(bool);
            i2 = ((View)localObject2).getMeasuredWidth();
            i3 = ((View)localObject2).getMeasuredHeight();
            ((d)localObject4).d(i2);
            ((d)localObject4).c(i3);
            if (n != 0) {
              ((d)localObject4).draw(i2);
            }
            if (i1 != 0) {
              ((d)localObject4).l(i3);
            }
            if (z)
            {
              n = ((View)localObject2).getBaseline();
              if (n != -1) {
                ((d)localObject4).measure(n);
              }
            }
            if ((weight) && (width))
            {
              ((d)localObject4).putByte().a(i2);
              ((d)localObject4).b().a(i3);
            }
          }
          else
          {
            ((d)localObject4).putByte().setText();
            ((d)localObject4).b().setText();
          }
        }
      }
      m += 1;
    }
    b.setTitle();
    int i4 = 0;
    for (;;)
    {
      int i6 = paramInt1;
      if (i4 >= i13) {
        break;
      }
      localObject3 = ((ViewGroup)localObject1).getChildAt(i4);
      if (((View)localObject3).getVisibility() == 8)
      {
        localObject2 = localObject1;
      }
      else
      {
        localObject4 = (a)((View)localObject3).getLayoutParams();
        localObject5 = a;
        localObject2 = localObject1;
        if (!gravity) {
          if (this$0)
          {
            localObject2 = localObject1;
          }
          else
          {
            ((d)localObject5).g(((View)localObject3).getVisibility());
            int i5 = width;
            int i7 = height;
            if ((i5 != 0) && (i7 != 0))
            {
              localObject2 = localObject1;
            }
            else
            {
              localObject2 = ((d)localObject5).a(c.a).a();
              android.support.constraint.asm.asm.m localM1 = ((d)localObject5).a(c.d).a();
              if ((((d)localObject5).a(c.a).getValue() != null) && (((d)localObject5).a(c.d).getValue() != null)) {
                m = 1;
              } else {
                m = 0;
              }
              android.support.constraint.asm.asm.m localM2 = ((d)localObject5).a(c.i).a();
              android.support.constraint.asm.asm.m localM3 = ((d)localObject5).a(c.c).a();
              if ((((d)localObject5).a(c.i).getValue() != null) && (((d)localObject5).a(c.c).getValue() != null)) {
                i3 = 1;
              } else {
                i3 = 0;
              }
              if ((i5 == 0) && (i7 == 0) && (m != 0) && (i3 != 0))
              {
                localObject2 = localObject1;
              }
              else
              {
                i2 = 0;
                int i10 = 0;
                int i8 = 0;
                int i9 = 0;
                if (b.g() != XLayoutStyle.b) {
                  n = 1;
                } else {
                  n = 0;
                }
                if (b.f() != XLayoutStyle.b) {
                  i1 = 1;
                } else {
                  i1 = 0;
                }
                if (n == 0) {
                  ((d)localObject5).putByte().setText();
                }
                if (i1 == 0) {
                  ((d)localObject5).b().setText();
                }
                if (i5 == 0)
                {
                  if ((n != 0) && (((d)localObject5).equals()) && (m != 0) && (((android.support.constraint.asm.asm.Label)localObject2).c()) && (localM1.c()))
                  {
                    i5 = (int)(localM1.e() - ((android.support.constraint.asm.asm.m)localObject2).e());
                    ((d)localObject5).putByte().a(i5);
                    m = ViewGroup.getChildMeasureSpec(i6, i12, i5);
                    i6 = n;
                  }
                  else
                  {
                    i2 = 1;
                    n = 0;
                    m = ViewGroup.getChildMeasureSpec(i6, i12, -2);
                    i6 = n;
                  }
                }
                else if (i5 == -1)
                {
                  m = ViewGroup.getChildMeasureSpec(i6, i12, -1);
                  i6 = n;
                }
                else
                {
                  i2 = i10;
                  if (i5 == -2) {
                    i2 = 1;
                  }
                  m = ViewGroup.getChildMeasureSpec(i6, i12, i5);
                  i6 = n;
                }
                if (i7 == 0)
                {
                  if ((i1 != 0) && (((d)localObject5).d()) && (i3 != 0) && (localM2.c()) && (localM3.c()))
                  {
                    i7 = (int)(localM3.e() - localM2.e());
                    ((d)localObject5).b().a(i7);
                    n = ViewGroup.getChildMeasureSpec(paramInt2, i11, i7);
                    i3 = i8;
                  }
                  else
                  {
                    i3 = 1;
                    i1 = 0;
                    n = ViewGroup.getChildMeasureSpec(paramInt2, i11, -2);
                  }
                }
                else if (i7 == -1)
                {
                  n = ViewGroup.getChildMeasureSpec(paramInt2, i11, -1);
                  i3 = i8;
                }
                else
                {
                  i3 = i9;
                  if (i7 == -2) {
                    i3 = 1;
                  }
                  n = ViewGroup.getChildMeasureSpec(paramInt2, i11, i7);
                }
                ((View)localObject3).measure(m, n);
                localObject1 = this;
                localObject2 = width;
                if (localObject2 != null) {
                  width += 1L;
                }
                if (i5 == -2) {
                  bool = true;
                } else {
                  bool = false;
                }
                ((d)localObject5).e(bool);
                if (i7 == -2) {
                  bool = true;
                } else {
                  bool = false;
                }
                ((d)localObject5).l(bool);
                m = ((View)localObject3).getMeasuredWidth();
                n = ((View)localObject3).getMeasuredHeight();
                ((d)localObject5).d(m);
                ((d)localObject5).c(n);
                if (i2 != 0) {
                  ((d)localObject5).draw(m);
                }
                if (i3 != 0) {
                  ((d)localObject5).l(n);
                }
                if (i6 != 0) {
                  ((d)localObject5).putByte().a(m);
                } else {
                  ((d)localObject5).putByte().a();
                }
                if (i1 != 0) {
                  ((d)localObject5).b().a(n);
                } else {
                  ((d)localObject5).b().a();
                }
                if (z)
                {
                  m = ((View)localObject3).getBaseline();
                  localObject2 = localObject1;
                  if (m != -1)
                  {
                    ((d)localObject5).measure(m);
                    localObject2 = localObject1;
                  }
                }
                else
                {
                  localObject2 = localObject1;
                }
              }
            }
          }
        }
      }
      i4 += 1;
      localObject1 = localObject2;
    }
  }
  
  private void a(AttributeSet paramAttributeSet)
  {
    b.a(this);
    l.put(getId(), this);
    i = null;
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintLayout_Layout);
      int n = paramAttributeSet.getIndexCount();
      int m = 0;
      while (m < n)
      {
        int i1 = paramAttributeSet.getIndex(m);
        if (i1 == IpAddress.ConstraintLayout_Layout_android_minWidth)
        {
          d = paramAttributeSet.getDimensionPixelOffset(i1, d);
        }
        else if (i1 == IpAddress.ConstraintLayout_Layout_android_minHeight)
        {
          f = paramAttributeSet.getDimensionPixelOffset(i1, f);
        }
        else if (i1 == IpAddress.ConstraintLayout_Layout_android_maxWidth)
        {
          g = paramAttributeSet.getDimensionPixelOffset(i1, g);
        }
        else if (i1 == IpAddress.ConstraintLayout_Layout_android_maxHeight)
        {
          j = paramAttributeSet.getDimensionPixelOffset(i1, j);
        }
        else if (i1 == IpAddress.ConstraintLayout_Layout_layout_optimizationLevel)
        {
          k = paramAttributeSet.getInt(i1, k);
        }
        else if (i1 == IpAddress.ConstraintLayout_Layout_constraintSet)
        {
          i1 = paramAttributeSet.getResourceId(i1, 0);
          try
          {
            Label localLabel = new Label();
            i = localLabel;
            localLabel = i;
            localLabel.a(getContext(), i1);
          }
          catch (Resources.NotFoundException localNotFoundException)
          {
            i = null;
          }
          e = i1;
        }
        m += 1;
      }
      paramAttributeSet.recycle();
    }
    b.b(k);
  }
  
  private void b()
  {
    int n = getChildCount();
    int m = 0;
    while (m < n)
    {
      View localView = getChildAt(m);
      if ((localView instanceof MethodWriter)) {
        ((MethodWriter)localView).c(this);
      }
      m += 1;
    }
    n = a.size();
    if (n > 0)
    {
      m = 0;
      while (m < n)
      {
        ((Item)a.get(m)).a(this);
        m += 1;
      }
    }
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    int i4 = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i1 = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    int i2 = getPaddingTop();
    int i3 = getPaddingBottom();
    int i5 = getPaddingLeft();
    int i6 = getPaddingRight();
    XLayoutStyle localXLayoutStyle1 = XLayoutStyle.a;
    XLayoutStyle localXLayoutStyle2 = XLayoutStyle.a;
    int n = 0;
    int m = 0;
    getLayoutParams();
    if (i4 != Integer.MIN_VALUE)
    {
      if (i4 != 0)
      {
        if (i4 != 1073741824) {
          paramInt1 = n;
        } else {
          paramInt1 = Math.min(g, paramInt1) - (i5 + i6);
        }
      }
      else
      {
        localXLayoutStyle1 = XLayoutStyle.b;
        paramInt1 = n;
      }
    }
    else {
      localXLayoutStyle1 = XLayoutStyle.b;
    }
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 0)
      {
        if (i1 != 1073741824) {
          paramInt2 = m;
        } else {
          paramInt2 = Math.min(j, paramInt2) - (i2 + i3);
        }
      }
      else
      {
        localXLayoutStyle2 = XLayoutStyle.b;
        paramInt2 = m;
      }
    }
    else {
      localXLayoutStyle2 = XLayoutStyle.b;
    }
    b.e(0);
    b.setCallback(0);
    b.b(localXLayoutStyle1);
    b.d(paramInt1);
    b.a(localXLayoutStyle2);
    b.c(paramInt2);
    b.e(d - getPaddingLeft() - getPaddingRight());
    b.setCallback(f - getPaddingTop() - getPaddingBottom());
  }
  
  private void createWorld()
  {
    int i2 = getChildCount();
    int i1 = 0;
    int m = 0;
    int n;
    for (;;)
    {
      n = i1;
      if (m >= i2) {
        break;
      }
      if (getChildAt(m).isLayoutRequested())
      {
        n = 1;
        break;
      }
      m += 1;
    }
    if (n != 0)
    {
      x.clear();
      a();
    }
  }
  
  private void measureHorizontal(int paramInt1, int paramInt2)
  {
    int i11 = getPaddingTop() + getPaddingBottom();
    int i12 = getPaddingLeft() + getPaddingRight();
    int i13 = getChildCount();
    int i3 = 0;
    for (;;)
    {
      int i9 = paramInt1;
      Object localObject = this;
      if (i3 >= i13) {
        break;
      }
      View localView = ((ViewGroup)localObject).getChildAt(i3);
      if (localView.getVisibility() != 8)
      {
        a localA = (a)localView.getLayoutParams();
        d localD = a;
        if ((!gravity) && (!this$0))
        {
          localD.g(localView.getVisibility());
          int i5 = width;
          int i6 = height;
          boolean bool = weight;
          int n;
          if ((!bool) && (!width) && ((bool) || (height != 1)) && (width != -1) && ((width) || ((top != 1) && (height != -1)))) {
            n = 0;
          } else {
            n = 1;
          }
          int i1 = 0;
          int i10 = 0;
          int i7 = 0;
          int i4 = 0;
          int i8 = 0;
          int i2 = i5;
          int m = i6;
          if (n != 0)
          {
            if (i5 == 0)
            {
              m = 1;
              i1 = ViewGroup.getChildMeasureSpec(i9, i12, -2);
            }
            else if (i5 == -1)
            {
              m = 0;
              i1 = ViewGroup.getChildMeasureSpec(i9, i12, -1);
            }
            else
            {
              m = i10;
              if (i5 == -2) {
                m = 1;
              }
              i1 = ViewGroup.getChildMeasureSpec(i9, i12, i5);
            }
            if (i6 == 0)
            {
              n = 1;
              i2 = ViewGroup.getChildMeasureSpec(paramInt2, i11, -2);
            }
            else if (i6 == -1)
            {
              i2 = ViewGroup.getChildMeasureSpec(paramInt2, i11, -1);
              n = i7;
            }
            else
            {
              n = i8;
              if (i6 == -2) {
                n = 1;
              }
              i2 = ViewGroup.getChildMeasureSpec(paramInt2, i11, i6);
            }
            localView.measure(i1, i2);
            localObject = width;
            if (localObject != null) {
              width += 1L;
            }
            if (i5 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localD.e(bool);
            if (i6 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localD.l(bool);
            i2 = localView.getMeasuredWidth();
            i4 = localView.getMeasuredHeight();
            i1 = m;
            m = i4;
            i4 = n;
          }
          localD.d(i2);
          localD.c(m);
          if (i1 != 0) {
            localD.draw(i2);
          }
          if (i4 != 0) {
            localD.l(m);
          }
          if (z)
          {
            m = localView.getBaseline();
            if (m != -1) {
              localD.measure(m);
            }
          }
        }
      }
      i3 += 1;
    }
  }
  
  public final d a(View paramView)
  {
    if (paramView == this) {
      return b;
    }
    if (paramView == null) {
      return null;
    }
    return getLayoutParamsa;
  }
  
  public Object a(int paramInt, Object paramObject)
  {
    if ((paramInt == 0) && ((paramObject instanceof String)))
    {
      paramObject = (String)paramObject;
      HashMap localHashMap = c;
      if ((localHashMap != null) && (localHashMap.containsKey(paramObject))) {
        return c.get(paramObject);
      }
    }
    return null;
  }
  
  protected void a(String paramString)
  {
    b.run();
    paramString = width;
    if (paramString != null) {
      type += 1L;
    }
  }
  
  public void add(int paramInt, Object paramObject1, Object paramObject2)
  {
    if ((paramInt == 0) && ((paramObject1 instanceof String)) && ((paramObject2 instanceof Integer)))
    {
      if (c == null) {
        c = new HashMap();
      }
      String str = (String)paramObject1;
      paramInt = str.indexOf("/");
      paramObject1 = str;
      if (paramInt != -1) {
        paramObject1 = str.substring(paramInt + 1);
      }
      paramInt = ((Integer)paramObject2).intValue();
      c.put(paramObject1, Integer.valueOf(paramInt));
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (Build.VERSION.SDK_INT < 14) {
      onViewAdded(paramView);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof a;
  }
  
  public void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if (isInEditMode())
    {
      int n = getChildCount();
      float f1 = getWidth();
      float f2 = getHeight();
      int m = 0;
      while (m < n)
      {
        Object localObject = getChildAt(m);
        if (((View)localObject).getVisibility() != 8)
        {
          localObject = ((View)localObject).getTag();
          if ((localObject != null) && ((localObject instanceof String)))
          {
            localObject = ((String)localObject).split(",");
            if (localObject.length == 4)
            {
              int i2 = Integer.parseInt(localObject[0]);
              int i4 = Integer.parseInt(localObject[1]);
              int i3 = Integer.parseInt(localObject[2]);
              int i1 = Integer.parseInt(localObject[3]);
              i2 = (int)(i2 / 1080.0F * f1);
              i4 = (int)(i4 / 1920.0F * f2);
              i3 = (int)(i3 / 1080.0F * f1);
              i1 = (int)(i1 / 1920.0F * f2);
              localObject = new Paint();
              ((Paint)localObject).setColor(-65536);
              paramCanvas.drawLine(i2, i4, i2 + i3, i4, (Paint)localObject);
              paramCanvas.drawLine(i2 + i3, i4, i2 + i3, i4 + i1, (Paint)localObject);
              paramCanvas.drawLine(i2 + i3, i4 + i1, i2, i4 + i1, (Paint)localObject);
              paramCanvas.drawLine(i2, i4 + i1, i2, i4, (Paint)localObject);
              ((Paint)localObject).setColor(-16711936);
              paramCanvas.drawLine(i2, i4, i2 + i3, i4 + i1, (Paint)localObject);
              paramCanvas.drawLine(i2, i4 + i1, i2 + i3, i4, (Paint)localObject);
            }
            else {}
          }
        }
        m += 1;
      }
    }
  }
  
  protected a generateDefaultLayoutParams()
  {
    return new a(-2, -2);
  }
  
  public a generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new a(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new a(paramLayoutParams);
  }
  
  public int getMaxHeight()
  {
    return j;
  }
  
  public int getMaxWidth()
  {
    return g;
  }
  
  public int getMinHeight()
  {
    return f;
  }
  
  public int getMinWidth()
  {
    return d;
  }
  
  public int getOptimizationLevel()
  {
    return b.h();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = getChildCount();
    paramBoolean = isInEditMode();
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      View localView = getChildAt(paramInt1);
      a localA = (a)localView.getLayoutParams();
      d localD = a;
      if (((localView.getVisibility() != 8) || (gravity) || (this$0) || (paramBoolean)) && (!e))
      {
        paramInt3 = localD.max();
        paramInt4 = localD.getWidth();
        int m = localD.size() + paramInt3;
        int n = localD.getValue() + paramInt4;
        localView.layout(paramInt3, paramInt4, m, n);
        if ((localView instanceof MethodWriter))
        {
          localView = ((MethodWriter)localView).getContent();
          if (localView != null)
          {
            localView.setVisibility(0);
            localView.layout(paramInt3, paramInt4, m, n);
          }
        }
      }
      paramInt1 += 1;
    }
    paramInt2 = a.size();
    if (paramInt2 > 0)
    {
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        ((Item)a.get(paramInt1)).addButton(this);
        paramInt1 += 1;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    System.currentTimeMillis();
    int n = View.MeasureSpec.getMode(paramInt1);
    int i1 = View.MeasureSpec.getSize(paramInt1);
    int i2 = View.MeasureSpec.getMode(paramInt2);
    int i3 = View.MeasureSpec.getSize(paramInt2);
    if (((p == -1) || (y != -1)) || (((n != 1073741824) || (i2 != 1073741824) || (i1 != p) || (i3 == y)) || ((n == mMeasuredHeight) && (i2 == mMeasuredWidth)))) {
      m = 1;
    } else {
      m = 0;
    }
    if (((m == 0) || (i1 != mWidth) || (i3 == mHeight)) || (((m == 0) || (n != Integer.MIN_VALUE) || (i2 != 1073741824) || (i1 < p) || (i3 == y)) || ((m != 0) && (n == 1073741824) && (i2 == Integer.MIN_VALUE) && (i1 == p) && (i3 < y)))) {}
    mMeasuredHeight = n;
    mMeasuredWidth = i2;
    mWidth = i1;
    mHeight = i3;
    int m = getPaddingLeft();
    n = getPaddingTop();
    b.append(m);
    b.setText(n);
    b.clear(g);
    b.put(j);
    Object localObject;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = b;
      boolean bool;
      if (getLayoutDirection() == 1) {
        bool = true;
      } else {
        bool = false;
      }
      ((f)localObject).c(bool);
    }
    b(paramInt1, paramInt2);
    int i15 = b.size();
    int i16 = b.getValue();
    if (h)
    {
      h = false;
      createWorld();
    }
    int i4;
    if ((k & 0x8) == 8) {
      i4 = 1;
    } else {
      i4 = 0;
    }
    if (i4 != 0)
    {
      b.i();
      b.b(i15, i16);
      a(paramInt1, paramInt2);
    }
    else
    {
      measureHorizontal(paramInt1, paramInt2);
    }
    b();
    if (getChildCount() > 0) {
      a("First pass");
    }
    int i12 = x.size();
    int i13 = n + getPaddingBottom();
    int i14 = m + getPaddingRight();
    if (i12 > 0)
    {
      i2 = 0;
      int i5;
      if (b.g() == XLayoutStyle.b) {
        i5 = 1;
      } else {
        i5 = 0;
      }
      int i6;
      if (b.f() == XLayoutStyle.b) {
        i6 = 1;
      } else {
        i6 = 0;
      }
      n = Math.max(b.size(), d);
      m = Math.max(b.getValue(), f);
      int i7 = 0;
      i1 = 0;
      View localView;
      while (i7 < i12)
      {
        localObject = (d)x.get(i7);
        localView = (View)((d)localObject).leftValue();
        int i8;
        int i9;
        int i10;
        int i11;
        if (localView == null)
        {
          i8 = i1;
          i9 = m;
          i10 = n;
          i11 = i2;
        }
        else
        {
          a localA = (a)localView.getLayoutParams();
          i8 = i1;
          i9 = m;
          i10 = n;
          i11 = i2;
          if (!this$0) {
            if (gravity)
            {
              i8 = i1;
              i9 = m;
              i10 = n;
              i11 = i2;
            }
            else if (localView.getVisibility() == 8)
            {
              i8 = i1;
              i9 = m;
              i10 = n;
              i11 = i2;
            }
            else if ((i4 != 0) && (((d)localObject).putByte().c()) && (((d)localObject).b().c()))
            {
              i8 = i1;
              i9 = m;
              i10 = n;
              i11 = i2;
            }
            else
            {
              if ((width == -2) && (weight)) {
                i3 = ViewGroup.getChildMeasureSpec(paramInt1, i14, width);
              } else {
                i3 = View.MeasureSpec.makeMeasureSpec(((d)localObject).size(), 1073741824);
              }
              if ((height == -2) && (width)) {
                i8 = ViewGroup.getChildMeasureSpec(paramInt2, i13, height);
              } else {
                i8 = View.MeasureSpec.makeMeasureSpec(((d)localObject).getValue(), 1073741824);
              }
              localView.measure(i3, i8);
              android.support.constraint.asm.m localM = width;
              if (localM != null) {
                y += 1L;
              }
              i9 = localView.getMeasuredWidth();
              i8 = localView.getMeasuredHeight();
              i3 = n;
              if (i9 != ((d)localObject).size())
              {
                ((d)localObject).d(i9);
                if (i4 != 0) {
                  ((d)localObject).putByte().a(i9);
                }
                i3 = n;
                if (i5 != 0)
                {
                  i3 = n;
                  if (((d)localObject).onClick() > n) {
                    i3 = Math.max(n, ((d)localObject).onClick() + ((d)localObject).a(c.d).b());
                  }
                }
                i2 = 1;
              }
              n = m;
              if (i8 != ((d)localObject).getValue())
              {
                ((d)localObject).c(i8);
                if (i4 != 0) {
                  ((d)localObject).b().a(i8);
                }
                n = m;
                if (i6 != 0)
                {
                  n = m;
                  if (((d)localObject).getItemCount() > m) {
                    n = Math.max(m, ((d)localObject).getItemCount() + ((d)localObject).a(c.c).b());
                  }
                }
                i2 = 1;
              }
              m = i2;
              if (z)
              {
                i8 = localView.getBaseline();
                m = i2;
                if (i8 != -1)
                {
                  m = i2;
                  if (i8 != ((d)localObject).getIcon())
                  {
                    ((d)localObject).measure(i8);
                    m = 1;
                  }
                }
              }
              i8 = i1;
              i9 = n;
              i10 = i3;
              i11 = m;
              if (Build.VERSION.SDK_INT >= 11)
              {
                i8 = View.combineMeasuredStates(i1, localView.getMeasuredState());
                i9 = n;
                i10 = i3;
                i11 = m;
              }
            }
          }
        }
        i7 += 1;
        i1 = i8;
        m = i9;
        n = i10;
        i2 = i11;
      }
      i3 = i12;
      if (i2 != 0)
      {
        b.d(i15);
        b.c(i16);
        if (i4 != 0) {
          b.setTitle();
        }
        a("2nd pass");
        i2 = 0;
        if (b.size() < n)
        {
          b.d(n);
          i2 = 1;
        }
        if (b.getValue() < m)
        {
          b.c(m);
          i2 = 1;
        }
        if (i2 != 0) {
          a("3rd pass");
        }
      }
      n = 0;
      m = i3;
      while (n < m)
      {
        localObject = (d)x.get(n);
        localView = (View)((d)localObject).leftValue();
        if (localView != null)
        {
          if ((localView.getMeasuredWidth() == ((d)localObject).size()) && (localView.getMeasuredHeight() == ((d)localObject).getValue())) {
            break label1445;
          }
          if (((d)localObject).l() != 8)
          {
            localView.measure(View.MeasureSpec.makeMeasureSpec(((d)localObject).size(), 1073741824), View.MeasureSpec.makeMeasureSpec(((d)localObject).getValue(), 1073741824));
            localObject = width;
            if (localObject != null) {
              y += 1L;
            }
          }
        }
        label1445:
        n += 1;
      }
    }
    else
    {
      i1 = 0;
    }
    m = b.size() + i14;
    n = b.getValue() + i13;
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramInt1 = View.resolveSizeAndState(m, paramInt1, i1);
      m = View.resolveSizeAndState(n, paramInt2, i1 << 16);
      n = Math.min(g, paramInt1 & 0xFFFFFF);
      paramInt2 = n;
      m = Math.min(j, m & 0xFFFFFF);
      paramInt1 = m;
      if (b.r()) {
        paramInt2 = n | 0x1000000;
      }
      if (b.o()) {
        paramInt1 = m | 0x1000000;
      }
      setMeasuredDimension(paramInt2, paramInt1);
      p = paramInt2;
      y = paramInt1;
      return;
    }
    setMeasuredDimension(m, n);
    p = m;
    y = n;
  }
  
  public void onViewAdded(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewAdded(paramView);
    }
    Object localObject = a(paramView);
    if (((paramView instanceof ProgressBar)) && (!(localObject instanceof android.support.constraint.asm.asm.MethodWriter)))
    {
      localObject = (a)paramView.getLayoutParams();
      a = new android.support.constraint.asm.asm.MethodWriter();
      gravity = true;
      ((android.support.constraint.asm.asm.MethodWriter)a).b(color);
    }
    if ((paramView instanceof Item))
    {
      localObject = (Item)paramView;
      ((Item)localObject).a();
      getLayoutParamsthis$0 = true;
      if (!a.contains(localObject)) {
        a.add(localObject);
      }
    }
    l.put(paramView.getId(), paramView);
    h = true;
  }
  
  public void onViewRemoved(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewRemoved(paramView);
    }
    l.remove(paramView.getId());
    d localD = a(paramView);
    b.b(localD);
    a.remove(paramView);
    x.remove(localD);
    h = true;
  }
  
  public void removeView(View paramView)
  {
    super.removeView(paramView);
    if (Build.VERSION.SDK_INT < 14) {
      onViewRemoved(paramView);
    }
  }
  
  public void requestLayout()
  {
    super.requestLayout();
    h = true;
    p = -1;
    y = -1;
    mWidth = -1;
    mHeight = -1;
    mMeasuredHeight = 0;
    mMeasuredWidth = 0;
  }
  
  public void setConstraintSet(Label paramLabel)
  {
    i = paramLabel;
  }
  
  public void setId(int paramInt)
  {
    l.remove(getId());
    super.setId(paramInt);
    l.put(getId(), this);
  }
  
  public void setMaxHeight(int paramInt)
  {
    if (paramInt == j) {
      return;
    }
    j = paramInt;
    requestLayout();
  }
  
  public void setMaxWidth(int paramInt)
  {
    if (paramInt == g) {
      return;
    }
    g = paramInt;
    requestLayout();
  }
  
  public void setMinHeight(int paramInt)
  {
    if (paramInt == f) {
      return;
    }
    f = paramInt;
    requestLayout();
  }
  
  public void setMinWidth(int paramInt)
  {
    if (paramInt == d) {
      return;
    }
    d = paramInt;
    requestLayout();
  }
  
  public void setOptimizationLevel(int paramInt)
  {
    b.b(paramInt);
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  public static class a
    extends ViewGroup.MarginLayoutParams
  {
    public float A = -1.0F;
    public int B = -1;
    public int C = -1;
    int E;
    int F;
    public int H = 0;
    public int M = 0;
    public float N = -1.0F;
    public boolean Q = false;
    float S;
    d a = new d();
    public int b = -1;
    float bottom = 0.5F;
    public float c = -1.0F;
    public int color = -1;
    public int count = 0;
    public float d = 0.0F;
    public String data = null;
    boolean e = false;
    public int f = -1;
    public boolean font = false;
    public int g = -1;
    boolean gravity = false;
    public int h = -1;
    public int height = 0;
    public int i = -1;
    int id = -1;
    public int index = 0;
    public int j = -1;
    public int k = -1;
    public int l = -1;
    int left = -1;
    public float length = 1.0F;
    int level = -1;
    public int m = -1;
    public int n = 0;
    public int name = -1;
    int next = -1;
    public int o = -1;
    int offset = 1;
    public int p = -1;
    public float position = 1.0F;
    public int q = -1;
    public int r = -1;
    int right = -1;
    public int s = -1;
    public int size = -1;
    int state = -1;
    public int status = 0;
    public int t = -1;
    public int target = -1;
    public int text = -1;
    boolean this$0 = false;
    public int top = 0;
    public int type = 0;
    public int u = -1;
    public int v = -1;
    public boolean value = false;
    public float w = 0.5F;
    boolean weight = true;
    boolean width = true;
    public int x = -1;
    public float y = 0.5F;
    boolean z = false;
    
    public a(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public a(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintLayout_Layout);
      int i3 = paramContext.getIndexCount();
      int i1 = 0;
      while (i1 < i3)
      {
        int i2 = paramContext.getIndex(i1);
        int i4;
        float f1;
        switch (a.subdomains.get(i2))
        {
        default: 
          break;
        case 43: 
          break;
        case 50: 
          text = paramContext.getDimensionPixelOffset(i2, text);
          break;
        case 49: 
          target = paramContext.getDimensionPixelOffset(i2, target);
          break;
        case 48: 
          M = paramContext.getInt(i2, 0);
          break;
        case 47: 
          H = paramContext.getInt(i2, 0);
          break;
        case 46: 
          A = paramContext.getFloat(i2, A);
          break;
        case 45: 
          N = paramContext.getFloat(i2, N);
          break;
        case 44: 
          data = paramContext.getString(i2);
          offset = -1;
          paramAttributeSet = data;
          if (paramAttributeSet != null)
          {
            i4 = paramAttributeSet.length();
            i2 = data.indexOf(',');
            if ((i2 > 0) && (i2 < i4 - 1))
            {
              paramAttributeSet = data.substring(0, i2);
              if (paramAttributeSet.equalsIgnoreCase("W")) {
                offset = 0;
              } else if (paramAttributeSet.equalsIgnoreCase("H")) {
                offset = 1;
              }
              i2 += 1;
            }
            else
            {
              i2 = 0;
            }
            int i5 = data.indexOf(':');
            if ((i5 >= 0) && (i5 < i4 - 1))
            {
              paramAttributeSet = data.substring(i2, i5);
              String str = data.substring(i5 + 1);
              if ((paramAttributeSet.length() > 0) && (str.length() > 0)) {
                try
                {
                  f1 = Float.parseFloat(paramAttributeSet);
                  float f2 = Float.parseFloat(str);
                  if ((f1 > 0.0F) && (f2 > 0.0F)) {
                    if (offset == 1)
                    {
                      f1 = f2 / f1;
                      Math.abs(f1);
                    }
                    else
                    {
                      f1 /= f2;
                      Math.abs(f1);
                    }
                  }
                }
                catch (NumberFormatException paramAttributeSet) {}
              }
            }
            else
            {
              paramAttributeSet = data.substring(i2);
              if (paramAttributeSet.length() > 0) {
                try
                {
                  Float.parseFloat(paramAttributeSet);
                }
                catch (NumberFormatException paramAttributeSet) {}
              }
            }
          }
          break;
        case 42: 
          break;
        case 41: 
          break;
        case 40: 
          break;
        case 39: 
          break;
        case 38: 
          length = Math.max(0.0F, paramContext.getFloat(i2, length));
          break;
        case 37: 
          i4 = type;
          try
          {
            i4 = paramContext.getDimensionPixelSize(i2, i4);
            type = i4;
          }
          catch (Exception paramAttributeSet)
          {
            if (paramContext.getInt(i2, type) == -2) {
              type = -2;
            }
          }
        case 36: 
          i4 = status;
          try
          {
            i4 = paramContext.getDimensionPixelSize(i2, i4);
            status = i4;
          }
          catch (Exception paramAttributeSet)
          {
            if (paramContext.getInt(i2, status) == -2) {
              status = -2;
            }
          }
        case 35: 
          position = Math.max(0.0F, paramContext.getFloat(i2, position));
          break;
        case 34: 
          i4 = count;
          try
          {
            i4 = paramContext.getDimensionPixelSize(i2, i4);
            count = i4;
          }
          catch (Exception paramAttributeSet)
          {
            if (paramContext.getInt(i2, count) == -2) {
              count = -2;
            }
          }
        case 33: 
          i4 = index;
          try
          {
            i4 = paramContext.getDimensionPixelSize(i2, i4);
            index = i4;
          }
          catch (Exception paramAttributeSet)
          {
            if (paramContext.getInt(i2, index) == -2) {
              index = -2;
            }
          }
        case 32: 
          top = paramContext.getInt(i2, 0);
          if (top == 1) {
            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
          }
          break;
        case 31: 
          height = paramContext.getInt(i2, 0);
          if (height == 1) {
            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
          }
          break;
        case 30: 
          w = paramContext.getFloat(i2, w);
          break;
        case 29: 
          y = paramContext.getFloat(i2, y);
          break;
        case 28: 
          value = paramContext.getBoolean(i2, value);
          break;
        case 27: 
          font = paramContext.getBoolean(i2, font);
          break;
        case 26: 
          size = paramContext.getDimensionPixelSize(i2, size);
          break;
        case 25: 
          name = paramContext.getDimensionPixelSize(i2, name);
          break;
        case 24: 
          f = paramContext.getDimensionPixelSize(i2, f);
          break;
        case 23: 
          v = paramContext.getDimensionPixelSize(i2, v);
          break;
        case 22: 
          u = paramContext.getDimensionPixelSize(i2, u);
          break;
        case 21: 
          r = paramContext.getDimensionPixelSize(i2, r);
          break;
        case 20: 
          s = paramContext.getResourceId(i2, s);
          if (s == -1) {
            s = paramContext.getInt(i2, -1);
          }
          break;
        case 19: 
          q = paramContext.getResourceId(i2, q);
          if (q == -1) {
            q = paramContext.getInt(i2, -1);
          }
          break;
        case 18: 
          t = paramContext.getResourceId(i2, t);
          if (t == -1) {
            t = paramContext.getInt(i2, -1);
          }
          break;
        case 17: 
          g = paramContext.getResourceId(i2, g);
          if (g == -1) {
            g = paramContext.getInt(i2, -1);
          }
          break;
        case 16: 
          p = paramContext.getResourceId(i2, p);
          if (p == -1) {
            p = paramContext.getInt(i2, -1);
          }
          break;
        case 15: 
          h = paramContext.getResourceId(i2, h);
          if (h == -1) {
            h = paramContext.getInt(i2, -1);
          }
          break;
        case 14: 
          i = paramContext.getResourceId(i2, i);
          if (i == -1) {
            i = paramContext.getInt(i2, -1);
          }
          break;
        case 13: 
          B = paramContext.getResourceId(i2, B);
          if (B == -1) {
            B = paramContext.getInt(i2, -1);
          }
          break;
        case 12: 
          C = paramContext.getResourceId(i2, C);
          if (C == -1) {
            C = paramContext.getInt(i2, -1);
          }
          break;
        case 11: 
          b = paramContext.getResourceId(i2, b);
          if (b == -1) {
            b = paramContext.getInt(i2, -1);
          }
          break;
        case 10: 
          l = paramContext.getResourceId(i2, l);
          if (l == -1) {
            l = paramContext.getInt(i2, -1);
          }
          break;
        case 9: 
          m = paramContext.getResourceId(i2, m);
          if (m == -1) {
            m = paramContext.getInt(i2, -1);
          }
          break;
        case 8: 
          k = paramContext.getResourceId(i2, k);
          if (k == -1) {
            k = paramContext.getInt(i2, -1);
          }
          break;
        case 7: 
          c = paramContext.getFloat(i2, c);
          break;
        case 6: 
          j = paramContext.getDimensionPixelOffset(i2, j);
          break;
        case 5: 
          o = paramContext.getDimensionPixelOffset(i2, o);
          break;
        case 4: 
          d = (paramContext.getFloat(i2, d) % 360.0F);
          f1 = d;
          if (f1 < 0.0F) {
            d = ((360.0F - f1) % 360.0F);
          }
          break;
        case 3: 
          n = paramContext.getDimensionPixelSize(i2, n);
          break;
        case 2: 
          x = paramContext.getResourceId(i2, x);
          if (x == -1) {
            x = paramContext.getInt(i2, -1);
          }
          break;
        case 1: 
          color = paramContext.getInt(i2, color);
          break;
        }
        i1 += 1;
      }
      paramContext.recycle();
      add();
    }
    
    public a(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public void add()
    {
      gravity = false;
      weight = true;
      width = true;
      if ((width == -2) && (font))
      {
        weight = false;
        height = 1;
      }
      if ((height == -2) && (value))
      {
        width = false;
        top = 1;
      }
      if ((width == 0) || (width == -1))
      {
        weight = false;
        if ((width == 0) && (height == 1))
        {
          width = -2;
          font = true;
        }
      }
      if ((height == 0) || (height == -1))
      {
        width = false;
        if ((height == 0) && (top == 1))
        {
          height = -2;
          value = true;
        }
      }
      if ((c != -1.0F) || (o != -1) || (j != -1))
      {
        gravity = true;
        weight = true;
        width = true;
        if (!(a instanceof android.support.constraint.asm.asm.MethodWriter)) {
          a = new android.support.constraint.asm.asm.MethodWriter();
        }
        ((android.support.constraint.asm.asm.MethodWriter)a).b(color);
      }
    }
    
    public void resolveLayoutDirection(int paramInt)
    {
      int i1 = leftMargin;
      int i2 = rightMargin;
      super.resolveLayoutDirection(paramInt);
      left = -1;
      id = -1;
      state = -1;
      next = -1;
      right = -1;
      level = -1;
      right = r;
      level = v;
      bottom = y;
      E = o;
      F = j;
      S = c;
      if (1 == getLayoutDirection()) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0)
      {
        paramInt = 0;
        int i3 = g;
        if (i3 != -1)
        {
          left = i3;
          paramInt = 1;
        }
        else
        {
          i3 = t;
          if (i3 != -1)
          {
            id = i3;
            paramInt = 1;
          }
        }
        i3 = q;
        if (i3 != -1)
        {
          next = i3;
          paramInt = 1;
        }
        i3 = s;
        if (i3 != -1)
        {
          state = i3;
          paramInt = 1;
        }
        i3 = name;
        if (i3 != -1) {
          level = i3;
        }
        i3 = size;
        if (i3 != -1) {
          right = i3;
        }
        if (paramInt != 0) {
          bottom = (1.0F - y);
        }
        if ((gravity) && (color == 1))
        {
          float f1 = c;
          if (f1 != -1.0F)
          {
            S = (1.0F - f1);
            E = -1;
            F = -1;
          }
          else
          {
            paramInt = o;
            if (paramInt != -1)
            {
              F = paramInt;
              E = -1;
              S = -1.0F;
            }
            else
            {
              paramInt = j;
              if (paramInt != -1)
              {
                E = paramInt;
                F = -1;
                S = -1.0F;
              }
            }
          }
        }
      }
      else
      {
        paramInt = g;
        if (paramInt != -1) {
          next = paramInt;
        }
        paramInt = t;
        if (paramInt != -1) {
          state = paramInt;
        }
        paramInt = q;
        if (paramInt != -1) {
          left = paramInt;
        }
        paramInt = s;
        if (paramInt != -1) {
          id = paramInt;
        }
        paramInt = name;
        if (paramInt != -1) {
          right = paramInt;
        }
        paramInt = size;
        if (paramInt != -1) {
          level = paramInt;
        }
      }
      if ((q == -1) && (s == -1) && (t == -1) && (g == -1))
      {
        paramInt = l;
        if (paramInt != -1)
        {
          left = paramInt;
          if ((rightMargin <= 0) && (i2 > 0)) {
            rightMargin = i2;
          }
        }
        else
        {
          paramInt = b;
          if (paramInt != -1)
          {
            id = paramInt;
            if ((rightMargin <= 0) && (i2 > 0)) {
              rightMargin = i2;
            }
          }
        }
        paramInt = k;
        if (paramInt != -1)
        {
          state = paramInt;
          if ((leftMargin <= 0) && (i1 > 0)) {
            leftMargin = i1;
          }
        }
        else
        {
          paramInt = m;
          if (paramInt != -1)
          {
            next = paramInt;
            if ((leftMargin <= 0) && (i1 > 0)) {
              leftMargin = i1;
            }
          }
        }
      }
    }
    
    private static class a
    {
      public static final SparseIntArray subdomains = new SparseIntArray();
      
      static
      {
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintCircle, 2);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
        subdomains.append(IpAddress.ConstraintLayout_Layout_android_orientation, 1);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginTop, 22);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginRight, 23);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginStart, 25);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constrainedWidth, 27);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constrainedHeight, 28);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
        subdomains.append(IpAddress.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
      }
    }
  }
}
