package cafe.adriel.androidaudiorecorder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import cafe.adriel.androidaudiorecorder.asynctasks.AllowedSolution;
import cafe.adriel.androidaudiorecorder.asynctasks.MathArrays.OrderDirection;
import cafe.adriel.androidaudiorecorder.asynctasks.c;

public class Row
{
  private c c;
  private AllowedSolution content;
  private boolean data;
  private MathArrays.OrderDirection id;
  private int index;
  private Activity parent;
  private boolean result;
  private String text;
  private int x;
  
  private Row(Activity paramActivity)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory());
    localStringBuilder.append("/recorded_audio.wav");
    text = localStringBuilder.toString();
    c = c.c;
    id = MathArrays.OrderDirection.c;
    content = AllowedSolution.table;
    x = Color.parseColor("#546E7A");
    index = 0;
    result = false;
    data = false;
    parent = paramActivity;
  }
  
  public static Row get(Activity paramActivity)
  {
    return new Row(paramActivity);
  }
  
  public Row delete(int paramInt)
  {
    x = paramInt;
    return this;
  }
  
  public Row delete(AllowedSolution paramAllowedSolution)
  {
    content = paramAllowedSolution;
    return this;
  }
  
  public Row delete(MathArrays.OrderDirection paramOrderDirection)
  {
    id = paramOrderDirection;
    return this;
  }
  
  public Row delete(c paramC)
  {
    c = paramC;
    return this;
  }
  
  public Row delete(String paramString)
  {
    text = paramString;
    return this;
  }
  
  public Row delete(boolean paramBoolean)
  {
    data = paramBoolean;
    return this;
  }
  
  public Row get(int paramInt)
  {
    index = paramInt;
    return this;
  }
  
  public Row set(boolean paramBoolean)
  {
    result = paramBoolean;
    return this;
  }
  
  public void set()
  {
    Intent localIntent = new Intent(parent, AudioRecorderActivity.class);
    localIntent.putExtra("filePath", text);
    localIntent.putExtra("color", x);
    localIntent.putExtra("source", c);
    localIntent.putExtra("channel", id);
    localIntent.putExtra("sampleRate", content);
    localIntent.putExtra("autoStart", result);
    localIntent.putExtra("keepDisplayOn", data);
    parent.startActivityForResult(localIntent, index);
  }
}
