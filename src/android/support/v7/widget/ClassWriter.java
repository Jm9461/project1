package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.org.v4.content.R.styleable;

class ClassWriter
{
  private static ConcurrentHashMap<String, Method> h = new ConcurrentHashMap();
  private static final RectF m = new RectF();
  private final TextView a;
  private TextPaint b;
  private int[] c = new int[0];
  private boolean e = false;
  private boolean f = false;
  private int k = 0;
  private final Context l;
  private float x = -1.0F;
  private float y = -1.0F;
  private float z = -1.0F;
  
  ClassWriter(TextView paramTextView)
  {
    a = paramTextView;
    l = a.getContext();
  }
  
  private int a(RectF paramRectF)
  {
    int n = c.length;
    if (n != 0)
    {
      int j = 0;
      int i = 0 + 1;
      n -= 1;
      while (i <= n)
      {
        j = (i + n) / 2;
        if (a(c[j], paramRectF))
        {
          int i1 = j + 1;
          j = i;
          i = i1;
        }
        else
        {
          n = j - 1;
          j = n;
        }
      }
      return c[j];
    }
    paramRectF = new IllegalStateException("No available text sizes to choose from.");
    throw paramRectF;
  }
  
  private StaticLayout a(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt)
  {
    float f1;
    float f2;
    boolean bool;
    if (Build.VERSION.SDK_INT >= 16)
    {
      f1 = a.getLineSpacingMultiplier();
      f2 = a.getLineSpacingExtra();
      bool = a.getIncludeFontPadding();
    }
    else
    {
      f1 = ((Float)get(a, "getLineSpacingMultiplier", Float.valueOf(1.0F))).floatValue();
      f2 = ((Float)get(a, "getLineSpacingExtra", Float.valueOf(0.0F))).floatValue();
      bool = ((Boolean)get(a, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue();
    }
    return new StaticLayout(paramCharSequence, b, paramInt, paramAlignment, f1, f2, bool);
  }
  
  private void a(float paramFloat)
  {
    if (paramFloat != a.getPaint().getTextSize())
    {
      a.getPaint().setTextSize(paramFloat);
      boolean bool = false;
      if (Build.VERSION.SDK_INT >= 18) {
        bool = a.isInLayout();
      }
      if (a.getLayout() != null)
      {
        e = false;
        try
        {
          Method localMethod = get("nullLayouts");
          if (localMethod != null)
          {
            TextView localTextView = a;
            localMethod.invoke(localTextView, new Object[0]);
          }
        }
        catch (Exception localException)
        {
          Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", localException);
        }
        if (!bool) {
          a.requestLayout();
        } else {
          a.forceLayout();
        }
        a.invalidate();
      }
    }
  }
  
  private void a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 > 0.0F)
    {
      if (paramFloat2 > paramFloat1)
      {
        if (paramFloat3 > 0.0F)
        {
          k = 1;
          y = paramFloat1;
          z = paramFloat2;
          x = paramFloat3;
          f = false;
          return;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("The auto-size step granularity (");
        localStringBuilder.append(paramFloat3);
        localStringBuilder.append("px) is less or equal to (0px)");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Maximum auto-size text size (");
      localStringBuilder.append(paramFloat2);
      localStringBuilder.append("px) is less or equal to minimum auto-size ");
      localStringBuilder.append("text size (");
      localStringBuilder.append(paramFloat1);
      localStringBuilder.append("px)");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Minimum auto-size text size (");
    localStringBuilder.append(paramFloat1);
    localStringBuilder.append("px) is less or equal to (0px)");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  private boolean a(int paramInt, RectF paramRectF)
  {
    CharSequence localCharSequence = a.getText();
    Object localObject2 = localCharSequence;
    TransformationMethod localTransformationMethod = a.getTransformationMethod();
    Object localObject1 = localObject2;
    if (localTransformationMethod != null)
    {
      localCharSequence = localTransformationMethod.getTransformation(localCharSequence, a);
      localObject1 = localObject2;
      if (localCharSequence != null) {
        localObject1 = localCharSequence;
      }
    }
    int i;
    if (Build.VERSION.SDK_INT >= 16) {
      i = a.getMaxLines();
    } else {
      i = -1;
    }
    localObject2 = b;
    if (localObject2 == null) {
      b = new TextPaint();
    } else {
      ((Paint)localObject2).reset();
    }
    b.set(a.getPaint());
    b.setTextSize(paramInt);
    localObject2 = (Layout.Alignment)get(a, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
    if (Build.VERSION.SDK_INT >= 23) {
      localObject2 = init((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right), i);
    } else {
      localObject2 = a((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right));
    }
    if (i != -1)
    {
      if (((StaticLayout)localObject2).getLineCount() > i) {
        break label253;
      }
      if (((Layout)localObject2).getLineEnd(((StaticLayout)localObject2).getLineCount() - 1) != ((CharSequence)localObject1).length()) {
        return false;
      }
    }
    return ((Layout)localObject2).getHeight() <= bottom;
    label253:
    return false;
  }
  
  private boolean b()
  {
    if ((f()) && (k == 1))
    {
      if ((!f) || (c.length == 0))
      {
        int i = 1;
        for (float f1 = Math.round(y); Math.round(x + f1) <= Math.round(z); f1 += x) {
          i += 1;
        }
        int[] arrayOfInt = new int[i];
        f1 = y;
        int j = 0;
        while (j < i)
        {
          arrayOfInt[j] = Math.round(f1);
          f1 += x;
          j += 1;
        }
        c = sort(arrayOfInt);
      }
      e = true;
    }
    else
    {
      e = false;
    }
    return e;
  }
  
  private void c()
  {
    k = 0;
    y = -1.0F;
    z = -1.0F;
    x = -1.0F;
    c = new int[0];
    e = false;
  }
  
  private boolean d()
  {
    int i = c.length;
    boolean bool;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    }
    f = bool;
    if (f)
    {
      k = 1;
      int[] arrayOfInt = c;
      y = arrayOfInt[0];
      z = arrayOfInt[(i - 1)];
      x = -1.0F;
    }
    return f;
  }
  
  private boolean f()
  {
    return a instanceof AppCompatEditText ^ true;
  }
  
  private Object get(Object paramObject1, String paramString, Object paramObject2)
  {
    int j = 0;
    int i = j;
    try
    {
      localObject = get(paramString);
      i = j;
      paramObject1 = ((Method)localObject).invoke(paramObject1, new Object[0]);
      if (paramObject1 == null) {
        return paramObject1;
      }
    }
    catch (Throwable paramObject1) {}catch (Exception paramObject1)
    {
      j = 1;
      i = j;
      Object localObject = new StringBuilder();
      i = j;
      ((StringBuilder)localObject).append("Failed to invoke TextView#");
      i = j;
      ((StringBuilder)localObject).append(paramString);
      i = j;
      ((StringBuilder)localObject).append("() method");
      i = j;
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject).toString(), paramObject1);
      return paramObject2;
    }
    return paramObject1;
    if (i != 0) {}
    throw paramObject1;
  }
  
  private Method get(String paramString)
  {
    Object localObject1 = h;
    try
    {
      localObject1 = ((ConcurrentHashMap)localObject1).get(paramString);
      localObject2 = (Method)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = TextView.class.getDeclaredMethod(paramString, new Class[0]);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          ((Method)localObject2).setAccessible(true);
          localObject1 = h;
          ((ConcurrentHashMap)localObject1).put(paramString, localObject2);
          return localObject2;
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Failed to retrieve TextView#");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("() method");
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject2).toString(), localException);
      return null;
    }
    return localException;
  }
  
  private StaticLayout init(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt1, int paramInt2)
  {
    TextDirectionHeuristic localTextDirectionHeuristic = (TextDirectionHeuristic)get(a, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
    paramCharSequence = StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), b, paramInt1).setAlignment(paramAlignment).setLineSpacing(a.getLineSpacingExtra(), a.getLineSpacingMultiplier()).setIncludePad(a.getIncludeFontPadding()).setBreakStrategy(a.getBreakStrategy()).setHyphenationFrequency(a.getHyphenationFrequency());
    if (paramInt2 == -1) {
      paramInt2 = Integer.MAX_VALUE;
    }
    return paramCharSequence.setMaxLines(paramInt2).setTextDirection(localTextDirectionHeuristic).build();
  }
  
  private void init(TypedArray paramTypedArray)
  {
    int j = paramTypedArray.length();
    int[] arrayOfInt = new int[j];
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        arrayOfInt[i] = paramTypedArray.getDimensionPixelSize(i, -1);
        i += 1;
      }
      c = sort(arrayOfInt);
      d();
    }
  }
  
  private int[] sort(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    if (j == 0) {
      return paramArrayOfInt;
    }
    Arrays.sort(paramArrayOfInt);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < j)
    {
      int n = paramArrayOfInt[i];
      if ((n > 0) && (Collections.binarySearch(localArrayList, Integer.valueOf(n)) < 0)) {
        localArrayList.add(Integer.valueOf(n));
      }
      i += 1;
    }
    if (j == localArrayList.size()) {
      return paramArrayOfInt;
    }
    j = localArrayList.size();
    paramArrayOfInt = new int[j];
    i = 0;
    while (i < j)
    {
      paramArrayOfInt[i] = ((Integer)localArrayList.get(i)).intValue();
      i += 1;
    }
    return paramArrayOfInt;
  }
  
  void a()
  {
    if (!put()) {
      return;
    }
    if (e) {
      if (a.getMeasuredHeight() > 0)
      {
        if (a.getMeasuredWidth() <= 0) {
          return;
        }
        int i;
        if (((Boolean)get(a, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue()) {
          i = 1048576;
        } else {
          i = a.getMeasuredWidth() - a.getTotalPaddingLeft() - a.getTotalPaddingRight();
        }
        int j = a.getHeight() - a.getCompoundPaddingBottom() - a.getCompoundPaddingTop();
        if (i <= 0) {
          return;
        }
        if (j <= 0) {
          return;
        }
        RectF localRectF = m;
        try
        {
          m.setEmpty();
          mright = i;
          mbottom = j;
          float f1 = a(m);
          if (f1 != a.getTextSize()) {
            b(0, f1);
          }
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      else
      {
        return;
      }
    }
    e = true;
  }
  
  void a(int paramInt)
  {
    if (f()) {
      if (paramInt != 0)
      {
        Object localObject;
        if (paramInt == 1)
        {
          localObject = l.getResources().getDisplayMetrics();
          a(TypedValue.applyDimension(2, 12.0F, (DisplayMetrics)localObject), TypedValue.applyDimension(2, 112.0F, (DisplayMetrics)localObject), 1.0F);
          if (b()) {
            a();
          }
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Unknown auto-size text type: ");
          ((StringBuilder)localObject).append(paramInt);
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        c();
      }
    }
  }
  
  void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (f())
    {
      DisplayMetrics localDisplayMetrics = l.getResources().getDisplayMetrics();
      a(TypedValue.applyDimension(paramInt4, paramInt1, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt2, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt3, localDisplayMetrics));
      if (b()) {
        a();
      }
    }
  }
  
  void a(AttributeSet paramAttributeSet, int paramInt)
  {
    float f2 = -1.0F;
    float f3 = -1.0F;
    float f1 = -1.0F;
    paramAttributeSet = l.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
      k = paramAttributeSet.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
      f1 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F);
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
      f2 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F);
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
      f3 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F);
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes))
    {
      paramInt = paramAttributeSet.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
      if (paramInt > 0)
      {
        TypedArray localTypedArray = paramAttributeSet.getResources().obtainTypedArray(paramInt);
        init(localTypedArray);
        localTypedArray.recycle();
      }
    }
    paramAttributeSet.recycle();
    if (f())
    {
      if (k == 1)
      {
        if (!f)
        {
          paramAttributeSet = l.getResources().getDisplayMetrics();
          float f4 = f2;
          if (f2 == -1.0F) {
            f4 = TypedValue.applyDimension(2, 12.0F, paramAttributeSet);
          }
          f2 = f3;
          if (f3 == -1.0F) {
            f2 = TypedValue.applyDimension(2, 112.0F, paramAttributeSet);
          }
          f3 = f1;
          if (f1 == -1.0F) {
            f3 = 1.0F;
          }
          a(f4, f2, f3);
        }
        b();
      }
    }
    else {
      k = 0;
    }
  }
  
  void a(int[] paramArrayOfInt, int paramInt)
  {
    if (f())
    {
      int j = paramArrayOfInt.length;
      if (j > 0)
      {
        int[] arrayOfInt = new int[j];
        Object localObject;
        if (paramInt == 0)
        {
          localObject = Arrays.copyOf(paramArrayOfInt, j);
        }
        else
        {
          DisplayMetrics localDisplayMetrics = l.getResources().getDisplayMetrics();
          int i = 0;
          for (;;)
          {
            localObject = arrayOfInt;
            if (i >= j) {
              break;
            }
            arrayOfInt[i] = Math.round(TypedValue.applyDimension(paramInt, paramArrayOfInt[i], localDisplayMetrics));
            i += 1;
          }
        }
        c = sort((int[])localObject);
        if (!d())
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("None of the preset sizes is valid: ");
          ((StringBuilder)localObject).append(Arrays.toString(paramArrayOfInt));
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        f = false;
      }
      if (b()) {
        a();
      }
    }
  }
  
  void b(int paramInt, float paramFloat)
  {
    Object localObject = l;
    if (localObject == null) {
      localObject = Resources.getSystem();
    } else {
      localObject = ((Context)localObject).getResources();
    }
    a(TypedValue.applyDimension(paramInt, paramFloat, ((Resources)localObject).getDisplayMetrics()));
  }
  
  int[] get()
  {
    return c;
  }
  
  int getHeight()
  {
    return Math.round(y);
  }
  
  int getTextSize()
  {
    return k;
  }
  
  int getValue()
  {
    return Math.round(z);
  }
  
  int getWidth()
  {
    return Math.round(x);
  }
  
  boolean put()
  {
    return (f()) && (k != 0);
  }
}
