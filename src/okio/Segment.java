package okio;

final class Segment
{
  final byte[] data;
  int limit;
  Segment next;
  boolean owner;
  int pos;
  Segment prev;
  boolean shared;
  
  Segment()
  {
    data = new byte['?'];
    owner = true;
    shared = false;
  }
  
  Segment(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    data = paramArrayOfByte;
    pos = paramInt1;
    limit = paramInt2;
    shared = paramBoolean1;
    owner = paramBoolean2;
  }
  
  public void compact()
  {
    Segment localSegment = prev;
    if (localSegment != this)
    {
      if (!owner) {
        return;
      }
      int j = limit - pos;
      int k = limit;
      int i;
      if (shared) {
        i = 0;
      } else {
        i = pos;
      }
      if (j > 8192 - k + i) {
        return;
      }
      writeTo(prev, j);
      pop();
      SegmentPool.recycle(this);
      return;
    }
    throw new IllegalStateException();
  }
  
  public Segment pop()
  {
    Segment localSegment1 = next;
    if (localSegment1 == this) {
      localSegment1 = null;
    }
    Segment localSegment2 = prev;
    next = next;
    next.prev = localSegment2;
    next = null;
    prev = null;
    return localSegment1;
  }
  
  public Segment push(Segment paramSegment)
  {
    prev = this;
    next = next;
    next.prev = paramSegment;
    next = paramSegment;
    return paramSegment;
  }
  
  public Segment split(int paramInt)
  {
    if ((paramInt > 0) && (paramInt <= limit - pos))
    {
      Object localObject;
      if (paramInt >= 1024)
      {
        localObject = write();
      }
      else
      {
        Segment localSegment = SegmentPool.take();
        localObject = localSegment;
        System.arraycopy(data, pos, data, 0, paramInt);
      }
      limit = (pos + paramInt);
      pos += paramInt;
      prev.push((Segment)localObject);
      return localObject;
    }
    throw new IllegalArgumentException();
  }
  
  Segment write()
  {
    shared = true;
    return new Segment(data, pos, limit, true, false);
  }
  
  public void writeTo(Segment paramSegment, int paramInt)
  {
    if (owner)
    {
      int i = limit;
      if (i + paramInt > 8192) {
        if (!shared)
        {
          int j = pos;
          if (i + paramInt - j <= 8192)
          {
            byte[] arrayOfByte = data;
            System.arraycopy(arrayOfByte, j, arrayOfByte, 0, i - j);
            limit -= pos;
            pos = 0;
          }
          else
          {
            throw new IllegalArgumentException();
          }
        }
        else
        {
          throw new IllegalArgumentException();
        }
      }
      System.arraycopy(data, pos, data, limit, paramInt);
      limit += paramInt;
      pos += paramInt;
      return;
    }
    throw new IllegalArgumentException();
  }
}
