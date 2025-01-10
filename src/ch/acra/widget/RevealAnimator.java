package ch.acra.widget;

public abstract interface RevealAnimator
{
  public static final PreHoneycombCompat.12 LOLLIPOP_PLUS = new PreHoneycombCompat.12();
  
  public abstract float getRevealRadius();
  
  public abstract void onRevealAnimationEnd();
  
  public abstract void onRevealAnimationStart();
  
  public abstract void setRevealRadius(float paramFloat);
  
  public abstract void setTarget(Target paramTarget);
}
