package apps.objectweb.asm;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import b.d.a.n.b;

public class e
  extends b.d.a.b<byte[]>
  implements n.b, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener
{
  private final float[] a = { 0.001814059F, 0.007936508F, 0.05668934F, 0.22675736F };
  private float[] hasJobQueue;
  private MediaPlayer.OnCompletionListener mOnCompletionListener;
  private MediaPlayer.OnPreparedListener preparedListener;
  private final VisualizerView this$0;
  private float[] tileCache;
  
  e(Context paramContext, int paramInt)
  {
    this$0 = new VisualizerView(paramContext, paramInt, this);
  }
  
  e(Context paramContext, MediaPlayer paramMediaPlayer)
  {
    this(paramContext, paramMediaPlayer.getAudioSessionId());
    paramMediaPlayer.setOnPreparedListener(this);
    paramMediaPlayer.setOnCompletionListener(this);
  }
  
  public void a()
  {
    super.a();
    this$0.release();
  }
  
  public void a(byte[] paramArrayOfByte)
  {
    a(paramArrayOfByte);
  }
  
  protected void draw(byte[] paramArrayOfByte, int paramInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    int j = paramArrayOfByte.length / 2 - 1;
    float[] arrayOfFloat = hasJobQueue;
    if ((arrayOfFloat == null) || (arrayOfFloat.length != j)) {
      hasJobQueue = new float[j];
    }
    arrayOfFloat = tileCache;
    if ((arrayOfFloat == null) || (arrayOfFloat.length != j)) {
      tileCache = new float[j];
    }
    int i = 0;
    float f1;
    float f2;
    while (i < j)
    {
      f1 = paramArrayOfByte[(i * 2)];
      f2 = paramArrayOfByte[(i * 2 + 1)];
      f2 = f1 * f1 + f2 * f2;
      hasJobQueue[i] = Label.a(f2);
      f1 = 1.0F;
      if ((i == 0) || (i == j - 1)) {
        f1 = 2.0F;
      }
      arrayOfFloat = tileCache;
      double d1 = f1;
      double d2 = Math.sqrt(f2);
      Double.isNaN(d1);
      double d3 = j;
      Double.isNaN(d3);
      arrayOfFloat[i] = ((float)(d1 * d2 / d3));
      i += 1;
    }
    i = 0;
    while (i < paramInt)
    {
      j = (int)(a[i] * paramArrayOfByte.length);
      f1 = hasJobQueue[j];
      f2 = tileCache[j];
      paramArrayOfFloat1[i] = (f1 / 76.0F);
      paramArrayOfFloat2[i] = f2;
      i += 1;
    }
  }
  
  public void onClick()
  {
    super.onClick();
    this$0.release(true);
  }
  
  public void onCloseMenu()
  {
    this$0.release(false);
    super.onCloseMenu();
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    onCreate();
    this$0.release(false);
    MediaPlayer.OnCompletionListener localOnCompletionListener = mOnCompletionListener;
    if (localOnCompletionListener != null) {
      localOnCompletionListener.onCompletion(paramMediaPlayer);
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    b();
    this$0.release(true);
    MediaPlayer.OnPreparedListener localOnPreparedListener = preparedListener;
    if (localOnPreparedListener != null) {
      localOnPreparedListener.onPrepared(paramMediaPlayer);
    }
  }
}
