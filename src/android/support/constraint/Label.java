package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Label
{
  private static final int[] end = { 0, 4, 8 };
  private static SparseIntArray phoneTypes = new SparseIntArray();
  private HashMap<Integer, c.b> c = new HashMap();
  
  static
  {
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintLeft_toRightOf, 26);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintRight_toLeftOf, 29);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintRight_toRightOf, 30);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintTop_toTopOf, 36);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintTop_toBottomOf, 35);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintBottom_toTopOf, 4);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
    phoneTypes.append(IpAddress.ConstraintSet_layout_editor_absoluteX, 6);
    phoneTypes.append(IpAddress.ConstraintSet_layout_editor_absoluteY, 7);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintGuide_begin, 17);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintGuide_end, 18);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintGuide_percent, 19);
    phoneTypes.append(IpAddress.ConstraintSet_android_orientation, 27);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintStart_toEndOf, 32);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintStart_toStartOf, 33);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintEnd_toStartOf, 10);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintEnd_toEndOf, 9);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginLeft, 13);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginTop, 16);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginRight, 14);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginBottom, 11);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginStart, 15);
    phoneTypes.append(IpAddress.ConstraintSet_layout_goneMarginEnd, 12);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintVertical_weight, 40);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHorizontal_weight, 39);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintVertical_chainStyle, 42);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHorizontal_bias, 20);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintVertical_bias, 37);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintDimensionRatio, 5);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintLeft_creator, 64);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintTop_creator, 64);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintRight_creator, 64);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintBottom_creator, 64);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintBaseline_creator, 64);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginLeft, 24);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginRight, 28);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginStart, 31);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginEnd, 8);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginTop, 34);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_marginBottom, 2);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_width, 23);
    phoneTypes.append(IpAddress.ConstraintSet_android_layout_height, 21);
    phoneTypes.append(IpAddress.ConstraintSet_android_visibility, 22);
    phoneTypes.append(IpAddress.ConstraintSet_android_alpha, 43);
    phoneTypes.append(IpAddress.ConstraintSet_android_elevation, 44);
    phoneTypes.append(IpAddress.ConstraintSet_android_rotationX, 45);
    phoneTypes.append(IpAddress.ConstraintSet_android_rotationY, 46);
    phoneTypes.append(IpAddress.ConstraintSet_android_rotation, 60);
    phoneTypes.append(IpAddress.ConstraintSet_android_scaleX, 47);
    phoneTypes.append(IpAddress.ConstraintSet_android_scaleY, 48);
    phoneTypes.append(IpAddress.ConstraintSet_android_transformPivotX, 49);
    phoneTypes.append(IpAddress.ConstraintSet_android_transformPivotY, 50);
    phoneTypes.append(IpAddress.ConstraintSet_android_translationX, 51);
    phoneTypes.append(IpAddress.ConstraintSet_android_translationY, 52);
    phoneTypes.append(IpAddress.ConstraintSet_android_translationZ, 53);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintWidth_default, 54);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHeight_default, 55);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintWidth_max, 56);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHeight_max, 57);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintWidth_min, 58);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintHeight_min, 59);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintCircle, 61);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintCircleRadius, 62);
    phoneTypes.append(IpAddress.ConstraintSet_layout_constraintCircleAngle, 63);
    phoneTypes.append(IpAddress.ConstraintSet_android_id, 38);
  }
  
  public Label() {}
  
  private R.id a(Context paramContext, AttributeSet paramAttributeSet)
  {
    R.id localId = new R.id(null);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintSet);
    run(localId, paramContext);
    paramContext.recycle();
    return localId;
  }
  
  private void run(R.id paramId, TypedArray paramTypedArray)
  {
    int j = paramTypedArray.getIndexCount();
    int i = 0;
    while (i < j)
    {
      int k = paramTypedArray.getIndex(i);
      int m = phoneTypes.get(k);
      switch (m)
      {
      default: 
        StringBuilder localStringBuilder;
        switch (m)
        {
        default: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(phoneTypes.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 64: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("unused attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(phoneTypes.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 63: 
          d = paramTypedArray.getFloat(k, d);
          break;
        case 62: 
          n = paramTypedArray.getDimensionPixelSize(k, n);
          break;
        case 61: 
          value = valueOf(paramTypedArray, k, value);
          break;
        case 60: 
          next = paramTypedArray.getFloat(k, next);
        }
        break;
      case 53: 
        size = paramTypedArray.getDimension(k, size);
        break;
      case 52: 
        m = paramTypedArray.getDimension(k, m);
        break;
      case 51: 
        h = paramTypedArray.getDimension(k, h);
        break;
      case 50: 
        j = paramTypedArray.getFloat(k, j);
        break;
      case 49: 
        c = paramTypedArray.getFloat(k, c);
        break;
      case 48: 
        s = paramTypedArray.getFloat(k, s);
        break;
      case 47: 
        z = paramTypedArray.getFloat(k, z);
        break;
      case 46: 
        flags = paramTypedArray.getFloat(k, flags);
        break;
      case 45: 
        buffer = paramTypedArray.getFloat(k, buffer);
        break;
      case 44: 
        height = true;
        parent = paramTypedArray.getDimension(k, parent);
        break;
      case 43: 
        name = paramTypedArray.getFloat(k, name);
        break;
      case 42: 
        color = paramTypedArray.getInt(k, color);
        break;
      case 41: 
        count = paramTypedArray.getInt(k, count);
        break;
      case 40: 
        start = paramTypedArray.getFloat(k, start);
        break;
      case 39: 
        title = paramTypedArray.getFloat(k, title);
        break;
      case 38: 
        i = paramTypedArray.getResourceId(k, i);
        break;
      case 37: 
        state = paramTypedArray.getFloat(k, state);
        break;
      case 36: 
        startTime = valueOf(paramTypedArray, k, startTime);
        break;
      case 35: 
        length = valueOf(paramTypedArray, k, length);
        break;
      case 34: 
        items = paramTypedArray.getDimensionPixelSize(k, items);
        break;
      case 33: 
        port = valueOf(paramTypedArray, k, port);
        break;
      case 32: 
        group = valueOf(paramTypedArray, k, group);
        break;
      case 31: 
        type = paramTypedArray.getDimensionPixelSize(k, type);
        break;
      case 30: 
        a = valueOf(paramTypedArray, k, a);
        break;
      case 29: 
        radius = valueOf(paramTypedArray, k, radius);
        break;
      case 28: 
        width = paramTypedArray.getDimensionPixelSize(k, width);
        break;
      case 27: 
        y = paramTypedArray.getInt(k, y);
        break;
      case 26: 
        w = valueOf(paramTypedArray, k, w);
        break;
      case 25: 
        e = valueOf(paramTypedArray, k, e);
        break;
      case 24: 
        p = paramTypedArray.getDimensionPixelSize(k, p);
        break;
      case 23: 
        t = paramTypedArray.getLayoutDimension(k, t);
        break;
      case 22: 
        index = paramTypedArray.getInt(k, index);
        index = end[index];
        break;
      case 21: 
        q = paramTypedArray.getLayoutDimension(k, q);
        break;
      case 20: 
        x = paramTypedArray.getFloat(k, x);
        break;
      case 19: 
        b = paramTypedArray.getFloat(k, b);
        break;
      case 18: 
        f = paramTypedArray.getDimensionPixelOffset(k, f);
        break;
      case 17: 
        g = paramTypedArray.getDimensionPixelOffset(k, g);
        break;
      case 16: 
        location = paramTypedArray.getDimensionPixelSize(k, location);
        break;
      case 15: 
        line = paramTypedArray.getDimensionPixelSize(k, line);
        break;
      case 14: 
        status = paramTypedArray.getDimensionPixelSize(k, status);
        break;
      case 13: 
        end = paramTypedArray.getDimensionPixelSize(k, end);
        break;
      case 12: 
        offset = paramTypedArray.getDimensionPixelSize(k, offset);
        break;
      case 11: 
        position = paramTypedArray.getDimensionPixelSize(k, position);
        break;
      case 10: 
        data = valueOf(paramTypedArray, k, data);
        break;
      case 9: 
        id = valueOf(paramTypedArray, k, id);
        break;
      case 8: 
        url = paramTypedArray.getDimensionPixelSize(k, url);
        break;
      case 7: 
        text = paramTypedArray.getDimensionPixelOffset(k, text);
        break;
      case 6: 
        level = paramTypedArray.getDimensionPixelOffset(k, level);
        break;
      case 5: 
        path = paramTypedArray.getString(k);
        break;
      case 4: 
        version = valueOf(paramTypedArray, k, version);
        break;
      case 3: 
        key = valueOf(paramTypedArray, k, key);
        break;
      case 2: 
        context = paramTypedArray.getDimensionPixelSize(k, context);
        break;
      case 1: 
        input = valueOf(paramTypedArray, k, input);
      }
      i += 1;
    }
  }
  
  private static int valueOf(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    paramInt2 = paramTypedArray.getResourceId(paramInt1, paramInt2);
    if (paramInt2 == -1) {
      return paramTypedArray.getInt(paramInt1, -1);
    }
    return paramInt2;
  }
  
  public void a(Context paramContext, int paramInt)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(paramInt);
    try
    {
      for (paramInt = localXmlResourceParser.getEventType(); paramInt != 1; paramInt = localXmlResourceParser.next()) {
        if (paramInt != 0)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3) {}
          }
          else
          {
            Object localObject = localXmlResourceParser.getName();
            R.id localId = a(paramContext, Xml.asAttributeSet(localXmlResourceParser));
            boolean bool = ((String)localObject).equalsIgnoreCase("Guideline");
            if (bool) {
              r = true;
            }
            localObject = c;
            paramInt = i;
            ((HashMap)localObject).put(Integer.valueOf(paramInt), localId);
          }
        }
        else {
          localXmlResourceParser.getName();
        }
      }
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void a(Toolbar paramToolbar)
  {
    int j = paramToolbar.getChildCount();
    c.clear();
    int i = 0;
    while (i < j)
    {
      View localView = paramToolbar.getChildAt(i);
      ClassWriter localClassWriter = (ClassWriter)localView.getLayoutParams();
      int k = localView.getId();
      if (k != -1)
      {
        if (!c.containsKey(Integer.valueOf(k))) {
          c.put(Integer.valueOf(k), new R.id(null));
        }
        R.id localId = (R.id)c.get(Integer.valueOf(k));
        if ((localView instanceof Item)) {
          R.id.write(localId, (Item)localView, k, localClassWriter);
        }
        R.id.write(localId, k, localClassWriter);
        i += 1;
      }
      else
      {
        throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
      }
    }
  }
  
  void update(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    Object localObject1 = new HashSet(c.keySet());
    int i = 0;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    while (i < j)
    {
      localObject2 = paramConstraintLayout.getChildAt(i);
      int k = ((View)localObject2).getId();
      if (k != -1)
      {
        if (c.containsKey(Integer.valueOf(k)))
        {
          ((HashSet)localObject1).remove(Integer.valueOf(k));
          localObject3 = (R.id)c.get(Integer.valueOf(k));
          int m = s2;
          if ((m != -1) && (m == 1))
          {
            localObject4 = (FloatingActionButton)localObject2;
            ((View)localObject4).setId(k);
            ((Item)localObject4).setReferencedIds(bytes);
            ((FloatingActionButton)localObject4).setType(s1);
            ((R.id)localObject3).init(paramConstraintLayout.generateDefaultLayoutParams());
          }
          localObject4 = (ConstraintLayout.a)((View)localObject2).getLayoutParams();
          ((R.id)localObject3).init((ConstraintLayout.a)localObject4);
          ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
          ((View)localObject2).setVisibility(index);
          if (Build.VERSION.SDK_INT >= 17)
          {
            ((View)localObject2).setAlpha(name);
            ((View)localObject2).setRotation(next);
            ((View)localObject2).setRotationX(buffer);
            ((View)localObject2).setRotationY(flags);
            ((View)localObject2).setScaleX(z);
            ((View)localObject2).setScaleY(s);
            if (!Float.isNaN(c)) {
              ((View)localObject2).setPivotX(c);
            }
            if (!Float.isNaN(j)) {
              ((View)localObject2).setPivotY(j);
            }
            ((View)localObject2).setTranslationX(h);
            ((View)localObject2).setTranslationY(m);
            if (Build.VERSION.SDK_INT >= 21)
            {
              ((View)localObject2).setTranslationZ(size);
              if (height) {
                ((View)localObject2).setElevation(parent);
              }
            }
          }
        }
        i += 1;
      }
      else
      {
        throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
      }
    }
    localObject1 = ((HashSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Integer)((Iterator)localObject1).next();
      localObject2 = (R.id)c.get(localObject3);
      i = s2;
      if ((i != -1) && (i == 1))
      {
        localObject4 = new FloatingActionButton(paramConstraintLayout.getContext());
        ((View)localObject4).setId(((Integer)localObject3).intValue());
        ((Item)localObject4).setReferencedIds(bytes);
        ((FloatingActionButton)localObject4).setType(s1);
        ConstraintLayout.a localA = paramConstraintLayout.generateDefaultLayoutParams();
        ((R.id)localObject2).init(localA);
        paramConstraintLayout.addView((View)localObject4, localA);
      }
      if (r)
      {
        localObject4 = new ProgressBar(paramConstraintLayout.getContext());
        ((View)localObject4).setId(((Integer)localObject3).intValue());
        localObject3 = paramConstraintLayout.generateDefaultLayoutParams();
        ((R.id)localObject2).init((ConstraintLayout.a)localObject3);
        paramConstraintLayout.addView((View)localObject4, (ViewGroup.LayoutParams)localObject3);
      }
    }
  }
}
