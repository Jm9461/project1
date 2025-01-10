package apps.afollestad.materialdialogs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView.d0;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import apps.afollestad.materialdialogs.base.DialogUtils;
import b.a.a.a.b;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import java.util.ArrayList;
import java.util.List;

class RecyclerView
  extends RecyclerView.g<a.b>
{
  private final int header;
  private final GravityEnum itemGravity;
  private Settings.1.1 mLayoutRequestEaten;
  private final MaterialDialog this$0;
  
  RecyclerView(MaterialDialog paramMaterialDialog, int paramInt)
  {
    this$0 = paramMaterialDialog;
    header = paramInt;
    itemGravity = mBuilder.itemsGravity;
  }
  
  private boolean isRTL()
  {
    if (Build.VERSION.SDK_INT < 17) {
      return false;
    }
    return this$0.getBuilder().getContext().getResources().getConfiguration().getLayoutDirection() == 1;
  }
  
  private void setupGravity(ViewGroup paramViewGroup)
  {
    ((LinearLayout)paramViewGroup).setGravity(itemGravity.getGravityInt() | 0x10);
    if (paramViewGroup.getChildCount() == 2)
    {
      CompoundButton localCompoundButton;
      TextView localTextView;
      if ((itemGravity == GravityEnum.END) && (!isRTL()) && ((paramViewGroup.getChildAt(0) instanceof CompoundButton)))
      {
        localCompoundButton = (CompoundButton)paramViewGroup.getChildAt(0);
        paramViewGroup.removeView(localCompoundButton);
        localTextView = (TextView)paramViewGroup.getChildAt(0);
        paramViewGroup.removeView(localTextView);
        localTextView.setPadding(localTextView.getPaddingRight(), localTextView.getPaddingTop(), localTextView.getPaddingLeft(), localTextView.getPaddingBottom());
        paramViewGroup.addView(localTextView);
        paramViewGroup.addView(localCompoundButton);
        return;
      }
      if ((itemGravity == GravityEnum.START) && (isRTL()) && ((paramViewGroup.getChildAt(1) instanceof CompoundButton)))
      {
        localCompoundButton = (CompoundButton)paramViewGroup.getChildAt(1);
        paramViewGroup.removeView(localCompoundButton);
        localTextView = (TextView)paramViewGroup.getChildAt(0);
        paramViewGroup.removeView(localTextView);
        localTextView.setPadding(localTextView.getPaddingRight(), localTextView.getPaddingTop(), localTextView.getPaddingRight(), localTextView.getPaddingBottom());
        paramViewGroup.addView(localCompoundButton);
        paramViewGroup.addView(localTextView);
      }
    }
  }
  
  public int a()
  {
    ArrayList localArrayList = this$0.mBuilder.items;
    if (localArrayList != null) {
      return localArrayList.size();
    }
    return 0;
  }
  
  public Item a(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup = LayoutInflater.from(paramViewGroup.getContext()).inflate(header, paramViewGroup, false);
    DialogUtils.setBackgroundCompat(paramViewGroup, this$0.getListSelector());
    return new Item(paramViewGroup, this);
  }
  
  public void init(Item paramItem, int paramInt)
  {
    View localView = itemView;
    boolean bool2 = DialogUtils.a(Integer.valueOf(paramInt), this$0.mBuilder.listType);
    int i;
    if (bool2) {
      i = DialogUtils.adjustAlpha(this$0.mBuilder.itemColor, 0.4F);
    } else {
      i = this$0.mBuilder.itemColor;
    }
    itemView.setEnabled(bool2 ^ true);
    int j = SecurityConstants.$SwitchMap$com$afollestad$materialdialogs$MaterialDialog$ListType[this$0.listType.ordinal()];
    boolean bool1;
    MaterialDialog.Builder localBuilder;
    ColorStateList localColorStateList;
    if (j != 1)
    {
      if (j == 2)
      {
        localObject = (CheckBox)content;
        bool1 = this$0.selectedIndicesList.contains(Integer.valueOf(paramInt));
        localBuilder = this$0.mBuilder;
        localColorStateList = mProgress;
        if (localColorStateList != null) {
          MDTintHelper.setTint((CheckBox)localObject, localColorStateList);
        } else {
          MDTintHelper.setTint((CheckBox)localObject, widgetColor);
        }
        ((CompoundButton)localObject).setChecked(bool1);
        ((TextView)localObject).setEnabled(bool2 ^ true);
      }
    }
    else
    {
      localObject = (RadioButton)content;
      if (this$0.mBuilder.selectedIndex == paramInt) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      localBuilder = this$0.mBuilder;
      localColorStateList = mProgress;
      if (localColorStateList != null) {
        MDTintHelper.setTint((RadioButton)localObject, localColorStateList);
      } else {
        MDTintHelper.setTint((RadioButton)localObject, widgetColor);
      }
      ((CompoundButton)localObject).setChecked(bool1);
      ((TextView)localObject).setEnabled(bool2 ^ true);
    }
    title.setText((CharSequence)this$0.mBuilder.items.get(paramInt));
    title.setTextColor(i);
    Object localObject = this$0;
    ((MaterialDialog)localObject).setTypeface(title, mBuilder.regularFont);
    setupGravity((ViewGroup)localView);
    paramItem = this$0.mBuilder.itemIds;
    if (paramItem != null) {
      if (paramInt < paramItem.length) {
        localView.setId(paramItem[paramInt]);
      } else {
        localView.setId(-1);
      }
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramItem = (ViewGroup)localView;
      if (paramItem.getChildCount() == 2)
      {
        if ((paramItem.getChildAt(0) instanceof CompoundButton))
        {
          paramItem.getChildAt(0).setBackground(null);
          return;
        }
        if ((paramItem.getChildAt(1) instanceof CompoundButton)) {
          paramItem.getChildAt(1).setBackground(null);
        }
      }
    }
  }
  
  void setOnScrollListener(Settings.1.1 param1)
  {
    mLayoutRequestEaten = param1;
  }
}
