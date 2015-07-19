package xtern.com.fundfest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class ProjectGridFragment extends Fragment {
    private xtern.com.fundfest.OnFragmentInteractionListener mListener;
    private ArrayList<Project> projectList;
    private static final String PROJECTS = "PROJECTS";
    private GridView gridView;

    public static ProjectGridFragment newInstance(ArrayList<Project> projectList) {
        ProjectGridFragment fragment = new ProjectGridFragment();
        Bundle args = new Bundle();
        args.putSerializable(PROJECTS, projectList);
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            projectList = (ArrayList<Project>)getArguments().getSerializable(PROJECTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_grid, container, false);

        gridView = (GridView)view.findViewById(R.id.projectGrid);
        gridView.setAdapter(new ProjectListAdapter(projectList,getActivity()));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_grid, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        if (mListener != null) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
//    }

}
