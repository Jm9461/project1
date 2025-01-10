package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
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
import com.lawyer_smartCalendar.package_2.AppCompatDelegateImplV7;
import com.lawyer_smartCalendar.ui.b;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NoteDetailActivity
  extends AppCompatActivity
{
  private com.lawyer_smartCalendar.data.List a;
  private TextView appName;
  private TextView authorView;
  private com.lawyer_smartCalendar.utils.h b;
  private TextView button;
  public String c = "";
  private TextView content;
  private boolean d = false;
  private TextView date;
  private TextView description;
  private TextView downloadText;
  private AppCompatDelegateImplV7 h;
  private ArrayList<File> id = new ArrayList();
  private int index = 156;
  private TextView mAlbumName;
  private TextView mArtistName;
  private TextView mAuthor;
  private TextView mClearButton;
  private TextView mContext;
  private TextView mDate;
  private CardView mDisconnectButton;
  private TextView mDoneButton;
  private TextView mErrorMessage;
  private TextView mId;
  private TextView mLabel;
  private RecyclerView mList;
  private TextView mName;
  private TextView mPassword;
  private TextView mPasswordView;
  private TextView mPort;
  private TextView mPortView;
  private TextView mResult;
  private TextView mServerView;
  private TextView mTitle;
  private TextView mTrackName;
  private TextView mUrl;
  private TextView mUser;
  private TextView mUserView;
  private CardView mView;
  private TextView messageView;
  private TextView name;
  private TextView okButton;
  private TextView packageName;
  private TextView prefix;
  private TextView t1;
  private TextView t2;
  private TextView t3;
  private TextView t4;
  private TextView tag;
  private TextView text;
  private TextView textView;
  private TextView time;
  private TextView title;
  private TextView titleTextView;
  private TextView titleView;
  private TextView uri;
  private TextView url;
  private TextView view;
  private TextView welcomeText;
  
  public NoteDetailActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == index) && (paramInt2 == -1))
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
    id = new ArrayList();
    c = getIntent().getStringExtra("id");
    b.setEnabled();
    a = b.create(c);
    Object localObject1 = b;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(a.size());
    ((StringBuilder)localObject2).append("");
    Object localObject3 = ((com.lawyer_smartCalendar.utils.h)localObject1).set(((StringBuilder)localObject2).toString());
    localObject1 = b;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(a.a());
    ((StringBuilder)localObject2).append("");
    localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).a(((StringBuilder)localObject2).toString());
    localObject2 = b;
    Object localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append(((com.lawyer_smartCalendar.data.h)localObject1).b());
    ((StringBuilder)localObject4).append("");
    localObject2 = ((com.lawyer_smartCalendar.utils.h)localObject2).remove(((StringBuilder)localObject4).toString());
    localObject4 = b.search(c, com.lawyer_smartCalendar.utils.c.c);
    b.close();
    int i = 0;
    Object localObject5;
    while (i < ((java.util.List)localObject4).size())
    {
      localObject5 = new File(Uri.decode(((Item)((java.util.List)localObject4).get(i)).getId()));
      id.add(localObject5);
      i += 1;
    }
    h = new AppCompatDelegateImplV7(this, id);
    localObject4 = new LinearLayoutManager(this);
    int j = 1;
    ((LinearLayoutManager)localObject4).setOrientation(1);
    mList.setLayoutManager((RecyclerView.o)localObject4);
    mList.setAdapter(h);
    if (!a.get().equals("")) {
      t3.setText(a.get());
    }
    if (!a.getId().equals(""))
    {
      localObject4 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(a.getId())));
      localObject5 = Calendar.getInstance();
      ((Calendar)localObject5).setTimeInMillis(Long.parseLong(a.getId()));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject4);
      localStringBuilder.append(" ???? ");
      localStringBuilder.append(((Calendar)localObject5).get(11));
      localStringBuilder.append(":");
      localStringBuilder.append(((Calendar)localObject5).get(12));
      localObject4 = localStringBuilder.toString();
      t4.setText((CharSequence)localObject4);
    }
    if (!a.getValue().equals("")) {
      messageView.setText(a.getValue());
    }
    if (a.getString().equals("ClientCase"))
    {
      if (!((com.lawyer_smartCalendar.data.f)localObject3).getValue().equals("")) {
        button.setText(((com.lawyer_smartCalendar.data.f)localObject3).getValue());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).d().equals("")) {
        view.setText(((com.lawyer_smartCalendar.data.f)localObject3).d());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).get().equals("")) {
        name.setText(((com.lawyer_smartCalendar.data.f)localObject3).get());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).getText().equals("")) {
        content.setText(((com.lawyer_smartCalendar.data.f)localObject3).getText());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).a().equals("")) {
        time.setText(((com.lawyer_smartCalendar.data.f)localObject3).a());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).e().equals("")) {
        tag.setText(((com.lawyer_smartCalendar.data.f)localObject3).e());
      }
      if (!((com.lawyer_smartCalendar.data.f)localObject3).getTitle().equals("")) {
        mLabel.setText(((com.lawyer_smartCalendar.data.f)localObject3).getTitle());
      }
      if (((com.lawyer_smartCalendar.data.f)localObject3).f().equals("legal"))
      {
        title.setVisibility(8);
        date.setVisibility(8);
        description.setVisibility(8);
        view.setVisibility(8);
        name.setVisibility(8);
        content.setVisibility(8);
        ((ViewGroup)((ViewGroup)view.getParent()).getParent()).removeView((ViewGroup)view.getParent());
        ((ViewGroup)((ViewGroup)name.getParent()).getParent()).removeView((ViewGroup)name.getParent());
        ((ViewGroup)((ViewGroup)content.getParent()).getParent()).removeView((ViewGroup)content.getParent());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getString().equals(""))
      {
        localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).getString();
        switch (((String)localObject3).hashCode())
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          if (((String)localObject3).equals("Executive"))
          {
            i = 1;
            break label988;
            if (((String)localObject3).equals("Bank"))
            {
              i = 3;
              break label988;
              if (((String)localObject3).equals("Challenge"))
              {
                i = 2;
                break label988;
                if (((String)localObject3).equals("Normal"))
                {
                  i = 0;
                  break label988;
                }
              }
            }
          }
        }
        i = -1;
        label988:
        if (i != 0)
        {
          if (i != 1)
          {
            if (i != 2)
            {
              if (i == 3) {
                mUrl.setText("?????");
              }
            }
            else {
              mUrl.setText("???????/??????");
            }
          }
          else {
            mUrl.setText("??????");
          }
        }
        else {
          mUrl.setText("????");
        }
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getIcon().equals(""))
      {
        localObject3 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject1).getIcon())));
        localObject4 = Calendar.getInstance();
        ((Calendar)localObject4).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.h)localObject1).getIcon()));
        localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append((String)localObject3);
        ((StringBuilder)localObject5).append(" ???? ");
        ((StringBuilder)localObject5).append(((Calendar)localObject4).get(11));
        ((StringBuilder)localObject5).append(":");
        ((StringBuilder)localObject5).append(((Calendar)localObject4).get(12));
        localObject3 = ((StringBuilder)localObject5).toString();
        mId.setText((CharSequence)localObject3);
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getTitle().equals(""))
      {
        localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).getTitle();
        switch (((String)localObject3).hashCode())
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          if (((String)localObject3).equals("Accused"))
          {
            i = 3;
            break label1364;
            if (((String)localObject3).equals("Reconsidered"))
            {
              i = 5;
              break label1364;
              if (((String)localObject3).equals("Read"))
              {
                i = 1;
                break label1364;
                if (((String)localObject3).equals("Applicant"))
                {
                  i = 0;
                  break label1364;
                  if (((String)localObject3).equals("Plaintiff"))
                  {
                    i = 2;
                    break label1364;
                    if (((String)localObject3).equals("Revisionist"))
                    {
                      i = 4;
                      break label1364;
                    }
                  }
                }
              }
            }
          }
        }
        i = -1;
        label1364:
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
                    titleView.setText("????? ??????");
                  }
                }
                else {
                  titleView.setText("????? ??? ????");
                }
              }
              else {
                titleView.setText("????");
              }
            }
            else {
              titleView.setText("????");
            }
          }
          else {
            titleView.setText("??????");
          }
        }
        else {
          titleView.setText("??????");
        }
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getCount().equals("")) {
        authorView.setText(((com.lawyer_smartCalendar.data.h)localObject1).getCount());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getText().equals(""))
      {
        localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).getText();
        i = ((String)localObject3).hashCode();
        if (i != -1808122976)
        {
          break label1535;
          if (i != -609016686) {
            break label1570;
          }
        }
        label1535:
        while (!((String)localObject3).equals("Stream"))
        {
          while (!((String)localObject3).equals("Finished")) {}
          i = 1;
          break;
        }
        i = 0;
        break label1572;
        label1570:
        i = -1;
        label1572:
        if (i != 0)
        {
          if (i == 1) {
            packageName.setText("??????");
          }
        }
        else
        {
          packageName.setTextColor(Color.parseColor("#E65100"));
          packageName.setText("??????");
        }
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).i().equals(""))
      {
        localObject3 = ((com.lawyer_smartCalendar.data.h)localObject1).i();
        i = ((String)localObject3).hashCode();
        if (i != -1851041679) {
          if (i != 73298585)
          {
            break label1665;
            break label1665;
            if (i != 2010341507) {
              break label1716;
            }
          }
        }
        label1665:
        while (!((String)localObject3).equals("Record"))
        {
          do
          {
            while (!((String)localObject3).equals("Criminal")) {}
            i = 1;
            break;
          } while (!((String)localObject3).equals("Legal"));
          i = 0;
          break;
        }
        i = 2;
        break label1718;
        label1716:
        i = -1;
        label1718:
        if (i != 0)
        {
          if (i != 1)
          {
            if (i == 2) {
              textView.setText("????");
            }
          }
          else {
            textView.setText("?????");
          }
        }
        else {
          textView.setText("?????");
        }
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).d().equals("")) {
        prefix.setText(((com.lawyer_smartCalendar.data.h)localObject1).d());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).e().equals("")) {
        url.setText(((com.lawyer_smartCalendar.data.h)localObject1).e());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getValue().equals("")) {
        text.setText(((com.lawyer_smartCalendar.data.h)localObject1).getValue());
      }
      okButton.setText((CharSequence)localObject2);
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getName().equals("")) {
        downloadText.setText(((com.lawyer_smartCalendar.data.h)localObject1).getName());
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).getId().equals(""))
      {
        localObject2 = ((com.lawyer_smartCalendar.data.h)localObject1).getId();
        switch (((String)localObject2).hashCode())
        {
        default: 
          break;
        }
        for (;;)
        {
          break;
          if (((String)localObject2).equals("8"))
          {
            i = 7;
            break label2110;
            if (((String)localObject2).equals("7"))
            {
              i = 6;
              break label2110;
              if (((String)localObject2).equals("6"))
              {
                i = 5;
                break label2110;
                if (((String)localObject2).equals("5"))
                {
                  i = 4;
                  break label2110;
                  if (((String)localObject2).equals("4"))
                  {
                    i = 3;
                    break label2110;
                    if (((String)localObject2).equals("3"))
                    {
                      i = 2;
                      break label2110;
                      if (((String)localObject2).equals("2"))
                      {
                        i = j;
                        break label2110;
                        if (((String)localObject2).equals("1"))
                        {
                          i = 0;
                          break label2110;
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
          welcomeText.setText("????? ????? ?????");
          break;
        case 6: 
          welcomeText.setText("????? ???? ????");
          break;
        case 5: 
          welcomeText.setText("????? ?????");
          break;
        case 4: 
          welcomeText.setText("??????");
          break;
        case 3: 
          welcomeText.setText("?? ??????");
          break;
        case 2: 
          welcomeText.setText("??????");
          break;
        case 1: 
          welcomeText.setText("????? ???");
          break;
        case 0: 
          label2110:
          welcomeText.setText("????");
        }
      }
      if (!((com.lawyer_smartCalendar.data.h)localObject1).f().equals("")) {
        appName.setText(((com.lawyer_smartCalendar.data.h)localObject1).f());
      }
    }
    else
    {
      mView.setVisibility(8);
      mDisconnectButton.setVisibility(8);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492916);
    b = new com.lawyer_smartCalendar.utils.h(this);
    Toolbar localToolbar = (Toolbar)findViewById(2131296838);
    localToolbar.setPadding(0, 0, 0, 0);
    localToolbar.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(localToolbar);
    localToolbar.setNavigationIcon(2131230872);
    mList = ((RecyclerView)findViewById(2131296684));
    mView = ((CardView)findViewById(2131296372));
    mDisconnectButton = ((CardView)findViewById(2131296373));
    mTrackName = ((TextView)findViewById(2131296901));
    mArtistName = ((TextView)findViewById(2131296900));
    mAlbumName = ((TextView)findViewById(2131296899));
    mErrorMessage = ((TextView)findViewById(2131296874));
    mDate = ((TextView)findViewById(2131296885));
    mTitle = ((TextView)findViewById(2131296892));
    mAuthor = ((TextView)findViewById(2131296893));
    title = ((TextView)findViewById(2131296894));
    date = ((TextView)findViewById(2131296895));
    description = ((TextView)findViewById(2131296897));
    titleTextView = ((TextView)findViewById(2131296898));
    uri = ((TextView)findViewById(2131296875));
    mContext = ((TextView)findViewById(2131296876));
    mPort = ((TextView)findViewById(2131296877));
    mPassword = ((TextView)findViewById(2131296878));
    mUser = ((TextView)findViewById(2131296879));
    mResult = ((TextView)findViewById(2131296880));
    mName = ((TextView)findViewById(2131296881));
    mDoneButton = ((TextView)findViewById(2131296882));
    mClearButton = ((TextView)findViewById(2131296883));
    mServerView = ((TextView)findViewById(2131296884));
    mPortView = ((TextView)findViewById(2131296886));
    mUserView = ((TextView)findViewById(2131296887));
    mPasswordView = ((TextView)findViewById(2131296888));
    t1 = ((TextView)findViewById(2131296889));
    t2 = ((TextView)findViewById(2131296890));
    t3 = ((TextView)findViewById(2131296903));
    t4 = ((TextView)findViewById(2131296914));
    messageView = ((TextView)findViewById(2131296921));
    button = ((TextView)findViewById(2131296922));
    view = ((TextView)findViewById(2131296923));
    name = ((TextView)findViewById(2131296924));
    content = ((TextView)findViewById(2131296926));
    time = ((TextView)findViewById(2131296927));
    tag = ((TextView)findViewById(2131296904));
    mLabel = ((TextView)findViewById(2131296905));
    mUrl = ((TextView)findViewById(2131296906));
    mId = ((TextView)findViewById(2131296907));
    titleView = ((TextView)findViewById(2131296908));
    authorView = ((TextView)findViewById(2131296909));
    textView = ((TextView)findViewById(2131296910));
    prefix = ((TextView)findViewById(2131296911));
    url = ((TextView)findViewById(2131296912));
    text = ((TextView)findViewById(2131296913));
    okButton = ((TextView)findViewById(2131296915));
    downloadText = ((TextView)findViewById(2131296916));
    welcomeText = ((TextView)findViewById(2131296917));
    appName = ((TextView)findViewById(2131296918));
    packageName = ((TextView)findViewById(2131296919));
    com.lawyer_smartCalendar.utils.f localF = new com.lawyer_smartCalendar.utils.f(this);
    mTrackName.setTypeface(localF.get());
    mArtistName.setTypeface(localF.get());
    mAlbumName.setTypeface(localF.get());
    mErrorMessage.setTypeface(localF.get());
    mDate.setTypeface(localF.get());
    mTitle.setTypeface(localF.get());
    mAuthor.setTypeface(localF.get());
    title.setTypeface(localF.get());
    date.setTypeface(localF.get());
    description.setTypeface(localF.get());
    titleTextView.setTypeface(localF.get());
    uri.setTypeface(localF.get());
    mContext.setTypeface(localF.get());
    mPort.setTypeface(localF.get());
    mPassword.setTypeface(localF.get());
    mUser.setTypeface(localF.get());
    mResult.setTypeface(localF.get());
    mName.setTypeface(localF.get());
    mDoneButton.setTypeface(localF.get());
    mClearButton.setTypeface(localF.get());
    mServerView.setTypeface(localF.get());
    mPortView.setTypeface(localF.get());
    mUserView.setTypeface(localF.get());
    mPasswordView.setTypeface(localF.get());
    t1.setTypeface(localF.get());
    t2.setTypeface(localF.get());
    t3.setTypeface(localF.setText());
    t4.setTypeface(localF.setText());
    messageView.setTypeface(localF.setText());
    button.setTypeface(localF.setText());
    view.setTypeface(localF.setText());
    name.setTypeface(localF.setText());
    content.setTypeface(localF.setText());
    time.setTypeface(localF.setText());
    tag.setTypeface(localF.setText());
    mLabel.setTypeface(localF.setText());
    mUrl.setTypeface(localF.setText());
    mId.setTypeface(localF.setText());
    titleView.setTypeface(localF.setText());
    authorView.setTypeface(localF.setText());
    textView.setTypeface(localF.setText());
    prefix.setTypeface(localF.setText());
    url.setTypeface(localF.setText());
    text.setTypeface(localF.setText());
    okButton.setTypeface(localF.setText());
    downloadText.setTypeface(localF.setText());
    welcomeText.setTypeface(localF.setText());
    appName.setTypeface(localF.setText());
    packageName.setTypeface(localF.setText());
    getSupportActionBar().setTitle("?????? ???????");
    localToolbar.setNavigationOnClickListener(new c());
    if (paramBundle != null) {
      id = ((ArrayList)paramBundle.getSerializable("audio_list"));
    }
    onCreate();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558411, paramMenu);
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
      paramMenuItem = new Intent(this, AddNoteActivity.class);
      paramMenuItem.putExtra("id", c);
      paramMenuItem.putExtra("frmMode", "edit");
      startActivityForResult(paramMenuItem, index);
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
    paramBundle.putSerializable("audio_list", id);
  }
  
  class a
    implements apps.afollestad.materialdialogs.c
  {
    a() {}
    
    public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
    {
      NoteDetailActivity.a(NoteDetailActivity.this).setEnabled();
      NoteDetailActivity.a(NoteDetailActivity.this).delete(c);
      NoteDetailActivity.a(NoteDetailActivity.this).close();
      paramMaterialDialog = e.a;
      try
      {
        paramMaterialDialog.onCreateView();
      }
      catch (Exception paramMaterialDialog) {}
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
