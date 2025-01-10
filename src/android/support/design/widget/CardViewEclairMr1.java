package android.support.design.widget;

class CardViewEclairMr1
  extends Slider
{
  CardViewEclairMr1(FloatingActionButtonImpl paramFloatingActionButtonImpl)
  {
    super(paramFloatingActionButtonImpl, null);
  }
  
  protected float getElevation()
  {
    FloatingActionButtonImpl localFloatingActionButtonImpl = this$0;
    return mElevation + mView;
  }
}
