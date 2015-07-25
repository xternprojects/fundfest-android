package xtern.com.fundfest.Listeners;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import com.facebook.login.LoginFragment;
import com.google.android.gms.maps.SupportMapFragment;

import xtern.com.fundfest.Activities.MainActivity;
import xtern.com.fundfest.Fragments.AboutFragment;
import xtern.com.fundfest.Fragments.LogInFragment;
import xtern.com.fundfest.Fragments.PreferencesFragment;
import xtern.com.fundfest.Fragments.ProjectDisplayFragment;
import xtern.com.fundfest.R;

public class OnNavigationClickListener implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    MainActivity activity;
    MapReadyCallback mapCallback;

    public OnNavigationClickListener(DrawerLayout drawer, MainActivity activity){
        this.drawer = drawer;
        this.activity = activity;
        mapCallback = new MapReadyCallback();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        drawer.closeDrawers();

        switch (menuItem.getItemId()){
            case R.id.projects_item: replaceFragment(ProjectDisplayFragment.newInstance()); return true;
            case R.id.schedule_item: return true;
            case R.id.map_item:
                SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                mapFragment.getMapAsync(mapCallback);
                replaceFragment(mapFragment);return true;
            case R.id.events_item: return true;
            case R.id.about_us_item: replaceFragment(AboutFragment.newInstance()); return true;
            case R.id.preferences_item: return true;
            case R.id.log_in_item: replaceFragment(LogInFragment.newInstance()); return true;
        }

        menuItem.setChecked(true);

        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentParent, fragment);
        transaction.commit();
    }
}
