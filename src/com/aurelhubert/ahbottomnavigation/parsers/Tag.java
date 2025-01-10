package com.aurelhubert.ahbottomnavigation.parsers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.aurelhubert.ahbottomnavigation.i.a;
import java.util.ArrayList;
import java.util.List;

public class Tag
  implements Parcelable
{
  public static final Parcelable.Creator<a> CREATOR = new Point.1();
  private int count;
  private int min;
  private String value;
  
  public Tag() {}
  
  private Tag(Parcel paramParcel)
  {
    value = paramParcel.readString();
    count = paramParcel.readInt();
    min = paramParcel.readInt();
  }
  
  public static List getValue(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramInt)
    {
      localArrayList.add(new Tag());
      i += 1;
    }
    return localArrayList;
  }
  
  public int count()
  {
    return count;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getColor()
  {
    return value;
  }
  
  public int getValue()
  {
    return min;
  }
  
  public boolean isEmpty()
  {
    return TextUtils.isEmpty(value);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(value);
    paramParcel.writeInt(count);
    paramParcel.writeInt(min);
  }
}
