package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

abstract interface CalligraphyActivityFactory
{
  public abstract View onActivityCreateView(View paramView1, View paramView2, String paramString, Context paramContext, AttributeSet paramAttributeSet);
}
