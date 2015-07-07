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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ProjectListFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    ProjectListFragment projectListFragment;
    ProjectListAsync listAsync;
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        api = new API();

        listAsync = new ProjectListAsync();
        listAsync.execute();
    }

    /**
     * Adds or 'Places' the list fragment into the activity
     */
    private void placeListFragment(ArrayList<Project> projectList){
        if(fragmentManager == null){
            Log.e("ERROR", "in Place List Fragement, fragmentManager is null");
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        projectListFragment = ProjectListFragment.newInstance(projectList);
        transaction.add(R.id.mainViewGroup, projectListFragment);
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
