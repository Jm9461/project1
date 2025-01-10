package com.lawyer_smartCalendar.ui;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewMediaFragment
  extends DialogFragment
{
  public static int iii = 0;
  private TextView date;
  private Handler handle = new Handler();
  private int id = 5000;
  private Button mButtonCancel;
  private Button mButtonOk;
  private int mSavedPlaybackPosition = 5000;
  private SeekBar mView;
  private Button nextButton;
  public String path = "";
  private MediaPlayer player;
  private Button previousButton;
  private Runnable refresh = new MonthByWeekFragment.2(this);
  private double weight = 0.0D;
  private double x = 0.0D;
  
  public PreviewMediaFragment() {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131492932, paramViewGroup, false);
    mButtonOk = ((Button)paramLayoutInflater.findViewById(2131296358));
    nextButton = ((Button)paramLayoutInflater.findViewById(2131296359));
    previousButton = ((Button)paramLayoutInflater.findViewById(2131296363));
    mButtonCancel = ((Button)paramLayoutInflater.findViewById(2131296364));
    date = ((TextView)paramLayoutInflater.findViewById(2131296872));
    player = new MediaPlayer();
    paramViewGroup = player;
    paramBundle = path;
    try
    {
      paramViewGroup.setDataSource(paramBundle);
      paramViewGroup = player;
      paramViewGroup.prepare();
    }
    catch (Exception paramViewGroup)
    {
      Toast.makeText(getActivity(), paramViewGroup.getMessage(), 1).show();
    }
    mView = ((SeekBar)paramLayoutInflater.findViewById(2131296722));
    mView.setClickable(false);
    nextButton.setEnabled(false);
    previousButton.setOnClickListener(new DownloadFragment.1(this));
    nextButton.setOnClickListener(new DashboardFragment.3(this));
    mButtonOk.setOnClickListener(new DashboardFragment.1(this));
    mButtonCancel.setOnClickListener(new DashboardFragment.2(this));
    return paramLayoutInflater;
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    super.onDismiss(paramDialogInterface);
    paramDialogInterface = player;
    if (paramDialogInterface != null) {
      paramDialogInterface.stop();
    }
  }
}
