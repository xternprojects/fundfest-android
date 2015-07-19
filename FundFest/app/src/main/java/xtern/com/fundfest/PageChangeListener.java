package xtern.com.fundfest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by sean.phillips on 7/18/15.
 */
public class PageChangeListener implements ViewPager.OnPageChangeListener {

    ActionBarActivity activity;

    PageChangeListener(ActionBarActivity activity){
        this.activity = activity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        activity.getSupportActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
