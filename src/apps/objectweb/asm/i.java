package apps.objectweb.asm;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

class i
  extends Widget
{
  private static final int y = (int)Math.ceil(2.5D) * 3;
  private float a;
  private float b;
  private float[] c;
  private final float e;
  private final float f;
  private final float g;
  private final float h;
  float i;
  private ShortBuffer indices;
  private float m;
  private final Random r;
  private FloatBuffer vertices;
  private float x;
  
  public i(float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, byte paramByte, Random paramRandom)
  {
    super(paramArrayOfFloat);
    float f1 = 0.0F;
    i = 0.0F;
    e = paramFloat1;
    f = paramFloat2;
    g = paramFloat3;
    h = paramFloat4;
    r = paramRandom;
    if (paramByte == 0) {
      paramFloat1 = f1;
    } else {
      paramFloat1 = 3.1415927F;
    }
    x = paramFloat1;
    a();
    add();
  }
  
  private void a()
  {
    c = new float[45 * 3];
    c[0] = Label.a(0.0F, e, f);
    c[1] = Label.a(-1.0F, g, h);
    c[3] = Label.a(-1.0F, e, f);
    c[4] = Label.a(-1.0F, g, h);
    float[] arrayOfFloat = c;
    arrayOfFloat[6] = arrayOfFloat[3];
    arrayOfFloat[7] = Label.a(0.0F, g, h);
    arrayOfFloat = c;
    arrayOfFloat[(arrayOfFloat.length - 6)] = Label.a(1.0F, e, f);
    arrayOfFloat = c;
    arrayOfFloat[(arrayOfFloat.length - 5)] = arrayOfFloat[7];
    arrayOfFloat[(arrayOfFloat.length - 3)] = arrayOfFloat[(arrayOfFloat.length - 6)];
    arrayOfFloat[(arrayOfFloat.length - 2)] = arrayOfFloat[4];
  }
  
  private void add()
  {
    short[] arrayOfShort = new short['?'];
    int j = 0;
    while (j < arrayOfShort.length / 3)
    {
      arrayOfShort[(j * 3)] = 0;
      arrayOfShort[(j * 3 + 1)] = ((short)(j + 1));
      arrayOfShort[(j * 3 + 2)] = ((short)(j + 2));
      j += 1;
    }
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(arrayOfShort.length * 2);
    localByteBuffer.order(ByteOrder.nativeOrder());
    indices = localByteBuffer.asShortBuffer();
    indices.put(arrayOfShort);
    indices.position(0);
  }
  
  public void b(float paramFloat)
  {
    b = paramFloat;
  }
  
  public void draw()
  {
    GLES20.glUseProgram(getPaddingLeft());
    int j = GLES20.glGetAttribLocation(getPaddingLeft(), "vPosition");
    GLES20.glEnableVertexAttribArray(j);
    GLES20.glVertexAttribPointer(j, 3, 5126, false, 12, vertices);
    GLES20.glUniform4fv(GLES20.glGetUniformLocation(getPaddingLeft(), "vColor"), 1, getColor(), 0);
    GLES20.glDrawElements(6, indices.capacity(), 5123, indices);
    GLES20.glDisableVertexAttribArray(j);
  }
  
  public void draw(float paramFloat)
  {
    Object localObject;
    if (vertices == null)
    {
      localObject = ByteBuffer.allocateDirect(c.length * 4);
      ((ByteBuffer)localObject).order(ByteOrder.nativeOrder());
      vertices = ((ByteBuffer)localObject).asFloatBuffer();
      vertices.put(c);
      vertices.position(0);
    }
    float f1 = x + paramFloat;
    x = f1;
    if (a == 0.0F)
    {
      paramFloat = b;
      if (paramFloat > 0.0F) {
        a = Label.put(0.0F, paramFloat, 0.35F);
      }
    }
    paramFloat = (float)Math.sin(f1) * a;
    if (((m > 0.0F) && (paramFloat <= 0.0F)) || ((m < 0.0F) && (paramFloat >= 0.0F)))
    {
      a = Label.put(a, b, 0.35F);
      f2 = r.nextFloat();
      if (r.nextBoolean()) {
        j = 1;
      } else {
        j = -1;
      }
      i = (f2 * 0.3F * j);
    }
    m = paramFloat;
    int j = 0;
    float f2 = Label.a(i, e, f);
    float f3 = Label.a(paramFloat, g, h);
    double d;
    for (paramFloat = 0.0F; paramFloat < 1.0D - 0.025D / 2.0D; paramFloat = (float)(d + 0.025D))
    {
      localObject = c;
      int k = y;
      localObject[(j * 3 + 1 + k)] = f1;
      vertices.put(j * 3 + k, Label.a(paramFloat, localObject[6], f2, localObject[(localObject.length - 6)]));
      localObject = vertices;
      k = y;
      float[] arrayOfFloat = c;
      ((FloatBuffer)localObject).put(j * 3 + 1 + k, Label.a(paramFloat, arrayOfFloat[7], f3, arrayOfFloat[(arrayOfFloat.length - 5)]));
      j += 1;
      d = paramFloat;
      Double.isNaN(d);
    }
  }
  
  public boolean g()
  {
    return Math.abs(m) < 0.001F;
  }
}
