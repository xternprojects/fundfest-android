package xtern.com.fundfest.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import xtern.com.fundfest.DataObjects.Project;
import xtern.com.fundfest.R;

public class ProjectRecyclerAdapter extends RecyclerView.Adapter<ProjectRecyclerAdapter.ViewHolder> {

    ArrayList<Project> dataset;

    public ProjectRecyclerAdapter(ArrayList<Project> projectList) {
        dataset = projectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.project_cards, parent, false);
        v.findViewById(R.id.votes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clickin", "HEARTS");
            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clickin", "BASE");
            }
        });
        // set the view's size, margins, paddings and layout parameters
        //...
        Log.e("Creating a ","Viewholder");
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void changeDataSet(ArrayList<Project> newSet){
        dataset = newSet;
        notifyDataSetChanged();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Project project = dataset.get(position);
        CardView card = holder.projectCard;//.setText(dataset.get(position).title);
        ((TextView)card.findViewById(R.id.title)).setText(project.title);
        ((TextView)card.findViewById(R.id.votes)).setText(Integer.toString(project.voteCount));

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView projectCard;

        public ViewHolder(CardView v) {
            super(v);
            projectCard = v;

        }
    }
}
