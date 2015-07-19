package xtern.com.fundfest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.List;

/**
 * Created by sean.phillips on 7/17/15.
 * Adapter for the Main view's tabs
 */
public class TabAdapter extends FragmentPagerAdapter {

    private Context context;
    private ActionBar actionBar;

    FragmentManager manager;

    public TabAdapter(ActionBarActivity activity){
        super(activity.getSupportFragmentManager());
        context = activity;
        actionBar = activity.getSupportActionBar();
        manager = activity.getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ProjectListFragment.newInstance();
            case 1:
                return CategoryFragment.newInstance();
            default:
                return ProjectListFragment.newInstance();
        }
    }
}
