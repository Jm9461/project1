package android.support.design.widget;

class ActionBar
  extends Slider
{
  ActionBar(FloatingActionButtonImpl paramFloatingActionButtonImpl)
  {
    super(paramFloatingActionButtonImpl, null);
  }
  
  protected float getElevation()
  {
    return 0.0F;
  }
}
