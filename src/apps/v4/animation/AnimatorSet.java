package apps.v4.animation;

import android.view.animation.Interpolator;
import b.h.a.a;
import b.h.a.c.d;
import b.h.a.c.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class AnimatorSet
  extends Animator
{
  private ValueAnimator mDelayAnim = null;
  private boolean mNeedsSort = true;
  private HashMap<a, c.f> mNodeMap = new HashMap();
  private ArrayList<c.f> mNodes = new ArrayList();
  private ArrayList<a> mPlayingSet = new ArrayList();
  private AnimatorSetListener mSetListener = null;
  private ArrayList<c.f> mSortedNodes = new ArrayList();
  private long mStartDelay = 0L;
  private boolean mStarted;
  boolean mTerminated = false;
  
  public AnimatorSet() {}
  
  private void sortNodes()
  {
    Object localObject1;
    int j;
    Object localObject2;
    int m;
    if (mNeedsSort)
    {
      mSortedNodes.clear();
      localObject1 = new ArrayList();
      j = mNodes.size();
      i = 0;
      Object localObject3;
      while (i < j)
      {
        localObject2 = (Node)mNodes.get(i);
        localObject3 = dependencies;
        if ((localObject3 == null) || (((ArrayList)localObject3).size() == 0)) {
          ((ArrayList)localObject1).add(localObject2);
        }
        i += 1;
      }
      localObject2 = new ArrayList();
      while (((ArrayList)localObject1).size() > 0)
      {
        k = ((ArrayList)localObject1).size();
        i = 0;
        while (i < k)
        {
          localObject3 = (Node)((ArrayList)localObject1).get(i);
          mSortedNodes.add(localObject3);
          Object localObject4 = nodeDependents;
          if (localObject4 != null)
          {
            m = ((ArrayList)localObject4).size();
            j = 0;
            while (j < m)
            {
              localObject4 = (Node)nodeDependents.get(j);
              nodeDependencies.remove(localObject3);
              if (nodeDependencies.size() == 0) {
                ((ArrayList)localObject2).add(localObject4);
              }
              j += 1;
            }
          }
          i += 1;
        }
        ((ArrayList)localObject1).clear();
        ((ArrayList)localObject1).addAll((Collection)localObject2);
        ((ArrayList)localObject2).clear();
      }
      mNeedsSort = false;
      if (mSortedNodes.size() == mNodes.size()) {
        return;
      }
      throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
    }
    int k = mNodes.size();
    int i = 0;
    while (i < k)
    {
      localObject1 = (Node)mNodes.get(i);
      localObject2 = dependencies;
      if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0))
      {
        m = dependencies.size();
        j = 0;
        while (j < m)
        {
          localObject2 = (Dependency)dependencies.get(j);
          if (nodeDependencies == null) {
            nodeDependencies = new ArrayList();
          }
          if (!nodeDependencies.contains(node)) {
            nodeDependencies.add(node);
          }
          j += 1;
        }
      }
      done = false;
      i += 1;
    }
  }
  
  public AnimatorSet clone()
  {
    AnimatorSet localAnimatorSet = (AnimatorSet)super.clone();
    mNeedsSort = true;
    mTerminated = false;
    mStarted = false;
    mPlayingSet = new ArrayList();
    mNodeMap = new HashMap();
    mNodes = new ArrayList();
    mSortedNodes = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject3 = mNodes.iterator();
    Object localObject2;
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject1 = (Node)((Iterator)localObject3).next();
      localObject2 = ((Node)localObject1).clone();
      localHashMap.put(localObject1, localObject2);
      mNodes.add(localObject2);
      mNodeMap.put(animation, localObject2);
      dependencies = null;
      tmpDependencies = null;
      nodeDependents = null;
      nodeDependencies = null;
      localObject4 = animation.getListeners();
      if (localObject4 != null)
      {
        localObject1 = null;
        Iterator localIterator = ((ArrayList)localObject4).iterator();
        while (localIterator.hasNext())
        {
          Animator.AnimatorListener localAnimatorListener = (Animator.AnimatorListener)localIterator.next();
          if ((localAnimatorListener instanceof AnimatorSetListener))
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localAnimatorListener);
            localObject1 = localObject2;
          }
        }
        if (localObject1 != null)
        {
          localObject1 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject1).hasNext()) {
            ((ArrayList)localObject4).remove((Animator.AnimatorListener)((Iterator)localObject1).next());
          }
        }
      }
    }
    Object localObject1 = mNodes.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Node)((Iterator)localObject1).next();
      localObject2 = (Node)localHashMap.get(localObject3);
      localObject3 = dependencies;
      if (localObject3 != null)
      {
        localObject3 = ((ArrayList)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (Dependency)((Iterator)localObject3).next();
          ((Node)localObject2).addDependency(new Dependency((Node)localHashMap.get(node), rule));
        }
      }
    }
    return localAnimatorSet;
  }
  
  public Builder play(Animator paramAnimator)
  {
    if (paramAnimator != null)
    {
      mNeedsSort = true;
      return new Builder(paramAnimator);
    }
    return null;
  }
  
  public void playTogether(Animator... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      mNeedsSort = true;
      Builder localBuilder = play(paramVarArgs[0]);
      int i = 1;
      while (i < paramVarArgs.length)
      {
        localBuilder.with(paramVarArgs[i]);
        i += 1;
      }
    }
  }
  
  public AnimatorSet setDuration(long paramLong)
  {
    if (paramLong >= 0L)
    {
      localObject = mNodes.iterator();
      while (((Iterator)localObject).hasNext()) {
        nextanimation.setDuration(paramLong);
      }
      return this;
    }
    Object localObject = new IllegalArgumentException("duration must be a value of zero or greater");
    throw ((Throwable)localObject);
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    Iterator localIterator = mNodes.iterator();
    while (localIterator.hasNext()) {
      nextanimation.setInterpolator(paramInterpolator);
    }
  }
  
  public void setStartDelay(long paramLong)
  {
    mStartDelay = paramLong;
  }
  
  public void start()
  {
    mTerminated = false;
    sortNodes();
    int k = mSortedNodes.size();
    int i = 0;
    Object localObject2;
    Object localObject3;
    while (i < k)
    {
      localObject1 = (Node)mSortedNodes.get(i);
      localObject2 = animation.getListeners();
      if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0))
      {
        localObject2 = new ArrayList((Collection)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Animator.AnimatorListener)((Iterator)localObject2).next();
          if (((localObject3 instanceof DependencyListener)) || ((localObject3 instanceof AnimatorSetListener))) {
            animation.removeListener((Animator.AnimatorListener)localObject3);
          }
        }
      }
      i += 1;
    }
    Object localObject1 = new ArrayList();
    i = 0;
    int j;
    while (i < k)
    {
      localObject2 = (Node)mSortedNodes.get(i);
      if (mSetListener == null) {
        mSetListener = new AnimatorSetListener(this);
      }
      localObject3 = dependencies;
      if ((localObject3 != null) && (((ArrayList)localObject3).size() != 0))
      {
        int m = dependencies.size();
        j = 0;
        while (j < m)
        {
          localObject3 = (Dependency)dependencies.get(j);
          node.animation.addListener(new DependencyListener((Node)localObject2, rule));
          j += 1;
        }
        tmpDependencies = ((ArrayList)dependencies.clone());
      }
      else
      {
        ((ArrayList)localObject1).add(localObject2);
      }
      animation.addListener(mSetListener);
      i += 1;
    }
    if (mStartDelay <= 0L)
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Node)((Iterator)localObject1).next();
        animation.start();
        mPlayingSet.add(animation);
      }
    }
    else
    {
      mDelayAnim = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      mDelayAnim.setDuration(mStartDelay);
      mDelayAnim.addListener(new AnimatorSet.1(this, (ArrayList)localObject1));
      mDelayAnim.start();
    }
    localObject1 = mListeners;
    if (localObject1 != null)
    {
      localObject1 = (ArrayList)((ArrayList)localObject1).clone();
      j = ((ArrayList)localObject1).size();
      i = 0;
      while (i < j)
      {
        ((Animator.AnimatorListener)((ArrayList)localObject1).get(i)).onAnimationStart(this);
        i += 1;
      }
    }
    if ((mNodes.size() == 0) && (mStartDelay == 0L))
    {
      localObject1 = mListeners;
      if (localObject1 != null)
      {
        localObject1 = (ArrayList)((ArrayList)localObject1).clone();
        j = ((ArrayList)localObject1).size();
        i = 0;
        while (i < j)
        {
          ((Animator.AnimatorListener)((ArrayList)localObject1).get(i)).onAnimationEnd(this);
          i += 1;
        }
      }
    }
  }
  
  class AnimatorSetListener
    implements Animator.AnimatorListener
  {
    private AnimatorSet mAnimatorSet;
    
    AnimatorSetListener(AnimatorSet paramAnimatorSet)
    {
      mAnimatorSet = paramAnimatorSet;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      paramAnimator.removeListener(this);
      AnimatorSet.access$getMPlayingSet(AnimatorSet.this).remove(paramAnimator);
      access$getMNodeMapmAnimatorSet).get(paramAnimator)).done = true;
      if (!mTerminated)
      {
        paramAnimator = AnimatorSet.access$getMSortedNodes(mAnimatorSet);
        int k = 1;
        int m = paramAnimator.size();
        int i = 0;
        int j;
        for (;;)
        {
          j = k;
          if (i >= m) {
            break;
          }
          if (!getdone)
          {
            j = 0;
            break;
          }
          i += 1;
        }
        if (j != 0)
        {
          paramAnimator = mListeners;
          if (paramAnimator != null)
          {
            paramAnimator = (ArrayList)paramAnimator.clone();
            j = paramAnimator.size();
            i = 0;
            while (i < j)
            {
              ((Animator.AnimatorListener)paramAnimator.get(i)).onAnimationEnd(mAnimatorSet);
              i += 1;
            }
          }
          AnimatorSet.access$setMStarted(mAnimatorSet, false);
        }
      }
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator) {}
  }
  
  public class Builder
  {
    private AnimatorSet.Node mCurrentNode;
    
    Builder(Animator paramAnimator)
    {
      mCurrentNode = ((AnimatorSet.Node)AnimatorSet.access$getMNodeMap(AnimatorSet.this).get(paramAnimator));
      if (mCurrentNode == null)
      {
        mCurrentNode = new AnimatorSet.Node(paramAnimator);
        AnimatorSet.access$getMNodeMap(AnimatorSet.this).put(paramAnimator, mCurrentNode);
        AnimatorSet.access$getMNodes(AnimatorSet.this).add(mCurrentNode);
      }
    }
    
    public Builder with(Animator paramAnimator)
    {
      AnimatorSet.Node localNode2 = (AnimatorSet.Node)AnimatorSet.access$getMNodeMap(AnimatorSet.this).get(paramAnimator);
      AnimatorSet.Node localNode1 = localNode2;
      if (localNode2 == null)
      {
        localNode2 = new AnimatorSet.Node(paramAnimator);
        localNode1 = localNode2;
        AnimatorSet.access$getMNodeMap(AnimatorSet.this).put(paramAnimator, localNode2);
        AnimatorSet.access$getMNodes(AnimatorSet.this).add(localNode2);
      }
      localNode1.addDependency(new AnimatorSet.Dependency(mCurrentNode, 0));
      return this;
    }
  }
  
  class Dependency
  {
    public int rule;
    
    public Dependency(int paramInt)
    {
      rule = paramInt;
    }
  }
  
  class DependencyListener
    implements Animator.AnimatorListener
  {
    private AnimatorSet.Node mNode;
    private int mRule;
    
    public DependencyListener(AnimatorSet.Node paramNode, int paramInt)
    {
      mNode = paramNode;
      mRule = paramInt;
    }
    
    private void startIfReady(Animator paramAnimator)
    {
      if (mTerminated) {
        return;
      }
      Object localObject2 = null;
      int j = mNode.tmpDependencies.size();
      int i = 0;
      Object localObject1;
      for (;;)
      {
        localObject1 = localObject2;
        if (i >= j) {
          break;
        }
        localObject1 = (AnimatorSet.Dependency)mNode.tmpDependencies.get(i);
        if ((rule == mRule) && (node.animation == paramAnimator))
        {
          paramAnimator.removeListener(this);
          break;
        }
        i += 1;
      }
      mNode.tmpDependencies.remove(localObject1);
      if (mNode.tmpDependencies.size() == 0)
      {
        mNode.animation.start();
        AnimatorSet.access$getMPlayingSet(AnimatorSet.this).add(mNode.animation);
      }
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (mRule == 1) {
        startIfReady(paramAnimator);
      }
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator)
    {
      if (mRule == 0) {
        startIfReady(paramAnimator);
      }
    }
  }
  
  class Node
    implements Cloneable
  {
    public ArrayList<c.d> dependencies = null;
    public boolean done = false;
    public ArrayList<c.f> nodeDependencies = null;
    public ArrayList<c.f> nodeDependents = null;
    public ArrayList<c.d> tmpDependencies = null;
    
    public Node() {}
    
    public void addDependency(AnimatorSet.Dependency paramDependency)
    {
      if (dependencies == null)
      {
        dependencies = new ArrayList();
        nodeDependencies = new ArrayList();
      }
      dependencies.add(paramDependency);
      if (!nodeDependencies.contains(node)) {
        nodeDependencies.add(node);
      }
      paramDependency = node;
      if (nodeDependents == null) {
        nodeDependents = new ArrayList();
      }
      nodeDependents.add(this);
    }
    
    public Node clone()
    {
      try
      {
        Object localObject = super.clone();
        localObject = (Node)localObject;
        Animator localAnimator = AnimatorSet.this;
        localAnimator = localAnimator.clone();
        animation = localAnimator;
        return localObject;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        throw new AssertionError();
      }
    }
  }
}
