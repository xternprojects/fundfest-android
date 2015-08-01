package xtern.com.fundfest.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xtern.com.fundfest.API;
import xtern.com.fundfest.Adapters.ProjectRecyclerAdapter;
import xtern.com.fundfest.DataObjects.Project;
import xtern.com.fundfest.Listeners.OnFragmentInteractionListener;
import xtern.com.fundfest.R;

public class ProjectListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String PROJECTS = "PROJECTS";
    private API api;
    private ProjectRecyclerAdapter adapter;
    RecyclerView recyclerView;
    ProjectListAsync projectAsync;

//    public static ProjectListFragment newInstance(ArrayList<Project> projectList) {
//        ProjectListFragment fragment = new ProjectListFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(PROJECTS,projectList);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static ProjectListFragment newInstance(){
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new API();
        projectAsync = new ProjectListAsync();
        projectAsync.execute();

//        if (getArguments() != null) projectList = (ArrayList<Project>)getArguments().getSerializable(PROJECTS);
//        else projectList = new ArrayList<>();


        //setListAdapter(new ProjectListAdapter(projectList,getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ProjectRecyclerAdapter(new ArrayList<Project>());
        recyclerView.setAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.notifyDataSetChanged();
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

    public class ProjectListAsync extends AsyncTask<Void,Void,ArrayList<Project>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //progressDialog = ProgressDialog.show(MainActivity.this,"Retrieving Projects...","Please Wait...");
        }

        @Override
        protected ArrayList<Project> doInBackground(Void... voids) {
            try {return api.getProjects();}
            catch (Exception e){e.printStackTrace();}
            return null; //if failed
        }

        @Override
        protected void onPostExecute(ArrayList<Project> projects) {
            adapter.changeDataSet(projects);
            //progressDialog.hide();
        }
    }
}
