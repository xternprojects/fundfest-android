package xtern.com.fundfest.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import xtern.com.fundfest.Activities.MainActivity;
import xtern.com.fundfest.Fragments.CategoryFragment;
import xtern.com.fundfest.Fragments.ProjectListFragment;

/**
 * Created by sean.phillips on 7/17/15.
 * Adapter for the Main view's tabs
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    FragmentManager manager;

    public TabAdapter(MainActivity activity){
        super(activity.getSupportFragmentManager());
        manager = activity.getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return 3;
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
