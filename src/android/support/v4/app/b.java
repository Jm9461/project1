package android.support.v4.app;

import java.util.ArrayList;

class b
  implements l
{
  private int a;
  final boolean b;
  final BackStackRecord c;
  
  b(BackStackRecord paramBackStackRecord, boolean paramBoolean)
  {
    b = paramBoolean;
    c = paramBackStackRecord;
  }
  
  public void a()
  {
    a -= 1;
    if (a != 0) {
      return;
    }
    c.mManager.enqueueAction();
  }
  
  public void b()
  {
    a += 1;
  }
  
  public boolean c()
  {
    return a == 0;
  }
  
  public void run()
  {
    int i = a;
    boolean bool1 = false;
    if (i > 0) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject1 = c.mManager;
    int k = mAdded.size();
    int j = 0;
    while (j < k)
    {
      localObject2 = (Fragment)mAdded.get(j);
      ((Fragment)localObject2).a(null);
      if ((i != 0) && (((Fragment)localObject2).isVisible())) {
        ((Fragment)localObject2).execPendingActions();
      }
      j += 1;
    }
    localObject1 = c;
    Object localObject2 = mManager;
    boolean bool2 = b;
    if (i == 0) {
      bool1 = true;
    }
    ((FragmentManagerImpl)localObject2).run((BackStackRecord)localObject1, bool2, bool1, true);
  }
  
  public void write()
  {
    BackStackRecord localBackStackRecord = c;
    mManager.run(localBackStackRecord, b, false, false);
  }
}
