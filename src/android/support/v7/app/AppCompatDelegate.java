package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract class AppCompatDelegate
{
  private static int sDefaultNightMode = -1;
  
  AppCompatDelegate() {}
  
  public static AppCompatDelegate create(Activity paramActivity, AppCompatCallback paramAppCompatCallback)
  {
    return new AppCompatDelegateImplV7(paramActivity, paramActivity.getWindow(), paramAppCompatCallback);
  }
  
  public static AppCompatDelegate create(Dialog paramDialog, AppCompatCallback paramAppCompatCallback)
  {
    return new AppCompatDelegateImplV7(paramDialog.getContext(), paramDialog.getWindow(), paramAppCompatCallback);
  }
  
  public static int getDefaultNightMode()
  {
    return sDefaultNightMode;
  }
  
  public abstract boolean a();
  
  public abstract void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);
  
  public abstract View findViewById(int paramInt);
  
  public abstract i getDrawerToggleDelegate();
  
  public abstract MenuInflater getMenuInflater();
  
  public abstract ActionBar getSupportActionBar();
  
  public abstract void installViewFactory();
  
  public abstract void invalidateOptionsMenu();
  
  public abstract void onConfigurationChanged(Configuration paramConfiguration);
  
  public abstract void onCreate();
  
  public abstract void onCreate(Bundle paramBundle);
  
  public abstract void onPostCreate(Bundle paramBundle);
  
  public abstract void onPostResume();
  
  public abstract void onSaveInstanceState(Bundle paramBundle);
  
  public abstract void onStop();
  
  public abstract void onTitleChanged(CharSequence paramCharSequence);
  
  public abstract boolean requestWindowFeature(int paramInt);
  
  public abstract void setContentView(int paramInt);
  
  public abstract void setContentView(View paramView);
  
  public abstract void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);
  
  public abstract void setSupportActionBar();
  
  public abstract void setSupportActionBar(Toolbar paramToolbar);
}
