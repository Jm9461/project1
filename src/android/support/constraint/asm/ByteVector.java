package android.support.constraint.asm;

abstract interface ByteVector
{
  public abstract Label a(i paramI, boolean[] paramArrayOfBoolean);
  
  public abstract void a(ByteVector paramByteVector);
  
  public abstract void b(Label paramLabel);
  
  public abstract void clear();
  
  public abstract Label getKey();
}
