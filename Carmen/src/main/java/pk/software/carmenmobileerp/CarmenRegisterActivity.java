package pk.software.carmenmobileerp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.dialogs.Dialogs;
import pk.software.carmenmobileerp.tools.CarmenValidator;
import pk.software.carmenmobileerp.obj.User;

public class CarmenRegisterActivity extends AppCompatActivity {

   // private static RegisterManager registerManager;
    ProgressDialog progres;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.profit)
    EditText profit;
    @BindView(R.id.actualState)
    EditText actualState;
    @BindView(R.id.registerBtn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_register);
        ButterKnife.bind(this);
        //registerManager = ((App) getApplication()).getRegisterManager();
        progres = new ProgressDialog(this);
        progres.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progres.setTitle("Rejestracja");
        progres.setMessage("Proszę czekać...");
    }

    private User createUserByFields() {
        String personLogin = login.getText().toString();
        String personPassword = password.getText().toString();
        String personName = name.getText().toString();
        double personProfit = Double.parseDouble(profit.getText().toString());
        double personActualState = Double.parseDouble(actualState.getText().toString());
        User person = new User();
        person.setCus_name(personName);
        person.setCus_actual_state(personActualState);
        person.setCus_profit(personProfit);
        person.setCus_login(personLogin);
        person.setActive(0);
        person.setCus_password(personPassword);
        return person;
    }

    private void clearFields() {
        name.setText("");
        profit.setText("");
        actualState.setText("");
        login.setText("");
        password.setText("");
    }

    private void saveUser() {
        progres.show();
        if (CarmenValidator.getInstance().validateFields(this, login) &&
                CarmenValidator.getInstance().validateFields(this, password) &&
                CarmenValidator.getInstance().validateFields(this, name) &&
                CarmenValidator.getInstance().validateFields(this, profit) &&
                CarmenValidator.getInstance().validateFields(this, actualState)
                ) {

            User person = createUserByFields();
            Realm.init(this);
            Realm realm = Realm.getDefaultInstance();
            DBManager userDB = new DBManager(this,realm);
            userDB.insertOrUpdateUser(person);
            progres.dismiss();
            clearFields();
            Dialogs.showDialogOk(getString(R.string.addUserSuccessMsg), false, this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
    }


    @Override
    protected void onStart() {
        //registerManager.onAttach(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        //registerManager.onStop();
        super.onStop();
    }


    public void registerSuccess() {
        Toast.makeText(this, R.string.addUserSuccessMsg, Toast.LENGTH_SHORT).show();
        finish();
    }


    public void showProgress(boolean b) {
        registerBtn.setEnabled(b);
    }

    public void registerError(String errorMsg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.attentionHeader));
        alertDialog.setMessage(errorMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.okInfo),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @OnClick(R.id.registerBtn)
    public void saveU() {
        saveUser();
    }

}
