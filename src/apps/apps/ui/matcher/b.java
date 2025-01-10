package apps.apps.ui.matcher;

import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import java.text.ParseException;

public class b
{
  private int a;
  private int b;
  private String f;
  private PointF x = new PointF();
  private int y;
  
  public b() {}
  
  private float a()
  {
    write();
    if (b == 3)
    {
      int j = 1;
      int k = 0;
      int i = a;
      while (i < y)
      {
        int m = f.charAt(i);
        if (((48 > m) || (m > 57)) && ((m != 46) || (k != 0)) && ((m != 45) || (j == 0))) {
          break;
        }
        if (m == 46) {
          k = 1;
        }
        j = 0;
        i += 1;
      }
      j = a;
      if (i != j)
      {
        localObject = f.substring(j, i);
        try
        {
          float f1 = Float.parseFloat((String)localObject);
          a = i;
          return f1;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid float value '");
          localStringBuilder.append((String)localObject);
          localStringBuilder.append("'.");
          throw new ParseException(localStringBuilder.toString(), a);
        }
      }
      throw new ParseException("Expected value", j);
    }
    Object localObject = new ParseException("Expected value", a);
    throw ((Throwable)localObject);
  }
  
  private void a(PointF paramPointF, boolean paramBoolean)
  {
    x = b(a());
    y = a(a());
    if (paramBoolean)
    {
      float f1 = x;
      PointF localPointF = x;
      x = (f1 + x);
      y += y;
    }
  }
  
  private char close()
  {
    write();
    int i = b;
    if ((i != 2) && (i != 1)) {
      throw new ParseException("Expected command", a);
    }
    String str = f;
    i = a;
    a = (i + 1);
    return str.charAt(i);
  }
  
  private int write()
  {
    for (;;)
    {
      int i = a;
      if (i >= y) {
        break label107;
      }
      i = f.charAt(i);
      if ((97 <= i) && (i <= 122))
      {
        b = 2;
        return 2;
      }
      if ((65 <= i) && (i <= 90))
      {
        b = 1;
        return 1;
      }
      if (((48 <= i) && (i <= 57)) || (i == 46) || (i == 45)) {
        break;
      }
      a += 1;
    }
    b = 3;
    return 3;
    label107:
    b = 4;
    return 4;
  }
  
  protected float a(float paramFloat)
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  protected float b(float paramFloat)
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public Path parsePath(String paramString)
  {
    x.set(NaN.0F, NaN.0F);
    f = paramString;
    a = 0;
    y = f.length();
    paramString = new PointF();
    PointF localPointF1 = new PointF();
    PointF localPointF2 = new PointF();
    Path localPath = new Path();
    localPath.setFillType(Path.FillType.WINDING);
    int i = 1;
    while (a < y)
    {
      int j = close();
      boolean bool1;
      if (b == 2) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if (j != 67)
      {
        label396:
        float f2;
        float f1;
        if (j != 72)
        {
          if (j != 86)
          {
            if (j != 90)
            {
              if (j == 99) {
                break label572;
              }
              if (j == 104) {
                break label484;
              }
              if (j == 118) {
                break label396;
              }
              if (j != 122)
              {
                if (j != 76) {
                  if (j != 77)
                  {
                    if (j != 108) {
                      if (j != 109) {
                        break label661;
                      }
                    }
                  }
                  else
                  {
                    j = 1;
                    while (write() == 3)
                    {
                      boolean bool2;
                      if ((bool1) && (x.x != NaN.0F)) {
                        bool2 = true;
                      } else {
                        bool2 = false;
                      }
                      a(paramString, bool2);
                      if (j != 0)
                      {
                        localPath.moveTo(x, y);
                        int k = 0;
                        j = k;
                        if (i != 0)
                        {
                          x.set(paramString);
                          i = 0;
                          j = k;
                        }
                      }
                      else
                      {
                        localPath.lineTo(x, y);
                      }
                    }
                    x.set(paramString);
                    break label661;
                  }
                }
                if (x.x != NaN.0F)
                {
                  while (write() == 3)
                  {
                    a(paramString, bool1);
                    localPath.lineTo(x, y);
                  }
                  x.set(paramString);
                  break label661;
                }
                throw new ParseException("Relative commands require current point", a);
              }
            }
            localPath.close();
            break label661;
          }
          if (x.x != NaN.0F)
          {
            while (write() == 3)
            {
              f2 = a(a());
              f1 = f2;
              if (bool1) {
                f1 = f2 + x.y;
              }
              localPath.lineTo(x.x, f1);
            }
            x.set(paramString);
            break label661;
          }
          throw new ParseException("Relative commands require current point", a);
        }
        label484:
        if (x.x != NaN.0F)
        {
          while (write() == 3)
          {
            f2 = b(a());
            f1 = f2;
            if (bool1) {
              f1 = f2 + x.x;
            }
            localPath.lineTo(f1, x.y);
          }
          x.set(paramString);
          break label661;
        }
        throw new ParseException("Relative commands require current point", a);
      }
      label572:
      if (x.x != NaN.0F)
      {
        while (write() == 3)
        {
          a(paramString, bool1);
          a(localPointF1, bool1);
          a(localPointF2, bool1);
          localPath.cubicTo(x, y, x, y, x, y);
        }
        x.set(localPointF2);
      }
      else
      {
        label661:
        throw new ParseException("Relative commands require current point", a);
      }
    }
    return localPath;
  }
}
