package pk.software.carmenmobileerp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Piotr Karmiński on 10.03.2018.
 */

public class MainMenuAdapter extends BaseAdapter {
    Activity activity;
    int itemId;
    private ArrayList<MainMenuItem> itemsList;


    public MainMenuAdapter(Activity a, int itemResourceId) {
        activity = a;
        itemId = itemResourceId;
        createItems();
    }

    public int getCount() {
        return itemsList.size();
        //return items.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    private MainMenuItem getMenuItem(int position) {
        return itemsList.get(position);
    }
    public MainMenuItems getItemType(int position)
    {
        return itemsList.get(position).getMainMenuItem();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(itemId, null);
        }
        final View covertV = convertView;
        ImageButton img = (ImageButton) covertV
                .findViewById(R.id.imgMenuItemView);
        initActions(position, img);
        TextView txt = (TextView) covertV.findViewById(R.id.txtMenuItemCaption);
        img.setImageResource(getMenuItem(position).getImgResourceId());
        txt.setText(getMenuItem(position).getCaption());
        covertV.setTag(getMenuItem(position).getMainMenuItem());
        return covertV;
    }

    private void initActions(final int position, ImageButton img) {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(position) {
                    case 0:
                        showOperation();
                    break;
                    case 1:
                        showPlanning();
                    break;
                    case 2:
                        showUser();
                    break;
                    case 3:
                        showDebts();
                    break;
                    case 4:
                        showReports();
                    break;
                    case 5:
                        showHistory();
                    break;
                    case 6:
                        showCategories();
                    break;
                    default:
                        Toast.makeText(activity, "Brak obsługi!", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    private void showCategories() {
        activity.startActivity(new Intent(activity, CarmenCategoryActivity.class));
    }

    private void showPlanning() {
        Toast.makeText(activity, "Do wykonania - Planowanie", Toast.LENGTH_SHORT).show();
    }

    private void showUser() {
        Toast.makeText(activity, "Do wykonania - Użytkownik", Toast.LENGTH_SHORT).show();
    }

    private void showReports() {
        Toast.makeText(activity, "Do wykonania - Raporty", Toast.LENGTH_SHORT).show();
    }

    private void showDebts() {
        Toast.makeText(activity, "Do wykonania - Długi", Toast.LENGTH_SHORT).show();
    }

    private void showHistory() {
        Toast.makeText(activity, "Do wykonania - Historia", Toast.LENGTH_SHORT).show();
    }

    private void showOperation() {
        activity.startActivity(new Intent(activity, CarmenOperationActivity.class));
    }


    private void addMenuItem(MainMenuItems mainMenuItem, int imgResourceId,	String caption) {
        itemsList.add(
                new MainMenuItem(mainMenuItem, imgResourceId, caption)
        );
    }

    private void createItems() {
        itemsList = new ArrayList<>();
                addMenuItem(MainMenuItems.MainMenu_OPERATIONS,
                        R.drawable.ic_operation, activity.getString(R.string.operationsMenu));
                addMenuItem(MainMenuItems.MainMenu_PLANNING,
                        R.drawable.ic_plan, activity.getString(R.string.planningMenu));
                addMenuItem(MainMenuItems.MainMenu_USER,
                        R.drawable.ic_user, activity.getString(R.string.userMenu));
                addMenuItem(MainMenuItems.MainMenu_DEBTS,
                        R.drawable.ic_bank, activity.getString(R.string.debtsMenu));
                addMenuItem(MainMenuItems.MainMenu_REPORTS,
                        R.drawable.ic_reports, activity.getString(R.string.reportsMenu));
                addMenuItem(MainMenuItems.MainMenu_HISTORY,
                        R.drawable.ic_history, activity.getString(R.string.historyMenu));
                addMenuItem(MainMenuItems.MainMenu_CATEGORIES,
                        R.drawable.ic_perpective, activity.getString(R.string.categoriesMenu));
                addMenuItem(MainMenuItems.MainMenu_CHANGE,
                        R.drawable.ic_change_date, activity.getString(R.string.importExportMenu));
                addMenuItem(MainMenuItems.MainMenu_TEMPLATES,
                        R.drawable.ic_templates, activity.getString(R.string.remplatesMenu));
    }

    public enum MainMenuItems {
        /**
         * Wydatki
         */
        MainMenu_OPERATIONS,
        /**
         * Planowanie
         */
        MainMenu_PLANNING,
        /**
         * Użytkownik
         */
        MainMenu_USER,
        /**
         * Długi
         */
        MainMenu_DEBTS,
        /**
         * Historia
         */
        MainMenu_HISTORY,
        /**
         * O aplikacji
         */
        MainMenu_CHANGE,
        /**
         * O wylogowanie
         */
        MainMenu_CATEGORIES,
        /**
         * Synchronizacja danych
         */
        MainMenu_REPORTS,
        /**
         * Wierzyciele
         */
        MainMenu_TEMPLATES
    }

    private class MainMenuItem {
        private MainMenuItems mainMenuItem;
        private int imgResourceId;
        private String caption;

        public MainMenuItems getMainMenuItem() {
            return mainMenuItem;
        }

        @SuppressWarnings("unused")
        public void setElemnt(MainMenuItems mainMenuItem) {
            this.mainMenuItem = mainMenuItem;
        }

        public int getImgResourceId() {
            return imgResourceId;
        }

        @SuppressWarnings("unused")
        public void setImgResourceId(int imgResourceId) {
            this.imgResourceId = imgResourceId;
        }

        public String getCaption() {
            return caption;
        }

        @SuppressWarnings("unused")
        public void setCaption(String caption) {
            this.caption = caption;
        }

        MainMenuItem(MainMenuItems mainMenuItem, int imgResourceId,
                     String caption) {
            this.mainMenuItem = mainMenuItem;
            this.imgResourceId = imgResourceId;
            this.caption = caption;
        }
    }
}