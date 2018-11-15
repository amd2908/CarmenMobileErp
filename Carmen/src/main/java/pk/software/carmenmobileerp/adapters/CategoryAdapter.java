package pk.software.carmenmobileerp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;
import pk.software.carmenmobileerp.R;
import pk.software.carmenmobileerp.obj.Category;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<Category> categories;

    public CategoryAdapter(RealmResults<Category> categories) {
        this.categories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category_grid_item, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Category category = categories.get(position);
        String cat_name = category.getCct_name();
        int cat_color = category.getCct_color();
        CategoryViewHolder uHolder = (CategoryViewHolder) holder;
        uHolder.name.setText(cat_name);
        uHolder.color.setBackgroundColor(cat_color);
        uHolder.color.setText((cat_name.charAt(0)+"").toUpperCase());
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView color;

        CategoryViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.categoryName);
            color = view.findViewById(R.id.categoryColor);
        }
    }
}
