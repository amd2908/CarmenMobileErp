package pk.software.carmenmobileerp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import pk.software.carmenmobileerp.CarmenAddOperation;
import pk.software.carmenmobileerp.R;
import pk.software.carmenmobileerp.db.DBManager;
import pk.software.carmenmobileerp.obj.Operation;

public class OperationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<Operation> operations;
    private Activity activity;
    private Realm realm;
    public OperationAdapter(RealmResults<Operation> operations, Activity activity, Realm realm) {
        this.operations = operations;
        this.activity = activity;
        this.realm = realm;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.operation_grid_item, parent, false);
        return new OperationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Operation operaton = operations.get(position);
        String opr_name = operaton.getcop_name();
        String opr_date = operaton.getcop_date();
        String opr_category = operaton.getCop_category().equals("") ? "Brak kategorii" : operaton.getCop_category();
        String opr_amount = String.format( "%.2f", operaton.getcop_amount());
        int opr_type = operaton.getcop_type();
        OperationViewHolder uHolder = (OperationViewHolder) holder;
        uHolder.title.setText(opr_name);
        uHolder.date.setText(opr_date);
        uHolder.amount.setText(opr_amount);
        uHolder.category.setText(opr_category);
        if(opr_type == 0)
            uHolder.amount.setTextColor(activity.getResources().getColor(R.color.darkRed));
        else
            uHolder.amount.setTextColor(activity.getResources().getColor(R.color.softGreen));
        final Button button = ((OperationViewHolder) holder).btnOption;

        uHolder.btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(activity, button);

                popup.inflate(R.menu.operation_listpopupmenu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itemDelButton:
                                DBManager operationDB = new DBManager(activity, realm);
                                operationDB.deleteOperation(operaton.getcop_id());
                                notifyItemRemoved(holder.getAdapterPosition());
                                break;
                            case R.id.itemEditButton:
                                Intent goToDetails = new Intent(activity, CarmenAddOperation.class);
                                goToDetails.putExtra("operationId", operaton.getcop_id());
                                activity.startActivity(goToDetails);
                                break;
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return operations.size();
    }

    class OperationViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView amount;
        TextView category;
        Button btnOption;

        OperationViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.operationTitle);
            date = view.findViewById(R.id.operationDate);
            amount = view.findViewById(R.id.operationAmount);
            category = view.findViewById(R.id.operationCategory);
            btnOption = view.findViewById(R.id.buttonOptions);
        }
    }
}
