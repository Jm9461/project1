package apps.objectweb.asm;

import android.opengl.GLSurfaceView;
import android.view.View;

public class Settings
  extends GLSurfaceView
  implements Log.DebugLogSettings, Item
{
  private b.d.a.b<?> a;
  private Object context;
  private final Renderer renderer;
  private final Switch this$0;
  
  private Settings(f paramF)
  {
    super(f.f(paramF));
    this$0 = new Switch(paramF, null);
    renderer = new Renderer(getContext(), this$0);
    onCreate();
  }
  
  private void onCreate()
  {
    setEGLContextClientVersion(2);
    setRenderer(renderer);
    renderer.onCreate(new ActivityMain.18(this));
  }
  
  public void a(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    renderer.draw(paramArrayOfFloat1, paramArrayOfFloat2);
  }
  
  public void apply()
  {
    if (getRenderMode() != 0) {
      setRenderMode(0);
    }
  }
  
  public void clear()
  {
    b localB = a;
    if (localB != null)
    {
      localB.a();
      a = null;
    }
  }
  
  public void load(Object paramObject)
  {
    context = paramObject;
  }
  
  public void onPause()
  {
    b localB = a;
    if (localB != null) {
      localB.onCloseMenu();
    }
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    b localB = a;
    if (localB != null) {
      localB.onClick();
    }
  }
  
  public void set(b paramB)
  {
    b localB = a;
    if (localB != null) {
      localB.a();
    }
    a = paramB;
    a.a(this, this$0.a);
  }
  
  public void setRenderMode()
  {
    if (getRenderMode() != 1) {
      setRenderMode(1);
    }
  }
}
