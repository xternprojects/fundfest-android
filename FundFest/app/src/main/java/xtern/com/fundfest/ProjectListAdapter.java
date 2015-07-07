package xtern.com.fundfest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by sean.phillips on 7/6/15.
 * The List adapter used for the project list.
 */

public class ProjectListAdapter extends BaseAdapter {

    ArrayList<Project> projectList;

    public ProjectListAdapter(ArrayList<Project> list){
        projectList = list;
    }

    @Override
    public int getCount() {
        return projectList.size();
    }

    @Override
    public Project getItem(int i) {
        return projectList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
