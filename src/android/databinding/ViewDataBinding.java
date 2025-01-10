package android.databinding;

import android.arch.lifecycle.MethodVisitor;
import android.arch.lifecycle.d;
import android.arch.lifecycle.f;
import android.arch.lifecycle.h;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import apps.core.network.action.R.id;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class ViewDataBinding
  extends MXParser
{
  static int SDK_INT = Build.VERSION.SDK_INT;
  private static final boolean USE_CHOREOGRAPHER;
  private h c;
  private boolean e;
  private Choreographer mChoreographer;
  private final Choreographer.FrameCallback mFrameCallback;
  private boolean mIsExecutingPendingBindings;
  private b<Object, ViewDataBinding, Void> mRebindCallbacks;
  private boolean mRebindHalted;
  private final Runnable mRebindRunnable;
  private Handler mUIThreadHandler;
  private ViewDataBinding this$0;
  
  static
  {
    "binding_".length();
    boolean bool;
    if (SDK_INT >= 16) {
      bool = true;
    } else {
      bool = false;
    }
    USE_CHOREOGRAPHER = bool;
    new a();
    new b();
    new c();
    new d();
    new e();
    new ReferenceQueue();
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    new f();
  }
  
  private void executePendingBindings()
  {
    if (mIsExecutingPendingBindings)
    {
      setTitle();
      return;
    }
    if (!hasPendingBindings()) {
      return;
    }
    mIsExecutingPendingBindings = true;
    mRebindHalted = false;
    CallbackRegistry localCallbackRegistry = mRebindCallbacks;
    if (localCallbackRegistry == null)
    {
      if (!mRebindHalted)
      {
        requestRebind();
        localCallbackRegistry = mRebindCallbacks;
        if (localCallbackRegistry != null)
        {
          localCallbackRegistry.add(this, 3, null);
          throw new NullPointerException("Null throw statement replaced by Soot");
        }
      }
      mIsExecutingPendingBindings = false;
      return;
    }
    localCallbackRegistry.add(this, 1, null);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  static ViewDataBinding getBinding(View paramView)
  {
    if (paramView != null) {
      return (ViewDataBinding)paramView.getTag(R.id.dataBinding);
    }
    return null;
  }
  
  public void getView()
  {
    ViewDataBinding localViewDataBinding = this$0;
    if (localViewDataBinding == null)
    {
      executePendingBindings();
      return;
    }
    localViewDataBinding.getView();
  }
  
  public abstract boolean hasPendingBindings();
  
  protected abstract void requestRebind();
  
  protected void setTitle()
  {
    Object localObject = this$0;
    if (localObject != null)
    {
      ((ViewDataBinding)localObject).setTitle();
      return;
    }
    localObject = c;
    if ((localObject != null) && (!((h)localObject).a().b().a(f.c))) {
      return;
    }
    try
    {
      if (e) {
        return;
      }
      e = true;
      if (USE_CHOREOGRAPHER)
      {
        mChoreographer.postFrameCallback(mFrameCallback);
        return;
      }
      mUIThreadHandler.post(mRebindRunnable);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  static class OnStartListener
    implements MethodVisitor
  {
    final WeakReference<ViewDataBinding> mMediaBrowser;
    
    public void onStart()
    {
      ViewDataBinding localViewDataBinding = (ViewDataBinding)mMediaBrowser.get();
      if (localViewDataBinding != null) {
        localViewDataBinding.getView();
      }
    }
  }
  
  static final class a
    implements ViewDataBinding.g
  {
    a() {}
  }
  
  static final class b
    implements ViewDataBinding.g
  {
    b() {}
  }
  
  static final class c
    implements ViewDataBinding.g
  {
    c() {}
  }
  
  static final class d
    implements ViewDataBinding.g
  {
    d() {}
  }
  
  static final class e
    extends b.a<Object, ViewDataBinding, Void>
  {
    e() {}
  }
  
  static final class f
    implements View.OnAttachStateChangeListener
  {
    f() {}
    
    public void onViewAttachedToWindow(View paramView)
    {
      ViewDataBinding.access$getMRebindRunnable(ViewDataBinding.getBinding(paramView)).run();
      paramView.removeOnAttachStateChangeListener(this);
    }
    
    public void onViewDetachedFromWindow(View paramView) {}
  }
  
  private static abstract interface g {}
}
