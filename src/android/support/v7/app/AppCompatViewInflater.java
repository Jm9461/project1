package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.org.jaxen.util.Label;
import org.org.v4.content.R.styleable;
import org.org.v4.view.ContextThemeWrapper;

public class AppCompatViewInflater
{
  private static final String LOG_TAG = "AppCompatViewInflater";
  private static final String[] sClassPrefixList = { "android.widget.", "android.view.", "android.webkit." };
  private static final Map<String, Constructor<? extends View>> sConstructorMap = new Label();
  private static final Class<?>[] sConstructorSignature = { Context.class, AttributeSet.class };
  private static final int[] sOnClickAttrs = { 16843375 };
  private final Object[] mConstructorArgs = new Object[2];
  
  public AppCompatViewInflater() {}
  
  private void checkOnClickListener(View paramView, AttributeSet paramAttributeSet)
  {
    Object localObject = paramView.getContext();
    if ((localObject instanceof ContextWrapper))
    {
      if ((Build.VERSION.SDK_INT >= 15) && (!ViewCompat.hasOnClickListeners(paramView))) {
        return;
      }
      paramAttributeSet = ((Context)localObject).obtainStyledAttributes(paramAttributeSet, sOnClickAttrs);
      localObject = paramAttributeSet.getString(0);
      if (localObject != null) {
        paramView.setOnClickListener(new a(paramView, (String)localObject));
      }
      paramAttributeSet.recycle();
    }
  }
  
  private View createViewByPrefix(Context paramContext, String paramString1, String paramString2)
  {
    Constructor localConstructor = (Constructor)sConstructorMap.get(paramString1);
    Object localObject = localConstructor;
    if (localConstructor == null) {}
    try
    {
      localObject = paramContext.getClassLoader();
      if (paramString2 != null)
      {
        paramContext = new StringBuilder();
        paramContext.append(paramString2);
        paramContext.append(paramString1);
        paramContext = paramContext.toString();
      }
      else
      {
        paramContext = paramString1;
      }
      paramContext = ((ClassLoader)localObject).loadClass(paramContext).asSubclass(View.class);
      paramString2 = sConstructorSignature;
      paramContext = paramContext.getConstructor(paramString2);
      localObject = paramContext;
      paramString2 = sConstructorMap;
      paramString2.put(paramString1, paramContext);
      ((Constructor)localObject).setAccessible(true);
      paramContext = mConstructorArgs;
      paramContext = ((Constructor)localObject).newInstance(paramContext);
      return (View)paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    String str = paramString;
    if (paramString.equals("view")) {
      str = paramAttributeSet.getAttributeValue(null, "class");
    }
    mConstructorArgs[0] = paramContext;
    mConstructorArgs[1] = paramAttributeSet;
    try
    {
      int i = str.indexOf('.');
      if (-1 == i)
      {
        i = 0;
        for (;;)
        {
          int j = sClassPrefixList.length;
          if (i >= j) {
            break;
          }
          paramString = sClassPrefixList[i];
          paramString = createViewByPrefix(paramContext, str, paramString);
          if (paramString != null)
          {
            paramContext = mConstructorArgs;
            paramContext[0] = null;
            paramContext[1] = null;
            return paramString;
          }
          i += 1;
        }
        paramContext = mConstructorArgs;
        paramContext[0] = null;
        paramContext[1] = null;
        return null;
      }
      paramContext = createViewByPrefix(paramContext, str, null);
      paramString = mConstructorArgs;
      paramString[0] = null;
      paramString[1] = null;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramString = mConstructorArgs;
      paramString[0] = null;
      paramString[1] = null;
      throw paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext = mConstructorArgs;
      paramContext[0] = null;
      paramContext[1] = null;
    }
    return null;
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    int i = 0;
    if (paramBoolean1) {
      i = paramAttributeSet.getResourceId(R.styleable.View_android_theme, 0);
    }
    int j = i;
    if (paramBoolean2)
    {
      j = i;
      if (i == 0)
      {
        int k = paramAttributeSet.getResourceId(R.styleable.View_theme, 0);
        i = k;
        j = i;
        if (k != 0)
        {
          Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
          j = i;
        }
      }
    }
    paramAttributeSet.recycle();
    if ((j != 0) && ((!(paramContext instanceof ContextThemeWrapper)) || (((ContextThemeWrapper)paramContext).getThemeResId() != j))) {
      return new ContextThemeWrapper(paramContext, j);
    }
    return paramContext;
  }
  
  private void verifyNotNull(View paramView, String paramString)
  {
    if (paramView != null) {
      return;
    }
    paramView = new StringBuilder();
    paramView.append(getClass().getName());
    paramView.append(" asked to inflate view for <");
    paramView.append(paramString);
    paramView.append(">, but returned null");
    throw new IllegalStateException(paramView.toString());
  }
  
  protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatButton createButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatButton(paramContext, paramAttributeSet);
  }
  
