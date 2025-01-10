package android.support.v4.app;

import android.arch.lifecycle.Attribute;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState
  implements Parcelable
{
  public static final Parcelable.Creator<o> CREATOR = new DownloadProgressInfo.1();
  final Bundle mArguments;
  final String mClassName;
  final int mContainerId;
  final boolean mDetached;
  final int mFragmentId;
  final boolean mFromLayout;
  final int mIndex;
  Fragment mInstance;
  final boolean mRetainInstance;
  Bundle mSavedFragmentState;
  final String mTag;
  final boolean mType;
  
  FragmentState(Parcel paramParcel)
  {
    mClassName = paramParcel.readString();
    mIndex = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool2 = true;
    boolean bool1;
    if (i != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mFromLayout = bool1;
    mFragmentId = paramParcel.readInt();
    mContainerId = paramParcel.readInt();
    mTag = paramParcel.readString();
    if (paramParcel.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mRetainInstance = bool1;
    if (paramParcel.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mDetached = bool1;
    mArguments = paramParcel.readBundle();
    if (paramParcel.readInt() != 0) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    mType = bool1;
    mSavedFragmentState = paramParcel.readBundle();
  }
  
  FragmentState(Fragment paramFragment)
  {
    mClassName = paramFragment.getClass().getName();
    mIndex = mIndex;
    mFromLayout = mFromLayout;
    mFragmentId = mFragmentId;
    mContainerId = i;
    mTag = mTag;
    mRetainInstance = mRetainInstance;
    mDetached = mDetached;
    mArguments = mArguments;
    mType = mHidden;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Fragment instantiate(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment, List paramList, Attribute paramAttribute)
  {
    if (mInstance == null)
    {
      Context localContext = paramFragmentHostCallback.getContext();
      Bundle localBundle = mArguments;
      if (localBundle != null) {
        localBundle.setClassLoader(localContext.getClassLoader());
      }
      if (paramFragmentContainer != null) {
        mInstance = paramFragmentContainer.instantiate(localContext, mClassName, mArguments);
      } else {
        mInstance = Fragment.instantiate(localContext, mClassName, mArguments);
      }
      paramFragmentContainer = mSavedFragmentState;
      if (paramFragmentContainer != null)
      {
        paramFragmentContainer.setClassLoader(localContext.getClassLoader());
        mInstance.mSavedFragmentState = mSavedFragmentState;
      }
      mInstance.setIndex(mIndex, paramFragment);
      paramFragmentContainer = mInstance;
      mFromLayout = mFromLayout;
      mRestored = true;
      mFragmentId = mFragmentId;
      i = mContainerId;
      mTag = mTag;
      mRetainInstance = mRetainInstance;
      mDetached = mDetached;
      mHidden = mType;
      mFragmentManager = mFragmentManager;
      if (FragmentManagerImpl.DEBUG)
      {
        paramFragmentHostCallback = new StringBuilder();
        paramFragmentHostCallback.append("Instantiated fragment ");
        paramFragmentHostCallback.append(mInstance);
        Log.v("FragmentManager", paramFragmentHostCallback.toString());
      }
    }
    paramFragmentHostCallback = mInstance;
    mActivity = paramList;
    DEBUG = paramAttribute;
    return paramFragmentHostCallback;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
}
