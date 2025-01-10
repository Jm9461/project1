package android.support.design.theme;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatViewInflater;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import org.org.android.widget.FloatingActionButton;

@Keep
public class MaterialComponentsViewInflater
  extends AppCompatViewInflater
{
  public MaterialComponentsViewInflater() {}
  
  protected AppCompatButton createButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new FloatingActionButton(paramContext, paramAttributeSet);
  }
}
