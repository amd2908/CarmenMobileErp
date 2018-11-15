package pk.software.carmenmobileerp.obj;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by Piotr Karmiński on 12.03.2018.
 */

public class Expenses {
    public static final String FIELD_ID             = "cwd_id";
    public static final String FIELD_NAME         = "cwd_nazwa";
    public static final String FIELD_AMOUNT       = "cwd_kwota";
    public static final String FIELD_DATE           = "cwd_data";
    public static final String FIELD_USER_ID         = "cwd_cuz_id";
    public static final String FIELD_PAID           = "cwd_zalacono";
    public static final String FIELD_CATEGORY_ID         = "cwd_ckt_id";

    @DatabaseField(columnName = FIELD_NAME )
    private String name;
    @DatabaseField(columnName = FIELD_AMOUNT)
    private double amount;
    @DatabaseField(columnName = FIELD_ID,generatedId = true)
    private int id;
    @DatabaseField(columnName = FIELD_DATE)
    private Date dateReal;
    @DatabaseField(columnName = FIELD_USER_ID)
    private int userId;
    @DatabaseField(columnName = FIELD_PAID)
    private int paidFlag;
    @DatabaseField(columnName = FIELD_CATEGORY_ID, canBeNull = true)
    private int categoryId;

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public Date getDateReal() {
        return dateReal;
    }

    public int getUserid() {
        return userId;
    }

    public int getPaidFlag() {
        return paidFlag;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateReal(Date dateReal) {
        this.dateReal = dateReal;
    }

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public void setPaidFlag(int paidFlag) {
        this.paidFlag = paidFlag;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
