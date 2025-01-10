package apps.afollestad.materialdialogs;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;

class Item
  extends RecyclerView.d0
  implements View.OnClickListener, View.OnLongClickListener
{
  final CompoundButton content;
  final RecyclerView this$0;
  final TextView title;
  
  Item(View paramView, RecyclerView paramRecyclerView)
  {
    super(paramView);
    content = ((CompoundButton)paramView.findViewById(R.id.md_control));
    title = ((TextView)paramView.findViewById(R.id.md_title));
    this$0 = paramRecyclerView;
    paramView.setOnClickListener(this);
    if (showmBuilder.onNeutralCallback != null) {
      paramView.setOnLongClickListener(this);
    }
  }
  
  public void onClick(View paramView)
  {
    if ((RecyclerView.access$getMLayoutRequestEaten(this$0) != null) && (getText() != -1))
    {
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (showthis$0).mBuilder.items != null)
      {
        localObject1 = localObject2;
        if (getText() < showthis$0).mBuilder.items.size()) {
          localObject1 = (CharSequence)showthis$0).mBuilder.items.get(getText());
        }
      }
      RecyclerView.access$getMLayoutRequestEaten(this$0).onClick(RecyclerView.show(this$0), paramView, getText(), (CharSequence)localObject1, false);
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    if ((RecyclerView.access$getMLayoutRequestEaten(this$0) != null) && (getText() != -1))
    {
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (showthis$0).mBuilder.items != null)
      {
        localObject1 = localObject2;
        if (getText() < showthis$0).mBuilder.items.size()) {
          localObject1 = (CharSequence)showthis$0).mBuilder.items.get(getText());
        }
      }
      return RecyclerView.access$getMLayoutRequestEaten(this$0).onClick(RecyclerView.show(this$0), paramView, getText(), (CharSequence)localObject1, true);
    }
    return false;
  }
}
