package apps.apps.ui;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import apps.apps.ui.views.Item;

public class h
{
  private ViewGroup a;
  private int b = -1;
  private int e = -1;
  private int f = -1;
  private String g;
  private int h = -1;
  private int i = -1;
  private int j = -1;
  private Item k;
  private int p = -1;
  private ViewGroup.LayoutParams t;
  
  public h() {}
  
  private void setIcon(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("You must provide ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" in order to draw the view properly.");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public MethodWriter a()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a19 = a18\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public h a(int paramInt)
  {
    h = paramInt;
    return this;
  }
  
  public h a(int paramInt1, int paramInt2)
  {
    f = paramInt1;
    p = paramInt2;
    return this;
  }
  
  public h a(ViewGroup.LayoutParams paramLayoutParams)
  {
    t = paramLayoutParams;
    return this;
  }
  
  public h a(ViewGroup paramViewGroup)
  {
    a = paramViewGroup;
    return this;
  }
  
  public h a(Item paramItem)
  {
    k = paramItem;
    return this;
  }
  
  public h a(String paramString)
  {
    g = paramString;
    return this;
  }
  
  public h b(int paramInt)
  {
    j = paramInt;
    return this;
  }
  
  public h c(int paramInt)
  {
    i = paramInt;
    return this;
  }
  
  public h e(int paramInt)
  {
    b = paramInt;
    return this;
  }
  
  public h setTitle(int paramInt)
  {
    e = paramInt;
    return this;
  }
}
