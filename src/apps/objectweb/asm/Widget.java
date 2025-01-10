package apps.objectweb.asm;

import android.opengl.GLES20;

abstract class Widget
{
  private final float[] a;
  private final int e;
  
  public Widget(float[] paramArrayOfFloat)
  {
    a = paramArrayOfFloat;
    int i = Renderer.loadShader(35633, "attribute vec4 vPosition;void main() {  gl_Position = vPosition;}");
    int j = Renderer.loadShader(35632, "precision mediump float;uniform vec4 vColor;void main() {  gl_FragColor = vColor;}");
    e = GLES20.glCreateProgram();
    GLES20.glAttachShader(e, i);
    GLES20.glAttachShader(e, j);
    GLES20.glLinkProgram(e);
  }
  
  protected float[] getColor()
  {
    return a;
  }
  
  protected int getPaddingLeft()
  {
    return e;
  }
}
