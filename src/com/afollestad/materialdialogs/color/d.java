package com.afollestad.materialdialogs.color;

import android.app.Dialog;
import apps.afollestad.materialdialogs.DialogAction;
import apps.afollestad.materialdialogs.MaterialDialog;
import apps.afollestad.materialdialogs.c;

class d
  implements c
{
  d(ColorChooserDialog paramColorChooserDialog) {}
  
  public void b(MaterialDialog paramMaterialDialog, DialogAction paramDialogAction)
  {
    if (ColorChooserDialog.access$getIsInSub(this$0))
    {
      paramMaterialDialog.setActionButton(DialogAction.NEGATIVE, access$getGetBuilderthis$0).mCancelBtn);
      ColorChooserDialog.access$getIsInSub(this$0, false);
      ColorChooserDialog.access$getSubIndex(this$0, -1);
      ColorChooserDialog.access$getInvalidate(this$0);
      return;
    }
    paramMaterialDialog.cancel();
  }
}
