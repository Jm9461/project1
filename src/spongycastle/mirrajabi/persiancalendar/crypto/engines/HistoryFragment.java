package spongycastle.mirrajabi.persiancalendar.crypto.engines;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ir.mirrajabi.persiancalendar.f.e.a;
import spongycastle.mirrajabi.persiancalendar.R.id;
import spongycastle.mirrajabi.persiancalendar.R.layout;
import spongycastle.mirrajabi.persiancalendar.crypto.f;

public class HistoryFragment
  extends Fragment
{
  private spongycastle.mirrajabi.persiancalendar.crypto.asm.Object a;
  private spongycastle.mirrajabi.persiancalendar.crypto.ec.h adapter;
  private f c;
  private TitlePageIndicator emptyView;
  private int l;
  private BroadcastReceiver mReceiver = new WalletTransactionsFragment.TransactionsLoader.2(this);
  
  public HistoryFragment() {}
  
  public void a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    if (c.r() != null) {
      c.r().a(paramObject);
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    c = f.a(getContext());
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.fragment_month, paramViewGroup, false);
    l = getArguments().getInt("OFFSET_ARGUMENT");
    paramViewGroup = c.a(l);
    a = c.a();
    int i = a.a() - l - 1;
    int k = a.get() + i / 12;
    int m = i % 12;
    int j = m;
    i = k;
    if (m < 0)
    {
      i = k - 1;
      j = m + 12;
    }
    a.a(j + 1);
    a.setId(i);
    a.add(1);
    paramBundle = (RecyclerView)paramLayoutInflater.findViewById(R.id.month_recycler);
    paramBundle.setHasFixedSize(true);
    paramBundle.setLayoutManager(new GridLayoutManager(getContext(), 7));
    adapter = new spongycastle.mirrajabi.persiancalendar.crypto.ec.h(getContext(), this, paramViewGroup);
    paramBundle.setAdapter(adapter);
    emptyView = ((TitlePageIndicator)getActivity().getSupportFragmentManager().findFragmentByTag(a.class.getName()));
    if (l == 0) {
      emptyView.setTextColor();
    }
    LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, new IntentFilter("BROADCAST_INTENT_TO_MONTH_FRAGMENT"));
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
    super.onDestroy();
  }
  
  public void remove(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    if (c.add() != null) {
      c.add().set(paramObject);
    }
  }
}
