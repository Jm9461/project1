package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

class Frame
  extends ClassWriter
{
  Frame() {}
  
  private static boolean hasSimpleTarget(Transition paramTransition)
  {
    return (!ClassWriter.a(paramTransition.getTargetIds())) || (!ClassWriter.a(paramTransition.getTargetNames())) || (!ClassWriter.a(paramTransition.getTargetTypes()));
  }
  
  public Object a(Object paramObject)
  {
    if (paramObject != null) {
      return ((Transition)paramObject).clone();
    }
    return null;
  }
  
  public Object a(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject = null;
    paramObject1 = (Transition)paramObject1;
    paramObject2 = (Transition)paramObject2;
    paramObject3 = (Transition)paramObject3;
    if ((paramObject1 != null) && (paramObject2 != null))
    {
      paramObject1 = new TransitionSet().addTransition(paramObject1).addTransition(paramObject2).setOrdering(1);
    }
    else if (paramObject1 == null)
    {
      paramObject1 = localObject;
      if (paramObject2 != null) {
        paramObject1 = paramObject2;
      }
    }
    if (paramObject3 != null)
    {
      paramObject2 = new TransitionSet();
      if (paramObject1 != null) {
        paramObject2.addTransition((Transition)paramObject1);
      }
      paramObject2.addTransition(paramObject3);
      return paramObject2;
    }
    return paramObject1;
  }
  
  public void a(ViewGroup paramViewGroup, Object paramObject)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }
  
  public void a(Object paramObject, Rect paramRect)
  {
    if (paramObject != null) {
      ((Transition)paramObject).setEpicenterCallback(new FragmentTransitionCompat21.1(this, paramRect));
    }
  }
  
  public void a(Object paramObject, View paramView)
  {
    if (paramObject != null) {
      ((Transition)paramObject).removeTarget(paramView);
    }
  }
  
  public void a(Object paramObject, View paramView, ArrayList paramArrayList)
  {
    paramObject = (TransitionSet)paramObject;
    List localList = paramObject.getTargets();
    localList.clear();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      ClassWriter.a(localList, (View)paramArrayList.get(i));
      i += 1;
    }
    localList.add(paramView);
    paramArrayList.add(paramView);
    a(paramObject, paramArrayList);
  }
  
  public void a(Object paramObject1, Object paramObject2, ArrayList paramArrayList1, Object paramObject3, ArrayList paramArrayList2, Object paramObject4, ArrayList paramArrayList3)
  {
    ((Transition)paramObject1).addListener(new FragmentTransitionCompat21.4(this, paramObject2, paramArrayList1, paramObject3, paramArrayList2, paramObject4, paramArrayList3));
  }
  
  public void a(Object paramObject, ArrayList paramArrayList)
  {
    paramObject = (Transition)paramObject;
    if (paramObject == null) {
      return;
    }
    int j;
    int i;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      j = paramObject.getTransitionCount();
      i = 0;
      while (i < j)
      {
        a(paramObject.getTransitionAt(i), paramArrayList);
        i += 1;
      }
      return;
    }
    if ((!hasSimpleTarget(paramObject)) && (ClassWriter.a(paramObject.getTargets())))
    {
      j = paramArrayList.size();
      i = 0;
      while (i < j)
      {
        paramObject.addTarget((View)paramArrayList.get(i));
        i += 1;
      }
    }
  }
  
  public void a(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    paramObject = (Transition)paramObject;
    int j;
    int i;
    if ((paramObject instanceof TransitionSet))
    {
      paramObject = (TransitionSet)paramObject;
      j = paramObject.getTransitionCount();
      i = 0;
      while (i < j)
      {
        a(paramObject.getTransitionAt(i), paramArrayList1, paramArrayList2);
        i += 1;
      }
      return;
    }
    if (!hasSimpleTarget(paramObject))
    {
      List localList = paramObject.getTargets();
      if ((localList != null) && (localList.size() == paramArrayList1.size()) && (localList.containsAll(paramArrayList1)))
      {
        if (paramArrayList2 == null) {
          i = 0;
        } else {
          i = paramArrayList2.size();
        }
        j = 0;
        while (j < i)
        {
          paramObject.addTarget((View)paramArrayList2.get(j));
          j += 1;
        }
        i = paramArrayList1.size() - 1;
        while (i >= 0)
        {
          paramObject.removeTarget((View)paramArrayList1.get(i));
          i -= 1;
        }
      }
    }
  }
  
  public Object b(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    TransitionSet localTransitionSet = new TransitionSet();
    localTransitionSet.addTransition((Transition)paramObject);
    return localTransitionSet;
  }
  
  public Object b(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    TransitionSet localTransitionSet = new TransitionSet();
    if (paramObject1 != null) {
      localTransitionSet.addTransition((Transition)paramObject1);
    }
    if (paramObject2 != null) {
      localTransitionSet.addTransition((Transition)paramObject2);
    }
    if (paramObject3 != null) {
      localTransitionSet.addTransition((Transition)paramObject3);
    }
    return localTransitionSet;
  }
  
  public void b(Object paramObject, View paramView)
  {
    if (paramObject != null) {
      ((Transition)paramObject).addTarget(paramView);
    }
  }
  
  public void b(Object paramObject, ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    paramObject = (TransitionSet)paramObject;
    if (paramObject != null)
    {
      paramObject.getTargets().clear();
      paramObject.getTargets().addAll(paramArrayList2);
      a(paramObject, paramArrayList1, paramArrayList2);
    }
  }
  
  public void c(Object paramObject, View paramView)
  {
    if (paramView != null)
    {
      paramObject = (Transition)paramObject;
      Rect localRect = new Rect();
      setText(paramView, localRect);
      paramObject.setEpicenterCallback(new FragmentTransitionCompat21.3(this, localRect));
    }
  }
  
  public void c(Object paramObject, View paramView, ArrayList paramArrayList)
  {
    ((Transition)paramObject).addListener(new TranslationAnimationCreator.TransitionPositionListener(this, paramView, paramArrayList));
  }
  
  public boolean get(Object paramObject)
  {
    return paramObject instanceof Transition;
  }
}
