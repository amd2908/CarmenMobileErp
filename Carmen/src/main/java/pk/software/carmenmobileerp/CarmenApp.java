package pk.software.carmenmobileerp;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CarmenApp extends Application {

        public static final String DB_NAME = "carmen.realm";

        @Override
        public void onCreate() {
            super.onCreate();
            Realm.init(this);
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                    .name(DB_NAME)
                    .schemaVersion(1)
                    .build();
            Realm.setDefaultConfiguration(realmConfig);
            Realm.getInstance(realmConfig);
        }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
