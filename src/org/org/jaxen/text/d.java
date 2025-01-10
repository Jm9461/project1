package org.org.jaxen.text;

abstract class d
  implements TextDirectionHeuristicCompat
{
  private final TextDirectionHeuristicsCompat.TextDirectionAlgorithm G;
  
  d(TextDirectionHeuristicsCompat.TextDirectionAlgorithm paramTextDirectionAlgorithm)
  {
    G = paramTextDirectionAlgorithm;
  }
  
  private boolean b(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    paramInt1 = G.checkRtl(paramCharSequence, paramInt1, paramInt2);
    if (paramInt1 != 0)
    {
      if (paramInt1 != 1) {
        return c();
      }
      return false;
    }
    return true;
  }
  
  public boolean a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if ((paramCharSequence != null) && (paramInt1 >= 0) && (paramInt2 >= 0) && (paramCharSequence.length() - paramInt2 >= paramInt1))
    {
      if (G == null) {
        return c();
      }
      return b(paramCharSequence, paramInt1, paramInt2);
    }
    throw new IllegalArgumentException();
  }
  
  protected abstract boolean c();
}
