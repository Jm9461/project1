package apps.objectweb.asm;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;

class VisualizerView
{
  private long active;
  private Visualizer.OnDataCaptureListener data;
  private int imageDataLength;
  private MediaPlayer mPlayer;
  private Visualizer mVisualizer;
  
  public VisualizerView(Context paramContext, int paramInt, c paramC)
  {
    mPlayer = MediaPlayer.create(paramContext, k.av_workaround_1min);
    mVisualizer = new Visualizer(paramInt);
    mVisualizer.setEnabled(false);
    mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
    imageDataLength = Visualizer.getMaxCaptureRate();
    data = new VisualizerView.1(this, paramC);
    mVisualizer.setEnabled(true);
  }
  
  public void release()
  {
    mVisualizer.setEnabled(false);
    mVisualizer.release();
    mVisualizer = null;
    mPlayer.release();
    mPlayer = null;
  }
  
  public void release(boolean paramBoolean)
  {
    Visualizer localVisualizer = mVisualizer;
    if (localVisualizer == null) {
      return;
    }
    localVisualizer.setEnabled(false);
    if (paramBoolean) {
      mVisualizer.setDataCaptureListener(data, imageDataLength, false, true);
    } else {
      mVisualizer.setDataCaptureListener(null, imageDataLength, false, false);
    }
    mVisualizer.setEnabled(true);
  }
}
