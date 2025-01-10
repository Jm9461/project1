package okio;

import java.util.Arrays;

final class SegmentedByteString
  extends ByteString
{
  final transient int[] directory;
  final transient byte[][] segments;
  
  SegmentedByteString(Buffer paramBuffer, int paramInt)
  {
    super(null);
    Util.checkOffsetAndCount(size, 0L, paramInt);
    int j = 0;
    int i = 0;
    Object localObject = head;
    int k;
    while (j < paramInt)
    {
      k = limit;
      int m = pos;
      if (k != m)
      {
        j += k - m;
        i += 1;
        localObject = next;
      }
      else
      {
        throw new AssertionError("s.limit == s.pos");
      }
    }
    segments = new byte[i][];
    directory = new int[i * 2];
    i = 0;
    j = 0;
    for (paramBuffer = head; i < paramInt; paramBuffer = next)
    {
      segments[j] = data;
      k = i + (limit - pos);
      i = k;
      if (k > paramInt) {
        i = paramInt;
      }
      localObject = directory;
      localObject[j] = i;
      localObject[(segments.length + j)] = pos;
      shared = true;
      j += 1;
    }
  }
  
  private ByteString toByteString()
  {
    return new ByteString(toByteArray());
  }
  
  private int write(int paramInt)
  {
    paramInt = Arrays.binarySearch(directory, 0, segments.length, paramInt + 1);
    if (paramInt >= 0) {
      return paramInt;
    }
    return paramInt;
  }
  
  public String base64()
  {
    return toByteString().base64();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    return ((paramObject instanceof ByteString)) && (((ByteString)paramObject).size() == size()) && (write(0, (ByteString)paramObject, 0, size()));
  }
  
  public int hashCode()
  {
    int i = hashCode;
    if (i != 0) {
      return i;
    }
    int k = 1;
    i = 0;
    int j = 0;
    int i2 = segments.length;
    while (j < i2)
    {
      byte[] arrayOfByte = segments[j];
      int[] arrayOfInt = directory;
      int n = arrayOfInt[(i2 + j)];
      int i1 = arrayOfInt[j];
      int m = n;
      while (m < n + (i1 - i))
      {
        k = k * 31 + arrayOfByte[m];
        m += 1;
      }
      i = i1;
      j += 1;
    }
    hashCode = k;
    return k;
  }
  
  public String hex()
  {
    return toByteString().hex();
  }
  
  public ByteString md5()
  {
    return toByteString().md5();
  }
  
  public byte read(int paramInt)
  {
    Util.checkOffsetAndCount(directory[(segments.length - 1)], paramInt, 1L);
    int j = write(paramInt);
    int i;
    if (j == 0) {
      i = 0;
    } else {
      i = directory[(j - 1)];
    }
    int[] arrayOfInt = directory;
    byte[][] arrayOfByte = segments;
    int k = arrayOfInt[(arrayOfByte.length + j)];
    return arrayOfByte[j][(paramInt - i + k)];
  }
  
  public ByteString sha256()
  {
    return toByteString().sha256();
  }
  
  public int size()
  {
    return directory[(segments.length - 1)];
  }
  
  public ByteString substring()
  {
    return toByteString().substring();
  }
  
  public ByteString substring(int paramInt1, int paramInt2)
  {
    return toByteString().substring(paramInt1, paramInt2);
  }
  
  public ByteString toAsciiLowercase()
  {
    return toByteString().toAsciiLowercase();
  }
  
  public byte[] toByteArray()
  {
    Object localObject2 = directory;
    Object localObject1 = segments;
    localObject2 = new byte[localObject2[(localObject1.length - 1)]];
    int j = 0;
    int i = 0;
    int m = localObject1.length;
    while (i < m)
    {
      localObject1 = directory;
      int n = localObject1[(m + i)];
      int k = localObject1[i];
      System.arraycopy(segments[i], n, localObject2, j, k - j);
      j = k;
      i += 1;
    }
    return localObject2;
  }
  
  public String toString()
  {
    return toByteString().toString();
  }
  
  public String utf8()
  {
    return toByteString().utf8();
  }
  
  void write(Buffer paramBuffer)
  {
    int j = 0;
    int i = 0;
    int m = segments.length;
    while (i < m)
    {
      Object localObject = directory;
      int n = localObject[(m + i)];
      int k = localObject[i];
      localObject = new Segment(segments[i], n, n + k - j, true, false);
      Segment localSegment = head;
      if (localSegment == null)
      {
        prev = ((Segment)localObject);
        next = ((Segment)localObject);
        head = ((Segment)localObject);
      }
      else
      {
        prev.push((Segment)localObject);
      }
      j = k;
      i += 1;
    }
    size += j;
  }
  
  public boolean write(int paramInt1, ByteString paramByteString, int paramInt2, int paramInt3)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt1 > size() - paramInt3) {
        return false;
      }
      int j = write(paramInt1);
      int i = paramInt1;
      paramInt1 = j;
      while (paramInt3 > 0)
      {
        if (paramInt1 == 0) {
          j = 0;
        } else {
          j = directory[(paramInt1 - 1)];
        }
        int k = Math.min(paramInt3, j + (directory[paramInt1] - j) - i);
        int[] arrayOfInt = directory;
        byte[][] arrayOfByte = segments;
        int m = arrayOfInt[(arrayOfByte.length + paramInt1)];
        if (!paramByteString.write(paramInt2, arrayOfByte[paramInt1], i - j + m, k)) {
          return false;
        }
        i += k;
        paramInt2 += k;
        paramInt3 -= k;
        paramInt1 += 1;
      }
      return true;
    }
    return false;
  }
  
  public boolean write(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if ((paramInt1 >= 0) && (paramInt1 <= size() - paramInt3) && (paramInt2 >= 0))
    {
      if (paramInt2 > paramArrayOfByte.length - paramInt3) {
        return false;
      }
      int j = write(paramInt1);
      int i = paramInt1;
      paramInt1 = j;
      while (paramInt3 > 0)
      {
        if (paramInt1 == 0) {
          j = 0;
        } else {
          j = directory[(paramInt1 - 1)];
        }
        int k = Math.min(paramInt3, j + (directory[paramInt1] - j) - i);
        int[] arrayOfInt = directory;
        byte[][] arrayOfByte = segments;
        int m = arrayOfInt[(arrayOfByte.length + paramInt1)];
        if (!Util.add(arrayOfByte[paramInt1], i - j + m, paramArrayOfByte, paramInt2, k)) {
          return false;
        }
        i += k;
        paramInt2 += k;
        paramInt3 -= k;
        paramInt1 += 1;
      }
      return true;
    }
    return false;
  }
}
