package org.org.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;

class Document
  extends DrawableContainer.DrawableContainerState
{
  int[][] name;
  
  Document(Document paramDocument, LayerDrawable paramLayerDrawable, Resources paramResources)
  {
    super(paramDocument, paramLayerDrawable, paramResources);
    if (paramDocument != null)
    {
      name = name;
      return;
    }
    name = new int[getChildCount()][];
  }
  
  int addChild(int[] paramArrayOfInt, Drawable paramDrawable)
  {
    int i = addChild(paramDrawable);
    name[i] = paramArrayOfInt;
    return i;
  }
  
  public void addChild(int paramInt1, int paramInt2)
  {
    super.addChild(paramInt1, paramInt2);
    int[][] arrayOfInt = new int[paramInt2][];
    System.arraycopy(name, 0, arrayOfInt, 0, paramInt1);
    name = arrayOfInt;
  }
  
  void init()
  {
    Object localObject = name;
    int[][] arrayOfInt = new int[localObject.length][];
    int i = localObject.length - 1;
    while (i >= 0)
    {
      localObject = name;
      if (localObject[i] != null) {
        localObject = (int[])localObject[i].clone();
      } else {
        localObject = null;
      }
      arrayOfInt[i] = localObject;
      i -= 1;
    }
    name = arrayOfInt;
  }
  
  public Drawable newDrawable()
  {
    return new LayerDrawable(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new LayerDrawable(this, paramResources);
  }
  
  int write(int[] paramArrayOfInt)
  {
    int[][] arrayOfInt = name;
    int j = getChildren();
    int i = 0;
    while (i < j)
    {
      if (StateSet.stateSetMatches(arrayOfInt[i], paramArrayOfInt)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
}
