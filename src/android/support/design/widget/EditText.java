package android.support.design.widget;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

public class EditText
  extends AppCompatEditText
{
  private CharSequence getHintFromLayout()
  {
    TextInputLayout localTextInputLayout = getTextInputLayout();
    if (localTextInputLayout != null) {
      return localTextInputLayout.getHint();
    }
    return null;
  }
  
  private TextInputLayout getTextInputLayout()
  {
    for (ViewParent localViewParent = getParent(); (localViewParent instanceof View); localViewParent = localViewParent.getParent()) {
      if ((localViewParent instanceof TextInputLayout)) {
        return (TextInputLayout)localViewParent;
      }
    }
    return null;
  }
  
  public CharSequence getHint()
  {
    TextInputLayout localTextInputLayout = getTextInputLayout();
    if ((localTextInputLayout != null) && (localTextInputLayout.isLoading())) {
      return localTextInputLayout.getHint();
    }
    return super.getHint();
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    if ((localInputConnection != null) && (hintText == null)) {
      hintText = getHintFromLayout();
    }
    return localInputConnection;
  }
}
