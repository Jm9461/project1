package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelImpl
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelImpl> CREATOR = new a();
  private final Context mErrorMessage;
  
  protected ParcelImpl(Parcel paramParcel)
  {
    mErrorMessage = new i(paramParcel).f();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    new i(paramParcel).i(mErrorMessage);
  }
  
  static final class a
    implements Parcelable.Creator<ParcelImpl>
  {
    a() {}
    
    public ParcelImpl createFromParcel(Parcel paramParcel)
    {
      return new ParcelImpl(paramParcel);
    }
    
    public ParcelImpl[] newArray(int paramInt)
    {
      return new ParcelImpl[paramInt];
    }
  }
}