  protected AppCompatCheckBox createCheckBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatCheckBox(paramContext, paramAttributeSet);
  }
  
  protected AppCompatCheckedTextView createCheckedTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatCheckedTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatEditText createEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatEditText(paramContext, paramAttributeSet);
  }
  
  protected AppCompatImageButton createImageButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatImageButton(paramContext, paramAttributeSet);
  }
  
  protected AppCompatImageView createImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatImageView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatMultiAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  protected AppCompatRadioButton createRadioButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatRadioButton(paramContext, paramAttributeSet);
  }
  
  protected AppCompatRatingBar createRatingBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatRatingBar(paramContext, paramAttributeSet);
  }
  
  protected SwitchCompat createSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new SwitchCompat(paramContext, paramAttributeSet);
  }
  
  protected AppCompatSpinner createSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatSpinner(paramContext, paramAttributeSet);
  }
  
  protected AppCompatTextView createTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new AppCompatTextView(paramContext, paramAttributeSet);
  }
  
  protected View createView(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    return null;
  }
  
  final View createView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    Object localObject1 = paramContext;
    if (paramBoolean1)
    {
      localObject1 = paramContext;
      if (paramView != null) {
        localObject1 = paramView.getContext();
      }
    }
    if (!paramBoolean2)
    {
      paramView = (View)localObject1;
      if (!paramBoolean3) {}
    }
    else
    {
      paramView = themifyContext((Context)localObject1, paramAttributeSet, paramBoolean2, paramBoolean3);
    }
    localObject1 = paramView;
    if (paramBoolean4) {
      localObject1 = TintContextWrapper.wrap(paramView);
    }
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (paramString.equals("Button"))
      {
        i = 2;
        break;
        if (paramString.equals("EditText"))
        {
          i = 3;
          break;
          if (paramString.equals("CheckBox"))
          {
            i = 6;
            break;
            if (paramString.equals("AutoCompleteTextView"))
            {
              i = 9;
              break;
              if (paramString.equals("ImageView"))
              {
                i = 1;
                break;
                if (paramString.equals("RadioButton"))
                {
                  i = 7;
                  break;
                  if (paramString.equals("Spinner"))
                  {
                    i = 4;
                    break;
                    if (paramString.equals("SeekBar"))
                    {
                      i = 12;
                      break;
                      if (paramString.equals("ImageButton"))
                      {
                        i = 5;
                        break;
                        if (paramString.equals("TextView"))
                        {
                          i = 0;
                          break;
                          if (paramString.equals("MultiAutoCompleteTextView"))
                          {
                            i = 10;
                            break;
                            if (paramString.equals("CheckedTextView"))
                            {
                              i = 8;
                              break;
                              if (paramString.equals("RatingBar")) {
                                i = 11;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    switch (i)
    {
    default: 
      paramView = createView((Context)localObject1, paramString, paramAttributeSet);
      break;
    case 12: 
      localObject2 = createSeekBar((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 11: 
      localObject2 = createRatingBar((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 10: 
      localObject2 = createMultiAutoCompleteTextView((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 9: 
      localObject2 = createAutoCompleteTextView((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 8: 
      localObject2 = createCheckedTextView((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 7: 
      localObject2 = createRadioButton((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 6: 
      localObject2 = createCheckBox((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 5: 
      localObject2 = createImageButton((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 4: 
      localObject2 = createSpinner((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 3: 
      localObject2 = createEditText((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 2: 
      localObject2 = createButton((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 1: 
      localObject2 = createImageView((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
      break;
    case 0: 
      localObject2 = createTextView((Context)localObject1, paramAttributeSet);
      paramView = (View)localObject2;
      verifyNotNull((View)localObject2, paramString);
    }
    Object localObject2 = paramView;
    if (paramView == null)
    {
      localObject2 = paramView;
      if (paramContext != localObject1) {
        localObject2 = createViewFromTag((Context)localObject1, paramString, paramAttributeSet);
      }
    }
    if (localObject2 != null) {
      checkOnClickListener((View)localObject2, paramAttributeSet);
    }
    return localObject2;
  }
  
  private static class a
    implements View.OnClickListener
  {
    private final View mHostView;
    private final String mMethodName;
    private Context mResolvedContext;
    private Method mResolvedMethod;
    
    public a(View paramView, String paramString)
    {
      mHostView = paramView;
      mMethodName = paramString;
    }
    
    private void resolveMethod(Context paramContext, String paramString)
    {
      while (paramContext != null)
      {
        try
        {
          boolean bool = paramContext.isRestricted();
          if (!bool)
          {
            paramString = paramContext.getClass();
            String str = mMethodName;
            paramString = paramString.getMethod(str, new Class[] { View.class });
            if (paramString != null)
            {
              mResolvedMethod = paramString;
              mResolvedContext = paramContext;
              return;
            }
          }
        }
        catch (NoSuchMethodException paramString) {}
        if ((paramContext instanceof ContextWrapper)) {
          paramContext = ((ContextWrapper)paramContext).getBaseContext();
        } else {
          paramContext = null;
        }
      }
      int i = mHostView.getId();
      if (i == -1)
      {
        paramContext = "";
      }
      else
      {
        paramContext = new StringBuilder();
        paramContext.append(" with id '");
        paramContext.append(mHostView.getContext().getResources().getResourceEntryName(i));
        paramContext.append("'");
        paramContext = paramContext.toString();
      }
      paramString = new StringBuilder();
      paramString.append("Could not find method ");
      paramString.append(mMethodName);
      paramString.append("(View) in a parent or ancestor Context for android:onClick ");
      paramString.append("attribute defined on view ");
      paramString.append(mHostView.getClass());
      paramString.append(paramContext);
      paramContext = new IllegalStateException(paramString.toString());
      throw paramContext;
    }
    
    public void onClick(View paramView)
    {
      if (mResolvedMethod == null) {
        resolveMethod(mHostView.getContext(), mMethodName);
      }
      Method localMethod = mResolvedMethod;
      Context localContext = mResolvedContext;
      try
      {
        localMethod.invoke(localContext, new Object[] { paramView });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new IllegalStateException("Could not execute method for android:onClick", paramView);
      }
      catch (IllegalAccessException paramView)
      {
        throw new IllegalStateException("Could not execute non-public method for android:onClick", paramView);
      }
    }
  }
}
