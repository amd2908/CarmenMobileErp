package pk.software.carmenmobileerp.obj;

import android.view.View;

/**
 * Created by Piotr Karmiński on 05.04.2018.
 */

public  interface ClickListener{
    public void onClick(View view, int position);
    public void onLongClick(View view,int position);
}