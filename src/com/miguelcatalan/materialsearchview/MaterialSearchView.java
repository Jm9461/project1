package com.miguelcatalan.materialsearchview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filter.FilterListener;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.miguelcatalan.materialsearchview.widget.Snackbar;
import com.miguelcatalan.materialsearchview.widget.ToolbarWidgetWrapper;
import java.lang.reflect.Field;
import java.util.List;

public class MaterialSearchView
  extends FrameLayout
  implements Filter.FilterListener
{
  private CharSequence a;
  private boolean b = false;
  private final View.OnClickListener buttonListener = new d();
  private boolean c = false;
  private Context context;
  private i d;
  private View emptyView;
  private boolean header;
  private j i;
  private boolean l = false;
  private Drawable label;
  private ListView list;
  private int listView;
  private ListAdapter mAdapter;
  private boolean mClearingFocus;
  private MenuItem mMenuItem;
  private View nextButton;
  private ImageButton playButton;
  private ImageButton previousButton;
  private CharSequence r;
  private ImageButton shuffleButton;
  private EditText text;
  private RelativeLayout this$0;
  private h x;
  
  public MaterialSearchView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MaterialSearchView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MaterialSearchView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    context = paramContext;
    onCreateView();
    parseAttributes(paramAttributeSet, paramInt);
  }
  
  private void addListeners()
  {
    text.setOnEditorActionListener(new a());
    text.addTextChangedListener(new b());
    text.setOnFocusChangeListener(new c());
  }
  
  private boolean auth()
  {
    if (isInEditMode()) {
      return true;
    }
    return getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() == 0;
  }
  
  private void b(CharSequence paramCharSequence)
  {
    Editable localEditable = text.getText();
    a = localEditable;
    if ((TextUtils.isEmpty(localEditable) ^ true))
    {
      previousButton.setVisibility(0);
      a(false);
    }
    else
    {
      previousButton.setVisibility(8);
      a(true);
    }
    if ((x != null) && (!TextUtils.equals(paramCharSequence, r))) {
      x.a(paramCharSequence.toString());
    }
    r = paramCharSequence.toString();
  }
  
  private void filter(CharSequence paramCharSequence)
  {
    ListAdapter localListAdapter = mAdapter;
    if ((localListAdapter != null) && ((localListAdapter instanceof Filterable))) {
      ((Filterable)localListAdapter).getFilter().filter(paramCharSequence, this);
    }
  }
  
  private void handleResult()
  {
    Intent localIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    localIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
    localIntent.putExtra("android.speech.extra.MAX_RESULTS", 1);
    Context localContext = context;
    if ((localContext instanceof Activity)) {
      ((Activity)localContext).startActivityForResult(localIntent, 9999);
    }
  }
  
  private void onCreateView()
  {
    LayoutInflater.from(context).inflate(R.layout.search_view, this, true);
    emptyView = findViewById(R.id.search_layout);
    this$0 = ((RelativeLayout)emptyView.findViewById(R.id.search_top_bar));
    list = ((ListView)emptyView.findViewById(R.id.suggestion_list));
    text = ((EditText)emptyView.findViewById(R.id.searchTextView));
    shuffleButton = ((ImageButton)emptyView.findViewById(R.id.action_up_btn));
    playButton = ((ImageButton)emptyView.findViewById(R.id.action_voice_btn));
    previousButton = ((ImageButton)emptyView.findViewById(R.id.action_empty_btn));
    nextButton = emptyView.findViewById(R.id.transparent_view);
    text.setOnClickListener(buttonListener);
    shuffleButton.setOnClickListener(buttonListener);
    playButton.setOnClickListener(buttonListener);
    previousButton.setOnClickListener(buttonListener);
    nextButton.setOnClickListener(buttonListener);
    header = false;
    a(true);
    addListeners();
    list.setVisibility(8);
    setAnimationDuration(Snackbar.mType);
  }
  
  private void onPostExecute()
  {
    g localG = new g();
    if (Build.VERSION.SDK_INT >= 21)
    {
      emptyView.setVisibility(0);
      Snackbar.show(this$0, localG);
      return;
    }
    Snackbar.show(emptyView, listView, localG);
  }
  
  private void parseAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = context.obtainStyledAttributes(paramAttributeSet, R.styleable.MaterialSearchView, paramInt, 0);
    if (paramAttributeSet != null)
    {
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchBackground)) {
        setBackground(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchBackground));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_android_textColor)) {
        setTextColor(paramAttributeSet.getColor(R.styleable.MaterialSearchView_android_textColor, 0));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_android_textColorHint)) {
        setHintTextColor(paramAttributeSet.getColor(R.styleable.MaterialSearchView_android_textColorHint, 0));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_android_hint)) {
        setHint(paramAttributeSet.getString(R.styleable.MaterialSearchView_android_hint));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchVoiceIcon)) {
        setVoiceIcon(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchVoiceIcon));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchCloseIcon)) {
        setCloseIcon(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchCloseIcon));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchBackIcon)) {
        setBackIcon(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchBackIcon));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchSuggestionBackground)) {
        setSuggestionBackground(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchSuggestionBackground));
      }
      if (paramAttributeSet.hasValue(R.styleable.MaterialSearchView_searchSuggestionIcon)) {
        setSuggestionIcon(paramAttributeSet.getDrawable(R.styleable.MaterialSearchView_searchSuggestionIcon));
      }
      paramAttributeSet.recycle();
    }
  }
  
  private void show()
  {
    Editable localEditable = text.getText();
    if ((localEditable != null) && (TextUtils.getTrimmedLength(localEditable) > 0))
    {
      h localH = x;
      if ((localH == null) || (!localH.add(localEditable.toString())))
      {
        update();
        text.setText(null);
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if ((paramBoolean) && (auth()) && (header))
    {
      playButton.setVisibility(0);
      return;
    }
    playButton.setVisibility(8);
  }
  
  public void b(CharSequence paramCharSequence, boolean paramBoolean)
  {
    text.setText(paramCharSequence);
    if (paramCharSequence != null)
    {
      EditText localEditText = text;
      localEditText.setSelection(localEditText.length());
      a = paramCharSequence;
    }
    if ((paramBoolean) && (!TextUtils.isEmpty(paramCharSequence))) {
      show();
    }
  }
  
  public void b(boolean paramBoolean)
  {
    if (get()) {
      return;
    }
    text.setText(null);
    text.requestFocus();
    if (paramBoolean)
    {
      onPostExecute();
    }
    else
    {
      emptyView.setVisibility(0);
      j localJ = i;
      if (localJ != null) {
        localJ.a();
      }
    }
    c = true;
  }
  
  public void clear()
  {
    b(true);
  }
  
  public void clearFocus()
  {
    mClearingFocus = true;
    startDrag(this);
    super.clearFocus();
    text.clearFocus();
    mClearingFocus = false;
  }
  
  public boolean get()
  {
    return c;
  }
  
  public void onFilterComplete(int paramInt)
  {
    if (paramInt > 0)
    {
      setValue();
      return;
    }
    updateTextAndIcon();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof i))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    d = ((i)paramParcelable);
    if (d.c)
    {
      b(false);
      b(d.a, false);
    }
    super.onRestoreInstanceState(d.getSuperState());
  }
  
  public Parcelable onSaveInstanceState()
  {
    d = new i(super.onSaveInstanceState());
    i localI = d;
    Object localObject = a;
    if (localObject != null) {
      localObject = ((CharSequence)localObject).toString();
    } else {
      localObject = null;
    }
    a = ((String)localObject);
    localObject = d;
    c = c;
    return localObject;
  }
  
  public boolean requestFocus(int paramInt, Rect paramRect)
  {
    if (mClearingFocus) {
      return false;
    }
    if (!isFocusable()) {
      return false;
    }
    return text.requestFocus(paramInt, paramRect);
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    mAdapter = paramListAdapter;
    list.setAdapter(paramListAdapter);
    filter(text.getText());
  }
  
  public void setAnimationDuration(int paramInt)
  {
    listView = paramInt;
  }
  
  public void setBackIcon(Drawable paramDrawable)
  {
    shuffleButton.setImageDrawable(paramDrawable);
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      this$0.setBackground(paramDrawable);
      return;
    }
    this$0.setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this$0.setBackgroundColor(paramInt);
  }
  
  public void setCloseIcon(Drawable paramDrawable)
  {
    previousButton.setImageDrawable(paramDrawable);
  }
  
  public void setCursorDrawable(int paramInt)
  {
    try
    {
      Field localField = TextView.class.getDeclaredField("mCursorDrawableRes");
      localField.setAccessible(true);
      EditText localEditText = text;
      localField.set(localEditText, Integer.valueOf(paramInt));
      return;
    }
    catch (Exception localException)
    {
      Log.e("MaterialSearchView", localException.toString());
    }
  }
  
  public void setEllipsize(boolean paramBoolean)
  {
    l = paramBoolean;
  }
  
  public void setHint(CharSequence paramCharSequence)
  {
    text.setHint(paramCharSequence);
  }
  
  public void setHintTextColor(int paramInt)
  {
    text.setHintTextColor(paramInt);
  }
  
  public void setMenuItem(MenuItem paramMenuItem)
  {
    mMenuItem = paramMenuItem;
    mMenuItem.setOnMenuItemClickListener(new f());
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    list.setOnItemClickListener(paramOnItemClickListener);
  }
  
  public void setOnQueryTextListener(h paramH)
  {
    x = paramH;
  }
  
  public void setOnSearchViewListener(j paramJ)
  {
    i = paramJ;
  }
  
  public void setSubmitOnClick(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void setSuggestionBackground(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      list.setBackground(paramDrawable);
      return;
    }
    list.setBackgroundDrawable(paramDrawable);
  }
  
  public void setSuggestionIcon(Drawable paramDrawable)
  {
    label = paramDrawable;
  }
  
  public void setSuggestions(final String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      nextButton.setVisibility(0);
      paramArrayOfString = new IconifiedTextListAdapter(context, paramArrayOfString, label, l);
      setAdapter(paramArrayOfString);
      setOnItemClickListener(new e(paramArrayOfString));
      return;
    }
    nextButton.setVisibility(8);
  }
  
  public void setTextColor(int paramInt)
  {
    text.setTextColor(paramInt);
  }
  
  public void setValue()
  {
    ListAdapter localListAdapter = mAdapter;
    if ((localListAdapter != null) && (localListAdapter.getCount() > 0) && (list.getVisibility() == 8)) {
      list.setVisibility(0);
    }
  }
  
  public void setVoiceIcon(Drawable paramDrawable)
  {
    playButton.setImageDrawable(paramDrawable);
  }
  
  public void setVoiceSearch(boolean paramBoolean)
  {
    header = paramBoolean;
  }
  
  public void startDrag(View paramView)
  {
    ((InputMethodManager)paramView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public void update()
  {
    if (!get()) {
      return;
    }
    text.setText(null);
    updateTextAndIcon();
    clearFocus();
    emptyView.setVisibility(8);
    j localJ = i;
    if (localJ != null) {
      localJ.rewind();
    }
    c = false;
  }
  
  public void updateTextAndIcon()
  {
    if (list.getVisibility() == 0) {
      list.setVisibility(8);
    }
  }
  
  public void updateView(View paramView)
  {
    if ((Build.VERSION.SDK_INT <= 10) && (paramView.hasFocus())) {
      paramView.clearFocus();
    }
    paramView.requestFocus();
    ((InputMethodManager)paramView.getContext().getSystemService("input_method")).showSoftInput(paramView, 0);
  }
  
  class a
    implements TextView.OnEditorActionListener
  {
    a() {}
    
    public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
    {
      MaterialSearchView.downloadImage(MaterialSearchView.this);
      return true;
    }
  }
  
  class b
    implements TextWatcher
  {
    b() {}
    
    public void afterTextChanged(Editable paramEditable) {}
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      MaterialSearchView.a(MaterialSearchView.this, paramCharSequence);
      MaterialSearchView.remove(MaterialSearchView.this, paramCharSequence);
      MaterialSearchView.d(MaterialSearchView.this, paramCharSequence);
    }
  }
  
  class c
    implements View.OnFocusChangeListener
  {
    c() {}
    
    public void onFocusChange(View paramView, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        paramView = MaterialSearchView.this;
        paramView.updateView(MaterialSearchView.access$getMSearchText(paramView));
        setValue();
      }
    }
  }
  
  class d
    implements View.OnClickListener
  {
    d() {}
    
    public void onClick(View paramView)
    {
      if (paramView == MaterialSearchView.access$getShuffleButton(MaterialSearchView.this))
      {
        update();
        return;
      }
      if (paramView == MaterialSearchView.access$getPlayButton(MaterialSearchView.this))
      {
        MaterialSearchView.startSync(MaterialSearchView.this);
        return;
      }
      if (paramView == MaterialSearchView.access$getPreviousButton(MaterialSearchView.this))
      {
        MaterialSearchView.access$getMSearchText(MaterialSearchView.this).setText(null);
        return;
      }
      if (paramView == MaterialSearchView.access$getMSearchText(MaterialSearchView.this))
      {
        setValue();
        return;
      }
      if (paramView == MaterialSearchView.access$getNextButton(MaterialSearchView.this)) {
        update();
      }
    }
  }
  
  class e
    implements AdapterView.OnItemClickListener
  {
    e(IconifiedTextListAdapter paramIconifiedTextListAdapter) {}
    
    public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      b((String)paramArrayOfString.getItem(paramInt), MaterialSearchView.b(MaterialSearchView.this));
    }
  }
  
  class f
    implements MenuItem.OnMenuItemClickListener
  {
    f() {}
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      clear();
      return true;
    }
  }
  
  class g
    implements ToolbarWidgetWrapper
  {
    g() {}
    
    public boolean get(View paramView)
    {
      return false;
    }
    
    public boolean playSound(View paramView)
    {
      return false;
    }
    
    public boolean setVisible(View paramView)
    {
      if (MaterialSearchView.c(MaterialSearchView.this) != null) {
        MaterialSearchView.c(MaterialSearchView.this).a();
      }
      return false;
    }
  }
  
  public static abstract interface h
  {
    public abstract boolean a(String paramString);
    
    public abstract boolean add(String paramString);
  }
  
  static class i
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<i> CREATOR = new a();
    String a;
    boolean c;
    
    private i(Parcel paramParcel)
    {
      super();
      a = paramParcel.readString();
      int i = paramParcel.readInt();
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      c = bool;
    }
    
    i(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    static final class a
      implements Parcelable.Creator<MaterialSearchView.i>
    {
      a() {}
      
      public MaterialSearchView.i createFromParcel(Parcel paramParcel)
      {
        return new MaterialSearchView.i(paramParcel, null);
      }
      
      public MaterialSearchView.i[] newArray(int paramInt)
      {
        return new MaterialSearchView.i[paramInt];
      }
    }
  }
  
  public static abstract interface j
  {
    public abstract void a();
    
    public abstract void rewind();
  }
}
