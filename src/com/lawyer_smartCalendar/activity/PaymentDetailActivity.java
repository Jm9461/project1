package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.MaterialDialog.Builder;
import apps.afollestad.materialdialogs.c;
import com.lawyer_smartCalendar.package_2.ByteVector;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PaymentDetailActivity
  extends AppCompatActivity
{
  private TextView a;
  private TextView aboutTitle;
  private ByteVector adapter;
  private TextView appDesc;
  private TextView appName;
  private TextView b;
  private TextView cancel;
  private TextView content;
  private TextView count;
  private TextView d;
  public String data = "";
  private TextView description;
  private TextView downloadText;
  private TextView edit;
  private TextView file;
  private TextView header;
  private TextView headerTextView;
  private TextView host;
  private int id = 300;
  private TextView indicator;
  private TextView language;
  public RecyclerView list;
  private TextView mAuthor;
  private TextView mButton;
  private TextView mDate;
  private TextView mDateView;
  private TextView mDescr;
  private TextView mDescriptionView;
  private TextView mItems;
  private TextView mPrompt;
  private TextView mTime;
  private TextView mTitle;
  private TextView mTitleView;
  private TextView messageView;
  private boolean modified = false;
  private TextView name;
  private TextView next;
  private TextView prefix;
  private List<com.lawyer_smartCalendar.d.h> result = new ArrayList();
  private TextView server;
  private TextView t1;
  private TextView t2;
  private TextView t3;
  private TextView tag;
  private TextView temp;
  private TextView text;
  private TextView textView;
  private com.lawyer_smartCalendar.utils.h this$0;
  private TextView time;
  private TextView timeView;
  private TextView title;
  private TextView titleView;
  private TextView tvDate;
  private TextView tvTime;
  private TextView type;
  private TextView url;
  private TextView username;
  private TextView view;
  private TextView welcomeText;
  
  public PaymentDetailActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == id) && (paramInt2 == -1))
    {
      onCreate();
      modified = true;
    }
  }
  
  public void onBackPressed()
  {
    if (modified) {
      setResult(-1);
    }
    finish();
  }
  
  public void onCreate()
  {
    data = getIntent().getStringExtra("id");
    result = new ArrayList();
    this$0.setEnabled();
    Object localObject3 = this$0.getItem(data);
    Object localObject1 = this$0;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.d)localObject3).b());
    ((StringBuilder)localObject2).append("");
    localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject1).a(((StringBuilder)localObject2).toString());
    localObject1 = this$0;
    Object localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.h)localObject2).c());
    ((StringBuilder)localObject4).append("");
    Object localObject5 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject4).toString());
    localObject1 = this$0;
    localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.d)localObject3).e());
    ((StringBuilder)localObject4).append("");
    result = ((com.lawyer_smartCalendar.utils.h)localObject1).load(((StringBuilder)localObject4).toString());
    localObject1 = this$0;
    localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.h)localObject2).b());
    ((StringBuilder)localObject4).append("");
    localObject4 = ((com.lawyer_smartCalendar.utils.h)localObject1).remove(((StringBuilder)localObject4).toString());
    this$0.close();
    if (result.size() > 0) {
      next.setVisibility(0);
    }
    adapter = new ByteVector(this, result);
    localObject1 = new GridLayoutManager(this, 1, 1, false);
    list.setLayoutManager((RecyclerView.o)localObject1);
    list.setAdapter(adapter);
    localObject1 = "";
    Object localObject6 = ((com.lawyer_smartCalendar.data.d)localObject3).getString();
    switch (((String)localObject6).hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject6).equals("installment"))
      {
        i = 3;
        break label416;
        if (((String)localObject6).equals("bankReceipt"))
        {
          i = 1;
          break label416;
          if (((String)localObject6).equals("cardToCart"))
          {
            i = 2;
            break label416;
            if (((String)localObject6).equals("cash"))
            {
              i = 0;
              break label416;
            }
          }
        }
      }
    }
    int i = -1;
    label416:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3) {
            localObject1 = "??????";
          }
        }
        else {
          localObject1 = "???? ?? ????";
        }
      }
      else {
        localObject1 = "??? ?????";
      }
    }
    else {
      localObject1 = "????";
    }
    tvDate.setText((CharSequence)localObject1);
    StringBuilder localStringBuilder;
    if (!((com.lawyer_smartCalendar.data.d)localObject3).getId().equals(""))
    {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.d)localObject3).getId())));
      localObject6 = Calendar.getInstance();
      ((Calendar)localObject6).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.d)localObject3).getId()));
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" ???? ");
      localStringBuilder.append(((Calendar)localObject6).get(11));
      localStringBuilder.append(":");
      localStringBuilder.append(((Calendar)localObject6).get(12));
      localObject1 = localStringBuilder.toString();
      tvTime.setText((CharSequence)localObject1);
    }
    else
    {
      tvTime.setText("-");
    }
    if (!((com.lawyer_smartCalendar.data.d)localObject3).getValue().equals(""))
    {
      localObject1 = new DecimalFormat("#,###,###,###,###,###,###");
      localObject6 = b;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(((DecimalFormat)localObject1).format(Long.parseLong(((com.lawyer_smartCalendar.data.d)localObject3).getValue())));
      localStringBuilder.append(" ????? ");
      ((TextView)localObject6).setText(localStringBuilder.toString());
    }
    else
    {
      b.setText("-");
    }
    if (!((com.lawyer_smartCalendar.data.d)localObject3).getTitle().equals("")) {
      a.setText(((com.lawyer_smartCalendar.data.d)localObject3).getTitle());
    } else {
      a.setText("-");
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getValue().equals("")) {
      d.setText(((com.lawyer_smartCalendar.data.f)localObject5).getValue());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).d().equals("")) {
      view.setText(((com.lawyer_smartCalendar.data.f)localObject5).d());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).get().equals("")) {
      time.setText(((com.lawyer_smartCalendar.data.f)localObject5).get());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getText().equals("")) {
      content.setText(((com.lawyer_smartCalendar.data.f)localObject5).getText());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).a().equals("")) {
      t1.setText(((com.lawyer_smartCalendar.data.f)localObject5).a());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).e().equals("")) {
      t2.setText(((com.lawyer_smartCalendar.data.f)localObject5).e());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getTitle().equals("")) {
      t3.setText(((com.lawyer_smartCalendar.data.f)localObject5).getTitle());
    }
    if (((com.lawyer_smartCalendar.data.f)localObject5).f().equals("legal"))
    {
      name.setVisibility(8);
      indicator.setVisibility(8);
      titleView.setVisibility(8);
      view.setVisibility(8);
      time.setVisibility(8);
      content.setVisibility(8);
      ((ViewGroup)((ViewGroup)view.getParent()).getParent()).removeView((ViewGroup)view.getParent());
      ((ViewGroup)((ViewGroup)time.getParent()).getParent()).removeView((ViewGroup)time.getParent());
      ((ViewGroup)((ViewGroup)content.getParent()).getParent()).removeView((ViewGroup)content.getParent());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getString().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).getString();
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject1).equals("Executive"))
        {
          i = 1;
          break label1188;
          if (((String)localObject1).equals("Bank"))
          {
            i = 3;
            break label1188;
            if (((String)localObject1).equals("Challenge"))
            {
              i = 2;
              break label1188;
              if (((String)localObject1).equals("Normal"))
              {
                i = 0;
                break label1188;
              }
            }
          }
        }
      }
      i = -1;
      label1188:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i == 3) {
              headerTextView.setText("?????");
            }
          }
          else {
            headerTextView.setText("???????/??????");
          }
        }
        else {
          headerTextView.setText("??????");
        }
      }
      else {
        headerTextView.setText("????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getIcon().equals(""))
    {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon())));
      localObject3 = Calendar.getInstance();
      ((Calendar)localObject3).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()));
      localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append((String)localObject1);
      ((StringBuilder)localObject5).append(" ???? ");
      ((StringBuilder)localObject5).append(((Calendar)localObject3).get(11));
      ((StringBuilder)localObject5).append(":");
      ((StringBuilder)localObject5).append(((Calendar)localObject3).get(12));
      localObject1 = ((StringBuilder)localObject5).toString();
      downloadText.setText((CharSequence)localObject1);
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getTitle().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).getTitle();
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject1).equals("Accused"))
        {
          i = 3;
          break label1554;
          if (((String)localObject1).equals("Reconsidered"))
          {
            i = 5;
            break label1554;
            if (((String)localObject1).equals("Read"))
            {
              i = 1;
              break label1554;
              if (((String)localObject1).equals("Applicant"))
              {
                i = 0;
                break label1554;
                if (((String)localObject1).equals("Plaintiff"))
                {
                  i = 2;
                  break label1554;
                  if (((String)localObject1).equals("Revisionist"))
                  {
                    i = 4;
                    break label1554;
                  }
                }
              }
            }
          }
        }
      }
      i = -1;
      label1554:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3)
            {
              if (i != 4)
              {
                if (i == 5) {
                  welcomeText.setText("????? ??????");
                }
              }
              else {
                welcomeText.setText("????? ??? ????");
              }
            }
            else {
              welcomeText.setText("????");
            }
          }
          else {
            welcomeText.setText("????");
          }
        }
        else {
          welcomeText.setText("??????");
        }
      }
      else {
        welcomeText.setText("??????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getCount().equals("")) {
      appName.setText(((com.lawyer_smartCalendar.data.h)localObject2).getCount());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getText().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).getText();
      i = ((String)localObject1).hashCode();
      if (i != -1808122976)
      {
        break label1723;
        if (i != -609016686) {
          break label1756;
        }
      }
      label1723:
      while (!((String)localObject1).equals("Stream"))
      {
        while (!((String)localObject1).equals("Finished")) {}
        i = 1;
        break;
      }
      i = 0;
      break label1758;
      label1756:
      i = -1;
      label1758:
      if (i != 0)
      {
        if (i == 1) {
          temp.setText("??????");
        }
      }
      else
      {
        temp.setTextColor(Color.parseColor("#E65100"));
        temp.setText("??????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).i().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).i();
      i = ((String)localObject1).hashCode();
      if (i != -1851041679) {
        if (i != 73298585)
        {
          break label1849;
          break label1849;
          if (i != 2010341507) {
            break label1897;
          }
        }
      }
      label1849:
      while (!((String)localObject1).equals("Record"))
      {
        do
        {
          while (!((String)localObject1).equals("Criminal")) {}
          i = 1;
          break;
        } while (!((String)localObject1).equals("Legal"));
        i = 0;
        break;
      }
      i = 2;
      break label1899;
      label1897:
      i = -1;
      label1899:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i == 2) {
            messageView.setText("????");
          }
        }
        else {
          messageView.setText("?????");
        }
      }
      else {
        messageView.setText("?????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).d().equals("")) {
      timeView.setText(((com.lawyer_smartCalendar.data.h)localObject2).d());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).e().equals("")) {
      aboutTitle.setText(((com.lawyer_smartCalendar.data.h)localObject2).e());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getValue().equals("")) {
      appDesc.setText(((com.lawyer_smartCalendar.data.h)localObject2).getValue());
    }
    cancel.setText((CharSequence)localObject4);
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getName().equals("")) {
      edit.setText(((com.lawyer_smartCalendar.data.h)localObject2).getName());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getId().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).getId();
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      }
      for (;;)
      {
        break;
        if (((String)localObject1).equals("8"))
        {
          i = 7;
          break label2282;
          if (((String)localObject1).equals("7"))
          {
            i = 6;
            break label2282;
            if (((String)localObject1).equals("6"))
            {
              i = 5;
              break label2282;
              if (((String)localObject1).equals("5"))
              {
                i = 4;
                break label2282;
                if (((String)localObject1).equals("4"))
                {
                  i = 3;
                  break label2282;
                  if (((String)localObject1).equals("3"))
                  {
                    i = 2;
                    break label2282;
                    if (((String)localObject1).equals("2"))
                    {
                      i = 1;
                      break label2282;
                      if (((String)localObject1).equals("1"))
                      {
                        i = 0;
                        break label2282;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      i = -1;
      switch (i)
      {
      default: 
        break;
      case 7: 
        file.setText("????? ????? ?????");
        break;
      case 6: 
        file.setText("????? ???? ????");
        break;
      case 5: 
        file.setText("????? ?????");
        break;
      case 4: 
        file.setText("??????");
        break;
      case 3: 
        file.setText("?? ??????");
        break;
      case 2: 
        file.setText("??????");
        break;
      case 1: 
        file.setText("????? ???");
        break;
      case 0: 
        label2282:
        file.setText("????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).f().equals("")) {
      header.setText(((com.lawyer_smartCalendar.data.h)localObject2).f());
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(com.lawyer_smartCalendar.utils.d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492918);
    this$0 = new com.lawyer_smartCalendar.utils.h(this);
    Toolbar localToolbar = (Toolbar)findViewById(2131296838);
    localToolbar.setPadding(0, 0, 0, 0);
    localToolbar.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(localToolbar);
    localToolbar.setNavigationIcon(2131230872);
    list = ((RecyclerView)findViewById(2131296684));
    Object localObject = (CardView)findViewById(2131296372);
    localObject = (CardView)findViewById(2131296373);
    textView = ((TextView)findViewById(2131296901));
    host = ((TextView)findViewById(2131296900));
    type = ((TextView)findViewById(2131296899));
    next = ((TextView)findViewById(2131296862));
    prefix = ((TextView)findViewById(2131296874));
    url = ((TextView)findViewById(2131296885));
    description = ((TextView)findViewById(2131296892));
    language = ((TextView)findViewById(2131296893));
    name = ((TextView)findViewById(2131296894));
    indicator = ((TextView)findViewById(2131296895));
    titleView = ((TextView)findViewById(2131296897));
    server = ((TextView)findViewById(2131296898));
    username = ((TextView)findViewById(2131296875));
    text = ((TextView)findViewById(2131296876));
    count = ((TextView)findViewById(2131296877));
    tag = ((TextView)findViewById(2131296878));
    title = ((TextView)findViewById(2131296879));
    mItems = ((TextView)findViewById(2131296880));
    mPrompt = ((TextView)findViewById(2131296881));
    mButton = ((TextView)findViewById(2131296882));
    mTitleView = ((TextView)findViewById(2131296883));
    mDescriptionView = ((TextView)findViewById(2131296884));
    mDateView = ((TextView)findViewById(2131296886));
    mDate = ((TextView)findViewById(2131296887));
    mAuthor = ((TextView)findViewById(2131296888));
    mTitle = ((TextView)findViewById(2131296889));
    mDescr = ((TextView)findViewById(2131296890));
    mTime = ((TextView)findViewById(2131296891));
    tvDate = ((TextView)findViewById(2131296903));
    tvTime = ((TextView)findViewById(2131296914));
    b = ((TextView)findViewById(2131296921));
    d = ((TextView)findViewById(2131296922));
    view = ((TextView)findViewById(2131296923));
    time = ((TextView)findViewById(2131296924));
    content = ((TextView)findViewById(2131296926));
    t1 = ((TextView)findViewById(2131296927));
    t2 = ((TextView)findViewById(2131296904));
    t3 = ((TextView)findViewById(2131296905));
    headerTextView = ((TextView)findViewById(2131296906));
    downloadText = ((TextView)findViewById(2131296907));
    welcomeText = ((TextView)findViewById(2131296908));
    appName = ((TextView)findViewById(2131296909));
    messageView = ((TextView)findViewById(2131296910));
    timeView = ((TextView)findViewById(2131296911));
    aboutTitle = ((TextView)findViewById(2131296912));
    appDesc = ((TextView)findViewById(2131296913));
    cancel = ((TextView)findViewById(2131296915));
    edit = ((TextView)findViewById(2131296916));
    file = ((TextView)findViewById(2131296917));
    header = ((TextView)findViewById(2131296918));
    a = ((TextView)findViewById(2131296919));
    temp = ((TextView)findViewById(2131296920));
    localObject = new com.lawyer_smartCalendar.utils.f(this);
    textView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    host.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    type.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    prefix.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    url.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    description.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    language.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    name.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    indicator.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    titleView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    server.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    username.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    text.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    count.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    tag.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    title.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mItems.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mPrompt.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mButton.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mTitleView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mDescriptionView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mDateView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mDate.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mAuthor.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mTitle.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mDescr.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mTime.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    tvDate.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    tvDate.setTextColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
    tvTime.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    b.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    d.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    view.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    time.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    content.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    t1.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    t2.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    headerTextView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    downloadText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    welcomeText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    appName.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    messageView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    timeView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    aboutTitle.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    appDesc.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    cancel.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    edit.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    file.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    header.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    a.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    temp.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    localToolbar.setNavigationOnClickListener(new c());
    onCreate();
    if (paramBundle != null) {
      result = ((ArrayList)paramBundle.getSerializable("payment_list"));
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558412, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i != 2131296288)
    {
      if (i != 2131296290) {
        return super.onOptionsItemSelected(paramMenuItem);
      }
      paramMenuItem = new Intent(this, AddPaymentActivity.class);
      paramMenuItem.putExtra("id", data);
      paramMenuItem.putExtra("frmMode", "edit");
      startActivityForResult(paramMenuItem, id);
      return true;
    }
    paramMenuItem = new MaterialDialog.Builder(this);
    paramMenuItem.get("IRANSansMobile.ttf", "IRANSansMobile.ttf");
    paramMenuItem.setText(GravityEnum.a);
    paramMenuItem.valueOf(GravityEnum.END);
    paramMenuItem.negativeColorRes(2131099708);
    paramMenuItem.content("??? ???? ??? ??? ???? ??????? ??????");
    paramMenuItem.positiveColorRes(2131099728);
    paramMenuItem.content(2131099730);
    paramMenuItem.positiveText("??????");
    paramMenuItem.negativeText("???");
    paramMenuItem.callback(new b());
    paramMenuItem.onPositive(new a());
    paramMenuItem.show();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (isFinishing()) {
      overridePendingTransition(17432576, 17432577);
    }
  }
  
  class a
    implements c
  {
    a() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      PaymentDetailActivity.get(PaymentDetailActivity.this).setEnabled();
      PaymentDetailActivity.get(PaymentDetailActivity.this).toString(data);
      PaymentDetailActivity.get(PaymentDetailActivity.this).close();
      setResult(-1);
      finish();
    }
  }
  
  class b
    implements c
  {
    b() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      paramMaterialDialog.dismiss();
    }
  }
  
  class c
    implements View.OnClickListener
  {
    c() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
}
