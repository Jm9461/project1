package android.support.v7.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultItemAnimator
  extends RecyclerView.ItemAnimator
{
  private static TimeInterpolator mDefaultInterpolator;
  ArrayList<RecyclerView.d0> mAddAnimations = new ArrayList();
  ArrayList<ArrayList<RecyclerView.d0>> mAdditionsList = new ArrayList();
  ArrayList<RecyclerView.d0> mChangeAnimations = new ArrayList();
  ArrayList<ArrayList<g0.i>> mChangesList = new ArrayList();
  ArrayList<RecyclerView.d0> mMoveAnimations = new ArrayList();
  ArrayList<ArrayList<g0.j>> mMovesList = new ArrayList();
  private ArrayList<RecyclerView.d0> mPendingAdditions = new ArrayList();
  private ArrayList<g0.i> mPendingChanges = new ArrayList();
  private ArrayList<g0.j> mPendingMoves = new ArrayList();
  private ArrayList<RecyclerView.d0> mPendingRemovals = new ArrayList();
  ArrayList<RecyclerView.d0> mRemoveAnimations = new ArrayList();
  
  public DefaultItemAnimator() {}
  
  private void a(List paramList, RecyclerView.d0 paramD0)
  {
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      g0.i localI = (g0.i)paramList.get(i);
      if ((a(localI, paramD0)) && (p == null) && (a == null)) {
        paramList.remove(localI);
      }
      i -= 1;
    }
  }
  
  private boolean a(g0.i paramI, RecyclerView.d0 paramD0)
  {
    boolean bool = false;
    if (a == paramD0)
    {
      a = null;
    }
    else
    {
      if (p != paramD0) {
        break label65;
      }
      p = null;
      bool = true;
    }
    itemView.setAlpha(1.0F);
    itemView.setTranslationX(0.0F);
    itemView.setTranslationY(0.0F);
    setVisible(paramD0, bool);
    return true;
    label65:
    return false;
  }
  
  private void animateRemoveImpl(RecyclerView.d0 paramD0)
  {
    View localView = itemView;
    ViewPropertyAnimator localViewPropertyAnimator = localView.animate();
    mRemoveAnimations.add(paramD0);
    localViewPropertyAnimator.setDuration(getAnimationDuration()).alpha(0.0F).setListener(new g0.d(this, paramD0, localViewPropertyAnimator, localView)).start();
  }
  
  private void endChangeAnimationIfNecessary(g0.i paramI)
  {
    RecyclerView.d0 localD0 = p;
    if (localD0 != null) {
      a(paramI, localD0);
    }
    localD0 = a;
    if (localD0 != null) {
      a(paramI, localD0);
    }
  }
  
  private void resetAnimation(RecyclerView.d0 paramD0)
  {
    if (mDefaultInterpolator == null) {
      mDefaultInterpolator = new ValueAnimator().getInterpolator();
    }
    itemView.animate().setInterpolator(mDefaultInterpolator);
    endAnimation(paramD0);
  }
  
  public boolean animateAdd(RecyclerView.d0 paramD0)
  {
    resetAnimation(paramD0);
    itemView.setAlpha(0.0F);
    mPendingAdditions.add(paramD0);
    return true;
  }
  
  public boolean animateChange(RecyclerView.d0 paramD01, RecyclerView.d0 paramD02, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramD01 == paramD02) {
      return animateMove(paramD01, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    float f1 = itemView.getTranslationX();
    float f2 = itemView.getTranslationY();
    float f3 = itemView.getAlpha();
    resetAnimation(paramD01);
    int i = (int)(paramInt3 - paramInt1 - f1);
    int j = (int)(paramInt4 - paramInt2 - f2);
    itemView.setTranslationX(f1);
    itemView.setTranslationY(f2);
    itemView.setAlpha(f3);
    if (paramD02 != null)
    {
      resetAnimation(paramD02);
      itemView.setTranslationX(-i);
      itemView.setTranslationY(-j);
      itemView.setAlpha(0.0F);
    }
    mPendingChanges.add(new g0.i(paramD01, paramD02, paramInt1, paramInt2, paramInt3, paramInt4));
    return true;
  }
  
  void animateChangeImpl(g0.i paramI)
  {
    Object localObject1 = p;
    View localView = null;
    if (localObject1 == null) {
      localObject1 = null;
    } else {
      localObject1 = itemView;
    }
    Object localObject2 = a;
    if (localObject2 != null) {
      localView = itemView;
    }
    if (localObject1 != null)
    {
      localObject2 = ((View)localObject1).animate().setDuration(getChangeDuration());
      mChangeAnimations.add(p);
      ((ViewPropertyAnimator)localObject2).translationX(toX - fromX);
      ((ViewPropertyAnimator)localObject2).translationY(toY - fromY);
      ((ViewPropertyAnimator)localObject2).alpha(0.0F).setListener(new g0.g(this, paramI, (ViewPropertyAnimator)localObject2, (View)localObject1)).start();
    }
    if (localView != null)
    {
      localObject1 = localView.animate();
      mChangeAnimations.add(a);
      ((ViewPropertyAnimator)localObject1).translationX(0.0F).translationY(0.0F).setDuration(getChangeDuration()).alpha(1.0F).setListener(new g0.h(this, paramI, (ViewPropertyAnimator)localObject1, localView)).start();
    }
  }
  
  public boolean animateMove(RecyclerView.d0 paramD0, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = itemView;
    paramInt1 += (int)itemView.getTranslationX();
    paramInt2 += (int)itemView.getTranslationY();
    resetAnimation(paramD0);
    int i = paramInt3 - paramInt1;
    int j = paramInt4 - paramInt2;
    if ((i == 0) && (j == 0))
    {
      dispatchMoveFinished(paramD0);
      return false;
    }
    if (i != 0) {
      localView.setTranslationX(-i);
    }
    if (j != 0) {
      localView.setTranslationY(-j);
    }
    mPendingMoves.add(new g0.j(paramD0, paramInt1, paramInt2, paramInt3, paramInt4));
    return true;
  }
  
  void animateMoveImpl(RecyclerView.d0 paramD0, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = itemView;
    paramInt1 = paramInt3 - paramInt1;
    paramInt2 = paramInt4 - paramInt2;
    if (paramInt1 != 0) {
      localView.animate().translationX(0.0F);
    }
    if (paramInt2 != 0) {
      localView.animate().translationY(0.0F);
    }
    ViewPropertyAnimator localViewPropertyAnimator = localView.animate();
    mMoveAnimations.add(paramD0);
    localViewPropertyAnimator.setDuration(getMoveDuration()).setListener(new g0.f(this, paramD0, paramInt1, localView, paramInt2, localViewPropertyAnimator)).start();
  }
  
  public boolean animateRemove(RecyclerView.d0 paramD0)
  {
    resetAnimation(paramD0);
    mPendingRemovals.add(paramD0);
    return true;
  }
  
  public boolean canReuseUpdatedViewHolder(RecyclerView.d0 paramD0, List paramList)
  {
    return (!paramList.isEmpty()) || (super.canReuseUpdatedViewHolder(paramD0, paramList));
  }
  
  void cancelAll(List paramList)
  {
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      getitemView.animate().cancel();
      i -= 1;
    }
  }
  
  void close(RecyclerView.d0 paramD0)
  {
    View localView = itemView;
    ViewPropertyAnimator localViewPropertyAnimator = localView.animate();
    mAddAnimations.add(paramD0);
    localViewPropertyAnimator.alpha(1.0F).setDuration(getRemoveDuration()).setListener(new g0.e(this, paramD0, localView, localViewPropertyAnimator)).start();
  }
  
  void dispatchFinishedWhenDone()
  {
    if (!isRunning()) {
      clear();
    }
  }
  
  public void endAnimation(RecyclerView.d0 paramD0)
  {
    View localView = itemView;
    localView.animate().cancel();
    int i = mPendingMoves.size() - 1;
    while (i >= 0)
    {
      if (mPendingMoves.get(i)).holder == paramD0)
      {
        localView.setTranslationY(0.0F);
        localView.setTranslationX(0.0F);
        dispatchMoveFinished(paramD0);
        mPendingMoves.remove(i);
      }
      i -= 1;
    }
    a(mPendingChanges, paramD0);
    if (mPendingRemovals.remove(paramD0))
    {
      localView.setAlpha(1.0F);
      dispatchRemoveFinished(paramD0);
    }
    if (mPendingAdditions.remove(paramD0))
    {
      localView.setAlpha(1.0F);
      dispatchAddFinished(paramD0);
    }
    i = mChangesList.size() - 1;
    ArrayList localArrayList;
    while (i >= 0)
    {
      localArrayList = (ArrayList)mChangesList.get(i);
      a(localArrayList, paramD0);
      if (localArrayList.isEmpty()) {
        mChangesList.remove(i);
      }
      i -= 1;
    }
    i = mMovesList.size() - 1;
    while (i >= 0)
    {
      localArrayList = (ArrayList)mMovesList.get(i);
      int j = localArrayList.size() - 1;
      while (j >= 0)
      {
        if (getholder == paramD0)
        {
          localView.setTranslationY(0.0F);
          localView.setTranslationX(0.0F);
          dispatchMoveFinished(paramD0);
          localArrayList.remove(j);
          if (!localArrayList.isEmpty()) {
            break;
          }
          mMovesList.remove(i);
          break;
        }
        j -= 1;
      }
      i -= 1;
    }
    i = mAdditionsList.size() - 1;
    while (i >= 0)
    {
      localArrayList = (ArrayList)mAdditionsList.get(i);
      if (localArrayList.remove(paramD0))
      {
        localView.setAlpha(1.0F);
        dispatchAddFinished(paramD0);
        if (localArrayList.isEmpty()) {
          mAdditionsList.remove(i);
        }
      }
      i -= 1;
    }
    mRemoveAnimations.remove(paramD0);
    mAddAnimations.remove(paramD0);
    mChangeAnimations.remove(paramD0);
    mMoveAnimations.remove(paramD0);
    dispatchFinishedWhenDone();
  }
  
  public void endAnimations()
  {
    Object localObject1 = mPendingMoves;
    DefaultItemAnimator localDefaultItemAnimator = this;
    int i = ((ArrayList)localObject1).size() - 1;
    Object localObject2;
    while (i >= 0)
    {
      localObject1 = (g0.j)mPendingMoves.get(i);
      localObject2 = holder.itemView;
      ((View)localObject2).setTranslationY(0.0F);
      ((View)localObject2).setTranslationX(0.0F);
      localDefaultItemAnimator.dispatchMoveFinished(holder);
      localObject1 = mPendingMoves;
      ((ArrayList)localObject1).remove(i);
      i -= 1;
    }
    localObject1 = mPendingRemovals;
    i = ((ArrayList)localObject1).size() - 1;
    while (i >= 0)
    {
      localDefaultItemAnimator.dispatchRemoveFinished((RecyclerView.d0)mPendingRemovals.get(i));
      localObject1 = mPendingRemovals;
      ((ArrayList)localObject1).remove(i);
      i -= 1;
    }
    localObject1 = mPendingAdditions;
    i = ((ArrayList)localObject1).size() - 1;
    while (i >= 0)
    {
      localObject1 = (RecyclerView.d0)mPendingAdditions.get(i);
      itemView.setAlpha(1.0F);
      localDefaultItemAnimator.dispatchAddFinished((RecyclerView.d0)localObject1);
      localObject1 = mPendingAdditions;
      ((ArrayList)localObject1).remove(i);
      i -= 1;
    }
    localObject1 = mPendingChanges;
    i = ((ArrayList)localObject1).size() - 1;
    while (i >= 0)
    {
      localObject1 = mPendingChanges;
      localDefaultItemAnimator.endChangeAnimationIfNecessary((g0.i)((ArrayList)localObject1).get(i));
      i -= 1;
    }
    mPendingChanges.clear();
    if (!localDefaultItemAnimator.isRunning()) {
      return;
    }
    localObject1 = mMovesList;
    i = ((ArrayList)localObject1).size() - 1;
    int j;
    while (i >= 0)
    {
      localObject1 = mMovesList;
      localObject1 = (ArrayList)((ArrayList)localObject1).get(i);
      j = ((ArrayList)localObject1).size() - 1;
      while (j >= 0)
      {
        localObject2 = (g0.j)((ArrayList)localObject1).get(j);
        View localView = holder.itemView;
        localView.setTranslationY(0.0F);
        localView.setTranslationX(0.0F);
        localDefaultItemAnimator.dispatchMoveFinished(holder);
        ((ArrayList)localObject1).remove(j);
        if (((ArrayList)localObject1).isEmpty()) {
          mMovesList.remove(localObject1);
        }
        j -= 1;
      }
      i -= 1;
    }
    localObject1 = mAdditionsList;
    i = ((ArrayList)localObject1).size() - 1;
    while (i >= 0)
    {
      localObject1 = mAdditionsList;
      localObject1 = (ArrayList)((ArrayList)localObject1).get(i);
      j = ((ArrayList)localObject1).size() - 1;
      while (j >= 0)
      {
        localObject2 = (RecyclerView.d0)((ArrayList)localObject1).get(j);
        itemView.setAlpha(1.0F);
        localDefaultItemAnimator.dispatchAddFinished((RecyclerView.d0)localObject2);
        ((ArrayList)localObject1).remove(j);
        if (((ArrayList)localObject1).isEmpty()) {
          mAdditionsList.remove(localObject1);
        }
        j -= 1;
      }
      i -= 1;
    }
    localObject1 = mChangesList;
    i = ((ArrayList)localObject1).size() - 1;
    while (i >= 0)
    {
      localObject1 = mChangesList;
      localObject1 = (ArrayList)((ArrayList)localObject1).get(i);
      j = ((ArrayList)localObject1).size() - 1;
      while (j >= 0)
      {
        localDefaultItemAnimator.endChangeAnimationIfNecessary((g0.i)((ArrayList)localObject1).get(j));
        if (((ArrayList)localObject1).isEmpty()) {
          mChangesList.remove(localObject1);
        }
        j -= 1;
      }
      i -= 1;
    }
    localDefaultItemAnimator.cancelAll(mRemoveAnimations);
    localDefaultItemAnimator.cancelAll(mMoveAnimations);
    localDefaultItemAnimator.cancelAll(mAddAnimations);
    localDefaultItemAnimator.cancelAll(mChangeAnimations);
    localDefaultItemAnimator.clear();
  }
  
  public boolean isRunning()
  {
    return (!mPendingAdditions.isEmpty()) || (!mPendingChanges.isEmpty()) || (!mPendingMoves.isEmpty()) || (!mPendingRemovals.isEmpty()) || (!mMoveAnimations.isEmpty()) || (!mRemoveAnimations.isEmpty()) || (!mAddAnimations.isEmpty()) || (!mChangeAnimations.isEmpty()) || (!mMovesList.isEmpty()) || (!mAdditionsList.isEmpty()) || (!mChangesList.isEmpty());
  }
  
  public void runPendingAnimations()
  {
    boolean bool1 = mPendingRemovals.isEmpty() ^ true;
    boolean bool2 = mPendingMoves.isEmpty() ^ true;
    boolean bool3 = mPendingChanges.isEmpty() ^ true;
    boolean bool4 = mPendingAdditions.isEmpty() ^ true;
    if ((!bool1) && (!bool2) && (!bool4) && (!bool3)) {
      return;
    }
    Object localObject1 = mPendingRemovals.iterator();
    while (((Iterator)localObject1).hasNext()) {
      animateRemoveImpl((RecyclerView.d0)((Iterator)localObject1).next());
    }
    mPendingRemovals.clear();
    Object localObject2;
    if (bool2)
    {
      localObject1 = new ArrayList();
      ((ArrayList)localObject1).addAll(mPendingMoves);
      mMovesList.add(localObject1);
      mPendingMoves.clear();
      localObject2 = new g0.a(this, (ArrayList)localObject1);
      if (bool1) {
        ViewCompat.add(get0holder.itemView, (Runnable)localObject2, getAnimationDuration());
      } else {
        ((Runnable)localObject2).run();
      }
    }
    if (bool3)
    {
      localObject1 = new ArrayList();
      ((ArrayList)localObject1).addAll(mPendingChanges);
      mChangesList.add(localObject1);
      mPendingChanges.clear();
      localObject2 = new g0.b(this, (ArrayList)localObject1);
      if (bool1) {
        ViewCompat.add(get0p.itemView, (Runnable)localObject2, getAnimationDuration());
      } else {
        ((Runnable)localObject2).run();
      }
    }
    if (bool4)
    {
      localObject1 = new ArrayList();
      ((ArrayList)localObject1).addAll(mPendingAdditions);
      mAdditionsList.add(localObject1);
      mPendingAdditions.clear();
      localObject2 = new g0.c(this, (ArrayList)localObject1);
      if ((!bool1) && (!bool2) && (!bool3))
      {
        ((Runnable)localObject2).run();
        return;
      }
      long l3 = 0L;
      long l1;
      if (bool1) {
        l1 = getAnimationDuration();
      } else {
        l1 = 0L;
      }
      if (bool2) {
        l2 = getMoveDuration();
      } else {
        l2 = 0L;
      }
      if (bool3) {
        l3 = getChangeDuration();
      }
      long l2 = Math.max(l2, l3);
      ViewCompat.add(get0itemView, (Runnable)localObject2, l2 + l1);
    }
  }
}
