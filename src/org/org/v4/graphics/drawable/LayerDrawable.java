package org.org.v4.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;

class LayerDrawable
  extends DrawableContainer
{
  private Document mLayerState;
  private boolean mMutated;
  
  LayerDrawable(Document paramDocument)
  {
    if (paramDocument != null) {
      mutate(paramDocument);
    }
  }
  
  LayerDrawable(Document paramDocument, Resources paramResources)
  {
    mutate(new Document(paramDocument, this, paramResources));
    onStateChange(getState());
  }
  
  int[] a(AttributeSet paramAttributeSet)
  {
    int j = 0;
    int n = paramAttributeSet.getAttributeCount();
    int[] arrayOfInt = new int[n];
    int i = 0;
    while (i < n)
    {
      int m = paramAttributeSet.getAttributeNameResource(i);
      int k = j;
      if (m != 0) {
        if ((m != 16842960) && (m != 16843161))
        {
          if (paramAttributeSet.getAttributeBooleanValue(i, false)) {
            k = m;
          } else {
            k = -m;
          }
          arrayOfInt[j] = k;
          k = j + 1;
        }
        else
        {
          k = j;
        }
      }
      i += 1;
      j = k;
    }
    return StateSet.trimStateSet(arrayOfInt, j);
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    super.applyTheme(paramTheme);
    onStateChange(getState());
  }
  
  Document draw()
  {
    return new Document(mLayerState, this, null);
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  public Drawable mutate()
  {
    if (!mMutated)
    {
      super.mutate();
      mLayerState.init();
      mMutated = true;
    }
    return this;
  }
  
  protected void mutate(DrawableContainer.DrawableContainerState paramDrawableContainerState)
  {
    super.mutate(paramDrawableContainerState);
    if ((paramDrawableContainerState instanceof Document)) {
      mLayerState = ((Document)paramDrawableContainerState);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    boolean bool = super.onStateChange(paramArrayOfInt);
    int j = mLayerState.write(paramArrayOfInt);
    int i = j;
    if (j < 0) {
      i = mLayerState.write(StateSet.WILD_CARD);
    }
    return (draw(i)) || (bool);
  }
}
