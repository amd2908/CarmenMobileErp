package pk.software.carmenmobileerp.manager;

import android.content.SharedPreferences;

import pk.software.carmenmobileerp.api.UserResponse;

/**
 * Created by Piotr Karmiński on 12.03.2018.
 */

public class ExpensesManager {

    private static ExpensesManager instance;

    public static ExpensesManager getInstance(){
        if(instance == null){
            instance = new ExpensesManager();
        }
        return instance;
    }

    /**
     * Created by Piotr Karmiński on 29.01.2018.
     */

    public static class UserPreferences {

        public static final String SESSION_TOKEN = "session_token";
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String USER_ID = "userId";
        private SharedPreferences sharedPreferences;
        public UserPreferences(SharedPreferences sharedPreferences)
        {
            this.sharedPreferences = sharedPreferences;
        }

        public void login(UserResponse loginResonse)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SESSION_TOKEN, loginResonse.sessionToken);
            editor.putString(USERNAME, loginResonse.username);
            editor.putString(EMAIL, loginResonse.email);
            editor.putString(FIRST_NAME, loginResonse.firstName);
            editor.putString(LAST_NAME, loginResonse.lastName);
            editor.putString(USER_ID, loginResonse.objectId);
            editor.apply();

        }

    }
}
