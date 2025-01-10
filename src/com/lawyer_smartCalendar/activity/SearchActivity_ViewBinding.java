package com.lawyer_smartCalendar.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class SearchActivity_ViewBinding
  implements Unbinder
{
  public SearchActivity_ViewBinding(SearchActivity paramSearchActivity, View paramView)
  {
    mRecyclerView = ((RecyclerView)Collection4.replace(paramView, 2131296679, "field 'mRecyclerView'", RecyclerView.class));
    txt_recycle_no_data = ((TextView)Collection4.replace(paramView, 2131296870, "field 'txt_recycle_no_data'", TextView.class));
  }
}
