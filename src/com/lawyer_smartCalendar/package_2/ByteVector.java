package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.u.a;
import com.lawyer_smartCalendar.data.b;
import com.lawyer_smartCalendar.utils.d;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.text.DecimalFormat;
import java.util.List;

public class ByteVector
  extends RecyclerView.g<u.a>
{
  private Context a;
  DecimalFormat buffer;
  private List<com.lawyer_smartCalendar.d.h> state;
  
  public ByteVector(Context paramContext, List paramList)
  {
    a = paramContext;
    state = paramList;
    new com.lawyer_smartCalendar.utils.h(paramContext);
    buffer = new DecimalFormat("#,###,###,###,###,###,###");
  }
  
  public int a()
  {
    return state.size();
  }
  
  public Media a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Media(this, LayoutInflater.from(a).inflate(2131492979, paramViewGroup, false));
  }
  
  public void onCreateView(Media paramMedia, int paramInt)
  {
    Object localObject = new Calendar();
    ((Calendar)localObject).setTimeInMillis(Long.parseLong(((b)state.get(paramInt)).a()));
    TextView localTextView = state;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("????? : ");
    localStringBuilder.append(((Calendar)localObject).format());
    localTextView.setText(localStringBuilder.toString());
    state.setTextColor(d.a());
    localObject = buffer.format(Long.parseLong(((b)state.get(paramInt)).b()));
    localTextView = path;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("???? : ");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" ?????");
    localTextView.setText(localStringBuilder.toString());
    paramMedia = size;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("????? ??/???? : ");
    ((StringBuilder)localObject).append(((b)state.get(paramInt)).c());
    paramMedia.setText(((StringBuilder)localObject).toString());
  }
}
