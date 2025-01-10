package apps.objectweb.asm;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.DisplayMetrics;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class Renderer
  implements GLWallpaperService.Renderer
{
  private float height = 1.0F;
  private final Random lock;
  private Object renderer;
  private final float t;
  private final Switch this$0;
  private long time;
  boolean updateView;
  private ClassWriter[] y;
  
  public Renderer(Context paramContext, Switch paramSwitch)
  {
    this$0 = paramSwitch;
    lock = new Random();
    time = System.currentTimeMillis();
    t = getResourcesgetDisplayMetricsheightPixels;
  }
  
  public static int loadShader(int paramInt, String paramString)
  {
    paramInt = GLES20.glCreateShader(paramInt);
    GLES20.glShaderSource(paramInt, paramString);
    GLES20.glCompileShader(paramInt);
    return paramInt;
  }
  
  public final void draw(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    if (y == null) {
      return;
    }
    int i = 0;
    for (;;)
    {
      ClassWriter[] arrayOfClassWriter = y;
      if (i >= arrayOfClassWriter.length) {
        break;
      }
      if (arrayOfClassWriter[i] == null) {
        return;
      }
      arrayOfClassWriter[i].a(paramArrayOfFloat1[i], paramArrayOfFloat2[i]);
      i += 1;
    }
  }
  
  public Renderer onCreate(Object paramObject)
  {
    renderer = paramObject;
    return this;
  }
  
  public void onDrawFrame(GL10 paramGL10)
  {
    boolean bool2 = updateView;
    int k = 0;
    if (bool2)
    {
      paramGL10 = this$0.y;
      GLES20.glClearColor(paramGL10[0], paramGL10[1], paramGL10[2], paramGL10[3]);
      updateView = false;
    }
    else
    {
      GLES20.glClear(16640);
    }
    long l1 = System.currentTimeMillis();
    long l2 = time;
    time = l1;
    paramGL10 = y;
    int m = paramGL10.length;
    boolean bool1 = true;
    int j = 0;
    int i = 0;
    while (i < m)
    {
      java.lang.Object localObject = paramGL10[i];
      localObject.a(l1 - l2, 0.015707964F * (1.0F - j * 1.0F / y.length * 0.8F), height);
      bool1 &= localObject.get();
      j += 1;
      i += 1;
    }
    paramGL10 = y;
    j = paramGL10.length;
    i = k;
    while (i < j)
    {
      paramGL10[i].draw();
      i += 1;
    }
    if (bool1)
    {
      paramGL10 = renderer;
      if (paramGL10 != null) {
        paramGL10.onClick();
      }
    }
  }
  
  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    GLES20.glViewport(0, 0, paramInt1, paramInt2);
    height = (paramInt1 / paramInt2);
  }
  
  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    paramGL10 = this$0.y;
    GLES20.glClearColor(paramGL10[0], paramGL10[1], paramGL10[2], paramGL10[3]);
    paramGL10 = this$0;
    y = new ClassWriter[a];
    float f1 = b;
    float f2 = f;
    float f3 = t;
    f1 = (f1 + f2) / f3;
    f2 /= f3;
    int i = 0;
    for (;;)
    {
      paramGL10 = y;
      if (i >= paramGL10.length) {
        break;
      }
      f3 = -1.0F + (paramGL10.length - i - 1) * (f2 * 2.0F) * 2.0F;
      paramEGLConfig = this$0;
      paramGL10[i] = new ClassWriter(paramEGLConfig, i[i], f3, f1 * 2.0F + f3, lock);
      i += 1;
    }
  }
}
