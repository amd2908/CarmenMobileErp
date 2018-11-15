package pk.software.carmenmobileerp.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import pk.software.carmenmobileerp.R;
import pk.software.carmenmobileerp.dialogs.ResultValue;

public class Dialogs {

    public static void showDialogOk(String message, boolean setCancelable, Activity activity)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.DialogStyle);
        builder.setMessage(message)
                .setCancelable(setCancelable)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showDialogOk(String message, boolean setCancelable, Activity activity, DialogInterface.OnClickListener onClickListener)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.DialogStyle);
        builder.setMessage(message)
                .setCancelable(setCancelable)
                .setPositiveButton("Ok", onClickListener);
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showDialogYesNo(String message, boolean setCancelable, Activity activity, DialogInterface.OnClickListener onClickListener)
    {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.DialogStyle);
        builder.setMessage(message)
                .setCancelable(setCancelable)
                .setPositiveButton("Ok", onClickListener)
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public static AlertDialog getTextDialog(Activity activity,
                                            String title,
                                            String msg,
                                            int inputType,
                                            final InputFilter[] filterType,
                                            final ResultValue<String> editText,
                                            final DialogInterface.OnClickListener clickListenerYes,
                                            final DialogInterface.OnClickListener clickListenerNo) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.edit_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edittext);
        if(filterType!=null)
         edt.setFilters(filterType);
        if (inputType != 0)
            edt.setInputType(inputType);
        if (editText != null) {
            edt.setText(editText.toString());
            if(edt.getText().toString().length()>0)
                edt.setSelection(edt.getText().toString().length());
            edt.requestFocus();

        }

        dialogBuilder.setTitle(title);
        if(!msg.equals(""))
            dialogBuilder.setMessage(msg);

        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                if (editText != null)
                    editText.set(edt.getText().toString());

                switch (which)  {
                    case DialogInterface.BUTTON_POSITIVE:
                        if (clickListenerYes != null)
                            clickListenerYes.onClick(dialog, which);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        if (clickListenerNo != null)
                            clickListenerNo.onClick(dialog, which);
                        break;
                }
            }
        };

        if(clickListenerYes!=null)
            dialogBuilder.setPositiveButton("Tak", clickListener);
        if(clickListenerNo!=null)
            dialogBuilder.setNegativeButton("Nie", clickListener);


        AlertDialog b = dialogBuilder.create();
        b.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        b.show();
        return b;
    }
}
