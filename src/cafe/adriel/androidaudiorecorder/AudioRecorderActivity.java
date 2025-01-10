package cafe.adriel.androidaudiorecorder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import apps.objectweb.asm.ByteVector;
import apps.objectweb.asm.Settings;
import apps.objectweb.asm.a;
import apps.objectweb.asm.b;
import apps.objectweb.asm.f;
import asm.TransportHttp.Service.HttpExecuteStream;
import asm.e;
import asm.w;
import cafe.adriel.androidaudiorecorder.asynctasks.AllowedSolution;
import cafe.adriel.androidaudiorecorder.asynctasks.MathArrays.OrderDirection;
import cafe.adriel.androidaudiorecorder.asynctasks.c;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class AudioRecorderActivity
  extends AppCompatActivity
  implements w, MediaPlayer.OnCompletionListener
{
  private asm.h b;
  private h c;
  private c d;
  private boolean freeze;
  private int g;
  private boolean h;
  private MathArrays.OrderDirection header;
  private AllowedSolution item;
  private int k;
  private boolean l;
  private MenuItem mDelete;
  private MediaPlayer mediaPlayer;
  private ImageButton repeatButton;
  private RelativeLayout row;
  private ImageButton shuffleButton;
  private ImageButton startButton;
  private Settings this$0;
  private int time;
  private Timer timer;
  private String title;
  private TextView tv;
  private TextView tv2;
  
  public AudioRecorderActivity() {}
  
  private void destroy()
  {
    Timer localTimer = timer;
    if (localTimer != null)
    {
      localTimer.cancel();
      timer.purge();
      timer = null;
    }
  }
  
  private void e()
  {
    this$0.clear();
    Object localObject = c;
    if (localObject != null) {
      ((h)localObject).c();
    }
    g = 0;
    localObject = b;
    if (localObject != null)
    {
      ((asm.h)localObject).a();
      b = null;
    }
    destroy();
  }
  
  private void init()
  {
    try
    {
      e();
      Object localObject1 = new MediaPlayer();
      mediaPlayer = ((MediaPlayer)localObject1);
      localObject1 = mediaPlayer;
      Object localObject2 = title;
      ((MediaPlayer)localObject1).setDataSource((String)localObject2);
      localObject1 = mediaPlayer;
      ((MediaPlayer)localObject1).prepare();
      localObject1 = mediaPlayer;
      ((MediaPlayer)localObject1).start();
      localObject1 = this$0;
      localObject2 = mediaPlayer;
      ((Settings)localObject1).set(ByteVector.add(this, (MediaPlayer)localObject2));
      localObject1 = this$0;
      ((View)localObject1).post(new c());
      localObject1 = tv;
      ((TextView)localObject1).setText("00:00:00");
      localObject1 = tv2;
      int i = R.string.aar_playing;
      ((TextView)localObject1).setText(i);
      localObject1 = tv2;
      ((View)localObject1).setVisibility(0);
      localObject1 = repeatButton;
      i = R.drawable.aar_ic_stop;
      ((ImageView)localObject1).setImageResource(i);
      k = 0;
      start();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void initialize()
  {
    tv2.setText("");
    tv2.setVisibility(4);
    repeatButton.setImageResource(R.drawable.aar_ic_play);
    this$0.clear();
    Object localObject = c;
    if (localObject != null) {
      ((h)localObject).c();
    }
    localObject = mediaPlayer;
    if (localObject != null) {
      try
      {
        ((MediaPlayer)localObject).stop();
        localObject = mediaPlayer;
        ((MediaPlayer)localObject).reset();
      }
      catch (Exception localException) {}
    }
    destroy();
  }
  
  private boolean next()
  {
    if (mediaPlayer != null)
    {
      MediaPlayer localMediaPlayer = mediaPlayer;
      try
      {
        boolean bool = localMediaPlayer.isPlaying();
        if ((bool) && (!l)) {
          return true;
        }
      }
      catch (Exception localException) {}
    }
    return false;
  }
  
  private void onCreate()
  {
    l = true;
    mDelete.setVisible(false);
    tv2.setText(R.string.aar_recording);
    tv2.setVisibility(0);
    startButton.setVisibility(4);
    repeatButton.setVisibility(4);
    shuffleButton.setImageResource(R.drawable.aar_ic_pause);
    repeatButton.setImageResource(R.drawable.aar_ic_play);
    c = new h();
    this$0.set(c);
    if (b == null)
    {
      tv.setText("00:00:00");
      b = e.a(new TransportHttp.Service.HttpExecuteStream(Label.a(d, header, item), this), new File(title));
    }
    b.c();
    start();
  }
  
  private void onCreateView()
  {
    l = false;
    if (!isFinishing()) {
      mDelete.setVisible(true);
    }
    tv2.setText(R.string.aar_paused);
    tv2.setVisibility(0);
    startButton.setVisibility(0);
    repeatButton.setVisibility(0);
    shuffleButton.setImageResource(R.drawable.aar_ic_rec);
    repeatButton.setImageResource(R.drawable.aar_ic_play);
    this$0.clear();
    Object localObject = c;
    if (localObject != null) {
      ((h)localObject).c();
    }
    localObject = b;
    if (localObject != null) {
      ((asm.h)localObject).e();
    }
    destroy();
  }
  
  private void refreshInterface()
  {
    runOnUiThread(new e());
  }
  
  private void saveProfile()
  {
    e();
    setResult(-1);
    finish();
  }
  
  private void start()
  {
    destroy();
    timer = new Timer();
    timer.scheduleAtFixedRate(new d(), 0L, 1000L);
  }
  
  public void a(asm.Label paramLabel)
  {
    float f;
    if (l) {
      f = (float)paramLabel.a();
    } else {
      f = 0.0F;
    }
    c.a(Float.valueOf(f));
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    initialize();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.aar_activity_audio_recorder);
    if (paramBundle != null)
    {
      title = paramBundle.getString("filePath");
      d = ((c)paramBundle.getSerializable("source"));
      header = ((MathArrays.OrderDirection)paramBundle.getSerializable("channel"));
      item = ((AllowedSolution)paramBundle.getSerializable("sampleRate"));
      time = paramBundle.getInt("color");
      h = paramBundle.getBoolean("autoStart");
      freeze = paramBundle.getBoolean("keepDisplayOn");
    }
    else
    {
      title = getIntent().getStringExtra("filePath");
      d = ((c)getIntent().getSerializableExtra("source"));
      header = ((MathArrays.OrderDirection)getIntent().getSerializableExtra("channel"));
      item = ((AllowedSolution)getIntent().getSerializableExtra("sampleRate"));
      time = getIntent().getIntExtra("color", -16777216);
      h = getIntent().getBooleanExtra("autoStart", false);
      freeze = getIntent().getBooleanExtra("keepDisplayOn", false);
    }
    if (freeze) {
      getWindow().addFlags(128);
    }
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setElevation(0.0F);
      getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Label.getColor(time)));
      getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.aar_ic_clear));
    }
    paramBundle = new f(this);
    paramBundle.e(1);
    paramBundle.d(6);
    paramBundle.f(R.dimen.aar_wave_height);
    paramBundle.add(R.dimen.aar_footer_height);
    paramBundle.setSize(20);
    paramBundle.b(R.dimen.aar_bubble_size);
    paramBundle.a(true);
    this$0 = ((f)((f)paramBundle.a(Label.getColor(time))).a(new int[] { time })).readSettings();
    row = ((RelativeLayout)findViewById(R.id.content));
    tv2 = ((TextView)findViewById(R.id.status));
    tv = ((TextView)findViewById(R.id.timer));
    startButton = ((ImageButton)findViewById(R.id.restart));
    shuffleButton = ((ImageButton)findViewById(R.id.record));
    repeatButton = ((ImageButton)findViewById(R.id.play));
    row.setBackgroundColor(Label.getColor(time));
    row.addView(this$0, 0);
    startButton.setVisibility(4);
    repeatButton.setVisibility(4);
    if (Label.update(time))
    {
      ContextCompat.getDrawable(this, R.drawable.aar_ic_clear).setColorFilter(-16777216, PorterDuff.Mode.SRC_ATOP);
      ContextCompat.getDrawable(this, R.drawable.aar_ic_check).setColorFilter(-16777216, PorterDuff.Mode.SRC_ATOP);
      tv2.setTextColor(-16777216);
      tv.setTextColor(-16777216);
      startButton.setColorFilter(-16777216);
      shuffleButton.setColorFilter(-16777216);
      repeatButton.setColorFilter(-16777216);
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(AppState.aar_audio_recorder, paramMenu);
    mDelete = paramMenu.findItem(R.id.action_save);
    mDelete.setIcon(ContextCompat.getDrawable(this, R.drawable.aar_ic_check));
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    restartRecording(null);
    setResult(0);
    Settings localSettings = this$0;
    try
    {
      localSettings.clear();
    }
    catch (Exception localException) {}
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 16908332) {
      finish();
    } else if (i == R.id.action_save) {
      saveProfile();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    restartRecording(null);
    Settings localSettings = this$0;
    try
    {
      localSettings.onPause();
    }
    catch (Exception localException) {}
    super.onPause();
  }
  
  public void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    if ((h) && (!l)) {
      toggleRecording(null);
    }
  }
  
  public void onResume()
  {
    super.onResume();
    Settings localSettings = this$0;
    try
    {
      localSettings.onResume();
      return;
    }
    catch (Exception localException) {}
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putString("filePath", title);
    paramBundle.putInt("color", time);
    super.onSaveInstanceState(paramBundle);
  }
  
  public void restartRecording(View paramView)
  {
    if (l)
    {
      e();
    }
    else if (next())
    {
      initialize();
    }
    else
    {
      c = new h();
      this$0.set(c);
      this$0.clear();
      paramView = c;
      if (paramView != null) {
        paramView.c();
      }
    }
    mDelete.setVisible(false);
    tv2.setVisibility(4);
    startButton.setVisibility(4);
    repeatButton.setVisibility(4);
    shuffleButton.setImageResource(R.drawable.aar_ic_rec);
    tv.setText("00:00:00");
    g = 0;
    k = 0;
  }
  
  public void togglePlaying(View paramView)
  {
    onCreateView();
    Label.a(100, new b());
  }
  
  public void toggleRecording(View paramView)
  {
    initialize();
    Label.a(100, new a());
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      if (AudioRecorderActivity.a(AudioRecorderActivity.this))
      {
        AudioRecorderActivity.removeFragment(AudioRecorderActivity.this);
        return;
      }
      AudioRecorderActivity.moveToState(AudioRecorderActivity.this);
    }
  }
  
  class b
    implements Runnable
  {
    b() {}
    
    public void run()
    {
      if (AudioRecorderActivity.f(AudioRecorderActivity.this))
      {
        AudioRecorderActivity.access$getInitialize(AudioRecorderActivity.this);
        return;
      }
      AudioRecorderActivity.access$getChangeCurrent(AudioRecorderActivity.this);
    }
  }
  
  class c
    implements Runnable
  {
    c() {}
    
    public void run()
    {
      AudioRecorderActivity.access$getMediaPlayer(AudioRecorderActivity.this).setOnCompletionListener(AudioRecorderActivity.this);
    }
  }
  
  class d
    extends TimerTask
  {
    d() {}
    
    public void run()
    {
      AudioRecorderActivity.persist(AudioRecorderActivity.this);
    }
  }
  
  class e
    implements Runnable
  {
    e() {}
    
    public void run()
    {
      if (AudioRecorderActivity.a(AudioRecorderActivity.this))
      {
        AudioRecorderActivity.b(AudioRecorderActivity.this);
        AudioRecorderActivity.access$getTv(AudioRecorderActivity.this).setText(Label.a(AudioRecorderActivity.e(AudioRecorderActivity.this)));
        return;
      }
      if (AudioRecorderActivity.f(AudioRecorderActivity.this))
      {
        AudioRecorderActivity.d(AudioRecorderActivity.this);
        AudioRecorderActivity.access$getTv(AudioRecorderActivity.this).setText(Label.a(AudioRecorderActivity.c(AudioRecorderActivity.this)));
      }
    }
  }
}
