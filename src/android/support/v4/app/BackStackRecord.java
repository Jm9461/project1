package android.support.v4.app;

import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.org.jaxen.util.LogWriter;

final class BackStackRecord
  extends FragmentTransaction
  implements FragmentManager.BackStackEntry, Runnable
{
  ArrayList<java.lang.Runnable> A;
  ArrayList<c.a> a = new ArrayList();
  ArrayList<String> b;
  boolean c = false;
  ArrayList<String> l;
  boolean mAddToBackStack;
  int mBreadCrumbShortTitleRes;
  CharSequence mBreadCrumbShortTitleText;
  int mBreadCrumbTitleRes;
  CharSequence mBreadCrumbTitleText;
  boolean mCommitted;
  int mIndex = -1;
  final FragmentManagerImpl mManager;
  String mName;
  int mPopEnterAnim;
  int mPopExitAnim;
  int mTransition;
  int mTransitionStyle;
  int popEnterAnim;
  int popExitAnim;
  
  public BackStackRecord(FragmentManagerImpl paramFragmentManagerImpl)
  {
    mManager = paramFragmentManagerImpl;
  }
  
  private static boolean a(e paramE)
  {
    paramE = a;
    return (paramE != null) && (mAdded) && (mView != null) && (!mDetached) && (!mHidden) && (paramE.isVisible());
  }
  
  private void add(int paramInt1, Fragment paramFragment, String paramString, int paramInt2)
  {
    Object localObject = paramFragment.getClass();
    int i = ((Class)localObject).getModifiers();
    if ((!((Class)localObject).isAnonymousClass()) && (Modifier.isPublic(i)) && ((!((Class)localObject).isMemberClass()) || (Modifier.isStatic(i))))
    {
      mFragmentManager = mManager;
      if (paramString != null)
      {
        localObject = mTag;
        if ((localObject != null) && (!paramString.equals(localObject)))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Can't change tag of fragment ");
          ((StringBuilder)localObject).append(paramFragment);
          ((StringBuilder)localObject).append(": was ");
          ((StringBuilder)localObject).append(mTag);
          ((StringBuilder)localObject).append(" now ");
          ((StringBuilder)localObject).append(paramString);
          throw new IllegalStateException(((StringBuilder)localObject).toString());
        }
        mTag = paramString;
      }
      if (paramInt1 != 0) {
        if (paramInt1 != -1)
        {
          i = mFragmentId;
          if ((i != 0) && (i != paramInt1))
          {
            paramString = new StringBuilder();
            paramString.append("Can't change container ID of fragment ");
            paramString.append(paramFragment);
            paramString.append(": was ");
            paramString.append(mFragmentId);
            paramString.append(" now ");
            paramString.append(paramInt1);
            throw new IllegalStateException(paramString.toString());
          }
          mFragmentId = paramInt1;
          i = paramInt1;
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Can't add fragment ");
          ((StringBuilder)localObject).append(paramFragment);
          ((StringBuilder)localObject).append(" with tag ");
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(" to container view with no id");
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      addOp(new e(paramInt2, paramFragment));
      return;
    }
    paramFragment = new StringBuilder();
    paramFragment.append("Fragment ");
    paramFragment.append(((Class)localObject).getCanonicalName());
    paramFragment.append(" must be a public static class to be  properly recreated from");
    paramFragment.append(" instance state.");
    throw new IllegalStateException(paramFragment.toString());
  }
  
  Fragment a(ArrayList paramArrayList, Fragment paramFragment)
  {
    int i = 0;
    for (Fragment localFragment1 = paramFragment; i < a.size(); localFragment1 = paramFragment)
    {
      Object localObject = (e)a.get(i);
      int j = i;
      if (j != 1) {
        if (j != 2)
        {
          if ((j != 3) && (j != 6))
          {
            if (j != 7)
            {
              if (j != 8)
              {
                j = i;
                paramFragment = localFragment1;
                break label447;
              }
              a.add(i, new e(9, localFragment1));
              j = i + 1;
              paramFragment = a;
              break label447;
            }
          }
          else
          {
            paramArrayList.remove(a);
            localObject = a;
            j = i;
            paramFragment = localFragment1;
            if (localObject != localFragment1) {
              break label447;
            }
            a.add(i, new e(9, (Fragment)localObject));
            j = i + 1;
            paramFragment = null;
            break label447;
          }
        }
        else
        {
          Fragment localFragment2 = a;
          int i1 = i;
          int k = 0;
          j = paramArrayList.size() - 1;
          for (paramFragment = localFragment1; j >= 0; paramFragment = localFragment1)
          {
            Fragment localFragment3 = (Fragment)paramArrayList.get(j);
            int m = i;
            int n = k;
            localFragment1 = paramFragment;
            if (i == i1) {
              if (localFragment3 == localFragment2)
              {
                n = 1;
                m = i;
                localFragment1 = paramFragment;
              }
              else
              {
                m = i;
                localFragment1 = paramFragment;
                if (localFragment3 == paramFragment)
                {
                  a.add(i, new e(9, localFragment3));
                  m = i + 1;
                  localFragment1 = null;
                }
                paramFragment = new e(3, localFragment3);
                exitAnim = exitAnim;
                popEnterAnim = popEnterAnim;
                enterAnim = enterAnim;
                popExitAnim = popExitAnim;
                a.add(m, paramFragment);
                paramArrayList.remove(localFragment3);
                m += 1;
                n = k;
              }
            }
            j -= 1;
            i = m;
            k = n;
          }
          if (k != 0)
          {
            a.remove(i);
            i -= 1;
          }
          else
          {
            i = 1;
            paramArrayList.add(localFragment2);
          }
          j = i;
          break label447;
        }
      }
      paramArrayList.add(a);
      paramFragment = localFragment1;
      j = i;
      label447:
      i = j + 1;
    }
    return localFragment1;
  }
  
  public void a()
  {
    ArrayList localArrayList = A;
    if (localArrayList != null)
    {
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        ((java.lang.Runnable)A.get(i)).run();
        i += 1;
      }
      A = null;
    }
  }
  
  void a(l paramL)
  {
    int i = 0;
    while (i < a.size())
    {
      e localE = (e)a.get(i);
      if (a(localE)) {
        a.a(paramL);
      }
      i += 1;
    }
  }
  
  boolean a(int paramInt)
  {
    int k = a.size();
    int i = 0;
    for (;;)
    {
      int j = 0;
      if (i >= k) {
        break;
      }
      Fragment localFragment = a.get(i)).a;
      if (localFragment != null) {
        j = i;
      }
      if ((j != 0) && (j == paramInt)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  boolean a(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    if (paramInt2 == paramInt1) {
      return false;
    }
    int i2 = a.size();
    int k = -1;
    int j = 0;
    while (j < i2)
    {
      Object localObject = a.get(j)).a;
      int i;
      if (localObject != null) {
        i = i;
      } else {
        i = 0;
      }
      int n = k;
      if (i != 0)
      {
        n = k;
        if (i != k)
        {
          k = i;
          int m = paramInt1;
          for (;;)
          {
            n = k;
            if (m >= paramInt2) {
              break;
            }
            localObject = (BackStackRecord)paramArrayList.get(m);
            int i3 = a.size();
            n = 0;
            while (n < i3)
            {
              Fragment localFragment = a.get(n)).a;
              int i1;
              if (localFragment != null) {
                i1 = i;
              } else {
                i1 = 0;
              }
              if (i1 == i) {
                return true;
              }
              n += 1;
            }
            m += 1;
          }
        }
      }
      j += 1;
      k = n;
    }
    return false;
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment)
  {
    add(paramInt, paramFragment, null, 1);
    return this;
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment, String paramString)
  {
    if (paramInt != 0)
    {
      add(paramInt, paramFragment, paramString, 2);
      return this;
    }
    throw new IllegalArgumentException("Must use non-zero containerViewId");
  }
  
  public FragmentTransaction add(Fragment paramFragment, String paramString)
  {
    add(0, paramFragment, paramString, 1);
    return this;
  }
  
  public void add()
  {
    disallowAddToBackStack();
    mManager.a(this, true);
  }
  
  void addOp(e paramE)
  {
    a.add(paramE);
    exitAnim = popEnterAnim;
    enterAnim = popExitAnim;
    popEnterAnim = mPopEnterAnim;
    popExitAnim = mPopExitAnim;
  }
  
  Fragment b(ArrayList paramArrayList, Fragment paramFragment)
  {
    int i = 0;
    while (i < a.size())
    {
      e localE = (e)a.get(i);
      int j = i;
      if (j != 1)
      {
        if (j != 3) {}
        switch (j)
        {
        default: 
          break;
        case 9: 
          paramFragment = a;
          break;
        case 8: 
          paramFragment = null;
          break;
        case 6: 
          paramArrayList.add(a);
          break;
        }
      }
      else
      {
        paramArrayList.remove(a);
      }
      i += 1;
    }
    return paramFragment;
  }
  
  void bumpBackStackNesting(int paramInt)
  {
    if (!mAddToBackStack) {
      return;
    }
    Object localObject1;
    if (FragmentManagerImpl.DEBUG)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Bump nesting in ");
      ((StringBuilder)localObject1).append(this);
      ((StringBuilder)localObject1).append(" by ");
      ((StringBuilder)localObject1).append(paramInt);
      Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
    }
    int j = a.size();
    int i = 0;
    while (i < j)
    {
      localObject1 = (e)a.get(i);
      Object localObject2 = a;
      if (localObject2 != null)
      {
        mBackStackNesting += paramInt;
        if (FragmentManagerImpl.DEBUG)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Bump nesting of ");
          ((StringBuilder)localObject2).append(a);
          ((StringBuilder)localObject2).append(" to ");
          ((StringBuilder)localObject2).append(a.mBackStackNesting);
          Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
        }
      }
      i += 1;
    }
  }
  
  boolean c()
  {
    int i = 0;
    while (i < a.size())
    {
      if (a((e)a.get(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public int commit()
  {
    return commitInternal(false);
  }
  
  public int commitAllowingStateLoss()
  {
    return commitInternal(true);
  }
  
  int commitInternal(boolean paramBoolean)
  {
    if (!mCommitted)
    {
      if (FragmentManagerImpl.DEBUG)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Commit: ");
        ((StringBuilder)localObject).append(this);
        Log.v("FragmentManager", ((StringBuilder)localObject).toString());
        localObject = new PrintWriter(new LogWriter("FragmentManager"));
        dump("  ", null, (PrintWriter)localObject, null);
        ((PrintWriter)localObject).close();
      }
      mCommitted = true;
      if (mAddToBackStack) {
        mIndex = mManager.allocBackStackIndex(this);
      } else {
        mIndex = -1;
      }
      mManager.enqueueAction(this, paramBoolean);
      return mIndex;
    }
    throw new IllegalStateException("commit already called");
  }
  
  public FragmentTransaction disallowAddToBackStack()
  {
    if (!mAddToBackStack) {
      return this;
    }
    throw new IllegalStateException("This transaction is already being added to the back stack");
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    dump(paramString, paramPrintWriter, true);
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mName=");
      paramPrintWriter.print(mName);
      paramPrintWriter.print(" mIndex=");
      paramPrintWriter.print(mIndex);
      paramPrintWriter.print(" mCommitted=");
      paramPrintWriter.println(mCommitted);
      if (mTransition != 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mTransition=#");
        paramPrintWriter.print(Integer.toHexString(mTransition));
        paramPrintWriter.print(" mTransitionStyle=#");
        paramPrintWriter.println(Integer.toHexString(mTransitionStyle));
      }
      if ((popEnterAnim != 0) || (popExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(popEnterAnim));
        paramPrintWriter.print(" mExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(popExitAnim));
      }
      if ((mPopEnterAnim != 0) || (mPopExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mPopEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(mPopEnterAnim));
        paramPrintWriter.print(" mPopExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(mPopExitAnim));
      }
      if ((mBreadCrumbTitleRes != 0) || (mBreadCrumbTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(mBreadCrumbTitleRes));
        paramPrintWriter.print(" mBreadCrumbTitleText=");
        paramPrintWriter.println(mBreadCrumbTitleText);
      }
      if ((mBreadCrumbShortTitleRes != 0) || (mBreadCrumbShortTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbShortTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
        paramPrintWriter.print(" mBreadCrumbShortTitleText=");
        paramPrintWriter.println(mBreadCrumbShortTitleText);
      }
    }
    if (!a.isEmpty())
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Operations:");
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("    ");
      ((StringBuilder)localObject).toString();
      int j = a.size();
      int i = 0;
      while (i < j)
      {
        e localE = (e)a.get(i);
        switch (i)
        {
        default: 
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("cmd=");
          ((StringBuilder)localObject).append(i);
          localObject = ((StringBuilder)localObject).toString();
          break;
        case 9: 
          localObject = "UNSET_PRIMARY_NAV";
          break;
        case 8: 
          localObject = "SET_PRIMARY_NAV";
          break;
        case 7: 
          localObject = "ATTACH";
          break;
        case 6: 
          localObject = "DETACH";
          break;
        case 5: 
          localObject = "SHOW";
          break;
        case 4: 
          localObject = "HIDE";
          break;
        case 3: 
          localObject = "REMOVE";
          break;
        case 2: 
          localObject = "REPLACE";
          break;
        case 1: 
          localObject = "ADD";
          break;
        case 0: 
          localObject = "NULL";
        }
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  Op #");
        paramPrintWriter.print(i);
        paramPrintWriter.print(": ");
        paramPrintWriter.print((String)localObject);
        paramPrintWriter.print(" ");
        paramPrintWriter.println(a);
        if (paramBoolean)
        {
          if ((exitAnim != 0) || (enterAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("enterAnim=#");
            paramPrintWriter.print(Integer.toHexString(exitAnim));
            paramPrintWriter.print(" exitAnim=#");
            paramPrintWriter.println(Integer.toHexString(enterAnim));
          }
          if ((popEnterAnim != 0) || (popExitAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("popEnterAnim=#");
            paramPrintWriter.print(Integer.toHexString(popEnterAnim));
            paramPrintWriter.print(" popExitAnim=#");
            paramPrintWriter.println(Integer.toHexString(popExitAnim));
          }
        }
        i += 1;
      }
    }
  }
  
  public String getName()
  {
    return mName;
  }
  
  public FragmentTransaction remove(Fragment paramFragment)
  {
    addOp(new e(3, paramFragment));
    return this;
  }
  
  void run()
  {
    Object localObject2 = a;
    Object localObject1 = this;
    int j = ((ArrayList)localObject2).size();
    int i = 0;
    while (i < j)
    {
      localObject2 = a;
      localObject2 = (e)((ArrayList)localObject2).get(i);
      Fragment localFragment = a;
      if (localFragment != null) {
        localFragment.a(mTransition, mTransitionStyle);
      }
      switch (i)
      {
      default: 
        break;
      case 2: 
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Unknown cmd: ");
        ((StringBuilder)localObject1).append(i);
        throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
      case 9: 
        mManager.addFragment(null);
        break;
      case 8: 
        mManager.addFragment(localFragment);
        break;
      case 7: 
        localFragment.a(exitAnim);
        mManager.attachFragment(localFragment);
        break;
      case 6: 
        localFragment.a(enterAnim);
        mManager.detachFragment(localFragment);
        break;
      case 5: 
        localFragment.a(exitAnim);
        mManager.showFragment(localFragment);
        break;
      case 4: 
        localFragment.a(enterAnim);
        mManager.hideFragment(localFragment);
        break;
      case 3: 
        localFragment.a(enterAnim);
        mManager.removeFragment(localFragment);
        break;
      }
      localFragment.a(exitAnim);
      mManager.addFragment(localFragment, false);
      if ((!c) && (i != 1) && (localFragment != null)) {
        mManager.run(localFragment);
      }
      i += 1;
    }
    if (!c)
    {
      localObject1 = mManager;
      ((FragmentManagerImpl)localObject1).moveToState(mCurState, true);
    }
  }
  
  void run(boolean paramBoolean)
  {
    Object localObject2 = a;
    Object localObject1 = this;
    int i = ((ArrayList)localObject2).size() - 1;
    while (i >= 0)
    {
      localObject2 = a;
      localObject2 = (e)((ArrayList)localObject2).get(i);
      Fragment localFragment = a;
      if (localFragment != null) {
        localFragment.a(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
      }
      switch (i)
      {
      default: 
        break;
      case 2: 
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Unknown cmd: ");
        ((StringBuilder)localObject1).append(i);
        throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
      case 9: 
        mManager.addFragment(localFragment);
        break;
      case 8: 
        mManager.addFragment(null);
        break;
      case 7: 
        localFragment.a(popExitAnim);
        mManager.detachFragment(localFragment);
        break;
      case 6: 
        localFragment.a(popEnterAnim);
        mManager.attachFragment(localFragment);
        break;
      case 5: 
        localFragment.a(popExitAnim);
        mManager.hideFragment(localFragment);
        break;
      case 4: 
        localFragment.a(popEnterAnim);
        mManager.showFragment(localFragment);
        break;
      case 3: 
        localFragment.a(popEnterAnim);
        mManager.addFragment(localFragment, false);
        break;
      }
      localFragment.a(popExitAnim);
      mManager.removeFragment(localFragment);
      if ((!c) && (i != 3) && (localFragment != null)) {
        mManager.run(localFragment);
      }
      i -= 1;
    }
    if ((!c) && (paramBoolean))
    {
      localObject1 = mManager;
      ((FragmentManagerImpl)localObject1).moveToState(mCurState, true);
    }
  }
  
  public boolean run(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    if (FragmentManagerImpl.DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Run: ");
      localStringBuilder.append(this);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    paramArrayList1.add(this);
    paramArrayList2.add(Boolean.valueOf(false));
    if (mAddToBackStack) {
      mManager.addBackStackState(this);
    }
    return true;
  }
  
  public FragmentTransaction show(int paramInt, Fragment paramFragment)
  {
    add(paramInt, paramFragment, null);
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("BackStackEntry{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    if (mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(mIndex);
    }
    if (mName != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(mName);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}
