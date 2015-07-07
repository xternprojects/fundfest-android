package xtern.com.fundfest;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sean.phillips on 7/3/15.
 *
 * Class used to call the server for information.
 * Uses OkHttp for calls
 */

public class API {

    OkHttpClient client;

    public API(){
        client = new OkHttpClient();
    }

    public ArrayList<Project> getProjectList() throws Exception{
        Request request = new Request.Builder()
                .url("http://fundfest-backend.herokuapp.com/get_all_projects")
                .build();

        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException();

        String responseStr = response.body().string();

        return Project.newList(responseStr);
    }

}
