package org.org.asm;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class f
  extends h
{
  boolean c = false;
  private boolean e = true;
  int l;
  private int r = 0;
  private ArrayList<a.b.f.m> w = new ArrayList();
  
  public f() {}
  
  private void c()
  {
    ListMenuItemView localListMenuItemView = new ListMenuItemView(this);
    Iterator localIterator = w.iterator();
    while (localIterator.hasNext()) {
      ((h)localIterator.next()).a(localListMenuItemView);
    }
    l = w.size();
  }
  
  public f a(long paramLong)
  {
    super.a(paramLong);
    if (h >= 0L)
    {
      int j = w.size();
      int i = 0;
      while (i < j)
      {
        ((h)w.get(i)).a(paramLong);
        i += 1;
      }
    }
    return this;
  }
  
  public f a(TimeInterpolator paramTimeInterpolator)
  {
    r |= 0x1;
    ArrayList localArrayList = w;
    if (localArrayList != null)
    {
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        ((h)w.get(i)).a(paramTimeInterpolator);
        i += 1;
      }
    }
    super.a(paramTimeInterpolator);
    return (f)this;
  }
  
  public f a(h paramH)
  {
    w.add(paramH);
    o = this;
    long l1 = h;
    if (l1 >= 0L) {
      paramH.a(l1);
    }
    if ((r & 0x1) != 0) {
      paramH.a(e());
    }
    if ((r & 0x2) != 0) {
      paramH.a(g());
    }
    if ((r & 0x4) != 0) {
      paramH.a(getIcon());
    }
    if ((r & 0x8) != 0) {
      paramH.a(getSupportActionProvider());
    }
    return this;
  }
  
  public f a(m paramM)
  {
    super.a(paramM);
    return (f)this;
  }
  
  public h a(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < w.size())) {
      return (h)w.get(paramInt);
    }
    return null;
  }
  
  public void a(View paramView)
  {
    super.a(paramView);
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      ((h)w.get(i)).a(paramView);
      i += 1;
    }
  }
  
  protected void a(ViewGroup paramViewGroup, x paramX1, x paramX2, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    long l1 = f();
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      h localH = (h)w.get(i);
      if ((l1 > 0L) && ((e) || (i == 0)))
      {
        long l2 = localH.f();
        if (l2 > 0L) {
          localH.f(l1 + l2);
        } else {
          localH.f(l1);
        }
      }
      localH.a(paramViewGroup, paramX1, paramX2, paramArrayList1, paramArrayList2);
      i += 1;
    }
  }
  
  public void a(ActionProvider paramActionProvider)
  {
    super.a(paramActionProvider);
    r |= 0x8;
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      ((h)w.get(i)).a(paramActionProvider);
      i += 1;
    }
  }
  
  public void a(AnnotationVisitor paramAnnotationVisitor)
  {
    super.a(paramAnnotationVisitor);
    r |= 0x4;
    int i = 0;
    while (i < w.size())
    {
      ((h)w.get(i)).a(paramAnnotationVisitor);
      i += 1;
    }
  }
  
  public void a(ByteVector paramByteVector)
  {
    super.a(paramByteVector);
    r |= 0x2;
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      ((h)w.get(i)).a(paramByteVector);
      i += 1;
    }
  }
  
  public void a(Label paramLabel)
  {
    if (d(a))
    {
      Iterator localIterator = w.iterator();
      while (localIterator.hasNext())
      {
        h localH = (h)localIterator.next();
        if (localH.d(a))
        {
          localH.a(paramLabel);
          b.add(localH);
        }
      }
    }
  }
  
  public f b(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt == 1)
      {
        e = false;
        return this;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid parameter for TransitionSet ordering: ");
      localStringBuilder.append(paramInt);
      throw new AndroidRuntimeException(localStringBuilder.toString());
    }
    e = true;
    return this;
  }
  
  protected void b()
  {
    if (w.isEmpty())
    {
      i();
      a();
      return;
    }
    c();
    if (!e)
    {
      int i = 1;
      while (i < w.size())
      {
        ((h)w.get(i - 1)).a(new FieldWriter(this, (h)w.get(i)));
        i += 1;
      }
      localObject = (h)w.get(0);
      if (localObject != null) {
        ((h)localObject).b();
      }
      return;
    }
    Object localObject = w.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((h)((Iterator)localObject).next()).b();
    }
  }
  
  public void b(View paramView)
  {
    super.b(paramView);
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      ((h)w.get(i)).b(paramView);
      i += 1;
    }
  }
  
  void b(Label paramLabel)
  {
    super.b(paramLabel);
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      ((h)w.get(i)).b(paramLabel);
      i += 1;
    }
  }
  
  public f c(View paramView)
  {
    int i = 0;
    while (i < w.size())
    {
      ((h)w.get(i)).c(paramView);
      i += 1;
    }
    super.c(paramView);
    return (f)this;
  }
  
  public f c(m paramM)
  {
    super.c(paramM);
    return (f)this;
  }
  
  public void c(Label paramLabel)
  {
    if (d(a))
    {
      Iterator localIterator = w.iterator();
      while (localIterator.hasNext())
      {
        h localH = (h)localIterator.next();
        if (localH.d(a))
        {
          localH.c(paramLabel);
          b.add(localH);
        }
      }
    }
  }
  
  public h clone()
  {
    f localF = (f)super.clone();
    w = new ArrayList();
    int j = w.size();
    int i = 0;
    while (i < j)
    {
      localF.a(((h)w.get(i)).clone());
      i += 1;
    }
    return localF;
  }
  
  public f e(View paramView)
  {
    int i = 0;
    while (i < w.size())
    {
      ((h)w.get(i)).e(paramView);
      i += 1;
    }
    super.e(paramView);
    return (f)this;
  }
  
  public f f(long paramLong)
  {
    super.f(paramLong);
    return (f)this;
  }
  
  public int size()
  {
    return w.size();
  }
  
  String toString(String paramString)
  {
    Object localObject = super.toString(paramString);
    int i = 0;
    while (i < w.size())
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append((String)localObject);
      localStringBuilder1.append("\n");
      localObject = (h)w.get(i);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString);
      localStringBuilder2.append("  ");
      localStringBuilder1.append(((h)localObject).toString(localStringBuilder2.toString()));
      localObject = localStringBuilder1.toString();
      i += 1;
    }
    return localObject;
  }
}
