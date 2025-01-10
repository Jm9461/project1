package org.org.asm;

import a.b.f.m;
import a.b.g.g.a;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.org.jaxen.util.Label;
import org.org.jaxen.util.f;

public class Handler
{
  static ArrayList<ViewGroup> a = new ArrayList();
  private static h c = new Segment();
  private static ThreadLocal<WeakReference<a<ViewGroup, ArrayList<m>>>> d = new ThreadLocal();
  
  static Label a()
  {
    Object localObject = (WeakReference)d.get();
    if (localObject != null)
    {
      localObject = (Label)((WeakReference)localObject).get();
      if (localObject != null) {
        return localObject;
      }
    }
    localObject = new Label();
    WeakReference localWeakReference = new WeakReference(localObject);
    d.set(localWeakReference);
    return localObject;
  }
  
  private static void a(ViewGroup paramViewGroup, h paramH)
  {
    Object localObject = (ArrayList)a().get(paramViewGroup);
    if ((localObject != null) && (((ArrayList)localObject).size() > 0))
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((h)((Iterator)localObject).next()).a(paramViewGroup);
      }
    }
    if (paramH != null) {
      paramH.a(paramViewGroup, true);
    }
    paramViewGroup = b.a(paramViewGroup);
    if (paramViewGroup != null) {
      paramViewGroup.a();
    }
  }
  
  public static void initialize(ViewGroup paramViewGroup, h paramH)
  {
    if ((!a.contains(paramViewGroup)) && (ViewCompat.get(paramViewGroup)))
    {
      a.add(paramViewGroup);
      h localH = paramH;
      if (paramH == null) {
        localH = c;
      }
      paramH = localH.clone();
      a(paramViewGroup, paramH);
      b.a(paramViewGroup, null);
      onPostExecute(paramViewGroup, paramH);
    }
  }
  
  private static void onPostExecute(ViewGroup paramViewGroup, h paramH)
  {
    if ((paramH != null) && (paramViewGroup != null))
    {
      paramH = new MainActivity.2(paramH, paramViewGroup);
      paramViewGroup.addOnAttachStateChangeListener(paramH);
      paramViewGroup.getViewTreeObserver().addOnPreDrawListener(paramH);
    }
  }
}
