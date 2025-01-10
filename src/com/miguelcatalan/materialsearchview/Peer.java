package com.miguelcatalan.materialsearchview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class Peer
{
  TextView a;
  ImageView b;
  
  public Peer(IconifiedTextListAdapter paramIconifiedTextListAdapter, View paramView)
  {
    a = ((TextView)paramView.findViewById(R.id.suggestion_text));
    if (IconifiedTextListAdapter.getDrawable(paramIconifiedTextListAdapter) != null)
    {
      b = ((ImageView)paramView.findViewById(R.id.suggestion_icon));
      b.setImageDrawable(IconifiedTextListAdapter.getDrawable(paramIconifiedTextListAdapter));
    }
  }
}
