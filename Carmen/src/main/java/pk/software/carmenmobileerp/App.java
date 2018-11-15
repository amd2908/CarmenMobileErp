package pk.software.carmenmobileerp;

import android.app.Application;
import android.preference.PreferenceManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pk.software.carmenmobileerp.api.MobileErpApi;
import pk.software.carmenmobileerp.manager.ExpensesManager;
import pk.software.carmenmobileerp.manager.login.LoginManager;
import pk.software.carmenmobileerp.manager.register.RegisterManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Piotr Karmiński on 28.01.2018.
 */

public class App extends Application {

    private LoginManager loginManager;
    private RegisterManager registerManager;
    private ExpensesManager.UserPreferences userPreferences;

    public LoginManager getLoginManager() {
        return loginManager;
    }
    public RegisterManager getRegisterManager() {
        return registerManager;
    }

    public MobileErpApi getErpApi() {
        return erpApi;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private MobileErpApi erpApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor loginIntercept = new HttpLoggingInterceptor();
        loginIntercept.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(loginIntercept).build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://parseapi.back4app.com/");
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(client);

        retrofit = builder.build();
        erpApi = retrofit.create(MobileErpApi.class);


        userPreferences = new ExpensesManager.UserPreferences(PreferenceManager.getDefaultSharedPreferences(this));
        loginManager = new LoginManager(userPreferences, erpApi, retrofit);
        registerManager = new RegisterManager(userPreferences, erpApi, retrofit);

    }
}
