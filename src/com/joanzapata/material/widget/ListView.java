package com.joanzapata.material.widget;

import android.content.Context;
import android.support.v7.widget.ListViewCompat;
import android.util.AttributeSet;
import android.widget.AbsListView.RecyclerListener;
import apps.muzei.api.asm.ClassVisitor;
import apps.muzei.api.asm.ClassWriter;

public class ListView
  extends ListViewCompat
  implements ClassVisitor
{
  private AbsListView.RecyclerListener mRecyclerListener;
  
  public ListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  protected void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super.setRecyclerListener(new SwipeDismissListViewTouchListener.2(this));
    if (!isInEditMode()) {
      ClassWriter.getStyleId(paramContext, paramAttributeSet, paramInt1, paramInt2);
    }
  }
}
