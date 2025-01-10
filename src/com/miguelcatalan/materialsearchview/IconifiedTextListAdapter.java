package com.miguelcatalan.materialsearchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;

public class IconifiedTextListAdapter
  extends BaseAdapter
  implements Filterable
{
  private boolean c;
  private Drawable mContext;
  private ArrayList<String> mItems;
  private LayoutInflater mLayoutInflater;
  private String[] mOriginalItems;
  
  public IconifiedTextListAdapter(Context paramContext, String[] paramArrayOfString, Drawable paramDrawable, boolean paramBoolean)
  {
    mLayoutInflater = LayoutInflater.from(paramContext);
    mItems = new ArrayList();
    mOriginalItems = paramArrayOfString;
    mContext = paramDrawable;
    c = paramBoolean;
  }
  
  public int getCount()
  {
    return mItems.size();
  }
  
  public Filter getFilter()
  {
    return new TimeZoneFilterTypeAdapter.ArrayFilter(this);
  }
  
  public Object getItem(int paramInt)
  {
    return mItems.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = mLayoutInflater.inflate(R.layout.suggest_item, paramViewGroup, false);
      paramView = paramViewGroup;
      localObject = new Peer(this, paramViewGroup);
      paramViewGroup.setTag(localObject);
      paramViewGroup = (ViewGroup)localObject;
    }
    else
    {
      paramViewGroup = (Peer)paramView.getTag();
    }
    Object localObject = (String)getItem(paramInt);
    a.setText((CharSequence)localObject);
    if (c)
    {
      a.setSingleLine();
      a.setEllipsize(TextUtils.TruncateAt.END);
    }
    return paramView;
  }
}
