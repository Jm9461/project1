package ch.acra.widget;

public abstract interface AnimatorListenerCompat
{
  public abstract void onAnimationCancel();
  
  public abstract void onAnimationEnd();
  
  public abstract void onAnimationRepeat();
  
  public abstract void onAnimationStart();
}
