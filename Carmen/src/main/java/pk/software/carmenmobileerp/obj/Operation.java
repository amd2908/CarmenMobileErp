package pk.software.carmenmobileerp.obj;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Operation extends RealmObject {
    @PrimaryKey
    private int cop_id;
    @Required
    private String cop_name;
    private double cop_amount;
    private String cop_category;
    private int cop_type;
    private String cop_date;
    private int cop_plan;
    private int cop_const;
    private int cop_cus_id;

    public Operation(int cop_id, String cop_name, double cop_amount, String cop_category, int cop_type, String cop_date, int cop_plan, int cop_const, int cop_cus_id) {
        this.cop_id = cop_id;
        this.cop_name = cop_name;
        this.cop_amount = cop_amount;
        this.cop_category = cop_category;
        this.cop_type = cop_type;
        this.cop_date = cop_date;
        this.cop_plan = cop_plan;
        this.cop_const = cop_const;
        this.cop_cus_id = cop_cus_id;
    }

    public Operation() {
    }


    public int getcop_id() {
        return cop_id;
    }

    public void setcop_id(int cop_id) {
        this.cop_id = cop_id;
    }

    public String getcop_name() {
        return cop_name;
    }

    public void setcop_name(String cop_name) {
        this.cop_name = cop_name;
    }

    public double getcop_amount() {
        return cop_amount;
    }

    public void setcop_amount(double cop_amount) {
        this.cop_amount = cop_amount;
    }


    public int getcop_type() {
        return cop_type;
    }

    public void setcop_type(int cop_type) {
        this.cop_type = cop_type;
    }

    public String getcop_date() {
        return cop_date;
    }

    public void setcop_date(String cop_date) {
        this.cop_date = cop_date;
    }

    public int getcop_plan() {
        return cop_plan;
    }

    public void setcop_plan(int cop_plan) {
        this.cop_plan = cop_plan;
    }

    public int getcop_const() {
        return cop_const;
    }

    public void setcop_const(int cop_const) {
        this.cop_const = cop_const;
    }

    public int getCop_cus_id() {
        return cop_cus_id;
    }

    public void setCop_cus_id(int cop_cus_id) {
        this.cop_cus_id = cop_cus_id;
    }

    public String getCop_category() {
        return cop_category;
    }

    public void setCop_category(String cop_category) {
        this.cop_category = cop_category;
    }

    public double getCop_amount() {
        return cop_amount;
    }

    public void setCop_amount(double cop_amount) {
        this.cop_amount = cop_amount;
    }
}


