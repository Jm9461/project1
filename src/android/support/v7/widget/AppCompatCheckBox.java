package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import org.org.v4.content.R.attr;
import org.org.v4.gui.helpers.Resources;

public class AppCompatCheckBox
  extends CheckBox
  implements TintableCompoundButton
{
  private final AppCompatCompoundButtonHelper mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
  
  public AppCompatCheckBox(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatCheckBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.checkboxStyle);
  }
  
  public AppCompatCheckBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mCompoundButtonHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  public int getCompoundPaddingLeft()
  {
    int j = super.getCompoundPaddingLeft();
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    int i = j;
    if (localAppCompatCompoundButtonHelper != null) {
      i = localAppCompatCompoundButtonHelper.getCompoundPaddingLeft(j);
    }
    return i;
  }
  
  public ColorStateList getSupportButtonTintList()
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      return localAppCompatCompoundButtonHelper.getSupportButtonTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportButtonTintMode()
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      return localAppCompatCompoundButtonHelper.getSupportButtonTintMode();
    }
    return null;
  }
  
  public void setButtonDrawable(int paramInt)
  {
    setButtonDrawable(Resources.getDrawable(getContext(), paramInt));
  }
  
  public void setButtonDrawable(Drawable paramDrawable)
  {
    super.setButtonDrawable(paramDrawable);
    paramDrawable = mCompoundButtonHelper;
    if (paramDrawable != null) {
      paramDrawable.onSetButtonDrawable();
    }
  }
  
  public void setSupportButtonTintList(ColorStateList paramColorStateList)
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      localAppCompatCompoundButtonHelper.setSupportButtonTintList(paramColorStateList);
    }
  }
  
  public void setSupportButtonTintMode(PorterDuff.Mode paramMode)
  {
    AppCompatCompoundButtonHelper localAppCompatCompoundButtonHelper = mCompoundButtonHelper;
    if (localAppCompatCompoundButtonHelper != null) {
      localAppCompatCompoundButtonHelper.setSupportButtonTintMode(paramMode);
    }
  }
}
