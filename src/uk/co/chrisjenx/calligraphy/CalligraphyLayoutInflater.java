package uk.co.chrisjenx.calligraphy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

class CalligraphyLayoutInflater
  extends LayoutInflater
  implements CalligraphyActivityFactory
{
  private static final String[] sClassPrefixList = { "android.widget.", "android.webkit." };
  private final int mAttributeId;
  private final CalligraphyFactory mCalligraphyFactory;
  private Field mConstructorArgs = null;
  private boolean mSetPrivateFactory = false;
  
  protected CalligraphyLayoutInflater(Context paramContext, int paramInt)
  {
    super(paramContext);
    mAttributeId = paramInt;
    mCalligraphyFactory = new CalligraphyFactory(paramInt);
    setUpLayoutFactories(false);
  }
  
  protected CalligraphyLayoutInflater(LayoutInflater paramLayoutInflater, Context paramContext, int paramInt, boolean paramBoolean)
  {
    super(paramLayoutInflater, paramContext);
    mAttributeId = paramInt;
    mCalligraphyFactory = new CalligraphyFactory(paramInt);
    setUpLayoutFactories(paramBoolean);
  }
  
  private View createCustomViewInternal(View paramView1, View paramView2, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if (!CalligraphyConfig.get().isCustomViewCreation()) {
      return paramView2;
    }
    Object[] arrayOfObject;
    Object localObject;
    if ((paramView2 == null) && (paramString.indexOf('.') > -1))
    {
      if (mConstructorArgs == null) {
        mConstructorArgs = ReflectionUtils.getField(LayoutInflater.class, "mConstructorArgs");
      }
      arrayOfObject = (Object[])ReflectionUtils.getValue(mConstructorArgs, this);
      localObject = arrayOfObject[0];
      arrayOfObject[0] = paramContext;
      ReflectionUtils.setValue(mConstructorArgs, this, arrayOfObject);
      try
      {
        paramView1 = createView(paramString, null, paramAttributeSet);
        arrayOfObject[0] = localObject;
      }
      catch (Throwable paramView1)
      {
        arrayOfObject[0] = localObject;
        ReflectionUtils.setValue(mConstructorArgs, this, arrayOfObject);
        throw paramView1;
      }
      catch (ClassNotFoundException paramView1)
      {
        for (;;)
        {
          arrayOfObject[0] = localObject;
          paramView1 = paramView2;
        }
      }
      ReflectionUtils.setValue(mConstructorArgs, this, arrayOfObject);
      return paramView1;
    }
    return paramView2;
  }
  
  private void setPrivateFactoryInternal()
  {
    if (mSetPrivateFactory) {
      return;
    }
    if (!CalligraphyConfig.get().isReflection()) {
      return;
    }
    if (!(getContext() instanceof LayoutInflater.Factory2))
    {
      mSetPrivateFactory = true;
      return;
    }
    Method localMethod = ReflectionUtils.getMethod(LayoutInflater.class, "setPrivateFactory");
    if (localMethod != null) {
      ReflectionUtils.invokeMethod(this, localMethod, new Object[] { new PrivateWrapperFactory2((LayoutInflater.Factory2)getContext(), this, mCalligraphyFactory) });
    }
    mSetPrivateFactory = true;
  }
  
  private void setUpLayoutFactories(boolean paramBoolean)
  {
    if (paramBoolean) {
      return;
    }
    if ((Build.VERSION.SDK_INT >= 11) && (getFactory2() != null) && (!(getFactory2() instanceof WrapperFactory2))) {
      setFactory2(getFactory2());
    }
    if ((getFactory() != null) && (!(getFactory() instanceof WrapperFactory))) {
      setFactory(getFactory());
    }
  }
  
  public LayoutInflater cloneInContext(Context paramContext)
  {
    return new CalligraphyLayoutInflater(this, paramContext, mAttributeId, true);
  }
  
  public View inflate(XmlPullParser paramXmlPullParser, ViewGroup paramViewGroup, boolean paramBoolean)
  {
    setPrivateFactoryInternal();
    return super.inflate(paramXmlPullParser, paramViewGroup, paramBoolean);
  }
  
  public View onActivityCreateView(View paramView1, View paramView2, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return mCalligraphyFactory.onViewCreated(createCustomViewInternal(paramView1, paramView2, paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
  }
  
  protected View onCreateView(View paramView, String paramString, AttributeSet paramAttributeSet)
  {
    return mCalligraphyFactory.onViewCreated(super.onCreateView(paramView, paramString, paramAttributeSet), getContext(), paramAttributeSet);
  }
  
  protected View onCreateView(String paramString, AttributeSet paramAttributeSet)
  {
    Object localObject1 = null;
    String[] arrayOfString = sClassPrefixList;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = arrayOfString[i];
      try
      {
        localObject2 = createView(paramString, (String)localObject2, paramAttributeSet);
        localObject1 = localObject2;
      }
      catch (ClassNotFoundException localClassNotFoundException) {}
      i += 1;
    }
    Object localObject3 = localObject1;
    if (localObject1 == null) {
      localObject3 = super.onCreateView(paramString, paramAttributeSet);
    }
    return mCalligraphyFactory.onViewCreated((View)localObject3, ((View)localObject3).getContext(), paramAttributeSet);
  }
  
  public void setFactory(LayoutInflater.Factory paramFactory)
  {
    if (!(paramFactory instanceof WrapperFactory))
    {
      super.setFactory(new WrapperFactory(paramFactory, this, mCalligraphyFactory));
      return;
    }
    super.setFactory(paramFactory);
  }
  
  public void setFactory2(LayoutInflater.Factory2 paramFactory2)
  {
    if (!(paramFactory2 instanceof WrapperFactory2))
    {
      super.setFactory2(new WrapperFactory2(paramFactory2, mCalligraphyFactory));
      return;
    }
    super.setFactory2(paramFactory2);
  }
  
  @TargetApi(11)
  private static class PrivateWrapperFactory2
    extends CalligraphyLayoutInflater.WrapperFactory2
  {
    private final CalligraphyLayoutInflater mInflater;
    
    public PrivateWrapperFactory2(LayoutInflater.Factory2 paramFactory2, CalligraphyLayoutInflater paramCalligraphyLayoutInflater, CalligraphyFactory paramCalligraphyFactory)
    {
      super(paramCalligraphyFactory);
      mInflater = paramCalligraphyLayoutInflater;
    }
    
    public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
    {
      return mCalligraphyFactory.onViewCreated(mInflater.createCustomViewInternal(paramView, mFactory2.onCreateView(paramView, paramString, paramContext, paramAttributeSet), paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
    }
  }
  
  private static class WrapperFactory
    implements LayoutInflater.Factory
  {
    private final CalligraphyFactory mCalligraphyFactory;
    private final LayoutInflater.Factory mFactory;
    private final CalligraphyLayoutInflater mInflater;
    
    public WrapperFactory(LayoutInflater.Factory paramFactory, CalligraphyLayoutInflater paramCalligraphyLayoutInflater, CalligraphyFactory paramCalligraphyFactory)
    {
      mFactory = paramFactory;
      mInflater = paramCalligraphyLayoutInflater;
      mCalligraphyFactory = paramCalligraphyFactory;
    }
    
    public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
    {
      if (Build.VERSION.SDK_INT < 11) {
        return mCalligraphyFactory.onViewCreated(mInflater.createCustomViewInternal(null, mFactory.onCreateView(paramString, paramContext, paramAttributeSet), paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
      }
      return mCalligraphyFactory.onViewCreated(mFactory.onCreateView(paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
    }
  }
  
  @TargetApi(11)
  private static class WrapperFactory2
    implements LayoutInflater.Factory2
  {
    protected final CalligraphyFactory mCalligraphyFactory;
    protected final LayoutInflater.Factory2 mFactory2;
    
    public WrapperFactory2(LayoutInflater.Factory2 paramFactory2, CalligraphyFactory paramCalligraphyFactory)
    {
      mFactory2 = paramFactory2;
      mCalligraphyFactory = paramCalligraphyFactory;
    }
    
    public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
    {
      return mCalligraphyFactory.onViewCreated(mFactory2.onCreateView(paramView, paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
    }
    
    public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
    {
      return mCalligraphyFactory.onViewCreated(mFactory2.onCreateView(paramString, paramContext, paramAttributeSet), paramContext, paramAttributeSet);
    }
  }
}
