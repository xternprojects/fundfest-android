package xtern.com.fundfest.Listeners;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    DrawerLayout drawerLayout;
    ListView drawerList;

    public DrawerItemClickListener(DrawerLayout drawerLayout, ListView drawerList){
        this.drawerLayout = drawerLayout;
        this.drawerList = drawerList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //do something
        drawerLayout.closeDrawer(drawerList);
    }
}
