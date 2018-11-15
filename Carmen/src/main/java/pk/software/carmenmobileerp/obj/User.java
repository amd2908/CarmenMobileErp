package pk.software.carmenmobileerp.obj;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Piotr Karmiński on 07.03.2018.
 */

public class User extends RealmObject {
    @PrimaryKey
    private int cus_id;
    private String cus_login;
    private String cus_password;
    private String cus_name;
    private int active;
    private double cus_profit;
    private double cus_actual_state;


    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_login() {
        return cus_login;
    }

    public void setCus_login(String cus_login) {
        this.cus_login = cus_login;
    }

    public String getCus_password() {
        return cus_password;
    }

    public void setCus_password(String cus_password) {
        this.cus_password = cus_password;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public double getCus_profit() {
        return cus_profit;
    }

    public void setCus_profit(double cus_profit) {
        this.cus_profit = cus_profit;
    }

    public double getCus_actual_state() {
        return cus_actual_state;
    }

    public void setCus_actual_state(double cus_actual_state) {
        this.cus_actual_state = cus_actual_state;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
