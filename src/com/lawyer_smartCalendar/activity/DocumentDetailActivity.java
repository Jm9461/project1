package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.lawyer_smartCalendar.data.Item;
import com.lawyer_smartCalendar.data.Label;
import com.lawyer_smartCalendar.package_2.MainScreen;
import com.lawyer_smartCalendar.utils.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DocumentDetailActivity
  extends AppCompatActivity
{
  public String a = "";
  private MainScreen adapter;
  private TextView authorView;
  private com.lawyer_smartCalendar.utils.h b;
  private int c = 156;
  private TextView channel;
  private TextView content;
  private boolean d = false;
  private TextView dateString;
  private TextView description;
  private TextView downloadText;
  private TextView dueDate;
  private TextView emptyView;
  private TextView headerTextView;
  private TextView indicator;
  private TextView input;
  private TextView instructions;
  private ArrayList<File> items = new ArrayList();
  private TextView lang;
  public RecyclerView listView;
  private TextView location;
  private TextView mAlbumName;
  private TextView mArtistName;
  private TextView mAuthor;
  private TextView mCancelButton;
  private TextView mDate;
  private TextView mErrorMessage;
  private TextView mNextButton;
  private TextView mOkButton;
  private TextView mSearchText;
  private TextView mTitle;
  private TextView mTrackName;
  private TextView messageText;
  private TextView name;
  private TextView output;
  private TextView phoneNumber;
  private TextView prefix;
  private TextView progressView;
  private TextView server;
  private TextView statusBar;
  private TextView swipeRefreshLayout;
  private TextView tag;
  private TextView temp;
  private TextView text;
  private TextView textView;
  private TextView time;
  private TextView tintManager;
  private TextView title;
  private TextView titleTextView;
  private TextView titleView;
  private TextView type;
  private TextView unit;
  private TextView uri;
  private TextView url;
  private TextView value;
  private TextView view;
  private TextView webView;
  private TextView welcomeText;
  
  public DocumentDetailActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == c) && (paramInt2 == -1))
    {
      onCreate();
      d = true;
    }
  }
  
  public void onBackPressed()
  {
    if (d) {
      setResult(-1);
    }
    finish();
  }
  
  public void onCreate()
  {
    a = getIntent().getStringExtra("id");
    items = new ArrayList();
    b.setEnabled();
    Object localObject4 = b.f(a);
    Object localObject1 = b;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((Label)localObject4).d());
    ((StringBuilder)localObject2).append("");
    Object localObject5 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject2).toString());
    localObject1 = b;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((Label)localObject4).a());
    ((StringBuilder)localObject2).append("");
    localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject1).a(((StringBuilder)localObject2).toString());
    localObject1 = b.search(a, com.lawyer_smartCalendar.utils.c.d);
    Object localObject3 = b;
    Object localObject6 = new StringBuilder();
    ((StringBuilder)localObject6).append(((com.lawyer_smartCalendar.data.h)localObject2).b());
    ((StringBuilder)localObject6).append("");
    localObject3 = ((com.lawyer_smartCalendar.utils.h)localObject3).remove(((StringBuilder)localObject6).toString());
    b.close();
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      localObject6 = new File(Uri.decode(((Item)((List)localObject1).get(i)).getId()));
      items.add(localObject6);
      i += 1;
    }
    adapter = new MainScreen(this, listView, items);
    localObject1 = new GridLayoutManager(this, 3, 1, false);
    listView.setLayoutManager((RecyclerView.o)localObject1);
    listView.setAdapter(adapter);
    localObject1 = "";
    localObject6 = ((Label)localObject4).getColor();
    switch (((String)localObject6).hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject6).equals("SejliDocument"))
      {
        i = 2;
        break label443;
        if (((String)localObject6).equals("Other"))
        {
          i = 3;
          break label443;
          if (((String)localObject6).equals("Check"))
          {
            i = 0;
            break label443;
            if (((String)localObject6).equals("MarriageCertificate"))
            {
              i = 1;
              break label443;
            }
          }
        }
      }
    }
    i = -1;
    label443:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3)
          {
            lang.setBackgroundResource(2131230846);
            localObject1 = "???? ?????";
          }
        }
        else
        {
          lang.setBackgroundResource(2131230848);
          localObject1 = "????? ????";
        }
      }
      else
      {
        lang.setBackgroundResource(2131230849);
        localObject1 = "??? ??????";
      }
    }
    else
    {
      lang.setBackgroundResource(2131230847);
      localObject1 = "??";
    }
    lang.setText((CharSequence)localObject1);
    StringBuilder localStringBuilder;
    if (!((Label)localObject4).getName().equals(""))
    {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)localObject4).getName())));
      localObject6 = Calendar.getInstance();
      ((Calendar)localObject6).setTimeInMillis(Long.parseLong(((Label)localObject4).getName()));
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" ???? ");
      localStringBuilder.append(((Calendar)localObject6).get(11));
      localStringBuilder.append(":");
      localStringBuilder.append(((Calendar)localObject6).get(12));
      localObject1 = localStringBuilder.toString();
      type.setText((CharSequence)localObject1);
    }
    else
    {
      type.setText("-");
    }
    if (!((Label)localObject4).getText().equals(""))
    {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)localObject4).getText())));
      localObject6 = Calendar.getInstance();
      ((Calendar)localObject6).setTimeInMillis(Long.parseLong(((Label)localObject4).getText()));
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(" ???? ");
      localStringBuilder.append(((Calendar)localObject6).get(11));
      localStringBuilder.append(":");
      localStringBuilder.append(((Calendar)localObject6).get(12));
      localObject1 = localStringBuilder.toString();
      server.setText((CharSequence)localObject1);
    }
    else
    {
      server.setText("-");
    }
    if (!((Label)localObject4).getValue().equals("")) {
      indicator.setText(((Label)localObject4).getValue());
    } else {
      indicator.setText("-");
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getValue().equals("")) {
      titleView.setText(((com.lawyer_smartCalendar.data.f)localObject5).getValue());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).d().equals("")) {
      view.setText(((com.lawyer_smartCalendar.data.f)localObject5).d());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).get().equals("")) {
      text.setText(((com.lawyer_smartCalendar.data.f)localObject5).get());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getText().equals("")) {
      location.setText(((com.lawyer_smartCalendar.data.f)localObject5).getText());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).a().equals("")) {
      channel.setText(((com.lawyer_smartCalendar.data.f)localObject5).a());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).e().equals("")) {
      input.setText(((com.lawyer_smartCalendar.data.f)localObject5).e());
    }
    if (!((com.lawyer_smartCalendar.data.f)localObject5).getTitle().equals("")) {
      output.setText(((com.lawyer_smartCalendar.data.f)localObject5).getTitle());
    }
    if (((com.lawyer_smartCalendar.data.f)localObject5).f().equals("legal"))
    {
      webView.setVisibility(8);
      content.setVisibility(8);
      statusBar.setVisibility(8);
      view.setVisibility(8);
      text.setVisibility(8);
      location.setVisibility(8);
      ((ViewGroup)((ViewGroup)view.getParent()).getParent()).removeView((ViewGroup)view.getParent());
      ((ViewGroup)((ViewGroup)text.getParent()).getParent()).removeView((ViewGroup)text.getParent());
      ((ViewGroup)((ViewGroup)location.getParent()).getParent()).removeView((ViewGroup)location.getParent());
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
          break label1324;
          if (((String)localObject1).equals("Bank"))
          {
            i = 3;
            break label1324;
            if (((String)localObject1).equals("Challenge"))
            {
              i = 2;
              break label1324;
              if (((String)localObject1).equals("Normal"))
              {
                i = 0;
                break label1324;
              }
            }
          }
        }
      }
      i = -1;
      label1324:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i == 3) {
              temp.setText("?????");
            }
          }
          else {
            temp.setText("???????/??????");
          }
        }
        else {
          temp.setText("??????");
        }
      }
      else {
        temp.setText("????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getIcon().equals(""))
    {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon())));
      localObject4 = Calendar.getInstance();
      ((Calendar)localObject4).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject2).getIcon()));
      localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append((String)localObject1);
      ((StringBuilder)localObject5).append(" ???? ");
      ((StringBuilder)localObject5).append(((Calendar)localObject4).get(11));
      ((StringBuilder)localObject5).append(":");
      ((StringBuilder)localObject5).append(((Calendar)localObject4).get(12));
      localObject1 = ((StringBuilder)localObject5).toString();
      authorView.setText((CharSequence)localObject1);
    }
    boolean bool = ((com.lawyer_smartCalendar.data.h)localObject2).getTitle().equals("");
    int j = 5;
    if (!bool)
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
          break label1712;
          if (((String)localObject1).equals("Reconsidered"))
          {
            i = 5;
            break label1712;
            if (((String)localObject1).equals("Read"))
            {
              i = 1;
              break label1712;
              if (((String)localObject1).equals("Applicant"))
              {
                i = 0;
                break label1712;
                if (((String)localObject1).equals("Plaintiff"))
                {
                  i = 2;
                  break label1712;
                  if (((String)localObject1).equals("Revisionist"))
                  {
                    i = 4;
                    break label1712;
                  }
                }
              }
            }
          }
        }
      }
      i = -1;
      label1712:
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
                  textView.setText("????? ??????");
                }
              }
              else {
                textView.setText("????? ??? ????");
              }
            }
            else {
              textView.setText("????");
            }
          }
          else {
            textView.setText("????");
          }
        }
        else {
          textView.setText("??????");
        }
      }
      else {
        textView.setText("??????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getCount().equals("")) {
      prefix.setText(((com.lawyer_smartCalendar.data.h)localObject2).getCount());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getText().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).getText();
      i = ((String)localObject1).hashCode();
      if (i != -1808122976)
      {
        break label1887;
        if (i != -609016686) {
          break label1922;
        }
      }
      label1887:
      while (!((String)localObject1).equals("Stream"))
      {
        while (!((String)localObject1).equals("Finished")) {}
        i = 1;
        break;
      }
      i = 0;
      break label1924;
      label1922:
      i = -1;
      label1924:
      if (i != 0)
      {
        if (i == 1) {
          name.setText("??????");
        }
      }
      else
      {
        name.setTextColor(Color.parseColor("#E65100"));
        name.setText("??????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).i().equals(""))
    {
      localObject1 = ((com.lawyer_smartCalendar.data.h)localObject2).i();
      i = ((String)localObject1).hashCode();
      if (i != -1851041679) {
        if (i != 73298585)
        {
          break label2019;
          break label2019;
          if (i != 2010341507) {
            break label2070;
          }
        }
      }
      label2019:
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
      break label2072;
      label2070:
      i = -1;
      label2072:
      if (i != 0)
      {
        if (i != 1)
        {
          if (i == 2) {
            url.setText("????");
          }
        }
        else {
          url.setText("?????");
        }
      }
      else {
        url.setText("?????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).d().equals("")) {
      downloadText.setText(((com.lawyer_smartCalendar.data.h)localObject2).d());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).e().equals("")) {
      welcomeText.setText(((com.lawyer_smartCalendar.data.h)localObject2).e());
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getValue().equals("")) {
      headerTextView.setText(((com.lawyer_smartCalendar.data.h)localObject2).getValue());
    }
    description.setText((CharSequence)localObject3);
    if (!((com.lawyer_smartCalendar.data.h)localObject2).getName().equals("")) {
      dueDate.setText(((com.lawyer_smartCalendar.data.h)localObject2).getName());
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
          break label2474;
          if (((String)localObject1).equals("7"))
          {
            i = 6;
            break label2474;
            if (((String)localObject1).equals("6"))
            {
              i = j;
              break label2474;
              if (((String)localObject1).equals("5"))
              {
                i = 4;
                break label2474;
                if (((String)localObject1).equals("4"))
                {
                  i = 3;
                  break label2474;
                  if (((String)localObject1).equals("3"))
                  {
                    i = 2;
                    break label2474;
                    if (((String)localObject1).equals("2"))
                    {
                      i = 1;
                      break label2474;
                      if (((String)localObject1).equals("1"))
                      {
                        i = 0;
                        break label2474;
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
        dateString.setText("????? ????? ?????");
        break;
      case 6: 
        dateString.setText("????? ???? ????");
        break;
      case 5: 
        dateString.setText("????? ?????");
        break;
      case 4: 
        dateString.setText("??????");
        break;
      case 3: 
        dateString.setText("?? ??????");
        break;
      case 2: 
        dateString.setText("??????");
        break;
      case 1: 
        dateString.setText("????? ???");
        break;
      case 0: 
        label2474:
        dateString.setText("????");
      }
    }
    if (!((com.lawyer_smartCalendar.data.h)localObject2).f().equals("")) {
      instructions.setText(((com.lawyer_smartCalendar.data.h)localObject2).f());
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492908);
    b = new com.lawyer_smartCalendar.utils.h(this);
    Toolbar localToolbar = (Toolbar)findViewById(2131296838);
    localToolbar.setPadding(0, 0, 0, 0);
    localToolbar.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(localToolbar);
    localToolbar.setNavigationIcon(2131230872);
    listView = ((RecyclerView)findViewById(2131296684));
    Object localObject = (CardView)findViewById(2131296372);
    localObject = (CardView)findViewById(2131296373);
    swipeRefreshLayout = ((TextView)findViewById(2131296901));
    emptyView = ((TextView)findViewById(2131296900));
    progressView = ((TextView)findViewById(2131296899));
    titleTextView = ((TextView)findViewById(2131296874));
    uri = ((TextView)findViewById(2131296885));
    phoneNumber = ((TextView)findViewById(2131296892));
    messageText = ((TextView)findViewById(2131296893));
    webView = ((TextView)findViewById(2131296894));
    content = ((TextView)findViewById(2131296895));
    statusBar = ((TextView)findViewById(2131296897));
    tintManager = ((TextView)findViewById(2131296898));
    mSearchText = ((TextView)findViewById(2131296875));
    mNextButton = ((TextView)findViewById(2131296876));
    mTrackName = ((TextView)findViewById(2131296877));
    mArtistName = ((TextView)findViewById(2131296878));
    mAlbumName = ((TextView)findViewById(2131296879));
    mErrorMessage = ((TextView)findViewById(2131296880));
    mDate = ((TextView)findViewById(2131296881));
    mTitle = ((TextView)findViewById(2131296882));
    mAuthor = ((TextView)findViewById(2131296883));
    mCancelButton = ((TextView)findViewById(2131296884));
    mOkButton = ((TextView)findViewById(2131296886));
    unit = ((TextView)findViewById(2131296887));
    value = ((TextView)findViewById(2131296888));
    time = ((TextView)findViewById(2131296889));
    tag = ((TextView)findViewById(2131296890));
    title = ((TextView)findViewById(2131296891));
    lang = ((TextView)findViewById(2131296903));
    type = ((TextView)findViewById(2131296914));
    server = ((TextView)findViewById(2131296921));
    titleView = ((TextView)findViewById(2131296922));
    view = ((TextView)findViewById(2131296923));
    text = ((TextView)findViewById(2131296924));
    location = ((TextView)findViewById(2131296926));
    channel = ((TextView)findViewById(2131296927));
    input = ((TextView)findViewById(2131296904));
    output = ((TextView)findViewById(2131296905));
    temp = ((TextView)findViewById(2131296906));
    authorView = ((TextView)findViewById(2131296907));
    textView = ((TextView)findViewById(2131296908));
    prefix = ((TextView)findViewById(2131296909));
    url = ((TextView)findViewById(2131296910));
    downloadText = ((TextView)findViewById(2131296911));
    welcomeText = ((TextView)findViewById(2131296912));
    headerTextView = ((TextView)findViewById(2131296913));
    description = ((TextView)findViewById(2131296915));
    dueDate = ((TextView)findViewById(2131296916));
    dateString = ((TextView)findViewById(2131296917));
    instructions = ((TextView)findViewById(2131296918));
    indicator = ((TextView)findViewById(2131296919));
    name = ((TextView)findViewById(2131296920));
    localObject = new com.lawyer_smartCalendar.utils.f(this);
    swipeRefreshLayout.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    emptyView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    progressView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    titleTextView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    uri.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    phoneNumber.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    messageText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    webView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    content.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    statusBar.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    tintManager.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mSearchText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mNextButton.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mTrackName.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mArtistName.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mAlbumName.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mErrorMessage.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mDate.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mTitle.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mAuthor.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mCancelButton.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    mOkButton.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    unit.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    value.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    time.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    tag.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    title.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).get());
    lang.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    type.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    server.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    titleView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    view.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    text.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    location.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    channel.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    input.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    temp.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    authorView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    textView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    prefix.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    url.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    downloadText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    welcomeText.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    headerTextView.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    description.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    dueDate.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    dateString.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    instructions.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    indicator.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    name.setTypeface(((com.lawyer_smartCalendar.utils.f)localObject).setText());
    localToolbar.setNavigationOnClickListener(new c());
    onCreate();
    if (paramBundle != null) {
      items = ((ArrayList)paramBundle.getSerializable("easy_image_photos_list"));
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558406, paramMenu);
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
      paramMenuItem = new Intent(this, AddClientDocumentsActivity.class);
      paramMenuItem.putExtra("id", a);
      paramMenuItem.putExtra("frmMode", "edit");
      startActivityForResult(paramMenuItem, c);
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
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putSerializable("easy_image_photos_list", items);
  }
  
  class a
    implements apps.afollestad.materialdialogs.c
  {
    a() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      DocumentDetailActivity.a(DocumentDetailActivity.this).setEnabled();
      DocumentDetailActivity.a(DocumentDetailActivity.this).b(a);
      DocumentDetailActivity.a(DocumentDetailActivity.this).remove(a, com.lawyer_smartCalendar.utils.c.d);
      DocumentDetailActivity.a(DocumentDetailActivity.this).close();
      setResult(-1);
      finish();
    }
  }
  
  class b
    implements apps.afollestad.materialdialogs.c
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
