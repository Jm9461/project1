package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Icon;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class IconCompat
  extends CustomVersionedParcelable
{
  static final PorterDuff.Mode o = PorterDuff.Mode.SRC_IN;
  public byte[] a;
  public int b;
  public int c;
  Object e;
  PorterDuff.Mode f = o;
  public ColorStateList g = null;
  public int p;
  public String s;
  public Parcelable x;
  
  public IconCompat() {}
  
  private static int a(Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResId();
    }
    try
    {
      Object localObject = paramIcon.getClass();
      localObject = ((Class)localObject).getMethod("getResId", new Class[0]);
      paramIcon = ((Method)localObject).invoke(paramIcon, new Object[0]);
      paramIcon = (Integer)paramIcon;
      int i = paramIcon.intValue();
      return i;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
      return 0;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon resource", paramIcon);
    }
    return 0;
  }
  
  private static String getType(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 3)
        {
          if (paramInt != 4)
          {
            if (paramInt != 5) {
              return "UNKNOWN";
            }
            return "BITMAP_MASKABLE";
          }
          return "URI";
        }
        return "DATA";
      }
      return "RESOURCE";
    }
    return "BITMAP";
  }
  
  private static String read(Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramIcon.getResPackage();
    }
    try
    {
      Object localObject = paramIcon.getClass();
      localObject = ((Class)localObject).getMethod("getResPackage", new Class[0]);
      paramIcon = ((Method)localObject).invoke(paramIcon, new Object[0]);
      return (String)paramIcon;
    }
    catch (NoSuchMethodException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (InvocationTargetException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
      return null;
    }
    catch (IllegalAccessException paramIcon)
    {
      Log.e("IconCompat", "Unable to get icon package", paramIcon);
    }
    return null;
  }
  
  public int a()
  {
    if ((c == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return a((Icon)e);
    }
    if (c == 2) {
      return b;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResId() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public void encode(boolean paramBoolean)
  {
    s = f.name();
    int i = c;
    if (i != -1)
    {
      if (i != 1) {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 4)
            {
              if (i == 5) {}
            }
            else {
              a = e.toString().getBytes(Charset.forName("UTF-16"));
            }
          }
          else {
            a = ((byte[])e);
          }
        }
        else
        {
          a = ((String)e).getBytes(Charset.forName("UTF-16"));
          return;
        }
      }
      if (paramBoolean)
      {
        Bitmap localBitmap = (Bitmap)e;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localBitmap.compress(Bitmap.CompressFormat.PNG, 90, localByteArrayOutputStream);
        a = localByteArrayOutputStream.toByteArray();
        return;
      }
      x = ((Parcelable)e);
      return;
    }
    if (!paramBoolean)
    {
      x = ((Parcelable)e);
      return;
    }
    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
  }
  
  public void init()
  {
    f = PorterDuff.Mode.valueOf(s);
    int i = c;
    if (i != -1)
    {
      if (i != 1)
      {
        if (i != 2) {
          if (i != 3)
          {
            if (i != 4) {
              if (i == 5) {
                break label77;
              }
            }
          }
          else
          {
            e = a;
            return;
          }
        }
        e = new String(a, Charset.forName("UTF-16"));
        return;
      }
      label77:
      localObject = x;
      if (localObject != null)
      {
        e = localObject;
        return;
      }
      localObject = a;
      e = localObject;
      c = 3;
      b = 0;
      p = localObject.length;
      return;
    }
    Object localObject = x;
    if (localObject != null)
    {
      e = localObject;
      return;
    }
    throw new IllegalArgumentException("Invalid icon");
  }
  
  public String read()
  {
    if ((c == -1) && (Build.VERSION.SDK_INT >= 23)) {
      return read((Icon)e);
    }
    if (c == 2) {
      return ((String)e).split(":", -1)[0];
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("called getResPackage() on ");
    localStringBuilder.append(this);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public String toString()
  {
    if (c == -1) {
      return String.valueOf(e);
    }
    StringBuilder localStringBuilder = new StringBuilder("Icon(typ=").append(getType(c));
    int i = c;
    if (i != 1) {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i != 4)
          {
            if (i != 5) {
              break label225;
            }
          }
          else
          {
            localStringBuilder.append(" uri=");
            localStringBuilder.append(e);
            break label225;
          }
        }
        else
        {
          localStringBuilder.append(" len=");
          localStringBuilder.append(b);
          if (p == 0) {
            break label225;
          }
          localStringBuilder.append(" off=");
          localStringBuilder.append(p);
          break label225;
        }
      }
      else
      {
        localStringBuilder.append(" pkg=");
        localStringBuilder.append(read());
        localStringBuilder.append(" id=");
        localStringBuilder.append(String.format("0x%08x", new Object[] { Integer.valueOf(a()) }));
        break label225;
      }
    }
    localStringBuilder.append(" size=");
    localStringBuilder.append(((Bitmap)e).getWidth());
    localStringBuilder.append("x");
    localStringBuilder.append(((Bitmap)e).getHeight());
    label225:
    if (g != null)
    {
      localStringBuilder.append(" tint=");
      localStringBuilder.append(g);
    }
    if (f != o)
    {
      localStringBuilder.append(" mode=");
      localStringBuilder.append(f);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
