package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState
  implements Parcelable
{
  public static final Parcelable.Creator<n> CREATOR = new DiscreteSeekBar.CustomState.1();
  FragmentState[] mActive;
  int[] mAdded;
  BackStackState[] mBackStack;
  int mFragmentId;
  int mIndex = -1;
  
  public FragmentManagerState() {}
  
  public FragmentManagerState(Parcel paramParcel)
  {
    mActive = ((FragmentState[])paramParcel.createTypedArray(FragmentState.CREATOR));
    mAdded = paramParcel.createIntArray();
    mBackStack = ((BackStackState[])paramParcel.createTypedArray(BackStackState.CREATOR));
    mIndex = paramParcel.readInt();
    mFragmentId = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeTypedArray(mActive, paramInt);
    paramParcel.writeIntArray(mAdded);
    paramParcel.writeTypedArray(mBackStack, paramInt);
    paramParcel.writeInt(mIndex);
    paramParcel.writeInt(mFragmentId);
  }
}
