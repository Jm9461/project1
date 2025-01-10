package org.org.jraf.drawable;

import a.b.g.a.b.b;
import android.animation.TypeEvaluator;
import org.org.jaxen.asm.PathParser;
import org.org.jaxen.asm.PathParser.PathDataNode;

class Label
  implements TypeEvaluator<b.b[]>
{
  private PathParser.PathDataNode[] k;
  
  Label() {}
  
  public PathParser.PathDataNode[] a(float paramFloat, PathParser.PathDataNode[] paramArrayOfPathDataNode1, PathParser.PathDataNode[] paramArrayOfPathDataNode2)
  {
    if (PathParser.canMorph(paramArrayOfPathDataNode1, paramArrayOfPathDataNode2))
    {
      PathParser.PathDataNode[] arrayOfPathDataNode = k;
      if ((arrayOfPathDataNode == null) || (!PathParser.canMorph(arrayOfPathDataNode, paramArrayOfPathDataNode1))) {
        k = PathParser.deepCopyNodes(paramArrayOfPathDataNode1);
      }
      int i = 0;
      while (i < paramArrayOfPathDataNode1.length)
      {
        k[i].interpolatePathDataNode(paramArrayOfPathDataNode1[i], paramArrayOfPathDataNode2[i], paramFloat);
        i += 1;
      }
      return k;
    }
    paramArrayOfPathDataNode1 = new IllegalArgumentException("Can't interpolate between two incompatible pathData");
    throw paramArrayOfPathDataNode1;
  }
}
