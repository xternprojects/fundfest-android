package xtern.com.fundfest;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

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

    public void getProjectList() throws Exception{

        // TODO : convince backend team to switch to Get rather than Post
        // Once Accomplished, will then work on this class
        Request request = new Request.Builder()
                .url("https://fundfest-backend.herokuapp.com/")
                //.post()
                .build();

    }

}
