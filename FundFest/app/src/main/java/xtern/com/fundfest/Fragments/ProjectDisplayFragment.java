package xtern.com.fundfest.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xtern.com.fundfest.Activities.MainActivity;
import xtern.com.fundfest.Adapters.TabAdapter;
import xtern.com.fundfest.Listeners.OnFragmentInteractionListener;
import xtern.com.fundfest.R;

public class ProjectDisplayFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    TabLayout tabLayout;
    ViewPager pager;

    TabLayout.ViewPagerOnTabSelectedListener tabSelectedListener;
    TabLayout.TabLayoutOnPageChangeListener pageChangeListener;

    public static ProjectDisplayFragment newInstance() {
        ProjectDisplayFragment fragment = new ProjectDisplayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectDisplayFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project_display, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        String[] tabNames = getResources().getStringArray(R.array.tab_items);
        for(String name : tabNames)
            tabLayout.addTab(tabLayout.newTab().setText(name));

        pageChangeListener  = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        tabSelectedListener = new TabLayout.ViewPagerOnTabSelectedListener(pager);

        pager.addOnPageChangeListener(pageChangeListener);
        tabLayout.setOnTabSelectedListener(tabSelectedListener);

        pager.setAdapter(new TabAdapter((MainActivity)getActivity()));
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
