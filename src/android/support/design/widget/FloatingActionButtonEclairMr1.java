package android.support.design.widget;

class FloatingActionButtonEclairMr1
  extends Slider
{
  FloatingActionButtonEclairMr1(FloatingActionButtonImpl paramFloatingActionButtonImpl)
  {
    super(paramFloatingActionButtonImpl, null);
  }
  
  protected float getElevation()
  {
    FloatingActionButtonImpl localFloatingActionButtonImpl = this$0;
    return mElevation + mPressedTranslationZ;
  }
}
