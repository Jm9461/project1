package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.lawyer_smartCalendar.a.p.a;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;
import java.util.List;

public class aa
  extends RecyclerView.g<p.a>
{
  private List<File> a;
  private Context c;
  
  public aa(Context paramContext, RecyclerView paramRecyclerView, List paramList)
  {
    c = paramContext;
    a = paramList;
  }
  
  public int a()
  {
    return a.size();
  }
  
  public SearchResult a(ViewGroup paramViewGroup, int paramInt)
  {
    return new SearchResult(this, LayoutInflater.from(c).inflate(2131493053, paramViewGroup, false));
  }
  
  public void onBindViewHolder(SearchResult paramSearchResult, int paramInt)
  {
    RequestCreator localRequestCreator = Picasso.with().load((File)a.get(paramInt));
    localRequestCreator.fit();
    localRequestCreator.transform();
    localRequestCreator.into(type);
  }
}
