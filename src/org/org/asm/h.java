package org.org.asm;

import a.b.f.m.d;
import a.b.f.m.f;
import a.b.f.s;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.org.jaxen.util.LongSparseArray;

public abstract class h
  implements Cloneable
{
  private static final int[] CIRCLE = { 2, 1, 3, 4 };
  private static final AnnotationVisitor EDGE = new AnnotationNode();
  private static ThreadLocal<a.b.g.g.a<Animator, m.d>> responseCache = new ThreadLocal();
  private AnnotationVisitor A = EDGE;
  private ActionProvider B;
  private String _groupId = getClass().getName();
  private ArrayList<Class> a = null;
  private a.b.g.g.a<String, String> b;
  private ArrayList<s> c;
  private ArrayList<Integer> d = null;
  private x e = new x();
  private long f = -1L;
  private TimeInterpolator g = null;
  long h = -1L;
  private ArrayList<View> header = null;
  private x i = new x();
  private ArrayList<s> j;
  private ArrayList<Integer> k = null;
  private ArrayList<String> l = null;
  private ArrayList<Class> list = null;
  ArrayList<View> m = new ArrayList();
  ArrayList<Integer> n = new ArrayList();
  f o = null;
  private ArrayList<String> p = null;
  private ArrayList<m.f> q = null;
  private ArrayList<Class> r = null;
  private ArrayList<Animator> s = new ArrayList();
  private ArrayList<View> t = null;
  ArrayList<Animator> this$0 = new ArrayList();
  private int[] u = CIRCLE;
  boolean v = false;
  private boolean w = false;
  private int x = 0;
  ByteVector y;
  private boolean z = false;
  
  public h() {}
  
  private void a(View paramView, boolean paramBoolean)
  {
    if (paramView == null) {
      return;
    }
    int i2 = paramView.getId();
    Object localObject = k;
    if ((localObject != null) && (((ArrayList)localObject).contains(Integer.valueOf(i2)))) {
      return;
    }
    localObject = t;
    if ((localObject != null) && (((ArrayList)localObject).contains(paramView))) {
      return;
    }
    localObject = r;
    int i1;
    if (localObject != null)
    {
      int i3 = ((ArrayList)localObject).size();
      i1 = 0;
      while (i1 < i3)
      {
        if (((Class)r.get(i1)).isInstance(paramView)) {
          return;
        }
        i1 += 1;
      }
    }
    if ((paramView.getParent() instanceof ViewGroup))
    {
      localObject = new Label();
      a = paramView;
      if (paramBoolean) {
        a((Label)localObject);
      } else {
        c((Label)localObject);
      }
      b.add(this);
      b((Label)localObject);
      if (paramBoolean) {
        a(e, paramView, (Label)localObject);
      } else {
        a(i, paramView, (Label)localObject);
      }
    }
    if ((paramView instanceof ViewGroup))
    {
      localObject = d;
      if ((localObject != null) && (((ArrayList)localObject).contains(Integer.valueOf(i2)))) {
        return;
      }
      localObject = header;
      if ((localObject != null) && (((ArrayList)localObject).contains(paramView))) {
        return;
      }
      localObject = list;
      if (localObject != null)
      {
        i2 = ((ArrayList)localObject).size();
        i1 = 0;
        while (i1 < i2)
        {
          if (((Class)list.get(i1)).isInstance(paramView)) {
            return;
          }
          i1 += 1;
        }
      }
      paramView = (ViewGroup)paramView;
      i1 = 0;
      while (i1 < paramView.getChildCount())
      {
        a(paramView.getChildAt(i1), paramBoolean);
        i1 += 1;
      }
    }
  }
  
  private static void a(x paramX, View paramView, Label paramLabel)
  {
    b.put(paramView, paramLabel);
    int i1 = paramView.getId();
    if (i1 >= 0) {
      if (d.indexOfKey(i1) >= 0) {
        d.put(i1, null);
      } else {
        d.put(i1, paramView);
      }
    }
    paramLabel = ViewCompat.a(paramView);
    if (paramLabel != null) {
      if (a.containsKey(paramLabel)) {
        a.put(paramLabel, null);
      } else {
        a.put(paramLabel, paramView);
      }
    }
    if ((paramView.getParent() instanceof ListView))
    {
      paramLabel = (ListView)paramView.getParent();
      if (paramLabel.getAdapter().hasStableIds())
      {
        long l1 = paramLabel.getItemIdAtPosition(paramLabel.getPositionForView(paramView));
        if (c.put(l1) >= 0)
        {
          paramView = (View)c.get(l1);
          if (paramView != null)
          {
            ViewCompat.b(paramView, false);
            c.put(l1, null);
          }
          return;
        }
        ViewCompat.b(paramView, true);
        c.put(l1, paramView);
      }
    }
  }
  
  private void a(x paramX1, x paramX2)
  {
    org.org.jaxen.util.Label localLabel1 = new org.org.jaxen.util.Label(b);
    org.org.jaxen.util.Label localLabel2 = new org.org.jaxen.util.Label(b);
    int i1 = 0;
    for (;;)
    {
      int[] arrayOfInt = u;
      if (i1 >= arrayOfInt.length) {
        break;
      }
      int i2 = arrayOfInt[i1];
      if (i2 != 1)
      {
        if (i2 != 2)
        {
          if (i2 != 3)
          {
            if (i2 == 4) {
              a(localLabel1, localLabel2, c, c);
            }
          }
          else {
            a(localLabel1, localLabel2, d, d);
          }
        }
        else {
          a(localLabel1, localLabel2, a, a);
        }
      }
      else {
        b(localLabel1, localLabel2);
      }
      i1 += 1;
    }
    a(localLabel1, localLabel2);
  }
  
  private void a(org.org.jaxen.util.Label paramLabel1, org.org.jaxen.util.Label paramLabel2)
  {
    int i1 = 0;
    while (i1 < paramLabel1.size())
    {
      Label localLabel = (Label)paramLabel1.get(i1);
      if (d(a))
      {
        j.add(localLabel);
        c.add(null);
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramLabel2.size())
    {
      paramLabel1 = (Label)paramLabel2.get(i1);
      if (d(a))
      {
        c.add(paramLabel1);
        j.add(null);
      }
      i1 += 1;
    }
  }
  
  private void a(org.org.jaxen.util.Label paramLabel1, org.org.jaxen.util.Label paramLabel2, SparseArray paramSparseArray1, SparseArray paramSparseArray2)
  {
    int i2 = paramSparseArray1.size();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView1 = (View)paramSparseArray1.valueAt(i1);
      if ((localView1 != null) && (d(localView1)))
      {
        View localView2 = (View)paramSparseArray2.get(paramSparseArray1.keyAt(i1));
        if ((localView2 != null) && (d(localView2)))
        {
          Label localLabel1 = (Label)paramLabel1.get(localView1);
          Label localLabel2 = (Label)paramLabel2.get(localView2);
          if ((localLabel1 != null) && (localLabel2 != null))
          {
            j.add(localLabel1);
            c.add(localLabel2);
            paramLabel1.remove(localView1);
            paramLabel2.remove(localView2);
          }
        }
      }
      i1 += 1;
    }
  }
  
  private void a(org.org.jaxen.util.Label paramLabel1, org.org.jaxen.util.Label paramLabel2, org.org.jaxen.util.Label paramLabel3, org.org.jaxen.util.Label paramLabel4)
  {
    int i2 = paramLabel3.size();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView1 = (View)paramLabel3.get(i1);
      if ((localView1 != null) && (d(localView1)))
      {
        View localView2 = (View)paramLabel4.get(paramLabel3.getValue(i1));
        if ((localView2 != null) && (d(localView2)))
        {
          Label localLabel1 = (Label)paramLabel1.get(localView1);
          Label localLabel2 = (Label)paramLabel2.get(localView2);
          if ((localLabel1 != null) && (localLabel2 != null))
          {
            j.add(localLabel1);
            c.add(localLabel2);
            paramLabel1.remove(localView1);
            paramLabel2.remove(localView2);
          }
        }
      }
      i1 += 1;
    }
  }
  
  private void a(org.org.jaxen.util.Label paramLabel1, org.org.jaxen.util.Label paramLabel2, LongSparseArray paramLongSparseArray1, LongSparseArray paramLongSparseArray2)
  {
    int i2 = paramLongSparseArray1.size();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView1 = (View)paramLongSparseArray1.valueAt(i1);
      if ((localView1 != null) && (d(localView1)))
      {
        View localView2 = (View)paramLongSparseArray2.get(paramLongSparseArray1.keyAt(i1));
        if ((localView2 != null) && (d(localView2)))
        {
          Label localLabel1 = (Label)paramLabel1.get(localView1);
          Label localLabel2 = (Label)paramLabel2.get(localView2);
          if ((localLabel1 != null) && (localLabel2 != null))
          {
            j.add(localLabel1);
            c.add(localLabel2);
            paramLabel1.remove(localView1);
            paramLabel2.remove(localView2);
          }
        }
      }
      i1 += 1;
    }
  }
  
  private static boolean a(Label paramLabel1, Label paramLabel2, String paramString)
  {
    paramLabel1 = c.get(paramString);
    paramLabel2 = c.get(paramString);
    if ((paramLabel1 == null) && (paramLabel2 == null)) {
      return false;
    }
    if ((paramLabel1 != null) && (paramLabel2 != null)) {
      return paramLabel1.equals(paramLabel2) ^ true;
    }
    return true;
  }
  
  private void b(org.org.jaxen.util.Label paramLabel1, org.org.jaxen.util.Label paramLabel2)
  {
    int i1 = paramLabel1.size() - 1;
    while (i1 >= 0)
    {
      Object localObject1 = (View)paramLabel1.getValue(i1);
      if ((localObject1 != null) && (d((View)localObject1)))
      {
        localObject1 = (Label)paramLabel2.remove(localObject1);
        if (localObject1 != null)
        {
          Object localObject2 = a;
          if ((localObject2 != null) && (d((View)localObject2)))
          {
            localObject2 = (Label)paramLabel1.d(i1);
            j.add(localObject2);
            c.add(localObject1);
          }
        }
      }
      i1 -= 1;
    }
  }
  
  private static org.org.jaxen.util.Label get()
  {
    org.org.jaxen.util.Label localLabel2 = (org.org.jaxen.util.Label)responseCache.get();
    org.org.jaxen.util.Label localLabel1 = localLabel2;
    if (localLabel2 == null)
    {
      localLabel1 = new org.org.jaxen.util.Label();
      responseCache.set(localLabel1);
    }
    return localLabel1;
  }
  
  private void remove(Animator paramAnimator, org.org.jaxen.util.Label paramLabel)
  {
    if (paramAnimator != null)
    {
      paramAnimator.addListener(new MainActivity.29(this, paramLabel));
      onBindViewHolder(paramAnimator);
    }
  }
  
  public Animator a(ViewGroup paramViewGroup, Label paramLabel1, Label paramLabel2)
  {
    return null;
  }
  
  public h a(long paramLong)
  {
    h = paramLong;
    return this;
  }
  
  public h a(TimeInterpolator paramTimeInterpolator)
  {
    g = paramTimeInterpolator;
    return this;
  }
  
  public h a(m paramM)
  {
    if (q == null) {
      q = new ArrayList();
    }
    q.add(paramM);
    return this;
  }
  
  protected void a()
  {
    x -= 1;
    if (x == 0)
    {
      Object localObject = q;
      if ((localObject != null) && (((ArrayList)localObject).size() > 0))
      {
        localObject = (ArrayList)q.clone();
        int i2 = ((ArrayList)localObject).size();
        i1 = 0;
        while (i1 < i2)
        {
          ((m)((ArrayList)localObject).get(i1)).a(this);
          i1 += 1;
        }
      }
      int i1 = 0;
      while (i1 < e.c.size())
      {
        localObject = (View)e.c.valueAt(i1);
        if (localObject != null) {
          ViewCompat.b((View)localObject, false);
        }
        i1 += 1;
      }
      i1 = 0;
      while (i1 < i.c.size())
      {
        localObject = (View)i.c.valueAt(i1);
        if (localObject != null) {
          ViewCompat.b((View)localObject, false);
        }
        i1 += 1;
      }
      w = true;
    }
  }
  
  public void a(View paramView)
  {
    if (!w)
    {
      org.org.jaxen.util.Label localLabel = get();
      int i1 = localLabel.size();
      paramView = a.c(paramView);
      i1 -= 1;
      while (i1 >= 0)
      {
        t localT = (t)localLabel.get(i1);
        if ((e != null) && (paramView.equals(c))) {
          Edge.remove((Animator)localLabel.getValue(i1));
        }
        i1 -= 1;
      }
      paramView = q;
      if ((paramView != null) && (paramView.size() > 0))
      {
        paramView = (ArrayList)q.clone();
        int i2 = paramView.size();
        i1 = 0;
        while (i1 < i2)
        {
          ((m)paramView.get(i1)).d(this);
          i1 += 1;
        }
      }
      z = true;
    }
  }
  
  void a(ViewGroup paramViewGroup)
  {
    j = new ArrayList();
    c = new ArrayList();
    a(e, i);
    org.org.jaxen.util.Label localLabel = get();
    int i1 = localLabel.size();
    c localC = a.c(paramViewGroup);
    i1 -= 1;
    while (i1 >= 0)
    {
      Animator localAnimator = (Animator)localLabel.getValue(i1);
      if (localAnimator != null)
      {
        t localT = (t)localLabel.get(localAnimator);
        if ((localT != null) && (e != null) && (localC.equals(c)))
        {
          Label localLabel1 = a;
          Object localObject = e;
          int i2 = 1;
          Label localLabel2 = c((View)localObject, true);
          localObject = b((View)localObject, true);
          if (((localLabel2 == null) && (localObject == null)) || (!b.b(localLabel1, (Label)localObject))) {
            i2 = 0;
          }
          if (i2 != 0) {
            if ((!localAnimator.isRunning()) && (!localAnimator.isStarted())) {
              localLabel.remove(localAnimator);
            } else {
              localAnimator.cancel();
            }
          }
        }
      }
      i1 -= 1;
    }
    a(paramViewGroup, e, i, j, c);
    b();
  }
  
  protected void a(ViewGroup paramViewGroup, x paramX1, x paramX2, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    org.org.jaxen.util.Label localLabel = get();
    long l1 = Long.MAX_VALUE;
    SparseIntArray localSparseIntArray = new SparseIntArray();
    int i3 = paramArrayList1.size();
    int i1 = 0;
    int i2;
    while (i1 < i3)
    {
      Object localObject1 = (Label)paramArrayList1.get(i1);
      paramX1 = (Label)paramArrayList2.get(i1);
      Object localObject2 = localObject1;
      if (localObject1 != null)
      {
        localObject2 = localObject1;
        if (!b.contains(this)) {
          localObject2 = null;
        }
      }
      x localX = paramX1;
      if (paramX1 != null)
      {
        localX = paramX1;
        if (!b.contains(this)) {
          localX = null;
        }
      }
      long l2;
      if ((localObject2 == null) && (localX == null))
      {
        l2 = l1;
      }
      else
      {
        if ((localObject2 != null) && (localX != null) && (!b(localObject2, localX))) {
          i2 = 0;
        } else {
          i2 = 1;
        }
        l2 = l1;
        if (i2 != 0)
        {
          localObject1 = a(paramViewGroup, localObject2, localX);
          paramX1 = (x)localObject1;
          if (paramX1 != null)
          {
            Object localObject3 = null;
            View localView;
            if (localX != null)
            {
              localView = a;
              Object localObject4 = getValue();
              if ((localView != null) && (localObject4 != null)) {
                if (localObject4.length > 0)
                {
                  localObject3 = new Label();
                  localObject1 = localObject3;
                  a = localView;
                  Label localLabel1 = (Label)b.get(localView);
                  if (localLabel1 != null)
                  {
                    i2 = 0;
                    while (i2 < localObject4.length)
                    {
                      c.put(localObject4[i2], c.get(localObject4[i2]));
                      i2 += 1;
                    }
                  }
                  int i4 = localLabel.size();
                  i2 = 0;
                  while (i2 < i4)
                  {
                    localObject4 = (t)localLabel.get((Animator)localLabel.getValue(i2));
                    if ((a != null) && (e == localView) && (d.equals(getGroupId())) && (a.equals(localObject3)))
                    {
                      paramX1 = null;
                      break;
                    }
                    i2 += 1;
                  }
                  break label434;
                }
              }
              paramX1 = (x)localObject1;
              localObject1 = localObject3;
            }
            else
            {
              label434:
              localView = a;
              localObject1 = null;
            }
            l2 = l1;
            if (paramX1 != null)
            {
              localObject3 = y;
              l2 = l1;
              if (localObject3 != null)
              {
                l2 = ((ByteVector)localObject3).a(paramViewGroup, this, localObject2, localX);
                localSparseIntArray.put(s.size(), (int)l2);
                l2 = Math.min(l2, l1);
              }
              localLabel.put(paramX1, new t(localView, getGroupId(), this, a.c(paramViewGroup), (Label)localObject1));
              s.add(paramX1);
            }
          }
          else
          {
            l2 = l1;
          }
        }
      }
      i1 += 1;
      l1 = l2;
    }
    if (l1 != 0L)
    {
      i1 = 0;
      while (i1 < localSparseIntArray.size())
      {
        i2 = localSparseIntArray.keyAt(i1);
        paramViewGroup = (Animator)s.get(i2);
        paramViewGroup.setStartDelay(localSparseIntArray.valueAt(i1) - l1 + paramViewGroup.getStartDelay());
        i1 += 1;
      }
    }
  }
  
  void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    a(paramBoolean);
    Object localObject1;
    if ((n.size() > 0) || (m.size() > 0))
    {
      localObject1 = l;
      if ((localObject1 == null) || (((ArrayList)localObject1).isEmpty()))
      {
        localObject1 = a;
        if ((localObject1 == null) || (((ArrayList)localObject1).isEmpty())) {
          break label75;
        }
      }
    }
    a(paramViewGroup, paramBoolean);
    break label309;
    label75:
    int i1 = 0;
    Object localObject2;
    while (i1 < n.size())
    {
      localObject1 = paramViewGroup.findViewById(((Integer)n.get(i1)).intValue());
      if (localObject1 != null)
      {
        localObject2 = new Label();
        a = ((View)localObject1);
        if (paramBoolean) {
          a((Label)localObject2);
        } else {
          c((Label)localObject2);
        }
        b.add(this);
        b((Label)localObject2);
        if (paramBoolean) {
          a(e, (View)localObject1, (Label)localObject2);
        } else {
          a(i, (View)localObject1, (Label)localObject2);
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < m.size())
    {
      paramViewGroup = (View)m.get(i1);
      localObject1 = new Label();
      a = paramViewGroup;
      if (paramBoolean) {
        a((Label)localObject1);
      } else {
        c((Label)localObject1);
      }
      b.add(this);
      b((Label)localObject1);
      if (paramBoolean) {
        a(e, paramViewGroup, (Label)localObject1);
      } else {
        a(i, paramViewGroup, (Label)localObject1);
      }
      i1 += 1;
    }
    label309:
    if (!paramBoolean)
    {
      paramViewGroup = b;
      if (paramViewGroup != null)
      {
        int i2 = paramViewGroup.size();
        paramViewGroup = new ArrayList(i2);
        i1 = 0;
        while (i1 < i2)
        {
          localObject1 = (String)b.getValue(i1);
          paramViewGroup.add(e.a.remove(localObject1));
          i1 += 1;
        }
        i1 = 0;
        while (i1 < i2)
        {
          localObject1 = (View)paramViewGroup.get(i1);
          if (localObject1 != null)
          {
            localObject2 = (String)b.get(i1);
            e.a.put(localObject2, localObject1);
          }
          i1 += 1;
        }
      }
    }
  }
  
  public void a(ActionProvider paramActionProvider)
  {
    B = paramActionProvider;
  }
  
  public void a(AnnotationVisitor paramAnnotationVisitor)
  {
    if (paramAnnotationVisitor == null)
    {
      A = EDGE;
      return;
    }
    A = paramAnnotationVisitor;
  }
  
  public void a(ByteVector paramByteVector)
  {
    y = paramByteVector;
  }
  
  public abstract void a(Label paramLabel);
  
  void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      e.b.clear();
      e.d.clear();
      e.c.clear();
      return;
    }
    i.b.clear();
    i.d.clear();
    i.c.clear();
  }
  
  Label b(View paramView, boolean paramBoolean)
  {
    Object localObject = o;
    if (localObject != null) {
      return ((h)localObject).b(paramView, paramBoolean);
    }
    if (paramBoolean) {
      localObject = j;
    } else {
      localObject = c;
    }
    if (localObject == null) {
      return null;
    }
    int i4 = ((ArrayList)localObject).size();
    int i3 = -1;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= i4) {
        break;
      }
      Label localLabel = (Label)((ArrayList)localObject).get(i1);
      if (localLabel == null) {
        return null;
      }
      if (a == paramView)
      {
        i2 = i1;
        break;
      }
      i1 += 1;
    }
    if (i2 >= 0)
    {
      if (paramBoolean) {
        paramView = c;
      } else {
        paramView = j;
      }
      return (Label)paramView.get(i2);
    }
    return null;
  }
  
  protected void b()
  {
    i();
    org.org.jaxen.util.Label localLabel = get();
    Iterator localIterator = s.iterator();
    while (localIterator.hasNext())
    {
      Animator localAnimator = (Animator)localIterator.next();
      if (localLabel.containsKey(localAnimator))
      {
        i();
        remove(localAnimator, localLabel);
      }
    }
    s.clear();
    a();
  }
  
  public void b(View paramView)
  {
    if (z)
    {
      if (!w)
      {
        org.org.jaxen.util.Label localLabel = get();
        int i1 = localLabel.size();
        paramView = a.c(paramView);
        i1 -= 1;
        while (i1 >= 0)
        {
          t localT = (t)localLabel.get(i1);
          if ((e != null) && (paramView.equals(c))) {
            Edge.start((Animator)localLabel.getValue(i1));
          }
          i1 -= 1;
        }
        paramView = q;
        if ((paramView != null) && (paramView.size() > 0))
        {
          paramView = (ArrayList)q.clone();
          int i2 = paramView.size();
          i1 = 0;
          while (i1 < i2)
          {
            ((m)paramView.get(i1)).b(this);
            i1 += 1;
          }
        }
      }
      z = false;
    }
  }
  
  void b(Label paramLabel)
  {
    if ((y != null) && (!c.isEmpty()))
    {
      String[] arrayOfString = y.get();
      if (arrayOfString == null) {
        return;
      }
      int i3 = 1;
      int i1 = 0;
      int i2;
      for (;;)
      {
        i2 = i3;
        if (i1 >= arrayOfString.length) {
          break;
        }
        if (!c.containsKey(arrayOfString[i1]))
        {
          i2 = 0;
          break;
        }
        i1 += 1;
      }
      if (i2 == 0) {
        y.a(paramLabel);
      }
    }
  }
  
  public boolean b(Label paramLabel1, Label paramLabel2)
  {
    if ((paramLabel1 != null) && (paramLabel2 != null))
    {
      Object localObject = getValue();
      if (localObject != null)
      {
        int i2 = localObject.length;
        int i1 = 0;
        while (i1 < i2)
        {
          if (a(paramLabel1, paramLabel2, localObject[i1])) {
            return true;
          }
          i1 += 1;
        }
        return false;
      }
      localObject = c.keySet().iterator();
      while (((Iterator)localObject).hasNext()) {
        if (a(paramLabel1, paramLabel2, (String)((Iterator)localObject).next())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public Label c(View paramView, boolean paramBoolean)
  {
    Object localObject = o;
    if (localObject != null) {
      return ((h)localObject).c(paramView, paramBoolean);
    }
    if (paramBoolean) {
      localObject = e;
    } else {
      localObject = i;
    }
    return (Label)b.get(paramView);
  }
  
  public h c(View paramView)
  {
    m.remove(paramView);
    return this;
  }
  
  public h c(m paramM)
  {
    ArrayList localArrayList = q;
    if (localArrayList == null) {
      return this;
    }
    localArrayList.remove(paramM);
    if (q.size() == 0) {
      q = null;
    }
    return this;
  }
  
  public abstract void c(Label paramLabel);
  
  public h clone()
  {
    try
    {
      Object localObject1 = super.clone();
      localObject1 = (h)localObject1;
      Object localObject2 = new ArrayList();
      s = ((ArrayList)localObject2);
      localObject2 = new x();
      e = ((x)localObject2);
      localObject2 = new x();
      i = ((x)localObject2);
      j = null;
      c = null;
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException) {}
    return null;
  }
  
  public List d()
  {
    return l;
  }
  
  boolean d(View paramView)
  {
    int i2 = paramView.getId();
    ArrayList localArrayList = k;
    if ((localArrayList != null) && (localArrayList.contains(Integer.valueOf(i2)))) {
      return false;
    }
    localArrayList = t;
    if ((localArrayList != null) && (localArrayList.contains(paramView))) {
      return false;
    }
    localArrayList = r;
    int i1;
    if (localArrayList != null)
    {
      int i3 = localArrayList.size();
      i1 = 0;
      while (i1 < i3)
      {
        if (((Class)r.get(i1)).isInstance(paramView)) {
          return false;
        }
        i1 += 1;
      }
    }
    if ((p != null) && (ViewCompat.a(paramView) != null) && (p.contains(ViewCompat.a(paramView)))) {
      return false;
    }
    if ((n.size() == 0) && (m.size() == 0))
    {
      localArrayList = a;
      if ((localArrayList == null) || (localArrayList.isEmpty()))
      {
        localArrayList = l;
        if (localArrayList == null) {
          break label296;
        }
        if (localArrayList.isEmpty()) {
          return true;
        }
      }
    }
    if (!n.contains(Integer.valueOf(i2)))
    {
      if (m.contains(paramView)) {
        return true;
      }
      localArrayList = l;
      if ((localArrayList != null) && (localArrayList.contains(ViewCompat.a(paramView)))) {
        return true;
      }
      if (a != null)
      {
        i1 = 0;
        while (i1 < a.size())
        {
          if (((Class)a.get(i1)).isInstance(paramView)) {
            return true;
          }
          i1 += 1;
        }
      }
      return false;
    }
    label296:
    return true;
    return false;
  }
  
  public TimeInterpolator e()
  {
    return g;
  }
  
  public h e(View paramView)
  {
    m.add(paramView);
    return this;
  }
  
  public long f()
  {
    return f;
  }
  
  public h f(long paramLong)
  {
    f = paramLong;
    return this;
  }
  
  public ByteVector g()
  {
    return y;
  }
  
  public String getGroupId()
  {
    return _groupId;
  }
  
  public AnnotationVisitor getIcon()
  {
    return A;
  }
  
  public long getItemId()
  {
    return h;
  }
  
  public List getSubMenu()
  {
    return m;
  }
  
  public ActionProvider getSupportActionProvider()
  {
    return B;
  }
  
  public List getTitle()
  {
    return a;
  }
  
  public String[] getValue()
  {
    return null;
  }
  
  protected void i()
  {
    if (x == 0)
    {
      ArrayList localArrayList = q;
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        localArrayList = (ArrayList)q.clone();
        int i2 = localArrayList.size();
        int i1 = 0;
        while (i1 < i2)
        {
          ((m)localArrayList.get(i1)).c(this);
          i1 += 1;
        }
      }
      w = false;
    }
    x += 1;
  }
  
  public List l()
  {
    return n;
  }
  
  protected void onBindViewHolder(Animator paramAnimator)
  {
    if (paramAnimator == null)
    {
      a();
      return;
    }
    if (getItemId() >= 0L) {
      paramAnimator.setDuration(getItemId());
    }
    if (f() >= 0L) {
      paramAnimator.setStartDelay(f());
    }
    if (e() != null) {
      paramAnimator.setInterpolator(e());
    }
    paramAnimator.addListener(new LogFragment.2(this));
    paramAnimator.start();
  }
  
  public String toString()
  {
    return toString("");
  }
  
  String toString(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(getClass().getSimpleName());
    ((StringBuilder)localObject).append("@");
    ((StringBuilder)localObject).append(Integer.toHexString(hashCode()));
    ((StringBuilder)localObject).append(": ");
    localObject = ((StringBuilder)localObject).toString();
    paramString = (String)localObject;
    if (h != -1L)
    {
      paramString = new StringBuilder();
      paramString.append((String)localObject);
      paramString.append("dur(");
      paramString.append(h);
      paramString.append(") ");
      paramString = paramString.toString();
    }
    localObject = paramString;
    if (f != -1L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("dly(");
      ((StringBuilder)localObject).append(f);
      ((StringBuilder)localObject).append(") ");
      localObject = ((StringBuilder)localObject).toString();
    }
    paramString = (String)localObject;
    if (g != null)
    {
      paramString = new StringBuilder();
      paramString.append((String)localObject);
      paramString.append("interp(");
      paramString.append(g);
      paramString.append(") ");
      paramString = paramString.toString();
    }
    if ((n.size() > 0) || (m.size() > 0))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("tgts(");
      paramString = ((StringBuilder)localObject).toString();
      localObject = paramString;
      int i1;
      if (n.size() > 0)
      {
        i1 = 0;
        for (;;)
        {
          localObject = paramString;
          if (i1 >= n.size()) {
            break;
          }
          localObject = paramString;
          if (i1 > 0)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(paramString);
            ((StringBuilder)localObject).append(", ");
            localObject = ((StringBuilder)localObject).toString();
          }
          paramString = new StringBuilder();
          paramString.append((String)localObject);
          paramString.append(n.get(i1));
          paramString = paramString.toString();
          i1 += 1;
        }
      }
      paramString = (String)localObject;
      if (m.size() > 0)
      {
        i1 = 0;
        for (;;)
        {
          paramString = (String)localObject;
          if (i1 >= m.size()) {
            break;
          }
          paramString = (String)localObject;
          if (i1 > 0)
          {
            paramString = new StringBuilder();
            paramString.append((String)localObject);
            paramString.append(", ");
            paramString = paramString.toString();
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(m.get(i1));
          localObject = ((StringBuilder)localObject).toString();
          i1 += 1;
        }
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(")");
      return ((StringBuilder)localObject).toString();
    }
    return paramString;
  }
}
