package com.afollestad.materialdialogs.color;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.Theme;
import apps.afollestad.materialdialogs.base.DialogUtils;
import apps.afollestad.materialdialogs.util.R.attr;
import apps.afollestad.materialdialogs.util.R.dimen;
import apps.afollestad.materialdialogs.util.R.drawable;
import apps.afollestad.materialdialogs.util.R.id;
import apps.afollestad.materialdialogs.util.R.layout;
import apps.afollestad.materialdialogs.util.R.string;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import java.io.Serializable;

public class ColorChooserDialog
  extends DialogFragment
  implements View.OnClickListener, View.OnLongClickListener
{
  private g a;
  private int mCircleSize;
  private View mColorChooserCustomFrame;
  private int[][] mColorsSub;
  private int[] mColorsTop;
  private EditText mCustomColorHex;
  private View mCustomColorIndicator;
  private SeekBar.OnSeekBarChangeListener mCustomColorRgbListener;
  private TextWatcher mCustomColorTextWatcher;
  private SeekBar mCustomSeekA;
  private TextView mCustomSeekAValue;
  private SeekBar mCustomSeekB;
  private TextView mCustomSeekBValue;
  private SeekBar mCustomSeekG;
  private TextView mCustomSeekGValue;
  private SeekBar mCustomSeekR;
  private TextView mCustomSeekRValue;
  private GridView mGrid;
  private int mSelectedCustomColor;
  
  public ColorChooserDialog() {}
  
  private void dismissIfNecessary(FragmentManager paramFragmentManager, String paramString)
  {
    paramString = paramFragmentManager.findFragmentByTag(paramString);
    if (paramString != null)
    {
      ((DialogFragment)paramString).dismiss();
      paramFragmentManager = paramFragmentManager.beginTransaction();
      paramFragmentManager.remove(paramString);
      paramFragmentManager.commit();
    }
  }
  
  private void findSubIndexForColor(int paramInt1, int paramInt2)
  {
    Object localObject = mColorsSub;
    if (localObject != null)
    {
      if (localObject.length - 1 < paramInt1) {
        return;
      }
      localObject = localObject[paramInt1];
      paramInt1 = 0;
      while (paramInt1 < localObject.length)
      {
        if (localObject[paramInt1] == paramInt2)
        {
          subIndex(paramInt1);
          return;
        }
        paramInt1 += 1;
      }
    }
  }
  
  private void generateColors()
  {
    Builder localBuilder = getBuilder();
    int[] arrayOfInt = mColorsTop;
    if (arrayOfInt != null)
    {
      mColorsTop = arrayOfInt;
      mColorsSub = mColorsSub;
      return;
    }
    if (mAccentMode)
    {
      mColorsTop = ColorPalette.ACCENT_COLORS;
      mColorsSub = ColorPalette.ACCENT_COLORS_SUB;
      return;
    }
    mColorsTop = ColorPalette.PRIMARY_COLORS;
    mColorsSub = ColorPalette.PRIMARY_COLORS_SUB;
  }
  
  private Builder getBuilder()
  {
    if ((getArguments() != null) && (getArguments().containsKey("builder"))) {
      return (Builder)getArguments().getSerializable("builder");
    }
    return null;
  }
  
  private int getSelectedColor()
  {
    View localView = mColorChooserCustomFrame;
    if ((localView != null) && (localView.getVisibility() == 0)) {
      return mSelectedCustomColor;
    }
    int i = 0;
    if (subIndex() > -1) {
      i = mColorsSub[topIndex()][subIndex()];
    } else if (topIndex() > -1) {
      i = mColorsTop[topIndex()];
    }
    int j = i;
    if (i == 0)
    {
      i = 0;
      if (Build.VERSION.SDK_INT >= 21) {
        i = DialogUtils.resolveColor(getActivity(), 16843829);
      }
      j = DialogUtils.resolveColor(getActivity(), R.attr.colorAccent, i);
    }
    return j;
  }
  
  private void invalidate()
  {
    if (mGrid.getAdapter() == null)
    {
      mGrid.setAdapter(new ColorGridAdapter());
      mGrid.setSelector(android.support.v4.content.view.Resources.getDrawable(getResources(), R.drawable.md_transparent, null));
    }
    else
    {
      ((BaseAdapter)mGrid.getAdapter()).notifyDataSetChanged();
    }
    if (getDialog() != null) {
      getDialog().setTitle(getTitle());
    }
  }
  
  private void invalidateDynamicButtonColors()
  {
    MaterialDialog localMaterialDialog = (MaterialDialog)getDialog();
    if (localMaterialDialog == null) {
      return;
    }
    if (getBuildermDynamicButtonColor)
    {
      int k = getSelectedColor();
      int i = k;
      int j;
      if (Color.alpha(k) >= 64)
      {
        j = i;
        if (Color.red(k) > 247)
        {
          j = i;
          if (Color.green(k) > 247)
          {
            j = i;
            if (Color.blue(k) <= 247) {}
          }
        }
      }
      else
      {
        j = Color.parseColor("#DEDEDE");
      }
      if (getBuildermDynamicButtonColor)
      {
        localMaterialDialog.getActionButton(DialogAction.POSITIVE).setTextColor(j);
        localMaterialDialog.getActionButton(DialogAction.NEGATIVE).setTextColor(j);
        localMaterialDialog.getActionButton(DialogAction.NEUTRAL).setTextColor(j);
      }
      if (mCustomSeekR != null)
      {
        if (mCustomSeekA.getVisibility() == 0) {
          MDTintHelper.setTint(mCustomSeekA, j);
        }
        MDTintHelper.setTint(mCustomSeekR, j);
        MDTintHelper.setTint(mCustomSeekG, j);
        MDTintHelper.setTint(mCustomSeekB, j);
      }
    }
  }
  
  private void isInSub(boolean paramBoolean)
  {
    getArguments().putBoolean("in_sub", paramBoolean);
  }
  
  private boolean isInSub()
  {
    return getArguments().getBoolean("in_sub", false);
  }
  
  private int subIndex()
  {
    if (mColorsSub == null) {
      return -1;
    }
    return getArguments().getInt("sub_index", -1);
  }
  
  private void subIndex(int paramInt)
  {
    if (mColorsSub == null) {
      return;
    }
    getArguments().putInt("sub_index", paramInt);
  }
  
  private void toggleCustom(MaterialDialog paramMaterialDialog)
  {
    MaterialDialog localMaterialDialog = paramMaterialDialog;
    if (paramMaterialDialog == null) {
      localMaterialDialog = (MaterialDialog)getDialog();
    }
    if (mGrid.getVisibility() == 0)
    {
      localMaterialDialog.setTitle(getBuildermCustomBtn);
      localMaterialDialog.setActionButton(DialogAction.NEUTRAL, getBuildermPresetsBtn);
      localMaterialDialog.setActionButton(DialogAction.NEGATIVE, getBuildermCancelBtn);
      mGrid.setVisibility(4);
      mColorChooserCustomFrame.setVisibility(0);
      mCustomColorTextWatcher = new ColorChooserDialog.5(this);
      mCustomColorHex.addTextChangedListener(mCustomColorTextWatcher);
      mCustomColorRgbListener = new ColorChooserDialog.6(this);
      mCustomSeekR.setOnSeekBarChangeListener(mCustomColorRgbListener);
      mCustomSeekG.setOnSeekBarChangeListener(mCustomColorRgbListener);
      mCustomSeekB.setOnSeekBarChangeListener(mCustomColorRgbListener);
      if (mCustomSeekA.getVisibility() == 0)
      {
        mCustomSeekA.setOnSeekBarChangeListener(mCustomColorRgbListener);
        mCustomColorHex.setText(String.format("%08X", new Object[] { Integer.valueOf(mSelectedCustomColor) }));
        return;
      }
      mCustomColorHex.setText(String.format("%06X", new Object[] { Integer.valueOf(0xFFFFFF & mSelectedCustomColor) }));
      return;
    }
    localMaterialDialog.setTitle(getBuildermTitle);
    localMaterialDialog.setActionButton(DialogAction.NEUTRAL, getBuildermCustomBtn);
    if (isInSub()) {
      localMaterialDialog.setActionButton(DialogAction.NEGATIVE, getBuildermBackBtn);
    } else {
      localMaterialDialog.setActionButton(DialogAction.NEGATIVE, getBuildermCancelBtn);
    }
    mGrid.setVisibility(0);
    mColorChooserCustomFrame.setVisibility(8);
    mCustomColorHex.removeTextChangedListener(mCustomColorTextWatcher);
    mCustomColorTextWatcher = null;
    mCustomSeekR.setOnSeekBarChangeListener(null);
    mCustomSeekG.setOnSeekBarChangeListener(null);
    mCustomSeekB.setOnSeekBarChangeListener(null);
    mCustomColorRgbListener = null;
  }
  
  private int topIndex()
  {
    return getArguments().getInt("top_index", -1);
  }
  
  private void topIndex(int paramInt)
  {
    if (paramInt > -1) {
      findSubIndexForColor(paramInt, mColorsTop[paramInt]);
    }
    getArguments().putInt("top_index", paramInt);
  }
  
  public int getTitle()
  {
    Builder localBuilder = getBuilder();
    int i;
    if (isInSub()) {
      i = mTitleSub;
    } else {
      i = mTitle;
    }
    int j = i;
    if (i == 0) {
      j = mTitle;
    }
    return j;
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    if ((getActivity() instanceof g))
    {
      a = ((g)getActivity());
      return;
    }
    if ((getParentFragment() instanceof g))
    {
      a = ((g)getParentFragment());
      return;
    }
    throw new IllegalStateException("ColorChooserDialog needs to be shown from an Activity/Fragment implementing ColorCallback.");
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getTag() != null)
    {
      int i = Integer.parseInt(((String)paramView.getTag()).split(":")[0]);
      paramView = (MaterialDialog)getDialog();
      Builder localBuilder = getBuilder();
      if (isInSub())
      {
        subIndex(i);
      }
      else
      {
        topIndex(i);
        int[][] arrayOfInt = mColorsSub;
        if ((arrayOfInt != null) && (i < arrayOfInt.length))
        {
          paramView.setActionButton(DialogAction.NEGATIVE, mBackBtn);
          isInSub(true);
        }
      }
      if (mAllowUserCustom) {
        mSelectedCustomColor = getSelectedColor();
      }
      invalidateDynamicButtonColors();
      invalidate();
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    if ((getArguments() != null) && (getArguments().containsKey("builder")))
    {
      generateColors();
      int i = 0;
      int m = 0;
      int j;
      int k;
      if (paramBundle != null)
      {
        i = paramBundle.getBoolean("in_custom", false) ^ true;
        j = getSelectedColor();
      }
      else if (getBuildermSetPreselectionColor)
      {
        int n = getBuildermPreselect;
        j = n;
        if (n != 0)
        {
          k = 0;
          for (i = m;; i = m)
          {
            paramBundle = mColorsTop;
            j = i;
            if (k >= paramBundle.length) {
              break;
            }
            if (paramBundle[k] == n)
            {
              j = 1;
              topIndex(k);
              if (getBuildermAccentMode)
              {
                subIndex(2);
                break;
              }
              if (mColorsSub != null)
              {
                findSubIndexForColor(k, n);
                break;
              }
              subIndex(5);
              break;
            }
            m = i;
            if (mColorsSub != null)
            {
              m = 0;
              for (;;)
              {
                paramBundle = mColorsSub;
                j = i;
                if (m >= paramBundle[k].length) {
                  break;
                }
                if (paramBundle[k][m] == n)
                {
                  j = 1;
                  topIndex(k);
                  subIndex(m);
                  break;
                }
                m += 1;
              }
              m = j;
              if (j != 0) {
                break;
              }
            }
            k += 1;
          }
          i = j;
          j = n;
        }
      }
      else
      {
        j = -16777216;
        i = 1;
      }
      mCircleSize = getResources().getDimensionPixelSize(R.dimen.md_colorchooser_circlesize);
      paramBundle = getBuilder();
      Object localObject1 = new MaterialDialog.Builder(getActivity());
      ((MaterialDialog.Builder)localObject1).title(getTitle());
      ((MaterialDialog.Builder)localObject1).autoDismiss(false);
      ((MaterialDialog.Builder)localObject1).customView(R.layout.md_dialog_colorchooser, false);
      ((MaterialDialog.Builder)localObject1).negativeText(mCancelBtn);
      ((MaterialDialog.Builder)localObject1).positiveText(mDoneBtn);
      if (mAllowUserCustom) {
        k = mCustomBtn;
      } else {
        k = 0;
      }
      ((MaterialDialog.Builder)localObject1).neutralText(k);
      ((MaterialDialog.Builder)localObject1).get(contents, current);
      ((MaterialDialog.Builder)localObject1).callback(new e(this));
      ((MaterialDialog.Builder)localObject1).onPositive(new d(this));
      ((MaterialDialog.Builder)localObject1).onNegative(new Label(this));
      localObject1 = ((MaterialDialog.Builder)localObject1).showListener(new LicensesDialog.6(this));
      Object localObject2 = LIGHT;
      if (localObject2 != null) {
        ((MaterialDialog.Builder)localObject1).theme((Theme)localObject2);
      }
      localObject1 = ((MaterialDialog.Builder)localObject1).build();
      localObject2 = ((MaterialDialog)localObject1).getCustomView();
      mGrid = ((GridView)((View)localObject2).findViewById(R.id.md_grid));
      if (mAllowUserCustom)
      {
        mSelectedCustomColor = j;
        mColorChooserCustomFrame = ((View)localObject2).findViewById(R.id.md_colorChooserCustomFrame);
        mCustomColorHex = ((EditText)((View)localObject2).findViewById(R.id.md_hexInput));
        mCustomColorIndicator = ((View)localObject2).findViewById(R.id.md_colorIndicator);
        mCustomSeekA = ((SeekBar)((View)localObject2).findViewById(R.id.md_colorA));
        mCustomSeekAValue = ((TextView)((View)localObject2).findViewById(R.id.md_colorAValue));
        mCustomSeekR = ((SeekBar)((View)localObject2).findViewById(R.id.md_colorR));
        mCustomSeekRValue = ((TextView)((View)localObject2).findViewById(R.id.md_colorRValue));
        mCustomSeekG = ((SeekBar)((View)localObject2).findViewById(R.id.md_colorG));
        mCustomSeekGValue = ((TextView)((View)localObject2).findViewById(R.id.md_colorGValue));
        mCustomSeekB = ((SeekBar)((View)localObject2).findViewById(R.id.md_colorB));
        mCustomSeekBValue = ((TextView)((View)localObject2).findViewById(R.id.md_colorBValue));
        if (!mAllowUserCustomAlpha)
        {
          ((View)localObject2).findViewById(R.id.md_colorALabel).setVisibility(8);
          mCustomSeekA.setVisibility(8);
          mCustomSeekAValue.setVisibility(8);
          mCustomColorHex.setHint("2196F3");
          mCustomColorHex.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });
        }
        else
        {
          mCustomColorHex.setHint("FF2196F3");
          mCustomColorHex.setFilters(new InputFilter[] { new InputFilter.LengthFilter(8) });
        }
        if (i == 0) {
          toggleCustom((MaterialDialog)localObject1);
        }
      }
      invalidate();
      return localObject1;
    }
    paramBundle = new IllegalStateException("ColorChooserDialog should be created using its Builder interface.");
    throw paramBundle;
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    super.onDismiss(paramDialogInterface);
    paramDialogInterface = a;
    if (paramDialogInterface != null) {
      paramDialogInterface.onCloseMenu(this);
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    if (paramView.getTag() != null)
    {
      int i = Integer.parseInt(((String)paramView.getTag()).split(":")[1]);
      ((CircleView)paramView).showHint(i);
      return true;
    }
    return false;
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("top_index", topIndex());
    paramBundle.putBoolean("in_sub", isInSub());
    paramBundle.putInt("sub_index", subIndex());
    View localView = mColorChooserCustomFrame;
    boolean bool;
    if ((localView != null) && (localView.getVisibility() == 0)) {
      bool = true;
    } else {
      bool = false;
    }
    paramBundle.putBoolean("in_custom", bool);
  }
  
  public ColorChooserDialog show(FragmentManager paramFragmentManager)
  {
    Builder localBuilder = getBuilder();
    if ((mColorsTop == null) && (!mAccentMode)) {}
    dismissIfNecessary(paramFragmentManager, "[MD_COLOR_CHOOSER]");
    show(paramFragmentManager, "[MD_COLOR_CHOOSER]");
    return this;
  }
  
  public class Builder
    implements Serializable
  {
    Theme LIGHT;
    String contents;
    String current;
    boolean mAccentMode = false;
    boolean mAllowUserCustom = true;
    boolean mAllowUserCustomAlpha = true;
    int mBackBtn = R.string.md_back_label;
    int mCancelBtn = R.string.md_cancel_label;
    int[][] mColorsSub;
    int[] mColorsTop;
    int mCustomBtn = R.string.md_custom_label;
    int mDoneBtn = R.string.md_done_label;
    boolean mDynamicButtonColor = true;
    int mPreselect;
    int mPresetsBtn = R.string.md_presets_label;
    boolean mSetPreselectionColor = false;
    final int mTitle;
    int mTitleSub;
    
    public Builder(int paramInt)
    {
      mTitle = paramInt;
    }
    
    public Builder allowUserColorInput(boolean paramBoolean)
    {
      mAllowUserCustom = paramBoolean;
      return this;
    }
    
    public Builder allowUserColorInputAlpha(boolean paramBoolean)
    {
      mAllowUserCustomAlpha = paramBoolean;
      return this;
    }
    
    public ColorChooserDialog build()
    {
      ColorChooserDialog localColorChooserDialog = new ColorChooserDialog();
      Bundle localBundle = new Bundle();
      localBundle.putSerializable("builder", this);
      localColorChooserDialog.setArguments(localBundle);
      return localColorChooserDialog;
    }
    
    public Builder cancelButton(int paramInt)
    {
      mCancelBtn = paramInt;
      return this;
    }
    
    public Builder customColors(int[] paramArrayOfInt, int[][] paramArrayOfInt1)
    {
      mColorsTop = paramArrayOfInt;
      mColorsSub = paramArrayOfInt1;
      return this;
    }
    
    public Builder doneButton(int paramInt)
    {
      mDoneBtn = paramInt;
      return this;
    }
    
    public Builder preselect(int paramInt)
    {
      mPreselect = paramInt;
      mSetPreselectionColor = true;
      return this;
    }
    
    public Builder set(String paramString1, String paramString2)
    {
      contents = paramString1;
      current = paramString2;
      return this;
    }
    
    public ColorChooserDialog show(FragmentActivity paramFragmentActivity)
    {
      return show(paramFragmentActivity.getSupportFragmentManager());
    }
    
    public ColorChooserDialog show(FragmentManager paramFragmentManager)
    {
      ColorChooserDialog localColorChooserDialog = build();
      localColorChooserDialog.show(paramFragmentManager);
      return localColorChooserDialog;
    }
  }
  
  class ColorGridAdapter
    extends BaseAdapter
  {
    ColorGridAdapter() {}
    
    public int getCount()
    {
      if (ColorChooserDialog.access$getIsInSub(ColorChooserDialog.this)) {
        return ColorChooserDialog.access$getMColorsSub(ColorChooserDialog.this)[ColorChooserDialog.access$getTopIndex(ColorChooserDialog.this)].length;
      }
      return ColorChooserDialog.access$getMColorsTop(ColorChooserDialog.this).length;
    }
    
    public Object getItem(int paramInt)
    {
      if (ColorChooserDialog.access$getIsInSub(ColorChooserDialog.this)) {
        return Integer.valueOf(ColorChooserDialog.access$getMColorsSub(ColorChooserDialog.this)[ColorChooserDialog.access$getTopIndex(ColorChooserDialog.this)][paramInt]);
      }
      return Integer.valueOf(ColorChooserDialog.access$getMColorsTop(ColorChooserDialog.this)[paramInt]);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      if (paramView == null)
      {
        paramView = new CircleView(getContext());
        paramViewGroup = paramView;
        paramView.setLayoutParams(new AbsListView.LayoutParams(ColorChooserDialog.access$getMCircleSize(ColorChooserDialog.this), ColorChooserDialog.access$getMCircleSize(ColorChooserDialog.this)));
      }
      paramView = (CircleView)paramViewGroup;
      int i;
      if (ColorChooserDialog.access$getIsInSub(ColorChooserDialog.this)) {
        i = ColorChooserDialog.access$getMColorsSub(ColorChooserDialog.this)[ColorChooserDialog.access$getTopIndex(ColorChooserDialog.this)][paramInt];
      } else {
        i = ColorChooserDialog.access$getMColorsTop(ColorChooserDialog.this)[paramInt];
      }
      paramView.setBackgroundColor(i);
      boolean bool;
      if (ColorChooserDialog.access$getIsInSub(ColorChooserDialog.this))
      {
        if (ColorChooserDialog.access$getSubIndex(ColorChooserDialog.this) == paramInt) {
          bool = true;
        } else {
          bool = false;
        }
        paramView.setSelected(bool);
      }
      else
      {
        if (ColorChooserDialog.access$getTopIndex(ColorChooserDialog.this) == paramInt) {
          bool = true;
        } else {
          bool = false;
        }
        paramView.setSelected(bool);
      }
      paramView.setTag(String.format("%d:%d", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i) }));
      paramView.setOnClickListener(ColorChooserDialog.this);
      paramView.setOnLongClickListener(ColorChooserDialog.this);
      return paramViewGroup;
    }
  }
}
