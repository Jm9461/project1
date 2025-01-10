package com.afollestad.materialdialogs.color;

import android.support.v4.app.DialogFragment;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.c;

class e
  implements c
{
  e(ColorChooserDialog paramColorChooserDialog) {}
  
  public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
  {
    paramMaterialDialog = ColorChooserDialog.a(b);
    paramDialogAction = b;
    paramMaterialDialog.a(paramDialogAction, ColorChooserDialog.access$getGetSelectedColor(paramDialogAction));
    b.dismiss();
  }
}
