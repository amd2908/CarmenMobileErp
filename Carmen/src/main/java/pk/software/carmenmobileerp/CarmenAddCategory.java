package pk.software.carmenmobileerp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.dialogs.Dialogs;
import pk.software.carmenmobileerp.dialogs.ResultValue;
import pk.software.carmenmobileerp.obj.Category;
import pk.software.carmenmobileerp.tools.CarmenValidator;
import yuku.ambilwarna.AmbilWarnaDialog;

public class CarmenAddCategory extends AppCompatActivity {


    @BindView(R.id.title_category)
    TextView titleCategory;
    @BindView(R.id.substract_category)
    RadioButton substractCategory;
    @BindView(R.id.addition_category)
    RadioButton additionCategory;
    @BindView(R.id.saveOperation)
    Button saveOperation;
    ResultValue<String> resultValue = null;
    @BindView(R.id.colorPicker)
    TextView colorPicker;
    AmbilWarnaDialog dialog;
    int choosedColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_add_category);
        ButterKnife.bind(this);
        resultValue = new ResultValue<String>("");
        setControlsParam();
        setControlsAction();
    }

    private void setControlsAction() {
        titleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getTextDialog(CarmenAddCategory.this, getString(R.string.addTitleInfo), "", 0, null, resultValue, btnYes, btnNo);
            }
        });
        titleCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getTextDialog(CarmenAddCategory.this, getString(R.string.addTitleInfo), "", 0, null, resultValue, btnYes, btnNo);
            }
        });
        substractCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                additionCategory.setChecked(!b);
            }
        });

        additionCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                substractCategory.setChecked(!b);
            }
        });
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AmbilWarnaDialog(CarmenAddCategory.this, 0xff0000ff, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        colorPicker.setBackgroundColor(color);
                        choosedColor = color;
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // cancel was selected by the user
                    }
                });
                dialog.show();
            }
        });
    }



    private void setControlsParam() {
        titleCategory.setSelected(false);
        titleCategory.setClickable(true);
        substractCategory.setChecked(true);
    }

    DialogInterface.OnClickListener btnNo = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {

        }
    };

    DialogInterface.OnClickListener btnYes = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface arg0, int arg1) {
            titleCategory.setText(resultValue.toString());
        }
    };


    @OnClick(R.id.saveOperation)
    public void onViewClicked() {
        if (CarmenValidator.getInstance().validateFields(this, titleCategory)) {
            Realm.init(this);
            Realm realm = Realm.getDefaultInstance();
            DBManager categoryDb = new DBManager(this, realm);
            Category category = createCategoryByFields();
            categoryDb.insertOrUpdateCategory(category);
            Dialogs.showDialogOk(getString(R.string.categorySavedInfo), false, this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
    }

    private Category createCategoryByFields() {
        String categoryName = titleCategory.getText().toString();
        int categoryType = substractCategory.isChecked() ? 0 : 1;
        Category category = new Category();
        category.setCct_name(categoryName);
        category.setCct_cop_type(categoryType);
        category.setCct_color(choosedColor);
        return category;
    }
}
