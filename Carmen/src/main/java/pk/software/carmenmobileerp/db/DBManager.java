package pk.software.carmenmobileerp.db;

import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmResults;
import pk.software.carmenmobileerp.obj.Category;
import pk.software.carmenmobileerp.obj.Operation;
import pk.software.carmenmobileerp.obj.User;

/**
 * Created by Piotr Karmiński on 07.03.2018.
 */

public class DBManager {

    private Activity activity;
    Realm realm;

    public DBManager(Activity activity, Realm realm) {
        this.activity = activity;
        this.realm = realm;
    }

    public void insertOrUpdateUser(final User user)
    {
        if(realm!=null && user!=null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Number currentIdNum = realm.where(User.class).max("cus_id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    user.setCus_id(nextId);
                    realm.insertOrUpdate(user);
                }
            });
        }
    }

    public void updateOperation(final Operation operation)
    {
        if(realm!=null && operation!=null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(operation);
                }
            });
        }
    }

    public void insertOrUpdateOperation(final Operation operation)
    {
        if(realm!=null && operation!=null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Number currentIdNum = realm.where(Operation.class).max("cop_id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    operation.setcop_id(nextId);
                    realm.insert(operation);
                }
            });
        }
    }

    public Operation getOperationById(final int id)
    {
        final RealmResults<Operation> oepration = realm.where(Operation.class).equalTo("cop_id",id).findAll();
        return oepration.get(0);
    }

    public void deleteOperation(final int id)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Operation> result = realm.where(Operation.class).equalTo("cop_id",id).findAll();
                result.deleteAllFromRealm();
            }
        });
    }

    public void insertOrUpdateCategory(final Category category)
    {
        if(realm!=null && category!=null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Number currentIdNum = realm.where(Category.class).max("cct_id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    category.setCct_id(nextId);
                    realm.insert(category);
                }
            });
        }
    }

    public RealmResults<Operation> getOperationList()
    {
        final RealmResults<Operation> operation = realm.where(Operation.class).findAll();
        return operation;
    }

    public RealmResults<Category> getCategoryList()
    {
        final RealmResults<Category> categories = realm.where(Category.class).findAll();
        return categories;
    }

    public double getExpensesSum()
    {
        final RealmResults<Operation> operation = realm.where(Operation.class).equalTo("cop_type", 0).findAll();
        double sumAmount = 0.0D;
        for(int i = 0; i<operation.size(); i++)
            sumAmount += operation.get(i).getcop_amount();
        return sumAmount;
    }

    public double getIncomesSum()
    {
        final RealmResults<Operation> operation = realm.where(Operation.class).equalTo("cop_type", 1).findAll();
        double sumAmount = 0.0D;
        for(int i = 0; i<operation.size(); i++)
            sumAmount += operation.get(i).getcop_amount();
        return sumAmount;
    }

    public double getBilansSum()
    {
        return getIncomesSum() - getExpensesSum();
    }

    public boolean checkIfUserIsCorrect(String login, String password)
    {
        final RealmResults<User> user = realm.where(User.class)
                .equalTo("cus_login", login).and()
                .equalTo("cus_password", password).findAll();
        if(user!=null)
            if(user.size()==1 &&
                    user.get(0).getCus_login().equals(login) &&
                    user.get(0).getCus_password().equals(password))
                return true;
        return false;
    }
}
