package pk.software.carmenmobileerp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.adapters.CategoryAdapter;
import pk.software.carmenmobileerp.obj.Category;

public class CarmenCategoryActivity extends AppCompatActivity {

    @BindView(R.id.addCategoryBtn)
    FloatingActionButton addCategoryBtn;
    @BindView(R.id.categoryList)
    RecyclerView container;
    private int GET_CATEGORY_MODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmen_category);
        ButterKnife.bind(this);
        readInputData();
        container.setLayoutManager(new LinearLayoutManager(this));
        container.setItemAnimator(new DefaultItemAnimator());
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        DBManager categoryDB = new DBManager(this, realm);
        RealmResults<Category> categories = categoryDB.getCategoryList();
        categories = categories.sort("cct_name");
        CategoryAdapter adapter = new CategoryAdapter(categories);
        if (GET_CATEGORY_MODE == 1)
            addCategoryBtn.setVisibility(View.GONE);
        else
            addCategoryBtn.setVisibility(View.VISIBLE);
        container.setAdapter(adapter);
        container.addOnItemTouchListener(new RecyclerItemClickListener(CarmenCategoryActivity.this, container, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (GET_CATEGORY_MODE == 1) {
                    String categoryName = ((TextView) container.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.categoryName)).getText().toString();
                    Intent goBack = new Intent();
                    goBack.putExtra("categoryName", categoryName);
                    setResult(RESULT_OK, goBack);
                    finish();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void readInputData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            GET_CATEGORY_MODE = extras.getInt("getCategory");
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        container.getAdapter().notifyDataSetChanged();
    }


    @OnClick(R.id.addCategoryBtn)
    public void onViewClicked() {
        Intent goToCategory = new Intent(CarmenCategoryActivity.this, CarmenAddCategory.class);
        startActivity(goToCategory);
    }
}
