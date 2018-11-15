package pk.software.carmenmobileerp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import pk.software.carmenmobileerp.adapters.OperationAdapter;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.obj.Operation;

public class CarmenOperationActivity extends AppCompatActivity {


    @BindView(R.id.expensesList)
    RecyclerView container;
    @BindView(R.id.sumOfExpenses)
    TextView sumOfExpenses;
    @BindView(R.id.sumOfIncomes)
    TextView sumOfIncomes;
    @BindView(R.id.bilans)
    TextView bilans;
    private int menuIndx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_operations);
        ButterKnife.bind(this);
        container.setLayoutManager(new LinearLayoutManager(this));
        container.setItemAnimator(new DefaultItemAnimator());
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        DBManager operationDB = new DBManager(this, realm);
        RealmResults<Operation> operations = operationDB.getOperationList();
        operations = operations.sort("cop_date");
        OperationAdapter adapter = new OperationAdapter(operations, this, realm);
        container.setAdapter(adapter);
        sumOfExpenses.setText("Łączna suma wydatków    : " + String.format("%.2f", operationDB.getExpensesSum()) + " zł");
        sumOfIncomes.setText( "Łączna suma przychodów  : " + String.format("%.2f", operationDB.getIncomesSum()) + " zł");
        bilans.setText(       "Bilans                  : " + String.format("%.2f", operationDB.getBilansSum()) + " zł");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addOperationBtn);
        fab.setBackgroundColor(getResources().getColor(R.color.white));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOperation = new Intent(CarmenOperationActivity.this, CarmenAddOperation.class);
                startActivity(goToOperation);
            }
        });
    }

    private void refreshData()
    {
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        DBManager operationDB = new DBManager(this, realm);
        sumOfExpenses.setText("Łączna suma wydatków      : " + String.format("%.2f", operationDB.getExpensesSum()) + " zł");
        sumOfIncomes.setText( "Łączna suma przychodów  : " + String.format("%.2f", operationDB.getIncomesSum()) + " zł");
        bilans.setText(       "Bilans                                      : " + String.format("%.2f", operationDB.getBilansSum()) + " zł");
    }

    @Override
    public void onResume() {
        super.onResume();
        container.getAdapter().notifyDataSetChanged();
        refreshData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
