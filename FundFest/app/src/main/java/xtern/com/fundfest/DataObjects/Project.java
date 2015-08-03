package xtern.com.fundfest.DataObjects;

import android.location.Location;
import android.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean.phillips on 7/3/15.
 * Data object for a Project
 */
public class Project {

    public String categoryId;
    public String description;
    public String facebookURL;
    public String kickStarterURL;
    public String location;
    public String title;
    public String twitterURL;
    public int voteCount;
    public String objectId;
//    public String[] pics;
//    public String[] vids;


    //TODO : make a json stream reader (?) possibly to be done later

    /**
     * Reads in a Json Object straight from the server and makes
     * a native Project data object
     */
    public static Project newInstance(JSONObject object) throws JSONException{
        Project project = new Project();

        project.categoryId = object.getString("categoryId");
        project.description = object.getString("description");
        project.facebookURL = object.getString("facebookURL");
        project.kickStarterURL = object.getString("kickstarterURL");
        project.location = object.getString("location");
        project.title = object.getString("title");
        project.twitterURL = object.getString("twitterURL");
        project.voteCount = object.getInt("voteCount");
        project.objectId = object.getString("objectId");

        return project;
    }

    public static ArrayList<Project> newList(JSONArray jsonArray) throws JSONException{
        ArrayList<Project> list = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            list.add(newInstance(jsonArray.getJSONObject(i)));
        }
        return list;
    }

    public static ArrayList<Project> newList(String jsonString) throws JSONException{
        return newList(new JSONArray(jsonString));
    }

    public static Project getProject(JSONArray jsonArray) throws  JSONException{
        return newInstance(jsonArray.getJSONObject(0));
    }

}
