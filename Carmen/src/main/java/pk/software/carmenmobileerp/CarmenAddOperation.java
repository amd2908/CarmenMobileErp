package pk.software.carmenmobileerp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.dialogs.Dialogs;
import pk.software.carmenmobileerp.dialogs.ResultValue;
import pk.software.carmenmobileerp.tools.CarmenValidator;
import pk.software.carmenmobileerp.tools.DecimalDigitsInputFilter;
import pk.software.carmenmobileerp.obj.Operation;
import pk.software.carmenmobileerp.tools.Convert;

public class CarmenAddOperation extends AppCompatActivity {
    
    Calendar calendar = Calendar.getInstance();
    ResultValue<String> resultValue = null;
    ResultValue<String> resultAmountValue = null;
    @BindView(R.id.amount_operation)
    TextView amountOperation;
    @BindView(R.id.separator)
    View separator;
    @BindView(R.id.categories_operation)
    TextView categoriesOperation;
    @BindView(R.id.title_operation)
    TextView titleOperation;
    @BindView(R.id.substract_operation)
    RadioButton substract_operation;
    @BindView(R.id.addition_operation)
    RadioButton additionOperation;
    @BindView(R.id.date_operation)
    TextView dateOperation;
    @BindView(R.id.const_operation)
    CheckBox constOperation;
    @BindView(R.id.plan_operation)
    CheckBox planOperation;
    @BindView(R.id.saveOperation)
    Button saveOperation;
    private Operation operation;
    private int operationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_add_expense);
        ButterKnife.bind(this);
        resultValue = new ResultValue<String>("");
        resultAmountValue = new ResultValue<String>("");
        setControlsAction();
        setControlsParam();
        readInputData();
    }

    private void readInputData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getInt("operationId")>0) {
                operation = new Operation();
                operationId = extras.getInt("operationId");
            }
        }

        if(operation!=null) {
            if (operationId > 0) {
                getSupportActionBar().setTitle("Edycja operacji");
                Realm.init(this);
                Realm realm = Realm.getDefaultInstance();
                DBManager operationDB = new DBManager(this, realm);
                operation = operationDB.getOperationById(operationId);
                loadControls();
            }
        }
    }

    private void loadControls() {
        amountOperation.setText(Convert.doubleAsCurrencyString(operation.getcop_amount(), "zł"));
        resultAmountValue.set(Convert.doubleAsCurrencyString(operation.getcop_amount(), "zł"));
        titleOperation.setText(operation.getcop_name());
        dateOperation.setText(operation.getcop_date());
        if(operation.getcop_type() == 0)
            substract_operation.setChecked(true);
        else
            additionOperation.setChecked(true);
        constOperation.setChecked(operation.getcop_const()>0);
        planOperation.setChecked(operation.getcop_plan()>0);
        categoriesOperation.setText(operation.getCop_category());
    }

    private void setControlsAction() {
        dateOperation.setText(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        categoriesOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCategory = new Intent(CarmenAddOperation.this, CarmenCategoryActivity.class);
                goToCategory.putExtra("getCategory", 1);
                startActivityForResult(goToCategory, 101);
            }
        });

        amountOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getTextDialog(CarmenAddOperation.this, getString(R.string.addAmountInfo), "",
                        InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL,
                        new InputFilter[] {DecimalDigitsInputFilter.Create(6,2)},
                        resultAmountValue, btnAmountYes, btnNo);
            }
        });

        substract_operation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                additionOperation.setChecked(!b);
            }
        });

        additionOperation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                substract_operation.setChecked(!b);
            }
        });


        titleOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getTextDialog(CarmenAddOperation.this, getString(R.string.addTitleInfo), "", 0, null,resultValue, btnYes, btnNo);
            }
        });

        dateOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CarmenAddOperation.this, R.style.DialogStyle, dateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setControlsParam() {
        categoriesOperation.setSelected(false);
        categoriesOperation.setClickable(true);
        titleOperation.setSelected(false);
        titleOperation.setClickable(true);
        amountOperation.setClickable(true);
        amountOperation.setSelected(false);
        substract_operation.setChecked(true);
    }

    DialogInterface.OnClickListener btnNo = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {

        }
    };

    DialogInterface.OnClickListener btnAmountYes = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {
            amountOperation.setText(Convert.doubleAsCurrencyString(Double.parseDouble(resultAmountValue.toString()),"zł"));
        }
    };

    DialogInterface.OnClickListener btnYes = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {
            titleOperation.setText(resultValue.toString());
        }
    };


    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateOperation.setText(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        }

    };


    @OnClick(R.id.saveOperation)
    public void onViewClicked() {
        double amountValue = Convert.getFromCurrencyToDouble(amountOperation.getText().toString(), "zł");
        if (CarmenValidator.getInstance().validateFields(this, titleOperation) &&
                CarmenValidator.getInstance().validateFields(this, amountValue))
        {
            Realm.init(this);
            Realm realm = Realm.getDefaultInstance();
            DBManager operationDB = new DBManager(this, realm);
            operation = createOpertionByFields();
            if(operationId<=0)
                operationDB.insertOrUpdateOperation(operation);
            else
                operationDB.updateOperation(operation);
            Dialogs.showDialogOk(operation.getcop_id()>0 ? getString(R.string.editOperationSuccessMsg) : getString(R.string.addOperationSuccessMsg),
                    false,
                    this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
    }

    private Operation createOpertionByFields() {
        double operationAmount = Convert.getFromCurrencyToDouble(amountOperation.getText().toString(), "zł");
        String operationCategory = categoriesOperation.getText().toString();
        String operationTitle = titleOperation.getText().toString();
        int operationType = substract_operation.isChecked() ? 0 : 1;
        String operationDate = dateOperation.getText().toString();
        int operationConst = constOperation.isChecked() ? 1 : 0;
        int operationPlan = planOperation.isChecked() ? 1 : 0;
        operation = new Operation();
        if(operationId>0)
            operation.setcop_id(operationId);
        operation.setcop_amount(operationAmount);
        operation.setCop_category(operationCategory);
        operation.setcop_const(operationConst);
        operation.setcop_date(operationDate);
        operation.setcop_plan(operationPlan);
        operation.setcop_type(operationType);
        operation.setcop_name(operationTitle);
        return operation;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101)
        {
            Bundle extras = data.getExtras();
            if (extras != null) {
                categoriesOperation.setText(extras.getString("categoryName"));
            }
        }
    }
}
