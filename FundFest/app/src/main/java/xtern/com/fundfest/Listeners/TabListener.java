package xtern.com.fundfest.Listeners;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

/**
 * Created by sean.phillips on 7/18/15.
 */
public class TabListener implements TabLayout.OnTabSelectedListener {

    ViewPager pager;

    public TabListener(ViewPager pager){
        this.pager = pager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
