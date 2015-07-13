package xtern.com.fundfest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.facebook.FacebookSdk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements OnFragmentInteractionListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    FragmentManager fragmentManager;
    android.support.v4.app.FragmentManager supportFragmentManager;
    ProjectListFragment projectListFragment;
    LogInFragment logInFragment;
    MapFragment mapFragment;
    GoogleApiClient googleApiClient;
    ProjectListAsync listAsync;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        supportFragmentManager = getSupportFragmentManager();
        api = new API();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        listAsync = new ProjectListAsync();
        //listAsync.execute();

        //placeMapFragment();
        placeLoginFragment();
    }

    /**
     * Adds or 'Places' the list fragment into the activity
     */
    private void placeListFragment(ArrayList<Project> projectList){
        if(fragmentManager == null){
            Log.e("ERROR", "in Place List Fragement, fragmentManager is null");
            return;
        }
        projectListFragment = ProjectListFragment.newInstance(projectList);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainViewGroup, projectListFragment);
        transaction.commit();
    }

    private void placeMapFragment(){
        if(mapFragment == null)
            mapFragment = MapFragment.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainViewGroup, mapFragment);
        transaction.commit();
        mapFragment.getMapAsync(this);
    }

    private void placeLoginFragment(){
        if(logInFragment == null)
            logInFragment = LogInFragment.newInstance();
        android.support.v4.app.FragmentTransaction transaction = supportFragmentManager.beginTransaction();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            try {
                return api.getProjectList();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Project> projects) {
            placeListFragment(projects);
            progressDialog.hide();
        }
    }


}
