package apps.v4.animation;

import android.view.animation.Interpolator;

public abstract class Keyframe
  implements Cloneable
{
  float mFraction;
  boolean mHasValue = false;
  private Interpolator mInterpolator = null;
  
  public Keyframe() {}
  
  public static Keyframe ofFloat(float paramFloat)
  {
    return new FloatKeyframe(paramFloat);
  }
  
  public static Keyframe ofFloat(float paramFloat1, float paramFloat2)
  {
    return new FloatKeyframe(paramFloat1, paramFloat2);
  }
  
  public abstract Keyframe clone();
  
  public float getFraction()
  {
    return mFraction;
  }
  
  public Interpolator getInterpolator()
  {
    return mInterpolator;
  }
  
  public abstract Object getValue();
  
  public boolean hasValue()
  {
    return mHasValue;
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    mInterpolator = paramInterpolator;
  }
  
  public abstract void setValue(Object paramObject);
  
  class FloatKeyframe
    extends Keyframe
  {
    float mValue;
    
    FloatKeyframe()
    {
      mFraction = this$1;
      Class localClass = Float.TYPE;
    }
    
    FloatKeyframe(float paramFloat)
    {
      mFraction = this$1;
      mValue = paramFloat;
      Class localClass = Float.TYPE;
      mHasValue = true;
    }
    
    public FloatKeyframe clone()
    {
      FloatKeyframe localFloatKeyframe = new FloatKeyframe(getFraction(), mValue);
      localFloatKeyframe.setInterpolator(getInterpolator());
      return localFloatKeyframe;
    }
    
    public float getFloatValue()
    {
      return mValue;
    }
    
    public Object getValue()
    {
      return Float.valueOf(mValue);
    }
    
    public void setValue(Object paramObject)
    {
      if ((paramObject != null) && (paramObject.getClass() == Float.class))
      {
        mValue = ((Float)paramObject).floatValue();
        mHasValue = true;
      }
    }
  }
}
