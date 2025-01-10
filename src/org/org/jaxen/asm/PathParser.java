package org.org.jaxen.asm;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

public class PathParser
{
  private static void addNode(ArrayList paramArrayList, char paramChar, float[] paramArrayOfFloat)
  {
    paramArrayList.add(new PathDataNode(paramChar, paramArrayOfFloat));
  }
  
  public static boolean canMorph(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2)
  {
    if (paramArrayOfPathDataNode1 != null)
    {
      if (paramArrayOfPathDataNode2 == null) {
        return false;
      }
      if (paramArrayOfPathDataNode1.length != paramArrayOfPathDataNode2.length) {
        return false;
      }
      int i = 0;
      while (i < paramArrayOfPathDataNode1.length) {
        if (type == type)
        {
          if (params.length != params.length) {
            return false;
          }
          i += 1;
        }
        else
        {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  static float[] copyOfRange(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    if (paramInt1 <= paramInt2)
    {
      int i = paramArrayOfFloat.length;
      if ((paramInt1 >= 0) && (paramInt1 <= i))
      {
        paramInt2 -= paramInt1;
        i = Math.min(paramInt2, i - paramInt1);
        float[] arrayOfFloat = new float[paramInt2];
        System.arraycopy(paramArrayOfFloat, paramInt1, arrayOfFloat, 0, i);
        return arrayOfFloat;
      }
      throw new ArrayIndexOutOfBoundsException();
    }
    throw new IllegalArgumentException();
  }
  
  public static PathDataNode[] createNodesFromPathData(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    int i = 0;
    int j = 1;
    ArrayList localArrayList = new ArrayList();
    while (j < paramString.length())
    {
      j = nextStart(paramString, j);
      String str = paramString.substring(i, j).trim();
      if (str.length() > 0)
      {
        float[] arrayOfFloat = getFloats(str);
        addNode(localArrayList, str.charAt(0), arrayOfFloat);
      }
      i = j;
      j += 1;
    }
    if ((j - i == 1) && (i < paramString.length())) {
      addNode(localArrayList, paramString.charAt(i), new float[0]);
    }
    return (PathDataNode[])localArrayList.toArray(new PathDataNode[localArrayList.size()]);
  }
  
  public static Path createPathFromPathData(String paramString)
  {
    Path localPath = new Path();
    Object localObject = createNodesFromPathData(paramString);
    if (localObject != null) {
      try
      {
        PathDataNode.nodesToPath((PathDataNode[])localObject, localPath);
        return localPath;
      }
      catch (RuntimeException localRuntimeException)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Error in parsing ");
        ((StringBuilder)localObject).append(paramString);
        throw new RuntimeException(((StringBuilder)localObject).toString(), localRuntimeException);
      }
    }
    return null;
  }
  
  public static PathDataNode[] deepCopyNodes(PathDataNode[] paramArrayOfPathDataNode)
  {
    if (paramArrayOfPathDataNode == null) {
      return null;
    }
    PathDataNode[] arrayOfPathDataNode = new PathDataNode[paramArrayOfPathDataNode.length];
    int i = 0;
    while (i < paramArrayOfPathDataNode.length)
    {
      arrayOfPathDataNode[i] = new PathDataNode(paramArrayOfPathDataNode[i]);
      i += 1;
    }
    return arrayOfPathDataNode;
  }
  
  private static void extract(String paramString, int paramInt, ExtractFloatResult paramExtractFloatResult)
  {
    int m = paramInt;
    int n = 0;
    mEndWithNegOrDot = false;
    int k = 0;
    int j;
    for (int i1 = 0; m < paramString.length(); i1 = j)
    {
      int i3 = 0;
      int i = paramString.charAt(m);
      int i2;
      if (i != 32)
      {
        if ((i != 69) && (i != 101)) {}
        switch (i)
        {
        default: 
          i = n;
          i2 = k;
          j = i3;
          break;
        case 46: 
          if (k == 0)
          {
            i2 = 1;
            i = n;
            j = i3;
          }
          else
          {
            i = 1;
            mEndWithNegOrDot = true;
            i2 = k;
            j = i3;
          }
          break;
        case 45: 
          i = n;
          i2 = k;
          j = i3;
          if (m == paramInt) {
            break;
          }
          i = n;
          i2 = k;
          j = i3;
          if (i1 != 0) {
            break;
          }
          i = 1;
          mEndWithNegOrDot = true;
          i2 = k;
          j = i3;
          break;
          j = 1;
          i = n;
          i2 = k;
          break;
        }
      }
      else
      {
        i = 1;
        j = i3;
        i2 = k;
      }
      if (i != 0) {
        break;
      }
      m += 1;
      n = i;
      k = i2;
    }
    mEndPosition = m;
  }
  
  private static float[] getFloats(String paramString)
  {
    if ((paramString.charAt(0) != 'z') && (paramString.charAt(0) != 'Z')) {
      try
      {
        int i = paramString.length();
        float[] arrayOfFloat = new float[i];
        int j = 0;
        i = 1;
        localObject = new ExtractFloatResult();
        int n = paramString.length();
        while (i < n)
        {
          extract(paramString, i, (ExtractFloatResult)localObject);
          int m = mEndPosition;
          int k = j;
          if (i < m)
          {
            float f = Float.parseFloat(paramString.substring(i, m));
            arrayOfFloat[j] = f;
            k = j + 1;
          }
          if (mEndWithNegOrDot)
          {
            i = m;
            j = k;
          }
          else
          {
            i = m + 1;
            j = k;
          }
        }
        arrayOfFloat = copyOfRange(arrayOfFloat, 0, j);
        return arrayOfFloat;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("error in parsing \"");
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("\"");
        throw new RuntimeException(((StringBuilder)localObject).toString(), localNumberFormatException);
      }
    }
    return new float[0];
  }
  
  private static int nextStart(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((((i - 65) * (i - 90) <= 0) || ((i - 97) * (i - 122) <= 0)) && (i != 101) && (i != 69)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramInt;
  }
  
  public static void updateNodes(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2)
  {
    int i = 0;
    while (i < paramArrayOfPathDataNode2.length)
    {
      type = type;
      int j = 0;
      while (j < params.length)
      {
        params[j] = params[j];
        j += 1;
      }
      i += 1;
    }
  }
  
  class ExtractFloatResult
  {
    int mEndPosition;
    boolean mEndWithNegOrDot;
    
    ExtractFloatResult() {}
  }
  
  public class PathDataNode
  {
    public float[] params;
    public char type;
    
    PathDataNode(float[] paramArrayOfFloat)
    {
      type = this$1;
      params = paramArrayOfFloat;
    }
    
    PathDataNode()
    {
      type = type;
      this$1 = params;
      params = PathParser.copyOfRange(this$1, 0, this$1.length);
    }
    
    private static void addCommand(Path paramPath, float[] paramArrayOfFloat1, char paramChar1, char paramChar2, float[] paramArrayOfFloat2)
    {
      float f1 = paramArrayOfFloat1[0];
      float f3 = paramArrayOfFloat1[1];
      float f5 = paramArrayOfFloat1[2];
      float f6 = paramArrayOfFloat1[3];
      float f2 = paramArrayOfFloat1[4];
      float f4 = paramArrayOfFloat1[5];
      char c1;
      switch (paramChar2)
      {
      default: 
        c1 = '\002';
        break;
      case 'Z': 
      case 'z': 
        paramPath.close();
        f1 = f2;
        f3 = f4;
        f5 = f2;
        f6 = f4;
        paramPath.moveTo(f2, f4);
        c1 = '\002';
        break;
      case 'Q': 
      case 'S': 
      case 'q': 
      case 's': 
        c1 = '\004';
        break;
      case 'L': 
      case 'M': 
      case 'T': 
      case 'l': 
      case 'm': 
      case 't': 
        c1 = '\002';
        break;
      case 'H': 
      case 'V': 
      case 'h': 
      case 'v': 
        c1 = '\001';
        break;
      case 'C': 
      case 'c': 
        c1 = '\006';
        break;
      case 'A': 
      case 'a': 
        c1 = '\007';
      }
      char c2 = '\000';
      int i = paramChar1;
      paramChar1 = c2;
      float f7 = f4;
      float f8 = f2;
      float f9 = f6;
      float f10 = f5;
      f2 = f3;
      while (paramChar1 < paramArrayOfFloat2.length)
      {
        label1017:
        boolean bool1;
        boolean bool2;
        if (paramChar2 != 'A')
        {
          if (paramChar2 != 'C')
          {
            if (paramChar2 != 'H')
            {
              if (paramChar2 != 'Q')
              {
                if (paramChar2 != 'V')
                {
                  if (paramChar2 != 'a')
                  {
                    if (paramChar2 != 'c')
                    {
                      if (paramChar2 != 'h')
                      {
                        if (paramChar2 != 'q')
                        {
                          if (paramChar2 != 'v')
                          {
                            if (paramChar2 != 'L')
                            {
                              if (paramChar2 != 'M')
                              {
                                if (paramChar2 != 'S')
                                {
                                  if (paramChar2 != 'T')
                                  {
                                    if (paramChar2 != 'l')
                                    {
                                      if (paramChar2 != 'm')
                                      {
                                        if (paramChar2 != 's')
                                        {
                                          if (paramChar2 != 't')
                                          {
                                            f3 = f10;
                                            f4 = f9;
                                            f5 = f8;
                                            f6 = f7;
                                          }
                                          else
                                          {
                                            f4 = 0.0F;
                                            f3 = 0.0F;
                                            if ((i == 113) || (i == 116) || (i == 81) || (i == 84))
                                            {
                                              f4 = f1 - f10;
                                              f3 = f2 - f9;
                                            }
                                            paramPath.rQuadTo(f4, f3, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                            f4 = f1 + f4;
                                            f5 = f2 + f3;
                                            f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
                                            f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
                                            f3 = f4;
                                            f4 = f5;
                                            f5 = f8;
                                            f6 = f7;
                                          }
                                        }
                                        else
                                        {
                                          if ((i != 99) && (i != 115) && (i != 67) && (i != 83))
                                          {
                                            f3 = 0.0F;
                                            f4 = 0.0F;
                                          }
                                          else
                                          {
                                            f3 = f1 - f10;
                                            f4 = f2 - f9;
                                          }
                                          paramPath.rCubicTo(f3, f4, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
                                          f3 = paramArrayOfFloat2[(paramChar1 + '\000')] + f1;
                                          f4 = paramArrayOfFloat2[(paramChar1 + '\001')] + f2;
                                          f1 += paramArrayOfFloat2[(paramChar1 + '\002')];
                                          f2 += paramArrayOfFloat2[(paramChar1 + '\003')];
                                          f5 = f8;
                                          f6 = f7;
                                        }
                                      }
                                      else
                                      {
                                        f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
                                        f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
                                        if (paramChar1 > 0)
                                        {
                                          paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                          f3 = f10;
                                          f4 = f9;
                                          f5 = f8;
                                          f6 = f7;
                                        }
                                        else
                                        {
                                          paramPath.rMoveTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                          f5 = f1;
                                          f6 = f2;
                                          f3 = f10;
                                          f4 = f9;
                                        }
                                      }
                                    }
                                    else
                                    {
                                      paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                      f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
                                      f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
                                      f3 = f10;
                                      f4 = f9;
                                      f5 = f8;
                                      f6 = f7;
                                    }
                                  }
                                  else
                                  {
                                    f4 = f1;
                                    f3 = f2;
                                    if ((i == 113) || (i == 116) || (i == 81) || (i == 84))
                                    {
                                      f4 = f1 * 2.0F - f10;
                                      f3 = 2.0F * f2 - f9;
                                    }
                                    paramPath.quadTo(f4, f3, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                    f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
                                    f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
                                    f5 = f3;
                                    f3 = f4;
                                    f4 = f5;
                                    f5 = f8;
                                    f6 = f7;
                                  }
                                }
                                else
                                {
                                  if ((i != 99) && (i != 115) && (i != 67) && (i != 83)) {
                                    break label1017;
                                  }
                                  f1 = f1 * 2.0F - f10;
                                  f2 = 2.0F * f2 - f9;
                                  paramPath.cubicTo(f1, f2, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
                                  f3 = paramArrayOfFloat2[(paramChar1 + '\000')];
                                  f4 = paramArrayOfFloat2[(paramChar1 + '\001')];
                                  f1 = paramArrayOfFloat2[(paramChar1 + '\002')];
                                  f2 = paramArrayOfFloat2[(paramChar1 + '\003')];
                                  f5 = f8;
                                  f6 = f7;
                                }
                              }
                              else
                              {
                                f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
                                f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
                                if (paramChar1 > 0)
                                {
                                  paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                  f3 = f10;
                                  f4 = f9;
                                  f5 = f8;
                                  f6 = f7;
                                }
                                else
                                {
                                  paramPath.moveTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                                  f5 = f1;
                                  f6 = f2;
                                  f3 = f10;
                                  f4 = f9;
                                }
                              }
                            }
                            else
                            {
                              paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
                              f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
                              f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
                              f3 = f10;
                              f4 = f9;
                              f5 = f8;
                              f6 = f7;
                            }
                          }
                          else
                          {
                            paramPath.rLineTo(0.0F, paramArrayOfFloat2[(paramChar1 + '\000')]);
                            f2 += paramArrayOfFloat2[(paramChar1 + '\000')];
                            f3 = f10;
                            f4 = f9;
                            f5 = f8;
                            f6 = f7;
                          }
                        }
                        else
                        {
                          paramPath.rQuadTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
                          f3 = paramArrayOfFloat2[(paramChar1 + '\000')] + f1;
                          f4 = paramArrayOfFloat2[(paramChar1 + '\001')] + f2;
                          f1 += paramArrayOfFloat2[(paramChar1 + '\002')];
                          f2 += paramArrayOfFloat2[(paramChar1 + '\003')];
                          f5 = f8;
                          f6 = f7;
                        }
                      }
                      else
                      {
                        paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], 0.0F);
                        f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
                        f3 = f10;
                        f4 = f9;
                        f5 = f8;
                        f6 = f7;
                      }
                    }
                    else
                    {
                      paramPath.rCubicTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')], paramArrayOfFloat2[(paramChar1 + '\004')], paramArrayOfFloat2[(paramChar1 + '\005')]);
                      f3 = paramArrayOfFloat2[(paramChar1 + '\002')] + f1;
                      f4 = paramArrayOfFloat2[(paramChar1 + '\003')] + f2;
                      f1 += paramArrayOfFloat2[(paramChar1 + '\004')];
                      f2 += paramArrayOfFloat2[(paramChar1 + '\005')];
                      f5 = f8;
                      f6 = f7;
                    }
                  }
                  else
                  {
                    f3 = paramArrayOfFloat2[(paramChar1 + '\005')];
                    f4 = paramArrayOfFloat2[(paramChar1 + '\006')];
                    f5 = paramArrayOfFloat2[(paramChar1 + '\000')];
                    f6 = paramArrayOfFloat2[(paramChar1 + '\001')];
                    f9 = paramArrayOfFloat2[(paramChar1 + '\002')];
                    if (paramArrayOfFloat2[(paramChar1 + '\003')] != 0.0F) {
                      bool1 = true;
                    } else {
                      bool1 = false;
                    }
                    if (paramArrayOfFloat2[(paramChar1 + '\004')] != 0.0F) {
                      bool2 = true;
                    } else {
                      bool2 = false;
                    }
                    drawArc(paramPath, f1, f2, f3 + f1, f4 + f2, f5, f6, f9, bool1, bool2);
                    f1 += paramArrayOfFloat2[(paramChar1 + '\005')];
                    f2 += paramArrayOfFloat2[(paramChar1 + '\006')];
                    f3 = f1;
                    f4 = f2;
                    f5 = f8;
                    f6 = f7;
                  }
                }
                else
                {
                  paramPath.lineTo(f1, paramArrayOfFloat2[(paramChar1 + '\000')]);
                  f2 = paramArrayOfFloat2[(paramChar1 + '\000')];
                  f3 = f10;
                  f4 = f9;
                  f5 = f8;
                  f6 = f7;
                }
              }
              else
              {
                paramPath.quadTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
                f3 = paramArrayOfFloat2[(paramChar1 + '\000')];
                f4 = paramArrayOfFloat2[(paramChar1 + '\001')];
                f1 = paramArrayOfFloat2[(paramChar1 + '\002')];
                f2 = paramArrayOfFloat2[(paramChar1 + '\003')];
                f5 = f8;
                f6 = f7;
              }
            }
            else
            {
              paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], f2);
              f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
              f3 = f10;
              f4 = f9;
              f5 = f8;
              f6 = f7;
            }
          }
          else
          {
            paramPath.cubicTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')], paramArrayOfFloat2[(paramChar1 + '\004')], paramArrayOfFloat2[(paramChar1 + '\005')]);
            f1 = paramArrayOfFloat2[(paramChar1 + '\004')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\005')];
            f3 = paramArrayOfFloat2[(paramChar1 + '\002')];
            f4 = paramArrayOfFloat2[(paramChar1 + '\003')];
            f5 = f8;
            f6 = f7;
          }
        }
        else
        {
          i = paramChar1;
          f3 = paramArrayOfFloat2[(paramChar1 + '\005')];
          f4 = paramArrayOfFloat2[(paramChar1 + '\006')];
          f5 = paramArrayOfFloat2[(paramChar1 + '\000')];
          f6 = paramArrayOfFloat2[(paramChar1 + '\001')];
          f9 = paramArrayOfFloat2[(paramChar1 + '\002')];
          if (paramArrayOfFloat2[(paramChar1 + '\003')] != 0.0F) {
            bool1 = true;
          } else {
            bool1 = false;
          }
          if (paramArrayOfFloat2[(i + 4)] != 0.0F) {
            bool2 = true;
          } else {
            bool2 = false;
          }
          drawArc(paramPath, f1, f2, f3, f4, f5, f6, f9, bool1, bool2);
          f1 = paramArrayOfFloat2[(i + 5)];
          f2 = paramArrayOfFloat2[(i + 6)];
          f3 = f1;
          f4 = f2;
          f6 = f7;
          f5 = f8;
        }
        i = paramChar2;
        paramChar1 += c1;
        f10 = f3;
        f9 = f4;
        f8 = f5;
        f7 = f6;
      }
      paramArrayOfFloat1[0] = f1;
      paramArrayOfFloat1[1] = f2;
      paramArrayOfFloat1[2] = f10;
      paramArrayOfFloat1[3] = f9;
      paramArrayOfFloat1[4] = f8;
      paramArrayOfFloat1[5] = f7;
    }
    
    private static void arcToBezier(Path paramPath, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9)
    {
      int j = (int)Math.ceil(Math.abs(paramDouble9 * 4.0D / 3.141592653589793D));
      double d6 = Math.cos(paramDouble7);
      double d7 = Math.sin(paramDouble7);
      paramDouble7 = Math.cos(paramDouble8);
      double d2 = Math.sin(paramDouble8);
      double d1 = -paramDouble3;
      double d3 = -paramDouble3;
      double d4 = j;
      Double.isNaN(d4);
      double d8 = paramDouble9 / d4;
      d1 = d1 * d6 * d2 - paramDouble4 * d7 * paramDouble7;
      d2 = d3 * d7 * d2 + paramDouble4 * d6 * paramDouble7;
      int i = 0;
      paramDouble9 = paramDouble8;
      paramDouble8 = paramDouble6;
      paramDouble7 = paramDouble5;
      paramDouble6 = d1;
      paramDouble5 = d2;
      for (;;)
      {
        d4 = paramDouble3;
        if (i >= j) {
          break;
        }
        double d5 = paramDouble9 + d8;
        double d9 = Math.sin(d5);
        double d10 = Math.cos(d5);
        d3 = paramDouble1 + d4 * d6 * d10 - paramDouble4 * d7 * d9;
        d2 = paramDouble2 + d4 * d7 * d10 + paramDouble4 * d6 * d9;
        d1 = -d4 * d6 * d9 - paramDouble4 * d7 * d10;
        d4 = -d4 * d7 * d9 + paramDouble4 * d6 * d10;
        d9 = Math.tan((d5 - paramDouble9) / 2.0D);
        paramDouble9 = Math.sin(d5 - paramDouble9) * (Math.sqrt(d9 * 3.0D * d9 + 4.0D) - 1.0D) / 3.0D;
        paramPath.rLineTo(0.0F, 0.0F);
        paramPath.cubicTo((float)(paramDouble7 + paramDouble9 * paramDouble6), (float)(paramDouble8 + paramDouble9 * paramDouble5), (float)(d3 - paramDouble9 * d1), (float)(d2 - paramDouble9 * d4), (float)d3, (float)d2);
        paramDouble9 = d5;
        paramDouble7 = d3;
        paramDouble8 = d2;
        paramDouble6 = d1;
        paramDouble5 = d4;
        i += 1;
      }
    }
    
    private static void drawArc(Path paramPath, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean1, boolean paramBoolean2)
    {
      double d5 = Math.toRadians(paramFloat7);
      double d6 = Math.cos(d5);
      double d7 = Math.sin(d5);
      double d1 = paramFloat1;
      Double.isNaN(d1);
      double d2 = paramFloat2;
      Double.isNaN(d2);
      double d3 = paramFloat5;
      Double.isNaN(d3);
      d1 = (d1 * d6 + d2 * d7) / d3;
      d2 = -paramFloat1;
      Double.isNaN(d2);
      d3 = paramFloat2;
      Double.isNaN(d3);
      double d4 = paramFloat6;
      Double.isNaN(d4);
      d4 = (d2 * d7 + d3 * d6) / d4;
      d2 = paramFloat3;
      Double.isNaN(d2);
      d3 = paramFloat4;
      Double.isNaN(d3);
      double d8 = paramFloat5;
      Double.isNaN(d8);
      d8 = (d2 * d6 + d3 * d7) / d8;
      d2 = -paramFloat3;
      Double.isNaN(d2);
      d3 = paramFloat4;
      Double.isNaN(d3);
      double d9 = paramFloat6;
      Double.isNaN(d9);
      d9 = (d2 * d7 + d3 * d6) / d9;
      double d11 = d1 - d8;
      double d10 = d4 - d9;
      d3 = (d1 + d8) / 2.0D;
      d2 = (d4 + d9) / 2.0D;
      double d12 = d11 * d11 + d10 * d10;
      if (d12 == 0.0D)
      {
        Log.w("PathParser", " Points are coincident");
        return;
      }
      double d13 = 1.0D / d12 - 0.25D;
      if (d13 < 0.0D)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Points are too far apart ");
        localStringBuilder.append(d12);
        Log.w("PathParser", localStringBuilder.toString());
        float f = (float)(Math.sqrt(d12) / 1.99999D);
        drawArc(paramPath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5 * f, paramFloat6 * f, paramFloat7, paramBoolean1, paramBoolean2);
        return;
      }
      d12 = Math.sqrt(d13);
      d11 = d12 * d11;
      d10 = d12 * d10;
      if (paramBoolean1 == paramBoolean2)
      {
        d3 -= d10;
        d2 += d11;
      }
      else
      {
        d3 += d10;
        d2 -= d11;
      }
      d10 = Math.atan2(d4 - d2, d1 - d3);
      d4 = Math.atan2(d9 - d2, d8 - d3) - d10;
      if (d4 >= 0.0D) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      }
      d1 = d4;
      if (paramBoolean2 != paramBoolean1) {
        if (d4 > 0.0D) {
          d1 = d4 - 6.283185307179586D;
        } else {
          d1 = d4 + 6.283185307179586D;
        }
      }
      d4 = paramFloat5;
      Double.isNaN(d4);
      d3 *= d4;
      d4 = paramFloat6;
      Double.isNaN(d4);
      d2 = d4 * d2;
      arcToBezier(paramPath, d3 * d6 - d2 * d7, d3 * d7 + d2 * d6, paramFloat5, paramFloat6, paramFloat1, paramFloat2, d5, d10, d1);
    }
    
    public static void nodesToPath(PathDataNode[] paramArrayOfPathDataNode, Path paramPath)
    {
      float[] arrayOfFloat = new float[6];
      char c = 'm';
      int i = 0;
      while (i < paramArrayOfPathDataNode.length)
      {
        addCommand(paramPath, arrayOfFloat, c, type, params);
        c = type;
        i += 1;
      }
    }
    
    public void interpolatePathDataNode(PathDataNode paramPathDataNode1, PathDataNode paramPathDataNode2, float paramFloat)
    {
      int i = 0;
      for (;;)
      {
        float[] arrayOfFloat = params;
        if (i >= arrayOfFloat.length) {
          break;
        }
        params[i] = (arrayOfFloat[i] * (1.0F - paramFloat) + params[i] * paramFloat);
        i += 1;
      }
    }
  }
}
