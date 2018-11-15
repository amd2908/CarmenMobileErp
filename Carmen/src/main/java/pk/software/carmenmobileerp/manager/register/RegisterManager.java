package pk.software.carmenmobileerp.manager.register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import pk.software.carmenmobileerp.CarmenRegisterActivity;
import pk.software.carmenmobileerp.api.MobileErpApi;
import pk.software.carmenmobileerp.api.RegisterRequest;
import pk.software.carmenmobileerp.api.UserResponse;
import pk.software.carmenmobileerp.manager.ExpensesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Piotr Karmiński on 28.01.2018.
 */

public class RegisterManager {
    private CarmenRegisterActivity registerActivity;
    private final ExpensesManager.UserPreferences userPreferences;
    private MobileErpApi erpapi;
    private Retrofit retrofit;
    private Call<UserResponse> callRegister = null;

    public RegisterManager(ExpensesManager.UserPreferences userPreferences, MobileErpApi erpapi, Retrofit retrofit) {
        this.userPreferences = userPreferences;
        this.erpapi = erpapi;
        this.retrofit = retrofit;
    }

    public void onAttach(CarmenRegisterActivity registerActivity)
    {
        this.registerActivity = registerActivity;
    }

    public void onStop()
    {
        this.registerActivity = null;
    }


    public void register(final Activity a, String firstName, String lastName, String email, String password)
    {
        final ProgressDialog pd = new ProgressDialog(a);
        // Set progress dialog style spinner
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Set the progress dialog title and message
        pd.setTitle("Rejestracja");
        pd.setMessage("Proszę czekać...");
        RegisterRequest regRequest = new RegisterRequest();
        regRequest.firstName = firstName;
        regRequest.lastName = lastName;
        regRequest.username = email;
        regRequest.password = password;
        regRequest.email = email;

        pd.show();
        if(callRegister==null) {
            callRegister = erpapi.registerUser(regRequest);
            callRegister.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    callRegister = null;
                    if (response.isSuccessful()) {
                        userPreferences.login(response.body());
                        UserResponse body = response.body();
                        userPreferences.login(body);
                        Log.d(CarmenRegisterActivity.class.getSimpleName(), "Resp: " + body.toString());
                        if (registerActivity != null)
                            registerActivity.registerSuccess();
                        callRegister = null;
                        updateProgress(callRegister);
                        pd.dismiss();
                    } else {
                        if (registerActivity != null)
                            registerActivity.registerError("Logowanie nie powiodło się.");
                        callRegister = null;
                        updateProgress(callRegister);
                        pd.dismiss();
                    }
                }



                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    callRegister = null;
                    updateProgress(callRegister);
                    if (registerActivity != null)
                        registerActivity.registerError(t.getLocalizedMessage());
                }
            });
        }
    }

    public void updateProgress(Call<UserResponse> call) {
        if(registerActivity!=null)
            registerActivity.showProgress(call == null);
    }




}
