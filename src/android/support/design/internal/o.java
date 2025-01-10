package android.support.design.internal;

import android.support.v7.widget.RecyclerView.d0;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import org.org.android.R.layout;

class o
  extends c
{
  public o(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, View.OnClickListener paramOnClickListener)
  {
    super(paramLayoutInflater.inflate(R.layout.design_navigation_item, paramViewGroup, false));
    itemView.setOnClickListener(paramOnClickListener);
  }
}
