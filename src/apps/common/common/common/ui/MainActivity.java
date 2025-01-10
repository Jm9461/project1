package apps.common.common.common.ui;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import apps.android.google.api.d;
import apps.android.google.api.e;
import apps.apps.ui.MethodWriter;
import apps.common.common.common.R.dimen;
import apps.common.common.common.R.id;
import apps.common.common.common.R.layout;
import apps.common.common.common.base.Counter;
import apps.common.common.common.base.g;
import ch.acra.widget.ValueAnimatorCompat.Impl;
import ch.acra.widget.ViewAnimationUtils;

public abstract class MainActivity
  extends AppCompatActivity
{
  private MethodWriter a;
  private apps.common.common.common.data.h b;
  private ImageView c;
  private boolean dirty = false;
  private FrameLayout e;
  private AppCompatTextView p;
  private int r = 0;
  private RelativeLayout y;
  
  public MainActivity() {}
  
  public void a(int paramInt)
  {
    setContentView(R.layout.activity_main_lib);
    y = ((RelativeLayout)findViewById(R.id.rlColor));
    p = ((AppCompatTextView)findViewById(R.id.txtTitle));
    if (paramInt != 1)
    {
      if (paramInt != 2) {
        return;
      }
      c = ((ImageView)findViewById(R.id.imgLogo));
      c.setImageResource(b.getTitle());
      return;
    }
    e = ((FrameLayout)findViewById(R.id.flCentral));
    onBindViewHolder();
  }
  
  public abstract void a(apps.common.common.common.data.h paramH);
  
  public void getView()
  {
    p.setText(b.getItemId());
    p.setTextSize(b.get());
    p.setTextColor(getResources().getColor(b.m()));
    if (!b.getString().isEmpty()) {
      init(b.getString());
    }
    Object localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(3, R.id.flCentral);
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    p.setLayoutParams((ViewGroup.LayoutParams)localObject);
    p.setVisibility(0);
    localObject = e.a(b.c());
    ((d)localObject).a(new AnimatorSet.AnimatorSetListener(this));
    ((d)localObject).a(b.d());
    ((d)localObject).a(p);
  }
  
  public void init(String paramString)
  {
    paramString = Typeface.createFromAsset(getAssets(), paramString);
    p.setTypeface(paramString);
  }
  
  public abstract void onActivityResult();
  
  public void onBindViewHolder()
  {
    int i = getResources().getDimensionPixelSize(R.dimen.fourthSampleViewSize);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i, i);
    localLayoutParams.setMargins(0, 0, 0, 50);
    apps.apps.ui.h localH = new apps.apps.ui.h();
    localH.a(e);
    localH.a(localLayoutParams);
    localH.a(b.b());
    localH.a(b.length(), b.g());
    localH.c(b.i());
    localH.setTitle(Color.parseColor(String.format("#%06X", new Object[] { Integer.valueOf(getResources().getColor(b.getIcon()) & 0xFFFFFF) })));
    localH.e(Color.parseColor(String.format("#%06X", new Object[] { Integer.valueOf(getResources().getColor(b.h()) & 0xFFFFFF) })));
    localH.a(b.e());
    localH.b(b.j());
    localH.a(new apps.apps.ui.views.h());
    a = localH.a();
    a.setOnStateChangeListener(new b(this));
  }
  
  public void onCreate()
  {
    c.setVisibility(0);
    c.setImageResource(b.getTitle());
    d localD = e.a(b.f());
    localD.a(new SettingsFragment.1(this));
    localD.a(b.l());
    localD.a(c);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    b = new apps.common.common.common.data.h();
    a(b);
    r = g.a(b);
    a(r);
  }
  
  public void onPostExecute()
  {
    int i = Math.max(y.getWidth(), y.getHeight());
    int j = y.getHeight() / 2;
    int k = Counter.insert(y, b.size());
    int m = Counter.insert(y, b.getCount());
    y.setBackgroundColor(getResources().getColor(b.getY()));
    ValueAnimatorCompat.Impl localImpl = ViewAnimationUtils.createCircularReveal(y, m, k, 0.0F, i + j);
    localImpl.setInterpolator(new AccelerateDecelerateInterpolator());
    localImpl.setDuration(b.k());
    localImpl.start(new ItemTouchHelper.RecoverAnimation(this));
    localImpl.start();
    dirty = true;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (!dirty)) {
      onPostExecute();
    }
  }
}
