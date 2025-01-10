package com.aurelhubert.ahbottomnavigation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.f;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aurelhubert.ahbottomnavigation.parsers.Board;
import com.aurelhubert.ahbottomnavigation.parsers.Tag;
import java.util.ArrayList;
import java.util.List;

public class AHBottomNavigation
  extends FrameLayout
{
  private static String e = "AHBottomNavigation";
  private Context a;
  private float active;
  private boolean b;
  private int baseColor;
  private float buf;
  private int c;
  private int color;
  private long context;
  private f d;
  private List<com.aurelhubert.ahbottomnavigation.i.a> data = Tag.getValue(5);
  private int errorColor;
  private float g;
  private ArrayList<View> h = new ArrayList();
  private int height;
  private boolean i = false;
  private Typeface iconBitmap;
  private boolean id;
  private int index;
  private int infoColor;
  private int items;
  private Resources j;
  private boolean k;
  private boolean l;
  private int length;
  private int m;
  private Drawable mIcon;
  private View mView;
  private g n;
  private AHBottomNavigationBehavior<AHBottomNavigation> o;
  private h out;
  private boolean p;
  private Typeface points;
  private int primaryColor;
  private int q;
  private int r;
  private ArrayList<a> s = new ArrayList();
  private int size;
  private float state;
  private LinearLayout table;
  private Boolean[] text;
  private Animator this$0;
  private int title;
  private int type;
  private int uri;
  private boolean value;
  private boolean w = false;
  private int x;
  private int y;
  private boolean z;
  
  public AHBottomNavigation(Context paramContext)
  {
    super(paramContext);
    Boolean localBoolean = Boolean.valueOf(true);
    text = new Boolean[] { localBoolean, localBoolean, localBoolean, localBoolean, localBoolean };
    p = false;
    size = 0;
    m = 0;
    k = true;
    l = false;
    z = false;
    value = true;
    length = -1;
    items = 0;
    r = 0;
    id = false;
    out = h.leaf;
    init(paramContext, null);
  }
  
  public AHBottomNavigation(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Boolean localBoolean = Boolean.valueOf(true);
    text = new Boolean[] { localBoolean, localBoolean, localBoolean, localBoolean, localBoolean };
    p = false;
    size = 0;
    m = 0;
    k = true;
    l = false;
    z = false;
    value = true;
    length = -1;
    items = 0;
    r = 0;
    id = false;
    out = h.leaf;
    init(paramContext, paramAttributeSet);
  }
  
  public AHBottomNavigation(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Boolean localBoolean = Boolean.valueOf(true);
    text = new Boolean[] { localBoolean, localBoolean, localBoolean, localBoolean, localBoolean };
    p = false;
    size = 0;
    m = 0;
    k = true;
    l = false;
    z = false;
    value = true;
    length = -1;
    items = 0;
    r = 0;
    id = false;
    out = h.leaf;
    init(paramContext, paramAttributeSet);
  }
  
  private int a(int paramInt)
  {
    if (!b) {
      return paramInt;
    }
    int i1 = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
    if (i1 > 0) {
      r = j.getDimensionPixelSize(i1);
    }
    TypedArray localTypedArray = getContext().getTheme().obtainStyledAttributes(new int[] { 16842973, 16843760 });
    localTypedArray.getBoolean(0, false);
    boolean bool = localTypedArray.getBoolean(1, true);
    i1 = paramInt;
    if (evaluate())
    {
      i1 = paramInt;
      if (bool) {
        i1 = paramInt + r;
      }
    }
    localTypedArray.recycle();
    return i1;
  }
  
  private void init()
  {
    if (s.size() < 3) {
      Log.w(e, "The items list should have at least 3 items");
    } else if (s.size() > 5) {
      Log.w(e, "The items list should not have more than 5 items");
    }
    int i1 = (int)j.getDimension(R.dimen.bottom_navigation_height);
    removeAllViews();
    h.clear();
    mView = new View(a);
    if (Build.VERSION.SDK_INT >= 21)
    {
      localLayoutParams = new FrameLayout.LayoutParams(-1, a(i1));
      addView(mView, localLayoutParams);
      q = i1;
    }
    table = new LinearLayout(a);
    table.setOrientation(0);
    table.setGravity(17);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, i1);
    addView(table, localLayoutParams);
    if ((out != h.h) && ((s.size() == 3) || (out == h.i))) {
      init(table);
    } else {
      update(table);
    }
    post(new a());
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    a = paramContext;
    j = a.getResources();
    primaryColor = ContextCompat.getColor(paramContext, R.color.colorBottomNavigationAccent);
    errorColor = ContextCompat.getColor(paramContext, R.color.colorBottomNavigationInactive);
    color = ContextCompat.getColor(paramContext, R.color.colorBottomNavigationDisable);
    infoColor = ContextCompat.getColor(paramContext, R.color.colorBottomNavigationActiveColored);
    baseColor = ContextCompat.getColor(paramContext, R.color.colorBottomNavigationInactiveColored);
    if (paramAttributeSet != null)
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AHBottomNavigationBehavior_Params, 0, 0);
      try
      {
        i = paramAttributeSet.getBoolean(R.styleable.AHBottomNavigationBehavior_Params_selectedBackgroundVisible, false);
        b = paramAttributeSet.getBoolean(R.styleable.AHBottomNavigationBehavior_Params_translucentNavigationEnabled, false);
        primaryColor = paramAttributeSet.getColor(R.styleable.AHBottomNavigationBehavior_Params_accentColor, ContextCompat.getColor(paramContext, R.color.colorBottomNavigationAccent));
        errorColor = paramAttributeSet.getColor(R.styleable.AHBottomNavigationBehavior_Params_inactiveColor, ContextCompat.getColor(paramContext, R.color.colorBottomNavigationInactive));
        color = paramAttributeSet.getColor(R.styleable.AHBottomNavigationBehavior_Params_disableColor, ContextCompat.getColor(paramContext, R.color.colorBottomNavigationDisable));
        infoColor = paramAttributeSet.getColor(R.styleable.AHBottomNavigationBehavior_Params_coloredActive, ContextCompat.getColor(paramContext, R.color.colorBottomNavigationActiveColored));
        baseColor = paramAttributeSet.getColor(R.styleable.AHBottomNavigationBehavior_Params_coloredInactive, ContextCompat.getColor(paramContext, R.color.colorBottomNavigationInactiveColored));
        w = paramAttributeSet.getBoolean(R.styleable.AHBottomNavigationBehavior_Params_colored, false);
        paramAttributeSet.recycle();
      }
      catch (Throwable paramContext)
      {
        paramAttributeSet.recycle();
        throw paramContext;
      }
    }
    uri = ContextCompat.getColor(paramContext, 17170443);
    q = ((int)j.getDimension(R.dimen.bottom_navigation_height));
    x = primaryColor;
    c = errorColor;
    y = ((int)j.getDimension(R.dimen.bottom_navigation_notification_margin_left_active));
    height = ((int)j.getDimension(R.dimen.bottom_navigation_notification_margin_left));
    index = ((int)j.getDimension(R.dimen.bottom_navigation_notification_margin_top_active));
    type = ((int)j.getDimension(R.dimen.bottom_navigation_notification_margin_top));
    context = 150L;
    ViewCompat.setElevation(this, j.getDimension(R.dimen.bottom_navigation_elevation));
    setClipToPadding(false);
    setLayoutParams(new ViewGroup.LayoutParams(-1, q));
  }
  
  private void init(LinearLayout paramLinearLayout)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)a.getSystemService("layout_inflater");
    float f6 = j.getDimension(R.dimen.bottom_navigation_height);
    float f3 = j.getDimension(R.dimen.bottom_navigation_min_width);
    float f4 = j.getDimension(R.dimen.bottom_navigation_max_width);
    float f2 = f3;
    float f1 = f4;
    if (out == h.i)
    {
      f2 = f3;
      f1 = f4;
      if (s.size() > 3)
      {
        f2 = j.getDimension(R.dimen.bottom_navigation_small_inactive_min_width);
        f1 = j.getDimension(R.dimen.bottom_navigation_small_inactive_max_width);
      }
    }
    final int i1 = getWidth();
    if (i1 != 0)
    {
      if (s.size() == 0) {
        return;
      }
      f4 = i1 / s.size();
      if (f4 < f2)
      {
        f3 = f2;
      }
      else
      {
        f3 = f4;
        if (f4 > f1) {
          f3 = f1;
        }
      }
      f4 = j.getDimension(R.dimen.bottom_navigation_text_size_active);
      float f5 = j.getDimension(R.dimen.bottom_navigation_text_size_inactive);
      int i4 = (int)j.getDimension(R.dimen.bottom_navigation_margin_top_active);
      if ((active != 0.0F) && (state != 0.0F))
      {
        f1 = active;
        f2 = state;
      }
      else
      {
        f1 = f4;
        f2 = f5;
        if (out == h.i)
        {
          f1 = f4;
          f2 = f5;
          if (s.size() > 3)
          {
            f1 = j.getDimension(R.dimen.bottom_navigation_text_size_forced_active);
            f2 = j.getDimension(R.dimen.bottom_navigation_text_size_forced_inactive);
          }
        }
      }
      i1 = 0;
      while (i1 < s.size())
      {
        int i2;
        if (size == i1) {
          i2 = 1;
        } else {
          i2 = 0;
        }
        Object localObject1 = (Label)s.get(i1);
        View localView = localLayoutInflater.inflate(R.layout.bottom_navigation_item, this, false);
        Object localObject3 = (FrameLayout)localView.findViewById(R.id.bottom_navigation_container);
        ImageView localImageView = (ImageView)localView.findViewById(R.id.bottom_navigation_item_icon);
        TextView localTextView = (TextView)localView.findViewById(R.id.bottom_navigation_item_title);
        Object localObject2 = (TextView)localView.findViewById(R.id.bottom_navigation_notification);
        localImageView.setImageDrawable(((Label)localObject1).getIcon(a));
        localTextView.setText(((Label)localObject1).getValue(a));
        Typeface localTypeface = points;
        if (localTypeface != null) {
          localTextView.setTypeface(localTypeface);
        }
        if ((out == h.i) && (s.size() > 3)) {
          ((View)localObject3).setPadding(0, ((View)localObject3).getPaddingTop(), 0, ((View)localObject3).getPaddingBottom());
        }
        if (i2 != 0)
        {
          if (i) {
            localView.setSelected(true);
          }
          localImageView.setSelected(true);
          if ((localView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
          {
            localObject3 = (ViewGroup.MarginLayoutParams)localImageView.getLayoutParams();
            ((ViewGroup.MarginLayoutParams)localObject3).setMargins(leftMargin, i4, rightMargin, bottomMargin);
            localObject2 = (ViewGroup.MarginLayoutParams)((View)localObject2).getLayoutParams();
            ((ViewGroup.MarginLayoutParams)localObject2).setMargins(y, topMargin, rightMargin, bottomMargin);
            localView.requestLayout();
          }
        }
        else
        {
          localImageView.setSelected(false);
          localObject2 = (ViewGroup.MarginLayoutParams)((View)localObject2).getLayoutParams();
          ((ViewGroup.MarginLayoutParams)localObject2).setMargins(height, topMargin, rightMargin, bottomMargin);
        }
        int i3;
        if (w)
        {
          if (i2 != 0)
          {
            setBackgroundColor(((Label)localObject1).get(a));
            m = ((Label)localObject1).get(a);
          }
        }
        else
        {
          i3 = items;
          if (i3 != 0) {
            setBackgroundResource(i3);
          } else {
            setBackgroundColor(length);
          }
        }
        if (i2 != 0) {
          f4 = f1;
        } else {
          f4 = f2;
        }
        localTextView.setTextSize(0, f4);
        if (text[i1].booleanValue())
        {
          localView.setOnClickListener(new b(i1));
          localObject1 = ((Label)s.get(i1)).getIcon(a);
          if (i2 != 0) {
            i3 = x;
          } else {
            i3 = c;
          }
          localImageView.setImageDrawable(ThemeManager.get((Drawable)localObject1, i3, id));
          if (i2 != 0) {
            i2 = x;
          } else {
            i2 = c;
          }
          localTextView.setTextColor(i2);
          localView.setSoundEffectsEnabled(value);
        }
        else
        {
          localImageView.setImageDrawable(ThemeManager.get(((Label)s.get(i1)).getIcon(a), color, id));
          localTextView.setTextColor(color);
        }
        paramLinearLayout.addView(localView, new FrameLayout.LayoutParams((int)f3, (int)f6));
        h.add(localView);
        i1 += 1;
      }
      onBindViewHolder(true, -1);
    }
  }
  
  private void onBindViewHolder(boolean paramBoolean, int paramInt)
  {
    int i1 = 0;
    while (i1 < h.size())
    {
      if (i1 >= data.size()) {
        return;
      }
      if ((paramInt == -1) || (paramInt == i1))
      {
        Tag localTag = (Tag)data.get(i1);
        int i2 = Board.get(localTag, uri);
        int i3 = Board.next(localTag, title);
        TextView localTextView = (TextView)((View)h.get(i1)).findViewById(R.id.bottom_navigation_notification);
        boolean bool = localTextView.getText().toString().equals(String.valueOf(localTag.getColor())) ^ true;
        if (paramBoolean)
        {
          localTextView.setTextColor(i2);
          Object localObject = iconBitmap;
          if (localObject != null) {
            localTextView.setTypeface((Typeface)localObject);
          } else {
            localTextView.setTypeface(null, 1);
          }
          localObject = mIcon;
          if (localObject != null)
          {
            if (Build.VERSION.SDK_INT >= 16) {
              localTextView.setBackground(((Drawable)localObject).getConstantState().newDrawable());
            } else {
              localTextView.setBackgroundDrawable((Drawable)localObject);
            }
          }
          else if (i3 != 0)
          {
            localObject = ContextCompat.getDrawable(a, R.drawable.notification_background);
            if (Build.VERSION.SDK_INT >= 16) {
              localTextView.setBackground(ThemeManager.get((Drawable)localObject, i3, id));
            } else {
              localTextView.setBackgroundDrawable(ThemeManager.get((Drawable)localObject, i3, id));
            }
          }
        }
        if ((localTag.isEmpty()) && (localTextView.getText().length() > 0))
        {
          localTextView.setText("");
          if (bool) {
            localTextView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(new AccelerateInterpolator()).setDuration(context).start();
          }
        }
        else if (!localTag.isEmpty())
        {
          localTextView.setText(String.valueOf(localTag.getColor()));
          if (bool)
          {
            localTextView.setScaleX(0.0F);
            localTextView.setScaleY(0.0F);
            localTextView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setInterpolator(new OvershootInterpolator()).setDuration(context).start();
          }
        }
      }
      i1 += 1;
    }
  }
  
  private void run(final int paramInt, boolean paramBoolean)
  {
    Object localObject1;
    if (size == paramInt)
    {
      localObject1 = n;
      if ((localObject1 != null) && (paramBoolean)) {
        ((g)localObject1).a(paramInt, true);
      }
    }
    else
    {
      localObject1 = n;
      if ((localObject1 != null) && (paramBoolean) && (!((g)localObject1).a(paramInt, false))) {
        return;
      }
      int i2 = (int)j.getDimension(R.dimen.bottom_navigation_small_margin_top_active);
      int i3 = (int)j.getDimension(R.dimen.bottom_navigation_small_margin_top);
      int i1 = 0;
      while (i1 < h.size())
      {
        localObject1 = (View)h.get(i1);
        if (i)
        {
          if (i1 == paramInt) {
            paramBoolean = true;
          } else {
            paramBoolean = false;
          }
          ((View)localObject1).setSelected(paramBoolean);
        }
        Object localObject2;
        TextView localTextView;
        ImageView localImageView;
        if (i1 == paramInt)
        {
          localObject2 = (FrameLayout)((View)localObject1).findViewById(R.id.bottom_navigation_small_container);
          localTextView = (TextView)((View)localObject1).findViewById(R.id.bottom_navigation_small_item_title);
          localImageView = (ImageView)((View)localObject1).findViewById(R.id.bottom_navigation_small_item_icon);
          localObject1 = (TextView)((View)localObject1).findViewById(R.id.bottom_navigation_notification);
          localImageView.setSelected(true);
          if (out != h.h)
          {
            ThemeManager.access$100(localImageView, i3, i2);
            ThemeManager.init((View)localObject1, height, y);
            ThemeManager.access$100((View)localObject1, type, index);
            ThemeManager.setColor(localTextView, c, x);
            ThemeManager.setColor((View)localObject2, g, buf);
          }
          ThemeManager.zoomTo(localTextView, 0.0F, 1.0F);
          ThemeManager.setColor(a, ((Label)s.get(paramInt)).getIcon(a), localImageView, c, x, id);
          int i4;
          if ((Build.VERSION.SDK_INT >= 21) && (w))
          {
            i4 = Math.max(getWidth(), getHeight());
            int i5 = (int)((View)h.get(paramInt)).getX();
            int i6 = ((View)h.get(paramInt)).getWidth() / 2;
            int i7 = ((View)h.get(paramInt)).getHeight() / 2;
            localObject1 = this$0;
            if ((localObject1 != null) && (((Animator)localObject1).isRunning()))
            {
              this$0.cancel();
              setBackgroundColor(((Label)s.get(paramInt)).get(a));
              mView.setBackgroundColor(0);
            }
            this$0 = ViewAnimationUtils.createCircularReveal(mView, i5 + i6, i7, 0.0F, i4);
            this$0.setStartDelay(5L);
            this$0.addListener(new e(paramInt));
            this$0.start();
          }
          else if (w)
          {
            ThemeManager.setColor(this, m, ((Label)s.get(paramInt)).get(a));
          }
          else
          {
            i4 = items;
            if (i4 != 0) {
              setBackgroundResource(i4);
            } else {
              setBackgroundColor(length);
            }
            mView.setBackgroundColor(0);
          }
        }
        else if (i1 == size)
        {
          localObject2 = ((View)localObject1).findViewById(R.id.bottom_navigation_small_container);
          localTextView = (TextView)((View)localObject1).findViewById(R.id.bottom_navigation_small_item_title);
          localImageView = (ImageView)((View)localObject1).findViewById(R.id.bottom_navigation_small_item_icon);
          localObject1 = (TextView)((View)localObject1).findViewById(R.id.bottom_navigation_notification);
          localImageView.setSelected(false);
          if (out != h.h)
          {
            ThemeManager.access$100(localImageView, i2, i3);
            ThemeManager.init((View)localObject1, y, height);
            ThemeManager.access$100((View)localObject1, index, type);
            ThemeManager.setColor(localTextView, x, c);
            ThemeManager.setColor((View)localObject2, buf, g);
          }
          ThemeManager.zoomTo(localTextView, 1.0F, 0.0F);
          ThemeManager.setColor(a, ((Label)s.get(size)).getIcon(a), localImageView, x, c, id);
        }
        i1 += 1;
      }
      size = paramInt;
      paramInt = size;
      if ((paramInt > 0) && (paramInt < s.size()))
      {
        m = ((Label)s.get(size)).get(a);
        return;
      }
      if (size == -1)
      {
        paramInt = items;
        if (paramInt != 0) {
          setBackgroundResource(paramInt);
        } else {
          setBackgroundColor(length);
        }
        mView.setBackgroundColor(0);
      }
    }
  }
  
  private void update(final int paramInt, boolean paramBoolean)
  {
    Object localObject;
    if (size == paramInt)
    {
      localObject = n;
      if ((localObject != null) && (paramBoolean)) {
        ((g)localObject).a(paramInt, true);
      }
    }
    else
    {
      localObject = n;
      if ((localObject != null) && (paramBoolean) && (!((g)localObject).a(paramInt, false))) {
        return;
      }
      int i2 = (int)j.getDimension(R.dimen.bottom_navigation_margin_top_active);
      int i3 = (int)j.getDimension(R.dimen.bottom_navigation_margin_top_inactive);
      float f3 = j.getDimension(R.dimen.bottom_navigation_text_size_active);
      float f4 = j.getDimension(R.dimen.bottom_navigation_text_size_inactive);
      float f1;
      float f2;
      if ((active != 0.0F) && (state != 0.0F))
      {
        f1 = active;
        f2 = state;
      }
      else
      {
        f1 = f3;
        f2 = f4;
        if (out == h.i)
        {
          f1 = f3;
          f2 = f4;
          if (s.size() > 3)
          {
            f1 = j.getDimension(R.dimen.bottom_navigation_text_size_forced_active);
            f2 = j.getDimension(R.dimen.bottom_navigation_text_size_forced_inactive);
          }
        }
      }
      int i1 = 0;
      while (i1 < h.size())
      {
        localObject = (View)h.get(i1);
        if (i)
        {
          if (i1 == paramInt) {
            paramBoolean = true;
          } else {
            paramBoolean = false;
          }
          ((View)localObject).setSelected(paramBoolean);
        }
        TextView localTextView1;
        ImageView localImageView;
        if (i1 == paramInt)
        {
          localTextView1 = (TextView)((View)localObject).findViewById(R.id.bottom_navigation_item_title);
          localImageView = (ImageView)((View)localObject).findViewById(R.id.bottom_navigation_item_icon);
          TextView localTextView2 = (TextView)((View)localObject).findViewById(R.id.bottom_navigation_notification);
          localImageView.setSelected(true);
          ThemeManager.access$100(localImageView, i3, i2);
          ThemeManager.init(localTextView2, height, y);
          ThemeManager.setColor(localTextView1, c, x);
          ThemeManager.start(localTextView1, f2, f1);
          ThemeManager.setColor(a, ((Label)s.get(paramInt)).getIcon(a), localImageView, c, x, id);
          int i4;
          if ((Build.VERSION.SDK_INT >= 21) && (w))
          {
            i4 = Math.max(getWidth(), getHeight());
            int i5 = (int)((View)localObject).getX();
            int i6 = ((View)localObject).getWidth() / 2;
            int i7 = ((View)localObject).getHeight() / 2;
            localObject = this$0;
            if ((localObject != null) && (((Animator)localObject).isRunning()))
            {
              this$0.cancel();
              setBackgroundColor(((Label)s.get(paramInt)).get(a));
              mView.setBackgroundColor(0);
            }
            this$0 = ViewAnimationUtils.createCircularReveal(mView, i5 + i6, i7, 0.0F, i4);
            this$0.setStartDelay(5L);
            this$0.addListener(new d(paramInt));
            this$0.start();
          }
          else if (w)
          {
            ThemeManager.setColor(this, m, ((Label)s.get(paramInt)).get(a));
          }
          else
          {
            i4 = items;
            if (i4 != 0) {
              setBackgroundResource(i4);
            } else {
              setBackgroundColor(length);
            }
            mView.setBackgroundColor(0);
          }
        }
        else if (i1 == size)
        {
          localTextView1 = (TextView)((View)localObject).findViewById(R.id.bottom_navigation_item_title);
          localImageView = (ImageView)((View)localObject).findViewById(R.id.bottom_navigation_item_icon);
          localObject = (TextView)((View)localObject).findViewById(R.id.bottom_navigation_notification);
          localImageView.setSelected(false);
          ThemeManager.access$100(localImageView, i2, i3);
          ThemeManager.init((View)localObject, y, height);
          ThemeManager.setColor(localTextView1, x, c);
          ThemeManager.start(localTextView1, f1, f2);
          ThemeManager.setColor(a, ((Label)s.get(size)).getIcon(a), localImageView, x, c, id);
        }
        i1 += 1;
      }
      size = paramInt;
      paramInt = size;
      if ((paramInt > 0) && (paramInt < s.size()))
      {
        m = ((Label)s.get(size)).get(a);
        return;
      }
      if (size == -1)
      {
        paramInt = items;
        if (paramInt != 0) {
          setBackgroundResource(paramInt);
        } else {
          setBackgroundColor(length);
        }
        mView.setBackgroundColor(0);
      }
    }
  }
  
  private void update(LinearLayout paramLinearLayout)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)a.getSystemService("layout_inflater");
    float f4 = j.getDimension(R.dimen.bottom_navigation_height);
    float f1 = j.getDimension(R.dimen.bottom_navigation_small_inactive_min_width);
    float f2 = j.getDimension(R.dimen.bottom_navigation_small_inactive_max_width);
    int i1 = getWidth();
    if (i1 != 0)
    {
      if (s.size() == 0) {
        return;
      }
      float f3 = i1 / s.size();
      if (f3 >= f1)
      {
        f1 = f3;
        if (f3 > f2) {
          f1 = f2;
        }
      }
      int i3 = (int)j.getDimension(R.dimen.bottom_navigation_small_margin_top_active);
      f2 = j.getDimension(R.dimen.bottom_navigation_small_selected_width_difference);
      buf = (s.size() * f2 + f1);
      f2 = f1 - f2;
      g = f2;
      final int i2 = 0;
      while (i2 < s.size())
      {
        Object localObject1 = (Label)s.get(i2);
        View localView = localLayoutInflater.inflate(R.layout.bottom_navigation_small_item, this, false);
        ImageView localImageView = (ImageView)localView.findViewById(R.id.bottom_navigation_small_item_icon);
        TextView localTextView = (TextView)localView.findViewById(R.id.bottom_navigation_small_item_title);
        Object localObject2 = (TextView)localView.findViewById(R.id.bottom_navigation_notification);
        localImageView.setImageDrawable(((Label)localObject1).getIcon(a));
        if (out != h.h) {
          localTextView.setText(((Label)localObject1).getValue(a));
        }
        f1 = active;
        if (f1 != 0.0F) {
          localTextView.setTextSize(0, f1);
        }
        Object localObject3 = points;
        if (localObject3 != null) {
          localTextView.setTypeface((Typeface)localObject3);
        }
        if (i2 == size)
        {
          if (i) {
            localView.setSelected(true);
          }
          localImageView.setSelected(true);
          if (out != h.h) {
            if ((localView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            {
              localObject3 = (ViewGroup.MarginLayoutParams)localImageView.getLayoutParams();
              ((ViewGroup.MarginLayoutParams)localObject3).setMargins(leftMargin, i3, rightMargin, bottomMargin);
              localObject2 = (ViewGroup.MarginLayoutParams)((View)localObject2).getLayoutParams();
              ((ViewGroup.MarginLayoutParams)localObject2).setMargins(y, index, rightMargin, bottomMargin);
              localView.requestLayout();
              break label496;
            }
          }
        }
        else
        {
          localImageView.setSelected(false);
          localObject2 = (ViewGroup.MarginLayoutParams)((View)localObject2).getLayoutParams();
          ((ViewGroup.MarginLayoutParams)localObject2).setMargins(height, type, rightMargin, bottomMargin);
        }
        label496:
        if (w)
        {
          if (i2 == size)
          {
            setBackgroundColor(((Label)localObject1).get(a));
            m = ((Label)localObject1).get(a);
          }
        }
        else
        {
          i1 = items;
          if (i1 != 0) {
            setBackgroundResource(i1);
          } else {
            setBackgroundColor(length);
          }
        }
        if (text[i2].booleanValue())
        {
          localObject1 = ((Label)s.get(i2)).getIcon(a);
          if (size == i2) {
            i1 = x;
          } else {
            i1 = c;
          }
          localImageView.setImageDrawable(ThemeManager.get((Drawable)localObject1, i1, id));
          if (size == i2) {
            i1 = x;
          } else {
            i1 = c;
          }
          localTextView.setTextColor(i1);
          if (size == i2) {
            f1 = 1.0F;
          } else {
            f1 = 0.0F;
          }
          localTextView.setAlpha(f1);
          localView.setOnClickListener(new c(i2));
          localView.setSoundEffectsEnabled(value);
        }
        else
        {
          localImageView.setImageDrawable(ThemeManager.get(((Label)s.get(i2)).getIcon(a), color, id));
          localTextView.setTextColor(color);
          localTextView.setAlpha(0.0F);
        }
        if (i2 == size) {
          i1 = (int)buf;
        } else {
          i1 = (int)f2;
        }
        if (out == h.h)
        {
          double d1 = f2;
          Double.isNaN(d1);
          i1 = (int)(d1 * 1.16D);
        }
        paramLinearLayout.addView(localView, new FrameLayout.LayoutParams(i1, (int)f4));
        h.add(localView);
        i2 += 1;
      }
      onBindViewHolder(true, -1);
    }
  }
  
  public void a(Label paramLabel)
  {
    if (s.size() > 5) {
      Log.w(e, "The items list should not have more than 5 items");
    }
    s.add(paramLabel);
    init();
  }
  
  public void close(float paramFloat1, float paramFloat2)
  {
    active = paramFloat1;
    state = paramFloat2;
    init();
  }
  
  public boolean evaluate()
  {
    Display localDisplay = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getRealMetrics(localDisplayMetrics);
    int i1 = heightPixels;
    int i2 = widthPixels;
    localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    int i3 = heightPixels;
    return (i2 > widthPixels) || (i1 > i3);
  }
  
  public int getAccentColor()
  {
    return x;
  }
  
  public int getCurrentItem()
  {
    return size;
  }
  
  public int getDefaultBackgroundColor()
  {
    return length;
  }
  
  public int getInactiveColor()
  {
    return c;
  }
  
  public int getItemsCount()
  {
    return s.size();
  }
  
  public h getTitleState()
  {
    return out;
  }
  
  public void next(int paramInt, boolean paramBoolean)
  {
    if (paramInt >= s.size())
    {
      String str = e;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("The position is out of bounds of the items (");
      localStringBuilder.append(s.size());
      localStringBuilder.append(" elements)");
      Log.w(str, localStringBuilder.toString());
      return;
    }
    if ((out != h.h) && ((s.size() == 3) || (out == h.i)))
    {
      update(paramInt, paramBoolean);
      return;
    }
    run(paramInt, paramBoolean);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!p)
    {
      setBehaviorTranslationEnabled(k);
      p = true;
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    Parcelable localParcelable = paramParcelable;
    if ((paramParcelable instanceof Bundle))
    {
      paramParcelable = (Bundle)paramParcelable;
      size = paramParcelable.getInt("current_item");
      data = paramParcelable.getParcelableArrayList("notifications");
      localParcelable = paramParcelable.getParcelable("superState");
    }
    super.onRestoreInstanceState(localParcelable);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("superState", super.onSaveInstanceState());
    localBundle.putInt("current_item", size);
    localBundle.putParcelableArrayList("notifications", new ArrayList(data));
    return localBundle;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    init();
  }
  
  public void setAccentColor(int paramInt)
  {
    primaryColor = paramInt;
    x = paramInt;
    init();
  }
  
  public void setBehaviorTranslationEnabled(boolean paramBoolean)
  {
    k = paramBoolean;
    if ((getParent() instanceof CoordinatorLayout))
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      Object localObject = o;
      if (localObject == null) {
        o = new AHBottomNavigationBehavior(paramBoolean, r);
      } else {
        ((AHBottomNavigationBehavior)localObject).a(paramBoolean, r);
      }
      localObject = d;
      if (localObject != null) {
        o.b((f)localObject);
      }
      ((CoordinatorLayout.f)localLayoutParams).a(o);
      if (l)
      {
        l = false;
        o.a(this, q, z);
      }
    }
  }
  
  public void setColored(boolean paramBoolean)
  {
    w = paramBoolean;
    int i1;
    if (paramBoolean) {
      i1 = infoColor;
    } else {
      i1 = primaryColor;
    }
    x = i1;
    if (paramBoolean) {
      i1 = baseColor;
    } else {
      i1 = errorColor;
    }
    c = i1;
    init();
  }
  
  public void setCurrentItem(int paramInt)
  {
    next(paramInt, true);
  }
  
  public void setDefaultBackgroundColor(int paramInt)
  {
    length = paramInt;
    init();
  }
  
  public void setDefaultBackgroundResource(int paramInt)
  {
    items = paramInt;
    init();
  }
  
  public void setForceTint(boolean paramBoolean)
  {
    id = paramBoolean;
    init();
  }
  
  public void setInactiveColor(int paramInt)
  {
    errorColor = paramInt;
    c = paramInt;
    init();
  }
  
  public void setItemDisableColor(int paramInt)
  {
    color = paramInt;
  }
  
  public void setNotificationAnimationDuration(long paramLong)
  {
    context = paramLong;
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationBackground(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationBackgroundColor(int paramInt)
  {
    title = paramInt;
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationBackgroundColorResource(int paramInt)
  {
    title = ContextCompat.getColor(a, paramInt);
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationTextColor(int paramInt)
  {
    uri = paramInt;
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationTextColorResource(int paramInt)
  {
    uri = ContextCompat.getColor(a, paramInt);
    onBindViewHolder(true, -1);
  }
  
  public void setNotificationTypeface(Typeface paramTypeface)
  {
    iconBitmap = paramTypeface;
    onBindViewHolder(true, -1);
  }
  
  public void setOnNavigationPositionListener(f paramF)
  {
    d = paramF;
    AHBottomNavigationBehavior localAHBottomNavigationBehavior = o;
    if (localAHBottomNavigationBehavior != null) {
      localAHBottomNavigationBehavior.b(paramF);
    }
  }
  
  public void setOnTabSelectedListener(g paramG)
  {
    n = paramG;
  }
  
  public void setSelectedBackgroundVisible(boolean paramBoolean)
  {
    i = paramBoolean;
    init();
  }
  
  public void setSoundEffectsEnabled(boolean paramBoolean)
  {
    super.setSoundEffectsEnabled(paramBoolean);
    value = paramBoolean;
  }
  
  public void setTitleState(h paramH)
  {
    out = paramH;
    init();
  }
  
  public void setTitleTypeface(Typeface paramTypeface)
  {
    points = paramTypeface;
    init();
  }
  
  public void setTranslucentNavigationEnabled(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void setUseElevation(boolean paramBoolean)
  {
    float f;
    if (paramBoolean) {
      f = j.getDimension(R.dimen.bottom_navigation_elevation);
    } else {
      f = 0.0F;
    }
    ViewCompat.setElevation(this, f);
    setClipToPadding(false);
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      requestLayout();
    }
  }
  
  class b
    implements View.OnClickListener
  {
    b(int paramInt) {}
    
    public void onClick(View paramView)
    {
      AHBottomNavigation.b(AHBottomNavigation.this, i1, true);
    }
  }
  
  class c
    implements View.OnClickListener
  {
    c(int paramInt) {}
    
    public void onClick(View paramView)
    {
      AHBottomNavigation.a(AHBottomNavigation.this, i2, true);
    }
  }
  
  class d
    implements Animator.AnimatorListener
  {
    d(int paramInt) {}
    
    public void onAnimationCancel(Animator paramAnimator) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      paramAnimator = AHBottomNavigation.this;
      paramAnimator.setBackgroundColor(((Label)AHBottomNavigation.access$getS(paramAnimator).get(paramInt)).get(AHBottomNavigation.getInstance(AHBottomNavigation.this)));
      AHBottomNavigation.access$getMView(AHBottomNavigation.this).setBackgroundColor(0);
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator)
    {
      AHBottomNavigation.access$getMView(AHBottomNavigation.this).setBackgroundColor(((Label)AHBottomNavigation.access$getS(AHBottomNavigation.this).get(paramInt)).get(AHBottomNavigation.getInstance(AHBottomNavigation.this)));
    }
  }
  
  class e
    implements Animator.AnimatorListener
  {
    e(int paramInt) {}
    
    public void onAnimationCancel(Animator paramAnimator) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      paramAnimator = AHBottomNavigation.this;
      paramAnimator.setBackgroundColor(((Label)AHBottomNavigation.access$getS(paramAnimator).get(paramInt)).get(AHBottomNavigation.getInstance(AHBottomNavigation.this)));
      AHBottomNavigation.access$getMView(AHBottomNavigation.this).setBackgroundColor(0);
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator)
    {
      AHBottomNavigation.access$getMView(AHBottomNavigation.this).setBackgroundColor(((Label)AHBottomNavigation.access$getS(AHBottomNavigation.this).get(paramInt)).get(AHBottomNavigation.getInstance(AHBottomNavigation.this)));
    }
  }
  
  public static abstract interface f
  {
    public abstract void setHeight(int paramInt);
  }
  
  public static abstract interface g
  {
    public abstract boolean a(int paramInt, boolean paramBoolean);
  }
  
  public static enum h
  {
    static
    {
      i = new h("ALWAYS_SHOW", 1);
      h = new h("ALWAYS_HIDE", 2);
    }
  }
}
