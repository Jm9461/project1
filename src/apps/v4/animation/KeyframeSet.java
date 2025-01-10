package apps.v4.animation;

import android.view.animation.Interpolator;
import b.h.a.g;
import java.util.ArrayList;
import java.util.Arrays;

class KeyframeSet
{
  TypeEvaluator mEvaluator;
  Interpolator mInterpolator;
  ArrayList<g> mKeyframes;
  Keyframe mLastKeyframe;
  int mNumKeyframes;
  
  public KeyframeSet(Keyframe... paramVarArgs)
  {
    mNumKeyframes = paramVarArgs.length;
    mKeyframes = new ArrayList();
    mKeyframes.addAll(Arrays.asList(paramVarArgs));
    paramVarArgs = (Keyframe)mKeyframes.get(0);
    mLastKeyframe = ((Keyframe)mKeyframes.get(mNumKeyframes - 1));
    mInterpolator = mLastKeyframe.getInterpolator();
  }
  
  public static KeyframeSet ofFloat(float... paramVarArgs)
  {
    int j = paramVarArgs.length;
    Keyframe.FloatKeyframe[] arrayOfFloatKeyframe = new Keyframe.FloatKeyframe[Math.max(j, 2)];
    if (j == 1)
    {
      arrayOfFloatKeyframe[0] = ((Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F));
      arrayOfFloatKeyframe[1] = ((Keyframe.FloatKeyframe)Keyframe.ofFloat(1.0F, paramVarArgs[0]));
    }
    else
    {
      arrayOfFloatKeyframe[0] = ((Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F, paramVarArgs[0]));
      int i = 1;
      while (i < j)
      {
        arrayOfFloatKeyframe[i] = ((Keyframe.FloatKeyframe)Keyframe.ofFloat(i / (j - 1), paramVarArgs[i]));
        i += 1;
      }
    }
    return new FloatKeyframeSet(arrayOfFloatKeyframe);
  }
  
  public KeyframeSet clone()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public Object getValue(float paramFloat)
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    mEvaluator = paramTypeEvaluator;
  }
  
  public String toString()
  {
    String str = " ";
    int i = 0;
    while (i < mNumKeyframes)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(((Keyframe)mKeyframes.get(i)).getValue());
      localStringBuilder.append("  ");
      str = localStringBuilder.toString();
      i += 1;
    }
    return str;
  }
}
