package apps.objectweb.asm;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

class LayoutManager
  extends Widget
{
  private float a;
  private float b;
  private float d;
  private float e;
  private float h;
  private float i;
  private final ShortBuffer indices;
  private final Random random;
  private final FloatBuffer vertices;
  private float x = -1.0F;
  
  public LayoutManager(float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Random paramRandom)
  {
    super(paramArrayOfFloat);
    random = paramRandom;
    init(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    Object localObject = new float[123];
    paramArrayOfFloat = new short[120];
    int j = 0;
    while (j < paramArrayOfFloat.length / 3 - 1)
    {
      paramArrayOfFloat[(j * 3)] = 0;
      paramArrayOfFloat[(j * 3 + 1)] = ((short)(j + 1));
      paramArrayOfFloat[(j * 3 + 2)] = ((short)(j + 2));
      j += 1;
    }
    paramArrayOfFloat[(j * 3)] = 0;
    paramArrayOfFloat[(j * 3 + 1)] = ((short)(j + 1));
    paramArrayOfFloat[(j * 3 + 2)] = 1;
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(localObject.length * 4);
    localByteBuffer.order(ByteOrder.nativeOrder());
    vertices = localByteBuffer.asFloatBuffer();
    vertices.put((float[])localObject);
    vertices.position(0);
    localObject = ByteBuffer.allocateDirect(paramArrayOfFloat.length * 2);
    ((ByteBuffer)localObject).order(ByteOrder.nativeOrder());
    indices = ((ByteBuffer)localObject).asShortBuffer();
    indices.put(paramArrayOfFloat);
    indices.position(0);
    double d1 = paramRandom.nextFloat() * 2.0F;
    Double.isNaN(d1);
    h = ((float)(d1 * 3.141592653589793D));
  }
  
  public boolean a()
  {
    return x > 1.0F;
  }
  
  public void draw()
  {
    GLES20.glUseProgram(getPaddingLeft());
    int j = GLES20.glGetAttribLocation(getPaddingLeft(), "vPosition");
    GLES20.glEnableVertexAttribArray(j);
    GLES20.glVertexAttribPointer(j, 3, 5126, false, 12, vertices);
    int k = GLES20.glGetUniformLocation(getPaddingLeft(), "vColor");
    GLES20.glEnable(3042);
    GLES20.glBlendFunc(770, 771);
    GLES20.glUniform4fv(k, 1, getColor(), 0);
    GLES20.glDrawElements(6, indices.capacity(), 5123, indices);
    GLES20.glDisableVertexAttribArray(j);
    GLES20.glDisable(3042);
  }
  
  public void draw(long paramLong, float paramFloat)
  {
    h += (float)paramLong * 0.0062831854F;
    float f1 = i + (float)(Math.sin(h) * 0.05000000074505806D);
    float f4 = b;
    float f2 = f1 + f4;
    float f3 = a + (float)paramLong * e;
    f4 += f3;
    x += (float)paramLong * d;
    getColor()[3] = (1.0F - x / 1.0F);
    vertices.put(0, Label.a(0.0F, f1, f2));
    vertices.put(1, Label.a(x * paramFloat, f3, f4));
    int j = 1;
    while (j <= 40)
    {
      FloatBuffer localFloatBuffer = vertices;
      double d1 = j;
      Double.isNaN(d1);
      localFloatBuffer.put(j * 3, Label.a((float)Math.sin(d1 * 0.15707963267948966D - 3.141592653589793D), f1, f2));
      localFloatBuffer = vertices;
      d1 = j;
      Double.isNaN(d1);
      localFloatBuffer.put(j * 3 + 1, Label.a((float)Math.cos(d1 * 0.15707963267948966D - 3.141592653589793D) * paramFloat, f3, f4));
      j += 1;
    }
    a = f3;
  }
  
  public void init(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    a = paramFloat2;
    b = paramFloat4;
    i = paramFloat1;
    x = -1.0F;
    paramFloat1 = random.nextFloat() * 0.8F + 0.4F;
    e = ((paramFloat3 - paramFloat2) / 1000.0F * paramFloat1);
    d = (0.002F * paramFloat1);
    getColor()[3] = 1.06535322E9F;
  }
}
