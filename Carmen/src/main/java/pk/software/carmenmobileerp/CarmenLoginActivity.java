package pk.software.carmenmobileerp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarmenLoginActivity extends AppCompatActivity {

    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    //private static LoginManager loginManager;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.registerBtn)
    Button registerBtn;
    ProgressDialog progres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_login);
        ButterKnife.bind(this);
        //loginManager = ((App) getApplication()).getLoginManager();
        progres = new ProgressDialog(this);
        progres.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progres.setTitle("Logowanie");
        progres.setMessage("Proszę czekać...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //loginManager.onAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //loginManager.onStop();
    }

    @OnClick({R.id.loginBtn})
    public void onViewClicked(View view) {
        startActivity(new Intent(this, CarmenMainActivity.class));
        /*switch (view.getId()) {
            case R.id.loginBtn:
                if (CarmenValidator.getInstance().validateFields(this, login) && CarmenValidator.getInstance().validateFields(this, password)) {
                    progres.show();
                    Realm.init(this);
                    Realm realm = Realm.getDefaultInstance();
                    DBManager userDb = new DBManager(this, realm);
                    boolean isUserCorrect = userDb.checkIfUserIsCorrect(login.getText().toString(), password.getText().toString());
                    if(isUserCorrect)
                        startActivity(new Intent(this, CarmenMainActivity.class));
                    else {
                        password.setText("");
                        Dialogs.showDialogOk("Użytkownik nie istnieje bądź wprowadzono nieprawidłowe dane.", false, this);
                    }
                    progres.dismiss();
                }
                break;
        }*/
    }


   /* public void loginSuccess() {
        startActivity(new Intent(this, CarmenMainActivity.class));
        finish();
    }*/

    public void loginError(String errorMsg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.attentionHeader));
        alertDialog.setMessage(errorMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /*public void showProgress(boolean b) {
        loginBtn.setEnabled(b);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionRegister) {
            startActivity(new Intent(this, CarmenRegisterActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.registerBtn)
    public void onViewClicked() {
        startActivity(new Intent(this, CarmenRegisterActivity.class));
    }
}
