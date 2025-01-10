package org.org.jraf.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.org.jaxen.asm.PathParser;
import org.org.jaxen.asm.PathParser.PathDataNode;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Context
{
  private static boolean a(int paramInt)
  {
    return (paramInt >= 28) && (paramInt <= 31);
  }
  
  private static void build(ValueAnimator paramValueAnimator, TypedArray paramTypedArray, int paramInt, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    paramValueAnimator = (ObjectAnimator)paramValueAnimator;
    String str1 = android.support.v4.content.view.Context.getString(paramTypedArray, paramXmlPullParser, "pathData", 1);
    if (str1 != null)
    {
      String str2 = android.support.v4.content.view.Context.getString(paramTypedArray, paramXmlPullParser, "propertyXName", 2);
      paramXmlPullParser = android.support.v4.content.view.Context.getString(paramTypedArray, paramXmlPullParser, "propertyYName", 3);
      if (((paramInt == 2) || (paramInt != 4)) || ((str2 == null) && (paramXmlPullParser == null)))
      {
        paramValueAnimator = new StringBuilder();
        paramValueAnimator.append(paramTypedArray.getPositionDescription());
        paramValueAnimator.append(" propertyXName or propertyYName is needed for PathData");
        throw new InflateException(paramValueAnimator.toString());
      }
      parse(PathParser.createPathFromPathData(str1), paramValueAnimator, 0.5F * paramFloat, str2, paramXmlPullParser);
      return;
    }
    paramValueAnimator.setPropertyName(android.support.v4.content.view.Context.getString(paramTypedArray, paramXmlPullParser, "propertyName", 0));
  }
  
  private static PropertyValuesHolder create(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, String paramString, int paramInt)
  {
    Object localObject1 = null;
    int j = paramInt;
    for (;;)
    {
      paramInt = paramXmlPullParser.next();
      if ((paramInt == 3) || (paramInt == 1)) {
        break;
      }
      if (paramXmlPullParser.getName().equals("keyframe"))
      {
        paramInt = j;
        if (j == 4) {
          paramInt = inflate(paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramXmlPullParser);
        }
        Keyframe localKeyframe = initialize(paramContext, paramResources, paramTheme, Xml.asAttributeSet(paramXmlPullParser), paramInt, paramXmlPullParser);
        Object localObject2 = localObject1;
        if (localKeyframe != null)
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArrayList();
          }
          ((ArrayList)localObject2).add(localKeyframe);
        }
        paramXmlPullParser.next();
        localObject1 = localObject2;
        j = paramInt;
      }
    }
    if (localObject1 != null)
    {
      int k = localObject1.size();
      int i = k;
      if (k > 0)
      {
        paramContext = (Keyframe)localObject1.get(0);
        paramResources = (Keyframe)localObject1.get(k - 1);
        float f = paramResources.getFraction();
        paramInt = i;
        if (f < 1.0F) {
          if (f < 0.0F)
          {
            paramResources.setFraction(1.0F);
            paramInt = i;
          }
          else
          {
            localObject1.add(localObject1.size(), toString(paramResources, 1.0F));
            paramInt = k + 1;
          }
        }
        f = paramContext.getFraction();
        k = paramInt;
        if (f != 0.0F) {
          if (f < 0.0F)
          {
            paramContext.setFraction(0.0F);
            k = paramInt;
          }
          else
          {
            localObject1.add(0, toString(paramContext, 0.0F));
            k = paramInt + 1;
          }
        }
        paramContext = new Keyframe[k];
        localObject1.toArray(paramContext);
        paramInt = 0;
        while (paramInt < k)
        {
          paramResources = paramContext[paramInt];
          if (paramResources.getFraction() < 0.0F) {
            if (paramInt == 0)
            {
              paramResources.setFraction(0.0F);
            }
            else if (paramInt == k - 1)
            {
              paramResources.setFraction(1.0F);
            }
            else
            {
              i = paramInt + 1;
              int m = paramInt;
              while ((i < k - 1) && (paramContext[i].getFraction() < 0.0F))
              {
                m = i;
                i += 1;
              }
              getValue(paramContext, paramContext[(m + 1)].getFraction() - paramContext[(paramInt - 1)].getFraction(), paramInt, m);
            }
          }
          paramInt += 1;
        }
        paramContext = PropertyValuesHolder.ofKeyframe(paramString, paramContext);
        if (j != 3) {
          return paramContext;
        }
        paramContext.setEvaluator(Method.getMethod());
        return paramContext;
      }
    }
    return null;
    return paramContext;
  }
  
  private static Animator createAnimatorFromXml(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, float paramFloat)
  {
    return createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0, paramFloat);
  }
  
  private static Animator createAnimatorFromXml(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt, float paramFloat)
  {
    int j = paramXmlPullParser.getDepth();
    Object localObject3 = null;
    Object localObject2 = null;
    int i;
    for (;;)
    {
      i = paramXmlPullParser.next();
      if ((i == 3) && (paramXmlPullParser.getDepth() <= j)) {
        break label338;
      }
      if (i == 1) {
        break label338;
      }
      if (i == 2)
      {
        Object localObject1 = paramXmlPullParser.getName();
        i = 0;
        if (((String)localObject1).equals("objectAnimator"))
        {
          localObject1 = loadObjectAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, paramFloat, paramXmlPullParser);
        }
        else if (((String)localObject1).equals("animator"))
        {
          localObject1 = loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, null, paramFloat, paramXmlPullParser);
        }
        else if (((String)localObject1).equals("set"))
        {
          localObject1 = new AnimatorSet();
          localObject3 = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.AnimatorSet);
          createAnimatorFromXml(paramContext, paramResources, paramTheme, paramXmlPullParser, paramAttributeSet, (AnimatorSet)localObject1, android.support.v4.content.view.Context.getString((TypedArray)localObject3, paramXmlPullParser, "ordering", 0, 0), paramFloat);
          ((TypedArray)localObject3).recycle();
        }
        else
        {
          if (!((String)localObject1).equals("propertyValuesHolder")) {
            break;
          }
          localObject1 = inflate(paramContext, paramResources, paramTheme, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser));
          if ((localObject1 != null) && (localObject3 != null) && ((localObject3 instanceof ValueAnimator))) {
            ((ValueAnimator)localObject3).setValues((PropertyValuesHolder[])localObject1);
          }
          i = 1;
          localObject1 = localObject3;
        }
        localObject3 = localObject1;
        if (paramAnimatorSet != null)
        {
          localObject3 = localObject1;
          if (i == 0)
          {
            Object localObject4 = localObject2;
            if (localObject2 == null) {
              localObject4 = new ArrayList();
            }
            ((ArrayList)localObject4).add(localObject1);
            localObject3 = localObject1;
            localObject2 = localObject4;
          }
        }
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("Unknown animator name: ");
    paramContext.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramContext.toString());
    label338:
    if ((paramAnimatorSet != null) && (localObject2 != null))
    {
      paramContext = new Animator[localObject2.size()];
      i = 0;
      paramResources = localObject2.iterator();
      while (paramResources.hasNext())
      {
        paramContext[i] = ((Animator)paramResources.next());
        i += 1;
      }
      if (paramInt == 0)
      {
        paramAnimatorSet.playTogether(paramContext);
        return localObject3;
      }
      paramAnimatorSet.playSequentially(paramContext);
    }
    return localObject3;
  }
  
  public static Animator getDrawable(android.content.Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return AnimatorInflater.loadAnimator(paramContext, paramInt);
    }
    return getDrawable(paramContext, paramContext.getResources(), paramContext.getTheme(), paramInt);
  }
  
  public static Animator getDrawable(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt)
  {
    return loadAnimator(paramContext, paramResources, paramTheme, paramInt, 1.0F);
  }
  
  private static int getType(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    TypedValue localTypedValue = paramTypedArray.peekValue(paramInt1);
    int k = 1;
    int j = 0;
    if (localTypedValue != null) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    int i;
    if (paramInt1 != 0) {
      i = type;
    } else {
      i = 0;
    }
    paramTypedArray = paramTypedArray.peekValue(paramInt2);
    if (paramTypedArray != null) {
      paramInt2 = k;
    } else {
      paramInt2 = 0;
    }
    if (paramInt2 != 0) {
      j = type;
    }
    if (((paramInt1 != 0) && (a(i))) || ((paramInt2 != 0) && (a(j)))) {
      return 3;
    }
    return 0;
  }
  
  private static PropertyValuesHolder getType(TypedArray paramTypedArray, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    Object localObject = paramTypedArray.peekValue(paramInt2);
    int i;
    if (localObject != null) {
      i = 1;
    } else {
      i = 0;
    }
    int k;
    if (i != 0) {
      k = type;
    } else {
      k = 0;
    }
    localObject = paramTypedArray.peekValue(paramInt3);
    int j;
    if (localObject != null) {
      j = 1;
    } else {
      j = 0;
    }
    int m;
    if (j != 0) {
      m = type;
    } else {
      m = 0;
    }
    if (paramInt1 == 4) {
      if (((i != 0) && (a(k))) || ((j != 0) && (a(m)))) {
        paramInt1 = 3;
      } else {
        paramInt1 = 0;
      }
    }
    int n;
    if (paramInt1 == 0) {
      n = 1;
    } else {
      n = 0;
    }
    if (paramInt1 == 2)
    {
      localObject = paramTypedArray.getString(paramInt2);
      paramTypedArray = paramTypedArray.getString(paramInt3);
      PathParser.PathDataNode[] arrayOfPathDataNode1 = PathParser.createNodesFromPathData((String)localObject);
      PathParser.PathDataNode[] arrayOfPathDataNode2 = PathParser.createNodesFromPathData(paramTypedArray);
      if ((arrayOfPathDataNode1 == null) && (arrayOfPathDataNode2 == null)) {
        break label334;
      }
      if (arrayOfPathDataNode1 != null)
      {
        Label localLabel = new Label();
        if (arrayOfPathDataNode2 != null)
        {
          if (PathParser.canMorph(arrayOfPathDataNode1, arrayOfPathDataNode2))
          {
            paramTypedArray = PropertyValuesHolder.ofObject(paramString, localLabel, new Object[] { arrayOfPathDataNode1, arrayOfPathDataNode2 });
          }
          else
          {
            paramString = new StringBuilder();
            paramString.append(" Can't morph from ");
            paramString.append((String)localObject);
            paramString.append(" to ");
            paramString.append(paramTypedArray);
            throw new InflateException(paramString.toString());
          }
        }
        else {
          paramTypedArray = PropertyValuesHolder.ofObject(paramString, localLabel, new Object[] { arrayOfPathDataNode1 });
        }
        return paramTypedArray;
      }
      if (arrayOfPathDataNode2 != null) {
        return PropertyValuesHolder.ofObject(paramString, new Label(), new Object[] { arrayOfPathDataNode2 });
      }
      label334:
      return null;
    }
    localObject = null;
    if (paramInt1 == 3) {
      localObject = Method.getMethod();
    }
    if (n != 0)
    {
      float f1;
      if (i != 0)
      {
        if (k == 5) {
          f1 = paramTypedArray.getDimension(paramInt2, 0.0F);
        } else {
          f1 = paramTypedArray.getFloat(paramInt2, 0.0F);
        }
        if (j != 0)
        {
          float f2;
          if (m == 5) {
            f2 = paramTypedArray.getDimension(paramInt3, 0.0F);
          } else {
            f2 = paramTypedArray.getFloat(paramInt3, 0.0F);
          }
          paramTypedArray = PropertyValuesHolder.ofFloat(paramString, new float[] { f1, f2 });
        }
        else
        {
          paramTypedArray = PropertyValuesHolder.ofFloat(paramString, new float[] { f1 });
        }
      }
      else
      {
        if (m == 5) {
          f1 = paramTypedArray.getDimension(paramInt3, 0.0F);
        } else {
          f1 = paramTypedArray.getFloat(paramInt3, 0.0F);
        }
        paramTypedArray = PropertyValuesHolder.ofFloat(paramString, new float[] { f1 });
      }
    }
    else if (i != 0)
    {
      if (k == 5) {
        paramInt1 = (int)paramTypedArray.getDimension(paramInt2, 0.0F);
      } else if (a(k)) {
        paramInt1 = paramTypedArray.getColor(paramInt2, 0);
      } else {
        paramInt1 = paramTypedArray.getInt(paramInt2, 0);
      }
      if (j != 0)
      {
        if (m == 5) {
          paramInt2 = (int)paramTypedArray.getDimension(paramInt3, 0.0F);
        } else if (a(m)) {
          paramInt2 = paramTypedArray.getColor(paramInt3, 0);
        } else {
          paramInt2 = paramTypedArray.getInt(paramInt3, 0);
        }
        paramTypedArray = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1, paramInt2 });
      }
      else
      {
        paramTypedArray = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1 });
      }
    }
    else if (j != 0)
    {
      if (m == 5) {
        paramInt1 = (int)paramTypedArray.getDimension(paramInt3, 0.0F);
      } else if (a(m)) {
        paramInt1 = paramTypedArray.getColor(paramInt3, 0);
      } else {
        paramInt1 = paramTypedArray.getInt(paramInt3, 0);
      }
      paramTypedArray = PropertyValuesHolder.ofInt(paramString, new int[] { paramInt1 });
    }
    else
    {
      paramTypedArray = null;
    }
    if ((paramTypedArray != null) && (localObject != null)) {
      paramTypedArray.setEvaluator((TypeEvaluator)localObject);
    }
    return paramTypedArray;
  }
  
  private static void getValue(Keyframe[] paramArrayOfKeyframe, float paramFloat, int paramInt1, int paramInt2)
  {
    paramFloat /= (paramInt2 - paramInt1 + 2);
    while (paramInt1 <= paramInt2)
    {
      paramArrayOfKeyframe[paramInt1].setFraction(paramArrayOfKeyframe[(paramInt1 - 1)].getFraction() + paramFloat);
      paramInt1 += 1;
    }
  }
  
  private static int inflate(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser)
  {
    paramResources = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableGroup);
    int i = 0;
    paramTheme = android.support.v4.content.view.Context.getType(paramResources, paramXmlPullParser, "value", 0);
    if (paramTheme != null) {
      i = 1;
    }
    if ((i != 0) && (a(type))) {
      i = 3;
    } else {
      i = 0;
    }
    paramResources.recycle();
    return i;
  }
  
  private static PropertyValuesHolder[] inflate(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
  {
    Object localObject1 = null;
    int i;
    for (;;)
    {
      i = paramXmlPullParser.getEventType();
      if ((i == 3) || (i == 1)) {
        break;
      }
      if (i != 2)
      {
        paramXmlPullParser.next();
      }
      else
      {
        Object localObject2 = localObject1;
        if (paramXmlPullParser.getName().equals("propertyValuesHolder"))
        {
          TypedArray localTypedArray = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.LayerDrawableItem);
          String str = android.support.v4.content.view.Context.getString(localTypedArray, paramXmlPullParser, "propertyName", 3);
          i = android.support.v4.content.view.Context.getString(localTypedArray, paramXmlPullParser, "valueType", 2, 4);
          localObject2 = create(paramContext, paramResources, paramTheme, paramXmlPullParser, str, i);
          Object localObject3 = localObject2;
          if (localObject2 == null) {
            localObject3 = getType(localTypedArray, i, 0, 1, str);
          }
          localObject2 = localObject1;
          if (localObject3 != null)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localObject3);
          }
          localTypedArray.recycle();
        }
        paramXmlPullParser.next();
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      int j = localObject1.size();
      paramContext = new PropertyValuesHolder[j];
      i = 0;
      while (i < j)
      {
        paramContext[i] = ((PropertyValuesHolder)localObject1.get(i));
        i += 1;
      }
    }
    return null;
    return paramContext;
  }
  
  private static Keyframe initialize(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int paramInt, XmlPullParser paramXmlPullParser)
  {
    paramTheme = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableGroup);
    paramResources = null;
    float f = android.support.v4.content.view.Context.getNamedFloat(paramTheme, paramXmlPullParser, "fraction", 3, -1.0F);
    paramAttributeSet = android.support.v4.content.view.Context.getType(paramTheme, paramXmlPullParser, "value", 0);
    int j;
    if (paramAttributeSet != null) {
      j = 1;
    } else {
      j = 0;
    }
    int i = paramInt;
    if (paramInt == 4) {
      if ((j != 0) && (a(type))) {
        i = 3;
      } else {
        i = 0;
      }
    }
    if (j != 0)
    {
      if (i != 0)
      {
        if ((i == 1) || (i == 3)) {
          paramResources = Keyframe.ofInt(f, android.support.v4.content.view.Context.getString(paramTheme, paramXmlPullParser, "value", 0, 0));
        }
      }
      else {
        paramResources = Keyframe.ofFloat(f, android.support.v4.content.view.Context.getNamedFloat(paramTheme, paramXmlPullParser, "value", 0, 0.0F));
      }
    }
    else if (i == 0) {
      paramResources = Keyframe.ofFloat(f);
    } else {
      paramResources = Keyframe.ofInt(f);
    }
    paramInt = android.support.v4.content.view.Context.getResourceId(paramTheme, paramXmlPullParser, "interpolator", 1, 0);
    if (paramInt > 0) {
      paramResources.setInterpolator(ArgbEvaluator.loadAnimator(paramContext, paramInt));
    }
    paramTheme.recycle();
    return paramResources;
  }
  
  private static void load(ValueAnimator paramValueAnimator, TypedArray paramTypedArray1, TypedArray paramTypedArray2, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    long l1 = android.support.v4.content.view.Context.getString(paramTypedArray1, paramXmlPullParser, "duration", 1, 300);
    long l2 = android.support.v4.content.view.Context.getString(paramTypedArray1, paramXmlPullParser, "startOffset", 2, 0);
    int k = android.support.v4.content.view.Context.getString(paramTypedArray1, paramXmlPullParser, "valueType", 7, 4);
    int i = k;
    int j = i;
    if (android.support.v4.content.view.Context.get(paramXmlPullParser, "valueFrom"))
    {
      j = i;
      if (android.support.v4.content.view.Context.get(paramXmlPullParser, "valueTo"))
      {
        if (k == 4) {
          i = getType(paramTypedArray1, 5, 6);
        }
        PropertyValuesHolder localPropertyValuesHolder = getType(paramTypedArray1, i, 5, 6, "");
        j = i;
        if (localPropertyValuesHolder != null)
        {
          paramValueAnimator.setValues(new PropertyValuesHolder[] { localPropertyValuesHolder });
          j = i;
        }
      }
    }
    paramValueAnimator.setDuration(l1);
    paramValueAnimator.setStartDelay(l2);
    paramValueAnimator.setRepeatCount(android.support.v4.content.view.Context.getString(paramTypedArray1, paramXmlPullParser, "repeatCount", 3, 0));
    paramValueAnimator.setRepeatMode(android.support.v4.content.view.Context.getString(paramTypedArray1, paramXmlPullParser, "repeatMode", 4, 1));
    if (paramTypedArray2 != null) {
      build(paramValueAnimator, paramTypedArray2, j, paramFloat, paramXmlPullParser);
    }
  }
  
  public static Animator loadAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, int paramInt, float paramFloat)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      XmlResourceParser localXmlResourceParser = paramResources.getAnimation(paramInt);
      localObject2 = localXmlResourceParser;
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramContext = createAnimatorFromXml(paramContext, paramResources, paramTheme, localXmlResourceParser, paramFloat);
      if (localXmlResourceParser == null) {
        return paramContext;
      }
      localXmlResourceParser.close();
      return paramContext;
    }
    catch (Throwable paramContext) {}catch (IOException paramContext)
    {
      localObject1 = localObject3;
      paramResources = new StringBuilder();
      localObject1 = localObject3;
      paramResources.append("Can't load animation resource ID #0x");
      localObject1 = localObject3;
      paramResources.append(Integer.toHexString(paramInt));
      localObject1 = localObject3;
      paramResources = new Resources.NotFoundException(paramResources.toString());
      localObject1 = localObject3;
      paramResources.initCause(paramContext);
      localObject1 = localObject3;
      throw paramResources;
    }
    catch (XmlPullParserException paramContext)
    {
      localObject1 = localObject2;
      paramResources = new StringBuilder();
      localObject1 = localObject2;
      paramResources.append("Can't load animation resource ID #0x");
      localObject1 = localObject2;
      paramResources.append(Integer.toHexString(paramInt));
      localObject1 = localObject2;
      paramResources = new Resources.NotFoundException(paramResources.toString());
      localObject1 = localObject2;
      paramResources.initCause(paramContext);
      localObject1 = localObject2;
      throw paramResources;
    }
    if (localObject1 != null) {
      localObject1.close();
    }
    throw paramContext;
    return paramContext;
  }
  
  private static ValueAnimator loadAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    TypedArray localTypedArray = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.Animator);
    paramTheme = android.support.v4.content.view.Context.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawablePath);
    paramResources = paramValueAnimator;
    if (paramValueAnimator == null) {
      paramResources = new ValueAnimator();
    }
    load(paramResources, localTypedArray, paramTheme, paramFloat, paramXmlPullParser);
    int i = android.support.v4.content.view.Context.getResourceId(localTypedArray, paramXmlPullParser, "interpolator", 0, 0);
    if (i > 0) {
      paramResources.setInterpolator(ArgbEvaluator.loadAnimator(paramContext, i));
    }
    localTypedArray.recycle();
    if (paramTheme != null) {
      paramTheme.recycle();
    }
    return paramResources;
  }
  
  private static ObjectAnimator loadObjectAnimator(android.content.Context paramContext, Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, float paramFloat, XmlPullParser paramXmlPullParser)
  {
    ObjectAnimator localObjectAnimator = new ObjectAnimator();
    loadAnimator(paramContext, paramResources, paramTheme, paramAttributeSet, localObjectAnimator, paramFloat, paramXmlPullParser);
    return localObjectAnimator;
  }
  
  private static void parse(Path paramPath, ObjectAnimator paramObjectAnimator, float paramFloat, String paramString1, String paramString2)
  {
    Object localObject = new PathMeasure(paramPath, false);
    float f = 0.0F;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Float.valueOf(0.0F));
    for (;;)
    {
      f += ((PathMeasure)localObject).getLength();
      localArrayList.add(Float.valueOf(f));
      if (!((PathMeasure)localObject).nextContour())
      {
        paramPath = new PathMeasure(paramPath, false);
        int m = Math.min(100, (int)(f / paramFloat) + 1);
        float[] arrayOfFloat1 = new float[m];
        localObject = new float[m];
        float[] arrayOfFloat2 = new float[2];
        f /= (m - 1);
        paramFloat = 0.0F;
        int j = 0;
        int i = 0;
        while (i < m)
        {
          paramPath.getPosTan(paramFloat - ((Float)localArrayList.get(j)).floatValue(), arrayOfFloat2, null);
          arrayOfFloat1[i] = arrayOfFloat2[0];
          localObject[i] = arrayOfFloat2[1];
          paramFloat += f;
          int k = j;
          if (j + 1 < localArrayList.size())
          {
            k = j;
            if (paramFloat > ((Float)localArrayList.get(j + 1)).floatValue())
            {
              k = j + 1;
              paramPath.nextContour();
            }
          }
          i += 1;
          j = k;
        }
        paramPath = null;
        localArrayList = null;
        if (paramString1 != null) {
          paramPath = PropertyValuesHolder.ofFloat(paramString1, arrayOfFloat1);
        }
        paramString1 = localArrayList;
        if (paramString2 != null) {
          paramString1 = PropertyValuesHolder.ofFloat(paramString2, (float[])localObject);
        }
        if (paramPath == null)
        {
          paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramString1 });
          return;
        }
        if (paramString1 == null)
        {
          paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramPath });
          return;
        }
        paramObjectAnimator.setValues(new PropertyValuesHolder[] { paramPath, paramString1 });
        return;
      }
    }
  }
  
  private static Keyframe toString(Keyframe paramKeyframe, float paramFloat)
  {
    if (paramKeyframe.getType() == Float.TYPE) {
      return Keyframe.ofFloat(paramFloat);
    }
    if (paramKeyframe.getType() == Integer.TYPE) {
      return Keyframe.ofInt(paramFloat);
    }
    return Keyframe.ofObject(paramFloat);
  }
}
