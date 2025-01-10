package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.org.jaxen.util.Label;

class f
{
  private static final ClassWriter a;
  private static final ClassWriter b = getInstance();
  private static final int[] d = { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  
  static
  {
    Frame localFrame;
    if (Build.VERSION.SDK_INT >= 21) {
      localFrame = new Frame();
    } else {
      localFrame = null;
    }
    a = localFrame;
  }
  
  private static ClassWriter a(Fragment paramFragment1, Fragment paramFragment2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFragment1 != null)
    {
      Object localObject = paramFragment1.getString();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      localObject = paramFragment1.findItem();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      paramFragment1 = paramFragment1.remove();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (paramFragment2 != null)
    {
      paramFragment1 = paramFragment2.getItemId();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.b();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getItem();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (localArrayList.isEmpty()) {
      return null;
    }
    paramFragment1 = a;
    if ((paramFragment1 != null) && (a(paramFragment1, localArrayList))) {
      return a;
    }
    paramFragment1 = b;
    if ((paramFragment1 != null) && (a(paramFragment1, localArrayList))) {
      return b;
    }
    if ((a == null) && (b == null)) {
      return null;
    }
    throw new IllegalArgumentException("Invalid Transition types");
  }
  
  private static h a(h paramH, SparseArray paramSparseArray, int paramInt)
  {
    h localH = paramH;
    if (paramH == null)
    {
      localH = new h();
      paramSparseArray.put(paramInt, localH);
    }
    return localH;
  }
  
  static View a(Label paramLabel, h paramH, Object paramObject, boolean paramBoolean)
  {
    paramH = s;
    if ((paramObject != null) && (paramLabel != null))
    {
      paramObject = l;
      if ((paramObject != null) && (!paramObject.isEmpty()))
      {
        if (paramBoolean) {
          paramH = (String)l.get(0);
        } else {
          paramH = (String)b.get(0);
        }
        return (View)paramLabel.get(paramH);
      }
    }
    return null;
  }
  
  private static Object a(ClassWriter paramClassWriter, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean)
  {
    if ((paramFragment1 != null) && (paramFragment2 != null))
    {
      if (paramBoolean) {
        paramFragment1 = paramFragment2.remove();
      } else {
        paramFragment1 = paramFragment1.getItem();
      }
      return paramClassWriter.b(paramClassWriter.a(paramFragment1));
    }
    return null;
  }
  
  private static Object a(ClassWriter paramClassWriter, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.b();
    } else {
      paramFragment = paramFragment.getItemId();
    }
    return paramClassWriter.a(paramFragment);
  }
  
  private static Object a(ClassWriter paramClassWriter, ViewGroup paramViewGroup, View paramView, Label paramLabel, h paramH, ArrayList paramArrayList1, ArrayList paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = b;
    Fragment localFragment2 = a;
    if (localFragment1 != null) {
      localFragment1.getValue().setVisibility(0);
    }
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      boolean bool = c;
      Object localObject;
      if (paramLabel.isEmpty()) {
        localObject = null;
      } else {
        localObject = a(paramClassWriter, localFragment1, localFragment2, bool);
      }
      Label localLabel2 = a(paramClassWriter, paramLabel, localObject, paramH);
      Label localLabel1 = b(paramClassWriter, paramLabel, localObject, paramH);
      if (paramLabel.isEmpty())
      {
        if (localLabel2 != null) {
          localLabel2.clear();
        }
        if (localLabel1 != null) {
          localLabel1.clear();
        }
        localObject = null;
      }
      else
      {
        a(paramArrayList1, localLabel2, paramLabel.keySet());
        a(paramArrayList2, localLabel1, paramLabel.values());
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject == null)) {
        return null;
      }
      a(localFragment1, localFragment2, bool, localLabel2, true);
      if (localObject != null)
      {
        paramArrayList2.add(paramView);
        paramClassWriter.a(localObject, paramView, paramArrayList1);
        b(paramClassWriter, localObject, paramObject2, localLabel2, i, e);
        paramView = new Rect();
        paramLabel = a(localLabel1, paramH, paramObject1, bool);
        if (paramLabel != null) {
          paramClassWriter.a(paramObject1, paramView);
        }
      }
      else
      {
        paramView = null;
        paramLabel = null;
      }
      RecyclerView.Adapter.a(paramViewGroup, new Item(localFragment1, localFragment2, bool, localLabel1, paramLabel, paramClassWriter, paramView));
      return localObject;
    }
    return null;
  }
  
  private static Object a(ClassWriter paramClassWriter, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramObject1 != null)
    {
      bool1 = bool2;
      if (paramObject2 != null)
      {
        bool1 = bool2;
        if (paramFragment != null)
        {
          if (paramBoolean) {
            paramBoolean = paramFragment.onMenuItemClick();
          } else {
            paramBoolean = paramFragment.visit();
          }
          bool1 = paramBoolean;
        }
      }
    }
    if (bool1) {
      return paramClassWriter.b(paramObject2, paramObject1, paramObject3);
    }
    return paramClassWriter.a(paramObject2, paramObject1, paramObject3);
  }
  
  private static String a(Label paramLabel, String paramString)
  {
    int j = paramLabel.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(paramLabel.get(i))) {
        return (String)paramLabel.getValue(i);
      }
      i += 1;
    }
    return null;
  }
  
  static ArrayList a(ClassWriter paramClassWriter, Object paramObject, Fragment paramFragment, ArrayList paramArrayList, View paramView)
  {
    ArrayList localArrayList;
    if (paramObject != null)
    {
      localArrayList = new ArrayList();
      paramFragment = paramFragment.getValue();
      if (paramFragment != null) {
        paramClassWriter.a(localArrayList, paramFragment);
      }
      if (paramArrayList != null) {
        localArrayList.removeAll(paramArrayList);
      }
      if (!localArrayList.isEmpty())
      {
        localArrayList.add(paramView);
        paramClassWriter.a(paramObject, localArrayList);
        return localArrayList;
      }
    }
    else
    {
      return null;
    }
    return localArrayList;
  }
  
  private static Label a(int paramInt1, ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt2, int paramInt3)
  {
    Label localLabel = new Label();
    paramInt3 -= 1;
    while (paramInt3 >= paramInt2)
    {
      Object localObject = (BackStackRecord)paramArrayList1.get(paramInt3);
      if (((BackStackRecord)localObject).a(paramInt1))
      {
        boolean bool = ((Boolean)paramArrayList2.get(paramInt3)).booleanValue();
        ArrayList localArrayList1 = l;
        if (localArrayList1 != null)
        {
          int j = localArrayList1.size();
          ArrayList localArrayList2;
          if (bool)
          {
            localArrayList1 = l;
            localArrayList2 = b;
          }
          else
          {
            localArrayList2 = l;
            localArrayList1 = b;
          }
          int i = 0;
          while (i < j)
          {
            localObject = (String)localArrayList2.get(i);
            String str1 = (String)localArrayList1.get(i);
            String str2 = (String)localLabel.remove(str1);
            if (str2 != null) {
              localLabel.put(localObject, str2);
            } else {
              localLabel.put(localObject, str1);
            }
            i += 1;
          }
        }
      }
      paramInt3 -= 1;
    }
    return localLabel;
  }
  
  private static Label a(ClassWriter paramClassWriter, Label paramLabel, Object paramObject, h paramH)
  {
    if ((!paramLabel.isEmpty()) && (paramObject != null))
    {
      paramObject = a;
      Label localLabel = new Label();
      paramClassWriter.a(localLabel, paramObject.getValue());
      paramClassWriter = e;
      if (i)
      {
        paramObject = paramObject.f();
        paramClassWriter = b;
      }
      else
      {
        paramObject = paramObject.e();
        paramClassWriter = l;
      }
      localLabel.equals(paramClassWriter);
      if (paramObject != null)
      {
        paramObject.put(paramClassWriter, localLabel);
        int i = paramClassWriter.size() - 1;
        while (i >= 0)
        {
          paramH = (String)paramClassWriter.get(i);
          paramObject = (View)localLabel.get(paramH);
          if (paramObject == null)
          {
            paramLabel.remove(paramH);
          }
          else if (!paramH.equals(ViewCompat.a(paramObject)))
          {
            paramH = (String)paramLabel.remove(paramH);
            paramLabel.put(ViewCompat.a(paramObject), paramH);
          }
          i -= 1;
        }
        return localLabel;
      }
      paramLabel.equals(localLabel.keySet());
      return localLabel;
    }
    paramLabel.clear();
    return null;
  }
  
  private static void a(BackStackRecord paramBackStackRecord, e paramE, SparseArray paramSparseArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Fragment localFragment = a;
    if (localFragment == null) {
      return;
    }
    int i1 = i;
    if (i1 == 0) {
      return;
    }
    if (paramBoolean1) {
      i = d[i];
    } else {
      i = i;
    }
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1;
    if (i != 1)
    {
      if (i != 3) {
        if (i != 4)
        {
          if (i != 5)
          {
            if (i != 6)
            {
              if (i == 7) {
                break label391;
              }
              bool1 = false;
              i = 0;
              j = 0;
              k = 0;
              break label442;
            }
          }
          else
          {
            if (paramBoolean2)
            {
              bool1 = bool2;
              if (mNeedMenuInvalidate)
              {
                bool1 = bool2;
                if (!mHidden)
                {
                  bool1 = bool2;
                  if (mAdded) {
                    bool1 = true;
                  }
                }
              }
            }
            else
            {
              bool1 = mHidden;
            }
            i = 0;
            j = 0;
            k = 1;
            break label442;
          }
        }
        else
        {
          if (paramBoolean2)
          {
            i = j;
            if (mNeedMenuInvalidate)
            {
              i = j;
              if (mAdded)
              {
                i = j;
                if (mHidden) {
                  i = 1;
                }
              }
            }
            j = i;
          }
          else
          {
            i = k;
            if (mAdded)
            {
              i = k;
              if (!mHidden) {
                i = 1;
              }
            }
            j = i;
          }
          bool1 = false;
          i = 1;
          k = 0;
          break label442;
        }
      }
      if (paramBoolean2)
      {
        i = m;
        if (!mAdded)
        {
          paramE = mView;
          i = m;
          if (paramE != null)
          {
            i = m;
            if (paramE.getVisibility() == 0)
            {
              i = m;
              if (x >= 0.0F) {
                i = 1;
              }
            }
          }
        }
        j = i;
      }
      else
      {
        i = n;
        if (mAdded)
        {
          i = n;
          if (!mHidden) {
            i = 1;
          }
        }
        j = i;
      }
      bool1 = false;
      i = 1;
      k = 0;
      break label442;
    }
    label391:
    if (paramBoolean2)
    {
      bool1 = mActive;
    }
    else
    {
      bool1 = bool3;
      if (!mAdded)
      {
        bool1 = bool3;
        if (!mHidden) {
          bool1 = true;
        }
      }
    }
    int i = 0;
    j = 0;
    k = 1;
    label442:
    Object localObject = (h)paramSparseArray.get(i1);
    paramE = (e)localObject;
    if (bool1)
    {
      paramE = a((h)localObject, paramSparseArray, i1);
      b = localFragment;
      c = paramBoolean1;
      s = paramBackStackRecord;
    }
    if ((!paramBoolean2) && (k != 0))
    {
      if ((paramE != null) && (a == localFragment)) {
        a = null;
      }
      localObject = mManager;
      if ((mState < 1) && (mCurState >= 1) && (!c))
      {
        ((FragmentManagerImpl)localObject).makeActive(localFragment);
        ((FragmentManagerImpl)localObject).moveToState(localFragment, 1, 0, 0, false);
      }
      else {}
    }
    if (j != 0)
    {
      e localE = paramE;
      if (paramE != null)
      {
        localObject = localE;
        if (a != null) {}
      }
      else
      {
        localObject = a(localE, paramSparseArray, i1);
        a = localFragment;
        i = paramBoolean1;
        e = paramBackStackRecord;
      }
    }
    else
    {
      localObject = paramE;
    }
    if ((!paramBoolean2) && (i != 0) && (localObject != null) && (b == localFragment)) {
      b = null;
    }
  }
  
  public static void a(BackStackRecord paramBackStackRecord, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int j = a.size();
    int i = 0;
    while (i < j)
    {
      a(paramBackStackRecord, (e)a.get(i), paramSparseArray, false, paramBoolean);
      i += 1;
    }
  }
  
  private static void a(ClassWriter paramClassWriter, ViewGroup paramViewGroup, Fragment paramFragment, View paramView, ArrayList paramArrayList1, Object paramObject1, ArrayList paramArrayList2, Object paramObject2, ArrayList paramArrayList3)
  {
    RecyclerView.Adapter.a(paramViewGroup, new a(paramObject1, paramClassWriter, paramView, paramFragment, paramArrayList1, paramArrayList2, paramArrayList3, paramObject2));
  }
  
  private static void a(ClassWriter paramClassWriter, Object paramObject, Fragment paramFragment, ArrayList paramArrayList)
  {
    if ((paramFragment != null) && (paramObject != null) && (mAdded) && (mHidden) && (mNeedMenuInvalidate))
    {
      paramFragment.a(true);
      paramClassWriter.c(paramObject, paramFragment.getValue(), paramArrayList);
      RecyclerView.Adapter.a(mContainer, new ChessController.SearchListener.1(paramArrayList));
    }
  }
  
  static void a(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, Label paramLabel, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      paramFragment1 = paramFragment2.f();
    } else {
      paramFragment1 = paramFragment1.f();
    }
    if (paramFragment1 != null)
    {
      paramFragment2 = new ArrayList();
      ArrayList localArrayList = new ArrayList();
      int i;
      if (paramLabel == null) {
        i = 0;
      } else {
        i = paramLabel.size();
      }
      int j = 0;
      while (j < i)
      {
        localArrayList.add(paramLabel.getValue(j));
        paramFragment2.add(paramLabel.get(j));
        j += 1;
      }
      if (paramBoolean2)
      {
        paramFragment1.startActivity(localArrayList, paramFragment2, null);
        return;
      }
      paramFragment1.send(localArrayList, paramFragment2, null);
    }
  }
  
  private static void a(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, h paramH, View paramView, Label paramLabel)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.findViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Object localObject5 = b;
    Object localObject3 = a;
    ClassWriter localClassWriter = a((Fragment)localObject3, (Fragment)localObject5);
    if (localClassWriter == null) {
      return;
    }
    boolean bool1 = c;
    boolean bool2 = i;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2 = a(localClassWriter, (Fragment)localObject5, bool1);
    Object localObject1 = b(localClassWriter, (Fragment)localObject3, bool2);
    Object localObject4 = a(localClassWriter, paramFragmentManagerImpl, paramView, paramLabel, paramH, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject4 == null) && (localObject1 == null)) {
      return;
    }
    paramH = (h)localObject1;
    localObject1 = a(localClassWriter, paramH, (Fragment)localObject3, localArrayList2, paramView);
    paramView = a(localClassWriter, localObject2, (Fragment)localObject5, localArrayList1, paramView);
    a(paramView, 4);
    localObject5 = a(localClassWriter, localObject2, paramH, localObject4, (Fragment)localObject5, bool1);
    if (localObject5 != null)
    {
      a(localClassWriter, paramH, (Fragment)localObject3, (ArrayList)localObject1);
      localObject3 = localClassWriter.a(localArrayList1);
      localClassWriter.a(localObject5, localObject2, paramView, paramH, (ArrayList)localObject1, localObject4, localArrayList1);
      localClassWriter.a(paramFragmentManagerImpl, localObject5);
      localClassWriter.a(paramFragmentManagerImpl, localArrayList2, localArrayList1, (ArrayList)localObject3, paramLabel);
      a(paramView, 0);
      localClassWriter.b(localObject4, localArrayList2, localArrayList1);
    }
  }
  
  static void a(FragmentManagerImpl paramFragmentManagerImpl, ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (mCurState < 1) {
      return;
    }
    SparseArray localSparseArray = new SparseArray();
    int i = paramInt1;
    Object localObject;
    while (i < paramInt2)
    {
      localObject = (BackStackRecord)paramArrayList1.get(i);
      if (((Boolean)paramArrayList2.get(i)).booleanValue()) {
        b((BackStackRecord)localObject, localSparseArray, paramBoolean);
      } else {
        a((BackStackRecord)localObject, localSparseArray, paramBoolean);
      }
      i += 1;
    }
    if (localSparseArray.size() != 0)
    {
      localObject = new View(mHost.getContext());
      int j = localSparseArray.size();
      i = 0;
      while (i < j)
      {
        int k = localSparseArray.keyAt(i);
        Label localLabel = a(k, paramArrayList1, paramArrayList2, paramInt1, paramInt2);
        h localH = (h)localSparseArray.valueAt(i);
        if (paramBoolean) {
          a(paramFragmentManagerImpl, k, localH, (View)localObject, localLabel);
        } else {
          b(paramFragmentManagerImpl, k, localH, (View)localObject, localLabel);
        }
        i += 1;
      }
    }
  }
  
  static void a(ArrayList paramArrayList, int paramInt)
  {
    if (paramArrayList == null) {
      return;
    }
    int i = paramArrayList.size() - 1;
    while (i >= 0)
    {
      ((View)paramArrayList.get(i)).setVisibility(paramInt);
      i -= 1;
    }
  }
  
  private static void a(ArrayList paramArrayList, Label paramLabel, Collection paramCollection)
  {
    int i = paramLabel.size() - 1;
    while (i >= 0)
    {
      View localView = (View)paramLabel.get(i);
      if (paramCollection.contains(ViewCompat.a(localView))) {
        paramArrayList.add(localView);
      }
      i -= 1;
    }
  }
  
  private static boolean a(ClassWriter paramClassWriter, List paramList)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      if (!paramClassWriter.get(paramList.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static Object b(ClassWriter paramClassWriter, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.findItem();
    } else {
      paramFragment = paramFragment.getString();
    }
    return paramClassWriter.a(paramFragment);
  }
  
  private static Object b(ClassWriter paramClassWriter, ViewGroup paramViewGroup, View paramView, Label paramLabel, h paramH, ArrayList paramArrayList1, ArrayList paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = b;
    Fragment localFragment2 = a;
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      boolean bool = c;
      Object localObject;
      if (paramLabel.isEmpty()) {
        localObject = null;
      } else {
        localObject = a(paramClassWriter, localFragment1, localFragment2, bool);
      }
      Label localLabel = a(paramClassWriter, paramLabel, localObject, paramH);
      if (paramLabel.isEmpty()) {
        localObject = null;
      } else {
        paramArrayList1.addAll(localLabel.values());
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject == null)) {
        return null;
      }
      a(localFragment1, localFragment2, bool, localLabel, true);
      if (localObject != null)
      {
        Rect localRect = new Rect();
        paramClassWriter.a(localObject, paramView, paramArrayList1);
        b(paramClassWriter, localObject, paramObject2, localLabel, i, e);
        if (paramObject1 != null) {
          paramClassWriter.a(paramObject1, localRect);
        }
        paramObject2 = localRect;
      }
      else
      {
        paramObject2 = null;
      }
      RecyclerView.Adapter.a(paramViewGroup, new NumberPicker(paramClassWriter, paramLabel, localObject, paramH, paramArrayList2, paramView, localFragment1, localFragment2, bool, paramArrayList1, paramObject1, paramObject2));
      return localObject;
    }
    return null;
  }
  
  static Label b(ClassWriter paramClassWriter, Label paramLabel, Object paramObject, h paramH)
  {
    Fragment localFragment = b;
    View localView = localFragment.getValue();
    if ((!paramLabel.isEmpty()) && (paramObject != null) && (localView != null))
    {
      Label localLabel = new Label();
      paramClassWriter.a(localLabel, localView);
      paramClassWriter = s;
      if (c)
      {
        paramObject = localFragment.e();
        paramClassWriter = l;
      }
      else
      {
        paramObject = localFragment.f();
        paramClassWriter = b;
      }
      if (paramClassWriter != null)
      {
        localLabel.equals(paramClassWriter);
        localLabel.equals(paramLabel.values());
      }
      if (paramObject != null)
      {
        paramObject.put(paramClassWriter, localLabel);
        int i = paramClassWriter.size() - 1;
        while (i >= 0)
        {
          paramH = (String)paramClassWriter.get(i);
          paramObject = (View)localLabel.get(paramH);
          if (paramObject == null)
          {
            paramObject = a(paramLabel, paramH);
            if (paramObject != null) {
              paramLabel.remove(paramObject);
            }
          }
          else if (!paramH.equals(ViewCompat.a(paramObject)))
          {
            paramH = a(paramLabel, paramH);
            if (paramH != null) {
              paramLabel.put(paramH, ViewCompat.a(paramObject));
            }
          }
          i -= 1;
        }
        return localLabel;
      }
      clear(paramLabel, localLabel);
      return localLabel;
    }
    paramLabel.clear();
    return null;
  }
  
  public static void b(BackStackRecord paramBackStackRecord, SparseArray paramSparseArray, boolean paramBoolean)
  {
    if (!mManager.mContainer.onHasView()) {
      return;
    }
    int i = a.size() - 1;
    while (i >= 0)
    {
      a(paramBackStackRecord, (e)a.get(i), paramSparseArray, true, paramBoolean);
      i -= 1;
    }
  }
  
  private static void b(ClassWriter paramClassWriter, Object paramObject1, Object paramObject2, Label paramLabel, boolean paramBoolean, BackStackRecord paramBackStackRecord)
  {
    ArrayList localArrayList = l;
    if ((localArrayList != null) && (!localArrayList.isEmpty()))
    {
      if (paramBoolean) {
        paramBackStackRecord = (String)b.get(0);
      } else {
        paramBackStackRecord = (String)l.get(0);
      }
      paramLabel = (View)paramLabel.get(paramBackStackRecord);
      paramClassWriter.c(paramObject1, paramLabel);
      if (paramObject2 != null) {
        paramClassWriter.c(paramObject2, paramLabel);
      }
    }
  }
  
  private static void b(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, h paramH, View paramView, Label paramLabel)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.findViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Fragment localFragment = b;
    Object localObject4 = a;
    ClassWriter localClassWriter = a((Fragment)localObject4, localFragment);
    if (localClassWriter == null) {
      return;
    }
    boolean bool1 = c;
    boolean bool2 = i;
    Object localObject2 = a(localClassWriter, localFragment, bool1);
    Object localObject1 = b(localClassWriter, (Fragment)localObject4, bool2);
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject3 = b(localClassWriter, paramFragmentManagerImpl, paramView, paramLabel, paramH, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject3 == null) && (localObject1 == null)) {
      return;
    }
    localObject4 = a(localClassWriter, localObject1, (Fragment)localObject4, localArrayList2, paramView);
    if ((localObject4 != null) && (!((ArrayList)localObject4).isEmpty())) {
      break label183;
    }
    localObject1 = null;
    label183:
    localClassWriter.b(localObject2, paramView);
    paramH = a(localClassWriter, localObject2, localObject1, localObject3, localFragment, c);
    if (paramH != null)
    {
      localArrayList2 = new ArrayList();
      localClassWriter.a(paramH, localObject2, localArrayList2, localObject1, (ArrayList)localObject4, localObject3, localArrayList1);
      a(localClassWriter, paramFragmentManagerImpl, localFragment, paramView, localArrayList1, localObject2, localArrayList2, localObject1, (ArrayList)localObject4);
      localClassWriter.a(paramFragmentManagerImpl, localArrayList1, paramLabel);
      localClassWriter.a(paramFragmentManagerImpl, paramH);
      localClassWriter.a(paramFragmentManagerImpl, localArrayList1, paramLabel);
    }
  }
  
  private static void clear(Label paramLabel1, Label paramLabel2)
  {
    int i = paramLabel1.size() - 1;
    while (i >= 0)
    {
      if (!paramLabel2.containsKey((String)paramLabel1.get(i))) {
        paramLabel1.d(i);
      }
      i -= 1;
    }
  }
  
  private static ClassWriter getInstance()
  {
    try
    {
      Object localObject = Class.forName("a.b.f.e");
      localObject = ((Class)localObject).getDeclaredConstructor(new Class[0]);
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return (ClassWriter)localObject;
    }
    catch (Exception localException) {}
    return null;
  }
}
