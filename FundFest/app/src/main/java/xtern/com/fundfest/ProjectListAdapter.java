package xtern.com.fundfest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sean.phillips on 7/6/15.
 * The List adapter used for the project list.
 */

public class ProjectListAdapter extends BaseAdapter {

    ArrayList<Project> projectList;
    LayoutInflater inflater;


    public ProjectListAdapter(ArrayList<Project> list, Context context){
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ProjectHolder holder;
        if(convertView == null) {
            view = inflater.inflate(R.layout.project_item, parent, false);
            holder = new ProjectHolder();
            holder.name = (TextView)view.findViewById(R.id.projName);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ProjectHolder)view.getTag();
        }

        Project project = projectList.get(position);
        holder.name.setText(project.projectName);

        return view;
    }
}
