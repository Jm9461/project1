package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public abstract class Artist
  implements Parcelable
{
  public static final Parcelable.Creator<a> CREATOR = new VerticalProgressBar.SavedState.1();
  public static final Artist EMPTY_STATE = new JavacResolver.1();
  private final Parcelable mSuperState;
  
  private Artist()
  {
    mSuperState = null;
  }
  
  protected Artist(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    paramParcel = paramParcel.readParcelable(paramClassLoader);
    if (paramParcel == null) {
      paramParcel = EMPTY_STATE;
    }
    mSuperState = paramParcel;
  }
  
  protected Artist(Parcelable paramParcelable)
  {
    if (paramParcelable != null)
    {
      if (paramParcelable == EMPTY_STATE) {
        paramParcelable = null;
      }
      mSuperState = paramParcelable;
      return;
    }
    throw new IllegalArgumentException("superState must not be null");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final Parcelable getSuperState()
  {
    return mSuperState;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(mSuperState, paramInt);
  }
}
