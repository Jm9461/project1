package com.lawyer_smartCalendar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.lawyer_smartCalendar.package_2.LineAndPointFormatter;
import com.lawyer_smartCalendar.package_2.NumberPicker;
import com.lawyer_smartCalendar.package_2.Type;
import com.lawyer_smartCalendar.package_2.t;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.h;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.MaterialSearchView.h;
import com.miguelcatalan.materialsearchview.MaterialSearchView.j;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity
  extends AppCompatActivity
{
  MaterialSearchView a;
  private String b = "";
  private h c;
  private String g = "";
  private MenuItem mDelete;
  public RecyclerView mRecyclerView;
  public TextView txt_recycle_no_data;
  
  public SearchActivity() {}
  
  public void a(String paramString)
  {
    String str = b;
    switch (str.hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (str.equals("meeting"))
      {
        i = 0;
        break label116;
        if (str.equals("note"))
        {
          i = 3;
          break label116;
          if (str.equals("case"))
          {
            i = 1;
            break label116;
            if (str.equals("client"))
            {
              i = 2;
              break label116;
            }
          }
        }
      }
    }
    int i = -1;
    label116:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i != 3) {
            return;
          }
          c.setEnabled();
          paramString = c.export(paramString);
          c.close();
          mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
          mRecyclerView.setAdapter(new LineAndPointFormatter(this, paramString));
          if (paramString.isEmpty())
          {
            mRecyclerView.setVisibility(8);
            txt_recycle_no_data.setVisibility(0);
            return;
          }
          mRecyclerView.setVisibility(0);
          txt_recycle_no_data.setVisibility(8);
          return;
        }
        c.setEnabled();
        paramString = c.search(paramString);
        c.close();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new Type(this, paramString));
        if (paramString.isEmpty())
        {
          mRecyclerView.setVisibility(8);
          txt_recycle_no_data.setVisibility(0);
          return;
        }
        mRecyclerView.setVisibility(0);
        txt_recycle_no_data.setVisibility(8);
        return;
      }
      c.setEnabled();
      paramString = c.initialize(paramString);
      c.close();
      mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
      mRecyclerView.setAdapter(new t(this, paramString));
      if (paramString.isEmpty())
      {
        mRecyclerView.setVisibility(8);
        txt_recycle_no_data.setVisibility(0);
        return;
      }
      mRecyclerView.setVisibility(0);
      txt_recycle_no_data.setVisibility(8);
      return;
    }
    c.setEnabled();
    paramString = c.merge(paramString);
    c.close();
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(new NumberPicker(this, paramString));
    if (paramString.isEmpty())
    {
      mRecyclerView.setVisibility(8);
      txt_recycle_no_data.setVisibility(0);
      return;
    }
    mRecyclerView.setVisibility(0);
    txt_recycle_no_data.setVisibility(8);
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(paramContext));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      a(g);
    }
  }
  
  public void onBackPressed()
  {
    if (a.get())
    {
      a.update();
      return;
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(d.getId());
    super.onCreate(paramBundle);
    overridePendingTransition(17432576, 17432577);
    setContentView(2131492922);
    ButterKnife.getView(this);
    paramBundle = (Toolbar)findViewById(2131296838);
    int i = 0;
    paramBundle.setPadding(0, 0, 0, 0);
    paramBundle.setContentInsetsAbsolute(0, 0);
    setSupportActionBar(paramBundle);
    paramBundle.setNavigationIcon(2131230872);
    paramBundle.setNavigationOnClickListener(new a());
    c = new h(this);
    paramBundle.setBackgroundColor(Color.parseColor(d.getValue()));
    a = ((MaterialSearchView)findViewById(2131296720));
    a.setVoiceSearch(false);
    a.setEllipsize(true);
    a.setOnQueryTextListener(new b());
    a.setOnSearchViewListener(new c());
    b = getIntent().getStringExtra("searchMode");
    paramBundle = b;
    switch (paramBundle.hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (paramBundle.equals("meeting"))
      {
        break label380;
        if (paramBundle.equals("document"))
        {
          i = 4;
          break label380;
          if (paramBundle.equals("note"))
          {
            i = 3;
            break label380;
            if (paramBundle.equals("case"))
            {
              i = 1;
              break label380;
              if (paramBundle.equals("tax"))
              {
                i = 6;
                break label380;
                if (paramBundle.equals("payment"))
                {
                  i = 5;
                  break label380;
                  if (paramBundle.equals("accountNumber"))
                  {
                    i = 7;
                    break label380;
                    if (paramBundle.equals("client"))
                    {
                      i = 2;
                      break label380;
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
      return;
    case 7: 
      a.setHint("????? ?? ????? ???? ??...");
      return;
    case 6: 
      a.setHint("????? ?? ??????...");
      return;
    case 5: 
      a.setHint("????? ?? ??????? ??...");
      return;
    case 4: 
      a.setHint("????? ?? ????? ? ?????...");
      return;
    case 3: 
      a.setHint("????? ?? ??????? ??...");
      return;
    case 2: 
      a.setHint("????? ?? ??????...");
      return;
    case 1: 
      label380:
      a.setHint("????? ?? ?????? ??...");
      return;
    }
    a.setHint("????? ?? ?????...");
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558414, paramMenu);
    mDelete = paramMenu.findItem(2131296301);
    a.setMenuItem(mDelete);
    a.clear();
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131296301) {
      a.clear();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (isFinishing()) {
      overridePendingTransition(17432576, 17432577);
    }
  }
  
  public void onTabSelected(Intent paramIntent)
  {
    startActivityForResult(paramIntent, 200);
  }
  
  class a
    implements View.OnClickListener
  {
    a() {}
    
    public void onClick(View paramView)
    {
      onBackPressed();
    }
  }
  
  class b
    implements MaterialSearchView.h
  {
    b() {}
    
    public boolean a(String paramString)
    {
      if ((!paramString.equals("")) && (paramString.length() > 2))
      {
        SearchActivity.a(SearchActivity.this, paramString);
        a(paramString);
      }
      if (paramString.length() == 0)
      {
        mRecyclerView.removeAllViews();
        mRecyclerView.setVisibility(8);
      }
      return false;
    }
    
    public boolean add(String paramString)
    {
      return false;
    }
  }
  
  class c
    implements MaterialSearchView.j
  {
    c() {}
    
    public void a() {}
    
    public void rewind() {}
  }
}
