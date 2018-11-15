package pk.software.carmenmobileerp.manager.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import pk.software.carmenmobileerp.CarmenLoginActivity;
import pk.software.carmenmobileerp.api.UserResponse;
import pk.software.carmenmobileerp.api.MobileErpApi;
import pk.software.carmenmobileerp.manager.ExpensesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Piotr Karmiński on 28.01.2018.
 */

public class LoginManager {
    private CarmenLoginActivity loginActivity;
    private final ExpensesManager.UserPreferences userPreferences;
    private MobileErpApi erpApi;
    private Retrofit retrofit;
    private Call<UserResponse> callMain = null;

    public LoginManager(ExpensesManager.UserPreferences userPreferences, MobileErpApi erpApi, Retrofit retrofit) {
        this.userPreferences = userPreferences;
        this.erpApi = erpApi;
        this.retrofit = retrofit;
    }

    public void onAttach(CarmenLoginActivity loginActivity)
    {
        this.loginActivity = loginActivity;
    }

    public void onStop()
    {
        this.loginActivity = null;
    }


    public void login(final Activity a, String username, String password)
    {
        final ProgressDialog pd = new ProgressDialog(a);
        // Set progress dialog style spinner
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Set the progress dialog title and message
        pd.setTitle("Logowanie");
        pd.setMessage("Proszę czekać...");


        pd.show();
        if(callMain==null) {
            callMain = erpApi.getLogin(username, password);
            updateProgress(callMain);
            callMain.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    callMain = null;
                    if (response.isSuccessful()) {
                        UserResponse body = response.body();
                        userPreferences.login(body);
                        Log.d(CarmenLoginActivity.class.getSimpleName(), "Resp: " + body.toString());
                        //if (loginActivity != null)
                            //loginActivity.loginSuccess();
                        callMain = null;
                        updateProgress(callMain);
                        pd.dismiss();
                    } else {
                        if (loginActivity != null)
                            loginActivity.loginError("Logowanie nie powiodło się.");
                        callMain = null;
                        updateProgress(callMain);
                        pd.dismiss();
                    }
                }



                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    callMain = null;
                    updateProgress(callMain);
                    if (loginActivity != null)
                        loginActivity.loginError(t.getLocalizedMessage());
                }
            });
        }
    }

    public void updateProgress(Call<UserResponse> call) {
      //  if(loginActivity!=null)
         // loginActivity.showProgress(call == null);
    }
}
