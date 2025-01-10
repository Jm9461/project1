package android.support.v7.widget;

import android.view.View;

class d
{
  static int a(RecyclerView.a0 paramA0, MethodWriter paramMethodWriter, View paramView1, View paramView2, RecyclerView.o paramO, boolean paramBoolean)
  {
    if ((paramO.getChildCount() != 0) && (paramA0.size() != 0) && (paramView1 != null) && (paramView2 != null))
    {
      if (!paramBoolean) {
        return Math.abs(paramO.a(paramView1) - paramO.a(paramView2)) + 1;
      }
      int i = paramMethodWriter.a(paramView2);
      int j = paramMethodWriter.f(paramView1);
      return Math.min(paramMethodWriter.get(), i - j);
    }
    return 0;
  }
  
  static int a(RecyclerView.a0 paramA0, MethodWriter paramMethodWriter, View paramView1, View paramView2, RecyclerView.o paramO, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramO.getChildCount() != 0) && (paramA0.size() != 0) && (paramView1 != null))
    {
      if (paramView2 == null) {
        return 0;
      }
      int i = Math.min(paramO.a(paramView1), paramO.a(paramView2));
      int j = Math.max(paramO.a(paramView1), paramO.a(paramView2));
      if (paramBoolean2) {
        i = Math.max(0, paramA0.size() - j - 1);
      } else {
        i = Math.max(0, i);
      }
      if (!paramBoolean1) {
        return i;
      }
      j = Math.abs(paramMethodWriter.a(paramView2) - paramMethodWriter.f(paramView1));
      int k = Math.abs(paramO.a(paramView1) - paramO.a(paramView2));
      float f = j / (k + 1);
      return Math.round(i * f + (paramMethodWriter.f() - paramMethodWriter.f(paramView1)));
    }
    return 0;
  }
  
  static int b(RecyclerView.a0 paramA0, MethodWriter paramMethodWriter, View paramView1, View paramView2, RecyclerView.o paramO, boolean paramBoolean)
  {
    if ((paramO.getChildCount() != 0) && (paramA0.size() != 0) && (paramView1 != null) && (paramView2 != null))
    {
      if (!paramBoolean) {
        return paramA0.size();
      }
      int i = paramMethodWriter.a(paramView2);
      int j = paramMethodWriter.f(paramView1);
      int k = Math.abs(paramO.a(paramView1) - paramO.a(paramView2));
      return (int)((i - j) / (k + 1) * paramA0.size());
    }
    return 0;
  }
}
