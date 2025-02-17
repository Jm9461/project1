package apps.objectweb.asm;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

class Image
  extends Widget
{
  private final ShortBuffer mIndexBuffer;
  private final FloatBuffer mVertexBuffer;
  
  public Image(float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    super(paramArrayOfFloat);
    paramArrayOfFloat = new float[12];
    paramArrayOfFloat[0] = Label.a(-1.0F, paramFloat1, paramFloat2);
    paramArrayOfFloat[1] = Label.a(1.0F, paramFloat3, paramFloat4);
    paramArrayOfFloat[2] = 0.0F;
    paramArrayOfFloat[3] = Label.a(-1.0F, paramFloat1, paramFloat2);
    paramArrayOfFloat[4] = Label.a(-1.0F, paramFloat3, paramFloat4);
    paramArrayOfFloat[5] = 0.0F;
    paramArrayOfFloat[6] = Label.a(1.0F, paramFloat1, paramFloat2);
    paramArrayOfFloat[7] = Label.a(-1.0F, paramFloat3, paramFloat4);
    paramArrayOfFloat[8] = 0.0F;
    paramArrayOfFloat[9] = Label.a(1.0F, paramFloat1, paramFloat2);
    paramArrayOfFloat[10] = Label.a(1.0F, paramFloat3, paramFloat4);
    paramArrayOfFloat[11] = 0.0F;
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(paramArrayOfFloat.length * 4);
    localByteBuffer.order(ByteOrder.nativeOrder());
    mVertexBuffer = localByteBuffer.asFloatBuffer();
    mVertexBuffer.put(paramArrayOfFloat);
    mVertexBuffer.position(0);
    paramArrayOfFloat = new short[6];
    float[] tmp167_166 = paramArrayOfFloat;
    tmp167_166[0] = 0;
    float[] tmp172_167 = tmp167_166;
    tmp172_167[1] = 1;
    float[] tmp177_172 = tmp172_167;
    tmp177_172[2] = 2;
    float[] tmp182_177 = tmp177_172;
    tmp182_177[3] = 0;
    float[] tmp187_182 = tmp182_177;
    tmp187_182[4] = 2;
    float[] tmp192_187 = tmp187_182;
    tmp192_187[5] = 3;
    tmp192_187;
    localByteBuffer = ByteBuffer.allocateDirect(paramArrayOfFloat.length * 2);
    localByteBuffer.order(ByteOrder.nativeOrder());
    mIndexBuffer = localByteBuffer.asShortBuffer();
    mIndexBuffer.put(paramArrayOfFloat);
    mIndexBuffer.position(0);
  }
  
  public void draw()
  {
    GLES20.glUseProgram(getPaddingLeft());
    int i = GLES20.glGetAttribLocation(getPaddingLeft(), "vPosition");
    GLES20.glEnableVertexAttribArray(i);
    GLES20.glVertexAttribPointer(i, 3, 5126, false, 12, mVertexBuffer);
    GLES20.glUniform4fv(GLES20.glGetUniformLocation(getPaddingLeft(), "vColor"), 1, getColor(), 0);
    GLES20.glDrawElements(6, mIndexBuffer.capacity(), 5123, mIndexBuffer);
    GLES20.glDisableVertexAttribArray(i);
  }
}
