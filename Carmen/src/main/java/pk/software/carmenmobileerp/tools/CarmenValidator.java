package pk.software.carmenmobileerp.tools;

import android.app.Activity;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import pk.software.carmenmobileerp.R;
import pk.software.carmenmobileerp.dialogs.Dialogs;

/**
 * Created by Piotr Karmiński on 27.01.2018.
 */

public class CarmenValidator {

    private static CarmenValidator instance;

    public static CarmenValidator getInstance(){
        if(instance == null){
            instance = new CarmenValidator();
        }
        return instance;
    }

    public  boolean validateFields(Activity a, EditText field)
    {
        boolean isValidate = true;
        String fieldText = field.getText().toString();
        if(fieldText.isEmpty()) {
            field.setError((Html.fromHtml("<font color='red'>" + a.getString(R.string.requiredFieldInfo) + "</font>")));
            isValidate = false;
        }
        return isValidate;
    }

    public  boolean validateFields(Activity a, double fieldValue)
    {
        boolean isValidate = true;
        if(fieldValue<= 0) {
                Dialogs.showDialogOk(a.getString(R.string.invalidAmount), false, a);
                isValidate = false;
        }
        return isValidate;
    }

    public  boolean validateFields(Activity a, TextView field)
    {
        boolean isValidate = true;
        String fieldText = field.getText().toString();
        if(fieldText.isEmpty()) {
            Dialogs.showDialogOk(a.getString(R.string.requiredFieldInfo), false, a);
            isValidate = false;
        }
        return isValidate;
    }
}
