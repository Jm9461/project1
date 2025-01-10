package android.support.v7.widget;

import a.b.g.g.a;
import org.org.jaxen.util.Label;
import org.org.jaxen.util.LongSparseArray;

class ViewInfoStore
{
  final a<RecyclerView.d0, q1.a> mLayoutHolderMap = new Label();
  final a.b.g.g.f<RecyclerView.d0> mOldChangedHolders = new LongSparseArray();
  
  ViewInfoStore() {}
  
  private RecyclerView.l.c a(RecyclerView.d0 paramD0, int paramInt)
  {
    int i = mLayoutHolderMap.add(paramD0);
    if (i < 0) {
      return null;
    }
    q1.a localA = (q1.a)mLayoutHolderMap.get(i);
    if (localA != null)
    {
      int j = flags;
      if ((j & paramInt) != 0)
      {
        flags = (paramInt & j);
        if (paramInt == 4)
        {
          paramD0 = h;
        }
        else
        {
          if (paramInt != 8) {
            break label109;
          }
          paramD0 = g;
        }
        if ((flags & 0xC) != 0) {
          return paramD0;
        }
        mLayoutHolderMap.d(i);
        q1.a.a(localA);
        return paramD0;
        label109:
        throw new IllegalArgumentException("Must provide flag PRE or POST");
      }
    }
    return null;
    return paramD0;
  }
  
  void a(q1.b paramB)
  {
    int i = mLayoutHolderMap.size() - 1;
    while (i >= 0)
    {
      RecyclerView.d0 localD0 = (RecyclerView.d0)mLayoutHolderMap.getValue(i);
      q1.a localA = (q1.a)mLayoutHolderMap.d(i);
      int j = flags;
      if ((j & 0x3) == 3)
      {
        paramB.run(localD0);
      }
      else if ((j & 0x1) != 0)
      {
        RecyclerView.l.c localC = h;
        if (localC == null) {
          paramB.run(localD0);
        } else {
          paramB.drawRect(localD0, localC, g);
        }
      }
      else if ((j & 0xE) == 14)
      {
        paramB.a(localD0, h, g);
      }
      else if ((j & 0xC) == 12)
      {
        paramB.animateChange(localD0, h, g);
      }
      else if ((j & 0x4) != 0)
      {
        paramB.drawRect(localD0, h, null);
      }
      else if ((j & 0x8) != 0)
      {
        paramB.a(localD0, h, g);
      }
      q1.a.a(localA);
      i -= 1;
    }
  }
  
  void addToAppearedInPreLayoutHolders(RecyclerView.d0 paramD0, RecyclerView.l.c paramC)
  {
    q1.a localA2 = (q1.a)mLayoutHolderMap.get(paramD0);
    q1.a localA1 = localA2;
    if (localA2 == null)
    {
      localA2 = q1.a.obtain();
      localA1 = localA2;
      mLayoutHolderMap.put(paramD0, localA2);
    }
    flags |= 0x2;
    h = paramC;
  }
  
  void addToDisappearedInLayout(RecyclerView.d0 paramD0)
  {
    q1.a localA2 = (q1.a)mLayoutHolderMap.get(paramD0);
    q1.a localA1 = localA2;
    if (localA2 == null)
    {
      localA2 = q1.a.obtain();
      localA1 = localA2;
      mLayoutHolderMap.put(paramD0, localA2);
    }
    flags |= 0x1;
  }
  
  void addToOldChangeHolders(long paramLong, RecyclerView.d0 paramD0)
  {
    mOldChangedHolders.put(paramLong, paramD0);
  }
  
  void addToPostLayout(RecyclerView.d0 paramD0, RecyclerView.l.c paramC)
  {
    q1.a localA2 = (q1.a)mLayoutHolderMap.get(paramD0);
    q1.a localA1 = localA2;
    if (localA2 == null)
    {
      localA2 = q1.a.obtain();
      localA1 = localA2;
      mLayoutHolderMap.put(paramD0, localA2);
    }
    g = paramC;
    flags |= 0x8;
  }
  
  void addToPreLayout(RecyclerView.d0 paramD0, RecyclerView.l.c paramC)
  {
    q1.a localA2 = (q1.a)mLayoutHolderMap.get(paramD0);
    q1.a localA1 = localA2;
    if (localA2 == null)
    {
      localA2 = q1.a.obtain();
      localA1 = localA2;
      mLayoutHolderMap.put(paramD0, localA2);
    }
    h = paramC;
    flags |= 0x4;
  }
  
  void clear()
  {
    mLayoutHolderMap.clear();
    mOldChangedHolders.clear();
  }
  
  void clear(RecyclerView.d0 paramD0)
  {
    int i = mOldChangedHolders.size() - 1;
    while (i >= 0)
    {
      if (paramD0 == mOldChangedHolders.valueAt(i))
      {
        mOldChangedHolders.removeAt(i);
        break;
      }
      i -= 1;
    }
    paramD0 = (q1.a)mLayoutHolderMap.remove(paramD0);
    if (paramD0 != null) {
      q1.a.a(paramD0);
    }
  }
  
  RecyclerView.d0 getFromOldChangeHolders(long paramLong)
  {
    return (RecyclerView.d0)mOldChangedHolders.get(paramLong);
  }
  
  boolean isDisappearing(RecyclerView.d0 paramD0)
  {
    paramD0 = (q1.a)mLayoutHolderMap.get(paramD0);
    return (paramD0 != null) && ((flags & 0x1) != 0);
  }
  
  boolean isInPreLayout(RecyclerView.d0 paramD0)
  {
    paramD0 = (q1.a)mLayoutHolderMap.get(paramD0);
    return (paramD0 != null) && ((flags & 0x4) != 0);
  }
  
  void onDetach() {}
  
  public void onViewDetached(RecyclerView.d0 paramD0)
  {
    removeFromDisappearedInLayout(paramD0);
  }
  
  RecyclerView.l.c popFromPostLayout(RecyclerView.d0 paramD0)
  {
    return a(paramD0, 8);
  }
  
  RecyclerView.l.c popFromPreLayout(RecyclerView.d0 paramD0)
  {
    return a(paramD0, 4);
  }
  
  void removeFromDisappearedInLayout(RecyclerView.d0 paramD0)
  {
    paramD0 = (q1.a)mLayoutHolderMap.get(paramD0);
    if (paramD0 == null) {
      return;
    }
    flags &= 0xFFFFFFFE;
  }
}
