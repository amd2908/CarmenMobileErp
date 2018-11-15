package pk.software.carmenmobileerp.manager.register;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import pk.software.carmenmobileerp.manager.ExpensesManager;

/**
 * Created by Piotr Karmiński on 16.03.2018.
 */

public class CarmenListManager {

    private static CarmenListManager instance;

    public static CarmenListManager getInstance(){
        if(instance == null){
            instance = new CarmenListManager();
        }
        return instance;
    }

    public void hideKeyboard(Activity activity, View v)
    {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 3);
            activity.getCurrentFocus().clearFocus();
        }
    }


}
