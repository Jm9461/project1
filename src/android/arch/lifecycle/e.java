package android.arch.lifecycle;

class e
{
  f a;
  GenericLifecycleObserver b;
  
  e(MethodVisitor paramMethodVisitor, f paramF)
  {
    b = Frame.a(paramMethodVisitor);
    a = paramF;
  }
  
  void a(h paramH, c paramC)
  {
    f localF = m.a(paramC);
    a = m.a(a, localF);
    b.b(paramH, paramC);
    a = localF;
  }
}
