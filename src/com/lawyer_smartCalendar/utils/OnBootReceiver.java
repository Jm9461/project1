package com.lawyer_smartCalendar.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.lawyer_smartCalendar.data.Log;
import java.util.List;

public class OnBootReceiver
  extends BroadcastReceiver
{
  private h c;
  
  public OnBootReceiver() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    c = new h(paramContext);
    paramIntent = java.util.Calendar.getInstance();
    c.setEnabled();
    List localList = c.get();
    c.close();
    if (localList.size() > 0)
    {
      int i = 0;
      while (i < localList.size())
      {
        if (Long.parseLong(((Log)localList.get(i)).getId()) >= paramIntent.getTimeInMillis())
        {
          Object localObject1 = new com.mohamadamin.persianmaterialdatetimepicker.views.Calendar();
          Object localObject2 = java.util.Calendar.getInstance();
          ((java.util.Calendar)localObject2).setTimeInMillis(Long.parseLong(((Log)localList.get(i)).getString()));
          ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).setTimeInMillis(Long.parseLong(((Log)localList.get(i)).getString()));
          localObject1 = ((com.mohamadamin.persianmaterialdatetimepicker.views.Calendar)localObject1).convert();
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(((java.util.Calendar)localObject2).get(12));
          ((StringBuilder)localObject3).append(" : ");
          ((StringBuilder)localObject3).append(((java.util.Calendar)localObject2).get(11));
          localObject2 = ((StringBuilder)localObject3).toString();
          localObject3 = new Intent(paramContext, OnAlarmReceiver.class);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(((Log)localList.get(i)).d());
          localStringBuilder.append("");
          ((Intent)localObject3).putExtra("id", localStringBuilder.toString());
          ((Intent)localObject3).putExtra("title", ((Log)localList.get(i)).getID());
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("????? : ");
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append("\n???? : ");
          localStringBuilder.append((String)localObject2);
          ((Intent)localObject3).putExtra("message", localStringBuilder.toString());
          new Item(paramContext, (Intent)localObject3, Long.parseLong(((Log)localList.get(i)).getId()), ((Log)localList.get(i)).d()).set();
        }
        i += 1;
      }
    }
  }
}
