package com.afollestad.materialdialogs.internal;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import apps.afollestad.materialdialogs.GravityEnum;

public class ThemeSingleton
{
  private static ThemeSingleton singleton;
  public int backgroundColor = 0;
  public int btnSelectorNegative = 0;
  public int btnSelectorNeutral = 0;
  public int btnSelectorPositive = 0;
  public int btnSelectorStacked = 0;
  public GravityEnum btnStackedGravity;
  public GravityEnum buttonsGravity;
  public int contentColor = 0;
  public GravityEnum contentGravity;
  public boolean darkTheme = false;
  public int dividerColor = 0;
  public Drawable icon = null;
  public int itemColor = 0;
  public GravityEnum itemsGravity;
  public ColorStateList linkColor = null;
  public int listSelector = 0;
  public ColorStateList negativeColor = null;
  public ColorStateList neutralColor = null;
  public ColorStateList positiveColor = null;
  public int titleColor = 0;
  public GravityEnum titleGravity;
  public int widgetColor = 0;
  
  public ThemeSingleton()
  {
    GravityEnum localGravityEnum = GravityEnum.START;
    btnStackedGravity = localGravityEnum;
    itemsGravity = localGravityEnum;
    buttonsGravity = GravityEnum.END;
    localGravityEnum = GravityEnum.START;
    titleGravity = localGravityEnum;
    contentGravity = localGravityEnum;
  }
  
  public static ThemeSingleton get()
  {
    return get(true);
  }
  
  public static ThemeSingleton get(boolean paramBoolean)
  {
    if ((singleton == null) && (paramBoolean)) {
      singleton = new ThemeSingleton();
    }
    return singleton;
  }
}
