package asm;

import android.media.AudioRecord;
import java.io.OutputStream;

public abstract class Attribute
  implements i
{
  private final c a = new c();
  final f b;
  final w n;
  
  Attribute(f paramF, w paramW)
  {
    b = paramF;
    n = paramW;
  }
  
  public f a()
  {
    return b;
  }
  
  public void a(OutputStream paramOutputStream)
  {
    read(read(), b.e(), paramOutputStream);
  }
  
  AudioRecord read()
  {
    AudioRecord localAudioRecord = b.i();
    localAudioRecord.startRecording();
    b.e(true);
    return localAudioRecord;
  }
  
  abstract void read(AudioRecord paramAudioRecord, int paramInt, OutputStream paramOutputStream);
  
  public void stop()
  {
    b.e(false);
    b.i().stop();
  }
  
  void write(Label paramLabel)
  {
    a.a(new e.a.a(this, paramLabel));
  }
}
