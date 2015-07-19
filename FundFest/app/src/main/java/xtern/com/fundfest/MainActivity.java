package xtern.com.fundfest;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.FacebookSdk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements OnFragmentInteractionListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ProjectListFragment projectListFragment;
    LogInFragment logInFragment;
    SupportMapFragment mapFragment;
    GoogleApiClient googleApiClient;
    ProjectListAsync listAsync;
    API api = new API();

    ViewPager pager;
    TabAdapter tabAdapter;
    TabListener tabListener;
    ActionBar bar;

    DrawerLayout drawerLayout;
    ListView drawerList;
    ActionBarDrawerToggle drawerToggle;
    String[] drawerItems = {"Map","Settings","Log In"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.mainViewGroup);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerItems));
        drawerList.setOnItemClickListener(new DrawerItemClickListener(drawerLayout, drawerList));

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_menu_white_24dp, R.string.openDesc, R.string.closeDesc);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // initialize the view pager and adapter
        pager = (ViewPager)findViewById(R.id.pager);
        tabListener = new TabListener(pager);

        bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.addTab(bar.newTab().setText("List").setTabListener(tabListener));
        bar.addTab(bar.newTab().setText("Category").setTabListener(tabListener));

        tabAdapter = new TabAdapter(this);

        pager.setAdapter(tabAdapter);
        pager.setOnPageChangeListener(new PageChangeListener(this));

        // initialize the googleClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // asynchronously populate the list
        listAsync = new ProjectListAsync();

        //listAsync.execute();
        //placeMapFragment();
        //placeLoginFragment();
    }

    /**
     * Adds or 'Places' the list fragment into the activity
     */
    private void placeListFragment(ArrayList<Project> projectList){
        projectListFragment = ProjectListFragment.newInstance(projectList);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainViewGroup, projectListFragment);
        transaction.commit();
        pager.setAdapter(tabAdapter);
    }

    private void placeMapFragment(){
        if(mapFragment == null)
            mapFragment = SupportMapFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainViewGroup, mapFragment);
        transaction.commit();
        mapFragment.getMapAsync(this);
    }

    private void placeLoginFragment(){
        if(logInFragment == null)
            logInFragment = LogInFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainViewGroup, logInFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
    }

    //need to call invalidateOptionsMenu first
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(Bundle bundle) {
        // TODO - check to make sure the phone can connect
    }

    @Override
    public void onConnectionSuspended(int i) {
        // TODO
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // TODO
    }

    public class ProjectListAsync extends AsyncTask<Void,Void,ArrayList<Project>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,"Retrieving Projects...","Please Wait...");
        }

        @Override
        protected ArrayList<Project> doInBackground(Void... voids) {
            try {return api.getProjectList();}
            catch (Exception e){e.printStackTrace();}
            return null; //if failed
        }

        @Override
        protected void onPostExecute(ArrayList<Project> projects) {
            placeListFragment(projects);
            progressDialog.hide();
        }
    }


}
