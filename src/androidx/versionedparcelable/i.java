package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

class i
  extends h
{
  private final SparseIntArray a = new SparseIntArray();
  private final int b;
  private int c = 0;
  private final int e;
  private int k = -1;
  private final String n;
  private final Parcel s;
  
  i(Parcel paramParcel)
  {
    this(paramParcel, paramParcel.dataPosition(), paramParcel.dataSize(), "");
  }
  
  i(Parcel paramParcel, int paramInt1, int paramInt2, String paramString)
  {
    s = paramParcel;
    e = paramInt1;
    b = paramInt2;
    c = e;
    n = paramString;
  }
  
  private int a(int paramInt)
  {
    int j;
    do
    {
      int i = c;
      if (i >= b) {
        break;
      }
      s.setDataPosition(i);
      i = s.readInt();
      j = s.readInt();
      c += i;
    } while (j != paramInt);
    return s.dataPosition();
    return -1;
  }
  
  public int a()
  {
    return s.readInt();
  }
  
  public void a(String paramString)
  {
    s.writeString(paramString);
  }
  
  public void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      s.writeInt(paramArrayOfByte.length);
      s.writeByteArray(paramArrayOfByte);
      return;
    }
    s.writeInt(-1);
  }
  
  public void b()
  {
    int i = k;
    if (i >= 0)
    {
      i = a.get(i);
      int j = s.dataPosition();
      s.setDataPosition(i);
      s.writeInt(j - i);
      s.setDataPosition(j);
    }
  }
  
  public void b(Parcelable paramParcelable)
  {
    s.writeParcelable(paramParcelable, 0);
  }
  
  public boolean b(int paramInt)
  {
    paramInt = a(paramInt);
    if (paramInt == -1) {
      return false;
    }
    s.setDataPosition(paramInt);
    return true;
  }
  
  protected h c()
  {
    Parcel localParcel = s;
    int m = localParcel.dataPosition();
    int j = c;
    int i = j;
    if (j == e) {
      i = b;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(n);
    localStringBuilder.append("  ");
    return new i(localParcel, m, i, localStringBuilder.toString());
  }
  
  public void c(int paramInt)
  {
    b();
    k = paramInt;
    a.put(paramInt, s.dataPosition());
    l(0);
    l(paramInt);
  }
  
  public Parcelable d()
  {
    return s.readParcelable(getClass().getClassLoader());
  }
  
  public String getValue()
  {
    return s.readString();
  }
  
  public void l(int paramInt)
  {
    s.writeInt(paramInt);
  }
  
  public byte[] read()
  {
    int i = s.readInt();
    if (i < 0) {
      return null;
    }
    byte[] arrayOfByte = new byte[i];
    s.readByteArray(arrayOfByte);
    return arrayOfByte;
  }
}
