package org.org.asm;

import android.graphics.Path;

final class AnnotationNode
  extends AnnotationVisitor
{
  AnnotationNode() {}
  
  public Path draw(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Path localPath = new Path();
    localPath.moveTo(paramFloat1, paramFloat2);
    localPath.lineTo(paramFloat3, paramFloat4);
    return localPath;
  }
}
