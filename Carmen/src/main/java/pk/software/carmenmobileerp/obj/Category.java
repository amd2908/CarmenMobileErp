package pk.software.carmenmobileerp.obj;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Category extends RealmObject {
    @PrimaryKey
    private int cct_id;
    @Required
    private String cct_name;
    private int cct_cop_type;
    private int cct_color;

    public Category(int cop_id, String cct_name, int cct_cop_type, int cct_color) {
        this.cct_id = cop_id;
        this.cct_cop_type = cct_cop_type;
        this.cct_name = cct_name;
        this.cct_color = cct_color;
    }

    public Category() {
    }


    public int getCct_id() {
        return cct_id;
    }

    public void setCct_id(int cct_id) {
        this.cct_id = cct_id;
    }

    public String getCct_name() {
        return cct_name;
    }

    public void setCct_name(String cct_name) {
        this.cct_name = cct_name;
    }

    public int getCct_cop_type() {
        return cct_cop_type;
    }

    public void setCct_cop_type(int cct_cop_type) {
        this.cct_cop_type = cct_cop_type;
    }

    public int getCct_color() {
        return cct_color;
    }

    public void setCct_color(int cct_color) {
        this.cct_color = cct_color;
    }
}


