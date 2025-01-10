package com.lawyer_smartCalendar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import com.lawyer_smartCalendar.data.Frame;
import com.lawyer_smartCalendar.data.Label;
import com.lawyer_smartCalendar.data.b;
import com.lawyer_smartCalendar.data.d;
import com.lawyer_smartCalendar.data.f;
import com.lawyer_smartCalendar.data.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class h
  extends SQLiteOpenHelper
{
  public static String b = "sahv";
  public SQLiteDatabase a;
  private final Context c;
  public String s = "data/data/[package]/databases/";
  
  public h(Context paramContext)
  {
    super(paramContext, b, null, 1);
    c = paramContext;
    s = s.replace("[package]", c.getPackageName());
  }
  
  public com.lawyer_smartCalendar.data.h a(String paramString)
  {
    com.lawyer_smartCalendar.data.h localH = new com.lawyer_smartCalendar.data.h();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_case where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localH.setIcon(paramString.getInt(0));
      localH.a(paramString.getInt(1));
      localH.put(paramString.getString(2));
      localH.add(paramString.getString(3));
      localH.setTitle(paramString.getString(4));
      localH.set(paramString.getString(5));
      localH.a(paramString.getString(6));
      localH.e(paramString.getInt(7));
      localH.c(paramString.getString(8));
      localH.write(paramString.getString(9));
      localH.setIcon(paramString.getString(10));
      localH.d(paramString.getString(11));
      localH.e(paramString.getString(12));
      localH.format(paramString.getString(13));
      localH.append(paramString.getString(14));
    }
    return localH;
  }
  
  public java.util.List a()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_clientDocuments order by receivedDate asc", null);
    if (localCursor != null)
    {
      while (localCursor.moveToNext())
      {
        Label localLabel = new Label();
        localLabel.add(localCursor.getInt(0));
        localLabel.b(localCursor.getInt(1));
        localLabel.a(localCursor.getInt(2));
        localLabel.b(localCursor.getString(3));
        localLabel.put(localCursor.getString(4));
        localLabel.add(localCursor.getString(5));
        localLabel.a(localCursor.getString(6));
        localArrayList.add(localLabel);
      }
      localCursor.close();
    }
    return localArrayList;
  }
  
  public void a(Context paramContext, String paramString)
  {
    Object localObject = get(paramString);
    int i = 0;
    while (i < ((java.util.List)localObject).size())
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(((com.lawyer_smartCalendar.data.Log)((java.util.List)localObject).get(i)).d());
      localStringBuilder.append("");
      save(paramContext, localStringBuilder.toString());
      i += 1;
    }
    paramContext = next(paramString);
    i = 0;
    while (i < paramContext.size())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(((com.lawyer_smartCalendar.data.List)paramContext.get(i)).c());
      ((StringBuilder)localObject).append("");
      remove(((StringBuilder)localObject).toString(), c.c);
      i += 1;
    }
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("case_id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_note", ((StringBuilder)localObject).toString(), null);
    paramContext = read(paramString);
    i = 0;
    while (i < paramContext.size())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(((Label)paramContext.get(i)).b());
      ((StringBuilder)localObject).append("");
      remove(((StringBuilder)localObject).toString(), c.d);
      i += 1;
    }
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("case_id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_clientDocuments", ((StringBuilder)localObject).toString(), null);
    paramContext = getType(paramString);
    localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("payment_id =");
    localStringBuilder.append(paramContext.e());
    ((SQLiteDatabase)localObject).delete("tbl_paymentDetail", localStringBuilder.toString(), null);
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("case_id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_payment", ((StringBuilder)localObject).toString(), null);
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_case", ((StringBuilder)localObject).toString(), null);
  }
  
  public void a(Frame paramFrame, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("year", Integer.valueOf(paramFrame.d()));
    localContentValues.put("total", Long.valueOf(paramFrame.b()));
    localContentValues.put("incomeTax", Long.valueOf(paramFrame.get()));
    localContentValues.put("income", Long.valueOf(paramFrame.getValue()));
    localContentValues.put("note", paramFrame.f());
    paramFrame = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramFrame.update("tbl_tax", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void a(d paramD, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("case_id", Integer.valueOf(paramD.b()));
    localContentValues.put("date", paramD.getId());
    localContentValues.put("payType", paramD.getString());
    localContentValues.put("payFor", paramD.a());
    localContentValues.put("amount", paramD.getValue());
    localContentValues.put("detail", paramD.getTitle());
    paramD = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramD.update("tbl_payment", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void a(f paramF)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramF.getValue());
    localContentValues.put("fatherName", paramF.d());
    localContentValues.put("nationalCode", paramF.get());
    localContentValues.put("phoneNumber", paramF.getText());
    localContentValues.put("landlinePhone", paramF.a());
    localContentValues.put("emailAddress", paramF.e());
    localContentValues.put("address", paramF.getTitle());
    localContentValues.put("type", paramF.f());
    a.insert("tbl_client", null, localContentValues);
  }
  
  public void a(f paramF, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("name", paramF.getValue());
    localContentValues.put("fatherName", paramF.d());
    localContentValues.put("nationalCode", paramF.get());
    localContentValues.put("phoneNumber", paramF.getText());
    localContentValues.put("landlinePhone", paramF.a());
    localContentValues.put("emailAddress", paramF.e());
    localContentValues.put("address", paramF.getTitle());
    localContentValues.put("type", paramF.f());
    paramF = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramF.update("tbl_client", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void a(com.lawyer_smartCalendar.data.h paramH)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramH.c()));
    localContentValues.put("title", paramH.getString());
    localContentValues.put("date", paramH.getIcon());
    localContentValues.put("clientTitleType", paramH.getTitle());
    localContentValues.put("subject", paramH.getCount());
    localContentValues.put("courtType", paramH.getId());
    localContentValues.put("courtCityId", Integer.valueOf(paramH.b()));
    localContentValues.put("branchName", paramH.getName());
    localContentValues.put("caseNumber", paramH.d());
    localContentValues.put("archiveNumber", paramH.e());
    localContentValues.put("attorneyLetterNumber", paramH.getValue());
    localContentValues.put("lawsuitInfo", paramH.f());
    localContentValues.put("status", paramH.getText());
    localContentValues.put("caseStatus", paramH.i());
    a.insert("tbl_case", null, localContentValues);
  }
  
  public void a(com.lawyer_smartCalendar.data.h paramH, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramH.c()));
    localContentValues.put("title", paramH.getString());
    localContentValues.put("date", paramH.getIcon());
    localContentValues.put("clientTitleType", paramH.getTitle());
    localContentValues.put("subject", paramH.getCount());
    localContentValues.put("courtType", paramH.getId());
    localContentValues.put("courtCityId", Integer.valueOf(paramH.b()));
    localContentValues.put("branchName", paramH.getName());
    localContentValues.put("caseNumber", paramH.d());
    localContentValues.put("archiveNumber", paramH.e());
    localContentValues.put("attorneyLetterNumber", paramH.getValue());
    localContentValues.put("lawsuitInfo", paramH.f());
    localContentValues.put("status", paramH.getText());
    localContentValues.put("caseStatus", paramH.i());
    paramH = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramH.update("tbl_case", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void a(java.util.List paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("fk_id", Integer.valueOf(((com.lawyer_smartCalendar.data.Item)paramList.get(i)).b()));
      localContentValues.put("title", ((com.lawyer_smartCalendar.data.Item)paramList.get(i)).a());
      localContentValues.put("file_type", ((com.lawyer_smartCalendar.data.Item)paramList.get(i)).getName());
      localContentValues.put("file_address", ((com.lawyer_smartCalendar.data.Item)paramList.get(i)).getId());
      a.insert("tbl_file", null, localContentValues);
      i += 1;
    }
  }
  
  public void a(java.util.List paramList, String paramString)
  {
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("payment_id =");
    localStringBuilder.append(paramString);
    ((SQLiteDatabase)localObject).delete("tbl_paymentDetail", localStringBuilder.toString(), null);
    int i = 0;
    while (i < paramList.size())
    {
      localObject = new ContentValues();
      ((ContentValues)localObject).put("payment_id", paramString);
      ((ContentValues)localObject).put("date", ((b)paramList.get(i)).a());
      ((ContentValues)localObject).put("amount", ((b)paramList.get(i)).b());
      ((ContentValues)localObject).put("detail", ((b)paramList.get(i)).c());
      a.insert("tbl_paymentDetail", null, (ContentValues)localObject);
      i += 1;
    }
  }
  
  public long add(Label paramLabel, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramLabel.d()));
    localContentValues.put("case_id", Integer.valueOf(paramLabel.a()));
    localContentValues.put("documentType", paramLabel.getColor());
    localContentValues.put("receivedDate", paramLabel.getName());
    localContentValues.put("deliveredDate", paramLabel.getText());
    localContentValues.put("note", paramLabel.getValue());
    paramLabel = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    return paramLabel.update("tbl_clientDocuments", localContentValues, localStringBuilder.toString(), null);
  }
  
  public String[][] all()
  {
    Cursor localCursor = a.rawQuery("select *from tbl_link order by title asc", null);
    String[][] arrayOfString;
    if (localCursor != null)
    {
      int i = 0;
      arrayOfString = (String[][])Array.newInstance(String.class, new int[] { localCursor.getCount(), 3 });
      while (localCursor.moveToNext())
      {
        arrayOfString[i][0] = localCursor.getString(0);
        arrayOfString[i][1] = localCursor.getString(1);
        arrayOfString[i][2] = localCursor.getString(2);
        i += 1;
      }
    }
    return null;
    return arrayOfString;
  }
  
  public void b(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_clientDocuments", localStringBuilder.toString(), null);
  }
  
  public java.util.List c()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_cityBankAccountValue order by id asc", null);
    if (localCursor != null)
    {
      while (localCursor.moveToNext())
      {
        i localI = new i();
        localI.a(localCursor.getInt(0));
        localI.c(localCursor.getInt(1));
        localI.b(localCursor.getInt(2));
        localI.a(localCursor.getString(3));
        localArrayList.add(localI);
      }
      localCursor.close();
    }
    return localArrayList;
  }
  
  public void c(Context paramContext, String paramString)
  {
    Object localObject = initialize(Integer.parseInt(paramString));
    int i = 0;
    while (i < ((java.util.List)localObject).size())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(((com.lawyer_smartCalendar.data.h)((java.util.List)localObject).get(i)).a());
      localStringBuilder.append("");
      a(paramContext, localStringBuilder.toString());
      i += 1;
    }
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("client_id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_schedule", ((StringBuilder)localObject).toString(), null);
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_client", ((StringBuilder)localObject).toString(), null);
  }
  
  public void c(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_tax", localStringBuilder.toString(), null);
  }
  
  public void c(java.util.List paramList, String paramString)
  {
    int i = 0;
    while (i < paramList.size())
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("payment_id", paramString);
      localContentValues.put("date", ((b)paramList.get(i)).a());
      localContentValues.put("amount", ((b)paramList.get(i)).b());
      localContentValues.put("detail", ((b)paramList.get(i)).c());
      a.insert("tbl_paymentDetail", null, localContentValues);
      i += 1;
    }
  }
  
  public void close()
  {
    a.close();
  }
  
  public Frame copy(String paramString)
  {
    Frame localFrame = new Frame();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_tax where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localFrame.d(paramString.getInt(0));
      localFrame.set(paramString.getInt(1));
      localFrame.d(paramString.getInt(2));
      localFrame.get(paramString.getInt(3));
      localFrame.set(paramString.getInt(4));
      localFrame.d(paramString.getString(5));
    }
    return localFrame;
  }
  
  public void copyFile()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(s);
    ((StringBuilder)localObject).append(b);
    localObject = new FileOutputStream(((StringBuilder)localObject).toString());
    byte[] arrayOfByte = new byte['?'];
    InputStream localInputStream = c.getAssets().open(b);
    for (;;)
    {
      int i = localInputStream.read(arrayOfByte);
      if (i <= 0) {
        break;
      }
      ((OutputStream)localObject).write(arrayOfByte, 0, i);
    }
    localInputStream.close();
    ((OutputStream)localObject).flush();
    ((OutputStream)localObject).close();
    apps.authenticator.wizard.runtime.Log.write("installed", true);
  }
  
  public com.lawyer_smartCalendar.data.List create(String paramString)
  {
    com.lawyer_smartCalendar.data.List localList = new com.lawyer_smartCalendar.data.List();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_note where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localList.d(paramString.getInt(0));
      localList.append(paramString.getInt(1));
      localList.add(paramString.getInt(2));
      localList.e(paramString.getString(3));
      localList.append(paramString.getString(4));
      localList.add(paramString.getString(5));
      localList.d(paramString.getString(6));
    }
    return localList;
  }
  
  public void delete(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_note", localStringBuilder.toString(), null);
  }
  
  public java.util.List doInBackground()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_tax order by year asc", null);
    if (localCursor != null)
    {
      while (localCursor.moveToNext())
      {
        Frame localFrame = new Frame();
        localFrame.d(localCursor.getInt(0));
        localFrame.set(localCursor.getInt(1));
        localFrame.d(localCursor.getLong(2));
        localFrame.get(localCursor.getLong(3));
        localFrame.set(localCursor.getLong(4));
        localFrame.d(localCursor.getString(5));
        localArrayList.add(localFrame);
      }
      localCursor.close();
    }
    return localArrayList;
  }
  
  public java.util.List doInBackground(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_note where client_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.List();
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.List)localObject).e(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getString(6));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public void e(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_cityBankAccountValue", localStringBuilder.toString(), null);
  }
  
  public java.util.List export(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_note where title like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' or detail like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.List();
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.List)localObject).e(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getString(6));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public String[][] export()
  {
    Cursor localCursor = a.rawQuery("select id,title from tbl_accountNumber order by title asc", null);
    String[][] arrayOfString;
    if (localCursor != null)
    {
      int i = 0;
      arrayOfString = (String[][])Array.newInstance(String.class, new int[] { localCursor.getCount(), 2 });
      while (localCursor.moveToNext())
      {
        arrayOfString[i][0] = localCursor.getString(0);
        arrayOfString[i][1] = localCursor.getString(1);
        i += 1;
      }
    }
    return null;
    return arrayOfString;
  }
  
  public Label f(String paramString)
  {
    Label localLabel = new Label();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_clientDocuments where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localLabel.add(paramString.getInt(0));
      localLabel.b(paramString.getInt(1));
      localLabel.a(paramString.getInt(2));
      localLabel.b(paramString.getString(3));
      localLabel.put(paramString.getString(4));
      localLabel.add(paramString.getString(5));
      localLabel.a(paramString.getString(6));
    }
    return localLabel;
  }
  
  public boolean f()
  {
    if (apps.authenticator.wizard.runtime.Log.get("installed", false)) {
      return true;
    }
    Object localObject1 = null;
    try
    {
      Object localObject2 = new StringBuilder();
      String str = s;
      ((StringBuilder)localObject2).append(str);
      str = b;
      ((StringBuilder)localObject2).append(str);
      localObject2 = SQLiteDatabase.openDatabase(((StringBuilder)localObject2).toString(), null, 1);
      localObject1 = localObject2;
    }
    catch (SQLException localSQLException) {}
    return localObject1 != null;
  }
  
  public String get(int paramInt)
  {
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select name from tbl_client where id=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" limit 1");
    localObject = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if ((localObject != null) && (((Cursor)localObject).moveToFirst())) {
      return ((Cursor)localObject).getString(0);
    }
    return "";
  }
  
  public java.util.List get()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_schedule order by date asc", null);
    if (localCursor != null)
    {
      while (localCursor.moveToNext())
      {
        com.lawyer_smartCalendar.data.Log localLog = new com.lawyer_smartCalendar.data.Log();
        localLog.init(localCursor.getInt(0));
        localLog.append(localCursor.getInt(1));
        localLog.set(localCursor.getInt(2));
        localLog.close(localCursor.getString(3));
        localLog.start(localCursor.getString(4));
        localLog.init(localCursor.getString(5));
        localLog.add(localCursor.getString(6));
        localLog.set(localCursor.getString(7));
        localArrayList.add(localLog);
      }
      localCursor.close();
    }
    return localArrayList;
  }
  
  public java.util.List get(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_schedule where case_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.Log();
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.Log)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.Log)localObject).close(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.Log)localObject).start(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.Log)localObject).add(paramString.getString(6));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getString(7));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public String getCount(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select title from tbl_accountNumber where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst())) {
      return paramString.getString(0);
    }
    return "";
  }
  
  public com.lawyer_smartCalendar.data.Log getInstance(String paramString)
  {
    com.lawyer_smartCalendar.data.Log localLog = new com.lawyer_smartCalendar.data.Log();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_schedule where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localLog.init(paramString.getInt(0));
      localLog.append(paramString.getInt(1));
      localLog.set(paramString.getInt(2));
      localLog.close(paramString.getString(3));
      localLog.start(paramString.getString(4));
      localLog.init(paramString.getString(5));
      localLog.add(paramString.getString(6));
      localLog.set(paramString.getString(7));
    }
    return localLog;
  }
  
  public d getItem(String paramString)
  {
    d localD = new d();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_payment where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localD.a(paramString.getInt(0));
      localD.d(paramString.getInt(1));
      localD.i(paramString.getString(2));
      localD.append(paramString.getString(3));
      localD.e(paramString.getString(4));
      localD.c(paramString.getString(5));
      localD.d(paramString.getString(6));
    }
    return localD;
  }
  
  public void getItem()
  {
    if (Boolean.valueOf(f()).booleanValue()) {
      return;
    }
    getReadableDatabase();
    try
    {
      copyFile();
      return;
    }
    catch (IOException localIOException) {}
  }
  
  public void getItem(Frame paramFrame)
  {
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select id from tbl_tax where year=");
    localStringBuilder.append(paramFrame.d());
    localObject = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (((Cursor)localObject).getCount() > 0)
    {
      ((Cursor)localObject).moveToFirst();
      a(paramFrame, ((Cursor)localObject).getString(0));
      ((Cursor)localObject).close();
      return;
    }
    localObject = new ContentValues();
    ((ContentValues)localObject).put("year", Integer.valueOf(paramFrame.d()));
    ((ContentValues)localObject).put("total", Long.valueOf(paramFrame.b()));
    ((ContentValues)localObject).put("incomeTax", Long.valueOf(paramFrame.get()));
    ((ContentValues)localObject).put("income", Long.valueOf(paramFrame.getValue()));
    ((ContentValues)localObject).put("note", paramFrame.f());
    a.insert("tbl_tax", null, (ContentValues)localObject);
  }
  
  public d getType(String paramString)
  {
    d localD = new d();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_payment where case_id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localD.a(paramString.getInt(0));
      localD.d(paramString.getInt(1));
      localD.i(paramString.getString(2));
      localD.append(paramString.getString(3));
      localD.e(paramString.getString(4));
      localD.c(paramString.getString(5));
      localD.d(paramString.getString(6));
    }
    return localD;
  }
  
  public String[][] getValues()
  {
    Cursor localCursor = a.rawQuery("select id,city from tbl_city order by city asc", null);
    String[][] arrayOfString;
    if (localCursor != null)
    {
      int i = 0;
      arrayOfString = (String[][])Array.newInstance(String.class, new int[] { localCursor.getCount(), 2 });
      while (localCursor.moveToNext())
      {
        arrayOfString[i][0] = localCursor.getString(0);
        arrayOfString[i][1] = localCursor.getString(1);
        i += 1;
      }
    }
    return null;
    return arrayOfString;
  }
  
  public java.util.List initialize()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_case order by date desc", null);
    if (localCursor != null) {
      while (localCursor.moveToNext())
      {
        com.lawyer_smartCalendar.data.h localH = new com.lawyer_smartCalendar.data.h();
        localH.setIcon(localCursor.getInt(0));
        localH.a(localCursor.getInt(1));
        localH.put(localCursor.getString(2));
        localH.add(localCursor.getString(3));
        localH.setTitle(localCursor.getString(4));
        localH.set(localCursor.getString(5));
        localH.a(localCursor.getString(6));
        localH.e(localCursor.getInt(7));
        localH.c(localCursor.getString(8));
        localH.write(localCursor.getString(9));
        localH.setIcon(localCursor.getString(10));
        localH.d(localCursor.getString(11));
        localH.e(localCursor.getString(12));
        localH.format(localCursor.getString(13));
        localH.append(localCursor.getString(14));
        localArrayList.add(localH);
      }
    }
    return localArrayList;
  }
  
  public java.util.List initialize(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("select *from tbl_case where client_id=");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" order by date desc");
    localObject1 = ((SQLiteDatabase)localObject1).rawQuery(((StringBuilder)localObject2).toString(), null);
    if (localObject1 != null) {
      while (((Cursor)localObject1).moveToNext())
      {
        localObject2 = new com.lawyer_smartCalendar.data.h();
        ((com.lawyer_smartCalendar.data.h)localObject2).setIcon(((Cursor)localObject1).getInt(0));
        ((com.lawyer_smartCalendar.data.h)localObject2).a(((Cursor)localObject1).getInt(1));
        ((com.lawyer_smartCalendar.data.h)localObject2).put(((Cursor)localObject1).getString(2));
        ((com.lawyer_smartCalendar.data.h)localObject2).add(((Cursor)localObject1).getString(3));
        ((com.lawyer_smartCalendar.data.h)localObject2).setTitle(((Cursor)localObject1).getString(4));
        ((com.lawyer_smartCalendar.data.h)localObject2).set(((Cursor)localObject1).getString(5));
        ((com.lawyer_smartCalendar.data.h)localObject2).a(((Cursor)localObject1).getString(6));
        ((com.lawyer_smartCalendar.data.h)localObject2).e(((Cursor)localObject1).getInt(7));
        ((com.lawyer_smartCalendar.data.h)localObject2).c(((Cursor)localObject1).getString(8));
        ((com.lawyer_smartCalendar.data.h)localObject2).write(((Cursor)localObject1).getString(9));
        ((com.lawyer_smartCalendar.data.h)localObject2).setIcon(((Cursor)localObject1).getString(10));
        ((com.lawyer_smartCalendar.data.h)localObject2).d(((Cursor)localObject1).getString(11));
        ((com.lawyer_smartCalendar.data.h)localObject2).e(((Cursor)localObject1).getString(12));
        ((com.lawyer_smartCalendar.data.h)localObject2).format(((Cursor)localObject1).getString(13));
        ((com.lawyer_smartCalendar.data.h)localObject2).append(((Cursor)localObject1).getString(14));
        localArrayList.add(localObject2);
      }
    }
    return localArrayList;
  }
  
  public java.util.List initialize(long paramLong1, long paramLong2)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("select *from tbl_schedule where  CAST(date as long)>=");
    ((StringBuilder)localObject2).append(paramLong1);
    ((StringBuilder)localObject2).append(" and CAST(date as long)<=");
    ((StringBuilder)localObject2).append(paramLong2);
    ((StringBuilder)localObject2).append(" order by date asc");
    localObject1 = ((SQLiteDatabase)localObject1).rawQuery(((StringBuilder)localObject2).toString(), null);
    if (localObject1 != null)
    {
      while (((Cursor)localObject1).moveToNext())
      {
        localObject2 = new com.lawyer_smartCalendar.data.Log();
        ((com.lawyer_smartCalendar.data.Log)localObject2).init(((Cursor)localObject1).getInt(0));
        ((com.lawyer_smartCalendar.data.Log)localObject2).append(((Cursor)localObject1).getInt(1));
        ((com.lawyer_smartCalendar.data.Log)localObject2).set(((Cursor)localObject1).getInt(2));
        ((com.lawyer_smartCalendar.data.Log)localObject2).close(((Cursor)localObject1).getString(3));
        ((com.lawyer_smartCalendar.data.Log)localObject2).start(((Cursor)localObject1).getString(4));
        ((com.lawyer_smartCalendar.data.Log)localObject2).init(((Cursor)localObject1).getString(5));
        ((com.lawyer_smartCalendar.data.Log)localObject2).add(((Cursor)localObject1).getString(6));
        ((com.lawyer_smartCalendar.data.Log)localObject2).set(((Cursor)localObject1).getString(7));
        localArrayList.add(localObject2);
      }
      ((Cursor)localObject1).close();
    }
    return localArrayList;
  }
  
  public java.util.List initialize(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_case where lawsuitInfo like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' or subject like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' order by date desc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null) {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.h();
        ((com.lawyer_smartCalendar.data.h)localObject).setIcon(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.h)localObject).a(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.h)localObject).put(paramString.getString(2));
        ((com.lawyer_smartCalendar.data.h)localObject).add(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.h)localObject).setTitle(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.h)localObject).set(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.h)localObject).a(paramString.getString(6));
        ((com.lawyer_smartCalendar.data.h)localObject).e(paramString.getInt(7));
        ((com.lawyer_smartCalendar.data.h)localObject).c(paramString.getString(8));
        ((com.lawyer_smartCalendar.data.h)localObject).write(paramString.getString(9));
        ((com.lawyer_smartCalendar.data.h)localObject).setIcon(paramString.getString(10));
        ((com.lawyer_smartCalendar.data.h)localObject).d(paramString.getString(11));
        ((com.lawyer_smartCalendar.data.h)localObject).e(paramString.getString(12));
        ((com.lawyer_smartCalendar.data.h)localObject).format(paramString.getString(13));
        ((com.lawyer_smartCalendar.data.h)localObject).append(paramString.getString(14));
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public boolean isEnabled(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_payment where case_id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    return (paramString != null) && (paramString.getCount() > 0);
  }
  
  public java.util.List load(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_paymentDetail where payment_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new b();
        ((b)localObject).setLow(paramString.getInt(0));
        ((b)localObject).close(paramString.getInt(1));
        ((b)localObject).add(paramString.getString(2));
        ((b)localObject).e(paramString.getString(3));
        ((b)localObject).d(paramString.getString(4));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public java.util.List match(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_note where note_type='");
    localStringBuilder.append(paramString);
    localStringBuilder.append("' order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.List();
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.List)localObject).e(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getString(6));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public java.util.List merge(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_schedule where subject like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' order by date asc ");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.Log();
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.Log)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.Log)localObject).close(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.Log)localObject).start(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.Log)localObject).add(paramString.getString(6));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getString(7));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public java.util.List next()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_payment order by date asc", null);
    if (localCursor != null)
    {
      while (localCursor.moveToNext())
      {
        d localD = new d();
        localD.a(localCursor.getInt(0));
        localD.d(localCursor.getInt(1));
        localD.i(localCursor.getString(2));
        localD.append(localCursor.getString(3));
        localD.e(localCursor.getString(4));
        localD.c(localCursor.getString(5));
        localD.d(localCursor.getString(6));
        localArrayList.add(localD);
      }
      localCursor.close();
    }
    return localArrayList;
  }
  
  public java.util.List next(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_note where case_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.List();
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.List)localObject).e(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.List)localObject).append(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.List)localObject).add(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.List)localObject).d(paramString.getString(6));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase) {}
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public java.util.List read(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_clientDocuments where case_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by receivedDate asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new Label();
        ((Label)localObject).add(paramString.getInt(0));
        ((Label)localObject).b(paramString.getInt(1));
        ((Label)localObject).a(paramString.getInt(2));
        ((Label)localObject).b(paramString.getString(3));
        ((Label)localObject).put(paramString.getString(4));
        ((Label)localObject).add(paramString.getString(5));
        ((Label)localObject).a(paramString.getString(6));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public String remove(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select city from tbl_city where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst())) {
      return paramString.getString(0);
    }
    return "";
  }
  
  public void remove(String paramString1, String paramString2)
  {
    paramString2 = search(paramString1, paramString2);
    int i = 0;
    while (i < paramString2.size())
    {
      localObject = new File(Uri.decode(((com.lawyer_smartCalendar.data.Item)paramString2.get(i)).getId()));
      if (((File)localObject).exists()) {
        ((File)localObject).delete();
      }
      i += 1;
    }
    paramString2 = a;
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("fk_id =");
    ((StringBuilder)localObject).append(paramString1);
    paramString2.delete("tbl_file", ((StringBuilder)localObject).toString(), null);
  }
  
  public long save(com.lawyer_smartCalendar.data.Log paramLog)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramLog.getText()));
    localContentValues.put("case_id", Integer.valueOf(paramLog.read()));
    localContentValues.put("scheduleType", paramLog.get());
    localContentValues.put("subject", paramLog.getID());
    localContentValues.put("date", paramLog.getString());
    localContentValues.put("result", paramLog.getValue());
    localContentValues.put("alarmDate", paramLog.getId());
    return a.insert("tbl_schedule", null, localContentValues);
  }
  
  public void save(Context paramContext, String paramString)
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeInMillis(Long.parseLong(getInstance(paramString).getId()));
    new Item(paramContext, new Intent(paramContext, OnAlarmReceiver.class), ((Calendar)localObject).getTimeInMillis(), Integer.parseInt(paramString)).a();
    NotificationHelper.cancelNotification(paramContext, Integer.parseInt(paramString));
    paramContext = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("id =");
    ((StringBuilder)localObject).append(paramString);
    paramContext.delete("tbl_schedule", ((StringBuilder)localObject).toString(), null);
  }
  
  public java.util.List search()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = a.rawQuery("select *from tbl_client order by name asc", null);
    if (localCursor != null) {
      while (localCursor.moveToNext())
      {
        f localF = new f();
        localF.clear(localCursor.getInt(0));
        localF.e(localCursor.getString(1));
        localF.a(localCursor.getString(2));
        localF.add(localCursor.getString(3));
        localF.set(localCursor.getString(4));
        localF.i(localCursor.getString(5));
        localF.update(localCursor.getString(6));
        localF.setTitle(localCursor.getString(7));
        localF.append(localCursor.getString(8));
        localArrayList.add(localF);
      }
    }
    return localArrayList;
  }
  
  public java.util.List search(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_client where name like'%");
    localStringBuilder.append(paramString);
    localStringBuilder.append("%' order by name asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null) {
      while (paramString.moveToNext())
      {
        localObject = new f();
        ((f)localObject).clear(paramString.getInt(0));
        ((f)localObject).e(paramString.getString(1));
        ((f)localObject).a(paramString.getString(2));
        ((f)localObject).add(paramString.getString(3));
        ((f)localObject).set(paramString.getString(4));
        ((f)localObject).i(paramString.getString(5));
        ((f)localObject).update(paramString.getString(6));
        ((f)localObject).setTitle(paramString.getString(7));
        ((f)localObject).append(paramString.getString(8));
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public java.util.List search(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_file where fk_id=");
    localStringBuilder.append(paramString1);
    localStringBuilder.append(" and file_type='");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("'");
    paramString1 = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if (paramString1 != null)
    {
      while (paramString1.moveToNext())
      {
        paramString2 = new com.lawyer_smartCalendar.data.Item();
        paramString2.setTitle(paramString1.getInt(0));
        paramString2.set(paramString1.getInt(1));
        paramString2.a(paramString1.getString(2));
        paramString2.setTitle(paramString1.getString(3));
        paramString2.set(paramString1.getString(4));
        localArrayList.add(paramString2);
      }
      paramString1.close();
    }
    return localArrayList;
  }
  
  public f set(String paramString)
  {
    f localF = new f();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_client where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localF.clear(paramString.getInt(0));
      localF.e(paramString.getString(1));
      localF.a(paramString.getString(2));
      localF.add(paramString.getString(3));
      localF.set(paramString.getString(4));
      localF.i(paramString.getString(5));
      localF.update(paramString.getString(6));
      localF.setTitle(paramString.getString(7));
      localF.append(paramString.getString(8));
    }
    return localF;
  }
  
  public void setEnabled()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(s);
    localStringBuilder.append(b);
    a = SQLiteDatabase.openDatabase(localStringBuilder.toString(), null, 0);
  }
  
  public void setEnabled(i paramI)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("city_id", Integer.valueOf(paramI.size()));
    localContentValues.put("accountNumber_id", Integer.valueOf(paramI.a()));
    localContentValues.put("number", paramI.get());
    a.insert("tbl_cityBankAccountValue", null, localContentValues);
  }
  
  public void setEnabled(i paramI, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("city_id", Integer.valueOf(paramI.size()));
    localContentValues.put("accountNumber_id", Integer.valueOf(paramI.a()));
    localContentValues.put("number", paramI.get());
    paramI = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramI.update("tbl_cityBankAccountValue", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void setIcon(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("file_address ='");
    localStringBuilder.append(paramString);
    localStringBuilder.append("'");
    localSQLiteDatabase.delete("tbl_file", localStringBuilder.toString(), null);
  }
  
  public i setTitle(String paramString)
  {
    i localI = new i();
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_cityBankAccountValue where id=");
    localStringBuilder.append(paramString);
    paramString = localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
    if ((paramString != null) && (paramString.moveToFirst()))
    {
      localI.a(paramString.getInt(0));
      localI.c(paramString.getInt(1));
      localI.b(paramString.getInt(2));
      localI.a(paramString.getString(3));
    }
    return localI;
  }
  
  public java.util.List start(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("select *from tbl_schedule where client_id=");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" order by date asc");
    paramString = ((SQLiteDatabase)localObject).rawQuery(localStringBuilder.toString(), null);
    if (paramString != null)
    {
      while (paramString.moveToNext())
      {
        localObject = new com.lawyer_smartCalendar.data.Log();
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getInt(0));
        ((com.lawyer_smartCalendar.data.Log)localObject).append(paramString.getInt(1));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getInt(2));
        ((com.lawyer_smartCalendar.data.Log)localObject).close(paramString.getString(3));
        ((com.lawyer_smartCalendar.data.Log)localObject).start(paramString.getString(4));
        ((com.lawyer_smartCalendar.data.Log)localObject).init(paramString.getString(5));
        ((com.lawyer_smartCalendar.data.Log)localObject).add(paramString.getString(6));
        ((com.lawyer_smartCalendar.data.Log)localObject).set(paramString.getString(7));
        localArrayList.add(localObject);
      }
      paramString.close();
    }
    return localArrayList;
  }
  
  public void toString(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("payment_id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_paymentDetail", localStringBuilder.toString(), null);
    localSQLiteDatabase = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    localSQLiteDatabase.delete("tbl_payment", localStringBuilder.toString(), null);
  }
  
  public long write(Label paramLabel)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramLabel.d()));
    localContentValues.put("case_id", Integer.valueOf(paramLabel.a()));
    localContentValues.put("documentType", paramLabel.getColor());
    localContentValues.put("receivedDate", paramLabel.getName());
    localContentValues.put("deliveredDate", paramLabel.getText());
    localContentValues.put("note", paramLabel.getValue());
    return a.insert("tbl_clientDocuments", null, localContentValues);
  }
  
  public long write(com.lawyer_smartCalendar.data.List paramList)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramList.size()));
    localContentValues.put("case_id", Integer.valueOf(paramList.a()));
    localContentValues.put("title", paramList.get());
    localContentValues.put("note_type", paramList.getString());
    localContentValues.put("date", paramList.getId());
    localContentValues.put("detail", paramList.getValue());
    return a.insert("tbl_note", null, localContentValues);
  }
  
  public long write(d paramD)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("case_id", Integer.valueOf(paramD.b()));
    localContentValues.put("date", paramD.getId());
    localContentValues.put("payType", paramD.getString());
    localContentValues.put("payFor", paramD.a());
    localContentValues.put("amount", paramD.getValue());
    localContentValues.put("detail", paramD.getTitle());
    return a.insert("tbl_payment", null, localContentValues);
  }
  
  public void write(com.lawyer_smartCalendar.data.List paramList, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramList.size()));
    localContentValues.put("case_id", Integer.valueOf(paramList.a()));
    localContentValues.put("title", paramList.get());
    localContentValues.put("note_type", paramList.getString());
    localContentValues.put("date", paramList.getId());
    localContentValues.put("detail", paramList.getValue());
    paramList = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramList.update("tbl_note", localContentValues, localStringBuilder.toString(), null);
  }
  
  public void write(com.lawyer_smartCalendar.data.Log paramLog, String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("client_id", Integer.valueOf(paramLog.getText()));
    localContentValues.put("case_id", Integer.valueOf(paramLog.read()));
    localContentValues.put("scheduleType", paramLog.get());
    localContentValues.put("subject", paramLog.getID());
    localContentValues.put("date", paramLog.getString());
    localContentValues.put("result", paramLog.getValue());
    localContentValues.put("alarmDate", paramLog.getId());
    paramLog = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id =");
    localStringBuilder.append(paramString);
    paramLog.update("tbl_schedule", localContentValues, localStringBuilder.toString(), null);
  }
}
